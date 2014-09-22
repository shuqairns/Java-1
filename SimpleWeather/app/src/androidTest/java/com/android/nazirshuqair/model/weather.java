package com.android.nazirshuqair.model;

import java.util.ArrayList;

/**
 * Created by nazirshuqair on 9/21/14.
 */
public class Weather {

    private String city;
    private String region;
    private int temperature;
    private String tempText;
    private ArrayList<String> forcast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTempText() {
        return tempText;
    }

    public void setTempText(String tempText) {
        this.tempText = tempText;
    }

    public ArrayList<String> getForcast() {
        return forcast;
    }

    public void setForcast(ArrayList<String> forcast) {
        this.forcast = forcast;
    }



}
