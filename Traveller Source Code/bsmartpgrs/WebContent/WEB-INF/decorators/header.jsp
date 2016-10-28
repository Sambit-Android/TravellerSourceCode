<%@ include file="/common/taglibs.jsp"%>
<%@page contentType="text/html" import="java.util.*" %>
<script src="./resources/kendo/js/jquery.min.js"></script>

<link href="http://fonts.googleapis.com/css?family=Lobster%3A400" rel="stylesheet" property="stylesheet" type="text/css" media="all" />

<%-- <c:if test = "${empty userId}"> 			        
		    <script>
				window.location.href="login";
			</script>
</c:if>
 --%>
<div id="score">

	<!-- HEADER -->
	<header id="header">
		<div id="logo-group">
	
			<!-- PLACE YOUR LOGO HERE -->
			<!-- <a href="./index"> --><span id="logo"><img src="./resources/img/cesclogo.png" alt="" style="margin-top: -10px;"></span><!-- </a> -->
			<span id="logo"> </span>
			<!-- END LOGO PLACEHOLDER -->
	
			<!-- Note: The activity badge color changes when clicked and resets the number to 0
			Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
			<c:if test="${not empty userName}">
			<span style="margin-top: -41px;" id="activity" class="activity-dropdown" onclick="${notificationUrl1}"> <i class="fa fa-user"></i> <b class="badge bg-color-red bounceIn animated" onclick="${notificationUrl1}" id="totCount"> ${totalCount} </b> </span>
			</c:if>
			<!-- <span id="activity" class="activity-dropdown"> <i class="fa fa-user"><b class="badge bg-color-red bounceIn animated" >12</b></i></span> --> 
			<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
			<div class="ajax-dropdown">
	
				<!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
				<div class="btn-group btn-group-justified" data-toggle="buttons">
					
					<c:if test="${not empty notificationName1}">
					<label class="btn btn-default">
						<input type="radio" onclick="${notificationUrl1}" id="header/recoupment/notification/details" onchange="${notificationUrl1}" />
						<span id="notifyTab1">${notificationName1} (${notificationCount1})</span> </label>
					</c:if>
					
					<c:if test="${not empty notificationName2}">
					<label class="btn btn-default">
						 <input type="radio" onclick="${notificationUrl2}" id="header/notification/details" onchange="${notificationUrl2}" /> 
						<span id="notifyTab2">${notificationName2} (${notificationCount2})</span> </label>
					</c:if>
					
					<%-- <c:if test="${not empty notificationName3}">
					<label class="btn btn-default">
						 <input type="radio" onclick="${notificationUrl3}" id="header/notification/details2" onchange="${notificationUrl3}" /> 
						${notificationName3} (${notificationCount3}) </label>
					</c:if> --%>
					
					
					<%-- <label class="btn btn-default">
						<input type="radio" onclick="taskDetails()" id="header/task/details" onchange="taskDetails()" /> 
						<!--  <input type="radio" onclick="pettyTaskDetails()" id="header/notification/details" onchange="pettyTaskDetails" /> --> 
						${notificationName3} (${notificationCount3}) </label>  --%>
				</div>
	
				<!-- notification content -->
				<div class="ajax-notifications custom-scroll">
	
					<div class="alert alert-transparent" style="margin-top: -5px;">
						<h4>Click a button to show messages here</h4>
						This blank page message helps protect your privacy, or you can show the first message here automatically.
					</div>
	
					<!-- <i class="fa fa-lock fa-4x fa-border"></i> -->
	
				</div>
				<!-- end notification content -->
	
				<!-- footer: refresh area -->
				<span> Last updated on: <%= new java.util.Date() %>
					<button type="button" onclick="${notificationUrl1}" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Loading..." class="btn btn-xs btn-default pull-right">
						<i class="fa fa-refresh"></i>
					</button> 
				</span>
				<!-- end footer -->
	
			</div>
			<!-- END AJAX-DROPDOWN -->
		</div>
		
		<div>
		<span 
			data-width="none"
			data-height="none"
			data-whitespace="nowrap"
			data-transform_idle="o:1;"
 
			 data-transform_in="z:0;rX:0;rY:0;rZ:0;sX:0.8;sY:0.8;skX:0;skY:0;opacity:0;s:500;e:Power4.easeOut;" 
			 data-transform_out="opacity:0;s:300;" 
			data-splitin="none"  
			data-splitout="none" 
			data-responsive_offset="on" 			
			style="padding-left:250px;white-space: nowrap; font-size: 30px; font-weight: 400; color: #2C699D;font-family:Lobster;"> bSmart - Support Ticket System </span>
	</div>
		
		<!-- projects dropdown -->
		<%-- <div class="project-context hidden-xs">
		
			<span class="label" style="color: blue">${companyName}</span>
			<span class="project-selector dropdown-toggle" data-toggle="dropdown">${projectName} <i class="fa fa-angle-down"></i></span>
	
			<!-- Suggestion: populate this list with fetch and push technique -->
			<ul class="dropdown-menu">
				<c:forEach items="${companylist}" var="company">
					<li>
						<a href="./changeCompany/${company.companyName}" style="background-color: gold">${company.companyName}</a>
							<c:forEach items="${company.userProject}" var="project">
								<ul>
									<li><a href="./changeProject/${company.companyName}/${project.projectName}">${project.projectName}</a>
								</ul>
							</c:forEach>
					</li>
				</c:forEach>
			</ul>
			<!-- end dropdown-menu-->
	
		</div> --%>
		<!-- end projects dropdown -->
	
		<!-- pulled right: nav area -->
		<div class="pull-right">
			
			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
			</div>
			<!-- end collapse menu -->
			
			<!-- #MOBILE -->
			<!-- Top menu profile link : this shows only when top menu is active -->
			<ul id="mobile-profile-img" class="header-dropdown-list hidden-xs padding-5">
				<li class="">
					<a href="#" class="dropdown-toggle no-margin userdropdown" data-toggle="dropdown"> 
						<c:if test="${not empty userName}">
						<img src="./getprofileimage" alt="" width="100px" height="30px" class="online" /> 
						</c:if> 
					</a>
					<ul class="dropdown-menu pull-right">
						<!-- <li>
							<a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0"><i class="fa fa-cog"></i> Setting</a>
						</li> 
						<li class="divider"></li>-->
						<li>
							<a href="./profile" class="padding-10 padding-top-0 padding-bottom-0"> <i class="fa fa-user"></i> <u>P</u>rofile</a>
						</li>
						<li class="divider"></li>
						<!-- <li>
							<a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="toggleShortcut"><i class="fa fa-arrow-down"></i> <u>S</u>hortcut</a>
						</li> 
						<li class="divider"></li>-->
						<li>
							<a href="javascript:void(0);" class="padding-10 padding-top-0 padding-bottom-0" data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i> Full <u>S</u>creen</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="./logout" class="padding-10 padding-top-5 padding-bottom-5" data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i> <strong><u>L</u>ogout</strong></a>
						</li>
					</ul>
				</li>
			</ul>
	
			<!-- logout button -->
			<c:if test="${not empty userName}">
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a href="./logout" title="Sign Out" data-action="userLogout" data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i class="fa fa-sign-out"></i></a> </span>
			</div>
			</c:if>
			
			<c:if test="${not empty CustomerLoginName}">
			<div id="customerlogout" class="btn-header transparent pull-right">
				<span> <a href="./custumerLoginPage" title="Sign Out" data-action="userLogout" data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i class="fa fa-sign-out"></i></a> </span>
			</div>
			</c:if>
			
			<!-- end logout button -->
	
			<!-- search mobile button (this is hidden till mobile view port) -->
			<div id="search-mobile" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
			</div>
			<c:if test="${not empty userName}">
			<div id="lockscreen" class="btn-header transparent pull-right">
				<span> <a target="_top" href="./lock" title="Lock Screen"><i class="fa fa-lock"></i></a> </span>
			
			</div>
			
			<!-- <div class="widget-toolbar" style="padding-top: 14px;">

				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-warning" data-toggle="dropdown">Download User Manuals <i class="fa fa-caret-down"></i></button>
					<ul class="dropdown-menu pull-right">
					 <li>
						<a  href="#menu2" data-toggle="tab" target="_blank" onclick="window.open('./downloadPGRSUserManual/download');"><i class="fa fa-file-text-alt"></i>PGRS User Manual</a>
					</li>
					<li>
						<a href="#" data-toggle="tab" target="_blank" onclick="window.open('./downloadLTHTUserManual/download');"><i class="fa fa-question-sign"></i>LTHT User Manual</a>
					</li>
					<li>
						<a href="#" data-toggle="tab" target="_blank" onclick="window.open('http://www.cescmysorepgrs.com/userguide/');"><i class="fa fa-question-sign"></i>Download/Play Videos</a>
					</li>
					
					<li>
						<a href="#" data-toggle="tab" target="_blank" onclick="window.open('./downloadCommonSRRates/download');"><i class="fa fa-question-sign"></i>Common Schedule of Rates 2014-15</a>
					</li>  
					
					</ul>
				</div>
			</div> -->
			
			
			</c:if>
			
			<c:if test="${not empty CustomerLoginName}">
			<div id="ncmsUserManual" class="pull-right" style="padding-top: 15px;">
				<span><a href="#menu2" data-toggle="tab" target="_blank" onclick="window.open('./downloadNCMSUserManual/download');">Download NCMS User Manual</a></span>
			
			</div>
			</c:if>
			
			<!-- end fullscreen button -->
			
			<!-- fullscreen button -->
			<!-- <div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen"><i class="fa fa-arrows-alt"></i></a> </span>
			</div> -->
			<!-- end fullscreen button -->
			
			<!-- #Voice Command: Start Speech -->
			<div id="speech-btn" class="btn-header transparent pull-right hidden-sm hidden-xs">
				<div> 
					<a href="javascript:void(0)" title="Voice Command" data-action="voiceCommand"><i class="fa fa-microphone"></i></a> 
					<div class="popover bottom"><div class="arrow"></div>
						<div class="popover-content">
							<h4 class="vc-title">Voice command activated <br><small>Please speak clearly into the mic</small></h4>
							<h4 class="vc-title-error text-center">
								<i class="fa fa-microphone-slash"></i> Voice command failed
								<br><small class="txt-color-red">Must <strong>"Allow"</strong> Microphone</small>
								<br><small class="txt-color-red">Must have <strong>Internet Connection</strong></small>
							</h4>
							<a href="javascript:void(0);" class="btn btn-success" onclick="commands.help()">See Commands</a> 
							<a href="javascript:void(0);" class="btn bg-color-purple txt-color-white" onclick="$('#speech-btn .popover').fadeOut(50);">Close Popup</a> 
						</div>
					</div>
				</div>
			</div>
			<!-- end voice command -->
	
			<!-- multiple lang dropdown : find all flags in the flags page -->
			<!-- <ul class="header-dropdown-list hidden-xs">
				<li>
					<a href="?language=en" class="dropdown-toggle" data-toggle="dropdown"> <img src="./resources/img/blank.gif" class="flag flag-us" alt="United States"> <span> English (US) </span> <i class="fa fa-angle-down"></i> </a>
					<ul class="dropdown-menu pull-right">
						<li class="active">
							<a href="?language=en"><img src="./resources/img/blank.gif" class="flag flag-us" alt="United States"> English (US)</a>
						</li>
						 <li>
							<a href="?language=hi"><img src="./resources/img/blank.gif" class="flag flag-in" alt="India"> Hindi</a>
						</li> 
						
						<li>
							<a href="?language=ka"><img src="./resources/img/blank.gif" class="flag flag-in" alt="India"> Kannada</a>
						</li> 
						
					</ul>
				</li>
			</ul> -->
			<!-- end multiple lang -->
			
			<!-- multiple lang dropdown : find all flags in the flags page -->
			<%-- <ul class="header-dropdown-list hidden-xs">
				<li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img src="./resources/Themes.png" width="25px;" alt=""> <span> ${theme} (Theme) </span> <i class="fa fa-angle-down"></i> </a>
					<ul class="dropdown-menu pull-right">
						<li class="tc-theme">
								<a data-value="default" onclick="changeGridThemes(this)" id="default" name="Default"
								class="tc-link" href="#"><span
									style="background-color: rgb(239, 111, 28);" class="tc-color"></span><span
									style="background-color: #e24b17" class="tc-color"></span><span
									style="background-color: #5a4b43" class="tc-color"></span><span
									style="background-color: #ededed" class="tc-color"></span><span
									class="tc-theme-name">Default</span></a></li>
							<li class="tc-theme">
								<a data-value="blueopal" onclick="changeGridThemes(this)" id="blueopal" name="Blue Opal"
								class="tc-link" href="#"><span
									style="background-color: #076186" class="tc-color"></span><span
									style="background-color: #7ed3f6" class="tc-color"></span><span
									style="background-color: #94c0d2" class="tc-color"></span><span
									style="background-color: #daecf4" class="tc-color"></span><span
									class="tc-theme-name">Blue Opal</span></a></li>
							<li class="tc-theme"><a data-value="bootstrap" onclick="changeGridThemes(this)" id="bootstrap" name="Bootstrap"
								class="tc-link" href="#"><span
									style="background-color: #3276b1" class="tc-color"></span><span
									style="background-color: #67afe9" class="tc-color"></span><span
									style="background-color: #ebebeb" class="tc-color"></span><span
									style="background-color: #ffffff" class="tc-color"></span><span
									class="tc-theme-name">Bootstrap</span></a></li>
							<li class="tc-theme"><a data-value="silver" onclick="changeGridThemes(this)" id="silver" name="Silver"
								class="tc-link active" href="#"><span
									style="background-color: #298bc8" class="tc-color"></span><span
									style="background-color: #515967" class="tc-color"></span><span
									style="background-color: #bfc6d0" class="tc-color"></span><span
									style="background-color: #eaeaec" class="tc-color"></span><span
									class="tc-theme-name">Silver</span></a></li>
							<li class="tc-theme"><a data-value="uniform" onclick="changeGridThemes(this)" id="uniform" name="Uniform"
								class="tc-link" href="#"><span
									style="background-color: #666666" class="tc-color"></span><span
									style="background-color: #cccccc" class="tc-color"></span><span
									style="background-color: #e7e7e7" class="tc-color"></span><span
									style="background-color: #ffffff" class="tc-color"></span><span
									class="tc-theme-name">Uniform</span></a></li>
							<li class="tc-theme"><a data-value="metro" class="tc-link" onclick="changeGridThemes(this)" id="metro" name="Metro"
								href="#"><span style="background-color: #8ebc00"
									class="tc-color"></span><span
									style="background-color: #787878" class="tc-color"></span><span
									style="background-color: #e5e5e5" class="tc-color"></span><span
									style="background-color: #ffffff" class="tc-color"></span><span
									class="tc-theme-name">Metro</span></a></li>
							<li class="tc-theme"><a data-value="black" class="tc-link" onclick="changeGridThemes(this)" id="black" name="Black"
								href="#"><span style="background-color: #0167cc"
									class="tc-color"></span><span
									style="background-color: #4698e9" class="tc-color"></span><span
									style="background-color: #272727" class="tc-color"></span><span
									style="background-color: #000000" class="tc-color"></span><span
									class="tc-theme-name">Black</span></a></li> 
							<li class="tc-theme"><a data-value="metroblack" onclick="changeGridThemes(this)" id="metroblack" name="Metro Black"
								class="tc-link" href="#"><span
									style="background-color: #00aba9" class="tc-color"></span><span
									style="background-color: #0e0e0e" class="tc-color"></span><span
									style="background-color: #333333" class="tc-color"></span><span
									style="background-color: #565656" class="tc-color"></span><span
									class="tc-theme-name">Metro Black</span></a></li>
							<li class="tc-theme"><a data-value="highcontrast" onclick="changeGridThemes(this)" id="highcontrast" name="High Contrast"
								class="tc-link" href="#"><span
									style="background-color: #b11e9c" class="tc-color"></span><span
									style="background-color: #880275" class="tc-color"></span><span
									style="background-color: #664e62" class="tc-color"></span><span
									style="background-color: #1b141a" class="tc-color"></span><span
									class="tc-theme-name">High Contrast</span></a></li>
							<li class="tc-theme"><a data-value="moonlight" onclick="changeGridThemes(this)" id="moonlight" name="Moonlight"
								class="tc-link" href="#"><span
									style="background-color: #ee9f05" class="tc-color"></span><span
									style="background-color: #40444f" class="tc-color"></span><span
									style="background-color: #2f3640" class="tc-color"></span><span
									style="background-color: #212a33" class="tc-color"></span><span
									class="tc-theme-name">Moonlight</span></a></li>
							<li class="tc-theme"><a data-value="flat" class="tc-link" onclick="changeGridThemes(this)" id="flat" name="Flat"
								href="#"><span style="background-color: #363940"
									class="tc-color"></span><span
									style="background-color: #2eb3a6" class="tc-color"></span><span
									style="background-color: #10c4b2" class="tc-color"></span><span
									style="background-color: #ffffff" class="tc-color"></span><span
									class="tc-theme-name">Flat</span></a></li>
					</ul>
				</li>
			</ul> --%>
			<!-- end multiple lang -->
			
		</div>
		<!-- end pulled right: nav area -->
	</header>
	<!-- END HEADER -->
	 <c:if test="${not empty userName}">
	 <c:if test="${empty navigation}" >
		<script>
			window.location.href="index";
		</script>
	</c:if> 
	</c:if>
