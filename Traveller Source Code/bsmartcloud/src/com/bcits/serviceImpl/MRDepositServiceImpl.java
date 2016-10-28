package com.bcits.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcits.entity.MRDepositEntity;
import com.bcits.service.MRDepositService;

@Repository
public class MRDepositServiceImpl extends GenericServiceImpl<MRDepositEntity>
		implements
		MRDepositService {

	@Override
	public List<MRDepositEntity> getAllMRDeposits() {

		return entityManager.createNamedQuery("MRDepositEntity.findAll",
				MRDepositEntity.class).getResultList();
	}

	@Override
	public List<MRDepositEntity> getAllMRDepositsByDate(String reqDate) {

		return entityManager
				.createNamedQuery("MRDepositEntity.getAllDepositsByDate",
						MRDepositEntity.class).setParameter("reqDate", reqDate)
				.getResultList();
	}

	@Override
	public List<Object> getAllMRDepositesByTwoDates(String fromDate,
			String toDate, String sdoCode, String mrCode) {
		// String query
		// ="select MRDepositEntity mrde,MRCreditMasterEntity mrcme,Payments p where pg_catalog.date(mrde.timestamp) between to_date(mrde.timestamp,'dd-MM-yyyy')='"+fromDate+"'and to_date(mrde.timestamp,'dd-MM-yyyy')='"+toDate+"'													mrde.sdoCode=mrcme.sdoCode and mrde.sdoCode=p.sdoCode and mrde.mrCode=mrcme.mrCode and mrde.mrCode=p.mrcode ";
		return entityManager
				.createNamedQuery("MRDepositEntity.findAllDepositsByTwoDates")
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate)
				.setParameter("sdoCode", sdoCode)
				.setParameter("mrCode", mrCode).getResultList();
	}

	@Override
	public List<Object[]> getMRDepositsAndPayments(String fromDate,
			String toDate,
			String sdoCode, String mrCode) {

		List<Object[]> list = entityManager
				.createNamedQuery("MRDepositEntity.findMRDepositsAndPayments")
				.setParameter("sdoCode", sdoCode)
				.setParameter("mrCode", mrCode)
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();

		System.out.println("Lise Date ==-=====" + list);
		Iterator<Object[]> it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = it.next();
			/*
			 * for (Object obj1 : obj) {
			 * System.out.println("obj1==================" + obj1); }
			 */

			for (int i = 0; i < obj.length; i++) {
				System.out.println("obj[" + i + "]============" + obj[i]
						+ "obj[" + i + "] class ==="
 + obj[i].getClass());
			}


		}

		return list;
	}

}
