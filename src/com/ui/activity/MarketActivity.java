package com.ui.activity;

import com.example.qr_market_android.R;
import com.example.qr_market_android.R.id;
import com.example.qr_market_android.R.layout;
import com.example.qr_market_android.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MarketActivity extends ActionBarActivity {

	// necessary for barcodes 
	static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market);
		
		Button qrReader = (Button) findViewById(R.id.barcode_reader);
		
		qrReader.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {

				scanQR(v);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.market, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	/*
	 * This method used to get result data of sub-activities(intents)
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
	            toast.show();
	            
	            /*
	             * Push data to servlet and get production
	             */
	        }
	    }
	}
	
	
	public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(MarketActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }
	
	public void scanBar(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(MarketActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }
	
	private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
		 AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
		 downloadDialog.setTitle(title);
		 downloadDialog.setMessage(message);
		 downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
		     public void onClick(DialogInterface dialogInterface, int i) {
		         Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
		         Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		         try {
		             act.startActivity(intent);
		         } catch (ActivityNotFoundException anfe) {
		         }
		         
		     }
		 });
		 downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
		     public void onClick(DialogInterface dialogInterface, int i) {
		     }
		 });
		 return downloadDialog.show();
	 }
}
