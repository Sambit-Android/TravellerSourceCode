package com.bcits.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.bcits.entity.Payments;
import com.bcits.entity.Resultspaymets;

public interface PaymentService extends GenericService<Payments> {

	public List<Payments> findByRRNO(String rrno);

	public List<Payments> findByDate(String fromDate, String toDate,
			String sdoCode, String mrCode);
	public Resultspaymets saveJSONObject(Payments payments);
	public List<Payments> findAll();

	public List<Object> findByTwoDates(String fromDate, String toDate,
			String sdoCode, String mrCode);

	public List<Object> showCollectionOnMap(String divSdo, String date, ModelMap model);
}
