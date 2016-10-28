package com.utils.helper;

import org.json.JSONException;
import org.json.JSONObject;

public class LiveStatusHelper {
	public String sitecode;
	public String mrcode;
	public String billed;
	public String synched;
	public String total;
	public String datetimestamp;
	
	
	public LiveStatusHelper() {
		
	}


	public LiveStatusHelper(String sitecode, String mrcode, String billed,
			String synched, String total, String datetimestamp) {
		super();
		this.sitecode = sitecode;
		this.mrcode = mrcode;
		this.billed = billed;
		this.synched = synched;
		this.total = total;
		this.datetimestamp = datetimestamp;
	}
	
	public JSONObject getJSONObject(String imeino) {
        JSONObject object = new JSONObject();
		try {
			object.put("sitecode", this.sitecode);
			object.put("mrcode", this.mrcode);
			object.put("billed", this.billed);
			object.put("synched", this.synched);
			object.put("total", this.total);
			object.put("datetimestamp", this.datetimestamp);
			object.put("deviceid", imeino);
		} catch (JSONException e) {
        }
        return object;
    }
		
}
