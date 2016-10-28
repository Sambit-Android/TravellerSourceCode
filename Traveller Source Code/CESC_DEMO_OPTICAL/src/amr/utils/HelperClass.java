package amr.utils;

import gurux.dlms.GXDateTime;
import gurux.dlms.enums.ClockStatus;
import gurux.dlms.enums.DateTimeSkips;
import gurux.dlms.internal.GXCommon;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Locale;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HelperClass {
	 
    public static final String UNWANTED_SPACE = "(\\r| |\\n|\\r\\n)+";
    

    public static String roundToKwh(String wh) {

    	try {
    		double kwhDouble =Double.parseDouble(wh);
        	kwhDouble=kwhDouble/1000;// kWh
        	DecimalFormat format = new DecimalFormat("0.00");
        	return format.format(kwhDouble);
        	
    		/*double kwhDouble =Double.parseDouble(wh);
        	kwhDouble=kwhDouble/1000;// kWh
        	String tempVar=String.valueOf(kwhDouble);
        	return tempVar.split("\\.")[0];*/
        	} catch (Exception e) {
			Utils.dirctLogcat(e.getMessage()+"   Parse KWH");
			return wh;
		}
    }
 
    public static String hexStringToString(String string) {
        String hexString = string.replaceAll(UNWANTED_SPACE, "").trim();
        try {
            byte[] bytes = Hex.decodeHex(hexString.toCharArray());
            hexString = new String(bytes, "UTF-8");
            return hexString;  //RETURN CONVERTED STRING
        } catch (DecoderException | UnsupportedEncodingException ex) {
            return string;   //NOT HEX FORMAT. RETURN SAME STRING
        }
    }

    public static byte[] fromHexString(final String encoded) {
        if ((encoded.length() % 2) != 0) {
            throw new IllegalArgumentException("Input string must contain an even number of characters");
        }

        final byte result[] = new byte[encoded.length() / 2];
        final char enc[] = encoded.toCharArray();
        for (int i = 0; i < enc.length; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            result[i / 2] = (byte) Integer.parseInt(curr.toString(), 16);
        }
        return result;
    }

    public static String valueRounder(String value) {//FOR METER READING
        value = value.replace(",", "").trim();
        double valueDouble = Double.parseDouble(value) / 1000;
        value = String.valueOf(valueDouble);
        if (value.contains(".")) {
            value = value.split("\\.")[0] + "." + value.split("\\.")[1].subSequence(0, 1);
        }
        return value;
    }

    static String getMeterCode(String meterName) {
        String meterCode;
        switch (meterName) {
            case "Secure":
                meterCode = "1";
                break;
            case "L&T":
                meterCode = "3";
                break;
            case "Genus":
                meterCode = "4";
                break;
            default:
                meterCode = "0";
                break;
        }
        return meterCode;
    }

    static String getMinute(String string) {
        try {
        int  minutes = (Integer.parseInt(string.trim())% 3600)/60;
           return minutes+""; //IF IT IS PROPER INTEGER, RETURN MINUTE
        } catch (Exception e) {
            return string; //IF IT IS NOT PROPER INTEGER, RETURN SAME STRING
        }
    }
    
    public static String getOctetStringDate(String hexString){
        try {
             hexString=hexString.replaceAll(UNWANTED_SPACE, "");
            int [] pos=new int[1];
            byte[] buff = fromHexString(hexString);
            //Get year.
            int year = GXCommon.getUInt16(buff, pos);
            //Get month
            int month = buff[pos[0]++];
            //Get day
            int day = buff[pos[0]++];
            //Skip week day
            pos[0]++;
            //Get time.
            int hour = buff[pos[0]++];
            int minute = buff[pos[0]++];
            int second = buff[pos[0]++];
            int ms = buff[pos[0]++] & 0xFF;
            if (ms != 0xFF)
            {
                ms *= 10;
            }
            else
            {
                ms = 0;
            }
            int deviation = GXCommon.getInt16(buff, pos);  
            ClockStatus status = ClockStatus.forValue(buff[pos[0]++] & 0xFF);                                                            
            GXDateTime dt = new GXDateTime();            
            dt.setStatus(status);
            java.util.Set<DateTimeSkips> Skip = EnumSet.noneOf(DateTimeSkips.class);
            if (year < 1 || year == 0xFFFF)
            {
                Skip.add(DateTimeSkips.YEAR);
                java.util.Calendar tm = java.util.Calendar.getInstance();
                year = tm.get(Calendar.YEAR);            
            }
            dt.setDaylightSavingsBegin(month == 0xFE);
            dt.setDaylightSavingsEnd(month == 0xFD);
            if (month < 1 || month > 12)
            {
                Skip.add(DateTimeSkips.MONTH);
                month = 0;
            }
            else
            {
                month -= 1;        
            }
            if (day == -1 || day == 0 || day > 31)
            {
                Skip.add(DateTimeSkips.DAY);
                day = 1;
            }
            else if (day < 0)
            {            
                Calendar cal = Calendar.getInstance();
                day = cal.getActualMaximum(Calendar.DATE) + day + 3;
            }
            if (hour < 0 || hour > 24)
            {
                Skip.add(DateTimeSkips.HOUR);
                hour = 0;
            }
            if (minute < 0 || minute > 60 )
            {
                Skip.add(DateTimeSkips.MINUTE);
                minute = 0;
            }        
            if (second < 0 || second > 60)
            {
                Skip.add(DateTimeSkips.SECOND);
                second = 0;
            }
            //If ms is Zero it's skipped.
            if (ms < 1 || ms > 1000)
            {
                Skip.add(DateTimeSkips.MILLISECOND);
                ms = 0;
            }          
            java.util.Calendar tm;
            if ((deviation & 0xFFFF) != 0x8000)
            {
                tm = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("IST"));            
                tm.add(java.util.Calendar.MINUTE, deviation);                                            
            }
            else
            {
                tm = java.util.Calendar.getInstance();
            }            
            tm.set(year, month, day, hour, minute, second);       
            if (ms != 0)
            {
                tm.set(Calendar.MILLISECOND, ms);
            }        
            dt.setValue(tm.getTime());
            dt.setSkip(Skip);
            System.out.print(dt+" +++++++++++++++++++++++");
           
            return dt.toString(); //RETURNING THE CONVERTED DATE IN STRING FORMAT
        } catch (Exception e) {
            System.out.println(e);
            return hexString; //RETURNING THE SAME STRING
        }
        
        }

	public static String hexToAscii(String s) throws IllegalArgumentException {
		int n = s.length();
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 2) {
			char a = s.charAt(i);
			char b = s.charAt(i + 1);
			sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
		}
		return sb.toString();
	}

	private static int hexToInt(char ch) {
		if ('a' <= ch && ch <= 'f') {
			return ch - 'a' + 10;
		}
		if ('A' <= ch && ch <= 'F') {
			return ch - 'A' + 10;
		}
		if ('0' <= ch && ch <= '9') {
			return ch - '0';
		}
		throw new IllegalArgumentException(String.valueOf(ch));
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
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };
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

	public static String asciiToHex(String asciiValue) {
		char[] chars = asciiValue.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}
	 public static double hexToDoubleConvert(String num) {
	        String digits = "0123456789ABCDEF";
	        num = num.toUpperCase(Locale.getDefault());
	        double temp1 = 0;
	        for (int i = 0; i < num.length(); i++) {
	            char c = num.charAt(i);
	            double d = digits.indexOf(c);
	           temp1 = 16*temp1 + d;
	        }
	        return temp1;
		}
}