</div>

<form id="themeForm" action="./changeGridTheme" >
		<input type="text" id="theme" name="theme" hidden="true"/>
		<input type="text" id="themeName" name="themeName" hidden="true"/>
</form>
	
<script> 

function changeGridThemes(themeName){
	$("#themeName").val(themeName.name);
	$("#theme").val(themeName.id);
	$("#themeForm").submit();
}

var ids1 = [];
function pendingForRegistrationNotifications(){
	
	$.ajax({
		type : "POST",
		url: "./header/getNotificationDataBasedOnStatus/"+3,
		dataType : "json",
		success : function(response) {
			var html="";
			var i=0;
			for ( var s = 0, len = response.length; s < len; ++s) {
              	var obj = response[s];

              	ids1[i]= obj.reqId;
          		i++;
          		html+="<div id='smallbox4'  data-content='<a href=./pendingForRegistrationUrl>click</a>'  ' class='class='SmallBox animated fadeInRight fast'style='background-color:#ddd; border: 1px solid #ddd;' style='border: 1px solid #ddd;width: 335px;margin-left: -12px;' >"
          			+" <div class='bar-holder no-padding bg-color-blueLight txt-color-white'onclick=./index>"
            		+"<p style='background-color: grey;'><i>&nbsp;Ticket with docket number "+obj.docketNumber+"&nbsp;&nbsp;has been resolved.</i> &nbsp;&nbsp;'<a href='./pendingForRegistrationUrl?docNum="+obj.docketNumber+"' style='color:khaki;font-size: small;'>Click here to see</a>'</p> "
           			+"  </div>"
            		+" <div class='miniIcono' >"
            		+" </div>"
         		    +"</div>";
			}
			
			$(".alert-transparent").html("");
			$(".alert-transparent").append(html);
			$(".ajax-loading-error").append(html);
			
		}
	});
	
	} 
	
