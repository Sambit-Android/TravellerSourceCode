<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<script src="./resources/js/plugin/superbox/superbox.min.js"></script>
<script src="./resources/js/plugin/pace/pace.min.js"></script>
<script src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
<script src="./resources/js/notification/SmartNotification.min.js"></script>
<script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>
<script src="./resources/js/speech/voicecommand.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>


<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="content">
	<div class="row">
		<article class="col-sm-12 ">
			<div class="jarviswidget" id="wid-id-1"
				data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<h2>
						<span id="spanId">Selection Criteria</span>
					</h2>
				</header>
				
				
				<div style="border-style: solid; border-width: 1px; border-color: #E1D6D6;">
					<form:form id="register-form" action="" method="POST" class="smart-form" novalidate="novalidate">
						<div class="row">
							<section class="col col-2">
								<label class="label">Circle</label> <label class="select">
									<select id="circleSiteCode" name="circleSiteCode">
										<option value="" selected="" disabled="">Select
											Circle</option>
										<c:forEach items="${circleList}" var="circle">
											<option value="${circle.circleSiteCode}">${circle.circleName}</option>
										</c:forEach>
								</select><i></i>

								</label>
							</section>

							<section class="col col-3">
								<label class="label">Division</label> <label class="select">

									<select name="siteCode" id="siteCode">
										<option value="" selected="" disabled="">Select
											Division</option>
								</select> <i></i>
								</label>
							</section>

							<section class="col col-3">
								<label class="label">Sub Division</label> <label class="select">
									<select id="subId" name="subId">
										<option value="" selected="" disabled="">Select Sub
											Division</option>
								</select><i></i>
								</label>
							</section>

							<section class="col col-2">
								<label class="label"> From Date</label> <label class="input"><i
									class="icon-append fa fa-calendar"></i> <input type="text"
									name="startdate" id="startdate" placeholder="From Date">
								</label>
							</section>

							<section class="col col-2">
								<label class="label"> To Date</label> <label class="input"><i
									class="icon-append fa fa-calendar"></i> <input type="text"
									name="finishdate" id="finishdate" placeholder="To Date">
								</label>
							</section>
						</div>
					</form:form>
				</div>
			</div>
		</article>
	</div>


	<div class="row">
		<article class="col-sm-12">
			<!-- new widget -->
			<div class="jarviswidget" id="wid-id-0"
				data-widget-togglebutton="false" data-widget-editbutton="false"
				data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
				data-widget-deletebutton="false">

				<header>
					<span class="widget-icon">
					</span>
					<h2>Reports</h2>

					<ul class="nav nav-tabs pull-right in" id="myTab">
						<li class="active"><a data-toggle="tab" href="#s1"> <span
								class="hidden-mobile hidden-tablet">Detailed </span></a></li>
						 <li><a data-toggle="tab" href="#s2"><span
								class="hidden-mobile hidden-tablet">Analytical </span></a></li> 

					</ul>

				</header>

				<!-- widget edit box -->
				<div class="jarviswidget-editbox">
				</div>
			
				<div class="no-padding">
					<!-- widget edit box -->
					<div class="jarviswidget-editbox"></div>
					<!-- end widget edit box -->
					
					
					

					<div class="widget-body">
						<!-- content -->
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in padding-10 no-padding-bottom"
								id="s1">

								<div class="col-sm-12">
									<br>
									<div class="jarviswidget jarviswidget-color-blueDark"
										data-widget-editbutton="true" data-widget-colorbutton="true">

										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2>Detailed Reports</h2>
										</header>
										<!-- widget div-->
										<div>

											<!-- widget edit box -->
											<div class="jarviswidget-editbox">
												<!-- This area used as dropdown edit box -->

											</div>
											<!-- end widget edit box -->

											<!-- widget content -->
											<div class="widget-body no-padding">

												<table id="datatable_fixed_column"
													class="table table-striped table-bordered" width="100%">

													<tbody>
														
														<tr>
															<td>1. Applications Registered</td>
															<td><a href="#" onclick="return applicationsRegistered();"> View</a></td>
														</tr>

														<!-- <tr>
															<td>2. Applications Accepted</td>
															<td><a href="#"
																onclick="return applicationsAccepted();"> View</a></td>
														</tr> -->

														
														<tr>
															<td>2. Field Verification Completed</td>
															<td><a href="#"
																onclick="return fieldVerificationCompleted();"> View</a></td>
														</tr>


														<tr>
															<td>3. Estimation Completed</td>
															<td><a href="#"
																onclick="return estimationRequired();"> View</a></td>
														</tr>
														
														<tr>
															<td>4.Power Sanction Completed</td>
															<td><a href="#"
																onclick="return powerSanctionCompleted();"> View</a></td>
														</tr>


														<!-- <tr>
															<td>5.Applications Deposit Received</td>
															<td><a href="#"
																onclick="return applicationsDepositReceived();">
																	View</a></td>
														</tr> -->

														<tr>
															<td>5. Work Order Completed</td>
															<td><a href="#"
																onclick="return workOrderCompleted();"> View</a></td>
														</tr>
														<tr>
															<td>6. Meter/PC Test</td>
															<td><a href="#"
																onclick="return meterPurchaseOrderIssued();"> View</a></td>
														</tr>
														
														<tr>
															<td>7. Work Completion Completed</td>
															<td><a href="#"
																onclick="return workCompletion();"> View</a></td>
														</tr>

														<tr>
															<td>8.Installations Serviced</td>
															<td><a href="#"
																onclick="return installationsServiced();"> View</a></td>
														</tr>
														
														<tr>
															<td>9.Rejected Applications</td>
															<td><a href="#"
																onclick="return rejectedApplications();"> View</a></td>
														</tr>

													</tbody>

												</table>

											</div>
											<!-- end widget content -->

										</div>
										<!-- end widget div -->

									</div>
								</div>
							</div>




							<div class="tab-pane fade" id="s2">

								<div class="col-sm-12">
									<br>
									<div class="jarviswidget jarviswidget-color-blueDark"
										data-widget-editbutton="true" data-widget-colorbutton="true">



										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2>Analytical Reports</h2>
										</header>
										<div>
										
											<div class="jarviswidget-editbox"></div>
											<div class="widget-body no-padding">

												<table id="datatable_fixed_column"
													class="table table-striped table-bordered" width="100%">

													<tbody>

														
														<tr>
															<td>1.Application Status Wise(InProgress,Completed,OnHold)</td>
															<td><a href="#" onclick="return appStatusWiseReport();"> View</a></td>
														</tr>
														
														<tr>
															<td>2.Tariff Wise</td>
															<td><a href="#" onclick="return tariffWisePowerSanctionReport();"> View</a></td>
														</tr>
														
														<tr>
															<td>3.Phase Wise Application Report(Selected Month)</td>
															<td><a href="#" onclick="return stageWisePowerSanctionReport();"> View</a></td>
														</tr>
														
														<tr>
															<td>4.Phase Wise Application Report Circle Wise</td>
															<td><a href="#" onclick="return phaseWiseCircleReport();"> View</a></td>
														</tr>
														
														<tr>
															<td>5.Phase Wise Application Report Sub Division Wise</td>
															<td><a href="#" onclick="return phaseWiseSubDivisionReport();"> View</a></td>
														</tr>
														
														<tr>
															<td>5.Tariff Wise Registration to Installation Service</td>
															<td><a href="#" onclick="return tariffWiseRegToService();"> View</a></td>
														</tr>
														
														<!-- <tr>
															<td>2.Number of Applications completed within KERC
																time lines</td>
															<td><a href="#"> View</a></td>
														</tr> -->



														<!-- <tr>
															<td>3.Trend analysis of Applications</td>
															<td><a href="#"> View</a></td>
														</tr>


														<tr>
															<td>4.Status Wise Summary of Applications</td>
															<td><a href="#"> View</a></td>
														</tr> -->


													</tbody>

												</table>
											</div>
											

										</div>
										
									</div>
								</div>
							</div>

						</div>

					</div>
					<!-- end widget div -->
				</div>
				<!-- end widget -->
			</div>
		</article>
	</div>

