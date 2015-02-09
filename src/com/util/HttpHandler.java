package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import android.os.AsyncTask;
import android.util.Log;

public class HttpHandler extends AsyncTask<String, Integer, Double> {
	
	public String responseStr = "Celal Selcuk";
	
	public HttpHandler(){
					
		
	}

	@Override
	protected Double doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
		
	
	public void postData(String valueIWantToSend) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://10.0.2.2:8080/QR_Market_Web/Auth");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("myHttpData", valueIWantToSend));
			httppost.setEntity((HttpEntity) new UrlEncodedFormEntity(nameValuePairs));				
			
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
									
			StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        responseStr = out.toString();
		        
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
	}

}
