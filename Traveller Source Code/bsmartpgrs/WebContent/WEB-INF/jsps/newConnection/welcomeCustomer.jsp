<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>

<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>    
<script src="./resources/js/bootstrap/bootstrap.min.js"></script>
<script src="./resources/js/plugin/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="./resources/js/plugin/fuelux/wizard/wizard.min.js"></script>
<script src="./resources/js/plugin/dropzone/dropzone.min.js"></script>

<div id="content">
<section id="widget-grid" class="">
            <c:if test="${not empty applicationResult}">
				<script>
					var result = "${applicationResult}";
					alert(result);
				</script>
						
			   <c:remove var="applicationResult" scope="session" />
		   </c:if>
		   
		
	  <div class="row">
		<div class="col-sm-12">
	     <div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-73" data-widget-editbutton="false" data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>Application Registration Process</h2>
					</header>

					<div>

						<div class="jarviswidget-editbox"></div>
						<div class="widget-body">

							<div class="row">
								<form:form action="" method="post"  commandName="application" modelAttribute="application" enctype="multipart/form-data" id="wizard-1"  novalidate="novalidate" autocomplete="off">

									<div id="bootstrap-wizard-1" class="col-sm-12">
										<div class="form-bootstrapWizard">
											<ul class="bootstrapWizard form-wizard">
												<li class="active" data-target="#step1" id="tab11"><a
													href="#tab1" data-toggle="tab"> <span class="step">1</span>
														<span class="title">Download Application</span>
												</a></li>
												<li data-target="#step2" id="tab22"><a href="#tab2"
													data-toggle="tab"> <span class="step">2</span> <span
														class="title">Nature Of Installation</span>
												</a></li>
												<li data-target="#step3" id="tab33"><a href="#tab3"
													data-toggle="tab"> <span class="step">3</span> <span
														class="title">General Particulars </span>
												</a></li>
												<li data-target="#step4" id="tab44"><a href="#tab4"
													data-toggle="tab"> <span class="step">4</span> <span
														class="title">Permanent Address</span>
												</a></li>


												<li data-target="#step5" id="tab55"><a href="#tab5"
													data-toggle="tab"> <span class="step">5</span> <span
														class="title">Connection & Account Details</span>
												</a></li>
												
												
												
												<li data-target="#step6" id="tab66"><a href="#tab6"
													data-toggle="tab"> <span class="step">6</span> <span
														class="title">Additional Details</span>
												</a></li>
												
												<li data-target="#step7" id="tab77"><a href="#tab7"
													data-toggle="tab"> <span class="step">7</span> <span
														class="title">Upload Documents and Save Application</span>
												</a></li>



												
											</ul>

											<div class="clearfix"></div>
										</div>
										<div class="tab-content">
											<div class="tab-pane active" id="tab1">
												<br>
												<h6>
													<strong>Step 1 </strong> - Download the Application Form
												</h6>

												
												
												
											1) Download the Application Form <a href="./downloadDocForApplicationRegistration/download" target="_blank">Click here to Download the Application Form</a>	<br>
											2) Fill the application details <br>
											3) Register the application (Click on Register Application Menu )

											
												
												
												
											</div>
											<!--END OF TAB1  -->
											<div class="tab-pane" id="tab2">
											<br>
												<h6>
													<strong>Step 2 </strong> - Nature Of Installation
												</h6>

												
												
											
											
												1) Select the section to which the proposed site belongs where the connection is required. <br>
												2) Select the locality that Proposed Installation belongs to  (CMC And URBAN local bodies OR Village Panchayat)<br>
												3) Select your Nature of installation (Ref the document to select Nature of installation)<br>
												 <a href="./citizentariffdetails/download" target="_blank">Click here to Download the document to know your specific installation Nature</a>
												





												
											</div>
											<!--END OF TAB 2  -->

											<div class="tab-pane" id="tab3">
											    <br>
												<h6>
													<strong>Step 3 </strong> - General Particulars
												</h6>

											
											
												 	Select Applicant Type (Individual OR Organization)<br>
												 	Enter  Name ,Father/Husband Name and Nominee Name (In case of Individual) <br>
													Enter  Name Of Organization  ,Authorized Signatory Name  and Designation  (In Organisation) <br>
																								 
												 	Enter Address Details Power Supply is required like <br>
													House/Flat No ,Floor No,Street Name 
													Area Name,Cross ,Main 
													City ,Pincode,Phone,Mobile No,Landmark ,E-Mail<br>
												
												

											</div>


											<div class="tab-pane" id="tab4">
											    <br>
												<h6>
													<strong>Step 4 </strong> - Permanent Address
												</h6>
												
												
												 	Enter Permanent Address Details like <br>
													House/Flat No ,Floor No,Street Name,Area Name,Cross ,Main,City ,Pincode,Phone,Mobile No,Landmark ,E-Mail<br>




											</div>




											<div class="tab-pane" id="tab5">
												
											    <br>
												<h6>
													<strong>Step 5 </strong> - Connection & Account Details
												</h6>
												
											
											
											Enter Connection & Account Details  like <br>
											 Desired Load (kW/HP/KVA)<br>
											 Purpose (Lighting OR Heating)<br>
											
											 Phase Requirement (I OR III Phase)<br>
											 Power Required For (Residential ,Commercial,Industrial etc...)<br>
											 If its Govt Inst Select (Central or State)<br>
											 If Public Street/Park Light Select (Self/Rental/Public Use)
												
											</div>
										 
										 
										 
										    <div class="tab-pane" id="tab6">
												
											
												<br>
												<h6>
													<strong>Step 6 </strong> - Additional Details 
												</h6>
											
											
											
											If Special Govt Scheme Select the Scheme (BJ/KJ/RGGY/GANGA KALYAN)<br>
											Select Type of Ownership(Individual/Leased/Rental/Co-Owned etc...)<br>
											If IP set Select Water Source (Open Well/Bore well etc...)
											Enter the Nearest RR Number and Account ID of the connection where connection already exists (At least One Detail)
												
												
												
															
											</div>
										  
										  
										   <div class="tab-pane" id="tab7">
											  <br>
											 	<h6>
													<strong>Step 7 </strong> - Upload Documents and Save Application
												</h6>
												
											
											
											
											Upload Required documents for New Connection<br>
											<a href="./downloadRequiredDocList/download" target="_blank">Click here to Download Required Documents list for New Connection.</a><br>
											Save the application<br>
											After saving the application you will get unique Application Id and this will be used for tracking the application status.	
											<br>
											<font color="red"> Note - For Multiple Connection,MS Building and Layout click on Track Application after submitting the Application then click on Application Id
											<br>
											and Add Multiple Connections for MS Building & Multiple Connection and Add Multiple Sites for Layout before ARF Payment Entry.
											</font>
															
											</div>
														
											
										</div>
									</div>
								</form:form>
							</div>

						</div>
						<!-- end widget content -->

					</div>
					<!-- end widget div -->

				</div>
	    
	    
	    
	    
	    
	    
	    
	    
	    <c:if test = "${not empty unseenNotificationsByAppId}">   
		  <div class="col-sm-100"> 
			<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id1-33" data-widget-editbutton="false" data-widget-colorbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Latest Notifications</h2>
				
								</header>
								<div>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column1"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>Application&nbsp;ID</th>
											<th>Sent On</th>
											<th>Subject</th>
											<th>Notification</th>
											<th>Sent Via</th>
											<th>Mobile/Email</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="app" items="${unseenNotificationsByAppId}">
												<tr onclick="notificationUpdateStatus(${app.id})" title="Click here to hide this Notification" style="cursor:pointer;">
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b>${app.applicationid}</b></td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b><fmt:formatDate value="${app.senton}" pattern="dd-MM-yyyy" /></b></td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b>${app.subject}</b></td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b>${app.message}</b></td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b>
													<c:if test="${app.nottificationtype=='E'}">E-Mail</c:if>
													<c:if test="${app.nottificationtype=='S'}">SMS</c:if>
													</b>
												</td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><b>
													<c:if test="${app.nottificationtype=='E'}">${app.email}</c:if>
													<c:if test="${app.nottificationtype=='S'}">${app.mobileno}</c:if></b>
												</td>
												
												</tr>
											
										</c:forEach>
									</tbody>

								</table>
				
									</div>
				
								</div>
							</div>
							
				</div>
		
	    </c:if>
	
		<c:if test = "${not empty applicationDetailsByLogin}"> 			        
		     
		  <div class="col-sm-100"> 
			<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-182" data-widget-editbutton="false" data-widget-colorbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered Applications </h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column" class="table table-striped table-bordered">
					
									        <thead>
									            <tr>
								                    <th>Application&nbsp;Id</th>
								                    <th >Status</th>
								                    <th>Reg.&nbsp;Date&nbsp;&nbsp;&nbsp;</th>
								                    <th>Elapsed Days</th>
								                    <th data-hide="phone">Name</th>
								                    <th data-hide="phone">City</th>
								                    <th data-hide="phone,tablet">Load(KW)</th>
								                    <th data-hide="phone,tablet">Load(HP)</th>
								                    <th data-hide="phone,tablet">Load(KVA)</th>								                    
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">App&nbsp;Type</th>
								                    <th data-hide="phone,tablet">Nature&nbsp;Of&nbsp;Inst.</th>
								                    <th data-hide="phone,tablet">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Locality&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								                    <th data-hide="phone,tablet">Location</th>
								                   
									            </tr>
									        </thead>
				
									        <tbody>
									        <c:forEach var="app" items="${applicationDetailsByLogin}">
									            <tr>
									                <td><a href='#' onclick="trackApplication(${app.applicationid})" rel='tooltip' data-placement='right' data-original-title='Click here to Track Status of this Application'><b>${app.applicationid}</b></a></td>
									                <c:choose>
									                <c:when test = "${app.finalstatus=='Submitted'}">
									                
									                <td>${app.finalstatus}<a href='#' onclick="return payARFRegister(${app.applicationid},'${app.paytoclick}');" rel='tooltip' data-placement='right' data-original-title="Click here to Pay Registration Fee/ARF Challan download for Manual Payment"><br><font color="#FF1493">Download&nbsp;Challan/ Pay&nbsp;Online</font></a></td>
									                </c:when> 
									                <c:otherwise>
									                <td>${app.finalstatus} </td>
									                </c:otherwise>
									                </c:choose>
									                <td><fmt:formatDate value="${app.appregdate}" pattern="dd-MM-yyyy"/></td>
									                <td>${app.diffDays}</td>
									                <td>${app.name}${app.organizationname}</td>
									                <td>${app.city}</td>
									                <td>${app.loadkw}</td>
									                <td>${app.loadhp}</td>
									                <td>${app.loadkva}</td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.applicationtype}</td>
									                <td>${app.natureofinst}</td>
									                <td>${app.locality}</td>
									                <td>${app.locationText}</td>
									               
									               
									            </tr>
									             </c:forEach>
									          </tbody>
									
										</table>
				
									</div>
				
								</div>
							</div></div>
		</c:if>
			
			
		</div>


	</div></section>
