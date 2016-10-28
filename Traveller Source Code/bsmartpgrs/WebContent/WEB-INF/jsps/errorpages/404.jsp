<%@include file="/common/taglibs.jsp"%>

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

<!-- FAVICONS -->
<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">

<!-- GOOGLE FONT -->
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

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

<style>
	.error-text-2 {
		text-align: center;
		font-size: 700%;
		font-weight: bold;
		font-weight: 100;
		color: #333;
		line-height: 1;
		letter-spacing: -.05em;
		background-image: -webkit-linear-gradient(92deg,#333,#ed1c24);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}
	.particle {
		position: absolute;
		top: 50%;
		left: 50%;
		width: 1rem;
		height: 1rem;
		border-radius: 100%;
		background-color: #ed1c24;
		background-image: -webkit-linear-gradient(rgba(0,0,0,0),rgba(0,0,0,.3) 75%,rgba(0,0,0,0));
		box-shadow: inset 0 0 1px 1px rgba(0,0,0,.25);
	}
	.particle--a {
		-webkit-animation: particle-a 1.4s infinite linear;
		-moz-animation: particle-a 1.4s infinite linear;
		-o-animation: particle-a 1.4s infinite linear;
		animation: particle-a 1.4s infinite linear;
	}
	.particle--b {
		-webkit-animation: particle-b 1.3s infinite linear;
		-moz-animation: particle-b 1.3s infinite linear;
		-o-animation: particle-b 1.3s infinite linear;
		animation: particle-b 1.3s infinite linear;
		background-color: #00A300;
	}
	.particle--c {
		-webkit-animation: particle-c 1.5s infinite linear;
		-moz-animation: particle-c 1.5s infinite linear;
		-o-animation: particle-c 1.5s infinite linear;
		animation: particle-c 1.5s infinite linear;
		background-color: #57889C;
	}@-webkit-keyframes particle-a {
	0% {
	-webkit-transform: translate3D(-3rem,-3rem,0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	} 25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-webkit-transform: translate3D(4rem, 3rem, 0);
	opacity: 1;
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .75rem;
	height: .75rem;
	opacity: .5;
	}

	100% {
	-webkit-transform: translate3D(-3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-moz-keyframes particle-a {
	0% {
	-moz-transform: translate3D(-3rem,-3rem,0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-moz-transform: translate3D(4rem, 3rem, 0);
	opacity: 1;
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .75rem;
	height: .75rem;
	opacity: .5;
	}

	100% {
	-moz-transform: translate3D(-3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-o-keyframes particle-a {
	0% {
	-o-transform: translate3D(-3rem,-3rem,0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-o-transform: translate3D(4rem, 3rem, 0);
	opacity: 1;
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .75rem;
	height: .75rem;
	opacity: .5;
	}

	100% {
	-o-transform: translate3D(-3rem,-3rem,0);
	z-index: -1;
	}
	}

	@keyframes particle-a {
	0% {
	transform: translate3D(-3rem,-3rem,0);
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	transform: translate3D(4rem, 3rem, 0);
	opacity: 1;
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .75rem;
	height: .75rem;
	opacity: .5;
	}

	100% {
	transform: translate3D(-3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-webkit-keyframes particle-b {
	0% {
	-webkit-transform: translate3D(3rem,-3rem,0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-webkit-transform: translate3D(-3rem, 3.5rem, 0);
	opacity: 1;
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-webkit-transform: translate3D(3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-moz-keyframes particle-b {
	0% {
	-moz-transform: translate3D(3rem,-3rem,0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-moz-transform: translate3D(-3rem, 3.5rem, 0);
	opacity: 1;
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-moz-transform: translate3D(3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-o-keyframes particle-b {
	0% {
	-o-transform: translate3D(3rem,-3rem,0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	-o-transform: translate3D(-3rem, 3.5rem, 0);
	opacity: 1;
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-o-transform: translate3D(3rem,-3rem,0);
	z-index: -1;
	}
	}

	@keyframes particle-b {
	0% {
	transform: translate3D(3rem,-3rem,0);
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.5rem;
	height: 1.5rem;
	}

	50% {
	transform: translate3D(-3rem, 3.5rem, 0);
	opacity: 1;
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	transform: translate3D(3rem,-3rem,0);
	z-index: -1;
	}
	}

	@-webkit-keyframes particle-c {
	0% {
	-webkit-transform: translate3D(-1rem,-3rem,0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.3rem;
	height: 1.3rem;
	}

	50% {
	-webkit-transform: translate3D(2rem, 2.5rem, 0);
	opacity: 1;
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-webkit-transform: translate3D(-1rem,-3rem,0);
	z-index: -1;
	}
	}

	@-moz-keyframes particle-c {
	0% {
	-moz-transform: translate3D(-1rem,-3rem,0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.3rem;
	height: 1.3rem;
	}

	50% {
	-moz-transform: translate3D(2rem, 2.5rem, 0);
	opacity: 1;
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-moz-transform: translate3D(-1rem,-3rem,0);
	z-index: -1;
	}
	}

	@-o-keyframes particle-c {
	0% {
	-o-transform: translate3D(-1rem,-3rem,0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.3rem;
	height: 1.3rem;
	}

	50% {
	-o-transform: translate3D(2rem, 2.5rem, 0);
	opacity: 1;
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	-o-transform: translate3D(-1rem,-3rem,0);
	z-index: -1;
	}
	}

	@keyframes particle-c {
	0% {
	transform: translate3D(-1rem,-3rem,0);
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	25% {
	width: 1.3rem;
	height: 1.3rem;
	}

	50% {
	transform: translate3D(2rem, 2.5rem, 0);
	opacity: 1;
	z-index: 1;
	animation-timing-function: ease-in-out;
	}

	55% {
	z-index: -1;
	}

	75% {
	width: .5rem;
	height: .5rem;
	opacity: .5;
	}

	100% {
	transform: translate3D(-1rem,-3rem,0);
	z-index: -1;
	}
	}
</style>

<!--[if IE 9]>
<style>
.error-text {
	color: #333 !important;
}
.particle {
	display:none;
}
</style>
<![endif]-->


			
<!-- MAIN CONTENT -->
<div id="content">

	<!-- row -->
	<div class="row">
	
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	
			<div class="row">
				<div class="col-sm-12">
					<div class="text-center error-box">
						<h1 class="error-text-2 bounceInDown animated"> Error 404 <span class="particle particle--c"></span><span class="particle particle--a"></span><span class="particle particle--b"></span></h1>
						<h2 class="font-xl"><strong><i class="fa fa-fw fa-warning fa-lg text-warning"></i> Page <u>Not</u> Found</strong></h2>
						<br />
						<p class="lead">
							The page you requested could not be found, either contact your webmaster or try again. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from
						</p>
						<p class="font-md">
							<b>... That didn't work on you? Dang. May we suggest a search, then?</b>
						</p>
						<br>
					
					</div>
	
				</div>
	
			</div>
	
		</div>

	<!-- end row -->

</div>

</div>
<!-- END MAIN CONTENT -->




<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script data-pace-options='{ "restartOnRequestAfter": true }' src="./resources/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script>
	if (!window.jQuery) {
		document.write('<script src="./resources/js/libs/jquery-2.0.2.min.js"><\/script>');
	}
</script>

<script src="./resources/kendo/js/jquery.min.js"></script>
<script>
	if (!window.jQuery.ui) {
		document.write('<script src="./resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
	}
</script>

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

<!-- SPARKLINES -->
<script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!-- JQUERY SELECT2 INPUT -->
<script src="./resources/js/plugin/select2/select2.min.js"></script>

<!-- JQUERY UI + Bootstrap Slider -->
<script src="./resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

<!-- browser msie issue fix -->
<script src="./resources/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

<!-- FastClick: For mobile devices -->
<script src="./resources/js/plugin/fastclick/fastclick.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- Demo purpose only -->
<script src="./resources/js/demo.min.js"></script>

<!-- MAIN APP JS FILE -->
<script src="./resources/js/app.min.js"></script>

<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
<!-- Voice command : plugin -->
<script src="./resources/js/speech/voicecommand.min.js"></script>

<!-- PAGE RELATED PLUGIN(S) 
<script src="..."></script>-->

<!-- Your GOOGLE ANALYTICS CODE Below -->
<script type="text/javascript">
	var _gaq = _gaq || [];
		_gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		_gaq.push(['_trackPageview']);
	
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();

</script>
