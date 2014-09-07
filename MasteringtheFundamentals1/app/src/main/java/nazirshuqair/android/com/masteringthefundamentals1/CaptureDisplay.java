/*
Nazir Shuqair
Java 1 - 1409
Mastering The Fundamentals 1
 */

package nazirshuqair.android.com.masteringthefundamentals1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.content.DialogInterface;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class CaptureDisplay extends Activity implements View.OnClickListener, DialogInterface.OnClickListener{

    //Debugging Tag
    final String TAG = "Mastering Fundamentals Log: ";
    //User entry textView
    private TextView mUserEntry;
    //HashSet Collection to hold input data
    private HashSet<String> mCountrySet = new HashSet<String>();
    //ListView to display data
    private ListView mCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_display);
        //connecting the user entry textView to variable
        mUserEntry = (TextView) findViewById(R.id.userEntry);
        //connecting button to variable
        Button submitBtn = (Button) findViewById(R.id.submitButton);
        //launches when a button is pressed
        submitBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        //retrives button by id, so to not duplicate code if I wanted to add a "clear all" button
        switch(v.getId()){
            //Submit button id
            case R.id.submitButton:
                // If anything is entered, save the data and run the methods
                if (mUserEntry.getText().length() > 0){
                    //Adding user Input to Set
                    mCountrySet.add(mUserEntry.getText().toString());
                    //Updating the list view
                    populateListView();

                    //Updates the number of entires
                    TextView numEntries = (TextView) findViewById(R.id.numEntries);
                    numEntries.setText(String.valueOf(mCountrySet.size()));

                    //Updates the Average length
                    avgLengthM();

                    // Toast Alert Code
                    Context context = getApplicationContext();
                    CharSequence text = "Saved!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 400);
                }else { //If nothing is entered, display alert/ don't do anything else
                    // Toast Alert Code
                    Context context = getApplicationContext();
                    CharSequence text = "Nothing to Save";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 400);
                }

                //Clear Textfield data
                mUserEntry.setText("");

                break;
        }

    }

    public void onClick(DialogInterface _dialog, int _which){
        switch (_which){
            case DialogInterface.BUTTON_NEUTRAL: //No
                //Do nothing
                Log.i(TAG, "Ok pressed");

                break;
            default:
                break;
        }
    }

    //Method to update the listView
    private void populateListView() {

        //List to hold all set elements to populate listview
        ArrayList<String> toPopulateList = new ArrayList<String>(mCountrySet);

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                   //Context for this activity
                R.layout.list_item,     //Layout to use
                toPopulateList);        //items to display

        //Configure the List View
        //Connecting the List view to variable
        mCountryList = (ListView) findViewById(R.id.entryList);
        mCountryList.setAdapter(adapter);

        //Setting clickListener to perform action if cell is pressed
        mCountryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView selected = (TextView) view;
                //method to create an alert by passing the string in the selected cell
                createAlert(selected.getText().toString());
            }
        });

    }

    // Average Length Method
    private void avgLengthM(){

        //init Iterator
        Iterator<String> iter = mCountrySet.iterator();
        //connect average length textview to variable
        TextView avgLength = (TextView) findViewById(R.id.avgLength);
        // set initial value of avgInt and avgResult to 0;
        Integer avgInt = 0;
        float avgResult = 0.0f;

        //Iterate mCountrySet and find average length
        while (iter.hasNext()){
            avgInt = avgInt + iter.next().length();
            avgResult = (float) avgInt / mCountrySet.size();
        }
        //Formats the float to only display 2 decimal places
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        //Set average length textview to average result
        avgLength.setText(String.valueOf(df.format(avgResult)));
    }

    // This is to create the detail alerts
    public void createAlert(String _selected){

        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage(_selected)
                .setTitle("More Info")
                .setNeutralButton("Ok", this)
                .setCancelable(false)
                .create();
        ad.show();

    }


}