function registeredAssignedNotifications(){
	$.ajax({
		type : "POST",
		url: "./header/getNotificationDataBasedOnStatus/"+1,
		dataType : "json",
		success : function(response) {
			var html="";
			var i=0;
			for ( var s = 0, len = response.length; s < len; ++s) {
              	var obj = response[s];

              	ids1[i]= obj.reqId;
          		i++;
          		html+="<div id='smallbox4'  data-content='<a href=./index>click</a>'  ' class='class='SmallBox animated fadeInRight fast'style='border: 1px solid #ddd;' style='border: 1px solid #ddd;width: 335px;margin-left: -12px;height:82px' >"
                    +" <div class='bar-holder no-padding bg-color-blueLight txt-color-white'onclick=./index>"
            		+"<p style='background-color: grey;'><i class='fa fa-user fa-fw fa-2x'></i><i>&nbsp;Complaint with docket number "+obj.docketNumber+"</i><br><i>&nbsp;&nbsp;has been assigned to you on "+obj.docketCreatedDt+".</i><br> <i>&nbsp;&nbsp;SLA Date for resolve it is "+obj.estResolveTime+".</i><br>&nbsp;&nbsp;'<a href='./getNotifyForAssignedDockets?escAssignDocNum="+obj.docketNumber+"' style='color:khaki;font-size: small;'>Click here to see</a>'</p> "
           			+"  </div>"
            		+" <div class='miniIcono' >"
            		+" </div>"
         		    +"</div>";
			}
			
			$(".alert-transparent").html("");
			$(".alert-transparent").append(html);
			$(".ajax-loading-error").append(html);
			
		}
	});
	
	} 
	
	function escalatedNotifications(){
		$.ajax({
			type : "POST",
			url: "./header/escalatedNotifications",
			dataType : "json",
			success : function(response) {
				var html="";
				var i=0;
				for ( var s = 0, len = response.length; s < len; ++s) {
	              	var obj = response[s];

	              	ids1[i]= obj.reqId;
	          		i++;
	          		html+="<div id='smallbox4'  data-content='<a href=./escalatedComplaintsUrl>click</a>'  ' class='class='SmallBox animated fadeInRight fast' style='width: 335px;margin-left: -12px;height:82px' >"
	                    +" <div class='bar-holder no-padding bg-color-blueLight txt-color-white'onclick=./escalatedComplaintsUrl>"
	            		+"<p style='background-color: grey;'><i class='fa fa-user fa-fw fa-2x'></i><i>&nbsp;Complaint with docket number "+obj.docketNumber+"</i><br><i>&nbsp;&nbsp;has been escalated on "+obj.escalatedDate+".</i><br>&nbsp;&nbsp;'<a href='./escalatedComplaintsUrl?escDocForCCNum="+obj.docketNumber+"' style='color:khaki;font-size: small;'>Click here to see</a>'</p> "
	           			+"  </div>"
	            		+" <div class='miniIcono' >"
	            		+" </div>"
	         		    +"</div>";
				}
				
				$(".alert-transparent").html("");
				$(".alert-transparent").append(html);
				$(".ajax-loading-error").append(html);
				
			}
		});
	}
	
	function showAllEscalatedNotificationsForCCCD(){
		$.ajax({
			type : "POST",
			url: "./header/showAllEscalatedNotificationsForCCCD",
			dataType : "json",
			success : function(response) {
				var html="";
				var i=0;
				for ( var s = 0, len = response.length; s < len; ++s) {
	              	var obj = response[s];

	              	ids1[i]= obj.reqId;
	          		i++;
	          		html+="<div id='smallbox4'  data-content='<a href=./escalatedComplaintsForCCCDUrl>click</a>'  ' class='class='SmallBox animated fadeInRight fast' style='width: 335px;margin-left: -12px;height:82px' >"
	                    +" <div class='bar-holder no-padding bg-color-blueLight txt-color-white'onclick=./escalatedComplaintsForCCCDUrl>"
	            		+"<p style='background-color: grey;'><i class='fa fa-user fa-fw fa-2x'></i><i>&nbsp;Ticket with docket number "+obj.docketNumber+"</i><br><i>&nbsp;&nbsp;has been escalated on "+obj.escalatedDate+".</i><br>&nbsp;&nbsp;'<a href='./escalatedComplaintsForCCCDUrl?escDocNum="+obj.docketNumber+"' style='color:khaki;font-size: small;'>Click here to see</a>'</p> "
	           			+"  </div>"
	            		+" <div class='miniIcono' >"
	            		+" </div>"
	         		    +"</div>";
				}
				
				$(".alert-transparent").html("");
				$(".alert-transparent").append(html);
				$(".ajax-loading-error").append(html);
				
			}
		});
	}

	
	
	function employeeUnseenNotifications(){
		
		$.ajax({
			type : "POST",
			url: "./header/showAllEmployeeNotifications",
			dataType : "json",
			success : function(response) {
				var html="";
				var i=0;
				for ( var s = 0, len = response.length; s < len; ++s) {
	              	var obj = response[s];

	              	ids1[i]= obj.id;
	          		i++;
	          		html+="<div id='smallbox4'  data-content='<a href=./ncmNotifaction>click</a>'  ' class='class='SmallBox animated fadeInRight fast' style='width: 335px;margin-left: -12px;height:82px' >"
	                    +" <div class='bar-holder no-padding bg-color-blueLight txt-color-white'onclick=./ncmNotifaction>"
	            		+"<p style='background-color: grey;'><i class='fa fa-user fa-fw fa-2x'></i>&nbsp; "+obj.subject+" notification<br>&nbsp;&nbsp;&nbsp;&nbsp; for ApplicationId - "+obj.applicationid+"&nbsp;'<a href='./ncmNotifaction' style='color:khaki;font-size: small;'>Click here to see</a>'</p> "
	           			+"  </div>"
	            		+" <div class='miniIcono' >"
	            		+" </div>"
	         		    +"</div>";
				}
				
				$(".alert-transparent").html("");
				$(".alert-transparent").append(html);
				$(".ajax-loading-error").append(html);
				
			}
		});
		
		/* window.location.href="./ncmNotifaction"; */
	}
