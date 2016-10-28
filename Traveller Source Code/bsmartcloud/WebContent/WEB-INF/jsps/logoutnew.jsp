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
	<!-- END THEME STYLES -->
	
</head>
<!-- END HEAD -->
<script>
 
 </script>
<!-- BEGIN BODY -->
<body>
	
	<div class="content">
		hello
	 </div>
	
</body>
<!-- END BODY -->
</html>