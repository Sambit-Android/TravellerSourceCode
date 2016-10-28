package com.bcits.recondiscon;

import getter.setter.GetterSetterDisconnection;

import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.uitls.maps.MapMainActivity;
import com.bcits.utils.SendRequest;
import com.sqlite.mvs.DbDisconnection;

public class DisconnectionView extends MasterActivity {
	TextView txtAcc, txtArrears, txtTariff;
	TextView txtLastPaidDate,txtLastPaidAmount;
	AutoCompleteTextView txtAccountNumber;
	ImageView imgPrevious, imgNext;
	Button btnDisconnect,btnCheckPaymentStatus ;
	ImageView btnGetTouteMap;
	int localId = 1;
	public static GetterSetterDisconnection disc;
	String scdoCode, ListNo;

	ArrayAdapter<String> adapter;
	ArrayList<String> arrayAccounts;
	TextView txtName, txtAddress;
	ImageView imgSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Select Consumer"); 
		setContentView(R.layout.disconnection_view);

		txtLastPaidDate=(TextView)findViewById(R.id.txtLastPaidDate);
		txtLastPaidAmount=(TextView)findViewById(R.id.txtLastPaidAmount);
		
		txtArrears = (TextView) findViewById(R.id.txtArrears);
		txtAccountNumber = (AutoCompleteTextView) findViewById(R.id.txtAccountNumber);
		txtAcc = (TextView) findViewById(R.id.txtAcc);
		imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
		imgNext = (ImageView) findViewById(R.id.imgNext);
		btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
		txtTariff = (TextView) findViewById(R.id.txtTariff);
		imgPrevious.setClickable(false);
		imgPrevious.setBackgroundColor(getResources().getColor(R.color.LightGrey));
		txtName = (TextView) findViewById(R.id.txtName);
		txtAddress = (TextView) findViewById(R.id.txtAddress);
		imgSearch = (ImageView) findViewById(R.id.imgSearch);
		btnGetTouteMap = (ImageView) findViewById(R.id.btnGetTouteMap);
		btnCheckPaymentStatus=(Button)findViewById(R.id.btnCheckPaymentStatus);
		
		DbDisconnection ddd = new DbDisconnection(getBaseContext());
		ddd.open();
		arrayAccounts = ddd.getAccountNumberDiconnect();
		String tot = ddd.getTodalToDisconnect();
		ddd.close(); 
		int total = Integer.parseInt(tot);
		if (total == 0) {
			 new AlertDialog.Builder(DisconnectionView.this)
					.setMessage("All the connections are disconnected")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,int which) {
									Intent intent = new Intent(DisconnectionView.this,DashBoard.class);
									startActivity(intent);
									DisconnectionView.this.finish();
								}
							}).show();
		} else {
			adapter = new ArrayAdapter<String>(DisconnectionView.this,	android.R.layout.simple_dropdown_item_1line, arrayAccounts);
			getAccountDetails();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		imgSearch.setVisibility(View.GONE);
		txtAccountNumber.setCursorVisible(false);
		
		btnCheckPaymentStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			//TODO
				new GetListDetailsAsync(txtAcc.getText().toString().trim()).execute();
			}
		});
		
		imgSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(txtAccountNumber.getText().toString().equals("")){
					 new AlertDialog.Builder(DisconnectionView.this)
					.setMessage("Please enter account number")
					.setCancelable(true)
					.setPositiveButton("Ok",null).show();
				}
				else{
				txtAccountNumber.setAdapter(null);
				DbDisconnection dbb = new DbDisconnection(getBaseContext());
				dbb.open();
				if(dbb.checkDisconnByAccount(txtAccountNumber.getText().toString().trim().toUpperCase(Locale.ENGLISH))){
					dbb.close();
					 new AlertDialog.Builder(DisconnectionView.this)
					.setMessage("This number not exists")
					.setCancelable(true)
					.setPositiveButton("Ok",null).show();
				}else{
				disc = dbb.getDisconnByAccount(txtAccountNumber.getText().toString().trim().toUpperCase(Locale.ENGLISH));
				dbb.close();

				if (disc == null) {
					 new AlertDialog.Builder(DisconnectionView.this)
					.setMessage("This number is already disconnected")
					.setCancelable(true)
					.setPositiveButton("Ok",null).show();
				} else {
					txtAccountNumber.setText(disc.getRrno());
					txtAcc.setText(disc.getRrno());
					txtTariff.setText(disc.getTariff());
					txtArrears.setText(disc.getArrears());
					if (disc.getAddress().equals("null")|| disc.getAddress().equals("null")) {
						txtAddress.setText("Address not available");
						txtName.setText("Name not available");
					} else {
						txtAddress.setText(disc.getAddress());
						txtName.setText(disc.getConsumer_name());
					}
				}
				}
				}
			}
		});
		txtAccountNumber.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				DbDisconnection db = new DbDisconnection(getBaseContext());
				db.open();
				disc = db.getDisconnByAccount(txtAccountNumber.getText().toString().trim());
				db.close();
				txtAccountNumber.setText(disc.getRrno());
				txtAcc.setText(disc.getRrno());
				txtTariff.setText(disc.getTariff());
				txtArrears.setText(disc.getArrears());
				if (disc.getAddress().equals("null")|| disc.getAddress().equals("null")) {
					txtAddress.setText("Address not available");
					txtName.setText("Name not available");
				} else {
					txtAddress.setText(disc.getAddress());
					txtName.setText(disc.getConsumer_name());
				}

				Toast.makeText(getApplicationContext(),
						txtAccountNumber.getText().toString(),
						Toast.LENGTH_LONG).show();

			}
		});

		txtAccountNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txtAccountNumber.setCursorVisible(true);
				imgSearch.setVisibility(View.VISIBLE);
				txtAccountNumber.setAdapter(adapter);
			}
		});

		imgNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtAccountNumber.setCursorVisible(false);
				imgSearch.setVisibility(View.GONE);
				txtAccountNumber.setAdapter(null);
				localId++;
				getAccountDetails();
			}
		});
		imgPrevious.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtAccountNumber.setCursorVisible(false);
				imgSearch.setVisibility(View.GONE);
				txtAccountNumber.setAdapter(null);
				localId--;
				getAccountDetails();
			}
		});
		btnDisconnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DisconnectionView.this,	DisconnectionSend.class);
				startActivity(intent);
			}
		});
		
		
		
		
		btnGetTouteMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(DisconnectionView.this,MapMainActivity.class);