/* function securityCheckForActions(url)
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
	
} */


// Added By raju For Notification------------------------



var auto = setInterval(function (){
	if('${userId}'!='null'){
		callBackFunction();
	}
},  1000*60*2);

function  callBackFunction() {
	
	var befrVal=$('#totCount').text();
		
		$.ajax({
			type : "POST",
			url : "./readNotifyEveryMinute",
			async: false,
			datatype:'json',
			success : function(response) 	{
				
				if(!isEmpty(response))
					{
				$('#totCount').html(response.totalCount);
				$('#notifyTab1').html(response.notificationName1+"("+response.notificationCount1+")");
				$('#pendigSpanId').html(response.reopenedCount);
				$('#notifyTab2').html(response.notificationName2+"("+response.notificationCount2+")");
				$('#escalationSpanId').html(response.notificationCount2);
				if(response.totalCount>befrVal)
					{
					newNotificatiob();
					}
				}
			}			
		});	
	
		
	}



function newNotificatiob() {


	$.smallBox({
		title : "You Got New Notification",
		content : "<i class='fa fa-clock-o'></i> <i>1 second ago...</i>",
		color : "#296191",
		iconSmall : "fa fa-thumbs-up bounce animated",
		timeout : 6000
	});
	
}

function isEmpty(obj) {
	  for (var o in obj) if (o) return false;
	  return true;
	}
</script>

<style>
.tc-color {
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(255, 255, 255, 0.1);
    border-style: solid;
    border-width: 0.5px;
    height: 10px;
    width: 10px;
}
.tc-theme .tc-link {
    border: 1px solid #FFFFFF;
    color: #4F4F4F;
}

.k-theme-chooser, .tc-theme, .tc-color, .tc-link, .tc-theme-name {
    display: inline-block;
    text-decoration: none;
}
.tc-theme-name {
    padding: 5px;
    width: auto;
}
.ajax-dropdown {
   
    width: 343px !important;
   
}
</style>
