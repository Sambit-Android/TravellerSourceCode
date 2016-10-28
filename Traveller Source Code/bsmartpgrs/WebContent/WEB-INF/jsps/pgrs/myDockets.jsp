<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<div id="content">
	<div class="row" style="margin-top: -9px">
		
		<div class="col-sm-4">
			<a class="btn btn-warning btn-lg"  onclick="showAllAssignedTickets(2)" style="width:350px;">Assigned Complaints (${notificationCount1})</a>
		</div>		
										
		<div class="col-sm-4">
			<a class="btn bg-color-greenLight btn-lg txt-color-white" onclick="showAllResolvedTickets()" style="width:350px;">Resolved Complaints (${notificationCount3})</a>
		</div> 
		
		<div class="col-sm-4">
			<a class="btn bg-color-redLight btn-lg txt-color-white" onclick="showAllEscalatedTickets(2)" style="width:350px;">Escalated Complaints (${notificationCount2})</a>
		</div>

	
	
	<div class="row">	
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 39px; padding-right: 26px;">
			<div class="row">
			<div class="well input-group" style="width: 100%; background: #d6dde7;">
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
											<input class="form-control" onkeypress="searchDocketNumber(event)" name="docketNo" type="text" style="width: 223px;margin-left: 32px;height: 34px;" data-mask="9999999999" placeholder="Enter Docket No." id="docketNo" data-original-title="Enter Docket Number" rel="tooltip">
											
										</div>
									</div>

								</div>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
						<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
										<input class="form-control" name="rrNumber" onkeypress="searchDocketRRNumber(event)" id="rrNumber" type="text" style="width: 223px;margin-left: 32px;height: 34px;"   placeholder="Enter RR Number" id="search-project">
										</div>
									</div>

								</div>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
						<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
										<input class="form-control"  onkeypress="searchMobileNumber(event)" type="text" name="mobileNo" id="mobileNo" style="width: 223px;margin-left: 32px;height: 34px;"  data-mask="9999999999" placeholder="Enter Mobile No." id="search-project" style="height: 37px; font-size: 13px;" data-original-title="Enter Mobile Number" rel="tooltip">
										</div>
									</div>

								</div>
						</div>
			</div>
			</div>
		</div>
	</div>
	</div>
	<c:if test = "${not empty escalatedComplaintsNotification}"> 			        
		        <script>		        
		            var escalatedComplaintsNotification = "${escalatedComplaintsNotification}";
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	showAllEscalatedTickets(1);
		            });
		        </script>
		    <c:remove var="escalatedComplaintsNotification" scope="session"/>
		 </c:if>
		 
		 
		 	<c:if test = "${not empty reqEscAssignedTick}"> 			        
		        <script>		        
		            var reqEscAssignedTick = "${reqEscAssignedTick}";
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	showAllAssignedTickets(1);
		            });
		        </script>
		    <c:remove var="reqEscAssignedTick" scope="session"/>
		 </c:if>

	<%-- <div class="col-sm-12" id="dateWiseDiv">
			<div class="widget-body no-padding" class="col-sm-12" class="well">
				<form:form id="order-form" action="" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate" style="background: #d6dde7;margin-top: -4px;">
					<fieldset style="background: #d6dde7;margin-top: -15px;">
									<section class="col col-2">
										<label class="select">
											<form:select path="categoryId" id="categoryId" name="categoryId">
												<option value="0" selected="" disabled="">Select Category</option>
  												<c:forEach items="${categoryList}" var="categories">
												<form:option value="${categories.categoryId}">${categories.categoryName}</form:option>
												</c:forEach>
										    </form:select><i></i></label>
									</section>
									<section class="col col-2">
										<label class="select">
											<form:select path="subCategoryId" name="subCategoryId" id="subCategoryId">
												<option value="0" selected="" disabled="">Select Sub Category</option>
											</form:select> <i></i> </label>
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label>
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
									<section class="col col-2">
										<label class="select">
											<select name="docketSource" id="docketSource">
												<option value="0" selected="" disabled="">Select Status</option>
												<option value="1">Assigned</option>
												<option value="2">On Hold</option>
												<option value="3">Resolved</option>
												<option value="4">Re Open</option>
												<option value="5">Escalated</option>
											</select> <i></i></label>
									</section>
									<section class="col col-2">
										<label class="select">
											<select name="docketSource" id="docketSource">
												<option value="0" selected="" disabled="">Select Source</option>
												<option value="1">Phone</option>
												<option value="1">Web</option>
												<option value="3">Sms</option>
												<option value="4">Email</option>
												<option value="5">Facebook</option>
												<option value="6">Hand Written</option>
											</select> <i></i></label>
									</section>
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;margin-left: 430px;">Search</button>
									</section>
							</fieldset>
						</form:form>
					</div>
			</div>  --%>

<div class="row" id="searchDocket" style="display:none">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"><i class="fa fa-fw fa-search fa-lg"></i></span>
									<h2>Searched Docket Details</h2>
								</header>
								<!-- widget div-->
								<br>
								<a class="btn bg-color-teal txt-color-white"  href="#"  onclick="return docketDetailsPopUp('MobTicketsSearchCheckBox')">Change Multiple Status</a>
								<div>
									<div class="widget-body no-padding">
										<table class="table table-striped table-bordered table-hover" width="100%" id="mobileTicketsSearch">
											<thead>
												<tr>
												<th><input type="checkbox" id="selectall"  onClick="selectAllMobSearch(this)" /></th>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th>Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Summary</th>
													<th data-hide="phone,tablet">Mode</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="searchTicketDiv">
												<c:forEach items="${allTickets}" var="ticket">
												<tr>
													<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td>
													<td>${ticket.consumerName}</td>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>		
			</article>
	</div>			
			
