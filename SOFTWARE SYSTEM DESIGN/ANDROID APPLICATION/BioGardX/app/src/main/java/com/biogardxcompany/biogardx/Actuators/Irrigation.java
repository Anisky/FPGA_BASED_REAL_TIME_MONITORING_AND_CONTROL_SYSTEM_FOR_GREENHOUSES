package com.biogardxcompany.biogardx.Actuators;

public class Irrigation extends Actuators {
    private int setPoint = 25 ;
    private int state =  0 ;

    public Irrigation() {
    }

    public Irrigation(boolean isEnabled, int setPoint,  int state) {
        super();
        this.setPoint = setPoint;

        this.state = state ;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setSetPoint(int setPoint) {
        this.setPoint = setPoint;
    }
    public int getSetPoint() {
        return setPoint;
    }

    public boolean isEnabled(){
        return super.isEnabled();
    }
    public void setIsEnabled(boolean boo ){
         super.setEnabled(boo) ;
        }
}
