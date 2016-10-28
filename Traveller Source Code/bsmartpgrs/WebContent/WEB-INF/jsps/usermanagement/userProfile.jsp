<%@include file="/common/taglibs.jsp"%>
<!-- SPARKLINES -->
<!-- <script src="js/plugin/sparkline/jquery.sparkline.min.js"></script> -->

<c:url value="/raiseRequest/helpDesk/helpDeskTicketRead" var="readRaisedTickets" />
<c:url value="/feedback/read" var="readFeedBacks" />

<c:url value="/products/readCustomerProducts" var="readCustomerProducts" />

						<%
   							java.util.Date dNow = new java.util.Date( );
						    java.text.SimpleDateFormat ft = new java.text.SimpleDateFormat ("dd-MM-yyyy");
						%>

			<div id="content">

				<!-- Bread crumb is created dynamically -->
				<!-- row -->
				<div class="row">
				
					<!-- col -->
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark"><i class="fa fa-lg fa-fw fa-user"></i>${usersDetails.userName}'s Profile</h1>						
					</div>
					
					<%-- <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
						<!-- sparks -->
						<ul id="sparks">
							<li class="sparks-info">
								<h5> Total Tickets <span class="glyphicon glyphicon-file">&nbsp;${totalTickets}</span></h5>
							</li>
							<li class="sparks-info">
								<h5> Open Tickets <span class="glyphicon glyphicon-check">&nbsp;${openTickets}</span></h5>
							</li>
							<li class="sparks-info">
								<h5> Assigned Tickets <span class="glyphicon glyphicon-paperclip">&nbsp;${assignedTickets}</span></h5>								
							</li>
							<li class="sparks-info">
								<h5> Closed Tickets <span class="glyphicon glyphicon-compressed">&nbsp;${closedTickets}</span></h5>								
							</li>
							<li class="sparks-info">
								<h5> Re-open Tickets <span class="glyphicon glyphicon-export">&nbsp;${reOpenTickets}</span></h5>								
							</li>
						</ul>
						<!-- end sparks -->
					</div> --%>
					
					<!-- <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">						
					</div> -->
				</div>				
				<div class="row">				
				  <div class="col-sm-12">	
				   <div class="well well-sm">
					 <div class="row">
				  	   <div class="col-sm-12 col-md-12 col-lg-6">
						 <div class="well well-light well-sm no-margin no-padding">
							<div class="row">
				   			 <div class="col-sm-12">
													<div id="myCarousel" class="carousel fade profile-carousel">
														<!-- <div class="air air-bottom-right padding-10">
															<a href="javascript:void(0);" class="btn txt-color-white bg-color-teal btn-sm"><i class="fa fa-check"></i> Follow</a>&nbsp; <a href="javascript:void(0);" class="btn txt-color-white bg-color-pinkDark btn-sm"><i class="fa fa-link"></i> Connect</a>
														</div> -->
														<div class="air air-top-left padding-10">														
															<h4 class="txt-color-white font-md"><%=ft.format(dNow)%></h4>
														</div>
														<ol class="carousel-indicators">
															<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
															<li data-target="#myCarousel" data-slide-to="1" class=""></li>
															<li data-target="#myCarousel" data-slide-to="2" class=""></li>
														</ol>
														<div class="carousel-inner">
															<!-- Slide 1 -->
															<div class="item active">
																<img src="./resources/img/demo/s1.jpg" alt="demo user">
															</div>
															<!-- Slide 2 -->
															<div class="item">
																<img src="./resources/img/demo/s2.jpg" alt="demo user">
															</div>
															<!-- Slide 3 -->
															<div class="item">
																<img src="./resources/img/demo/s3.jpg" alt="demo user">
															</div>
														</div>
													</div>
												</div>
				
												<div class="col-sm-12">
				
													<div class="row">
				
														<div class="col-sm-3 profile-pic" style="cursor:pointer;">
															<!-- <img src="./getprofileimage" style="height: 127px;margin-top: 30px;" alt="Click to upload" onclick="onImageClick()"> -->
															<div class="padding-10">
																<!-- <h4 class="font-md"><strong>1,543</strong>
																<br>
																<small>Followers</small></h4>
																<br>
																<h4 class="font-md"><strong>419</strong>
																<br>
																<small>Connections</small></h4> -->
															</div>
														</div>
														<div class="col-sm-6">
															<h1>${usersDetails.userName}
															<br>
															<small> </small></h1>
				
															<ul class="list-unstyled">
																<li>
																	<p class="text-muted">
																		<i class="fa fa-phone"></i>&nbsp;&nbsp;(<span class="txt-color-darken">${usersDetails.contactNo}</span>)
																	</p>
																</li>
																<li>
																	<p class="text-muted">
																		<i class="fa fa-envelope"></i>&nbsp;&nbsp;<a href="mailto:simmons@smartadmin">${usersDetails.emailId}</a>
																	</p>
																</li>
																<li hidden="true">
																	<p class="text-muted">
																		<i class="fa fa-calendar"></i>&nbsp;&nbsp;<span class="txt-color-darken">User Id <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment">${usersDetails.userId}</a></span>
																	</p>
																</li>
																<%-- <li>
																	<p class="text-muted">
																		<i class="fa fa-skype"></i>&nbsp;&nbsp;<span class="txt-color-darken">${emailId}</span>
																	</p>
																</li> --%>
																<%-- <li>
																	<p class="text-muted">
																		<i class="fa fa-calendar"></i>&nbsp;&nbsp;<span class="txt-color-darken">Birthday <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment">${dob}</a></span>
																	</p>
																</li> --%>
															</ul>
															<br>
															<p class="font-md">
																<i>Address</i>
															</p>
															<p>${usersDetails.address}</p>
															<br>
															<!-- <a href="javascript:void(0);" class="btn btn-default btn-xs"><i class="fa fa-envelope-o"></i> Send Message</a> -->
																			
														</div>
														<div class="col-sm-3">
															<h1><small></small></h1>
															<ul class="list-inline friends-list">
																<!-- <li><img src="img/avatars/1.png" alt="friend-1">
																</li>
																<li><img src="img/avatars/2.png" alt="friend-2">
																</li>
																<li><img src="img/avatars/3.png" alt="friend-3">
																</li>
																<li><img src="img/avatars/4.png" alt="friend-4">
																</li>
																<li><img src="img/avatars/5.png" alt="friend-5">
																</li>
																<li><img src="img/avatars/male.png" alt="friend-6">
																</li>
																<li>
																	<a href="javascript:void(0);">413 more</a>
																</li> -->
															</ul>
				
															<%-- <h1><i class="fa fa-rss text-success">&nbsp;&nbsp;<small>Products</small></i></h1>
															<ul class="list-inline friends-list">
																<li><b>Products </b>- ${productsCount}</li><br>
																<li><b>Item </b>-${itemNameCount}</li>
															</ul> --%>
														</div>
													</div>
												</div>
											</div>
				
											<div class="row">
				
												<div class="col-sm-12">
				
													<hr>
				
													<div class="padding-10">
				
														
				
													</div>
				
												</div>
				
											</div>
											<!-- end row -->
				
										</div>
				
									</div>
									<div class="col-sm-12 col-md-12 col-lg-6">
				                      <hr class="simple">
										<ul id="myTab1" class="nav nav-tabs bordered">
											<li class="active">											
												<a href="#s1" data-toggle="tab"><i class="fa fa-fw fa-lg fa-user">&nbsp;&nbsp;&nbsp;</i>Personal Information</a>
											</li>
											<li>
												<a href="#s2" data-toggle="tab"><i class="fa fa-fw fa-lg fa-gear"></i> Change Password</a>
											</li>
											<!-- <li>
												<a href="#s3" data-toggle="tab"><i class="fa fa-rss text-success"></i> Tickets</a>
											</li> -->
											<!-- <li>
												<a href="#s3" data-toggle="tab"><i class="glyphicon glyphicon-link"></i> Recent Pictures</a>
											</li> -->										
										</ul>
				
										<div id="myTabContent1" class="tab-content padding-10" style="border-style:ridge;">
										  <div class="tab-pane fade in active" id="s1">										  
											<form id="checkout-form" class="smart-form" novalidate="novalidate">
												<fieldset>
													<div class="row">
														<section class="col col-6">
														    <input type="hidden" name="userId" value="${usersDetails.userId}" placeholder="First name">
															<label class="input"> <i class="icon-prepend fa fa-user"></i>
																<input type="text" name="userName" value="${usersDetails.userName}" placeholder="User Name">
																<b class="tooltip tooltip-bottom-right">Person Name</b>
															</label>
														</section>
														<section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-user"></i>
																<input type="text" name="urLoginName" value="${usersDetails.urLoginName}" placeholder="Login name" readonly="readonly">
																<b class="tooltip tooltip-bottom-right">User Login Name</b>
															</label>
														</section>
													</div>
					
													<div class="row">
														<section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
																<input type="email" name="emailId" value="${usersDetails.emailId}" placeholder="E-mail" readonly="readonly">
																<b class="tooltip tooltip-bottom-right">Email Id</b>
															</label>
														</section>
														<section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-phone"></i>
																<input type="tel" name="contactNo" value="${usersDetails.contactNo}" placeholder="Mobile Number" data-mask="(999) 999-9999" onkeypress="return isNumber(event)">
																<b class="tooltip tooltip-bottom-right">Mobile No.</b>
															</label>
														</section>
													</div>
													
													<div class="row">
														<section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-paw"></i>
																<input type="text" name="department" value="${usersDetails.deptName}" placeholder="Department" readonly="readonly">
																<b class="tooltip tooltip-bottom-right">Department Name</b>
															</label>
														</section>
														<section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-graduation-cap"></i>
																<input type="text" name="degnation" value="${usersDetails.desgName}" placeholder="Designation" readonly="readonly">
																<b class="tooltip tooltip-bottom-right">Designation Name</b>
															</label>
														</section>	
													</div>
													
													<div class="row">
														<%-- <section class="col col-6">
															<label class="input"> <i class="icon-prepend fa fa-globe"></i>
																<input type="text" name="office" value="${usersDetails.officeName}" placeholder="Office Name" readonly="readonly">
															</label>
														</section>	 --%>
														
														<br><br><br>													
														<a class="btn bg-color-blueLight" onclick="onSubmitClick()" style="width: 145px;height: 37px;margin-left: 224px">
														  <strong><i class="fa fa-arrow-circle-right" style="margin-top: 10px"></i>Update Details</strong>
														</a>	
																											
													</div>
												</fieldset>
							                </form>
											</div>
											<div class="tab-pane fade" id="s2">
											  <form id="checkout-form" class="smart-form" novalidate="novalidate">
											   <fieldset>												 
												 <div class="row" style="margin-left: 90px;">
												   <section class="col col-6">
												   <input type="hidden" name="userPwd" value="${userPwd}" placeholder="Date of birth">
												   <input type="hidden" name="emailId" value="${emailId}">
												    <label class="label">Enter old Password</label>													  
													  <label class="input">
													    <i class="icon-append fa fa-lock"></i>
														  <input id="password" name="oldPwd" type="password">
														<b class="tooltip tooltip-bottom-right">Your Old password</b>
													  </label>
												   </section>
											     </div>
											     
											     <div class="row" style="margin-left: 90px;">
												   <section class="col col-6">
												    <label class="label">Enter New Password</label>													  
													  <label class="input">
													    <i class="icon-append fa fa-lock"></i>
														  <input id="password" name="newPwd1" type="password">
														<b class="tooltip tooltip-bottom-right">Enter New password</b>
													  </label>
												   </section>
											     </div>
											     
											     <div class="row" style="margin-left: 90px;">
												   <section class="col col-6">
												    <label class="label">Re-Enter New Password</label>													  
													  <label class="input">
													    <i class="icon-append fa fa-lock"></i>
														  <input id="password" name="newPwd2" type="password">
														<b class="tooltip tooltip-bottom-right">Re-Enter New password</b>
													  </label>
												   </section>
												   <br><br><br><br><br>
												   <a class="btn bg-color-blueLight" onclick="onPasswordUpdateClick()" style="width: 145px;height: 31px;margin-left: 30px">
													  <strong><i class="fa fa-arrow-circle-right" style="margin-top: 10px"></i>Update Password</strong>
												   </a>
											     </div>											     
											   </fieldset>
											  </form>
											  </div>
