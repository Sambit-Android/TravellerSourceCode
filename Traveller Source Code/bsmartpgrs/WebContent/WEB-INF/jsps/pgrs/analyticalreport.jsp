<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>
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
								<label class="select"> <select name="reportName" id="reportName" onchange="showStatusValue(this.value);">
										<option value="dockets/complaints" selected="">Dockets/ complaints which are resolved, unresolved and escalated</option>
										 <option value="compliantstatus">Trend analysis of Complaint status</option>
										 <option value="averagetime">Trend analysis Average time breached for complaints</option>
										<!-- <option value="averagetimebreachedforthedockets">Average time breached for the dockets / complaints </option>
										<option value="complaintsandresolution">Performance of complaints and their resolution</option> -->
										<option value="CustomerSatisfactionResults">Customer Satisfaction Results</option>
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
									<section class="col col-3" id="statusId" hidden="true">
								<label class="select"> <select name="status" id="status" >
										<option value="resolved" selected="">Resolved</option>
										<option value="unresolved">Unresolved</option>
									</select> <i></i></label>
									<br>
							</section>
							<section class="col col-3" id="statusId1" hidden="true">
								<label class="select"> <select name="status1" id="status1" >
										<option value="resolved" selected="">Resolved</option>
										<option value="unresolved">Unresolved</option>
										<!-- <option value="escalated">Escalated</option> -->
									</select> <i></i></label>
									<br>
							</section>
									<section class="col col-3">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 106px;" onclick="return loadData()">Generate Report</button>
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 106px;" onclick="return loadGraphicalData()">Graphical View</button>
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
					<span class="widget-icon">
					</span>
					<h2></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'Analytical Report')">Export to Excel</a>
					<div class="widget-body no-padding" id="monthContent">
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>		
						<div  class="dropzone" id="mydropzone" style="min-width: 300px; height: 460px; margin: 0 auto"><center style="padding-top: 10%;"><span style="font-size: 40px; color: #CBCBC5">Analytical Report</span></center></div>
					</div>
				</div>
			</div>
		</div>
	</div>






</div>
	<div id="container" style="min-width: 300px; height: 460px; margin: 0 auto"></div>			
	<div id="alertsBox" title="Alert"></div>				
					

