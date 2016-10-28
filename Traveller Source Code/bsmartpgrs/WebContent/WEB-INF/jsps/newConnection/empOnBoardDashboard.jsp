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


<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.orderBar.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.pie.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>

<script src="./resources/js/plugin/morris/raphael.min.js"></script>
<script src="./resources/js/plugin/morris/morris.min.js"></script>

<div id="content">

				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark"><span><b >New Connection LT And HT</b></span></h1>
					</div>
					
					</div>
					
						<article class="col-sm-12 col-md-12 col-lg-12">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
				<!--  <header>
					<h2><span id="spanId">Register  Complaint</span></h2>				
				</header> -->
				<div style="border-style: solid;border-width: 1px;border-color: #E1D6D6;">
					
						<form:form id="register-form" action="./dashboardDetailsBySiteCode" method="POST"  class="smart-form" novalidate="novalidate">

								<div class="row">
									<section class="col col-3">
									<label class="label">Circle</label>
										<label class="select">
											<select id="circleSiteCode" name="circleSiteCode">
												<option value="" selected="" disabled="">Select Circle</option>
  												<c:forEach items="${circleList}" var="circle">
												<option value="${circle.circleSiteCode}">${circle.circleName}</option>
												</c:forEach>
										 </select><i></i>
										 
										 </label>
											</section>
									
									<section class="col col-3">
									<label class="label">Division</label>
										<label class="select">
										
											<select  name="siteCode" id="siteCode">
												<option value="" selected="" disabled="">Select Division</option>
											</select> <i></i> </label>
									</section>
									
									<section class="col col-3">
									<label class="label">Sub Division</label>
										<label class="select">
											<select  id="subId" name="subId">
											<option value="" selected="" disabled="">Select Sub Division</option>	
										    </select><i></i></label>
									</section>
									
									<section class="col col-3">
									<label class="label"></label>
									<label class="button">
										<button type="submit" class="btn btn-primary">
									Search
								</button></label>
									</section>
								</div>
							
						</form:form>

					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->
		</article>	
					
			<div class="row">
							<article class="col-sm-12 ">

							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-2" data-widget-editbutton="false">
								
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Application Status </h2>

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
										
										<!-- <div class="alert alert-info no-margin fade in">
											<button class="close" data-dismiss="alert">
												×
											</button>
											<i class="fa-fw fa fa-info"></i>
											Enables hover effect <code>&lt;table&gt;</code> by adding the <code>.table-hover</code> with the base class
										</div> -->
										<div class="table-responsive">
												
											<table class="table table-hover">
												<thead>
													<tr>
														<th>Application Phases</th>
														<th>In Progress</th>
														<th>Completed</th>
														<th>Pending</th>
													</tr>
												</thead>
												<tbody>
													<%-- <tr>
														<td ><B>Registered </B></td>
													<td>${applicationReceived} </td>
													<td>${fvProgress} </td>
													<td>${appAccPending} </td>
													</tr> --%>
													
													
													<tr>
														<td ><B>Field Verification </B></td>
														<td>${fvProgress} </td>
													<td>${estInProgress} </td>
													<td>${fvPending} </td>
													</tr>
													<tr>
														<td ><B>Estimation </B></td>
														<td>${estInProgress} </td>
													<td>${powerSancInProgress} </td>
													<td>${estPending} </td>
													</tr>
													<tr>
														<td ><B>Power Sanction </B></td>
														<td>${powerSancInProgress} </td>
													<td>${depInProgress} </td>
													<td>${powerSancpding} </td>
													</tr>
													<tr>
														<td ><B>Deposit </B> </td>
													<td>${depInProgress} </td>
													<td>${workOrderInProgress} </td>
													<td>${depPending} </td>
													</tr>
													<tr>
														<td ><B>Work Order </B> </td>
													<td>${workOrderInProgress} </td>
													<td>${meterPOInProgress} </td>
													<td>${workOrderPending} </td>
													</tr>
													<tr>
														<td ><B>Meter Purchase Order </B></td>
														<td>${meterPOInProgress} </td>
													<td>${wcrInProgress} </td>
													<td>${meterPOPending} </td>
													</tr>
													<tr>
														<td ><B>Work Completion Report </B> </td>
													<td>${wcrInProgress} </td>
													<td>${serviceInProgress} </td>
													<td>${wcrPending} </td>
													</tr>
													<tr>
														<td ><B>Service </B></td>
														<td>${serviceInProgress} </td>
													<td>${serviceCompleted} </td>
													<td>${servicePending} </td>
													</tr>
												</tbody>
											</table>
											
										</div>
									</div>
									<!-- end widget content -->

								</div>
								<!-- end widget div -->

							</div>
							<!-- end widget -->

						</article>
						</div>	
				
					
					
					<section id="widget-grid" class="">
						<div class="row">

							<!-- NEW WIDGET START -->
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<!-- row -->
					<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
								
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
									<h2>  Apllication Phases (In Number)</h2>

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

										<div id="normal-bar-graph" class="chart no-padding"></div>

									</div>
									<!-- end widget content -->

								</div>
								<!-- end widget div -->

							</div>
							<!-- end widget -->
						</article>
							</div>
					</section>
					
					
							
				
