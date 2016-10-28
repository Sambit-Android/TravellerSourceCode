package com.pgrs.daoimpl;
// default package

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.entity.ProjectHeirarchyEntity;


@Repository
public class ProjectHeirarchyDAO extends GenericDAOImpl<ProjectHeirarchyEntity> implements IProjectHeirarchyDAO {

	private static final Log logger = LogFactory.getLog(ProjectHeirarchyDAO.class);

	
	@Autowired
	private MasterGenericDAO genericDAO;

	@SuppressWarnings("rawtypes")
	Map criterias = new HashMap();

	@PersistenceContext
	protected EntityManager entityManager;


	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectHeirarchyEntity> read(Integer id,int projectId,int companyId) {

		criterias.clear();
		if(projectId>0){
			criterias.put("project.id", projectId);
			criterias.put("project.company.id", companyId);
			
		}
		if(id==null)
			id=2;
		criterias.put("parentId",id);

		List<ProjectHeirarchyEntity> list = genericDAO.findByCriteria(ProjectHeirarchyEntity.class, criterias);
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectHeirarchyEntity> read1(Integer id,int projectId,int companyId) {
		
		criterias.clear();
		if(projectId>0){
			criterias.put("project.id", projectId);
			criterias.put("project.company.id", companyId);
			
		}
		if(id==null)
			id=2;
		criterias.put("parentId",id);
		
		List<ProjectHeirarchyEntity> list = genericDAO.findByCriteria(ProjectHeirarchyEntity.class, criterias);
		CopyOnWriteArrayList<ProjectHeirarchyEntity> res= new CopyOnWriteArrayList<>(list);
		for(Iterator<?> iterator=res.iterator();iterator.hasNext();){
			ProjectHeirarchyEntity projectHeirarchyEntity=(ProjectHeirarchyEntity) iterator.next();
			System.out.println(projectHeirarchyEntity.getId());
			if(projectHeirarchyEntity.getId()==456 || projectHeirarchyEntity.getId()==457 ){
				System.out.println(projectHeirarchyEntity.getId());
				res.remove(projectHeirarchyEntity);
				list.remove(projectHeirarchyEntity);
			}
		}
		
		
		return list;
	}
	
	@Override
	public List<ProjectHeirarchyEntity> readCustom(Integer id,
			int projectId, int companyId) {

		if(id==null)
			id=2;

		//List<?> list=genericDAO.executeSimpleQuery("Select o.id,o.text from ProjectHeirarchyEntity o WHERE o.project.company.id="+companyId+" AND o.project.id="+projectId+" AND o.parentId="+id);
		List<?> list=entityManager.createNamedQuery("ProjectHeirarchyEntity.readTree").setParameter("companyId", companyId).setParameter("projectId",projectId).setParameter("id",id).getResultList();
		List<ProjectHeirarchyEntity> entity=new ArrayList<ProjectHeirarchyEntity>();
		for (Iterator<?> iterator2=list.iterator();iterator2.hasNext();) {
			final Object[] values = (Object[]) iterator2.next();
			ProjectHeirarchyEntity projectHeirarchyEntity=new ProjectHeirarchyEntity();
			projectHeirarchyEntity.setId((int)values[0]);
			projectHeirarchyEntity.setText((String)values[1]);
			Set<ProjectHeirarchyEntity> childs=new HashSet<ProjectHeirarchyEntity>();
			childs.add(projectHeirarchyEntity);
			projectHeirarchyEntity.setChilds(childs);
			entity.add(projectHeirarchyEntity);
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProjectHeirarchyEntity getDetailsOnId(int id) {
		criterias.clear();
		criterias.put("id",id);
		ProjectHeirarchyEntity projectHeirarchyEntity =  genericDAO.getByCriteria(ProjectHeirarchyEntity.class, criterias);
		return projectHeirarchyEntity;
	}

	@Override
	public void delete(int id) {
		genericDAO.deleteById(ProjectHeirarchyEntity.class, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public int getIdByparentIdAndText(Integer id, String text) {
		criterias.clear();
		criterias.put("parentId", id);
		criterias.put("text",text);
		ProjectHeirarchyEntity entity = genericDAO.getByCriteria(ProjectHeirarchyEntity.class, criterias);
		return entity.getId();
	}

	@Override
	public String getOfficeNameBasedOnId(int officeId) {

		List<String> oficeNames = entityManager.createNamedQuery("ProjectHeirarchyEntity.getOfficeNameBasedOnId",String.class)
				.setParameter("officeId", officeId)
				.getResultList();	
		Iterator<String> it=oficeNames.iterator();
		while(it.hasNext()){

			return (String) it.next();
		}
		return null;
	}

	@Override
	public Integer getParentIdOnId(int id) {
		return entityManager.createNamedQuery("ProjectHeirarchyEntity.getParentId",Integer.class).setParameter("id", id).getSingleResult();	 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getZones(int id) {
		return entityManager.createNamedQuery("ProjectHeirarchyEntity.getZone").setParameter("id",id).getResultList();	 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDivisions(int id) {
		return entityManager.createNamedQuery("ProjectHeirarchyEntity.getDivision").setParameter("id",id).getResultList();	 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getOffices(int id) {
		return entityManager.createNamedQuery("ProjectHeirarchyEntity.getOffice").setParameter("id",id).getResultList();	 
	}

	public String getAllChildsIds(int officeId){
		String officeIds=officeId+",";
		ProjectHeirarchyEntity projectHeirarchyEntity = genericDAO.getById(ProjectHeirarchyEntity.class, officeId);
		for(ProjectHeirarchyEntity entity : projectHeirarchyEntity.getChilds()){
			officeIds+=entity.getId()+",";
			for (ProjectHeirarchyEntity entity2 : entity.getChilds()) {
				officeIds+=entity2.getId()+",";
				for (ProjectHeirarchyEntity entity3 : entity2.getChilds()) {
					officeIds+=entity3.getId()+",";
					for (ProjectHeirarchyEntity entity4 : entity3.getChilds()) {
						officeIds+=entity4.getId()+",";
						for (ProjectHeirarchyEntity entity5 : entity4.getChilds()) {
							officeIds+=entity5.getId()+",";
						}
					}
				}
			}
		}
		return officeIds.substring(0, officeIds.length()-1);
	}

	@Override
	public List<?> getCircles(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		List<Object[]> list = genericDAO.executeSimpleObjectQuery("select model.id,model.text,model.parent.text from ProjectHeirarchyEntity model where model.type='Circle' and model.project.id="+(int)session.getAttribute("projectId"));
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Map<String,Object> map;
		for (Object[] entity : list) {
			map =new HashMap<String,Object>();
			map.put("id", (int)entity[0]);
			map.put("text", (String)entity[1]);
			map.put("parents", (String)entity[2]);
			result.add(map);
		}
		return result;
	}

	

String getOfficeIds(int officeId,int projId){
		
		List<?> bankNames = entityManager.createNamedQuery("InvoiceCheque.getAllOffice").getResultList();
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
			if(key !=0 && key!=1){
				ProjectHeirarchyEntity projectHeirarchyEntity=officeMap.get(key);
				if(projectHeirarchyEntity.getParentId()==officeId){
					office.add(projectHeirarchyEntity.getId());
					getAllOfficeUnderThisId(officeMap,projectHeirarchyEntity.getId(),office);

				}
			}
		}

	}

	@Override
	public List<?> getAllOffice() {
		return entityManager.createNamedQuery("ProjectHeirarchyEntity.getAllOffice").getResultList();
	}

	@Override
	public String getCountBasedOnLevelAndProjectId(int level, int projectId,String siteCode) {
		try{
			if(siteCode==null){
				logger.info("inside siteCode NULL");
				return (String)entityManager.createNamedQuery("ProjectHeirarchyEntity.getCountBasedOnLevelAndProjectId").setParameter("level",level).setParameter("projectId", projectId).setMaxResults(1).getSingleResult();
			}else{
				logger.info("inside siteCode NOT null");
				return (String)entityManager.createNamedQuery("ProjectHeirarchyEntity.getCountBasedOnLevelAndProjectIdAndSiteCode").setParameter("level",level).setParameter("projectId", projectId).setParameter("siteCode", siteCode).setMaxResults(1).getSingleResult();
			}
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getDivisionSiteCode(int level, int projectId, Integer id) {
		try{
		return (String)entityManager.createNamedQuery("ProjectHeirarchyEntity.getDivisionSiteCode").setParameter("level",level).setParameter("projectId", projectId).setParameter("parentId", id).setMaxResults(1).getSingleResult();
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public void deleteProjectHeirarchyBasedOnProjectId(int projectId) {
		entityManager.createNamedQuery("Project.deleteProjectHeirarchyBasedOnProjectId").setParameter("projectId",projectId).executeUpdate();
	}

}