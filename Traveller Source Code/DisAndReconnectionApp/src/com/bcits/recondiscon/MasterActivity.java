package com.bcits.recondiscon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MasterActivity extends Activity{

	@Override
	protected void onResume() {
		super.onResume();
		if(Login.sdoCode ==null || Login.sdoCode.isEmpty ()){
		 new AlertDialog.Builder(MasterActivity.this).setCancelable(false).setMessage("Session out").setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(MasterActivity.this, Login.class);
					startActivity(intent);
					
				}
			}).show();
		}
		return;
		
	}

	
	@Override
	public void onBackPressed() {
		this.finish();
	}
	
}