</div>
<!-- END MAIN CONTENT -->







<script>




// onchange Drop Down For Division

	$('select[id*=circleSiteCode]').change(function() {
			var circleSiteCode = $("#circleSiteCode").val();
			$.ajax({
				type : "POST",
				url : "./helpDesk/getAllDivisions/" + circleSiteCode,
				dataType : "json",
				success : function(data) {
					var newOption = $('<option>');
	                newOption.attr('value',null).text(" "); 
	                $('#siteCode').empty(newOption);
	                var defaultOption = $('<option>');
	                defaultOption.attr('value',"0").text("Select Division");
	                $('#siteCode').append(defaultOption);
					($.map(data, function(item) {					 
						var newOption = $('<option>'); 					
						newOption.attr('value', item.siteCode).text(item.divisionName);
						$('#siteCode').append(newOption);
					}));
				}
			});
		});
	
	
	
	
	$('select[id*=siteCode]').change(function() {
		var siteCode = $("#siteCode").val();
		$.ajax({
			type : "POST",
			url : "./newConnection/getSubAllDivisions/" + siteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',null).text(" "); 
                $('#subId').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',"0").text("Select Sub Division");
                $('#subId').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.subId).text(item.subDivisionName);
					$('#subId').append(newOption);
				}));
			}
		});
	});

	
	




