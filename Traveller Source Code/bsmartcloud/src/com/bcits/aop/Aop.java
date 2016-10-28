package com.bcits.aop;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.bcits.service.ModuleMasterService;

@Aspect
public class Aop {
	
	@Autowired
	private ModuleMasterService moduleMasterService;

	@After("execution(* com.bcits.controller.*.*(..)) && !execution(* com.bcits.controller.AdminController.getprofilePic(..))")
	public void removeSessionForWebreq(JoinPoint joinPoint) throws Throwable 
	{
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		if(request.getSession().getAttribute("userType")!=null)
		{
		  request.setAttribute("activeModules",moduleMasterService.findAllActivatedModuleMasters());	
		}
	}
}
