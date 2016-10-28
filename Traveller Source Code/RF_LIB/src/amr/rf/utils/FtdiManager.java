package amr.rf.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.ftdi.j2xx.D2xxManager;
import com.ftdi.j2xx.FT_Device;

public class FtdiManager extends Activity {

	public static final String UNWANTED_SPACE = "(\\r| |\\n|\\r\\n)+";
	// j2xx
	private static D2xxManager ftD2xx = null;
	private FT_Device ftDev;
	public int DevCount = -1;
	private int currentPortIndex = -1;
	private int portIndex = -1;

	private enum DeviceStatus {
		DEV_NOT_CONNECT, DEV_NOT_CONFIG, DEV_CONFIG
	}

	// log tag
	private final String TT = "Trace";
	private final String TXS = "XM-Send";

	// handler event
	private final static int UPDATE_TEXT_VIEW_CONTENT = 0;

	private final byte XON = 0x11; /* Resume transmission */
	private final byte XOFF = 0x13; /* Pause transmission */

	// strings of file transfer protocols

	private final int MODE_GENERAL_UART = 0;
	private final int MODE_X_MODEM_CHECKSUM_RECEIVE = 1;
	private final int MODE_X_MODEM_CHECKSUM_SEND = 2;
	private final int MODE_X_MODEM_CRC_RECEIVE = 3;
	private final int MODE_X_MODEM_CRC_SEND = 4;
	private final int MODE_X_MODEM_1K_CRC_RECEIVE = 5;
	private final int MODE_X_MODEM_1K_CRC_SEND = 6;
	private final int MODE_Y_MODEM_1K_CRC_RECEIVE = 7;
	private final int MODE_Z_MODEM_RECEIVE = 9;
	private final int MODE_Z_MODEM_SEND = 10;
	private int transferMode = MODE_GENERAL_UART;
	private final byte ACK = 6; /* ACKnowlege */
	private final byte NAK = 0x15; /* Negative AcKnowlege */
	private final byte CHAR_C = 0x43; /* Character 'C' */
	private final byte CHAR_G = 0x47; /* Character 'G' */

	private final int MODEM_BUFFER_SIZE = 2048;
	private int[] modemReceiveDataBytes;
	private byte[] modemDataBuffer;
	// X modem -//

	// thread to read the data
	private HandlerThread handlerThread; // update data to UI
	private ReadThread readThread; // read data from USB

	private boolean bContentFormatHex = false;

	// show information message while send data by tapping "Write" button in hex
	// content format
	// android:id="@+id/ReadValues - android:maxLines="5000"

	// variables
	private final int UI_READ_BUFFER_SIZE = 10240; // Notes: 115K:1440B/100ms,
	private static byte[] readBuffer;
	private static char[] readBufferToChar;
	private static int actualNumBytes;

	public int baudRate; /* baud rate */
	public byte stopBit; /* 1:1stop bits, 2:2 stop bits */
	public byte dataBit; /* 8:8bit, 7: 7bit */
	public byte parity; /* 0: none, 1: odd, 2: even, 3: mark, 4: space */
	public byte flowControl; /* 0:none, 1: CTS/RTS, 2:DTR/DSR, 3:XOFF/XON */
	public static Context context;
	private boolean uart_configured = false;

	private BufferedOutputStream buf_save;

	// data buffer
	private byte[] readDataBuffer; /* circular buffer */

	private int iTotalBytes;
	private int iReadIndex;

	private final int MAX_NUM_BYTES = 65536;

	private boolean isReadThreadEnabled = false; 
	private boolean isHandlerThreadEnabled = false; 

	protected static byte[] receivedData;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			ftD2xx = D2xxManager.getInstance(this);
			
			context = this;

			// init modem variables
			modemReceiveDataBytes = new int[1];
			modemReceiveDataBytes[0] = 0;
			modemDataBuffer = new byte[MODEM_BUFFER_SIZE];
			readBuffer = new byte[UI_READ_BUFFER_SIZE];
			readBufferToChar = new char[UI_READ_BUFFER_SIZE];
			readDataBuffer = new byte[MAX_NUM_BYTES];
			actualNumBytes = 0;