<div class="row" id="viewTickets">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<section>
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">My Assigned Complaints</span></h2>
									
								</header>
								<br>
								
								<a class="btn bg-color-redLight txt-color-white"  href="#"  onclick="return docketDetailsPopUp('CHECKBOXYES')">Change Multiple Status</a>
								<!-- widget div-->
								<div>
								
									<div class="widget-body no-padding">
									
										<table id="mobileTicketsSearchAssigned" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
												<th> <input type="checkbox" id="selectall"  onClick="selectAll(this)" /></th>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-hide="phone,tablet">SLA Date&Time for Resolving</th>
													<th data-hide="phone,tablet">Time Pending</th>
													<th data-hide="phone">Category</th>
													<th data-hide="phone">SubCategory</th>
													<!-- <th data-hide="phone,tablet">Summary</th> -->
													<!-- <th data-hide="phone,tablet">Mode</th> -->
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="viewTicketDiv">
												<c:forEach items="${allTickets}" var="ticket">
											     <tr>
													<td><div id="docketNumDiv"> <input type="checkbox"    autocomplete="off" placeholder="" name="docketNumb" id="${ticket.docketNumber}" value="${ticket.docketNumber}" /> </div></td>
													<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.estResolveDate}</td> 
													<td>${ticket.timePending}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
												<%-- 	<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td> --%>
													<td>${ticket.consumerName}</td>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
					</section>		
			</article>
	</div>
	
	<div class="row" id="viewResolvedTickets" style="display:none">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<section>
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">My Resolved Complaints</span></h2>
								</header>
								<!-- widget div-->
								<div>
									<div class="widget-body no-padding" id="resolvedTicketsDiv">
										<!-- <table id="datatable_tabletoolsResolved" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-hide="phone,tablet">SLA Date&Time for Resolving</th>
													<th data-hide="phone,tablet">Resolved Date&Time</th>
													<th data-hide="phone,tablet">Total Time taken for Resolving</th>													
													<th data-hide="phone">Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="resolvedTicketsDiv">
												
											</tbody>
										</table> -->
									</div>
								</div>
							</div>
					</section>		
				</article>
		</div>
		
		<div class="row" id="viewEscalatedTickets" style="display:none">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<section>
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">My Escalated Complaints</span></h2>
								</header>
								<!-- widget div-->
								<div>
								<br>
									<div class="widget-body no-padding" id="escalatedDiv">
									
										<!-- <table id="datatable_tabletoolsResolved" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-hide="phone,tablet">SLA Date&Time for Resolving</th>
													<th data-hide="phone,tablet">Resolved Date&Time</th>
													<th data-hide="phone,tablet">Total Time taken for Resolving</th>													
													<th data-hide="phone">Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="resolvedTicketsDiv">
												
											</tbody>
										</table> -->
									</div>
								</div>
							</div>
					</section>		
				</article>
		</div>
		
	</div>	

	<div id="addtab" title="Docket Details Update" style="display:none">

				<form id="updateStatus-form">
					<fieldset>
						<input name="authenticity_token" type="hidden"><br>
						<div class="form-group">
								<input type="radio" id="commentId" checked="checked" name="Comment" onchange="commentValueChange(this.value)" value="Status"><label>&nbsp;Change Status</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="Comment" value="Comment" onchange="commentValueChange(this.value)"><label>&nbsp;Comment</label><br>
							</div>
							<div class="form-group" id="commentStatus">
							<label>Docket Status </label>
							<select class="form-control" id="docketStatus" name="docketStatus">
								<option value="0" selected="" disabled="">Select Status</option>
								<option value="2">On Hold</option>
								<option value="3">Resolved</option>
							</select><i></i>
						</div>
						<div class="form-group">
							<label>Remarks</label>
							<textarea class="form-control" name="remarks" id="remarks" placeholder="Docket Remarks" rows="3"></textarea>
						</div>
					</fieldset>
				</form>

	</div>	

		<div class="row" style="display:none" id="selectedDocketDetails">		
			<article class="col-sm-12">
				<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon"></span>
								<ul class="nav nav-tabs pull-right in" id="myTab">
									<li id="tabOne" class="active">
										<a data-toggle="tab" href="#s1" onclick="tab1Click()"><i class="fa fa-envelope"></i> <span class="hidden-mobile hidden-tablet">Docket Details</span></a>
									</li>
									<li id="tabTwo">
											<a data-toggle="tab" href="#s2" onclick="tab2Click()"><i class="fa fa-folder"></i> <span class="hidden-mobile hidden-tablet">Docket Histroy</span></a>
									</li>
									<li id="tabThree">
											<a data-toggle="tab" href="#s3" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
									</li>
									</ul>	
										
								</header>
								<div class="no-padding">
									<div class="widget-body" id="tab1">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in padding-12 no-padding-bottom" id="s1"> 	
											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
													<tbody id="viewDocket">
	
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab2" style='overflow: scroll;'>
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s2">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped" >
													<thead>
													<tr>
															<th data-hide="phone,tablet">SI No.</th>
															<th data-hide="phone,tablet">Date</th>
															<th data-class="phone,tablet">Status</th>
															<!-- <th>Action</th> -->
															<th data-hide="phone,tablet">Action By</th>
															<th data-hide="phone,tablet">Name</th>
															<th data-hide="phone,tablet">User Name</th>
															<th data-hide="phone,tablet">Office</th>
															<th data-hide="phone,tablet">Designation</th>
															<th data-hide="expand">Official Mobile No.</th>
															<th data-hide="phone,tablet">Remarks</th>
														</tr>
														</thead>
													<tbody id="docketHistoryDiv">
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab3">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s3">	
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<!-- <th data-hide="phone">Created Date</th> -->
															<th data-hide="phone">Escalated Date</th>
															<th data-class="expand">Level</th>
															<!-- <th data-hide="phone">From Officer</th> -->
															<th data-hide="phone">To Officer</th>
														</tr>
														</thead>
													<tbody id="docketEscHistoryDiv">
													</tbody>
													</table>
												</div>
											</div>
									</div>
							</div>
					</div>
			</div>
		</article>
		
