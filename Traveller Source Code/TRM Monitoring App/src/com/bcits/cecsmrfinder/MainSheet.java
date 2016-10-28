package com.bcits.cecsmrfinder;
/*package com.bcits.cecsip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.GetSetCircle;
import com.bcits.utils.SendRequest;
import com.sqlite.cescip.DBCircles;
import com.sqlite.cescip.DBDivision;
import com.sqlite.cescip.DBFeederNo;
import com.sqlite.cescip.DBSubDivision;
import com.sqlite.cescip.DBUnit;

public class MainSheet extends Activity {
	ArrayList<String> listdata;ArrayList<String> listdata2;ArrayList<String> listdata3;ArrayList<String> listdata4;ArrayList<String> listdata5;ArrayList<String> listdata6;
	ArrayList<GetSetCircle> arrayCircle;ArrayList<GetSetCircle> arrayDivision;ArrayList<GetSetCircle> arraysubDivision;ArrayList<GetSetCircle> arrayunit;ArrayList<GetSetCircle> oandmunit;ArrayList<GetSetCircle> arraystation;ArrayList<GetSetCircle> feederarray;
	String listNumber;
	AlertDialog show;
	Dialog dialogGetList;
	String[] circlestring = null;
	String circle_load,div_load,sub_div_load,feederno_load;
	String unit_post="",substation_post="";
//	DatePicker dateofinsp;
	Spinner circle,division,omsubdiv,omunit,substation,feederno;
	EditText sheetno,dateofinsp; 
	Button btn_create,btn_cancel;
	Calendar c = Calendar.getInstance(); 
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH)+1;
	int dt = c.get(Calendar.DATE);
	String curr_date =  dt+"/"+month+"/"+year;
	List<String> list2;
	List<String> list1;
	ArrayAdapter<String> adapter4;
	ArrayAdapter<String> adapter5;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Add Details");
		setContentView(R.layout.add_details);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		circle = (Spinner)findViewById(R.id.spinner2);
		division = (Spinner)findViewById(R.id.spinner3);
		omsubdiv = (Spinner)findViewById(R.id.spinner4);
		omunit = (Spinner) findViewById(R.id.spinner6);
		substation = (Spinner) findViewById(R.id.spinner7);
		

		sheetno = (EditText) findViewById(R.id.sheetNo);
		feederno = (Spinner) findViewById(R.id.spinner17);
		dateofinsp = (EditText) findViewById(R.id.dateofinsp);
		btn_create = (Button) findViewById(R.id.btnCreate);
		btn_cancel = (Button) findViewById(R.id.btnCancel);
		
		
		
		

		if (AmrMethods.doubleClick()) {
		Boolean isSDPresent2 = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (isSDPresent2) {
			if (haveNetworkConnection()) {
				try {
					new performBackgroundTaskLoadMasterdetails().execute();
					new performBackgroundTaskLoadFeeder().execute();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				
				
				
				DBCircles db=new DBCircles(getBaseContext());
				db.open();
				Cursor cursor =db.getOfflineCircles();
				if(cursor == null)
				{
					Toast.makeText(getApplicationContext(),"No Circle Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata = new ArrayList<String>();
					while (cursor != null && cursor.moveToPosition(k)) {
									k++;
									listdata.add(cursor.getColumnIndex(DBCircles.COLUMN_CIRCLE_NAME), cursor.getString(0));
								}
					ArrayAdapter<String> adapter3;
					adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
					adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					circle.setAdapter(adapter3);
					circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						    	circle_load = circle.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailsDivision().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db.close();
				
			
			//starts here division
				
				
				DBDivision db1=new DBDivision(getBaseContext());
				db1.open();
				Cursor cursor1 =db1.getOfflineDivisions();
				if(cursor1 == null)
				{
					Toast.makeText(getApplicationContext(),"No Division Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata2 = new ArrayList<String>();
					while (cursor1 != null && cursor1.moveToPosition(k)) {
									k++;
									listdata2.add(cursor1.getColumnIndex(DBDivision.COLUMN_DIVISION_NAME), cursor1.getString(0));
								}
					ArrayAdapter<String> adapter31;
					adapter31 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata2);
					adapter31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					division.setAdapter(adapter31);
					 division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 div_load = division.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailssubDivision().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db1.close();
				
			
			
			//subDivision
				
				DBSubDivision db2=new DBSubDivision(getBaseContext());
				db2.open();
				Cursor cursor2 =db2.getOfflineSubDivisions();
				if(cursor2 == null)
				{
					Toast.makeText(getApplicationContext(),"No Sub Division Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata3 = new ArrayList<String>();
					while (cursor2 != null && cursor2.moveToPosition(k)) {
									k++;
									listdata3.add(cursor2.getColumnIndex(DBDivision.COLUMN_DIVISION_NAME), cursor2.getString(0));
								}
					ArrayAdapter<String> adapter310;
					adapter310 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata3);
					adapter310.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					omsubdiv.setAdapter(adapter310);
					 omsubdiv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 sub_div_load = omsubdiv.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailsunit().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
			
				}
				db2.close();
			
			//unit
				DBUnit db3=new DBUnit(getBaseContext());
				db3.open();
				Cursor cursor3 =db3.getOfflineUnits();
				if(cursor3 == null)
				{
					Toast.makeText(getApplicationContext(),"No unit/substation Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata4 = new ArrayList<String>();
					while (cursor3 != null && cursor3.moveToPosition(k)) {
									k++;
									listdata4.add(cursor3.getColumnIndex(DBUnit.COLUMN_UNIT_NAME), cursor3.getString(0));
								}
					ArrayAdapter<String> adapter10;
					adapter10 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata4);
					adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					omunit.setAdapter(adapter10);
					 omunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 unit_post = omunit.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetaisstation().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db3.close();
			
				
				//substation
				
				DBUnit db4=new DBUnit(getBaseContext());
				db4.open();
				Cursor cursor4 =db4.getOfflineUnits();
				if(cursor4 == null)
				{
					Toast.makeText(getApplicationContext(),"No unit/substation Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata5 = new ArrayList<String>();
					while (cursor4 != null && cursor4.moveToPosition(k)) {
									k++;
									listdata5.add(cursor4.getColumnIndex(DBUnit.COLUMN_UNIT_NAME), cursor4.getString(0));
								}
					ArrayAdapter<String> adapter15;
					adapter15 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata5);
					adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					substation.setAdapter(adapter15);
					 substation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 substation_post = substation.getSelectedItem().toString();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db4.close();
				
				
				//feeder number and name offline 
				
				DBFeederNo db6=new DBFeederNo(getBaseContext());
				db6.open();
				Cursor cursor6 =db6.getOfflineFeeders();
				if(cursor6 == null)
				{
					Toast.makeText(getApplicationContext(),"No Circle Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata6 = new ArrayList<String>();
					while (cursor6 != null && cursor6.moveToPosition(k)) {
									k++;
									listdata6.add(cursor6.getColumnIndex(DBFeederNo.COLUMN_FEEDERNUMBER_NAME), cursor6.getString(0));
								}
					ArrayAdapter<String> adapter36;
					adapter36 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata6);
					adapter36.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					feederno.setAdapter(adapter36);
					feederno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 feederno_load = feederno.getSelectedItem().toString();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db6.close();
				
			}
			}

		
		}
		else {
			show = new AlertDialog.Builder(MainSheet.this)
					.setMessage("No SD CARD FOUND")
					.setPositiveButton("Ok", null).show();
		}

	
		dateofinsp.setText(curr_date);
		btn_create.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 if (circle_load.matches("")||circle_load.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Choose a Circle")
								.setPositiveButton("OK", null).show();

					} 
				 else if (div_load.matches("")||div_load.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Choose a Division")
								.setPositiveButton("OK", null).show();

					} 
				 else if (sub_div_load.matches("")||sub_div_load.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Choose a Sub Division")
								.setPositiveButton("OK", null).show();

					} 
				 else if ((sheetno.getText().toString()).matches("")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Enter Sheet Number")
								.setPositiveButton("OK", null).show();

					} 
				 else if (unit_post.matches("")||unit_post.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Enter OM Unit")
								.setPositiveButton("OK", null).show();

					} 
				 else if (substation_post.matches("")||substation_post.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Enter Sub Station")
								.setPositiveButton("OK", null).show();

					} 
				 else if (feederno_load.matches("")||feederno_load.matches("Select")) {
						show = new AlertDialog.Builder(MainSheet.this)
								.setTitle("Info").setMessage("Enter Feeder Name")
								.setPositiveButton("OK", null).show();

					} 
				 
					else{
				
				new GetDisconnectionStaus().execute();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(MainSheet.this, Transformer_Inspection.class);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
	//	btnUpload.setEnabled(true);
		//new GetDisconnectionStaus().execute();

		btnUpload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btnUpload.setEnabled(false);
				if(haveNetworkConnection()){
				Transformers db = new Transformers(getBaseContext());
				db.open();
				if(db.getOfflineDataReConn()){
				new SyncDisconnctionAsync().execute();
				}else{
					show =new AlertDialog.Builder(Transformer_Inspection.this).setCancelable(false).setMessage("No more data to upload.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							btnUpload.setEnabled(true);
							
						}
					}).show();
				}
			}else{
				show =new AlertDialog.Builder(Transformer_Inspection.this).setCancelable(false).setMessage("Internet is not available").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						btnUpload.setEnabled(true);
						
					}
				}).show();
			
			}
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainSheet.this,Transformer_Inspection.class);
				startActivity(intent);

			}
		});

	
	}



	private class GetDisconnectionStaus extends AsyncTask<Void, Void, Void> {
		ProgressDialog progress;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = new ProgressDialog(MainSheet.this);
			progress.setMessage("wait...");
			progress.setCancelable(false);
			progress.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			Transformers dbAdapter = new Transformers(getBaseContext());
			dbAdapter.open();
			dbAdapter .insertInspectionDetails(curr_date, " ", " ", " ", " ", " "," ", " ", dtcunique.getText().toString(), ipset.getText().toString(), total_ipsets.getText().toString(),light_arr_post, eleven_kv_post, eleven_kv_hg.getText().toString(), eleven_dolo.getText().toString(), ht_dir_conn_post, ltpkit.getText().toString(),distbox.getText().toString(), fuse_cutos.getText().toString(), dir_conn_post, dtc_meter_post, body.getText().toString(), neutral.getText().toString(), light_arr_dtc_post,latitudetext.getText().toString(), longitudetext.getText().toString(), villagetext.getText().toString(), Login.userName, Login.userName , curr_date, "Mysore", "HUNSUR", "160", "5600", "schneider electric", "123 ImpG", "500", "10", "NARSIPUR", "0", "Approve", "0");
			dbAdapter.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			Intent myIntent = new Intent(MainSheet.this, Add_transformer.class);
			myIntent.putExtra("DateofInspection",dateofinsp.getText().toString());
			myIntent.putExtra("Circle",circle_load);
			myIntent.putExtra("Division",div_load);
			myIntent.putExtra("omsubdivision",sub_div_load);
			myIntent.putExtra("enm_sheet",sheetno.getText().toString());
			myIntent.putExtra("unit",unit_post);
			myIntent.putExtra("substation",substation_post);
			myIntent.putExtra("feederno",feederno_load);
			myIntent.putExtra("flag","flagadd");
			startActivity(myIntent);
			
			progress.dismiss();
					
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
	public class performBackgroundTaskLoadMasterdetails extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("circle", "circle");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getCircleList";
					responsefromserver = req.uploadToServer(url1, ja1);
		
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				
				//circle
				
				DBCircles db=new DBCircles(getBaseContext());
				db.open();
				Cursor cursor =db.getOfflineCircles();
				if(cursor == null)
				{
					Toast.makeText(getApplicationContext(),"No Circle Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata = new ArrayList<String>();
					while (cursor != null && cursor.moveToPosition(k)) {
									k++;
									listdata.add(cursor.getColumnIndex(DBCircles.COLUMN_CIRCLE_NAME), cursor.getString(0));
								}
					ArrayAdapter<String> adapter3;
					adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
					adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					circle.setAdapter(adapter3);
					circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						    	circle_load = circle.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailsDivision().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db.close();
				//End of circle
				
				//Division
				
				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Circle Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
				JSONArray ja = null;
				try {
					ja = new JSONArray(responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				listdata = new ArrayList<String>();
				listdata.add("Select");
				arrayCircle = new ArrayList<GetSetCircle>();
				if (ja != null) {
					
					DBCircles dbAdapter = new DBCircles(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setCircle(obj.getString("circle"));
							arrayCircle.add(comp);
							listdata.add(obj.getString("circle"));
							dbAdapter.addcircle(obj.getString("circle"));
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						}
					dbAdapter.close();
				}
				ArrayAdapter<String> adapter3;
				adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
				adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				circle.setAdapter(adapter3);
				circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					    	circle_load = circle.getSelectedItem().toString();
							new performBackgroundTaskLoadMasterdetailsDivision().execute();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
		
			} else {
				
				
			}
		}
		
		
		}
	public class performBackgroundTaskLoadFeeder extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("feederNoAndName", "feederNoAndName");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getFeederCodeList";
					responsefromserver = req.uploadToServer(url1, ja1);
		
					Log.e("Response", responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();

				DBFeederNo db6=new DBFeederNo(getBaseContext());
				db6.open();
				Cursor cursor6 =db6.getOfflineFeeders();
				if(cursor6 == null)
				{
					Toast.makeText(getApplicationContext(),"No Circle Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata6 = new ArrayList<String>();
					while (cursor6 != null && cursor6.moveToPosition(k)) {
									k++;
									listdata6.add(cursor6.getColumnIndex(DBFeederNo.COLUMN_FEEDERNUMBER_NAME), cursor6.getString(0));
								}
					ArrayAdapter<String> adapter36;
					adapter36 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata6);
					adapter36.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					feederno.setAdapter(adapter36);
					feederno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 feederno_load = feederno.getSelectedItem().toString();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db6.close();
				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Feeder Number Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
				JSONArray ja = null;
				try {
					ja = new JSONArray(responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				listdata6 = new ArrayList<String>();
				listdata6.add("Select");
				feederarray = new ArrayList<GetSetCircle>();
				if (ja != null) {
					DBFeederNo dbAdapter = new DBFeederNo(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setFeederno(obj.getString("feederNoAndName"));
							feederarray.add(comp);
							listdata6.add(obj.getString("feederNoAndName"));
							dbAdapter.addFeeder(obj.getString("feederNoAndName"));
							
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					dbAdapter.close();
				}
				ArrayAdapter<String> adapter13;
				adapter13 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata6);
				adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				feederno.setAdapter(adapter13);
				feederno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						 feederno_load = feederno.getSelectedItem().toString();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
		
			} else {
			//	Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				//loginFromMobile();
			}
		}
		
		
		}
	
	public class performBackgroundTaskLoadMasterdetailsDivision extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;String responsefromserver2 = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("division", "division");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getDivisionList/"+circle_load;
					responsefromserver = req.uploadToServer(url1, ja1);
					String url2 = "ipSets/getAllDivisionList";
					responsefromserver2 = req.uploadToServer(url2, ja1);
					
					Log.e("Response", responsefromserver);
					Log.e("Response", responsefromserver2);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				
				
				
				
				DBDivision db1=new DBDivision(getBaseContext());
				db1.open();
				Cursor cursor1 =db1.getOfflineDivisions();
				if(cursor1 == null)
				{
					Toast.makeText(getApplicationContext(),"No Division Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata2 = new ArrayList<String>();
					while (cursor1 != null && cursor1.moveToPosition(k)) {
									k++;
									listdata2.add(cursor1.getColumnIndex(DBDivision.COLUMN_DIVISION_NAME), cursor1.getString(0));
								}
					ArrayAdapter<String> adapter31;
					adapter31 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata2);
					adapter31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					division.setAdapter(adapter31);
					 division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 div_load = division.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailssubDivision().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db1.close();
				
				
				
				
				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Division Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
				JSONArray ja = null;
				JSONArray ja2 = null;
				try {
					ja = new JSONArray(responsefromserver);
					ja2 = new JSONArray(responsefromserver2);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				listdata2 = new ArrayList<String>();
				arrayDivision = new ArrayList<GetSetCircle>();
				if (ja != null) {
					listdata2.add("Select");
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setDivision(obj.getString("division"));
							arrayDivision.add(comp);
							listdata2.add(obj.getString("division"));
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				if (ja2 != null) {
					DBDivision dbAdapter = new DBDivision(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja2.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja2.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							dbAdapter.adddivision(obj.getString("subDivision"));
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					dbAdapter.close();
				}
				ArrayAdapter<String> adapter4;
				 adapter4 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata2);
	    		 adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    		 division.setAdapter(adapter4);
				
				
	    		 division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						 div_load = division.getSelectedItem().toString();
							new performBackgroundTaskLoadMasterdetailssubDivision().execute();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
		
			} else {
				Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				//loginFromMobile();
			}
		}
		
		
		}
	
	
	
	public class performBackgroundTaskLoadMasterdetailssubDivision extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		String responsefromserver2 = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("subdivision", "subdivision");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getSubDivisionList/"+div_load;
					responsefromserver = req.uploadToServer(url1, ja1);
					String url2 = "ipSets/getSearchSubDivisionList";
					responsefromserver2 = req.uploadToServer(url2, ja1);
		
					Log.e("Response", responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
			//	Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				
				DBSubDivision db2=new DBSubDivision(getBaseContext());
				db2.open();
				Cursor cursor2 =db2.getOfflineSubDivisions();
				if(cursor2 == null)
				{
					Toast.makeText(getApplicationContext(),"No Sub Division Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata3 = new ArrayList<String>();
					while (cursor2 != null && cursor2.moveToPosition(k)) {
									k++;
									listdata3.add(cursor2.getColumnIndex(DBDivision.COLUMN_DIVISION_NAME), cursor2.getString(0));
								}
					ArrayAdapter<String> adapter310;
					adapter310 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata3);
					adapter310.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					omsubdiv.setAdapter(adapter310);
					 omsubdiv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 sub_div_load = omsubdiv.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetailsunit().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
			
				}
				db2.close();
				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Sub Division Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
			JSONArray ja = null;
			JSONArray ja2 = null;
				try {
					ja = new JSONArray(responsefromserver);
					ja2 = new JSONArray(responsefromserver2);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				listdata3 = new ArrayList<String>();
				listdata3.add("Select");
				arraysubDivision = new ArrayList<GetSetCircle>();
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setSubdivision(obj.getString("subDivision"));
							arraysubDivision.add(comp);
							listdata3.add(obj.getString("subDivision"));
							
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				if (ja2 != null) {
					DBSubDivision dbAdapter = new DBSubDivision(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja2.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja2.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							dbAdapter.addSubdivision(obj.getString("subDivision"));
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					dbAdapter.close();
				}
				
				 adapter5 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata3);
	    		 adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    		 omsubdiv.setAdapter(adapter5);
				
				
	    		 omsubdiv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						 sub_div_load = omsubdiv.getSelectedItem().toString();
							new performBackgroundTaskLoadMasterdetailsunit().execute();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
		
			} else {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				//loginFromMobile();
			}
		}
		
		
		}
	public class performBackgroundTaskLoadMasterdetailsunit extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		String responsefromserver2 = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("unit", "unit");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getUnitList/"+sub_div_load;
					responsefromserver = req.uploadToServer(url1, ja1);
					String url2 = "ipSets/getSearchUnitList";
					responsefromserver2 = req.uploadToServer(url2, ja1);
		
					Log.e("Response", responsefromserver);
					Log.e("Response2", responsefromserver2);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				
				DBUnit db3=new DBUnit(getBaseContext());
				db3.open();
				Cursor cursor3 =db3.getOfflineUnits();
				if(cursor3 == null)
				{
					Toast.makeText(getApplicationContext(),"No unit/substation Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata4 = new ArrayList<String>();
					while (cursor3 != null && cursor3.moveToPosition(k)) {
									k++;
									listdata4.add(cursor3.getColumnIndex(DBUnit.COLUMN_UNIT_NAME), cursor3.getString(0));
								}
					ArrayAdapter<String> adapter10;
					adapter10 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata4);
					adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					omunit.setAdapter(adapter10);
					 omunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 unit_post = omunit.getSelectedItem().toString();
								new performBackgroundTaskLoadMasterdetaisstation().execute();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db3.close();
				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Sub Division Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
				JSONArray ja = null;
				JSONArray ja2 = null;
				try {
					ja = new JSONArray(responsefromserver);
					ja2 = new JSONArray(responsefromserver2);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				listdata4 = new ArrayList<String>();
				listdata4.add("Select");
				arrayunit = new ArrayList<GetSetCircle>();
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setUnit(obj.getString("unit"));
							arrayunit.add(comp);
							listdata4.add(obj.getString("unit"));
							
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				if (ja2 != null) {
					DBUnit dbAdapter = new DBUnit(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja2.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja2.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							dbAdapter.addunit(obj.getString("unit"));
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					dbAdapter.close();
				}
				ArrayAdapter<String> adapter6;
				 adapter6 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata4);
	    		 adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    		 omunit.setAdapter(adapter6);
				
				
	    		 omunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						 unit_post = omunit.getSelectedItem().toString();
							new performBackgroundTaskLoadMasterdetaisstation().execute();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
		
			} else {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				//loginFromMobile();
			}
		}
		
		
		}
	public class performBackgroundTaskLoadMasterdetaisstation extends
	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainSheet.this);
			mProgressDialog.setMessage("Laoding...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("unit", "unit");
					ja1.put(jsonarray);
		
					SendRequest req = new SendRequest();
					String url1 = "ipSets/getSubStationList/"+unit_post;
					responsefromserver = req.uploadToServer(url1, ja1);
		
					Log.e("Response", responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}
		
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				DBUnit db4=new DBUnit(getBaseContext());
				db4.open();
				Cursor cursor4 =db4.getOfflineUnits();
				if(cursor4 == null)
				{
					Toast.makeText(getApplicationContext(),"No unit/substation Found",Toast.LENGTH_LONG).show();
				}
				else
				{
					int k=0;
					listdata5 = new ArrayList<String>();
					while (cursor4 != null && cursor4.moveToPosition(k)) {
									k++;
									listdata5.add(cursor4.getColumnIndex(DBUnit.COLUMN_UNIT_NAME), cursor4.getString(0));
								}
					ArrayAdapter<String> adapter15;
					adapter15 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata5);
					adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					substation.setAdapter(adapter15);
					 substation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							 substation_post = substation.getSelectedItem().toString();
						   }
						 public void onNothingSelected(AdapterView<?> parent) {
						    }
						});
				}
				db4.close();				
			}
		
			else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("Something went wrog try again Later")
						.setPositiveButton("OK", null).show();
		
			}
			else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MainSheet.this).setTitle("Info")
						.setMessage("No Sub Division Found")
						.setPositiveButton("OK", null).show();
			}
		
			else if (responsefromserver != null) {
				JSONArray ja = null;
				try {
					ja = new JSONArray(responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				listdata5 = new ArrayList<String>();
				listdata5.add("Select");
				oandmunit = new ArrayList<GetSetCircle>();
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							GetSetCircle comp = new GetSetCircle();
							comp.setUnit(obj.getString("subStation"));
							oandmunit.add(comp);
							listdata5.add(obj.getString("subStation"));
							
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				ArrayAdapter<String> adapter7;
				 adapter7 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata5);
	    		 adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    		 substation.setAdapter(adapter7);
	    		 
	    		 substation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						 substation_post = substation.getSelectedItem().toString();
					   }
					 public void onNothingSelected(AdapterView<?> parent) {
					    }
					});
	    		 
		
			} else {
				//Toast.makeText(getApplicationContext(), "Mobile Login", Toast.LENGTH_SHORT).show();
				//loginFromMobile();
			}
		}
		
		
		}
}*/