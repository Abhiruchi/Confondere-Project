package com.example.project_basketballstats;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
//import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Screen2 extends Activity {
	Button login;
	String k;
    EditText username, password;
    String user,pass;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen2);
		
		login = (Button) findViewById(R.id.btnLogin);
		username = (EditText) findViewById(R.id.tv_username);
		password = (EditText) findViewById(R.id.tv_password);
		
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				MainDatabase db = new MainDatabase(Screen2.this);
				db.open();
				String userName=username.getText().toString();
                String pass=password.getText().toString();
                if(userName.equals("")||pass.equals("")) {
                	Toast.makeText(Screen2.this, " Vacant Field", Toast.LENGTH_LONG).show();
                } else {

                String k = "";
                Toast.makeText(getApplicationContext(), k, Toast.LENGTH_LONG);

                if(pass.equals(k))
                {
                    Toast.makeText(Screen2.this, " Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent a = new Intent(Screen2.this, Tab.class);
                    final String u =  db.getUserId(username.getText().toString());
                    Log.d("Screen2", u);
                    a.putExtra("UserID", u);
    				startActivity(a);
                }
                else if (k.equals("NOT EXIST")) {
                	Toast.makeText(Screen2.this, " User Name does not exist", Toast.LENGTH_LONG).show();
                } else 
                {
                    Toast.makeText(Screen2.this, " User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
                }
                db.close();
			}
		});
	}
}
