package com.bcits.bsmartbilling;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import com.utils.helper.DetailedReportHelper;
import com.utils.helper.MStatusReportHelper;

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



public class BluetoothChatService_Reports {
	String mvfwdmsg  ;
	Handler handler = new Handler() ;
	
	
	
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	/*private static String BackUP_DATABASE_NAME = dir
			+ "/JVVNL/JVVNLNOTINSERTED.db";*/
	// Debugging
	private static final String TAG = "BluetoothChatService_Reports";
	private static final boolean D = true;
	// Name for the SDP record when creating server socket
	private static final String NAME = "BluetoothChat_Reports";

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
	 * Constructor. Prepares a new BluetoothChat_Reports session.
	 * 
	 * @param context
	 *            The UI Activity Context
	 * @param handler
	 *            A Handler to send messages back to the UI Activity
	 */
	public BluetoothChatService_Reports(Context context, Handler handler) {
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
		mHandler.obtainMessage(BluetoothChat_Reports.MESSAGE_STATE_CHANGE, state, -1)
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
		Message msg = mHandler.obtainMessage(BluetoothChat_Reports.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat_Reports.DEVICE_NAME, device.getName());
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
	public void write(Context context, String type ,byte[] heading1 ,byte[] heading2, byte[] heading3 , byte[] total ,	byte[] billed,	byte[] unbilled ,List list_) {
		// Create temporary object
		ConnectedThread r;
		// Synchronize a copy of the ConnectedThread
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		// Perform the write unsynchronized
		
	if(type.equalsIgnoreCase("SUMMARY_REPORT")){
		r.write_sumReport(heading1 , heading2 , heading3, total , billed , unbilled, list_);
	}else if(type.equalsIgnoreCase("DETAILED_SUMMARY_REPORT")){
		r.write_detailedSumReport(heading1 , heading2 , heading3, total , billed , unbilled, list_);
	}else if(type.equalsIgnoreCase("ROUTEWISE_SUMMERY_REPORT")){
		r.write_route_wise_SumReport(heading1 , heading2 , heading3, total , billed , unbilled, list_);
	}
	
	}


	/**
	 * Indicate that the connection attempt failed and notify the UI Activity.
	 */
	private void connectionFailed() {
		setState(STATE_LISTEN);

		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(BluetoothChat_Reports.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat_Reports.TOAST, "Unable to connect device");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	/**
	 * Indicate that the connection was lost and notify the UI Activity.
	 */
	private void connectionLost() {
		setState(STATE_LISTEN);

		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(BluetoothChat_Reports.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BluetoothChat_Reports.TOAST, "Device connection was lost");
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
					socket = mmServerSocket.accept();
				} catch (IOException e) {
					Log.e(TAG, "accept() failed", e);
					FilePropertyManager.appendLog(Log.getStackTraceString(e));
					break;
				}

				// If a connection was accepted
				if (socket != null) {
					synchronized (BluetoothChatService_Reports.this) {
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
				BluetoothChatService_Reports.this.start();
				return;
			}

			// Reset the ConnectThread because we're done
			synchronized (BluetoothChatService_Reports.this) {
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
					mHandler.obtainMessage(BluetoothChat_Reports.MESSAGE_READ, bytes,
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
		
		public void write_sumReport(byte[] heading1 ,byte[] heading2, byte[] heading3 , byte[] total ,	byte[] billed,	byte[] unbilled , List<MStatusReportHelper> list_) {

			try {

				mmOutStream.flush();
				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				
				mmOutStream.write(0x1C);
				mmOutStream.write(0x09);
				mmOutStream.write(0x09);
				/*mmOutStream.write(0x09);*/
				/*mmOutStream.write(0x09);
				*/
				mmOutStream.write(0x20);
				mmOutStream.write(0x20);
				mmOutStream.write(heading1);
				mmOutStream.write(0x0A);
				
				mmOutStream.write(0x1D);
            	mmOutStream.write(0x1B);
        		mmOutStream.write(0x4B);
        		mmOutStream.write(0x09);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write(heading2);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write("MeterReader : ".getBytes());
				mmOutStream.write(heading3);
				mmOutStream.write(0x0A);
				mmOutStream.write("   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot No Cons", 14)+": ").getBytes());
				mmOutStream.write(total);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Billed  ", 14)+": ").getBytes());
				mmOutStream.write(billed);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Unbilled  ", 14)+": ").getBytes());
				mmOutStream.write(unbilled);
				
				for (MStatusReportHelper mStatusReportHelper : list_) {
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write(mStatusReportHelper.status.getBytes());
					mmOutStream.write((": "+mStatusReportHelper.count).getBytes());
				}
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}
		public void write_detailedSumReport(byte[] heading1 ,byte[] heading2, byte[] heading3 , byte[] total ,	byte[] billed,	byte[] unbilled , List<DetailedReportHelper> list_) {

			try {

				mmOutStream.flush();
				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				
				mmOutStream.write(0x1C);
				mmOutStream.write(0x09);
				mmOutStream.write(0x09);
				/*mmOutStream.write(0x09);*/
				/*mmOutStream.write(0x09);
				*/
				mmOutStream.write(0x20);
				mmOutStream.write(0x20);
				mmOutStream.write(heading1);
				mmOutStream.write(0x0A);
				
				mmOutStream.write(0x1D);
            	mmOutStream.write(0x1B);
        		mmOutStream.write(0x4B);
        		mmOutStream.write(0x09);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write(heading2);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write("MeterReader : ".getBytes());
				mmOutStream.write(heading3);
				mmOutStream.write(0x0A);
				mmOutStream.write("   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot No Cons", 14)+": ").getBytes());
				mmOutStream.write(total);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Billed  ", 14)+": ").getBytes());
				mmOutStream.write(billed);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Unbilled  ", 14)+": ").getBytes());
				mmOutStream.write(unbilled);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Units  ", 14)+": ").getBytes());
				mmOutStream.write(ReportViewHelper.drepotTotal_Units.getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("TotRevenue", 14)+": ").getBytes());
				mmOutStream.write(ReportViewHelper.drepotTotal_rev.getBytes());
				
				mmOutStream.write(0x0A);
				
				int space = 5 ;
				int btSpace = 10 ;
				for (int i= 0 ; i < list_.size() ; i++) {
					DetailedReportHelper detailedReportHelper = list_.get(i) ;
					
					mmOutStream.write("\n   ----------------------------------------- ".getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace(detailedReportHelper.traifDesc, 14).getBytes());
					mmOutStream.write(("  NoOfConn: "+detailedReportHelper.report_totalConn).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Billed", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_Billed+"").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Units", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_Totalunits+"").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Revenue", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_totalRev+"").getBytes());
					
					mmOutStream.write("\n   ------------------- ".getBytes());
					
					
					/*mmOutStream.write( ("NRM   DL   MNR   DC   DIS   IDLE   MB   MS   NV").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write((detailedReportHelper.mtrStatusNrm +" , "+ detailedReportHelper.mtrStatusDL +" , "+ detailedReportHelper.mtrStatusMNR +" , "+ 
							detailedReportHelper.mtrStatusDC +" , "+ detailedReportHelper.mtrStatusDISSCONN +" , "+ 
							detailedReportHelper.mtrStatusIDLE +" , "+ detailedReportHelper.mtrStatusMB +" , "+ 
							detailedReportHelper.mtrStatusMS +" , "+ detailedReportHelper.mtrStatusNV).getBytes());*/
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("NRM", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusNrm+"" , btSpace) ).getBytes());
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("DL", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusDL+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("MNR", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusMNR+"", btSpace)).getBytes());
					
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("DC", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusDC+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("DIS", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusDISSCONN+"", btSpace)).getBytes());
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("IDLE", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusIDLE+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("MB", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusMB+"", btSpace)).getBytes());
					
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("MS", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusMS+"").getBytes());
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("NV", space).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusNV+"").getBytes());
					
					mmOutStream.flush();
				}
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.flush();
				mmOutStream.write(0x0A);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}
		public void write_route_wise_SumReport(byte[] heading1 ,byte[] heading2, byte[] heading3 , byte[] total ,	byte[] billed,	byte[] unbilled , List<DetailedReportHelper> list_) {

			try {

				mmOutStream.flush();
				mmOutStream.write(0x1D);
				mmOutStream.write(0x1B);
				mmOutStream.write(0x4B);
				mmOutStream.write(0x03);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				
				mmOutStream.write(0x1C);
				mmOutStream.write(0x09);
				mmOutStream.write(0x09);
				/*mmOutStream.write(0x09);*/
				/*mmOutStream.write(0x09);
				*/
				mmOutStream.write(0x20);
				mmOutStream.write(0x20);
				mmOutStream.write(heading1);
				mmOutStream.write(0x0A);
				
				mmOutStream.write(0x1D);
            	mmOutStream.write(0x1B);
        		mmOutStream.write(0x4B);
        		mmOutStream.write(0x09);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write(heading2);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write("MeterReader : ".getBytes());
				mmOutStream.write(heading3);
				mmOutStream.write(0x0A);
				mmOutStream.write("   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot No Cons", 14)+": ").getBytes());
				mmOutStream.write(total);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Billed  ", 14)+": ").getBytes());
				mmOutStream.write(billed);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Unbilled  ", 14)+": ").getBytes());
				mmOutStream.write(unbilled);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("Tot Units  ", 14)+": ").getBytes());
				mmOutStream.write(ReportViewHelper.drepotTotal_Units.getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x20);
				mmOutStream.write((addPostSpace("TotRevenue", 14)+": ").getBytes());
				mmOutStream.write(ReportViewHelper.drepotTotal_rev.getBytes());
				
				mmOutStream.write(0x0A);
				
				int space = 5 ;
				int btSpace = 10 ;
				for (int i= 0 ; i < list_.size() ; i++) {
					DetailedReportHelper detailedReportHelper = list_.get(i) ;
					
					mmOutStream.write("\n   ----------------------------------------- ".getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace(detailedReportHelper.tariffCode, 14).getBytes());
					mmOutStream.write(("  NoOfConn: "+detailedReportHelper.report_totalConn).getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Billed", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_Billed+"").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Units", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_Totalunits+"").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("Revenue", 14).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.report_totalRev+"").getBytes());
					
					mmOutStream.write("\n   ------------------- ".getBytes());
					
					
					/*mmOutStream.write( ("NRM   DL   MNR   DC   DIS   IDLE   MB   MS   NV").getBytes());
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write((detailedReportHelper.mtrStatusNrm +" , "+ detailedReportHelper.mtrStatusDL +" , "+ detailedReportHelper.mtrStatusMNR +" , "+ 
							detailedReportHelper.mtrStatusDC +" , "+ detailedReportHelper.mtrStatusDISSCONN +" , "+ 
							detailedReportHelper.mtrStatusIDLE +" , "+ detailedReportHelper.mtrStatusMB +" , "+ 
							detailedReportHelper.mtrStatusMS +" , "+ detailedReportHelper.mtrStatusNV).getBytes());*/
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("NRM", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusNrm+"" , btSpace) ).getBytes());
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("DL", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusDL+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("MNR", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusMNR+"", btSpace)).getBytes());
					
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("DC", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusDC+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("DIS", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusDISSCONN+"", btSpace)).getBytes());
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("IDLE", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusIDLE+"").getBytes());
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("MB", space).getBytes());
					mmOutStream.write((addPostSpace(" : "+detailedReportHelper.mtrStatusMB+"", btSpace)).getBytes());
					
					
					/*mmOutStream.write(0x0A);
					mmOutStream.write(0x20);*/
					mmOutStream.write( addPostSpace("MS", btSpace).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusMS+"").getBytes());
					
					
					mmOutStream.write(0x0A);
					mmOutStream.write(0x20);
					mmOutStream.write( addPostSpace("NV", space).getBytes());
					mmOutStream.write((" : "+detailedReportHelper.mtrStatusNV+"").getBytes());
					
					mmOutStream.flush();
				}
				mmOutStream.write(0x0A);
				mmOutStream.write("\n   ----------------------------------------- ".getBytes());
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.write(0x0A);
				mmOutStream.flush();
				mmOutStream.write(0x0A);
			} catch (Exception e) {
				
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
}
