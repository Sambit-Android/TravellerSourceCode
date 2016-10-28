package com.pgrs.service;

import com.pgrs.entity.Office;

public interface OfficeMasterService {

	public void saveInvoice(Office office);

	public void updateOfficeDetails(Office office);
	
	/*String getOfficeNameBasedOnId(int officeId);*/
}