//				TrackDisconnection.isdisconnection=true; 
//				MapMainActivity.isdisconnection=true; 
				String accountNo= txtAcc.getText().toString().trim();
				i.putExtra("accountNo", accountNo);
				//Intent i = new Intent(DisconnectionView.this,MapActivity.class);
				//i.putExtra("Address", txtArrears.getText());
			//	i.putExtra("Address", "Hebbal bangalore");
				startActivity(i);
				
				
				
				
			}
		});
	}

	private void getAccountDetails() {

		DbDisconnection ddd = new DbDisconnection(getBaseContext());
		ddd.open();
		String tot = ddd.getTodalToDisconnect();
		ddd.close();
		int total = Integer.parseInt(tot);

		System.out.println("======================================");
		System.out.println(total+"   =total=====================================");
		System.out.println(localId+"  == localId====================================");
		System.out.println("======================================");
		
		
		
		if (localId <= 1) {
			localId = 1;
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources().getColor(R.color.LightGrey));
		} else {
			imgPrevious.setClickable(true);
			imgPrevious.setBackgroundResource(R.drawable.button_main);
		}

		if (localId >= total) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.LightGrey));
		} else {
			imgNext.setClickable(true);
			imgNext.setBackgroundResource(R.drawable.button_main);

		}

		if (total == 1) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.LightGrey));
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources().getColor(R.color.LightGrey));
		}
		
		DbDisconnection db = new DbDisconnection(getBaseContext());
		db.open();
		disc = db.getDisconnOneByOne(localId + "");
		db.close();
		txtAccountNumber.setText(disc.getRrno());
		txtAcc.setText(disc.getRrno());
		txtTariff.setText(disc.getTariff());
		txtArrears.setText(disc.getArrears());
		
		txtLastPaidDate.setText(disc.getLastPaidDate());
		txtLastPaidAmount.setText(disc.getLastPaidAmount());
		
		if (disc.getAddress().equals("null")|| disc.getAddress().equals("null")) {
			txtAddress.setText("Address not available");
			txtName.setText("Name not available");
		} else {
			txtAddress.setText(disc.getAddress());
			txtName.setText(disc.getConsumer_name());
		}

	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(DisconnectionView.this, DashBoard.class);
		startActivity(intent);
		this.finish();
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.back_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btn_back:
			Intent intent = new Intent(DisconnectionView.this,	DashBoard.class);
			startActivity(intent);
			this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	private class GetListDetailsAsync extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		String accountNo;
		ProgressDialog mProgressDialog;
		public GetListDetailsAsync(String accountNo) {
			this.accountNo=accountNo;
			mProgressDialog = new ProgressDialog(DisconnectionView.this);
			mProgressDialog.setMessage("Checking Payment Status...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				String listNo = "",sdoCode =Login.sdoCode;
					
				DbDisconnection dbbb = new DbDisconnection(getBaseContext());
				dbbb.open();
				if (dbbb.getListNo().split("@@@")[0] != null&& dbbb.getListNo().split("@@@")[1] != null) {
					sdoCode = dbbb.getListNo().split("@@@")[0];
					listNo = dbbb.getListNo().split("@@@")[1];
				}
					
				
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
					jsonarray.put("MRCODE", Login.userName);
					jsonarray.put("SDOCODE", sdoCode);
					jsonarray.put("LISTNO", listNo);
					jsonarray.put("ACCOUNTNO", accountNo);
					ja1.put(jsonarray);
					SendRequest req = new SendRequest();
					String url1 = "getDisconnectionPaymentStatus";
					responsefromserver = req.sendRequest(url1, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void unused) {

			if (responsefromserver == null) {
				new AlertDialog.Builder(DisconnectionView.this).setCancelable(false)
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

				new AlertDialog.Builder(DisconnectionView.this).setCancelable(false)
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

				new AlertDialog.Builder(DisconnectionView.this).setCancelable(false)
						.setMessage(
								"No Data Found.")
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
				try {
					JSONArray ja = new JSONArray(responsefromserver);
					mProgressDialog.setMax(ja.length());
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = ja.getJSONObject(i);
						String status = obj.getString("status");
						String arrears = obj.getString("arrears");

						System.out.println(status+"  "+arrears+"    ==============");
						
						if(status.equals("0")) {
							new AlertDialog.Builder(
									DisconnectionView.this)
									.setMessage("This consumer has not paid the bill yet")
									.setPositiveButton("Ok",null).show();
						}else {
							new AlertDialog.Builder(
									DisconnectionView.this)
									.setMessage("This consumer has paid the bill. Current Arrears is "+arrears)
									.setPositiveButton("Ok",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog,	int which) {/*
											     DbDisconnection db = new DbDisconnection(getBaseContext());
											     db.open();
											     if(db.updatePaymentStatus()) {
											    	 Toast.makeText(getApplicationContext(), "Payment Status Updated", Toast.LENGTH_SHORT).show();
											     }
											     db.close();

												*/
													}
											}).show();
						}
						
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}

			mProgressDialog.dismiss();
		}
	}
}
