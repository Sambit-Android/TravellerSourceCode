package amr.utils;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SWAMI SHARANAM
 */
public class FrameMaster {
    
    public static byte[] ping() { //23 means 17
        byte[] pingArray = createByteFrame( 23, new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1}, new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1}, new byte[]{(byte) 80, (byte) 73});
        System.out.println("  " + bytesToHex(pingArray));
        return pingArray;
    }
    
    public static byte[] discover(String deviceAddress) {
        return createByteFrame(8, hexStringToByteArray(deviceAddress), hexStringToByteArray(deviceAddress), new byte[0]);
    }
     
    public static byte[] setContext(String deviceAddress, int meterChannel) { // SETTING THE COMMUNICATION CHANNEL
    	  byte [] dataContext=new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) meterChannel, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
          byte[] setContext = createByteFrame( 64, hexStringToByteArray(deviceAddress), hexStringToByteArray(deviceAddress), dataContext);
          System.out.println("  " + bytesToHex(setContext));
          return setContext;
    }
    
    public static byte[] openChannel(String deviceAddress,String meterAddress) {
    	 byte[] openChannel = createByteFrame( 1, hexStringToByteArray(deviceAddress), hexStringToByteArray(meterAddress), new byte[0]);
         System.out.println("  " + bytesToHex(openChannel));
         return openChannel;
    }
    public static byte[] closeChannel(String deviceAddress,String meterAddress) {
    	byte[] closeChannel = createByteFrame( 2, hexStringToByteArray(deviceAddress), hexStringToByteArray(meterAddress), new byte[0]);
        System.out.println("  " + bytesToHex(closeChannel));
        return closeChannel;
   }
    public static byte[] disConnectMeter(String deviceAddress,String meterAddress) { //c4e1028e009a6b
   	 	byte[] diconnnect = createByteFrame( 3, hexStringToByteArray(deviceAddress), hexStringToByteArray(meterAddress),  hexStringToByteArray("c4e1028e009a6b"));
   	 	System.out.println("  " + bytesToHex(diconnnect));
   	 	return diconnnect;
   }
    
    public static byte[] reConnectMeter(String deviceAddress,String meterAddress) { //c4e1028e018a4a
   	 	byte[] reconnect = createByteFrame( 3, hexStringToByteArray(deviceAddress), hexStringToByteArray(meterAddress),  hexStringToByteArray("c4e1028e018a4a"));
   	 	System.out.println("  " + bytesToHex(reconnect));
   	 	return reconnect;
   }
    
    public static byte[] readInstantaneous(String deviceAddress,String meterAddress) { //c4e101604298
   	 	byte[] openChannel = createByteFrame( 3, hexStringToByteArray(deviceAddress), hexStringToByteArray(meterAddress),  hexStringToByteArray("c4e101604298"));
   	 	System.out.println("  " + bytesToHex(openChannel));
   	 	return openChannel;
   }
    
    public static byte[] createByteFrame(int frameId, byte[] source, byte[] destination, byte[] data) {
        int i2 = 0;
        List<Byte> frame = new ArrayList<Byte>();
        frame.add((byte) -55); // MAGIC NUMBER C9 
        frame.add((byte) 30);  // MAGIC NUMBER IE 
        frame.add((byte) -49); // MAGIC NUMBER CF
        frame.add((byte) 87);  // MAGIC NUMBER 57
        int length = (frameId == 8 || frameId == 1 || frameId == 2) ? 14 : data.length + 14;
        frame.add((byte) 0);
        frame.add((byte) length); // DATA SIZE
        frame.add((byte) frameId); // FRAME ID
        frame.add((byte) 0);
        frame.add((byte) 3);  //FLAG
        frame.add(source[0]);
        frame.add(source[1]);
        frame.add(source[2]);
        frame.add(source[3]);
        frame.add(destination[0]); 
        frame.add(destination[1]);
        frame.add(destination[2]);
        frame.add(destination[3]);
        frame.add(computeCheckSum(frame));
        for (length = 0; length < data.length && frameId != 8 && frameId != 1 && frameId != 2; length++) {
            frame.add(Byte.valueOf(data[length]));
        }
        int i3 = 0;
        for (int i4 = 4; i4 < frame.size(); i4++) {
            i3 = crc_update(i3, (char) ((Byte) frame.get(i4)).byteValue());
        }
        byte[] hexStringToByteArray = hexStringToByteArray(Integer.toHexString(i3));
        for (byte valueOf : hexStringToByteArray) {
            frame.add(Byte.valueOf(valueOf));
        }
        hexStringToByteArray = new byte[frame.size()];
        while (i2 < hexStringToByteArray.length) {
            hexStringToByteArray[i2] = ((Byte) frame.get(i2)).byteValue();
            i2++;
        }
        System.out.println("");
        return hexStringToByteArray;
    }

    public static byte computeCheckSum(List<Byte> list) {
        int i = 0;
        for (int i2 = 4; i2 < list.size(); i2++) {
            i += ((Byte) list.get(i2)).byteValue();
        }
        return (byte) i;
    }

    public static int crc_update(int i, char c) {
        int i2 = 0;
        int initialVal='\u0000';
       /* if(isBilling) {
        	initialVal='\uffff' or '\u00ff';
        }*/
        if (c > initialVal) {//u0000  for RF COMMUNICATION and uffff for METER COMMUNICATION
            i2 = c % 256;
        }
        int i3 = ((char) i2) ^ ((i >>> 8) | (i << 8));
        i3 ^= (i3 & 255) >>> 4;
        i3 ^= i3 << 12;
        return (char) (i3 ^ ((i3 & 255) << 5));
    }

    private static byte[] hexStringToByteArray(String str) {
        int i = 0; 
        int length = str.length();
        if (length == 3) {
            return new byte[]{(byte) Character.digit(str.charAt(0), 16), (byte) ((Character.digit(str.charAt(1), 16) << 4) + Character.digit(str.charAt(2), 16))};
        }
        byte[] bArr = new byte[(length / 2)];
        while (i < length) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
            i += 2;
        }
        return bArr;
    }
 
    static final int HEX_SIZE = 3;
    static final int MAX_BYTE_SIZE = 0xFF;
    static final int NIBBLE = 4;
    static final int LOW_BYTE_PART = 0x0F;

    public static String bytesToHex(final byte[] bytes) {
        // Return empty string if array is empty.
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * HEX_SIZE];
        int tmp;
        for (int pos = 0; pos != bytes.length; ++pos) {
            tmp = bytes[pos] & MAX_BYTE_SIZE;
            hexChars[pos * HEX_SIZE] = hexArray[getHighByte(tmp)];
            hexChars[pos * HEX_SIZE + 1] = hexArray[getLowByte(tmp)];
            hexChars[pos * HEX_SIZE + 2] = ' ';
        }
        return new String(hexChars, 0, hexChars.length - 1);
    }

    public static byte getHighByte(final int value) {
        return (byte) (value >>> NIBBLE);
    }

    public static byte getLowByte(final int value) {
        return (byte) (value & LOW_BYTE_PART);
    }
}
