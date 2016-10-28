 <%@include file="/common/taglibs.jsp"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-skins.min.css">
		
		<link href="<c:url value='./resources/kendo/css/web/kendo.common.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='./resources/kendo/css/web/kendo.rtl.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='./resources/kendo/css/web/kendo.silver.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='./resources/kendo/css/dataviz/kendo.dataviz.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='./resources/kendo/css/dataviz/kendo.dataviz.default.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='./resources/kendo/shared/styles/examples-offline.css'/>" rel="stylesheet">	
		
		<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">	

		<title> bSmart - Ticket </title>
		<meta name="description" content="">
		<meta name="author" content="">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<decorator:head/>
	</head>
	<body onload="change()">
		<jsp:include page="header.jsp" />
		<c:if test="${not empty userName}">
		    <jsp:include page="leftMenu.jsp" />
		</c:if>
		
		<c:if test="${not empty CustomerLoginName}">
		    <%@include file="/common/customerLeftMenu.jsp"%>
		</c:if>
		 
		<!-- MAIN PANEL -->
		<div id="main" role="main">
			<jsp:include page="breadcrum.jsp" />
			 <%@include file="/common/genericdashboard.jsp"%>
			 	
				<%
					String str ="";
					if(session.getAttribute("themeName") == null)
					{
						str ="kendo.default.min.css";
					}
					else
					{
						str ="kendo."+session.getAttribute("themeName")+".min.css";
					}
					
				  %>
				  <link href="./resources/kendo/css/web/<%=str%>" rel="stylesheet" />
				<script src="<c:url value='./resources/kendo/js/kendo.all.min.js' />"></script>
				<script src="<c:url value='./resources/kendo/shared/js/console.js'/>"></script>
				<script src="<c:url value='./resources/kendo/shared/js/prettify.js'/>"></script>
			<decorator:body />
		</div>
		<jsp:include page="footer.jsp" />
		
	</body>
</html>
<script>
function change() {
	if(!($("body").attr("class").contains("pace-running desktop-detected menu-on-top"))){
		var id='${openstatus}';
		$("#"+id).find('ul:first').slideToggle();
	    $("#"+id).addClass('active');
	}
	
}
$(document).ajaxError( function(event, request, settings, exception) {
    if(String.prototype.indexOf.call(request.responseText, "j_username") != -1) {
        window.location.reload(document.URL);
    }
});
</script>

<style>
 .k-window-titlebar {
   height: 25px;
 }
 div.ui-dialog {position:fixed;overflow:"auto";} 
</style>

