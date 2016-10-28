package com.pgrs.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPasswordSendMailThread implements Runnable {

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
	private final String personName;
	
	public ForgotPasswordSendMailThread(EmailCredentialsDetailsBean emailCredentialsDetailsBean,String loginUserName,String loginPwd,String personName) {
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
			message.setSubject("Password Recovery");
			
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
					+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">CESC Administration Department</h2> Dear "+ personName + ", <br/><br/>"
					+ "Welcome to PGRS, the smarter way to provide an effective grievance reddressel mechanism any time, anywhere.<br/>"
					+ "Kindly to inform you that the password has been sent to your mail as per your request.<br/>"
					+ "Please login to the system with the User Name and Password provided below :<br/>"
					+ "&nbsp;&nbsp;USERNAME : "+loginUserName+"<br/>"
					+ "&nbsp;&nbsp;PASSWORD : "+loginPwd+"<br/><br/>"
					+ "<br/>Thank you,<br/>"
					+ "CESC Administration Services. <br/> <br/>";
			
			message.setContent(messageContent,"text/html; charset=ISO-8859-1");

			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
