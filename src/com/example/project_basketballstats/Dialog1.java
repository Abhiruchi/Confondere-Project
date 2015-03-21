package com.example.project_basketballstats;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

@SuppressLint("NewApi")
public class Dialog1 extends DialogFragment {
	
	
	public static Dialog1 newInstance() {
		return new Dialog1();
	}
	
	protected Dialog onCreateDialog() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View dialogview = inflater.inflate(R.layout.screen9, null);
		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
		b.setTitle("ENTER DETAILS");
		b.setView(dialogview);
		b.setNeutralButton("Done", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.cancel();
			}
		});
		Dialog b1 = b.create();
		return b1;
		
	}

}
