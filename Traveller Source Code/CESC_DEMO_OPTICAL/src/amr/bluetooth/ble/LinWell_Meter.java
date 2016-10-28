package amr.bluetooth.ble;


public class LinWell_Meter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String current_details = "3A 34 00 00 01 FF 44 77 00 30 32 31 38 33 31 37 37 2D AD A6 1C 00 00 00 00 00 00 00 00 00 00 E8 03 00 00 02 00 00 00 00 00 E6 01 00 00 A4 01 00 00 00 00 00 00 00 00 00 00 A0 5F 0D 0A".replace(" ", "");

		// --- DATA IN BYTES FORMATE-- /
				byte[] inputByteArray_00 =Conversation. fromHexString(current_details) ;
				System.out.println("input:"+inputByteArray_00.length);
				String ip_meterNo = current_details.substring(18, 34);
				
				String MeterSerialNumber =Conversation .hexToAscii(ip_meterNo);
				System.out.println("ip_meterNo: "+ip_meterNo+" MeterSerialNumber:"+MeterSerialNumber);
				String Voltage_ip =  Conversation.byteArrayToHex(new byte[]{inputByteArray_00[22]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[21]});
				String VOLTAGE =Conversation .hexToAscii(Voltage_ip);
				
				System.out.println("Voltage_ip: "+Voltage_ip+" VOLTAGE:"+VOLTAGE);
				String Current_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[28]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[27]});
				String CURRENT =Conversation .hexToAscii(Current_ip);
				
				System.out.println("Current_ip: "+Current_ip+" CURRENT:"+CURRENT);
				
				String PowerFactor_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[32]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[31]});
				String POWERFACTOR = Float.parseFloat(Conversation .hexToDecimal(PowerFactor_ip))/1000 +"";
				
				System.out.println("PowerFactor_ip: "+PowerFactor_ip+" POWERFACTOR:"+POWERFACTOR);
				
				String Cumulative_kWh_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[48]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[47]})+
						Conversation.byteArrayToHex(new byte[]{inputByteArray_00[46]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[45]});
				String CUMULATIVE_KWH = Float.parseFloat(Conversation .hexToDecimal(Cumulative_kWh_ip))/100 +"";
				System.out.println("Cumulative_kWh_ip: "+Cumulative_kWh_ip+" CUMULATIVE_KWH:"+CUMULATIVE_KWH);
	}
	
	public LinWell_Meter() {
	}
	
	/**
	 * @param current_details
	 * @return String[] MeterSerialNumber, VOLTAGE , CURRENT , POWERFACTOR , CUMULATIVE_KWH
	 */
	public String[] getLinWelData(String current_details){
		String[] result = new String[5] ;
		// --- DATA IN BYTES FORMATE-- /
		byte[] inputByteArray_00 =Conversation. fromHexString(current_details) ;
		System.out.println("input:"+inputByteArray_00.length);
		String ip_meterNo = current_details.substring(18, 34);
		
		
		String MeterSerialNumber =Conversation .hexToAscii(ip_meterNo);
		System.out.println("ip_meterNo: "+ip_meterNo+" MeterSerialNumber:"+MeterSerialNumber);
		String Voltage_ip =  Conversation.byteArrayToHex(new byte[]{inputByteArray_00[22]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[21]});
		String VOLTAGE =Conversation .hexToAscii(Voltage_ip);
		
		System.out.println("Voltage_ip: "+Voltage_ip+" VOLTAGE:"+VOLTAGE);
		String Current_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[28]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[27]});
		String CURRENT =Conversation .hexToAscii(Current_ip);
		
		System.out.println("Current_ip: "+Current_ip+" CURRENT:"+CURRENT);
		
		String PowerFactor_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[32]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[31]});
		String POWERFACTOR = Float.parseFloat(Conversation .hexToDecimal(PowerFactor_ip))/1000 +"";
		
		System.out.println("PowerFactor_ip: "+PowerFactor_ip+" POWERFACTOR:"+POWERFACTOR);
		
		String Cumulative_kWh_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[48]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[47]})+
				Conversation.byteArrayToHex(new byte[]{inputByteArray_00[46]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[45]});
		String CUMULATIVE_KWH = Float.parseFloat(Conversation .hexToDecimal(Cumulative_kWh_ip))/100 +"";
		System.out.println("Cumulative_kWh_ip: "+Cumulative_kWh_ip+" CUMULATIVE_KWH:"+CUMULATIVE_KWH);
		
		
		
		
		
		
		
		
		
		
		String meterTimeDate =Conversation .hexToDecimal(Conversation.byteArrayToHex(new byte[]{inputByteArray_00[20]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[19]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[18]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[17]}));
		
		System.out.println(meterTimeDate+"  =================Meter Time date");
	//	String timeStamp=Conversation.hexToDecimal(meterTimeDate);
		long timeSta=Long.parseLong(meterTimeDate);
		java.util.Date time=new java.util.Date((long)timeSta*1000);
		System.out.println(" ===========================  "+time+"  =========================       ");
		///05-11 18:34:05.247: E/AndroidRuntime(18616): java.lang.NumberFormatException: Invalid int: "F51D00B3"

		
		
		
		
		
		
		
		
		
		
		
		result[0] = MeterSerialNumber;
		result[1] = VOLTAGE;
		result[2] = CURRENT;
		result[3] = POWERFACTOR;
		result[4] = CUMULATIVE_KWH;
		
		return result;
	}

	public String[] getLinWelData53(String current_details){
		String[] result = new String[2] ;
		// --- DATA IN BYTES FORMATE-- /
		byte[] inputByteArray_00 =Conversation. fromHexString(current_details) ;
		System.out.println("input:"+inputByteArray_00.length);
		String ip_meterNo = current_details.substring(18, 34);
		
		
		String MeterSerialNumber =Conversation .hexToAscii(ip_meterNo);
		System.out.println("ip_meterNo: "+ip_meterNo+" MeterSerialNumber:"+MeterSerialNumber);
		
		
		/*String Voltage_ip =  Conversation.byteArrayToHex(new byte[]{inputByteArray_00[22]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[21]});
		String VOLTAGE =Conversation .hexToAscii(Voltage_ip);
		
		System.out.println("Voltage_ip: "+Voltage_ip+" VOLTAGE:"+VOLTAGE);*/
		
		
		/*String Current_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[28]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[27]});
		String CURRENT =Conversation .hexToAscii(Current_ip);
		
		System.out.println("Current_ip: "+Current_ip+" CURRENT:"+CURRENT);*/
		
		/*String PowerFactor_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[32]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[31]});
		String POWERFACTOR = Float.parseFloat(Conversation .hexToDecimal(PowerFactor_ip))/1000 +"";
		
		System.out.println("PowerFactor_ip: "+PowerFactor_ip+" POWERFACTOR:"+POWERFACTOR);*/
		
		
		
		String Cumulative_kWh_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[40]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[39]})+
				                   Conversation.byteArrayToHex(new byte[]{inputByteArray_00[38]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[37]});
		
		
		String CUMULATIVE_KWH = Float.parseFloat(Conversation .hexToDecimal(Cumulative_kWh_ip))/100 +"";
		System.out.println("Cumulative_kWh_ip: "+Cumulative_kWh_ip+" CUMULATIVE_KWH:"+CUMULATIVE_KWH);
		
		result[0] = MeterSerialNumber;
		/*result[1] = VOLTAGE;
		result[2] = CURRENT;
		result[3] = POWERFACTOR;*/
		result[1] = CUMULATIVE_KWH;
		
		return result;
	}
	
	
	
	public String[] getMeterOtherDetails(String current_details){
		String[] result = new String[5] ;
		// --- DATA IN BYTES FORMATE-- /
		byte[] inputByteArray_00 =Conversation. fromHexString(current_details) ;
		System.out.println("input:"+inputByteArray_00.length);
		String ip_meterNo = current_details.substring(18, 34);
		
		
		String MeterSerialNumber =Conversation .hexToAscii(ip_meterNo);
		System.out.println("ip_meterNo: "+ip_meterNo+" MeterSerialNumber:"+MeterSerialNumber);
		String Voltage_ip =  Conversation.byteArrayToHex(new byte[]{inputByteArray_00[22]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[21]});
		String VOLTAGE =Conversation .hexToAscii(Voltage_ip);
		
		System.out.println("Voltage_ip: "+Voltage_ip+" VOLTAGE:"+VOLTAGE);
		String Current_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[28]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[27]});
		String CURRENT =Conversation .hexToAscii(Current_ip);
		
		System.out.println("Current_ip: "+Current_ip+" CURRENT:"+CURRENT);
		
		String PowerFactor_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[32]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[31]});
		String POWERFACTOR = Float.parseFloat(Conversation .hexToDecimal(PowerFactor_ip))/1000 +"";
		
		System.out.println("PowerFactor_ip: "+PowerFactor_ip+" POWERFACTOR:"+POWERFACTOR);
		
		String Cumulative_kWh_ip = Conversation.byteArrayToHex(new byte[]{inputByteArray_00[48]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[47]})+
				Conversation.byteArrayToHex(new byte[]{inputByteArray_00[46]})+Conversation.byteArrayToHex(new byte[]{inputByteArray_00[45]});
		String CUMULATIVE_KWH = Float.parseFloat(Conversation .hexToDecimal(Cumulative_kWh_ip))/100 +"";
		System.out.println("Cumulative_kWh_ip: "+Cumulative_kWh_ip+" CUMULATIVE_KWH:"+CUMULATIVE_KWH);
		
		result[0] = MeterSerialNumber;
		result[1] = VOLTAGE;
		result[2] = CURRENT;
		result[3] = POWERFACTOR;
		result[4] = CUMULATIVE_KWH;
		
		return result;
	}
	
	
	
}