<%-- 											<div class="tab-pane fade" id="s3">
											<kendo:tabStrip name="tabStrip">
											 <kendo:tabStrip-items>											
											  <kendo:tabStrip-item text="Raised Tickets" selected="true">
											   <kendo:grid name="raisedTicketsGrid" pageable="true" resizable="true" filterable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" groupable="true">
											     <kendo:grid-toolbar>
			                                       <kendo:grid-toolbarItem text="Clear Filter" name="Clear_Filter"/>
											     </kendo:grid-toolbar>
											    
										        <kendo:grid-columns>
		                                          <kendo:grid-column title="Ticket No." field="ticketNumber" width="120px"/>
		                                          <kendo:grid-column title="Complaint Type" field="complaintType" width="145px"/>
		                                          <kendo:grid-column title="Subject" field="issueSubject" width="125px"/>
		                                          <kendo:grid-column title="Status" field="status" width="110px" template="#:status == 0 ? 'Open' : status == 1 ? 'Assigned' :status == 2 ? 'Closed' : 'Re-Open'#"/>		 
		                                        </kendo:grid-columns>
		                                       
		                                        <kendo:dataSource pageSize="5">
												 <kendo:dataSource-transport>
												   <kendo:dataSource-transport-read url="${readRaisedTickets}" dataType="json" type="GET" contentType="application/json" />
												 </kendo:dataSource-transport>														
												 <kendo:dataSource-schema>
												   <kendo:dataSource-schema-model id="ticketId">
													 <kendo:dataSource-schema-model-fields>
													   <kendo:dataSource-schema-model-field name="ticketId" type="number"/>
													   <kendo:dataSource-schema-model-field name="ticketNumber"/>
													   <kendo:dataSource-schema-model-field name="complaintType"/>
													   <kendo:dataSource-schema-model-field name="issueSubject"/>
													   <kendo:dataSource-schema-model-field name="status" type="number"/>													   
													   </kendo:dataSource-schema-model-fields>
												   </kendo:dataSource-schema-model>
												 </kendo:dataSource-schema>
											   </kendo:dataSource>		                                       
										       </kendo:grid>
											 </kendo:tabStrip-item>
											 
											 <kendo:tabStrip-item text="FeedBack">
										        <kendo:grid name="ticketsFeedbackGrid" pageable="true" resizable="true" filterable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" groupable="true">
											      <kendo:grid-toolbar>
			                                        <kendo:grid-toolbarItem text="Clear Filter" name="Clear_Filter"/>
											      </kendo:grid-toolbar>
											      											    
										          <kendo:grid-columns>
		                                            <kendo:grid-column title="Feedback About" field="feedbackAbout" width="120px"/>
		                                            <kendo:grid-column title="Product Name" field="productName" width="145px"/>
		                                            <kendo:grid-column title="Subject" field="subject" width="125px"/>
		                                            <kendo:grid-column title="Comment" field="feedback" width="110px"/>		 
		                                          </kendo:grid-columns>
		                                          
		                                           <kendo:dataSource pageSize="5">
												 <kendo:dataSource-transport>
												   <kendo:dataSource-transport-read url="${readFeedBacks}" dataType="json" type="GET" contentType="application/json" />
												 </kendo:dataSource-transport>														
												 <kendo:dataSource-schema>
												   <kendo:dataSource-schema-model id="feedbackId">
													 <kendo:dataSource-schema-model-fields>
													   <kendo:dataSource-schema-model-field name="feedbackId" type="number"/>
													   <kendo:dataSource-schema-model-field name="feedbackAbout"/>
													   <kendo:dataSource-schema-model-field name="productName"/>
													   <kendo:dataSource-schema-model-field name="subject"/>
													   <kendo:dataSource-schema-model-field name="feedback"/>													   
													   </kendo:dataSource-schema-model-fields>
												   </kendo:dataSource-schema-model>
												 </kendo:dataSource-schema>
											   </kendo:dataSource>		                                          
		                                       </kendo:grid>
											 </kendo:tabStrip-item>											  											  
											</kendo:tabStrip-items>
										  </kendo:tabStrip>
										</div> --%>
										</div>
										<div>										
										</div>
										<br>
