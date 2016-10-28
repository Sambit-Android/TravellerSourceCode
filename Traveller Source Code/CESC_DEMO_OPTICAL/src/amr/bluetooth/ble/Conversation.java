package amr.bluetooth.ble;

import java.text.NumberFormat;
import java.text.ParseException;

public class Conversation {
	
/*----------UTILL METHODS ----------*/
	
	
	public static byte[] fromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("Input string must contain an even number of characters");

	    final byte result[] = new byte[encoded.length()/2];
	    final char enc[] = encoded.toCharArray();
	    for (int i = 0; i < enc.length; i += 2) {
	        StringBuilder curr = new StringBuilder(2);
	        curr.append(enc[i]).append(enc[i + 1]);
	        result[i/2] = (byte) Integer.parseInt(curr.toString(), 16);
	    }
	    return result;
	}
	public static String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b & 0xff).toUpperCase());
		   return sb.toString();
		}
	
	
	public static String hexToBinary(String hex) {
	    int i = Integer.parseInt(hex, 16);
	    String bin =String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');;
	    return bin;
	}
	public static String binaryToDecimal(String s){
	    int n = Integer.parseInt(s, 2);
	    return String.format("%2s",n ).replace(' ', '0');
	}
	public static String hexToDecimal(String s){
	    int n = Integer.parseInt(s, 16);
	    return n+"";
	}
	
	public static String hexToAscii(String s) {
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
		  if ('a' <= ch && ch <= 'f') { return ch - 'a' + 10; }
		  if ('A' <= ch && ch <= 'F') { return ch - 'A' + 10; }
		  if ('0' <= ch && ch <= '9') { return ch - '0'; }
		  throw new IllegalArgumentException(String.valueOf(ch));
		}
	
	public static byte[] hexStringToByteArray(String s) {
		System.out.println(s+"   ===============ssss=====================");

	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+ Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	public static  boolean isValueCurr(String string){
	    	boolean b = false ;
	    	try
	    	 {
	    	      NumberFormat.getInstance().parse(string);
	    	      b= true ;
	    	 }
	    	 catch(ParseException e)
	    	 {
	    	     //Not a number.
	    	 }
	    	return b ;
	    }
	
	/*public static String AsciiToDesimal(String s) {
		char x;
		String newDecimal="";
		int[] t = new int[s.length()];
		for(int i = 0; i < s.length(); i++)
		{
		x = s.charAt(i);
		int z = (int) x;
		t[i] = z;
		newDecimal=newDecimal+z;
		}
		return newDecimal;
		}*/
	
	
	
	
}
