package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.ModuleMasterEntity;
import com.bcits.service.ModuleMasterService;

@Repository
public class ModuleMasterServiceImpl extends
		GenericServiceImpl<ModuleMasterEntity> implements
		ModuleMasterService {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ModuleMasterEntity> findAllModuleMasters() {
		return entityManager.createNamedQuery("ModuleMasterEntity.findAll").getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateActivatedModuleStatus(String status, String[] modulesID) {



			
		if (status.equals("Active")) {

			updateStatusAllModules("In Active");
		}

		for (int i = 0; i < modulesID.length; i++) {
			System.out.println("id==" + modulesID[i]);
			entityManager
					.createNamedQuery("ModuleMasterEntity.updateStatus")

			.setParameter("status", status)
					.setParameter("id", "" + modulesID[i]).executeUpdate();
		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ModuleMasterEntity> findAllActivatedModuleMasters() {

		return entityManager.createNamedQuery(
				"ModuleMasterEntity.findAllActivatedModuleMasters",
				ModuleMasterEntity.class).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStatusAllModules(String status) {
		entityManager
				.createNamedQuery("ModuleMasterEntity.updateStatusAllModules")
				.setParameter("status", status).executeUpdate();

	}

	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateActiveDeactiveStatus(String moduleIds)
	{
		String[] modules=moduleIds.split(",");
		List<String> allModuleIds=new ArrayList<String>();
		for (int i = 0; i < modules.length; i++) {
			allModuleIds.add(modules[i]);
		}
		//To activate selected modules
		entityManager.createNamedQuery("ModuleMasterEntity.ActivateSelectedModules").setParameter("allModules", allModuleIds).setParameter("status", "Active").executeUpdate();
		
		//To de_activate other modules
	    entityManager.createNamedQuery("ModuleMasterEntity.DeActivateNonSelectedModules").setParameter("allModules", allModuleIds).setParameter("status", "InActive").executeUpdate();
		
	}
}
