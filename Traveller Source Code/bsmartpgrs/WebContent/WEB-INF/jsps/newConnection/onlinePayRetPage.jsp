<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<%
	String paymentStatus = (String)session.getAttribute("paymentStatusBD"); 
	String TxnID = (String)session.getAttribute("TxnIDBD");
	String TxnAmount = (String)session.getAttribute("TxnAmountBD");
	String ApplicationId = (String)session.getAttribute("applicationIdBD");	
	String payreturnPage = (String)session.getAttribute("payreturnPage"); 
	
  if(payreturnPage.equalsIgnoreCase("newconnection")){
	out.println("</br>");
	out.println("</br>");
	out.println("<h4 style='color:red;text-align:center'>Your Payment has been Processed.Here are the transaction details - </h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Application Id <font color='red'>:"+ApplicationId+"</font></h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Your Transaction Id <font color='red'>:"+TxnID+"</font></h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Payment Status  <font color='red'>:"+paymentStatus+"</font></h1>");
	out.println("</br>");
	out.println("<h4 style='text-align:center'>Amount Paid  <font color='red'>:"+TxnAmount+"</font></h1>");
	}else{
		out.println("</br>");
		out.println("</br>");
		out.println("<h4 style='color:red;text-align:center'>Your Payment has been Processed.Here are the transaction details - </h1>");
		out.println("</br>");
		out.println("<h4 style='text-align:center'>Account Id <font color='red'>:"+ApplicationId+"</font></h1>");
		out.println("</br>");
		out.println("<h4 style='text-align:center'>Your Transaction Id <font color='red'>:"+TxnID+"</font></h1>");
		out.println("</br>");
		out.println("<h4 style='text-align:center'>Payment Status  <font color='red'>:"+paymentStatus+"</font></h1>");
		out.println("</br>");
		out.println("<h4 style='text-align:center'>Amount Paid  <font color='red'>:"+TxnAmount+"</font></h1>");
	}
	
	session.removeAttribute("payreturnPage");
	session.removeAttribute("paymentStatusBD");
	session.removeAttribute("TxnIDBD");
	session.removeAttribute("TxnAmountBD"); 
	session.removeAttribute("applicationIdBD");
	


%>
</body>
</html>