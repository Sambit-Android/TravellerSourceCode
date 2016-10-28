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
<script src="./resources/js/plugin/morris/raphael.min.js"></script>
<script src="./resources/js/plugin/morris/morris.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>

<script src="./resources/js/plugin/flot/jquery.flot.pie.min.js"></script>


<div id="content">

				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark"><span><b >NCMS - Dashboard</b></span></h1>
					</div>
					
					</div>
					
								
					
					<div class="row" >
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						                <div class="panel-heading bg-color-darken txt-color-white">
						                    <h3 class="panel-title">
                                            Mysore</h3>
						                </div>
						                  <div id="pie-chart1" class="chart"></div>
						                    <table class="table">
						                    <thead>
													<tr>
														<th>App Phase</th>
														<th ><a title="Completed">C</a></th>
														<th ><a title="Progress">P</a></th>
														<th ><a title="Hold" >H</a></th>
														
													</tr>
												</thead>
						                    <tbody>
						                     <c:forEach var="mysoreWork" items="${appphaseMysoreWork}">
						                        <tr>
						                       
						                            <td><b>Field Ver</b></td>
						                            <td>${mysoreWork.estInProgress}</td>
						                            <td>${mysoreWork.fvProgress}</td>
						                            <td>${mysoreWork.fvPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Estimation</b>
						                            </td>
						                            <td>${mysoreWork.powerSancInProgress}</td>
						                            <td>${mysoreWork.estInProgress}</td>
						                            <td>${mysoreWork.estPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>PS</b>
						                            </td>
						                            <td>${mysoreWork.depInProgress}</td>
						                            <td>${mysoreWork.powerSancInProgress}</td>
						                            <td>${mysoreWork.powerSancpding}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Deposit</b>
						                            </td>
						                           <td>${mysoreWork.workOrderInProgress}</td>
						                            <td>${mysoreWork.depInProgress}</td>
						                            <td>${mysoreWork.depPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>Work Order</b>
						                            </td>
						                           <td>${mysoreWork.meterPOInProgress}</td>
						                            <td>${mysoreWork.workOrderInProgress}</td>
						                            <td>${mysoreWork.workOrderPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>MPO</b>
						                            </td>
						                         <td>${mysoreWork.wcrInProgress}</td>
						                            <td>${mysoreWork.meterPOInProgress}</td>
						                            <td>${mysoreWork.meterPOPending}</td>						                        </tr>
						                        <tr>
						                            <td>
						                               <b>WCR</b>
						                            </td>
						                           <td>${mysoreWork.serviceInProgress}</td>
						                            <td>${mysoreWork.wcrInProgress}</td>
						                            <td>${mysoreWork.wcrPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                               <b>Service</b>
						                            </td>
						                          <td>${mysoreWork.serviceCompleted}</td>
						                            <td>${mysoreWork.serviceInProgress}</td>
						                            <td>${mysoreWork.servicePending}</td>
						                        </tr>
						                        </c:forEach>
						                        </tbody>
						                    </table>
						                </div>
						              
						            </div>
						            
						            
						            <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						                <div class="panel-heading  txt-color-white">
						                    <h3 class="panel-title">
                                            CH Nagara-Kodagu</h3>
						                </div>
						                
						                <div id="pie-chart2" class="chart"></div>
						                
						                    <table class="table">
						                    <thead>
													<tr>
														<th>App Phase</th>
														<th ><a title="Completed">C</a></th>
														<th ><a title="Progress">P</a></th>
														<th ><a title="Hold" >H</a></th>
														
													</tr>
												</thead>
						                    <tbody>
						                    
						                        <c:forEach var="appphaseMysoreOAM" items="${appphaseMysoreOAM}">
						                        <tr>
						                       
						                            <td><b>Field Ver</b></td>
						                            <td>${appphaseMysoreOAM.estInProgress}</td>
						                            <td>${appphaseMysoreOAM.fvProgress}</td>
						                            <td>${appphaseMysoreOAM.fvPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Estimation</b>
						                            </td>
						                            <td>${appphaseMysoreOAM.powerSancInProgress}</td>
						                            <td>${appphaseMysoreOAM.estInProgress}</td>
						                            <td>${appphaseMysoreOAM.estPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>PS</b>
						                            </td>
						                            <td>${appphaseMysoreOAM.depInProgress}</td>
						                            <td>${appphaseMysoreOAM.powerSancInProgress}</td>
						                            <td>${appphaseMysoreOAM.powerSancpding}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Deposit</b>
						                            </td>
						                           <td>${appphaseMysoreOAM.workOrderInProgress}</td>
						                            <td>${appphaseMysoreOAM.depInProgress}</td>
						                            <td>${appphaseMysoreOAM.depPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>Work Order</b>
						                            </td>
						                           <td>${appphaseMysoreOAM.meterPOInProgress}</td>
						                            <td>${appphaseMysoreOAM.workOrderInProgress}</td>
						                            <td>${appphaseMysoreOAM.workOrderPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>MPO</b>
						                            </td>
						                         <td>${appphaseMysoreOAM.wcrInProgress}</td>
						                            <td>${appphaseMysoreOAM.meterPOInProgress}</td>
						                            <td>${appphaseMysoreOAM.meterPOPending}</td>						                        </tr>
						                        <tr>
						                            <td>
						                               <b>WCR</b>
						                            </td>
						                           <td>${appphaseMysoreOAM.serviceInProgress}</td>
						                            <td>${appphaseMysoreOAM.wcrInProgress}</td>
						                            <td>${appphaseMysoreOAM.wcrPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                               <b>Service</b>
						                            </td>
						                          <td>${appphaseMysoreOAM.serviceCompleted}</td>
						                            <td>${appphaseMysoreOAM.serviceInProgress}</td>
						                            <td>${appphaseMysoreOAM.servicePending}</td>
						                        </tr>
						                        </c:forEach>
						                        
						                        </tbody>
						                    </table>
						                </div>
						              
						            </div>
						            
						            
						            <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						            
						              
						                <div class="panel-heading bg-color-greenLight txt-color-white">
						                    <h3 class="panel-title">
                                            Hassan</h3>
						                </div>
						                  <div id="pie-chart3" class="chart"></div>
						                    <table class="table">
						                    <thead>
													<tr>
														<th>App Phase</th>
														<th ><a title="Completed">C</a></th>
														<th ><a title="Progress">P</a></th>
														<th ><a title="Hold" >H</a></th>
														
													</tr>
												</thead>
						                    <tbody>
						       					<c:forEach var="appphaseHassan" items="${appphaseHassan}">
						                        <tr>
						                       
						                            <td><b>Field Ver</b></td>
						                            <td>${appphaseHassan.estInProgress}</td>
						                            <td>${appphaseHassan.fvProgress}</td>
						                            <td>${appphaseHassan.fvPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Estimation</b>
						                            </td>
						                            <td>${appphaseHassan.powerSancInProgress}</td>
						                            <td>${appphaseHassan.estInProgress}</td>
						                            <td>${appphaseHassan.estPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>PS</b>
						                            </td>
						                            <td>${appphaseHassan.depInProgress}</td>
						                            <td>${appphaseHassan.powerSancInProgress}</td>
						                            <td>${appphaseHassan.powerSancpding}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Deposit</b>
						                            </td>
						                           <td>${appphaseHassan.workOrderInProgress}</td>
						                            <td>${appphaseHassan.depInProgress}</td>
						                            <td>${appphaseHassan.depPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>Work Order</b>
						                            </td>
						                           <td>${appphaseHassan.meterPOInProgress}</td>
						                            <td>${appphaseHassan.workOrderInProgress}</td>
						                            <td>${appphaseHassan.workOrderPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>MPO</b>
						                            </td>
						                         <td>${appphaseHassan.wcrInProgress}</td>
						                            <td>${appphaseHassan.meterPOInProgress}</td>
						                            <td>${appphaseHassan.meterPOPending}</td>						                        </tr>
						                        <tr>
						                            <td>
						                               <b>WCR</b>
						                            </td>
						                           <td>${appphaseHassan.serviceInProgress}</td>
						                            <td>${appphaseHassan.wcrInProgress}</td>
						                            <td>${appphaseHassan.wcrPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                               <b>Service</b>
						                            </td>
						                          <td>${appphaseHassan.serviceCompleted}</td>
						                            <td>${appphaseHassan.serviceInProgress}</td>
						                            <td>${appphaseHassan.servicePending}</td>
						                        </tr>
						                        </c:forEach>
						                        
						                        </tbody>
						                    </table>
						                </div>
						              
						            </div>
						            
						            
						            <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						                <div class="panel-heading bg-color-blueDark txt-color-white">
						                    <h3 class="panel-title">
                                            Mandya</h3>
						                </div>
						                  <div id="pie-chart4" class="chart"></div>
						                    <table class="table">
						                    <thead>
													<tr>
														<th>App Phase</th>
														<th ><a title="Completed">C</a></th>
														<th ><a title="Progress">P</a></th>
														<th ><a title="Hold" >H</a></th>
														
													</tr>
												</thead>
						                    <tbody>
						                       <c:forEach var="appphaseMandya" items="${appphaseMandya}">
						                        <tr>
						                       
						                            <td><b>Field Ver</b></td>
						                            <td>${appphaseMandya.estInProgress}</td>
						                            <td>${appphaseMandya.fvProgress}</td>
						                            <td>${appphaseMandya.fvPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Estimation</b>
						                            </td>
						                            <td>${appphaseMandya.powerSancInProgress}</td>
						                            <td>${appphaseMandya.estInProgress}</td>
						                            <td>${appphaseMandya.estPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>PS</b>
						                            </td>
						                            <td>${appphaseMandya.depInProgress}</td>
						                            <td>${appphaseMandya.powerSancInProgress}</td>
						                            <td>${appphaseMandya.powerSancpding}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>Deposit</b>
						                            </td>
						                           <td>${appphaseMandya.workOrderInProgress}</td>
						                            <td>${appphaseMandya.depInProgress}</td>
						                            <td>${appphaseMandya.depPending}</td>
						                        </tr>
						                        <tr>
						                            <td>
						                               <b>Work Order</b>
						                            </td>
						                           <td>${appphaseMandya.meterPOInProgress}</td>
						                            <td>${appphaseMandya.workOrderInProgress}</td>
						                            <td>${appphaseMandya.workOrderPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                                <b>MPO</b>
						                            </td>
						                         <td>${appphaseMandya.wcrInProgress}</td>
						                            <td>${appphaseMandya.meterPOInProgress}</td>
						                            <td>${appphaseMandya.meterPOPending}</td>						                        </tr>
						                        <tr>
						                            <td>
						                               <b>WCR</b>
						                            </td>
						                           <td>${appphaseMandya.serviceInProgress}</td>
						                            <td>${appphaseMandya.wcrInProgress}</td>
						                            <td>${appphaseMandya.wcrPending}</td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                               <b>Service</b>
						                            </td>
						                          <td>${appphaseMandya.serviceCompleted}</td>
						                            <td>${appphaseMandya.serviceInProgress}</td>
						                            <td>${appphaseMandya.servicePending}</td>
						                        </tr>
						                        </c:forEach>                
						                        </tbody>
						                    </table>
						                </div>
						              
						            </div>
						            
						            
						        </div>
						        
						        
						        
				
							<!-- <div class="row">
								
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-darken">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                        Mysore Works</h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table">
						                    	<tr class="active">
						                            <td colspan="3"><h4>9</h4></td>
						                        </tr>
						                        <tr >
						                            <td><b>Completed</b></td>
						                            <td><b>In&nbsp;Progress</b></td>
						                            <td><b>On&nbsp;Hold</b></td>
						                        </tr>
						                        <tr class="active">
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                        </tr>
						                    </table>
						                </div>
						                
						              
						            </div>
						        </div>
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						               
						                <div class="panel-heading">
						                     <h3 class="panel-title">
						                        Mysore-O&M</h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table">
						                    	<tr class="active">
						                            <td colspan="3"><h4>9</h4></td>
						                        </tr>
						                         <tr >
						                            <td><b>Completed</b></td>
						                            <td><b>In&nbsp;Progress</b></td>
						                            <td><b>On&nbsp;Hold</b></td>
						                        </tr>
						                        <tr class="active">
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>		       
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-greenLight">
						                <div class="panel-heading">
						                     <h3 class="panel-title">
						                        Hassan</h3>
						                </div>
						                 <div class="panel-body no-padding text-align-center">
						                    <table class="table">
						                    	<tr class="active">
						                            <td colspan="3"><h4>9</h4></td>
						                        </tr>
						                        <tr >
						                            <td><b>Completed</b></td>
						                            <td><b>In&nbsp;Progress</b></td>
						                            <td><b>On&nbsp;Hold</b></td>
						                        </tr>
						                        <tr class="active">
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>		        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary">
						                <div class="panel-heading bg-color-blueDark txt-color-white">
						                     <h3 class="panel-title">
						                       Mandya</h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table" >
						                    	<tr class="active">
						                            <td colspan="3"><h4>9</h4></td>
						                        </tr>
						                         <tr >
						                            <td><b>Completed</b></td>
						                            <td><b>In&nbsp;Progress</b></td>
						                            <td><b>On&nbsp;Hold</b></td>
						                        </tr>
						                        <tr class="active">
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                            <td><font size="3"><b>9</b></font></td>
						                        </tr>
						                    </table>
						                </div>
						                
						                
						            </div>
						        </div>
						    			    	
				    		</div> -->
				    		
				    		
					 
					 
					 
					 
						    			    	
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
			
		/* 	model.addAttribute("serviceInProgresscircleWiseMysoreOM",serviceInProgresscircleWiseMysoreOM);
			model.addAttribute("serviceInPendingcircleWiseMysoreOM",serviceInPendingcircleWiseMysoreOM);
			model.addAttribute("serviceInCompletedcircleWiseMysoreOM",serviceInCompletedcircleWiseMysoreOM);
			
			model.addAttribute("serviceInProgresscirclesiteCodeHassan",serviceInProgresscirclesiteCodeHassan);
			model.addAttribute("serviceInPendingcirclesiteCodeHassan",serviceInPendingcirclesiteCodeHassan);
			model.addAttribute("serviceInCompletedcirclesiteCodeHassan",serviceInCompletedcirclesiteCodeHassan);
			
			model.addAttribute("serviceInProgresscirclesiteCodeMandya",serviceInProgresscirclesiteCodeMandya);
			model.addAttribute("serviceInPendingcirclesiteCodeMandya",serviceInPendingcirclesiteCodeMandya);
			model.addAttribute("serviceInCompletedcirclesiteCodeMandya",serviceInCompletedcirclesiteCodeMandya); */
			
		
			
			
			
			

			$(document).ready(function() {

				// DO NOT REMOVE : GLOBAL FUNCTIONS!
				pageSetUp();
			
			
			
				

				/* sales chart */
				if ($('#pie-chart1').length) 
				{
					var data_pie = [];
					var series = 3 ;
					/* for (var i = 0; i < series; i++) {
						data_pie[i] = {
							label : "Series" + (i + 1),
							data : Math.floor(Math.random() * 100) + 1
						}
					} */
					
					data_pie[0] = {
							label : "Completed-"+'${serviceInCompletedscirclesiteCodeMysoreWork}',
							data : '${serviceInCompletedscirclesiteCodeMysoreWork}'
							//data : 300
						}

					data_pie[1] = {
							label : "Progress -"+'${serviceInProgresscirclesiteCodeMysoreWork}' ,
							data : '${serviceInProgresscirclesiteCodeMysoreWork}'
							//data : 300
						}

					data_pie[2] = {
							label : "Hold-"+'${serviceInPendingscirclesiteCodeMysoreWork}',
							data : '${serviceInPendingscirclesiteCodeMysoreWork}'
							//data : 300
						}


					$.plot($("#pie-chart1"), data_pie, {
						series : {
							pie : {
								show : true,
								innerRadius : 0.5,
								radius : 1,
								label : {
									show : true,
									radius : 2 / 3,
									formatter : function(label, series) {
										return '<div style="font-size:12px;text-align:center;padding:4px;color:white;" ><br/><b>' + Math.round((series.percent)*100)/100 + '%</b></div>';
									},
									
									threshold : 0.1
								}
							}
						},
						legend : {
							show : true,
							noColumns : 1, // number of colums in legend table
							labelFormatter : null, // fn: string -> string
							labelBoxBorderColor : "#000", // border color for the little label boxes
							container : null, // container (as jQuery object) to put legend in, null means default on top of graph
							position : "ne", // position of default legend container within plot
							margin : [85, 81], // distance from grid edge to default legend container within plot
							backgroundColor : null, // null means auto-detect
							backgroundOpacity : 0// set to 0 to avoid background
						},
						grid : {
							hoverable : true,
							clickable : true
						},
						
					});
					// $("#tool").UseTooltip();
				}
				
				
				
				/* $("#pie-chart1").hover(function(){
					
				  $(this).css("background-color", "yellow");
				    }, function(){
				    $(this).css("background-color", "pink"); 
				});  */
				
				if ($('#pie-chart2').length) {

					var data_pie = [];
					var series = 3 ;
									
					data_pie[0] = {
							label : "Completed-"+'${serviceInCompletedcircleWiseMysoreOM}',
							data : '${serviceInCompletedcircleWiseMysoreOM}'
						}

					data_pie[1] = {
							label : "Progress-"+'${serviceInProgresscircleWiseMysoreOM}' ,
							data : '${serviceInProgresscircleWiseMysoreOM}'
						}

					data_pie[2] = {
							label : "Hold-"+'${serviceInPendingcircleWiseMysoreOM}'  ,
							data : '${serviceInPendingcircleWiseMysoreOM}'
						}


					$.plot($("#pie-chart2"), data_pie, {
						series : {
							pie : {
								show : true,
								innerRadius : 0.5,
								radius : 1,
								label : {
									show : true,
									radius : 2 / 3,
									
									formatter : function(label, series) {
										return '<div style="font-size:12px;text-align:center;padding:4px;color:white;" ><br/><b>' + Math.round(series.percent) + '%</b></div>';
									},
									threshold : 0.1
								}
							}
						},
						legend : {
							show : true,
							noColumns : 1, // number of colums in legend table
							labelFormatter : null, // fn: string -> string
							labelBoxBorderColor : "#000", // border color for the little label boxes
							container : null, // container (as jQuery object) to put legend in, null means default on top of graph
							position : "ne", // position of default legend container within plot
							margin : [85, 81], // distance from grid edge to default legend container within plot
							backgroundColor : "#FFFFFF", // null means auto-detect
							backgroundOpacity : 1 // set to 0 to avoid background
						},
						grid : {
							hoverable : true,
							clickable : true
						},
					});

				}
				
				
				if ($('#pie-chart3').length) {

					var data_pie = [];
					var series = 3 ;
					/* for (var i = 0; i < series; i++) {
						data_pie[i] = {
							label : "Series" + (i + 1),
							data : Math.floor(Math.random() * 100) + 1
						}
					} */
					
					
				
					
					
					data_pie[0] = {
							label : "Completed-"+'${serviceInCompletedcirclesiteCodeHassan}',
							data : '${serviceInCompletedcirclesiteCodeHassan}'
						}

					data_pie[1] = {
							label : "Progress-"+'${serviceInProgresscirclesiteCodeHassan}' ,
							data : '${serviceInProgresscirclesiteCodeHassan}'
						}

					data_pie[2] = {
							label : "Hold-"+'${serviceInPendingcirclesiteCodeHassan}',
							data : '${serviceInPendingcirclesiteCodeHassan}'
						}
					
					

					$.plot($("#pie-chart3"), data_pie, {
						series : {
							pie : {
								show : true,
								innerRadius : 0.5,
								radius : 1,
								label : {
									show : true,
									radius : 2 / 3,
									formatter : function(label, series) {
										return '<div style="font-size:12px;text-align:center;padding:4px;color:white;"><br/><b>' + Math.round(series.percent) + '%</b></div>';
									},
									threshold : 0.1
								}
							}
						},
						legend : {
							show : true,
							noColumns : 1, // number of colums in legend table
							labelFormatter : null, // fn: string -> string
							labelBoxBorderColor : "#000", // border color for the little label boxes
							container : null, // container (as jQuery object) to put legend in, null means default on top of graph
							position : "ne", // position of default legend container within plot
							margin : [85, 81], // distance from grid edge to default legend container within plot
							backgroundColor : "#FFFFFF", // null means auto-detect
							backgroundOpacity : 1 // set to 0 to avoid background
						},
						grid : {
							hoverable : true,
							clickable : true
						},
					});

				}
				
				
				if ($('#pie-chart4').length) {

					var data_pie = [];
					var series = 3 ;
					/* for (var i = 0; i < series; i++) {
						data_pie[i] = {
							label : "Series" + (i + 1),
							data : Math.floor(Math.random() * 100) + 1
						}
					} */
					
					
					
					
					
					data_pie[0] = {
							label : "Completed-"+'${serviceInCompletedcirclesiteCodeMandya}',
							data : '${serviceInCompletedcirclesiteCodeMandya}'
						}

					data_pie[1] = {
							label : "Progress-"+'${serviceInProgresscirclesiteCodeMandya}' ,
							data : '${serviceInProgresscirclesiteCodeMandya}'
						}

					data_pie[2] = {
							label : "Hold-"+'${serviceInPendingcirclesiteCodeMandya}'  ,
							data : '${serviceInPendingcirclesiteCodeMandya}'
						}

					$.plot($("#pie-chart4"), data_pie, {
						series : {
							pie : {
								show : true,
								innerRadius : 0.5,
								radius : 1,
								label : {
									show : true,
									radius : 2 / 3,
									formatter : function(label, series) {
										return '<div style="font-size:12px;text-align:center;padding:4px;color:white;"><br/><b>' + Math.round(series.percent) + '%</b></div>';
									},
									threshold : 0.1
								}
							}
						},
						legend : {
							show : true,
							noColumns : 1, // number of colums in legend table
							labelFormatter : null, // fn: string -> string
							labelBoxBorderColor : "#000", // border color for the little label boxes
							container : null, // container (as jQuery object) to put legend in, null means default on top of graph
							position : "ne", // position of default legend container within plot
							margin : [85, 81], // distance from grid edge to default legend container within plot
							backgroundColor : "#FFFFFF", // null means auto-detect
							backgroundOpacity : 1 // set to 0 to avoid background
						},
						grid : {
							hoverable : true,
							clickable : true
						},
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
				var completed=400;
				var progress=500;
				var pending=600;		
				
				if ($('#donut-graph1').length) {
					
					Morris.Donut({
						element : 'donut-graph1',
						data : [{
							value : completed,
							label : 'Completed'
						}, {
							value :progress,
							label : 'Progress'
						}, {
							value : pending,
							label : 'Pending'
						}],
						formatter : function(x) {
							return x 
						}
					});
				}
				
if ($('#donut-graph2').length) {
					
					Morris.Donut({
						element : 'donut-graph2',
						data : [{
							value : completed,
							label : 'Completed'
						}, {
							value :progress,
							label : 'Progress'
						}, {
							value : pending,
							label : 'Pending'
						}],
						formatter : function(x) {
							return x 
						}
					});
				}
				
if ($('#donut-graph3').length) {
	
	Morris.Donut({
		element : 'donut-graph3',
		data : [{
			value : completed,
			label : 'Completed'
		}, {
			value :progress,
			label : 'Progress'
		}, {
			value : pending,
			label : 'Pending'
		}],
		formatter : function(x) {
			return x 
		}
	});
}

if ($('#donut-graph4').length) {
	
	Morris.Donut({
		element : 'donut-graph4',
		data : [{
			value : completed,
			label : 'Completed'
		}, {
			value :progress,
			label : 'Progress'
		}, {
			value : pending,
			label : 'Pending'
		}],
		formatter : function(x) {
			return x 
		}
	});
}
				
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

<style>
a {
  color: #900;
  text-decoration: none;
}

a:hover {
  color: red;
  position: relative;
}

a[title]:hover:after {
  content: attr(title);
  padding: 4px 8px;
  color: #333;
  position: absolute;
  left: 0;
  top: 100%;
  white-space: nowrap;
  z-index: 20px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-box-shadow: 0px 0px 4px #222;
  -webkit-box-shadow: 0px 0px 4px #222;
  box-shadow: 0px 0px 4px #222;
  background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
  background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0, #eeeeee),color-stop(1, #cccccc));
  background-image: -webkit-linear-gradient(top, #eeeeee, #cccccc);
  background-image: -moz-linear-gradient(top, #eeeeee, #cccccc);
  background-image: -ms-linear-gradient(top, #eeeeee, #cccccc);
  background-image: -o-linear-gradient(top, #eeeeee, #cccccc);
}
</style>
