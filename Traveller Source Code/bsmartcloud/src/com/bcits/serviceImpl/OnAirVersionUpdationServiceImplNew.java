package com.bcits.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.OnAirVersionUpdationEntityNew;
import com.bcits.service.OnAirVersionUpdationServiceNew;

@Repository
public class OnAirVersionUpdationServiceImplNew extends
		GenericServiceImpl<OnAirVersionUpdationEntityNew>
		implements OnAirVersionUpdationServiceNew {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OnAirVersionUpdationEntityNew> findAll(ModelMap model,HttpServletRequest request) 
	{
		model.put("remarksList",getCheckConstraints("onairversionupdationnew", "remarks_check", request));
		return entityManager.createNamedQuery("OnAirVersionUpdationEntityNew.findAll",OnAirVersionUpdationEntityNew.class).getResultList();
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OnAirVersionUpdationEntityNew> getlatestVersion(String appname) {
		return entityManager.createNamedQuery(
				"OnAirVersionUpdationEntityNew.latestVersion",
				OnAirVersionUpdationEntityNew.class).setParameter("appname", appname).getResultList();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public long checkVersionExist(String apkVersion, String remarks)
	{
		return (Long)entityManager.createNamedQuery("OnAirVersionUpdationEntityNew.checkVersionExist").setParameter("version", apkVersion).setParameter("remarks", remarks).getSingleResult();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public OnAirVersionUpdationEntityNew findByRemarks(String apkVersion,String remarks)
	{
		return (OnAirVersionUpdationEntityNew) entityManager.createNamedQuery("OnAirVersionUpdationEntityNew.findByRemarks").setParameter("remarks", remarks).getSingleResult();
	}

}
