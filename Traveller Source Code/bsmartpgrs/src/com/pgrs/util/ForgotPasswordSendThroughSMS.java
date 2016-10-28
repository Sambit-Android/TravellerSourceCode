package com.pgrs.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;

public class ForgotPasswordSendThroughSMS implements Runnable {

	private final String number;
	private final String userName;
	//private final String message;
	private final String gateWayUserName;
	private final String gateWayPassword;
	private final String gateWayURL;
	private final String smsGatewaySid;
	private final String smsGatewayFL;
	private final String smsGatewayGwid;
	private final String loginUserName;
	private final String loginPwd;
	
	public ForgotPasswordSendThroughSMS(SmsCredentialsDetailsBean smsCredentialsDetailsBean,String loginUserName,String loginPwd)
	{
		this.number = "91"+smsCredentialsDetailsBean.getNumber();
		this.userName = smsCredentialsDetailsBean.getUserName();
		//this.message = smsCredentialsDetailsBean.getMessage();
		this.gateWayUserName = smsCredentialsDetailsBean.getSmsGatewayUsername();
		this.gateWayPassword = smsCredentialsDetailsBean.getSmsGatewayPwd();
		this.gateWayURL = smsCredentialsDetailsBean.getSmsGatewayURL();
		this.smsGatewaySid = smsCredentialsDetailsBean.getSmsGatewaySid();
		this.smsGatewayFL = smsCredentialsDetailsBean.getSmsGatewayFL();
		this.smsGatewayGwid = smsCredentialsDetailsBean.getSmsGatewayGwid();
		this.loginUserName=loginUserName;
		this.loginPwd=loginPwd;
	}

	@Override
	public void run()
	{
		HttpClient client=null;
		 PostMethod post=null;
		 String sURL;
		 client = new HttpClient(new MultiThreadedHttpConnectionManager());
		 client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);//set
		 sURL = gateWayURL;
		 post = new PostMethod(sURL);
		 //give all in string
		 post.addParameter("user", gateWayUserName);
		 post.addParameter("password", gateWayPassword);
		 post.addParameter("msisdn", number);
		 post.addParameter("msg", "Dear "+userName+", Your account details are Login Name : "+loginUserName+" and Password : "+loginPwd+" - CSEC Administration services.");
		 post.addParameter("sid",smsGatewaySid);
		 post.addParameter("fl", smsGatewayFL);
		 post.addParameter("GWID", smsGatewayGwid);
		  //PUSH the URL 
		 String sent = "";
		 try 
			{
				int statusCode = client.executeMethod(post);
				if (statusCode != HttpStatus.SC_OK) 
					PGRSLogger.logger.error("Method failed: " + post.getStatusLine());
				else
					PGRSLogger.logger.info("************* MESSAGE SENT SUCCESSFULY *************");
				PGRSLogger.logger.info("statusCode --------"+statusCode);
				PGRSLogger.logger.info("line 1 ------------"+post.getStatusLine().toString());
				PGRSLogger.logger.info("SC_OK -------------"+HttpStatus.SC_OK);
				PGRSLogger.logger.info("line 2 ------------"+post.getResponseBodyAsString());
				
				if(post.getResponseBodyAsString().contains("Failed"))
					PGRSLogger.logger.info("Failed ------------"+sent);
				else
					PGRSLogger.logger.info("Success ------------"+sent);
				
				
				sent=post.getResponseBodyAsString().toString();
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				post.releaseConnection();
			}

	}
}