</div>

<script>

// addTab form: calls addTab function on submit and closes the dialog
/* var form = dialog.find("form").submit(function(event) {
	addTab();
	dialog.dialog("close");
	event.preventDefault();
}); */


$(document).ready(function() {
	pageSetUp();
	loadSearchAndFilter('mobileTicketsSearchAssigned');
	
	$('#updateStatus-form').validate({
				// Rules for form validation
				rules : {
					docketStatus : {
						required : true
					},
					remarks : {
						required : true,
						maxlength : 250
					}
				},

				// Messages for form validation
				messages : {
					docketStatus : {
						required : 'Please select status'
					},
					remarks : {
						required : 'Please enter remarks',
						maxlength : 'Remarks max length 250 characters'
					}
				},

				// Do not change code below
				errorPlacement : function(error,element) {
					error.insertAfter(element.parent());
				}
			});
	
	$('#todate').datepicker({
		dateFormat : 'dd/mm/yy'
	});

	$('#fromdate').datepicker({
		dateFormat : 'dd/mm/yy'
	});

	var responsiveHelper_datatable_tabletools = undefined;
	
	 var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	}; 

	 
$('#datatable_tabletools').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
	"t"+
	"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>", 
	"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
	"aaSorting": [[ 5, "asc" ]],
	 "bPaginate":true,
     "sPaginationType":"full_numbers",
      "iDisplayLength": 05,
	/* "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>", */
    /* "oTableTools": {
    	 "aButtons": [
         "copy",
         "csv",
         "xls",
            {
                "sExtends": "pdf",
                "sTitle": "SmartAdmin_PDF",
                "sPdfMessage": "SmartAdmin PDF Export",
                "sPdfSize": "letter"
            },
         	{
            	"sExtends": "print",
            	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
        	}
         ],
        "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
    }, */
	"autoWidth" : true,
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_tabletools) {
			responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_tabletools.respond();
	}
});

/* $('#datatable_tabletools_filter').append("<a class='btn bg-color-redLight txt-color-white' href='#'  onclick='return docketDetailsPopUp('CHECKBOXYES')'>Change Multiple Status</a>"); */
})

function searchMobileNumber(e)
	{
	
	 if(e.keyCode === 13){
		 var mobileNo = $("#mobileNo").val();
		 var mobileregex = new RegExp("^[0-9]{10}$");
     	if(!mobileregex.test(mobileNo)){
     		$("#alertsBox").html("");
			$("#alertsBox").html("Please Enter 10 digit Mobile Number");
			$("#alertsBox").dialog
			({
				modal : true,
				buttons : {
				   "Close" : function() {
				      $(this).dialog("close");
					}
				}
				});
     		return false;
     	}
	var tableData = "";
	$("#searchTicketDiv").empty();
	$('#liveTicketDiv').hide();
	$('#viewTickets').show();
	$('#searchDocket').show();
	$('#dateWiseDiv').hide();
	$("#subdivisionwiseTicketsDiv").hide();
	$("#viewEscalatedTickets").hide();
	$('#pendingForRegistrationTickets').hide();
	$('#registerComplaintId').hide();
	$("#complaintsAgingDiv").hide();
	$('#data12').hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchMobileNumber/"+mobileNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		             	tableData += "<tr>"	
			              	if(obj.docketStatus=='Resolved'){
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}else{
			              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}
		              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
		              	tableData+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+"</td>"
		              	+"<td>"+obj.categoryName+ "</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.docketSummary+ "</td>"
		              	+"<td>"+obj.docketSource+"</td>"			              	
		              	+"<td>"+obj.consumerName+"</td>"
		              	+"<td>"+obj.consumerMobileNo+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
						$('#searchTicketDiv').html(tableData);
					

		},
		 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			}  
	});
	
	$("#docketNo").val("");
	$("#mobileNo").val("");
	$("#rrNumber").val("");
	 }
}

function searchDocketNumber(e)
{
if(e.keyCode === 13){
	var docketNo = $("#docketNo").val();
	var mobileregex = new RegExp("^[0-9]{10}$");
	if(!mobileregex.test(docketNo)){
		$("#alertsBox").html("");
		$("#alertsBox").html("Please Enter 10 digit Docket Number");
		$("#alertsBox").dialog
		({
			modal : true,
			buttons : {
			   "Close" : function() {
			      $(this).dialog("close");
				}
			}
			});
		return false;
	}

	var tableData = "";
	$("#searchTicketDiv").empty();
	$('#liveTicketDiv').hide();
	$('#viewTickets').hide();
	$('#searchDocket').show();
	$('#dateWiseDiv').hide();
	$('#viewResolvedTickets').hide();
	$('#viewEscalatedTickets').hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketNumber/"+docketNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"		
		              	if(obj.docketStatus=='Resolved'){
		              		tableData+="<td></td>" 
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation()'>"+obj.docketNumber+"</a></td>";
		              	}else{
		              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
		              	}
		              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
		              	tableData+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+"</td>"
		              	+"<td>"+obj.categoryName+ "</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.docketSummary+ "</td>"
		              	+"<td>"+obj.docketSource+"</td>"			              	
		              	+"<td>"+obj.consumerName+"</td>"
		              	+"<td>"+obj.consumerMobileNo+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
						$('#searchTicketDiv').html(tableData);
		},
		 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			}   
	});
	
	$("#docketNo").val("");
	$("#mobileNo").val("");
	$("#rrNumber").val("");
	return true;
}

