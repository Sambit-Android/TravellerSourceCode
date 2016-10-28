package amr.utils;

import com.physicaloid.lib.usb.driver.uart.UartConfig;

public class MeterSettings {
	public static final int DISP_CHAR = 0;
	public static final int DISP_DEC = 1;
	public static final int DISP_HEX = 2;

	// Linefeed Code Settings
	public static final int LINEFEED_CODE_CR = 0;
	public static final int LINEFEED_CODE_CRLF = 1;
	public static final int LINEFEED_CODE_LF = 2;

	public static int mDisplayType = DISP_HEX;
	public static int mReadLinefeedCode = LINEFEED_CODE_LF;
	public static int mBaudrate = 9600;
	public static int mDataBits = UartConfig.DATA_BITS8;
	public static int mParity = UartConfig.PARITY_NONE;
	public static int mStopBits = UartConfig.STOP_BITS1;
	public static int mFlowControl = UartConfig.FLOW_CONTROL_ON;
	public static boolean rtsOn = true;
	public static boolean dtrOn = true;;
	

	
}
