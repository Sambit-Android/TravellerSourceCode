package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bcits.entity.UserDetails;

public interface UserDetailsService extends GenericService<UserDetails>
{
	public abstract List<UserDetails> findAll(String emailId, String password);
	public String afterLogin(String userName,String password,HttpSession session);
}
