<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<!-- <script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>
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
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script> -->


<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<div id="content">
					
					<c:if test="${not empty idnotexists}">
						<script>
									var result = "${idnotexists}";
									alert(result);
								</script>
					<c:remove var="idnotexists" scope="request"/>
					</c:if>
	
		        <div style="display: none;">
				         <table>
				         <tr>
				         <td class="finalstatus">${finalStatus} </td>
				         
				         </tr>
				         
				         </table>
				         
				         </div>
				
				
                        	<div class="row">
							<article class="col-sm-12 ">

							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-greenDark" id="wigdh2" data-widget-editbutton="false">
								
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Application Status for Application ID -<a href="#" onclick='applicationDetail(${applicationId},${finalStatus})' style="color:white"> <b>Click here to track Application&nbsp;(${applicationId})</b></a>  </h2>

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
														<th>Application Status</th>
														
													</tr>
												</thead>
												<tbody>
													
													
													<tr>
														<td ><B>Application Registration </B></td>
														<td>${application} </td>													
													
													</tr>
													<tr>
														<td ><B>Field Verification </B></td>
														<td>${fieldverification} </td>													
													
													</tr>
													<tr>
														<td ><B>Estimation </B></td>
														<td>${estimation} </td>
													
													</tr>
													<tr>
														<td ><B>Field Verification Acceptance </B></td>
														<td>${fvacceptance} </td>													
													
													</tr>
													<tr>
														<td ><B>Power Sanction </B></td>
														<td>${powerSanction} </td>													
													
													</tr>
													<tr>
														<td ><B>Deposit </B> </td>
													<td>${deposit} </td>
												
													
													</tr>
													<tr>
														<td ><B>Work Order </B> </td>
													<td>${workOrder} </td>												
													
													</tr>
													<tr>
														<td ><B>Meter Purchase Order </B></td>
														<td>${meterPo} </td>
													</tr>
													<tr>
														<td ><B>Work Completion Report </B> </td>
													<td>${wcr} </td>
													</tr>
													<tr>
														<td ><B>Service </B></td>
														<td>${service} </td>
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
			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->
		<script type="text/javascript">
		
		 
		
		
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			
			pageSetUp();
		
			
		});
		
		function applicationDetail(applicationId,finalstatus){
			window.location.href="./applicationStatusDetail?applicationId="+applicationId+"&finalstatus="+finalstatus;
		}
		
		
		
		    
		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
			var _gaq = _gaq || [];
				_gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
				_gaq.push(['_trackPageview']);
			
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();

		</script>