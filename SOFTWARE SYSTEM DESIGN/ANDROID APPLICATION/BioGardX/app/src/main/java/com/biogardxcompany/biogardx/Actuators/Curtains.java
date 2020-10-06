package com.biogardxcompany.biogardx.Actuators;

public class Curtains extends Actuators {

    private int state =  0 ;

    public Curtains() {
    }

    public Curtains(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public boolean isEnabled(){
        return super.isEnabled();
    }
    public void setIsEnabled(boolean boo ){
        super.setEnabled(boo);
    }
}