return false;
}
function searchDocketRRNumber(e)
{
if(e.keyCode === 13){
	var rrNumber = $("#rrNumber").val();

	var tableData = "";
	$("#searchTicketDiv").empty();
	$('#liveTicketDiv').hide();
	$('#viewTickets').hide();
	$('#searchDocket').show();
	$('#dateWiseDiv').hide();
	$('#viewResolvedTickets').hide();
	$('#viewEscalatedTickets').hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketRRNumber/"+rrNumber,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"		
		              	if(obj.docketStatus=='Resolved'){
		              		tableData+="<td></td>" 
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation()'>"+obj.docketNumber+"</a></td>";
		              	}else{
		              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
		              	}
		              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
		              	tableData+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+"</td>"
		              	+"<td>"+obj.categoryName+ "</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.docketSummary+ "</td>"
		              	+"<td>"+obj.docketSource+"</td>"			              	
		              	+"<td>"+obj.consumerName+"</td>"
		              	+"<td>"+obj.consumerMobileNo+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
						$('#searchTicketDiv').html(tableData);
		},
		 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			}   
	});
	
	$("#docketNo").val("");
	$("#mobileNo").val("");
	$("#rrNumber").val("");
	return true;
}

return false;
}


$('select[id*=categoryId]').change(function() {
	var categoryId = $("#categoryId").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSubCategories/" + categoryId,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#subCategoryId').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',0).text("Select SubCategory");
            $('#subCategoryId').append(defaultOption);
			($.map(data, function(item) {
				var newOption = $('<option>'); 
				newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
				$('#subCategoryId').append(newOption);
			}));
		}
	});
});

function commentValueChange(value){
	if(value=='Comment'){
		$("#commentStatus").hide();
		$('#docketStatus').val(0);
	}else{
		$("#commentStatus").show();
	}
}

var dialog = "";
function docketDetailsPopUp(docketNo)
{
	
	
	if(docketNo=='CHECKBOXYES')//This is for Assign Complaint CheckBox
		{
	var checkboxes = document.getElementsByName('docketNumb');
	var checks = "";
	for(var i =0;i<checkboxes.length;i++)
	{
		
		if(checkboxes[i].checked)
		 {
			checks = checks+checkboxes[i].value+",";
			
		}
		}
	if(checks == "")
		{
			alert("Please Select the Docket Number ...");
			return false;
		}
	checks = checks.substring(0,checks.length-1);
	
	docketNo=checks;
	
		}
	
	if(docketNo=='ESCULATEDCHECKBOX')//This is for Esculated Complaint CheckBox
		{
		
		var checkboxes = document.getElementsByName('docketNumbEsc');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
		{
			
			if(checkboxes[i].checked)
			 {
				checks = checks+checkboxes[i].value+",";
				
			}
			}
		if(checks == "")
			{
				alert("Please Select the Docket Number ...");
				return false;
			}
		checks = checks.substring(0,checks.length-1);
		var s=checks.split(",");
		  var result = [];
		  $.each(s, function(i, e) {
		    if ($.inArray(e, result) == -1) result.push(e);
		  });
		  var sa="";
		for(var j=0;j<result.length;j++)
			{
			 sa+=result[j]+",";
			}

		docketNo=sa.substring(0,sa.length-1);
		
		}
	
	if(docketNo=='MobTicketsSearchCheckBox')//for Mobile Based Search Detail
	{
		var checkboxes = document.getElementsByName('docketNumbMobSearch');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
		{
			
			if(checkboxes[i].checked)
			 {
				checks = checks+checkboxes[i].value+",";
				
			}
			}
		if(checks == "")
			{
				alert("Please Select the Docket Number ...");
				return false;
			}
		checks = checks.substring(0,checks.length-1);
		
		docketNo=checks;
		
	}

	
	
	$("#commentStatus").show();
	$('#commentId').prop('checked', true);
	dialog = $("#addtab").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{

			html : "Update",
			"class" : "btn btn-primary",
			click : function() {				
			var status = ticketStatusUpdateClick(docketNo);			
			if(status!=false){
				$(this).dialog("close");
			}
				
			}
		},{
			html : "<i class='fa fa-times'></i>&nbsp; Cancel",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");

			}
		}]
	}).dialog("open");
}

function ticketStatusUpdateClick(docketNo){
	
	var commentValue = $("input[name=Comment]:checked").val();
	
	var docketStatus = $("#docketStatus").val();
	var remarks = $("#remarks").val();
	
	if(commentValue == "Status"){
		if(docketStatus==0 || docketStatus=="" || docketStatus==null){
			alert("Please select status");
			return false;
		}else if(remarks==null || remarks == ""){
			alert("Please enter remarks");
			return false;
		}
		
	}else if(remarks==null || remarks == ""){
		alert("Please enter remarks");
		return false;
	}
	
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/updateDocket",
		dataType : "text",
		data:{
			docketNo : docketNo,
			docketStatus : docketStatus,
			remarks : remarks,
		},
		success : function(response){	    
				alert(response);	
		} 
	});
	$("#docketStatus").val(0);
	$("#remarks").val("");
}

