package com.pgrs.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SpringSecurity {
	private static final Log logger = LogFactory.getLog(SpringSecurity.class);

		// Invoice Tracker Module Invoices use case Security Things
				 @RequestMapping(value = {	
						 
						 //Users
						 "/userManagement/users/createButton",
						 "/userManagement/users/updateButton",
						 "/userManagement/users/statusButton",
						 
						 //Roles
						 "/userManagement/roles/createButton",
						 "/userManagement/roles/updateButton",
						 "/userManagement/roles/statusButton",
						 
						 //Product Access
						 "/users/pam/assign",
						 "/users/pam/commit",
						 
						 //Project Access
						 "/users/prm/commit",

						 //Department
						 "/userManagement/department/createButton",
						 "/userManagement/department/updateButton",
						 "/userManagement/department/statusButton",
						 
						 //Designation
						 "/userManagement/designation/createButton",
						 "/userManagement/designation/statusButton",
						 
						 //Masters
						 "/master/project/create",
						 "/master/project/update",
						 "/master/project/delete",
						 "/master/project/approve",
						 
						 "/master/company/create",
						 "/master/company/update",
						 "/master/company/delete",
						 "/master/company/approve",
						 
						 "/master/office/create",
						 "/master/office/update",
						 "/master/office/delete",
						 
							})			
					public void collectionsSecurityMethod(String model, HttpServletResponse res)throws IOException {
						logger.info("Sending Response to JSP as "+model);
					}

}
	

