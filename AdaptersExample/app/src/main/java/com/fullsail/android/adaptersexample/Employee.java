package com.fullsail.android.adaptersexample;

public class Employee {
	
	// Example of factory pattern. Hope you haven't forgotten it already.
	public static Employee newInstance(String _name, 
			String _department, int _service) {
		Employee emp = new Employee();
		emp.setName(_name);
		emp.setDepartment(_department);
		emp.setYearsOfService(_service);
		return emp;
	}
	
	private int mYearsOfService;
	private String mName;
	private String mDepartment;
	
	public Employee() {
		mYearsOfService = 0;
		mName = mDepartment = "";
	}
	
	public Employee(String _name, String _department, int _service) {
		mYearsOfService = _service;
		mName = _name;
		mDepartment = _department;
	}
	
	public int getYearsOfService() {
		return mYearsOfService;
	}
	
	public void setYearsOfService(int _service) {
		mYearsOfService = _service;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String _name) {
		mName = _name;
	}
	
	public String getDepartment() {
		return mDepartment;
	}
	
	public void setDepartment(String _department) {
		mDepartment = _department;
	}
	
	@Override
	public String toString() {
		return mName + " - " + mDepartment + " - " + mYearsOfService;
	}
}