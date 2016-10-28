/*
 * Copyright (C) 2009 The Android Open Source Project
 *createRfcommSocketToServiceRecord
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bcits.bsmartbilling.rf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import Utils.FilePropertyManager;
import Utils.Logger;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import bcits.camera.lib.CustomCamera;

import com.utils.DatabaseHandler;


/******************* DO NOT delete any of the line (Even commented lines)********************************/
/**
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for incoming
 * connections, a thread for connecting with a device, and a thread for
 * performing data transmissions when connected.
 */
public class KannadaBluetoothChatService  
{
	String mvfwdmsg  ;
	Handler handler = new Handler() ;
	private  BluetoothSocket mmSocket;
	private  InputStream mmInStream;
	private  OutputStream mmOutStream;
	
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	// Debugging
	private static final String TAG = "BluetoothChatService";
	private static final boolean D = true;
	// Name for the SDP record when creating server socket
	private static final String NAME = "BluetoothChat";

	// Unique UUID for this application
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	// Member fields
	private final BluetoothAdapter mAdapter;
	private final Handler mHandler;
	private AcceptThread mAcceptThread;
	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;
	private int mState;

	// Constants that indicate the current connection state
	public static final int STATE_NONE = 0; // we're doing nothing
	public static final int STATE_LISTEN = 1; // now listening for incoming
	// connections
	public static final int STATE_CONNECTING = 2; // now initiating an outgoing
	// connection
	public static final int STATE_CONNECTED = 3; // now connected to a remote
	// device
	Context context = null;

	/**
	 * Constructor. Prepares a new BluetoothChat session.
	 * 
	 * @param context
	 *            The UI Activity Context
	 * @param handler
	 *            A Handler to send messages back to the UI Activity
	 */
	public KannadaBluetoothChatService(Context context, Handler handler) {
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		mState = STATE_NONE;
		mHandler = handler;
		this.context = context ;
	}

	/**
	 * Set the current state of the chat connection
	 * 
	 * @param state
	 *            An integer defining the current connection state
	 */
	private synchronized void setState(int state) {
		if (D)
			Log.d(TAG, "setState() " + mState + " -> " + state);
		mState = state;

		// Give the new state to the Handler so the UI Activity can update
		mHandler.obtainMessage(BluetoothChat.MESSAGE_STATE_CHANGE, state, -1)
		.sendToTarget();
	}

	/**
	 * Return the current connection state.
	 */
	public synchronized int getState() {
		return mState;
	}

	/**
	 * Start the chat service. Specifically start AcceptThread to begin a
	 * session in listening (server) mode. Called by the Activity onResume()
	 */
	public synchronized void start() {
		if (D)
			Log.d(TAG, "start");

		// Cancel any thread attempting to make a connection
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		// Start the thread to listen on a BluetoothServerSocket
		if (mAcceptThread == null) {
			mAcceptThread = new AcceptThread();
			mAcceptThread.start();
		}
		setState(STATE_LISTEN);
	}

	/**
	 * Start the ConnectThread to initiate a connection to a remote device.
	 * 
	 * @param device
	 *            The BluetoothDevice to connect
	 */
	public synchronized void connect(BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connect to: " + device);

		// Cancel any thread attempting to make a connection
		if (mState == STATE_CONNECTING) {
			if (mConnectThread != null) {
				mConnectThread.cancel();
				mConnectThread = null;
			}
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		// Start the thread to connect with the given device
		mConnectThread = new ConnectThread(device);
		mConnectThread.start();
		setState(STATE_CONNECTING);
	}

	/**
	 * Start the ConnectedThread to begin managing a Bluetooth connection
	 * 
	 * @param socket
	 *            The BluetoothSocket on which the connection was made
	 * @param device
	 *            The BluetoothDevice that has been connected
	 */
	public synchronized void connected(BluetoothSocket socket,
			BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connected");

		// Cancel the thread that completed the connection
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		// Cancel the accept thread because we only want to connect to one
		// device
		if (mAcceptThread != null) {
			mAcceptThread.cancel();
			mAcceptThread = null;
		}

		// Start the thread to manage the connection and perform transmissions
		mConnectedThread = new ConnectedThread(socket);
		//mConnectedThread.start();

		// Send the name of the connected device back to the UI Activity
		Message msg = mHandler.obtainMessage(BluetoothChat.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat.DEVICE_NAME, device.getName());
		msg.setData(bundle);
		mHandler.sendMessage(msg);

		setState(STATE_CONNECTED);
	}

	/**
	 * Stop all threads
	 */
	public synchronized void stop() {
		if (D)
			Log.d(TAG, "stop");
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}
		if (mAcceptThread != null) {
			mAcceptThread.cancel();
			mAcceptThread = null;
		}
		setState(STATE_NONE);
	}

	/**
	 * Write to the ConnectedThread in an unsynchronized manner
	 * 
	 * @param out
	 *            The bytes to write
	 * @see ConnectedThread#write(byte[])
	 */
	
	
	
	public class PrintBill extends AsyncTask<Void,  Void, Void>{
		
		ProgressDialog barProgressBar;
		
