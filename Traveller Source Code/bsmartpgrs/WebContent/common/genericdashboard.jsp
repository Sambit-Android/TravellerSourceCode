 <%@include file="/common/taglibs.jsp"%>

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/font-awesome.min.css">

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-skins.min.css">

<!-- SmartAdmin RTL Support is under construction
	 This RTL CSS will be released in version 1.5 -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-rtl.min.css">

<!-- We recommend you use "your_style.css" to override SmartAdmin
     specific styles this will also ensure you retrain your customization with each SmartAdmin update. -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/your_style.css">

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/demo.min.css">

<!-- FAVICONS -->
<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">

<!-- GOOGLE FONT -->
<!-- <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700"> -->

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

<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
	Note: These tiles are completely responsive,
	you can add as many as you like
	-->
	<div id="shortcut">
		<ul>
			<li>
				<a href="#inbox.html" class="jarvismetro-tile big-cubes bg-color-blue"> <span class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail <span class="label pull-right bg-color-darken">14</span></span> </span> </a>
			</li>
			<li>
				<a href="#calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span> </span> </a>
			</li>
			<li>
				<a href="#gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple"> <span class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span> </span> </a>
			</li>
			<!-- <li>
				<a href="#invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
			</li> -->
			<li>
				<a href="#gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
			</li>
			<li>
				<a href="./profile" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
			</li>
		</ul>
	</div>
	<!-- END SHORTCUT AREA -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script data-pace-options='{ "restartOnRequestAfter": true }' src="./resources/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="./resources/js/libs/jquery-2.0.2.min.js"></script>


<script src="./resources/js/libs/jquery-ui-1.10.3.min.js"></script>


<!-- IMPORTANT: APP CONFIG -->
<script src="./resources/js/app.config.js"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
<script src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> 

<!-- BOOTSTRAP JS -->
<script src="./resources/js/bootstrap/bootstrap.min.js"></script>

<!-- CUSTOM NOTIFICATION -->
<script src="./resources/js/notification/SmartNotification.min.js"></script>

<!-- JARVIS WIDGETS -->
<script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>

<!-- EASY PIE CHARTS -->
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>



<!-- Demo purpose only -->
<!-- ------------------------------------------------------------------------------------------------------------------------------ -->

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- Demo purpose only -->
<!-- <script src="./resources/js/demo.min.js"></script> -->

<script type="text/javascript" src="./resources/js/app.min.js"></script>


<!-- Voice command : plugin -->
<script src="./resources/js/speech/voicecommand.min.js"></script>
		
<!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>

<!-- Vector Maps Plugin: Vectormap engine, Vectormap language -->
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- Full Calendar -->
<script src="./resources/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>

<script>

	
	$(document).ajaxError( function(event, request, settings, exception) {
	    if(String.prototype.indexOf.call(request.responseText, "j_username") != -1) {
	        window.location.reload(document.URL);
	    }
	    if(request.status==500){
	    	window.location.href = "./500";
	    }
	    /* if(request.status==403){
	    	window.location.href = "./403";
	    }
	    if(request.status==404){
	    	window.location.href = "./404";
	    } */
	    
	});
	
	function securityCheckForActions(url)
	{
		$.ajax({
			type : "POST",
			url : url,
			success : function(response) 
			{
			},			
			error: function(jqXHR, textStatus, errorThrown) {
			   
			    if(jqXHR.status=="403")
			    {
			    	window.location.href = "./403";
			    }
			}
		});

	}
	
	
	function securityCheckForActions(url)
	{
		$.ajax({
			type : "POST",
			url : url,
			success : function(response) 
			{
			},			
			error: function(jqXHR, textStatus, errorThrown) {
			   
			    if(jqXHR.status=="403")
			    {
			    	window.location.href = "./accessDenied";
			    }
			}
		});

	}
	function securityCheckForActionsForStatus(url)
	{
		var result="";
		$.ajax({
			type : "POST",
			url : url,
			async: false,
			datatype:'text',
			success : function(response) 	{			
				result = "success";			
			},			
			error: function(jqXHR, textStatus, errorThrown) {	 
			    
			    if(jqXHR.status=="403")
			    {
			    	result = "false";	
			    	window.location.href = "./accessDenied";
			    }
			}		
		});	
		return result;
		
	}

</script>