			// start main text area read thread
			handlerThread = new HandlerThread(handler);
			handlerThread.start();

			/* setup the baud rate list */
			baudRate = 9600;
			/* stop bits */
			stopBit = 1;
			/* data bits */
			dataBit = 8;
			/* parity */
			parity = 0;
			/* flow control */
			flowControl =0;
			/* port */
			portIndex = 0;
		} catch (Exception e) {
			Log.e("FTDI_HT", "getInstance fail!!");
		}
	}

	public synchronized  void writeToDevice(byte[] temp) {
		try {
			receivedData = null; // SETTING RECIEVE ARRAY NULL
			if (DeviceStatus.DEV_CONFIG != checkDevice()) {
				return;
			}
			sendData(temp.length, temp);
		} catch (IllegalArgumentException e) {
			DLog.e(TT, "Illeagal HEX input.");
			return;
		}
	}

	
	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			if (null == ftDev || false == ftDev.isOpen()) {
				DLog.e(TT, "onResume - reconnect");
				createDeviceList();
				if (DevCount > 0) {
					connectFunction();
					setConfig(baudRate, dataBit, stopBit, parity, flowControl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	// j2xx functions +
	public void createDeviceList() {
		try {
			int tempDevCount = ftD2xx.createDeviceInfoList(context);

			if (tempDevCount > 0) {
				if (DevCount != tempDevCount) {
					DevCount = tempDevCount;
				}
			} else {
				DevCount = -1;
				currentPortIndex = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void connectFunction() {
		try {
			if (portIndex + 1 > DevCount) {
				portIndex = 0;
			}
			if (currentPortIndex == portIndex && ftDev != null	&& true == ftDev.isOpen()) {
				// Toast.makeText(global_context,"Port("+portIndex+") is already opened.",
				// J2xxHyperTerm.this).show();
				return;
			}

			if (true == isReadThreadEnabled) {
				isReadThreadEnabled = false;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (null == ftDev) {
				ftDev = ftD2xx.openByIndex(context, portIndex);
			} else {
				ftDev = ftD2xx.openByIndex(context, portIndex);
			}
			uart_configured = false;

			if (ftDev == null) {
				FileManager.writeLog("Open port(" + portIndex + ") NG!");
				return;
			}

			if (true == ftDev.isOpen()) {
				currentPortIndex = portIndex;
				if (false == isReadThreadEnabled) {
					readThread = new ReadThread(handler);
					readThread.start();
				}
			} else {
				FileManager.writeLog("Open port(" + portIndex + ") NG!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private DeviceStatus checkDevice() {
		try {
			if (ftDev == null || false == ftDev.isOpen()) {
				FileManager.writeLog("Device Not Connected");
				return DeviceStatus.DEV_NOT_CONNECT;
			} else if (false == uart_configured) {
				FileManager.writeLog("Need to configure UART.");
				return DeviceStatus.DEV_NOT_CONFIG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return DeviceStatus.DEV_CONFIG;

	}

	public void setConfig(int baud, byte dataBits, byte stopBits, byte parity,	byte flowControl) {
		
		try {

			// configure port
			// reset to UART mode for 232 devices
			ftDev.setBitMode((byte) 0, D2xxManager.FT_BITMODE_RESET);

			ftDev.setBaudRate(baud);

			switch (dataBits) {
			case 7:
				dataBits = D2xxManager.FT_DATA_BITS_7;
				break;
			case 8:
				dataBits = D2xxManager.FT_DATA_BITS_8;
				break;
			default:
				dataBits = D2xxManager.FT_DATA_BITS_8;
				break;
			}

			switch (stopBits) {
			case 1:
				stopBits = D2xxManager.FT_STOP_BITS_1;
				break;
			case 2:
				stopBits = D2xxManager.FT_STOP_BITS_2;
				break;
			default:
				stopBits = D2xxManager.FT_STOP_BITS_1;
				break;
			}

			switch (parity) {
			case 0:
				parity = D2xxManager.FT_PARITY_NONE;
				break;
			case 1:
				parity = D2xxManager.FT_PARITY_ODD;
				break;
			case 2:
				parity = D2xxManager.FT_PARITY_EVEN;
				break;
			case 3:
				parity = D2xxManager.FT_PARITY_MARK;
				break;
			case 4:
				parity = D2xxManager.FT_PARITY_SPACE;
				break;
			default:
				parity = D2xxManager.FT_PARITY_NONE;
				break;
			}

			ftDev.setDataCharacteristics(dataBits, stopBits, parity);

			short flowCtrlSetting;
			switch (flowControl) {
			case 0:
				flowCtrlSetting = D2xxManager.FT_FLOW_NONE;
				break;
			case 1:
				flowCtrlSetting = D2xxManager.FT_FLOW_RTS_CTS;
				break;
			case 2:
				flowCtrlSetting = D2xxManager.FT_FLOW_DTR_DSR;
				break;
			case 3:
				flowCtrlSetting = D2xxManager.FT_FLOW_XON_XOFF;
				break;
			default:
				flowCtrlSetting = D2xxManager.FT_FLOW_NONE;
				break;
			}

			ftDev.setFlowControl(flowCtrlSetting, XON, XOFF);

			uart_configured = true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// j2xx functions -

	private byte readData(int numBytes, byte[] buffer) {
		byte intstatus = 0x00; /* success by default */
		try {

			/* should be at least one byte to read */
			if ((numBytes < 1) || (0 == iTotalBytes)) {
				actualNumBytes = 0;
				intstatus = 0x01;
				return intstatus;
			}

			if (numBytes > iTotalBytes) {
				numBytes = iTotalBytes;
			}

			/* update the number of bytes available */
			iTotalBytes -= numBytes;
			actualNumBytes = numBytes;

			/* copy to the user buffer */
			for (int count = 0; count < numBytes; count++) {
				buffer[count] = readDataBuffer[iReadIndex];
				iReadIndex++;
				iReadIndex %= MAX_NUM_BYTES;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intstatus;
	}

	private static final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
				case UPDATE_TEXT_VIEW_CONTENT:
					if (actualNumBytes > 0) {
						byte[] dummyBytes = new byte[actualNumBytes];
						for (int i = 0; i < actualNumBytes; i++) {
							readBufferToChar[i] = (char) readBuffer[i]; // TODO
							dummyBytes[i] = readBuffer[i];
						}
						FileManager.writeLog("<<<<<  "+HelperClass.bytesToHex(dummyBytes));
						if (receivedData == null) {
							receivedData = dummyBytes;
						} else {
							byte[] c = new byte[receivedData.length	+ dummyBytes.length];
							System.arraycopy(receivedData, 0, c, 0,receivedData.length);
							System.arraycopy(dummyBytes, 0, c, receivedData.length,dummyBytes.length);
							receivedData = c;
						}
					}
					break;
				default:
					FileManager.writeLog("NG CASE");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	// Update UI content
	private class HandlerThread extends Thread {
		Handler mHandler;

		HandlerThread(Handler h) {
			mHandler = h;
		}

		public void run() {
			byte status;
			Message msg;
			isHandlerThreadEnabled=true;
			while (true == isHandlerThreadEnabled) {
			try {
				try {
					Thread.sleep(140);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (true == bContentFormatHex) // consume input data at hex
												// content format
				{
					status = readData(UI_READ_BUFFER_SIZE, readBuffer);
				} else if (MODE_GENERAL_UART == transferMode) {
					status = readData(UI_READ_BUFFER_SIZE, readBuffer);

					if (0x00 == status) {
						if (buf_save != null) {
							try {
								buf_save.write(readBuffer, 0, actualNumBytes);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						msg = mHandler.obtainMessage(UPDATE_TEXT_VIEW_CONTENT);
						mHandler.sendMessage(msg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	}

	private class ReadThread extends Thread {
		final int USB_DATA_BUFFER = 8192;

		ReadThread(Handler h) {
			this.setPriority(MAX_PRIORITY);
		}

		public void run() {
			byte[] usbdata = new byte[USB_DATA_BUFFER];
			int readcount = 0;
			int iWriteIndex = 0;
			isReadThreadEnabled = true;

			while (true == isReadThreadEnabled) {
				try {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					DLog.e(TT, "iTotalBytes:" + iTotalBytes);
					while (iTotalBytes > (MAX_NUM_BYTES - (USB_DATA_BUFFER + 1))) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					readcount = ftDev.getQueueStatus();
					// Log.e(">>@@","iavailable:" + iavailable);
					if (readcount > 0) {
						if (readcount > USB_DATA_BUFFER) {
							readcount = USB_DATA_BUFFER;
						}
						ftDev.read(usbdata, readcount);

						if ((MODE_X_MODEM_CHECKSUM_SEND == transferMode)
								|| (MODE_X_MODEM_CRC_SEND == transferMode)
								|| (MODE_X_MODEM_1K_CRC_SEND == transferMode)) {
							for (int i = 0; i < readcount; i++) {
								modemDataBuffer[i] = usbdata[i];
								DLog.e(TXS, "RT usbdata[" + i + "]:(" + usbdata[i]
										+ ")");
							}

							if (NAK == modemDataBuffer[0]) {
								DLog.e(TXS, "get response - NAK");
							} else if (ACK == modemDataBuffer[0]) {
								DLog.e(TXS, "get response - ACK");
							} else if (CHAR_C == modemDataBuffer[0]) {
								DLog.e(TXS, "get response - CHAR_C");
							}
							if (CHAR_G == modemDataBuffer[0]) {
								DLog.e(TXS, "get response - CHAR_G");
							}
						} else {
							// DLog.e(TT,"readcount:"+readcount);
							for (int count = 0; count < readcount; count++) {
								readDataBuffer[iWriteIndex] = usbdata[count];
								iWriteIndex++;
								iWriteIndex %= MAX_NUM_BYTES;
							}
							if (iWriteIndex >= iReadIndex) {
								iTotalBytes = iWriteIndex - iReadIndex;
							} else {
								iTotalBytes = (MAX_NUM_BYTES - iReadIndex)
										+ iWriteIndex;
							}

							// DLog.e(TT,"iTotalBytes:"+iTotalBytes);
							if ((MODE_X_MODEM_CHECKSUM_RECEIVE == transferMode)
									|| (MODE_X_MODEM_CRC_RECEIVE == transferMode)
									|| (MODE_X_MODEM_1K_CRC_RECEIVE == transferMode)
									|| (MODE_Y_MODEM_1K_CRC_RECEIVE == transferMode)
									|| (MODE_Z_MODEM_RECEIVE == transferMode)
									|| (MODE_Z_MODEM_SEND == transferMode)) {
								modemReceiveDataBytes[0] += readcount;
								DLog.e(TT, "modemReceiveDataBytes:"
										+ modemReceiveDataBytes[0]);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			DLog.e(TT, "read thread terminate...");
		}
	}

	private void sendData(int numBytes, byte[] buffer) {
		try {
			if (ftDev.isOpen() == false) {
				DLog.e(TT, "SendData: device not open");
				FileManager.writeLog("Device not open!");
				return;
			}

			if (numBytes > 0) {
				SystemClock.sleep(10);
				FileManager.writeLog(">>>>>  "+HelperClass.bytesToHex(buffer));
				ftDev.write(buffer, numBytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// TODO=====================================================================================================================

	protected void onDestroy() {
		super.onDestroy();
		try {
			disconnectFunction();
//			android.os.Process.killProcess(android.os.Process.myPid());
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			am.killBackgroundProcesses(getApplicationContext().getPackageName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void disconnectFunction() {
		try {
			DevCount = -1;
			currentPortIndex = -1;
			isReadThreadEnabled = false;
			isHandlerThreadEnabled=false;
			Thread.sleep(50);

			if (ftDev != null) {
				if (true == ftDev.isOpen()) {
					ftDev.close();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void connection() {
		try {
			 createDeviceList(); 
				if (DevCount > 0) {
					connectFunction();
					setConfig(baudRate, dataBit, stopBit, parity, flowControl);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}