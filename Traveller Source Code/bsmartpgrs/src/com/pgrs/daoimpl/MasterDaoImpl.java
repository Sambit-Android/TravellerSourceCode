package com.pgrs.daoimpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.dao.MasterDao;
import com.pgrs.entity.ProjectHeirarchyEntity;

@Repository
public class MasterDaoImpl implements MasterDao{
	
	@Autowired
	private IProjectHeirarchyDAO projectHeirarchyDAO;
	
	@Override
	public String getAllOfficeDetailsOfLoggedInUser(HttpServletRequest request) {
		HttpSession session =request.getSession(false);
		int id = (int)session.getAttribute("officeId"); 
		int projectId = (int)session.getAttribute("projectId");  
		return getOfficeChildDetails(id, projectId);
	}
	
	@Override
	public String getOfficeChildDetails(int id,int projectId){
		
		/*String subQuery = "";
		String query = "select pl.level,pl.level from ProjectHeirarchyEntity p,ProjectTreeTemplateLevels pl WHERE p.projectTreeTemplateLevels.phlId=pl.phlId AND p.id="+id;
		Object[] levelObj = genericDAO.executeSingleObjectQuery(query);
		int level = 0;
		if(levelObj!=null){
			level = (int)levelObj[0];
			Object[] maxLevel = genericDAO.executeSingleObjectQuery("select MAX(o.level),MAX(o.level) from ProjectTreeTemplateLevels o where o.projectId="+projectId);
			for (int i = 1; i < (((int)maxLevel[0]-level)+1); i++) {
				subQuery+="(select p from ProjectHeirarchyEntity p where p.parentId In";
			}
			subQuery+="("+id+")";
			for (int i = 1; i < (((int)maxLevel[0]-level)+1); i++) {
				subQuery+=")";
			}
		}else{
			subQuery+= "(select o.id from ProjectHeirarchyEntity o where o.project.id="+projectId+")";
		}*/
			String subQuery = getOfficeIds(id,projectId);
		return subQuery;
	}

String getOfficeIds(int officeId,int projId){
		
		List<?> bankNames = projectHeirarchyDAO.getAllOffice();
		Map<Integer,ProjectHeirarchyEntity> officeMap=new HashMap<Integer,ProjectHeirarchyEntity>();
		for (Iterator<?> iterator = bankNames.iterator(); iterator.hasNext();)
		{
			final Object[] values = (Object[]) iterator.next();
			if(values[3]!=null && projId==(int)values[3]){
			ProjectHeirarchyEntity projectHeirarchyEntity=new ProjectHeirarchyEntity();
			projectHeirarchyEntity.setId((int)values[0]);
			projectHeirarchyEntity.setText((String)values[1]);
			if(!((String)values[1]).equalsIgnoreCase("root")){
				projectHeirarchyEntity.setParentId((int)values[2]);
			}
			officeMap.put((Integer)values[0],projectHeirarchyEntity);
			}
		}
		String officeIds="";
		Set<Integer> office=new HashSet<>();
		office.add(officeId);
		
		getAllOfficeUnderThisId(officeMap,officeId,office);
		Iterator<Integer> iterator=office.iterator();
		while(iterator.hasNext()){
			officeIds+=iterator.next()+",";
		}
		return "("+officeIds.substring(0,officeIds.length()-1)+")";
	}
	private void getAllOfficeUnderThisId(
			Map<Integer,ProjectHeirarchyEntity> officeMap, int officeId,Set<Integer> office) {
		Set<Integer> keys = officeMap.keySet();
		for(Integer key: keys){
			if(key !=0 && key!=1 && key>=officeId){
				ProjectHeirarchyEntity projectHeirarchyEntity=officeMap.get(key);
				if(projectHeirarchyEntity.getParentId()==officeId){
					office.add(projectHeirarchyEntity.getId());
					getAllOfficeUnderThisId(officeMap,projectHeirarchyEntity.getId(),office);

				}
			}
		}
	}





	@Override
	public String getAllOfficeDetailsOfLoggedInUsermobile(
			HttpServletRequest request, int id, int projectId) {
		return getOfficeChildDetails(id, projectId);
	}
}