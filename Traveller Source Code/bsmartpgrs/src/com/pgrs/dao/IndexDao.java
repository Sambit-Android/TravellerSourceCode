package com.pgrs.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface IndexDao {

	void setIndexDetails(Model model, HttpServletRequest request);

	Map<String, Object> setIndexDetailsmobile(String username,Model model,HttpServletRequest request, int officeid, int projectid);

}
