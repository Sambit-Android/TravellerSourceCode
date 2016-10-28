package com.utils;

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
import org.json.JSONException;

import Utils.ErrorClass;
import android.util.Log;

import com.bcits.bsmartbilling.Login;
import com.bcits.bsmartbilling.MasterLibraryFunction;

public class Sendrequest {

	public  static long length = 0;

	//  String str_url = "http://192.168.2.164:9090/mobilecestrm/ws/";
	//String str_url = "http://192.168.2.178:8080/mobilecestrm/ws/";    
	String str_url = "http://"+Login.server_ip+"/mobilecestrm/ws/";   
	
	public InputStream sendrequest(String url) 
	{
		ErrorClass.errorMessage="";
		HttpParams httpParameters = new BasicHttpParams();
		
		int timeoutConnection = 3 * 10 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
		
		int timeoutSocket = 3 * 10 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		str_url = str_url+url ;
		Log.e("request url ", str_url + "");
		System.out.println("webservice URl" + str_url);
		HttpGet request = new HttpGet(str_url);
		HttpResponse response = null;
		try 
		{
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			length=entity.getContentLength();
			System.out.println( length + "length********************************8");
			System.out.println(response + "response");
		
		} catch (ClientProtocolException e) 
		{
			System.out.println("Client ProtocolException " + e.getMessage());
			ErrorClass.errorMessage = e.getMessage();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		
		} catch (IOException e) 
		{
			System.out.println("IO Exception " + e.getMessage());
			ErrorClass.errorMessage = e.toString();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		}
		
		InputStream instream = null;
		
		if (response != null) 
		{
			HttpEntity entity = response.getEntity();
			System.out.println(entity + "webservicedddddddddddd");
			try 
			{
				instream = entity.getContent();
				System.out.println(instream + "//////////" );
			
			} catch (IllegalStateException e) 
			{
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			} catch (IOException e) 
			{
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			}

			return instream;
		} else 
		{
			return instream;
		}
		
	}

	public InputStream sendrequestLogin(String url)
	{
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 3 * 10 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
		
		int timeoutSocket = 3 * 10 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		str_url = str_url+url ;
		Log.e("request url ", str_url + "");
		System.out.println("webservice localhosts: " + str_url);
		HttpGet request = new HttpGet(str_url);
		HttpResponse response = null;
		try 
		{
			response = httpclient.execute(request);
	    	System.out.println(response + "response");
		
		} catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		InputStream instream = null;
		if (response != null) 
		{
			HttpEntity entity = response.getEntity();
			System.out.println(entity + "webservicedddddddddddd");
			try
			{
				instream = entity.getContent();
				System.out.println(instream + "//////////");
			
			} catch (IllegalStateException e) 
			{
				e.printStackTrace();

			} catch (IOException e) 
			{
				e.printStackTrace();
			}

			return instream;
			
		} else 
		{
			return instream;
		}
	}

	
	public String uploadtoServerfromlocaldb(String url, JSONArray ja1) throws JSONException
	{
		str_url = str_url+url ;
		HttpPost requestPost = new HttpPost(str_url);
		System.out.println(" link  "+ url+"****************************");
		System.out.println(" str_url  : "+ str_url);
		HttpParams httpParameters = new BasicHttpParams();
		
		int timeoutConnection = 3 * 60 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
		
		int timeoutSocket = 3 * 60 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		//BasicHttpResponse response = null;

		StringEntity s = null;
		try {
			s = new StringEntity(ja1.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			MasterLibraryFunction.collectLogs1(e2.toString());
		}
		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		s.setContentType("application/json");

		requestPost.setEntity(s);
		HttpResponse response = null;
		try {
			response = httpClient.execute(requestPost);
			
		} catch (ClientProtocolException e) {
			System.out.println("Client ProtocalException" + e.getMessage());
			ErrorClass.errorMessage = e.getMessage();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		} catch (IOException e) {
			System.out.println("IO Exception" + e.getMessage());
			ErrorClass.errorMessage = e.getMessage();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		}
		InputStream instream = null;
		if (response != null) {
			HttpEntity entity = response.getEntity();

			try {
				instream = entity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			}
			String message = read(instream);
			if(httpClient != null)
				httpClient.getConnectionManager().shutdown();
			return message;
		}
		if(httpClient != null)
			httpClient.getConnectionManager().shutdown();
		
		return null;
	}
	
	public String uploadtoServerfromlocaldb_forService(String url, JSONArray ja1) throws JSONException
	{
		str_url = str_url+url ;
		HttpPost requestPost = new HttpPost(str_url);
		System.out.println(" link  "+ url+"****************************");
		System.out.println(" str_url  : "+ str_url);
		HttpParams httpParameters = new BasicHttpParams();
		
		int timeoutConnection = 3 * 60 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
		
		int timeoutSocket = 3 * 60 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		//BasicHttpResponse response = null;

		StringEntity s = null;
		try {
			s = new StringEntity(ja1.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			MasterLibraryFunction.collectLogs1(e2.toString());
		}
		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		s.setContentType("application/json");

		requestPost.setEntity(s);
		HttpResponse response = null;
		try {
			response = httpClient.execute(requestPost);
			
		} catch (ClientProtocolException e) {
			System.out.println("Client ProtocalException" + e.getMessage());
			ErrorClass.errorMessage = e.getMessage();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		} catch (IOException e) {
			System.out.println("IO Exception" + e.getMessage());
			ErrorClass.errorMessage = e.getMessage();
			e.printStackTrace();
			MasterLibraryFunction.collectLogs1(e.toString());
		}
		InputStream instream = null;
		if (response != null) {
			HttpEntity entity = response.getEntity();

			try {
				instream = entity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MasterLibraryFunction.collectLogs1(e.toString());
			}
			String message = read(instream);
			if(httpClient != null)
				httpClient.getConnectionManager().shutdown();
			return message;
		}
		if(httpClient != null)
			httpClient.getConnectionManager().shutdown();
		
		return null;
	}
	public String read(InputStream instream) 
	{
		StringBuilder sb = null;
		try 
		{
			sb = new StringBuilder();
			BufferedReader r = new BufferedReader(new InputStreamReader(instream));
			for (String line = r.readLine(); line != null; line = r.readLine())
			{
				sb.append(line);
			}

			instream.close();

		} catch (IOException e) 
		{
			
		}
		return sb.toString();
	}

}
