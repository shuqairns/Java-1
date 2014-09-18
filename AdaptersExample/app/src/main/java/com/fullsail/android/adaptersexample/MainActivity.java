package com.fullsail.android.adaptersexample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ArrayList<Employee> mEmployees;
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEmployees = new ArrayList<Employee>();
		mEmployees.add(new Employee("Joe Smith", "Accounting", 3));
		mEmployees.add(new Employee("John Doe", "Sales", 6));
		mEmployees.add(new Employee("Anna Thompson", "Customer Service", 8));
		mEmployees.add(new Employee("Becky Stevens", "Accounting", 1));
		mEmployees.add(new Employee("Jim Bob", "Sales", 2));
		mEmployees.add(new Employee("Mary Smith", "Sales", 2));
		mEmployees.add(new Employee("Jose Diaz", "Accounting", 6));
		mEmployees.add(new Employee("Ann Perkins", "Customer Service", 9));
		mEmployees.add(new Employee("George Willard", "Accounting", 0));
		mEmployees.add(new Employee("Paul Rogers", "Customer Service", 2));
		mEmployees.add(new Employee("Dora Cornish", "Sales", 1));
		
		mListView = (ListView)findViewById(R.id.example_list);		
		mListView.setAdapter(new ExampleAdapter(this, mEmployees));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu _menu) {
		getMenuInflater().inflate(R.menu.main, _menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem _item) {
		
		switch(_item.getItemId()) {
		case R.id.menu_array:
			setArrayAdapter();
			break;
			
		case R.id.menu_simple:
			setSimpleAdapter();
			break;
			
		case R.id.menu_base:
			setBaseAdapter();
			break;
			
			default:
				break;
		}
		
		return true;
	}
	
	/**
	 * Creates a new ArrayAdapter and attaches it to our ListView.
	 */
	private void setArrayAdapter() {
		ArrayAdapter<Employee> arrayAdapter = new ArrayAdapter<Employee>(this, 
				android.R.layout.simple_list_item_1, mEmployees);
		mListView.setAdapter(arrayAdapter);
	}
	
	/**
	 * Creates a new ExampleAdapter and attaches it to our ListView.
	 */
	private void setBaseAdapter() {
		mListView.setAdapter(new ExampleAdapter(this, mEmployees));
	}
	
	/**
	 * Creates a new SimpleAdapter and attaches it to our ListView.
	 */
	private void setSimpleAdapter() {
		
		// Field identifiers
		final String name = "name";
		final String department = "department";
		final String years = "years";
		
		// List of elements for our adapter.
		ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String,String>>();
		
		// Goes through each employee and maps the data elements to a String key.
		for(Employee employee : mEmployees) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(name, employee.getName());
			map.put(department, employee.getDepartment());
			map.put(years, getString(R.string.years, employee.getYearsOfService()));
			elements.add(map);
		}
		
		// Creating an array of our keys
		String[] keys = new String[] {
			name, department, years	
		};
		
		// Creating an array of our list item components.
		// Indices must match the keys array.
		int[] views = new int[] {
				R.id.employee_name,
				R.id.employee_department,
				R.id.employee_service_time
		};
		
		// Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
		SimpleAdapter adapter = new SimpleAdapter(this, elements, R.layout.example_list_item, keys, views);
		
		mListView.setAdapter(adapter);
	}
}
