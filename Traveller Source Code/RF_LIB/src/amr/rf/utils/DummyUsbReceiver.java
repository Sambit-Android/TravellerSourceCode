package amr.rf.utils;

import android.app.Activity;
import android.os.Bundle;

public class DummyUsbReceiver extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
//			Toast.makeText(getApplicationContext(), "USB REGISTERED", Toast.LENGTH_SHORT).show();
			this.finish();
		} catch (Exception e) {
			FileManager.writeLog("DummyUsbReceiver "+e.getMessage());
		}
		
	}
}
