<%@page import="java.util.Date"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/exportToExcel.js"></script>
<div id="content">		

						<div class="jarviswidget jarviswidget-color-blueDark"  id="gridSupprtTeam" data-widget-editbutton="false" style="display: none;">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Details</h2>
									<div style="padding-top: 5px; padding-right: 2px;"></div>
								</header>
				
								<br><a href='javascript:void(0);' onclick='backToMonth()' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>Back to Support Team</a>
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body no-padding" style='overflow: scroll;'>
				
										<table id="datatatabletoolsgfdgfdg" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<!-- <th> <input type="checkbox" id="selectall"  onClick="selectAll(this)" /></th> -->
													<th data-hide="phone">Docket No.</th>
													<th data-class="expand">Docket Status</th>
													<th>Created Date</th>
													<th data-hide="phone">SLA Date&Time for Resolving</th>
													<th data-hide="phone">Time Pending</th>
													<th data-hide="phone">Resolved Date & Time</th>
													<!-- <th data-hide="phone">Time taken for resolving</th> -->
													<th data-hide="phone,tablet">Category</th>
													<th data-hide="phone,tablet">Sub Category</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th >AssignedTo</th>
													<th></th>
													
												</tr>
											</thead>
											<tbody id="supprtTeamAppendData">
											
												
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>


		
				<!--search form   -->
					<div class="jarviswidget" id="wididSearch" data-widget-editbutton="false" data-widget-custombutton="false">
				
				

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form id="orderform123" action="#" method="POST" class="smart-form" novalidate="novalidate">
							<fieldset>
								

								<div class="row">
								
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
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;" onclick="return getDateWiseData()">Search</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form>

					</div>
					
					
				</div>
				<!-- end widget div -->
				
			</div> 
	<div class="row" id="SupportNumBasedDetailsdsNew">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget jarviswidget-color-blueDark" id="Supportwid-" data-widget-editbutton="false">
								<header>
									<h2 ></h2>
										<div style="padding-top: 5px; padding-right: 2px;"><a class="btn btn-warning btn-xs" id="excelExport" style="float: right;" href="#" onclick="tableToExcel('SupportTableId', 'TeamWiseReport')">Export</a></div>
								</header>
								<div>
									<div class="widget-body no-padding" id="SupportComp">
										 <table id="SupportTableId" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
											<c:set var="now" value="<%=new java.util.Date()%>" />
											<tr><th colspan="7" style="text-align: center; font-size: 18px;"><span id="review">Review of tickets attended by CCCD Executives</span></th></tr>
											<tr>
									        <th data-hide="phone">Name</th>
									        <th data-class="expand"> Total Number Of Tickets Attended</th>
									   <!--      <th data-hide="phone"> Pending For Registration</th> -->
									        <th data-hide="phone,tablet">Marked As "Registered&Assigned"</th>
									        <th data-hide="phone,tablet">Marked As  "OnHold"</th>
									        <th data-hide="phone,tablet">Marked As "Resolved"</th>
									         <th data-hide="phone,tablet">Marked As "Reopened"</th>
									         <th data-hide="phone,tablet">Marked As "Invalid"</th>
								             </tr>
											</thead>
											<tbody id="getRrNumDetailsSupprt">
											<c:forEach var="support" items="${supprtTeam}">
											<tr>
											<td>${support.userName}</td>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','total')">${support.total}</a></td>
										<%-- 	<td>${support.pending}</td> --%>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','1')">${support.assigned}</a></td>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','2')">${support.onhold}</a></td>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','3')">${support.resolvd}</a></td>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','4')">${support.reopne}</a></td>
											<td><a href="#" onclick="readSupprtDetaqils('${support.uEmail}','5')">${support.invalid}</a></td>
											
											</tr>
											</c:forEach>
												
											</tbody>
										</table>			
									</div>				
								</div>				
							</div>	
			</article>
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
											<a data-toggle="tab" href="#s4" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
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
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Date</th>
															<th data-class="expand">Status</th>
															<!-- <th>Action</th> -->
															<th data-hide="phone">Action By</th>
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
										<div class="tab-pane fade" id="s4">	
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Created Date</th>
															<th data-hide="phone">Escalated Date</th>
															<th data-class="expand">Level</th>
															<!-- <th data-hide="phone">From Officer</th> -->
															<th data-hide="phone">Escalated To Officer</th>
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
</div>	

<script type="text/javascript">
$(document).ready(function() {
	
	$.validator.addMethod("regex", function(value, element, regexpr) {
		return regexpr.test(value);
	}, "");


	  $("#orderform123").validate({

		// Rules for form validation
		rules : {
			fromdate : {
				required : true
				
			},
			todate : {
				required : true
				
			},
		},

		// Messages for form validation
		messages : {
			fromdate : {
				required : 'Please select From Date'
			},
			todate : {
				required : 'Please select To Date'
			},
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	  
	  
	  pageSetUp();
		
		$('#fromdate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$('#todate').datepicker('option', 'minDate', selectedDate);
			}
		});
		
		$('#todate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$('#fromdate').datepicker('option', 'maxDate', selectedDate);
			}
		});
});
	
