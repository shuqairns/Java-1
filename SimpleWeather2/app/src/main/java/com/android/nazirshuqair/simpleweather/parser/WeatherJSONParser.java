/*
Nazir Shuqair
Java 1 - 1409
Final Project
 */
package com.android.nazirshuqair.simpleweather.parser;

import com.android.nazirshuqair.simpleweather.model.Weather;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazirshuqair on 9/21/14.
 */
public class WeatherJSONParser {

    public static List<Weather> parseFeed(String content) {

        JSONObject apiData;
        try{

            List<Weather> weatherList = new ArrayList();

            apiData = new JSONObject(content);
            Weather weather = new Weather();

            JSONObject channel = apiData.getJSONObject("query").getJSONObject("results").getJSONObject("channel");

            if (channel.getString("title").equals("Yahoo! Weather - Error")){
                return null;
            }else {
                weather.setCity(channel.getJSONObject("location").getString("city"));
                weather.setRegion(channel.getJSONObject("location").getString("region"));
                weather.setTemperature(channel.getJSONObject("item").getJSONObject("condition").getInt("temp"));
                weather.setTempText(channel.getJSONObject("item").getJSONObject("condition").getString("text"));
                weather.setForecastJSON(channel.getJSONObject("item").getJSONArray("forecast"));

                weatherList.add(weather);
                //Change
                return weatherList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}