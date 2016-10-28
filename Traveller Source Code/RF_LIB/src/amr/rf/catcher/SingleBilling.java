package amr.rf.catcher;


import android.os.Bundle;
import android.view.View;

public class SingleBilling extends MasterActivity {

	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			linearSearchingFrame.setVisibility(View.GONE);	
			linearReadingFrame.setVisibility(View.VISIBLE);
	}
	
	
   public void onClickOneTapBilling(View v) {
	  if(startCommunication()) {
		  asyncTimeout=20000; //20 SECONDS
		  new OneTapReading(METER_CHANNEL,METER_ADDRESS,BILLING).execute();
	  }
	}

	public void onClickOneTapDisconnect(View v) {
	 if(startCommunication()) {
		  asyncTimeout=20000; //20 SECONDS
		  new OneTapReading(METER_CHANNEL,METER_ADDRESS,DISCONNECT_METER).execute();
	  }
	}

	public void onClickOneTapReconnect(View v) {
	 if(startCommunication()) {
		  asyncTimeout=20000; //20 SECONDS
		  new OneTapReading(METER_CHANNEL,METER_ADDRESS,RECONNECT_METER).execute();
	  }
	}
	
}
