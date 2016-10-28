package com.bcits.disconreconn;

import com.bcits.disconnreconn.R;
import com.bcits.disconrecon.utils.MRDatabaseHandler;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MRCosumerActivity extends Activity{

	TextView title, txrrno, txname, txbalance, txinstallationtype, txtariff, txfdrcode, txpoleno, txtccode, txtaverage;
	Button btnNext,prevbill;
	Intent intent;
	MRDatabaseHandler databasehandler;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.mrconsumerdetailsactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		title = (TextView) findViewById(R.id.title);
		title.setText("MR Consumer");

		btnNext = (Button) findViewById(R.id.next);
		prevbill = (Button) findViewById(R.id.nexta);

        txrrno = (TextView) findViewById(R.id.tvrrno);
        txname = (TextView) findViewById(R.id.tvname);
        txbalance = (TextView) findViewById(R.id.tvbalance);
        txinstallationtype = (TextView) findViewById(R.id.tvaddress);
        txtariff = (TextView) findViewById(R.id.tvtariff);
        txfdrcode = (TextView) findViewById(R.id.tvfdrcode);
        txpoleno = (TextView) findViewById(R.id.txtpoleno);
        txtccode = (TextView) findViewById(R.id.txttccode);
        txtaverage = (TextView) findViewById(R.id.tvaverage);
        
        if ((LoginActivity.mrcode != null)	) {
			
		} else {

			MRUtilMaster.show(MRCosumerActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRCosumerActivity.this, LoginActivity.class)).show();

		}
	}


	@Override
	protected void onStart() {
		try {
			
			// TODO Auto-generated method stub
			super.onStart();
			
			txrrno.setText(MRUtilMaster.getMrrno());
			txname.setText(MRUtilMaster.getMconsumer_name());
			txbalance.setText(MRUtilMaster.getMmtrcode());
			
			
			if(MRUtilMaster.getMmeterflag().equals("0")){
				txinstallationtype.setText("METER CHANGE");
			}
			else if(MRUtilMaster.getMmeterflag().equals("1")){
				
				txinstallationtype.setText("NEW METER");
				
			}
			else{
				
				
				txinstallationtype.setText("--");
			}
			
			txtariff.setText(MRUtilMaster.getMtariff());
			txfdrcode.setText(MRUtilMaster.getMfdrcode());
			txpoleno.setText(MRUtilMaster.getMpoleno());
			txtccode.setText(MRUtilMaster.getMtccode());
			txtaverage.setText(MRUtilMaster.getMaddress());
			
			btnNext.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					if(MRUtilMaster.getMmeterflag().equals("0")){
						intent = new Intent(getApplicationContext(), MROldMtrDetailsRecordingActivity.class);
						startActivity(intent);
					}
					else if(MRUtilMaster.getMmeterflag().equals("1")){
						
						intent = new Intent(getApplicationContext(), MRNewMtrDetailsRecordingActivity.class);
						startActivity(intent);
						
					}
					else{
						intent = new Intent(getApplicationContext(), MROldMtrDetailsRecordingActivity.class);
						startActivity(intent);
					}
				}
			});
		
			} catch (Exception e) {

				// TODO: handle exception
				
				
				e.printStackTrace();
				
				
				if ((LoginActivity.mrcode != null)	) {

				} else {

					MRUtilMaster.show(MRCosumerActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRCosumerActivity.this, LoginActivity.class)).show();

				}
			}
		
		
		prevbill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(getApplicationContext(),HistoryOfBills.class);
				startActivity(intent);
				
			}
		});
		
		
		
		
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		String sqliterrno = null;


		//NEXT RRNO

		databasehandler = new MRDatabaseHandler(getApplicationContext());
		databasehandler.openToRead();
		Cursor cursor = databasehandler.checkuser_RRNO();
		/*startManagingCursor(cursor);*/
		if (cursor != null && cursor.moveToFirst()) {
			sqliterrno = cursor.getString(0);
		}else{
			sqliterrno = "-";
		}
		cursor.close();
		databasehandler.close();

		move_inten(getApplicationContext(), MRListActivity.class, sqliterrno);

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


}