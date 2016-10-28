package com.pgrs.daoimpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.pgrs.dao.IndexDao;

@Repository
public class IndexDaoImpl implements IndexDao{
	
	@Override
	public void setIndexDetails(Model model,HttpServletRequest request) {
		
		HttpSession session =request.getSession(false);
		model.addAttribute("urName", session.getAttribute("urName"));
				
	}

	@Override
	public Map<String, Object> setIndexDetailsmobile(String username,Model model, HttpServletRequest request, int officeid, int projectid) {
		Map<String, Object> indexdetails = new HashMap<>();
		indexdetails.put("urName",username);
		
		return indexdetails;
	}


}