<%-- 										<div id="productsDiv">										
										  <kendo:grid name="productsGrid" pageable="true" resizable="true" filterable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" groupable="true">
										  <kendo:grid-groupable>
										     <kendo:grid-groupable-messages empty="My Products"/>
										   </kendo:grid-groupable>
										    <kendo:grid-columns>
		                                      <kendo:grid-column title="Sl.No" field="slNo" width="60px"/>
		                                      <kendo:grid-column title="Part No" field="partNo" width="60px"/>
		                                      <kendo:grid-column title="Item Group" field="imGroup" width="60px"/>
		                                      <kendo:grid-column title="Item Name" field="imName" width="60px"/>
		                                      <kendo:grid-column title="Warranty" field="warranty" width="60px" template="#:status == 0 ? 'Open' : status == 1 ? 'Assigned' :status == 2 ? 'Closed' : 'Re-Open'#"/>		 
		                                    </kendo:grid-columns>
		                                    
		                                    <kendo:dataSource pageSize="5">
										      <kendo:dataSource-transport>
												<kendo:dataSource-transport-read url="${readCustomerProducts}" dataType="json" type="POST" contentType="application/json" />
											  </kendo:dataSource-transport>														
											  <kendo:dataSource-schema>
												<kendo:dataSource-schema-model id="pid">
												 <kendo:dataSource-schema-model-fields>												   
												   <kendo:dataSource-schema-model-field name="pid" type="number"/>
												   <kendo:dataSource-schema-model-field name="slNo"/>
												   <kendo:dataSource-schema-model-field name="partNo"/>
												   <kendo:dataSource-schema-model-field name="imGroup"/>
												   <kendo:dataSource-schema-model-field name="imName"/>													   
												   <kendo:dataSource-schema-model-field name="warranty" type="date"/>													   
												 </kendo:dataSource-schema-model-fields>
											   </kendo:dataSource-schema-model>
												 </kendo:dataSource-schema>
											   </kendo:dataSource>	
										  </kendo:grid>
								       </div> --%>										
								   </div>								   
								</div>
							</div>				
					</div>				
				</div>				
				<!-- end row -->
			</div>
			<!-- END MAIN CONTENT -->

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
				<li>
					<a href="#invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
				</li>
				<li>
					<a href="#gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
				</li>
				<li>
					<a href="javascript:void(0);" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
				</li>
			</ul>
		</div>
		<!-- END SHORTCUT AREA -->

		<!--================================================== -->
		<div id="alertsBox" title="Alert"></div>
		
		<%-- <div id="imageUpload" style="display:none;">
		  <kendo:upload complete="oncomplete" name="files" upload="onpersonImageUpload" multiple="false" success="onImageSuccess">
			 <kendo:upload-async autoUpload="true" saveUrl="./person/upload/personImage/${usersDetails.userId}" />
	      </kendo:upload>
		</div> --%>
