package com.pgrs.service;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface DashboardService {

	void setCookie(HttpServletRequest request, Principal principal,HttpServletResponse response);

	String getCookie(HttpServletRequest request, HttpServletResponse response) throws IOException;

	void getImageFromCookie(HttpServletRequest request,HttpServletResponse response)throws Exception;

	void getProfileImage(String string,HttpServletResponse response) throws Exception;

	void setDasboradDetails(Model model, int id, int projectId,HttpServletRequest request);

}
