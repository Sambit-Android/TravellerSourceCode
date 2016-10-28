<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 
<script src="./resources/js/exportToExcel.js"></script>






<div id="content">

					<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">

				<div>
					<div class="widget-body no-padding">
						
						<form:form id="order-form" action="./searchEscalation" method="POST" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">
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
										<label class="select">
											<form:select path="circleSiteCode" id="circleSiteCode" name="circleSiteCode">
												<option value="0" selected="" disabled="">Select Circle</option>
  												<form:option value="3">All</form:option>
  												<c:forEach items="${circleList}" var="circles">
												<form:option value="${circles.circleSiteCode}">${circles.circleName}</form:option>
												</c:forEach>
										    </form:select><i></i>
										</label>
									</section>
									<section class="col col-2">
										<label class="select">
											<form:select path="divisionSiteCode" id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
										    </form:select><i></i></label>
									</section>
									
									<section class="col col-2">
										<label class="select">
											<form:select path="siteCode" name="siteCode" id="siteCode">
												<option value="0" selected="" disabled="">Select Sub Division</option>
											</form:select> <i></i></label>
									</section>									
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 140px;">Search</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form:form>

					</div>
					
					
				</div>
				<!-- end widget div -->
			</div> 
			<c:if test="${filter!=null}">
			<div style='text-align:center'><font size='4px'>${filter}</font></div>
			</c:if>
			
	<div class="row">

		<div class="col-sm-12 col-md-12 col-lg-3" id="data11" style="padding-right: 0px;">
			<!-- new widget -->
			<div class="jarviswidget jarviswidget-color-blueDark" id="naren">
				<header>
					<h2>Level Wise Complaints</h2>
				</header>
				<div class="row" style="padding: 0px;">
					

						<table class="table table-bordered">
							<thead>
								<tr>
									<th style="width: 50%">Levels</th>
									<th style="width: 50%">No. Of Complaints</th>
								</tr>
							</thead>
							<tbody>
									<c:set var="sum" value="0"/> 
								<c:forEach items="${escalation}" var="esca">
									<c:if test="${esca.levels % 5==1}">
										<tr>
											<td><span class="label label-success">Level ${esca.levels}</span></td>
											<td><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','${esca.levels}')"><span class="badge bg-color-greenLight">${esca.notickets}</span></a></td>
										</tr>
										<c:set var="sum" value="${sum + esca.notickets}"/>
									</c:if>
									<c:if test="${esca.levels % 5==2}">
										<tr>
										
											<td><span class="label label-info">Level ${esca.levels}</span></td>
											<td><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','${esca.levels}')"><span class="badge bg-color-blueLight">${esca.notickets}</span></a></td>
										</tr>
										<c:set var="sum" value="${sum + esca.notickets}"/>
									</c:if>
									<c:if test="${esca.levels % 5==3}">
										<tr>
											<td><span class="label label-warning">Level ${esca.levels}</span></td>
											<td><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','${esca.levels}')"><span class="badge bg-color-orange">${esca.notickets}</span></a></td>
										</tr>
										<c:set var="sum" value="${sum + esca.notickets}"/>
									</c:if>
									<c:if test="${esca.levels % 5==4}">
										<tr>
											<td><span class="label label-danger">Level ${esca.levels}</span></td>
											<td><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','${esca.levels}')"><span class="badge bg-color-red">${esca.notickets}</span></a></td>
										</tr>
										<c:set var="sum" value="${sum + esca.notickets}"/>
									</c:if>
									<c:if test="${esca.levels % 5==0}">
										<tr>
											<td><span class="label label-danger">Level ${esca.levels}</span></td>
											<td><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','${esca.levels}')"><span class="badge bg-color-red">${esca.notickets}</span></a></td>
										</tr>
										<c:set var="sum" value="${sum + esca.notickets}"/>
									</c:if>
									
								</c:forEach>
							</tbody>
							
							<tfoot>
							<tr>
							<th>Grand&nbsp;Total:</th>
							<th><a href='#' onclick="getLevelDetails(${sitecode},'${fromdate}','${todate}','0')"><span class="badge bg-color-red">${sum}</span></a></th>
							</tr>
							</tfoot>
							
						</table>
					
				</div>
			</div>

		</div>
		<div class="col-sm-12 col-md-12 col-lg-9" id="data12" style="padding-left: 2px;" hidden="true">
			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">Escalated Complaints</span></h2>
								</header>
								<!-- widget div-->
								<div>
								<div class="jarviswidget-editbox"></div>
								<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'Escalated Complaints')">Export to Excel</a>&nbsp;&nbsp;
								<br><br>
								
									<div class="widget-body no-padding" id="monthContent">
										<table id="datatable_tabletools" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th data-hide="phone">Docket No.</th>
													<th data-hide="phone">Status</th>
													<th data-hide="phone,tablet">Created Date</th>
													<th data-hide="phone,tablet">Esclated Date</th>
													<th data-class="expand">SLA Date&Time for Resolving</th>
													<th data-hide="phone">Level</th>
													<th data-hide="phone">Category</th>
													<th data-hide="phone,tablet">Sub Category</th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="viewTicketDiv">
												<%-- <c:forEach items="${allTickets}" var="ticket">
												<tr>
													<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.estResolveDate}</td> 
													<td>${ticket.timePending}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td>
													<td>${ticket.consumerName}</td>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach> --%>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
		</div>
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
<script>
function getLevelDetails(sitecode,fromdate,todate,level){
	$('#data12').show();
	var FromDATE="${fromdate}";
	var ToDATE="${todate}";
	var CirDivSub="${CirDivSub}";
	$.ajax({
		url : "./esclation/escalationAnalysis",
		type : "POST",
		dataType : "JSON",
	data:{
		sitecode:sitecode,
		fromdate:fromdate,
		todate:todate,
		level:level,
	},
	success : function(response) {
	    if(level==0){
	    	level="All";
	    }
		$('.monthTabDetail').remove();
		var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'>" 
		+"<div id='naren1'>"
		+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
		+ "<thead>"
		+"<tr>"
	    +"<th colspan='14' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Location : "+CirDivSub+"<br>From date : "+FromDATE+"&nbsp;&nbsp;ToDate : "+ToDATE+"<br>Level "+level+" Complaints<br></th>"
	    +"</tr>"
		+"<tr>"
		+"<th data-hide='phone'>Docket No.</th>"
		+"<th data-hide='phone'>Status</th>"
		+"<th data-class='expand'>Created Date</th>"
		+"<th data-class='expand'>Esclated Date</th>"
		+"<th data-class='expand'>SLA Date&Time for Resolving</th>"
		+"<th data-class='expand'>Level</th>"
		+"<th>Category</th>"
		+"<th data-hide='phone'>SubCategory</th>"
		+"<th data-hide='phone,tablet'></th>"
	    +"</tr>"
				+"</thead>"
				+"<tbody>";
				var dataNew=response;
				 $.each(dataNew, function(index, data){
					htmlTable+= "<tr>"
						+"<td>"+data.docketNumber+"</td>"
						+"<td>"+data.docketStatus+"</td>"
						+"<td>"+data.docketCreatedDt+"</td>"
						+"<td>"+data.escalatedDate+"</td>"
						+"<td>"+data.estResolveTime+"</td>"
						+"<td>"+data.level+"</td>"
						+"<td>"+data.categoryName+"</td>"
						+"<td>"+data.subCategoryName+"</td>"
						+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
						+"</tr>";
					 
	         		});
			htmlTable +=""		
				+"</tbody>"
				+"</table></div></div>";
			
		//$('.monthtab').hide();
		$('#datatable_tabletools_wrapper').hide();
		$('#monthContent').append(htmlTable);
		
		pageSetUp();
		var responsiveHelper_dt_basic = undefined;
		var responsiveHelper_datatable_fixed_column = undefined;
		var responsiveHelper_datatable_col_reorder = undefined;
		var responsiveHelper_datatable_tabletools = undefined;
		var reportname="Escalated Complaints";
		var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                      Location : "+CirDivSub+"                                                                                        From date : "+FromDATE+"  ToDate : "+ToDATE+"                                                                                                                                 Level "+level+" Complaints";
	
		var breakpointDefinition = {
				tablet : 1024,
				phone : 480
			};
		$('#datatable_tabletools5')
		.dataTable(
		{
			"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-2 col-xs-2 hidden-xs'C>l>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-4 col-xs-12 hidden-xs'i><'col-sm-4 col-xs-12'p>>",
				  "oTableTools": {
				     	 "aButtons": [
				          
				             {
				                 "sExtends": "pdf",
				                 "sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
				                 "sFileName": reportname+".pdf",
				                 "sPdfMessage":lines,
				                 "sPdfSize": "letter"
				                 
				             },
				            
				          	{
				             	"sExtends": "print",
				             	"sButtonText":"<font color='#000000' size='3px'><i class='fa fa-print'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Print</font>",
				             	"sMessage": "Generated by PGRS_CESC <i>(press Esc to close)</i>"
				         	}
				          ],
				         "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
				     },
			"autoWidth" : true,
			"aLengthMenu": [[10, 15, 25, 50, 100 , -1], [10, 15, 25, 50, 100, "All"]],
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
			},
			"aoColumnDefs": [{
                'bSortable': false,
                'aTargets': [0]
            }
        ]
		}); 
	},

});
}
$(document).ready(function() {
	$('#fromdate').datepicker({
		dateFormat : 'mm/dd/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#todate').datepicker('option', 'minDate', selectedDate);
		}
	});
	
	$('#todate').datepicker({
		dateFormat : 'mm/dd/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#fromdate').datepicker('option', 'maxDate', selectedDate);
		}
	});
	
	$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		if(divisionSiteCode==1){
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#siteCode').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',0).text("Select Sub Division");
            $('#siteCode').append(defaultOption);
            defaultOption = $('<option>');
            defaultOption.attr('value',0).text("All");
            $('#siteCode').append(defaultOption);
		}else{
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#siteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#siteCode').append(defaultOption);
                defaultOption = $('<option>');
                defaultOption.attr('value',0).text("All");
                $('#siteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.subDivisionName);
					$('#siteCode').append(newOption);
				}));
			}
		});
		}
	});
	
	
	$('select[id*=circleSiteCode]').change(function() {
		var circleSiteCode = $("#circleSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllDivisions/" + circleSiteCode,
			dataType : "json",
			success : function(data) {
				
				if(data.length > 0){
					
					var newOption = $('<option>');
	                newOption.attr('value',0).text(" "); 
	                $('#divisionSiteCode').empty(newOption);
	                var defaultOption = $('<option>');
	                defaultOption.attr('value',0).text("Select Division");
	                $('#divisionSiteCode').append(defaultOption);
	                defaultOption = $('<option>');
	                defaultOption.attr('value',0).text("All");
	                $('#divisionSiteCode').append(defaultOption);
					($.map(data, function(item) {					 
						var newOption = $('<option>'); 					
						newOption.attr('value', item.siteCode).text(item.divisionName);
						$('#divisionSiteCode').append(newOption);
					}));
					
					
					
				}else{
					
					var newOption1 = $('<option>');
			        newOption1.attr('value',0).text(" "); 
			        $('#divisionSiteCode').empty(newOption1);
			        var defaultOption1 = $('<option>');
			        defaultOption1.attr('value',0).text("Select Division");
			        $('#divisionSiteCode').append(defaultOption1);
			        
			        
			        var newOption2 = $('<option>');
			        newOption2.attr('value',0).text(" "); 
			        $('#siteCode').empty(newOption2);
			        var defaultOption2 = $('<option>');
			        defaultOption2.attr('value',0).text("Select Sub Division");
			        $('#siteCode').append(defaultOption2);
				}
				
			}
		});
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

$.validator.addMethod("regex", function(value, element, regexpr) {
	return regexpr.test(value);
}, "");


  $("#order-form").validate({

	// Rules for form validation
	rules : {
		fromdate : {
			required : true
			
		},
		todate : {
			required : true
			
		},
		circleSiteCode : {
			required : true
			
		},
		divisionSiteCode : {
			required : true
			
		},
		siteCode : {
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
		circleSiteCode : {
			required : 'Please select Circle'
		},
		divisionSiteCode : {
			required : 'Please select Division'
		},
		siteCode : {
			required : 'Please select Sub Division'
		},
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});
	
	
});	
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
		              /* 	+"<td>"+obj.docketCreatedDt+"</td>" */
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
<style>
.jarviswidget {
    margin: 0px 0px 4px;
    }
</style>