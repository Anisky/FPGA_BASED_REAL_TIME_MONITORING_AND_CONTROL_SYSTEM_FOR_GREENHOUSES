package com.biogardxcompany.biogardx.ConnectionServices;

import android.util.Log;

import com.biogardxcompany.biogardx.Actuators.Actuators;
import com.biogardxcompany.biogardx.Actuators.AirConditioner;
import com.biogardxcompany.biogardx.Actuators.Curtains;
import com.biogardxcompany.biogardx.Actuators.Irrigation;
import com.biogardxcompany.biogardx.Actuators.Lights;
import com.biogardxcompany.biogardx.Actuators.Roof;
import com.biogardxcompany.biogardx.Measurment.Measurments;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.lifecycle.MutableLiveData;

public class FirebaseService {
    private String mUserName = "";
    private String mUserEmail = "";
    private String mProfilePhotoUrl = "";



    private String userId ;

    // firebase instances variables
    private FirebaseAuth mFirebaseAuth;
    public FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    public ChildEventListener mMeasurementsChildEventListener;
    public ChildEventListener mCommandsChildEventListener;

    private static FirebaseService instance;
    final MutableLiveData<Measurments> liveData = new MutableLiveData<>();
    final MutableLiveData<ArrayList<Object>>liveCmd = new MutableLiveData<>();

    ArrayList<Object> cmdList = new ArrayList<>(5);


    // data instances variables
    private Measurments measurments ;



    public static FirebaseService getInstance() {
        if (instance == null) {
            instance = new FirebaseService();
        }
        return instance;
    }

    public FirebaseService() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("GreenHouse_01");

        mFirebaseAuth = FirebaseAuth.getInstance();
        measurments = new Measurments();

        cmdList.add(new AirConditioner());
        cmdList.add(new Lights());
        cmdList.add(new Irrigation());
        cmdList.add(new Roof());
        cmdList.add(new Curtains());


    }

    public void setmUserName(String mName) {
        this.mUserName = mName;
    }

    public void setUserEmail(String mEmail) {
        this.mUserEmail = mEmail;
    }

    public void setProfilePhotoUrl(String ProfilePhotoUrl) {
        mProfilePhotoUrl = mProfilePhotoUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public FirebaseAuth getFirebaseAuth() {
        return mFirebaseAuth;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return mFirebaseDatabase;
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }

    public String getUserEmail() {
        return mUserEmail;

    }

    public String getProfilePhotoUrl() {
        return mProfilePhotoUrl;
    }
    public String getUserId() {
        userId = mFirebaseAuth.getUid();
        return userId;
    }

    public void detachMeasurementsDatabaseChildListener() {
        if (mMeasurementsChildEventListener != null) {
            mDatabaseReference.removeEventListener(mMeasurementsChildEventListener);
            mMeasurementsChildEventListener = null;
        }
    }
    public void detachCommandsDatabaseChildListener() {
        if (mCommandsChildEventListener != null) {
            mDatabaseReference.removeEventListener(mCommandsChildEventListener);
            mCommandsChildEventListener = null;
        }
    }

    public void attachMeasurementsChildListener() {
        if (mMeasurementsChildEventListener == null) {
            mMeasurementsChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Measurments data = snapshot.getValue(Measurments.class);
                    liveData.setValue(data);
                    //Log.e("TAG" ,measurments.getValueCo2Con()+ "   :   " + measurments.getValueHumIn());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            mDatabaseReference.child("Measurements").addChildEventListener(mMeasurementsChildEventListener);
        }


    }
    public void attachCommandsChildListener() {
        if (mCommandsChildEventListener == null) {
            mCommandsChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                    liveCmd.setValue(getDataFromSnapShot(snapshot));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    liveCmd.setValue(getDataFromSnapShot(snapshot));
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            mDatabaseReference.child("Commands").addChildEventListener(mCommandsChildEventListener);
        }


    }

    private ArrayList<Object> getDataFromSnapShot(DataSnapshot snapshot) {


        for(DataSnapshot sp : snapshot.getChildren()) {
           switch (sp.getKey()){
               case "AirConditioner":
                   cmdList.set(0,snapshot.child("AirConditioner").getValue(AirConditioner.class));
                   Log.e("TAG", "" + ((snapshot.child("AirConditioner").getValue(AirConditioner.class)).getValue()));
                   break;
               case "Lights":
                   cmdList.set(1,snapshot.child("Lights").getValue(Lights.class));
                   Log.e("TAG", "" + ((snapshot.child("Lights").getValue(Lights.class)).getValue()));
                   break;
               case "Irrigation":
                   cmdList.set(2,snapshot.child("Irrigation").getValue(Irrigation.class));
                   //Log.e("TAG", "" + ((snapshot.child("Irrigation").getValue(Irrigation.class)).getValue()));
                   break;
               case "Roof":
                   cmdList.set(3,snapshot.child("Roof").getValue(Roof.class));
                   Log.e("TAG", "" + ((snapshot.child("Roof").getValue(Roof.class)).getState()));
                   break;
               case "Curtains":
                   cmdList.set(4,snapshot.child("Curtains").getValue(Curtains.class));
                   Log.e("TAG", "" + ((snapshot.child("Curtains").getValue(Curtains.class)).getState()));
                   break;
            }
        }
        return cmdList ;
    }

    public void setMeasurments(Measurments measurments) {
        this.measurments = measurments;
    }
    public Measurments getMeasurments() {
        return measurments;
    }

    public MutableLiveData<Measurments> getData () {
        attachMeasurementsChildListener();
        return liveData;
    }

    public MutableLiveData<ArrayList<Object>> getLiveCmd () {
        attachCommandsChildListener();
        return liveCmd;
    }

    public static class Users {
        private String name ;
        private String email;
        private String uid ;
        private String photoUrl;

        public Users() {
        }

        public Users(String name, String email, String uid) {
            this.name = name;
            this.email = email;
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

}