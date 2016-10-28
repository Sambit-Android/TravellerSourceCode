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

package com.bcits.bsmartbilling;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.utils.Demo;

import Utils.FilePropertyManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


/******************* DO NOT delete any of the line (Even commented lines)********************************/
/**
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for incoming
 * connections, a thread for connecting with a device, and a thread for
 * performing data transmissions when connected.
 */
public class BluetoothChatServiceOLD  
{
	String mvfwdmsg  ;
	Handler handler = new Handler() ;
	
	
	
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	/*private static String BackUP_DATABASE_NAME = dir
			+ "/JVVNL/JVVNLNOTINSERTED.db";*/
	// Debugging
	private static final String TAG = "BluetoothChatService";
	private static final boolean D = true;
	// Name for the SDP record when creating server socket
	private static final String NAME = "BluetoothChat";

	// Unique UUID for this application
	private static final UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");

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
	public BluetoothChatServiceOLD(Context context, Handler handler) {
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
	public void write(Context context, byte[] heading1, byte[] heading2,
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
			byte[] counternamelabel) {
		// Create temporary object
		ConnectedThread r;
		// Synchronize a copy of the ConnectedThread
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		// Perform the write unsynchronized
		
	
		r.write_newWRTtheVersion121(heading1, heading2, heading3, u_line, u_space, division,
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
				duedatedep, amountreceivedlabel, counternamelabel);
	
	
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
					//by shiva
					//socket = mmServerSocket.accept();
				} catch (Exception e) {
					Log.e(TAG, "accept() failed", e);
					FilePropertyManager.appendLog(Log.getStackTraceString(e));
					break;
				}

				// If a connection was accepted
				if (socket != null) {
					synchronized (BluetoothChatServiceOLD.this) {
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
				BluetoothChatServiceOLD.this.start();
				return;
			}

			// Reset the ConnectThread because we're done
			synchronized (BluetoothChatServiceOLD.this) {
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
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

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

		/**
		 * Write to the connected OutStream.
		 * 
		 * @param buffer
		 *            The bytes to write
		 */
		
		public void write(byte[] heading1, byte[] heading2, byte[] heading3,
				byte[] u_line, byte[] u_space, byte[] division,
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
				byte[] meterrent, byte[] energychargelabel, byte[] ec,
				byte[] duty, byte[] fc, byte[] fppcalabel, byte[] fppca,
				byte[] cap, byte[] arears, byte[] sundrylabel, byte[] sundry,
				byte[] credit, byte[] misc, byte[] netamountlabel,
				byte[] netamount, byte[] departlabel, byte[] departlabel1,
				byte[] divisionlabel, byte[] divisiondep, byte[] subdivlabel,
				byte[] subdiv, byte[] receiptnolabel, byte[] receiptno,
				byte[] datelabel, byte[] date, byte[] billisslabel,
				byte[] billissuedate, byte[] duedatelabel, byte[] duedatedep,
				byte[] amountreceivedlabel, byte[] counternamelabel) {

			try {

				/*byte[] rf3 = new byte[4];
				rf3[0] = 0x1B;
				rf3[1] = 0x51;
				rf3[2] = 0x46;
				rf3[3] = 0x01;*/

				/* heading */
				/*        ***Bhaskar****           mmOutStream.write(0x1B);
				    mmOutStream.write(0x2B);
				    mmOutStream.write(0x00);
				*/ 
				
	/*******Black mark detection ***********/			
				
				
			/*	byte[] rf3 = new byte[4];
				rf3[0] = 0x1B;
				rf3[1] = 0x51;
				rf3[2] = 0x19;
				rf3[3] = 0x19;
				
				mmOutStream.write(rf3);*/
				
	/*************Bill *****************/			
				mmOutStream.write(0x1B);

				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
		
				mmOutStream.write(divisioname);

				mmOutStream.write(subdivision);

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x08);
				//mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
				mmOutStream.write(billfrom);
				mmOutStream.write(billto);
				mmOutStream.write(billno);
				

			//	mmOutStream.write(u_space);   ***old 
			//	mmOutStream.write(billnolabel);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
				mmOutStream.write(billnolabel);
				mmOutStream.write(consumerid);
				mmOutStream.write(billtolabel);

				/* Consumer Name ****** */

			//	mmOutStream.write(u_space);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x08);
				
				mmOutStream.write(consumername);
				mmOutStream.write(address);
				
				mmOutStream.write(address1);

				
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x0B);
				mmOutStream.write(u_space);
			

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
				mmOutStream.write(u_space);
				mmOutStream.write(meterno);
				mmOutStream.write(poleno);
				mmOutStream.write(phase);
				mmOutStream.write(tariff);
				mmOutStream.write(u_space);

				mmOutStream.write(u_space);
				mmOutStream.write(conndate);
				mmOutStream.write(sacnload);
				mmOutStream.write(linemin);
				mmOutStream.write(avgunits);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
				mmOutStream.write(u_space);
				mmOutStream.write(currentrdng);
				mmOutStream.write(previousrdng);
				mmOutStream.write(mincharges);
				mmOutStream.write(mfunits);
				
				mmOutStream.write(u_space);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
				mmOutStream.write(meterstatus);
				mmOutStream.write(consumerlabel);
				mmOutStream.write(energychargelabel);
			//	mmOutStream.write(0x1B);
			//	mmOutStream.write(0x4B);
			//	mmOutStream.write(0x09);
				mmOutStream.write(u_space);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x09);
		
				/* FIXED , ENERGY CHARGERS BLOCK ****** */

				mmOutStream.write(fc);
				mmOutStream.write(ec);
				mmOutStream.write(fppca);
				mmOutStream.write(u_space);

				mmOutStream.write(u_space);
				mmOutStream.write(meterrent);
				mmOutStream.write(duty);
				mmOutStream.write(cap);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);

				mmOutStream.write(sundry);
				mmOutStream.write(sundrylabel);
				mmOutStream.write(fppcalabel);
				mmOutStream.write(u_space);

				mmOutStream.write(u_space);

				mmOutStream.write(misc);
				mmOutStream.write(credit);
				mmOutStream.write(arears);
				mmOutStream.write(u_space);

/* ISSUE DATE AND NET AMOUNT BLOCK ****** */

				mmOutStream.write(issuedate);
				mmOutStream.write(duedate);

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x08);
				mmOutStream.write(0x1D);

				//mmOutStream.write(0x1C);
				
				mmOutStream.write(u_space);
				mmOutStream.write(foliono);

