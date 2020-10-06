package com.biogardxcompany.biogardx.Actuators;


 public  class Lights extends Actuators {
     private int setPoint = 25 ;
     private int value  = 25 ;

     public Lights() {
     }

     public Lights(boolean isEnabled, int setPoint, int value) {
         setIsEnabled(isEnabled);
         this.setPoint = setPoint;
         this.value = value;
     }

     public void setSetPoint(int setPoint) {
         this.setPoint = setPoint;
     }

     public void setValue(int value) {
         this.value = value;
     }

     public int getSetPoint() {
         return setPoint;
     }

     public int getValue() {
         return value;
     }
     public boolean isEnabled() {
         return super.isEnabled();
     }
     public void setIsEnabled(boolean boo ){
         super.setEnabled(boo) ;
     }
}
