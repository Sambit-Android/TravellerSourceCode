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

						<div id="content">
			<div style="display: none;">
              	<table>
           		<tr><td class="appphase">${appPhase}<td></tr>
            	</table>
            </div>
               
               
				<%-- <div class="row">
					<div class="col-xs-6 col-sm-7 col-md-7 col-lg-4">
						<h2 class="page-title txt-color-blueDark">
							<i class="fa fa-edit fa-fw "></i> 
								Consumer Application Status  
							<span>
								Application ID -  <b>${applicationId} </b>
							</span>
						</h2>
					</div>
					<div class="col-xs-9 col-sm-7 col-md-7 col-lg-6" style="margin-right: ">
						<ul id="sparks" class="">
							
							<li class="sparks-info">
								<h4 id="redirectData2"><a href="javascript:void(0);"> In Progress</a> <span class="txt-color-purple"><i  data-rel="bootstrap-tooltip" title="Increased"></i></span></h4>
								
							</li>
							<li class="sparks-info">
								<h4 id="redirectData3"><a href="javascript:void(0);"> Completed </a> <span class="txt-color-greenDark"></span></h4>
								
							</li>
							<li class="sparks-info">
								<h4 id="redirectData4"><a href="javascript:void(0);"> Pending </a> <span class="txt-color-greenDark"></span></h4>
								
							</li>
						</ul>
					</div> 
				</div>--%>
				
				
				<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;">
				
							<div class="col-sm-4">
							  		<div class="panel panel-orange">
						                <div class="panel-heading">
						                    <h3 id="redirectData2" onclick="redirect" class="panel-title" align="center"><b> In Progress</b></h3>
						                </div>
						                
						            </div>
							</div>		
							
							<div class="col-sm-4">
						            <div class="panel panel-greenLight">
						                <div class="panel-heading">
						                    <h3 class="panel-title" id="redirectData3" align="center"><b>Completed</b></h3>
						                </div>
						               
						            </div>
							</div>		
							
							<div class="col-sm-4">
						            <div class="panel panel-redLight">
						                <div class="panel-heading">
						                    <h3 class="panel-title" id="redirectData4" align="center"><b>Pending</b></h3>
						                </div>
						                
						            </div>
							</div>		
				
				</div>
				