<script>
function loadGraphicalData(){
	fromdate = $('#fromdate').val();
	 todate = $('#todate').val();
	 reportName = $('#reportName').val();
	 status = $('#status').val();
	 
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
		if(reportName==""){
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Please select report");
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
		 var statusVal='';
		 if(status=='resolved')
		 {
		   statusVal="Resolved";
		 }
		 else if(status=='unresolved')
		 {
		    statusVal="Unresolved";
		 }
		 
		 
		 status1 = $('#status1').val();
		 var statusVal1='';
		 if(status1=='resolved')
		 {
		   statusVal1="Resolved";
		 }
		 else if(status1=='unresolved')
		 {
		    statusVal1="Unresolved";
		 }
		 
		 if(reportName=='averagetime'){
				$.ajax({
					url : "./reports/showAverageTimeBreachedGraphicalReport",
					type : "GET",
					dataType : "JSON",
					async : false,
					data : {
						fromdate : fromdate,
						todate : todate,
						status1: status1,
					},
					success : function(response) {
						$('#data12').hide();
						Highcharts.setOptions({
					        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
					    });
						$('#container').highcharts({
					        title: {
					            text: 'Trend analysis Average time breached for '+status1+' complaints<br>'+response[2]+' <br>From Date : '+fromdate+' To Date : '+todate,
					        },
					        
					        xAxis: {
					        	categories: response[0],
					            type: 'category',
					            labels: {
					                rotation: -45,
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif',
					                },
					        		overflow : true,
					            }
					        },
					        yAxis: {
					            min: 0,
					            title: {
					                text: 'Resolution Rate(%)'
					            }
					        },
					        plotOptions: {
					            series: {
					                dataLabels:{
					                    enabled:true,
					                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333',
					                    formatter:function(){
					                        if(this.y > 0)
					                            return  Highcharts.numberFormat(this.y, 2);
					                    }
					                }
					            },
					        },
					        legend: {
					            align: 'right',
					            verticalAlign: 'top',
					            layout: 'vertical',
					            x: 0,
					            y: 100
					        },
					        series: [{
					            name: '	Avg time breached in hours',
					            type: 'column',
					            data:response[1],
					            dataLabels: {
					                enabled: true,
					                formatter:function(){
				                        if(this.y > 0)
				                            return this.y+' hrs';
				                    },
					                //rotation: -90,
					                color: '#FFFFFF',
					                align: 'center',
					                y: 10, // 10 pixels down from the top
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif'
					                },
					                
					            }
					        } , {
					            name: 'Linear(Avg time breached in hours)',
					            type: 'spline',
					            data:response[1],
					            dataLabels: {
					                enabled: false,
					                formatter:function(){
				                        if(this.y > 0)
				                            return this.y;
				                    },
					                //rotation: -90,
					                color: '#FFFFFF',
					                align: 'right',
					                y: 10, // 10 pixels down from the top
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif'
					                },
					                
					            }
					            
					            
					            
					        }]
					    });
					}
				});
				
		 }	
		if(reportName=="dockets/complaints"){
			
		$.ajax({
					url : "./reports/showComplaintStatusGraphicalReport",
					type : "GET",
					dataType : "JSON",
					async : false,
					data : {
						fromdate : fromdate,
						todate : todate,
						status: status,
					},
					success : function(response) {
						$('#data12').hide();
						Highcharts.setOptions({
					        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
					    });
						$('#container').highcharts({
					        title: {
					            text: 'Trend analysis of dockets/ complaints which are '+status+'<br>'+response[0]+' <br>From Date : '+fromdate+' To Date : '+todate,
					        },
					        
					        xAxis: {
					        	categories: response[3],
					            type: 'category',
					            labels: {
					                rotation: -45,
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif',
					                },
					        		overflow : true,
					            }
					        },
					        yAxis: {
					            min: 0,
					            title: {
					                text: 'Resolution Rate(%)'
					            }
					        },
					        plotOptions: {
					            series: {
					                dataLabels:{
					                    enabled:true,
					                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333',
					                    formatter:function(){
					                        if(this.y > 0)
					                            return  Highcharts.numberFormat(this.y, 2);
					                    }
					                }
					            },
					        },
					        legend: {
					            align: 'right',
					            verticalAlign: 'top',
					            layout: 'vertical',
					            x: 0,
					            y: 100
					        },
					        tooltip: {
					        	formatter: function(){
					            	 var index = this.point.x;
					                    var comment = response[1][index];
					                    var comment1 = response[2][index];
					                    return 'Number of complaints '+status+' : '+comment+'<br>Total Number of complaints:'+comment1;	
					            }
					        },
					        series: [{
					            name: 'Column Estimation',
					            type: 'column',
					            data:response[4],
					            dataLabels: {
					                enabled: true,
					                formatter:function(){
				                        if(this.y > 0)
				                            return this.y+' %';
				                    },
					                //rotation: -90,
					                color: '#FFFFFF',
					                align: 'right',
					                y: 10, // 10 pixels down from the top
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif'
					                },
					                
					            }
					        } , {
					            name: 'Linear Estimation',
					            type: 'spline',
					            data:response[4],
					            dataLabels: {
					                enabled: true,
					                formatter:function(){
				                        if(this.y > 0)
				                            return this.y+' %';
				                    },
					                //rotation: -90,
					                color: '#FFFFFF',
					                align: 'right',
					                y: 10, // 10 pixels down from the top
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Verdana, sans-serif'
					                },
					                
					            }
					            
					            
					            
					        }]
					    });
						
		}
		});
		}else if(reportName=="compliantstatus"){
			
			$.ajax({
						url : "./reports/compliantstatusGraphicalReport",
						type : "GET",
						dataType : "JSON",
						async : false,
						data : {
							fromdate : fromdate,
							todate : todate,
							status: status,
						},
						success : function(response) {
							$('#data12').hide();
							/* Highcharts.setOptions({
						        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
						    }); */
							 $('#container').highcharts({
							        chart: {
							            type: 'column'
							        },
							        title: {
							            text: 'Trend analysis of complaints Status <br>'+response[2]+' <br>From Date : '+fromdate+' To Date : '+todate,
							        },
							        xAxis: {
							            categories: response[0]
							        },
							        yAxis: {
							            min: 0,
							            title: {
							                text: 'Number of Complaints'
							            },
							            stackLabels: {
							                enabled: false,
							                style: {
							                    fontWeight: 'bold',
							                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							                }
							            }
							        },
							        legend: {
							        	 align: 'right',
								            verticalAlign: 'top',
								            layout: 'vertical',
								            x: 0,
								            y: 100
							        },
							        tooltip: {
							            headerFormat: '<b>{point.x}</b><br/>',
							            pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							        },
							        plotOptions: {
							            column: {
							                stacking: 'normal',
							                dataLabels: {
							                    enabled: true,
							                    formatter: function(){
							                        console.log(this);
							                        var val = this.y;
							                        if (val == 0) {
							                            return '';
							                        }
							                        return val;
							                    },
							                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
							                    style: {
							                        textShadow: '0 0 3px black'
							                    }
							                }
							            }
							        },
							        series: response[1],
							    });
							
			}
			});
			
		}else if(reportName=="CustomerSatisfactionResults"){
			
			$.ajax({
						url : "./reports/CustomerSatisfactionGraphicalReport",
						type : "GET",
						dataType : "JSON",
						async : false,
						data : {
							fromdate : fromdate,
							todate : todate,
							status: status,
						},
						success : function(response) {
							$('#data12').hide();
							/* Highcharts.setOptions({
						        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
						    }); */
							 $('#container').highcharts({
							        chart: {
							            type: 'column'
							        },
							        title: {
							            text: 'Trend analysis of Customer Satisfaction Results <br>'+response[2]+' <br>From Date : '+fromdate+' To Date : '+todate,
							        },
							        xAxis: {
							            categories: response[0]
							        },
							        yAxis: {
							            min: 0,
							            title: {
							                text: 'Number of Complaints'
							            },
							            stackLabels: {
							                enabled: false,
							                style: {
							                    fontWeight: 'bold',
							                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							                }
							            }
							        },
							        legend: {
							        	 align: 'right',
								            verticalAlign: 'top',
								            layout: 'vertical',
								            x: 0,
								            y: 100
							        },
							        tooltip: {
							            headerFormat: '<b>{point.x}</b><br/>',
							            pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							        },
							        plotOptions: {
							            column: {
							                stacking: 'normal',
							                dataLabels: {
							                    enabled: true,
							                    formatter: function(){
							                        console.log(this);
							                        var val = this.y;
							                        if (val == 0) {
							                            return '';
							                        }
							                        return val;
							                    },
							                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
							                    style: {
							                        textShadow: '0 0 3px black'
							                    }
							                }
							            }
							        },
							        series: response[1],
							    });
							
			}
			});
			
		}
