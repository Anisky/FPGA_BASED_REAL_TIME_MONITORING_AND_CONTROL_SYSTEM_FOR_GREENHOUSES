package com.biogardxcompany.biogardx.ConnectionServices;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.biogardxcompany.biogardx.R;

import java.util.ArrayList;

class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater mLayoutInfalter ;
    private ArrayList<BluetoothDevice> mDevices ;
    private int mViewRecourceId ;
    public DeviceListAdapter(Context context, int resource, ArrayList<BluetoothDevice> devices) {
        super(context, resource, devices);
        this.mDevices = devices ;
        mLayoutInfalter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewRecourceId = resource;

    }

    public View getView(int position , View convertView , ViewGroup parent){

        convertView = mLayoutInfalter.inflate(mViewRecourceId, null);
        final BluetoothDevice device = mDevices.get(position);
        if(device != null){
            TextView deviceName = (TextView)convertView.findViewById(R.id.device_name);
            TextView deviceAddress = (TextView)convertView.findViewById(R.id.device_address);
            if(deviceName != null ){
                deviceName.setText(device.getName());}
            if(deviceAddress!=null){
                deviceAddress.setText(device.getAddress());}
        }

    return convertView;
    }



}