</div>	

<script type="text/javascript">



var responsiveHelper_datatable_col_reorder = undefined;
var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	

	

$('#datatable_fixed_column').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"order": [],
	"bPaginate":true,
	"sPaginationType":"full_numbers",
	"iDisplayLength": 05,
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});

$('#datatable_fixed_column1').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"order": [],
	"bPaginate":true,
	"sPaginationType":"full_numbers",
	"iDisplayLength": 05,
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});


 
 
$(document).ready(function() {
	pageSetUp();
});


function trackApplication(applicationId){
	 window.location.href="./consumerAppStatus?applicationId="+applicationId;
}
function payARFRegister(applicationId,paytoclick){
	
	if(paytoclick=="notok"){
		if(confirm("Have you filled all Mutiple Connections (if not Click on Application Id and Add Multiple Connection details)?")){
			window.location.href="./onlinePaymentForPendingRegApplication?applicationId="+applicationId;
		}
		else{
	        return false;
	    }
	}else{
	 window.location.href="./onlinePaymentForPendingRegApplication?applicationId="+applicationId;
	}
	
}
function notificationUpdateStatus(notificationId)
{
	
	$.ajax({
	type : "POST",
	url : "./notificationUpdate/updateStatus/"+notificationId,
	async: false,
	dataType : "text",
	success:function(response){
  			
			window.location.reload(true);
		 }
});

}


