package com.bcits.uitls.maps;

public class MapData {

	public MapData(){

	}
	public MapData(String rr_no, double latitude, double longitude, String location){

		this.RR_NO=rr_no;
		this.LATITUDE = latitude;
		this.LONGITUDE = longitude;
		this.LOCATION = location;
	}
	String RR_NO;
	public String getRR_NO() {
		return RR_NO;
	}
	public void setRR_NO(String rR_NO) {
		RR_NO = rR_NO;
	}
	public double getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(double lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public double getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	double LATITUDE;
	double LONGITUDE;
	String LOCATION;





}