var docketNo;
function docketDetailsViewPopUp(docNo)
{
	docketNo = docNo;
	dialog = $("#selectedDocketDetails").dialog({
		autoOpen : false,
		width : 1000,
		height : 580,
		resizable : false,
		modal : true,
	}).dialog("open");
	$("#tabOne").addClass("active");
	$("#tabTwo").removeClass();
	$("#tabThree").removeClass();
	tab1Click();	
}

function tab1Click(){
	$("#tab1").show();
	$("#tab2").hide();
	$("#tab3").hide();
	$("#viewDocket").empty();
	var tableData = "";
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketNumber/"+docketNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"	
				              	+"<th colspan='6'>Docket Details</th>"
				              	+"</tr>"						
								+"<tr>"
								+"<th>Docket No.</th>"
								+"<td>"+obj.docketNumber+"</td>"
								+"<th>Docket Source</th>"
								+"<td>"+obj.docketSource+"</td>"
								+"<th>Docket Status</th>"
								+"<td>"+obj.docketStatus+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Consumer Name</th>"
								+"<td>"+obj.consumerName+"</td>"
								+"<th>Consumer Mobile No.</th>"
								+"<td>"+obj.consumerMobileNo+"</td>"
								+"<th>Email Id</th>"
								+"<td>"+obj.consumerEmailId+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>RR Number</th>"
								+"<td>"+obj.rrNumber+"</td>"
								+"<th>Consumer Other Mobile No.</th>"
								+"<td>"+obj.alternativeMobileNo+"</td>"
								+"<th>Facebook Id</th>"
								+"<td>"+obj.facebookId+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Address</th>"
								+"<td>"+obj.address+"</td>"
								+"<th>Category</th>"
								+"<td>"+obj.categoryName+"</td>"
								+"<th>Sub Category</th>"
								+"<td>"+obj.subCategoryName+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Sub Division</th>"
								+"<td>"+obj.divisionName+"</td>"
								+"<th>Section</th>"
								+"<td>"+obj.subDivisionName+"</td>"
								+"<th>Land Mark</th>"
								+"<td>"+obj.landMark+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Created By</th>"
								+"<td>"+obj.userName+"</td>"
								+"<th>Created Date</th>"
								+"<td>"+obj.docketCreatedDt+"</td>"
								+"<th>SLA Date&Time for Resolving</th>"
								+"<td>"+obj.estResolveTime+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Resolved Date</th>"
								+"<td>"+obj.resolvedDate+"</td>"
								+"<th>Asigned To</th>"
								+"<td colspan='5'>"+obj.assinedName+" - "+obj.assignedToEmail+" - "+obj.designation+" - "+obj.officialMobileNo+" - "+obj.officeName+"</td>"
								/* +"<th>Official Mobile No.</th>"
								+"<td>"+obj.officialMobileNo+"</td>" */
								+"</tr>"
								+"<tr>"
								+"<th>Docket Summary</th>"
								+"<td>"+obj.docketSummary+"</td>"
								+"<th>Service Station</th>"
								+"<td colspan='5'>"+obj.serviceStation+"</td>"
								+"</tr>";
				                
				     }
				$('#viewDocket').append(tableData);
		}
	});
}

function tab2Click(){
	var docketHistroyTable = "";
	$("#docketHistoryDiv").empty();
	$("#tab2").show();
	$("#tab1").hide();
	$("#tab3").hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketHistory/"+docketNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	docketHistroyTable += "<tr>"		
		              	+"<td>"+obj.serialNo+"</td>"
		              	+"<td>"+obj.docketUpdatedDt+"</td>"
		              	+"<td>"+obj.docketStatus+ "</td>"
		              	/* +"<td>"+obj.action+"</td>" */
		              	+"<td>"+obj.actionBy+ "</td>"
		              	+"<td>"+obj.personName+"</td>"			              	
		              	+"<td>"+obj.userName+"</td>"
		              	+"<td>"+obj.office+ "</td>"
		              	+"<td>"+obj.designation+ "</td>"
		              	+"<td>"+obj.officialMobileNo+ "</td>"
		              	+"<td>"+obj.remarks+ "</td>"
		                +"</tr>";
		         }
						$('#docketHistoryDiv').append(docketHistroyTable);
		} 
	});
}

function tab3Click(){
	var docketEscHistroyTable = "";
	$("#docketEscHistoryDiv").empty();
	$("#tab3").show();
	$("#tab1").hide();
	$("#tab2").hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketEscHistory/"+docketNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	docketEscHistroyTable += "<tr>"		
		              	+"<td>"+obj.serialNo+"</td>"
		              	/* +"<td>"+obj.docketCreatedDt+"</td>" */
		              	+"<td>"+obj.escalatedDate+"</td>"
		              	+"<td>"+obj.escLevel+ "</td>"
		              	/* +"<td>"+obj.fromUserData+"</td>" */
		              	+"<td>"+obj.toUserData+ "</td>"	
		                +"</tr>";
		         }
						$('#docketEscHistoryDiv').append(docketEscHistroyTable);
		} 
	});
}

function docketDetailsValidation()
{
	alert("Resolved dockets cannot be updated");
}

