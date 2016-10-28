package com.pgrs.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.TicketEscalationDAO;
import com.pgrs.entity.TicketEscalationEntity;
import com.pgrs.service.TicketEscalationService;

@Service
public class TicketEscalationServiceImpl implements TicketEscalationService {

	@Autowired
	private TicketEscalationDAO ticketEscalationDAO; 
	
	@Override
	public List<?> findAllEscalationLevels(int subCategoryId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> escalationMap = null;
		for (Iterator<?> iterator = ticketEscalationDAO.findAllEscalationLevels(subCategoryId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				escalationMap = new HashMap<String, Object>();
				
				escalationMap.put("escId", (Integer)values[0]);
				escalationMap.put("subCategoryId", (Integer)values[1]);
				escalationMap.put("level", (Integer)values[2]);
				escalationMap.put("dnId", (Integer)values[3]);
				escalationMap.put("dnName", (String)values[4]);
				escalationMap.put("notificationType", (String)values[5]);
				escalationMap.put("assignmentType", (String)values[6]);
				escalationMap.put("urbanTimeLines", (Integer)values[7]);
				escalationMap.put("ruralTimeLines", (Integer)values[8]);
			
			result.add(escalationMap);
	     }
		 return result;
	}

	@Override
	public void save(TicketEscalationEntity escalationEntity) {
		ticketEscalationDAO.save(escalationEntity);
	}

	@Override
	public void update(TicketEscalationEntity escalationEntity) {
		ticketEscalationDAO.update(escalationEntity);
	}

	@Override
	public void delete(int escId) {
		ticketEscalationDAO.delete(escId);
	}


}
