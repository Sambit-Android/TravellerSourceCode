package com.bcits.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.GroupEntity;
import com.bcits.service.GroupService;

@Repository
public class GroupServiceImpl extends GenericServiceImpl<GroupEntity> implements GroupService 
{
	@Transactional(propagation=Propagation.SUPPORTS)
    public List<GroupEntity> findAll() 
    {
		return entityManager.createNamedQuery("GroupEntity.Findll").getResultList();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
    public void updateGroupData(HttpServletRequest request ,ModelMap model,GroupEntity grp) 
    {
		try {
			grp.setGrpName(grp.getGrpName().replaceAll(",", " "));
			long res=(Long)entityManager.createNamedQuery("GroupEntity.CheckGrpExist").setParameter("id", grp.getId()).setParameter("name", grp.getGrpName()).getSingleResult();
		if(res==0)
		{
			
			if(grp.getId()==0)
		 {
				save(grp);
				 model.put("results", "Group Name Added successfully");
		 }
		 else
		 {
			GroupEntity oldGrp=find(grp.getId());		
			//to update in mrgroup
			int result=entityManager.createNamedQuery("MrGroup.updateGroups").setParameter("grpName", oldGrp.getGrpName()).setParameter("newGrpName", grp.getGrpName()).executeUpdate();
			
				update(grp);				
				model.put("results", "Group Name updated successfully");
			
		 }
		}
		else {
			model.put("results", "Group Name already exist");
		}
		} catch (Exception e) {
			model.put("results", "Error while Grouping");
			e.printStackTrace();
		}
		model.put("allGroup",findAll());
		model.put("group",new GroupEntity());
		
	}
	
	
}
