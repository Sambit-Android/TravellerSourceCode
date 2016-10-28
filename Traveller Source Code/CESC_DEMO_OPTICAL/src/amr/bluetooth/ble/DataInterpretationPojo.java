package amr.bluetooth.ble;

import android.graphics.Typeface;



public class DataInterpretationPojo {

	// Default settings
		public int       mTextFontSize  ;//	 = 12;
		public Typeface  mTextTypeface  	 ;// = Typeface.MONOSPACE;
		public int       mDisplayType      ;//  = DISP_CHAR;
		public int       mReadLinefeedCode  ;// = LINEFEED_CODE_LF;
		public int       mWriteLinefeedCode  ;//= LINEFEED_CODE_LF;
		public int       mBaudrate 	 ;//	 = 9600;
		public int       mDataBits 		 ;// = UartConfig.DATA_BITS8;
		public int       mParity 		 ;//	 = UartConfig.PARITY_NONE;
		public int       mStopBits 	 ;//	 = UartConfig.STOP_BITS1;
		public int       mFlowControl 		 ;// = UartConfig.FLOW_CONTROL_OFF;
		public String    mEmailAddress 	 ;// = "@gmail.com";
		
		public DataInterpretationPojo() {
		}
		
		
		
		
		public DataInterpretationPojo(int mDisplayType, int mReadLinefeedCode,
				int mWriteLinefeedCode) {
			super();
			this.mDisplayType = mDisplayType;
			this.mReadLinefeedCode = mReadLinefeedCode;
			this.mWriteLinefeedCode = mWriteLinefeedCode;
		}




		public DataInterpretationPojo(int mTextFontSize,
				Typeface mTextTypeface, int mDisplayType,
				int mReadLinefeedCode, int mWriteLinefeedCode, int mBaudrate,
				int mDataBits, int mParity, int mStopBits, int mFlowControl,
				String mEmailAddress) {
			super();
			this.mTextFontSize = mTextFontSize;
			this.mTextTypeface = mTextTypeface;
			this.mDisplayType = mDisplayType;
			this.mReadLinefeedCode = mReadLinefeedCode;
			this.mWriteLinefeedCode = mWriteLinefeedCode;
			this.mBaudrate = mBaudrate;
			this.mDataBits = mDataBits;
			this.mParity = mParity;
			this.mStopBits = mStopBits;
			this.mFlowControl = mFlowControl;
			this.mEmailAddress = mEmailAddress;
		}


		public int getmTextFontSize() {
			return mTextFontSize;
		}


		public void setmTextFontSize(int mTextFontSize) {
			this.mTextFontSize = mTextFontSize;
		}


		public Typeface getmTextTypeface() {
			return mTextTypeface;
		}


		public void setmTextTypeface(Typeface mTextTypeface) {
			this.mTextTypeface = mTextTypeface;
		}


		public int getmDisplayType() {
			return mDisplayType;
		}


		public void setmDisplayType(int mDisplayType) {
			this.mDisplayType = mDisplayType;
		}


		public int getmReadLinefeedCode() {
			return mReadLinefeedCode;
		}


		public void setmReadLinefeedCode(int mReadLinefeedCode) {
			this.mReadLinefeedCode = mReadLinefeedCode;
		}


		public int getmWriteLinefeedCode() {
			return mWriteLinefeedCode;
		}


		public void setmWriteLinefeedCode(int mWriteLinefeedCode) {
			this.mWriteLinefeedCode = mWriteLinefeedCode;
		}


		public int getmBaudrate() {
			return mBaudrate;
		}


		public void setmBaudrate(int mBaudrate) {
			this.mBaudrate = mBaudrate;
		}


		public int getmDataBits() {
			return mDataBits;
		}


		public void setmDataBits(int mDataBits) {
			this.mDataBits = mDataBits;
		}


		public int getmParity() {
			return mParity;
		}


		public void setmParity(int mParity) {
			this.mParity = mParity;
		}


		public int getmStopBits() {
			return mStopBits;
		}


		public void setmStopBits(int mStopBits) {
			this.mStopBits = mStopBits;
		}


		public int getmFlowControl() {
			return mFlowControl;
		}


		public void setmFlowControl(int mFlowControl) {
			this.mFlowControl = mFlowControl;
		}


		public String getmEmailAddress() {
			return mEmailAddress;
		}


		public void setmEmailAddress(String mEmailAddress) {
			this.mEmailAddress = mEmailAddress;
		}
		
		
		
		
}
