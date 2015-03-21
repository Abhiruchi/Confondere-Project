package com.example.project_basketballstats;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Game extends ListFragment{
	
	String k;
	String id;
	ListView lv;
	String x,y,z;
	TextView editschedule, deleteschedule;
	
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
	
		MainDatabase db = new MainDatabase(getActivity());
		db.open();
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.listview,
				db.getgame(id)));
		lv.setLongClickable(true);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> arg, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View ddialogview = inflater.inflate(R.layout.editg, null);
				
				editschedule = (TextView) ddialogview.findViewById(R.id.editschedule);
				deleteschedule = (TextView) ddialogview.findViewById(R.id.deleteschedule);
				
				AlertDialog.Builder b = new AlertDialog.Builder(new ContextThemeWrapper(
					    getActivity(),android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog));
				b.setTitle("EDIT DETAILS");
				b.setView(ddialogview);
			
				editschedule.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						LayoutInflater inflater = LayoutInflater.from(getActivity());
						final View dialogvieww = inflater.inflate(R.layout.egame, null);
						AlertDialog.Builder b = new AlertDialog.Builder(new ContextThemeWrapper(
							    getActivity(),android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog));
						b.setTitle("ADD GAME");
						b.setView(dialogvieww);
						b.setPositiveButton("Save",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								MainDatabase db = new MainDatabase(getActivity());
                                                                db.open();
								
							}
						});
						Dialog b1 = b.create();
						b1.show();
						}
					
				});
				
				deleteschedule.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						//db se delete kr dio
                                                MainDatabase db = new MainDatabase(getActivity());
                                                db.open();
					}
					
				});
					
				Dialog b1 = b.create();
				b1.show();
				return true;
			}
		});
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {
				
				Dialog d = new Dialog(getActivity());
				d.setTitle("EDIT GAME");
				LayoutInflater f = LayoutInflater.from(getActivity());
				View dialog = f.inflate(R.layout.editgame, null);
				d.setContentView(dialog);
				d.show();
				TextView x = (TextView) dialog.findViewById(R.id.textView1);
				TextView y = (TextView) dialog.findViewById(R.id.textView2);
				x.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent a = new Intent(getActivity(), Teamonetwo.class);
						a.putExtra("Team", lv.getItemAtPosition(arg2).toString());
						startActivity(a);
					}
				});
				y.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent a = new Intent(getActivity(), Winning.class);
						a.putExtra("Team", lv.getItemAtPosition(arg2).toString());
						startActivity(a);	
					}
				});
				
				
			}
			
		});
		db.close();
		final Button b = (Button) getView().findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(getActivity());
				db.open();
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View dialogview = inflater.inflate(R.layout.addgame, null);
		final Spinner s = (Spinner) dialogview.findViewById(R.id.spinner1);
		final Spinner d = (Spinner) dialogview.findViewById(R.id.spinner2);
		final Spinner type = (Spinner) dialogview.findViewById(R.id.spinner3);
		final EditText a = (EditText) dialogview.findViewById(R.id.edittext3);
		final TimePicker t = (TimePicker) dialogview.findViewById(R.id.timePicker1);
		final DatePicker da = (DatePicker) dialogview.findViewById(R.id.datePicker1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, db.getteam(id));

		s.setAdapter(dataAdapter);
		d.setAdapter(dataAdapter);
		db.close();
		
		ArrayAdapter<String> dataAda = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, new String[]{"Quarter","Halves"});
		type.setAdapter(dataAda);
		
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				x = s.getSelectedItem().toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				x = " ";	
			}
			
		});
		
		d.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				y = d.getSelectedItem().toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				y = " ";	
			}
			
		});
		
		type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				z = type.getSelectedItem().toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				z = " ";	
			}
			
		});
		
		AlertDialog.Builder b = new AlertDialog.Builder(new ContextThemeWrapper(
			    getActivity(),android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog));
		b.setTitle("ADD GAME");
		b.setView(dialogview);
		b.setPositiveButton("Save",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				MainDatabase db = new MainDatabase(getActivity());
				db.open();
				Log.d("saved", x + y + z);
				db.insertGame(id, x, y, getDateFromDatePicket(da), t.getCurrentHour(), z, a.getText().toString());
				db.insertWin(x, y);
				Toast.makeText(getActivity(), db.getWIN(), Toast.LENGTH_LONG).show();
				arg0.cancel();
				db.close();
				Intent a = new Intent(getActivity(), Tab.class);
				a.putExtra("UserID", id);
				startActivity(a);
				//Toast.makeText(getActivity(), db.getGame(id), Toast.LENGTH_LONG).show();
				
			}
		});
		Dialog b1 = b.create();
		b1.show();
		}
		});
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.screen6, container, false);
		lv = (ListView) view.findViewById(android.R.id.list);
		return view;	
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
