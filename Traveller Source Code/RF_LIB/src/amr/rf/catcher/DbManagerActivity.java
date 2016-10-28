package amr.rf.catcher;

import java.util.ArrayList;

import bcits.rf.catcher.R;
import amr.rf.utils.DbMeterDetails;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DbManagerActivity extends Activity{

	ListView listMeterDetails;
	EditText edMeterNo,edRRno,edUUID,edChannel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_manager);
		listMeterDetails=(ListView)findViewById(R.id.listMeterDetails);
		edMeterNo=(EditText)findViewById(R.id.edMeterNo);
		edRRno=(EditText)findViewById(R.id.edRRno);
		edUUID=(EditText)findViewById(R.id.edUUID);
		edChannel=(EditText)findViewById(R.id.edChannel);
		setListView();
		
		 listMeterDetails.setOnItemClickListener(new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            	 final String val = (String) listMeterDetails.getItemAtPosition(position);
	            	 Toast.makeText(getApplicationContext(), val, Toast.LENGTH_SHORT).show();
	            	 new AlertDialog.Builder(DbManagerActivity.this).setMessage("Delete\n"+val+" ?").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							 DbMeterDetails db = new DbMeterDetails(getBaseContext());
							 db.open();
							 String meterNo= val.split(" - ")[0];
							 if(db.deleteRow(meterNo)) {
								 Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
								 setListView();
							 }
							 db.close();
							
						}
					}).setNegativeButton("No", null).show();
	            }
	        });
	}
	
	
 public void onClickSaveToSqlite(View v) {
	 String meterNo=edMeterNo.getText().toString().trim();
	 String rrNo=edRRno.getText().toString().trim();
	 String channel=edChannel.getText().toString().trim();
	 String uuid=edUUID.getText().toString().trim();
	 if(meterNo.isEmpty() || rrNo.isEmpty() || uuid.isEmpty() || channel.isEmpty()) {
		 Toast.makeText(getApplicationContext(), "Enter Full Field", Toast.LENGTH_SHORT).show();
		 return;
	 }
	 DbMeterDetails db = new DbMeterDetails(getBaseContext());
	 db.open();
	 if(db.saveMetersToDb(meterNo, rrNo, uuid, channel)) {
		 Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
		 edMeterNo.setText("");
		 edRRno.setText("");
		 edChannel.setText("");
		 edUUID.setText("");
		 setListView();
	 }
	 db.close();
	 
 }
 private void setListView () {
	 DbMeterDetails db = new DbMeterDetails(getBaseContext());
	 db.open();
	 ArrayList<String> meters= db.getDbContents();
	 db.close();
	 ArrayAdapter<String> adapter= new ArrayAdapter<>(DbManagerActivity.this, android.R.layout.simple_list_item_1,meters);
	 listMeterDetails.setAdapter(adapter);
 }
}
