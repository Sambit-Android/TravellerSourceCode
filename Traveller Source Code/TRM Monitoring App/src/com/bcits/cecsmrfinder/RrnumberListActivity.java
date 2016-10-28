package com.bcits.cecsmrfinder;

import org.json.JSONException;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RrnumberListActivity extends Activity {
    ListView listView ;
    public static String arrearsRange;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_arrears);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_arrears, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
        setContentView(R.layout.rrnumberslist);
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        // Defined Array values to show in ListView
                
        /*String[] values = new String[] { "500 and 1000", 
        		"1000 and 5000","5000 and 10000","10000 and 25000","25000 and 50000","50000 and 100000","100000"};*/
        String[] values = new String[] { "500 to 1000", 
        		"1001 to 5000","5001 to 10000","10001 to 25000","25001 to 50000","50001 to 100000","Above 100000"};


        CustomAdapterArrearsRange adapter = null;
		try {
			adapter = new CustomAdapterArrearsRange(this,values);
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
				
				LinearLayout ll = (LinearLayout) view;
				TextView arrears = (TextView) ll.findViewById(R.id.textView1);
				
				arrearsRange = arrears.getText().toString().trim();
				
				
				/*Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				          Toast.LENGTH_SHORT).show();*/
				
				Intent intent = new Intent(RrnumberListActivity.this,MapSurveyArreas.class);
				startActivity(intent);
				
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