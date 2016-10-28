package amr.rf.catcher;

import bcits.rf.catcher.R;
import amr.rf.utils.UsbRecieverService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainRF extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_rf);
		startService(new Intent(MainRF.this,UsbRecieverService.class)); // START USB SERVICE
	}
	
	 public void onClickSingleBilling(View v) {
		 startActivity(new Intent(MainRF.this, SingleBilling.class));
	 }
	 
	 public void onClickGroupBilling(View v) {
		 startActivity(new Intent(MainRF.this, GroupBilling.class));
	 }
	 
	 public void onClickManageMeters(View v) {
		 startActivity(new Intent(MainRF.this, DbManagerActivity.class));
	 }
}