function showAllAssignedTickets(temp){

	
	$("#viewTickets").show();
	$("#searchDocket").hide();
	$("#viewResolvedTickets").hide();
	$("#viewEscalatedTickets").hide();
	if('${escAssignDocNum}'!=''&&temp=='1')
	{
		
		$('#viewTicketDiv').empty();
		var tab1="";
		var tab2="";
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/getAllAssignedTicketsInMyDockets",
			async: false,
			dataType : "JSON",
			success : function(response) 
			{
				
				tab1+="<tr hidden='true'>"
				       +"<td></td>"
				       +"<td></td>" 
				       +"<td></td>" 
				       +"<td></td>" 
				       +"<td></td>" 
				       +"<td></td>" 
				       +"<td></td>"
				       +"<td></td>"
				       +"<td></td>"
				       +"<td></td>"
				       +"<td></td>"
				       +"</tr>";
				
					for ( var s = 0, len = response.length; s < len; ++s) {
			            	var ticket = response[s];
			            	if('${escAssignDocNum}'==ticket.docketNumber)
			            		{
			            	tab1+="<tr style='font-size:14px;background: #C59F50'>"
			            	+"<td><div id='docketNumDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumb' id='"+ticket.docketNumber+"' value='"+ticket.docketNumber+"' /> </div></td>"
			            	+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+ticket.docketNumber+")'>"+ticket.docketNumber+"</a></td>"
			            	+"<td>"+ticket.docketStatus+"</td>"
			            	+"<td>"+ticket.docketCreatedDt+"</td>"
			            	+"<td>"+ticket.estResolveDate+"</td>" 
			            	+"<td>"+ticket.timePending+"</td>"
			            	+"<td>"+ticket.categoryName+"</td>"
			            	+"<td>"+ticket.subCategoryName+"</td>"
			                +"<td>"+ticket.consumerName+"</td>"
			                +"<td>"+ticket.consumerMobileNo+"</td>"
			            	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+ticket.docketNumber+")'>View</a></td>"
			                +"</tr>"; 
			            	}
			            	else
			            		{
			            		
			            		tab2+="<tr>"
					            	+"<td><div id='docketNumDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumb' id='"+ticket.docketNumber+"' value='"+ticket.docketNumber+"' /> </div></td>"
					            	+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+ticket.docketNumber+")'>"+ticket.docketNumber+"</a></td>"
					            	+"<td>"+ticket.docketStatus+"</td>"
					            	+"<td>"+ticket.docketCreatedDt+"</td>"
					            	+"<td>"+ticket.estResolveDate+"</td>" 
					            	+"<td>"+ticket.timePending+"</td>"
					            	+"<td>"+ticket.categoryName+"</td>"
					            	+"<td>"+ticket.subCategoryName+"</td>"
					                +"<td>"+ticket.consumerName+"</td>"
					                +"<td>"+ticket.consumerMobileNo+"</td>"
					            	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+ticket.docketNumber+")'>View</a></td>"
					                +"</tr>"; 
			            		
			            		}
			                
					}
					tab1+="</tbody>";
					tab2+="</tbody>";
					$('#mobileTicketsSearchAssigned').dataTable().fnDestroy();
					$('#viewTicketDiv').append(tab1);
					$('#viewTicketDiv').append(tab2+"</table></div>");
			},
			
			 complete:function(response)
				{
					loadSearchAndFilter('mobileTicketsSearchAssigned');
				}  
		});
	

	}
    else
     {
	
    	
var tab1="";
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/getAllAssignedTicketsInMyDockets",
			async: false,
			dataType : "JSON",
			success : function(response) 
			{
				
					for ( var s = 0, len = response.length; s < len; ++s) {
			            	var ticket = response[s];
			            	tab1+="<tr>"
			            	+"<td><div id='docketNumDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumb' id='"+ticket.docketNumber+"' value='"+ticket.docketNumber+"' /> </div></td>"
			            	+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+ticket.docketNumber+")'>"+ticket.docketNumber+"</a></td>"
			            	+"<td>"+ticket.docketStatus+"</td>"
			            	+"<td>"+ticket.docketCreatedDt+"</td>"
			            	+"<td>"+ticket.estResolveDate+"</td>" 
			            	+"<td>"+ticket.timePending+"</td>"
			            	+"<td>"+ticket.categoryName+"</td>"
			            	+"<td>"+ticket.subCategoryName+"</td>"
			                +"<td>"+ticket.consumerName+"</td>"
			                +"<td>"+ticket.consumerMobileNo+"</td>"
			            	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+ticket.docketNumber+")'>View</a></td>"
			                +"</tr>"; 
					}
					tab1+="</tbody>"
					     +"</table>"
					     +"</div>";
					 $('#mobileTicketsSearchAssigned').dataTable().fnDestroy();
							$('#viewTicketDiv').html(tab1);
							
			},
			
			 complete:function(response)
				{
					loadSearchAndFilter('mobileTicketsSearchAssigned');
				}  
		});
	
	
     }
	
}

