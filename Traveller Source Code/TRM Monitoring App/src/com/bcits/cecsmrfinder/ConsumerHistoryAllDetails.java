package com.bcits.cecsmrfinder;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bcits.utils.SendRequest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ConsumerHistoryAllDetails extends Activity {

	ListView billing_details;
	LinearLayout listview, masterdetails, billg, payg, depog, meterg, meterchg;
	TextView consumertitle, listtitle, name, place, socode, feedercode, poleno,
			telephone, noofmeters, sanctionedkw, dos, kwhpdmd;
	LinearLayout llbilling, llpayment, lldeposit, llmeter, llmeterchange;
	public static String responsefromserver; // consumer details
	public static String responsefromserverbillingdetails; // consumer details
	public static String responsefromserverpaymentdetails; // consumer details
	public static String responsefromserverdepositdetails; // consumer details
	public static String responsefromservermeterdetails; // consumer details
	public static String responsefromservermeterchangedetails; // consumer
																// details
	public static int billingposition;
	public static int paymentposition;
	public static int depositposition;
	public static int meterposition;
	public static int meterchangeposition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_consumerhistory);
		getActionBar().getCustomView().findViewById(R.id.title);

		// hideSoftKeyboard();

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_consumerhistory,
				null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		setContentView(R.layout.consumer_history_alldetails);

		name = (TextView) findViewById(R.id.infomrcode);
		place = (TextView) findViewById(R.id.inforrno);
		socode = (TextView) findViewById(R.id.infoname);
		feedercode = (TextView) findViewById(R.id.infoaddress);
		poleno = (TextView) findViewById(R.id.infoplace);
		telephone = (TextView) findViewById(R.id.infomobile);
		noofmeters = (TextView) findViewById(R.id.infoemail);
		sanctionedkw = (TextView) findViewById(R.id.infotariff);
		dos = (TextView) findViewById(R.id.infodos);
		kwhpdmd = (TextView) findViewById(R.id.infokwhpdmd);

		billg = (LinearLayout) findViewById(R.id.billg);
		payg = (LinearLayout) findViewById(R.id.payg);
		depog = (LinearLayout) findViewById(R.id.depog);
		meterg = (LinearLayout) findViewById(R.id.meterg);
		meterchg = (LinearLayout) findViewById(R.id.meterchg);

		listview = (LinearLayout) findViewById(R.id.listview);
		listview.setVisibility(View.INVISIBLE);
		billing_details = (ListView) findViewById(R.id.billingdetailslv);
		listtitle = (TextView) findViewById(R.id.listtitle);
		consumertitle = (TextView) findViewById(R.id.consumerdetailstitle);
		masterdetails = (LinearLayout) findViewById(R.id.masterdetails);

		llbilling = (LinearLayout) findViewById(R.id.llbilling);
		llpayment = (LinearLayout) findViewById(R.id.llpayment);
		lldeposit = (LinearLayout) findViewById(R.id.lldeposit);
		llmeter = (LinearLayout) findViewById(R.id.llmeter);
		llmeterchange = (LinearLayout) findViewById(R.id.llmeterchange);

		place.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),
						DetailedViewActivity.class);
				startActivity(in);
			}
		});
		new GetConsumerDetails().execute();

		billg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listtitle.setText("Billing Details Of RR No - "
						+ ConsumerHistorySearch.rrno);

				try {
					new GetBillingDetails().execute();

					listview.setVisibility(View.VISIBLE);
					llbilling.setVisibility(View.VISIBLE);
					llpayment.setVisibility(View.GONE);
					lldeposit.setVisibility(View.GONE);
					llmeter.setVisibility(View.GONE);
					llmeterchange.setVisibility(View.GONE);
					// ListView Item Click Listener
					billing_details
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									billingposition = position;
									Intent in = new Intent(
											getApplicationContext(),
											DetailedViewActivityBilling.class);
									startActivity(in);

								}

							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		payg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listtitle.setText("Payment Details Of RR No - "
						+ ConsumerHistorySearch.rrno);
				try {
					new GetPaymentDetails().execute();

					listview.setVisibility(View.VISIBLE);
					llbilling.setVisibility(View.GONE);
					llpayment.setVisibility(View.VISIBLE);
					lldeposit.setVisibility(View.GONE);
					llmeter.setVisibility(View.GONE);
					llmeterchange.setVisibility(View.GONE);
					// ListView Item Click Listener
					billing_details
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									paymentposition = position;
									Intent in = new Intent(
											getApplicationContext(),
											DetailedViewActivityPayment.class);
									startActivity(in);

								}

							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		depog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listtitle.setText("Deposit Details Of RR No - "
						+ ConsumerHistorySearch.rrno);
				try {
					new GetDepositDetails().execute();

					listview.setVisibility(View.VISIBLE);
					llbilling.setVisibility(View.GONE);
					llpayment.setVisibility(View.GONE);
					lldeposit.setVisibility(View.VISIBLE);
					llmeter.setVisibility(View.GONE);
					llmeterchange.setVisibility(View.GONE);
					// ListView Item Click Listener
					billing_details
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									depositposition = position;
									Intent in = new Intent(
											getApplicationContext(),
											DetailedViewActivityDeposit.class);
									startActivity(in);

								}

							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		meterg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listtitle.setText("Meter Details Of RR No - "
						+ ConsumerHistorySearch.rrno);
				try {
					new GetMeterDetails().execute();

					listview.setVisibility(View.VISIBLE);
					llbilling.setVisibility(View.GONE);
					llpayment.setVisibility(View.GONE);
					lldeposit.setVisibility(View.GONE);
					llmeter.setVisibility(View.VISIBLE);
					llmeterchange.setVisibility(View.GONE);
					// ListView Item Click Listener
					billing_details
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									meterposition = position;
									Intent in = new Intent(
											getApplicationContext(),
											DetailedViewActivityMeter.class);
									startActivity(in);

								}

							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		meterchg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listtitle.setText("Meter Change Details Of RR No - "
						+ ConsumerHistorySearch.rrno);
				try {
					new GetMeterChangeDetails().execute();

					listview.setVisibility(View.VISIBLE);
					llbilling.setVisibility(View.GONE);
					llpayment.setVisibility(View.GONE);
					lldeposit.setVisibility(View.GONE);
					llmeter.setVisibility(View.GONE);
					llmeterchange.setVisibility(View.VISIBLE);
					// ListView Item Click Listener
					billing_details
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									meterchangeposition = position;
									Intent in = new Intent(
											getApplicationContext(),
											DetailedViewActivityMeterChange.class);
									startActivity(in);

								}

							});
				} catch (Exception e) {
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

			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class GetConsumerDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Consumer Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer(
						"consumerHistoryGetConsumerDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {

				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			}

			else if (responsefromserver.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			}

			else {

				try {
					json = new JSONArray(responsefromserver);

					String date = json.getJSONObject(0).getString(
							"DATEOFSERVICE");
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					long milliSeconds = Long.parseLong(date);
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(milliSeconds);
					if (date.equals(null) || date.equals("null")) {

						String s = "Consumer Details Of RR No - "
								+ ConsumerHistorySearch.rrno;
						SpannableString ss1 = new SpannableString(s);
						ss1.setSpan(new ForegroundColorSpan(Color.RED), 28,
								s.length(), 28);// set color

						String udata = json.getJSONObject(0).getString("RRNO");
						SpannableString content = new SpannableString(udata);
						content.setSpan(new UnderlineSpan(), 0, udata.length(),
								0);

						consumertitle.setText(ss1);
						name.setText(json.getJSONObject(0).getString("MRCODE"));
						place.setText(content);
						socode.setText(json.getJSONObject(0).getString(
								"CONSUMERNAME"));
						feedercode.setText(json.getJSONObject(0).getString(
								"CONSUMERADDRESS")
								+ " "
								+ json.getJSONObject(0).getString(
										"CONSUMERADDRESS1"));
						poleno.setText(json.getJSONObject(0).getString("PLACE"));
						telephone.setText(json.getJSONObject(0).getString(
								"TELEPHONENO"));
						noofmeters.setText("-");
						sanctionedkw.setText(json.getJSONObject(0).getString(
								"TARIFFDCB"));
						dos.setText("-");
						kwhpdmd.setText(json.getJSONObject(0).getString(
								"SANCTIONKW")
								+ "/"
								+ json.getJSONObject(0).getString("SANCTIONHP")
								+ "/"
								+ json.getJSONObject(0)
										.getString("CONTRACTDMD"));
					} else {

						String s = "Consumer Details Of RR No - "
								+ ConsumerHistorySearch.rrno;
						SpannableString ss1 = new SpannableString(s);
						ss1.setSpan(new ForegroundColorSpan(Color.RED), 28,
								s.length(), 28);// set color

						String udata = json.getJSONObject(0).getString("RRNO");
						SpannableString content = new SpannableString(udata);
						content.setSpan(new UnderlineSpan(), 0, udata.length(),
								0);

						consumertitle.setText(ss1);
						name.setText(json.getJSONObject(0).getString("MRCODE"));
						place.setText(content);
						socode.setText(json.getJSONObject(0).getString(
								"CONSUMERNAME"));
						feedercode.setText(json.getJSONObject(0).getString(
								"CONSUMERADDRESS")
								+ " "
								+ json.getJSONObject(0).getString(
										"CONSUMERADDRESS1"));
						poleno.setText(json.getJSONObject(0).getString("PLACE"));
						telephone.setText(json.getJSONObject(0).getString(
								"TELEPHONENO"));
						noofmeters.setText("-");
						sanctionedkw.setText(json.getJSONObject(0).getString(
								"TARIFFDCB"));
						dos.setText(formatter.format(calendar.getTime()));
						kwhpdmd.setText(json.getJSONObject(0).getString(
								"SANCTIONKW")
								+ "/"
								+ json.getJSONObject(0).getString("SANCTIONHP")
								+ "/"
								+ json.getJSONObject(0)
										.getString("CONTRACTDMD"));
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class GetBillingDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Billing Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserverbillingdetails = req.uploadToServer(
						"consumerHistoryGetBillingDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);

				// ConsumerHistoryAllDetails.responsefromserverbillingdetails =
				// responsefromserver;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserverbillingdetails == null) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromserverbillingdetails = "";
					BillingDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (responsefromserverbillingdetails.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {

					responsefromserverbillingdetails = "";
					BillingDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				try {

					BillingDetailsLVAdapter adapter = new BillingDetailsLVAdapter(
							ConsumerHistoryAllDetails.this);
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class GetPaymentDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Payment Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserverpaymentdetails = req.uploadToServer(
						"consumerHistoryGetPaymentDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserverpaymentdetails == null) {

				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromserverpaymentdetails = "";
					PaymentDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromserverpaymentdetails.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromserverpaymentdetails = "";
					PaymentDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				try {
					PaymentDetailsLVAdapter adapter = new PaymentDetailsLVAdapter(
							ConsumerHistoryAllDetails.this);
					billing_details.setAdapter(adapter);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class GetDepositDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Deposit Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserverdepositdetails = req.uploadToServer(
						"consumerHistoryGetDepositDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserverdepositdetails == null) {

				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromserverdepositdetails = "";
					DepositDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromserverdepositdetails.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromserverdepositdetails = "";
					DepositDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				try {
					DepositDetailsLVAdapter adapter = new DepositDetailsLVAdapter(
							ConsumerHistoryAllDetails.this);
					billing_details.setAdapter(adapter);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class GetMeterDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Meter Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromservermeterdetails = req.uploadToServer(
						"consumerHistoryGetMeterDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromservermeterdetails == null) {

				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromservermeterdetails = "";
					MeterDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromservermeterdetails.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromservermeterdetails = "";
					MeterDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				try {
					MeterDetailsLVAdapter adapter = new MeterDetailsLVAdapter(
							ConsumerHistoryAllDetails.this);
					billing_details.setAdapter(adapter);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class GetMeterChangeDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistoryAllDetails.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Meter Change Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromservermeterchangedetails = req.uploadToServer(
						"consumerHistoryGetMeterChangeDetails/"
								+ ConsumerHistorySearch.rrno + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromservermeterchangedetails == null) {

				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromservermeterchangedetails = "";
					MeterChangeDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromservermeterchangedetails.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistoryAllDetails.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				try {
					responsefromservermeterchangedetails = "";
					MeterChangeDetailsLVAdapter adapter = null;
					billing_details.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				try {
					MeterChangeDetailsLVAdapter adapter = new MeterChangeDetailsLVAdapter(
							ConsumerHistoryAllDetails.this);
					billing_details.setAdapter(adapter);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}
}
