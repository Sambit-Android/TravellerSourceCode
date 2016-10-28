package com.bcits.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Link;

@Controller
public class Vigilance {
	
	@RequestMapping(value = "/assignVigilance", method = { RequestMethod.POST,RequestMethod.GET })
	@Link(label="Assign Vigilance",family="UserController",parent="Home" )
	public String index(ModelMap model,HttpServletRequest request)

	{
		BCITSLogger.logger.info("in assign vigilance");
		return "assignVigilance";
	}

	  /*public static void main(String[] args) throws ParseException 
	  { 
		  String myTime = "14:10";
	  SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	  Date d = df.parse(myTime); 
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(d);
	  cal.add(Calendar.MINUTE, 30);
	  String newTime = df.format(cal.getTime());
	  BCITSLogger.logger.info("in assign vigilance"+newTime);
	  
	  } */ 
}
