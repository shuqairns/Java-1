package com.android.nazirshuqair.simpleWeather;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nazirshuqair on 9/21/14.
 */
public class HttpManager {

    public static String getData(String uri){

        BufferedReader reader = null;

        try {

            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }

            return sb.toString();
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }finally {

            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getData(String uri, String userName, String password){

        HttpURLConnection con = null;
        BufferedReader reader = null;

        byte[] loginBytes = (userName + ":" + password).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {

            URL url = new URL(uri);
            con = (HttpURLConnection) url.openConnection();

            // Setting connection properties.
            //con.setRequestMethod("GET");
            //con.setConnectTimeout(10000); // 10 seconds
            //con.setReadTimeout(10000); // 10 seconds
            // Refreshing the connection.
            //con.connect();

            con.addRequestProperty("Authorization", loginBuilder.toString());
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }

            return sb.toString();
        }catch (Exception e){
            try {
                int status = con.getResponseCode();
                Log.i("permission test", String.valueOf(status));
                return String.valueOf(status);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }finally {

            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
