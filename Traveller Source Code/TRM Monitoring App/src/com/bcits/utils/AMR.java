package com.bcits.utils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.os.AsyncTask;

public class AMR {
	String serverTime;
	long networkTS = 0;
	private final Context context;
	public static String currentTime;

	public AMR(Context ctx) {
		this.context = ctx;
	}

	public void getCurrentTime() {
		if (AmrMethods.getDefaults("currentTime", context) != null) {
			try {
				networkTS = Long.parseLong(AmrMethods.getDefaults(
						"currentTime", context));
			} catch (Exception e) {
				Calendar c = Calendar.getInstance();
				networkTS = c.getTimeInMillis();
			}
		} else {
			Calendar c = Calendar.getInstance();
			networkTS = c.getTimeInMillis();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.ENGLISH);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(networkTS);
		currentTime = formatter.format(calendar.getTime());
	}

	public void getServerTime() {
		getCurrentTime();
		Calendar c = Calendar.getInstance();
		long dateTime = 0;
		if (AmrMethods.getDefaults("currentTime", context) != null) {
			dateTime = Long.parseLong(AmrMethods.getDefaults("currentTime",
					context));
		} else {
			new GetServerTimeAsync().execute();
		}

		if (c.getTimeInMillis() - dateTime > 3600000) {
			new GetServerTimeAsync().execute();
		} else if (dateTime - c.getTimeInMillis() > 3600000) {
			new GetServerTimeAsync().execute();
		} else {
		}
	}

	public class GetServerTimeAsync extends AsyncTask<Void, Void, Void> {
		InputStream str1;
		SendRequest send;
		String response = null;
		protected void onPreExecute() {
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				send = new SendRequest();
				str1 = send.sendRequest(" ");
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (str1 == null) {

			} else {
				response = send.read(str1);
				if (response == null) {

				}
				
				else if(response.contains("<html>")){
					
				}
				else {
					AmrMethods.setDefaults("currentTime", response.trim() + "",
							context);
				}
			}
		}
	}
}
