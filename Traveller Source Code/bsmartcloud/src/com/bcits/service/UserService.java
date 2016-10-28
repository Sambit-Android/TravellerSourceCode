package com.bcits.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

import com.bcits.entity.AppActivityEntity;
import com.bcits.entity.User;

public interface UserService extends GenericService<User>
{

	public abstract List<User> findAll(String userMailId, String userPassword);
	public String afterLogin(String userMailId,String userPassword,HttpSession session,ModelMap model);
	public String unLockScreen(String password,HttpServletRequest request,ModelMap model);
	
	//WEB-SERVICE
	public abstract List<User> findUser(String userName, String passWord);
	
	List<User> findAll();
	public void getUserData(ModelMap model,HttpServletRequest request);
	
	byte[] getImage(HttpServletRequest request,HttpServletResponse response,int id);
	
	long checkEmailExist(String emailId);
	
	long checkEmailExistWhileEdit(String emailId,int id);
	
	void updateuserDetails(HttpServletRequest request,User user,Date current_date,ModelMap model);
	
	byte[] displayProfilePic(HttpServletRequest request,HttpServletResponse response);
	
	byte[] defaultImage(HttpServletRequest request);
	
	String killIdleConnection();
}