function getDateWiseData() {
	
	if($("#orderform123").valid())
		{
	var fromDate=$('#fromdate').val();
	var toDate=$('#todate').val();
	$('#review').html("Review of tickets attended by CCCD Executives");
	var tableData="";
	$.ajax({
		type : "POST",
		url : "./readSupportTeamDateWiseDetails",
		data:{
			fromDate:fromDate,
			toDate:toDate,
			
		},
		dataType : "json",
		success : function(response) {
			for ( var s = 0, len = response.length; s < len; ++s) {
              	var obj = response[s];
              	tableData += "<tr>"	
                +"<td>"+obj.userName+"</td>"
              	+"<td><a href='#' id='1' onclick=readSupprtDetaqils('"+obj.uEmail+"','total')>"+obj.total+"</a></td>"
             /*  	+"<td>"+obj.pending+"</td>" */
              	+"<td><a href='#' id='2' onclick=readSupprtDetaqils('"+obj.uEmail+"','1')>"+obj.assigned+ "</a></td>"
             	+"<td><a href='#' id='3' onclick=readSupprtDetaqils('"+obj.uEmail+"','2')>"+obj.onhold+"</a></td>" 
              	+"<td><a href='#' id='4' onclick=readSupprtDetaqils('"+obj.uEmail+"','3')>"+obj.resolvd+ "</a></td>"
              	+"<td><a href='#' id='5' onclick=readSupprtDetaqils('"+obj.uEmail+"','4')>"+obj.reopne+ "</a></td>"
              	+"<td><a href='#' id='6' onclick=readSupprtDetaqils('"+obj.uEmail+"','5')>"+obj.invalid+ "</a></td>"
                +"</tr>";
         }
			$('#getRrNumDetailsSupprt').html(tableData);
		}
	});
	return false;
		}
	
	else
		{
		return false;
		}
	
}	


function readSupprtDetaqils(email,status) {
	var uemail=email;
	var uStatus=status;
	var fromDate=$('#fromdate').val();
	var toDate=$('#todate').val();
	var tableData1="";
	$.ajax({
		type : "POST",
		url : "./readInAllDetailsWise",
		data:{
			fromDate:fromDate,
			toDate:toDate,
			uemail:uemail,
			uStatus:uStatus
			
		},
		dataType : "json",
		success : function(response) {
		
			
			for ( var s = 0, len = response.length; s < len; ++s) {
              	var obj = response[s];
              	tableData1 += "<tr>"		
	              	+"<td>"+obj.docketNumber+"</td>"
	              	+"<td>"+obj.docketStatus+"</td>"
	              	+"<td>"+obj.docketCreatedDt+ "</td>"
	              	+"<td>"+obj.estResolveTime+ "</td>"	
	            	+"<td>"+obj.pending+ "</td>"	
	            	+"<td>"+obj.resolvedDate+ "</td>"	
	            	+"<td>"+obj.categoryName+ "</td>"	
	            	+"<td>"+obj.subCategoryName+ "</td>"
	            	+"<td>"+obj.consumerName+ "</td>"
	            	+"<td>"+obj.consumerMobileNo+ "</td>";
	            	if(obj.assinedName==""){
	            		tableData1 +="<td>"+obj.assinedName+"</td>";
	            	}else{
	            		tableData1 +="<td>"+obj.assinedName+" - "+obj.assignedTo+" - "+obj.designation+" - "+obj.officialMobileNo+"</td>";
	            	}
	            	tableData1 +="<td><a class='btn btn-primary' onclick=docketDetailsViewPopUp('"+obj.docketNumber+"')>View</a></td>"
	            	+"</tr>";
         }
			
			 $('#datatatabletoolsgfdgfdg').dataTable().fnDestroy();
			$('#wididSearch').hide();
			$('#SupportNumBasedDetailsdsNew').hide();
			$('#supprtTeamAppendData').html(tableData1);
			$('#gridSupprtTeam').show();
			
			
		},
		 complete:function(response)
			{
				loadSearchAndFilter('datatatabletoolsgfdgfdg');
			}   
	});
	return false;
}


function backToMonth(){
	$('#gridSupprtTeam').hide();
	$('#wididSearch').show();
	$('#SupportNumBasedDetailsdsNew').show();
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
	
	
var docketNum;
function docketDetailsViewPopUp(docNo)
{
	docketNum = docNo;
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
		url : "./helpDesk/searchDocketNumber/"+docketNum,
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
		url : "./helpDesk/searchDocketHistory/"+docketNum,
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
		url : "./helpDesk/searchDocketEscHistory/"+docketNum,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              		docketEscHistroyTable += "<tr>"		
			              	+"<td>"+obj.serialNo+"</td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
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

</script>
	