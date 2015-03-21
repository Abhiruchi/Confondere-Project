package com.example.project_basketballstats;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3 extends Activity {
Button signup;
TextView v;
EditText t,b,c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen3);
		signup = (Button) findViewById(R.id.btnR);
		t = (EditText) findViewById(R.id.reg_fullname);
		b = (EditText) findViewById(R.id.reg_password);
		c = (EditText) findViewById(R.id.reg_email);
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(Screen3.this);
				db.open();
				if(!c.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
					Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
				}
				else if(t.getText().toString().equals("")||b.getText().toString().equals(""))
		        {
		                Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
		        }
		        else
		        {
		            // Save the Data in Database
		        	if(db.getUsername(t.getText().toString()).equals("not exists")) {
		        	db.insertEntry(t.getText().toString(),b.getText().toString(),c.getText().toString());
		            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
		        	} else {
		        	Toast.makeText(getApplicationContext(), "Username Already Taken", Toast.LENGTH_LONG).show();
		        	}
			}
				db.close();
			}
		});
		v = (TextView) findViewById(R.id.link_to_login);
		v.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a = new Intent(Screen3.this, Screen2.class);
				startActivity(a);
			}
		});
	}
}
