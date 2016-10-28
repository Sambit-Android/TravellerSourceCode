package com.bcits.disconreconn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.disconnreconn.R;
import com.bcits.disconrecon.utils.MRDatabaseHandler;

public class MRListActivity extends Activity{

	TextView title,  txTotalConnections, txNotReplaced, txReplaced, txNotSyncedToServer, txSyncedToServer ;
	Button btnNext, btnMVprv,btnMVnxt ;
	Intent intent;
	MRDatabaseHandler databasehandler;
	boolean dbstatus;
	AutoCompleteTextView edtAccNo;
	String data_str, accno, flag;
	int backJ = 1 ;
	int i=1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.mrlistactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		title = (TextView) findViewById(R.id.title);
		title.setText("MtrReplaceList");

		btnNext = (Button) findViewById(R.id.next);

		txTotalConnections = (TextView) findViewById(R.id.totalconnectionvalue);
		txNotReplaced = (TextView) findViewById(R.id.nocunbilledvalue);
		txReplaced = (TextView) findViewById(R.id.nocbilledvalue);
		txNotSyncedToServer = (TextView) findViewById(R.id.nocsyncedvalue);
		txSyncedToServer = (TextView) findViewById(R.id.txtUnsynched);


		btnMVprv  = (Button)findViewById(R.id.button1);
		btnMVnxt  = (Button)findViewById(R.id.Button01);
		edtAccNo =  (AutoCompleteTextView)findViewById(R.id.accountno);

