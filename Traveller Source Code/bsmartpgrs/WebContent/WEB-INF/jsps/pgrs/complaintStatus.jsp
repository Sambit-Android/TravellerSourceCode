<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<script src="./resources/js/exportToExcel.js"></script>
<c:url value="/projecttree/map/read1" var="treeReadUrl" />
<div id="content">
					<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
				<div>
					<div class="jarviswidget-editbox"></div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form:form id="order-form" action="#" method="POST" class="smart-form" novalidate="novalidate">
							<fieldset>
								<div class="row">	
									<section class="col col-3">
								<label class="select"> <select name="siteCode" id="siteCode">
										<option value="" selected="">Select Report</option>
										<option value="complaint">Complaint Status Report</option>
										<option value="accident">Accident Report</option>
										<option value="transformer">Transformer complaints </option>
										<option value="resolved">Resolved Complaint</option>
										<option value="unresolved">UnResolved Complaint</option>
										<option value="esclated">Esclated Complaint</option>
										<option value="logged">complaints logged at Helpdesk</option>
										<option value="dailyReport">Day Wise Complaints</option>
									</select> <i></i></label>
									<br>
							</section>
									<div id="fromdate1">
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label> 
										
									</section>
									</div>
									
									<div id="todate1">
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
									</div>
									
									<div id="selectmonth" style="display: none;">
									<section class="col col-3">
										<label class="input">
											<input type="text" name="selectedMonth" id="selectedMonth" placeholder="Select Month">
										</label>
									</section>
									</div>
									
									<section class="col col-3">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;"  onclick="return loadData()">Generate Report</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form:form>

					</div>
					
					
				</div>
				
			</div> 
	<div class="col-sm-12 col-md-12 col-lg-12" id="data12"
		style="padding-left: 13px; padding-right: 11px; padding-bottom: 29px;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Complaints Status</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'Daily Report')">Export to Excel</a><br><br>
					<div class="widget-body no-padding" id="monthContent" style='overflow-x: scroll;'>
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>
						<table id="datatable_tabletools" class="table table-striped table-bordered table-hover monthtab" width="100%">
							<thead>
								<tr>
									<th data-hide="phone">Category of Complaints</th>
									<th data-class="expand">Total</th>
									<th>Pending for registration</th>
									<th data-hide="phone">Registered and Assigned</th>
									<th data-hide="phone">On Hold</th>
									<th data-hide="phone">Invalid</th>
									<th data-hide="phone,tablet">Resolved</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>






</div>
				
	<div id="alertsBox" title="Alert"></div>				
					

