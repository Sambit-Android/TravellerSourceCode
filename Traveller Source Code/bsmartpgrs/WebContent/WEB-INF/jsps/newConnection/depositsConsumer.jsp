<%@include file="/common/taglibs.jsp"%>
 <%@include file="/common/commonPage.jsp"%>

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

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>

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
				
				
				<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
							<div class="col-sm-4" >
							<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:350px;">In Progress </a>							
							</div>		
							
							<div class="col-sm-4">
							<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:350px;">Completed </a>						           
							</div>		
							
							<div class="col-sm-4">
						            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:350px;">Pending </a>
							</div>		
				
				</div>
				
				
				
<div class="row">
						<article class="col-sm-12">
							<!-- new widget -->
							<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon">  </span>
								<h2>Application ID - <b>${applicationId}</b></h2>
                                  <h2><a href="#" onclick='applicationHistoryPopUp(${app.applicationid})'>Timeline</a></h2>    
									<ul class="nav nav-tabs pull-right in" id="myTab">
										
										<li>
											<a data-toggle="tab" href="#s1"><i ></i> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>

										<li>
											<a data-toggle="tab" href="#s2"><i ></i> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>

										<li id="estli">
											<a data-toggle="tab" href="#s3"><i ></i> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s4"><i ></i> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li class="active">
											<a data-toggle="tab" href="#s5"><i ></i> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										<li >
											<a data-toggle="tab" href="#s6"><i ></i> <span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li> 
									</ul>

								</header>
								</div>

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
																	<c:choose >
																	<c:when test="${element.govtpvt ==1}">
																	<td>Central</td>
																	</c:when>
																	<c:when test="${element.govtpvt ==2}">
																	<td>Central</td>
																	</c:when>
																	<c:otherwise>
																	<td></td>
																	</c:otherwise>
																	</c:choose>
																	
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
											

										
											
										
											
											
											<div class="tab-pane fade" id="s2">
	                                          	<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															
															<tbody>
															
															
															
																<c:forEach var="element" items="${fieldVerificationServiceDetails}">
															
																<tr>
																	<th>Field Verification Ref No</th>
																	<td colspan="2">${element.fvrefno}</td>
																</tr>
															
																<tr>
																	
																	<th>Field Verification Date</th>
																	
																	<td colspan="2"><fmt:formatDate value="${element.fvdate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																	<th>Field Verification Done By</th>
																	<td  colspan="2">${element.username}</td>
																</tr>
																<tr>
																	<th>Estimation Required </th>
																	<c:choose >
																	<c:when test="${element.estmnreq ==0}">
																	<td class="estreq" colspan="2">No</td>
																	</c:when>
																	<c:otherwise>
																	<td class="estreq" colspan="2">Yes</td>
																	</c:otherwise>
																	</c:choose>
																	
																</tr>
																<tr>
																	<th>Required Phase </th>
																	<td  colspan="2">${element.phasereqd} </td>
																</tr>
																
																<tr>
																	<th>Meter Phase </th>
																	<td  colspan="2">${element.meterphase} </td>
																</tr>
																
																<tr>
																	<th>Meter Rating</th>
																	<td  colspan="2">${element.meterrating}</td>
																</tr>
																
																
																<tr>
																	<th>Meter Capacity</th>
																	<td  colspan="2">${element.metercapacity}</td>
																</tr>
																
																
																<tr>
																	<th>Nearest Installation</th>
																	<th>RR Number</th>
																	<th>Account Number</th>
																	
																</tr>
	
																<tr>
																	<th> Installation # 1</th>
																	<td>${element.nearbyrrnoone}</td>
																	<td>${element.nearbyacnoone}</td>
																</tr>
																<tr>
																	<th> Installation # 2</th>
																	<td>${element.nearbyrrnotwo}</td>
																	<td>${element.nearbyacnotwo}</td>
																</tr>
																<tr>
																	<th> Installation # 3</th>
																	<td>${element.nearbyrrnothree}</td>
																	<td>${element.nearbyacnothree}</td>
																</tr>
																
																</c:forEach>
															</tbody>
														</table>
													</div>
											</div>
											
											<div class="tab-pane fade" id="s3">
                                        			<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:forEach var="estimation" items="${estimationServiceDetails}">
																<tr>
																	<th>Estimation Ref No.</th>
																	<td>${estimation.estmnno}</td>
																</tr>
																<tr>
																	<th>Estimation Date  </th>																	
																	<td><fmt:formatDate value="${estimation.estmndate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																<tr>
																	<th>Estimation Description </th>
																	<td>${estimation.estmndesc}</td>
																</tr>
																<tr>
																	<th>Estimation Cost</th>
																	<td>${estimation.estmcost}</td>
																</tr>
																<tr>
																	<th>Estimation Done By </th>
																	<td>${estimation.username}</td>
																</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
										
											</div>
											
											
											<div class="tab-pane fade" id="s4">
														<div class="col-sm-6">
															<br>
															<table class="table table-bordered table-striped">
																<tbody>
																<c:forEach var="powersanc" items="${powerSanctionServiceDetails}">
																	<tr>
																		<th>Power Sanction Ref No.</th>
																		<td>${powersanc.psrefno}</td>
																	</tr>
																	<tr>
																		<th>Power Sanction Date  </th>
																		
																		<td><fmt:formatDate value="${powersanc.psdate}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	<tr>
																		<th>Power Sanctiond By  </th>
																		<td>${powersanc.username}</td>
																	</tr>
																	
																	<tr>
																		<th>Power Sanction Submitted On  </th>
																		<td><fmt:formatDate value="${powersanc.datestamp}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	
																	
																	</c:forEach>
																	<tr>
																		<th colspan="4">Required Deposit Details</th>
																		
																	</tr>
																	
																	<tr>
																		<th > Deposit Type</th>
																		<th >  Amount</th>
																		
																	</tr>
																	
																	<c:forEach var="depositsReq" items="${depositsReqService}">
																		<tr>
																			<td >${depositsReq.towards}</td>
																			<td >${depositsReq.amount}</td>
																		</tr>
																	</c:forEach>
																	
																	
																</tbody>
															</table>
													     </div>
											</div>
											
											
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s5">
														<div class="col-sm-6">
															<br>
															<table class="table table-bordered table-striped">
																<tbody>
																	
																	<tr>
																		<th colspan="4">Deposit Payment Done By Applicant</th>
																		
																	</tr>
																	
																	<tr>
																		<th>Deposit Head</th>
																		<th>Receipt No.</th>
																		<th>Receipt Date</th>
																		<th>Amount</th>
																	</tr>
																	
																	<c:forEach var="deposit" items="${depositsPaidServiceDetails}">
																	<tr>
																		<td>${deposit.towards}</td>
																		<td>${deposit.recno}</td>																		
																		<td><fmt:formatDate value="${deposit.recdate}" pattern="dd-MM-yyyy"/></td>
																		<td>${deposit.amount}</td>
																	</tr>
																	</c:forEach>
																	
																	
																	<tr>
																		<th colspan="4">Wiring Details</th>
																		
																	</tr>
																	
																	
																	<c:forEach var="lec" items="${lecWCServiceDetails}">
																	<tr>
																		<td>Wiring Completion Date</td>																		
																		<td colspan="3"><fmt:formatDate value="${lec.lecwcdate}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	<tr>
																		<td>Total Outlet Points  </td>
																		<td  colspan="3">${lec.outletpoints}</td>
																	</tr>
																	<tr>
																		<td>Total Outlet Watts </td>
																		<td  colspan="3">${lec.outletwatts}</td>
																	</tr>
																	
																	
																	
																	<tr>
																		<td>Wiring Completion Submitted On</td>
																		
																		<td colspan="3"><fmt:formatDate value="${lec.datestamp}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	</c:forEach>
																	
																</tbody>
															</table>
															
															
															<div >
															<form action="./depositUpdate" method="post" id="deposit-form" class="smart-form" enctype="multipart/form-data" >
																<fieldset>
																<div class="row">
																		<section class="col col-6">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<input type="text"	name="applicationid" placeholder="Application ID"  id="applicationid" value="${applicationId}" readonly="readonly"/>
																			</label>
																		</section> 
																	</div>
																	<section>
																		<label class="textarea"> 										
																			<textarea rows="3" name="remarks" id="remarks" placeholder="Remarks/Comments"></textarea> 
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
												
															
													     </div>
											</div>
											<div class="tab-pane fade" id="s6">
                                             <div class="col-sm-6">
                                        			<br>
														
												 	<table class="table table-bordered table-striped">
												 	<thead>
										     <tr>
											<th width="220">Name</th>
											<th>Uploaded ON</th>
											<th>Type</th>
											<!--    <th>File Name</th> -->
											<th>View/Download</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="app" items="${documentDetails}">
									
											<tr>
												<td>${app.docname}</td>
												<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy"/></td>
												<td>${app.typeOfDoc}</td>
												<td><a href='#' onclick="return checkViewDocument(${app.id});">View/Download</a>  </td>

											</tr>
										</c:forEach>
									</tbody>
															
														</table>  
													</div>
										
											</div>					
											
											

										<!-- end content -->
									</div>

								</div>
								<!-- end widget div -->
							</div>
							<!-- end widget -->

						</article>
					</div>

			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->

		<div id="addtab" title="Timeline" style="display:none">
          <table class="table table-bordered table-striped">
												 	<thead>
										     <tr>
											<th width="220">Application Phase</th>
											<th>Reference No.</th>
											<th>Date</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="applist" items="${applicationServiceDetailsList}">
											<tr>
												<td>Application Registered</td>
												<td>${applist.apprefno}</td>
												<td ><fmt:formatDate value="${applist.appregdate}" pattern="dd-MM-yyyy"/></td>
												

											</tr>
										</c:forEach>
										<c:forEach var="fv" items="${fieldVerificationServiceDetails}">
											<tr>
												<td>Field Verification</td>
												<td>${fv.fvrefno}</td>
												<td ><fmt:formatDate value="${fv.fvdate}" pattern="dd-MM-yyyy"/></td>

											</tr>
										</c:forEach>
										<c:forEach var="est" items="${estimationServiceDetails}">
											<tr>
												<td>Estimation</td>
												<td>${est.estmnno}</td>
												<td ><fmt:formatDate value="${est.estmndate}" pattern="dd-MM-yyyy"/></td>

											</tr>
										</c:forEach>
										<c:forEach var="powersanc" items="${powerSanctionServiceDetails}">
											<tr>
												<td>Power Sanction</td>
												<td>${powersanc.psrefno}</td>
												<td ><fmt:formatDate value="${powersanc.psdate}" pattern="dd-MM-yyyy"/></td>

											</tr>
										</c:forEach>									
										
									</tbody>
															
						</table>  
				

           </div>	
		

		

		

		<script type="text/javascript">
		
		var dialog = "";
		function applicationHistoryPopUp(docketNo)
		{
			dialog = $("#addtab").dialog({
				autoOpen : false,
				width : 500,
				resizable : false,
				modal : true,
				
			}).dialog("open");
		}
		
		
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
			

				 $('#workOrder-form').validate({
			// Rules for form validation
				rules : {
					
					worefno : {
						required : true,
						
					},
					wocdate : {
						required : true,
						
					},
					remarks : {
						required : true,
						
					}
				},
		
				// Messages for form validation
				messages : {
					
					
					
					worefno : {
						required : 'Enter Work Order Ref. Number',
						
					},
					wocdate : {
						required : 'Select Installation Work Order Date',
						
					},
					remarks : {
						required : 'Enter Remarks/Comment',
						
					}
				},
		
				// Do not change code below
				errorPlacement : function(error, element) {
					error.insertAfter(element.parent());
				}
			});
				 
				 var estreq = document.getElementsByClassName('estreq')[0].innerHTML;			
					if(estreq == 'No'){
					$("#s3").hide();
					$("#estli").hide();
					}
		
		});

		$('#wocdate').datepicker({
			dateFormat : 'dd/mm/yy',
				
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