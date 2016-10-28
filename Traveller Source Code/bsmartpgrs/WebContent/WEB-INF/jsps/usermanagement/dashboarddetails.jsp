<%@include file="/common/taglibs.jsp"%>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="http://code.highcharts.com/modules/solid-gauge.js"></script>
<script src="./resources/js/app.config.js"></script>
<script
	src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
<script src="./resources/js/bootstrap/bootstrap.min.js"></script>
<script src="./resources/js/notification/SmartNotification.min.js"></script>
<script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>
<script
	src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<script
	src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script
	src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/select2/select2.min.js"></script>
<script
	src="./resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
<script src="./resources/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>
<script src="./resources/js/plugin/fastclick/fastclick.min.js"></script>
<script src="./resources/js/demo.min.js"></script>
<script src="./resources/js/app.min.js"></script>
<script src="./resources/js/speech/voicecommand.min.js"></script>
<script src="./resources/js/plugin/morris/raphael.min.js"></script>
<script src="./resources/js/plugin/morris/morris.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
<script
	src="./resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
	src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
	src="./resources/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>





<!-- <script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="http://code.highcharts.com/modules/solid-gauge.js"></script>
<script src="./resources/js/plugin/morris/raphael.min.js"></script>
<script src="./resources/js/plugin/morris/morris.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.orderBar.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.pie.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script> -->
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script
	src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script
	src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script
	src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script
	src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.pie.min.js"></script>
