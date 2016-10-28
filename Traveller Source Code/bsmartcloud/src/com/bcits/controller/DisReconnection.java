package com.bcits.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcits.entity.User;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Link;

@Controller
public class DisReconnection
{
	@RequestMapping(value = "/disReConnDashboard", method = { RequestMethod.POST,RequestMethod.GET })
	@Link(label="Dashboard", family="UserController", parent = "Home" )
	public String dashboard(ModelMap model,HttpServletRequest request)

	{	
		BCITSLogger.logger.info("in dis-reDashboard");
		return "disReconnDashboard";
	}
	
	@RequestMapping(value = "/disConnList", method = { RequestMethod.POST,RequestMethod.GET })
	@Link(label="DisconnectionList", family="UserController", parent = "Home" )
	public String disConnList(ModelMap model,HttpServletRequest request)

	{	
		BCITSLogger.logger.info("in dis-reconn list");
		return "manageDisconnection";
	}
 	
}