function applicationsRegistered() {
	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
	  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode=0;
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	 
	   commonSiteCode= circleSiteCode;
	   
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  
		   commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="applicationsRegistered";
	  window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	 
	  
	  
	  
	  
	 

}



function applicationsAccepted() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  
	  var reportStatus="applicationsAccepted";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}



function  fieldVerificationCompleted() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	  var reportStatus="fieldVerificationCompleted";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function powerSanctionCompleted() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="powerSanctionCompleted";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function applicationsDepositReceived() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="applicationsDepositReceived";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function workOrderCompleted() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
      
      
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="workOrderCompleted";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function meterPurchaseOrderIssued() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="meterPurchaseOrderIssued";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function installationsServiced() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
  	  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="installationsServiced";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}

function rejectedApplications(){
	 
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
 	  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="rejectedApplications";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}


function estimationRequired() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   
	   var DaysDiff=getDaysDifference(fromDate,toDate);
	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="estimationRequired";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}



function appStatusWiseReport() {

	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	   

	 
	   var reportStatus="appStatusWiseReport";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&commonSiteCode='+commonSiteCode;	   
	 

}


function tariffWisePowerSanctionReport() {
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
	        alert("Please select Circle");
	        return false;
	   }
	
	   commonSiteCode= circleSiteCode;
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	   var reportStatus="tariffWiseStatusReport";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&commonSiteCode='+commonSiteCode;	   
	 

}

function stageWisePowerSanctionReport(){
	
	  var fromDate=document.getElementById("startdate").value;
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  var subId=document.getElementById("subId").value;

	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
	        alert("Please select Circle");
	        return false;
	  }
	  
	  if (fromDate=="" || fromDate==null) {
	        alert("Please select From date");
	        return false;
	   }
	   commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	   var reportStatus="stageWiseStatusReport";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&commonSiteCode='+commonSiteCode;
	  
}


function phaseWiseCircleReport(){
	   var reportStatus="phaseWiseCircleReport";
	   window.location.href="./phaseWiseCountReport?reportStatus="+reportStatus;
	  
}

function phaseWiseSubDivisionReport(){
	   var reportStatus="phaseWiseSubDivisionReport";
	   window.location.href="./phaseWiseCountReport?reportStatus="+reportStatus;
	  
}

