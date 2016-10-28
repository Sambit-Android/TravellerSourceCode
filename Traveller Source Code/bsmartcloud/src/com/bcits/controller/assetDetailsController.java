package com.bcits.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.AssetTransactionEntity;
import com.bcits.service.AssetDetailsService;
import com.bcits.service.AssetTransactionService;
import com.bcits.utility.BCITSLogger;

@Controller
public class assetDetailsController {
	
	@Autowired
	private AssetDetailsService assetDetailsService;
	
	@Autowired
	private AssetTransactionService assetTransactionService;
	
	@RequestMapping(value="/assetDetails",method={RequestMethod.POST,RequestMethod.GET})
	public String dashBoard(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In Asset Details");
		HttpSession session=request.getSession();
		String usertype=(String)session.getAttribute("userType");
		if(usertype.equalsIgnoreCase("ADMIN")){
			//model.addAttribute("assetData",assetDetailsService.getAllAssetDetails());
		}else{
			
		}
		return "assetDetails";
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@RequestMapping(value = "/deregisterDevice", method = RequestMethod.POST)
	public @ResponseBody Object deregisterDevice(@RequestParam("deviceid") String deviceid,@RequestParam("sitecode") String sitecode,HttpServletRequest request, ModelMap model) {
		String status="DeRegister";
		assetDetailsService.updateMRDeviceEntity(deviceid,status);
		AssetTransactionEntity assetTransactionEntity=new AssetTransactionEntity();
		assetTransactionEntity.setDeviceId(deviceid);
		assetTransactionEntity.setSiteCode(Integer.parseInt(sitecode));
		assetTransactionEntity.setTransDate(new Timestamp(new Date().getTime()));
		assetTransactionEntity.setTransType("DeRegistered");
		assetTransactionService.save(assetTransactionEntity);
		return null;
	}
}
