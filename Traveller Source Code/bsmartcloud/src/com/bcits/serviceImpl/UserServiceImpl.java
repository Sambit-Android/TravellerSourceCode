package com.bcits.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bcits.entity.SiteLocationEntity;
import com.bcits.entity.User;
import com.bcits.service.SiteLocationService;
import com.bcits.service.UserService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Encryption;

@Repository
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService
{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Encryption encryption;
	
	@Autowired
	private SiteLocationService siteLocationService;
	/* (non-Javadoc)
	 * @see com.bcits.serviceImpl.UserService#findAll(java.lang.String, java.lang.String)
	 */
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public String afterLogin(String userMailId,String userPassword,HttpSession session,ModelMap model)
	{
	    String pageName = "";
	    BCITSLogger.logger.info("userMailId------------afterLogin-----> : "+userPassword+"========"+userMailId);
		List<User> list = findAll(userMailId, userPassword);
		if(list.size() > 0)
		{
			session.setAttribute("userType", list.get(0).getUserType());
			session.setAttribute("username",list.get(0).getUsername());
			session.setAttribute("userMailId",list.get(0).getUserMailId());
			
			String usertypeVal=list.get(0).getUserType();
			BCITSLogger.logger.info("usertypeVal---------------------->"+usertypeVal);
			if(usertypeVal.equalsIgnoreCase("ADMIN_SUBDIVISION"))
			{
				String[] mailid=userMailId.split("@");
				String siteCode="";
				List<SiteLocationEntity> siteEntity=siteLocationService.getLocationByMail(mailid[0]);
				for (SiteLocationEntity siteLocationEntity : siteEntity) 
				{
					//siteCode=siteLocationEntity.getNewSitecode();
					siteCode=siteLocationEntity.getSiteCode()+"";
				}
				BCITSLogger.logger.info("siteCode----------------siteCode------>"+siteCode);
				//String[] sdocode=list.get(0).getUserMailId().split("_");
				//BCITSLogger.logger.info("sdocode---------------------->"+sdocode[1]);
				//String[] sdocodeVal=sdocode[1].split("@");
				//String dbUser=siteLocationService.getDbuser(sdocodeVal[0]);
				String dbUser=siteLocationService.getDbuser(siteCode);
				//BCITSLogger.logger.info("dbUser----------------------dbUser>"+dbUser);
				//List<SiteLocationEntity> sitecodeList=siteLocationService.findBySdocode(sdocodeVal[0]);
				List<SiteLocationEntity> sitecodeList=siteLocationService.findBySdocode(siteCode);
				//String newSdocode="";
				for (SiteLocationEntity siteLocationEntity : sitecodeList)
				{
					session.setAttribute("sectionVal",siteLocationEntity.getSection());
					//newSdocode=siteLocationEntity.getNewSitecode();
				}
	             if(!dbUser.equals("noVal"))
	             {
	            	 session.setAttribute("sdocodeval",siteCode);
	             }
	             else
	             {
	            	 session.setAttribute("sdocodeval","noValue");
	             }
			}
			else
			{
				session.removeAttribute("sdocodeval");
			}
			session.setAttribute("userMobileNo",list.get(0).getUserMobileNo());
			session.setAttribute("userStatus",list.get(0).getUserStatus());
			session.setAttribute("userLevel",list.get(0).getUserLevel());
			session.setAttribute("userId",list.get(0).getId());
			session.setAttribute("image",list.get(0).getImage());
			session.setAttribute("currentMonth", new SimpleDateFormat("yyyyMM").format(new Date()));
			BCITSLogger.logger.info("list.get(0).getUserType()"+list.get(0).getUserType());
		   // System.out.println("DATA>>>>>>>>>>>"+list.get(0).getUserType());
			Connection c=null;
			BCITSLogger.logger.info("In User DashBoard");
			try {
				 c=dataSource.getConnection();
				 BCITSLogger.logger.info("dataSource.getConnection()"+c.getMetaData().getURL().contains("gescom"));
				 if(c.getMetaData().getURL().contains("gescom")==true)
				 {
					 session.setAttribute("projectName","gescom");
					 pageName="redirect:userDashboard";
				 }
				 else
				 {
					 session.setAttribute("projectName","cescom");
					 pageName="redirect:cescUserDashBoard";
				 }
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			/*if((list.get(0).getUserType()).equalsIgnoreCase("ADMIN"))
			{
				pageName= "redirect:adminDashboard";
			}
			else
			{
				pageName="redirect:userDashboard";
			}*/
			
		}
		else
		{
			pageName="login";
			model.put("msg", "notDisplay");
			model.put("msg", "Please enter valid Username and Password");
		}
		return pageName; 
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<User> findAll(String userMailId, String userPassword)
	{
		BCITSLogger.logger.info("userPassword------userPassword---userPasswogjhghjrd--drfse5r45355555555555------> : "+userPassword+"--------"+userMailId);
		List<User> list=null;
		try
		{
			list=entityManager.createNamedQuery("User.findAll").setParameter("userMailId", userMailId).setParameter("userPassword", userPassword).getResultList();
		}
		catch(Exception e)
		{
			list=entityManager.createNamedQuery("User.findAllSdo").setParameter("userMailId", userMailId).setParameter("userPassword", userPassword).getResultList();
		}
		return list;
		
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String unLockScreen(String password,HttpServletRequest request,ModelMap model)
	{
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");	 
		if(!username.equalsIgnoreCase("") || username!=null)
		{
			List<User> lockList =  entityManager.createNamedQuery("User.screenUnlock").setParameter("username",username).setParameter("password", encryption.encrypt(password)).getResultList();
			if(lockList.size()>0)
			{
				BCITSLogger.logger.info("userrrrr "+lockList);
				String recentPath=(String) session.getAttribute("path");
				System.out.println("RECENT PATH"+recentPath);
				return "redirect:/userDashboard";
			}	
			else
			{
				model.put("error", "Please enter correct password");
			}
		}
		
		return "lockScreen";
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<User> findAll() 
	{
		return entityManager.createNamedQuery("User.findAllUser").getResultList();	
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public long checkEmailExist(String emailId) 
	{
		return (Long)entityManager.createNamedQuery("User.checkEmailExist").setParameter("emailId", emailId).getSingleResult();	
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public long checkEmailExistWhileEdit(String emailId,int id) 
	{
		return (Long)entityManager.createNamedQuery("User.checkEmailExistWhileEdit").setParameter("emailId", emailId).setParameter("id", id).getSingleResult();	
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getUserData(ModelMap model,HttpServletRequest request) 
	{
		try
		{
			 List<User> usersList=findAll();		  
			  model.addAttribute("usersList",usersList);
			  
			 List<String> userLevelCheck =getCheckConstraints("users", "userlevel_check",request);
			 
			  model.addAttribute("userLevelCheck", userLevelCheck);
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<User> findUser(String userName, String passWord) 
	{
		return entityManager.createNamedQuery("User.findUser").setParameter("userName", userName).setParameter("passWord", passWord).getResultList();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public byte[] getImage(HttpServletRequest request,HttpServletResponse response,int id) 
	{
		 User data=find(id);
		 response.setContentType("image/jpeg/png");
		if(data.getImage()!=null)
		 {
			 byte photo[] = data.getImage();    	
	    	 OutputStream ot = null;
	    	 try {
				ot = response.getOutputStream();
				ot.write(photo);
				ot.close(); 
			} catch (IOException e) {
				
				e.printStackTrace();
			}   	
	         Base64 b=new Base64();
	         b.encodeBase64(photo);
			 return b.encodeBase64(photo);
		 }
   	 else {
			return null;
		}
	}
	
	@Override	
	public byte[] displayProfilePic(HttpServletRequest request,HttpServletResponse response) 
	{		 
		     response.setContentType("image/jpeg/png");
			 byte photo[] = (byte[]) request.getSession().getAttribute("image");   	
	    	 OutputStream ot = null;
	    	 try {
				ot = response.getOutputStream();
				ot.write(photo);
				ot.close(); 
			} catch (IOException e) {
				
				e.printStackTrace();
			}   	
	         Base64 b=new Base64();
	         b.encodeBase64(photo);
			 return b.encodeBase64(photo);		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateuserDetails(HttpServletRequest request,User user,Date current_date,ModelMap model) 
	{
		//Added by manjunath
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("image1");
		 try {
			 if(multipartFile.getSize()>0)
			 {
				 byte[] image = IOUtils.toByteArray(multipartFile.getInputStream());				
				 user.setImage(image);	
			}			
		
		 //end by manjunath
    	
		if(user.getId()==0)
		{	
			user.setUserCreatedDate(current_date);
			user.setUserPassword(encryption.encrypt(user.getUserPassword()));
			long res=checkEmailExist(user.getUserMailId());
			if(res==0)
			{
				//to set defalut Image
				if(multipartFile.getSize()==0)
				{
					
				/*String realPath=request.getServletContext().getRealPath("/");				 
			    File file = new File(realPath+"resources\\assets\\img\\imagesDefault.jpg");
		        byte[] bFile = new byte[(int) file.length()];
		        try {
			     FileInputStream fileInputStream = new FileInputStream(file);
			     //convert file into array of bytes
			     fileInputStream.read(bFile);
			     fileInputStream.close();
		        } catch (Exception e) {
			     e.printStackTrace();
		        }*/	
				byte[] bFile = 	defaultImage(request);
		        user.setImage(bFile);			
				}
				//End default image
				
				save(user);
				model.put("results", "User Details Added Successfully");
			}
			else {
				model.put("results", "EmailId already exist please enter other emailId");
			}
		}
		else
		{
			user.setUserCreatedDate(getDate2(request.getParameter("userCreatedDate")));
			if(multipartFile.getSize()==0)// to get old image if doesnot update new image
			{
				User userDtls=find(user.getId());
				user.setImage(userDtls.getImage());
			}				
			user.setUserPassword(encryption.encrypt(user.getUserPassword()));
			long res1=checkEmailExistWhileEdit(user.getUserMailId(), user.getId());						
			if(res1==0)
			{
				update(user);
				model.put("results", "User Details Updated Successfully");
			}
			else {
				model.put("results", "EmailId already exist please enter other emailId");
			}
			
		}
		 } 
		 catch (IOException e)
		{				
			e.printStackTrace();
		}
	}
	
	public byte[] defaultImage(HttpServletRequest request)
	{
		String realPath=request.getServletContext().getRealPath("/");	
		System.out.println(realPath);
	    File file = new File(realPath+"resources\\assets\\img\\imagesDefault.jpg");
        byte[] bFile = new byte[(int) file.length()];
        try {
	     FileInputStream fileInputStream = new FileInputStream(file);
	     //convert file into array of bytes
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        return bFile;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String killIdleConnection()
	{
		String error="";
		String sql="";
		Connection c=null;
		try 
		{
			c=dataSource.getConnection();
			 BCITSLogger.logger.info("dataSource.getConnection()"+c.getMetaData().getURL().contains("gescom"));
			 if(c.getMetaData().getURL().contains("gescom")==true)
			 {
				 sql="SELECT pg_terminate_backend(pg_stat_activity.pid)FROM pg_stat_activity WHERE pg_stat_activity.datname = 'gescom' AND pid <> pg_backend_pid();";
			 }
			 else
			 {
				 sql="SELECT pg_terminate_backend(pg_stat_activity.pid)FROM pg_stat_activity WHERE pg_stat_activity.datname = 'cescnew' AND pid <> pg_backend_pid();";
			 }
			entityManager.createNativeQuery(sql).getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			error="error";
		}
		return error;
	}
	
	
}
