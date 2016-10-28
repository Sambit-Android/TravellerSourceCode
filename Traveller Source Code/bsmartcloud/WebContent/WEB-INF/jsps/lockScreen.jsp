<%--  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@include file="headerFiles.jsp" %>
 <script>
 function checKLockPass()
 {
	 if(document.getElementById('lockId').value=='')
		 {
		 bootbox.alert("please enter password to unLock")
		 return false;
		 }
	 
 }
 </script>
	<div class="page-lock">
		<div class="page-logo">			
			<font size="4" color="white" style="text-align: center;margin-left: 0px;">     IREO</font>
            <font size="4" color="red">STA</font>
			<!-- <img src="resources/assets/img/logo-big.png" alt="logo" /> -->
			
		</div>
		<div class="page-body">
			<img class="page-lock-img" src="resources/assets/img/profile/profile.jpg" alt="">
			<div class="page-lock-info">
				<!-- <h1>Bob Nilson</h1> -->
				<span class="email"><%=(String)session.getAttribute("userName") %></span>
				<span class="locked">Locked</span>
				<form class="form-inline" action="./unlock">
				 <p style="color: red">${error}</p>
					<div class="input-group input-medium">					    
						<input type="password" class="form-control" placeholder="Password" name="password" id="lockId">
						<span class="input-group-btn">        
						<button type="submit" class="btn blue icn-only" onclick="return checKLockPass();"><i class="m-icon-swapright m-icon-white"></i></button>
						</span>
					</div>
					<!-- /input-group -->
					<div class="relogin">
						<!-- <a href="login.html">Not Bob Nilson ?</a> -->
					</div>
				</form>
			</div>
		</div>
		 
	</div>
	 
 --%>
 
 
 
 <!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0
Version: 1.5.3
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<!-- <title>Metronic | Extra - Lock Screen</title> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<!-- BEGIN GLOBAL MANDATORY STYLES -->          
	<link href="resources/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN THEME STYLES --> 
	<link href="resources/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="resources/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="resources/assets/css/pages/lock.css" rel="stylesheet" type="text/css" />
	<link href="resources/assets/css/custom.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	
</head>
<!-- END HEAD -->
<script>
 function checKLockPass()
 { 	 
	  if(document.forms[0].lockId.value=="")
		 {		  
		 bootbox.alert("please enter password to unLock");
		 return false;
		 }
	 
 }
 </script>
<!-- BEGIN BODY -->
<body>
	<div class="page-lock">
		<div class="page-logo">			
			<font size="4" color="white" style="text-align: center;margin-left: 0px;">Bsmart</font>
            <font size="4" color="red">Cloud</font>
			<!-- <img src="resources/resources/assets/img/logo-big.png" alt="logo" /> -->
			
		</div>
		<div class="page-body">
			<img class="page-lock-img" src="resources/assets/img/profile/profile.jpg" alt="">
			<div class="page-lock-info">
				<!-- <h1>Bob Nilson</h1> -->
				<span class="email"><%=(String)session.getAttribute("username") %></span>
				<span class="locked">Locked</span>
				<form class="form-inline" action="./unlock" method="post">
				 <p style="color: red">${error}</p>
					<div class="input-group input-medium">					    
						<input type="password" class="form-control" placeholder="Password" name="password" id="lockId">
						<span class="input-group-btn">        
						<button type="submit" class="btn blue icn-only" onclick="return checKLockPass();"><i class="m-icon-swapright m-icon-white"></i></button>
						</span>
					</div>
					<!-- /input-group -->
					<div class="relogin">
						<!-- <a href="login.html">Not Bob Nilson ?</a> -->
					</div>
				</form>
			</div>
		</div>
		 
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->   
	<!--[if lt IE 9]>
	<script src="resources/assets/plugins/respond.min.js"></script>
	<script src="resources/assets/plugins/excanvas.min.js"></script> 
	<![endif]-->   
	<script src="resources/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="resources/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>    
	<script src="resources/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="resources/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<script src="resources/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="resources/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="resources/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="resources/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="resources/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->   
	<script src="resources/assets/plugins/bootbox/bootbox.min.js" type="text/javascript" ></script>
	<script src="resources/assets/scripts/app.js"></script>  
	<script src="resources/assets/scripts/lock.js"></script>    
	
</body>
<!-- END BODY -->
</html>