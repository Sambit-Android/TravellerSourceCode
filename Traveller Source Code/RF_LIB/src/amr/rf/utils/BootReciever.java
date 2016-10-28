package amr.rf.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			context.startService(new Intent(context,UsbRecieverService.class)); //STARTING USB SERVICE
		} catch (Exception e) {
			AmrMethods.customToast(context, e.getMessage());
		}
	}
}