package com.biogardxcompany.biogardx.Actuators;

public class Roof extends Actuators {

    private boolean state = false;

    public Roof() {
    }

    public Roof(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public boolean isEnabled(){
        return super.isEnabled();
    }
    public void setIsEnabled(boolean boo ){
        super.setEnabled(boo) ;
    }
}
