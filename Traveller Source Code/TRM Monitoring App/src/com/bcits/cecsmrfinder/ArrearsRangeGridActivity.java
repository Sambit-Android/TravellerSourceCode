package com.bcits.cecsmrfinder;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bcits.utils.SendRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.cescip.DBLocation;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ArrearsRangeGridActivity extends Activity {

	LinearLayout btn500, btn1001, btn5001, btn10001, btn25001, btn50001,
			btn100000, btnhidden;
	ImageView i1, ii1, i2, ii2, i3, ii3, i4, ii4, i5, ii5, i6, ii6, i7, ii7;
	public static String arrearsRange = "";
	public static String consumer_count;
	TextView c1, c2, c3, c4, c5, c6, c7;
	ArrayList<String> count = new ArrayList<String>();
	ArrayList<String> responses = new ArrayList<String>();
	public static String response = null;
	public static JSONArray jsonresponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_arrears);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_arrears, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_arrearrange_page);

		new performBackgroundTaskLoadInstalledIPandDTC().execute();

		c1 = (TextView) findViewById(R.id.c1);
		c2 = (TextView) findViewById(R.id.c2);
		c3 = (TextView) findViewById(R.id.c3);
		c4 = (TextView) findViewById(R.id.c4);
		c5 = (TextView) findViewById(R.id.c5);
		c6 = (TextView) findViewById(R.id.c6);
		c7 = (TextView) findViewById(R.id.c7);

		i1 = (ImageView) findViewById(R.id.map1);
		i2 = (ImageView) findViewById(R.id.map2);
		i3 = (ImageView) findViewById(R.id.map3);
		i4 = (ImageView) findViewById(R.id.map4);
		i5 = (ImageView) findViewById(R.id.map5);
		i6 = (ImageView) findViewById(R.id.map6);
		i7 = (ImageView) findViewById(R.id.map7);

		ii1 = (ImageView) findViewById(R.id.list1);
		ii2 = (ImageView) findViewById(R.id.list2);
		ii3 = (ImageView) findViewById(R.id.list3);
		ii4 = (ImageView) findViewById(R.id.list4);
		ii5 = (ImageView) findViewById(R.id.list5);
		ii6 = (ImageView) findViewById(R.id.list6);
		ii7 = (ImageView) findViewById(R.id.list7);

		btn500 = (LinearLayout) findViewById(R.id.btn500);
		btn1001 = (LinearLayout) findViewById(R.id.btn1001);
		btn5001 = (LinearLayout) findViewById(R.id.btn5001);
		btn10001 = (LinearLayout) findViewById(R.id.btn10001);
		btn25001 = (LinearLayout) findViewById(R.id.btn25001);
		btn50001 = (LinearLayout) findViewById(R.id.btn50001);
		btn100000 = (LinearLayout) findViewById(R.id.btn100000);
		btnhidden = (LinearLayout) findViewById(R.id.btnhidden);

		i1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "500 to 1000";
				consumer_count = count.get(0);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "1001 to 5000";
				consumer_count = count.get(1);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "5001 to 10000";
				consumer_count = count.get(2);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "10001 to 25000";
				consumer_count = count.get(3);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "25001 to 50000";
				consumer_count = count.get(4);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "50001 to 100000";
				consumer_count = count.get(5);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});
		i7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arrearsRange = "Above 100000";
				consumer_count = count.get(6);
				if (Integer.parseInt(consumer_count) == 0) {

				} else {
				Intent intent = new Intent(ArrearsRangeGridActivity.this,
						MapSurveyArreas.class);
				startActivity(intent);
				}
			}
		});

		ii1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(0));
					consumer_count = count.get(0);
					arrearsRange = "500 to 1000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(1));
					consumer_count = count.get(1);
					arrearsRange = "1001 to 5000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(2));
					consumer_count = count.get(2);
					arrearsRange = "5001 to 10000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(3));
					consumer_count = count.get(3);
					arrearsRange = "10001 to 25000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(4));
					consumer_count = count.get(4);
					arrearsRange = "25001 to 50000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(5));
					consumer_count = count.get(5);
					arrearsRange = "50001 to 100000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		ii7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					jsonresponse = new JSONArray(responses.get(6));
					consumer_count = count.get(6);
					arrearsRange = "Above 100000";
					if (Integer.parseInt(consumer_count) == 0) {

					} else {
						Intent intent = new Intent(
								ArrearsRangeGridActivity.this,
								ArrearsRRnoListActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		btnhidden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// do nothing here
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

	private class performBackgroundTaskLoadInstalledIPandDTC extends
			AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ArrearsRangeGridActivity.this,R.style.MyTheme2);
			mProgressDialog.setMessage(getResources().getString(
					R.string.LoadingDetails));
			mProgressDialog.setCancelable(false);
			
			Drawable draw=getResources().getDrawable(R.drawable.custom_progressbar);
			// set the drawable as progress drawable
			mProgressDialog.setProgressDrawable(draw);
			System.out.println("Month Arrears BILL MONTH "+Dashboard.month_arreas);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {

					for (int i = 0; i < 7; i++) {
						String arrearsrange = "";
						if (i == 0) {
							arrearsrange = "500 and 1000";
						}
						if (i == 1) {
							arrearsrange = "1000 and 5000";
						}
						if (i == 2) {
							arrearsrange = "5000 and 10000";
						}
						if (i == 3) {
							arrearsrange = "10000 and 25000";
						}
						if (i == 4) {
							arrearsrange = "25000 and 50000";
						}
						if (i == 5) {
							arrearsrange = "50000 and 100000";
						}
						if (i == 6) {
							arrearsrange = "100000";
						}
						if(Login.sdoCode.equals("1410")){
							jsonarray.put("sdocode","2120");
						}else{
						jsonarray.put("sdocode",Login.sdoCode);
						}
						//jsonarray.put("sdocode", Login.sdoCode);
						jsonarray.put("billmonth", Dashboard.month_arreas);
						jsonarray.put("arrearrange", arrearsrange);
						ja1.put(jsonarray);

						SendRequest req = new SendRequest();
						String url1 = "getConsumersArrearMobile";
						responsefromserver = req.uploadToServer(url1, ja1);
						if (!responsefromserver.equals(null)) {
							JSONArray arr = new JSONArray(responsefromserver);
							count.add("" + arr.length());
							JSONArray arrayj = new JSONArray(responsefromserver);
							responses.add(arrayj.toString());
						}

					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {

			for (int i = 0; i < count.size(); i++) {
				if (i == 0) {
					c1.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 1) {
					c2.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 2) {
					c3.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 3) {
					c4.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 4) {
					c5.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 5) {
					c6.setText("" + count.get(i) + " - Consumers");
				}
				if (i == 6) {
					c7.setText("" + count.get(i) + " - Consumers");
				}
			}

			mProgressDialog.dismiss();

		}

	}

	@Override
	public void onBackPressed() {
		finish();
	}

}
