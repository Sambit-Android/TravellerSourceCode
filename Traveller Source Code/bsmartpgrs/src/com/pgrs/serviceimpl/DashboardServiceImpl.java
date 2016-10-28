package com.pgrs.serviceimpl;


import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.service.DashboardService;

import net.sf.ehcache.CacheManager;

@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	
	@Autowired
	private MasterGenericDAO genericDAO; 
	
	public static String projectName="";
	public static String companyName="";

	@Override
	public void setCookie(HttpServletRequest request,Principal principal,HttpServletResponse response) {
		Cookie newcookie = new Cookie("userCookie", principal.getName().toLowerCase());
		newcookie.setMaxAge(60*60*24*365);
		response.addCookie(newcookie);
	}

	@Override
	public String getCookie(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++){
			cookie = cookies[i];
			if(cookie.getName( ).equals("userCookie")){
				return cookie.getValue( );
			}
		}
		return null;
	}

	@Override
	public void getImageFromCookie(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++){
			cookie = cookies[i];
			if(cookie.getName( ).equals("userCookie")){
				response.setContentType("image/jpeg");
				byte bt[] = null;
				OutputStream ot = null;
				ot = response.getOutputStream();
				//bt = ldapBusinessModel.getImage(cookie.getValue( ));
				ot.write(bt);
				ot.close();
			}
		}
	}

	@Override
	public void getProfileImage(String string,HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		byte bt[] = null;
		OutputStream ot = null;
		ot = response.getOutputStream();
		bt = ldapBusinessModel.getImage(string);
		ot.write(bt);
		ot.close();

	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void setDasboradDetails(Model model,int id,int projectId,HttpServletRequest request) {

		HttpSession httpSession =  request.getSession(false);
		ServletContext servletcontext = httpSession.getServletContext();
		List<String> rolesList = (List<String>)servletcontext.getAttribute("roles");

		
		

		String officeDetails="select o.id,o.parentId,o.text ,o.type,o.project.id from ProjectHeirarchyEntity o where o.id="+id;
		Object[] office = genericDAO.executeSingleObjectQuery(officeDetails);
		int projId=0;
		if(rolesList.contains("Admin") || rolesList.contains("Super User")){
		projId=(int)office[4];
		}else{
			projId=projectId;
		}
		
		
		model.addAttribute("officeName",(String)office[2]+" "+(String)office[3]);

		String subQuery = "";
		String query = "select pl.level,pl.level from ProjectHeirarchyEntity p,ProjectTreeTemplateLevels pl WHERE p.projectTreeTemplateLevels.phlId=pl.phlId AND p.id="+id;
		Object[] levelObj = genericDAO.executeSingleObjectQuery(query);
		final SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yy");
		final SimpleDateFormat format2 = new SimpleDateFormat("MMM yy");
		if(levelObj!=null){
			
			int level = (int)levelObj[0];

			Object[] maxLevel = genericDAO.executeSingleObjectQuery("select MAX(o.level),MAX(o.level) from ProjectTreeTemplateLevels o where o.projectId="+projId);

			for (int i = 1; i < (((int)maxLevel[0]-level)+1); i++) {
				subQuery+="(select p from ProjectHeirarchyEntity p where p.parentId In";
			}
			subQuery+="("+id+")";
			for (int i = 1; i < (((int)maxLevel[0]-level)+1); i++) {
				subQuery+=")";
			}
		}else{
			subQuery+="(select o.id from ProjectHeirarchyEntity o WHERE o.project.id="+projId+")";
		}

	}

	public void aging(Model model){

	}

	

}
