  <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableManaged.init();
   	    	  Index.init();
   		   Index.initJQVMAP(); // init index page's custom scripts
   		   Index.initCalendar(); // init index page's custom scripts
   		   Index.initCharts(); // init index page's custom scripts
   		   Index.initChat();
   		   Index.initMiniCharts();
   		   Index.initDashboardDaterange();
   		   Tasks.initDashboardWidget();
   		 
   		   Charts.init();
		   Charts.initCharts();
		   Charts.initPieCharts();
   	    	   $('#dash-board').addClass('start active ,selected');
   	    	 $("#360d-view,cumulative-Analysis,cmri-Manager,seal-Manager,cdf-Import,system-Security,meter-Observations,interval-DataAnalyzer,events-Analyzer,exceptions-Analyzer,Load-SurveyAnalyzer,instantaneous-Parameters,VEE-RulesEngine,Assessment-Reports,MIS-Reports").removeClass('start active ,selected');
   	    	     
   	    	   });
  
  </script>
<div class="page-content" >
	<!-- BEGIN DASHBOARD STATS -->
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number">
								11600
							</div>
							<div class="desc">                           
								Meters
							</div>
						</div>
						<a class="more" href="#">
						Details <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number">30</div>
							<div class="desc">MIS Reports</div>
						</div>
						<a class="more" href="#">
						Go<i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
			           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								
								<div class="panel panel-warning">
									<!-- Default panel contents -->
									<div class="panel-heading">
										
										Defective Meters
										<a>
										<i class="fa fa-bar-chart-o"></i> 
										<span class="title">View Detailed Report</span>					
										</a>
									</div>
									
									<!-- List group -->
									<ul class="list-group">
										<li class="list-group-item">RTC Problem<span class="badge badge-default">5</span></li>
										<li class="list-group-item">MR Correction <span class="badge badge-success">11</span></li>
										
									
									</ul>
								</div>
						</div>	
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box light-grey">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Meter Data Analysis Summary</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="sample_2">
								<thead>
									<tr>
										
										<th>Discription</th>
										<th >No of Consumers</th>
										<th >Remedial Solution</th>
										<th >Remarks</th>
									</tr>
								</thead>
								<tbody>
									<tr class="odd gradeX">
										
										<td>Class 1.0 Static Meters</td>
										<td >213</td>
										<td >To be replaced for class 0.5s HT/LT TVM in case HT/ LTTVM Meters</td>
										<td >--</td>
									</tr>
									<tr class="odd gradeX">
										
										<td>No Tamper Events in the Tamper Register</td>
										<td >400</td>
										<td >--</td>
										<td >--</td>
									</tr>
									<tr class="odd gradeX">
										
										<td>No Transaction Events</td>
										<td >243</td>
										<td >--</td>
										<td >--</td>
									</tr>
									<tr class="odd gradeX">
										
										<td>Low Monthly Power Factor(<0.7)</td>
										<td >147</td>
										<td >Taken action as per rule</td>
										<td >--</td>
									</tr>
									<tr class="odd gradeX">
										
										<td>Maximum Demand Exceeded Contract Demand</td>
										<td >248</td>
										<td >It also include Direct Meter and take action as per rule</td>
										<td >--</td>
									</tr>
									
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- ************************************************ -->
			<div class="clearfix"></div>
			<div class="row ">
				
				<div class="col-md-6 col-sm-6">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-calendar"></i>Meter Make Wise Meter installation Status</div>
							<div class="actions">
								<a href="javascript:;" class="btn btn-sm yellow easy-pie-chart-reload"><i class="fa fa-repeat"></i> Reload</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number transactions" data-percent="55"><span>33</span>%</div>
										<a class="title" href="#">L&T <i class="m-icon-swapright"></i></a>
									</div>
								</div>
								<div class="margin-bottom-10 visible-sm"></div>
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number visits" data-percent="85"><span>45</span>%</div>
										<a class="title" href="#">Secure Meter<i class="m-icon-swapright"></i></a>
									</div>
								</div>
								<div class="margin-bottom-10 visible-sm"></div>
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number bounce" data-percent="46"><span>15</span>%</div>
										<a class="title" href="#">Genus<i class="m-icon-swapright"></i></a>
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number transactions" data-percent="55"><span>20</span>%</div>
										<a class="title" href="#">HPL <i class="m-icon-swapright"></i></a>
									</div>
								</div>
								<div class="margin-bottom-10 visible-sm"></div>
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number visits" data-percent="85"><span>5</span>%</div>
										<a class="title" href="#">Elymer <i class="m-icon-swapright"></i></a>
									</div>
								</div>
								<div class="margin-bottom-10 visible-sm"></div>
								<div class="col-md-4">
									<div class="easy-pie-chart">
										<div class="number bounce" data-percent="46"><span>2</span>%</div>
										<a class="title" href="#">Others<i class="m-icon-swapright"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bell-o"></i>Tamper Events</div>
							<div class="actions">
								
							</div>
						</div>
						<div class="portlet-body">
							<div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														B phase CT reversed. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												10
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														R-Ph current missing
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												18
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														Load imbalance. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												30
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														B phase potential missing. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												20
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														Power failed. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												15
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														CT short. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												5
											</div>
										</div>
									</li>
									<li>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-warning">                        
														<i class="fa fa-bell-o"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														CT Open. 
														            
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="date">
												12
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="scroller-footer">
								<div class="pull-right">
									<a href="#">View Events Report <i class="m-icon-swapright m-icon-gray"></i></a> &nbsp;
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		
		
			
</div>