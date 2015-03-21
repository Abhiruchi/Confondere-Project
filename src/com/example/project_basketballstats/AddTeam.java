package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeam extends Activity{
	

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.addteam);
			final String k = getIntent().getExtras().getString("Userid");
			
				Log.d("Add",k);
			Button addteamdone = (Button) findViewById(R.id.addteamdone);
			final EditText teamname = (EditText) findViewById(R.id.etteamname);
			final EditText noofplayers = (EditText) findViewById(R.id.etnoofplayers);
			
			addteamdone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MainDatabase db = new MainDatabase(AddTeam.this);
					db.open();
					if(teamname.getText().toString().equals("")||noofplayers.getText().toString().equals(""))
			        {
			                Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
			        }
			        else
			        {
			        	db.insertTeam(teamname.getText().toString(),noofplayers.getText().toString(), k);
			            //Toast.makeText(getApplicationContext(), db.get(k) + "Team Added ", Toast.LENGTH_LONG).show();
					Intent ab = new Intent(AddTeam.this,Addplayer.class);
					ab.putExtra("Teamid",db.getTeamId(teamname.getText().toString()));
					//Toast.makeText(AddTeam.this, db.getTeamId(teamname.getText().toString()), Toast.LENGTH_LONG).show();
					ab.putExtra("Userid",k);
					ab.putExtra("count", noofplayers.getText().toString());
					startActivity(ab);
			        }
					db.close();
				}
			});
			
		}

}
