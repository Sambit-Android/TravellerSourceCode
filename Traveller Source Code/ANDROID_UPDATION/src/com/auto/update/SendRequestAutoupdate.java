package com.auto.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class SendRequestAutoupdate {

	public static long length = 0;

	public InputStream sendrequest(String url) {
		
		HttpParams httpParameters = new BasicHttpParams();
		
		int timeoutConnection = 3 * 10 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
		
		int timeoutSocket = 3 * 10 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		
		 String str_url=AndroidautoUpdateActivity.domainName+ url ;

		Log.e("request url ", str_url + "");


		HttpGet request = new HttpGet(str_url);

		HttpResponse response = null;
		try {
			response = httpclient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream instream = null;
		if (response != null) {

			HttpEntity entity = response.getEntity();
			try {
				instream = entity.getContent();
				length = entity.getContentLength();

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return instream;
		} else {
			return instream;
		}
	}

	public String read(InputStream instream) {
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			BufferedReader r = new BufferedReader(new InputStreamReader(
					instream));
			for (String line = r.readLine(); line != null; line = r.readLine()) {
				sb.append(line);
			}

			instream.close();

		} catch (IOException e) {
		}
		return sb.toString();

	}
}
