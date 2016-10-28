package com.bcits.cecsmrfinder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcits.cecsmrfinder.Login.LoginCheck;
import com.bcits.utils.SendRequest;
import com.bcits.utils.TransactionDOAdapter;
import com.sqlite.cescip.DBMrWiseBilling;

public class DetailedMRCardActivity extends Activity {

	Button rrnumbers, Button01, Button02, Button04, Button03;
	DBMrWiseBilling databasehandler;
	JSONObject object;
	TextView mrname, mrcode, mrmobile, title2, textView01, todaysdate,
			billingpercentage, billingpercentageday;
	public static String headertitle = "";
	String responsetype = "";
	public static JSONArray json = null;
	public static JSONArray rrjson = null;
	public static String mrcod = "";
	ImageView taketomessages;
	LinearLayout contact;
	int asynccount = 0;
	String buttonvar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress,
				null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		setContentView(R.layout.detailedmrcard);
		taketomessages = (ImageView) findViewById(R.id.taketomessages);
		contact = (LinearLayout) findViewById(R.id.contact);
		billingpercentage = (TextView) findViewById(R.id.billingpercentage);
		billingpercentageday = (TextView) findViewById(R.id.billingpercentageday);
		rrnumbers = (Button) findViewById(R.id.rrnumbers);
		Button01 = (Button) findViewById(R.id.Button01);
		Button02 = (Button) findViewById(R.id.Button02);
		Button04 = (Button) findViewById(R.id.Button04);
		Button03 = (Button) findViewById(R.id.Button03);

		mrname = (TextView) findViewById(R.id.mrname);
		mrcode = (TextView) findViewById(R.id.mrcode);
		mrmobile = (TextView) findViewById(R.id.mrmobile);
		title2 = (TextView) findViewById(R.id.title2);
		textView01 = (TextView) findViewById(R.id.textView01);
		todaysdate = (TextView) findViewById(R.id.todaysdate);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		mrcod = getIntent().getStringExtra("mrcode").trim();
		databasehandler = new DBMrWiseBilling(getApplicationContext());
		databasehandler.open();
		object = databasehandler.getMrCodeWiseDetails(getIntent()
				.getStringExtra("mrcode").trim());
		databasehandler.close();

