package com.utils;

import android.os.Environment;
import android.os.StatFs;

public class AvailableSpaceHandler {
	//*********
    //Variables
    /**
     * Number of bytes in one KB = 2<sup>10</sup>
     */
    public final static long SIZE_KB = 1024L;

    /**
     * Number of bytes in one MB = 2<sup>20</sup>
     */
    public final static long SIZE_MB = SIZE_KB * SIZE_KB;

    /**
     * Number of bytes in one GB = 2<sup>30</sup>
     */
    public final static long SIZE_GB = SIZE_KB * SIZE_KB * SIZE_KB;
    public final static float SIZE_GB1 = SIZE_KB * SIZE_KB * SIZE_KB;

    //********
    // Methods

    /**
     * @return Number of bytes available on external storage
     */
    public static long getExternalAvailableSpaceInBytes() {
        long availableSpace = -1L;
        try {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableSpace;
    }
    
    
    
    
    public static float getExternalTotalSpaceInBytes() {
        float totalSpace = -1f;
        try {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            totalSpace = (float) stat.getBlockCount() * (float) stat.getBlockSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSpace;
    }
    
  
    

    /**
     * @return Number of kilo bytes available on external storage
     */
    public static long getExternalAvailableSpaceInKB(){
        return getExternalAvailableSpaceInBytes()/SIZE_KB;
    }
    /**
     * @return Number of Mega bytes available on external storage
     */
    public static long getExternalAvailableSpaceInMB(){
        return getExternalAvailableSpaceInBytes()/SIZE_MB;
    }
    
    
    
    

    /**
     * @return gega bytes of bytes available on external storage
     */
    public static long getExternalAvailableSpaceInGB(){
        return getExternalAvailableSpaceInBytes()/SIZE_GB;
    }

    
    /**
     *@returns giga bytes of bytes of total memory in external storage 
     */
    public static float getExternalTotalSpaceInGB(){
        return getExternalTotalSpaceInBytes()/SIZE_GB1;
    }
    
    
    
    /**
     * @return Total number of available blocks on external storage
     */
    public static long getExternalStorageAvailableBlocks() {
        long availableBlocks = -1L;
        try {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            availableBlocks = stat.getAvailableBlocks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return availableBlocks;
    }
}
