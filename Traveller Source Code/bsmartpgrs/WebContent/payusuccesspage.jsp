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
String paymentStatus = (String)request.getAttribute("paymentStatus"); 
String TxnID = (String)request.getAttribute("TxnID");
String TxnAmount = (String)request.getAttribute("TxnAmount");
String payuMoneyId = (String)request.getAttribute("payuMoneyId");
if(paymentStatus!=null && paymentStatus.equals("Success"))
{
	//Handle Success Response and Update your database
	out.println("<h1 style='color:green;text-align:center'><b>Payment Successful</b></h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Your payment has been processed.Here are the transaction details - </h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Your Transaction Id :"+TxnID+"</h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>payU MoneyId  :"+payuMoneyId+"</h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Amount Paid  :"+TxnAmount+"</h1>");
	// Redirect to Success Page
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