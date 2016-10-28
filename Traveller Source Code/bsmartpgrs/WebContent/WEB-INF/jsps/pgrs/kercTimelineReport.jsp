<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<c:url value="/projecttree/map/read1" var="treeReadUrl" />
<script src="./resources/js/exportToExcel.js"></script>
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
								<label class="select"> <select name="reportName" id="reportName" onchange="showStatusValue(this.value);">
										<option value="Complaintssolvedwithin/beyondKERCtimelines">Complaints solved within/ beyond KERC timelines</option>
									</select> <i></i></label>
									<br>
							</section>
							
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label> 
										
									</section>
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
									<section class="col col-3">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;" onclick="return loadData()">Filter</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form:form>

					</div>
					
					
				</div>
				
			</div> 
	<div class="col-sm-12 col-md-12 col-lg-12" id="data12"
		style="padding-left: 13px; padding-right: 13px; padding-bottom: 37px;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>KERC Complaints Status</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'KERC report')">Export to Excel</a><br><br>
					<div class="widget-body no-padding" id="monthContent">
						<!-- <div style='text-align: center'>
							<font size='5px'></font>
						</div>
						<table id="datatable_tabletools" class="table table-striped table-bordered table-hover monthtab" width="100%">
							<thead>
								<tr>
									<th data-hide="phone">Category</th>
									<th data-class="expand">Total</th>
									<th>Resolved</th>
									<th data-hide="phone">Resolution rate in percentage</th>
								</tr>
							</thead>
						</table> -->
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
								circleSiteCode : {
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
							},

							// Do not change code below
							errorPlacement : function(error, element) {
								error.insertAfter(element.parent());
							}
						});

						pageSetUp();
						$("#naren").kendoTreeView({
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
						})
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

	
	
	
	
	
	function loadData() {
		 fromdate = $('#fromdate').val();
		 todate = $('#todate').val();
		 reportName = $('#reportName').val();
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
		
		if(reportName=="Complaintssolvedwithin/beyondKERCtimelines"){
			
		$.ajax({
					url : "./reports/showKERCStatusReport",
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
						var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'>"
								+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+ "<thead>"
								+"<tr><th colspan='8' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Complaints solved within/ beyond KERC timelines<br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
								+ "<tr>"
								+ "<th data-hide='phone'>Category</th>"
								+ "<th data-hide='phone'>Total number of complaints</th>"
								+ "<th data-hide='phone,tablet'>Total number of compalints resolved </th>"
								+ "<th data-hide='phone,tablet'>Resolved within timelines</th>"
								+ "<th data-hide='phone,tablet'>Resolved beyond timelines</th>"
								+ "</tr>" + "</thead>" + "<tbody>";
						$.each(dataNew, function(index, data)
								{
							htmlTable += "<tr></td>" + "<td>" + data.category
									+ "</td>" + "<td>" + data.totalComplaints + "</td>"
									+ "<td>" + data.totalResolved + "</td>" 
									+ "<td>" + data.resolvedwithintime + "</td>"
									+ "<td>" + data.resolvedtimeline + "</td>"
						});
						htmlTable += "</tbody>";
						$('.monthtab').hide();
						$('#datatable_tabletools_wrapper').hide();
						$('#monthContent').append(htmlTable);

						pageSetUp();
						var responsiveHelper_dt_basic = undefined;
						var responsiveHelper_datatable_fixed_column = undefined;
						var responsiveHelper_datatable_col_reorder = undefined;
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Complaints solved within/ beyond KERC timelines";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                                          "+reportname+"                                                                                                     From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
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
											"oTableTools" : {
												"aButtons" : [
														
														{
															"sExtends" : "pdf",
															"sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
															"sFileName": "Complaints solved within or beyond KERC timelines.pdf",
											                 "sPdfMessage":lines,
															"sPdfSize" : "letter"
														},
														{
															"sExtends" : "print",
															"sButtonText":"<font color='#000000' size='3px'><i class='fa fa-print'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Print</font>",
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
										});
					},

				});
		}
		
		return false;
	}
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
</style>		