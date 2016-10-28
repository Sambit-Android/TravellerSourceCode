package com.bcits.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.User;
import com.bcits.entity.UserDetails;
import com.bcits.service.UserDetailsService;

@Repository
public class UserDetailsServiceImpl  extends GenericServiceImpl<UserDetails> implements UserDetailsService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<UserDetails> findAll(String userName, String password) 
	{
		return entityManager.createNamedQuery("UserDetails.findAll").setParameter("userName", userName).setParameter("password", password).getResultList();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String afterLogin(String userName,String password,HttpSession session)
	{
		ModelMap model=new ModelMap();
	    String pageName = "adminPage";
		List<UserDetails> list = findAll(userName, password);
		if(list.size() > 0)
		{
			String email=null;
			for (UserDetails userDetails : list) 
			{
				 email=userDetails.getEmailId();
			}
			pageName= "adminPage";
			
			/*session.setAttribute("userType", list.get(0).getUserType());
			session.setAttribute("username",list.get(0).getUsername());
			session.setAttribute("userMailId",list.get(0).getUserMailId());
			session.setAttribute("userMobileNo",list.get(0).getUserMobileNo());
			session.setAttribute("userStatus",list.get(0).getUserStatus());
		
			session.setAttribute("userLevel",list.get(0).getUserLevel());
		
			if((list.get(0).getUserType()).equalsIgnoreCase("ADMIN"))
			{
				pageName= "adminPage";
			}*/
			
		}
		return pageName; 
	}
}
