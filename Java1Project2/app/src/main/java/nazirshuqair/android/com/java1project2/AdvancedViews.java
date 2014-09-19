/*
Nazir Shuqair
Project 3 - Working with Advanced Views
Java 1 - 1409
 */
package nazirshuqair.android.com.java1project2;

import android.app.Activity;
import android.content.res.Configuration;
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

    // Log Tag
    static final String TAG = "Project 3";
    //Object array
    private ArrayList<Sites> mSites;
    //Hasmap key final strings
    final String NAME = "name";
    final String URL = "url";
    final String LOGO = "logo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_views);

        //Adding objects to the object array
        mSites = new ArrayList<Sites>();
        mSites.add(Sites.newInstance("Tech Crunch", "http://techcrunch.com/", R.drawable.techcrunchlogo));
        mSites.add(Sites.newInstance("Mashable", "http://mashable.com/", R.drawable.mashablelogo));
        mSites.add(Sites.newInstance("Engadget", "http://www.engadget.com/", R.drawable.engadgetlogo));
        mSites.add(Sites.newInstance("Cocoa Controls", "https://www.cocoacontrols.com/", R.drawable.cococontrolslogo));
        mSites.add(Sites.newInstance("Cocoa Pods", "http://cocoapods.org/", R.drawable.cocoapodslogo));
        mSites.add(Sites.newInstance("Cnet", "http://www.cnet.com/", R.drawable.cnetlogo));

        //Call adapter method
        setSimpleAdapter();


    }

    private void setSimpleAdapter() {

        // List of elements in our adapter
        ArrayList<HashMap<String, Object>> mSitesItems = new ArrayList<HashMap<String, Object>>();

        // Goes through each site and maps the data elements to a String key
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

        //Check if orientation and display approriate elements
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            // If portrait then we create a spinner and set the adapter to it
            Spinner mSitesSpinner = (Spinner) findViewById(R.id.techSpinner);
            mSitesSpinner.setAdapter(adapter);

            mSitesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i(TAG, "Item Selected");

                    //Call the displayDetails method and pass the adapter view and position
                    //to populate details elements
                    displayDetail(adapterView, i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i(TAG, "Nothing Selected");

                }
            });
        }else {

            //Otherwise create a listview and set the adapter to it
            ListView mSitesListView = (ListView) findViewById(R.id.techList);
            mSitesListView.setAdapter(adapter);

            mSitesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Call the displayDetails method and pass the adapter view and position
                    //to populate details elements
                    displayDetail(adapterView, i);
                }
            });

        }


    }

    public void displayDetail(AdapterView<?> incomingView, int postion){

        //Get the site object from an incoming position
        Object site = incomingView.getItemAtPosition(postion);
        Log.i(TAG, site.toString());

        //Create textview and connect it to siteName then update the text based on the site object
        TextView tv = (TextView) findViewById(R.id.siteName);
        tv.setText(mSites.get(postion).getmName());

        //change the distination of the textview and update text
        tv = (TextView) findViewById(R.id.siteUrl);
        tv.setText(mSites.get(postion).getmUrl());

        //change the distination of the textview and update text
        ImageView iv = (ImageView) findViewById(R.id.siteLogo);
        iv.setImageResource(mSites.get(postion).getmImage());

    }



}