<script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> Dashboard <span>> <a
					href="./dashboard">My Dashboard</a></span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
			<ul id="sparks" class="">
				<li class="sparks-info">
					<h2>${officeName}Details</h2>
				</li>

			</ul>
		</div>
	</div>
	<section id="widget-grid" class="">
		<!-- row -->
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="wid-id-0">

					<header>
						<span class="widget-icon"> <i
							class="glyphicon glyphicon-stats txt-color-darken"></i>
						</span>
						<h2>Live Feeds</h2>
					</header>
					<div class="no-padding">
						<div class="widget-body">
							<div id="myTabContent" class="tab-content">
								<div
									class="tab-pane fade active in padding-10 no-padding-bottom"
									id="s1">

									<div class="row no-space">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
											style="padding-right: 30px !important">
											<div>
												<div class="jarviswidget-editbox">
													<input type="text">
												</div>
												<!-- <div class="widget-body-toolbar bg-color-white smart-form"
													id="rev-toggles">

													<div class="inline-group">

														<label for="gra-0" class="checkbox"> <input
															type="checkbox" name="gra-0" id="gra-0" checked="checked">
															<i></i> Receivables
														</label> <label for="gra-1" class="checkbox"> <input
															type="checkbox" name="gra-1" id="gra-1" checked="checked">
															<i></i> Receipts
														</label> <label for="gra-2" class="checkbox"> <input
															type="checkbox" name="gra-2" id="gra-2" checked="checked">
															<i></i> Penalties
														</label>
													</div>

												</div>
 -->
												<div class="padding-10">
													<!-- <div id="flotcontainer"
														class="chart-large has-legend-unique"></div> -->
													<div style="height: 300px" id="megahighchartcontainer"></div>
												</div>
											</div>
										</div>

									</div>

									<div class="show-stat-microcharts">
										<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

											<div class="easy-pie-chart txt-color-orangeDark"
												data-percent="${invoiceAmountPecent}" data-pie-size="50">
												<span class="percent percent-sign">100%</span>
											</div>
											<span class="easy-pie-title" style="overflow: visible;">
												<i class="fa fa-inr"></i> ${invoiceAmount} <br> Invoice
												Amount
											</span>

											<div
												class="sparkline txt-color-greenLight hidden-sm hidden-md pull-right"
												data-sparkline-type="line" data-sparkline-height="33px"
												data-sparkline-width="70px" data-fill-color="transparent">${invoiceString}</div>
										</div>
										<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
											<div class="easy-pie-chart txt-color-greenLight"
												data-percent="${receivedAmountPecent}" data-pie-size="50">
												<span class="percent percent-sign">78.9 </span>
											</div>
											<span class="easy-pie-title" style="overflow: visible;">
												<i class="fa fa-inr"></i> ${receivedAmount} <br>
												Received Amount
											</span>

											<div
												class="sparkline txt-color-blue hidden-sm hidden-md pull-right"
												data-sparkline-type="line" data-sparkline-height="33px"
												data-sparkline-width="70px" data-fill-color="transparent">${paymentsString}</div>
										</div>
										<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
											<div class="easy-pie-chart txt-color-blue"
												data-percent="${tdsPercent}" data-pie-size="50">
												<span class="percent percent-sign">23 </span>
											</div>

											<span class="easy-pie-title" style="overflow: visible;">
												<i class="fa fa-inr"></i> ${tds} <br> TDS
											</span>

											<div
												class="sparkline txt-color-darken hidden-sm hidden-md pull-right"
												data-sparkline-type="line" data-sparkline-height="33px"
												data-sparkline-width="70px" data-fill-color="transparent">
												200, 210, 363, 247, 300, 270, 130, 187, 250, 257, 363, 247,
												270</div>
										</div>
										<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
											<div class="easy-pie-chart txt-color-darken"
												data-percent="${penaltyPercent}" data-pie-size="50">
												<span class="percent percent-sign">36 <i
													class="fa fa-caret-up"></i></span>
											</div>
											<span class="easy-pie-title" style="overflow: visible;">
												<i class="fa fa-inr"></i> ${penalty} <br> Penalty
											</span>

											<div
												class="sparkline txt-color-red hidden-sm hidden-md pull-right"
												data-sparkline-type="line" data-sparkline-height="33px"
												data-sparkline-width="70px" data-fill-color="transparent">${penaltyString}</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-6">
				<div class="jarviswidget" id="wid-id-1">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Penalty Break ups</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<table class="table table-striped table-hover table-condensed">
								<thead>
									<tr>
										<th>Name</th>
										<th>Amount</th>
										<th class="text-align-center">No. of invoices</th>
										<th class="text-align-center">Demographic</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${penaltyBreakUp}" var="penaltyBreakUp">
										<tr>
											<td><a href="javascript:void(0);"><c:out
														value="${penaltyBreakUp.penaltyName}" /></a></td>
											<td><a href="javascript:void(0);"><c:out
														value="${penaltyBreakUp.amount}" /></a></td>
											<td class="text-align-center"><c:out
													value="${penaltyBreakUp.invoicesCount}" /></td>

											<td class="text-align-center">
												<div class="sparkline display-inline"
													data-sparkline-type='pie'
													data-sparkline-piecolor='["#E979BB", "#57889C"]'
													data-sparkline-offset="90" data-sparkline-piesize="23px">
													<c:out value="${penaltyBreakUp.smartPie}" />
												</div>
												<div
													class="btn-group display-inline pull-right text-align-left hidden-tablet">
													<button class="btn btn-xs btn-default dropdown-toggle"
														data-toggle="dropdown">
														<i class="fa fa-cog fa-lg"></i>
													</button>
													<ul class="dropdown-menu dropdown-menu-xs pull-right">
														<li><a href="javascript:void(0);"><i
																class="fa fa-file fa-lg fa-fw txt-color-greenLight"></i>
																<u>P</u>DF</a></li>
														<li><a href="javascript:void(0);"><i
																class="fa fa-times fa-lg fa-fw txt-color-red"></i> <u>D</u>elete</a>
														</li>
														<li class="divider"></li>
														<li class="text-align-center"><a
															href="javascript:void(0);">Cancel</a></li>
													</ul>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="jarviswidget" id="wid-id-2">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Aging</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 show-stats"
								style="margin-left: 20px">
								<span class="text">${last2mLeft} (< 2 m) <span
									class="pull-right">${last2mRight}</span>
								</span>
								<div class="progress">
									<div id='2m' class="progress-bar bg-color-blueDark"
										style="width: 0%;"></div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 show-stats"
								style="margin-left: 20px">
								<span class="text">${last2n4mLeft} (> 2m < 4 m) <span
									class="pull-right">${last2n4mRight}</span>
								</span>
								<div class="progress">
									<div id='2n4m' class="progress-bar bg-color-blue"
										style="width: 0%;"></div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 show-stats"
								style="margin-left: 20px">
								<span class="text">${last4n6mLeft} (> 4 m < 6 m) <span
									class="pull-right">${last4n6mRight}</span>
								</span>
								<div class="progress">
									<div id='4n6m' class="progress-bar bg-color-blue"
										style="width: 0%;"></div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 show-stats"
								style="margin-left: 20px">
								<span class="text">${last6mLeft} (> 6 m) <span
									class="pull-right">${last6mRight}</span>
								</span>
								<div class="progress">
									<div id='6m' class="progress-bar bg-color-greenLight"
										style="width: 0%;"></div>
								</div>
							</div>
						</div>
					</div>


				</div>

				<div class="jarviswidget" id="wid-id-3">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Last 5 invoices</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<table class="table table-striped table-hover table-condensed">
								<thead>
									<tr>
										<th>Office</th>
										<th>Invoice Date</th>
										<th class="text-align-center">Period</th>
										<th class="text-align-center">Invoice Number</th>
										<th>Invoice Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${last5invoices}" var="last5invoices">
										<tr>
											<td><a href="javascript:void(0);"></a> <c:out
													value="${last5invoices.office}"></c:out></td>
											<td><a href="javascript:void(0);"></a> <c:out
													value="${last5invoices.invoiceDate}"></c:out></td>
											<td><a href="javascript:void(0);"></a> <c:out
													value="${last5invoices.periodOfWork}"></c:out></td>
											<td><a href="javascript:void(0);"></a> <c:out
													value="${last5invoices.invoiceNumber}"></c:out></td>
											<td class="text-align-center"><c:out
													value="${last5invoices.invoiceAmount}"></c:out>
												<div
													class="btn-group display-inline pull-right text-align-left hidden-tablet">
													<button class="btn btn-xs btn-default dropdown-toggle"
														data-toggle="dropdown">
														<i class="fa fa-cog fa-lg"></i>
													</button>
													<ul class="dropdown-menu dropdown-menu-xs pull-right">
														<li><a href="javascript:void(0);"><i
																class="fa fa-file fa-lg fa-fw txt-color-greenLight"></i>
																<u>P</u>DF</a></li>
														<li><a href="javascript:void(0);"><i
																class="fa fa-times fa-lg fa-fw txt-color-red"></i> <u>D</u>elete</a>
														</li>
														<li class="divider"></li>
														<li class="text-align-center"><a
															href="javascript:void(0);">Cancel</a></li>
													</ul>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</article>
			<article class="col-sm-12 col-md-12 col-lg-6">
				<div class="jarviswidget" id="wid-id-4">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Last 5 payments</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<table class="table table-striped table-hover table-condensed">
								<thead>
									<tr>
										<th class="text-align-center">Date</th>
										<th>Payment Office</th>
										<th>Ch. Number</th>
										<th class="text-align-center">No of Invoices</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${last5payment}" var="last5payment">
										<tr>
											<td class="text-align-center"><c:out
													value="${last5payment.chequeDate}"></c:out></td>
											<td><a href="javascript:void(0);"><c:out
														value="${last5payment.paymentOffice}"></c:out></a></td>
											<td><a href="javascript:void(0);"><c:out
														value="${last5payment.chequeNumber}"></c:out></a></td>
											<td class="text-align-center"><a
												href="javascript:void(0);"><c:out
														value="${last5payment.noOfInvoices}"></c:out></a></td>
											<td><a href="javascript:void(0);"><c:out
														value="${last5payment.chequeAmount}"></c:out></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="jarviswidget" id="wid-id-5">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Receivables vs Receipts : Calender Month</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="row no-space">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
								style="padding-right: 30px !important">
								<div>
									<div class="jarviswidget-editbox">
										<input type="text">
									</div>
									<div class="widget-body no-padding">
										<div id="bar-chart-calender" class="chart no-padding"></div>
									</div>
								</div>
							</div>

						</div>


					</div>
				</div>

				<div class="jarviswidget" id="wid-id-6"
					data-widget-colorbutton="false" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Amount Share</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<h5>
								Total Amount : ${totalAmount} <i class="fa fa-inr"></i>
							</h5>
							<!-- <div id="pie-graph" style="height: 250px"></div> -->
							<div id="donut-graph" class="chart no-padding"></div>
						</div>
					</div>
				</div>
				<div class="jarviswidget" id="wid-id-7"
					data-widget-colorbutton="false" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-map-marker"></i>
						</span>
						<h2>Health</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<div>
								<label>Title:</label> <input type="text" />
							</div>
						</div>
						<div class="widget-body no-padding">
							<div style="width: 100%; height: 100px; margin: 0 auto">
								<div id="container-receipt"
									style="width: 25%; height: 100px; float: left"></div>
								<div id="container-master"
									style="width: 50%; height: 200px; float: left"></div>
								<div id="container-penalty"
									style="width: 25%; height: 100px; float: right"></div>
							</div>

						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script>
	var $chrt_border_color = "#efefef";
	var $chrt_grid_color = "#DDD";
	var $chrt_main = "#E24913";
	var $chrt_second = "#6595b4";
	var $chrt_third = "#FF9F01";
	var $chrt_fourth = "#7e9d3a";
	var $chrt_fifth = "#BD362F";
	var $chrt_mono = "#000";
	$(document)
			.ready(
					function() {
						pageSetUp();
						$.ajax({
									url : "./dashboarddetails/master/hc/${phId}",
									type : "GET",
									dataType : "JSON",
									success : function(response) {
										
										var first = response[0];
										var second = response[1];
										var third = response[2];
										var fourth = response[3];
										var fifth = response[4];
										
										var one = fifth[0];
										var two = fifth[1];
										var three = fifth[2];
										$('#megahighchartcontainer')
												.highcharts(
														{
															title : {
																text : '',
																style : {
																	display : 'none'
																}
															},
															subtitle : {
																text : '',
																style : {
																	display : 'none'
																}
															},
															credits : {
																enable:false,
															},
															xAxis : {
																categories : second
															},
															labels : {
																items : [ {
																	html : 'Amount Share for total amount '+one,
																	style : {
																		left : '50px',
																		top : '18px',
																		color : (Highcharts.theme && Highcharts.theme.textColor)
																				|| 'black'
																	}
																} ]
															},
															series : [
																	{
																		type : 'column',
																		name : 'Receivable',
																		data : first
																	},
																	{
																		type : 'column',
																		name : 'Received',
																		data : third
																	},
																	{
																		type : 'spline',
																		name : 'Penalty',
																		data : fourth,
																		marker : {
																			lineWidth : 2,
																			lineColor : Highcharts
																					.getOptions().colors[3],
																			fillColor : 'white'
																		}
																	},
																	{
																		type : 'pie',
																		name : 'Total',
																		data : [
																				{
																					name : 'Received',
																					y : two,
																					color : Highcharts
																							.getOptions().colors[7]
																				// Jane's color
																				},
																				{
																					name : 'Penalty',
																					y : three,
																					color : Highcharts
																							.getOptions().colors[3]
																				// John's color
																				},
																				{
																					name : 'Balance',
																					y : one-(two+three),
																					color : '#FF4D4D',
																				// John's color
																				} ],
																		center : [
																				100,
																				80 ],
																		size : 100,
																		showInLegend : false,
																		dataLabels : {
																			enabled : false
																		}
																	} ]
														});

									}
								});

						var twoMper = '${twoMPercent}';
						var twoNfourMper = '${twofourMPercent}';
						var fourNsixMper = '${foursixMPercent}';
						var sixMper = '${sixMPercent}';

						$('#2m').css("width", twoMper + "%");
						$('#2n4m').css("width", twoNfourMper + "%");
						$('#4n6m').css("width", fourNsixMper + "%");
						$('#6m').css("width", sixMper + "%");

						if ($("#bar-chart-calender").length) {

							$.ajax({
								url : "./dashboard/bargraph/calender/${phId}",
								type : "GET",
								dataType : "JSON",
								success : function(response) {
									Morris.Bar({
										element : 'bar-chart-calender',
										data : response,
										xkey : 'period',
										ykeys : [ 'invoices', 'payments' ],
										labels : [ 'Invoices', 'Payments' ]
									});
								}
							});
						}

						$
								.ajax({
									url : "./dashboard/bargraph/period/${phId}",
									type : "GET",
									dataType : "JSON",
									success : function(response) {
										var first = "";
										var second = "";
										var three = "";
										for (var s = 0, len = response.length; s < len; ++s) {
											first = response[0];
											second = response[1];
											three = response[2];
										}
										var toggles = $("#rev-toggles"), target = $("#flotcontainer");
										var data = [
												{
													label : "Receivables",
													data : first,
													bars : {
														show : true,
														align : "left",
														barWidth : 30 * 30 * 60
																* 1000 * 20
													}
												},
												{
													label : "Receipts",
													data : second,
													bars : {
														show : true,
														align : "right",
														barWidth : 30 * 30 * 60
																* 1000 * 20
													}
												}, {
													label : "Penalty",
													data : three,
													color : '#3276B1',
													lines : {
														show : true,
														lineWidth : 3
													},
													points : {
														show : true
													}
												}, {
													label : "Penalty",
													data : three,
													color : '#71843F',
													lines : {
														show : true,
														lineWidth : 1
													},
													points : {
														show : true
													}
												} ]

										var options = {
											grid : {
												hoverable : true
											},
											tooltip : true,
											tooltipOpts : {
												content : '%x - %y',
												dateFormat : '%b %y',
												defaultTheme : false
											},
											xaxis : {
												mode : "time"
											},
											yaxes : {
												tickFormatter : function(val,
														axis) {
													return "$" + val;
												},
												max : 1200
											}

										};

										plot2 = null;

										function plotNow() {
											var d = [];
											toggles
													.find(':checkbox')
													.each(
															function() {
																if ($(this)
																		.is(
																				':checked')) {
																	d
																			.push(data[$(
																					this)
																					.attr(
																							"name")
																					.substr(
																							4,
																							1)]);
																}
															});
											if (d.length > 0) {
												if (plot2) {
													plot2.setData(d);
													plot2.draw();
												} else {
													plot2 = $.plot(target, d,
															options);
												}
											}

										}
										;

										toggles.find(':checkbox').on('change',
												function() {
													plotNow();
												});
										plotNow()

									}
								});

						Morris.Donut({
							element : 'donut-graph',
							data : [ {
								value : parseInt('${tdsPercent}'),
								label : 'TDS'
							}, {
								value : parseInt('${receivedAmountPecent}'),
								label : 'Received'
							}, {
								value : parseInt('${penaltyPercent}'),
								label : 'Penalty'
							}, {
								value : parseInt('${balancePercent}'),
								label : 'Balance'
							} ],
							formatter : function(x) {
								return x + " %"
							}
						});

						$('#pie-graph')
								.highcharts(
										{
											chart : {
												plotBackgroundColor : null,
												plotBorderWidth : null,
												plotShadow : false
											},
											title : {
												text : 'Amount Share'
											},
											tooltip : {
												pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
											},
											credits : {
												enabled : false
											},
											plotOptions : {
												pie : {
													allowPointSelect : true,
													cursor : 'pointer',
													dataLabels : {
														enabled : true,
														format : '<b>{point.name}</b>: {point.percentage:.1f} %',
														style : {
															color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																	|| 'black'
														}
													}
												}
											},
											series : [ {
												type : 'pie',
												name : 'Amount share',
												data : [
														[
																'TDS',
																parseInt('${tdsDonut}') ],
														[
																'Received',
																parseInt('${receivedAmountDonut}') ],
														[
																'Penalty',
																parseInt('${penaltyDonut}') ],
														[
																'Balance',
																parseInt('${balanceDonut}') ] ]
											} ]
										});

						var gaugeOptions = {

							chart : {
								type : 'solidgauge'
							},

							title : null,

							pane : {
								center : [ '50%', '85%' ],
								size : '140%',
								startAngle : -90,
								endAngle : 90,
								background : {
									backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
											|| '#EEE',
									innerRadius : '60%',
									outerRadius : '100%',
									shape : 'arc'
								}
							},

							tooltip : {
								enabled : false
							},

							// the value axis
							yAxis : {
								stops : [ [ 0.2, '#DF5353' ],
										[ 0.5, '#DDDF0D' ], [ 0.7, '#55BF3B' ],
										[ 0.9, '#55BF3B' ] ],
								lineWidth : 0,
								minorTickInterval : null,
								tickPixelInterval : 400,
								tickWidth : 0,
								title : {
									y : -70
								},
								labels : {
									y : 16
								}
							},

							plotOptions : {
								solidgauge : {
									dataLabels : {
										y : 5,
										borderWidth : 0,
										useHTML : true
									}
								}
							},
							exporting : {
								enabled : false
							}
						};

						var status1 = "";
						if (parseInt('${pma}') < 3 && parseInt('${pma}') >= 0) {
							status1 = "Poor";
						} else if (parseInt('${pma}') > 6
								&& parseInt('${pma}') >= 3) {
							status1 = "Average";
						} else {
							status1 = "Good";
						}
						var status2 = "";
						if (parseInt('${ptyma}') < 3
								&& parseInt('${ptyma}') >= 0) {
							status2 = "Poor";
						} else if (parseInt('${ptyma}') > 6
								&& parseInt('${ptyma}') >= 3) {
							status2 = "Average";
						} else {
							status2 = "Good";
						}

						var status3 = "";
						if (parseInt('${ppma}') < 3 && parseInt('${ppma}') >= 0) {
							status3 = "Poor";
						} else if (parseInt('${ppma}') > 6
								&& parseInt('${ppma}') > 3) {
							status3 = "Average";
						} else {
							status3 = "Good";
						}

						// The speed gauge
						$('#container-receipt')
								.highcharts(
										Highcharts
												.merge(
														gaugeOptions,
														{
															yAxis : {
																min : 0,
																max : 10,
																title : {
																	text : 'Receipt Meter'
																}
															},

															credits : {
																enabled : false
															},

															series : [ {
																name : 'Speed',
																data : [ parseInt('${pma}') ],
																dataLabels : {
																	format : '<div style="text-align:center"><span style="font-size:10px;color:'
																			+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
																			+ '">{y}</span><br/>'
																			+ '<span style="font-size:12px;color:silver">'
																			+ status1
																			+ '</span></div>'
																}
															} ]

														}));

						$('#container-penalty')
								.highcharts(
										Highcharts
												.merge(
														gaugeOptions,
														{
															yAxis : {
																min : 0,
																max : 10,
																title : {
																	text : 'Penalty Meter'
																}
															},

															credits : {
																enabled : false
															},

															series : [ {
																name : 'Speed',
																data : [ parseInt('${ptyma}') ],
																dataLabels : {
																	format : '<div style="text-align:center"><span style="font-size:10px;color:'
																			+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
																			+ '">{y}</span><br/>'
																			+ '<span style="font-size:12px;color:silver">'
																			+ status2
																			+ '</span></div>'
																},
																tooltip : {
																	valueSuffix : ' km/h'
																}
															} ]

														}));

						$('#container-master')
								.highcharts(
										Highcharts
												.merge(
														gaugeOptions,
														{
															yAxis : {
																min : 0,
																max : 10,
																title : {
																	text : 'Health Meter'
																}
															},

															credits : {
																enabled : false
															},

															series : [ {
																name : 'Speed',
																data : [ parseInt('${ppma}') ],
																dataLabels : {
																	format : '<div style="text-align:center"><span style="font-size:20px;color:'
																			+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
																			+ '">{y}</span><br/>'
																			+ '<span style="font-size:12px;color:silver">'
																			+ status3
																			+ '</span></div>'
																}
															} ]

														}));

					});
</script>

<style>
.smaller-stat span.label {
	width: 67px;
}

.show-stat-microcharts>div {
	padding: 0px 9px !important;
}
</style>
