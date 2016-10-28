package amr.rf.utils;

import amr.rf.catcher.MasterActivity;
import android.os.SystemClock;

public class TimeOutManager extends Thread {

	public static boolean isRunning;
	static int timeOut=0;
	public TimeOutManager(int timeOut) {
		TimeOutManager.timeOut=timeOut;
	}

	@Override
	public synchronized void start() {
		super.start();
		isRunning=true;
	}
	
	@Override
	public void interrupt() {
		super.interrupt();
		isRunning=false;
	}
	
	@Override
	public void run() {
		super.run();
		 if(isTimeOut()) {
         	MasterActivity.isReadingMeter=false;
         	SystemClock.sleep(10); // GIVE DELAY TO STOP THE MAIN READING THREAD
         	MasterActivity.isReadingMeter=true;
         	interrupt();
         }
	}
	static boolean isTimeOut() {
	    long endTimeMillis = System.currentTimeMillis() + timeOut; //timeOut SECONDS FOR READING KWH THROUGH GROUP BILLING
	    while (isRunning) {
	        if (System.currentTimeMillis() > endTimeMillis) {
	            return true;
	        }
	    }
	    return false;
	}
}
