<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PayUMoney - Loading...</title>
   <meta name="description" content="PayUMoney is renowned Payment Gateway Service Providers in India providing safe payments gateway platforms for transactions carried out between customers & merchants"/>
   <meta name="keywords" content="Payment Gateway Service Provider India, Electronic Payment Systems Provider in India"/>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<%
String paymentStatus = (String)session.getAttribute("paymentStatus"); 
String TxnID = (String)session.getAttribute("TxnID");
String TxnAmount = (String)session.getAttribute("TxnAmount");
String payuMoneyId = (String)session.getAttribute("payuMoneyId");
String paymentforRegsiter = String.valueOf(session.getAttribute("paymentforRegsiter"));
String onlinepaymentAppId = String.valueOf(session.getAttribute("onlinepaymentAppId"));
if(paymentStatus!=null && paymentStatus.equals("Success"))
{
	//Handle Success Response and Update your database
	out.println("<h1 style='color:green;text-align:center'><b>Payment Successful</b></h1>");
	out.println("</br>");
	if("paymentforRegsiter".equalsIgnoreCase(paymentforRegsiter)){
	out.println("<h4 style='text-align:center'>Your Application has been Registered Successfully.Here are the transaction details - </h1>");	
	}else{
	out.println("<h4 style='text-align:center'>Your payment has been processed.Here are the transaction details - </h1>");
	}
	if(onlinepaymentAppId!=null && onlinepaymentAppId!="null" && onlinepaymentAppId!=""){
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Application Id :"+onlinepaymentAppId+"</h1>");
	}
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Your Transaction Id :"+TxnID+"</h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>payU MoneyId  :"+payuMoneyId+"</h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Amount Paid  :"+TxnAmount+"</h1>");
	// Redirect to Success Page
	session.removeAttribute("paymentStatus");
	session.removeAttribute("TxnID");
	session.removeAttribute("payuMoneyId");
	session.removeAttribute("Success"); 
	
	session.removeAttribute("onlinepaymentAppId");
	session.removeAttribute("onlinepaymentSiteCode");
	session.removeAttribute("paymentforRegsiter");
}
else
{
	//Handle Failure Response and Update your database
	out.println("<h1 style='color:red;text-align:center'><b>Your Transaction Failed. Please try again later</b></h1>");
	// Redirect to Failure Page
}

%>
</body>
</html>