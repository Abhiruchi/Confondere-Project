package com.example.project_basketballstats;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Frag extends Fragment {
	
	String k;
	String id;
	String x,y,z;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		k = getArguments().getString("Type");
		id =  getArguments().getString("Userid");
		}
	

	@SuppressLint("NewApi")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(k.equals("team")) {
		final Button b = (Button) getView().findViewById(R.id.add_new_team);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(getActivity(), AddTeam.class);
				a.putExtra("Userid",id);
				startActivity(a);
			}
		});
		}
		else if(k.equals("logout")) {
			Intent a = new Intent(getActivity(), Screen2.class);
			startActivity(a);
		} else if(k.equals("settings")) {
			Intent a = new Intent(getActivity(), Options.class);
			startActivity(a);
		} 
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(k.equals("team")) {
		View view =  inflater.inflate(R.layout.screen5, container, false);
		return view;	
		} else if (k.equals("game")) {
			View view =  inflater.inflate(R.layout.screen6, container, false);
			return view;
		} else if(k.equals("logout")||k.equals("settings")){
			View view =  inflater.inflate(R.layout.logoutbg, container, false);
			return view;
		} else {
			View view =  inflater.inflate(R.layout.screen2, container, false);
			return view;
		}
	}
	
	public static String getDateFromDatePicket(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);

	    return calendar.getTime().toString();
	}
	
	

}
