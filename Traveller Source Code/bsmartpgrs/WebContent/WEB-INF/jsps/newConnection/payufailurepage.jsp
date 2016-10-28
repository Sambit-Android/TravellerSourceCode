<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body>

<%

	out.println("<h1 style='color:red;text-align:center'><b>Your Transaction Failed. Please try again later</b></h1>");
	session.removeAttribute("onlinepaymentAppId");
	session.removeAttribute("onlinepaymentSiteCode"); 
%>
</body>
</html>