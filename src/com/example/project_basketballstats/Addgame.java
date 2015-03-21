package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Addgame extends Activity{
	
	TextView a,b;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		a = (TextView) findViewById(R.id.Team1);
		b = (TextView) findViewById(R.id.Team2);
		a.setText(getIntent().getExtras().getString("Team1"));
		b.setText(getIntent().getExtras().getString("Team2"));
		a.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent b = new Intent(Addgame.this, Stats.class);
				b.putExtra("Team", a.getText().toString());
				startActivity(b);
				
			}
			
		});
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(Addgame.this, Stats.class);
				a.putExtra("Team", b.getText().toString());
				startActivity(a);
				
			}
			
		});
	}

}
