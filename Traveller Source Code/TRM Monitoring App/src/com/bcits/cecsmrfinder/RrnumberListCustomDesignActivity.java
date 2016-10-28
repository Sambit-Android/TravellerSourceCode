package com.bcits.cecsmrfinder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.sqlite.cescip.DBMrWiseBilling;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RrnumberListCustomDesignActivity extends Activity {
    ListView listView ;
    public static String arrearsRange;
    TextView header;
    TextView mrname, mrcode, mrmobile, todaysdate;
    DBMrWiseBilling databasehandler;
    JSONObject object;
    ImageView taketomessages;
    LinearLayout contact;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		
		
        setContentView(R.layout.rr_no_custom_list);
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        header = (TextView)findViewById(R.id.header);
        header.setText(DetailedMRCardActivity.headertitle);
        taketomessages = (ImageView)findViewById(R.id.taketomessages);
        // Defined Array values to show in ListView
        contact = (LinearLayout)findViewById(R.id.contact);
        mrname = (TextView) findViewById(R.id.mrname);
		mrcode = (TextView) findViewById(R.id.mrcode);
		mrmobile = (TextView) findViewById(R.id.mrmobile);
		todaysdate = (TextView) findViewById(R.id.todaysdate);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		
		databasehandler = new DBMrWiseBilling(getApplicationContext());
		databasehandler.open();
		object = databasehandler.getMrCodeWiseDetails(DetailedMRCardActivity.mrcod);
		databasehandler.close();
        
        RRnumber_custom_listAdapter adapter = null;
		try {
			adapter = new RRnumber_custom_listAdapter(this);
			mrname.setText("MR NAME - " + object.getString("mr_name"));
			mrcode.setText("MR CODE - " + object.getString("mr_code"));
			if (object.getString("mr_mobile").equals("0")||object.getString("mr_mobile").equals("-")) {
				contact.setVisibility(View.GONE);
			}else{
				contact.setVisibility(View.VISIBLE);
				mrmobile.setText(/* "MOBILE - "+ */object.getString("mr_mobile"));
			}
			todaysdate.setText("Details As On - " + formattedDate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

             
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				/*arrearsRange = (String) ((TextView) view).getText().toString().trim();
				
				
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				          Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(RrnumberListCustomDesignActivity.this,MapSurveyArreas.class);
				startActivity(intent);*/
				
			}

         }); 
        taketomessages.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*SmsManager smsManager = SmsManager.getDefault();
                try {
					smsManager.sendTextMessage(object.getString("mr_mobile"), null,"Your message goes here", null, null);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
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

}