<script>
	$(document)
			.ready(
					function() {
						
						$.validator.addMethod("regex", function(value, element,
								regexpr) {
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
								siteCode : {
									required : 'Please select Report'
								},
							},

							// Do not change code below
							errorPlacement : function(error, element) {
								error.insertAfter(element.parent());
							}
						});

						pageSetUp();
						/* $("#naren").kendoTreeView({
							dataSource : {
								transport : {
									read : {
										url : "${transportReadUrlCat}",
										dataType : "json"
									}
								},
								schema : {
									model : {
										id : "id",
										hasChildren : "hasChilds"
									}
								}
							}
						}) */
						$('#fromdate')
								.datepicker(
										{
											dateFormat : 'mm/dd/yy',
											prevText : '<i class="fa fa-chevron-left"></i>',
											nextText : '<i class="fa fa-chevron-right"></i>',
											onSelect : function(selectedDate) {
												$('#todate').datepicker(
														'option', 'minDate',
														selectedDate);
											}
										});

						$('#todate')
								.datepicker(
										{
											dateFormat : 'mm/dd/yy',
											prevText : '<i class="fa fa-chevron-left"></i>',
											nextText : '<i class="fa fa-chevron-right"></i>',
											onSelect : function(selectedDate) {
												$('#fromdate').datepicker(
														'option', 'maxDate',
														selectedDate);
											}
										});
						
						
						
						$("#selectedMonth").keypress(function(event) {event.preventDefault();});
						  pageSetUp();
						  $("#selectedMonth").kendoDatePicker({
						        start: "decade",
						        depth: "year",
						        format: "MMM-yyyy",
						        min: new Date(2015, 1, 1),
						          max: new Date(),
						          readonly : true,
						    }); 
					
						
							var sitecode = $('#siteCode').val();
							if(sitecode=="dailyReport"){
								$('#todate1').hide();
								$('#fromdate1').hide();
								$('#selectmonth').show();
							}else{
								$('#todate1').show();
								$('#fromdate1').show();
								$('#selectmonth').hide();
							}
							
						var responsiveHelper_datatable_tabletools1 = undefined;
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
						$('#datatable_tabletools')
								.dataTable(
										{
											"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
													+ "t"
													+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
											"oTableTools" : {
												"aButtons" : [
														"copy",
														"csv",
														"xls",
														{
															"sExtends" : "pdf",
															"sTitle" : "SmartAdmin_PDF",
															"sPdfMessage" : "SmartAdmin PDF Export",
															"sPdfSize" : "letter"
														},
														{
															"sExtends" : "print",
															"sMessage" : "<i>(press Esc to close)</i>"
														} ],
												"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
											},
											"autoWidth" : true,
											"preDrawCallback" : function() {
												// Initialize the responsive datatables helper once.
												if (!responsiveHelper_datatable_tabletools1) {
													responsiveHelper_datatable_tabletools1 = new ResponsiveDatatablesHelper(
															$('#datatable_tabletools'),
															breakpointDefinition);
												}
											},
											"rowCallback" : function(nRow) {
												responsiveHelper_datatable_tabletools1
														.createExpandIcon(nRow);
											},
											"drawCallback" : function(oSettings) {
												responsiveHelper_datatable_tabletools1
														.respond();
											}
										});

					});
	var dochketStatus = "";
	var docketSource = "";
	var categoryId = 0;
	var subCategoryId = 0;
	var selectedMonth="";
	function loadData() {
		fromdate = $('#fromdate').val();
		todate = $('#todate').val();
		siteCode = $('#siteCode').val();
		selectedMonth = $('#selectedMonth').val();
		
		if(siteCode==""){
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Please select Report");
			$("#alertsBox").dialog({
				modal : true,
				draggable: false,
				resizable: false,
				buttons : {
					"Close" : function() {
					$(this).dialog("close");
				 }
			    }
		    });
			return false;
		}
		
		if(siteCode!="dailyReport"){
		if(fromdate==""){
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Please enter From Date");
			$("#alertsBox").dialog({
				modal : true,
				draggable: false,
				resizable: false,
				buttons : {
					"Close" : function() {
					$(this).dialog("close");
				 }
			    }
		    });
			return false;
		}
		
		if(todate==""){
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Please enter To Date");
			$("#alertsBox").dialog({
				modal : true,
				draggable: false,
				resizable: false,
				buttons : {
					"Close" : function() {
					$(this).dialog("close");
				 }
			    }
		    });
			return false;
		}
		}
		
		if(siteCode=="dailyReport"){
			if(selectedMonth==""){
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Please select Month");
				$("#alertsBox").dialog({
					modal : true,
					draggable: false,
					resizable: false,
					buttons : {
						"Close" : function() {
						$(this).dialog("close");
					 }
				    }
			    });
				return false;
			}
		}
		
		if(siteCode=="complaint"){
		$.ajax({
					url : "./reports/complaintStatusReport",
					type : "GET",
					dataType : "JSON",
					async : false,
					data : {
						fromdate : fromdate,
						todate : todate,
					},
					success : function(response) {
						$('.monthTabDetail').remove();
						var dataNew = response[0];
						var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
								+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+ "<thead>"
								+"<tr><th colspan='8' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Complaint Status Report <br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
								+ "<tr>"
								+ "<th data-hide='phone' width='40'>Sl No.</th>"
								+ "<th data-hide='phone'>Category of Complaints</th>"
								+ "<th data-class='expand'>Total</th>"
								+ "<th>Pending for registration</th>"
								+ "<th data-hide='phone'>Registered and Assigned</th>"
								+ "<th data-hide='phone'>On Hold</th>"
								+ "<th data-hide='phone'>Resolved</th>"
								+ "<th data-hide='phone,tablet'>Reopened</th>"
								+ "</tr>" + "</thead>" + "<tbody>";
								var i=1;
						$.each(dataNew, function(index, data) {
							if(data.category!="Grand Total"){
								htmlTable+= "<tr>"
									+"<td>"+(i++)+"</td>"
									+"<td>"+data.category+"</td>"
									+"<td>"+data.total+"</td>"
									+"<td>"+data.pending+"</td>"
									+"<td>"+data.assigned+"</td>"
									+"<td>"+data.onhold+"</td>"
									+"<td>"+data.resolved+"</td>"
									+"<td>"+data.reopened+"</td>"
									+"</tr>";
							}else{
								htmlTable+= "<tr>"
									+"<td></td>"
									+"<td><b>"+data.category+"</b></td>"
									+"<td><b>"+data.total+"</b></td>"
									+"<td><b>"+data.pending+"</b></td>"
									+"<td><b>"+data.assigned+"</b></td>"
									+"<td><b>"+data.onhold+"</b></td>"
									+"<td><b>"+data.resolved+"</b></td>"
									+"<td><b>"+data.reopened+"</b></td>"
									+"</tr>";
							}
							
						});
						htmlTable += "</tbody>";
						$('.monthtab').hide();
						$('#datatable_tabletools_wrapper').hide();
						$('#monthContent').append(htmlTable);

						/* pageSetUp();
						var responsiveHelper_dt_basic = undefined;
						var responsiveHelper_datatable_fixed_column = undefined;
						var responsiveHelper_datatable_col_reorder = undefined;
						var responsiveHelper_datatable_tabletools = undefined;
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						}; */
						/* $('#datatable_tabletools5')
								.dataTable(
										{
											"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
													+ "t"
													+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
											"oTableTools" : {
												"aButtons" : [
															"copy",
															"csv",
															"xls",
															{
																"sExtends" : "pdf",
																"sTitle" : "CESE_PGRS_PDF",
																"sPdfMessage" : "PGRS PDF Export",
																"sPdfSize" : "letter"
															},
														{
															"sExtends" : "print",
															"sMessage" : "<i>(press Esc to close)</i>"
														} ],
												"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
											},
											
											"autoWidth" : true,
											"preDrawCallback" : function() {
												// Initialize the responsive datatables helper once.
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
										}); */
										 pageSetUp();
										
										var responsiveHelper_datatable_tabletools = undefined;
										var reportname="Complaint Status Report";
										var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           		 "+response[1]+"                                                                                                                                               "+reportname+"                                                                                                                                From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
		}else if(siteCode=="resolved"){
			$.ajax({
				url : "./reports/resolvedReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					fromdate : fromdate,
					todate : todate,
				},
				success : function(response) {
					$('.monthTabDetail').remove();
					var dataNew = response[0];
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
					
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='14' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br> Resolved Complaint Report <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
						    +"</tr>"
							+"<tr>"
							+"<th data-hide='phone' width='40''>Sl No</th>"
							+"<th data-hide='phone'>Name of Consumer</th>"
							+"<th data-class='expand'>Consumer Contact No.</th>"
							+"<th>Docket No.</th>"
							+"<th data-hide='phone'>Date of Complaints Received </th>"
							+"<th data-hide='phone,tablet'>Address</th>"
							+"<th data-hide='phone,tablet'>Complaint Category</th>"
							+"<th data-hide='phone,tablet'>Complaint Subcategory</th>"
							+"<th data-hide='phone,tablet'>Complaint Summary</th>"
							+"<th data-hide='phone,tablet'>Assigned official</th>"
							+"<th data-hide='phone,tablet'>SLA time for Resolution</th>"
							+"<th data-hide='phone,tablet'>Resolved Time</th>"
							+"<th data-hide='phone,tablet'>Time Taken</th>"
							+"<th data-hide='phone,tablet'>Within SLA time (Y/N)</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						    var i=1;
					$.each(dataNew, function(index, data) {

						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.address+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.assinedName+" - "+data.designation+" - "+data.officialMobileNo+"</td> "
							+"<td>"+data.estResolveTime+"</td>"
							+"<td>"+data.resolvedDate+"</td>"
							+"<td>"+data.totalTime+"</td>"
							+"<td>"+data.withIn+"</td>"
							+"</tr>";
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').append(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Resolved Complaint Report";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          "+reportname+"                                                                                                                             					From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
						                 "sPdfMessage":lines,
						                 "sPdfSize": "letter",
						                 "sFileName": reportname+".pdf",
						                 
						                 
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
		}else if(siteCode=="logged"){
			$.ajax({
				url : "./reports/loggedReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					fromdate : fromdate,
					todate : todate,
				},
				success : function(response) {
					$('.monthTabDetail').remove();
					var dataNew = response[0];
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
					
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='12' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Detailed summary of complaints Logged at Helpdesk <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
						    +"</tr>"
							+"<tr>"
							+"<th data-hide='phone' width='10%;''>Sl No.</th>"
							+"<th data-hide='phone'>Name of Consumer</th>"
							+"<th data-class='expand'>Consumer Contact No.</th>"
							+"<th>Docket No.</th>"
							+"<th data-hide='phone'>Date of Complaints Received </th>"
							+"<th data-hide='phone,tablet'>Address</th>"
							+"<th data-hide='phone,tablet'>Complaint Category</th>"
							+"<th data-hide='phone,tablet'>Complaint Subcategory</th>"
							+"<th data-hide='phone,tablet'>Complaint Summary</th>"
							+"<th data-hide='phone,tablet'>Complaint Mode</th>"
							+"<th data-hide='phone,tablet'>Complaint Assigned To</th>"
							+"<th data-hide='phone,tablet'>Complaint Attended BY</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						    var i=1;
					$.each(dataNew, function(index, data) {

						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.address+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.docketSource+"</td> "
							+"<td>"+data.assinedName+" - "+data.designation+" - "+data.officialMobileNo+"</td> "
							+"<td>"+data.attendedBy+" - "+data.attendedDesig+" - "+data.attendedMobile+"</td> "
							+"</tr>";
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').append(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Detailed summary of complaints Logged at Helpdesk";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          "+reportname+"                                                                               							         From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
		}else if(siteCode=="unresolved"){
			$.ajax({
				url : "./reports/unResolvedReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					fromdate : fromdate,
					todate : todate,
				},
				success : function(response) {
					$('.monthTabDetail').remove();
					var dataNew = response[0];
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='12' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Un-Resolved Complaint Report <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
						    +"</tr>"
							+"<tr>"
							+"<th data-hide='phone'>Sl No.</th>"
							+"<th data-hide='phone'>Name of Consumer</th>"
							+"<th data-class='expand'>Consumer Contact No.</th>"
							+"<th>Docket No.</th>"
							+"<th data-hide='phone'>Date of Complaints Received </th>"
							+"<th data-hide='phone,tablet'>Address</th>"
							+"<th data-hide='phone,tablet'>Complaint Category</th>"
							+"<th data-hide='phone,tablet'>Complaint Subcategory</th>"
							+"<th data-hide='phone,tablet'>Complaint Summary</th>"
							+"<th data-hide='phone,tablet'>Assigned official</th>"
							+"<th data-hide='phone,tablet'>SLA time for Resolution</th>"
							+"<th data-hide='phone,tablet'>Time Pending</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						    var i=1;
					$.each(dataNew, function(index, data) {

						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.address+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.assinedName+" - "+data.designation+" - "+data.officialMobileNo+"</td> "
							+"<td>"+data.estResolveTime+"</td>"
							+"<td>"+data.totalTime+"</td>"
							+"</tr>";
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').append(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Un-Resolved Complaint Report";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          "+reportname+"                                                                                                                             From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
		}else if(siteCode=="transformer"){
			$.ajax({
				url : "./reports/transformerReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					fromdate : fromdate,
					todate : todate,
				},
				success : function(response) {
					$('.monthTabDetail').remove();
					var dataNew = response[0];
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='9' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Transformer Complaint Report <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
						    +"</tr>"
							+"<tr>"
							+"<th data-hide='phone' width='40'>Sl No</th>"
							+"<th data-hide='phone'>Name of Consumer</th>"
							+"<th data-class='expand' width='140'>Consumer Contact No.</th>"
							+"<th>Docket No</th>"
							+"<th data-hide='phone'>Date of Complaints Received </th>"
							+"<th data-hide='phone,tablet'>Address</th>"
							+"<th data-hide='phone,tablet'>Nature of Complaint</th>"
							/* +"<th data-hide='phone,tablet'>Complaint Subcategory</th>" */
							+"<th data-hide='phone,tablet'>Complaint Summary</th>"
							+"<th data-hide='phone,tablet'>Complaint Status</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						    var i=1;
					$.each(dataNew, function(index, data) {

						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.address+"</td>"
							/* +"<td>"+data.categoryName+"</td>" */
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.docketStatus+"</td>"
							+"</tr>";
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').append(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Transformer Complaint Report";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           		"+response[1]+"                                                                                                                                         		 "+reportname+"                                                                                                                             	From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
		}else if(siteCode=="accident"){
			$.ajax({
				url : "./reports/accidentStatusReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					fromdate : fromdate,
					todate : todate,
				},
				success : function(response) {
					$('.monthTabDetail').remove();
					var dataNew = response[0];
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='8' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Major Interruptions and accident reports <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
						    +"</tr>"
							+ "<tr>"
							+ "<th data-hide='phone' width='10%;''>Sl No.</th>"
							+ "<th data-hide='phone'>Category of Complaints</th>"
							+ "<th data-class='expand'>Total</th>"
							+ "<th>Pending for registration</th>"
							+ "<th data-hide='phone'>Registered and Assigned</th>"
							+ "<th data-hide='phone'>On Hold</th>"
							+ "<th data-hide='phone'>Resolved</th>"
							+ "<th data-hide='phone,tablet'>Reopened</th>"
							+ "</tr>" + "</thead>" + "<tbody>";
							var i=1;
					$.each(dataNew, function(index, data) {
						if(data.category!="Grand Total"){
						if(data.total!=0){
						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.category+"</td>"
							+"<td>"+data.total+"</td>"
							+"<td>"+data.pending+"</td>"
							+"<td>"+data.assigned+"</td>"
							+"<td>"+data.onhold+"</td>"
							+"<td>"+data.resolved+"</td>"
							+"<td>"+data.reopened+"</td>"
							+"</tr>";
						}
						}else{
							htmlTable+= "<tr>"
								+"<td></td>"
								+"<td><b>"+data.category+"</b></td>"
								+"<td><b>"+data.total+"</b></td>"
								+"<td><b>"+data.pending+"</b></td>"
								+"<td><b>"+data.assigned+"</b></td>"
								+"<td><b>"+data.onhold+"</b></td>"
								+"<td><b>"+data.resolved+"</b></td>"
								+"<td><b>"+data.reopened+"</b></td>"
								+"</tr>";
						}
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').append(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Major Interruptions and accident reports";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          "+reportname+"                                                                                                                     From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
	}else if(siteCode=="esclated"){
		$.ajax({
			url : "./reports/esclatedStatusReport",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				fromdate : fromdate,
				todate : todate,
			},
			success : function(response) {
				$('.monthTabDetail').remove();
				var dataNew = response[0];
				var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
						+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
						+ "<thead>"
						+"<tr>"
					    +"<th colspan='17' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+"<br>Esclated Complaint Report <br>From Date : "+fromdate+" To Date : "+todate+"</th>"
					    +"</tr>"
						+ "<tr>"
						+ "<th data-hide='phone' width='10%;''>Sl No.</th>"
						+ "<th data-hide='phone'>Consumer Name</th>"
						+ "<th data-class='expand'>Consumer Contact No.</th>"
						+ "<th>Docket No.</th>"
						+ "<th data-hide='phone'>Date of Complaint Received</th>"
						+ "<th data-hide='phone'>Time of Complaint Recevied</th>"
						+ "<th data-hide='phone'>Address</th>"
						+ "<th data-hide='phone,tablet'>Complaint Category</th>"
						+ "<th data-hide='phone,tablet'>Complaint Sub-Category</th>"
						+ "<th data-hide='phone,tablet'>Complaint Summary</th>"
						+ "<th data-hide='phone,tablet'>Assigned Official</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 1</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 2</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 3</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 4</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 5</th>"
						+ "<th data-hide='phone,tablet'>Esclated Level 6</th>"
						+ "</tr>" + "</thead>" + "<tbody>";
						var i=1;
				$.each(dataNew, function(index, data) {
					htmlTable+= "<tr>"
						+"<td>"+(i++)+"</td>"
						 +"<td>"+data.consumername+"</td>"
						 +"<td>"+data.consumermobilenumber+"</td>"
						 +"<td>"+data.docketnumber+"</td>"
						 +"<td>"+data.createdDate+"</td>"
						 +"<td>"+data.createdtime+"</td>"
						 +"<td>"+data.address+"</td>"
						 +"<td>"+data.category_name+"</td>"
						 +"<td>"+data.subcategory_name+"</td>"
						 +"<td>"+data.docketsummary+"</td>"
						 +"<td>"+data.name+" - "+data.dn_name+" - "+data.ur_contact+"</td>"
						 +"<td>"+data.level1+"</td>"
						 +"<td>"+data.level2+"</td>"
						 +"<td>"+data.level3+"</td>"
						 +"<td>"+data.level4+"</td>"
						 +"<td>"+data.level5+"</td>"
						 +"<td>"+data.level6+"</td>"
						+"</tr>";
				});
				htmlTable += "</tbody>";
				$('.monthtab').hide();
				$('#datatable_tabletools_wrapper').hide();
				$('#monthContent').append(htmlTable);

				 pageSetUp();
					
					var responsiveHelper_datatable_tabletools = undefined;
					var reportname="Esclated Complaint Report ";
					var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          			"+reportname+"                                                                                                                             			From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
}else if(siteCode=="dailyReport"){
	$.ajax({
		url : "./reports/dayWiseReport",
		type : "GET",
		dataType : "JSON",
		async : false,
		data : {
			selectedMonth : selectedMonth,
			
		},
		success : function(response) {
			$('.monthTabDetail').remove();
			var dataNew = response[1];
			var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
					+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
					+ "<thead>"
					+"<tr>"
				    +"<th colspan='16' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Location : CESC<br>Day Wise Complaints Report <br>Selected Month : "+selectedMonth+"</th>"
				    +"</tr>"
					+ "<tr>"
					+ "<th data-hide='phone' width='13%;''>Date</th>";
					for(var i=0;i<response[0].length;i++){
						htmlTable+= "<th data-hide='phone'>"+response[0][i]+"</th>";
						
					}
					htmlTable+= "<th data-hide='phone,tablet'><b>Total</b></th>"
					+ "</tr>" + "</thead>" + "<tbody>";
					var i=1;
			   var d1=0;
			   var d2=0;
			   var d3=0;
			   var d4=0;
			   var d5=0;
			   var d6=0;
			   var d7=0;
			   var d8=0;
			   var d9=0;
			   var d10=0;
			   var d11=0;
			   var d12=0;
			   var d13=0;
			   var d14=0;
			   var d15=0;
			   
			   $.each(dataNew, function(index, data) {
				if(data.total != 0){
				htmlTable+= "<tr>"
					+"<td>"+data.date+"</td>"
					for(var i=0;i<data.category.length;i++){
					
						if(i==0)
						{
						d1+=data.category[i];
						}
						if(i==1)
						{
						d2+=data.category[i];
						}
						if(i==2)
						{
						d3+=data.category[i];
						}
						if(i==3)
						{
						d4+=data.category[i];
						}
						if(i==4)
						{
						d5+=data.category[i];
						}
						if(i==5)
						{
						d6+=data.category[i];
						}
						if(i==6)
						{
						d7+=data.category[i];
						}
						if(i==7)
						{
						d8+=data.category[i];
						}
						if(i==8)
						{
						d9+=data.category[i];
						}
						if(i==9)
						{
						d10+=data.category[i];
						}
						if(i==10)
						{
						d11+=data.category[i];
						}
						if(i==11)
						{
						d12+=data.category[i];
						}
						if(i==12)
						{
						d13+=data.category[i];
						}
						if(i==13)
						{
						d14+=data.category[i];
						}
						htmlTable+= "<td>"+data.category[i]+"</td>";
						
					}
					htmlTable+= "<td><b>"+data.total+"</b></td>";
					d15+=data.total;
					+"</tr>";
				}
			});
			htmlTable += "</tbody>";
			
			htmlTable += "<tfoot>"
				+ "<tr>"
				+ " <th>Grand&nbsp;Total:</th>" 
				+ " <th>"+d1+"</th>"
				+ " <th>"+d2+"</th>"
				+ " <th>"+d3+"</th>"
				+ " <th>"+d4+"</th>"
				+ " <th>"+d5+"</th>"
				+ " <th>"+d6+"</th>"
				+ " <th>"+d7+"</th>"
				+ " <th>"+d8+"</th>"
				+ " <th>"+d9+"</th>"
				+ " <th>"+d10+"</th>"
				+ " <th>"+d11+"</th>"
				+ " <th>"+d12+"</th>"
				+ " <th>"+d13+"</th>"
				+ " <th>"+d14+"</th>"
				+ " <th>"+d15+"</th>"
				+ " </tr>"
				+ "</tfoot>";
			
			$('.monthtab').hide();
			$('#datatable_tabletools_wrapper').hide();
			$('#monthContent').append(htmlTable);

			 pageSetUp();
				
				var responsiveHelper_datatable_tabletools = undefined;
				var reportname="Day Wise Complaints Report";
				var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           Location : CESC                                                                                                                                               "+reportname+"                                                                                                                                         Selected Month : "+selectedMonth+"";
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
			"aLengthMenu": [[31 , -1], [31, "All"]],
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
		$('#siteCode').val("");
		$('#selectedMonth').val("");
		},

	});
}
		return false;
	}
	
$("#siteCode").change(function(){
	var sitecode = $('#siteCode').val();
	if(sitecode=="dailyReport"){
		$('#todate1').hide();
		$('#fromdate1').hide();
		$('#selectmonth').show();
	}else{
		$('#todate1').show();
		$('#fromdate1').show();
		$('#selectmonth').hide();
	}

});
	
	
	
</script>
<style>
#content {
    padding: 2px 0px;
    position: relative;
}
.jarviswidget {
    margin: 0px 0px 0px;
}
.smart-form section {
    margin-bottom: 0px;
    position: relative;
}
.smart-form .state-error + em {
    margin-top: -2px;
    padding: 0px 0px; 
}
#datatable_tabletools5 {
    width: 100% !Important;
}
</style>		