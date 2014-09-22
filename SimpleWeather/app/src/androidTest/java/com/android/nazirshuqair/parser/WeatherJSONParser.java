package com.android.nazirshuqair.parser;

import com.android.nazirshuqair.model.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazirshuqair on 9/21/14.
 */
public class WeatherJSONParser {

    public static List<Weather> parseFeed(String content) {

        try{

            JSONArray ar = new JSONArray(content);
            List<Weather> weatherList = new ArrayList();

            for (int i = 0; i < ar.length(); i++){

                JSONObject obj = ar.getJSONObject(i);
                Weather weather = new Weather();

                weather.setCity(obj.getString("city"));
                weather.setRegion(obj.getString("region"));

                weatherList.add(weather);
            }

            return weatherList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}