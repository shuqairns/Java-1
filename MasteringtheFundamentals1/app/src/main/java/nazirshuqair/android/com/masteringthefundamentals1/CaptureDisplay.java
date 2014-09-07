package nazirshuqair.android.com.masteringthefundamentals1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;


public class CaptureDisplay extends Activity implements View.OnClickListener, DialogInterface.OnClickListener{

    final String TAG = "Mastering Fundamentals Log: ";
    private TextView mUserEntry;
    private HashSet<String> countrySet = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_display);

        mUserEntry = (TextView) findViewById(R.id.userEntry);

        Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(this);

        ListView countryList = (ListView) findViewById(R.id.entryList);
        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "List Item selected");

                TextView selected = (TextView) view;

                createAlert(selected.getText().toString());

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.capture_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.submitButton:
                //DO something
                Log.i(TAG, "Button Clicked");

                //Toast Alert code
                Context context = getApplicationContext();

                //Clear Textfield data
                mUserEntry.setText("");

                // Toast Alert Code
                CharSequence text = "Saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 400);

                break;
        }

    }

    // This is to create the detail alerts
    public void createAlert(String selected){

        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage(selected)
                .setTitle("More Info")
                .setNeutralButton("Ok", this)
                .setCancelable(false)
                .create();
        ad.show();

    }

    public void onClick(DialogInterface dialog, int which){

        switch (which){

            case DialogInterface.BUTTON_NEUTRAL: //No
                //Do nothing
                Log.i(TAG, "Ok pressed");

                break;
            default:
                break;

        }

    }
}
