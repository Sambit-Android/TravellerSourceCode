package com.pgrs.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserCredentialSendMailThread implements Runnable {
	
	private final String toAddressEmail;
	private final String mailId;
	private final String mailIdPwd;	
	private final String mailSmtpHost;
	private final String mailSmtpSocketFactoryPort;
	private final String mailSmtpSocketFactoryClass;
	private final String mailSmtpAuth;
	private final String mailSmtpPort;
	private final InternetAddress fromAddress;
	private final String loginUserName;
	private final String loginPwd;
	private final String hostIp;
	private final String portNo;
	private final String appName;
	private final String personName;
	
	public UserCredentialSendMailThread(EmailCredentialsDetailsBean emailCredentialsDetailsBean,String loginUserName,String loginPwd,String personName) {
		this.toAddressEmail=emailCredentialsDetailsBean.getToAddressEmail();
		this.mailId=emailCredentialsDetailsBean.getMailId();
		this.mailIdPwd=emailCredentialsDetailsBean.getMailIdPwd();
		this.mailSmtpHost=emailCredentialsDetailsBean.getMailSmtpHost();
		this.mailSmtpSocketFactoryPort=emailCredentialsDetailsBean.getMailSmtpSocketFactoryPort();
		this.mailSmtpSocketFactoryClass=emailCredentialsDetailsBean.getMailSmtpSocketFactoryClass();
		this.mailSmtpAuth=emailCredentialsDetailsBean.getMailSmtpAuth();
		this.mailSmtpPort=emailCredentialsDetailsBean.getMailSmtpPort();
		this.fromAddress=emailCredentialsDetailsBean.getFromAddress();
		this.loginUserName=loginUserName;
		this.loginPwd=loginPwd;
		this.hostIp=emailCredentialsDetailsBean.getHostIp();
		this.portNo=emailCredentialsDetailsBean.getPortNo();
		this.appName=emailCredentialsDetailsBean.getAppName();
		this.personName=personName;
	}

	@Override
	public void run() {
		Properties props = new Properties();
		props.put("mail.smtp.host", mailSmtpHost);
		props.put("mail.smtp.socketFactory.port", mailSmtpSocketFactoryPort);
		props.put("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
		props.put("mail.smtp.auth", mailSmtpAuth);
		props.put("mail.smtp.port", mailSmtpPort);
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(mailId, mailIdPwd);
			}
		});	
		
		try{
			Message message = new MimeMessage(session);
			message.setFrom(fromAddress);
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddressEmail));
			message.setSubject("User Credential Mail");
			
			String messageContent = "<html>"
					+ "<style type=\"text/css\">"
					+ "table.hovertable {"
					+ "font-family: verdana,arial,sans-serif;"
					+ "font-size:11px;"
					+ "color:#333333;"
					+ "border-width: 1px;"
					+ "border-color: #999999;"
					+ "border-collapse: collapse;"
					+ "}"
					+ "table.hovertable th {"
					+ "background-color:#c3dde0;"
					+ "border-width: 1px;"
					+ "padding: 8px;"
					+ "border-style: solid;"
					+ "border-color: #394c2e;"
					+ "}"
					+ "table.hovertable tr {"
					+ "background-color:#88ab74;"
					+ "}"
					+ "table.hovertable td {"
					+ "border-width: 1px;"
					+ "padding: 8px;"
					+ "border-style: solid;"
					+ "border-color: #394c2e;"
					+ "}"
					+ "</style>"
					+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">CESC ADMINISTRATION DEPARTMENT.</h2> <br /> Dear "+ personName + ", Welcome to PGRS <br/> <br/> "
					+ "Please use following LoginName and Password to login. <br/> <br/>"
					+ "<table class=\"hovertable\" >" + "<tr>"
					+ "<td colspan='2'>Your Account Details are</td>"
					+ "</tr>" + "<tr>" + "<td> Login Name :</td>"
					+ "<td>" + loginUserName + "</td>" +

					"</tr>" + "<tr>" + "<td> Password:</td>" + "<td>"
					+ loginPwd + "</td>" + "</tr>" + "</table>"
					+ "<br/><br/>"
					+ "<a href=http://"+ hostIp +":"+ portNo +"/"+ appName
					+ ">click to login</a></body></html>"
					+ "<br/><br/>"
					+ "<br/>Thanks,<br/>"
					+ "CESC Administration Services. <br/> <br/>";
			
			message.setContent(messageContent,"text/html; charset=ISO-8859-1");
			
			PGRSLogger.logger.info("User credentials mail sent successfully");
			Transport.send(message);
		}catch(Exception e){
			PGRSLogger.logger.info("User credentials mail failed");
			e.printStackTrace();
		}		
	}

}
