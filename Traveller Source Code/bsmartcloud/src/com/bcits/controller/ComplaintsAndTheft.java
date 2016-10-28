package com.bcits.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.VigilanceComplaintEntity;
import com.bcits.entity.VigilanceTheftEntity;
import com.bcits.service.UserService;
import com.bcits.service.VigilanceComplaintService;
import com.bcits.service.VigilanceTheftService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Link;

@Controller
public class ComplaintsAndTheft {
	
	@Autowired
	private VigilanceComplaintService vigilanceCompService;
	
	@Autowired
	private VigilanceTheftService vigilanceTheftService;
	
	@Autowired
    private UserService userService;
	
	@Link(label="Dashboard", family="UserController", parent = "Home" )
	@RequestMapping(value="/complainTheftDashboard",method=RequestMethod.GET)
	public String complainTheftDashboard(@ModelAttribute("conComplaints") VigilanceComplaintEntity complaintEntity , ModelMap model,BindingResult bindingResult,HttpServletRequest request)
	{
		BCITSLogger.logger.info("In complainTheftDashboard");
		
		return "complainThefDashboard";
	}
	
	@Link(label="Consumer Complaints", family="UserController", parent = "Home" )
	@RequestMapping(value="/consumerComplaints",method=RequestMethod.GET)
	public String consumerComplaints(@ModelAttribute("conComplaints") VigilanceComplaintEntity complaintEntity , ModelMap model,BindingResult bindingResult,HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-Consumer Complaints");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
		model.addAttribute("results", "notDisplay");
		vigilanceCompService.getConsComplData(model,request);
		return "vigilanceComplaint";
	}
    
    @Link(label="Consumer Complaints", family="UserConroller", parent = "Home" )
	@RequestMapping(value="/updateComplStatus",method=RequestMethod.POST)
	public String updateComplStatus(@ModelAttribute("conComplaints") VigilanceComplaintEntity complaintEntity, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
	{		
    		vigilanceCompService.update(complaintEntity);
			model.put("results", "Complaint Status updated Successfully");
			vigilanceCompService.getConsComplData(model,request);
			model.put("conComplaints",new VigilanceComplaintEntity());
			return "vigilanceComplaint";
	}
    
    @RequestMapping(value="/editConsComplaints/{operation}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object editConsComplaints(@PathVariable int operation,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
    	VigilanceComplaintEntity complaintEntity=vigilanceCompService.find(operation);
		return complaintEntity;
	}
    
    @Link(label="Theft Recording", family="UserController", parent = "Home" )
	@RequestMapping(value="/theftRecording",method=RequestMethod.GET)
	public String showTheftRecording(@ModelAttribute("theftRecording") VigilanceTheftEntity theftRecordingEntity , ModelMap model,BindingResult bindingResult,HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-Theft Recording");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		vigilanceTheftService.getTheftData(model,request);
		/*List<TheftRecordingEntity> theftList=theftService.findAll();
		model.put("theftList", theftList);*/
		model.addAttribute("results", "notDisplay");
		return "vigilanceTheft";
	}
    
    @RequestMapping(value="/editTheftRecording/{operation}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object editTheftRecording(@PathVariable int operation,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
    	VigilanceTheftEntity theftEntity=vigilanceTheftService.find(operation);
		return theftEntity;
	}
    
    @Link(label="Consumer Complaints", family="UserConroller", parent = "Home" )
	@RequestMapping(value="/updateTheftStatus",method=RequestMethod.POST)
	public String updateTheftStatus(@ModelAttribute("theftRecording") VigilanceTheftEntity theftRecordingEntity, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
	{		
    	   BCITSLogger.logger.info("DATA ID--------------------->"+theftRecordingEntity.getId()+"  "+theftRecordingEntity.getStatus());
    	   int value=0;
    	    if(theftRecordingEntity.getId()>0)
    	    {
    		    value=vigilanceTheftService.updateStatus(theftRecordingEntity.getId(),theftRecordingEntity.getStatus());
    	    }
			if(value>0)
			{
				model.put("results", "Theft Status updated Successfully");
			}
			else
			{
				model.put("results", "Error while updating the status");
			}
			vigilanceTheftService.getTheftData(model,request);
			model.put("theftRecording",new VigilanceTheftEntity());
			return "vigilanceTheft";
	}

}