function tariffWiseRegToService(){
  var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  var subId=document.getElementById("subId").value;
	  
	  var fromDate=document.getElementById("startdate").value;
	  var toDate=document.getElementById("finishdate").value;
	  var commonSiteCode="";
	  if (circleSiteCode=="" || circleSiteCode==null) {
	        alert("Please select Circle");
	        return false;
	   }
	
	   commonSiteCode= circleSiteCode;
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	   
	   if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
		  
	   var reportStatus="tariffWiseRegToService";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	
}



function workCompletion() {

	
	  var fromDate=document.getElementById("startdate").value;
	  
	  var toDate=document.getElementById("finishdate").value;
	  
      var circleSiteCode=document.getElementById("circleSiteCode").value;
	  
	  var divisionsiteCode=document.getElementById("siteCode").value;
	  
	  var subId=document.getElementById("subId").value;
	  
	  var commonSiteCode="";
	  
	  if (circleSiteCode=="" || circleSiteCode==null) {
		   
	        alert("Please select Circle");
	        return false;
	   }
	  
	  if (fromDate=="" || fromDate==null) {
		   
	        alert("Please select From date");
	        return false;
	   }
	  
	  if (toDate=="" || toDate==null) {
		   
	        alert("Please select To date");
	        return false;
	   }
	  
	  commonSiteCode= circleSiteCode;
	  
	   if(divisionsiteCode!="" && divisionsiteCode!=null && divisionsiteCode!=0){
		  commonSiteCode= divisionsiteCode; 
	   }
	   if(subId!="" && subId!=null && subId!=0){
		  commonSiteCode=subId; 
	   }
	 
	 var DaysDiff=getDaysDifference(fromDate,toDate);
	 	  
	  if(DaysDiff > 31){
		 alert("Please select one month data");
		 return false;
	  }
	 
	  var reportStatus="workCompletion";
	   window.location.href="./statusWiseReport?reportStatus="+reportStatus+'&fromDate='+fromDate+'&toDate='+toDate+'&commonSiteCode='+commonSiteCode;	   
	 

}



function getDaysDifference(fromDate,toDate){
	 var monthyear=fromDate.split("/");					 
		var monlast="";
		var year="";
		var date="";
	    for(var i=0;i<=monthyear.length;i++){
	    	 date=monthyear[0];
	    	 monlast=monthyear[1];
	         year=monthyear[2];
	    	
	    }
	 var month=(parseFloat(monlast))-1;					   
	 var from=new Date(year,month,date);
	


	var monthyear1=toDate.split("/");					 
	var monlast1="";
	var year1="";
	var date1="";
 for(var i=0;i<=monthyear1.length;i++){
 	 date1=monthyear1[0];
 	 monlast1=monthyear1[1];
      year1=monthyear1[2];
 	
 }
var month1=(parseFloat(monlast1))-1;					   
var to=new Date(year1,month1,date1);


var d1 = new Date(from);
var d2 = new Date(to);
var timeDiff = d2.getTime() - d1.getTime();
var DaysDiff = timeDiff / (1000 * 3600 * 24);

return DaysDiff;
}


// START AND FINISH DATE
$('#startdate').datepicker({
	dateFormat : 'dd/mm/yy',
	maxDate:new Date(),
	prevText : '<i class="fa fa-chevron-left"></i>',
	nextText : '<i class="fa fa-chevron-right"></i>',
	onSelect : function(selectedDate) {
		$('#finishdate').datepicker('option', 'minDate', selectedDate);
	}
});

$('#finishdate').datepicker({
	dateFormat : 'dd/mm/yy',
	maxDate:new Date(),
	prevText : '<i class="fa fa-chevron-left"></i>',
	nextText : '<i class="fa fa-chevron-right"></i>',
	onSelect : function(selectedDate) {
		$('#startdate').datepicker('option', 'maxDate', selectedDate);
	}
});

</script>

