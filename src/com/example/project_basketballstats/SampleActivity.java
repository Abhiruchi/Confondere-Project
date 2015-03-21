package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flyoutmenuexample.view.viewgroup.FlyOutContainer;
import com.example.project_basketballstats.controls.Carousel;
import com.example.project_basketballstats.controls.CarouselAdapter;
import com.example.project_basketballstats.controls.CarouselItem;
import com.example.project_basketballstats.controls.CarouselAdapter.OnItemClickListener;
import com.example.project_basketballstats.controls.CarouselAdapter.OnItemSelectedListener;

public class SampleActivity extends Activity implements OnClickListener {

	FlyOutContainer root;
	Button twopm,twopmiss,ftm,ftmiss,threepm,threepmiss,orbd,drbd,assist,block,foul,turnover,steal,b;
	String j,k;
	String[] Team;
	String Teamname;
	String Teamid;
	String Playerid;
	String[] Player;
	String curplayer;
	int l = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
		 if(myPreference.getBoolean("pref_opt1", false)) {
		      l = 1;
		 }
		 
		super.onCreate(savedInstanceState);
		this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_sample, null);
		this.setContentView(root);
		b = (Button)findViewById(R.id.button1);
		twopm = (Button)findViewById(R.id.twopm);
		twopmiss = (Button)findViewById(R.id.twopmiss);
		ftm = (Button)findViewById(R.id.ftm);
		ftmiss = (Button)findViewById(R.id.ftmiss);
		threepm = (Button)findViewById(R.id.threepm);
		threepmiss = (Button)findViewById(R.id.threepmiss);
		orbd = (Button)findViewById(R.id.orbd);
		drbd = (Button)findViewById(R.id.drbd);
		assist = (Button)findViewById(R.id.assist);
		block = (Button)findViewById(R.id.block);
		foul = (Button)findViewById(R.id.foul);
		turnover = (Button)findViewById(R.id.turnover);
		steal = (Button)findViewById(R.id.steal);
		j = getIntent().getExtras().getString("Position");
		k = getIntent().getExtras().getString("Team");
		Log.d("SampleActivity",j+k);
		Toast.makeText(this, j+k, Toast.LENGTH_LONG).show();
		Team = k.split("vs");
		if(j.equals("0")) {
			Teamname = Team[0];
		} else {
			Teamname = Team[1];
		}
		Teamname = Teamname.replace("\n", "");
		Teamname = Teamname.replace(" ", "");
		Carousel carousel = (Carousel)findViewById(R.id.carousel);
        carousel.setOnItemClickListener(new OnItemClickListener(){
        	@Override
			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {
        		MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				Teamid = db.getTeamId(Teamname);
				Playerid = db.getPlayerID(Teamid);
				Player = Playerid.split(" ");
				curplayer = Player[position];
				if(l==1)
				b.setText("Add Stats for Player " + db.getPlayername(curplayer));
				else
				b.setText("Add Stats for Player at position" + db.getPlayerpos(curplayer));
				db.close();
				
			}
        });
        carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				Teamid = db.getTeamId(Teamname);
				Playerid = db.getPlayerID(Teamid);
				Player = Playerid.split(" ");
				curplayer = Player[position];
				if(l==1)
				b.setText("Add Stats for Player " + db.getPlayername(curplayer));
				else
				b.setText("Add Stats for Player at position" + db.getPlayerpos(curplayer));
				db.close();
			}

			public void onNothingSelected(CarouselAdapter<?> parent) {
			}
        	
        }
        );
		
		twopm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.update2pmade(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		twopmiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.update2pmiss(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		ftm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateftmade(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		ftmiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateftmiss(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		threepm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updatefgmade(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		threepmiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updatefgmiss(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		orbd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateobrd(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		drbd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updatedbrd(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		assist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateassist(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		block.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateblock(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		foul.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updatefoul(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		steal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updatesteal(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		turnover.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(SampleActivity.this);
				db.open();
				db.updateturnover(curplayer);
				Toast.makeText(SampleActivity.this, db.getGame(), Toast.LENGTH_LONG).show();
				db.close();
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sample, menu);
		return true;
	}
	
	public void toggleMenu(View v){
		this.root.toggleMenu();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
