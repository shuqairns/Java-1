package com.fullsail.android.adaptersexample;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExampleAdapter extends BaseAdapter {
	
	private static final long ID_CONSTANT = 0x010101010L;
	
	private Context mContext;
	private ArrayList<Employee> mEmployees;
	
	// We take in a context and list of Employee objects.
	// The list is our backing collection and the context is used
	// to create new views in our getView() method.
	public ExampleAdapter(Context _context, ArrayList<Employee> _employees) {
		mContext = _context;
		mEmployees = _employees;
	}

	// Returning the number of objects in our collection.
	@Override
	public int getCount() {
		return mEmployees.size();
	}

	// Returning Employee objects from our collection.
	@Override
	public Employee getItem(int _position) {
		return mEmployees.get(_position);
	}

	// Adding our constant and position to create unique ID values.
	@Override
	public long getItemId(int _position) {
		return ID_CONSTANT + _position;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {
		
		// If we don't have a recycled view, create a new view.
		if(_convertView == null) {
			// Creating the new view.
			_convertView = LayoutInflater.from(mContext).inflate(R.layout.example_list_item, _parent, false);
		}
		
		// Get the object from the collection in an index-safe manner.
		Employee employee = getItem(_position);
		
		// Use the object from the collection to fill out our view.
		TextView tv = (TextView)_convertView.findViewById(R.id.employee_name);
		tv.setText(employee.getName());
		
		tv = (TextView)_convertView.findViewById(R.id.employee_department);
		tv.setText(employee.getDepartment());
		
		tv = (TextView)_convertView.findViewById(R.id.employee_service_time);
		tv.setText(mContext.getString(R.string.years, employee.getYearsOfService()));
		
		// Returning our filled out view.
		return _convertView;
	}
	
}