function showAllResolvedTickets(){
	
	$("#viewResolvedTickets").show();
	$("#viewEscalatedTickets").hide();
	$("#viewTickets").hide();
	$("#searchDocket").hide();
	
	$('.resolvedTicketsTabDetail').remove();
	$("#resolvedTicketsDiv").empty();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/getAllResolvedTickets",
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
			var htmlTable = "<div class='resolvedTicketsTabDetail' style='overflow: scroll;'>" 
				+"<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th data-hide='phone'>Docket No.</th>"
				+"<th data-hide='phone'>Status</th>"
				+"<th data-class='expand'>Created Date</th>"
				/* +"<th data-hide='phone,tablet'>SLA Date&Time for Resolving</th>" */
				+"<th data-hide='phone,tablet'>Resolved Date&Time</th>"
				/* +"<th data-hide='phone,tablet'>Total Time taken for Resolving</th>"	 */												
				+"<th data-hide='phone'>Category</th>"
				+"<th data-hide='phone'>SubCategory</th>"
				+"<th data-hide='phone,tablet'>Consumer Name</th>"
				+"<th data-hide='phone,tablet'>Mobile No.</th>"
				+"<th data-hide='phone,tablet'></th>"
			    +"</tr>"
				+"</thead>"
				+"<tbody>";
				for ( var s = 0, len = response.length; s < len; ++s) {
		            	var obj = response[s];
		              	htmlTable += "<tr>"		
		              	+"<td>"+obj.docketNumber+"</td>"
		              	+"<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+ "</td>"
		              	/* +"<td>"+obj.estResolveDate+"</td>" */
		              	+"<td>"+obj.resolvedDate+ "</td>"
		              	/* +"<td>"+obj.totalTimeTaken+"</td>"	 */		              	
		              	+"<td>"+obj.categoryName+"</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.consumerName+"</td>"
		              	+"<td>"+obj.consumerMobileNo+"</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					htmlTable +=""		
						+"</tbody>"
						+"</table></div>";
						$('#resolvedTicketsDiv').append(htmlTable);
						
						pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};
						$('#datatable_tabletools5')
						.dataTable(
								{
									"autoWidth" : true,
									"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
									"order": [],
									"preDrawCallback" : function() {
										if (!responsiveHelper_datatable_tabletools) {
											responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
													$('#datatable_tabletools5'),
													breakpointDefinition);
										}
									},
									"rowCallback" : function(nRow) {
										responsiveHelper_datatable_tabletools
												.createExpandIcon(nRow);
									},
									"drawCallback" : function(oSettings) {
										responsiveHelper_datatable_tabletools
												.respond();
									}
								});
		} 
	});
}

function showAllEscalatedTickets(temp){
	$("#viewEscalatedTickets").show();
	$("#viewResolvedTickets").hide();
	$("#viewTickets").hide();
	$("#searchDocket").hide();
	
	$('.escalatedTicketsTabDetail').remove();
	$("#escalatedDiv").empty();
	
	if('${escDocForCCNum}'!=''&&temp==1)
		{
		var data1="";
		var data2="";
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/getAllEscalatedTickets",
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	
				var d='"ESCULATEDCHECKBOX"';
				var htmlTable ="<a class='btn bg-color-greenDark txt-color-white'  href='#'  onclick='return docketDetailsPopUp("+d+")'>Change Multiple Status</a>"
					+"<div class='escalatedTicketsTabDetail' style='overflow: scroll;'>" 
					+"<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th><input type='checkbox' id='selectall'  onClick='selectAllEsc(this)' /></th>"
					+"<th data-hide='phone'>Docket No.</th>"
					+"<th data-hide='phone'>Status</th>"
					+"<th data-class='expand'>Escalated Date</th>"
					+"<th data-hide='phone,tablet'>Level</th>" 
					+"<th data-hide='phone,tablet'>Escalated From Officer</th>"
					+"<th data-hide='phone,tablet'>Escalated To Officer</th>"
					+"<th data-hide='phone,tablet'></th>"
				    +"</tr>"
					+"</thead>"
					+"<tbody id='appAssindTic'>";
					for ( var s = 0, len = response.length; s < len; ++s) {
			            	var obj = response[s];
			            	
			            	if('${escDocForCCNum}'==obj.docketNumber)
			            		{
			            	 	data1 += "<tr style='color: maroon;font-size:14px;background: #FFFAF0'>"	
					              	if(obj.docketStatus=='Resolved')
					              		{
					              		data1+="<td></td>"
					              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation()'>"+obj.docketNumber+"</a></td>";
					              		}
					              	else
					              		{
					              		data1+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
					              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
					              		 
					              		}
					              	data1+="<td>"+obj.docketStatus+"</td>"
					              	+"<td>"+obj.escalatedDate+ "</td>"
					              	+"<td>"+obj.level+ "</td>"
					              	+"<td>"+obj.fromUser+ "</td>"
					              	+"<td>"+obj.toUser+ "</td>"
					              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
					                +"</tr>";
			            		}
			            	
			            	else
			            		{
			            		data2 += "<tr>"	
					              	if(obj.docketStatus=='Resolved')
					              		{
					              		data2+="<td></td>"
					              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation()'>"+obj.docketNumber+"</a></td>";
					              		}
					              	else
					              		{
					              		data2+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
					              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
					              		 
					              		}
			            	     	data2+="<td>"+obj.docketStatus+"</td>"
					              	+"<td>"+obj.escalatedDate+ "</td>"
					              	+"<td>"+obj.level+ "</td>"
					              	+"<td>"+obj.fromUser+ "</td>"
					              	+"<td>"+obj.toUser+ "</td>"
					              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
					                +"</tr>";
			            		}
			            	
			             
			         }
					
						htmlTable +=""		
							+"</table></div>";
							$('#escalatedDiv').append(htmlTable);
							
							 data1+="</tbody>";
							 data2+="</tbody>";
								$('#appAssindTic').append(data1);
								$('#appAssindTic').append(data2);         
							
							pageSetUp();
							
							var responsiveHelper_datatable_tabletools = undefined;
							var breakpointDefinition = {
									tablet : 1024,
									phone : 480
								};
							$('#datatable_tabletools6')
							.dataTable(
									{
										"autoWidth" : true,
										"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
										"order": [],
										"preDrawCallback" : function() {
											if (!responsiveHelper_datatable_tabletools) {
												responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
														$('#datatable_tabletools6'),
														breakpointDefinition);
											}
										},
										"rowCallback" : function(nRow) {
											responsiveHelper_datatable_tabletools
													.createExpandIcon(nRow);
										},
										"drawCallback" : function(oSettings) {
											responsiveHelper_datatable_tabletools
													.respond();
										}
									});
			} 
		});
		
		 '<c:remove var="escDocForCCNum" scope="session"/>'
		}
	else
		{
	
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/getAllEscalatedTickets",
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	
			var d='"ESCULATEDCHECKBOX"';
			var htmlTable ="<a class='btn bg-color-greenDark txt-color-white'  href='#'  onclick='return docketDetailsPopUp("+d+")'>Change Multiple Status</a>"
				+"<div class='escalatedTicketsTabDetail' style='overflow: scroll;'>" 
				+"<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th><input type='checkbox' id='selectall'  onClick='selectAllEsc(this)' /></th>"
				+"<th data-hide='phone'>Docket No.</th>"
				+"<th data-hide='phone'>Status</th>"
				+"<th data-class='expand'>Escalated Date</th>"
				+"<th data-hide='phone,tablet'>Level</th>" 
				+"<th data-hide='phone,tablet'>Escalated From Officer</th>"
				+"<th data-hide='phone,tablet'>Escalated To Officer</th>"
				+"<th data-hide='phone,tablet'></th>"
			    +"</tr>"
				+"</thead>"
				+"<tbody>";
				for ( var s = 0, len = response.length; s < len; ++s) {
		            	var obj = response[s];
		              	htmlTable += "<tr>"	
		              	if(obj.docketStatus=='Resolved')
		              		{
		              		htmlTable+="<td></td>"
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation()'>"+obj.docketNumber+"</a></td>";
		              		}
		              	else
		              		{
		              		htmlTable+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
		              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
		              		 
		              		}
		              	
		              /* 	+"<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" */
		              	htmlTable+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.escalatedDate+ "</td>"
		              	+"<td>"+obj.level+ "</td>"
		              	+"<td>"+obj.fromUser+ "</td>"
		              	+"<td>"+obj.toUser+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					htmlTable +=""		
						+"</tbody>"
						+"</table></div>";
						$('#escalatedDiv').append(htmlTable);
						
						pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};
						$('#datatable_tabletools6')
						.dataTable(
								{
									"autoWidth" : true,
									"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
									"order": [],
									"preDrawCallback" : function() {
										if (!responsiveHelper_datatable_tabletools) {
											responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
													$('#datatable_tabletools6'),
													breakpointDefinition);
										}
									},
									"rowCallback" : function(nRow) {
										responsiveHelper_datatable_tabletools
												.createExpandIcon(nRow);
									},
									"drawCallback" : function(oSettings) {
										responsiveHelper_datatable_tabletools
												.respond();
									}
								});
		} 
	});
	
}
}