$.ajaxSetup({cache: false});



			  
  $('#bootstrap-wizard-1').bootstrapWizard({
    'tabClass': 'form-wizard',
    'onNext': function (tab, navigation, index) {
      var $valid = $("#wizard-1").valid();
   
      if (!$valid) {
        $validator.focusInvalid();
        return false;
      } else {
        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
          'complete');
        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
        .html('<i class="fa fa-check"></i>');
      }
    }
  });
  

 var validate=new Array();
 
 $("#nextButton").click(function(){

	 var className1=$('#tab11').attr('class');
	 var className2=$('#tab22').attr('class');
	 var className3=$('#tab33').attr('class');
	 var className4=$('#tab44').attr('class');
	 var className5=$('#tab55').attr('class');
	 var className6=$('#tab66').attr('class');
	 var className7=$('#tab77').attr('class');
	 
	 
	
	 if(className1 == 'complete'){	
		 
		 $("#show-tabs1").prop('checked', true);
	 }
	 if(className2 == 'complete'){
		
		 $("#show-tabs2").prop('checked', true);
	 }
	 if(className3 == 'complete'){
		
		 $("#show-tabs3").prop('checked', true);
	 }
	 if(className4 == 'complete'){
		
		 $("#show-tabs4").prop('checked', true);
	 }
	 
	 if(className5 == 'complete'){				 
		 $("#show-tabs5").prop('checked', true);
	 }
	 if(className6 == 'complete'){				 
		 $("#show-tabs6").prop('checked', true);
	 }
	 if(className7 == 'complete'){				 
		 $("#show-tabs7").prop('checked', true);
	 }
	

	 
 });





</script>
<style>
th {
	vertical-align: middle !important
}

.popover-content {
	overflow: overlay;
	max-height: 250px;
}

.sparkline {
	width: 100% !important
}

.easy-pie-title {
	overflow: visible !important;
}

.input-group-addon{
height: 18px;
 font-size: 13px;
}
.form-control input-lg {
height: 37px; 
font-size: 13px;
}
.bootstrapWizard li {
    display: block;
    float: left;
    padding-left: 0;
    text-align: center;
    width: 13.1%;
}

 
  
    
</style>
