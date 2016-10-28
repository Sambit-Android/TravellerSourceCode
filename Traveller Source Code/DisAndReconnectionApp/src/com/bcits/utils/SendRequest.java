package com.bcits.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

public class SendRequest {
	private int timeout = 30000;

//	public static String url1 = "http://192.168.2.106:7070/bsmartcloud/"; // REMITH
	//public static String url1 = "http://192.168.4.148:7070/bsmartcloud/"; // PRADEEP
	public static String url1 = " http://1.23.144.185:8888/bsmartcloud/"; // LIVE
//	public static String url1 = "http://192.168.2.192:8080/bsmartcloud/"; // SAMBIT
	public SendRequest() {

	} 

	public String sendRequest(String methodName, JSONArray array) {

		BufferedReader reader = null;

		try {
			URL url = new URL(url1 + methodName);
			// Opening the connection (Not setting or using CONNECTION_TIMEOUT)
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// Post Method
			con.setRequestMethod("POST");
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			// To enable inputting values using POST method
			// (Basically, after this we can write the dataToSend to the body of
			// POST method)
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// OutputStreamWriter writer = new
			// OutputStreamWriter(con.getOutputStream());
			// Writing dataToSend to outputstreamwriter

			byte[] outputBytes = array.toString().getBytes("UTF-8");
			OutputStream os = con.getOutputStream();
			// Sending the data to the server - This much is enough to send data
			// to server
			os.write(outputBytes);
			// But to read the response of the server, you will have to
			// implement the procedure below
			os.flush();
			os.close();

			// Data Read Procedure - Basically reading the data comming line by
			// line
			StringBuilder sb = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) { // Read till there is
															// something
															// available
				sb.append(line + "\n"); // Reading and saving line by line - not
										// all at once
			}
			line = sb.toString(); // Saving complete data received in string,
									// you can do it differently


			return line.trim();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close(); // Closing the
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}