package com.ui.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.qr_market_android.R;
import com.util.HttpHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public String responseStr="xxx";	
	public static EditText editTextExample;
	public View vi;	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		
		Button twitter = (Button) findViewById(R.id.twitter);
		
		vi = this.getWindow().getDecorView().findViewById(android.R.id.content);

		Button button = (Button) findViewById(R.id.Login);
		final EditText cduName = (EditText) findViewById(R.id.LoginUserName);
		final EditText cduPass = (EditText) findViewById(R.id.LoginPassword);
					
		button.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {

				Map parameters = new HashMap();
				parameters.put("authDo", "carpeLogin");
				parameters.put("cduMail", cduName.getText().toString());
				parameters.put("cduPass", cduPass.getText().toString());
				
				//new HttpHandler(vi).execute(parameters);
				
				goToMarketActivity();

			}
		});
		
		twitter.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				createAlarm("Deneme" , 6 , 2);

			}
		});
		
				
				
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.social_login) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void goToMarketActivity(){
		Intent intent = new Intent(this,MarketActivity.class);
		startActivity(intent);
	}
	
	
		
 	public void createAlarm(String message, int hour, int minutes) {
	    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
	            .putExtra(AlarmClock.EXTRA_MESSAGE, message)
	            .putExtra(AlarmClock.EXTRA_HOUR, hour)
	            .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivity(intent);
	    }
	}
	
	
	

}
