package com.android.nazirshuqair.simpleweather.model;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by nazirshuqair on 9/21/14.
 */
public class Weather {

    private String city;
    private String region;
    private int temperature;
    private String tempText;
    private JSONArray forecastJSON;

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

    public JSONArray getForecastJSON() {
        return forecastJSON;
    }

    public void setForecastJSON(JSONArray forecastJSON) {
        this.forecastJSON = forecastJSON;
    }

}
