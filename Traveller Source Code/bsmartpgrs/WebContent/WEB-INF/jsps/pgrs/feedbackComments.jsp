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

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Feedback Comments</h1>
			</div>
		</div>
		
		<c:if test = "${not empty resultFeedbackComments}"> 			        
		        <script>		        
		            var resultFeedbackComments = "${resultFeedbackComments}";
		            alert(resultFeedbackComments);
		        </script>
		    <c:remove var="resultFeedbackComments" scope="session"/>
		 </c:if>
		
		<div class="row" id="viewResolvedTickets">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<section>
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">Resolved Complaints</span></h2>
								</header>
								<!-- widget div-->
								<br>
								
								<a class="btn bg-color-redLight txt-color-white"  href="#"  onclick="return docketFeedbackDetailsPopUp('CHECKBOXYES')">Change Multiple Feedback</a>
								<div>
									<div class="widget-body no-padding" id="resolvedTicketsDiv">
										<table id="datatable_tabletools" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
												<th> <input type="checkbox" id="selectall"  onClick="selectAll(this)" /></th>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-hide="phone,tablet">SLA Date&Time for Resolving</th>
													<th data-hide="phone">Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th data-hide="phone,tablet">Rating</th>
													<th data-hide="phone,tablet">Feedback Comments</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="resolvedTicketsDiv">
												<c:forEach items="${resolvedComplaints}" var="ticket">
												<tr>
													<c:choose>
    													<c:when test="${ticket.feedbackRating==0}">
    													    <td><div id="docketNumDiv"> <input type="checkbox"    autocomplete="off" placeholder="" name="docketNumb" id="${ticket.docketNumber}" value="${ticket.docketNumber}" /> </div></td>
	      													<td><a style="cursor:pointer;" onclick='docketFeedbackDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
   														</c:when> 
    													<c:otherwise>
    														<td></td>
      														<td><a style="cursor:pointer;" onclick='docketFeedbackDetailsValidation()'>${ticket.docketNumber}</a></td>
    													</c:otherwise>
													</c:choose>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td> 
													<td>${ticket.resolvedDate}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.consumerName}</td>
													<td>${ticket.consumerMobileNo}</td>
													<c:choose>
    													<c:when test="${ticket.feedbackRating==1}">
	      													<td>Poor</td>
   														</c:when>   
   														<c:when test="${ticket.feedbackRating==2}">
	      													<td>Average</td>
   														</c:when> 
   														<c:when test="${ticket.feedbackRating==3}">
	      													<td>Good</td>
   														</c:when>  
   														<c:when test="${ticket.feedbackRating==4}">
	      													<td>Excellent</td>
   														</c:when>
    													<c:otherwise>
      														<td></td>
    													</c:otherwise>
													</c:choose>
													<td>${ticket.feedbackComments}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
					</section>		
				</article>
		</div>
		
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
										<div class="widget-body" id="tab2">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s2">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
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

<div id="feedbackCommentsDiv" style="display:none;">
	<article>
	<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
				</header>
				<div>
					<div class="widget-body no-padding">
						<form:form id="review-form" action="./submitFeedbackComments" modelAttribute="helpdeskEntity" commandName="helpdeskEntity" class="smart-form">
							<header>
								<span id="docketNumberSpanId"></span> Docket Feedback Comments
							</header>

							<fieldset>
							
								<section>
									<div class="rating">
									   
									   <form:input type="hidden" path="transDocNum" name="transDocNum" id="transDocNum"></form:input>
										<form:input type="hidden" path="docketNumber" name="docketNumber" id="docketNumber"></form:input>
										<%-- <form:radiobutton path="feedBackRating" name="feedBackRating" value="5" id="quality-5"></form:radiobutton>
										<label for="quality-5"><i class="fa fa-star"></i></label> --%>
										<form:radiobutton path="feedBackRating" name="feedBackRating" value="4" id="quality-4"></form:radiobutton>
										<label for="quality-4"><i class="fa fa-star"></i></label>
										<form:radiobutton path="feedBackRating" name="feedBackRating" value="3" id="quality-3"></form:radiobutton>
										<label for="quality-3"><i class="fa fa-star"></i></label>
										<form:radiobutton path="feedBackRating" name="feedBackRating" value="2" id="quality-2"></form:radiobutton>										
										<label for="quality-2"><i class="fa fa-star"></i></label>
										<form:radiobutton path="feedBackRating" name="feedBackRating" value="1" id="quality-1"></form:radiobutton>
										<label for="quality-1"><i class="fa fa-star"></i></label>
										Quality of Redressal
									</div>
								</section>

								<section>
									<label class="label"></label>
									<label class="textarea"> <i class="icon-append fa fa-comment"></i> 										
										<form:textarea path="feedBackComments" rows="3" name="feedBackComments" id="feedBackComments" placeholder="Feedback Comments"></form:textarea> 
									</label>
								</section>
							</fieldset>
							<footer>
								<button type="submit" class="btn btn-primary">
									Submit Comments
								</button>
							</footer>
						</form:form>		
					</div>
				</div>
			</div>

		</article>
</div>

<script>
$(document).ready(function() {
	pageSetUp();
	
	document.getElementById("review-form").reset();
	
	$("#review-form").validate({
		// Rules for form validation
		rules : {
			feedBackComments : {
				required : true,
				maxlength : 100
			},
			feedBackRating : {
				required : true
			}
		},

		// Messages for form validation
		messages : {
			feedBackComments : {
				required : 'Please enter feedback comments',
					maxlength : 'Max length is 100 characters'
			},
			feedBackRating : {
				required : 'Please rate quality of the redressal'
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
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
	"order": [],
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
	},
	"aoColumnDefs": [{
        'bSortable': false,
        'aTargets': [0]
    }
]
});
})

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
								+"<td>"+obj.assinedName+" - "+obj.designation+"</td>"
								+"<th>Official Mobile No.</th>"
								+"<td>"+obj.officialMobileNo+"</td>"
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

var dialog = "";
function docketFeedbackDetailsPopUp(docketNo)
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
	
	$("#docketNumberSpanId").text(docketNo);
	$("#transDocNum").val(docketNo);
	dialog = $("#feedbackCommentsDiv").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
	}).dialog("open");
	
	$("#feedBackRating").val("");
	$("#feedBackComments").val("");
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
				window.location.reload();
		} 
	});
	$("#docketStatus").val(0);
	$("#remarks").val("");
}

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
	
function docketFeedbackDetailsValidation()
{
	alert("Feedback has already been captured for this docket");
}
</script>		