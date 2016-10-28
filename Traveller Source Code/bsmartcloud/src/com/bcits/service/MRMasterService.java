/**
 * 
 */
package com.bcits.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.bcits.entity.MRMasterEntity;

/**
 * @author user11
 *
 */
public interface MRMasterService extends GenericService<MRMasterEntity> {

	/**
	 * Fetching all MRMasters details in the form of List.
	 * 
	 * @return List object which contains all MRMasterEntity Objects.
	 */
	public List<MRMasterEntity> findAllMRMasters();

	public MRMasterEntity findMRMaster(String mrCode);

	public Integer updateMRMaster(MRMasterEntity mrMasterEntity);

	public List<String> getMatchedMRCodes();

	public Integer deleteMRMaster(String mrCode);
	
	public String validateForMrMaster(String mrCode,String password);

	public List<MRMasterEntity> findMobileUser(String userName,String password);

	public List<String> getMatchedMRCodesForAllocation();
	
	byte[] getPhoto(ModelMap model,HttpServletRequest request,HttpServletResponse response,String mrCode) throws IOException;

	void updateMRMasterData(MRMasterEntity mrMasters,HttpServletRequest request,Date current_date,ModelMap model,String groups,String operation);

	List<MRMasterEntity> getNotAllocatedOperators();
}
