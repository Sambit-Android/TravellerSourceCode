package com.bcits.disconrecon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class Sendrequest {

	public  static long length = 0;


	//String str_url = "http://192.168.2.172:8080/bsmartcloud/";
	
	String str_url = "http://1.23.144.189:8001/bsmartcloud/";
	
	
	public InputStream sendrequest(String url) 
	{
		HttpParams httpParameters = new BasicHttpParams();

		int timeoutConnection = 70 * 10 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);

		int timeoutSocket = 70 * 10 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		str_url = str_url+url ;
		Log.e("request url ", str_url + "");
		
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("sendrequest method of sendrequest class is executing");
		
		System.out.println("webservice URl" + str_url);
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		
		
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
				System.out.println(instream + "//////////" );

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

	public InputStream sendrequestLogin(String url)
	{
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5 * 10 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);

		int timeoutSocket = 5 * 10 * 1000;
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

	public InputStream uploadtoServerfromlocaldb(String url, JSONArray ja1) throws JSONException{
		str_url = str_url+url ;
		HttpPost requestPost = new HttpPost(str_url);
		System.out.println(" link  "+ url+"****************************");
		System.out.println(" str_url   "+ str_url+"  ****************************");
		HttpParams httpParameters = new BasicHttpParams();

		int timeoutConnection = 70* 60 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);

		int timeoutSocket = 70 * 60 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		BasicHttpResponse response = null;
		try
		{
			StringEntity se = new StringEntity( ja1.toString());
			
			
			
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			System.out.println("url*******************"+se);

			requestPost.setEntity(se);
			System.out.println("url*******************"+requestPost.toString());

			response = (BasicHttpResponse) httpClient.execute(requestPost);
			System.out.println(response);

		} catch (ClientProtocolException e) 
		{
			System.out.println(response + "response");
			e.printStackTrace();

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		InputStream instream = null;
		if (response != null) 
		{
			HttpEntity entity = response.getEntity();
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
          e.printStackTrace();
		}
		return sb.toString();
	}
}