<script>
function onImageClick()
{
	 $('#imageUpload').dialog({
			modal : true,
			title : "Upload Image",
	 }); 
	 return false;
}		
function oncomplete() 
{
	 
}
function onpersonImageUpload() 
{
	 /* var files = $('input[name="files"]').val();
	 $.each(files, function() {
		 if (this.extension.toLowerCase() == ".png"){}
		 else if (this.extension.toLowerCase() == ".jpg"){}
		 else if (this.extension.toLowerCase() == ".jpeg"){}
		 else
		 {
			alert("Only Images can be uploaded\nAcceptable formats are png, jpg and jpeg");
			preventDefault();
			return false;
		 }
	 }); */  
}
function onImageSuccess()
{
	 alert("Uploaded Successfully");
	 $(".k-upload-files.k-reset").find("li").remove();
	 window.location.reload(); 
}
</script>
    <script>
         function isNumber(evt)
         {
        	 evt = (evt) ? evt : window.event;
        	 var charCode = (evt.which) ? evt.which : evt.keyCode;
        	 if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        	    return false;
        	 }
        	 return true; 
         }    
		 function onSubmitClick()
		 {
			 var userId = $('input[name="userId"]').val();
			 var userName = $('input[name="userName"]').val();
			 var contactNo = $('input[name="contactNo"]').val();
			 var emailId = $('input[name="emailId"]').val();
			 var urLoginName = $('input[name="urLoginName"]').val();
			 
			 if(userName=="")
			 {
				 alert("Enter User name");
				 return false;
			 }			
			 else if(contactNo=="")
			 {
				 alert("Enter Contact Number");
				 return false;
			 }
			 else if(contactNo.length!=10)
			 {
				 alert("Enter 10 digit mobile number");
				 return false;
			 }
			 else{
			 	 $.ajax({
				 type : "POST",
				 datatype : "text",
				 url : "./users/updateUserDetails",
				 data :
				 {
					userId : userId,
					userName : userName,
					contactNo : contactNo, 
					emailId : emailId,
					urLoginName : urLoginName
				 },
			     success : function(response)
			     {
					 alert(response);
					 window.location.reload();
			     }				 
			 });
			 }
		 }
		 
		 function onPasswordUpdateClick()
		 {
			var userId = $('input[name="userId"]').val();
			var urLoginName = $('input[name="urLoginName"]').val();
			var contactNo = $('input[name="contactNo"]').val();
			var emailId = $('input[name="emailId"]').val();
			var oldPwd = $('input[name="oldPwd"]').val();
			var newPwd = $('input[name="newPwd1"]').val();
			var rePwd = $('input[name="newPwd2"]').val();
			if(oldPwd == "")
			{
			   alert("Enter Old Password");	
			}
			else if(newPwd == "")
			{
				alert("Enter New Password");
			}
			else if(oldPwd==newPwd){
				alert("New Password cannot be same as Old Password");
			}
			else if(rePwd == "")
			{
				alert("Re-Enter New Password");
			}
			else if(newPwd.length<5){
				alert("New Password requires min of 5 characters");
			}
			else if(newPwd.length>15){
				alert("New Password requires max of 15 characters");
			}
			else if(newPwd!=rePwd){
				alert("New password and Re-Entered doesn't match");
			}
			else
			{
				$.ajax
				({
					 type : "POST",
					 datatype : "text",
					 url : "./users/updateChangedUserPassword",
					 data :
					 {
						userId : userId,
						newPwd : newPwd,
						oldPwd : oldPwd,
						urLoginName : urLoginName,
						contactNo : contactNo,
						emailId : emailId
					 },
				     success : function(response)
				     {
				    	 if(response.indexOf("true") > -1){
				    		 alert(response.replace("true",""));
					    	 window.location.href="./logout";
				    	 }else{
				    		 alert(response.replace("false",""));
				    	 }
				    	 
				     }				 
				 });
			}				
		 }		 
		 
		 function test()
		 {
			 window.open("./logout");
		 }
		 
		</script>		