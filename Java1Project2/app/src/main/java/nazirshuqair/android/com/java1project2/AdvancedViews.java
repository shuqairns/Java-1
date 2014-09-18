package nazirshuqair.android.com.java1project2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class AdvancedViews extends Activity {

    static final String TAG = "Project 3";
    private ArrayList<Sites> mSites;
    final String NAME = "name";
    final String URL = "url";
    final String LOGO = "logo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_views);

        mSites = new ArrayList<Sites>();
        mSites.add(Sites.newInstance("Tech Crunch", "http://techcrunch.com/", R.drawable.techcrunchlogo));
        mSites.add(Sites.newInstance("Mashable", "http://mashable.com/", R.drawable.mashablelogo));
        mSites.add(Sites.newInstance("Engadget", "http://www.engadget.com/", R.drawable.engadgetlogo));
        mSites.add(Sites.newInstance("Cocoa Controls", "https://www.cocoacontrols.com/", R.drawable.cococontrolslogo));
        mSites.add(Sites.newInstance("Cocoa Pods", "http://cocoapods.org/", R.drawable.cocoapodslogo));
        mSites.add(Sites.newInstance("Cnet", "http://www.cnet.com/", R.drawable.cnetlogo));

        setSimpleAdapter();


    }

    private void setSimpleAdapter() {

        // List of elements in our adapter
        ArrayList<HashMap<String, Object>> mSitesItems = new ArrayList<HashMap<String, Object>>();

        // Goes through each employee and maps the data elements to a String key
        for (Sites site : mSites){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(NAME, site.getmName());
            map.put(URL, site.getmUrl());
            map.put(LOGO, site.getmImage());

            mSitesItems.add(map);
        }

        // Creating an array of our keys
        String[] keys = new String[] {
                NAME, URL, LOGO
        };

        // Creating an array of our list item components.
        // Indices must match the keys array.
        int[] views = new int[] {
                R.id.siteNameLabel,
                R.id.siteUrlLabel,
                R.id.siteImageView
        };

        // Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
        SimpleAdapter adapter = new SimpleAdapter(this, mSitesItems, R.layout.list_layout_view, keys, views);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Spinner mSitesSpinner = (Spinner) findViewById(R.id.techSpinner);
            mSitesSpinner.setAdapter(adapter);

            mSitesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i(TAG, "Item Selected");
                    displayDetail(adapterView, i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i(TAG, "Nothing Selected");

                }
            });
        }else {
            ListView mSitesListView = (ListView) findViewById(R.id.techList);
            mSitesListView.setAdapter(adapter);

            mSitesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    displayDetail(adapterView, i);
                }
            });

        }


    }

    public void displayDetail(AdapterView<?> incomingView, int postion){

        Object site = incomingView.getItemAtPosition(postion);
        Log.i(TAG, site.toString());

        TextView tv = (TextView) findViewById(R.id.siteName);
        tv.setText(mSites.get(postion).getmName());

        tv = (TextView) findViewById(R.id.siteUrl);
        tv.setText(mSites.get(postion).getmUrl());

        ImageView iv = (ImageView) findViewById(R.id.siteLogo);
        iv.setImageResource(mSites.get(postion).getmImage());

    }



    }
