
public class Sambit {

	public static void main(String[] args) {
		
		System.out.println(getDateTime("1CA6AD2D"));
	}
	

	
	private static String  getDateTime(String dateTime){
		dateTime=hexToBinary(dateTime);
		System.out.println(dateTime);
		System.out.println(dateTime.length());
		String	Second = binaryToDecimal(dateTime.substring(0, 6));
	    String	Minute = binaryToDecimal(dateTime.substring(6, 11));
	    String	Hour =binaryToDecimal(dateTime.substring(12, 17));
		String	Year  =binaryToDecimal(dateTime.substring(17, 23));
		String	Month =binaryToDecimal(dateTime.substring(23, 27));
		String	Date=binaryToDecimal(dateTime.substring(27, 29));
	return Date+"-"+Month+"-"+Year+" "+Hour+":"+Minute+":"+Second ;
	}
	//26/03/15 11:42:37 
	public static String hexToBinary(String hex) {
	    int i = Integer.parseInt(hex, 16);
	    String bin =String.format("%s", Integer.toBinaryString(i)).replace(' ', '0');;
	    return bin;
	}
	public static String binaryToDecimal(String s){
	    int n = Integer.parseInt(s, 2);
	    return String.format("%2s",n ).replace(' ', '0');
	}

}
