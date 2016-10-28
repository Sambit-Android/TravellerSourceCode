package com.bcits.disconreconn;

import com.bcits.disconnreconn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HistoryOfBills extends Activity{
	ImageView previous;
	Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.history);
	   
	    previous = (ImageView) findViewById(R.id.previousscreen);
	    
	    
	    previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(getApplicationContext(),MRCosumerActivity.class);
				startActivity(intent);
				
			}
		});
		
	    
	}
	
	
	
	
	
	
	

}
