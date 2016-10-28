package com.bcits.recondiscon;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.BackgroundSyncServie;
import com.bcits.utils.SendRequest;
import com.sqlite.mvs.DbDisconnection;

public class DashBoard extends MasterActivity {
	String listNumber;
	Dialog dialogGetList;

	Button btnRefresh, btnDisconnect, btnReconnect;
	LinearLayout linearHead, linearReloadlist,linearDashboard;
	Button btnDownload,btnViewReport;
	Button  btnUpload;
	TextView txtTotalConnection, txtNotDisconnected, txtToReconnect,txtDisconnected, txtReconnected,txtSyncStatus,txtFailDisconnection;

	String sdoCodeOffline;
	String listNumberOffline;

	String totalConnections;
	ProgressDialog mProgressDialog;
	TextView txtSdoCode, txtMrCode,txtListNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setTitle("Dashboard");
		btnViewReport=(Button)findViewById(R.id.btnViewReport);
		linearDashboard=(LinearLayout)findViewById(R.id.linearDashboard);
		btnUpload = (Button) findViewById(R.id.btnUpload);
		btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
		txtListNumber= (TextView) findViewById(R.id.txtListNumber);
		btnReconnect = (Button) findViewById(R.id.btnReconnect);
		linearReloadlist = (LinearLayout) findViewById(R.id.linearReloadlist);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		linearHead = (LinearLayout) findViewById(R.id.linearHead);
		txtReconnected = (TextView) findViewById(R.id.txtReconnected);
		txtDisconnected = (TextView) findViewById(R.id.txtDisconnected);
		btnDownload = (Button) findViewById(R.id.btnDownload);
		txtTotalConnection = (TextView) findViewById(R.id.txtTotalConnection);
		txtNotDisconnected = (TextView) findViewById(R.id.txtNotDisconnected);
		txtToReconnect = (TextView) findViewById(R.id.txtToReconnect);
		txtSyncStatus=(TextView)findViewById(R.id.txtSyncStatus);
		txtFailDisconnection=(TextView)findViewById(R.id.txtFailDisconnection);
		
