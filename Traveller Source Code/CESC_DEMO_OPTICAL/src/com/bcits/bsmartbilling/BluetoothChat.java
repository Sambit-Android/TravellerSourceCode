
package com.bcits.bsmartbilling;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Utils.Logger;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.DatabaseHandler;




/*******************DO NOT delete any line (both uncommented and commented lines)********************************/

public class BluetoothChat extends Activity {

	Dialog  dialog ;
	public String billName = " " ;

	// Debugging
	static Logger logger ;
	private static final String TAG = "BluetoothChat";
	private static final boolean D = true;
	public static int bill;
	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;
	DatabaseHandler databasehandler;

	// Layout Views
	private TextView mTitle;
	//	private ListView mConversationView;
	//	private EditText mOutEditText;
	private Button mSendButton , button_menu1 , bluetoothbckpress1;
	String sqlit_rrno,sqlit_unbilled,sqlit_billed,notupdatedcount,updtedcount;

	// Name of the connected device
	private String mConnectedDeviceName = null;
	// Array adapter for the conversation thread
	private ArrayAdapter<String> mConversationArrayAdapter;
	// String buffer for outgoing messages
	private StringBuffer mOutStringBuffer;
	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothChatService mChatService = null;
	String backbuttonenable="YES";

	/** bill no *************/
	//static int bill_series = 10000;
	//static int print_series = 0;


	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if (D)
			Log.e(TAG, "+++ ON CREATE +++");

