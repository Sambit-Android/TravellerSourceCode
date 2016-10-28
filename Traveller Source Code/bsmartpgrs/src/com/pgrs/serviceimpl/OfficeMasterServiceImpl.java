package com.pgrs.serviceimpl;

import org.springframework.stereotype.Service;

import com.pgrs.entity.Office;
import com.pgrs.service.OfficeMasterService;

@Service
public class OfficeMasterServiceImpl implements OfficeMasterService {
	
	@Override
	public void saveInvoice(Office office) {
//		officeDAO.save(office);
	}

	@Override
	public void updateOfficeDetails(Office office) {
	//	officeDAO.update(office);
	}

	/*@Override
	public String getOfficeNameBasedOnId(int officeId) {
		return officeDAO.executeGetOfficeNameBasedOnId(officeId);
	}*/

}
