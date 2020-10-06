package com.biogardxcompany.biogardx.Actuators;

import com.biogardxcompany.biogardx.Constants;

public  class Actuators {


    private boolean isEnabled = Constants.DISABLED;
    private static boolean mode = Constants.MANUAL_MODE ;

    public static Actuators instance ;


    public static Actuators getInstance(){
        if(instance == null ){
            instance = new Actuators() ;
        }
        return instance ;
    }



    public Actuators() {
    }
    public Actuators (boolean mode){
        this.mode = mode ;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
