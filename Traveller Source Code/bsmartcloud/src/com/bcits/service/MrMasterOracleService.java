package com.bcits.service;

import java.util.List;
import java.util.Map;

import com.bcits.entity.MrMasterOracleEntity;

public interface MrMasterOracleService extends GenericServiceOracle<MrMasterOracleEntity>
{
	 List<Object[]> getMrmaster(String sdocode);

	List<Object[]> getOracleMrmaster(String sdocode);

	Map<String, Object> showMrOracleDetails(String mrcode, String sdocode);

	List<String[]> showMrMobileDetails(String sitecode, String mrCodeVal);

}
