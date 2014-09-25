package com.android.nazirshuqair.simpleweather;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nazirshuqair.simpleweather.model.Weather;
import com.android.nazirshuqair.simpleweather.parser.WeatherJSONParser;
import com.android.nazirshuqair.simpleweather.textViewHelper.AutoResizeTextView;
import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    //Debugging Tag
    final String TAG = "Simple Weather Log: ";

    final String DAY = "day";
    final String TEXT = "text";
    final String HIGH = "high";
    final String LOW = "low";

    ProgressBar pb;

    List<Weather> weatherList;

    @InjectView(R.id.zipEntry)
    EditText zipEntry;
    @InjectView(R.id.updateButton)
    Button updateButton;
    @InjectView(R.id.cityRegion)AutoResizeTextView cityRegion;
    @InjectView(R.id.tempLabel) TextView tempLabel;
    @InjectView(R.id.tempTextLabel) AutoResizeTextView tempTextLabel;

    SmartImageView myImage;


    @OnClick(R.id.updateButton)void runRequest(){

        String apiUrlPart1 = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20weather.forecast%20WHERE%20location%3D%22";
        String apiUrlPart2 = "%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        String fullURL = apiUrlPart1 + zipEntry.getText().toString() + apiUrlPart2;

        if (zipEntry.getText().length() > 5){
            Toast.makeText(this, "Please enter a valid Zip Code", Toast.LENGTH_LONG).show();
        }else if (zipEntry.getText().length() < 5){
            Toast.makeText(this, "Please enter a valid Zip Code", Toast.LENGTH_LONG).show();
        }else {
            Log.i(TAG, fullURL);
            if (isOnline()){
                requestData(fullURL);

            }else {
                Toast.makeText(this, "No network", Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        //myImage = (SmartImageView) this.findViewById(R.id.testImage);
        //myImage.setImageUrl("http://www.sr-tour.com/LostLandSinai/style/images/weather.png");


        ButterKnife.inject(this);

    }

    private void requestData(String uri){

        MyTask myTask = new MyTask();
        myTask.execute(uri);

    }

    // Update all ui elements
    protected void updateDisplay(){


        if (weatherList != null){

            for (Weather weather : weatherList){
                //will output some text for testing here

                cityRegion.setText(weather.getCity() + ", " + weather.getRegion());
                cityRegion.resizeText();
                tempLabel.setText(String.valueOf(weather.getTemperature()));
                tempTextLabel.setText(weather.getTempText());
                tempTextLabel.resizeText();

                populateListView(weather);

            }
        }

    }

    protected boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            //updateDisplay
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params){

            String content = HttpManager.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String result){

            pb.setVisibility(View.INVISIBLE);

            Log.i(TAG, result);

            if (result == null){
                Toast.makeText(MainActivity.this, "Can't Connect", Toast.LENGTH_LONG).show();
                return;
            }

            weatherList = WeatherJSONParser.parseFeed(result);

            if (weatherList == null){
                Toast.makeText(MainActivity.this, "Zip code is Invalid!", Toast.LENGTH_LONG).show();
            }

            updateDisplay();
        }

        @Override
        protected void onProgressUpdate(String... values){

        }

    }

    private void populateListView(Weather weather) {

        JSONArray forcastOutput = weather.getForecastJSON();

        // List of elements in our adapter
        ArrayList<HashMap<String, Object>> mForecastList = new ArrayList<HashMap<String, Object>>();

        // Goes through each site and maps the data elements to a String key

        for (int i = 0; i < forcastOutput.length(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            try {
                map.put(DAY, forcastOutput.getJSONObject(i).getString(DAY));
                map.put(TEXT, forcastOutput.getJSONObject(i).getString(TEXT));
                map.put(HIGH, "H: " + forcastOutput.getJSONObject(i).getString(HIGH));
                map.put(LOW, "L: " + forcastOutput.getJSONObject(i).getString(LOW));

                mForecastList.add(map);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Creating an array of our keys
        String[] keys = new String[] {
                DAY, TEXT, HIGH, LOW
        };

        // Creating an array of our list item components.
        // Indices must match the keys array.
        int[] views = new int[] {
                R.id.forecastDayLabel,
                R.id.forecastTextLabel,
                R.id.forecastHighLabel,
                R.id.forecastLowLabel
        };


        // Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
        SimpleAdapter adapter = new SimpleAdapter(this, mForecastList, R.layout.grid_layout_view, keys, views);

            //Otherwise create a listview and set the adapter to it
            GridView mForcastGridView = (GridView) findViewById(R.id.forecastGrid);
            mForcastGridView.setAdapter(adapter);
    }
}
