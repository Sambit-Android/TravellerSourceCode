package com.bcits.service;

import java.util.List;

import com.bcits.entity.ModuleMasterEntity;

public interface ModuleMasterService extends GenericService<ModuleMasterEntity> {

	public List<ModuleMasterEntity> findAllModuleMasters();

	public void updateActivatedModuleStatus(String status, String[] modulesID);

	public List<ModuleMasterEntity> findAllActivatedModuleMasters();

	public void updateStatusAllModules(String status);
	
	void updateActiveDeactiveStatus(String moduleIds);

}
