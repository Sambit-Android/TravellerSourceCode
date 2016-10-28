<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>



			<div id="content">
				
						
                         <div class="row">
						<article class="col-sm-12">
							<!-- new widget -->
							<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon">  </span>
									<h2>Application Phases- Application ID - <b>${applicationId}</b> 
									
									<c:if test = "${finalStatus==22}">
									  <b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Field Verification is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==-22}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Field Verification Acceptance is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==23}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estimation is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==24}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Power Sanction is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==26}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Work Order is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==27}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Meter Purchase Order is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==28}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WCR is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==29}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Installation Service is in On Hold <a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason for OnHold'><b>Click Here</b></a></font></b>
									</c:if>
									
									<c:if test = "${finalStatus==5}">
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application Rejected&nbsp;&nbsp;&nbsp;<a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here for reason'><b>Click here for Reason</b></a></font></b>
										

									</c:if>
									
									</h2>

									<ul class="nav nav-tabs pull-right in" id="myTab">
										
										
										
                                     <c:if test = "${finalStatus==0 || finalStatus==5 || finalStatus==85}"> 
										<li class="active">
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</c:if>
									
								    
								    <c:if test = "${finalStatus==12 || finalStatus==22}"> 
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#s3"><span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>

										
										
										
                                    </c:if>
                                    <c:if test = "${finalStatus==-12 || finalStatus==-22}">  
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										
										<li>
										<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li class="active">
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>

										
										
										
                                    </c:if>
									<c:if test = "${finalStatus==13 || finalStatus==23 }"> 
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application Details</span></a>
										</li>
										
										<li>
										<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
										
										
										
									</c:if>
									<c:if test = "${finalStatus==14 || finalStatus==24}">
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</c:if>
									<c:if test = "${finalStatus==15}"> 
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										 
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li class="active">
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</c:if> 
									<c:if test = "${finalStatus==16 || finalStatus==26 || finalStatus==86 || finalStatus==87}"> 
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
									  
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
									
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										<li class="active">
											<a data-toggle="tab" href="#s7"> <span class="hidden-mobile hidden-tablet">Work Order</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
										
									</c:if>
									<c:if test = "${finalStatus==17 || finalStatus==27}">   
										
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
									   
									   <li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
									
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										<li>
											<a data-toggle="tab" href="#s7"> <span class="hidden-mobile hidden-tablet">Work Order</span></a>
										</li> 
										<li class="active">
											<a data-toggle="tab" href="#s8"> <span class="hidden-mobile hidden-tablet">Meter/PC Test</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
										
										
									</c:if>
									
									
									<c:if test = "${finalStatus==18 || finalStatus==28}"> 
										
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
									
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
								    
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										<li>
											<a data-toggle="tab" href="#s7"> <span class="hidden-mobile hidden-tablet">Work Order</span></a>
										</li> 
										<li>
											<a data-toggle="tab" href="#s8"> <span class="hidden-mobile hidden-tablet">Meter/PC Test</span></a>
										</li> 
										
										<li class="active">
											<a data-toggle="tab" href="#s9"> <span class="hidden-mobile hidden-tablet">WCR</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
										
									</c:if>
									
									
									
									
									<c:if test = "${finalStatus==19 || finalStatus==29}"> 
											<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
									
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
									
										<li>
											<a data-toggle="tab" href="#s5"> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s6"> <span class="hidden-mobile hidden-tablet">Deposits</span></a>
										</li> 
										<li>
											<a data-toggle="tab" href="#s7"> <span class="hidden-mobile hidden-tablet">Work Order</span></a>
										</li> 
										<li>
											<a data-toggle="tab" href="#s8"> <span class="hidden-mobile hidden-tablet">Meter/PC Test</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s9"> <span class="hidden-mobile hidden-tablet">WCR</span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#s10"> <span class="hidden-mobile hidden-tablet">Service</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</c:if>
									
									
									<c:if test = "${finalStatus==61 || finalStatus==62 || finalStatus==63 || finalStatus==64 || finalStatus==65 || finalStatus==66 || finalStatus==67 || finalStatus==68 || finalStatus==69 || finalStatus==70 || finalStatus==71 || finalStatus==72 || finalStatus==73}"> 	
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application Details</span></a>
										</li>
										
										<li>
										<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										<li>
											<a data-toggle="tab" href="#sda12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										<li class="active">
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										
										</li> 
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</c:if>
									
									
										
										
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
										
											
											
											<c:choose>
											<c:when test = "${finalStatus==0 || finalStatus==5 || finalStatus==85}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s1"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s1">
											</c:otherwise>
											</c:choose>
												

													

											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
															<tbody>
															
	
	
														<c:forEach var="element" items="${applicationServiceDetailsList}">
															
															    <tr>
																	<th colspan="4">Application Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Location(Section)
																	&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${locationText}</font></b>&nbsp;&nbsp;&nbsp;&nbsp;(Division)&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${divisionText}</font></b>
																	&nbsp;&nbsp;&nbsp;&nbsp;(Sub Division)&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${subdivisionText}</font><input type="button" value="View Application"
													                class="btn btn-primary" onclick="return applicationDocumentView(${applicationId});" style="float:right;"></b></th>
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
																	<td>New Service</td>
																</tr>
																
																<tr>
																	<th>Tariff</th>
																	<td>${element.tariffmain}</td>
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
																	<td>${element.landmarkpres}</td>
																	<td colspan="2">${element.landmarkperm}</td>
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
																	<%-- <th>Purpose</th>
																	<c:choose >
																	<c:when test="${element.purpose ==1}">
																	<td>Lighting</td>
																	</c:when>
																	
																	<c:when test="${element.purpose ==2}">
																	<td>Heating</td>
																	</c:when>
																	
																	<c:otherwise>
																
																	<td>Lighting & Heating</td>
																	</c:otherwise>
																	</c:choose> --%>
																	
																	<th>Phase Requirement</th>
																	
																	<c:choose >
																	<c:when test="${element.reqphase ==1}">
																	<td>Single Phase</td>
																	</c:when>
																	<c:otherwise>
																	<td>Three Phase</td>
																	</c:otherwise>
																	</c:choose>
																	
																	<th>Application Type</th>
																	<td>${element.applicationtype}</td>
																	
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td>${element.supplyfor}</td>
																	
																	<th>No Of Connections for this Tariff</th>
																	<td>${element.noofconnections}</td>
																</tr>
																
																<tr>
																	
																	<th>If Govt</th>
																	<c:choose >
																	<c:when test="${element.govtpvt ==1}">
																	<td>Central Govt.</td>
																	</c:when>
																	<c:when test="${element.govtpvt ==2}">
																	<td>State Govt.</td>
																	</c:when>
																	<c:when test="${element.govtpvt ==3}">
																	<td>Private</td>
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
																	<th>Nearest Installation(RR Number 1)</th>
																	<td>${element.nearbyrrnoone}</td>
																	<th>Nearest Installation(Account Number 1)</th>
																	<td>${element.nearbyacnoone}</td>
																</tr>
																<tr>
																	<th>Nearest Installation(RR Number 2)</th>
																	<td>${element.nearbyrrnotwo}</td>
																	<th>Nearest Installation(Account Number 2)</th>
																	<td>${element.nearbyacnotwo}</td>
																</tr>
																<tr>
																	<th>Nearest Installation(RR Number 3)</th>
																	<td>${element.nearbyrrnothree}</td>
																	<th>Nearest Installation(Account Number 3)</th>
																	<td>${element.nearbyacnothree}</td>
																</tr>
																
																<tr hidden="true">
																	<th>Remarks </th>
																	<td class="remarksContent">${element.remarks}</td>
																	
																</tr>
																
																
																</c:forEach>
																
																<c:set var="counts" value="0" scope="page" />
																	<c:forEach var="deposit" items="${depositsPaidServiceDetails}">
																		
																		 <c:set var="counts" value="${counts + 1}" scope="page"/>
																		<tr>
																			<th colspan="4">Receipt - ${counts}</th>
																		</tr>
																		<tr>
																			
																			<th>Receipt No.</th>
																			<td>${deposit.recno}</td>
																			<th>Receipt Date</th>
																			<td>${deposit.recdate}</td>
																			
																		</tr>
																		<tr>
																			
																			<th>Amount</th>
																			<td>${deposit.amount}</td>
																			<th>Pay Towards</th>
																			<td>${deposit.towards}</td>
																			
																		</tr>
																	</c:forEach>
																
																<c:forEach var="element" items="${lecServiceDetails}">
																<tr>
																	<th colspan="4">LEC&nbsp;Details&nbsp;(Licensed Electrical Contractor) </th>
																</tr>
																<tr>
																	
																	<th>Contractor&nbsp;Name</th>
																	<td>${element.lecname}</td>
																	<th>Address</th>
																	<td>${element.lecaddress}</td>
																	
																</tr>
																<tr>
																	
																	<th>License&nbsp;Number</th>
																	<td>${element.leclicenseno}</td>
																	<th>License&nbsp;Validity</th>
																	<td><fmt:formatDate value="${element.lecvalidity}" pattern="dd-MM-yyyy"/></td>
																	
																</tr>
																<tr>
																	
																	<th>Supervisor&nbsp;Permit&nbsp;No</th>
																	<td>${element.supervisorpermitno}</td>
																	<th>Permit&nbsp;No&nbsp;Validity</th>
																	<td><fmt:formatDate value="${element.supervisorvalidity}" pattern="dd-MM-yyyy"/></td>
																	
																</tr>
																<tr>
																	
																	<th colspan="1">Class</th>
																	<td colspan="1">${element.lecclass}</td>
																	
																	
																</tr>
																
																</c:forEach>
																
															</tbody>
														</table>
												</div>
											</div>
											

									
											
											<c:choose>
											<c:when test = "${finalStatus==12 || finalStatus==22}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s3"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s3">
											</c:otherwise>
											</c:choose>
											
											
	                                          	<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															
															<tbody>
															
															
															 <c:set var="count" value="${fn:length(fieldVerificationServiceDetails)}" scope="page" />
																<c:forEach var="element" items="${fieldVerificationServiceDetails}">
															
																	<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/></font>. <font color="#71843F"> Field Verification Details</font></th>
																		
																	</tr>
																	<c:set var="count" value="${count - 1}" scope="page"/>
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
																	<td  colspan="2">No</td>
																	</c:when>
																	<c:otherwise>
																	<td  colspan="2">Yes</td>
																	</c:otherwise>
																	</c:choose>
																</tr>
																
																
																<%-- <tr>
																	<th>Meter Phase </th>
																	<c:choose >
																		<c:when test="${element.meterphase ==1}">
																		<td  colspan="2">Single Phase</td>
																		</c:when>
																		<c:otherwise>
																		<c:if test="${element.meterphase ==3}">
																		  <td  colspan="2">Three Phase</td>	
																		</c:if>																
																		</c:otherwise>
																	</c:choose>
																	
																</tr>
																
																<tr>
																	<th>Meter Rating</th>
																	<td  colspan="2">${element.meterrating}</td>
																</tr>
																
																
																<tr>
																	<th>Meter Capacity</th>
																	<td  colspan="2">${element.metercapacity}</td>
																</tr> --%>
																
																<tr>
																	<th>Pole Number</th>
																	<td  colspan="2">${element.polenumber}</td>
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
																
																	<c:if test="${tarifftypeltht =='LT'}">
																	<tr>
																		<th>Feeder Name</th>
																		<td  colspan="2">${element.feedername}</td>
																	</tr>
																	
																	<tr>
																		<th>Feeder Code</th>
																		<td  colspan="2">${element.feedercode}</td>
																	</tr>
																	
																	<tr>
																		<th>DTC Name</th>
																		<td  colspan="2">${element.dtcname}</td>
																	</tr>
																	
																	<tr>
																		<th>DTC Code</th>
																		<td  colspan="2">${element.dtccode}</td>
																	</tr>
																</c:if>
																
																<c:if test="${tarifftypeltht =='HT'}">
																	<tr>
																		<th>Existing/Proposed Feeder Name</th>
																		<td  colspan="2">${element.feedername}</td>
																	</tr>
																	
																	<tr>
																		<th>Existing/Proposed Feeder Code</th>
																		<td  colspan="2">${element.feedercode}</td>
																	</tr>
																	
																																		
																	<tr>
																		<th>Proposed DTC Capacity</th>
																		<td  colspan="2">${element.dtccode}</td>
																	</tr>
																</c:if>
																
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${element.remarks}</font></b></td>
																</tr>
																
																<tr>
																	
																	<td colspan="4"><br></td>
																</tr>
																
																</c:forEach>
															</tbody>
														</table>
													</div>
											</div>
											
											
											
											<c:choose>
											<c:when test = "${finalStatus==13 || finalStatus==23 || finalStatus==61 || finalStatus==62 || finalStatus==63
											|| finalStatus==64 || finalStatus==65 || finalStatus==66 || finalStatus==67 || finalStatus==68 || finalStatus==69
											|| finalStatus==70 || finalStatus==71 || finalStatus==72 || finalStatus==73}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s4"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s4">
											</c:otherwise>
											</c:choose>
											
                                        			<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:set var="count" value="${fn:length(estimationServiceDetails)}" scope="page" />
															<c:forEach var="estimation" items="${estimationServiceDetails}">
																<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/></font>.<font color="#71843F">.Estimation Details</font></th>
																		
																	</tr>
																<c:set var="count" value="${count - 1}" scope="page"/>
																
																<tr>
																	<th>Estimation Ref No.</th>
																	<td>${estimation.estmnno}</td>
																</tr>
																<tr>
																	<th>Estimation Date  </th>
																	<td><fmt:formatDate value="${estimation.estmndate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																<th>No of Floors Existing</th>
																<td>${estimation.nooffloorsexisting}</td>
																</tr>
																	
																<tr>
																	<th>No of Floors Added</th>
																	<td>${estimation.nooffloorsadded}</td>
																</tr>
																<tr>
																	<th>Existing Plinth Area</th>
																	<td>${estimation.existingplintharea}</td>
																	</tr>
																<tr>
																	<th>New Plinth Area</th>
																	<td>${estimation.newplintharea}</td>
																</tr>	
																	
																	
																<tr>
																<th>Building Height</th>
																<td>${estimation.buildingheight}</td>
																</tr>
																
																
																<tr>
																	<th>Estimation Description </th>
																	<td>${estimation.estmndesc}</td>
																</tr>
																<tr>
																	<th>Estimation Cost</th>
																	<td><fmt:formatNumber type="number" minFractionDigits="2" value="${estimation.estmcost}" /></td>
																</tr>
																<tr>
																	<th>Estimation Done By </th>
																	<td>${estimation.username}</td>
																</tr>
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${estimation.remarks}</font></b></td>
																</tr>
																<tr>
																	<td colspan="4"></td>
																</tr>
																</c:forEach>
																
																<c:if test="${empty estimationServiceDetails && (finalStatus==13 || finalStatus==14 || finalStatus==15 || finalStatus==16 || finalStatus==17 || finalStatus==18 || finalStatus==19 || finalStatus==24 || finalStatus==26 || finalStatus==27 || finalStatus==28 || finalStatus==29)}">
																<fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;Estimation is not required for this Application.</h6>
																	</fieldset>
															    </c:if>
															    
															</tbody>
														</table>
													</div>
										
											</div>
											
											<c:choose>
											<c:when test = "${finalStatus==14 || finalStatus==24}"> 
											  <div class="tab-pane fade" id="s5"> 	
											</c:when>
											<c:otherwise>
											  <div class="tab-pane fade" id="s5">
											</c:otherwise>
											</c:choose>
											
											
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
																	
																	<tr>
																		<th>PC Test Required</th>
																		<td>${powersanc.pcTestRequired}</td>
																	</tr>
																	
																	
																	<c:choose >
																		<c:when test="${tarifftypeltht =='LT'}">
																		   <tr>
																		<th>Account Id</th>
																		<td>${powersanc.accountid}</td>
																	</tr>
																	<tr>
																		<th>No of Floors Existing</th>
																		<td>${powersanc.nooffloorsexisting}</td>
																	</tr>
																			
																	<tr>
																		<th>No of Floors Added</th>
																		<td>${powersanc.nooffloorsadded}</td>
																	</tr>
																	<tr>
																		<th>Existing Plinth Area</th>
																		<td>${powersanc.existingplintharea}</td>
																		</tr>
																	<tr>
																		<th>New Plinth Area</th>
																		<td>${powersanc.newplintharea}</td>
																	</tr>	
																		
																		
																		<tr>
																		<th>Building Height</th>
																		<td>${powersanc.buildingheight}</td>
																		</tr>
																		<tr>
																		<th>Proposed Sanctioned Load(KW)</th>
																		<td>${powersanc.prosanctionedload}</td>
																		</tr>
																		
																		<tr>
																		<th>Existing Sanctioned Load(KW)</th>
																		<td>${powersanc.existingsanctionedload}</td>
																		</tr>
																		
																		
																		<tr>
																		<th>Single phase Meters(NOs)</th>
																		<td>${powersanc.singlephasemtrno}</td>
																		</tr>
																		
																		<tr>
																		<th>Three phase Meters(NOs)</th>
																		<td>${powersanc.threephasemtrno}</td>
																		</tr>
																		
																		<tr>
																		<th>ETV Meters(NOs)</th>
																		<td>${powersanc.etvmtrno}</td>
																		</tr>
																		
																		<tr>
																		<th>Top box(NOs)</th>
																		<td>${powersanc.topboxno}</td>
																		</tr>
																		
																		<tr>
																		<th>CTR (NOs)</th>
																		<td>${powersanc.ctrno}</td>
																		</tr>
																		
																		<tr>
																		<th>Existing KVA</th>
																		<td>${powersanc.existingkva}</td>
																		</tr>
																		
																		<tr>
																		<th>Enhancement of TC Capacity(KVA)</th>
																		<td>${powersanc.tccapacitykva}</td>
																		</tr>
																		
																		<tr>
																		<th>New KVA for Service Connection</th>
																		<td>${powersanc.newkvaforservice}</td>
																		</tr>
																		
																		</c:when>
																		<c:otherwise>
																		
																		<%-- <tr>
																		<th>Reference No HT</th>
																		<td>${powersanc.referencenoforHT}</td>
																		
																		</tr> --%>
																		
																		</c:otherwise>
																	</c:choose>
																	
																		<tr>
																		<th>Power Supply arranged at (KVA)</th>
																		<td>${powersanc.arrangedpowersupply}</td>
																		
																		</tr>
																		
																		
																	<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${powersanc.remarks}</font></b></td>
																    </tr>
																	
																	
																 </c:forEach>
																
																	
																	
																</tbody>
															</table>
													     </div>
											
											</div>
											
											<c:choose>
											<c:when test = "${finalStatus==-12 || finalStatus==-22}"> 
											  <div class="tab-pane fade active in padding-10 no-padding-bottom" id="sda12"> 	
											</c:when>
											<c:otherwise>
											  <div class="tab-pane fade" id="sda12">
											</c:otherwise>
											</c:choose>
											
											
											<div class="col-sm-6">
															<br>
															<table class="table table-bordered table-striped">
																<tbody>
																<c:set var="count" value="${fn:length(applicationVerificationDetails)}" scope="page" />
																<c:forEach var="applicationVer" items="${applicationVerificationDetails}">
																	
																	<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/></font>.<font color="#71843F"> Field Verification Acceptance Details</font></th>
																		
																	</tr>
																	<c:set var="count" value="${count - 1}" scope="page"/>
																	
																	<tr>
																		<th>Verification Date  </th>
																		<td><fmt:formatDate value="${applicationVer.verdate}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	
																	<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${applicationVer.remarks}</font></b></td>
																    </tr>
																	
																	<tr>
																	<td colspan="4"><br></td>
																	
																    </tr>
																	
																 </c:forEach>
																 
																 <c:if test = "${empty applicationVerificationDetails &&  (finalStatus==13 || finalStatus==14 || finalStatus==15 || finalStatus==16 || finalStatus==17 || finalStatus==18 || finalStatus==19 || finalStatus==24 || finalStatus==26 || finalStatus==27 || finalStatus==28 || finalStatus==29)}">
																 <fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;Field Verification Acceptance is not required for this Application</h6>
																 </fieldset>
																 </c:if>
																 
																 <c:if test = "${empty applicationVerificationDetails &&  (finalStatus==-12)}">
																 <fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;Waiting for Field Verification Acceptance</h6>
																 </fieldset>
																 </c:if>
																
																	
																	
																</tbody>
															</table>
											</div>
											
											</div>
											
											<c:choose>
											<c:when test = "${finalStatus==15 || finalStatus==14}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s6"> 	
											</c:when>
											<c:otherwise>
											  <div class="tab-pane fade" id="s6">
											</c:otherwise>
											</c:choose>
											
										  <article class="col-sm-12">
											<div class="col-sm-3">
											<br>
											
											<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget jarviswidget-sortable jarviswidget-color-greenLight" >
													
													<header>
														
														<h2>Required Deposit Details</h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
														
															<table class="table table-bordered table-striped">
																<tbody>
																	
																	<tr>
																		<th>Towards</th>
																		<th>Amount</th>
																		
																	</tr>
																	
																	<c:forEach var="depositsReq" items="${depositsReqService}">
																		<c:if test="${depositsReq.amount!=0}">
																			<tr>
																				<td >${depositsReq.towards}</td>
																				<td >${depositsReq.amount}</td>
																			</tr>
																		</c:if>
																		
																		
																	</c:forEach>
																	
																	
																	
																	<c:forEach var="estimation" items="${estimationServiceDetails}">
																	<tr>
																			<td >Estimation Cost</td>
																			<td ><fmt:formatNumber type="number" minFractionDigits="2" value="${estimation.estmcost}" /></td>
																		</tr>
																	</c:forEach>
																	
																</tbody>
															</table>
											         </div>
											  </div>
											  </div>
											  </div>
											
											
											
														
														
														
											<div class="col-sm-5">
												<br><!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget jarviswidget-color-orange jarviswidget-sortable" id="widid-86" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"></span>
														<h2>Deposit Paid Details</h2>				
													</header>
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
														
												<table class="table table-bordered table-striped" id="table2">
													<tbody>
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
														
														
													</tbody>
												</table>
												
											 </div>
											  </div>
											  </div>
											  </div>
													     
													 
													     
									     <div class="col-sm-4">
												<br><!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget  jarviswidget-sortable jarviswidget-color-orangeDark" id="widid-86" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"></span>
														<h2>Wiring Completion Details</h2>				
													</header>
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
											
											
											<table class="table table-bordered table-striped" id="table3">
												<tbody>
													
													<tr>
														<th>Wiring Completion Date</th>
														<th>Total Outlet Points </th>
														<th>Total Outlet Watts</th>
														<th>Wiring Completion Submitted On</th>
													</tr>
													
													<c:forEach var="lec" items="${lecWCServiceDetails}">
													<tr>
														<td><fmt:formatDate value="${lec.lecwcdate}" pattern="dd-MM-yyyy"/></td>
														<td>${lec.outletpoints}</td>
														<td>${lec.outletwatts}</td>
														<td><fmt:formatDate value="${lec.datestamp}" pattern="dd-MM-yyyy"/></td>
														
													</tr>
													</c:forEach>
													
												</tbody>
											</table>
											
											
											 </div>
											  </div>
											  </div>
											  </div>
										</article>		 
								
													     
											</div>
											
											
											
											<c:choose>
											<c:when test = "${finalStatus==16 || finalStatus==26 || finalStatus==86 || finalStatus==87}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s7"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s7">
											</c:otherwise>
											</c:choose>
											
													<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:forEach var="workOrder" items="${workOrderServiceDetails}">
																<tr>
																	<th>Work Order Ref No.</th>
																	<td>${workOrder.worefno}</td>
																</tr>
																<tr>
																	<th>Work Order Date  </th>
																	<td><fmt:formatDate value="${workOrder.wocdate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																<tr>
																	<th>Work Order Done By </th>
																	<td>${workOrder.username}</td>
																</tr>
																<tr>
																	<th>Meter To Be Purchased by Consumer </th>
																		<c:choose >
																		<c:when test="${workOrder.meterpuchase ==0}">
																		<td>NO</td>
																		</c:when>
																		<c:otherwise>
																		<td>Yes</td>																	
																		</c:otherwise>
																		</c:choose>
																	
																	
																</tr>
																<tr>
																		<th>Work Order  Submitted On  </th>
																		<td><fmt:formatDate value="${workOrder.datestamp}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${workOrder.remarks}</font></b></td>
																 </tr>
																</c:forEach>
															</tbody>
															
														</table>
													</div>
											</div>
											
											
											
											
												
											<c:choose>
											<c:when test = "${finalStatus==17 || finalStatus==27}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s8"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s8">
											</c:otherwise>
											</c:choose>

													<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
																<c:set var="count" value="${fn:length(meterProServiceDetails)}" scope="page" />
																<c:forEach var="meterPro" items="${meterProServiceDetails}">
															
																	<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/></font>. <font color='red'> Meter PC/Test Details</font></th>
																		
																	</tr>
																	<c:set var="count" value="${count - 1}" scope="page"/>
																
																
																
																<tr>
																	<th>Meter/PC Test Ref No.</th>
																	<td>${meterPro.porefno}</td>
																</tr>
																<tr>
																	<th>Meter/PC Test Date  </th>
																	<td><fmt:formatDate value="${meterPro.podate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																<tr>
																	<th>Meter/PC Test Done By </th>
																	<td>${meterPro.username}</td>
																</tr>
																<tr>
																	<th>Meter Phase </th>
																	<td  colspan="2">${meterPro.meterphase}&nbsp;phase</td>
																</tr>
																
																<tr>
																	<th>Meter Make </th>
																	<td  colspan="2">${meterPro.metermake}</td>
																</tr>
																
																<tr>
																	<th>Meter Type </th>
																	<td  colspan="2">${meterPro.metertype}</td>
																</tr>
																
																<tr>
																	<th>Meter Sl No </th>
																	<td  colspan="2">${meterPro.meterslno}</td>
																</tr>
																
																<tr>
																	<th>C.T Ratio </th>
																	<td  colspan="2">${meterPro.ctratio}</td>
																</tr>
																<tr>
																	<th>P.T </th>
																	<td  colspan="2">${meterPro.ptratio}</td>
																</tr>
																
																<tr>
																	<th>Meter Accuracy</th>
																	<td  colspan="2">${meterPro.meteraccuracy}</td>
																</tr>
																
																<tr>
																	<th>Meter Option</th>
																	<td  colspan="2">${meterPro.meteroption}</td>
																</tr>
																
																<tr>
																	<th>Bidirectional</th>
																	<td  colspan="2">${meterPro.bidirectional}</td>
																</tr>
																
																
																
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${meterPro.remarks}</font></b></td>
																 </tr>
																</c:forEach>
																
																<c:if test="${empty meterProServiceDetails && (finalStatus==17 || finalStatus==18 || finalStatus==19)}">
																<fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;You have not Purchased Meter.</h6>
																	</fieldset>
															    </c:if>
															    
															</tbody>
														</table>
													</div>
											</div>
											
											
											
											<c:choose>
											<c:when test = "${finalStatus==18 || finalStatus==28}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s9"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s9">
											</c:otherwise>
											</c:choose>
                         
													<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:forEach var="wcr" items="${wcrServiceDetails}">
																<tr>
																	<th>Work Completion Ref No.</th>
																	<td>${wcr.wcrrefno}</td>
																</tr>
																<tr>
																	<th>Work Completion Date  </th>
																	<td><fmt:formatDate value="${wcr.wcrdate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																	<th>Work Completion Done By </th>
																	<td>${wcr.username}</td>
																</tr>
																
																<tr>
																	<th>Work Completion Submitted Date  </th>
																	<td><fmt:formatDate value="${wcr.datestamp}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${wcr.remarks}</font></b></td>
																 </tr>
																
																</c:forEach>
															</tbody>
														</table>
													</div>
											</div>
											
											
											
											
											
											<c:choose>
											<c:when test = "${finalStatus==19 || finalStatus==29}"> 
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s10"> 	
											</c:when>
											<c:otherwise>
											<div class="tab-pane fade" id="s10">
											</c:otherwise>
											</c:choose>
											
											
												<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:forEach var="ins" items="${installationService}">
																<tr>
																	<th>RR No</th>
																	<td>${ins.rrno}</td>
																</tr>
																
																<tr>
																	<th>Service&nbsp;Ref&nbsp;No.</th>
																	<td>${ins.servrefno}</td>
																</tr>
																<tr>
																	<th>Installation&nbsp;Service&nbsp;Date</th>
																	<td><fmt:formatDate value="${ins.servrdate}" pattern="dd-MM-yyyy"/></td>
																</tr>
																
																<tr>
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${ins.remarks}</font></b></td>
																 </tr>
																
																</c:forEach>
															</tbody>
														</table>
													</div>
													
											

										</div>
												
												
												<div class="tab-pane fade" id="s15">
													<div class="col-sm-8">
														<br>
												
														<table class="table table-bordered table-striped">
															<thead>
																<tr>
																	<th width="220">Document&nbsp;Name</th>
																	<th>Type of Document</th>
																	<th>Submitted&nbsp;Date</th>
																	<th>View /Download</th>
													      </tr>
															</thead>
															<tbody>
																<c:forEach var="app" items="${documentDetails}" varStatus="status">
																<c:if test="${status.first}">
																
																
																<c:set var="dateAppForm" scope="session" value="${app.uploadedOn}"/>
																</c:if>
																<c:if test="${status.last}">
											
																	<tr>
																		<td>Application Form</td>
						        										<td>PDF</td>
																		<td><fmt:formatDate value="${dateAppForm}" pattern="dd-MM-yyyy" /></td>
																		<td><a href='#' onclick="return applicationDocumentView(${app.applicationid});"><strong>View/Download</strong></a></td>
																
																	</tr>
											
											
																</c:if>
															</c:forEach>
																
																<c:forEach var="app" items="${documentDetails}">
																	<tr>
																		<td>${app.docname}</td>
																		<td>${app.typeOfDoc}</td>
																		<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy" /></td>
																		<td><a href='#' onclick="return checkViewDocument(${app.id});"><strong>View/Download</strong></a></td>
																	</tr>
															<c:if test="${app.docname=='Power Sanction'}">
															<tr>
																		<td>Power Sanction Intimation Letter</td>
																		<td>PDF</td>
																		<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy" /></td>
															<td><a href='#' onclick="viewpowerSanctionReport(${applicationId});">View/Download</a>  </td>
															</c:if>
															
															<c:if test="${app.docname=='Work Order Document'}">
															<tr>
																		<td>Meter Procurement Letter</td>
																		<td>PDF</td>
																		<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy" /></td>
															<td><a href='#' onclick="viewMeterProcurementReport(${applicationId});">View/Download</a>  </td>
															
															</c:if>
															
															<c:if test="${app.docname=='Service Document' || app.docname=='CR Document'}">
															<tr>
																		<td>Service Certificate</td>
																		<td>PDF</td>
																		<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy" /></td>
															<td><a href='#' onclick="viewServiceCertificate(${applicationId});">View/Download</a>  </td>
															
															</c:if>
															
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
                         </div>
						</article>
					</div>

			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->

		
		

		

		

<script type="text/javascript">

function viewpowerSanctionReport(applicationId) {
	window.open("./document/report/view/" + applicationId);
}
function viewMeterProcurementReport(applicationId){
	window.open("./document/meterProcurement/view/" + applicationId);
}
function viewServiceCertificate(applicationId) {
	window.open("./viewServiceCertificate/download/"+applicationId);
}


function reasontoOnhold(){
	var str1 = document.getElementsByClassName('remarksContent')[0].innerHTML;	
	var str = str1.replace("null", "");
	alert(str);
}

function applicationDocumentView(appliId){
	
	window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
}

function checkViewDocument(docId) {
	window.open("./customerUploadedDoc/download/"+docId);
}

$('#receiptDate').datepicker({
	  dateFormat : 'dd/mm/yy',
	  maxDate: new Date()

}); 

$('#lecwcdate').datepicker({
	  dateFormat : 'dd/mm/yy',
	  

}); 

$('#servrdate').datepicker({
		dateFormat : 'dd/mm/yy'
		
});
    
</script>

		
		