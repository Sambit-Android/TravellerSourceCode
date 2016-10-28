package amr.rf.catcher;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import amr.rf.utils.AmrMethods;
import amr.rf.utils.DbMeterDetails;
import amr.rf.utils.FileManager;
import amr.rf.utils.FrameMaster;
import amr.rf.utils.HelperClass;
import amr.rf.utils.TimeOutManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import bcits.rf.catcher.R;

public class GroupBilling extends MasterActivity{

	static final String KEY_CHANNEL="KEY_CHANNEL";
	static final String KEY_UUID="KEY_UUID";
	static final String KEY_CONSUMER_NO="KEY_CONSUMER_NO";
	public static final String KEY_METER_NO="KEY_METER_NO";
	static final String KEY_KWH="KEY_KWH";
	boolean isReadingActive=false; // FOR LOOPING THE CHANNELS
	boolean isAsyncRunning; // FOR PREVENTING BILLING BEFORE STOPING THE ASYNC TASK
	static final int  startChannel=1;
	static final int endChannel=2;
	
	public void onClickConfigerMeter(View v) { 
		Toast.makeText(getApplicationContext(), "Configure meters", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(context , DbManagerActivity.class));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		linearReadingFrame.setVisibility(View.GONE);
		linearSearchingFrame.setVisibility(View.VISIBLE);
//		MasterActivity.isReadingMeter=false;
		try { // TAKING SAVED METER DATA
			String savedData= AmrMethods.getDefaults(AmrMethods.KEY_SEARCHED_DATA, context);
	         if(null!=savedData) {
	        	 JSONArray meterList= new JSONArray(savedData);
	        	 JSONAdapter adapter =new JSONAdapter(GroupBilling.this, meterList);
				 listMeterNumbers . setAdapter(adapter);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public void onClickRadioShort(View v) {
		DISCOVERY_DELAY=5000;
	}
	public void onClickRadioNormal(View v) {
		DISCOVERY_DELAY=7500;
	}
	public void onClickRadioLong(View v) {
		DISCOVERY_DELAY=10000;
	}
	
	public void onClickStopDiscovery(View v) {
		btnStopDiscover.setText("Stopping. Please wait...");
		isReadingMeter=false;
		isReadingActive=false; 
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
					isReadingActive=true;
					isAsyncRunning=true;
					connection();
					txtMeterCount=(TextView)findViewById(R.id.txtMeterCount);
				    txtMeterCount.setVisibility(View.GONE);
				    meterList = new JSONArray();
				    btnDiscover.setVisibility(View.GONE);
				    btnStopDiscover.setVisibility(View.VISIBLE);
				    listMeterNumbers.setClickable(false);
				    linearSearchingFrameMode.setVisibility(View.GONE);
				}

				@Override
				protected String doInBackground(String... arg0) {
					publishProgress("Connecting to RF...");
					String response="";
					try {
						publishProgress("Pinging...");
						writeToDevice(FrameMaster.ping());
					    response= parseResponse(PING,false);
					    publishProgress(PING+" "+response);
						if(SUCCESS.equals(response))
						{
							for (int channel = startChannel; channel <= endChannel && isReadingActive; channel++) { //  LOOPING THE CHANNEL NUMBER FROM 0 TO 9
							publishProgress("Setting channel to "+channel+" ...");
							SystemClock.sleep(50);
							writeToDevice(FrameMaster.setContext(SOURCE_ADDRESS,channel));
							response = parseResponse(SET_CONTEXT,false);
							publishProgress(SET_CONTEXT+" "+response);
							if(SUCCESS.equals(response))
							{
								   publishProgress("Searching meters on channel "+channel+" ...");
								   writeToDevice(FrameMaster.discover(SOURCE_ADDRESS));
								   SystemClock.sleep(DISCOVERY_DELAY); // WAIT DISCOVERY_DELAY SECONDS FOR EACH CHANNEL
								   if(null ==receivedData) {
									   continue; // AFTER DISCOVERY_DELAY SECONDS RECEIVED DATA IS NULL MEANS, NO METERS FOUND. CONTINUE WITH NEXT METER CHANNEL
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
												        	getMeterArray(channel,dataInHex); // TODO
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
				
				private void getMeterArray(int channel,String dataInHex) {
					try {
						 
						  String[] meters=dataInHex.split(MAGIC_NUMBER);
						  
						for (int i = 0; i < meters.length; i++) {
							String dataAfterMagicNo =dataInHex.split(MAGIC_NUMBER)[i].trim(); 
							 int sizeFrame=Integer.parseInt(dataAfterMagicNo.substring(0,4), 16); // TAKING SIZE OF THE FRAME FROM THE RESPONSE
							 if(dataAfterMagicNo.length()==(sizeFrame*2)+4 || dataAfterMagicNo.length()==(sizeFrame*2)+4+20)// INCLUDING CRC LENGTH
							   { //ONLY ONE METER
								 String uuid=dataAfterMagicNo.substring(10, 18);
								 String meterNo=HelperClass.hexStringToString(dataAfterMagicNo.substring(48, 64));
								 String kWh="NA";
								 if(meterNo.equals("GSV00002")) {
										meterNo="C1032937";
									}
								 if(meterNo.equals("22222222")) { //TODO
										meterNo="62007364";
									}
								 if(meterNo.equals("33444444")) { //TODO
										meterNo="50135072";
									}
								  
								 if(meterNo.equals("88888888")) { //TODO
										meterNo="50431874";
									}
								 
								 DbMeterDetails db = new DbMeterDetails(getBaseContext());
								 db.open();
								 String consumerNo= db.getConsumerNo(uuid);
								 db.close();
								 System.err.println(" CONSUMER NUMBER +++++++  "+consumerNo);
								 
								 if(null!=consumerNo) {
									 System.err.println(" BEFORE READING KWH +++++++  "+consumerNo);
								    	 kWh=readKwh(uuid); 
								    	 System.err.println(" AFTER READING KWH +++++++  "+consumerNo+"  KWH= "+kWh);	 
										JSONObject obj = new JSONObject();
										obj.put(KEY_CHANNEL, channel);
										obj.put(KEY_UUID,uuid);
										obj.put(KEY_CONSUMER_NO,consumerNo);
										obj.put(KEY_METER_NO,meterNo);
										obj.put(KEY_KWH,kWh);
										meterList.put(obj);
									    publishProgress("Meter Found");
								 }
							   }
							 }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				private  String readKwh(String uuid) { // TODO GET KWH
					TimeOutManager timeOut= new TimeOutManager(7000);// SET TIMEOUT FOR KWH READING
					timeOut.start();
					
                    String response="",responseOpenChannel=";"; 
                    String kWh="NA";
					writeToDevice(FrameMaster.openChannel(SOURCE_ADDRESS,uuid));
					responseOpenChannel=response= parseResponse(OPEN_CHANNEL,false);
					publishProgress(OPEN_CHANNEL+" "+response);
					System.out.println(OPEN_CHANNEL+" "+response);
					
					if(response.contains("FRAME ALREADY OPENED"))
					{
						writeToDevice(FrameMaster.closeChannel(SOURCE_ADDRESS,uuid));
						response= parseResponse(CLOSE_CHANNEL,false);
						publishProgress(CLOSE_CHANNEL+" "+response);
						
						writeToDevice(FrameMaster.openChannel(SOURCE_ADDRESS,uuid));
						response= parseResponse(OPEN_CHANNEL,false);
						publishProgress(OPEN_CHANNEL+" "+response);
						System.out.println(OPEN_CHANNEL+" "+response);
					}
					
					if(SUCCESS.equals(response))
					{
						writeToDevice(FrameMaster.readInstantaneous(SOURCE_ADDRESS,uuid)); // READING 
						
						try {
							response= parseResponse(BILLING,true);
							kWh=response.split(SPLITTER)[0];
							response=response.split(SPLITTER)[1];
						} catch (Exception e) {
							e.printStackTrace();
						}
						    publishProgress(BILLING+" "+response);
					}
					
//					MasterActivity.isReadingMeter=true;// IN ANY CASE IF IT GONE TO FALSE,MEKE IT TRUE (MAINLY TIMEOUT)
					
					if(SUCCESS.equals(responseOpenChannel)) {// IF CHANNEL IS OPENED,CLOSE IT
						writeToDevice(FrameMaster.closeChannel(SOURCE_ADDRESS,uuid));
						response= parseResponse(CLOSE_CHANNEL,false);
						publishProgress(CLOSE_CHANNEL+" "+response);	
					}
					
						if(!timeOut.isInterrupted()) {
							timeOut.interrupt();// INTERRUPT TIMEOUT THREAD
						}
						timeOut=null;
					return kWh;
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
					AmrMethods.setDefaults(AmrMethods.KEY_SEARCHED_DATA, meterList.toString(), context); // SAVING JSONARRAY FOR RE-USE
					
					linearSearchingFrameMode.setVisibility(View.VISIBLE);
					listMeterNumbers.setClickable(true);
					btnDiscover.setVisibility(View.VISIBLE);
					btnStopDiscover.setVisibility(View.GONE);
					btnStopDiscover.setText(STOP_SEARCHING);
					isReadingMeter=false;
					isReadingActive=false;
					txtMeterCount.setVisibility(View.GONE);
					txtProgressInfo.setText("Searching Finished");
					isAsyncRunning=false;
				}
				
			}
			
		/*	private JSONArray concatArray(JSONArray arr1, JSONArray arr2)  throws JSONException {
			    JSONArray result = new JSONArray();
			    for (int i = 0; i < arr1.length(); i++) {
			        result.put(arr1.get(i));
			    }
			    for (int i = 0; i < arr2.length(); i++) {
			        result.put(arr2.get(i));
			    }
			    return result;
			}*/
			
	
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
		TextView txtKWhCustom=(TextView)convertView.findViewById(R.id.txtKWhCustom);
		TextView txtRemoveRR=(TextView)convertView.findViewById(R.id.txtRemoveRR);
		LinearLayout btnReadCustom=(LinearLayout)convertView.findViewById(R.id.btnReadCustom);

		 String uuid="",consumerNo="",meterNo="",kWh="";
		 int channel=1;
		try {
			JSONObject json_data = getItem(position);
			if (null != json_data) { 
				 uuid = json_data.getString(KEY_UUID);
				 channel = Integer.parseInt(json_data.getString(KEY_CHANNEL));
				 consumerNo=json_data.getString(KEY_CONSUMER_NO);
				 meterNo=json_data.getString(KEY_METER_NO);
				 kWh=json_data.getString(KEY_KWH);
				 txtUUIDCustom.setText(channel+" / "+uuid);
				 txtMeterNoCustom.setText(meterNo);
				 txtRRNoCustom.setText(consumerNo);
				 if(kWh.contains("FAILED") || kWh.equals("NA")) {
					 kWh="NA";
					 btnReadCustom.setEnabled(false);
				 }
				 txtKWhCustom.setText(kWh);
			}
			txtRemoveRR.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					try {
						if(!isAsyncRunning) {
							JSONObject json = getItem(position);
							if(RfLibMain.removeMeterNoFromTemp(context, json.getString(KEY_METER_NO)))
							{
							String savedData=AmrMethods.getDefaults(AmrMethods.KEY_SEARCHED_DATA, context);
							JSONArray array = new JSONArray(savedData);								
							JSONAdapter adapter =new JSONAdapter(GroupBilling.this, array);
							listMeterNumbers . setAdapter(adapter);
							}
						}else {
							AmrMethods.customToast(context, "STOP READING AND TRY AGAIN");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
			});
			
			btnReadCustom.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(!isAsyncRunning) {
		        		DecimalFormat format = new DecimalFormat("0");
		        		try {
		        			JSONObject json = getItem(position);
		        			
			        		RfLibMain.setMeterReading(String.format(format.format(Double.parseDouble(json.getString(KEY_KWH)))));
			        		RfLibMain.setMeterNumber(json.getString(KEY_METER_NO));
			        		startActivity(new Intent(GroupBilling.this, RfLibMain.activityOut));
			        		GroupBilling.this.finish();
						} catch (Exception e) {
							AmrMethods.customToast(context, "Libray not connected with the billing app/ \n"+e.getMessage());
						}
		    	    
					}else {
						AmrMethods.customToast(context, "STOP READING AND TRY AGAIN");
					}
					}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}
	private void readMeter(int position,String readingType) {
		try {
			if(!isAsyncRunning) {
			if(startCommunication()) {
				JSONObject json_data = getItem(position);
				if (null != json_data) 
				  asyncTimeout=1000*60*60; //1 HOUR
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