return false;
}


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
						$('#excelExport').hide();
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

	
	
	 function showStatusValue(reportName)
	{
		if(reportName=='dockets/complaints')
			{
			 $('#statusId').show();
			}
		else
			{
			   $('#statusId').hide();
			}
		if(reportName=='averagetime' )
			{
			 $('#statusId1').show();
			}
		else
			{
			   $('#statusId1').hide();
			}
		
		
		
	} 
	
	
	function loadData() {
		 fromdate = $('#fromdate').val();
		 todate = $('#todate').val();
		 reportName = $('#reportName').val();
		 status = $('#status').val();
		 status1 = $('#status1').val();
		 $('#data12').show();
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
			if(reportName==""){
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Please Select Report");
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
		 var statusVal='';
		 $('#excelExport').show();
		 if(status=='resolved')
		 {
		   statusVal="Resolved";
		 }
		 else if(status=='unresolved')
		 {
		    statusVal="Unresolved";
		 }
		 var statusVal1='';
		 if(status1=='resolved')
		 {
		   statusVal1="Resolved";
		 }
		 else if(status1=='unresolved')
		 {
		    statusVal1="Unresolved";
		 }else{
			 statusVal1="Escalated"; 
		 }
		if(reportName=='averagetime'){
				$.ajax({
					url : "./reports/showAverageTimeBreached",
					type : "GET",
					dataType : "JSON",
					async : false,
					data : {
						fromdate : fromdate,
						todate : todate,
						status1: status1,
					},
					success : function(response) {
						$('.monthTabDetail').remove();
						var dataNew = response[0];
						if(status1=='resolved'){
						var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
								+ "<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+ "<thead>"
								+"<tr><th colspan='6' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Trend analysis Average time breached for "+statusVal1+" complaints<br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
								+ "<tr>"
								+ "<th data-hide='phone'>Category</th>"
								+ "<th data-hide='phone'>Total</th>"
								+ "<th data-hide='phone,tablet'>"+statusVal1+"</th>"
								+ "<th data-hide='phone,tablet'>Average time to resolve</th>"
								+ "<th data-hide='phone,tablet'>Average of Actual time taken</th>"
								+ "<th data-hide='phone,tablet'>Average time breached in hours</th>"
								+ "</tr>" 
								
								+ "</thead>" + "<tbody>";
						$.each(dataNew, function(index, data)
								{
							htmlTable += "<tr></td>" + "<td>" + data.category+"</td>" 
									+ "<td>" + data.total + "</td>"
									+ "<td>" + data.resolved + "</td>" 
									+ "<td>" + data.estavgresolved + "</td>" 
									+ "<td>" + data.avgresolved + "</td>"
									+"<td>" + data.resPercentage + "</td></tr>";	
									
								
									
						});
						htmlTable += "</tbody></table></div>";
						$('.monthtab').hide();
						$('#datatable_tabletools_wrapper').hide();
						$('#monthContent').html(htmlTable);
						}else if(status1=='unresolved'){
							var htmlTable = "<br><div class='monthTabDetail' style='overflow-x: scroll;'>"
								+ "<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+ "<thead>"
								+"<tr><th colspan='6' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Trend analysis Average time breached for "+statusVal1+" complaints <br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
								+ "<tr>"
								+ "<th data-hide='phone'>Category</th>"
								+ "<th data-hide='phone'>Total</th>"
								+ "<th data-hide='phone,tablet'>"+statusVal1+"</th>"
								+ "<th data-hide='phone,tablet'>Average time to resolve</th>"
								+ "<th data-hide='phone,tablet'>Average of Actual time taken</th>"
								+ "<th data-hide='phone,tablet'>Average time breached in hours</th>"
								+ "</tr>" 
								
								+ "</thead>" + "<tbody>";
						$.each(dataNew, function(index, data)
								{
							htmlTable += "<tr></td>" + "<td>" + data.category+"</td>" 
									+ "<td>" + data.total + "</td>"
									+ "<td>" + data.resolved + "</td>" 
									+ "<td>" + data.estavgresolved + "</td>" 
									+ "<td>" + data.avgresolved + "</td>"
									+ "<td>" + data.resPercentage + "</td></tr>";	
						});
						htmlTable += "</tbody></table></div>";
						$('.monthtab').hide();
						$('#datatable_tabletools_wrapper').hide();
						$('#monthContent').html(htmlTable);
						}else if(status1=='escalated'){
							
						}
						pageSetUp();
						var responsiveHelper_dt_basic = undefined;
						var responsiveHelper_datatable_fixed_column = undefined;
						var responsiveHelper_datatable_col_reorder = undefined;
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Trend analysis Average time breached for Resolved complaints";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                     	             "+reportname+"                                                                                          From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
						$('#datatable_tabletools5')
								.dataTable(
										{
											"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-3 col-xs-3 hidden-xs'C>l>"+
											"t"+
											"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
											"oTableTools" : {
												"aButtons" : [
														
														{
															"sExtends" : "pdf",
															"sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
															"sFileName": reportname+".pdf",
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
		}else if(reportName=="dockets/complaints"){
			
		$.ajax({
					url : "./reports/showComplaintStatusReport",
					type : "GET",
					dataType : "JSON",
					async : false,
					data : {
						fromdate : fromdate,
						todate : todate,
						status: status,
					},
					success : function(response) {
						$('.monthTabDetail').remove();
						var dataNew = response[0];
						var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
								+ "<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+ "<thead>"
								+"<tr><th colspan='4' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Trend analysis of dockets/ complaints which are "+status+" <br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
								+ "<tr>"
								+ "<th data-hide='phone'>Category</th>"
								+ "<th data-hide='phone'>Total</th>"
								+ "<th data-hide='phone,tablet'>"+status+"</th>"
								+ "<th data-hide='phone,tablet'>Resolution rate in percentage</th>"
								+ "</tr>" 
								
								+ "</thead>" + "<tbody>";
						$.each(dataNew, function(index, data)
								{
							htmlTable += "<tr></td>" + "<td>" + data.category
									+ "</td>" + "<td>" + data.total + "</td>"
									+ "<td>" + data.resolved + "</td>" 
									+ "<td>" + data.resPercentage + "</td>"
						});
						htmlTable += "</tbody>";
						$('.monthtab').hide();
						$('#datatable_tabletools_wrapper').hide();
						$('#monthContent').html(htmlTable);

						pageSetUp();
						var responsiveHelper_dt_basic = undefined;
						var responsiveHelper_datatable_fixed_column = undefined;
						var responsiveHelper_datatable_col_reorder = undefined;
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Trend analysis of complaints which are "+status;
						var lines="                                             Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                     								       Trend analysis of dockets/ complaints which are "+status+"                                                                                          	From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            "; 
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
						$('#datatable_tabletools5')
								.dataTable(
										{
											"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-3 col-xs-3 hidden-xs'C>l>"+
											"t"+
											"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
											"oTableTools" : {
												"aButtons" : [
														
														{
															"sExtends" : "pdf",
															"sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
															"sFileName": reportname+".pdf",
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
		else if(reportName=="CustomerSatisfactionResults")
		{
			
			$.ajax({
				url : "./reports/showCusResults",
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
							+ "<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr><th colspan='5' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Trend analysis of Customer Satisfaction Results <br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
							+"<tr>"
							+"<th data-hide='phone'>Category</th>"
							+"<th data-hide='phone'>Number of resolved complaints</th>"
							+"<th data-class='expand'>Good</th>"
							+"<th>Poor</th>"
							+"<th data-hide='phone'>Excellent</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
					$.each(dataNew, function(index, data) {
                           if(data.resolved!=0)
                        	   {
                        	   htmlTable+= "<tr>"
       							+"<td>"+data.category+"</td>"
       							+"<td>"+data.resolved+"</td>"
       							+"<td>"+data.good+"</td>"
       							+"<td>"+data.poor+"</td>"
       							+"<td>"+data.excellent+"</td>"
       							+"</tr>";
                        	   }
						
					});
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').html(htmlTable);

					pageSetUp();
					var responsiveHelper_dt_basic = undefined;
					var responsiveHelper_datatable_fixed_column = undefined;
					var responsiveHelper_datatable_col_reorder = undefined;
					var responsiveHelper_datatable_tabletools = undefined;
					var reportname="Trend analysis of Customer Satisfaction Results";
					var lines="                                             Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                           "+response[1]+"                                                                                                                     								       Trend analysis of Customer Satisfaction Results                                                                                          	       From date : "+fromdate+"  ToDate : "+todate+"                                                                                                                            ";
					var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
					$('#datatable_tabletools5')
							.dataTable(
									{
										"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-3 col-xs-3 hidden-xs'C>l>"+
										"t"+
										"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
										"oTableTools" : {
											"aButtons" : [
													
													{
														"sExtends" : "pdf",
														"sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
														"sFileName": reportname+".pdf",
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
		}else if(reportName=="compliantstatus"){
			
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
							+ "<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr><th colspan='8' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>"+response[1]+" <br>Trend analysis of Compliant status <br>From Date : "+fromdate+" To Date : "+todate+"</th></tr>"
							+ "<tr>"
							+ "<th data-hide='phone'>Sl No.</th>"
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
					$('#monthContent').html(htmlTable);

					pageSetUp();
					
					var responsiveHelper_datatable_tabletools = undefined;
					var reportname="Trend analysis of Compliant status";
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