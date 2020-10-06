package com.biogardxcompany.biogardx;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.biogardxcompany.biogardx.Actuators.Actuators;
import com.biogardxcompany.biogardx.ConnectionServices.BluetoothConnectionService;
import com.biogardxcompany.biogardx.ConnectionServices.BluetoothDevicesDialogueFragment;
import com.biogardxcompany.biogardx.Measurment.Measurments;
import com.biogardxcompany.biogardx.ConnectionServices.FirebaseService;
import com.biogardxcompany.biogardx.ui.analytics.AnalyticsFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.biogardxcompany.biogardx.R.drawable.ic_bluetooth_connected_24px;
import static com.biogardxcompany.biogardx.R.drawable.ic_bluetooth_disabled_24px;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                               BluetoothDevicesDialogueFragment.BluetoothDevicesListener {
    // Constants
    private static final String TAG = "MainActivity";
    private static final String ANONYMOUS = "anonymous";
    private static final int RC_SIGN_IN = 1;

    // Views variables
    private TextView mUserNameTV ;
    private TextView mUserEmailTV ;
    private ImageView mProfilePhoto ;
    private Spinner mSpinner;
    private NavController navController;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private View mainLayout;
    private MenuItem item , item2 ;
    private Menu menu;

    //private Measurments measurments = Measurments.getInstance();
    private Actuators mActuators ;


    // Services instances
    private FirebaseService mFirebaseService ;
    private BluetoothConnectionService mBluetoothConnection = null;
    private BluetoothDevice device ;
    private Measurments measurments;

    // variables
    private StringBuffer mOutStringBuffer;
    int []mes = new int[7];

    Bundle bundle = new Bundle();
    // set Fragmentclass Arguments
    AnalyticsFragment analyticsFragment = new AnalyticsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseService = FirebaseService.getInstance();
        mActuators = Actuators.getInstance();


        //FloatingActionButton fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mainLayout = findViewById(R.id.drawer_layout);
        item = findViewById(R.id.action_state);


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_analytics, R.id.nav_control_panel)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);







        //Initialize references to views
        mUserNameTV = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        mUserEmailTV = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
      //  mProfilePhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_photo);

        // firebase service instance
        mFirebaseService = new FirebaseService();
        mFirebaseService.mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                  //  Toast.makeText(MainActivity.this, " You're signed in ! Welcome to chat app ", Toast.LENGTH_SHORT).show();
                    onSignedInInitialize(user.getDisplayName(), user.getEmail(), "user.getPhotoUrl().toString())");
                    updateNavHeaderUi();
                    if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivity(enableIntent);
                    }
                    //Toast.makeText(MainActivity.this ,"name: "+mFirebaseService.getUserName(),Toast.LENGTH_SHORT).show();


                } else {
                    // No user is signed in
                    onSignedOutCleanUp();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))

                                   .setTheme(R.style.LoginTheme)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

       // mAnalyticsViewModel.init();


    }

    private void onSignedOutCleanUp() {
        mFirebaseService.setmUserName(ANONYMOUS);
        mFirebaseService.setUserEmail(ANONYMOUS);
        mFirebaseService.setProfilePhotoUrl(ANONYMOUS);
        mFirebaseService.detachMeasurementsDatabaseChildListener();
        mFirebaseService.detachCommandsDatabaseChildListener();
    }

    private void onSignedInInitialize(String name, String email, String photoUrl) {
       mFirebaseService.setmUserName(name);
       mFirebaseService.setUserEmail(email);
       mFirebaseService.setProfilePhotoUrl(photoUrl);
        mFirebaseService.getDatabaseReference().child("Users").setValue(new FirebaseService.Users(name,email,mFirebaseService.getUserId()));
      //mFirebaseService.attachDataBaseChildListener(); ;
    }
    private void updateNavHeaderUi(){
        mUserNameTV.setText(mFirebaseService.getUserName());
        mUserEmailTV.setText(mFirebaseService.getUserEmail());
//        boolean isPhoto = mFirebaseService.getProfilePhotoUrl() != null;
//        if (isPhoto) {
//
//            Glide.with(mProfilePhoto.getContext())
//                    .load(mFirebaseService.getProfilePhotoUrl())
//                    .into(mProfilePhoto);
//        }
    }
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new BluetoothDevicesDialogueFragment(getResources().getString(R.string.bt_dialog_title),null);
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }
    @Override
    public void onStart() {
        super.onStart();

         if (mBluetoothConnection == null) {
            mBluetoothConnection = new BluetoothConnectionService( mHandler , mainLayout);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mFirebaseService.getFirebaseAuth() != null) {
            mFirebaseService.getFirebaseAuth().removeAuthStateListener(mFirebaseService.mAuthStateListener);
        }
        Log.e(TAG,"main activity paused");
         mFirebaseService.detachMeasurementsDatabaseChildListener();
        mFirebaseService.detachCommandsDatabaseChildListener();
    }
    @Override
    protected void onResume() {
        super.onResume();

        mFirebaseService.getFirebaseAuth().addAuthStateListener(mFirebaseService.mAuthStateListener);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothConnection != null) {
            mBluetoothConnection.stop();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "sign in canceled !", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu =menu ;
        item = menu.findItem(R.id.action_state);
        item2 = menu.findItem(R.id.action_conncet);
        setStatus(mBluetoothConnection.getState());

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_conncet:
                if(mBluetoothConnection.getState()== BluetoothConnectionService.STATE_NONE) {
                    showNoticeDialog();
                }
                    else{
                        mBluetoothConnection.stop();
                }

                    return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_analytics:

                navController.navigate(R.id.nav_analytics);
                break;
            case R.id.nav_control_panel:

                navController.navigate(R.id.nav_control_panel);
                break;

            case R.id.nav_help_feedback :
                mFirebaseService.getDatabaseReference().child("Measurements").push().setValue(new Measurments(String.valueOf(Math.round(Math.random()*100)),"60",
                        "40",null, "25","37","0",
                        "150","45","7.1"));

                Toast.makeText(MainActivity.this, "feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about :
                /*mFirebaseService.getDatabaseReference().child("Measurements").push().setValue(new Measurments(
                        String.valueOf(Math.round(Math.random()*100)),"60",
                        "43",null, "43","43","43",
                        "43",null,"7"));*/
                //mFirebaseService.getDatabaseReference().child("Measurements").removeValue();
                Toast.makeText(MainActivity.this, "About BioGardX", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_sign_out :
                AuthUI.getInstance().signOut(this);
                Toast.makeText(MainActivity.this, "Signing out", Toast.LENGTH_SHORT).show();
                break;
        }
         drawer.closeDrawer(GravityCompat.START);
        return true ;
    }


    @Override
    public void onDialogDeviceConnect(BluetoothDevice device) {
         this.device = device;
        Snackbar.make(mainLayout, device.getName() + " is connecting ...",
                Snackbar.LENGTH_SHORT).show();
        connectDevice(device);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
    private void connectDevice(BluetoothDevice device) {

        mBluetoothConnection.connect(device);
    }
    public void sendMessage(byte[] message) {
        // Check that we're actually connected before trying anything
        if (mBluetoothConnection.getState() != mBluetoothConnection.STATE_CONNECTED) {
            Snackbar.make(mainLayout, R.string.not_connected,
                    Snackbar.LENGTH_INDEFINITE).setAction("Try again", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "trying to reconnect");
                    if(device!=null){
                    connectDevice(device);}
                    else{
                        Snackbar.make(mainLayout, "No device is connected !",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
            }).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
           // byte[] send = message.getBytes();
            mBluetoothConnection.write(message);

            // Reset out string buffer to zero and clear the edit text field
            // mOutStringBuffer.setLength(0);
            //mOutEditText.setText(mOutStringBuffer);
        }
    }
    public void receivedMsg (String s , int len){
        //inputMsg.setText(s);
        Log.i(TAG, "sent message :"+ s + " number of bytes : "+ len);
    }
    public void makeSnackbar (String msg ,boolean hasAction){
        if(hasAction){
            Snackbar.make(mainLayout, msg,
                    Snackbar.LENGTH_INDEFINITE).setAction("Try again", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "trying to reconnect");
                        connectDevice(device);

                }
            }).setBackgroundTint(getResources().getColor(R.color.snackbar_bg_color))
                    .setActionTextColor(getResources().getColor(R.color.snackbar_tx_color))
                    .show();

        }else{
            Snackbar.make(mainLayout, msg,
                    Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.snackbar_bg_color))
                    .setActionTextColor(getResources().getColor(R.color.snackbar_tx_color))
                    .show();
        }
    }

    public void setStatus(int  state){
        switch (state) {
            case BluetoothConnectionService.STATE_CONNECTED:
                //setStatus(R.string.title_connected);
                item.setIcon(ic_bluetooth_connected_24px);
                item2.setTitle(R.string.disconnect);
                break;
            case BluetoothConnectionService.STATE_CONNECTING:
                //setStatus(R.string.title_not_connected);
                break;
            case BluetoothConnectionService.STATE_LISTEN:
            case BluetoothConnectionService.STATE_NONE:
                //setStatus(R.string.title_not_connected);
                item.setIcon(ic_bluetooth_disabled_24px);
                item2.setTitle(R.string.connect_to_bt_device);
                break;
        }
    }

    public Measurments getData (){
        return measurments;
    }

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    setStatus(msg.arg1);
                    break;
                case Constants.MESSAGE_WRITE:
