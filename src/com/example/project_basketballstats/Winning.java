package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Winning extends Activity {
	
	Button b,e;
	EditText a,d,c;
	String k;
	String[] Team;
	String Team1;
	String Team2;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winningstats);
		k = getIntent().getExtras().getString("Team");
		Team = k.split("vs");
		Team1 = Team[0];
		Team2 = Team[1];
		Toast.makeText(Winning.this, Team1 + " " + Team2, Toast.LENGTH_SHORT).show();
		Team1 = Team1.replace("\n", "");
		Team1 = Team1.replace(" ", "");
		Team2 = Team2.replace("\n", "");
		Team2 = Team2.replace(" ", "");
		b = (Button) findViewById(R.id.button1);
		e = (Button) findViewById(R.id.button2);
		a = (EditText) findViewById(R.id.editText1);
		d = (EditText) findViewById(R.id.editText2);
		c = (EditText) findViewById(R.id.editText3);
		MainDatabase db = new MainDatabase(this);
		db.open();
		int s = db.getScore(db.getTeamId(Team1));
		int y = db.getScore(db.getTeamId(Team2));
		a.setText(String.valueOf(db.getScore(db.getTeamId(Team1))));
		d.setText(String.valueOf(db.getScore(db.getTeamId(Team2))));
		if(s>y) {
			c.setText(Team1 + " Won !!");
		} else if (s<y) {
			c.setText(Team2 + " Won !!");
		} else {
			c.setText("Draw !!");
		}
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(Winning.this, Stats.class);
				a.putExtra("Team", Team1);
				startActivity(a);
			}
		});
		
		e.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(Winning.this, Stats.class);
				a.putExtra("Team", Team2);
				startActivity(a);
			}
		});
		db.close();
		
	}

}
