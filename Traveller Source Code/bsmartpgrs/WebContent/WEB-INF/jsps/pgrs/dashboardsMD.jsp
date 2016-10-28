<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.pie.min.js"></script>

<div class="jarviswidget" id="wid-id-8" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" style="padding-top: 2px;">
				<header>
					<h2></h2>
					<ul class="nav nav-tabs pull-right in">

						<!-- <li>

							<a data-toggle="tab" onclick="tab1Click()"> <i class="fa fa-lg fa-arrow-circle-o-down"></i> <span class="hidden-mobile hidden-tablet"> Circle Wise </span> </a>

						</li> -->
						<li class="active">

							<a data-toggle="tab" onclick="tab2Click()"> <i class="fa fa-lg fa-arrow-circle-o-down"></i> <span class="hidden-mobile hidden-tablet"> Category Wise </span> </a>

						</li>
						<li>

							<a data-toggle="tab" onclick="tab3Click()"> <i class="fa fa-lg fa-arrow-circle-o-down"></i> <span class="hidden-mobile hidden-tablet"> Month Wise </span> </a>

						</li>

						 <li>
							<a data-toggle="tab" onclick="tab4Click()"> <i class="fa fa-lg fa-arrow-circle-o-up"></i> <span class="hidden-mobile hidden-tablet"> Status Wise </span> </a>
						</li> 

					</ul>
				</header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body">

						<div class="tab-content">
							<!-- <div class="tab-pane active" id="hb1">
							
										<div id="container1" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
										
										<div class="row">	
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 26px; padding-right: 26px;">
												<div class="row">
													<div class="well input-group" style="width: 100%; background: #d6dde7;">
														<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
									<div class="jarviswidget" id="wid-id-6"
										data-widget-editbutton="false">

										<header>
											<span class="widget-icon"> <i
												class="fa fa-bar-chart-o"></i>
											</span>
											<h2>Mandya</h2>

										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div class="widget-body no-padding">
												<div id="pie-chart" class="chart"></div>
											</div>
										</div>
									</div>



								</div>
														<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
															<div class="jarviswidget" id="wid-id-6"
										data-widget-editbutton="false">

										<header>
											<span class="widget-icon"> <i
												class="fa fa-bar-chart-o"></i>
											</span>
											<h2>Mysore Works</h2>

										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div class="widget-body no-padding">
												<div id="pie-chart1" class="chart"></div>
											</div>
										</div>
									</div>
														</div>
														<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
															<div class="jarviswidget" id="wid-id-6"
										data-widget-editbutton="false">

										<header>
											<span class="widget-icon"> <i
												class="fa fa-bar-chart-o"></i>
											</span>
											<h2>Mysore-O&M</h2>

										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div class="widget-body no-padding">
												<div id="pie-chart2" class="chart"></div>
											</div>
										</div>
									</div>
														</div>
														<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
															<div class="jarviswidget" id="wid-id-6"
										data-widget-editbutton="false">

										<header>
											<span class="widget-icon"> <i
												class="fa fa-bar-chart-o"></i>
											</span>
											<h2>Hassan</h2>

										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div class="widget-body no-padding">
												<div id="pie-chart3" class="chart"></div>
											</div>
										</div>
									</div>
														</div>
													</div>
													
												</div>
											</div>
										</div>
								-------------------------------Circle Wise---------------------------------------------

							</div> -->
							<div class="tab-pane active" id="hb2">
										<div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
								<!-- -------------------------------category Wise--------------------------------------------- -->

							</div>
							<div class="tab-pane" id="hb3">
										<div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
								<!-- -------------------------------month Wise--------------------------------------------- -->

							</div>
							<div class="tab-pane" id="hb4">

								<ul id="internal-tab-1" class="nav nav-tabs tabs-pull-left">
									<li class="active">
										<a href="#is1" data-toggle="tab">Resolved Tickets</a>
									</li>
									<li>
										<a href="#is2" data-toggle="tab">Escalated Tickets</a>
									</li>
									<li>
										<a href="#is4" data-toggle="tab">Re-Opened Tickets</a>
									</li>
									<li>
										<a href="#is5" data-toggle="tab">On Hold Tickets</a>
									</li>
									
								</ul>
								<div class="tab-content padding-10">
									<div class="tab-pane active" id="is1">
								 <div class="row">
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2></h2>
										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div id="monthdata">
												<div class="widget-body no-padding" id="monthContent">
													<table id="dt_basic" class="table table-striped table-bordered table-hover monthtab" width="100%">
														<thead>
															<tr>
																<!-- <th data-hide="phone">Circle</th> -->
																<th data-class="expand">Total Complaints</th>
																<th data-hide="phone">Resolved with in SLA</th>
																<th data-hide="phone,tablet">Resolved Beyond SLA</th>
															</tr>
														</thead>
													</table>

												</div>
											</div>
										</div>
									</div>
							</div> 
						</div>
									<div class="tab-pane fade" id="is2">
										<div class="row">
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2></h2>
										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div id="monthdata1">
												<div class="widget-body no-padding" id="monthContent1">
													<table id="dt_basic1" class="table table-striped table-bordered table-hover monthtab1" width="100%">
														<thead>
															<tr>
																<!-- <th data-hide="phone">Circle</th> -->
																<th data-class="expand">Total Complaints</th>
																<th data-hide="phone">Level1</th>
																<th data-hide="phone">Level2</th>
																<th data-hide="phone">Level3</th>
																<th data-hide="phone">Level4</th>
																<th data-hide="phone">Level5</th>
																<th data-hide="phone">Level6</th>
															</tr>
														</thead>
													</table>

												</div>
											</div>
										</div>
									</div>
							</div> 
									</div>
									<div class="tab-pane fade" id="is4">
										<div class="row">
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2></h2>
										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div id="monthdata2">
												<div class="widget-body no-padding" id="monthContent2">
													<table id="dt_basic2" class="table table-striped table-bordered table-hover monthtab2" width="100%">
														<thead>
															<tr>
																<!-- <th data-hide="phone">Circle</th> -->
																<th data-class="expand">Total Complaints</th>
																<th data-hide="phone">Re-Opened</th>
															</tr>
														</thead>
													</table>

												</div>
											</div>
										</div>
									</div>
							</div> 
									</div>
									<div class="tab-pane fade" id="is5">
										<div class="row">
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2></h2>
										</header>
										<div>
											<div class="jarviswidget-editbox"></div>
											<div id="monthdata3">
												<div class="widget-body no-padding" id="monthContent3">
													<table id="dt_basic3" class="table table-striped table-bordered table-hover monthtab3" width="100%">
														<thead>
															<tr>
																<!-- <th data-hide="phone">Circle</th> -->
																<th data-class="expand">Total Complaints</th>
																<th data-hide="phone">On Hold</th>
															</tr>
														</thead>
													</table>

												</div>
											</div>
										</div>
									</div>
							</div> 
									</div>
								</div>

							</div>
						</div>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->
			
