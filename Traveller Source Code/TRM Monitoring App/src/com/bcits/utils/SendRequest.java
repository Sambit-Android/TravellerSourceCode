package com.bcits.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

import android.util.Log;

public class SendRequest {
	int timeConnct = 20000;


	 //public static  String url1 ="http://192.168.2.178:7070/bsmartcloud/"; //my local machine BSMARTCLOUD
	 //public static  String url1 ="http://192.168.2.178:7070/mobilecestrm/"; //my local machine MOBILECESTRM
	 //public static  String url1 ="http://1.23.144.189:8001/bsmartcloud/"; //LIVE GESCOM
	 public static  String url1 ="http://1.23.144.185:8888/bsmartcloud/"; //LIVE CESC
	 //public static  String url1 ="http://192.168.2.192:8080/bsmartcloud/"; //Sambit PGRS
	//public static  String url2 ="http://192.168.2.199:8080/bsmartpgrs/"; //96 PGRS
	 //public static  String url2 ="http://122.166.217.53:8101/bsmartpgrs/"; //live PGRS
	 public static  String url2 ="http://45.127.101.236:8181/bsmartpgrs/"; //live PGRS 2
   //public static  String url1 ="http://192.168.4.148:7106/bsmartcloud/"; //pradeep kumar
	 //public static  String url1 ="http://192.168.4.148:7070/bsmartcloud/"; //pradeep kumar
	//192.168.4.148:7070/bsmartcloud/
	//	 public static  String url1 ="http://1.23.144.189:7070/vcloudengine/"; //static ip
	//	 public static  String url1 ="http://192.168.2.81:9100/vcloudengine/"; //test server
	 //public static  String url1 ="http://192.168.4.104:8080/bsmartcloud/"; //test server
	public SendRequest() {
	}

	public InputStream sendRequest(String url55) {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = timeConnct;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = timeConnct;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);

		HttpGet request = new HttpGet(url1 + url55);

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
				if (entity != null) {
					instream = entity.getContent();
				}

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
			e.printStackTrace();
		}
		return sb.toString();

	}
	public String readDisconnection(InputStream instream) {
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
			e.printStackTrace();
		}
		return sb.toString();

	}

	public String readReconnection(InputStream instream) {
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
			e.printStackTrace();
		}
		return sb.toString();

	}

	public String disconnectionSend(String url12, JSONArray ja1) {
		HttpParams httpParameters = new BasicHttpParams();
		String message = null;
		try{
			HttpConnectionParams.setConnectionTimeout(httpParameters,timeConnct);

			HttpConnectionParams.setSoTimeout(httpParameters, timeConnct);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost request = new HttpPost(url1 + url12);
			StringEntity s = null;
			try {
				s = new StringEntity(ja1.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			s.setContentType("application/json");
			request.setEntity(s);
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
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String message1 = readDisconnection(instream);
				httpclient.getConnectionManager().shutdown();
				return message1;
			}
			message = readDisconnection(instream);
		}
		catch(Exception e){
			message = null ;
		}
		return message;
	}
	public String reconnectionSend(String url12, JSONArray ja1) {
		HttpParams httpParameters = new BasicHttpParams();
		String message = null;
		try{
			HttpConnectionParams.setConnectionTimeout(httpParameters,timeConnct);

			HttpConnectionParams.setSoTimeout(httpParameters, timeConnct);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost request = new HttpPost(url1 + url12);
			StringEntity s = null;
			try {
				s = new StringEntity(ja1.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			s.setContentType("application/json");
			request.setEntity(s);
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
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String message1 = readReconnection(instream);
				httpclient.getConnectionManager().shutdown();
				return message1;
			}
			message = readReconnection(instream);
		}
		catch(Exception e){
			message = null ;
		}
		return message;
	}
	public String uploadToServer(String url12, JSONArray ja1) {
		HttpParams httpParameters = new BasicHttpParams();
		String message = null;
		try{
			HttpConnectionParams.setConnectionTimeout(httpParameters,timeConnct);

			HttpConnectionParams.setSoTimeout(httpParameters, timeConnct);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);


			
			HttpPost request = new HttpPost(url1 + url12);
			StringEntity s = null;
			try {
				s = new StringEntity(ja1.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			s.setContentType("application/json");
			request.setEntity(s);
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
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String message1 = read(instream);
				httpclient.getConnectionManager().shutdown();
				return message1;
			}
			message = read(instream);
		}
		catch(Exception e){
			message = null ;
		}
		return message;
	}
	public String uploadToServer2(String url12, JSONArray ja1) {
		HttpParams httpParameters = new BasicHttpParams();
		String message = null;
		try{
			HttpConnectionParams.setConnectionTimeout(httpParameters,timeConnct);

			HttpConnectionParams.setSoTimeout(httpParameters, timeConnct);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);


			
			HttpPost request = new HttpPost(url2 + url12);
			StringEntity s = null;
			try {
				s = new StringEntity(ja1.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			s.setContentType("application/json");
			request.setEntity(s);
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
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String message1 = read(instream);
				httpclient.getConnectionManager().shutdown();
				return message1;
			}
			message = read(instream);
		}
		catch(Exception e){
			message = null ;
		}
		return message;
	}

	public InputStream sendrequestPhoneStatus(String urlurll) {

		HttpClient httpclient = new DefaultHttpClient();

		HttpGet request = new HttpGet(url1 + urlurll);

		request.addHeader("Accept", "text/plain");
		HttpResponse response = null;
		try {
			response = httpclient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		InputStream instream = null;
		if (response != null) {
			HttpEntity entity = response.getEntity();
			try {
				instream = entity.getContent();
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
}
