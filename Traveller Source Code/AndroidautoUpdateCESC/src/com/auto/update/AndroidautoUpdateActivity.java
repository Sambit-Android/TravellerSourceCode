package com.auto.update;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class AndroidautoUpdateActivity extends Activity {
	/** Called when the activity is first created. */
	AlertDialog show;
	static String fileName = "CESC_NEW.apk";
	String[] apk_ver;
	SendRequestAutoupdate send;
	InputStream str,file_str;
	 int progress_temp =0;
	 static String[] arr_version;
	 static String domainName=null;
	 String apk_version=null;
public static boolean b = false ;	 
	
	 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		System.out.println("<--AndroidautoUpdateActivity---->");
		String getExtraIntent =   getIntent().getStringExtra("apk_version");
		String[] getValuesofExtraIntent = getExtraIntent.split("/")  ;
		
		/****FORMATE :  "JVVNL.apk"+"/"+versionName+"/"+domain_name  ***/
		
		// apk_version = getIntent().getStringExtra("apk_version");
		//fileName = getValuesofExtraIntent[0];
		apk_version = getValuesofExtraIntent[1];
		domainName = getValuesofExtraIntent[2];
		 
		//String apk_version = "HUBLIWATERBOARD.apk/2.0";
		 
		System.out.println("domainName" + domainName);
		System.out.println("apk_version" + apk_version);
		
		
		

	}

	public void onStart() {
		super.onStart();
		
		
		
		
		//new performBackgroundTask().execute();
		
		new performBackgroundTask1().execute();

	}
	

	public class performBackgroundTask1 extends AsyncTask<Void, Void, Void> {
		private ProgressDialog Dialog = new ProgressDialog(AndroidautoUpdateActivity.this);
	    boolean newVersionExists = false;
		protected void onPreExecute() {
		    super.onPreExecute();
			Dialog.setMessage("Please wait...");
			Dialog.setCancelable(false);
			Dialog.show();
			
			
		}
		
		
		protected Void doInBackground(Void... params) {
			
			
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			
			send  = new SendRequestAutoupdate();
			str = send.sendrequest("apk/getapkversion/"+imeino);
			Dialog.dismiss();
			return null;
		}
		protected void onPostExecute(Void unused) {
			if(str == null)
			{
				Dialog.dismiss();
				Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
				finish();
			}
			else{
				Dialog.dismiss();
				String latest_version = read(str);
				System.out.println("latest_version  ********"+latest_version);
				arr_version = latest_version.split("@");
				System.out.println("apk_version ********"+apk_version);
				//fileName = arr_version[2];
				/*if (!(arr_version[0].equals(apk_ver[1]))) */
				if(!arr_version[0].contains("<html>"))
				{
					
					 int isNewer = AndroidautoUpdateActivity.compareVersions(apk_version, latest_version); 

	                 if  ( newVersionExists = (isNewer == -1))
	                 {
	                //	 LoginActivity.i = LoginActivity.i+1;
	                	 b = true ;
	                		System.out.println("check*****************  apk boolean  "+newVersionExists);
	                		show = new AlertDialog.Builder(AndroidautoUpdateActivity.this)
							.setTitle("Info")
							.setMessage("New Apk available for this app,Download now")
							.setCancelable(false)
							.setPositiveButton("Download",	new DialogInterface.OnClickListener() 
							{
								public void onClick(DialogInterface arg0,int arg1) 
								{  // show.dismiss();
									new performBackgroundTaskdownload().execute();
								}
							})
						.show();
					
	                		
	                 }else {
	                	 System.out.println("check*********else********  apk boolean  "+newVersionExists);
	                	 finish();
	                 }
				}
				else
				{
					
					finish();
				}
			
				
			}
		
		}
		
		
	}
	public class performBackgroundTaskdownload extends AsyncTask<String, String, String>
	{
		
		 File filename = new File(Environment.getExternalStorageDirectory() , AndroidautoUpdateActivity.fileName);
		//File filename = new File("/sdcard/"+AndroidautoUpdateActivity.fileName);
		// File filename = dir ;
		   public  ProgressDialog mProgressDialog = new ProgressDialog(AndroidautoUpdateActivity.this);
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	            mProgressDialog.setMessage("Downloading file..");
	            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	            mProgressDialog.setCancelable(false);
	            mProgressDialog.setMax((int) SendRequestAutoupdate.length);
	            mProgressDialog.show();
	        }


		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			
			
			SendRequestAutoupdate send = new SendRequestAutoupdate();
			/*file_str = send.sendrequest("apk/downloadApk/"
					+ fileName);*/
			file_str = send.sendrequest("apk/downloadApk/"+imeino);
			
			
			if(file_str == null)
			{
				Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
				
				finish();
			}
			else
			{
				  
				File file = new File(filename.getPath());
				BufferedInputStream bis = new BufferedInputStream(file_str, 1024 * 50);
			
				
				Log.d("AndroidUpdate",	"downloaded file name:"+ filename);
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024 * 50];
				Log.d("AndroidUpdate",	"downloaded file name:"	+ fileName);
				int current = 0;
				try {
					
					 long total = 0;
	                    int progress = 0;

					while ((current = bis.read(buffer)) != -1) {
						
						 total += current;
	                        progress_temp = (int) ((int) total * 100 / SendRequestAutoupdate.length);
	                        System.out.println("SendRequest.length"+SendRequestAutoupdate.length);
	                       //mProgressDialog.setMessage("g"+ progress_temp);
	                        String[] val = new String[2];
	                        		val[0]=""+total;
	                        val[1] = ""+progress_temp;
	                        publishProgress(val);
	                        if (progress_temp % 10 == 0 && progress != progress_temp) {
	                            progress = progress_temp;
	                        }

						fos.write(buffer, 0, current);
					}
					
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}catch (Exception e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if(filename.exists()){
				
					System.out.println("filename size : "+ filename.length());
					if(SendRequestAutoupdate.length == filename.length())
					{
					
						mProgressDialog.dismiss();
						Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
						int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
						startActivity(int_install); 
						/* new AlertDialog.Builder(
									AndroidautoUpdateActivity.this)
									.setTitle("Info")
									.setCancelable(false)
									.setMessage(
											"Installing new version and  application requires restart once installation is completed")
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										
										public void onClick(DialogInterface arg0, int arg1) {
											
											Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
											int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
											startActivity(int_install);
											
										}
									})
									.show();*/
						
					}
					else
					{
						//mProgressDialog.dismiss();
					//	removeFile();
						Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
						finish();
					}
					}else{
						Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
						
						finish();
					// error msg 
				}
				
			}
			
			return null;
		}


		public void removeFile() {
			show = new AlertDialog.Builder(
					AndroidautoUpdateActivity.this)
					.setTitle("Info")
					.setMessage(
							"Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface arg0, int arg1) {
							filename.delete();
						}
					})
					.show();
		}
		
		
		
	
		protected void onProgressUpdate(String... values) {
		    mProgressDialog.setProgress(Integer.parseInt(values[1]));
		    mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
		    System.out.println("total"+values[0]);
		    DecimalFormat df = new DecimalFormat("###.##");
		    System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
		    mProgressDialog.setMessage("Downloading file.. "+df.format(((Float.valueOf(values[0])/1048576)*100)/100)+" of "+df.format(((Float.valueOf(SendRequestAutoupdate.length)/1048576)*100)/100)+" MB");

		}
		 @Override
	        protected void onPostExecute(String unused) {
	        	mProgressDialog.dismiss();
	        }



		
	}
	
	public class performBackgroundTaskdownload_new extends AsyncTask<String, String, String>
	{
		String file_ex = "yes";
		File filename = new File("/sdcard/"+AndroidautoUpdateActivity.fileName);
		   public  ProgressDialog mProgressDialog;
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            mProgressDialog = new ProgressDialog(AndroidautoUpdateActivity.this);
	            mProgressDialog.setMessage("Downloading file..");
	            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	            mProgressDialog.setCancelable(false);
	            mProgressDialog.setMax((int) SendRequestAutoupdate.length);
	            mProgressDialog.show();
	        }


		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			
			
			SendRequestAutoupdate send = new SendRequestAutoupdate();
			/*file_str = send.sendrequest("apk/downloadApk/"
					+ fileName);*/
			
			String version=arg0[0]; 
			 System.out.println("check*********version********  apk :"+version);
			 file_str = null;
			 try {
				 file_str = send.sendrequest("apk/downloadApk/"+imeino);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			  file_ex = "yes";
			if(file_str == null)
			{
				file_ex = "no";
				//Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
				//mProgressDialog.dismiss();
				//finish();
			}
			else
			{
				  
				File file = new File(fileName);
				BufferedInputStream bis = new BufferedInputStream(
						file_str, 1024 * 50);
			
				
				Log.d("AndroidUpdate",
						"downloaded file name:"
								+ fileName);
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(
							"/sdcard/" + file);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024 * 50];
				Log.d("AndroidUpdate",
						"downloaded file name:"
								+ fileName);
				int current = 0;
				try {
					
					 long total = 0;
	                    int progress = 0;

					while ((current = bis.read(buffer)) != -1) {
						
						 total += current;
	                        progress_temp = (int) ((int) total * 100 / SendRequestAutoupdate.length);
	                        System.out.println("SendRequest.length"+SendRequestAutoupdate.length);
	                       //mProgressDialog.setMessage("g"+ progress_temp);
	                        String[] val = new String[2];
	                        		val[0]=""+total;
	                        val[1] = ""+progress_temp;
	                        publishProgress(val);
	                        if (progress_temp % 10 == 0 && progress != progress_temp) {
	                            progress = progress_temp;
	                        }

						fos.write(buffer, 0, current);
					}
					
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}catch (Exception e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if(filename.exists()){
				
					System.out.println("filename size : "+ filename.length());
					if(SendRequestAutoupdate.length == filename.length())
					{
					
						
						Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
						int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
						startActivity(int_install); 
						/* new AlertDialog.Builder(
									AndroidautoUpdateActivity.this)
									.setTitle("Info")
									.setCancelable(false)
									.setMessage(
											"Installing new version and  application requires restart once installation is completed")
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										
										public void onClick(DialogInterface arg0, int arg1) {
											
											Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
											int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
											startActivity(int_install);
											
										}
									})
									.show();*/
						
					}
					else
					{
						//myProgressDialog.dismiss();
					//	removeFile();
						Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
						finish();
					}
					}else{
						Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
						
						finish();
					// error msg 
				}
				
			}
			
			return null;
		}


		public void removeFile() {
			show = new AlertDialog.Builder(
					AndroidautoUpdateActivity.this)
					.setTitle("Info")
					.setMessage(
							"Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface arg0, int arg1) {
							filename.delete();
						}
					})
					.show();
		}
		
		
		
	
		protected void onProgressUpdate(String... values) {
		    mProgressDialog.setProgress(Integer.parseInt(values[1]));
		    mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
		    System.out.println("total"+values[0]);
		    DecimalFormat df = new DecimalFormat("###.##");
		    System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
		    mProgressDialog.setMessage("Downloading file.. "+df.format(((Float.valueOf(values[0])/1048576)*100)/100)+" of "+df.format(((Float.valueOf(SendRequestAutoupdate.length)/1048576)*100)/100)+" MB");

		}
		 @Override
	        protected void onPostExecute(String unused) {
		if(file_ex.equalsIgnoreCase("no")){
			Toast.makeText(getApplicationContext(), ("Sorry...! unable to download file contact admin"), Toast.LENGTH_LONG).show();
			finish();
		}
	        	mProgressDialog.dismiss();
	        
		 }



		
	}
	public class performBackgroundTaskdownload_newZZZ extends AsyncTask<String, String, String>
	{
		File filename = new File("/sdcard/"+AndroidautoUpdateActivity.fileName);
		   public  ProgressDialog mProgressDialog;
		   String status_server = "ERROR";
		   
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            mProgressDialog = new ProgressDialog(AndroidautoUpdateActivity.this);
	            mProgressDialog.setMessage("Downloading file..");
	            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	            mProgressDialog.setCancelable(false);
	           mProgressDialog.setMax((int) SendRequestAutoupdate.length);
	            mProgressDialog.show();
	        }


		@Override
		protected String doInBackground(String... arg0) {
			SendRequestAutoupdate send = new SendRequestAutoupdate();
			
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			
			
			
			file_str = send.sendrequest("apk/downloadApk/"+imeino);
			
			
			if(file_str == null)
			{
				status_server  = "Sorry...! Server is down.";
				//Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
				//finish();
			}
			else
			{
				File file = new File(fileName);
				BufferedInputStream bis = new BufferedInputStream(file_str, 1024 * 50);
				Log.d("AndroidUpdate",	"downloaded file name:"	+ fileName);
				FileOutputStream fos = null;
				try 
				{
					fos = new FileOutputStream(	"/sdcard/" + file);
				} catch (FileNotFoundException e) {
					status_server = "SD card is about to full. Press menu to delete the old files in sdcard. " ;
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024 * 50];
				Log.d("AndroidUpdate",	"downloaded file name:"	+ fileName);
				int current = 0;
				try {
					 long total = 0;
					 int progress = 0;

					while ((current = bis.read(buffer)) != -1) {
						 total += current;
						 progress_temp = (int) ((int) total * 100 / SendRequestAutoupdate.length);
	                        String[] val = new String[2];
	                        val[0]=""+total;
	                        val[1] = ""+progress_temp;
	                        publishProgress(val);
	                        if (progress_temp % 10 == 0 && progress != progress_temp) {
	                            progress = progress_temp;
	                        }
						fos.write(buffer, 0, current);
					}
					
					if(filename.exists()){
						
						System.out.println("filename size : "+ filename.length());
						if(SendRequestAutoupdate.length == filename.length())
						{
						
							status_server = "NOERROR" ;
							
						/*	Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
							int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
							startActivity(int_install);*/
							
							
							
						}
						else
						{
							status_server ="Sorry...! Error occurred while Downloading" ;
							

							//finish();
						}
						}else{
							
							status_server  = "Sorry...! Server is down.";
							//Toast.makeText(getApplicationContext(), ("Sorry...! Server is down."), Toast.LENGTH_LONG).show();
							
						//	finish();
					}
					
					
					
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}catch (Exception e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}


		public void removeFile() {
			show = new AlertDialog.Builder(
					AndroidautoUpdateActivity.this)
					.setTitle("Info")
					.setMessage(
							"Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface arg0, int arg1) {
							filename.delete();
						}
					})
					.show();
		}
		
		
		
	
		protected void onProgressUpdate(String... values) {
		    mProgressDialog.setProgress(Integer.parseInt(values[1]));
		    mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
		    System.out.println("total"+values[0]);
		    DecimalFormat df = new DecimalFormat("###.##");
		    System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
		    mProgressDialog.setMessage("Downloading file.. "+df.format(((Float.valueOf(values[0])/1048576)*100)/100)+" of "+df.format(((Float.valueOf(SendRequestAutoupdate.length)/1048576)*100)/100)+" MB");

		}
		 @Override
	        protected void onPostExecute(String unused) {
	        	mProgressDialog.dismiss();
	        	
	        	if(status_server.equalsIgnoreCase("NOERROR") )
	        	{
	        		Intent int_install = new Intent(AndroidautoUpdateActivity.this,	InstallApk.class);
					int_install.putExtra("serverfileLength", SendRequestAutoupdate.length);
					startActivity(int_install);
	        	}else{
	        		Toast.makeText(getApplicationContext(), (status_server), Toast.LENGTH_LONG).show();
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
	
	
	
	
	
	
	public static final int compareVersions(String ver1, String ver2) {

	    String[] vals1 = ver1.split("\\.");
	    String[] vals2 = ver2.split("\\.");
	    int i=0;
	    while(i<vals1.length&&i<vals2.length&&vals1[i].equals(vals2[i])) {
	      i++;
	    }

	    if (i<vals1.length&&i<vals2.length) {
	        int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
	        return diff<0?-1:diff==0?0:1;
	    }

	    return vals1.length<vals2.length?-1:vals1.length==vals2.length?0:1;
	}

	
	
	
	
}