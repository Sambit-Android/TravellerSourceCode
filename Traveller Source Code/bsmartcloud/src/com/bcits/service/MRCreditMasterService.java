package com.bcits.service;

import java.util.List;

import com.bcits.entity.MRCreditMasterEntity;

public interface MRCreditMasterService extends
		GenericService<MRCreditMasterEntity> {

	public List<MRCreditMasterEntity> getAllCreditMasters();
	public List<MRCreditMasterEntity> findAllByMrcode(String mrCode,String sdoCode);
	public int updateAvailableCreditMobile(String mrCode,String sdoCode,String amount) ;

	public MRCreditMasterEntity checkMRCMasterAvail(String sdoCode,
			String mrCode);

	public Integer updateAvailCredit(Double availCredit, String mrCode,
			String sdoCode, String localMobSynched);
	public int updateStatusMobSynchMobile(String mrCode,String sdoCode);


	public List<MRCreditMasterEntity> getAllMRCreditMastersByTwoDates(
			String fromDate, String toDate, String mrCode, String sdoCode);
}
