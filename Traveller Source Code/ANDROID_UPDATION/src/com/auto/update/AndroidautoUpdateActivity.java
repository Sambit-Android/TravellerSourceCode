package com.auto.update;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

@SuppressLint("SdCardPath")
public class AndroidautoUpdateActivity extends Activity {
	static String fileName ;
	String[] apk_ver;
	SendRequestAutoupdate send;
	InputStream str, file_str;
	int progress_temp = 0;
	static String[] arr_version;
	static String domainName = null;
	String apk_version = null;
	public static boolean b = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.update_main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		String getExtraIntent = getIntent().getStringExtra("apk_version");
		String[] getValuesofExtraIntent = getExtraIntent.split("@@@");

		fileName=getValuesofExtraIntent[0];
		apk_version = getValuesofExtraIntent[1];
		domainName = getValuesofExtraIntent[2];

		/*try {
			File folder = new File("/sdcard/");

			 File[] filenamestemp = folder.listFiles();

			for(int i=0;i<filenamestemp.length;i++){
			      if(filenamestemp[i].getAbsolutePath().toString().contains(fileName.substring(0, 3)))
			         filenamestemp[i].delete();     

			    }
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
	}

	public void onStart() {
		super.onStart();

		new GetApkVersion().execute();

	}

	@Override
	public void onBackPressed() {
	}

	private class GetApkVersion extends AsyncTask<Void, Void, Void> {
	    ProgressDialog Dialog = new ProgressDialog(	AndroidautoUpdateActivity.this,R.style.MyTheme2);
		boolean newVersionExists = false;

		protected void onPreExecute() {
			super.onPreExecute();
			Dialog.setMessage("Checking If App can be Updated...");
			Dialog.setCancelable(false);
			Dialog.show();
		}

		protected Void doInBackground(Void... params) {

			send = new SendRequestAutoupdate();
			str = send.sendrequest("apk/getapkversion/"+apk_version+"/"+fileName);
			return null;
		}

		protected void onPostExecute(Void unused) {
			Dialog.dismiss();
			if (str == null) {
				Toast.makeText(getApplicationContext(),("Sorry...! Server is down (APK UPDATION)."), Toast.LENGTH_LONG).show();
				finish();
			} else {
				String latest_version = read(str);
				arr_version = latest_version.split("@");
				if (!arr_version[0].contains("html")) {

					int isNewer = 0;
					try {
						isNewer = AndroidautoUpdateActivity.compareVersions(apk_version, latest_version);
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Version parse error. Server down (APK UPDATE)", Toast.LENGTH_LONG).show();
						finish();
					}

					if (newVersionExists = (isNewer == -1)) {
						b = true;
						 new AlertDialog.Builder(
								AndroidautoUpdateActivity.this)
								.setTitle("Info")
								.setMessage(
										"New Apk available for this app,Download now")
								.setCancelable(false)
								.setPositiveButton("Download",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface arg0,
													int arg1) {
												new DownloadApk()
														.execute();
											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface dialog,
													int which) {

												finish();
											}
										}).show();

					} else {
						finish();
					}
				} else {

					finish();
				}

			}

		}

	}

	private class DownloadApk extends AsyncTask<String, String, String> {
		File filename = new File("/sdcard/" + AndroidautoUpdateActivity.fileName);
		public ProgressDialog mProgressDialog;
		String status_server = "ERROR";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(AndroidautoUpdateActivity.this);
			mProgressDialog.setMessage("Downloading New Application..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setMax((int) SendRequestAutoupdate.length);
			mProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			SendRequestAutoupdate send = new SendRequestAutoupdate();
			file_str = send.sendrequest("apk/downloadApk/"+apk_version+"/"+fileName);

			if (file_str == null) {
				status_server = "Sorry...! Server is down.";
				// Toast.makeText(getApplicationContext(),
				// ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
				// finish();
			} else {
				File file = new File(fileName);
				BufferedInputStream bis = new BufferedInputStream(file_str,	1024 * 50);
				Log.d("AndroidUpdate", "downloaded file name:" + fileName);
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream("/sdcard/"  + file);
				} catch (FileNotFoundException e) {
					status_server = "SD card is about to full. Press menu to delete the old files in sdcard. ";
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024 * 50];
				Log.d("AndroidUpdate", "downloaded file name:" + fileName);
				int current = 0;
				try {
					long total = 0;
					int progress = 0;

					while ((current = bis.read(buffer)) != -1) {
						total += current;
						progress_temp = (int) ((int) total * 100 / SendRequestAutoupdate.length);
						String[] val = new String[2];
						val[0] = "" + total;
						val[1] = "" + progress_temp;
						publishProgress(val);
						if (progress_temp % 10 == 0
								&& progress != progress_temp) {
							progress = progress_temp;
						}
						fos.write(buffer, 0, current);
					}

					if (filename.exists()) {

						if (SendRequestAutoupdate.length == filename.length()) {

							status_server = "NOERROR";

						} else {
							status_server = "Sorry...! Error occurred while Downloading";

						}
					} else {

						status_server = "Sorry...! Server is down.";

					}

				} catch (IOException e) {

					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					fos.flush();
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					fos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					bis.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			return null;
		}


		protected void onProgressUpdate(String... values) {
			mProgressDialog.setProgress(Integer.parseInt(values[1]));
			mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
			DecimalFormat df = new DecimalFormat("###.##");
			mProgressDialog	.setMessage("Downloading file.. "+ df.format(((Float.valueOf(values[0]) / 1048576) * 100) / 100)
							+ " of "+ df.format(((Float.valueOf(SendRequestAutoupdate.length) / 1048576) * 100) / 100)+ " MB");

		}

		@Override
		protected void onPostExecute(String unused) {
			mProgressDialog.dismiss();

			if (status_server.equalsIgnoreCase("NOERROR")) {
				Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
				int_install.putExtra("serverfileLength",SendRequestAutoupdate.length);
				startActivity(int_install);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), (status_server),Toast.LENGTH_LONG).show();
				finish();
			}

		}

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private String read(InputStream instream) {
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

	private static final int compareVersions(String ver1, String ver2) throws Exception {

		String[] vals1 = ver1.split("\\.");
		String[] vals2 = ver2.split("\\.");
		int i = 0;
		while (i < vals1.length && i < vals2.length
				&& vals1[i].equals(vals2[i])) {
			i++;
		}

		if (i < vals1.length && i < vals2.length) {
			int diff = Integer.valueOf(vals1[i]).compareTo(
					Integer.valueOf(vals2[i]));
			return diff < 0 ? -1 : diff == 0 ? 0 : 1;
		}

		return vals1.length < vals2.length ? -1
				: vals1.length == vals2.length ? 0 : 1;
	}

}