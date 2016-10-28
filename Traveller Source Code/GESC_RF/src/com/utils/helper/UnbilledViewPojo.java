package com.utils.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UnbilledViewPojo {

	 int sl_no;
	 String consumer_No ;
	 String tariff ;
	 String con_name ;
	public  UnbilledViewPojo(int sl_no, String consumer_No, String tariff,
			String con_name) {
		super();
		this.sl_no = sl_no;
		this.consumer_No = consumer_No;
		this.tariff = tariff;
		this.con_name = con_name;
	}
	public int getSl_no() {
		return sl_no;
	}
	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}
	public String getConsumer_No() {
		return consumer_No;
	}
	public void setConsumer_No(String consumer_No) {
		this.consumer_No = consumer_No;
	}
	public String getTariff() {
		return tariff;
	}
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	public String getCon_name() {
		return con_name;
	}
	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}
	
	{
		/*if(datCountValue== 0)
		{
			System.out.println("if datCountValue== 0  " );
			datCountValue = 1 ;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(mbDate);
			calendar.add(Calendar.DATE, 1);
			mbDate =  new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
		}*/
	}
	
}
