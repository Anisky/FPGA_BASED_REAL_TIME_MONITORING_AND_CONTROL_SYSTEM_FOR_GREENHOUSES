package com.biogardxcompany.biogardx.Measurment;

public class Measurments {
    private String valueTempIn   ;
    private String valueTempOut ;
    private String valueHumIn   ;
    private String valueHumOut  ;
    private String valueSoilMoisture ;
    private String valueCo2Con  ;
    private String valueRainDrops;
    private String valueSolarRadi;
    private String valueWaterLvl;
    private String valuePhLevel;

    private static Measurments instance ;

//    public static Measurments getInstance(){
//        if(instance==null){
//            instance = new Measurments() ;
//        }
//        return instance;
//    }
    public Measurments(){}
    public Measurments(String valueTempIn, String valueHumIn, String valueSoilMoisture,String valueCo2Con,
                       String valueTempOut, String valueHumOut, String valueRainDrops, String valueSolarRadi,
                       String valueWaterLvl,String valuePhLevel
                       ) {
        this.valueTempIn = valueTempIn;
        this.valueTempOut = valueTempOut;
        this.valueHumIn = valueHumIn;
        this.valueHumOut = valueHumOut;
        this.valueSoilMoisture = valueSoilMoisture;
        this.valueCo2Con = valueCo2Con;
        this.valueRainDrops = valueRainDrops;
        this.valueSolarRadi= valueSolarRadi;
        this.valuePhLevel = valuePhLevel;
        this.valueWaterLvl = valueWaterLvl;
    }

    public String getValueTempIn() {
        return valueTempIn;
    }

    public String getValueTempOut() {
        return valueTempOut;
    }

    public String getValueHumIn() {
        return valueHumIn;
    }

    public String getValueHumOut() {
        return valueHumOut;
    }

    public String getValueSoilMoisture() {
        return valueSoilMoisture;
    }

    public String getValueCo2Con() {
        return valueCo2Con;
    }

    public String getValueRainDrops() {
        return valueRainDrops;
    }

    public String getValuePhLevel() {
        return valuePhLevel;
    }

    public String getValueSolarRadi() {
        return valueSolarRadi;
    }

    public String getValueWaterLvl() {
        return valueWaterLvl;
    }

    public void setValueTempIn(String valueTempIn) {
        this.valueTempIn = valueTempIn;
    }

    public void setValueTempOut(String valueTempOut) {
        this.valueTempOut = valueTempOut;
    }

    public void setValueHumIn(String valueHumIn) {
        this.valueHumIn = valueHumIn;
    }

    public void setValueHumOut(String valueHumOut) {
        this.valueHumOut = valueHumOut;
    }

    public void setValueSoilMoisture(String valueSoilMoisture) {
        this.valueSoilMoisture = valueSoilMoisture;
    }

    public void setValueCo2Con(String valueCo2Con) {
        this.valueCo2Con = valueCo2Con;
    }

    public void setValueRainDrops(String valueRainDrops) {
        this.valueRainDrops = valueRainDrops;
    }

    public void setValuePhLevel(String valuePhLevel) {
        this.valuePhLevel = valuePhLevel;
    }

    public void setValueSolarRadi(String valueSolarRadi) {
        this.valueSolarRadi = valueSolarRadi;
    }

    public void setValueWaterLvl(String valueWaterLvl) {
        this.valueWaterLvl = valueWaterLvl;
    }
}
