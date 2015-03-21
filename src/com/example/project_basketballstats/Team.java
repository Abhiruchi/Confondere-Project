package com.example.project_basketballstats;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Team extends ListFragment implements OnItemLongClickListener{
	
	String k;
	String id;
	ListView lv;
	
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
		final Button b = (Button) getView().findViewById(R.id.add_new_team);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(getActivity(), AddTeam.class);
				a.putExtra("Userid",id);
				startActivity(a);
			}
		});
		MainDatabase db = new MainDatabase(getActivity());
		db.open();
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.listview,
				db.getteam(id)));
		lv.setLongClickable(true);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> arg, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View dialogview = inflater.inflate(R.layout.editteam, null);
				AlertDialog.Builder b = new AlertDialog.Builder(new ContextThemeWrapper(
					    getActivity(),android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog));
				b.setTitle("DELETE / EDIT TEAM");
				b.setView(dialogview);
				b.setPositiveButton("Save",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						MainDatabase db = new MainDatabase(getActivity());
						db.open();
						EditText a = (EditText) dialogview.findViewById(R.id.editteam);
						//Toast.makeText(getActivity(),lv.getItemAtPosition(arg2).toString() + " " +  a.getText().toString() , Toast.LENGTH_LONG).show();
						db.updateTeam(lv.getItemAtPosition(arg2).toString().replace("\n", ""), a.getText().toString());
						//Toast.makeText(getActivity(), db.getTeam(), Toast.LENGTH_LONG).show();
						db.close();
						arg0.cancel();
					}
				});
				b.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						MainDatabase db = new MainDatabase(getActivity());
						db.open();
						db.deleteTeam(lv.getItemAtPosition(arg2).toString().replace("\n", ""));
						db.close();
						arg0.cancel();
					}
				});
				
				b.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						MainDatabase db = new MainDatabase(getActivity());
						db.open();
						db.close();
						arg0.cancel();
					}
				});
				Dialog b1 = b.create();
				b1.show();
				return true;
			}
		});
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.screen5, container, false);
		lv = (ListView) view.findViewById(android.R.id.list);
		return view;	
	}


	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
