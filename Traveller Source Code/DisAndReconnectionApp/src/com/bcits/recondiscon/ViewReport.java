package com.bcits.recondiscon;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sqlite.mvs.DbDisconnection;

public class ViewReport extends MasterActivity{

	Context context;
	ListView listReport;
	Button btnPrintReport;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_report);
		context=this;
		listReport=(ListView)findViewById(R.id.listReport);
		btnPrintReport=(Button)findViewById(R.id.btnPrintReport);
		DbDisconnection db = new DbDisconnection(context);
		ArrayList<String> dates= db.getAllDataWithDate();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.simple_spinner_small,R.id.txtSpinner,dates);
		listReport.setAdapter(adapter);
		
		
		btnPrintReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				BluetoothChat.isReport=true;
				Intent intent = new Intent(ViewReport.this,BluetoothChat.class);
				startActivity(intent);
			}
		});
		
		/*listReport.setOnItemClickListener(new OnItemClickListener(){


			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				String item = listReport.getItemAtPosition(arg2).toString();
				Intent intent = new Intent(ViewReport.this,ViewReport2.class);
				intent.putExtra("DATE", item);
				startActivity(intent);
			  }
			});*/
	}
}
