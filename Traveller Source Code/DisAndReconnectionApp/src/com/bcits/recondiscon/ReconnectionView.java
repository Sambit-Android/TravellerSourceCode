package com.bcits.recondiscon;

import getter.setter.GetterSetterDisconnection;

import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.sqlite.mvs.DbDisconnection;

public class ReconnectionView extends MasterActivity {
	TextView  txtAcc, txtTariff;
	ImageView imgPrevious, imgNext;
	Button btnDisconnect ;
	ImageView btnGetTouteMap;
	int localId = 1;
	AlertDialog show;
	public static GetterSetterDisconnection disc;
	AutoCompleteTextView txtAccountNumber;
	ArrayAdapter<String> adapter;
	ArrayList<String> arrayAccounts;
	TextView txtName, txtAddress;
	
	ImageView imgSearch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Reconnection");
		setContentView(R.layout.reconnection_view);
		
		txtAccountNumber = (AutoCompleteTextView)findViewById(R.id.txtAccountNumber);
		txtAcc = (TextView) findViewById(R.id.txtAcc);
		imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
		imgNext = (ImageView) findViewById(R.id.imgNext);
		btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
		btnGetTouteMap = (ImageView) findViewById(R.id.btnGetTouteMap);
		txtTariff = (TextView) findViewById(R.id.txtTariff);
		imgPrevious.setClickable(false);
		imgPrevious.setBackgroundColor(getResources().getColor(R.color.Gray));
		txtName=(TextView)findViewById(R.id.txtName);
		txtAddress=(TextView)findViewById(R.id.txtAddress);
		imgSearch=(ImageView)findViewById(R.id.imgSearch);
		
		DbDisconnection ddd = new DbDisconnection(getBaseContext());
		ddd.open();
		arrayAccounts= ddd.getAccountNumberReconnect();
		String tot = ddd.getTodalToReconnect();
		ddd.close();
		int total = Integer.parseInt(tot);
		if (total == 0) {
			show = new AlertDialog.Builder(ReconnectionView.this)
					.setMessage("All the connections are reconnected")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,	int which) {
									Intent intent = new Intent(ReconnectionView.this,DashBoard.class);
									startActivity(intent);
									ReconnectionView.this.finish();
								}
							}).show();

		}else{
			adapter =new ArrayAdapter<String>(ReconnectionView.this, android.R.layout.simple_dropdown_item_1line, arrayAccounts);
			getAccountDetails();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		imgSearch.setVisibility(View.GONE);
		txtAccountNumber.setCursorVisible(false);
		imgSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(txtAccountNumber.getText().toString().equals("")){
					show = new AlertDialog.Builder(ReconnectionView.this)
					.setMessage("Please enter account number")
					.setCancelable(true)
					.setPositiveButton("Ok",null).show();
				}
				else{
				txtAccountNumber.setAdapter(null);
				DbDisconnection dbb = new DbDisconnection(getBaseContext());
				dbb.open();
				if(dbb.checkReconnByAccount(txtAccountNumber.getText().toString().trim().toUpperCase(Locale.ENGLISH))){
					dbb.close();
					show = new AlertDialog.Builder(ReconnectionView.this)
					.setMessage("This number not exists")
					.setCancelable(true)
					.setPositiveButton("Ok",null).show();
				}else{
					disc = dbb.getReconnByAccount(txtAccountNumber.getText().toString().trim().toUpperCase(Locale.ENGLISH));
					dbb.close();
					if(disc==null){
						show = new AlertDialog.Builder(ReconnectionView.this)
						.setMessage("This number is already reconnected")
						.setCancelable(true)
						.setPositiveButton("Ok",null).show();
					}
					else{
					txtAccountNumber.setText(disc.getRrno());
					txtAcc.setText(disc.getRrno());
					txtTariff.setText(disc.getTariff());
					if(disc.getAddress().equals("null") || disc.getAddress().equals("null") )
					{
						txtAddress.setText("Address not available");
						txtName.setText("Name not available");
					}else{
						txtAddress.setText(disc.getAddress());
						txtName.setText(" KEMPEGOWDA");
					}
				}
				}
			
				}
			}
		});
		
		txtAccountNumber.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				DbDisconnection db = new DbDisconnection(getBaseContext());
				db.open();
				disc = db.getReconnByAccount(txtAccountNumber.getText().toString().trim());
				db.close();
				txtAccountNumber.setText(disc.getRrno());
				txtAcc.setText(disc.getRrno());
				txtTariff.setText(disc.getTariff());
				if(disc.getAddress().equals("null") || disc.getAddress().equals("null") )
				{
					txtAddress.setText("Address not available");
					txtName.setText("Name not available");
				}else{
					txtAddress.setText(disc.getAddress());
					txtName.setText(disc.getConsumer_name());
				}
				Toast.makeText(getApplicationContext(), txtAccountNumber.getText().toString(), Toast.LENGTH_LONG).show();
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
				Intent intent = new Intent(ReconnectionView.this,ReconnectionSend.class);
				startActivity(intent);
			}
		});
		
		
		
	btnGetTouteMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ReconnectionView.this,MapMainActivity.class);
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

	private void getAccountDetails() { //TODO

		DbDisconnection ddd = new DbDisconnection(getBaseContext());
		ddd.open();
		String tot = ddd.getTodalToReconnect();
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

		if (total==1) {
			localId = total;
			imgNext.setClickable(false);
			imgNext.setBackgroundColor(getResources().getColor(R.color.LightGrey));
			imgPrevious.setClickable(false);
			imgPrevious.setBackgroundColor(getResources().getColor(R.color.LightGrey));
		} 

		DbDisconnection db = new DbDisconnection(getBaseContext());
		db.open();
		disc = db.getReconnOneByOne(localId + "");
		db.close();
		txtAccountNumber.setText(disc.getRrno());
		txtAcc.setText(disc.getRrno());
		txtTariff.setText(disc.getTariff());
		if(null==disc.getAddress() || null==disc.getAddress())
		{
			txtAddress.setText("Address not available");
			txtName.setText("Name not available");
		}else{
			txtAddress.setText(disc.getAddress());
			txtName.setText(disc.getConsumer_name());
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ReconnectionView.this, DashBoard.class);
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
			Intent intent = new Intent(ReconnectionView.this,DashBoard.class);
			startActivity(intent);
			this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