				mmOutStream.write(netamount);
				

			
				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x04);
			
			/***********/	
				mmOutStream.write(u_space);
				/***********/		
				mmOutStream.write(u_space);
				
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x08);
				mmOutStream.write(meterstatuslabel);
				mmOutStream.write(divisiondep);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
	//old 	**		mmOutStream.write(divisiondep);
				
				mmOutStream.write(consumerid);
				mmOutStream.write(billtolabel);	
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
				mmOutStream.write(receiptno);
				mmOutStream.write(date);

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x0B);
				mmOutStream.write(0x1D);
				

				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x08);

				//mmOutStream.write(0x1C);
				mmOutStream.write(u_space);
				mmOutStream.write(foliono);
				mmOutStream.write(netamount);
				mmOutStream.write("\n  .".getBytes());
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				mmOutStream.write(u_space);
				
			//	 mmOutStream.write(0x1D);
			
			/*	 int i = BluetoothChat.print_series ;
					
					*//******* barcode *************************//*
					
					 byte[] rf2 = new byte[5];
					 rf2[0] = 0x1B;
					 rf2[1] = 0x7A;
					 rf2[2] = 0x32;
					 rf2[3] = 0x0B;
					
					 if(!(((i-1) % 4) == 0))
					 {
						 rf2[4] = 0x41;
					
					 }else if ((((i-1) % 4) == 0) && ((i-1) > 0) )
					 {
						 rf2[4] = 0x51;
					 } else
					 {
						rf2[4] = 0x41;
					 }
					
					mmOutStream.write(rf2);

					mmOutStream.flush();

					
					mmOutStream.write((Consumer.CONSUMER_SC_NO.trim()).getBytes());
					mmOutStream.flush();*/
				
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x02);
					mmOutStream.write("\n .. ".getBytes());
					mmOutStream.write(u_space);
					mmOutStream.write("\n ......... ".getBytes());
				
					
					/*mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0B);
					mmOutStream.write("\n  ".getBytes());*/
				
					//mmOutStream.flush();
					
					
				
					
					

					
	/**  PRAVEEN **/				
					//mmOutStream.write("\n   .".getBytes());
					
								
				/*	 if(((i-1) % 4) == 0)
					{
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x08);
						mmOutStream.write("  .".getBytes());
						
					}else
					{
							
							mmOutStream.write("\n  .".getBytes());
							
					}	*/
					/*
					 if(( (i-1) > 10)  &&((((i-1) % 10) == 0)))
						{
						
							mmOutStream.write(0x1B);
							mmOutStream.write(0x4B);
							mmOutStream.write(0x0B);
						//	mmOutStream.write(0x04); edited
							mmOutStream.write("\n  ".getBytes());
						
						}*/
	/***END****/	
	
				
				/*      **********Bhaskar******** mmOutStream.write(0x1B);
				    							  mmOutStream.write(0x63);
				    							  mmOutStream.write(0x1B);
				    							  mmOutStream.write(0x2B);
				    							  mmOutStream.write(0x00);
*/
					
					
					
					
					mmOutStream.flush();
					if(mmOutStream!=null){
						mmOutStream.close();
					}
					if(mmSocket!=null){
						mmSocket.close();
					}
					

				} catch (IOException e) {
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

		}

		  public void writeplainStationery( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
	            
	            	
	            	
	            try {

					
		/*************Bill *****************/			
			/*		mmOutStream.write(0x1B);

					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					mmOutStream.write(heading1);
					mmOutStream.write(heading2);
					mmOutStream.write(heading3);
					mmOutStream.write(u_line);
					
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0C);
					mmOutStream.write(division);
					mmOutStream.write(divisioname);

					mmOutStream.write(subdivision);
					
					
					
					
					mmOutStream.write(u_space);*/
					
	            	mmOutStream.write(0x1B);

					mmOutStream.write(0x4B);
					mmOutStream.write(0x05);
				//	mmOutStream.write(u_line);
					mmOutStream.write(heading1);
					mmOutStream.write(heading2);
					mmOutStream.write(heading3);
					//mmOutStream.write("\n ______________________________________".getBytes());
					mmOutStream.write("\n --------------------------------------".getBytes());
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0C);
					mmOutStream.write(0x1C);
				//	mmOutStream.write(division);
					mmOutStream.write(divisioname);

					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0C);
				//	mmOutStream.write(" ____________________________________________".getBytes());
					mmOutStream.write(" --------------------------------------------".getBytes());
					
					
				/*	mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x09);*/
					
					
					mmOutStream.write(subdivision);
					mmOutStream.write(billfromlabel);
					mmOutStream.write(billfrom);
					mmOutStream.write(billto);
					mmOutStream.write(billno);
				
					mmOutStream.write(billnolabel);
					
				
					/*mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);*/
					mmOutStream.write(0x03);
				//	mmOutStream.write("\n 12345678901234567890123456789012345678901234567890123456".getBytes());
					mmOutStream.write(consumerid);
				//	mmOutStream.write(billtolabel);
					/*mmOutStream.write(u_space);*/
					/* Consumer Name ****** */

				
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0C);
				//	mmOutStream.write("\n Consumer Name and Address ".getBytes());
					mmOutStream.write(consumername);
					mmOutStream.write(address);
					
					mmOutStream.write(address1);
					
					
		
				

					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x0C);
				
					//mmOutStream.write("\n Mtr NO:".getBytes());
					
				//	mmOutStream.write("\n 123456789012345678901234567890123456789012345678".getBytes());
					System.out.println("******************************length : "+" 12345678901234567890123456789012345678901234567890123456".length());
					
					mmOutStream.write("\n METER NO : ".getBytes());
					mmOutStream.write(meterno);
			
					
				
					mmOutStream.write(" POLE NO : ".getBytes());
					mmOutStream.write(poleno);
					
					
					mmOutStream.write("\n TARIFF   : ".getBytes());
					mmOutStream.write(tariff);
					mmOutStream.write(" LOAD    : ".getBytes());
					mmOutStream.write(sacnload);
					
					
					mmOutStream.write("\n LINE MIN : ".getBytes());
					mmOutStream.write(linemin);
					
					mmOutStream.write(" AVERAGE : ".getBytes());
					mmOutStream.write(avgunits);
					
					mmOutStream.write("\n CON DATE : ".getBytes());
					mmOutStream.write(conndate);
					
					mmOutStream.write(" PH      : ".getBytes());
					mmOutStream.write(phase);
					
					mmOutStream.write("\n CUR RDNG : ".getBytes());
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(" PRV RDNG: ".getBytes());
					mmOutStream.write(previousrdng);
					
					
					mmOutStream.write("\n UNITS    : ".getBytes());
					mmOutStream.write(mfunits);
					mmOutStream.write(" MC UNITS: ".getBytes());
					mmOutStream.write(energychargelabel);
					
					
					mmOutStream.write("\n STATUS   : ".getBytes());
					mmOutStream.write(meterstatus);
					mmOutStream.write(" MF      : ".getBytes());
					mmOutStream.write(mincharges);
				
					mmOutStream.write("\n PF       : ".getBytes());
					mmOutStream.write(consumerlabel);
					
					mmOutStream.write(" MTR RENT: ".getBytes());
					mmOutStream.write(meterrent);
					
					
					
					
					mmOutStream.write("\n FIXED CH : ".getBytes());
					mmOutStream.write(fc);
					
					
					mmOutStream.write(" FPPCA   : ".getBytes());
					mmOutStream.write(fppca);
					
					
					
					
					
					
					mmOutStream.write("\n ENERGY CH: ".getBytes());
					mmOutStream.write(ec);

					mmOutStream.write(" DUTY    : ".getBytes());
					mmOutStream.write(duty);
					
					
					
					mmOutStream.write("\n CAP CH   : ".getBytes());
					mmOutStream.write(sundrylabel);
					
				
					
					
					mmOutStream.write(" LPS     : ".getBytes());
					mmOutStream.write(cap);
					
					mmOutStream.write("\n ARREARS  : ".getBytes());
					mmOutStream.write(arears);
					
					mmOutStream.write(" DL ADJ  : ".getBytes());
					mmOutStream.write(credit);
					
					
					mmOutStream.write("\n SUNDRY   : ".getBytes());
					mmOutStream.write(sundry);
					
					mmOutStream.write(" OTHER   : ".getBytes());
					mmOutStream.write(misc);
					
				
					mmOutStream.write("\n BILL DATE: ".getBytes());
					mmOutStream.write(issuedate);
					
					String netlbl = "\n ON/BEFORE DUE DATE        AFTER DUE DATE" ;
				
					mmOutStream.write(" DUE DATE:".getBytes());
					mmOutStream.write(duedate);
					
					
					
					
					
					
					
					//mmOutStream.write(u_space);
					mmOutStream.write(netlbl.getBytes());
				
				
					String dtyDispy  = "\n DUTY     : " +UtilMaster.dutyBfrdue +" DUTY    : "+ UtilMaster.dutyAftdue ;
					mmOutStream.write((dtyDispy ).getBytes());
					mmOutStream.write(("\n CHARGES  : "+ UtilMaster.chrgBfrdue +" CHARGES : " +UtilMaster.chrgAftdue ).getBytes());
					mmOutStream.write(("\n").getBytes());
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write((" TOTAL      : "+ UtilMaster.nettotalBfrdue +"    TOTAL     : "+ UtilMaster.nettotalAftdue ).getBytes());
					
					
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x0C);
						mmOutStream.write(u_space);
						mmOutStream.write("\n ----------------------------------------------  ".getBytes());
						//mmOutStream.write(u_space);
						mmOutStream.write(departlabel);
						mmOutStream.write(u_space);
						mmOutStream.write(billno);
						mmOutStream.write(billnolabel);
						mmOutStream.write(consumerid);
						mmOutStream.write(u_space);
						mmOutStream.write("\n BILL DATE: ".getBytes());
						mmOutStream.write(issuedate);
						
						mmOutStream.write("DUE DATE:".getBytes());
						mmOutStream.write(duedate);
						netlbl = "\n ON/BEFORE DUE DATE       AFTER DUE DATE" ;
						mmOutStream.write(netlbl.getBytes());
							 dtyDispy  = "\n DUTY     :" +UtilMaster.dutyBfrdue +" DUTY    : "+ UtilMaster.dutyAftdue ;
						mmOutStream.write((dtyDispy ).getBytes());
						mmOutStream.write(("\n CHARGES  :"+ UtilMaster.chrgBfrdue +" CHARGES : " +UtilMaster.chrgAftdue ).getBytes());
						mmOutStream.write(("\n").getBytes());
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						mmOutStream.write(0x1C);
						mmOutStream.write((" TOTAL      : "+ UtilMaster.nettotalBfrdue +"   TOTAL     : "+ UtilMaster.nettotalAftdue ).getBytes());
						
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x0A);
					//	mmOutStream.write("\n ______________________________________________ ".getBytes());
						UtilMaster.dutyAftdue = null;
						UtilMaster.chrgBfrdue= null;
						UtilMaster.nettotalBfrdue= null ;
						UtilMaster.chrgAftdue= null ;
						UtilMaster.nettotalAftdue = null ;
						UtilMaster.dutyBfrdue  = null ;
						
						 
						// int i = BluetoothChat.print_series ;
						 byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z
						// rf2[1] = 0x5A; // Z
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();
				
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						//mmOutStream.write(" ______________________________________________ ".getBytes());
						mmOutStream.write("---------------------------------------------- ".getBytes());
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x0C);
						String recipt =  "\n AMOUNT RECEIVED : ";
						String RepDate = "\n DATE  	        : " ;
						mmOutStream.write(recipt.getBytes());
						mmOutStream.write(u_space);
						mmOutStream.write(RepDate.getBytes());
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write("\n    CASHER SIGN              BANK SEAL".getBytes());
						mmOutStream.write("\n-------------------------------------------- ".getBytes());
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
					//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
				
					mmOutStream.flush();
					
			
					if(mmOutStream!=null){
						mmOutStream.close();
					}
					if(mmSocket!=null){
						mmSocket.close();
					}


				} catch (IOException e) {
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
	         	

	        }
			
		  
		  
		  
		  public void write_newBackupofversionM123withblackmarkdetectioWithoutbarcode( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
			  

	            
        	
        	
	            try {

	            	  /* try {
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
	        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
	        					} catch (Exception e) {
	        						e.printStackTrace();
	        					}
	        				}else{
	        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
	        	*/
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
		/************* Bill *****************/			
		
	            	mmOutStream.flush();
	            	 mmOutStream.write(0x1D);
	            	
	            	 
	            	 //mmOutStream.write(0x1B);
	        		
	            	
	            	 
	            	 
	            	mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		
	        		//mmOutStream.write(0x1C);
	        		
	        		
	        		/*
	        		 * 
	        		 * for the alternative line feed*/
	        		
	        		 if(Demo.var1==false)
    	           {
    	           mmOutStream.write(0x0A);
    	           Demo.var1=true;
    	           }
    	           else if(Demo.var1==true)
    	           {
    	        	   Demo.var1=false;
    	           }
	        		
	        		
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		
	        	
	        		
	        		
	        		/*mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);*/
	        		
	        	// to seperate the groups and move the column 1 to right
	        		
	        		 mmOutStream.write(0x0B);
	        		
					mmOutStream.write(heading1);
					/*mmOutStream.write(0x0A);*/
					
					
				    mmOutStream.write(0x1D);
   	           mmOutStream.write(0x1B);
   	           //mmOutStream.write(0x1B);
   		
   			
   	           mmOutStream.write(0x0A);	
   	           
   	           
   	           
   	       //changes end
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write(0x09);
	        		
				
					mmOutStream.write(heading2);
					mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading3);
					
					/*NAME , ADDRESS1 , ADDRESS2 */
		        		
					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					
					mmOutStream.write(0x20);
					mmOutStream.write(consumername);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address1);
					
					
					/*POLE NO*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					// this condition is for line feed WRT pole number
					
					if(Demo.var2==true)
					{
						Demo.var2=false;
					}
					else if(Demo.var2==false)
					{
						Demo.var2=true;
					mmOutStream.write(0x0A);
					}
					
					/*mmOutStream.write(0x0A);*/
					//mmOutStream.write(0x0A);
					
					
					
					
					
					mmOutStream.write(0x20);
					mmOutStream.write(poleno);
					
					mmOutStream.write(subdivision);
					
					/*BILL NO */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
				
					mmOutStream.write(0x20);
					mmOutStream.write(billno);
					
					mmOutStream.write(issuedate);
					
					mmOutStream.write(duedate);
					
					

					/*TARIFF CODE */
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(tariff);
					
					/*LAOD */
					mmOutStream.write(sacnload);
					
					/*LAOD */
					mmOutStream.write(meterno);
					
					
					
					
					
					
					/****************READING  >>>**********************/
					/*PRESENT READING */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(previousrdng);
					
					mmOutStream.write(mfunits);
					
					
					/*METER_CONSTANT*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mincharges);
					
					/*BMD READING*/
					mmOutStream.write(phase);
					/*PF*/
					mmOutStream.write(consumerlabel);
						
					
					
					/*AVG UNITS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(avgunits);
					/*PRESENT METER STATUS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(meterstatus);
					/*BM */
					//mmOutStream.write(0x09);
					mmOutStream.write(billto);
						
					
					
					/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
					/*FC*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(fc);
					/*EC*/
					//mmOutStream.write(0x09);
					mmOutStream.write(ec);
					/*PF PENALITY*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundrylabel);
						
					
					
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(minchargeslabel);
					/*REBATE*/
					//mmOutStream.write(0x09);
					mmOutStream.write(fppcalabel);
					/*OTHERS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundry);
					
					
					
					/*----ARREARS----*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(arears);
					/*----INTEREST---- */
					//mmOutStream.write(0x09);
					mmOutStream.write(cap);
					/*----TAX ----*/
					//mmOutStream.write(0x09);
					mmOutStream.write(duty);
					
					
					/* ----OLD----*/
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					/*----NET ---- */
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					/*----TAX---- */
					
					mmOutStream.write(linemin);
					
					/*	----DL ADJ---- 
					mmOutStream.write(0x0A);
					
					 ----OLD----
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					----NET ---- 
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					----TAX---- 
					
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);*/
					
						
						/* byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();*/
				
						
						mmOutStream.write("\n--------------------- ".getBytes());
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
		        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
		        					} catch (Exception e) {
		        						e.printStackTrace();
		        					}
		        				}else{
		        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
	            
		  }
		  
		  
		  public void write_new( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
			  

	            
          	
          	
	            try {

	            	  /* try {
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
	        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
	        					} catch (Exception e) {
	        						e.printStackTrace();
	        					}
	        				}else{
	        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
	        	*/
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
		/************* Bill *****************/			
		
	            	/*mmOutStream.flush();*/
	            	
	            	
	            	 
	            	 //mmOutStream.write(0x1B);
	        		
	            	mmOutStream.write(0x1B);         
					mmOutStream.write(0x4B); 
					mmOutStream.write(0x0C);
	            	 
	            	 mmOutStream.write(0x1D);
	            	/*mmOutStream.write(0x4B);*/
	            	
	            	
	            	
	            	
	            	 mmOutStream.write(0x0A);
	        		mmOutStream.write(0x09);
	        		
	        		//mmOutStream.write(0x1C);
	        		
	        		
	        		/*
	        		 * 
	        		 * for the alternative line feed*/
	        		
	        		 if(Demo.var1==false)
      	           {
      	           mmOutStream.write(0x0A);
      	           Demo.var1=true;
      	           }
      	           else if(Demo.var1==true)
      	           {
      	        	   Demo.var1=false;
      	           }
	        		
	        		
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		
	        		
	        	
	        		
	        		
	        		/*mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);*/
	        		
	        	// to seperate the groups and move the column 1 to right
	        		
	        		 mmOutStream.write(0x0B);
	        		 mmOutStream.write(0x09);
	        		
					mmOutStream.write(heading1);
					/*mmOutStream.write(0x0A);*/
					
					
				    mmOutStream.write(0x1D);
     	           mmOutStream.write(0x1B);
     	           //mmOutStream.write(0x1B);
     		
     			
     	           mmOutStream.write(0x0A);	
     	           
     	           
     	           
     	       //changes end
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write(0x09);
	        		
				
					mmOutStream.write(heading2);
					mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading3);
					
					/*NAME , ADDRESS1 , ADDRESS2 */
		        		
					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					
					mmOutStream.write(0x20);
					mmOutStream.write(consumername);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address1);
					
					
					/*POLE NO*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					// this condition is for line feed WRT pole number
					
					if(Demo.var2==true)
					{
						Demo.var2=false;
					}
					else if(Demo.var2==false)
					{
						Demo.var2=true;
					mmOutStream.write(0x0A);
					}
					
					/*mmOutStream.write(0x0A);*/
					//mmOutStream.write(0x0A);
					
					
					
					
					
					mmOutStream.write(0x20);
					mmOutStream.write(poleno);
					
					mmOutStream.write(subdivision);
					
					/*BILL NO */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
				
					mmOutStream.write(0x20);
					mmOutStream.write(billno);
					
					mmOutStream.write(issuedate);
					
					mmOutStream.write(duedate);
					
					

					/*TARIFF CODE */
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(tariff);
					
					/*LAOD */
					mmOutStream.write(sacnload);
					
					/*LAOD */
					mmOutStream.write(meterno);
					
					
					
					
					
					
					/****************READING  >>>**********************/
					/*PRESENT READING */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(previousrdng);
					
					mmOutStream.write(mfunits);
					
					
					/*METER_CONSTANT*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mincharges);
					
					/*BMD READING*/
					mmOutStream.write(phase);
					/*PF*/
					mmOutStream.write(consumerlabel);
						
					
					
					/*AVG UNITS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(avgunits);
					/*PRESENT METER STATUS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(meterstatus);
					/*BM */
					//mmOutStream.write(0x09);
					mmOutStream.write(billto);
						
					
					
					/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
					/*FC*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(fc);
					/*EC*/
					//mmOutStream.write(0x09);
					mmOutStream.write(ec);
					/*PF PENALITY*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundrylabel);
						
					
					
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(minchargeslabel);
					/*REBATE*/
					//mmOutStream.write(0x09);
					mmOutStream.write(fppcalabel);
					/*OTHERS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundry);
					
					
					
					/*----ARREARS----*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(arears);
					/*----INTEREST---- */
					//mmOutStream.write(0x09);
					mmOutStream.write(cap);
					/*----TAX ----*/
					//mmOutStream.write(0x09);
					mmOutStream.write(duty);
					
					
					/* ----OLD----*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					/*----NET ---- */
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					/*----TAX---- */
					
					mmOutStream.write(linemin);
					
					/*	----DL ADJ---- 
					mmOutStream.write(0x0A);
					
					 ----OLD----
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					----NET ---- 
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					----TAX---- 
					
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);*/
					
					mmOutStream.write(0x0A);
						 byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();
				
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						/*mmOutStream.write("\n--------------------- ".getBytes());
						mmOutStream.flush();*/
						//mmOutStream.write(u_space);
						/*mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);*/
					//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
					//mmOutStream.write(u_space);
						
				/*		try {
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
		        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
		        					} catch (Exception e) {
		        						e.printStackTrace();
		        					}
		        				}else{
		        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
		        					break ;
		        				}
		        				
		        				mmSocket.getInputStream();
		        			}
		        		}
		        		
		        		
		        		
		        		mmOutStream.flush();
		        		
		        		
		        		
		        	} catch (IOException e) {
		        		Log.e(TAG, "Exception during write", e);
		        		FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
		        	}*/
						
						/*mmOutStream.flush();
						
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);
						
						*/
						
						/*	----DL ADJ---- */
					//	mmOutStream.write(0x0A);
						
						
					//	mmOutStream.write(0x0A);
						/*mmOutStream.write(0x20);
						mmOutStream.write(credit);
						
						
						----NET ---- 
						//mmOutStream.write(0x09);
						
						mmOutStream.write(netamount);
						
						----TAX---- 
						
						mmOutStream.write(linemin);
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
					mmOutStream.write(u_space);*/
					
			
						
						mmOutStream.flush();
						
						if(mmOutStream!=null){
							mmOutStream.close();
						}
						if(mmSocket!=null){
							mmSocket.close();
						}
						/*if(mmInStream!=null){
							mmInStream.close();
						}*/


					} catch (IOException e) {
						Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
	         	

	        
			  
			  
		  }
		  
		  
		  public void write_newBackupWRTOldpaperallignment( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
	            
	            	
	            	
	            try {

	            	  /* try {
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
	        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
	        					} catch (Exception e) {
	        						e.printStackTrace();
	        					}
	        				}else{
	        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
	        	*/
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
		/************* Bill *****************/			
		
	            	mmOutStream.flush();
	            	 mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading1);
					mmOutStream.write(0x0A);
					
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write(0x09);
	        		
					mmOutStream.write(heading2);
					mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading3);
					
					/*NAME , ADDRESS1 , ADDRESS2 */
		        		
					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					
					mmOutStream.write(0x20);
					mmOutStream.write(consumername);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address1);
					
					
					/*POLE NO*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(poleno);
					
					mmOutStream.write(subdivision);
					
					/*BILL NO */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
				
					mmOutStream.write(0x20);
					mmOutStream.write(billno);
					
					mmOutStream.write(issuedate);
					
					mmOutStream.write(duedate);
					
					

					/*TARIFF CODE */
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(tariff);
					
					/*LAOD */
					mmOutStream.write(sacnload);
					
					/*LAOD */
					mmOutStream.write(meterno);
					
					
					
					
					
					
					/****************READING  >>>**********************/
					/*PRESENT READING */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(previousrdng);
					
					mmOutStream.write(mfunits);
					
					
					/*METER_CONSTANT*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mincharges);
					
					/*BMD READING*/
					mmOutStream.write(phase);
					/*PF*/
					mmOutStream.write(consumerlabel);
						
					
					
					/*AVG UNITS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(avgunits);
					/*PRESENT METER STATUS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(meterstatus);
					/*BM */
					//mmOutStream.write(0x09);
					mmOutStream.write(billto);
						
					
					
					/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
					/*FC*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(fc);
					/*EC*/
					//mmOutStream.write(0x09);
					mmOutStream.write(ec);
					/*PF PENALITY*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundrylabel);
						
					
					
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(minchargeslabel);
					/*REBATE*/
					//mmOutStream.write(0x09);
					mmOutStream.write(fppcalabel);
					/*OTHERS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundry);
					
					
					
					/*----ARREARS----*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(arears);
					/*----INTEREST---- */
					//mmOutStream.write(0x09);
					mmOutStream.write(cap);
					/*----TAX ----*/
					//mmOutStream.write(0x09);
					mmOutStream.write(duty);
					
					
					/* ----OLD----*/
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					/*----NET ---- */
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					/*----TAX---- */
					
					mmOutStream.write(linemin);
					
					/*	----DL ADJ---- 
					mmOutStream.write(0x0A);
					
					 ----OLD----
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					----NET ---- 
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					----TAX---- 
					
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);*/
					
						
						/* byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();*/
				
						
						mmOutStream.write("\n--------------------- ".getBytes());
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
		        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
		        					} catch (Exception e) {
		        						e.printStackTrace();
		        					}
		        				}else{
		        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

	        }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  public void write_newWRTtheVersion121( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
	            
	            	
	            	
	            try {

	            	  /* try {
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
	        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
	        					} catch (Exception e) {
	        						e.printStackTrace();
	        					}
	        				}else{
	        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
	        	*/
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
		/************* Bill *****************/			
		
	            	mmOutStream.flush();
	            	 mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading1);
					mmOutStream.write(0x0A);
					
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write(0x09);
	        		
					mmOutStream.write(heading2);
					mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading3);
					
					
					
					
		        		/*NAME , ADDRESS1 , ADDRESS2 */
		        		
					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					
					mmOutStream.write(0x20);
					mmOutStream.write(consumername);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address1);
					
					
					/*POLE NO*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(poleno);
					
					mmOutStream.write(subdivision);
					
					/*BILL NO */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
				
					mmOutStream.write(0x20);
					mmOutStream.write(billno);
					
					mmOutStream.write(issuedate);
					
					mmOutStream.write(duedate);
					
					

					/*TARIFF CODE */
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(tariff);
					
					/*LAOD */
					mmOutStream.write(sacnload);
					
					/*LAOD */
					mmOutStream.write(meterno);
					
					
					
					
					
					
					/****************READING  >>>**********************/
					/*PRESENT READING */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(previousrdng);
					
					mmOutStream.write(mfunits);
					
					
					/*METER_CONSTANT*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mincharges);
					
					/*BMD READING*/
					mmOutStream.write(phase);
					/*PF*/
					mmOutStream.write(consumerlabel);
						
					
					
					/*AVG UNITS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(avgunits);
					/*PRESENT METER STATUS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(meterstatus);
					/*BM */
					//mmOutStream.write(0x09);
					mmOutStream.write(billto);
						
					
					
					/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
					/*FC*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(fc);
					/*EC*/
					//mmOutStream.write(0x09);
					mmOutStream.write(ec);
					/*PF PENALITY*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundrylabel);
						
					
					
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(minchargeslabel);
					/*REBATE*/
					//mmOutStream.write(0x09);
					mmOutStream.write(fppcalabel);
					/*OTHERS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundry);
					
					
					
					/*----ARREARS----*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(arears);
					/*----INTEREST---- */
					//mmOutStream.write(0x09);
					mmOutStream.write(cap);
					/*----TAX ----*/
					//mmOutStream.write(0x09);
					mmOutStream.write(duty);
					
					
					/* ----OLD----*/
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					/*----NET ---- */
					//mmOutStream.write(0x09);
					
					
					
					/*mmOutStream.write(netamount);*/
					
					mmOutStream.write(addPostSpace("", 16).getBytes());
					
					/*----TAX---- */
					
					
					
					
					
					mmOutStream.write(linemin);
					
					/*	----DL ADJ---- 
					mmOutStream.write(0x0A);
					
					 ----OLD----
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					
					
					----NET ---- 
					//mmOutStream.write(0x09);
					
					mmOutStream.write(netamount);
					
					----TAX---- 
					
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);*/
					
						
						/* byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();*/
				
						
					
					
					mmOutStream.write(0x0A);
					/*mmOutStream.write(0x0A);*/
					
					
					mmOutStream.write(0x1B);         
					mmOutStream.write(0x4B); 
					mmOutStream.write(0x0C);



					mmOutStream.write(0x1C);


					
					
					
					mmOutStream.write("                  ".getBytes());
					mmOutStream.write(netamount);
					
					
					mmOutStream.write(0x1B);         
					mmOutStream.write(0x4B); 
					mmOutStream.write(0x0C);



					mmOutStream.write(0x1D);
					
					
					
					
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
		        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
		        					} catch (Exception e) {
		        						e.printStackTrace();
		        					}
		        				}else{
		        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

	        }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  public void write_newThisisWRTTheNEWBILL_NOTYETfullyalligned( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
	            
	            	
	            	
	            try {

	            	  /* try {
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
	        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
	        					} catch (Exception e) {
	        						e.printStackTrace();
	        					}
	        				}else{
	        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
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
	        	*/
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
	            	/************* Bill *****************/		
	          	           mmOutStream.flush();
	          	           
	          	         
	          	       
	          	           mmOutStream.write(0x1B);
	        	           mmOutStream.write(0x4B);
	        	         mmOutStream.write(0x0C);
	        	         
	        	         mmOutStream.write(0x1D);
	        	         
	        	         
	        	         
	        	           //mmOutStream.write(0x0A);
	        	           
	        	           if(Demo.var1==false)
	        	           {
	        	        	   mmOutStream.write(0x0A);
	        	           Demo.var1=true;
	        	           }
	        	           else if(Demo.var1==true)
	        	           {
	        	        	   
	        	        	   Demo.var1=false;
	        	           }
	        	           //mmOutStream.write(0x09);
	        	           mmOutStream.write(0x09);
	        	           mmOutStream.write(0x09);
	        	           mmOutStream.write(0x09);
	        	           //mmOutStream.write(0x20);
	        	           //mmOutStream.write(0x20);
	        	           mmOutStream.write(0x0B);
	        	           mmOutStream.write(0x09);
	        	           mmOutStream.write(heading1);
	        	        		
	        	           mmOutStream.write(0x1D);
	        	           mmOutStream.write(0x1B);
	        	           //mmOutStream.write(0x1B);
	        		
	        			
	        	           mmOutStream.write(0x0A);		
					       mmOutStream.write(0x1D);
						   mmOutStream.write(0x1B);
						   mmOutStream.write(0x4B);
						   mmOutStream.write(0x09);
						   mmOutStream.write(0x0A);
						   //mmOutStream.write(0x0A);
						   mmOutStream.write(0x0A);
						   mmOutStream.write(0x0A);
								
						   mmOutStream.write(0x1D);
						   mmOutStream.write(0x1B);
						   mmOutStream.write(0x4B);
						   mmOutStream.write(0x09);
						   mmOutStream.write(0x1C);
						   mmOutStream.write(0x09);
						       
						   mmOutStream.write(heading2);
						   mmOutStream.write(0x09);
						   mmOutStream.write(0x20);
						   mmOutStream.write(0x20);
						   mmOutStream.write(0x09);
						   mmOutStream.write(0x20);
						   mmOutStream.write(0x20);
						   mmOutStream.write(0x09);
						   mmOutStream.write(0x20);
						   mmOutStream.write(0x20);
						   mmOutStream.write(heading3);
						   
					/*NAME , ADDRESS1 , ADDRESS2 */
		        		
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);
						
						mmOutStream.write(0x20);
						mmOutStream.write(consumername);
						mmOutStream.write(0x0A);
						
						mmOutStream.write(0x20);
						mmOutStream.write(address);
						mmOutStream.write(0x0A);
						
						mmOutStream.write(0x20);
						mmOutStream.write(address1);
						
						
						/*POLE NO*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						if(Demo.var2==true)
						{
							Demo.var2=false;
						}
						else if(Demo.var2==false)
						{
							Demo.var2=true;
						mmOutStream.write(0x0A);
						}
						//mmOutStream.write(0x0A);
						
						mmOutStream.write(0x20);
						mmOutStream.write(poleno);
						
						mmOutStream.write(subdivision);
						
						
						/*BILL NO */
						
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						
						mmOutStream.write(0x20);
						mmOutStream.write(billno);
						
						mmOutStream.write(issuedate);
						
						mmOutStream.write(duedate);
						
						
	
						/*TARIFF CODE */
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(tariff);
						
						/*LAOD */
						mmOutStream.write(sacnload);
						
						/*LAOD */
						mmOutStream.write(meterno);
						
						
						
						
						
						
						/****************READING  >>>**********************/
						/*PRESENT READING */
						
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(currentrdng);
						
						mmOutStream.write(previousrdng);
						
						mmOutStream.write(mfunits);
						
						
						/*METER_CONSTANT*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(mincharges);
						
						/*BMD READING*/
						mmOutStream.write(phase);
						/*PF*/
						mmOutStream.write(consumerlabel);
							
						
						
						/*AVG UNITS*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(avgunits);
						/*PRESENT METER STATUS*/
						//mmOutStream.write(0x09);
						mmOutStream.write(meterstatus);
						/*BM */
						//mmOutStream.write(0x09);
						mmOutStream.write(billto);
							
						
						
						/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
						/*FC*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						
						mmOutStream.write(0x20);
						mmOutStream.write(fc);
						/*EC*/
						//mmOutStream.write(0x09);
						mmOutStream.write(ec);
						/*PF PENALITY*/
						//mmOutStream.write(0x09);
						mmOutStream.write(sundrylabel);
							
						
						
						
						
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(minchargeslabel);
						/*REBATE*/
						//mmOutStream.write(0x09);
						mmOutStream.write(fppcalabel);
						/*OTHERS*/
						//mmOutStream.write(0x09);
						mmOutStream.write(sundry);
						
						
					
						/*----ARREARS----*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(arears);
						/*----INTEREST---- */
						//mmOutStream.write(0x09);
						mmOutStream.write(cap);
						/*----TAX ----*/
						//mmOutStream.write(0x09);
						mmOutStream.write(duty);
						
						
						/* ----OLD----*/
						mmOutStream.write(0x0A);
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20); 
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20); 
						mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
						mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(credit);
						
						
						/*----NET ---- */
						//mmOutStream.write(0x09);
						
						mmOutStream.write(netamount);
						
						/*----TAX---- */
						
						mmOutStream.write(linemin);
						
						/*	----DL ADJ---- 
						mmOutStream.write(0x0A);
						
						 ----OLD----
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20); 
						mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
						mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
						mmOutStream.write(0x0A);
						mmOutStream.write(0x20);
						mmOutStream.write(credit);
						
						
						----NET ---- 
						//mmOutStream.write(0x09);
						
						mmOutStream.write(netamount);
						
					----TAX---- 
					
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);*/
					
						 mmOutStream.write(0x0A);
						 //mmOutStream.write(0x0A);
						 //mmOutStream.write(0x0A);
						 //mmOutStream.write(0x0A);
						
						 
						 byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();
						
						
						/*
						  byte[] rf2 = new byte[5];
                          rf2[0] = 0x1B;
                          rf2[1] = 0x7A;// z
                         // rf2[1] = 0x5A; // Z
                          rf2[2] = 0x32;
                          rf2[3] = 0x09;

                             rf2[4] = 0x41;
                         mmOutStream.write(rf2);
                         mmOutStream.flush();

                         //String net_toPrint = Y2.toString() ;
                         String net_toPrint = new String(netamount);
                         String vlu_barCode = UtilMaster.addPostSpace( UtilMaster.mconsumer_sc_no.trim()+"-"+net_toPrint , 16);

                        mmOutStream.write((vlu_barCode).getBytes());
                        
                        
                        mmOutStream.write(((" "+consumer.LConsumer_SC_No.trim())).getBytes());
                         mmOutStream.flush();*/
						
						
						
						

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
						
						//mmOutStream.write(u_space);
						//mmOutStream.write("\n--------------------- ".getBytes());
						//mmOutStream.flush();
						
						
						//mmOutStream.write(u_space);
						/*mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);*/
					//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
					//mmOutStream.write(u_space);
						
					/*	try {
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
		        						//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\nBLACKMARK NOT FOUND", Toast.LENGTH_SHORT).show();
		        					} catch (Exception e) {
		        						e.printStackTrace();
		        					}
		        				}else{
		        					//Toast.makeText(getApplicationContext(), mvfwdmsg+"\n"+mesage+"\n--BLACKMARK FOUND", Toast.LENGTH_SHORT).show();
		        					break ;
		        				}
		        				
		        				mmSocket.getInputStream();
		        			}
		        		}
		        		
		        		
		        		
		        		mmOutStream.flush();
		        		
		        	
		        	} catch (IOException e) {
		        		Log.e(TAG, "Exception during write", e);
		        		FilePropertyManager.appendLog("IOException : "+Log.getStackTraceString(e));
		        	}*/
						
					/*	mmOutStream.flush();
						
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x03);*/
						
						
						
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
						//mmOutStream.write(0x0A);
						//mmOutStream.write(0x0A);
						
						
						
						
						
						//mmOutStream.write(u_space);
					/*	mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);*/
				
	        	
						if(mmOutStream!=null){
							mmOutStream.close();
						}
						if(mmSocket!=null){
							mmSocket.close();
						}
						/*if(mmInStream!=null){
							mmInStream.close();
						}*/


					} catch (IOException e) {
						Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
	            

	        }
		  
		  
		  public void write_back( byte[] heading1,byte[] heading2,byte[] heading3,byte[] u_line,byte[] u_space,byte[] division,byte[] divisioname,byte[] subdivision,byte[] subdivisioname,
	        		byte[] billnolabel,byte[] billno,byte[] issuedate,byte[] duedate,byte[] consumerlabel,byte[] consumerid,byte[] consumeraddlabel,byte[] consumername,byte[] address,byte[] address1,
	        		byte[] billfromlabel,byte[] billfrom,byte[] billtolabel,byte[] billto,byte[] connecdatelabel,byte[] conndate,byte[] sacnload,byte[] phase, byte[] poleno ,byte[] tariff,
	        		byte[] minchargeslabel,byte[] mincharges,byte[] linemin,byte[] meterno ,byte[] avguintslabel,byte[] avgunits,byte[] foliolabel,byte[] foliono,byte[] readinglabel,
	        		byte[] currentrdng,byte[] previousrdng,byte[] mfunits,byte[] meterstatuslabel,byte[] meterstatus,byte[] meterrent,byte[] energychargelabel,byte[] ec,byte[] duty,byte[] fc,
	        		byte[] fppcalabel,byte[] fppca,byte[] cap,byte[] arears,byte[] sundrylabel,byte[] sundry,byte[] credit,byte[] misc,byte[] netamountlabel,byte[] netamount,
	        		byte[] departlabel,byte[] departlabel1,byte[] divisionlabel,byte[] divisiondep,byte[] subdivlabel,byte[] subdiv,byte[] receiptnolabel,byte[] receiptno,byte[] datelabel,byte[] date,
	        		byte[] billisslabel,byte[] billissuedate,byte[] duedatelabel,byte[] duedatedep,byte[] amountreceivedlabel,byte[] counternamelabel
	        		) {
	            
	            	
	            	
	            try {

	            	 
	            	
	            	
					
	            	/*ref****
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x0A);
	        		*
	        		*/
	            	
	            	
		/************* Bill *****************/			
		
	            	mmOutStream.flush();
	            	 mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x1C);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading1);
					mmOutStream.write(0x0A);
					
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x1D);
	            	mmOutStream.write(0x1B);
	        		mmOutStream.write(0x4B);
	        		mmOutStream.write(0x09);
					mmOutStream.write(0x1C);
					mmOutStream.write(0x09);
	        		
					mmOutStream.write(heading2);
					mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x09);
	        		mmOutStream.write(0x20);
	        		mmOutStream.write(0x20);
					mmOutStream.write(heading3);
					
					
					
					
		        		/*NAME , ADDRESS1 , ADDRESS2 */
		        		
					mmOutStream.write(0x1D);
					mmOutStream.write(0x1B);
					mmOutStream.write(0x4B);
					mmOutStream.write(0x03);
					
					mmOutStream.write(0x20);
					mmOutStream.write(consumername);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(address1);
					
					
					/*POLE NO*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					//mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(poleno);
					
					mmOutStream.write(subdivision);
					
					/*BILL NO */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
				
					mmOutStream.write(0x20);
					mmOutStream.write(billno);
					
					mmOutStream.write(issuedate);
					
					mmOutStream.write(duedate);
					
					

					/*TARIFF CODE */
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(tariff);
					
					/*LAOD */
					mmOutStream.write(sacnload);
					
					/*LAOD */
					mmOutStream.write(meterno);
					
					
					
					
					
					
					/****************READING  >>>**********************/
					/*PRESENT READING */
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(currentrdng);
					
					mmOutStream.write(previousrdng);
					
					mmOutStream.write(mfunits);
					
					
					/*METER_CONSTANT*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mincharges);
					
					/*BMD READING*/
					mmOutStream.write(phase);
					/*PF*/
					mmOutStream.write(consumerlabel);
						
					
					
					/*AVG UNITS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(avgunits);
					/*PRESENT METER STATUS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(meterstatus);
					/*BM */
					//mmOutStream.write(0x09);
					mmOutStream.write(billto);
						
					
					
					/*************** FIXED , ENERGY CHARGERS BLOCK ****** */
					/*FC*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
					mmOutStream.write(0x20);
					mmOutStream.write(fc);
					/*EC*/
					//mmOutStream.write(0x09);
					mmOutStream.write(ec);
					/*PF PENALITY*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundrylabel);
						
					
					
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(minchargeslabel);
					/*REBATE*/
					//mmOutStream.write(0x09);
					mmOutStream.write(fppcalabel);
					/*OTHERS*/
					//mmOutStream.write(0x09);
					mmOutStream.write(sundry);
					
					
					
					/*ARREARS*/
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(arears);
					/*INTEREST */
					//mmOutStream.write(0x09);
					mmOutStream.write(cap);
					/*TAX */
					//mmOutStream.write(0x09);
					mmOutStream.write(duty);
					
					
					
					/*	DL ADJ*/ 
					mmOutStream.write(0x0A);
					
					/* OLD*/ mmOutStream.write(0x0A);
					mmOutStream.write(0x20); 
					mmOutStream.write(BluetoothChat.addPostSpace("Dl ADJ", 10).getBytes());
					mmOutStream.write(BluetoothChat.addPreSpace("Net Amount", 15).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(credit);
					/*NET  */
					//mmOutStream.write(0x09);
					mmOutStream.write(netamount);
					/*TAX */
					//mmOutStream.write(0x09);
					mmOutStream.write(linemin);
					mmOutStream.write(0x0A);
					mmOutStream.write(0x0A);
					
						
						/* byte[] rf2 = new byte[5];
						 rf2[0] = 0x1B;
						 rf2[1] = 0x7A;// z without human readable text
						// rf2[1] = 0x5A; // Z  ASCII human readable text 
						 rf2[2] = 0x32;
						// rf2[3] = 0x0A;
						 if(UtilMaster.mconsumer_sc_no.trim().length() == 8){
						 rf2[3] = 0x09; // 9 DIGITS
						 }else if(UtilMaster.mconsumer_sc_no.trim().length() == 9){
							 rf2[3] = 0x0A; // 10 digits
						 }else{
							 rf2[3] = 0x0B;
						 }
						
						
							rf2[4] = 0x41;
						 
						
						mmOutStream.write(rf2);

						mmOutStream.flush();

						
						mmOutStream.write((" "+(UtilMaster.mconsumer_sc_no.trim())).getBytes());
						mmOutStream.flush();*/
				
						
						mmOutStream.write("\n-------------------------------------------- ".getBytes());
						//mmOutStream.write(u_space);
						mmOutStream.write(0x1D);
						mmOutStream.write(0x1B);
						mmOutStream.write(0x4B);
						mmOutStream.write(0x09);
					//if(!Consumer.isPrinterType()){mmOutStream.write(u_space);}
					//mmOutStream.write(u_space);
						
						
						mmOutStream.write(u_space);
						mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					mmOutStream.write(u_space);
					
			
					if(mmOutStream!=null){
						mmOutStream.close();
					}
					if(mmSocket!=null){
						mmSocket.close();
					}
					/*if(mmInStream!=null){
						mmInStream.close();
					}*/


				} catch (IOException e) {
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
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
	
	
	
}
