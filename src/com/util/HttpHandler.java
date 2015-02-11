package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.qr_market_android.R;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class HttpHandler extends AsyncTask< Map , Integer, String> {
	
	public String responseStr = "Celal Selcuk";
	
	public HttpHandler(View v){
		
	}

	@Override
	protected String doInBackground(Map... params) {
		// TODO Auto-generated method stub
		return postData(params[0]);
	}
	
	@Override
    protected void onPostExecute(String result) {
		Log.i("HttpHandler" , "onPostExecute called...");
    }
	
	
	public List getParameterPair(Map m){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();			
		
		Iterator iter = m.keySet().iterator();
		while(iter.hasNext()){
			String key = (String) iter.next();
			nameValuePairs.add(new BasicNameValuePair(key, (String) m.get(key)));
			
			Log.i(key , (String) m.get(key));
		}		
		return nameValuePairs;		
	}
	
	public String postData(Map param) {			
		
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://10.0.2.2:8080/QR_Market_Web/Auth");
//		HttpPost httppost = new HttpPost("http://10.0.2.2:8080/QR_Market_Web/Auth?authDo=carpeLogin&cduMail=kskaraca@gmail.com&cduPass=12345");

		try {
			
			httppost.setEntity((HttpEntity) new UrlEncodedFormEntity( getParameterPair(param) ));				
			
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
									
			StatusLine statusLine = response.getStatusLine();
		    
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        
		               
		        
		        responseStr = out.toString();
		        
		        try {
		        	
					JSONObject reader = new JSONObject(responseStr);
					Iterator iterator = reader.keys();
					while(iterator.hasNext()){
						String key = (String)iterator.next();
						Log.i("JSON KEY" , key);
						
					}
										
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        
		        
		        Log.i("RESPONSE" , responseStr);
		        
		        
		        
		    } else{
		        //Closes the connection.
		        response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
			

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		return responseStr;
	}

}