		// Set up the window layout
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.bluetooth);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);

		// Set up the custom title
		mTitle = (TextView) findViewById(R.id.title_left_text);
		mTitle.setText(R.string.app_name);
		mTitle = (TextView) findViewById(R.id.title_right_text);
		bluetoothbckpress1 =  (Button) findViewById(R.id.bluetoothbckpress);
		button_menu1 =  (Button) findViewById(R.id.button_menu);
		registerForContextMenu(button_menu1);

		try {
			if(logger == null){
				logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
			}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			logger.error("logger is NULL");
		}
		logger.info("In Side onCreate()");
		mSendButton = (Button) findViewById(R.id.button_print);


		//mSendButton.setClickable(true);
		mSendButton.setVisibility(View.GONE);
		backbuttonenable="YES";
		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		try{

			// If the adapter is null, then Bluetooth is not supported
			if (mBluetoothAdapter == null) 
			{
				System.out.println("check****************");
				Toast.makeText(this, "Bluetooth is not available",Toast.LENGTH_LONG).show();
				finish();
				return;
			}

			SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
			String deviceID = "000";
			//System.out.println("SITE CODE   : "+LoginActivity.sitecode1);
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();

			//imeino = "352384056737168";
			System.out.println("imeino : "+imeino);
			System.out.println("3 DIGITE CODE    : "+Generate3digitalphanumericcode.generateAuthCode(imeino));
			deviceID = Generate3digitalphanumericcode.generateAuthCode(imeino);
			if (UtilMaster. mbilledstatus.equalsIgnoreCase("0"))
			{
				billName = " " ;
				try {
					DatabaseHandler dbhelper = new DatabaseHandler(getApplicationContext());
					dbhelper.openToRead();
					String billno = dbhelper.getNextbillnolocal();
					dbhelper.close();
					//Consumer.LBillno = LoginActivity.sitecode1+deviceID+(bill);
					
					try {
						
						UtilMaster. mbillno = String.valueOf(Integer.parseInt(billno) + 1) ;
						
					} catch (Exception e) {
						// TODO: handle exception
						
						UtilMaster. mbillno = String.valueOf(Long.parseLong(billno) + 1) ;
						
					}
					
					
					
					
					String versionName = null;
					versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
					UtilMaster. mdevicefirmwareversion  = versionName;

					UtilMaster.mbilldate = new SimpleDateFormat("dd/MM/yyyy").format(MasterLibraryFunction.getMobileDate(new SimpleDateFormat("MM/dd/yyyy").parse(UtilMaster.mreading_date)));
					MasterLibraryFunction masterLibraryFunction = new MasterLibraryFunction();
					masterLibraryFunction. setBill_Due_date(14 , new SimpleDateFormat("MM/dd/yyyy").parse(UtilMaster.mreading_date) ) ;



				} catch (NameNotFoundException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				billName = "(DUPLICATE BILL)";
			}
		}
		catch (Exception e) {
			logger.error(Log.getStackTraceString(e));
			new AlertDialog.Builder(BluetoothChat.this)
			.setTitle("Info")
			.setMessage("Consumer details is not correct or unable to bill , Please try again later ")
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Intent nfData_intnt = new Intent(BluetoothChat.this,MainActivity.class);
					startActivity(nfData_intnt);

				}
			}).show();
		}

	}

	public void moveactivity()
	{
		System.out.println("move activity method");
		Intent int_print = new Intent(BluetoothChat.this,PrintBillAction.class);
		startActivity(int_print);
	}

	@Override
	public void onStart() 
	{
		super.onStart();

		if( (UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase("") ){
			logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(BluetoothChat.this)
			.setTitle("Sorry..!")
			.setMessage("Session timeout ..! Please Login again")
			.setPositiveButton("OK", new DialogInterface.OnClickListener()
			{

				public void onClick(DialogInterface dialog,int which) 
				{

					startActivity(new Intent(BluetoothChat.this,Login.class));
					//   return;


				}
			}).show();
		}


		if (D)
			logger.info(TAG, "++ ON START ++");
		// If BT is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) 
		{
			System.out.println("***********mBluetoothAdapter.enable();*********");
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);

		} else
		{
			if (mChatService == null)
				setupChat();
		}

	}

	@Override
	public synchronized void onResume() 
	{
		super.onResume();
		if (D)
			logger.info(TAG, "+ ON RESUME +");
		if (mChatService != null) 
		{
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) 
			{
				// Start the Bluetooth chat services
				mChatService.start();
			}
		}
	}

	private void setupChat() 
	{
		Log.d(TAG, "setupChat()");


		mSendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//	mSendButton.setClickable(false);
				mSendButton.setVisibility(View.GONE);
				// int max=8,m=0;
				System.out.println("Consumer.LSitecode  "+UtilMaster.mGLogin_SiteCode);
				// String [] labels =new String[]{(String) Consumer.LSitecode.subSequence(0, 2),Consumer.LBillno,Consumer.getlBilldate(),Consumer.getLDuedate(),"",(String) Consumer.LSitecode.subSequence(0, 3)};
				//System.out.println(labels[0]);
				byte[] heading1,heading2,heading3,division,divisioname,subdivision,subdivisioname,u_line,u_space,billnolabel,issuedate,duedate,billno,
				consumerlabel,consumerid,consumeraddlabel,consumername,address,address1,billfromlabel,billtolabel,billfrom,billto,connecdatelabel,
				conndate,sacnload,phase,poleno,tariff,minchargeslabel,mincharges,linemin,meterno,avguintslabel,avgunits,foliolabel,foliono,readinglabel,
				currentrdng,previousrdng,mfunits,meterstatuslabel,meterstatus,meterrent,energychargelabel,ec,duty,fc,fppcalabel,fppca,cap,arears,
				sundrylabel,sundry,credit,misc,netamountlabel,netamount,departlabel,departlabel1,divisionlabel,divisiondep,subdivlabel,subdiv,
				receiptnolabel,receiptno,datelabel,date,billisslabel,billissuedate,duedatelabel,duedatedep,amountreceivedlabel,counternamelabel;



				///////////// UNUSED ////////////////////////
				subdivisioname="".getBytes();
				readinglabel = "".getBytes();
				//String divspace = " ";
				divisioname= "".getBytes();
				departlabel = null ;
				departlabel1 = null ;
				divisionlabel = null ;
				divisiondep = null ;
				subdivlabel = null ;
				subdiv = null ;
				receiptnolabel = null ;
				receiptno = null ;
				datelabel = null ;
				date = null ;
				billisslabel = null ;
				billissuedate = null ;duedatelabel = null ;duedatedep = null ;amountreceivedlabel = null ;
				counternamelabel =null ;
				billfromlabel = null ;billfrom = null ;billtolabel = null ; 
				meterrent= null ; 
				energychargelabel = null ; 
				foliolabel= null ; 
				foliono= null ; 
				netamountlabel = null ; 
				billnolabel = null ; 
				connecdatelabel= null ; 
				conndate= null ; 

				meterstatuslabel = null ; 



				consumerid = null;
				/////////////////////////END ////////////////////////////////	










				u_line = "\n ______________________________".getBytes();
				division ="\n DIVISION: ".getBytes();
				u_space="\n            ".getBytes();


				//old heading1 = "\n   CHAMUNDESHWARI ELECTRICITY SUPPLY".getBytes();
				/*subDivisionName*/
				heading1 = (UtilMaster.mdivision.trim()).getBytes();

				//old	heading2 = "\n      CORPORATION Ltd. MYSORE".getBytes();
				/*subDivisionName 2nd*/
				heading2 = (UtilMaster.mGLogin_SiteCode.trim()).getBytes();     


				//old heading3 = "\n           ELECTRICITY BILL".getBytes();
				/*CONSUMER_SC_NO */
				heading3 = UtilMaster.mconsumer_sc_no.trim().getBytes();







				/**************** heading    **************/ 




				//	(addPreSpace(validateStrLength(consumer.Lfname ,41), 42)).getBytes() ;




				//////////////////NEW CODE FOR ALIGNMENT ///////////////////////
				/**************8 Consumer Name ******        upto 32 Chars            */ 
				int labLength = 42 ;
				int threeSlabLength = 16 ;

				consumeraddlabel = "\n CONSUMER NAME AND ADDRESS: ".getBytes();
				UtilMaster.mconsumer_name = UtilMaster.mconsumer_name.trim();

				/*if (UtilMaster.mconsumer_name.length() >= (labLength+1)) {
					UtilMaster.mconsumer_name = UtilMaster.mconsumer_name.substring(0, labLength);
				} 

				UtilMaster.maddress = UtilMaster.maddress.trim();

				if (UtilMaster.maddress.length() >= (labLength+1)) {
					UtilMaster.maddress = UtilMaster.maddress.substring(0, labLength);
				} 

				UtilMaster.maddress1 = UtilMaster.maddress1.trim();

				if (UtilMaster.maddress1.length() >= (labLength+1)) {
					UtilMaster.maddress1 = UtilMaster.maddress1.substring(0, labLength);
				} */

				/*NAME , ADDRESS1 , ADDRESS2 */
				/*	consumername = (addPreSpace(validateStrLength(UtilMaster.mconsumer_name,labLength), (labLength+1))).getBytes();
				address = validateStrLength(UtilMaster.maddress,labLength).getBytes();
				address1 = validateStrLength(UtilMaster.maddress1,labLength).getBytes();*/

				//by shivanad


				consumername = getTrimmedStringto20Chars(UtilMaster.mconsumer_name.trim()).getBytes();
				address = getTrimmedStringto20Chars(UtilMaster.maddress.trim()).getBytes();
				address1 = getTrimmedStringto20Chars(UtilMaster.maddress1.trim()).getBytes();











				//  ledger_no/folio_no
				poleno =( addPostSpace( UtilMaster. mledger_no.trim()+"/"+UtilMaster.mfolio_no.trim() , (threeSlabLength)) ).getBytes();

				// IMEI NO AND MR CODE 
				TelephonyManager tm1 = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String imeino1 = tm1.getDeviceId();
				subdivision =(imeino1+" -"+UtilMaster.mGLogin_MRCode).getBytes();




				/*BILL NO */
				String billNo = UtilMaster. mbillno.trim() ;
				billno =(addPostSpace( billNo ,threeSlabLength)).getBytes();



				/* *ISSUE DATE AND DUE DATE  ******                    */ 

				issuedate = (addPostSpace(UtilMaster.mbilldate.trim(),threeSlabLength)).getBytes();
				duedate = (UtilMaster.getMduedate().trim()).getBytes();

				/*TARIFF CODE */
				tariff =(addPostSpace(UtilMaster.mtariffdesc.trim(),threeSlabLength)).getBytes();

				/*LAOD */
				sacnload = (addPostSpace(UtilMaster. mconnected_load.trim(),threeSlabLength)).getBytes();


				/*METER NO */

				try{
					if(UtilMaster. mmeterno.trim().toUpperCase().equalsIgnoreCase("NULL") || UtilMaster. mmeterno.trim().equalsIgnoreCase(null) || UtilMaster. mmeterno.trim().equalsIgnoreCase("0"))
					{
						UtilMaster. mmeterno = "0";
					}
				}catch(Exception exception){
					UtilMaster. mmeterno = "0";
				}

				meterno = UtilMaster. mmeterno.trim().getBytes();




				/****************READING  >>>**********************/

				/*PRESENT READING */
				String presentrdng = UtilMaster.mpresentreading.trim();
				/*currentrdng  = (addPostSpace(presentrdng,threeSlabLength)).getBytes();*/
				currentrdng = presentrdng.trim().getBytes();

				String prevMtrStatus =  getMeterStatus (UtilMaster.getMpreviousreadingstatus().trim()) ;
				if(prevMtrStatus.equalsIgnoreCase("NORMAL"))prevMtrStatus = "NRM" ;
				else if(prevMtrStatus.equalsIgnoreCase("DISSCONN"))prevMtrStatus = "DIS" ;

				/*PREVIOUS READING */
				previousrdng = (addPostSpace(UtilMaster.getMprevious_reading().trim()+"-"+prevMtrStatus,threeSlabLength)).getBytes();

				/*METER_CONSTANT*/
				String mincharg = UtilMaster. mmeter_constant.trim();
				/*mincharges = (addPostSpace(mincharg,threeSlabLength)).getBytes();*/

				mincharges = mincharg.getBytes();


				/*CONSUMPTION*/
				String mf=(UtilMaster.mconsumption.trim());

				int consumtionn = (int)(MasterLibraryFunction.doubRound(( Float.parseFloat(UtilMaster.mconsumption.trim())),0));

				mfunits = String.valueOf(consumtionn).getBytes();


				/*PF*/
				String PF = UtilMaster.mpf_reading.trim() ;
				consumerlabel =(PF).getBytes();


				/*AVG UNITS*/
				int setAvg = 0 ;
				setAvg=(int)(MasterLibraryFunction.doubRound(( Float.parseFloat(UtilMaster.maverage_consumption.trim())),0));
				avgunits =(addPostSpace(""+setAvg,threeSlabLength)).getBytes();

				/*PRESENT METER STATUS*/
				String mtrstatus =getMeterStatus (UtilMaster.getMpresentmeterstatus().trim());
				/*meterstatus = addPostSpace(mtrstatus,threeSlabLength).getBytes();*/

				// by shivanand
				meterstatus = mtrstatus.getBytes();



				// BY SHIVANAND

				if(UtilMaster.getMpresentmeterstatus().equalsIgnoreCase("2") || UtilMaster.getMpresentmeterstatus().equalsIgnoreCase("9") ){

					UtilMaster.mno_of_months_issued = UtilMaster.mActualBmForDL;

				}



				/*BM */
				String biltolngth = UtilMaster.mno_of_months_issued.trim()+" Mnt(s)" ;
				billto = (biltolngth).getBytes();




				/*************** FIXED , ENERGY CHARGERS BLOCK ****** */



				String energchar =  getSpace(UtilMaster.mec.trim(),threeSlabLength);


				String fixedcharges = getSpace(UtilMaster.mfc.trim() , threeSlabLength);


				String checkfppca = UtilMaster.mfppca.trim();


				/*EC*/
				ec = (energchar).getBytes();


				/*FC*/
				fc =( fixedcharges).getBytes();

				/*FPPCA*/
				fppca = checkfppca.getBytes();



				/*************SUNDRY CHARG****************/

				/*OTHERS*/

				/*String sundrycharges = ""+doubRoundInChart(Float.parseFloat(UtilMaster.mothers.trim()),2) ;*/

				// fppca change

				String sundrycharges = ""+doubRoundInChart(Float.parseFloat(UtilMaster.mfppca.trim()),2) ;
				sundry =( sundrycharges).getBytes();



				/*PF PENALITY*/ 
				String SundyPFpnly = UtilMaster.mpf_penality.trim()  ;
				sundrylabel = SundyPFpnly.getBytes();

				/*BMD READING */
				phase=  	getSpace(UtilMaster.mbmd_reading.trim(),threeSlabLength).getBytes();   


				/*BMD PENALITY */
				minchargeslabel= null ;
				String bmdPenality =  getSpace(UtilMaster.mbmd_penality , threeSlabLength);
				minchargeslabel = bmdPenality.getBytes();

				/*******************MISC/CAP CHRG****************************/
				/*	DL ADJ*/ 
				String credit_ = UtilMaster.mdl_adj.trim();
				credit = addPostSpace( credit_,threeSlabLength).getBytes();








				String misc_charges=getSpace("0",14);
				misc =( misc_charges).getBytes();

				/*ARREARS*/
				String arrears_=getSpace(UtilMaster.marrears.trim() , threeSlabLength);
				arears = arrears_.getBytes();





				String temp_cerdit = "0", temp_subsidies = "0";
				int Ltariff_code_i = 0;
				try {
					Ltariff_code_i = Integer.parseInt(UtilMaster.mtariff.trim());
				} catch (Exception e) {
				}

				/************* free Lighting FL case , OL and BJ-KJ REBATE ****************************/
				/*if (Ltariff_code_i == 53 || Ltariff_code_i == 58|| Ltariff_code_i == 63 || Ltariff_code_i == 69
						|| Ltariff_code_i == 1 || Ltariff_code_i == 2
						|| Ltariff_code_i == 104 || Ltariff_code_i == 208)
					temp_subsidies = UtilMaster.mcredit_or_rebate;
				else
					temp_cerdit = UtilMaster.mcredit_or_rebate;*/








				/*	if ( Ltariff_code_i == 1 || Ltariff_code_i == 2	)
					temp_cerdit = UtilMaster.mcredit_or_rebate;

				else
					temp_subsidies = UtilMaster.mcredit_or_rebate;*/


				/*BY SHIVANAND DATED 29-11-2015*/


				temp_cerdit = UtilMaster.mcredit_or_rebate;

				temp_subsidies = "-";






				/**********************************************************/


				/*************REBATE****************************/
				/*fppcalabel = addPostSpace( temp_cerdit,threeSlabLength).getBytes();*/
				fppcalabel = temp_cerdit.getBytes();

				/************Government subsidies****************************/
				linemin= temp_subsidies.getBytes() ; 




				/*INTEREST 
				String DPS=UtilMaster. minterest .trim();
				cap=(addPostSpace(DPS,threeSlabLength)).getBytes();	*/


				// BY SHIVANAND
				try {
					float currentMonthInterest = (float) doubRoundInChart((Float.parseFloat(UtilMaster. minterest) - Float.parseFloat(UtilMaster.mold_interest)), 2);
					String DPS= String.valueOf(currentMonthInterest);
					cap=(addPostSpace(DPS,threeSlabLength)).getBytes();


				} catch (Exception e) {
					// TODO: handle exception


					String DPS=UtilMaster. minterest .trim();
					cap=(addPostSpace(DPS,threeSlabLength)).getBytes();

				}















				/*TAX */
				String dutycharges=""+UtilMaster.mtax;
				duty = (dutycharges).getBytes();



				/*BillDateTimeStamp */
				UtilMaster.setMbilledatetimestamp(UtilMaster.getMobileBillDatetimeStamp());





				/*NET */

				float orgTOTALNet = 0 ;
				float round_TOTALNet = 0;
				float round_diff = 0 ;
				if(UtilMaster. mbilledstatus.equalsIgnoreCase("0")){
					orgTOTALNet = (Float.parseFloat(UtilMaster.mtotal));
					round_TOTALNet = (int)doubRoundInChart(Float.parseFloat(UtilMaster.mtotal),0) ;
					round_diff =(float) MasterLibraryFunction.doubRound( orgTOTALNet-round_TOTALNet , 2 ) ;
					UtilMaster.setMroundOff(Float.toString(round_diff));

					UtilMaster.setMroundfftotal( round_TOTALNet+"");

				}else{
					orgTOTALNet = (Float.parseFloat(UtilMaster.mtotal));
					round_TOTALNet = (int)doubRoundInChart(Float.parseFloat(UtilMaster.mtotal),0) ;
					round_diff =(float) MasterLibraryFunction.doubRound( orgTOTALNet-round_TOTALNet , 2 ) ;
				}
				netamount = ( addPostSpace( (int)round_TOTALNet+"",threeSlabLength)).getBytes();

				UtilMaster.netamountForBJKJ = round_TOTALNet;

				/** ********bar code***********/		
				int i = (int)(doubRoundInChart(MasterLibraryFunction.doubRound(Float.parseFloat(UtilMaster.mtotal),2),0));
				String value1 = ""+i;
				System.out.println("length ****** " + value1.length());
				if(value1.length() < 6)
				{
					for (int cout = 0 ; cout <= (8 - value1.length() ); cout++)
					{
						System.out.println(" count "+cout);
						value1 = value1+" " ;
					}
				}else if(value1.length() > 6)
				{
					value1 = " ";
					for (int cout = 0 ; cout <(7); cout++)
					{
						value1 = value1 + " ";
					}
				}

				System.out.println("length 22222222 ****** " + value1.length());

				String netamountForBArCode = " "+UtilMaster.mconsumer_sc_no.trim()+"-"+value1;
				//String netamountForBArCode = " 123456789-"+value1;
				avguintslabel= netamountForBArCode.getBytes();



				/**   ***  ******  END **** ************ 	*/


				/////////////////////////END ////////////////////////////////


				/*int lngth =(48 - (UtilMaster. mdivision+"/"+UtilMaster. msubdivision +billName).length() )/ 2  ;*/

				/*	if(lngth < 24 )
				{
					int m1 =lngth;
					 for (int j = 0; j <m1; j++) 
					 {
						 divspace = divspace+" ";
					 } 
				}*/

				/************     NET AMOUNT*************	*/
				/*		
				foliono =("\n   (ED+BD = NET)              (+2% of(ED+BD)=NET)").getBytes() ;
				int i = (int)(doubRoundInChart(MasterLibraryFunction.doubRound(Float.parseFloat(UtilMaster.mtotal),2),0));
				float g = (float)MasterLibraryFunction.doubRound((Float.parseFloat(UtilMaster.mtotal)),2) ;
				float tAfdDate = 0;
				String tAfdDate1 = null;
				float eD =(float) doubRoundInChart(Float.parseFloat(UtilMaster.mtax.trim()) ,1);

				float bD =(float) doubRoundInChart( ((Float.parseFloat(UtilMaster.mtotal))) - eD ,1) ;

				float inrtvalueINTR =0 ;
				inrtvalueINTR =(float) doubRoundInChart( Float.parseFloat(UtilMaster. minterest.trim()),2) ;
				float intvalforBD = bD ;


				float newBdaftdate = intvalforBD - inrtvalueINTR;
				float AfEd =(float) doubRoundInChart( eD+((eD*2)/100) ,1);
				float AfBd =(float) doubRoundInChart( bD+((newBdaftdate*2)/100),1);
				tAfdDate = AfEd+AfBd ;
				eD = (float) doubRoundInChart(eD,2);
			int newTotal =(int)(doubRoundInChart(MasterLibraryFunction.doubRound( eD + bD,2),0)) ;
			if(UtilMaster. mbilledstatus.equalsIgnoreCase("0")){
				float orgTOTALNet = (Float.parseFloat(UtilMaster.mtotal));
			 	float round_TOTALNet = newTotal ;
			 	float round_diff =(float) MasterLibraryFunction.doubRound( orgTOTALNet-round_TOTALNet , 2 ) ;
			 	UtilMaster.setMroundOff(Float.toString(round_diff));

			 	UtilMaster.setMroundfftotal( round_TOTALNet+"");

				}
			String sign1 = "+";
			String netAmtDisply = ""+newTotal;
			if(bD < 0  ){
				sign1 = "";
			}
			if(newTotal <0 )
			{
				netAmtDisply = "("+newTotal+")";
			}

				String tBFDATE =( eD+""+sign1+""+ bD+"="+netAmtDisply); 

				UtilMaster.dutyBfrdue  = ""+eD;
					if(UtilMaster.dutyBfrdue.length() < 14)
					{
						int m1 = 14-UtilMaster.dutyBfrdue.length();
						 for (int j = 0; j <m1; j++) {
							 UtilMaster.dutyBfrdue = UtilMaster.dutyBfrdue+" ";
						  } 
					}



				UtilMaster.chrgBfrdue = bD+""; 
				if(UtilMaster.chrgBfrdue.length() < 14)
				{
					int m1 = 14-UtilMaster.chrgBfrdue.length();
					 for (int j = 0; j <m1; j++) {
						 UtilMaster.chrgBfrdue = UtilMaster.chrgBfrdue+" ";
					  } 
				}



				UtilMaster.nettotalBfrdue = netAmtDisply ;

				if(UtilMaster.nettotalBfrdue.length() < 14)
				{
					int m1 = 14-UtilMaster.nettotalBfrdue.length();
					 for (int j = 0; j <m1; j++) {
						 UtilMaster.nettotalBfrdue = UtilMaster.nettotalBfrdue+" ";
					  } 
				}
				 *//** for -ve amount DPS = 0 ; and net amount after due date should be same as net amount******************//*		
				String sign2 = "+";
				if(AfBd < 0  ){
					sign2 = "";
				}
				String netAmtDisply2 = ""+(int)doubRoundInChart(tAfdDate,0) ;
				if(tAfdDate < 0)
				{
					netAmtDisply2 = "("+(int)doubRoundInChart(tAfdDate,0)+")" ;
				}


				if(((int)(doubRoundInChart(Float.parseFloat(UtilMaster.mtotal),0)))>0)
				{
				 tAfdDate1 = AfEd+""+sign2+""+ AfBd+"="+netAmtDisply2; 
				 UtilMaster.dutyAftdue = AfEd+"" ;
				 UtilMaster.chrgAftdue = AfBd+"" ;
				 UtilMaster.nettotalAftdue =  netAmtDisply2 ;


				}else
				{
					tAfdDate1 =tBFDATE ;

					UtilMaster.dutyAftdue = eD+"" ;
					UtilMaster.chrgAftdue = bD+"" ;
					UtilMaster.nettotalAftdue  = netAmtDisply ;
				}
				System.out.println("tAfdDate 222  *******"+doubRoundInChart(tAfdDate,0)+" &  with int** "+(int)doubRoundInChart(tAfdDate,0));

				System.out.println("tAfdDate 3333  *******"+tAfdDate1);

				if(tBFDATE.length() <=20)
				{
					int m1 = 22-tBFDATE.length();
					 for (int j = 0; j <m1; j++) {
						 tBFDATE = tBFDATE+" ";
					  } 
				}

				netamount=("\n  "+tBFDATE+"      "+tAfdDate1).getBytes();

				float newExtra2 =(float) MasterLibraryFunction.doubRound((( g - inrtvalueINTR)+(( ( g - inrtvalueINTR )*2)/100)),2) ;

				if( ((int)(doubRoundInChart(Float.parseFloat(UtilMaster.mtotal),0)))>0)
				{
					UtilMaster.mextra2 =""+(float)MasterLibraryFunction.doubRound((newExtra2 - ( g - inrtvalueINTR)),2) ;
				}else
				{
					UtilMaster.mextra2 ="0";
				}




				foliolabel = ("               " +tAfdDate1 ).getBytes();	



				  */	






				sendMessage(heading1,heading2,heading3,u_line,u_space,division,divisioname,subdivision,subdivisioname,billnolabel,billno,issuedate,duedate,
						consumerlabel,consumerid,consumeraddlabel,consumername,address,address1,billfromlabel,billfrom,billtolabel,billto,connecdatelabel,
						conndate,sacnload,phase,poleno,tariff,minchargeslabel,mincharges,linemin,meterno,avguintslabel,avgunits,foliolabel,foliono,readinglabel,
						currentrdng,previousrdng,mfunits,meterstatuslabel,meterstatus,meterrent,energychargelabel,ec,duty,fc,fppcalabel,fppca,cap,arears,sundrylabel,
						sundry,credit,misc,netamountlabel,netamount,departlabel,departlabel1,divisionlabel,divisiondep,subdivlabel,subdiv,receiptnolabel,receiptno,datelabel,date
						,billisslabel,billissuedate,duedatelabel,duedatedep,amountreceivedlabel,counternamelabel);




			}
		});

		button_menu1.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View arg0) {

				System.out.println(" testing :backbuttonenable.equals(NO): "+ backbuttonenable.equals("NO") );

				if (backbuttonenable.equals("NO")) { // Here no means
					// dont allow
					// user RECONNECT
					// back

				} else 
				{
					//	mSendButton.setVisibility(View.VISIBLE);
					registerForContextMenu(button_menu1);
					openContextMenu(button_menu1);
					new AlertDialog.Builder(BluetoothChat.this)
					.setTitle("Bluetooth menu")
					.setPositiveButton(	"Connect a device",	new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog,	int id)
						{
							Intent serverIntent = new Intent(BluetoothChat.this,DeviceListActivity.class);
							startActivityForResult(	serverIntent,REQUEST_CONNECT_DEVICE);
						}
					})
					.setNegativeButton("Make discoverable",	new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog,	int id) 
						{
							ensureDiscoverable();
						}
					}).show();
					// Create the AlertDialog object and return it
				}
			}
		});


		bluetoothbckpress1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				onBackPressed();

			}
		});

		// Initialize the BluetoothChatService to perform bluetooth connections
		mChatService = new BluetoothChatService(this, mHandler);

		// Initialize the buffer for outgoing messages
		mOutStringBuffer = new StringBuffer("");
	}


	@Override
	public synchronized void onPause() {
		super.onPause();
		if (D)
			logger.info(TAG, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (D)
			logger.info(TAG, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth chat services
		if (mChatService != null)
			mChatService.stop();
		if (D)
			logger.info(TAG, "--- ON DESTROY ---");
	}

	private void ensureDiscoverable() {
		if (D)
			Log.d(TAG, "ensure discoverable");
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) 
		{
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            A string of text to send.
	 */
	private void sendMessage(byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[]subdivision,byte[] subdivisioname,
			byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[]consumername,byte[]address,byte[]address1,
			byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno , byte[] tariff,
			byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
			byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
			byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
			byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
			byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel) {
		try {



			// Check that we're actually connected before trying anything
			if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
				mSendButton.setVisibility(View.VISIBLE);
				//mSendButton.setClickable(true);
				backbuttonenable="YES";
				Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
				return;
			}else{
				backbuttonenable="NO";
				mSendButton.setVisibility(View.GONE);
				//mSendButton.setEnabled(false);
				/*mChatService.write(getApplicationContext(), heading1,heading2,heading3,u_line,u_space,division,divisioname,subdivision,subdivisioname,billnolabel,billno,issuedate,duedate,
						consumerlabel,consumerid, consumeraddlabel,consumername,address,address1,billfromlabel,billfrom,billtolabel,billto,connecdatelabel,conndate
						,sacnload,phase,poleno, tariff,minchargeslabel,mincharges,linemin,meterno,avguintslabel,avgunits,foliolabel,foliono,readinglabel,currentrdng,previousrdng,mfunits,
						meterstatuslabel,meterstatus,meterrent,energychargelabel,ec,duty,fc,fppcalabel,fppca,cap,arears,sundrylabel,sundry,credit,misc,netamountlabel,netamount,
						departlabel,departlabel1,divisionlabel,divisiondep,subdivlabel,subdiv,receiptnolabel,receiptno,datelabel,date,billisslabel,billissuedate,duedatelabel,duedatedep,amountreceivedlabel,counternamelabel);
				 */

				//ProgressDialog barProgressBar= new ProgressDialog(BluetoothChat.this);
				mChatService.new PrintBill(BluetoothChat.this, heading1, heading2, heading3, u_line, u_space, division, divisioname, subdivision, subdivisioname, billnolabel, billno, issuedate, duedate, consumerlabel, consumerid, consumeraddlabel, consumername, address, address1, billfromlabel, billfrom, billtolabel, billto, connecdatelabel, conndate, sacnload, phase, poleno, tariff, minchargeslabel, mincharges, linemin, meterno, avguintslabel, avgunits, foliolabel, foliono, readinglabel, currentrdng, previousrdng, mfunits, meterstatuslabel, meterstatus, meterrent, energychargelabel, ec, duty, fc, fppcalabel, fppca, cap, arears, sundrylabel, sundry, credit, misc, netamountlabel, netamount, departlabel, departlabel1, divisionlabel, divisiondep, subdivlabel, subdiv, receiptnolabel, receiptno, datelabel, date, billisslabel, billissuedate, duedatelabel, duedatedep, amountreceivedlabel, counternamelabel).execute();

				//print_billl();
				System.out.println("***********************");
				//Movetobilledactivity();
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			logger.error(Log.getStackTraceString(e2));
			new AlertDialog.Builder(BluetoothChat.this)
			.setTitle("Info")
			.setMessage("Consumer details are not correct / Problem occured while connecting to device ,Please try again  ")
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Intent nfData_intnt = new Intent(BluetoothChat.this,MainActivity.class);
					startActivity(nfData_intnt);

				}
			}).show();
		}


	}




	/*
	public void print_billl()
	 {
		System.out.println("PRINT MEHTOD********************************");

		String KEY_CONSUMER_SC_NO = Consumer.CONSUMER_SC_NO;
		 String KEY_METER_CONSTANT = Consumer.getMeter_constant();
		 String KEY_CONSUMER_NAME = Consumer.LConsumerName;
		 String KEY_ADDRESS = Consumer.LAddress;
		 String KEY_ADDRESS1 = Consumer.lAddress1;
		 String KEY_TARIFF =""+Consumer.TarifCode;
		 String KEY_TARIFFDESC =""+Consumer.Ltariffdesc;
		 String KEY_LEDGER_NO = Consumer.LLedgno;
		 String KEY_FOLIO_NO = Consumer.LFolio;
		 String KEY_CONNECTED_LOAD = Consumer.Lconnection_load;
		 String KEY_D_AND_R_FEE = "0.0";
		 String KEY_ARREARS = Consumer.LArrears;
		 String KEY_INTEREST = Consumer.LInterest;
		 String KEY_OTHERS = ""+Consumer.Others;
		 String KEY_BACKBILLARR = "0.0";
		 String KEY_AVERAGE_CONSUMPTION = Consumer.LAverage;
		 String KEY_DL_OR_MNR_PREV_MONTH = Consumer.LBM;
		 String KEY_PREVIOUS_READING = Consumer.LPrevReading;
		 String KEY_PRESENTREADING = Consumer.LPresentReading;
		 String KEY_CONSUMPTION = Consumer.LConsumed;
		 String KEY_POWER_FACTOR = Consumer.KEY_POWER_FACTOR;
		 String KEY_READING_DATE = Consumer.LRdng_Date;
		 String KEY_METER_CHANGE_UNITS_CONSUMED = "0";
		 String KEY_NO_OF_MONTHS_ISSUED = Consumer.LBM;
		 String KEY_CREDIT_OR_REBATE = "0.0";
		 String KEY_FIXED_GES = Consumer.FIXED_GES;

		 String KEY_AUDIT_ARREARS = Consumer.AUDIT_ARREARS;
		 String KEY_OLD_INTEREST = Consumer.OLD_INTEREST;
		 String KEY_TRIVECTOR = Consumer.TRIVECTOR;
		 String KEY_DOORLOCKAVG = "N";
		 String KEY_CONSUMERCODE = "null";
		 String KEY_ADDITIONALDEP = "0";
		 String KEY_MRCODE = Consumer.LMrcode;
		 System.out.println("bill month *************bluetooth******************: " + Consumer.getLBillMonth());
		 String KEY_BILLMONTH =Consumer.getLBillMonth();
		 String KEY_SITECODE = Consumer.LSitecode;
		 String KEY_SYNCSTATUS = Consumer.KEY_SYNCSTATUS1;
		 String KEY_DATAPREPAREDDATE = Consumer.KEY_DATAPREPAREDDATE;
		 String KEY_SERVERTOSBMDATE = Consumer.SERVERTOSBMDATE;
		 String KEY_METERNO = "0";
		 String KEY_INTERESTONDEPOSIT = "0";
		 String KEY_DL_ADJ = Consumer.DL_ADJ;
		 String KEY_METERRENT = Consumer.Lmeterrent;
		 String KEY_FPPCA = Consumer.TotalFPPCA;
		 String KEY_EXTRA1 =Consumer.extra1;
		 String KEY_EXTRA2 = Consumer.extra2;

		String key_cyclename = Consumer.key_cyclename;
		String key_consumer_key = Consumer.key_consumer_key;
		String key_installationo = Consumer.key_installationo;
		String key_consumerno = Consumer.key_consumerno;

		String key_division1 = Consumer.divisionName;
		String key_subdivision1 = Consumer.subDivisionName;

		 String key_bookno = Consumer.bookNo;
		 String key_devicefirmwareversion = Consumer.deviceFirmwareVersion;

		 String Key_billedatetimestamp = Consumer.billedatetimestamp ;


		 String KEY_BILLNO = Consumer.LBillno;
		 String KEY_STATUS = "NOT UPDATED";
		 String KEY_BILLEDSTATUS = "0";

		 String previous_bdate = Consumer.previous_bdate;
			 String actual_previous_bdate = Consumer.actual_previous_bdate;
			 String seasonal_consumer = Consumer.seasonal_consumer;
		 String lineminimum = Consumer.lineMinimum ;
		 String ligPoints = Consumer.ligPoints ;
		 String metered = Consumer.metered.trim();
		 String key_taxflagextra6 = Consumer.getTaxFlagExtra6().trim();

		 System.out.println("Check bluetooth value *****************KEY_STATUS*********  : "+KEY_STATUS);
		 System.out.println("Check bluetooth value *************KEY_BILLEDSTATUS**************  : "+KEY_BILLEDSTATUS);

	   databasehandler = new DatabaseHandler(
	     getApplicationContext());
	   databasehandler.openToWrite();

	 databasehandler.insert( 

	    KEY_CONSUMER_SC_NO  ,
		  KEY_METER_CONSTANT  ,
		  KEY_CONSUMER_NAME  ,
		  KEY_ADDRESS  ,
		  KEY_ADDRESS1 ,
		  KEY_TARIFF  ,
		  KEY_TARIFFDESC,
		  KEY_LEDGER_NO  ,
		  KEY_FOLIO_NO  ,
		  KEY_CONNECTED_LOAD  ,
		  KEY_D_AND_R_FEE  ,
		  KEY_ARREARS  ,
		  KEY_INTEREST  ,
		  KEY_OTHERS  ,
		  KEY_BACKBILLARR  ,
		  KEY_AVERAGE_CONSUMPTION  ,
		  KEY_DL_OR_MNR_PREV_MONTH ,
		  KEY_PREVIOUS_READING  ,
		  KEY_POWER_FACTOR  ,
		  KEY_READING_DATE  ,
		  KEY_METER_CHANGE_UNITS_CONSUMED  ,
		  KEY_NO_OF_MONTHS_ISSUED  ,
		  KEY_CREDIT_OR_REBATE  ,
		  KEY_FIXED_GES  ,

		  KEY_AUDIT_ARREARS  ,
		  KEY_OLD_INTEREST  ,
		  KEY_TRIVECTOR  ,
		  KEY_DOORLOCKAVG  ,
		  KEY_CONSUMERCODE  ,
		  KEY_ADDITIONALDEP  ,
		  KEY_MRCODE  ,
		  KEY_BILLMONTH  ,
		  KEY_SITECODE  ,
		  KEY_SYNCSTATUS  ,
		  KEY_DATAPREPAREDDATE  ,
		  KEY_SERVERTOSBMDATE ,
		  KEY_METERNO  ,
		  KEY_INTERESTONDEPOSIT  ,
		  KEY_DL_ADJ  ,
		  KEY_METERRENT  ,
		  KEY_FPPCA  ,
		  KEY_EXTRA1 ,
		  KEY_EXTRA2 ,


		  key_cyclename,
			 key_consumer_key,
			 key_installationo,
			 key_consumerno,

			 key_division1,
			 key_subdivision1,

			 key_bookno,
			 key_devicefirmwareversion,

			 Key_billedatetimestamp,

		  KEY_BILLNO 
		  ,KEY_STATUS,

		     KEY_BILLEDSTATUS,  previous_bdate,
			    actual_previous_bdate,lineminimum,
			    seasonal_consumer,ligPoints,
			    metered
			    ,key_taxflagextra6
		   );


	   databasehandler.close();
	   Movetobilledactivity();



	 }*/


	public void Movetobilledactivity()
	{



		System.out.println("Movetobilledactivity  ");


		if( UtilMaster. mbilledstatus.equalsIgnoreCase("0")){

			databasehandler = new DatabaseHandler(getApplicationContext());
			try {


				// checking login details in sqlite db if once already
				//logded


				byte[] img = "".getBytes();


				if(UtilMaster.isPhotoEnable()){


					File imagefile = new File(Environment.getExternalStorageDirectory(),	"/CESCTRM/CESCImages/" + UtilMaster.mImagePath.trim());

					FileInputStream fis = null;
					try {
						fis = new FileInputStream(imagefile);
					} catch (FileNotFoundException e) {
						logger.error(Log.getStackTraceString(e));
						e.printStackTrace();
					}

					Bitmap bm = BitmapFactory.decodeStream(fis);
					System.out.println( "1st  bm.getWidth() :"+ bm.getWidth() +" && bm.getHeight() "+bm.getHeight());
					bm = getResizedBitmap(bm, (int)150, (int)200);
					System.out.println( "2nd  bm2.getWidth() :"+ bm.getWidth() +" && bm2.getHeight() "+bm.getHeight());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					bm.compress(Bitmap.CompressFormat.PNG, 10 , baos);  
					img = baos.toByteArray();


				}

				else{

					UtilMaster.mImagePath = "test";
				}












				databasehandler.openToWrite();




				if(!UtilMaster.isGpsEnable()){

					/*if(UtilMaster.mlangitude == null)*/UtilMaster.mlangitude = "-";
					/*if(UtilMaster.mlattitude == null)*/UtilMaster.mlattitude = "-";

				}




				Calendar c = Calendar.getInstance();
				SimpleDateFormat df1 = new SimpleDateFormat("h:mm a");
				final String time = df1.format(c.getTime());
				UtilMaster.mtakeTime = time ;
				logger.debug("********** Updatetolocaldb started*********** ");
				Log.e(TAG, "mbmd_penality: "+UtilMaster.mbmd_penality);
				Log.e(TAG, "mbmd_reading: "+UtilMaster.mbmd_reading);


				int cursor = databasehandler.Updatetolocaldb(
						UtilMaster. mmeter_constant.trim(), UtilMaster. mpresentreading,UtilMaster.mpresentmeterstatus.trim(),
						UtilMaster.mconsumption, UtilMaster.mtax, UtilMaster.mec,
						UtilMaster.mfc, UtilMaster.mtotal, UtilMaster.mothers,
						UtilMaster.mbillno,

						UtilMaster.mconsumer_sc_no, UtilMaster.mbillmonth.trim(),
						UtilMaster.mbilldate, UtilMaster.mduedate,
						UtilMaster.mtariffdesc, UtilMaster.mbilledatetimestamp,
						UtilMaster.mmeterrent, UtilMaster.mno_of_months_issued,
						UtilMaster.mligpoints, UtilMaster.mextra2,UtilMaster. mckwhlkwh , UtilMaster.mpf_reading, UtilMaster.mpf_penality,
						UtilMaster.mdl_adj.trim(), UtilMaster.minterest, UtilMaster.mmeter_change_units_consumed.trim(), 
						UtilMaster.mdevicefirmwareversion.trim(),
						UtilMaster.mroundOff.trim(), 
						UtilMaster.mroundfftotal.trim() ,
						img ,	
						UtilMaster.mImagePath.trim()	 , 
						UtilMaster.mlangitude.trim() ,
						UtilMaster.mlattitude.trim()
						,UtilMaster.mtakeTime.trim() , UtilMaster.mdivision.trim() , UtilMaster.mbmd_penality , UtilMaster.mbmd_reading, UtilMaster.mcredit_or_rebate
						, UtilMaster.maverage_consumption.trim(), UtilMaster.phoneNumber, UtilMaster.deviceInfo
						);

				System.out.println("cursor *******************************************"+cursor);
				logger.debug("********** Updatetolocaldb Completed *********** cursor : "+ cursor);

				System.out.println("updated******************************************************in local db");
			}
			catch (Exception e) {
				logger.error(Log.getStackTraceString(e));
				e.printStackTrace();
			}


		}


		System.out.println("gps data updated****************************************************************************");

		databasehandler = new DatabaseHandler(getApplicationContext());

		try {


			// getting rrno,billed and unbilled count




			databasehandler.openToRead();
			final boolean isMrvalid = databasehandler.isUserValid(	UtilMaster.mGLogin_SiteCode, UtilMaster.mGLogin_MRCode);
			databasehandler.close();







			// adding delay while printing
			new Handler().postDelayed(new Runnable() {
				public void run() {

					if (isMrvalid) {
						startActivity(new Intent(BluetoothChat.this,
								SearchActivity.class));
					} else {
						new AlertDialog.Builder(BluetoothChat.this)
						.setTitle("Info")
						.setMessage(
								"Please download the  new data. No more consumer to bill for this meter reader code")
								.setPositiveButton("OK",  new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,int which) 
									{
										startActivity(new Intent(BluetoothChat.this,
												MainActivity.class));
									}
								}).show();
					}



				}
			}, 1000 * 5);



		} catch (Exception e) {
			logger.error(Log.getStackTraceString(e));
			e.printStackTrace();
			new Handler().postDelayed(new Runnable() {
				public void run() {


					new AlertDialog.Builder(BluetoothChat.this)
					.setTitle("Info")
					.setMessage(
							"Consumer details is not correct or unable to bill , Please try again later ")
							.setPositiveButton("OK",  new DialogInterface.OnClickListener()
							{

								public void onClick(DialogInterface dialog,int which) 
								{
									startActivity(new Intent(BluetoothChat.this,
											MainActivity.class));
								}
							}).show();




				}
			}, 1000 * 5);

		}






	}



	/*// The action listener for the EditText widget, to listen for the return key
	private TextView.OnEditorActionListener mWriteListener = new TextView.OnEditorActionListener() {
		public boolean onEditorAction(TextView view, int actionId,
				KeyEvent event) {
			// If the action is a key-up event on the return key, send the
			// message
			if (actionId == EditorInfo.IME_NULL
					&& event.getAction() == KeyEvent.ACTION_UP) {
				// String message = view.getText().toString();
				// sendMessage(message);
			}
			if (D)
				Log.i(TAG, "END onEditorAction");
			return true;
		}
	};*/

	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override

		public void handleMessage(Message msg) {
			try {

				switch (msg.what) {
				case MESSAGE_STATE_CHANGE:
					if (D)
						Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
					switch (msg.arg1) {
					case BluetoothChatService.STATE_CONNECTED:
						mTitle.setText(R.string.title_connected_to);
						mTitle.append(mConnectedDeviceName);
						// mConversationArrayAdapter.clear();
						break;
					case BluetoothChatService.STATE_CONNECTING:
						mTitle.setText(R.string.title_connecting);
						break;
					case BluetoothChatService.STATE_LISTEN:
					case BluetoothChatService.STATE_NONE:
						mTitle.setText(R.string.title_not_connected);
						break;
					}
					break;
				case MESSAGE_WRITE:
					byte[] writeBuf = (byte[]) msg.obj;
					// construct a string from the buffer
					String writeMessage = new String(writeBuf);
					mConversationArrayAdapter.add("Me:  " + writeMessage);
					break;
				case MESSAGE_READ:
					byte[] readBuf = (byte[]) msg.obj;
					// construct a string from the valid bytes in the buffer
					String readMessage = new String(readBuf, 0, msg.arg1);
					if(mConversationArrayAdapter!=null)
						mConversationArrayAdapter.add(mConnectedDeviceName + ":  "
								+ readMessage);
					break;
				case MESSAGE_DEVICE_NAME:
					// save the connected device's name
					mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
					Toast.makeText(getApplicationContext(),"Connected to " + mConnectedDeviceName,Toast.LENGTH_SHORT).show();
					mSendButton.setVisibility(View.VISIBLE);
					break;
					/*case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;*/
				case MESSAGE_TOAST:
					///////////////////////ADDED CODE////////////////////////////////////////
					String txt = msg.getData().getString(TOAST);
					if(txt.equalsIgnoreCase("Unable to connect device")){
						SharedPreferences sp = getSharedPreferences("prefs", 0);
						SharedPreferences.Editor editor = sp.edit();
						editor.clear();
						editor.commit();
						Toast.makeText(getApplicationContext(),	txt, Toast.LENGTH_SHORT).show();
						Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
						break;
						/////////////////////////////////////////////////////////////////////////////
					}

				}

			}catch (Exception e) {
				// TODO: handle exception
				//BluetoothChatService.STATE_NONE:
				mTitle.setText(R.string.title_not_connected);
			}
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (D)
			Log.d(TAG, "onActivityResult >>>>>>>>>>>onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>: " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address
				String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);


				/////////////////ADDED CODE///////////////////
				SharedPreferences sp = getSharedPreferences("prefs", 0);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("last_macid", address);					     
				editor.commit();	
				/////////////////////////////////////////////

				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
				// Attempt to connect to the device
				mChatService.connect(device);
			}
			break;
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				setupChat();

				/////////////////ADDED CODE///////////////////
				SharedPreferences sp = getSharedPreferences("prefs", 0);  
				String mac_address = sp.getString("last_macid", "no device");
				if(mac_address.equals("no device")){

					System.out.println("------------------------------");
					System.out.println("no device is executing ");
					System.out.println("------------------------------");

					Intent serverIntent = new Intent(this, DeviceListActivity.class);
					startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
				}else{ 			

					System.out.println("------------------------------");
					System.out.println(" same device is executing ");
					System.out.println("------------------------------");

					BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac_address);
					// Attempt to connect to the device
					mChatService.connect(device);		    			    
				}   
				///////////////////////////////////////////////
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.scan:
			// Launch the DeviceListActivity to see devices and do scan
			/*Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			return true;*/

			/////////////////ADDED CODE///////////////////
			SharedPreferences sp = getSharedPreferences("prefs", 0);  
			String mac_address = sp.getString("last_macid", "no device");
			if(mac_address.equals("no device")){

				System.out.println("------------------------------");
				System.out.println("no device is executing ");
				System.out.println("------------------------------");

				Intent serverIntent = new Intent(this, DeviceListActivity.class);
				startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			}else{ 			

				System.out.println("------------------------------");
				System.out.println(" same device is executing ");
				System.out.println("------------------------------");

				BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac_address);
				// Attempt to connect to the device
				mChatService.connect(device);		    			    
			}   
			///////////////////////////////////////////////
			return true;

		case R.id.discoverable:
			// Ensure this device is discoverable by others
			ensureDiscoverable();
			return true;
		}
		return false;
	}

	/*private String convertbillmonth(String lbillmonth) {
		// TODO Auto-generated method stub
		String yearno = lbillmonth.substring(2, 4);
		String billmonth = lbillmonth.substring(4, 6);
		//String year = lbillmonth.substring(0, 4);
		if (billmonth.equals("01")) {
			billmonth = "JAN";
		} else if (billmonth.equals("02")) {
			billmonth = "FEB";
		} else if (billmonth.equals("03")) {
			billmonth = "MAR";
		} else if (billmonth.equals("04")) {
			billmonth = "APR";
		} else if (billmonth.equals("05")) {
			billmonth = "MAY";
		} else if (billmonth.equals("06")) {
			billmonth = "JUN";
		} else if (billmonth.equals("07")) {
			billmonth = "JUL";
		} else if (billmonth.equals("08")) {
			billmonth = "AUG";
		} else if (billmonth.equals("09")) {
			billmonth = "SEP";
		} else if (billmonth.equals("10")) {
			billmonth = "OCT";
		} else if (billmonth.equals("11")) {
			billmonth = "NOV";
		} else if (billmonth.equals("12")) {
			billmonth = "DEC";
		}

		return billmonth + yearno;
	}*/


	public static double doubRoundInChart(double val, int places) {
		long factor = (long)Math.pow(10,places);

		// Shift the decimal the correct number of places
		// to the right.
		val = val * factor;

		// Round to the nearest integer.
		long tmp = Math.round(val);

		// Shift the decimal the correct number of places
		// back to the left.
		return (double)tmp / factor;
	}









	/*private void SavePreferences(String key, int value) {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// editor.putString(key, value);
		editor.putInt(key, value);
		editor.commit();
	}*/


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/* if (keyCode == KeyEvent.KEYCODE_BACK) {  
	    	SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);	
	    	System.out.println("bill no  bill_series bck but 111111111  " + bill_series);	
	    	bill_series = sharedPreferences.getInt(
					"BillSeries", 0);
	   int 	bill = bill_series-1 ;
			bill_series = (bill);

			SavePreferences("BillSeries",
					bill_series);
			System.out.println("bill no  bill_series  22222222222 " + bill_series);

	    	Intent intent = new Intent(BluetoothChat.this,
					ModeActivity.class);
			startActivity(intent);
	        return true;




	    }*/
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		System.out.println(" onBackPressed ");

		System.out.println(" backbuttonenable=;"+ backbuttonenable);

		if (backbuttonenable.equals("NO")) {  //Here no means dont allow user to go back

		} else {
			super.onBackPressed(); // Process Back key  default behavior. 
		}

	}

	public static String getSpace(String string, int length) {

		int n = 0;
		if (string == null) {
			string = "-";
			n = string.length();
		} else {
			n = string.length();

		}

		int space = length - n;
		//StringBuilder s = new StringBuilder(" ");
		StringBuilder k = new StringBuilder(" ");

		for (int i = 0; i < space; i++) {
			k = k.append(" ");
		}

		String f = k.toString();
		return string+f;
	}


	private String getMeterStatus(String value) {
		String mtrStatus = "-";
		if (value.equals("1")) {
			mtrStatus = getString(R.string.a);
		} else if (value.equals("2")) {
			mtrStatus = getString(R.string.b);
		} else if (value.equals("3")) {

			mtrStatus = getString(R.string.c);
		} else if (value.equals("4")) {
			mtrStatus = getString(R.string.d);
		} else if (value.equals("5")) {
			mtrStatus = getString(R.string.e);
		} else if (value.equals("6")) {
			mtrStatus = getString(R.string.f);
		} else if (value.equals("7")) {
			mtrStatus = getString(R.string.g);
		} else if (value.equals("8")) {
			mtrStatus = getString(R.string.h);
		} else if (value.equals("9")) {
			mtrStatus = getString(R.string.i);
		} else {
			mtrStatus = ("-");
		}

		return mtrStatus ;
	}

	public static Bitmap getResizedBitmap(Bitmap image, int newHeight, int newWidth) {
		int width = image.getWidth();
		int height = image.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}









	public static String addPreSpace(String string, int length) {

		int n = 0;
		if (string == null) {
			string = "-";
			n = string.length();
		} else {
			n = string.length();

		}

		int space = length - n;
		//StringBuilder s = new StringBuilder(" ");
		StringBuilder k = new StringBuilder(" ");

		for (int i = 0; i < space; i++) {
			k = k.append(" ");
		}

		String f = k.toString();
		return  f+string;
	}
	public static String addPostSpace(String string, int length) {

		int n = 0;
		if (string == null) {
			string = "-";
			n = string.length();
		} else {
			n = string.length();

		}

		int space = length - n;
		//StringBuilder s = new StringBuilder(" ");
		StringBuilder k = new StringBuilder(" ");

		for (int i = 0; i < space; i++) {
			k = k.append(" ");
		}

		String f = k.toString();
		return  string+f;
	}

	public static String validateStrLength(String string ,  int val){
		if(string != null){
			if(string.length() >= val){
				string =string.substring(0, (val-1));
			}
		}else{
			string= "-";
		}

		return string;
	}



	public static String getTrimmedStringto20Chars(String string){


		try {


			if(string.length() >20){
				string = string.substring(0, 20);
			}


		} catch (Exception e) {
			// TODO: handle exception



		}




		return string;


	}


}





































