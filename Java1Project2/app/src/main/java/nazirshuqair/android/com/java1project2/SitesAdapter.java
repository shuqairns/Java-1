package nazirshuqair.android.com.java1project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by nazirshuqair on 9/18/14.
 */
public class SitesAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x010101010L;

    private  Context sContext;
    private  ArrayList<Sites> sSites;

    public SitesAdapter(Context _context, ArrayList<Sites> _sites){
        sContext = _context;
        sSites = _sites;
    }

    // Returning the number of objects in our collection
    @Override
    public int getCount(){
        return sSites.size();
    }

    // Returning Employee objects from our collection
    @Override
    public Sites getItem(int _position){
        return sSites.get(_position);
    }

    // Adding our constant and position to create unique ID values
    @Override
    public long getItemId(int _position){
        return ID_CONSTANT + _position;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {

        // If we don't have a recycled view, create a new view.
        if(_convertView == null) {
            // Creating the new view.
            _convertView = LayoutInflater.from(sContext).inflate(R.layout.list_layout_view, _parent, false);
        }

        // Get the object from the collection in an index-safe manner.
        Sites site = getItem(_position);

        // Use the object from the collection to fill out our view.
        TextView tv = (TextView)_convertView.findViewById(R.id.);
        tv.setText(employee.getName());

        tv = (TextView)_convertView.findViewById(R.id.employee_department);
        tv.setText(employee.getDepartment());

        tv = (TextView)_convertView.findViewById(R.id.employee_service_time);
        tv.setText(mContext.getString(R.string.years, employee.getYearsOfService()));

        // Returning our filled out view.
        return _convertView;
    }
}
