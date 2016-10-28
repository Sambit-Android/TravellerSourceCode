package amr.rf.utils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HelperClass {
	public static final String UNWANTED_SPACE = "(\\r| |\\n|\\r\\n)+";
	
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
}
