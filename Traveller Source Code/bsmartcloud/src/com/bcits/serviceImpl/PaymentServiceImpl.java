package com.bcits.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.Payments;
import com.bcits.entity.Resultspaymets;
import com.bcits.service.PaymentService;
import com.bcits.utility.BCITSLogger;

@Repository
public class PaymentServiceImpl extends GenericServiceImpl<Payments> implements PaymentService {

	@Override
	public List<Payments> findByRRNO(String rrno) {
		return entityManager.createNamedQuery("Find.Payments.ByRRNO").setParameter("rrno", rrno).getResultList();
	}

	@Override
	//@Transactional(propagation=Propagation.REQUIRED)
	public Resultspaymets saveJSONObject(Payments payments)
	{
		Resultspaymets resultspaymets = new Resultspaymets();
		try
		{
			entityManager.persist(payments);
			resultspaymets.setRrno(payments.getRrno());
			resultspaymets.setStatus("UPDATED");
			BCITSLogger.logger.info("DATA INSERTED--");
			
		}
		catch(Exception exception)
		{
			System.out.println("Error while saving JSON object to DB");
		}
		return  resultspaymets;
		
	}

	@Override
	public List<Payments> findByDate(String fromDate, String toDate,
			String sdoCode, String mrCode) {

		List<Payments> res = null;
		if (sdoCode == null || sdoCode == "") {

			res = entityManager
					.createNamedQuery("Find.Payments.Bydate", Payments.class)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate).getResultList();
		} else if (sdoCode != null || sdoCode != "") {
			if (mrCode == null || mrCode == ""
					|| mrCode.equals("Select MR Code".trim())) {

				res = entityManager
						.createNamedQuery("Find.Payments.BydateSdoCode",
								Payments.class)
						.setParameter("fromDate", fromDate)
						.setParameter("toDate", toDate)
						.setParameter("sdoCode", sdoCode).getResultList();
			} else if (mrCode != "" && !mrCode.equals("Select MR Code".trim())) {
				res = entityManager
						.createNamedQuery("Find.Payments.BydateSdoCodeMrCode",
								Payments.class)
						.setParameter("fromDate", fromDate)
						.setParameter("toDate", toDate)
						.setParameter("sdoCode", sdoCode)
						.setParameter("mrCode", mrCode).getResultList();
			}

		}

		return res;
		
	}

	@Override
	public List<Payments> findAll() {
		return entityManager.createNamedQuery("Payment.findAll").getResultList();
	}

	@Override
	public List<Object> findByTwoDates(String fromDate, String toDate,
			String sdoCode, String mrCode) {

		return entityManager
.createNamedQuery("Payments.getByTwoDates")
				.setParameter("mrCode", mrCode)
				.setParameter("sdoCode", sdoCode)
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).getResultList();
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object> showCollectionOnMap(String divSdo, String date, ModelMap model)
	{
		
		List list=null;
		try 
		{
			Date d=new SimpleDateFormat("dd-MM-yyyy").parse(date);
			//String cuurentMonth=new SimpleDateFormat("yyyyMM").format(d);
			String cuurentDate=new SimpleDateFormat("yyyy-MM-dd").format(d);
			//BCITSLogger.logger.info("================."+cuurentMonth+" "+cuurentDate);
			String sql="SELECT AA.*,(SELECT section FROM vcloudengine.location WHERE cast(sitecode as VARCHAR) LIKE lococde)FROM"+" "
+"(SELECT pay.mrcode, pay.rrno,pay.amount, pay.recno, pay.paymode ,cast(pay.rdate as varchar),pay.branch,pay.sdocode as lococde, hhc.consumer_name, hhd.lattitude, hhd.longitude FROM mvs.payments pay, photobilling.hhbm_consumers hhc , photobilling.hhbm_download hhd WHERE    hhc.consumer_sc_no=hhd.consumer_sc_no AND hhc.sdo_code=hhd.sdo_code AND hhc.bill_month=hhd.bill_month and    pay.sdocode=hhc.sdo_code  AND pay.rrno=hhc.consumer_sc_no  and pay.sdocode like '"+divSdo+"' and substr(cast(pay.rdate as varchar),0,11) LIKE '"+cuurentDate+"' )AA";
			BCITSLogger.logger.info("=-=================================sql>"+sql);
			list=entityManager.createNativeQuery(sql).getResultList();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	

}