		try {

			float billing_percentage = (Float.valueOf(object
					.getString("billed")) / Float.valueOf(object
					.getString("tobebilled"))) * 100;
			double newKB = Math.round(billing_percentage * 100.0) / 100.0;
			
			if(Float.valueOf(object.getString("tobebilledday"))==0.0){
				textView01.setText("TARGET FOR THE DAY - NA");
				Button04.setText("Billed\n" + object.getString("billedday"));
				Button03.setText("Unbilled\nNA");
				billingpercentageday.setText("Billing Percentage - NA");

			}else if(Float.valueOf(object.getString("tobebilledday"))>0.0){
				textView01.setText("TARGET FOR THE DAY - "
						+ object.getString("tobebilledday"));
				Button04.setText("Billed\n" + object.getString("billedday"));
				Button03.setText("Unbilled\n" + object.getString("unbilledday"));
				
				float billing_percentage_day = (Float.valueOf(object
						.getString("billedday")) / Float.valueOf(object
						.getString("tobebilledday"))) * 100;
				double newKB2 = Math.round(billing_percentage_day * 100.0) / 100.0;
				
				billingpercentageday.setText("Billing Percentage - " + newKB2
						+ " %");
				
				if (Double.isNaN(billing_percentage_day)) {
					billing_percentage_day = 0;
				}
			}

			

			/*
			 * DecimalFormat df=new DecimalFormat("0.00"); String formate_month
			 * = df.format(billing_percentage); String formate_day =
			 * df.format(billing_percentage_day);
			 * 
			 * double finalValuemonth = (Double)df.parse(formate_month) ; double
			 * finalValueday = (Double)df.parse(formate_day) ;
			 */
			billingpercentage.setText("Billing Percentage - " + newKB + " %");
			
			if (Double.isNaN(billing_percentage)) {
				billing_percentage = 0;
			}
			

			mrname.setText("MR NAME - " + object.getString("mr_name"));
			mrcode.setText("MR CODE - " + object.getString("mr_code"));

			if (object.getString("mr_mobile").equals("0")
					|| object.getString("mr_mobile").equals("-")) {
				contact.setVisibility(View.GONE);
			} else {
				contact.setVisibility(View.VISIBLE);
				mrmobile.setText(/* "MOBILE - "+ */object.getString("mr_mobile"));
			}

			title2.setText("TARGET FOR THE MONTH - "
					+ object.getString("tobebilled"));
			Button01.setText("Billed\n" + object.getString("billed"));
			Button02.setText("Unbilled\n" + object.getString("unbilled"));
			
			//Button03.setText("Unbilled\n" + (Integer.parseInt(object.getString("tobebilledday"))-(Integer.parseInt(object.getString("billedday")))));
			todaysdate.setText("Details As On - " + formattedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Button01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					if (Integer.parseInt(object.getString("billed")) > 0) {

						if (asynccount == 0) {
							++asynccount;
							buttonvar = "1";
							new RRnumberlistasyncforallresponsetype().execute();

						}else{
							try {
								headertitle = "BILLED DETAILS FOR MONTH = "
										+ object.getString("billed");
								DBMrWiseBilling db = new DBMrWiseBilling(
										getApplicationContext());
								db.open();
								rrjson = db
										.getmrcodeandsdocodewiserrnumberlistdatabilledmonth(object
												.getString("billed"));
								db.close();
								if (rrjson != null) {
									Intent intent = new Intent(getApplicationContext(),
											RrnumberListCustomDesignActivity.class);
									startActivity(intent);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						
					}
				} catch (NumberFormatException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (Integer.parseInt(object.getString("unbilled")) > 0) {
						if (asynccount == 0) {
							++asynccount;
							buttonvar = "2";
							new RRnumberlistasyncforallresponsetype().execute();
						}else{
							try {
								headertitle = "UNBILLED DETAILS FOR MONTH = "
										+ object.getString("unbilled");
								DBMrWiseBilling db = new DBMrWiseBilling(
										getApplicationContext());
								db.open();
								rrjson = db
										.getmrcodeandsdocodewiserrnumberlistdataunbilledmonth(object
												.getString("unbilled"));
								db.close();
								if (rrjson != null) {
									Intent intent = new Intent(getApplicationContext(),
											RrnumberListCustomDesignActivity.class);
									startActivity(intent);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				} catch (NumberFormatException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button04.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (Integer.parseInt(object.getString("billedday")) > 0) {
						if (asynccount == 0) {
							++asynccount;
							buttonvar = "3";
							new RRnumberlistasyncforallresponsetype().execute();
						}else{
							try {
								headertitle = "Billed for the day = "
										+ object.getString("billedday");
								DBMrWiseBilling db = new DBMrWiseBilling(
										getApplicationContext());
								db.open();
								rrjson = db
										.getmrcodeandsdocodewiserrnumberlistdatabilledday(object
												.getString("billedday"));
								db.close();
								if (rrjson != null) {
									Intent intent = new Intent(getApplicationContext(),
											RrnumberListCustomDesignActivity.class);
									startActivity(intent);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				} catch (NumberFormatException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (Integer.parseInt(object.getString("unbilledday")) > 0) {
						if (asynccount == 0) {
							++asynccount;
							buttonvar = "4";
							new RRnumberlistasyncforallresponsetype().execute();
						}else{
							try {
								headertitle = "Unbilled for the day = "
										+ object.getString("unbilledday");
								DBMrWiseBilling db = new DBMrWiseBilling(
										getApplicationContext());
								db.open();
								rrjson = db
										.getmrcodeandsdocodewiserrnumberlistdataunbilledday(object
												.getString("unbilledday"));
								db.close();
								if (rrjson != null) {
									Intent intent = new Intent(getApplicationContext(),
											RrnumberListCustomDesignActivity.class);
									startActivity(intent);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				} catch (NumberFormatException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		rrnumbers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),
						RrnumberListActivity.class);
				startActivity(in);
			}
		});
		// new RRnumberlistasyncforallresponsetype().execute();
		taketomessages.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * SmsManager smsManager = SmsManager.getDefault(); try {
				 * smsManager.sendTextMessage(object.getString("mr_mobile"),
				 * null,"Your message goes here", null, null); } catch
				 * (JSONException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 */

				try {
					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.setType("vnd.android-dir/mms-sms");
					smsIntent.putExtra("address", object.getString("mr_mobile"));
					startActivity(smsIntent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.back_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btn_back:
			onBackPressed();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class RRnumberlistasyncforallresponsetype extends
			AsyncTask<Void, Void, Void> {

		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(DetailedMRCardActivity.this,
					R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching RR numbers...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String todaysdate = sdf.format(date);

				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				// Login.sdoCode = "5111";

				jsonarray.put("sdocode", Login.sdoCode);
				jsonarray.put("schema", Login.dbSchema);
				jsonarray.put("rdngmonth", date.getMonth() + 1);// starting from
																// 0
				jsonarray.put("mrcode", object.getString("mr_code"));
				jsonarray.put("rdngdate", todaysdate);
				// jsonarray.put("responsetype",responsetype);

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer("getBilledRrnos", ja1);

				if (responsefromserver != null
						&& !responsefromserver.contains("NoData")) {
					try {
						json = new JSONArray(responsefromserver);

						if (json != null) {

							DBMrWiseBilling db = new DBMrWiseBilling(
									getApplicationContext());
							db.open();
							if (db.checkDataBase()) {
								db.deleteAllRR();
							}
							db.startBooster();
							db.insertintorrnumberlist(json);
							db.stopBooster();
							db.close();

							/*
							 * Intent intent = new
							 * Intent(getApplicationContext(),
							 * RrnumberListCustomDesignActivity.class);
							 * startActivity(intent);
							 */

						} else {

							new AlertDialog.Builder(DetailedMRCardActivity.this)
									.setTitle(
											getResources().getString(
													R.string.Info))
									.setMessage(
											"Unable To Connect Please Try Again Later")
									.setPositiveButton(
											getResources().getString(
													R.string.OK), null).show();

						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {

			mProgressDialog.dismiss();
			if (responsefromserver == null) {

				new AlertDialog.Builder(DetailedMRCardActivity.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get RR numbers")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			}

			else if (responsefromserver.contains("NoData")) {
				new AlertDialog.Builder(DetailedMRCardActivity.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("No Data Found For This MR Code")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			} else {
				if (asynccount > 0 && buttonvar.equals("1")) {
					try {
						headertitle = "BILLED DETAILS FOR MONTH = "
								+ object.getString("billed");
						DBMrWiseBilling db = new DBMrWiseBilling(
								getApplicationContext());
						db.open();
						rrjson = db
								.getmrcodeandsdocodewiserrnumberlistdatabilledmonth(object
										.getString("billed"));
						db.close();
						if (rrjson != null) {
							Intent intent = new Intent(getApplicationContext(),
									RrnumberListCustomDesignActivity.class);
							startActivity(intent);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if (asynccount > 0 && buttonvar.equals("2")) {
					try {
						headertitle = "UNBILLED DETAILS FOR MONTH = "
								+ object.getString("unbilled");
						DBMrWiseBilling db = new DBMrWiseBilling(
								getApplicationContext());
						db.open();
						rrjson = db
								.getmrcodeandsdocodewiserrnumberlistdataunbilledmonth(object
										.getString("unbilled"));
						db.close();
						if (rrjson != null) {
							Intent intent = new Intent(getApplicationContext(),
									RrnumberListCustomDesignActivity.class);
							startActivity(intent);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if (asynccount > 0 && buttonvar.equals("3")) {
					try {
						headertitle = "Billed for the day = "
								+ object.getString("billedday");
						DBMrWiseBilling db = new DBMrWiseBilling(
								getApplicationContext());
						db.open();
						rrjson = db
								.getmrcodeandsdocodewiserrnumberlistdatabilledday(object
										.getString("billedday"));
						db.close();
						if (rrjson != null) {
							Intent intent = new Intent(getApplicationContext(),
									RrnumberListCustomDesignActivity.class);
							startActivity(intent);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if (asynccount > 0 && buttonvar.equals("4")) {
					try {
						headertitle = "Unbilled for the day = "
								+ object.getString("unbilledday");
						DBMrWiseBilling db = new DBMrWiseBilling(
								getApplicationContext());
						db.open();
						rrjson = db
								.getmrcodeandsdocodewiserrnumberlistdataunbilledday(object
										.getString("unbilledday"));
						db.close();
						if (rrjson != null) {
							Intent intent = new Intent(getApplicationContext(),
									RrnumberListCustomDesignActivity.class);
							startActivity(intent);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}

}
