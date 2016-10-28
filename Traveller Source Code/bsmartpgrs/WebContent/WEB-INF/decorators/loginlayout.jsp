<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
	<head>
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-skins.min.css">
		
		<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">	

		<title> bSmart - Ticket </title>
		<meta name="description" content="">
		<meta name="author" content="">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	</head>
	
	<body class="animated fadeInDown">
		<%@include file="/common/genericlogin.jsp"%>
		<jsp:include page="loginheader.jsp" />
		<div id="main" role="main">
			<decorator:body />
		</div>
	</body>
</html>