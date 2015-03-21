package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addplayer extends Activity{
	
	EditText a,b;
	int count = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addplayer);
		a = (EditText) findViewById(R.id.editText1);
		b = (EditText) findViewById(R.id.editText2);
		final String k = getIntent().getExtras().getString("Teamid");
		final String c = getIntent().getExtras().getString("count");
		final String id = getIntent().getExtras().getString("Userid");
		final int f = Integer.valueOf(c);
		final Button addnewplayer = (Button) findViewById(R.id.addnewplayer);
		if(count == f-1) 
		addnewplayer.setText("Done");
		addnewplayer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count++;
				if(count == f-1) 
				addnewplayer.setText("Done");
				if(count == f) {
					//addnewplayer.setText("Done");
					
					a.putExtra("UserID", id);
					startActivity(a);
				}
				MainDatabase db = new MainDatabase(Addplayer.this);
				db.open();
				db.insertPlayer(a.getText().toString(), b.getText().toString(),k );
				db.insertStatistics(db.getPlayerId(a.getText().toString()),"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
				//Toast.makeText(Addplayer.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
				a.setText("");
				b.setText("");
			}
		});

	}
}
