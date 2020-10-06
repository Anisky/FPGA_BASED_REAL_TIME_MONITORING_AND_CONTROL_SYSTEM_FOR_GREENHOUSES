package com.biogardxcompany.biogardx.ConnectionServices;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.biogardxcompany.biogardx.MainActivity;
import com.biogardxcompany.biogardx.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static android.content.Context.LOCATION_SERVICE;

public class BluetoothDevicesDialogueFragment extends DialogFragment {

    private static final String TAG = "Bluetooth fragment ";


    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    private String mMessage = "";
    private String mTitle;
    private int pos ;

    private Switch swOnOffBt ;
    private Button btDiscover , btScan ;
    private ConstraintLayout conLayDev ;
    private View mainLayout;

    private BluetoothConnectionService mBluetoothService;
    private DeviceListAdapter mDeviceListAdapter;
    private ArrayList<BluetoothDevice> mAvailableDevicesArrayList;
    private ArrayList<BluetoothDevice> mPairedDevicesArrayList;
    public ListView mAvailableDevicesListView, mPairedDevicesListView;
    private BluetoothDevicesListener listener;

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,mAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive , STATE OFF");
                        Toast.makeText(getContext(), "onReceive , STATE OFF", Toast.LENGTH_SHORT).show();
                        swOnOffBt.setChecked(false);
                        conLayDev.setVisibility(View.INVISIBLE);
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "onReceive , STATE TURNING OFF");
                        Toast.makeText(getContext(), "onReceive , STATE TURNING OFF", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "onReceive , STATE ON");
                        Toast.makeText(getContext(), "onReceive , STATE ON", Toast.LENGTH_SHORT).show();

                        swOnOffBt.setChecked(true);
                        conLayDev.setVisibility(View.VISIBLE);
                        showPairedDevices();

                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "onReceive , STATE TURNING ON");
                        Toast.makeText(getContext(), "onReceive , STATE TURNING ON", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        }
    };

    // Create a BroadcastReceiver for ACTION_DISCOVERABLE.
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive , SCAN MODE");
            if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)) {
                //Log.d(TAG, "onReceive , SCAN MODE");

                int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR);
                switch (mode){
                    // Device is in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE :
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Enabled.");
                        break;
                    // Device is not in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Enabled, Able to receive connections.");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Enabled, not able to receive connections.");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "mBroadcastReceiver2: Connecting ....");
                        Toast.makeText(getContext(),"Bluetooth Connecting ...",Toast.LENGTH_SHORT);
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "mBroadcastReceiver2: Connected.");
                        Toast.makeText(getContext(),"Bluetooth Connected",Toast.LENGTH_SHORT);
                        break;
                }
            }
        }
    };
    // Create a BroadcastReceiver for listing devices that are not paired yet.
    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (BluetoothDevice.ACTION_FOUND.equals(action)){

                makingScanedDevicesList(intent);

            }
        }
    };
    // Create a BroadcastReceiver for ACTION_BOND.
    private final BroadcastReceiver mBroadcastReceiver4 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 3 cases
                // case 1: bonded already
                if(mDevice.getBondState()== BluetoothDevice.BOND_BONDED ){
                    Log.d(TAG, "Broadcast Receiver 4 : BOND_BONDED.");
                    mAvailableDevicesArrayList.remove(pos);
                    showAvailableDevicesList();
                    showPairedDevices();

                }
                // case 2: creating a bond
                if(mDevice.getBondState()== BluetoothDevice.BOND_BONDING ){
                    Log.d(TAG, "Broadcast Receiver 4 : BOND_BONDING.");
                }
                // case 3: breaking a bond
                if(mDevice.getBondState()== BluetoothDevice.BOND_NONE ){
                    Log.d(TAG, "Broadcast Receiver 4 : BOND_NONE.");
                }

            }
        }
    };


    public BluetoothDevicesDialogueFragment(String title , String message) {
      mMessage = message;
       mTitle = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view  = inflater.inflate(R.layout.bluetooth_dialog_fragment,null);

        mBluetoothService = new BluetoothConnectionService();
        swOnOffBt = view.findViewById(R.id.sw_bt_on_off);
        btDiscover=view.findViewById(R.id.discoverable_btn);
        btScan=view.findViewById(R.id.bt_scan);

        conLayDev = view.findViewById(R.id.device_layout);
        mainLayout = view.findViewById(R.id.dialog_layout);


        mAvailableDevicesListView =view.findViewById(R.id.lst_available_devices);
        mPairedDevicesListView = view.findViewById(R.id.lst_paired_devices);

        swOnOffBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean btnState = swOnOffBt.isChecked();
                turnOnOffBluetooth(btnState);
            }
        });

        btDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               discoverableBT();
            }
        });
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discoverBT();
            }
        });
        builder.setView(view)
                .setTitle(mTitle)
                .setMessage(mMessage);

        mPairedDevicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onDialogDeviceConnect(mPairedDevicesArrayList.get(position));


                dismiss();
            }
        });
        mAvailableDevicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                // first cancel discovery because it is memory intensive
                mAdapter.cancelDiscovery();

                Log.d(TAG, "OnItemClicked : you clicked on a device.");

                String deviceName = mAvailableDevicesArrayList.get(position).getName();
                String deviceAddress = mAvailableDevicesArrayList.get(position).getAddress();

                Log.d(TAG, "Trying to pair with : "+deviceName);
                mAvailableDevicesArrayList.get(position).createBond();
            }
        });

        IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver,BTIntent);


        IntentFilter bondIntent = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver4,bondIntent);

        mAvailableDevicesArrayList = new ArrayList<>();
        return builder.create();
    }

    public interface BluetoothDevicesListener {
        public void onDialogDeviceConnect(BluetoothDevice device );
        public void onDialogNegativeClick(DialogFragment dialog);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BluetoothDevicesListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must impliment NoticeDailogLister");
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver2);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver3);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver4);
    }

    @Override
    public void onResume() {
        super.onResume();
        swOnOffBt.setChecked(mAdapter.isEnabled());
        if(mAdapter.isEnabled()){
            conLayDev.setVisibility(View.VISIBLE);
            showPairedDevices();
        }
        else {
            conLayDev.setVisibility(View.INVISIBLE);
        }
    }

    public void turnOnOffBluetooth(Boolean btnState) {

            if (btnState) {  // if the switch button is on
                mBluetoothService.enableBluetooth();
               // Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
               // startActivityForResult(enableBT,REQUEST_ENABLE_BT);
        }
        else {  // if the Bluetooth is on

               mBluetoothService.disableBluetooth();
            }
        }
    public void showPairedDevices (){

            Set<BluetoothDevice> PD = mAdapter.getBondedDevices();

            mPairedDevicesArrayList = new ArrayList();
            if (PD.size() > 0) {
                for (BluetoothDevice BD : PD) {
                    mPairedDevicesArrayList.add(BD);
                }
            }


        mDeviceListAdapter = new DeviceListAdapter(getContext(), R.layout.device_adapter_view, mPairedDevicesArrayList);
        mPairedDevicesListView.setAdapter(mDeviceListAdapter);
    }
    public void discoverableBT (){
        Log.d(TAG, "reciever2 : Making Bluetooth discoverable for 300s.");

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
        IntentFilter discoverIntent = new IntentFilter(mAdapter.ACTION_SCAN_MODE_CHANGED);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver2,discoverIntent);

    }
    private void makingScanedDevicesList(Intent intent) {
        ArrayList<BluetoothDevice> mBTDev = new ArrayList<>();;
        int count = 0 ;
        BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
        Log.d(TAG, "onReceive: device  ");
        if(mAvailableDevicesArrayList.size()<= 0 ){
            mAvailableDevicesArrayList.add(device);
            Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
            count ++ ;
        }
        else {
            for (BluetoothDevice dev : mAvailableDevicesArrayList) {
                // Log.d(TAG, "onReceive: entering loop ");
                if(dev != null ) {
                    if ((dev.getName().equals(device.getName())) && (dev.getAddress().equals(device.getAddress()))) {
                        Log.d(TAG, "onReceive: device duplicated ");
                        count ++ ;
                        break;
                    }
                }
            }
            if( count == 0 && device != null){
                mAvailableDevicesArrayList.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
            }

        }
        showAvailableDevicesList();
//
    }
    void showAvailableDevicesList(){
        for(int i =0; i < mAvailableDevicesArrayList.size(); i ++ ){
            for(BluetoothDevice dev1 : mPairedDevicesArrayList){
                if(mAvailableDevicesArrayList.get(i).getAddress().equals(dev1.getAddress())){
                    mAvailableDevicesArrayList.remove(i);
                    break;
                }
            }
        }


        mDeviceListAdapter = new DeviceListAdapter(getContext(), R.layout.device_adapter_view, mAvailableDevicesArrayList);
        mAvailableDevicesListView.setAdapter(mDeviceListAdapter);
    }
    public void discoverBT() {
        Log.e(TAG, "btnDiscover: Looking for unpaired devices.");
        mAvailableDevicesArrayList.clear();
        showAvailableDevicesList();

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            useLocationDialoge();
            Log.e(TAG, "location  : location turned off ");
        }

        if (mAdapter.isDiscovering()) {
            mAdapter.cancelDiscovery();
            Log.e(TAG, "btnDiscover: Canceling discovery.");
            checkGpsPermissions();


            mAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getActivity().registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
        if (!mAdapter.isDiscovering()) {
            checkGpsPermissions();

            mAdapter.startDiscovery();
            Log.e(TAG, "btnDiscover: Start Discovery.");
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getActivity().registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
    }
    public void locationSettings(){

        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsIntent);

    }
    private void checkGpsPermissions() {
        int permissionCheck = getActivity().checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if (permissionCheck != 0) {
            requestLocationPermissions();


        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");

        }
    }
    public void requestLocationPermissions(){

        if(ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                useLocationDialoge();
            }

        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)) {


                Snackbar.make(mainLayout, R.string.permission_dialog_message,
                    Snackbar.LENGTH_INDEFINITE).setAction("Trun it on", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001); }}).show();
        }
        else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            Snackbar.make(mainLayout, "Cannot Scan Other Bluetooth Devices", Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1001) {
            // Request for camera permission.
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    useLocationDialoge();
                }
            } else {
                // Permission request was denied.
                Snackbar.make(mainLayout,"Location permission denied",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }
    public void useLocationDialoge() {
        Log.e(TAG, "location  : location turned on ");
        Snackbar.make(mainLayout, R.string.permission_dialog_message,
                Snackbar.LENGTH_INDEFINITE).setAction("Trun it on", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Request the permission
                locationSettings();
                Log.e(TAG, "location  : location turned on ");
            }
        }).show();
}
}