</div>


    <script type="text/javascript">
			// PAGE RELATED SCRIPTS

			/* chart colors default */
			var $chrt_border_color = "#efefef";
			var $chrt_grid_color = "#DDD"
			var $chrt_main = "#E24913";
			/* red       */
			var $chrt_second = "#6595b4";
			/* blue      */
			var $chrt_third = "#FF9F01";
			/* orange    */
			var $chrt_fourth = "#7e9d3a";
			/* green     */
			var $chrt_fifth = "#BD362F";
			/* dark red  */
			var $chrt_mono = "#000";
			
			

			$(document).ready(function() {

				// DO NOT REMOVE : GLOBAL FUNCTIONS!
				pageSetUp();

				/* sales chart */

				
				/* bar chart */

				if ($("#bar-chart").length) {

					var data1 = [];
					for (var i = 0; i <= 12; i += 1)
						data1.push([i, parseInt(Math.random() * 30)]);

					var data2 = [];
					for (var i = 0; i <= 12; i += 1)
						data2.push([i, parseInt(Math.random() * 30)]);

					var data3 = [];
					for (var i = 0; i <= 12; i += 1)
						data3.push([i, parseInt(Math.random() * 30)]);

					var ds = new Array();

					ds.push({
						data : data1,
						bars : {
							show : true,
							barWidth : 0.2,
							order : 1,
						}
					});
					ds.push({
						data : data2,
						bars : {
							show : true,
							barWidth : 0.2,
							order : 2
						}
					});
					ds.push({
						data : data3,
						bars : {
							show : true,
							barWidth : 0.2,
							order : 3
						}
					});

					//Display graph
					$.plot($("#bar-chart"), ds, {
						colors : [$chrt_second, $chrt_fourth, "#666", "#BBB"],
						grid : {
							show : true,
							hoverable : true,
							clickable : true,
							tickColor : $chrt_border_color,
							borderWidth : 0,
							borderColor : $chrt_border_color,
						},
						legend : true,
						tooltip : true,
						tooltipOpts : {
							content : "<b>%x</b> = <span>%y</span>",
							defaultTheme : false
						}

					});

				}

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
				
				if ($('#normal-bar-graph').length) {

		
					Morris.Bar({
												element : 'normal-bar-graph',
												data : [ /* {
													x : 'Application',
													y : 100,
													z : 20,
													a : 30
												},  */{
													x : 'FV',
													y : '${fvProgress}',
													z : '${estInProgress}',
													a : '${fvPending}'
												}, {
													x : 'Est',
													y : '${estInProgress}',
													z : '${powerSancInProgress}',
													a : '${estPending}'

												}, {
													x : 'Power San',
													y : '${powerSancInProgress}',
													z : '${depInProgress}',
													a : '${powerSancpding}'

												}, {
													x : 'Deposit',
													y : '${depInProgress}',
													z : '${workOrderInProgress}',
													a : '${depPending}'

												}, {
													x : 'Work Order ',
													y : '${workOrderInProgress}',
													z : '${meterPOInProgress}',
													a : '${workOrderPending}'

												}, {
													x : 'Meter Po',
													y : '${meterPOInProgress}',
													z : '${wcrInProgress}',
													a : '${meterPOPending}'

												}, {
													x : 'WCR',
													y : '${wcrInProgress}',
													z : '${serviceInProgress}',
													a : '${wcrPending}'

												}, {
													x : 'Service',
													y : '${serviceInProgress}',
													z : '${serviceCompleted}',
													a : '${servicePending}'

												}

												],
												xkey : 'x',
												ykeys : [ 'y', 'z', 'a' ],
												labels : [ 'Completed','In Progress','Pending' ]
											});

										}

										/* end bar-chart-h

										 /* fill chart */

									});

					/* end flot charts */
				</script>

		