		txtSdoCode = (TextView) findViewById(R.id.txtSdoCode);
		txtMrCode = (TextView) findViewById(R.id.txtMrCode);
		txtSdoCode.setText(Login.location);
		callSerive();
	}
 
	
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder adb = new AlertDialog.Builder(DashBoard.this);
		adb.setTitle("Exit").setMessage("Do you want to exit?");
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(getApplicationContext(), Login.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("exit", "exit");
				finish();
				startActivity(intent);

			}
		});
		adb.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

			}
		});
		AlertDialog alert = adb.create();
		alert.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			txtMrCode.setText(Login.userName);
		} catch (Exception e) {
			e.printStackTrace();
			Intent intent = new Intent(DashBoard.this,	Login.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "Session Timeout", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		new GetConnectionStatus().execute();
		btnRefresh.setEnabled(true);
		btnDownload.setEnabled(true);
		
		btnViewReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(DashBoard.this, ViewReport.class));
			}
		});
		
		btnUpload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (haveNetworkConnection()) {
					DbDisconnection db = new DbDisconnection(getBaseContext());
					db.open();
					if (db.getOfflineDataMain()) {
						db.close();
						new SyncDisconnctionAsync().execute();
					} else {
						db.close();
						
						new GetConnectionStatus().execute();
						new AlertDialog.Builder(DashBoard.this).setCancelable(false)
								.setMessage("No more data to upload.")
								.setPositiveButton("Ok",null).show();
					}
				} else {
					new AlertDialog.Builder(DashBoard.this).setCancelable(false)
							.setMessage("Internet is not available")
							.setPositiveButton("Ok", null).show();
				}
			}
		});
		
		
		btnDisconnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DashBoard.this,DisconnectionView.class);
				startActivity(intent);

			}
		});
		btnReconnect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DashBoard.this,ReconnectionView.class);
				startActivity(intent);

			}
		});

		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btnDownload.setEnabled(false);
				btnRefresh.setEnabled(false);
				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setMessage(
								"Do you want to reload your list from the server ?")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										updateListFromServer();

									}
								}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										btnDownload.setEnabled(true);
										btnRefresh.setEnabled(true);
										dialog.dismiss();
										
									}
								}).show();

			}
		});

		btnDownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btnRefresh.setEnabled(false);
				btnDownload.setEnabled(false);
				if (haveNetworkConnection()) {
					final EditText edSearchList;
					ImageView imgSearchList, imgCancel;

					dialogGetList = new Dialog(DashBoard.this);
					dialogGetList.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialogGetList.setContentView(R.layout.custom_get_list);
					dialogGetList.setCancelable(true);
					
					dialogGetList.setOnCancelListener(new DialogInterface.OnCancelListener() {
						
						@Override
						public void onCancel(DialogInterface dialog) {
							btnRefresh.setEnabled(true);
							btnDownload.setEnabled(true);
							
						}
					});
					
					edSearchList = (EditText) dialogGetList.findViewById(R.id.edSearchList);
					imgSearchList = (ImageView) dialogGetList.findViewById(R.id.imgSearchList);
					imgCancel = (ImageView) dialogGetList.findViewById(R.id.imgCancel);

					dialogGetList.show();
					imgCancel.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialogGetList.dismiss();
							btnDownload.setEnabled(true);
							btnRefresh.setEnabled(true);
						}
					});
					imgSearchList.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if(AmrMethods.doubleClick()){
							listNumber = edSearchList.getText().toString();
//							DbDisconnection dbbb = new DbDisconnection(	getBaseContext());
//							dbbb.open();
							if (listNumber.trim().equals("")) {
								new AlertDialog.Builder(
										DashBoard.this)
										.setMessage("Please Enter List Number")
										.setPositiveButton("Ok", null).show();
							} /*else if (dbbb.checkListExists(listNumber,	Login.sdoCode)) {
								dbbb.close();
								new AlertDialog.Builder(
										DashBoard.this)
										.setTitle("This list is already exist")
										.setMessage(
												"Please press the 'Reload List' button to reload the same list.")
										.setNegativeButton("Ok", null).show();
							}*/ else {
//								dbbb.close();

								if (totalConnections.equals("0")) {
									new GetListDetailsAsync(false).execute();
								} else {
									new AlertDialog.Builder(
											DashBoard.this)
											.setTitle("Warning")
											.setMessage(
													"This process will delete all your previous data.")
											.setPositiveButton(
													"Download",
													new DialogInterface.OnClickListener() {

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															DbDisconnection d = new DbDisconnection(getBaseContext());
															d.open();
															boolean isDataPending=d.getAllOfflineData();
															d.close();
															if (isDataPending) {
																dialog.dismiss();
																new AlertDialog.Builder(
																		DashBoard.this)
																		.setTitle(
																				"Warning")
																		.setMessage(
																				"First upload all saved disconnected and reconnected data from phone to server.")
																		.setPositiveButton(
																				"Ok",
																				null)
																		.show();
															} else {
																new GetListDetailsAsync(false).execute();
															}

														}
													})
											.setNegativeButton("Cancel", null)
											.show();

								}
							}

						}}
					});
				} else {
					new AlertDialog.Builder(
							DashBoard.this)
							.setMessage("Internet is not available")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											btnDownload.setEnabled(true);
											btnRefresh.setEnabled(true);
										}
									}).show();
				}
			}
		});
	}

	private class GetListDetailsAsync extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		int progress;
		boolean isReload;
		public GetListDetailsAsync(boolean isReload) {
			this.isReload=isReload;
			mProgressDialog = new ProgressDialog(DashBoard.this);
			mProgressDialog.setTitle("Data saving to phone memory");
			mProgressDialog.setMessage("Please wait...");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			progress=0;
		}
		//TODO
		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressDialog.setProgress(values[0]);
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				String listNo,sdoCode;
				if(isReload)
				{
					listNo=listNumberOffline;
					sdoCode=sdoCodeOffline;
				}else {
					listNo=listNumber;
					sdoCode=Login.sdoCode;
				}
				System.out.println(Login.userName+"  "+sdoCode+"  "+listNo+" ==========isReload : "+isReload);
				
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
					jsonarray.put("MRCODE", Login.userName);
					jsonarray.put("SDOCODE", sdoCode);
					jsonarray.put("LISTNO", listNo);
					ja1.put(jsonarray);
					SendRequest req = new SendRequest();
					String url1 = "getDisReConnectionMobileUser";
					responsefromserver = req.sendRequest(url1, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (responsefromserver == null) {}

			else if (responsefromserver.equals("[]") || responsefromserver.contains("html")) {} else {
				DbDisconnection db = new DbDisconnection(getBaseContext());
				db.open();
				db.deleteDisconnectionTable();
				db.startBooster();
				try {
					JSONArray ja = new JSONArray(responsefromserver);
					mProgressDialog.setMax(ja.length());
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = ja.getJSONObject(i);
					
						publishProgress(progress);
						progress++;
						db.insertDisconnection(obj.getString("rrno"),
								obj.getString("listno"),
								obj.getString("listdate"),
								obj.getString("tariff"),
								obj.getString("disdate"),
								obj.getString("disfr"),
								obj.getString("sdocode"),
								obj.getString("remarks"),
								obj.getString("dramt"),
								obj.getString("rdngdate"),
								obj.getString("mtrcode"),
								obj.getString("arrears"),
								obj.getString("username"),
								obj.getString("datestamp"),
								obj.getString("reclistno"),
								obj.getString("redate"),
								obj.getString("ageing"),
								obj.getString("status"), 
								obj.getString("id"),
								obj.getString("consumer_name"),
								obj.getString("address"),
								obj.getString("fdrcode"),
								obj.getString("tccode"),
								obj.getString("poleno"),
						        obj.getString("latitude_dis").trim(),
						        obj.getString("longitude_dis").trim(),
						        obj.getString("PAY_AMOUNT").trim(),
						        obj.getString("PAY_RECDATE").trim(),
						        obj.getString("PAY_RECNO").trim(),
						        obj.getString("PAY_MODE").trim());

					}
					db.stopBooster();
					db.close();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void unused) {

			if (responsefromserver == null) {
				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setTitle("Warning")
						.setMessage("Server is down. Please try again later.")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();

			}
			
			else if (responsefromserver.contains("<html>")) {

				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setTitle("Warning")
						.setMessage("Error Found On Response. Please try again later.")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();

			
				
			}
			else if (responsefromserver.equals("[]")) {

				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setMessage(
								"No Data Found. Please check the list number again.")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

									}
								}).show();

			} else {
				new AlertDialog.Builder(
						DashBoard.this)
						.setMessage("Download is completed")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										btnRefresh.setEnabled(true);
										btnDownload.setEnabled(true);

									}
								}).show();
				
				try {
					dialogGetList.dismiss();
				} catch (Exception e) {
					System.out.println("For Reload dialogGetList would be null");
				}
			}

			mProgressDialog.dismiss();
			new GetConnectionStatus().execute();
		}
	}

	private class GetConnectionStatus extends AsyncTask<Void, Void, Void> {
		ProgressDialog progress;
		String totalReconne="-", totalNotDisconn="-", totalDisconnected="-",totalReconnected="-",listNo="-",syncStatus="0";
		String failedDisconnection="-";
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = new ProgressDialog(DashBoard.this);
			progress.setMessage("Loading Dashboard...");
			progress.setCancelable(false);
			progress.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				DbDisconnection db = new DbDisconnection(getBaseContext());
				db.open();
				totalConnections = db.getTotalConnection();
				totalReconne = db.getTodalReconnection();
				totalNotDisconn = db.getTodalDisconnection();
				totalDisconnected = db.getTotalDisconnected();
				totalReconnected = db.getTodalReConnected();
				failedDisconnection=db.getFailedDisconnection();
				
				String sdoList= db.getListNo();
//				sdo= sdoList.split("@@@")[0];
				listNo= sdoList.split("@@@")[1];
				
				syncStatus=db.getTotalNotSynced();
				
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			

			if (totalConnections.equals("0")) {
				linearHead.setVisibility(View.GONE);
				linearReloadlist.setVisibility(View.GONE);
				linearDashboard.setVisibility(View.GONE);
			} else {
				linearHead.setVisibility(View.VISIBLE);
				linearReloadlist.setVisibility(View.GONE);
				linearDashboard.setVisibility(View.VISIBLE);
			}
				
				txtTotalConnection.setText(totalConnections);
				txtToReconnect.setText(totalReconne);
				txtNotDisconnected.setText(totalNotDisconn);
				txtDisconnected.setText(totalDisconnected);
				txtReconnected.setText(totalReconnected);
//				txtSdoCode.setText(sdo);
				txtListNumber.setText(listNo);
				txtSyncStatus.setText(syncStatus);
				txtFailDisconnection.setText(failedDisconnection);
				if(Integer.parseInt(syncStatus)>0) {
					btnUpload.setVisibility(View.VISIBLE);
				}
				else {
					txtSyncStatus.setText("Fine");
					btnUpload.setVisibility(View.GONE);
				}
				
				progress.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.logout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btn_back:
		new AlertDialog.Builder(DashBoard.this)
			.setMessage("Do you want to logout?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Login.userName=null;
							Intent intent = new Intent(DashBoard.this,	Login.class);
							startActivity(intent);
							finish();
						}
					})
					.setNegativeButton("No", null).show();
		
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private boolean haveNetworkConnection() {
		boolean haveConnectedMobile = false;
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("MOBILE")
					|| ni.getTypeName().equalsIgnoreCase("wifi"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedMobile;
	}

	private void updateListFromServer() {
		if (haveNetworkConnection()) {
			DbDisconnection dbbb = new DbDisconnection(getBaseContext());
			dbbb.open();
			if (dbbb.getListNo().split("@@@")[0] != null
					&& dbbb.getListNo().split("@@@")[1] != null) {
				sdoCodeOffline = dbbb.getListNo().split("@@@")[0];
				listNumberOffline = dbbb.getListNo().split("@@@")[1];
				if (dbbb.checkListExists(listNumberOffline, sdoCodeOffline)
						&& dbbb.getAllOfflineData() == false) {
					new GetListDetailsAsync(true).execute();
				} else {
					new AlertDialog.Builder(
							DashBoard.this)
							.setMessage(
									"First upload all saved data to server.")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											btnRefresh.setEnabled(true);
											btnDownload.setEnabled(true);
										}
									}).show();
				}
			}
		} else {
			new AlertDialog.Builder(DashBoard.this).setCancelable(false)
					.setMessage("Internet is not available")
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									btnRefresh.setEnabled(true);
									btnDownload.setEnabled(true);

								}
							}).show();
		}
	}

	private void callSerive() {
		try {
			Intent myIntent = new Intent(DashBoard.this, BackgroundSyncServie.class);
			PendingIntent pendingIntent = PendingIntent.getService(DashBoard.this,0, myIntent, 0);
			AlarmManager alarmManager = (AlarmManager)DashBoard.this.getSystemService(Context.ALARM_SERVICE);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.add(Calendar.SECOND, 30);
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 900 * 1000, pendingIntent); // 900 * 1000 =  15 minutes
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class SyncDisconnctionAsync extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;
		String responsefromserver = null;
		JSONArray json = new JSONArray();
		DbDisconnection db ;
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(DashBoard.this);
			progressDialog.setMessage("Uploading data. Please Wait...");
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		protected Void doInBackground(Void... params) {
			try {
			     db = new DbDisconnection(getBaseContext());
				json = db.uploadData();
				
				SendRequest req = new SendRequest();
				String url1 = "UpdateDisReConnectionMobileUser";
				responsefromserver = req.sendRequest(url1, json);
				
				System.out.println("DISCONNECTION RESPONSE EEEEEEEEEEEEEEEEEEEEEE  "+responsefromserver);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			progressDialog.dismiss();
			if (responsefromserver == null) {
				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setMessage("Server is down. Please try again later.")
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								btnUpload.setEnabled(true);
								
							}
						}).show();

			} else if (responsefromserver.equals("[]")) {
				new AlertDialog.Builder(DashBoard.this).setCancelable(false)
						.setMessage("Server has some problem. Please try after some time")
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								btnUpload.setEnabled(true);
								
							}
						}).show();
			} else {
				String status = null;
				String serverId = null;
				try {
					JSONArray jsonarr = new JSONArray(responsefromserver);

					if (jsonarr != null) {
						for (int i = 0; i < jsonarr.length(); i++) {
							JSONObject obje = jsonarr.getJSONObject(i);
							status = obje.getString("status");
							serverId = obje.getString("serverid");
							if (status != null && status.equals("updated")) {
								db.updateOfflineToOnline(serverId);
							} 
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Data Uploaded",Toast.LENGTH_LONG).show();
				new GetConnectionStatus().execute();
				btnUpload.setEnabled(true);
			}

		}
	}
}