		  if ((LoginActivity.mrcode != null)	) {
				
			} else {

				MRUtilMaster.show(MRListActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRListActivity.this, LoginActivity.class)).show();

			}

	}


	@Override
	protected void onStart() {
		
		try {
			// TODO Auto-generated method stub
			super.onStart();
			
			
			setDashboard();

			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                 android.R.layout.simple_dropdown_item_1line, getRRNoValues());
			 edtAccNo.setAdapter(adapter);
			
			data_str = getIntent().getStringExtra("RRno/bu");
			System.out.println("data from mode"+data_str);
			System.out.println("data from print" + data_str + "");

			edtAccNo.setText(data_str.trim());

			accno = edtAccNo.getText().toString().trim();
			
			btnMVnxt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {


					System.out.println("forword else statement");

					btnMVnxt.setEnabled(false);
						System.out
						.println("forword else statement  *****  consumer no search");
						Log.e("came in account check offline",
								"came in account check");
						flag = "0";

						System.out.println("account no"
								+ edtAccNo.getText().toString());


							databasehandler = new MRDatabaseHandler(
									getApplicationContext());

							try {

								databasehandler.openToRead();

								/*
								 * fetching unbilled count from sqlite DB
								 */
								Cursor cursor_next = databasehandler.Fetchnextconsumer();
								startManagingCursor(cursor_next);
								String flag_offline = "0";
								accno = edtAccNo.getText().toString().trim();
								if(i == 1){
									backJ = 1 ;
								}

								if (cursor_next.moveToPosition(i)) {

									i++;
									System.out.println("count" + i);
									edtAccNo.setText(cursor_next.getString(0));
								}
								cursor_next.close();
							} catch (Exception e) {
								
								e.printStackTrace();

							}
							databasehandler.close();
							btnMVnxt.setEnabled(true);
						
				}
			});
			
			
			btnMVprv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					btnMVprv.setEnabled(false);


						Log.e("came in account check", "came in account check");
						flag = "0";
						

							databasehandler = new MRDatabaseHandler(getApplicationContext());
							try {
								databasehandler.openToRead();

								/*
								 * fetching unbilled count from sqlite DB
								 */
								Cursor cursor_next = databasehandler.Fetchnextconsumer();
								startManagingCursor(cursor_next);
								String flag_offline = "0";
								String offlin_rrno = null;
								accno = edtAccNo.getText().toString().trim();
								if (cursor_next.moveToPosition(cursor_next.getCount()-backJ)) {
									if (cursor_next.getString(0) == accno) {
										btnMVprv.setEnabled(true);
										Toast.makeText(getApplicationContext(),	"No more consumer ....",Toast.LENGTH_LONG).show();
									} else {
										edtAccNo.setText(cursor_next.getString(0));
										/*accno = cursor_next
												.getString(0) ;*/
										++backJ;
									}

								}else{
									btnMVprv.setEnabled(true);
									Toast.makeText(getApplicationContext(),	"No more consumer",	Toast.LENGTH_LONG).show();
								}
								cursor_next.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							databasehandler.close();
							btnMVprv.setEnabled(true);
						
					
				}
			});
			
			btnNext.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {


					if (edtAccNo.getText().toString().equals("")) 
					{
						
							MRUtilMaster.showToast(MRListActivity.this,"Enter RR No").show();

						
					} 
					else{
						databasehandler = new MRDatabaseHandler(getApplicationContext());
						
						
						
						try {
							boolean isdetailsSet = false ;
							databasehandler.openToRead();
								
							
							Cursor cursor_next = databasehandler.Getconsumer_detailsbyconsumer_no(edtAccNo.getText().toString().trim());
								startManagingCursor(cursor_next);
								String flag_offline = "0";
								String offlin_rrno = null;
								accno = edtAccNo.getText().toString().trim().replaceAll(" +", "");
								if (cursor_next != null && cursor_next.moveToFirst()) 
								{
									isdetailsSet  = setConsumerDetailsforbilling(cursor_next);
								}else
								{
									MRUtilMaster.showToast(MRListActivity.this,"Invalid RR No").show();

								}
								cursor_next.close();
								databasehandler.close();



							
							System.out.println("accno is : " +accno);
							System.out.println("UtilMaster.getMconsumerid() :"+MRUtilMaster.getMrrno());
							
							if(MRUtilMaster.getMmeterreplacestatus().equals("0") && ((accno.equals(MRUtilMaster.getMrrno().trim()) || (accno.equals(MRUtilMaster.getMrrno())) ))) 
							{
								

								move_inten(MRListActivity.this,MRCosumerActivity.class);
								
								
							}else if(MRUtilMaster.getMmeterreplacestatus().equals("1")) 
							{
								
								new AlertDialog.Builder(MRListActivity.this)
								.setTitle("Info..! ")
								.setMessage("Meter Already Replaced")
								.setCancelable(false)
								.setPositiveButton("OK", null)
								.show();

							}
							else
							{
							}


						}

						catch(Exception exception){
							
							exception.printStackTrace();
						}
					}
				}
			
			
			});
		
		
			} catch (Exception e) {

				// TODO: handle exception
				
				
				e.printStackTrace();
				
				
				if ((LoginActivity.mrcode != null)	) {

				} else {

					MRUtilMaster.show(MRListActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRListActivity.this, LoginActivity.class)).show();

				}
			}
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		intent = new Intent(getApplicationContext(), MRStatusActivity.class);
		startActivity(intent);


	}






	public void setDashboard(){

		databasehandler = new MRDatabaseHandler(getApplicationContext());
		try {
			databasehandler.openToRead();
			dbstatus = databasehandler.checkDataBase();
			databasehandler.close();
			if(dbstatus)
			{
				databasehandler = new MRDatabaseHandler(getApplicationContext());
				try {



					/*//NEXT RRNO
					databasehandler.openToRead();
					Cursor cursor = databasehandler.checkuser_RRNO();
					startManagingCursor(cursor);
					if (cursor != null && cursor.moveToFirst()) {
						sqlit_rrno = cursor.getString(0);
						//txTotal.setText("Total Connections          : "+sqlit_rrno);
					}else{
						sqlit_rrno = "-";
					}
					cursor.close();
					databasehandler.close();*/


					//TOTAL CONNECTIONS
					databasehandler.openToRead();
					Cursor cursor = databasehandler.Gettotalcount();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {

						txTotalConnections.setText(cursor.getString(0));

					}else{
						txTotalConnections.setText("-");
					}
					cursor.close();
					databasehandler.close();


					//BILLED COUNT
					databasehandler.openToRead();
					cursor = databasehandler.GetReplacedcount();
				/*	startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {

						txReplaced.setText(cursor.getString(0));

					}else{

						txReplaced.setText("-");
					}
					cursor.close();
					databasehandler.close();


					//UNBILLED COUNT
					databasehandler.openToRead();
					cursor = databasehandler.GetNotReplacedCount();
					/*startManagingCursor(cursor);*/
					if (cursor != null
							&& cursor.moveToFirst()) {


						txNotReplaced.setText(cursor.getString(0));

					}else {

						txNotReplaced.setText("-");

					}
					cursor.close();
					databasehandler.close();


					//SYCN TO SERVER
					databasehandler.openToRead();
					cursor = databasehandler.GetSyncedToserverCount();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {

						txSyncedToServer.setText(cursor.getString(0));

					}else {

						txSyncedToServer.setText("-");

					}
					cursor.close();
					databasehandler.close();


					//not synced to server

					databasehandler.openToRead();
					cursor = databasehandler.GetNotSyncedToServerCount();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {

						txNotSyncedToServer.setText(cursor.getString(0));
					}
					else{

						txNotSyncedToServer.setText("-");

					}


					cursor.close();
					databasehandler.close();


					/*// Mrname

					databasehandler.openToRead();
					cursor = databasehandler.checkmReadMR();
					startManagingCursor(cursor);
					if (cursor != null && cursor.moveToFirst()) {
						String mrcode = cursor.getString(cursor	.getColumnIndex("MR"));
						txmrname.setText(mrcode);
						mrCodeVal = mrcode ;
					} else {
						txmrname.setText(" - ");
						mrCodeVal ="-";

					}
					cursor.close();
					databasehandler.close();*/



				} catch (Exception e) {
					e.printStackTrace();
				}

				//databasehandler.close();

			}

		}catch(Exception exception){
			exception.printStackTrace();
		}

	}



	
	public boolean setConsumerDetailsforbilling(Cursor cursor_next)throws NumberFormatException, SQLException 
	{
		System.out.println(" ************  STARTED ****************");

		MRUtilMaster.setMrrno(cursor_next.getString(cursor_next.getColumnIndex("RRNO")));
		
		System.out.println("rrno :"+MRUtilMaster.getMrrno());
		
		MRUtilMaster.setMlistno(cursor_next.getString(cursor_next.getColumnIndex("LISTNO")));
		MRUtilMaster.setMlistdate(cursor_next.getString(cursor_next.getColumnIndex("LISTDATE")));
		MRUtilMaster.setMtariff(cursor_next.getString(cursor_next.getColumnIndex("TARIFF")));
		MRUtilMaster.setMsocode(cursor_next.getString(cursor_next.getColumnIndex("SOCODE")));
		MRUtilMaster.setMdramt(cursor_next.getString(cursor_next.getColumnIndex("DRAMT")));
		MRUtilMaster.setMrdngdate(cursor_next.getString(cursor_next.getColumnIndex("RDNGDATE")));
		MRUtilMaster.setMmtrcode(cursor_next.getString(cursor_next.getColumnIndex("MTRCODE")));
		MRUtilMaster.setMarrears(cursor_next.getString(cursor_next.getColumnIndex("ARREARS")));
		MRUtilMaster.setMusername(cursor_next.getString(cursor_next.getColumnIndex("USERNAME")));
		MRUtilMaster.setMdatestamp(cursor_next.getString(cursor_next.getColumnIndex("DATESTAMP")));
		MRUtilMaster.setMageing(cursor_next.getString(cursor_next.getColumnIndex("AGEING")));
		MRUtilMaster.setMstatus(cursor_next.getString(cursor_next.getColumnIndex("STATUS")));
		MRUtilMaster.setMid(cursor_next.getString(cursor_next.getColumnIndex("ID")));
		MRUtilMaster.setMcollectionid(cursor_next.getString(cursor_next.getColumnIndex("COLLECTIONID")));
		MRUtilMaster.setMoldrrno(cursor_next.getString(cursor_next.getColumnIndex("OLDRRNO")));
		MRUtilMaster.setMconsumer_name(cursor_next.getString(cursor_next.getColumnIndex("CONSUMER_NAME")));
		MRUtilMaster.setMaddress(cursor_next.getString(cursor_next.getColumnIndex("ADDRESS")));
		MRUtilMaster.setMfdrcode(cursor_next.getString(cursor_next.getColumnIndex("FDRCODE")));
		MRUtilMaster.setMtccode(cursor_next.getString(cursor_next.getColumnIndex("TCCODE")));
		MRUtilMaster.setMpoleno(cursor_next.getString(cursor_next.getColumnIndex("POLENO")));
		MRUtilMaster.setMbalance(cursor_next.getString(cursor_next.getColumnIndex("BALANCE")));
		MRUtilMaster.setMaverage(cursor_next.getString(cursor_next.getColumnIndex("AVERAGE")));
		MRUtilMaster.setMomdateofrelease(cursor_next.getString(cursor_next.getColumnIndex("OMDATEOFRELEASE")));
		MRUtilMaster.setMomserialno(cursor_next.getString(cursor_next.getColumnIndex("OMSERIALNO")));
		MRUtilMaster.setMommake(cursor_next.getString(cursor_next.getColumnIndex("OMMAKE")));
		MRUtilMaster.setMomcapacity(cursor_next.getString(cursor_next.getColumnIndex("OMCAPACITY")));
		MRUtilMaster.setMomcover(cursor_next.getString(cursor_next.getColumnIndex("OMCOVER")));
		MRUtilMaster.setMomposition(cursor_next.getString(cursor_next.getColumnIndex("OMPOSITION")));
		MRUtilMaster.setMomtype(cursor_next.getString(cursor_next.getColumnIndex("OMTYPE")));
		MRUtilMaster.setMomfr(cursor_next.getString(cursor_next.getColumnIndex("OMFR")));
		MRUtilMaster.setMomimage(cursor_next.getString(cursor_next.getColumnIndex("OMIMAGE")));
		MRUtilMaster.setMomlongitude(cursor_next.getString(cursor_next.getColumnIndex("OMLONGITUDE")));
		MRUtilMaster.setMomlattitude(cursor_next.getString(cursor_next.getColumnIndex("OMLATTITUDE")));
		MRUtilMaster.setMnmdateofinstall(cursor_next.getString(cursor_next.getColumnIndex("NMDATEOFINSTALL")));
		MRUtilMaster.setMnmserialno(cursor_next.getString(cursor_next.getColumnIndex("NMSERIALNO")));
		MRUtilMaster.setMnmmake(cursor_next.getString(cursor_next.getColumnIndex("NMMAKE")));
		MRUtilMaster.setMnmcapacity(cursor_next.getString(cursor_next.getColumnIndex("NMCAPACITY")));
		MRUtilMaster.setMnmcover(cursor_next.getString(cursor_next.getColumnIndex("NMCOVER")));
		MRUtilMaster.setMnmposition(cursor_next.getString(cursor_next.getColumnIndex("NMPOSITION")));
		MRUtilMaster.setMnmtype(cursor_next.getString(cursor_next.getColumnIndex("NMTYPE")));
		MRUtilMaster.setMnmir(cursor_next.getString(cursor_next.getColumnIndex("NMIR")));
		MRUtilMaster.setMnmimage(cursor_next.getString(cursor_next.getColumnIndex("NMIMAGE")));
		MRUtilMaster.setMnmlongitude(cursor_next.getString(cursor_next.getColumnIndex("NMLONGITUDE")));
		MRUtilMaster.setMnmlattitude(cursor_next.getString(cursor_next.getColumnIndex("NMLATTITUDE")));
		MRUtilMaster.setMmeterreplacedate(cursor_next.getString(cursor_next.getColumnIndex("METERREPLACEDATE")));
		MRUtilMaster.setMdeviceid(cursor_next.getString(cursor_next.getColumnIndex("DEVICEID")));
			
		MRUtilMaster.setMmeterreplacestatus(cursor_next.getString(cursor_next.getColumnIndex("METERREPLACESTATUS")));
		MRUtilMaster.setMmr(cursor_next.getString(cursor_next.getColumnIndex("MR")));
		
		System.out.println("MR :"+MRUtilMaster.getMmr());
		
		
		MRUtilMaster.setMservertomobiledate(cursor_next.getString(cursor_next.getColumnIndex("SERVERTOMOBILEDATE")));
		MRUtilMaster.setMdevicefirmwareversion(cursor_next.getString(cursor_next.getColumnIndex("DEVICEFIRMWAREVERSION")));
		MRUtilMaster.setMmeterflag(cursor_next.getString(cursor_next.getColumnIndex("METERFLAG")));
		
		MRUtilMaster.setMextra1(cursor_next.getString(cursor_next.getColumnIndex("EXTRA1")));
		MRUtilMaster.setMextra2(cursor_next.getString(cursor_next.getColumnIndex("EXTRA2")));
		MRUtilMaster.setMextra3(cursor_next.getString(cursor_next.getColumnIndex("EXTRA3")));
		MRUtilMaster.setMextra4(cursor_next.getString(cursor_next.getColumnIndex("EXTRA4")));
		MRUtilMaster.setMextra5(cursor_next.getString(cursor_next.getColumnIndex("EXTRA5")));
		MRUtilMaster.setMextra6(cursor_next.getString(cursor_next.getColumnIndex("EXTRA6")));


		System.out.println(" ************  ENDING ****************");
		return true ;

	}
	
	
	
	
	
	/* ********************* USER DEFINED METHODS *******************************/	



	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				move_inten(context, class1);
			}
		};

	}

	public void move_inten( Context context , Class class1 ) {
		Intent intent = new Intent(context,class1);
		startActivity(intent);
	}
	public void move_inten( Context context , Class class1 , String data ) {

		Intent intent = new Intent(context,class1);
		intent.putExtra("RRno/bu", data);
		startActivity(intent);
	}


	
	private String[] getRRNoValues(){
		
		 String[] rrno = null;

		try {
			databasehandler = new MRDatabaseHandler(getApplicationContext());
			databasehandler.openToRead();
			// fetching billed count from sqlite DB
			Cursor cursor_cmrid = databasehandler.GetDistictRRNO();
			//		startManagingCursor(cursor_billed);
			
			rrno = new String[cursor_cmrid.getCount()];
			
			
			if(cursor_cmrid != null){
				
				for(int i = 0; i< cursor_cmrid.getCount() ;i++){
					
					cursor_cmrid.moveToPosition(i);
					
					rrno[i] = cursor_cmrid.getString(cursor_cmrid.getColumnIndex("RRNO"));
					
					System.out.println("RRNO("+i+") is :"+rrno[i]);
					
					
				}
				
			}
			
			cursor_cmrid.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		databasehandler.close();
		
		/* String[] cmrid = new String[] {
	         "  Belgium", "  France", "  Italy", "  Germany", "  Spain"
	     };*/
		 
		 return rrno;
		 
	}
	

}