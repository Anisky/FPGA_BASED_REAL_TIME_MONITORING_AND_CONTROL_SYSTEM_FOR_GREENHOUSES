package com.biogardxcompany.biogardx.ConnectionServices;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.biogardxcompany.biogardx.Constants;
import com.biogardxcompany.biogardx.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class BluetoothConnectionService {

        // Debugging
        private static final String TAG = "BluetoothChatService";
        // Name for the SDP record when creating server socket
        private static final String AppName = "BluetoothChat";
        // Unique UUID for this application
        private static final UUID MY_UUID =
                UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final int REQUEST_ENABLE_BT = 1 ;

    // Member fields
        private final BluetoothAdapter mAdapter;
        private final Handler mHandler;

        private ConnectThread mConnectThread;
        private ConnectedThread mConnectedThread;
        private int mState;
        private int mNewState;
        private BufferedReader input;
        // Constants that indicate the current connection state
        public static final int STATE_NONE = 0;       // we're doing nothing
        public static final int STATE_LISTEN = 1;     // now listening for incoming connections
        public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
        public static final int STATE_CONNECTED = 3;  // now connected to a remote device

        public static BluetoothConnectionService instance;

        private View  root ;

    public BluetoothConnectionService() {

        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        mNewState = mState;
        mHandler = new Handler();
    }

    /**
         * Constructor. Prepares a new BluetoothChat session.
         *
       //  * @param context The UI Activity Context
       //  * @param handler A Handler to send messages back to the UI Activity
         */

        public BluetoothConnectionService(Handler handler,View root) {
            mAdapter = BluetoothAdapter.getDefaultAdapter();
            mState = STATE_NONE;
            mNewState = mState;
            mHandler = handler;
            this.root = root ;
        }

        /**
         * Update UI title according to the current state of the chat connection
         */
        private synchronized void updateUserInterfaceTitle() {
            mState = getState();
            Log.d(TAG, "updateUserInterfaceTitle() " + mNewState + " -> " + mState);
            mNewState = mState;

            // Give the new state to the Handler so the UI Activity can update
            mHandler.obtainMessage(Constants.MESSAGE_STATE_CHANGE, mNewState, -1).sendToTarget();

        }

        /**
         * Return the current connection state.
         */
        public synchronized int getState() {
            return mState;
        }

        /**
         * Start the ConnectThread to initiate a connection to a remote device.
         *
         * @param device The BluetoothDevice to connect
         */
        public synchronized void connect(BluetoothDevice device) {
            Log.d(TAG, "connect to: " + device);

            // Cancel any thread attempting to make a connection
            if (mState == STATE_CONNECTING) {
                if (mConnectThread != null) {
                    mConnectThread.cancel();
                    mConnectThread = null;
                }
            }

            // Cancel any thread currently running a connection
            if (mConnectedThread != null) {
                mConnectedThread.cancel();
                mConnectedThread = null;
            }

            // Start the thread to connect with the given device
            mConnectThread = new ConnectThread(device);
            mConnectThread.start();
            // Update UI title
            updateUserInterfaceTitle();
        }

        /**
         * Start the ConnectedThread to begin managing a Bluetooth connection
         *
         * @param socket The BluetoothSocket on which the connection was made
         * @param device The BluetoothDevice that has been connected
         */
        public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
            Log.d(TAG, "connected, " );

            // Cancel the thread that completed the connection
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }

            // Cancel any thread currently running a connection
            if (mConnectedThread != null) {
                mConnectedThread.cancel();
                mConnectedThread = null;
            }



            // Start the thread to manage the connection and perform transmissions
            mConnectedThread = new ConnectedThread(socket);
            mConnectedThread.start();
            Log.d(TAG, "device connected :"+device.getName() + " " + device.getAddress());
            // Send the name of the connected device back to the UI Activity
           Message msg = mHandler.obtainMessage(Constants.MESSAGE_DEVICE_NAME);
            Bundle bundle = new Bundle();
           bundle.putString(Constants.DEVICE_NAME, device.getName()+": device connected");
            msg.setData(bundle);
            mHandler.sendMessage(msg);
            // Update UI title
            updateUserInterfaceTitle();
        }

        /**
         * Stop all threads
         */
        public synchronized void stop() {
            Log.d(TAG, "stop");

            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }

            if (mConnectedThread != null) {
                mConnectedThread.cancel();
                mConnectedThread = null;
            }


            mState = STATE_NONE;
            // Update UI title
            updateUserInterfaceTitle();
        }
        /**
         * Write to the ConnectedThread in an unsynchronized manner
         *
         * @param out The bytes to write
         * @see ConnectedThread#write(byte[])
         */
        public void write(byte[] out) {
            // Create temporary object
            ConnectedThread r;
            // Synchronize a copy of the ConnectedThread
            synchronized (this) {
                if (mState != STATE_CONNECTED) return;
                r = mConnectedThread;
            }
            // Perform the write unsynchronized
            r.write(out);
        }
        /**
         * Indicate that the connection attempt failed and notify the UI Activity.
         */
        private void connectionFailed() {
            // Send a failure message back to the Activity
            Message msg = mHandler.obtainMessage(Constants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TOAST, "Unable to connect device");
            msg.setData(bundle);
            mHandler.sendMessage(msg);
            Log.d(TAG, " Unable to connect device 1.");
            mState = STATE_NONE;
            // Update UI title
            updateUserInterfaceTitle();

            // Start the service over to restart listening mode
           // BluetoothConnectionService.this.connect();
        }
        /**
         * Indicate that the connection was lost and notify the UI Activity.
         */
        private void connectionLost() {
            // Send a failure message back to the Activity
            Message msg = mHandler.obtainMessage(Constants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TOAST, "Device connection was lost");
            msg.setData(bundle);
            mHandler.sendMessage(msg);
            Log.d(TAG, " Device connection was lost");
            mState = STATE_NONE;
            // Update UI title
            updateUserInterfaceTitle();

            // Start the service over to restart listening mode
//            BluetoothConnectionService.this.start();
        }

        /**
         * This thread runs while attempting to make an outgoing connection
         * with a device. It runs straight through; the connection either
         * succeeds or fails.
         */
        private class ConnectThread extends Thread {
            private final BluetoothSocket mmSocket;
            private final BluetoothDevice mmDevice;


            public ConnectThread(BluetoothDevice device) {
                mmDevice = device;
                BluetoothSocket tmp = null;

                // Get a BluetoothSocket for a connection with the
                // given BluetoothDevice
                try {

                        tmp = device.createRfcommSocketToServiceRecord(MY_UUID);

                } catch (IOException e) {
                    Log.e(TAG,  "create() failed", e);
                }
                mmSocket = tmp;
                mState = STATE_CONNECTING;
            }

            public void run() {
                Log.i(TAG, "BEGIN mConnectThread : "+ mmDevice.getAddress() );
                setName("ConnectThread" );

                // Always cancel discovery because it will slow down a connection
                mAdapter.cancelDiscovery();

                // Make a connection to the BluetoothSocket
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    mmSocket.connect();
                    //input = new BufferedReader(new InputStreamReader(mmSocket.getInputStream()));


                    Log.e(TAG, " socket connect");

                } catch (IOException e) {
                    // Close the socket
                    try {
                        mmSocket.close();
                    } catch (IOException e2) {
                        Log.e(TAG, "unable to close() socket during connection failure", e2);
                    }
                    connectionFailed();
                    return;
                }

                // Reset the ConnectThread because we're done
                synchronized (BluetoothConnectionService.this) {
                    mConnectThread = null;
                }

                // Start the connected thread
                connected(mmSocket, mmDevice);
            }

            public void cancel() {
                try {
                    mmSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "close() of connect socket failed", e);
                }
            }
        }

        /**
         * This thread runs during a connection with a remote device.
         * It handles all incoming and outgoing transmissions.
         */
        private class ConnectedThread extends Thread {
            private final BluetoothSocket mmSocket;
            private final InputStream mmInStream;
            private final OutputStream mmOutStream;

            public ConnectedThread(BluetoothSocket socket) {
                Log.d(TAG, "create ConnectedThread " );
                mmSocket = socket;
                InputStream tmpIn = null;
                OutputStream tmpOut = null;

                // Get the BluetoothSocket input and output streams
                try {

                    tmpIn = socket.getInputStream();

                    tmpOut = socket.getOutputStream();
                } catch (IOException e) {
                    Log.e(TAG, "temp sockets not created", e);
                }

                mmInStream = tmpIn;
                mmOutStream = tmpOut;
                mState = STATE_CONNECTED;
            }

            public void run() {
                Log.i(TAG, "BEGIN mConnectedThread ..... state : "+mState);
                byte[] buffer = new byte[1024];
                int bytes;
                StringBuilder readMessage  = new StringBuilder();
                // Keep listening to the InputStream while connected
                while (mState == STATE_CONNECTED) {
                    try {
                        // Read from the InputStream
                        //input = new BufferedReader(new InputStreamReader(mmInStream));

                        bytes = mmInStream.read(buffer);
                        String read = new String(buffer, 0, bytes);



                        //readMessage.append(read);
//                        for(int i=0; i<bytes;i++){
//                            readMessage.append(Integer.valueOf(buffer[i]));
//                        }
                        if (buffer[0]!=0x02) {
                            mHandler.obtainMessage(Constants.MESSAGE_READ, bytes, -1, buffer)
                                    .sendToTarget();
                    }

//                        Log.e(TAG, readMessage.toString());
//                        readMessage.setLength(0);
//                        if (read.contains("\n")) {
//                            // Send the obtained bytes to the UI Activity
//                            mHandler.obtainMessage(Constants.MESSAGE_READ, bytes, -1, readMessage.toString())
//                                    .sendToTarget();
//                            readMessage.setLength(0);
//                        }



                    } catch (IOException e) {
                        Log.e(TAG, "disconnected", e);
                        connectionLost();
                        break;
                    }
                }
            }

            /**
             * Write to the connected OutStream.
             *
             * @param buffer The bytes to write
             */
            public void write(byte[] buffer) {
                try {
                    mmOutStream.write(buffer);
                    // Share the sent message back to the UI Activity
                    mHandler.obtainMessage(Constants.MESSAGE_WRITE, -1, -1, buffer)
                            .sendToTarget();
                    Log.i(TAG, " sent msg : "+ buffer.toString());
                } catch (IOException e) {
                    Log.e(TAG, "Exception during write", e);
                }
            }

            public void cancel() {
                try {
                    mmSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "close() of connect socket failed", e);
                }
            }
        }

    public void enableBluetooth(){
        if(mAdapter!=null) {
            if (!mAdapter.isEnabled()) {
                mAdapter.enable();
            }
        }
    }

    public void disableBluetooth() {
        if (mAdapter != null) {
            if (mAdapter.isEnabled()) {
                mAdapter.disable();
            }
        }
    }


}



