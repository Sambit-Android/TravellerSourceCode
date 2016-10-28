package bcits.rf.catcher;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import amr.utils.AmrMethods;
import amr.utils.FileManager;
import amr.utils.FrameMaster;
import amr.utils.HelperClass;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bcits.bsmartbilling.R;

public class GroupBilling extends MasterActivity{

	static final String KEY_CHANNEL="KEY_CHANNEL";
	static final String KEY_UUID="KEY_UUID";
	static final String KEY_CONSUMER_NO="KEY_CONSUMER_NO";
	static final String KEY_METER_NO="KEY_METER_NO";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		linearReadingFrame.setVisibility(View.GONE);
		linearSearchingFrame.setVisibility(View.VISIBLE);
	
	}
	
	public void onClickRadioShort(View v) {
		DISCOVERY_DELAY=3000;
	}
	public void onClickRadioNormal(View v) {
		DISCOVERY_DELAY=6000;
	}
	public void onClickRadioLong(View v) {
		DISCOVERY_DELAY=9000;
	}
	
	public void onClickStopDiscovery(View v) {
		btnStopDiscover.setText("Stopping. Please wait...");
		isReadingMeter=false;
	}
	public void onClickMultiBillingRF(View v) {
		 if(startCommunication()) {
			 asyncTimeout=60*1000*5; // 5 MINUTES
			 new DiscoverMeters().execute();
		 }
	}
	
	  private class DiscoverMeters extends AsyncTaskParent{
			TextView txtMeterCount;
			  JSONArray meterList;
				public DiscoverMeters() {
					isReadingMeter=true;  
					connection();
					txtMeterCount=(TextView)findViewById(R.id.txtMeterCount);
				    txtMeterCount.setVisibility(View.GONE);
				    meterList = new JSONArray();
				    btnDiscover.setVisibility(View.GONE);
				    btnStopDiscover.setVisibility(View.VISIBLE);
				    listMeterNumbers.setClickable(false);
				}

				@Override
				protected String doInBackground(String... arg0) {
					publishProgress("Connecting to RF...");
					String response="";
					try {
						publishProgress("Pinging...");
						writeToDevice(FrameMaster.ping());
					    response= parseResponse(PING);
					    publishProgress(PING+" "+response);
						if(SUCCESS.equals(response))
						{ 
							for (int channel = 0; channel <= 9 && isReadingMeter; channel++) { //  LOOPING THE CHANNEL NUMBER FROM 0 TO 9
							publishProgress("Setting channel to "+channel+" ...");
							writeToDevice(FrameMaster.setContext(SOURCE_ADDRESS,channel));
							response= parseResponse(SET_CONTEXT);
							publishProgress(SET_CONTEXT+" "+response);
							if(SUCCESS.equals(response))
							{
								   publishProgress("Searching meters on channel "+channel+" ...");
								   writeToDevice(FrameMaster.discover(SOURCE_ADDRESS));
								   SystemClock.sleep(DISCOVERY_DELAY); // WAIT 5 SECONDS FOR EACH CHANNEL
								   if(null ==receivedData) {
									   continue; // AFTER 5 SECONDS RECEIVED DATA IS NULL MEANS, NO METERS FOUND. CONTINUE WITH NEXT METER CHANNEL
								   }
	 								  byte [] dummyReceivedData;
									  while(isReadingMeter) { 
										  dummyReceivedData=receivedData;
										  SystemClock.sleep(DISCOVERY_DELAY);
										  if (Arrays.equals(receivedData, dummyReceivedData))// CHECKING RECEIVED DATA AND ASSIGNED DATA AFTER 5 SECONDS, IF IT IS SAME MEANS, NO DATA ADDED EXTRA
										  {
											  publishProgress("Collecting first data...");
											  String  dataInHex = HelperClass.bytesToHex(receivedData).replaceAll(UNWANTED_SPACE, "").trim();
												if (null!=dataInHex && !dataInHex.isEmpty())
												{
													 publishProgress("Collecting second data...");
													if (dataInHex.length() > DATA_MIN_LENGTH && dataInHex.contains(MAGIC_NUMBER)) // CHECK MINIMUM LENGTH TO GET THE SIZE OF FRAME
													{
														 publishProgress("Collecting data...");
														if(containFullDiscoveryData(dataInHex)) // IF DATA IS NOT FULL, LOOP CONTINUE ITS WORK
														{
															publishProgress("Meter Found");
												        	meterList = concatArray(meterList,getMeterArray(channel,dataInHex)); // TODO
												        	break;
														}
												}
										  }
									}
								}
							}
						}
							
						}
					} catch (Exception e) {
						FileManager.writeLog("OneTapReading doinback - "+e.getMessage());
						e.printStackTrace();
					}
					return meterList.toString();
				}
				
				private boolean containFullDiscoveryData(String dataInHex) {
					String[] meters=dataInHex.split(MAGIC_NUMBER);
					int totalMeters=0;		
					
					for (int i = 0; i < meters.length; i++) {
						String dataAfterMagicNo =dataInHex.split(MAGIC_NUMBER)[i].trim(); 
						 int sizeFrame=Integer.parseInt(dataAfterMagicNo.substring(0,4), 16); // TAKING SIZE OF THE FRAME FROM THE RESPONSE
						 if(dataAfterMagicNo.length()==(sizeFrame*2)+4)// INCLUDING CRC LENGTH
						   { //ONLY ONE METER
							 totalMeters++;
						   }
						 else if(dataAfterMagicNo.length()==(sizeFrame*2)+4+20)// INCLUDING CRC LENGTH AND FRAME STARTING 20 ZEROS
						   { //MORE THAN ONE METER
							 totalMeters++;
						   } 
						 }
					
					if(meters.length==(totalMeters+1)) { // +1 is the first value before the magic number including the all the frames. ie 20 zeros
						return true; // ALL METER DATA RECEIVED FULLY
					}
						return false; // ALL METER DATA NOT RECEIVED FULLY
					}
				
				private JSONArray getMeterArray(int channel,String dataInHex) {
					JSONArray array = new JSONArray();
					try {
						
						String[] meters=dataInHex.split(MAGIC_NUMBER);
						
						for (int i = 0; i < meters.length; i++) {
							String dataAfterMagicNo =dataInHex.split(MAGIC_NUMBER)[i].trim(); 
							 int sizeFrame=Integer.parseInt(dataAfterMagicNo.substring(0,4), 16); // TAKING SIZE OF THE FRAME FROM THE RESPONSE
							 if(dataAfterMagicNo.length()==(sizeFrame*2)+4 || dataAfterMagicNo.length()==(sizeFrame*2)+4+20)// INCLUDING CRC LENGTH
							   { //ONLY ONE METER
								 String uuid=dataAfterMagicNo.substring(10, 18);
								 String consumerNo="",meterNo="";
								 boolean isMeter=false;
								 if (uuid.equals("0000BDBB")) { // TODO TAKE DATA FROM THE SQLITE FROM HERE
									 consumerNo="3265897345";
									 meterNo="L2312312";
									 isMeter=true;
								 }else if(uuid.equals("0000B9A4")) {
									 consumerNo="8454389745";
									 meterNo="C1032937";
									 isMeter=true;
								} 
								 
								 if(isMeter) {
								 JSONObject obj = new JSONObject();
									obj.put(KEY_CHANNEL, channel);
									obj.put(KEY_UUID,uuid);
									obj.put(KEY_CONSUMER_NO,consumerNo);
									obj.put(KEY_METER_NO,meterNo);
									array.put(obj);
							   }
							   }
							 }
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return array;
				}

				@Override
				protected void onProgressUpdate(String... response) {
					super.onProgressUpdate(response);
						 txtMeterCount.setVisibility(View.VISIBLE);
						 txtMeterCount.setText(String.valueOf(meterList.length()));
						 JSONAdapter adapter =new JSONAdapter(GroupBilling.this, meterList);
						 listMeterNumbers . setAdapter(adapter);
				}
				
				@Override
				protected void onPostExecute(String result) { 
					super.onPostExecute(result);
					listMeterNumbers.setClickable(true);
					btnDiscover.setVisibility(View.VISIBLE);
					btnStopDiscover.setVisibility(View.GONE);
					btnStopDiscover.setText(STOP_SEARCHING);
					isReadingMeter=false;
					txtMeterCount.setVisibility(View.GONE);
					txtProgressInfo.setText("Searching Finished");
				}
				
			}
			
			private JSONArray concatArray(JSONArray arr1, JSONArray arr2)  throws JSONException {
			    JSONArray result = new JSONArray();
			    for (int i = 0; i < arr1.length(); i++) {
			        result.put(arr1.get(i));
			    }
			    for (int i = 0; i < arr2.length(); i++) {
			        result.put(arr2.get(i));
			    }
			    return result;
			}
			
	
	public  class JSONAdapter extends BaseAdapter implements ListAdapter {
		
	    private final Activity activity;
	    private final JSONArray jsonArray;
	    JSONAdapter (Activity activity, JSONArray jsonArray) {
	        assert activity != null;
	        assert jsonArray != null;

	        this.jsonArray = jsonArray;
	        this.activity = activity;
	    }


	    @Override public int getCount() {
	        if(null==jsonArray) 
	         return 0;
	        else
	        return jsonArray.length();
	    }

	    @Override public JSONObject getItem(int position) {
	         if(null==jsonArray) return null;
	         else
	           return jsonArray.optJSONObject(position);
	    }

	    @Override public long getItemId(int position) {
	        JSONObject jsonObject = getItem(position);

	        return jsonObject.optLong("id");
	    }

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null)
		convertView = activity.getLayoutInflater().inflate(	R.layout.custom_list_mters, null);
		TextView txtUUIDCustom = (TextView) convertView	.findViewById(R.id.txtUUIDCustom);
		TextView txtMeterNoCustom=(TextView)convertView.findViewById(R.id.txtMeterNoCustom);
		TextView txtRRNoCustom=(TextView)convertView.findViewById(R.id.txtRRNoCustom);
		Button btnDisconnectCustom=(Button)convertView.findViewById(R.id.btnDisconnectCustom);
		Button btnReconnectCustom=(Button)convertView.findViewById(R.id.btnReconnectCustom);
		Button btnReadCustom=(Button)convertView.findViewById(R.id.btnReadCustom);

		 String uuid="",consumerNo="",meterNo="";
		 int channel=1;
		try {
			JSONObject json_data = getItem(position);
			if (null != json_data) { 
				 uuid = json_data.getString(KEY_UUID);
				 channel = Integer.parseInt(json_data.getString(KEY_CHANNEL));
				 consumerNo=json_data.getString(KEY_CONSUMER_NO);
				 meterNo=json_data.getString(KEY_METER_NO);
				 txtUUIDCustom.setText(channel+" / "+uuid);
				 txtMeterNoCustom.setText(meterNo);
				 txtRRNoCustom.setText(consumerNo);
			}
			
			btnReadCustom.setOnClickListener(new OnClickListener() {
				 
				@Override
				public void onClick(View arg0) {
					readMeter(position, BILLING);
				}
			});
			
			btnDisconnectCustom.setOnClickListener(new OnClickListener() {
				 
				@Override
				public void onClick(View arg0) {
					readMeter(position, DISCONNECT_METER);
				}
			});
			
			btnReconnectCustom.setOnClickListener(new OnClickListener() {
				 
				@Override
				public void onClick(View arg0) {
					readMeter(position, RECONNECT_METER);
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}
	private void readMeter(int position,String readingType) {
		try {
			if(!isReadingMeter) {
			if(startCommunication()) {
				JSONObject json_data = getItem(position);
				if (null != json_data) 
				  asyncTimeout=20000; //20 SECONDS
				  new OneTapReading(Integer.parseInt(json_data.getString(KEY_CHANNEL)),json_data.getString(KEY_UUID),readingType).execute();
			  }
			}else {
				AmrMethods.customToast(context, "STOP READING AND TRY AGAIN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	}
	
	
}
