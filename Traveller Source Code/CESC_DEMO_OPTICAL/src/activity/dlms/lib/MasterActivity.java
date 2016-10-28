package activity.dlms.lib;

import gurux.dlms.client.sampleclient;
import amr.utils.AmrMethods;
import amr.utils.UsbHelper;
import amr.utils.UsbRecieverService;
import amr.utils.Utils;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bcits.bsmartbilling.R;
import com.physicaloid.lib.Physicaloid;

public class MasterActivity extends Activity{
	public static Physicaloid mSerial;
	sampleclient sampleClient;
	UsbHelper usbHelper;  
	Handler mHandler = new Handler();
	public static boolean mRunningMainLoop = false;
	public static boolean isConnected = false;
	TextView txtProgressInfo,txtKWH,txtMeterName;
	LinearLayout linearMeterDetails;
	public static Context context;
	IntentFilter filter = new IntentFilter();
	Button btnReadMeter;
	ProgressBar progressBar;
	public static String meterName,meterPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlms_main);
		btnReadMeter=(Button)findViewById(R.id.btnReadMeter);
		txtMeterName=(TextView)findViewById(R.id.txtMeterName);
		txtProgressInfo=(TextView)findViewById(R.id.txtProgressInfo);
		txtKWH=(TextView)findViewById(R.id.txtKWH);
		linearMeterDetails=(LinearLayout)findViewById(R.id.linearMeterDetails);
		progressBar =(ProgressBar)findViewById(R.id.progressBar);
		btnReadMeter.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);
		context=this; //MAIN CONTEXT
		mSerial = new Physicaloid(this); //INITIATE USB LIB
		usbHelper= new UsbHelper(MasterActivity.this, mSerial); //INITIATE USB HELPER CLASS 
		sampleClient=new sampleclient(); // INITIATE GURUX LIB MAIN CLASS
		
        initiateLogCat(); //START LOG
		
		AmrMethods amr = new AmrMethods();
		
		amr.listAssetFiles(MasterActivity.this, "Manufacturer_Settings"); //COPY MANUFACTURER SETTINGS TO PHONE
		filter.addAction(UsbRecieverService.USB_ENABLED_INTENT);  //INTENT FOR USB DETECTION
		registerReceiver(updateUIReciver,filter);                 //REGISTER USB RECEIVER
		
		resetUI();
		onClickSelectMeter(0);//SETTING DEFAULT METER NAME
		new ReadManufacturer().execute();
	}
	

	private void initiateLogCat() {//LOGCAT CREATOR
		if(Utils.getDefaults("logCate", MasterActivity.this)!=null){
			Utils.setDefaults("logCate", Utils.getDefaults("logCate", MasterActivity.this)+"\n=====NEW LAUNCH=====\n", MasterActivity.this);
		}
		else{
		Utils.setDefaults("logCate", " == ", MasterActivity.this);
		}
	}
	public abstract class AsyncTaskParent extends  AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
			btnReadMeter.setVisibility(View.GONE);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			btnReadMeter.setVisibility(View.VISIBLE);
			onResume();//CHECKING FOR THE USB STATUS
		}
	}
	
	BroadcastReceiver updateUIReciver = new BroadcastReceiver() { //THIS CUSTOM BROADCAST USED FOR DETECTING USB FROM SERVICE
        @Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getBooleanExtra("USB_CONNECTED", false)) {
				uiUsbConnected();
			} else 
			{
				uiUsbNotConnected();
			}
		}
    };
    
    @Override
    protected void onResume() {
		super.onResume();
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_HOST))
		{
			if(UsbRecieverService.usbConnected) {
				uiUsbConnected();
			}else {
				uiUsbNotConnected();
			}
		}else {
			txtProgressInfo.setText("THIS PHONE IS NOT SUPPORTED");
		}
	
    };
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(updateUIReciver);
	};
	
	public void uiUsbConnected() {
		btnReadMeter.setVisibility(View.VISIBLE);
		txtProgressInfo.setText("USB CONNECTED");
		txtProgressInfo.setCompoundDrawablesWithIntrinsicBounds( android.R.drawable.presence_online, 0, 0, 0);
		txtProgressInfo.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
	}
	public void uiUsbNotConnected() {
		btnReadMeter.setVisibility(View.GONE);
		txtProgressInfo.setText("USB NOT CONNECTED");
		txtProgressInfo.setCompoundDrawablesWithIntrinsicBounds( android.R.drawable.presence_busy, 0, 0, 0);
		txtProgressInfo.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
		isConnected = false;
		usbHelper.closeUsbSerial();
	}
	
	public void resetUI() {
		btnReadMeter.setVisibility(View.GONE);
		linearMeterDetails.setVisibility(View.GONE);
		txtProgressInfo.setVisibility(View.VISIBLE);
	}

	private class ReadManufacturer extends AsyncTaskParent {
		@Override
		protected Void doInBackground(Void... params) {
			sampleClient.readManufacturer();
			return null;
		}
	}

	
	int meterPosition=0;
	private void onClickSelectMeter(int position)
      {
		meterPosition=meterPosition+position; 
		switch (meterPosition) {
		case 0:
			meterName="SECURE";
			meterPassword="ABCD0001";
			break;
		case 1:
			meterName="GENUS";
			meterPassword="1A2B3C4D";
			break;
		case 2:
			meterName="L&T";
			meterPassword="LNT1";
			break; 
		default:
		    if(-1==position) meterPosition=0; else meterPosition=position;
			break;
		}
		
		txtMeterName.setText(meterName);
	}
	public void onClickMeterPrev(View v) {
		onClickSelectMeter(-1);
	}
	public void onClickMeterNext(View v) {
		onClickSelectMeter(+1);
	}
	public void btnResetLogcat(View v) {
		 Utils.setDefaults("logCate", "", MasterActivity.this);
		 AmrMethods.customToast(context,"Log Reset");
	}
	
	public void btnSeeLogcat(View v) {
		AmrMethods.customAlert(context, "LOG", Utils.getDefaults("logCate", MasterActivity.this));
	}
	
	public void onClickSendLogFile(View v) {
		 AmrMethods.customToast(context, "Not Activiated");
	}
	@Override
	public void onBackPressed() {
		usbHelper.closeUsbSerial();
		this.finish();
	}
}
