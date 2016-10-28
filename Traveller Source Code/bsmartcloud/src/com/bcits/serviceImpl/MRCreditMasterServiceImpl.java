package com.bcits.serviceImpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.MRCreditMasterEntity;
import com.bcits.service.MRCreditMasterService;
import com.bcits.utility.BCITSLogger;
@Repository
public class MRCreditMasterServiceImpl extends
		GenericServiceImpl<MRCreditMasterEntity> implements
		MRCreditMasterService {


	@Override
	public List<MRCreditMasterEntity> getAllCreditMasters() {

		return entityManager.createNamedQuery("MRCreditMasterEntity.findAll",
				MRCreditMasterEntity.class)
				.getResultList();
	}
	
	
	public List<MRCreditMasterEntity> findAllByMrcode(String mrCode,String sdoCode) 
	{
		return entityManager.createNamedQuery("MRCreditMasterEntity.findAllByMrcode").setParameter("mrCode", mrCode).setParameter("sdoCode", sdoCode).getResultList();
	}
	
	@Override
	 @Transactional(propagation=Propagation.REQUIRED)
	 public int updateAvailableCreditMobile(String mrCode,String sdoCode,String amount) 
	 {
	  Query query=entityManager.createNamedQuery("MRCreditMasterEntity.updateAvailableCreditMobile").setParameter("mrCode", mrCode).setParameter("amount",Double.parseDouble(amount)).setParameter("sdoCode", sdoCode);

	  int updateData=query.executeUpdate();
	  BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
	  return updateData;
	 }
	@Override
	 @Transactional(propagation=Propagation.REQUIRED)
	 public int updateStatusMobSynchMobile(String mrCode,String sdoCode) 
	 {
	  Query query=entityManager.createNamedQuery("MRCreditMasterEntity.updateStatusMobSynchMobile").setParameter("mrCode", mrCode).setParameter("sdoCode", sdoCode);

	  int updateData=query.executeUpdate();
	  BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
	  return updateData;
	 }
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateAvailCredit(Double availCredit, String mrCode,
			String sdoCode, String localMobSynched) {

		return entityManager
				.createNamedQuery("MRCreditMasterEntity.updateAvailCredt")
				.setParameter("availCredit", availCredit)
				.setParameter("sdoCode", sdoCode)
				.setParameter("localMobSynched", localMobSynched)
				.setParameter("mrCode", mrCode).executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public MRCreditMasterEntity checkMRCMasterAvail(String sdoCode,
			String mrCode) {

		try {
			return (MRCreditMasterEntity) entityManager
					.createNamedQuery("MRCreditMasterEntity.findMRCreditMaster")
					.setParameter("mrCode", mrCode)
					.setParameter("sdoCode", sdoCode).getSingleResult();

		} catch (NoResultException nre) {
			BCITSLogger.logger.info("NO Results founded:::::::::::::::::::::");
			return null;

		}
	}

	@Override
	public List<MRCreditMasterEntity> getAllMRCreditMastersByTwoDates(
			String fromDate, String toDate, String mrCode, String sdoCode) {

		return entityManager
				.createNamedQuery(
						"MRCreditMasterEntity.findMRCreditMasterByTwoDates",
						MRCreditMasterEntity.class)
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate)
				.setParameter("sdoCode", sdoCode)
				.setParameter("mrCode", mrCode).getResultList();
	}
}