		  Context context;
		  ConnectedThread r;
		byte[]heading1,  heading2,
		 heading3,  u_line,  u_space,  division,
		 divisioname,  subdivision,  subdivisioname,
		 billnolabel,  billno,  issuedate,
		 duedate,  consumerlabel,  consumerid,
		 consumeraddlabel,  consumername,  address,
		 address1,  billfromlabel,  billfrom,
		 billtolabel,  billto,  connecdatelabel,
		 conndate,  sacnload,  phase,  poleno,
		 tariff,  minchargeslabel,  mincharges,
		 linemin,  meterno,  avguintslabel,
		 avgunits,  foliolabel,  foliono,
		 readinglabel,  currentrdng,  previousrdng,
		 mfunits,  meterstatuslabel,  meterstatus,
		 meterrent,  energychargelabel,  ec,  duty,
		 fc,  fppcalabel,  fppca,  cap,
		 arears,  sundrylabel,  sundry,  credit,
		 misc,  netamountlabel,  netamount,
		 departlabel,  departlabel1,  divisionlabel,
		 divisiondep,  subdivlabel,  subdiv,
		 receiptnolabel,  receiptno,  datelabel,
		 date,  billisslabel,  billissuedate,
		 duedatelabel,  duedatedep,  amountreceivedlabel,
		 counternamelabel;
		public PrintBill(Context context, byte[] heading1, byte[] heading2,
				byte[] heading3, byte[] u_line, byte[] u_space, byte[] division,
				byte[] divisioname, byte[] subdivision, byte[] subdivisioname,
				byte[] billnolabel, byte[] billno, byte[] issuedate,
				byte[] duedate, byte[] consumerlabel, byte[] consumerid,
				byte[] consumeraddlabel, byte[] consumername, byte[] address,
				byte[] address1, byte[] billfromlabel, byte[] billfrom,
				byte[] billtolabel, byte[] billto, byte[] connecdatelabel,
				byte[] conndate, byte[] sacnload, byte[] phase, byte[] poleno,
				byte[] tariff, byte[] minchargeslabel, byte[] mincharges,
				byte[] linemin, byte[] meterno, byte[] avguintslabel,
				byte[] avgunits, byte[] foliolabel, byte[] foliono,
				byte[] readinglabel, byte[] currentrdng, byte[] previousrdng,
				byte[] mfunits, byte[] meterstatuslabel, byte[] meterstatus,
				byte[] meterrent, byte[] energychargelabel, byte[] ec, byte[] duty,
				byte[] fc, byte[] fppcalabel, byte[] fppca, byte[] cap,
				byte[] arears, byte[] sundrylabel, byte[] sundry, byte[] credit,
				byte[] misc, byte[] netamountlabel, byte[] netamount,
				byte[] departlabel, byte[] departlabel1, byte[] divisionlabel,
				byte[] divisiondep, byte[] subdivlabel, byte[] subdiv,
				byte[] receiptnolabel, byte[] receiptno, byte[] datelabel,
				byte[] date, byte[] billisslabel, byte[] billissuedate,
				byte[] duedatelabel, byte[] duedatedep, byte[] amountreceivedlabel,
				byte[] counternamelabel){
			
			this.context=context;
			this.heading1 = heading1;
			
			this.heading2 = heading2;
			this.heading3 = heading3;
			this.u_line = u_line;
			this.u_space = u_space;
			
			this.division = division;
			this.divisioname = divisioname;
			this.subdivision = subdivision;
			this.subdivisioname = subdivisioname;
			this.billnolabel = billnolabel;
			this.billno = billno;
			this.issuedate = issuedate;
			this.duedate = duedate;
			this.consumerlabel = consumerlabel;
			this.consumerid = consumerid;
			this.consumeraddlabel = consumeraddlabel;
			this.consumername = consumername;
			this.address = address;
			this.address1 = address1;
			this.billfromlabel = billfromlabel;
			this.billfrom = billfrom;
			this.billtolabel = billtolabel;
			this.billto = billto;
			this.connecdatelabel = connecdatelabel;
			this.conndate = conndate;
			this.sacnload = sacnload;
			this.phase = phase;
			this.poleno = poleno;
			this.tariff = tariff;
			this.minchargeslabel = minchargeslabel;
			this.mincharges = mincharges;
			this.linemin = linemin;
			this.meterno = meterno;
			this.avguintslabel = avguintslabel;
			this.avgunits = avgunits;
			this.foliolabel = foliolabel;
			this.foliono = foliono;
			this.readinglabel = readinglabel;
			this.currentrdng = currentrdng;
			this.previousrdng = previousrdng;
			this.mfunits = mfunits;
			this.meterstatuslabel = meterstatuslabel;
			this.meterstatus = meterstatus;
			this.meterrent = meterrent;
			this.energychargelabel = energychargelabel;
			this.ec = ec;
			this.duty = duty;
			this.fc = fc;
			this.fppcalabel = fppcalabel;
			this.fppca = fppca;
			this.cap = cap;
			this.arears = arears;
			this.sundrylabel = sundrylabel;
			this.sundry = sundry;
			this.credit = credit;
			this.misc = misc;
			this.netamountlabel = netamountlabel;
			this.netamount = netamount;
			this.departlabel = departlabel;
			this.departlabel1 = departlabel1;
			this.divisionlabel = divisionlabel;
			this.divisiondep = divisiondep;
			this.subdivlabel = subdivlabel;
			this.subdiv = subdiv;
			this.receiptnolabel = receiptnolabel;
			this.receiptno = receiptno;
			this.datelabel = datelabel;
			this.date = date;
			this.billisslabel = billisslabel;
			this.billissuedate = billissuedate;
			this.duedatelabel = duedatelabel;
			this.duedatedep = duedatedep;
			this.amountreceivedlabel = amountreceivedlabel;
			this.counternamelabel = counternamelabel;
			
			

			// Create temporary object
			
			// Synchronize a copy of the ConnectedThread
			synchronized (this) {
				if (mState != STATE_CONNECTED)
					return;
				r = mConnectedThread;
			}
			// Perform the write unsynchronized

			//this.barProgressBar=barProgressBar;
			this.barProgressBar =new ProgressDialog(context);
			this.barProgressBar.setCancelable(false);
			this.barProgressBar.setMessage("Printing Bill\nPlease Wait...");
			this.barProgressBar.show();
			
			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			
			
			
			boolean isKannadaPrint = true;
			
			
			
			
			
			
			
			
			if(UtilMaster.isStationerySelection() && isKannadaPrint){
				
				try {
					

					//TODO
						/*r.GescomAllignemntNEW_WRTBlackmarandBarcodeIMEIVERSION(heading1, heading2, heading3, u_line, u_space, division,
								divisioname, subdivision, subdivisioname, billnolabel, billno,
								issuedate, duedate, consumerlabel, consumerid,
								consumeraddlabel, consumername, address, address1,
								billfromlabel, billfrom, billtolabel, billto, connecdatelabel,
								conndate, sacnload, phase, poleno, tariff, minchargeslabel,
								mincharges, linemin, meterno, avguintslabel, avgunits,
								foliolabel, foliono, readinglabel, currentrdng, previousrdng,
								mfunits, meterstatuslabel, meterstatus, meterrent,
								energychargelabel, ec, duty, fc, fppcalabel, fppca, cap,
								arears, sundrylabel, sundry, credit, misc, netamountlabel,
								netamount, departlabel, departlabel1, divisionlabel,
								divisiondep, subdivlabel, subdiv, receiptnolabel, receiptno,
								datelabel, date, billisslabel, billissuedate, duedatelabel,
								duedatedep, amountreceivedlabel, counternamelabel);*/



						/************* Bill *****************/			

						mmOutStream.flush();


						byte[] rf=new byte[1];
						rf[0]=0x0A;

						byte[] rfTAB=new byte[1];
						rfTAB[0]=0x09;



						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);


						mmOutStream.write(rf);


						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(heading1); // subdevision name
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						
						
						/*mmOutStream.write(rf);*/
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.getMdataprepareddate().getBytes()); // data prepared date
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.mbilledatetimestamp.trim().getBytes()); // bill given date
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());// jakati/mumpramana
						mmOutStream.write(UtilMaster.mtariffdesc.trim().getBytes());
						mmOutStream.write("/".getBytes());
						mmOutStream.write(UtilMaster. mconnected_load.trim().getBytes());
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(billno); // bill no
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  balakedarana sanke
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(heading3); //  RRNO
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(consumername); //  consumer name 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address); //  address1 
						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address1); // address2 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  tc sanke 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(poleno); //  legder and pane sanke 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi dina 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi motta 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(issuedate); //  oduva dinanka 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duedate); //  due date
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(currentrdng); //  present reading
						mmOutStream.write(" - ".getBytes());
						mmOutStream.write(meterstatus);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.getMprevious_reading().trim().getBytes()); //  previous reading
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mfunits); //  consumtion
						mmOutStream.write(rf);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mincharges); //  mapaka stiranka
						mmOutStream.write(" / BM ".getBytes());
						mmOutStream.write(UtilMaster.mno_of_months_issued.trim().getBytes());



						mmOutStream.write(rf);
						mmOutStream.write(rf);
						/*mmOutStream.write(rf);*/


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fc); //  nigadit shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(ec); //  vidyut shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("0.0".getBytes()); //  marga kanishta


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(minchargeslabel); //  adika danda/ helo danda value added
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundrylabel); //  pfdanda
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundry); //  others
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.mold_interest.trim().getBytes()); //  baddi indina varege
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(arears); //  baki
						mmOutStream.write(rf);



						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(cap); //  ee tingala baddi
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fppcalabel); //  vinayiti
						mmOutStream.write(" /DL ADJ: ".getBytes());
						mmOutStream.write(UtilMaster.mdl_adj.trim().getBytes()); //  DL ADJ
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duty); //  terige
						mmOutStream.write(rf);


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);





						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(netamount); //  net amount
						mmOutStream.write(rf);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);


						
					/*	if(UtilMaster.getMtariff().equalsIgnoreCase("1")){
							
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write("0".getBytes()); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}
						else */
						
						int consumtionn = 0;
						float bm = 0f;
						
						if(UtilMaster.getMtariff().equalsIgnoreCase("2")){
							
							consumtionn = (int)(MasterLibraryFunction.doubRound(( Float.parseFloat(UtilMaster.mconsumption.trim())),0));
							bm = Float.parseFloat(UtilMaster.mno_of_months_issued.trim());
						}
						
						
					
					
					if(((consumtionn/bm < 18) && (UtilMaster.getMtariff().equalsIgnoreCase("2") ) ) || UtilMaster.getMtariff().equalsIgnoreCase("1")) {
						
						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						
						String grahkamotta = String.valueOf(UtilMaster.netamountForBJKJ - Float.parseFloat(UtilMaster.mec));
						
						mmOutStream.write(grahkamotta.getBytes()); //  grahakaru barisbekada motta
						mmOutStream.write(rf);
						
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(ec); //  sarkara barisbekada motta
						mmOutStream.write(rf);
						
					}
						
						
						
						/*
						if(UtilMaster.isBJKJLessThan18Units && (UtilMaster.getMtariff().equalsIgnoreCase("2")  || UtilMaster.getMtariff().equalsIgnoreCase("1")) ){
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							
							String grahkamotta = String.valueOf(UtilMaster.netamountForBJKJ - Float.parseFloat(UtilMaster.mec));
							
							mmOutStream.write(grahkamotta.getBytes()); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(ec); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}*/
						
						else{
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(linemin); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}
						


						

						mmOutStream.write("     Ver - ".getBytes());
						mmOutStream.write(UtilMaster. mdevicefirmwareversion.getBytes());
						mmOutStream.write(" IMEI :".getBytes());
						mmOutStream.write(subdivision);
						mmOutStream.write(rf);




						byte[] rf2 = new byte[5];
						rf2[0] = 0x1B;
						rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						if(UtilMaster.mconsumer_sc_no.trim().length() == 10){
							rf2[3] = 0x0A; // 10 DIGITS
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 11){
							rf2[3] = 0x0B; // 11 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 16){
							rf2[3] = 0x11; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x0C;
						}
						if(UtilMaster.mtotal.trim().length() == 14){
							rf2[3] = 0x0E; // 14 DIGITS
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 15 digits
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mtotal.trim().length() == 16){
							rf2[3] = 0x11; // 13 mconsumer_sc_no
						}else if(UtilMaster.mtotal.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x11; // 16 digits
						}


						rf2[4] = 0x41;


						mmOutStream.write(rf);	

						mmOutStream.write(rf2);

						mmOutStream.flush();

						mmOutStream.write("".getBytes());
						mmOutStream.write(((UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.write("-".getBytes());
						mmOutStream.write(netamount);
						mmOutStream.write("   ".getBytes());

						/*	mmOutStream.flush();*/










						mmOutStream.write("\n-".getBytes());
						
						mmOutStream.flush();
						//mmOutStream.write(u_space);
						/*mmOutStream.write(0x1D);
								mmOutStream.write(0x1B);
								mmOutStream.write(0x4B);
								mmOutStream.write(0x09);*/
						//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
						//mmOutStream.write(u_space);

						//SystemClock.sleep(1000);
						
						
						try {
							String val = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
							mvfwdmsg = val;
							mmOutStream.write(val.getBytes());
							mmOutStream.flush();
							int bytesRead = -1;
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							while (true) {
								String	message = "";
								System.out.println(">>>>>>>>>>> bytesRead  : "+bytesRead);
								//	System.out.println(">>>>>>>>>>>> mmInStream.read(buffer) :  "+mmInStream.read(buffer));
								bytesRead = mmInStream.read(buffer);


								if (bytesRead != -1) {
									while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
										message = message + new String(buffer, 0, bytesRead);
										bytesRead = mmInStream.read(buffer);
									}
									message = message + new String(buffer, 0, (bytesRead+(-1)));
									//handler.post(new MessagePoster(message,mmInStream ,mmOutStream));

									if(mvfwdmsg.contains(message)){
										try {			
											String msg = "";
											if(message.contains("F")){
												msg = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
											}else{
												msg = (char)0X1B+"QB250"+(char)0x0D+(char)0x0A;
											}					
											mvfwdmsg = msg.substring(1, msg.length());
											byte[] msgBuffer = msg.getBytes();
											mmOutStream.write(msgBuffer);		
											mmOutStream.flush();   
											//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}else{
										//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
										break ;
									}

									mmSocket.getInputStream();
								}
							}



							mmOutStream.flush();



						

						mmOutStream.flush();

						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);

						mmOutStream.write(0x0A);

						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.flush();



						// New changes for the bluetooth disconnection after the print


						} catch (IOException e) {
							Log.e(TAG, "Exception during write", e);
							FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				
			}
			
			// This is for analogic printing allignment
			
			else if (isKannadaPrint){
				
				
				
				try {
					

					//TODO
						/*r.GescomAllignemntNEW_WRTBlackmarandBarcodeIMEIVERSION(heading1, heading2, heading3, u_line, u_space, division,
								divisioname, subdivision, subdivisioname, billnolabel, billno,
								issuedate, duedate, consumerlabel, consumerid,
								consumeraddlabel, consumername, address, address1,
								billfromlabel, billfrom, billtolabel, billto, connecdatelabel,
								conndate, sacnload, phase, poleno, tariff, minchargeslabel,
								mincharges, linemin, meterno, avguintslabel, avgunits,
								foliolabel, foliono, readinglabel, currentrdng, previousrdng,
								mfunits, meterstatuslabel, meterstatus, meterrent,
								energychargelabel, ec, duty, fc, fppcalabel, fppca, cap,
								arears, sundrylabel, sundry, credit, misc, netamountlabel,
								netamount, departlabel, departlabel1, divisionlabel,
								divisiondep, subdivlabel, subdiv, receiptnolabel, receiptno,
								datelabel, date, billisslabel, billissuedate, duedatelabel,
								duedatedep, amountreceivedlabel, counternamelabel);*/



						/************* Bill *****************/			

						mmOutStream.flush();


						byte[] rf=new byte[1];
						rf[0]=0x0A;

						byte[] rfTAB=new byte[1];
						rfTAB[0]=0x09;

					

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write(rf);
						
						/*mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);


						mmOutStream.write(rf);



						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
					
						
						mmOutStream.write("  ".getBytes());

						mmOutStream.write(0x0047);
						mmOutStream.write(0x00A5);
						
						mmOutStream.write(" ".getBytes());
						
						mmOutStream.write(0x00AB);
						mmOutStream.write(0x00A8);
						mmOutStream.write(0x00C1);
						mmOutStream.write(0x0055);
						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x30);
						
						
						
						
						
						
						
						
						
						
						mmOutStream.write("                   :".getBytes());
						mmOutStream.write(heading1); // subdevision name
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);


						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
						
						
						mmOutStream.write("  ".getBytes());
						
						mmOutStream.write(0x00AA);
						
						mmOutStream.write(0x00C5);
						
						mmOutStream.write(0x00C1);
						
						//ABOVE 3 LINES FOR MA 
						
						mmOutStream.write(0x00BB); //HI
						mmOutStream.write(0x0077); //TI
						
						mmOutStream.write(" ".getBytes()); 
						
						mmOutStream.write(0x0076); // TA
						mmOutStream.write(0x00AC);//YA WRONG
						mmOutStream.write(0x0067);
						//RAA
						mmOutStream.write(0x00C1);
						
						mmOutStream.write(0x007A);//DA
						
						
						mmOutStream.write(" ".getBytes());
						
						mmOutStream.write(0x00A2);//DI
						mmOutStream.write(0x00A3);
						//NAA
						mmOutStream.write(0x00C1);
						
						mmOutStream.write(0x0041);// 0
						
						mmOutStream.write(0x0050);// KA
						
						
						mmOutStream.write(rf);
						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x30);
						
						mmOutStream.write("  :".getBytes());
						mmOutStream.write(UtilMaster.getMdataprepareddate().getBytes()); // data prepared date
						mmOutStream.write(rf);
						mmOutStream.write(rf);



						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
						
						mmOutStream.write("  ".getBytes());
						
						mmOutStream.write(0x00A9);//BI
						mmOutStream.write(0x00AE);//LA
						
						mmOutStream.write(" ".getBytes());
						
						mmOutStream.write(0x0050);
						// KO
						mmOutStream.write(0x00C6);
						
						mmOutStream.write(0x006C);
						//TTA
						mmOutStream.write(0x00D6);
						
						mmOutStream.write(" ".getBytes());
						
						mmOutStream.write(0x00A2);//DI
						mmOutStream.write(0x00A3); //NA
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x30);
						
						
						mmOutStream.write("          :".getBytes());
						mmOutStream.write(UtilMaster.mbilledatetimestamp.trim().getBytes()); // bill given date
						mmOutStream.write(rf);
						mmOutStream.write(rf);

						
						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
						

						mmOutStream.write("  ".getBytes());
						mmOutStream.write(0x64); //ja
						mmOutStream.write(0x0050); 
						// kaa
						mmOutStream.write(0x00C1);
						mmOutStream.write(0x0077); //ti
						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x30);
						
						mmOutStream.write("                           :".getBytes());// jakati/mumpramana
						
						
						
						
						mmOutStream.write(UtilMaster.mtariffdesc.trim().getBytes());
						mmOutStream.write("/".getBytes());
						mmOutStream.write(UtilMaster. mconnected_load.trim().getBytes());
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						
						
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
						
						
						mmOutStream.write(" ".getBytes());
						mmOutStream.write(0x00A9); //bi
						mmOutStream.write(0x00AE); //LA
						
						mmOutStream.write(" ".getBytes());
						
						mmOutStream.write(0x00A3);  //NA
						mmOutStream.write(0x0041); //0
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x30);
						
						
						mmOutStream.write("                    :".getBytes());
						mmOutStream.write(billno); // bill no
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  balakedarana sanke
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(heading3); //  RRNO
*/						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(consumername); //  consumer name 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address); //  address1 
						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address1); // address2 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  tc sanke 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(poleno); //  legder and pane sanke 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi dina 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi motta 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(issuedate); //  oduva dinanka 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duedate); //  due date
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(currentrdng); //  present reading
						mmOutStream.write(" - ".getBytes());
						mmOutStream.write(meterstatus);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.getMprevious_reading().trim().getBytes()); //  previous reading
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mfunits); //  consumtion
						mmOutStream.write(rf);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mincharges); //  mapaka stiranka
						mmOutStream.write(" / BM ".getBytes());
						mmOutStream.write(UtilMaster.mno_of_months_issued.trim().getBytes());



						mmOutStream.write(rf);
						mmOutStream.write(rf);
						/*mmOutStream.write(rf);*/


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fc); //  nigadit shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(ec); //  vidyut shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("0.0".getBytes()); //  marga kanishta


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(minchargeslabel); //  adika danda/ helo danda value added
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundrylabel); //  pfdanda
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundry); //  others
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.mold_interest.trim().getBytes()); //  baddi indina varege
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(arears); //  baki
						mmOutStream.write(rf);



						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(cap); //  ee tingala baddi
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fppcalabel); //  vinayiti
						mmOutStream.write(" /DL ADJ: ".getBytes());
						mmOutStream.write(UtilMaster.mdl_adj.trim().getBytes()); //  DL ADJ
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duty); //  terige
						mmOutStream.write(rf);


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);





						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(netamount); //  net amount
						mmOutStream.write(rf);

						/*mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);


						
						if(UtilMaster.getMtariff().equalsIgnoreCase("1")){
							
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write("0".getBytes()); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}else{
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(linemin); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}
						


						

						mmOutStream.write("     Ver - ".getBytes());
						mmOutStream.write(UtilMaster. mdevicefirmwareversion.getBytes());
						mmOutStream.write(" IMEI :".getBytes());
						mmOutStream.write(subdivision);
						mmOutStream.write(rf);




						byte[] rf2 = new byte[5];
						rf2[0] = 0x1B;
						rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						if(UtilMaster.mconsumer_sc_no.trim().length() == 10){
							rf2[3] = 0x0A; // 10 DIGITS
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 11){
							rf2[3] = 0x0B; // 11 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 16){
							rf2[3] = 0x11; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x0C;
						}
						if(UtilMaster.mtotal.trim().length() == 14){
							rf2[3] = 0x0E; // 14 DIGITS
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 15 digits
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mtotal.trim().length() == 16){
							rf2[3] = 0x11; // 13 mconsumer_sc_no
						}else if(UtilMaster.mtotal.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x11; // 16 digits
						}


						rf2[4] = 0x41;


						mmOutStream.write(rf);	

						mmOutStream.write(rf2);

						mmOutStream.flush();

						mmOutStream.write("".getBytes());
						mmOutStream.write("   ".getBytes());
						mmOutStream.write(((UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.write("-".getBytes());
						mmOutStream.write(netamount);
						mmOutStream.write("   ".getBytes());*/

						mmOutStream.flush();










					/*	mmOutStream.write("\n-".getBytes());
						
						mmOutStream.flush();
						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
								mmOutStream.write(0x1B);
								mmOutStream.write(0x4B);
								mmOutStream.write(0x09);
						//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
						//mmOutStream.write(u_space);

						//SystemClock.sleep(1000);
						
						
						try {
							String val = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
							mvfwdmsg = val;
							mmOutStream.write(val.getBytes());
							mmOutStream.flush();
							int bytesRead = -1;
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							while (true) {
								String	message = "";
								System.out.println(">>>>>>>>>>> bytesRead  : "+bytesRead);
								//	System.out.println(">>>>>>>>>>>> mmInStream.read(buffer) :  "+mmInStream.read(buffer));
								bytesRead = mmInStream.read(buffer);


								if (bytesRead != -1) {
									while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
										message = message + new String(buffer, 0, bytesRead);
										bytesRead = mmInStream.read(buffer);
									}
									message = message + new String(buffer, 0, (bytesRead+(-1)));
									//handler.post(new MessagePoster(message,mmInStream ,mmOutStream));

									if(mvfwdmsg.contains(message)){
										try {			
											String msg = "";
											if(message.contains("F")){
												msg = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
											}else{
												msg = (char)0X1B+"QB250"+(char)0x0D+(char)0x0A;
											}					
											mvfwdmsg = msg.substring(1, msg.length());
											byte[] msgBuffer = msg.getBytes();
											mmOutStream.write(msgBuffer);		
											mmOutStream.flush();   
											//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}else{
										//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
										break ;
									}

									mmSocket.getInputStream();
								}
							}



							mmOutStream.flush();



						

						mmOutStream.flush();

						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);

						mmOutStream.write(0x0A);

						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.flush();



						// New changes for the bluetooth disconnection after the print


						} catch (IOException e) {
							Log.e(TAG, "Exception during write", e);
							FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
						}*/
						
						
						
						
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						
						
						mmOutStream.flush();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				
				
				
				
				
				
				
				
				
				
				
			}
			
			else{
				

				
				
				
				try {
					

					//TODO
						/*r.GescomAllignemntNEW_WRTBlackmarandBarcodeIMEIVERSION(heading1, heading2, heading3, u_line, u_space, division,
								divisioname, subdivision, subdivisioname, billnolabel, billno,
								issuedate, duedate, consumerlabel, consumerid,
								consumeraddlabel, consumername, address, address1,
								billfromlabel, billfrom, billtolabel, billto, connecdatelabel,
								conndate, sacnload, phase, poleno, tariff, minchargeslabel,
								mincharges, linemin, meterno, avguintslabel, avgunits,
								foliolabel, foliono, readinglabel, currentrdng, previousrdng,
								mfunits, meterstatuslabel, meterstatus, meterrent,
								energychargelabel, ec, duty, fc, fppcalabel, fppca, cap,
								arears, sundrylabel, sundry, credit, misc, netamountlabel,
								netamount, departlabel, departlabel1, divisionlabel,
								divisiondep, subdivlabel, subdiv, receiptnolabel, receiptno,
								datelabel, date, billisslabel, billissuedate, duedatelabel,
								duedatedep, amountreceivedlabel, counternamelabel);*/



						/************* Bill *****************/			

						mmOutStream.flush();


						byte[] rf=new byte[1];
						rf[0]=0x0A;

						byte[] rfTAB=new byte[1];
						rfTAB[0]=0x09;



						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write(rf);
						
						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);


						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(heading1); // subdevision name
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);


						/*mmOutStream.write(rf);*/
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.getMdataprepareddate().getBytes()); // data prepared date
						mmOutStream.write(rf);


						
						/*mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						mmOutStream.write(0x1B);
						mmOutStream.write(0x32);
						mmOutStream.write(0x42);
						mmOutStream.write(0x6d);
						mmOutStream.write(0x7c);
						mmOutStream.write(0x4d);
						mmOutStream.write(0xc9);
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						mmOutStream.write("                        ".getBytes());
						
						
						mmOutStream.write(0x0043);
						mmOutStream.write(0x0044);
						
						mmOutStream.write(rf);
						
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(0x0045);
						mmOutStream.write(0x0046);
						mmOutStream.write(0x00BA);
						mmOutStream.write(0x00C6);
						
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						
						
						mmOutStream.write(0x0C85);
						
						mmOutStream.write(rf);
						
						mmOutStream.write(0x3206);
						
						mmOutStream.write(rf);
						
						
						
						mmOutStream.write(rf);
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						
						
						mmOutStream.write("OC85".getBytes());
						mmOutStream.write("OC85".getBytes());
						mmOutStream.write("OC85".getBytes());
						mmOutStream.write("OC85".getBytes());
						
					
						
						
						mmOutStream.write(rf);
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						
						
						
						
						
						mmOutStream.write(rf);
						mmOutStream.write(rf);
						*/
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.mbilledatetimestamp.trim().getBytes()); // bill given date
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());// jakati/mumpramana
						mmOutStream.write(UtilMaster.mtariffdesc.trim().getBytes());
						mmOutStream.write("/".getBytes());
						mmOutStream.write(UtilMaster. mconnected_load.trim().getBytes());
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(billno); // bill no
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  balakedarana sanke
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(heading3); //  RRNO
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(consumername); //  consumer name 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address); //  address1 
						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(address1); // address2 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  tc sanke 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(poleno); //  legder and pane sanke 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi dina 
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("-".getBytes()); //  hindin racidi motta 
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(issuedate); //  oduva dinanka 
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duedate); //  due date
						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(currentrdng); //  present reading
						mmOutStream.write(" - ".getBytes());
						mmOutStream.write(meterstatus);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.getMprevious_reading().trim().getBytes()); //  previous reading
						mmOutStream.write(rf);
						mmOutStream.write(rf);




						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mfunits); //  consumtion
						mmOutStream.write(rf);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);

						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(mincharges); //  mapaka stiranka
						mmOutStream.write(" / BM ".getBytes());
						mmOutStream.write(UtilMaster.mno_of_months_issued.trim().getBytes());



						mmOutStream.write(rf);
						mmOutStream.write(rf);
						/*mmOutStream.write(rf);*/


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fc); //  nigadit shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(ec); //  vidyut shulka
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write("0.0".getBytes()); //  marga kanishta


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(minchargeslabel); //  adika danda/ helo danda value added
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundrylabel); //  pfdanda
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(sundry); //  others
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(UtilMaster.mold_interest.trim().getBytes()); //  baddi indina varege
						mmOutStream.write(rf);



						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(arears); //  baki
						mmOutStream.write(rf);



						mmOutStream.write(rf);
						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(cap); //  ee tingala baddi
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(fppcalabel); //  vinayiti
						mmOutStream.write(" /DL ADJ: ".getBytes());
						mmOutStream.write(UtilMaster.mdl_adj.trim().getBytes()); //  DL ADJ
						mmOutStream.write(rf);




						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(duty); //  terige
						mmOutStream.write(rf);


						mmOutStream.write(rf);
						mmOutStream.write(rf);


						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1C);





						mmOutStream.write("                        ".getBytes());
						mmOutStream.write(netamount); //  net amount
						mmOutStream.write(rf);

						mmOutStream.write(0x1B);         
						mmOutStream.write(0x4B); 
						mmOutStream.write(0x0C);



						mmOutStream.write(0x1D);


						
						if(UtilMaster.getMtariff().equalsIgnoreCase("1")){
							
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write("0".getBytes()); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}else{
							
							mmOutStream.write(rf);
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(netamount); //  grahakaru barisbekada motta
							mmOutStream.write(rf);
							
							mmOutStream.write("                        ".getBytes());
							mmOutStream.write(linemin); //  sarkara barisbekada motta
							mmOutStream.write(rf);
							
						}
						


						

						mmOutStream.write("     Ver - ".getBytes());
						mmOutStream.write(UtilMaster. mdevicefirmwareversion.getBytes());
						mmOutStream.write(" IMEI :".getBytes());
						mmOutStream.write(subdivision);
						mmOutStream.write(rf);




						byte[] rf2 = new byte[5];
						rf2[0] = 0x1B;
						rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						if(UtilMaster.mconsumer_sc_no.trim().length() == 10){
							rf2[3] = 0x0A; // 10 DIGITS
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 11){
							rf2[3] = 0x0B; // 11 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 16){
							rf2[3] = 0x11; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mconsumer_sc_no.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x0C;
						}
						if(UtilMaster.mtotal.trim().length() == 14){
							rf2[3] = 0x0E; // 14 DIGITS
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 15 digits
						}else if(UtilMaster.mtotal.trim().length() == 15){
							rf2[3] = 0x0F; // 12 digits
						}else if(UtilMaster.mtotal.trim().length() == 16){
							rf2[3] = 0x11; // 13 mconsumer_sc_no
						}else if(UtilMaster.mtotal.trim().length() == 17){
							rf2[3] = 0x12; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 18){
							rf2[3] = 0x13; // 13 digits
						}else if(UtilMaster.mtotal.trim().length() == 19){
							rf2[3] = 0x14; // 13 digits
						}else{
							rf2[3] = 0x11; // 16 digits
						}


						rf2[4] = 0x41;


						mmOutStream.write(rf);	

						mmOutStream.write(rf2);

						mmOutStream.flush();

						mmOutStream.write("".getBytes());
						mmOutStream.write("   ".getBytes());
						mmOutStream.write(((UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.write("-".getBytes());
						mmOutStream.write(netamount);
						/*mmOutStream.write("   ".getBytes());*/

						mmOutStream.flush();










					/*	mmOutStream.write("\n-".getBytes());
						
						mmOutStream.flush();
						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
								mmOutStream.write(0x1B);
								mmOutStream.write(0x4B);
								mmOutStream.write(0x09);
						//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
						//mmOutStream.write(u_space);

						//SystemClock.sleep(1000);
						
						
						try {
							String val = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
							mvfwdmsg = val;
							mmOutStream.write(val.getBytes());
							mmOutStream.flush();
							int bytesRead = -1;
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							while (true) {
								String	message = "";
								System.out.println(">>>>>>>>>>> bytesRead  : "+bytesRead);
								//	System.out.println(">>>>>>>>>>>> mmInStream.read(buffer) :  "+mmInStream.read(buffer));
								bytesRead = mmInStream.read(buffer);


								if (bytesRead != -1) {
									while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
										message = message + new String(buffer, 0, bytesRead);
										bytesRead = mmInStream.read(buffer);
									}
									message = message + new String(buffer, 0, (bytesRead+(-1)));
									//handler.post(new MessagePoster(message,mmInStream ,mmOutStream));

									if(mvfwdmsg.contains(message)){
										try {			
											String msg = "";
											if(message.contains("F")){
												msg = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
											}else{
												msg = (char)0X1B+"QB250"+(char)0x0D+(char)0x0A;
											}					
											mvfwdmsg = msg.substring(1, msg.length());
											byte[] msgBuffer = msg.getBytes();
											mmOutStream.write(msgBuffer);		
											mmOutStream.flush();   
											//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}else{
										//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
										break ;
									}

									mmSocket.getInputStream();
								}
							}



							mmOutStream.flush();



						

						mmOutStream.flush();

						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);

						mmOutStream.write(0x0A);

						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.flush();



						// New changes for the bluetooth disconnection after the print


						} catch (IOException e) {
							Log.e(TAG, "Exception during write", e);
							FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
						}*/
						
						
						
						
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						
						
						mmOutStream.flush();
						
						
						
						
						
					
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				
				
				
				
				
				
				
				
				
				
				
			
				
				
				
				
				
				
			}
			
			
			
			
			
			









			return null;
		}
		
	
@Override
protected void onPostExecute(Void result) {
	super.onPostExecute(result);
	
	try {
		if(mmOutStream!=null){
			mmOutStream.close();
		}
		if(mmSocket!=null){
			mmSocket.close();
		}
		if(mmInStream!=null){
			mmInStream.close();
		}
		
		Movetobilledactivity(context,this.barProgressBar);		
	} catch (Exception e) {
	e.printStackTrace();
	}
	
}
	}
	



	/**
	 * Indicate that the connection attempt failed and notify the UI Activity.
	 */
	private void connectionFailed() {
		setState(STATE_LISTEN);

		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(BluetoothChat.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat.TOAST, "Unable to connect device");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	/**
	 * Indicate that the connection was lost and notify the UI Activity.
	 */
	private void connectionLost() {
		setState(STATE_LISTEN);

		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(BluetoothChat.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat.TOAST, "Device connection was lost");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	/**
	 * This thread runs while listening for incoming connections. It behaves
	 * like a server-side client. It runs until a connection is accepted (or
	 * until cancelled).
	 */
	private class AcceptThread extends Thread {
		// The local server socket
		private final BluetoothServerSocket mmServerSocket;

		public AcceptThread() {
			BluetoothServerSocket tmp = null;

			// Create a new listening server socket
			try {
				tmp = mAdapter
						.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
			} catch (IOException e) {
				FilePropertyManager.appendLog(Log.getStackTraceString(e));

			}
			mmServerSocket = tmp;
		}

		public void run() {
			if (D)
				Log.d(TAG, "BEGIN mAcceptThread" + this);
			setName("AcceptThread");
			BluetoothSocket socket = null;

			// Listen to the server socket if we're not connected
			while (mState != STATE_CONNECTED) {
				try {
					// This is a blocking call and will only return on a
					// successful connection or an exception
					
					//if(mmServerSocket!=null) //BY SHIVA
					//socket = mmServerSocket.accept();
				} catch (Exception e) {
					Log.e(TAG, "accept() failed", e);
					FilePropertyManager.appendLog(Log.getStackTraceString(e));
					break;
				}

				// If a connection was accepted
				if (socket != null) {
					synchronized (KannadaBluetoothChatService.this) {
						switch (mState) {
						case STATE_LISTEN:
						case STATE_CONNECTING:
							// Situation normal. Start the connected thread.
							connected(socket, socket.getRemoteDevice());
							break;
						case STATE_NONE:
						case STATE_CONNECTED:
							// Either not ready or already connected. Terminate
							// new socket.
							try {
								socket.close();
							} catch (IOException e) {
								Log.e(TAG, "Could not close unwanted socket", e);
								FilePropertyManager.appendLog(Log.getStackTraceString(e));
							}
							break;
						}
					}
				}
			}
			if (D)
				Log.i(TAG, "END mAcceptThread");
		}

		public void cancel() {
			if (D)
				Log.d(TAG, "cancel " + this);
			try {
				mmServerSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of server failed", e);
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}
		}
	}

	/**
	 * This thread runs while attempting to make an outgoing connection with a
	 * device. It runs straight through; the connection either succeeds or
	 * fails.
	 */
	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			mmDevice = device;
			BluetoothSocket tmp = null;

			// Get a BluetoothSocket for a connection with the
			// given BluetoothDevice
			try {
				tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
			} catch (IOException e) {
				Log.e(TAG, "create() failed", e);
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}
			mmSocket = tmp;
		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectThread");
			setName("ConnectThread");

			// Always cancel discovery because it will slow down a connection
			mAdapter.cancelDiscovery();

			// Make a connection to the BluetoothSocket
			try {
				// This is a blocking call and will only return on a
				// successful connection or an exception
				mmSocket.connect();
			} catch (IOException e) {
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
				connectionFailed();
				// Close the socket
				try {
					mmSocket.close();
				} catch (IOException e2) {
					Log.e(TAG,
							"unable to close() socket during connection failure",
							e2);
					FilePropertyManager.appendLog(Log.getStackTraceString(e));
				}
				// Start the service over to restart listening mode
				KannadaBluetoothChatService.this.start();
				return;
			}

			// Reset the ConnectThread because we're done
			synchronized (KannadaBluetoothChatService.this) {
				mConnectThread = null;
			}

			// Start the connected thread
			connected(mmSocket, mmDevice);
		}

		public void cancel() {
			try {
				mmSocket.close(); 
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}
		}
	}

	/**
	 * This thread runs during a connection with a remote device. It handles all
	 * incoming and outgoing transmissions.
	 */
	private class ConnectedThread extends Thread {
	

		public ConnectedThread(BluetoothSocket socket) {
			Log.d(TAG, "create ConnectedThread");
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			// Get the BluetoothSocket input and output streams
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
				Log.e(TAG, "temp sockets not created", e);
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
			/*mmInStream_glb = mmInStream ;
			mmOutStream_glb =mmOutStream ;
			mmSocket_glb =mmSocket;
			BluetoothSocketListener bsl = new BluetoothSocketListener(mmSocket_glb,handler,mmInStream_glb,mmOutStream_glb);
			Thread messageListener = new Thread(bsl);
			messageListener.start();*/

		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectedThread");
			byte[] buffer = new byte[1024];
			int bytes;

			// Keep listening to the InputStream while connected
			while (true) {
				try {
					// Read from the InputStream
					bytes = mmInStream.read(buffer);

					// Send the obtained bytes to the UI Activity
					mHandler.obtainMessage(BluetoothChat.MESSAGE_READ, bytes,
							-1, buffer).sendToTarget();
				} catch (IOException e) {
					Log.e(TAG, "disconnected", e);
					FilePropertyManager.appendLog(Log.getStackTraceString(e));
					connectionLost();

					break;
				}
			}
		}

	
	
		//TODO
		public void GescomAllignemntNEW_WRTBlackmarandBarcodeIMEIVERSION( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
				byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
				byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
				byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
				byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
				byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
				byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
				byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
				) {



			try {

				/************* Bill *****************/			

				mmOutStream.flush();


				byte[] rf=new byte[1];
				rf[0]=0x0A;

				byte[] rfTAB=new byte[1];
				rfTAB[0]=0x09;



				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1D);

				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1C);


				mmOutStream.write(rf);


				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(heading1); // subdevision name
				mmOutStream.write(rf);


				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1D);


				/*mmOutStream.write(rf);*/
				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(UtilMaster.getMdataprepareddate().getBytes()); // data prepared date
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(UtilMaster.mbilledatetimestamp.trim().getBytes()); // bill given date
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());// jakati/mumpramana
				mmOutStream.write(UtilMaster.mtariffdesc.trim().getBytes());
				mmOutStream.write("/".getBytes());
				mmOutStream.write(UtilMaster. mconnected_load.trim().getBytes());
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(billno); // bill no
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write("-".getBytes()); //  balakedarana sanke
				mmOutStream.write(rf);
				mmOutStream.write(rf);




				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1C);

				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(heading3); //  RRNO
				mmOutStream.write(rf);


				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1D);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write((UtilMaster.mconsumer_name).getBytes()); //  consumer name 
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write((UtilMaster.maddress).getBytes()); //  address1 
				mmOutStream.write(rf);
				mmOutStream.write("                        ".getBytes());
				mmOutStream.write((UtilMaster.maddress1).getBytes()); // address2 
				mmOutStream.write(rf);
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write("-".getBytes()); //  tc sanke 
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(poleno); //  legder and pane sanke 
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write("-".getBytes()); //  hindin racidi dina 
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write("-".getBytes()); //  hindin racidi motta 
				mmOutStream.write(rf);
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(issuedate); //  oduva dinanka 
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(duedate); //  due date
				mmOutStream.write(rf);
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(currentrdng); //  present reading
				mmOutStream.write(" - ".getBytes());
				mmOutStream.write(meterstatus);
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(UtilMaster.getMprevious_reading().trim().getBytes()); //  previous reading
				mmOutStream.write(rf);
				mmOutStream.write(rf);




				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1C);

				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(mfunits); //  consumtion
				mmOutStream.write(rf);

				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1D);

				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(mincharges); //  mapaka stiranka
				mmOutStream.write(" / BM ".getBytes());
				mmOutStream.write(UtilMaster.mno_of_months_issued.trim().getBytes());



				mmOutStream.write(rf);
				mmOutStream.write(rf);
				/*mmOutStream.write(rf);*/


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(fc); //  nigadit shulka
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(ec); //  vidyut shulka
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write("0.0".getBytes()); //  marga kanishta


				mmOutStream.write(rf);
				mmOutStream.write(rf);


				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(minchargeslabel); //  adika danda/ helo danda value added
				mmOutStream.write(rf);




				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(sundrylabel); //  pfdanda
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(sundry); //  others
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(UtilMaster.mold_interest.trim().getBytes()); //  baddi indina varege
				mmOutStream.write(rf);



				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(arears); //  baki
				mmOutStream.write(rf);



				mmOutStream.write(rf);
				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(cap); //  ee tingala baddi
				mmOutStream.write(rf);




				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(fppcalabel); //  vinayiti
				mmOutStream.write(" /DL ADJ: ".getBytes());
				mmOutStream.write(UtilMaster.mdl_adj.trim().getBytes()); //  DL ADJ
				mmOutStream.write(rf);




				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(duty); //  terige
				mmOutStream.write(rf);






				mmOutStream.write(rf);
				mmOutStream.write(rf);


				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1C);





				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(netamount); //  net amount
				mmOutStream.write(rf);

				mmOutStream.write(0x1B);         
				mmOutStream.write(0x4B); 
				mmOutStream.write(0x0C);



				mmOutStream.write(0x1D);

				mmOutStream.write(rf);
				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(netamount); //  grahakaru barisbekada motta
				mmOutStream.write(rf);





				mmOutStream.write("                        ".getBytes());
				mmOutStream.write(linemin); //  sarkara barisbekada motta
				mmOutStream.write(rf);

				mmOutStream.write("     Ver - ".getBytes());
				mmOutStream.write(UtilMaster. mdevicefirmwareversion.getBytes());
				mmOutStream.write(" IMEI - :".getBytes());
				mmOutStream.write(subdivision);
				mmOutStream.write(rf);




				byte[] rf2 = new byte[5];
				rf2[0] = 0x1B;
				rf2[1] = 0x7A;// z without human readable text
				// rf2[1] = 0x5A; // Z  ASCII human readable text 
				rf2[2] = 0x32;
				// rf2[3] = 0x0A;
				if(UtilMaster.mconsumer_sc_no.trim().length() == 10){
					rf2[3] = 0x0A; // 10 DIGITS
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 11){
					rf2[3] = 0x0B; // 11 digits
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 15){
					rf2[3] = 0x0F; // 12 digits
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 16){
					rf2[3] = 0x11; // 13 digits
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 17){
					rf2[3] = 0x12; // 13 digits
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 18){
					rf2[3] = 0x13; // 13 digits
				}else if(UtilMaster.mconsumer_sc_no.trim().length() == 19){
					rf2[3] = 0x14; // 13 digits
				}else{
					rf2[3] = 0x0C;
				}
				if(UtilMaster.mtotal.trim().length() == 14){
					rf2[3] = 0x0E; // 14 DIGITS
				}else if(UtilMaster.mtotal.trim().length() == 15){
					rf2[3] = 0x0F; // 15 digits
				}else if(UtilMaster.mtotal.trim().length() == 15){
					rf2[3] = 0x0F; // 12 digits
				}else if(UtilMaster.mtotal.trim().length() == 16){
					rf2[3] = 0x11; // 13 mconsumer_sc_no
				}else if(UtilMaster.mtotal.trim().length() == 17){
					rf2[3] = 0x12; // 13 digits
				}else if(UtilMaster.mtotal.trim().length() == 18){
					rf2[3] = 0x13; // 13 digits
				}else if(UtilMaster.mtotal.trim().length() == 19){
					rf2[3] = 0x14; // 13 digits
				}else{
					rf2[3] = 0x11; // 16 digits
				}


				rf2[4] = 0x41;


				mmOutStream.write(rf);	

				mmOutStream.write(rf2);

				mmOutStream.flush();

				mmOutStream.write("".getBytes());
				mmOutStream.write(((UtilMaster.mconsumer_sc_no.trim())).getBytes());
				mmOutStream.write("-".getBytes());
				mmOutStream.write(netamount);
				mmOutStream.write("   ".getBytes());

				/*	mmOutStream.flush();*/










				mmOutStream.write("\n---------".getBytes());
				
				mmOutStream.flush();
				//mmOutStream.write(u_space);
				/*mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);*/
				//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
				//mmOutStream.write(u_space);

				try {
					String val = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
					mvfwdmsg = val;
					mmOutStream.write(val.getBytes());
					mmOutStream.flush();
					int bytesRead = -1;
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];
					while (true) {
						String	message = "";
						System.out.println(">>>>>>>>>>> bytesRead  : "+bytesRead);
						//	System.out.println(">>>>>>>>>>>> mmInStream.read(buffer) :  "+mmInStream.read(buffer));
						bytesRead = mmInStream.read(buffer);


						if (bytesRead != -1) {
							while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
								message = message + new String(buffer, 0, bytesRead);
								bytesRead = mmInStream.read(buffer);
							}
							message = message + new String(buffer, 0, (bytesRead+(-1)));
							//handler.post(new MessagePoster(message,mmInStream ,mmOutStream));

							if(mvfwdmsg.contains(message)){
								try {			
									String msg = "";
									if(message.contains("F")){
										msg = (char)0X1B+"QF250"+(char)0x0D+(char)0x0A;
									}else{
										msg = (char)0X1B+"QB250"+(char)0x0D+(char)0x0A;
									}					
									mvfwdmsg = msg.substring(1, msg.length());
									byte[] msgBuffer = msg.getBytes();
									mmOutStream.write(msgBuffer);		
									mmOutStream.flush();   
									//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								//Toast.makeText(context, mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
								break ;
							}

							mmSocket.getInputStream();
						}
					}



					mmOutStream.flush();



				} catch (IOException e) {
					Log.e(TAG, "Exception during write", e);
					FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
				}

				mmOutStream.flush();

				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);



				/*	----DL ADJ---- */
				//	mmOutStream.write(0x0A);


				//	mmOutStream.write(0x0A);
				/*mmOutStream.write(0x20);
						mmOutStream.write(credit);


						----NET ---- 
						//mmOutStream.write(0x09);

						mmOutStream.write(netamount);

						----TAX---- 

						mmOutStream.write(linemin);*/
				mmOutStream.write(0x0A);
				//mmOutStream.write(0x0A);





				//mmOutStream.write(u_space);
				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.flush();



				// New changes for the bluetooth disconnection after the print

				if(mmOutStream!=null){
					mmOutStream.close();
				}
				if(mmSocket!=null){
					mmSocket.close();
				}
				if(mmInStream!=null){
					mmInStream.close();
				}


			} catch (IOException e) {
			//	Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}





		}

		public void cancel() 
		{
			try 
			{
				mmSocket.close();
			} catch (IOException e) 
			{
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}
	}
	public void Movetobilledactivity(final Context context,final ProgressDialog barProgressBar)
	{
		DatabaseHandler databasehandler;
		 Logger logger = null ;

		try {
			if(logger == null){
				logger = MasterLibraryFunction.getlogger(context, TAG);
			}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			logger.error("logger is NULL");
		}

		System.out.println("Movetobilledactivity  ");


		if( UtilMaster. mbilledstatus.equalsIgnoreCase("0") ){

			databasehandler = new DatabaseHandler(context);
			try {
				
				
				System.out.println("Coming hereeeeeeeeeeeeeeeeeeeeeeeeeee for printing-----");
				


				// checking login details in sqlite db if once already
				//logded


				byte[] img = "".getBytes();


				if(UtilMaster.isPhotoEnable()){

/*
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
					bm = BluetoothChat.getResizedBitmap(bm, (int)150, (int)200);
					System.out.println( "2nd  bm2.getWidth() :"+ bm.getWidth() +" && bm2.getHeight() "+bm.getHeight());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					bm.compress(Bitmap.CompressFormat.PNG, 10 , baos);  
					img = baos.toByteArray();*/
					
					img=CustomCamera.getImageByteArray();
					CustomCamera.setImageByteArray(null);
					Toast.makeText(context, img.length+" image length", Toast.LENGTH_SHORT).show();
					
					UtilMaster.mImagePath =CustomCamera.getImagePath();
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
						, UtilMaster.maverage_consumption.trim(), UtilMaster.phoneNumber.trim(), UtilMaster.deviceInfo
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

		databasehandler = new DatabaseHandler(context);

		try {


			// getting rrno,billed and unbilled count




			databasehandler.openToRead();
			final boolean isMrvalid = databasehandler.isUserValid(	UtilMaster.mGLogin_SiteCode, UtilMaster.mGLogin_MRCode);
			databasehandler.close();



			// adding delay while printing
			new Handler().postDelayed(new Runnable() {
				public void run() {

					if (isMrvalid) {
						barProgressBar.dismiss();
						context.startActivity(new Intent(context,SearchActivity.class));
					} else {
						new AlertDialog.Builder(context)
						.setTitle("Info")
						.setMessage(
								"Please download the  new data. No more consumer to bill for this meter reader code")
								.setPositiveButton("OK",  new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,int which) 
									{
										barProgressBar.dismiss();
										context.startActivity(new Intent(context,	MainActivity.class));
									}
								}).show();
					}



				}
			}, 1);



		} catch (Exception e) {
			logger.error(Log.getStackTraceString(e));
			e.printStackTrace();
			new Handler().postDelayed(new Runnable() {
				public void run() {


					new AlertDialog.Builder(context)
					.setTitle("Info")
					.setMessage(
							"Consumer details is not correct or unable to bill , Please try again later ")
							.setPositiveButton("OK",  new DialogInterface.OnClickListener()
							{

								public void onClick(DialogInterface dialog,int which) 
								{
									barProgressBar.dismiss();
									context.startActivity(new Intent(context,MainActivity.class));
								}
							}).show();




				}
			}, 1);

		}






	}
}