//by raju


	/* function updateStatus()
	{
		var checkboxes = document.getElementsByName('docketNumb');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
		{
			
			if(checkboxes[i].checked)
			 {
				checks = checks+checkboxes[i].value+",";
				
			}
			}
		if(checks == "")
			{
				alert("Please Select the Status ...");
				return false;
			}
		checks = checks.substring(0,checks.length-1);
		alert("---"+checks);
		
		return false;
		
		
	}
	 */
	 // For Assigned Search Select all Check box
	function selectAll(source) {
		 
		   var flagChecked = 0;
			checkboxes = document.getElementsByName('docketNumb');
			for(var i =0;i<checkboxes.length;i++)
				{
				checkboxes[i].checked = source.checked;
				if(checkboxes[i].checked)
				 {
					flagChecked = 1;
				}
				}
			
			 if(flagChecked==0)
			{
				$('#docketNumDiv span:first-child').removeClass("checked");
			}
			else{
				$('#docketNumDiv span:first-child').addClass("checked");
			} 
			
			
		}
	 
	 // For Esculated Search Select all Check box
	 function selectAllEsc(source) {
		 
		   var flagChecked = 0;
			checkboxes = document.getElementsByName('docketNumbEsc');
			for(var i =0;i<checkboxes.length;i++)
				{
				checkboxes[i].checked = source.checked;
				if(checkboxes[i].checked)
				 {
					flagChecked = 1;
				}
				}
			
			 if(flagChecked==0)
			{
				$('#docketNumEscDiv span:first-child').removeClass("checked");
			}
			else{
				$('#docketNumEscDiv span:first-child').addClass("checked");
			} 
			
			
		}
	 
	 // For Mobile Search Select all Check box
	 function selectAllMobSearch(source) {
		 
		   var flagChecked = 0;
			checkboxes = document.getElementsByName('docketNumbMobSearch');
			for(var i =0;i<checkboxes.length;i++)
				{
				checkboxes[i].checked = source.checked;
				if(checkboxes[i].checked)
				 {
					flagChecked = 1;
				}
				}
			
			 if(flagChecked==0)
			{
				$('#docketNumMobSearchDiv span:first-child').removeClass("checked");
			}
			else{
				$('#docketNumMobSearchDiv span:first-child').addClass("checked");
			} 
			
			
		}
	 
	 
	  function loadSearchAndFilter(param) // This Function Is For Multiple Table Id Search and Pagination
	  	{ 
		
		  $('#'+param).dataTable().fnDestroy();
	  	   pageSetUp();
			
			var responsiveHelper_datatable_tabletools = undefined;
			var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};
			$('#'+param)
			.dataTable(
					{
						"autoWidth" : true,
						"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
						"order": [],
						"preDrawCallback" : function() {
							if (!responsiveHelper_datatable_tabletools) {
								responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
										$('#'+param),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_datatable_tabletools
									.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_datatable_tabletools
									.respond();
						},
						"aoColumnDefs": [{
			                'bSortable': false,
			                'aTargets': [0]
			            }
			        ]
					});
	  	
	  	}
</script>