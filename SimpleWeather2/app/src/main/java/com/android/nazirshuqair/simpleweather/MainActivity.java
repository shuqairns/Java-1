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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nazirshuqair.simpleweather.model.Weather;
import com.android.nazirshuqair.simpleweather.parser.WeatherJSONParser;
import com.android.nazirshuqair.simpleweather.textViewHelper.AutoResizeTextView;
import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    //Debugging Tag
    final String TAG = "Simple Weather Log: ";

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

    //This needs to be cleaned up by a gridview
    @InjectView(R.id.forcast1) TextView forcast1;
    @InjectView(R.id.forcast2) TextView forcast2;
    @InjectView(R.id.forcast3) TextView forcast3;
    @InjectView(R.id.forcast4) TextView forcast4;
    @InjectView(R.id.forcast5) TextView forcast5;
    @InjectView(R.id.forcast1details) TextView forcast1details;
    @InjectView(R.id.forcast2details) TextView forcast2details;
    @InjectView(R.id.forcast3details) TextView forcast3details;
    @InjectView(R.id.forcast4details) TextView forcast4details;
    @InjectView(R.id.forcast5details) TextView forcast5details;

    TextView[] forcastDays = {forcast1, forcast2, forcast3, forcast4, forcast5};
    TextView[] forcastDetails = {forcast1details, forcast2details, forcast3details, forcast4details, forcast5details};

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

                JSONArray forcastOutput = weather.getForecastJSON();

                if (forcastOutput != null){
                    int len = forcastOutput.length();
                    for (int i = 0; i <= len; i++){
                        try {
                            String day = forcastOutput.getJSONObject(i).getString("day");
                            String text = forcastOutput.getJSONObject(i).getString("text");
                            String high = forcastOutput.getJSONObject(i).getString("high");
                            String low = forcastOutput.getJSONObject(i).getString("low");

                            switch (i){
                                case 0:
                                    forcast1.setText(day);
                                    forcast1details.setText(text + "\n \nH:" + high + "\nL:" + low);
                                    break;
                                case 1:
                                    forcast2.setText(day);
                                    forcast2details.setText(text + "\n \nH:" + high + "\nL:" + low);
                                    break;
                                case 2:
                                    forcast3.setText(day);
                                    forcast3details.setText(text + "\n \nH:" + high + "\nL:" + low);
                                    break;
                                case 3:
                                    forcast4.setText(day);
                                    forcast4details.setText(text + "\n \nH:" + high + "\nL:" + low);
                                    break;
                                case 4:
                                    forcast5.setText(day);
                                    forcast5details.setText(text + "\n \nH:" + high + "\nL:" + low);
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    };
                }
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

            updateDisplay();
        }

        @Override
        protected void onProgressUpdate(String... values){

        }

    }

}