<script>
var four=0;
function tab4Click(){
	if(four==0){
		 $.ajax({
			url : "./dashboard/totalComplaints",
			type : "GET",
			dataType : "JSON",
			async : false,
		success : function(response) {
			$('.monthTabDetail').remove();
			var dataNew=response[0];
			var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'>" 
				+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				/* +"<th data-hide='phone'>Circle</th>" */
				+"<th data-hide='phone'>Total Complaints</th>"
				+"<th data-hide='phone'>Total Resolved</th>"
				+"<th data-hide='phone,tablet'>Resolved with in SLA</th>"
				+"<th data-hide='phone,tablet'>Resolved Beyond SLA</th>"
			    +"</tr>"
						+"</thead>"
						+"<tbody>";
			 $.each(dataNew, function(index, data){
				 htmlTable+= "<tr>"
		              	/* +"<td>"+data.text+"</td>" */
		              	+"<td>"+data.total+"</td>"
		              	+"<td>"+data.resolved+"</td>"
		              	+"<td>"+data.asPerSLA+"</td>"
		              	+"<td>"+data.beyondSLA+"</td></tr>";
			 });
			 htmlTable+="</tbody>";
			 $('.monthtab').hide();
				$('#dt_basic_wrapper').hide();
				$('#monthContent').append(htmlTable);
				
				pageSetUp();
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
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
				
				
				
				
				
				
				$('.monthTabDetail1').remove();
				var dataNew1=response[1];
				var htmlTable1 = "<div class='monthTabDetail1' style='overflow: scroll;'>" 
					+"<br><table id='datatable_tabletools51' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					/* +"<th data-hide='phone'>Circle</th>" */
					+"<th data-hide='phone'>Total Complaints</th>"
					+"<th data-hide='phone,tablet'>Level1</th>"
					+"<th data-hide='phone,tablet'>Level2</th>"
					+"<th data-hide='phone,tablet'>Level3</th>"
					+"<th data-hide='phone,tablet'>Level4</th>"
					+"<th data-hide='phone,tablet'>Level5</th>"
					+"<th data-hide='phone,tablet'>Level6</th>"
					
				    +"</tr>"
							+"</thead>"
							+"<tbody>";
				 $.each(dataNew1, function(index, data){
					 htmlTable1+= "<tr>"
			              	/* +"<td>"+data.text+"</td>" */
			              	+"<td>"+data.total+"</td>"
			              	+"<td>"+data.level1+"</td>"
			              	+"<td>"+data.level2+"</td>"
			              	+"<td>"+data.level3+"</td>"
			              	+"<td>"+data.level4+"</td>"
			              	+"<td>"+data.level5+"</td>"
			              	+"<td>"+data.level6+"</td></tr>";
			              
				 });
				 htmlTable1+="</tbody>";
				 $('.monthtab1').hide();
					$('#dt_basic_wrapper1').hide();
					$('#monthContent1').append(htmlTable1);
					
					pageSetUp();
					var responsiveHelper_datatable_tabletools1 = undefined;
					var breakpointDefinition1 = {
							tablet : 1024,
							phone : 480
						};
					$('#datatable_tabletools51')
					.dataTable(
							{
								"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
										+ "t"
										+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
								"oTableTools" : {
									"aButtons" : [
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
												$('#datatable_tabletools51'),
												breakpointDefinition1);
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
					
					
					
					
					
					
					$('.monthTabDetail2').remove();
					var dataNew2=response[1];
					var htmlTable2 = "<div class='monthTabDetail2' style='overflow: scroll;'>" 
						+"<br><table id='datatable_tabletools52' width='100%' class='table table-striped table-bordered table-hover'>"
						+"<thead>"
						+"<tr>"
						/* +"<th data-hide='phone'>Circle</th>" */
						+"<th data-hide='phone'>Total Complaints</th>"
						+"<th data-hide='phone,tablet'>Re-Opened</th>"
					    +"</tr>"
								+"</thead>"
								+"<tbody>";
					 $.each(dataNew1, function(index, data){
						 htmlTable2+= "<tr>"
				              /* 	+"<td>"+data.text+"</td>" */
				              	+"<td>"+data.total+"</td>"
				              	+"<td>"+data.pending+"</td></tr>";
				              
					 });
					 htmlTable2+="</tbody>";
					 $('.monthtab2').hide();
						$('#dt_basic_wrapper2').hide();
						$('#monthContent2').append(htmlTable2);
						
						pageSetUp();
						var responsiveHelper_datatable_tabletools2 = undefined;
						var breakpointDefinition2 = {
								tablet : 1024,
								phone : 480
							};
						$('#datatable_tabletools52')
						.dataTable(
								{
									"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
											+ "t"
											+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
									"oTableTools" : {
										"aButtons" : [
												{
													"sExtends" : "print",
													"sMessage" : "<i>(press Esc to close)</i>"
												} ],
										"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
									},
									"autoWidth" : true,
									"preDrawCallback" : function() {
										// Initialize the responsive datatables helper once.
										if (!responsiveHelper_datatable_tabletools2) {
											responsiveHelper_datatable_tabletools2 = new ResponsiveDatatablesHelper(
													$('#datatable_tabletools52'),
													breakpointDefinition2);
										}
									},
									"rowCallback" : function(nRow) {
										responsiveHelper_datatable_tabletools2
												.createExpandIcon(nRow);
									},
									"drawCallback" : function(oSettings) {
										responsiveHelper_datatable_tabletools1
												.respond();
									}
								});
				
						
						
						
						
						
						
						$('.monthTabDetail3').remove();
						var dataNew3=response[1];
						var htmlTable3 = "<div class='monthTabDetail3' style='overflow: scroll;'>" 
							+"<br><table id='datatable_tabletools53' width='100%' class='table table-striped table-bordered table-hover'>"
							+"<thead>"
							+"<tr>"
							/* +"<th data-hide='phone'>Circle</th>" */
							+"<th data-hide='phone'>Total Complaints</th>"
							+"<th data-hide='phone,tablet'>On Hold</th>"
						    +"</tr>"
									+"</thead>"
									+"<tbody>";
						 $.each(dataNew3, function(index, data){
							 htmlTable3+= "<tr>"
					              	/* +"<td>"+data.text+"</td>" */
					              	+"<td>"+data.total+"</td>"
					              	+"<td>"+data.onhold+"</td></tr>";
					              
						 });
						 htmlTable3+="</tbody>";
						 $('.monthtab3').hide();
							$('#dt_basic_wrapper3').hide();
							$('#monthContent3').append(htmlTable3);
							
							pageSetUp();
							var responsiveHelper_datatable_tabletools3 = undefined;
							var breakpointDefinition3 = {
									tablet : 1024,
									phone : 480
								};
							$('#datatable_tabletools53')
							.dataTable(
									{
										"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
												+ "t"
												+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
										"oTableTools" : {
											"aButtons" : [
													{
														"sExtends" : "print",
														"sMessage" : "<i>(press Esc to close)</i>"
													} ],
											"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
										},
										"autoWidth" : true,
										"preDrawCallback" : function() {
											// Initialize the responsive datatables helper once.
											if (!responsiveHelper_datatable_tabletools3) {
												responsiveHelper_datatable_tabletools3 = new ResponsiveDatatablesHelper(
														$('#datatable_tabletools53'),
														breakpointDefinition3);
											}
										},
										"rowCallback" : function(nRow) {
											responsiveHelper_datatable_tabletools3
													.createExpandIcon(nRow);
										},
										"drawCallback" : function(oSettings) {
											responsiveHelper_datatable_tabletools3
													.respond();
										}
									});
				
				
		},

	}); 
	}
	$("#hb2").hide();
	$("#hb3").hide();
	$("#hb1").hide();
	$("#hb4").show();
	four=1;
}
function tab1Click(){
	$("#hb2").hide();
	$("#hb3").hide();
	$("#hb4").hide();
	$("#hb1").show();
}
var two=0;
function tab2Click(){
	$("#hb1").hide();
	$("#hb4").hide();
	$("#hb3").hide();
	$("#hb2").show();
	if(two==0){
		$.ajax({
			url : "./dashboard/categoryWiseDetails",
			type : "GET",
			dataType : "JSON",
			async : false,
			success : function(response) {
				$('#data12').hide();
				Highcharts.setOptions({
					colors: ['#5882FA','#660066','#006600','#FF0000', '#996600','#65596B', ' #916B08']
			    }); 
				 $('#container2').highcharts({
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: 'Category Wise Ticket Details',
				        },
				        xAxis: {
				            categories: response[0]
				        },
				        yAxis: {
				            title: {
				            	 min: 0,
				                text: 'Number of Tickets'
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
				            x: -30,
				            verticalAlign: 'top',
				            y: 25,
				            floating: true,
				            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				            borderColor: '#CCC',
				            borderWidth: 1,
				            shadow: false
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
				                    useHTML: true,
				                    borderWidth: 1,
				                    y: -6,
				                    pointPadding: 0.2,
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
	
	two=1;
}
var three=0;
function tab3Click(){
	$("#hb2").hide();
	$("#hb1").hide();
	$("#hb4").hide();
	$("#hb3").show();
	if(three==0){
		$.ajax({
			url : "./dashboard/monthWiseDetails",
			type : "GET",
			dataType : "JSON",
			async : false,
			success : function(response) {
				 $('#container3').highcharts({
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: 'Month Wise Ticket Details'
				        },
				        subtitle: {
				            text: ''
				        },
				        xAxis: {
				            categories: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
				            crosshair: true
				        },
				        yAxis: {
				            min: 0,
				            title: {
				                text: 'Number Of Tickets'
				            }
				        },
				        tooltip: {
				            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				                '<td style="padding:0"><b>{point.y}</b></td></tr>',
				            footerFormat: '</table>',
				            shared: true,
				            useHTML: true
				        },
				        plotOptions: {
				            column: {
				                pointPadding: 0.2,
				                borderWidth: 0
				            }
				        },
				        series: [{
				            name: 'Registered&Assigned',
				            data: response[1]

				        }, {
				            name: 'On Hold',
				            data: response[2]

				        }, {
				            name: 'Resolved',
				            data: response[3]

				        },{
				            name: 'Reopened',
				            data: response[4]

				        }]
				      
				    });
				
				
				
				
				
				
			}
		});
			    
	}
	
	three=1;
}

$(document).ready(function() {
	
	Highcharts.setOptions({
		colors: ['#5882FA','#660066','#006600','#FF0000', '#996600','#65596B', ' #916B08']
    });  
	
	tab2Click();
	
	
});
</script>
<style>
.legendLabel{
font-size: 14px;
}

.legendColorBox div > div {
    width: 11px;
    height: 11px;
    border-radius: 50%;
    box-sizing: content-box;
    border: 10px;
    overflow: hidden !important;
}
</style>