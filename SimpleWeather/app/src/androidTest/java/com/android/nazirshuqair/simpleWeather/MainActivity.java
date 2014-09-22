package com.android.nazirshuqair.simpleWeather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nazirshuqair.R;
import com.android.nazirshuqair.model.Weather;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    //Debugging Tag
    final String TAG = "Simple Weather Log: ";

    @InjectView(R.id.zipEntry) EditText zipEntry;
    @InjectView(R.id.updateButton) Button updateButton;
    @OnClick(R.id.updateButton)void runRequest(){

        String test1 = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20weather.forecast%20WHERE%20location%3D%22";
        String test2 = "3%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

        String test3 = test1 + zipEntry.getText().toString() + test2;

        Log.i(TAG, test3);

    }
    List<Weather> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }



}
