package com.example.project_basketballstats;

import com.example.flyoutmenuexample.view.viewgroup.FlyOutContainer;
import com.example.project_basketballstats.controls.Carousel;
import com.example.project_basketballstats.controls.CarouselAdapter;
import com.example.project_basketballstats.controls.CarouselAdapter.OnItemClickListener;
import com.example.project_basketballstats.controls.CarouselAdapter.OnItemSelectedListener;
import com.example.project_basketballstats.controls.CarouselItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.flyoutmenuexample.view.viewgroup.FlyOutContainer;


public class Stats extends Activity{
	
	String k;
	FlyOutContainer root;
	String Teamid;
	String Playerid;
	String[] Player;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      //  this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_sample, null);
	//	this.setContentView(root);
       k = getIntent().getExtras().getString("Team");
        Carousel carousel = (Carousel)findViewById(R.id.carousel);
        carousel.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(CarouselAdapter<?> parent, View view,
				int position, long id) {	
				Intent a = new Intent(Stats.this, SampleActivity.class);
				startActivity(a);
				//toggleMenu(view);
				Toast.makeText(Stats.this, 
						String.format("%s has been clicked", 
						((CarouselItem)parent.getChildAt(position)).getName()), 
						Toast.LENGTH_SHORT).show();				
			}
        	
        });

        carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {
				MainDatabase db = new MainDatabase(Stats.this);
				db.open();
				Teamid = db.getTeamId(k);
				Playerid = db.getPlayerID(Teamid);
				Player = Playerid.split(" ");
				
		      final TextView txt = (TextView)(findViewById(R.id.selected_item));
		        
				switch(position){
				case 0:
					txt.setText(db.getPlayerStats(Player[0]));
					break;
				case 1:
					txt.setText(db.getPlayerStats(Player[1]));
					break;
				case 2:
					txt.setText(db.getPlayerStats(Player[2]));
					break;
				case 3:
					txt.setText(db.getPlayerStats(Player[3]));
					break;
				case 4:
					txt.setText(db.getPlayerStats(Player[4]));
					break;
				}	
				db.close();
			}

			public void onNothingSelected(CarouselAdapter<?> parent) {
			}
        	
        }
        );
       
        
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

}
