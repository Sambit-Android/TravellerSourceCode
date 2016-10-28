package com.pgrs.service;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface IndexService {

	void setMenu(HttpServletRequest request, Principal principal)
			throws Exception;

	void setIndexDetails(Model model, HttpServletRequest request);

	Map<String, Object> getindexmobile(String username, int officeid,int projectid);

}
