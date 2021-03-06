package com.intelimina.pollwatcher;

import holders.LGUHolder;
import holders.NavigationHolder;
import holders.UserHolder;

import java.text.ParseException;
import java.util.Date;

import models.Lgus;
import models.Region;
import models.Regions;
import models.User;
import utils.MyDialogHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration3Activity extends Activity {
	static final int DATE_DIALOG_ID = 0;

//	private EditText txtBday;	
	private EditText txtEmail;	
//	private EditText txtPhone;	
	private EditText txtRegion;

	private Region region;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration3);
		context=Registration3Activity.this;

		setupView();
	}
	private void setupView()
	{
		txtRegion = (EditText) findViewById(R.id.region);
//		txtBday = (EditText) findViewById(R.id.bday);
		txtEmail = (EditText) findViewById(R.id.email);
//		txtPhone = (EditText) findViewById(R.id.phone);

//		txtBday.setOnClickListener(new View.OnClickListener() {
//	        @Override
//	        public void onClick(View v) {
//	    		showDialog(DATE_DIALOG_ID);		
//	        }
//	    });
		
		txtRegion.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	save();
	        	regionList();
	        }
	    });

		//default values
//		txtBday.setText(PrettyDateHelper.toString(DateHelper.getNullDate2000()));
	}
	public void back(View button){back();}
	public void back()
	{
		save();
		NavigationHolder.setDestination(NavigationHolder.Registration2Activity);
		finish();
	}
	public void next(View button)
	{
//		if(txtBday.getText().toString().isEmpty())
//		{
//        	String message="Please enter your Birthday";
//			MyDialogHelper.popup(context, message);
//        	return;
//		} 
//		else 
		if(txtEmail.getText().toString().isEmpty())
		{
        	String message="Please enter your Email Address";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches())
		{
        	String message="Please enter a valid Email Address";
			MyDialogHelper.popup(context, message);
        	return;
		} 
//		else if(txtPhone.getText().toString().isEmpty())
//		{
//        	String message="Please enter your Phone Number";
//			MyDialogHelper.popup(context, message);
//        	return;
//		} 
		if(txtRegion.getText().toString().isEmpty())
		{
        	String message="Please choose your Region";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		
		save();
		NavigationHolder.setDestination(NavigationHolder.Registration4Activity);
		finish();
	}
	public void save()
	{
		
		User user=UserHolder.getRegUser();
//		user.setBday(PrettyDateHelper.toDate(txtBday.getText().toString()));
		user.setEmail(txtEmail.getText().toString());
//		user.setPhone(txtPhone.getText().toString());
	}
	public void regionList()
	{
		Intent intent = new Intent(context, RegionListActivity.class);
		intent.setAction("region");
		startActivity(intent);
	}

//	//-----date picker system----
//	private Integer byear=0,bmonth=0,bdayOfMonth=0;
//	public void setDate(int year, int month, int date) {
//		this.byear=year;
//		this.bmonth=month;
//		this.bdayOfMonth=date;
////		String myString = OutputStringCalculator.getGivenDateString(year, month, date);
//		txtBday.setText(PrettyDateHelper.toString(DateHelper.toDate(byear+"-"+bmonth+"-"+bdayOfMonth)));
////		textscrollview.fullScroll(ScrollView.FOCUS_UP);
//	}
//	//this receives the chosen date from the datepicker dialog 
//	private DatePickerDialog.OnDateSetListener mDateSetListener =
//	new DatePickerDialog.OnDateSetListener() {
//
//		public void onDateSet(DatePicker view, int year, 
//				int monthOfYear, int dayOfMonth) {
//			setDate(year, monthOfYear+1, dayOfMonth);
//		}
//	};
//	//this creates the datepicker dialog
//	@Override
//	protected Dialog onCreateDialog(int id) {
//		switch (id) {
//		case DATE_DIALOG_ID:
//			Calendar c = Calendar.getInstance(); 
//			User user=UserHolder.getRegUser();
//			c.setTime(user.getBday());
//			byear=c.get(c.YEAR);
//			bmonth=c.get(c.MONTH)+1;
//			bdayOfMonth=c.get(c.DATE);
//			//set datepicker date to user birthday
//			DatePickerDialog d=new DatePickerDialog(this,
//					mDateSetListener,
//					2000, 0, 1);
//			
//			c = Calendar.getInstance(); 
//			byear=c.get(c.YEAR);
//			bmonth=c.get(c.MONTH)+1;
//			bdayOfMonth=c.get(c.DATE);
//			d.getDatePicker().setMinDate(DateHelper.toDate(String.valueOf(byear-100)+"-"+bmonth+"-"+bdayOfMonth).getTime());
//			d.getDatePicker().setMaxDate(DateHelper.toDate(byear+"-"+bmonth+"-"+bdayOfMonth).getTime());
//			return d;
//		}
//		return null;
//	}
//	//-----end date picker system----
	public void fill(View button)
	{
//		txtBday.setText("Jan 1, 2000");
//		byear=2000;bmonth=1;bdayOfMonth=1;
//		txtPhone.setText("12345677");
		txtEmail.setText("a@a.a");
		LGUHolder.setRegion(Regions.getByName("NCR"));
		txtRegion.setText(LGUHolder.getRegion().getName());
	}
	@Override
	protected void onStart() {
		super.onStart();

		User user=UserHolder.getRegUser();
		Date bday=user.getBday();
//		txtBday.setText(PrettyDateHelper.toString(bday));
//		byear=DateHelper.getYear(bday);
//		bmonth=DateHelper.getMonth(bday);
//		bdayOfMonth=DateHelper.getDayOfMonth(bday);
//		txtPhone.setText(user.getPhone());
		txtEmail.setText(user.getEmail());
		
		Region tempregion=LGUHolder.getRegion();
		if(tempregion!=null)
		{
			this.region=tempregion;
			txtRegion.setText(region.getName());
		}
		else
		{
			txtRegion.setText("");
		}
		
	}
	@Override
	public void onBackPressed()
	{
		back();
	}
}
