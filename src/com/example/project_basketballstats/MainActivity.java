package com.example.project_basketballstats;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Button login, signup;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.screen1);
		
		final Animation animBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
		final ImageView image = (ImageView)findViewById(R.id.ball);
		
		image.startAnimation(animBounce);
		
		login = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(MainActivity.this,Screen2.class);
				
			}
		});
		
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(MainActivity.this, Screen3.class);
				startActivity(a);
			}
		});
		
	} 

}