<div class="row">
						<article class="col-sm-12">
							<!-- new widget -->
							<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon">  </span>
									<h2>Application Phases </h2>

									<ul class="nav nav-tabs pull-right in" id="myTab">
										<li>
											<a data-toggle="tab" href="#s1"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Application Details</span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#s2"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Application Acceptance</span></a>
										</li>
									</ul>

								</header>

								<!-- widget div-->
								<div class="no-padding">
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">

										test
									</div>
									<!-- end widget edit box -->

									<div class="widget-body">
										<!-- content -->
										<div id="myTabContent" class="tab-content">
										
										
											<div class="tab-pane fade" id="s1">
																<div class="col-sm-9">
												<br>
													<table class="table table-bordered table-striped">
															<tbody>
														<c:forEach var="element" items="${applicationServiceDetailsList}">
															    <tr>
																	<th colspan="4">ARF Details</th>
																</tr>
																<tr>
																	<th >ARF Receipt No. </th>
																	
																	<td>${element.arfreceiptno}</td>
																	
																
																	<th >ARF Receipt Date</th>
																	<td><fmt:formatDate value="${element.arfreceiptdt}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																	<th >ARF Amount </th>
																	<td>${element.arfamount}</td>
																	<th >Towards</th>
																	<td>${element.arftowards}</td>
																</tr>
															
															
															    <tr>
																	<th colspan="4">Application Details</th>
																</tr>
																<tr>
																	<th colspan="2">Individual Applicants </th>
																	<th colspan="2">Organisation/Corporate Applicant</th>
																</tr>
															
																<tr>
																	<th>Name</th>
																	<td>${element.name}</td>
																	<th>Name Of Organisation</th>
																	<td>${element.nameoforg}</td>
																</tr>
																
																<tr>
																	<th>Father's/Husband Name</th>
																	<td>${element.fhname}</td>
																	<th>Authorised Signatory</th>
																	<td>${element.nameauthsignatory}</td>
																</tr>
																
																<tr>
																	<th>Name Of Nominee</th>
																	<td>${element.nomineename}</td>
																	<th>Designation Of Authorised Signatory</th>
																	<td>${element.desigauthsignatory}</td>
																</tr>
																
																<tr>
																	<th>Nature OF Installation</th>
																	<td>${element.natureofinst}</td>
																	<th>Power Allocation Reason</th>
																	<td>${element.reqfor}</td>
																</tr>
																
																<tr>
																	<th>Tariff</th>
																	<td>${element.tariffdesc}</td>
																	<th>Desired Load </th>
																	<td>${element.loadkw} KW ${element.loadhp} HP ${element.loadkva} KVA</td>
																</tr>
																
																
																<tr>
																	<th colspan="2">Address Where Power Supply Required</th>
																	<th colspan="2">Permanent Address/Reg Off Address (In Case Of Company)</th>
																</tr>
																
																<tr>
																	
																	<th>House/Flat/Shop/Plot No. </th>
																	<td>${element.hnopres}</td>
																	<td colspan="2">${element.hnoperm}</td>
																</tr>
																
																<tr>
																	<th>Street Name</th>
																	<td>${element.streetpres}</td>
																	<td colspan="2">${element.streetperm}</td>
																</tr>
																
																
																<tr>
																	<th>Area/Location</th>
																	<td>${element.areapres}</td>
																	<td  colspan="2">${element.areaperm}</td>
																</tr>
																<tr>
																	<th>Main - Cross</th>
																	<td>${element.mainpres} - ${element.crosspres}</td>
																	<th>Main - Cross</th>
																	<td >${element.mainperm} - ${element.crossperm}</td>
																</tr>
																<tr>
																	
																	<th>Land Mark</th>
																	<td>${element.name}</td>
																	<td colspan="2">${element.name}</td>
																</tr>
																
																<tr>
																	<th>City - Pin</th>
																	<td>${element.citypres} - ${element.pinpres}</td>
																	<th>City-Pin</th>
																	<td>${element.cityperm} - ${element.pinperm}</td>
																	
																</tr>
																<tr>
																	<th>Phone</th>
																	<td >${element.phonepres}</td>
																	<th>Phone</th>
																	<td >${element.phoneperm}</td>
																</tr>
																<tr>
																	
																	<th>Mobile Number</th>
																	<td>${element.mobilenopres}</td>
																	<th>Mobile Number</th>
																	<td colspan="4">${element.mobilenoperm}</td>
																</tr>
																
																<tr>
																	<th>E-Mail</th>
																	<td>${element.emailpres}</td>
																	
																	<th>E-Mail</th>
																	<td>${element.emailperm}</td>
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td colspan="4">${element.supplyfor}</td>
																</tr>
																
																<tr>
																	
																	<th>If Govt</th>
																	<td>${element.govtpvt}</td>
																	<th>Public Street Light/Park Light</th>
																	<td>${element.supplyfor}</td>
																	
																</tr>
																<tr>
																	
																	<th>Special Govt Scheme</th>
																	<td>${element.splgovtscheme}</td>
																	<th>Type Of Ownership</th>
																	<td>${element.ownership}</td>
																	
																</tr>
																<tr>
																	<th>Water Source (If IP Set)</th>
																	<td colspan="4">${element.watersource}</td>
																</tr>
																<tr>
																	<th>Nearest Installation</th>
																	<td colspan="4">${element.nearbyrrnoone}</td>
																</tr>
																
																
																</c:forEach>
															</tbody>
														</table>
												</div>
											</div>
											
										<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s2">
											
											
											 <article class="col-sm-12 col-md-12 col-lg-6">
			
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
														<h2>ARF Details </h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form id="checkout-form" class="smart-form" action="./updateAccetance" novalidate="novalidate">
																<fieldset>
																	
																	<c:forEach var="element" items="${applicationServiceDetailsList}">
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<input type="text"	name="applicationid" placeholder="Application ID"  id="applicationid" value="${element.applicationid}" readonly="readonly">
																			</label>
																		</section> 
																	</div>
																	
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">ARF Receipt Number</label>
																			<label class="input">
																				
																				<input type="text" name="named" id="named" value="${element.arfreceiptno}" readonly="readonly">
																			</label>
																		</section>
																		
																		<section class="col col-6">
																			<label class="label">ARF Receipt Date</label>
																			<label class="input">
																				
																				<input type="text" name="named" id="named" value="${element.arfreceiptdt}" readonly="readonly">
																			</label>
																		</section>
																		
																	</div>
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Amount</label>
																			<label class="input">
																				
																				<input type="text" name="named" id="named"  value="${element.arfamount}" readonly="readonly">
																			</label>
																		</section>
																		
																		<section class="col col-6">
																			<label class="label">Towards</label>
																			<label class="input">
																				
																				<input type="text" name="named" id="named"  value="${element.arftowards}" readonly="readonly">
																			</label>
																		</section>
																		
																	</div>
																	</c:forEach>
																
																	<section>
																		<label class="textarea"> 										
																			<textarea rows="3" name="info" placeholder="Remarks/Comments"></textarea> 
																		</label>
																	</section>
																
																	
																	
																	
																</fieldset>
									
																<footer>
																	<button type="submit" class="btn btn-primary">
																		Submit 
																	</button>
																</footer>
															</form>
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												<!-- end widget -->
												</article>

											</div>
											
											
											
											
											
										

										<!-- end content -->
									</div>

								</div>
								<!-- end widget div -->
							</div>
							<!-- end widget -->
                           </div>
						</article>
					</div>

			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->

		
		

		

		

		<script type="text/javascript">
		
		$('#redirectData2').click(function () {
			var appStatus="In Progress";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });

		 
		$('#redirectData3').click(function () {
			var appStatus="Completed";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });
		 
		 
		$('#redirectData4').click(function () {
			var appStatus="Pending";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			pageSetUp();
		
		})

		$('#startdate').datepicker({
				dateFormat : 'dd.mm.yy',
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>',
				onSelect : function(selectedDate) {
					$('#finishdate').datepicker('option', 'minDate', selectedDate);
				}
		});
		    
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