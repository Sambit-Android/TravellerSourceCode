package com.utils.helper;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

public class MemoryCheck {
	 @SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public String Memory()
	    {
		 	final long KILOBYTE = 1024;
			long internalTotal;
			long internalFree;
			long externalTotal;
			long externalFree;
			StatFs internalStatFs = new StatFs( Environment.getRootDirectory().getAbsolutePath() );
				StatFs externalStatFs = new StatFs( Environment.getExternalStorageDirectory().getAbsolutePath() );

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
				    internalTotal = ( internalStatFs.getBlockCountLong() * internalStatFs.getBlockSizeLong() ) / ( KILOBYTE * KILOBYTE );
				    internalFree = ( internalStatFs.getAvailableBlocksLong() * internalStatFs.getBlockSizeLong() ) / ( KILOBYTE * KILOBYTE );
				    externalTotal = ( externalStatFs.getBlockCountLong() * externalStatFs.getBlockSizeLong() ) / ( KILOBYTE * KILOBYTE );
				    externalFree = ( externalStatFs.getAvailableBlocksLong() * externalStatFs.getBlockSizeLong() ) / ( KILOBYTE * KILOBYTE );
				}
				else {
				    internalTotal = ( (long) internalStatFs.getBlockCount() * (long) internalStatFs.getBlockSize() ) / ( KILOBYTE * KILOBYTE );
				    internalFree = ( (long) internalStatFs.getAvailableBlocks() * (long) internalStatFs.getBlockSize() ) / ( KILOBYTE * KILOBYTE );
				    externalTotal = ( (long) externalStatFs.getBlockCount() * (long) externalStatFs.getBlockSize() ) / ( KILOBYTE * KILOBYTE );
				    externalFree = ( (long) externalStatFs.getAvailableBlocks() * (long) externalStatFs.getBlockSize() ) / ( KILOBYTE * KILOBYTE );
				}

				long total = internalTotal + externalTotal;
				long free = internalFree + externalFree;
				
				return total+"@"+free+"@"+externalTotal+"@"+externalFree;
	    }
}
