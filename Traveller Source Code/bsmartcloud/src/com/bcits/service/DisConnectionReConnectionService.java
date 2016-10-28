package com.bcits.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.bcits.entity.DisconnectionMasterEntity;
import com.bcits.entity.ResultDisconnection;

public interface DisConnectionReConnectionService extends GenericServiceOracle<DisconnectionMasterEntity> 
{
	
	public ResultDisconnection updateReConnectionListMobile(int serverid,String remarks,Timestamp redate,String longitude,String latitude,byte[] image);
	public List<Map<String, Object>> getAllDisConnectionListData(int listno,String schema);
	public ResultDisconnection updateDisConnectionListMobile(String schema,String type, int serverid, String remarks, String date, int disfr,int statusdis, byte[] imageByte);
	public List<Map<String, Object>> getPaymentStatus(int listno,	String schema, String rrNo);	
}
