package com.pgrs.util;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;

@Component
public class EmailCredentialsDetailsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailCredentialsDetailsBean()throws Exception{
		
	}
	
	private String toAddressEmail;
	
	private String messageContent;
	
	private final String mailId = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("Email.gateWay.username");
	
	private final String mailIdPwd = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("Email.gateWay.password");
	
	private final String mailSmtpHost = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("mail.smtp.host");
	
	private final String mailSmtpSocketFactoryPort = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("mail.smtp.socketFactory.port");
	
	private final String mailSmtpSocketFactoryClass = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("mail.smtp.socketFactory.class");
	
	private final String mailSmtpAuth = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("mail.smtp.auth");
	
	private final String mailSmtpPort = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("mail.smtp.port");
	
	private final InternetAddress fromAddress = new InternetAddress(mailId,ResourceBundle.getBundle("EmailAndSmsCredentials").getString("Email.gateWay.displayName"));
	
	private final String hostIp = ResourceBundle.getBundle("configuration").getString("Unique.conf.serverIp");
	
	private final String portNo = ResourceBundle.getBundle("configuration").getString("Unique.conf.Serverport");
	
	private final String appName = ResourceBundle.getBundle("configuration").getString("Unique.conf.AppVersion");
	
	private final String trackComplaintConsumer = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("Email.gateWay.trackComplaintConsumer");
	
	private final String trackComplaintOfficial = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("Email.gateWay.trackComplaintOfficial");
	
	private String mailSubject;
	
	private final String tollFreeNumber = ResourceBundle.getBundle("EmailAndSmsCredentials").getString("SMS.gateWay.tollFreeNumber");

	public String getToAddressEmail() {
		return toAddressEmail;
	}

	public void setToAddressEmail(String toAddressEmail) {
		this.toAddressEmail = toAddressEmail;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	public String getMailId() {
		return mailId;
	}

	public String getMailIdPwd() {
		return mailIdPwd;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public String getMailSmtpSocketFactoryPort() {
		return mailSmtpSocketFactoryPort;
	}

	public String getMailSmtpSocketFactoryClass() {
		return mailSmtpSocketFactoryClass;
	}

	public String getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public String getMailSmtpPort() {
		return mailSmtpPort;
	}

	public InternetAddress getFromAddress() {
		return fromAddress;
	}

	public String getHostIp() {
		return hostIp;
	}

	public String getPortNo() {
		return portNo;
	}

	public String getAppName() {
		return appName;
	}

	public String getTrackComplaintConsumer() {
		return trackComplaintConsumer;
	}

	public String getTrackComplaintOfficial() {
		return trackComplaintOfficial;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getTollFreeNumber() {
		return tollFreeNumber;
	}
	
}
