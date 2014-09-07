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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class CaptureDisplay extends Activity implements View.OnClickListener, DialogInterface.OnClickListener{

    final String TAG = "Mastering Fundamentals Log: ";
    private TextView mUserEntry;

    private HashSet<String> mCountrySet = new HashSet<String>();
    private ListView mCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_display);

        mUserEntry = (TextView) findViewById(R.id.userEntry);

        Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(this);
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.submitButton:
                //DO something
                Log.i(TAG, "Button Clicked");

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
                }else {
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

    // Average Length Method
    private void avgLengthM(){

        Iterator<String> iter = mCountrySet.iterator();
        TextView avgLength = (TextView) findViewById(R.id.avgLength);
        Integer avgInt = 0;
        Integer avgResult = 0;

        while (iter.hasNext()){
            avgInt = avgInt + iter.next().length();
            avgResult = avgInt / mCountrySet.size();
            Log.i(TAG, String.valueOf(mUserEntry.getText().length()));
            Log.i(TAG, String.valueOf((avgInt)));
        }

        avgLength.setText(String.valueOf(avgResult));
    }

    private void populateListView() {

        //List to hold all set elements to populate listview
        ArrayList<String> toPopulateList = new ArrayList<String>(mCountrySet);

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                toPopulateList);

        //Configure the List View

        mCountryList = (ListView) findViewById(R.id.entryList);
        mCountryList.setAdapter(adapter);

        //mAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mCountrySet);
        mCountryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "List Item selected");

                TextView selected = (TextView) view;
                createAlert(selected.getText().toString());

            }
        });

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
}
