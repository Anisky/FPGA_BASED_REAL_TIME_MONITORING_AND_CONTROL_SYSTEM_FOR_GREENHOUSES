package com.biogardxcompany.biogardx;

public interface Constants {

    public final int LIGHT_IRR = 0 ;
    public final int MEDIUM_IRR= 1 ;
    public final int INTENSIVE_IRR = 2 ;

    public final int CURTAINS_OPEN = 0 ;
    public final int CURTAINS_H_OPEN = 1 ;
    public final int CURTAINS_CLOSED = 2 ;

    public final boolean  ROOF_OPEN = false ;
    public final boolean  ROOF_CLOSED = true;

    public final boolean MANUAL_MODE = false;
    public final boolean AUTOMATIC_MODE = true;

    public final boolean ENABLED = true;
    public final boolean DISABLED = true;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";


}
