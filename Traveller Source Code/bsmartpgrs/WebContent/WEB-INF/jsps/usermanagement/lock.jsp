<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en-us" id="lock-page">
	<head>
		<meta charset="utf-8">
		<title> bSmart - Ticket </title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<!-- #CSS Links -->
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support is under construction
			 This RTL CSS will be released in version 1.5
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

		<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/demo.min.css">

		<!-- page related CSS -->
		<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/lockscreen.min.css">

		<!-- #FAVICONS -->
		<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">

		<!-- #GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

		<!-- #APP SCREEN / ICONS -->
		<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
		<link rel="apple-touch-icon" href="./resources/img/splash/sptouch-icon-iphone.png">
		<link rel="apple-touch-icon" sizes="76x76" href="./resources/img/splash/touch-icon-ipad.png">
		<link rel="apple-touch-icon" sizes="120x120" href="./resources/img/splash/touch-icon-iphone-retina.png">
		<link rel="apple-touch-icon" sizes="152x152" href="./resources/img/splash/touch-icon-ipad-retina.png">
		
		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<!-- Startup image for web apps -->
		<link rel="apple-touch-startup-image" href="./resources/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
		<link rel="apple-touch-startup-image" href="./resources/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
		<link rel="apple-touch-startup-image" href="./resources/img/splash/iphone.png" media="screen and (max-device-width: 320px)">

	</head>
	
	<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

		<div id="main" role="main">

			<!-- MAIN CONTENT -->

			<form class="lockscreen animated flipInY" action="./lockrelease" method="post">
				<div class="logo">
					<!-- <h1 class="semi-bold"><img src="./resources/img/cesc.png" style="height: 47px;width: 56px;" alt="" /></h1> -->
				</div>
				<div>
					<img src="./getprofileimage" onclick="getUserName();" alt="" width="120" height="120" />
					<div>
						<h1><i class="fa-3x text-muted air air-top-right hidden-mobile" style=""></i>${userName}<small><i class="fa fa-lock text-muted"></i> &nbsp;Locked</small></h1>
						<p class="text-muted">
							<%-- <a>${userName}</a> --%>
						</p>
						<div class="input-group">
							<input class="form-control" type="password" name="password" required="required">
							<div class="input-group-btn">
								<input class="btn btn-primary" type="submit" value="Unlock">
									<i class="fa fa-key"></i>
								
							</div>
						</div>
						<p class="no-margin margin-top-5">
							Logged as someone else? <a href="./logout"> Click here</a>
						</p>
						<c:if test="${not empty msg}">
							<p class="no-margin margin-top-5" style="color: red;">${msg}</p>
						</c:if>
					</div>

				</div>
				<p class="font-xs margin-top-5" style="margin-left: 480px;">
					<!-- BCITS Pvt Ltd  © 2015. -->

				</p>
			</form>

		</div>

		<!--================================================== -->	

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script src="./resources/js/plugin/pace/pace.min.js"></script>

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script> if (!window.jQuery) { document.write('<script src="./resources/js/libs/jquery-2.0.2.min.js"><\/script>');} </script>

	    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script> if (!window.jQuery.ui) { document.write('<script src="./resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="./resources/js/bootstrap/bootstrap.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!-- JQUERY MASKED INPUT -->
		<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
		
		<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

		<!-- MAIN APP JS FILE -->
		<script src="./resources/js/app.min.js"></script>
		
		<SCRIPT type="text/javascript">
		    window.history.forward();
		    function noBack() { window.history.forward(); }
		</SCRIPT>

	</body>
</html>