package com.bcits.cecsmrfinder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.SendRequest;

public class MapSurveyPerformance extends Activity {

	TextView mrcodetv, txtdevutil, txtmrname, txtcontact, txtavgbills,
			txtTotal, txtdl, txtTariffmnr, txtTariffnormal, txtTariffothers;
	AutoCompleteTextView txtAccountNumber;
	String dateofview = "";
	AlertDialog show;
	ImageView imgPrevious, imgNext;
	ArrayAdapter<String> adapter;
	ArrayList<String> array;
	// JSONArray data;
	// JSONArray serverdata;
	JSONArray scripts = null;
	// String id,mrcode;
	String fvalue;
	ArrayList<String> accounts;
	int localId = 0;

	String s1 = null;
	String billmonth = null;

	public String id = null;
	public String mrcode = null;
	public String mrname = null;
	public String ctno = null;
	public String dutil = null;
	public String avgbills = null;
	public String tbills = null;
	public String dl = null;
	public String mnr = null;
	public String normal = null;
	public String other = null;

	public String responsefromservercopy = null;
	ArrayList<String> arrayLocation = new ArrayList<String>();

	public static int tot = 0;

	@SuppressLint("SimpleDateFormat")
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_mr_performance);
		getActionBar().getCustomView().findViewById(R.id.title);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater
				.inflate(R.layout.custom_title_mr_performance, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_view);

		txtAccountNumber = (AutoCompleteTextView) findViewById(R.id.txtAccountNumber);
		//txtAccountNumber.dismissDropDown();
		txtmrname = (TextView) findViewById(R.id.txtmrname);
		txtcontact = (TextView) findViewById(R.id.txtcontact);
		txtdevutil = (TextView) findViewById(R.id.txtdevutil);
		txtavgbills = (TextView) findViewById(R.id.txtavgbills);
		txtTotal = (TextView) findViewById(R.id.txtTotal);
		txtdl = (TextView) findViewById(R.id.txtdl);
		txtTariffmnr = (TextView) findViewById(R.id.txtTariffmnr);
		txtTariffnormal = (TextView) findViewById(R.id.txtTariffnormal);
		txtTariffothers = (TextView) findViewById(R.id.txtTariffothers);
		mrcodetv = (TextView) findViewById(R.id.txtmrcode);
		// txtAcc = (TextView) findViewById(R.id.txtAcc);
		// txtAcc = (TextView) findViewById(R.id.txtAcc);

		imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
		imgNext = (ImageView) findViewById(R.id.imgNext);

		imgPrevious.setClickable(false);
		imgPrevious.setBackgroundColor(getResources().getColor(R.color.Gray));

		// new performBackgroundTaskLoadInstalledIPandDTC().execute();

		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = format.format(today);

		String Str = new String(curDate);
		String[] arr = Str.split("-");
		billmonth = arr[0] + arr[1];
		// String s="";

		/*
		 * fvalue=fvalue+retval;
		 * 
		 * }
		 */
		/*
		 * Date now = new Date(); // java.util.Date, NOT java.sql.Date or
		 * java.sql.Timestamp! String format1 = new SimpleDateFormat("yyyymm",
		 * Locale.ENGLISH).format(now);
		 * +format1+now);
		 */
		getMobileDate();

		if (AmrMethods.doubleClick()) {
			Boolean isSDPresent2 = android.os.Environment
					.getExternalStorageState().equals(
							android.os.Environment.MEDIA_MOUNTED);
			if (isSDPresent2) {
				if (haveNetworkConnection()) {

					new performBackgroundTaskLoadInstalledIPandDTC().execute();

				}
				// getAccountDetails();
			} else {
			}
		} else {
			show = new AlertDialog.Builder(MapSurveyPerformance.this)
					.setMessage(
							getResources().getString(R.string.NoSDCARDFOUND))
					.setPositiveButton(getResources().getString(R.string.OK),
							null).show();
		}
		
	}

	@SuppressLint("SimpleDateFormat")
	public Date getMobileDate() {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();

		try {
			date = df.parse(df.format(calendar.getTime()));
		} catch (java.text.ParseException e) {

			date = null;
		}

		return date;
	}

	@Override
	protected void onResume() {
		super.onResume();

		txtAccountNumber.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String mr = txtAccountNumber.getText().toString();
				String[] mrsplit = mr.split(" ");
				
				getAccountDetailsMr(mrsplit[0]);
				//hideSoftKeyboard();
				txtAccountNumber.showDropDown();
			}
		});

		

		imgNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * txtAccountNumber.setCursorVisible(false);
				 * imgSearch.setVisibility(View.GONE);
				 * txtAccountNumber.setAdapter(null);
				 */
				localId++;
				try {
					getAccountDetails();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		imgPrevious.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * txtAccountNumber.setCursorVisible(false);
				 * imgSearch.setVisibility(View.GONE);
				 * txtAccountNumber.setAdapter(null);
				 */
				localId--;
				try {
					getAccountDetails();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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
	
	@SuppressWarnings("unused")
	private void getAccountDetailsMr(String input) {
		if (accounts != null) {
			JSONArray jacd = null;
			// for(int i=0;i<accounts.size();i++){
			calllistID(localId);
			// String s1=accounts.get(0).toString();
			try {
				if (s1 != null) {
					jacd = new JSONArray(responsefromservercopy);
					for(int i =0;i<jacd.length();i++){
						JSONArray arr = jacd.getJSONArray(i);
						if(input.equals(arr.getString(1))){
							id = arr.get(0).toString();
							mrcode = arr.get(1).toString();
							mrname = arr.get(11).toString();
							ctno = arr.get(12).toString();
							dutil = arr.get(10).toString();
							avgbills = arr.get(4).toString();
							tbills = arr.get(2).toString();
							dl = arr.get(6).toString();
							mnr = arr.get(7).toString();
							normal = arr.get(5).toString();
							other = arr.get(8).toString();
							mrcodetv.setText(mrcode);
							txtAccountNumber.setText(mrcode);
							txtmrname.setText(mrname);
							txtcontact.setText(ctno);
							txtdevutil.setText(dutil + " %");
							txtavgbills.setText(avgbills);
							txtTotal.setText(tbills);
							txtdl.setText(dl);
							txtTariffmnr.setText(mnr);
							txtTariffnormal.setText(normal);
							txtTariffothers.setText(other);
						}else{
						}
						
					}

					
				}

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// }

		}

		int total = tot;

		if (localId <= 0) {
			localId = 0;
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources()
					.getColor(R.color.Gray));
		} else {
			imgPrevious.setClickable(true);
			imgPrevious.setBackgroundResource(R.drawable.button_main);
		}

		if (localId >= total) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.Gray));
		} else {
			imgNext.setClickable(true);
			imgNext.setBackgroundResource(R.drawable.button_main);

		}

		if (total == 0) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.Gray));
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources()
					.getColor(R.color.Gray));
		}

		/*
		 * DbDisconnection db = new DbDisconnection(getBaseContext());
		 * db.open(); disc = db.getDisconnOneByOne(localId + ""); db.close();
		 * txtAccountNumber.setText(disc.getRrno());
		 * txtAcc.setText(disc.getRrno()); txtTariff.setText(disc.getTariff());
		 * txtArrears.setText(disc.getArrears());
		 * txtSdoCode.setText(disc.getSdocode());
		 * txtListNo.setText(disc.getListno()); if
		 * (disc.getAddress().equals("null") ||
		 * disc.getAddress().equals("null")) {
		 * txtAddress.setText("Address not available");
		 * txtName.setText("Name not available"); } else {
		 * txtAddress.setText(disc.getAddress());
		 * txtName.setText(disc.getConsumer_name()); }
		 */

	}

	@SuppressWarnings("unused")
	private void getAccountDetails() {
		if (accounts != null) {
			JSONArray jacd = null;
			// for(int i=0;i<accounts.size();i++){
			calllistID(localId);
			// String s1=accounts.get(0).toString();
			try {
				if (s1 != null) {
					jacd = new JSONArray(s1);

					id = jacd.get(0).toString();
					mrcode = jacd.get(1).toString();
					mrname = jacd.get(11).toString();
					ctno = jacd.get(12).toString();
					dutil = jacd.get(10).toString();
					avgbills = jacd.get(4).toString();
					tbills = jacd.get(2).toString();
					dl = jacd.get(6).toString();
					mnr = jacd.get(7).toString();
					normal = jacd.get(5).toString();
					other = jacd.get(8).toString();
					mrcodetv.setText(mrcode);
					txtAccountNumber.setText(mrcode);
					txtmrname.setText(mrname);
					txtcontact.setText(ctno);
					txtdevutil.setText(dutil + " %");
					txtavgbills.setText(avgbills);
					txtTotal.setText(tbills);
					txtdl.setText(dl);
					txtTariffmnr.setText(mnr);
					txtTariffnormal.setText(normal);
					txtTariffothers.setText(other);
				}

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			txtAccountNumber.dismissDropDown();

			// }

		}

		int total = tot;

		if (localId <= 0) {
			localId = 0;
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources()
					.getColor(R.color.Gray));
		} else {
			imgPrevious.setClickable(true);
			imgPrevious.setBackgroundResource(R.drawable.button_main);
		}

		if (localId >= total) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.Gray));
		} else {
			imgNext.setClickable(true);
			imgNext.setBackgroundResource(R.drawable.button_main);

		}

		if (total == 0) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.Gray));
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources()
					.getColor(R.color.Gray));
		}

		/*
		 * DbDisconnection db = new DbDisconnection(getBaseContext());
		 * db.open(); disc = db.getDisconnOneByOne(localId + ""); db.close();
		 * txtAccountNumber.setText(disc.getRrno());
		 * txtAcc.setText(disc.getRrno()); txtTariff.setText(disc.getTariff());
		 * txtArrears.setText(disc.getArrears());
		 * txtSdoCode.setText(disc.getSdocode());
		 * txtListNo.setText(disc.getListno()); if
		 * (disc.getAddress().equals("null") ||
		 * disc.getAddress().equals("null")) {
		 * txtAddress.setText("Address not available");
		 * txtName.setText("Name not available"); } else {
		 * txtAddress.setText(disc.getAddress());
		 * txtName.setText(disc.getConsumer_name()); }
		 */

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
			hideSoftKeyboard();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void calllistID(int localId2) {

		if (localId2 <= accounts.size() - 1) {
			s1 = accounts.get(localId2).toString();
		}

	}

	public class performBackgroundTaskLoadInstalledIPandDTC extends
			AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MapSurveyPerformance.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Getting MR Wise Performance Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					if(Login.sdoCode.equals("1410")){
						jsonarray.put("siteCodeval","2120");
					}else{
					jsonarray.put("siteCodeval",Login.sdoCode);
					}
					//jsonarray.put("siteCodeval", Login.sdoCode);
					jsonarray.put("fromDate", Dashboard.month_arreas);

					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "getMobileMRPerformance";
					// String url1 = "getConsumersArrearMobile";
					responsefromserver = req.uploadToServer(url1, ja1);

					// Toast.makeText(getApplicationContext(),responsefromserver,
					// Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@SuppressLint("NewApi")
		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				new AlertDialog.Builder(MapSurveyPerformance.this)
						.setTitle("Error..!")
						.setMessage(
								"Sorry, server down / some error occurred while connecting server.")
						.setPositiveButton("OK", null).show();

			} else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MapSurveyPerformance.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Something went wrog try again Later")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			} else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MapSurveyPerformance.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Please select Location Type as Section ")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			}

			else if (responsefromserver != null) {

				responsefromservercopy = responsefromserver;
				JSONArray ja = null;
				JSONArray jac = null;

				accounts = new ArrayList<String>();
				// Toast.makeText(getApplicationContext(),responsefromserver,
				// Toast.LENGTH_LONG).show();
				try {
					ja = new JSONArray(responsefromserver);
					tot = ja.length();
					// getAccountDetails();

					for (int i = 0; i < ja.length(); i++) {
						accounts.add(ja.get(i).toString());

						String s1 = ja.get(i).toString();
						jac = new JSONArray(s1);

					}
					setMrNameAndCode(responsefromserver);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				// getAccountDetails();
				// arrayCircle = new ArrayList<GetSetCircle>();

				// JSONArray jaa;

				// ArrayAdapter<String> adapter = new
				// ArrayAdapter<String>(MapSurveyPerformance.this,android.R.layout.simple_dropdown_item_1line,
				// listdata);
				// rrnumberauto.setThreshold(1);
				// rrnumberauto.setAdapter(adapter);
				getAccountDetails();
			}
		}

	}

	private void setMrNameAndCode(String mrArray) {
		try {
			JSONArray array = new JSONArray(mrArray);
			arrayLocation = new ArrayList<String>();
			for (int i = 0; i < array.length(); i++) {
				JSONArray obj = array.getJSONArray(i);
				arrayLocation.add(obj.getString(1).trim()+" "+obj.getString(11).trim());
			}
			/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					MapSurveyPerformance.this, R.layout.custom_dropdown,
					R.id.txtCustomDropdown, arrayLocation);
			txtAccountNumber.setThreshold(0);
			txtAccountNumber.setAdapter(adapter);
			txtAccountNumber.showDropDown();*/
			/*ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, arrayLocation);*/
			ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
					MapSurveyPerformance.this, R.layout.custom_dropdown,
					R.id.txtCustomDropdown, arrayLocation);
			txtAccountNumber.setAdapter(myAdapter );
			txtAccountNumber.setThreshold(0);
			//txtAccountNumber.showDropDown();
			txtAccountNumber.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(final View arg0) {
		        	txtAccountNumber.showDropDown();
		        }
		    });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(), 0);
		}
	}

}