//                    byte[] writeBuf = (byte[]) msg.obj;
//                    // construct a string from the buffer
//                    String writeMessage = new String(writeBuf);
//                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                   // String readMessage =(String) msg.obj;

//                    tem = readBuf[0];
////                    hum = readBuf[1];
////                    tem_out = readBuf[2];
////                    hum_out = readBuf[4];

                    for(int i  = 0 ; i< readBuf.length; i ++){
                        if(((char)readBuf[i]) == '-') readBuf[i] = 0 ;
                    }
                    mFirebaseService.getDatabaseReference()
                            .child("Measurements")
                            .push()
                            .setValue(new Measurments(String.valueOf((( 0x00) << 8) | (readBuf[2] & 0xff)),String.valueOf((( 0x00) << 8) | (readBuf[1] & 0xff)),
                                                      String.valueOf((( 0x00) << 8) | (readBuf[4] & 0xff)), null,
                                                      String.valueOf((( 0x00) << 8) | (readBuf[2] & 0xff)),String.valueOf((( 0x00) << 8) | (readBuf[1] & 0xff)),
                                                      String.valueOf((( 0x00) << 8) | (readBuf[5] & 0xff)),String.valueOf((( 0x00) << 8) | (readBuf[6] & 0xff)),
                                         null,null));



                    String s = "tem: "+ String.valueOf(readBuf[0])+ " hum: "+String.valueOf(readBuf[1])+" out tem: "+String.valueOf(readBuf[2])
                            + " out hum: "+String.valueOf(readBuf[3]);
                   Log.e(TAG, s.toString());
                  //  receivedMsg(s,msg.arg1);


                    // mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
//                    // save the connected device's name
                    makeSnackbar(msg.getData().getString(Constants.DEVICE_NAME),false);

                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
//                        Toast.makeText(MainActivity.this , msg.getData().getString(Constants.TOAST),
//                                Toast.LENGTH_SHORT).show();
                        makeSnackbar(msg.getData().getString(Constants.TOAST),true);

                    }
                    break;
            }
        }
    };
}






