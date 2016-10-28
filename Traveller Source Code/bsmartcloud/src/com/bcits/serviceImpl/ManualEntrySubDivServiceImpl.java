package com.bcits.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.bcits.entity.ManualSubDivDetails;
import com.bcits.service.ManualEntrySubDivService;

@Repository
public class ManualEntrySubDivServiceImpl extends GenericServiceImpl<ManualSubDivDetails> implements ManualEntrySubDivService

{

	@Override
	public List<ManualSubDivDetails> getAllDetails() 
	{
	
		List<ManualSubDivDetails> manualSubDivDetails=null;
		try
		{
			manualSubDivDetails=entityManager.createNamedQuery("ManualSubDivDetails.GetAll").getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return manualSubDivDetails;
	}

	@Override
	public List<ManualSubDivDetails> getSelectedDetails(ManualSubDivDetails manualSubDivDetails)
	{
		List<ManualSubDivDetails> getSubDivSelectedDetails=null;
		try
		{
			getSubDivSelectedDetails=entityManager.createNamedQuery("ManualSubDivDetails.GetSelectedDetails").setParameter("slno",manualSubDivDetails.getSlno()).getResultList();
		}
		catch(EntityNotFoundException enfe)
		{
			enfe.printStackTrace();
			return null;
		}
		return getSubDivSelectedDetails;
	}

}
