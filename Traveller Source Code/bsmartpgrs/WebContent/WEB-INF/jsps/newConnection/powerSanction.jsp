<%@include file="/common/taglibs.jsp"%>
 <%@include file="/common/commonPage.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>


<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>


			<div id="content">
			<jsp:include page="../newConnection/viewLayout.jsp"/>
			<jsp:include page="../newConnection/viewMultipleConnections.jsp"/>
			<c:if test="${not empty htAuthenticate}">
				<script>
					var result = "${htAuthenticate}";
					alert(result);
				</script>
						
			   <c:remove var="htAuthenticate" scope="session" />
		   </c:if>
		   
			<div style="display: none;">
              	<table>
           		<tr><td class="appphase">${appPhase}<td></tr>
            	</table>
            </div>
				<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
							<div class="col-sm-3" >
							<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:350px;">In Progress </a>							
							</div>		
							
							<div class="col-sm-3">
							<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:350px;">Completed </a>						           
							</div>		
							
							<div class="col-sm-3">
						            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:350px;">On Hold </a>
							</div>	
							
							
							<div class="col-sm-3">
						             <a class="btn bg-color-blueLight btn-lg txt-color-white" id="redirectData5" style="width:300px;">Clarifications </a>
							</div>	
									
				
				</div>
				
                         <div class="row">
						<article class="col-sm-12">
							<!-- new widget -->
							<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon">  </span>
										<h2>Application ID - <b><font color="maroon">${applicationId} </font> </b> </h2>
                                    <h2><a href="#" onclick='applicationHistoryPopUp(${app.applicationid})'>Timeline/Log Details</a></h2>
                                    <h2><a href="#" onclick='applicationClarificationsPopUp(${applicationId})'>Clarifications</a></h2>     
									<ul class="nav nav-tabs pull-right in" id="myTab">
										<li>
											<a data-toggle="tab" href="#s1"><i class=""></i> <span class="hidden-mobile hidden-tablet">Application</span></a>
										</li>
										<li>
											<a data-toggle="tab" href="#s2"><i class=""></i> <span class="hidden-mobile hidden-tablet">Field Ver</span></a>
										</li>

										<li id="estli">
											<a data-toggle="tab" href="#s3"><i class=""></i> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li>
											<a data-toggle="tab" href="#s7"><i class=""></i> <span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li class="active">
										
											<a data-toggle="tab" href="#s4"><i class=""></i> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										<li >
										
											<a data-toggle="tab" href="#s5"><i class=""></i> <span class="hidden-mobile hidden-tablet">Document</span></a>
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
											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
															<tbody>
														<c:forEach var="element" items="${applicationServiceDetailsList}">
															
															    <tr>
																	<th colspan="4">Application Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Location(Section)
																	&nbsp;:&nbsp;<b><font color="red">${locationText}</font></b>&nbsp;&nbsp;&nbsp;&nbsp;Division&nbsp;:&nbsp;<b><font color="red">${divisionText}</font></b>
																	&nbsp;&nbsp;&nbsp;Sub Division&nbsp;:&nbsp;<b><font color="red">${subdivisionText}</font>&nbsp;&nbsp;
																	
																	<c:if test="${element.applicationtype =='Multiple Connection' || element.applicationtype =='MS Building'}">
																	<a class="btn bg-color-pinkDark txt-color-white"
																		style="width:180px;"
																		onclick="return viewSubConnection(${applicationId});" title="View multiple connections for MS Building/Multiple Connection">
																		<font size="2"><b>View Connections</b></font>
																	</a> &nbsp;&nbsp;
																	
																	</c:if>
																	
																	<c:if test="${element.applicationtype =='Layout'}">
																		
																		<a class="btn bg-color-pinkDark txt-color-white"
																			style="width:150px;"
																			onclick="return viewLayouts(${applicationId});" title="View Layout Data">
																			<font size="2"><b>View Multiple Sites</b></font>
																		</a> &nbsp;&nbsp;
																    </c:if>
																	
																	<input type="button" value="View Application"
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
																	<td class="tariffMain" hidden="true">${element.tariffmain}</td>
																	<td class="tariffdesc">${element.tariffdesc}</td>
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
																	<td class="meterPhaseSelect" hidden="true">${element.reqphase}</td>
																	<c:choose >
																	<c:when test="${element.reqphase ==1}">
																	<td>Single Phase</td>
																	</c:when>
																	<c:otherwise>
																	<td>Three Phase</td>
																	</c:otherwise>
																	</c:choose>
																	
																	<th>Application Type</th>
												    				<td class="ApplicationType">${element.applicationtype}</td>
																	
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td>${element.supplyfor}</td>
																	
																	<th>No Of Connections for this Tariff</th>
																	<td class="noOfConnection">${element.noofconnections}</td>
																	
																</tr>
																
																<tr>
																	
																	<th>If Govt.</th>
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
																	<c:choose >
																	<c:when test="${element.splgovtscheme =='0'}">
																	<td></td>
																	</c:when>
																	<c:otherwise>
																	<td>${element.splgovtscheme}</td>
																	</c:otherwise>
																	</c:choose>
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
											
											<div class="tab-pane fade" id="s2">
	                                          	<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															
															<tbody>
															  <c:set var="count" value="${fn:length(fieldVerificationServiceDetails)}" scope="page" />
																<c:forEach var="element" items="${fieldVerificationServiceDetails}">
															
																	<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/>.</font> <font color="#71843F"> Field Verification Details</font></th>
																		
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
																	<td class="estreq" colspan="2">No</td>
																	</c:when>
																	<c:otherwise>
																	<td class="estreq" colspan="2">Yes</td>
																	</c:otherwise>
																	</c:choose>
																	
																</tr>
																<%-- <tr>
																	<th>Required Phase </th>
																	<td  colspan="2">${element.phasereqd} </td>
																</tr>
																
																<tr>
																	<th>Meter Phase </th>
																	<td  colspan="2" class="meterPhaseSelect">${element.meterphase} </td>
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
											
											<div class="tab-pane fade" id="s3">
                                        			<div class="col-sm-6">
														<br>
														<table class="table table-bordered table-striped">
															<tbody>
															<c:set var="count" value="${fn:length(estimationServiceDetails)}" scope="page" />
															<c:forEach var="estimation" items="${estimationServiceDetails}">
																	<tr>
																		<th colspan="4"><font color="red"><c:out value="${count}"/>.</font><font color="#71843F">Estimation Details</font></th>
																		
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
																	<th>Estimation Description </th>
																	<td>${estimation.estmndesc}</td>
																</tr>
																<tr>
																	<th>Estimation Cost</th>
																	<td class="estiCost"><fmt:formatNumber type="number" minFractionDigits="2" value="${estimation.estmcost}" /></td>
																</tr>
																<tr>
																	<th>Estimation Done By </th>
																	<td>${estimation.username}</td>
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
																	<th>Remarks - Comments</th>
																	<td><b><font color="red">${element.remarks}</font></b></td>
																</tr>
																<tr>
																	
																	<td colspan="4"><br></td>
																</tr>
																</c:forEach>
																
																<c:if test="${empty estimationServiceDetails}">
																<fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;Estimation is not required for this Application.</h6>
																	</fieldset>
															    </c:if>
															    
															</tbody>
														</table>
													</div>
										
											</div>
											
											
											<div class="tab-pane fade" id="s7">
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
																 
																 <c:if test = "${empty applicationVerificationDetails}">
																 <fieldset>
																	<h6>&nbsp;&nbsp;&nbsp;&nbsp;Field Verification Acceptance is not required for this Application</h6>
																 </fieldset>
																 </c:if>
																
																
																	
																	
																</tbody>
															</table>
													</div>
										
											</div>
											
											
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s4">
											
											
											 <article class="col-sm-12">
												
												<c:if test="${empty powerSanctionServiceDetails}">
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget" id="wididpowersanction" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
														<h2>Power Sanction Details &nbsp;&nbsp;&nbsp;&nbsp;
														
														<input type="button" value="Forward Application To Clarifications"
													                class="btn btn-primary" onclick="showForwardDiv();"  id="forwardApplicationIds">
														
														</h2>				
													</header>
													
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form:form action="./powerSanctionInsert" method="post" id="powerSanc-form" class="smart-form" commandName="powersanction" modelAttribute="powersanction" enctype="multipart/form-data" >
																
																<input type="text" hidden="true" name="arrangedpowersupply" autocomplete="off" id="arrangedpowersupply" value="0" placeholder="Arranged Power Supply by CESC" onchange="myFunction16(this.value)">
																<input type="number" hidden="true" name="nooffloorsexisting" autocomplete="off" placeholder="Floors Existed"  id="nooffloorsexisting" min="0" value="${noOfFloorsExisting}" onchange="myFunction(this.value)"/>
																<input type="number" hidden="true" name="nooffloorsadded"  autocomplete="off" id="nooffloorsadded" placeholder="Floors Added/New" min="0" value="${noOfFloorsAdded}" onchange="myFunction1(this.value)"/>
																<input type="text" hidden="true" name="existingplintharea" autocomplete="off" id="existingplintharea" placeholder="Area(Sq.m)" value="${existingplintharea}" onchange="getTotalPlinthArea(this.value);"/>
																<input type="text" hidden="true" name="newplintharea" autocomplete="off" id="newplintharea" placeholder="Area(Sq.m)" value="${newplintharea}" onchange="getTotalPlinthAreaNew(this.value);"/>
																<input type="text" hidden="true" name="buildingheight" autocomplete="off" id="buildingheight" placeholder="Building height (mtr)" value="${buildingHeight}" onchange="myFunction8(this.value)"/>
																
																
																<input type="text" hidden="true" name="countSize" id="countSize"/>
																<input type="text" hidden="true" name="applicationtypes" id="applicationtypes" value="${applicationType}"/>
																
																<fieldset>
																
																
																<div class="row">
																		
																		<section class="col col-2">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<input type="text"	name="applicationid" placeholder="Application ID"  id="applicationid" value="${applicationId}" readonly="readonly"/>
																			</label>
																		</section> 
																		
																		<section class="col col-2">
																			<label class="label">Account ID&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<input type="text" name="accountid" id="accountid" autocomplete="off" onchange="checkduplicateAccountId()" onkeyup="convertToUpperCase();" autocomplete="off" placeholder="Account ID">
																			</label>
																		</section>
																		
																		 <section class="col col-2">
																			<label class="label">Power San. Ref Number&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<input type="text" name="psrefno" id="psrefno" autocomplete="off" onchange="checkduplicate()" autocomplete="off" placeholder="Reference No">
																			</label>
																		</section>
																		
																		
																		<section class="col col-2">
																			<label class="label">Power Sanction Date&nbsp;<font color="red">*</font></label>
																			<label class="input"> <i class="icon-append fa fa-calendar"></i>
																				<input type="text" name="psdate" id="psdate" autocomplete="off" placeholder="PS Date">
																			</label>
																		</section>
																		
																		<section class="col col-2">
																			<label class="label">Service Tax&nbsp;(%)<font color="red">*</font></label>
																			<label class="input">
																				<input type="text" name="serviceTax" id="serviceTax" placeholder="Service Tax" value="0" onchange="myFunction20(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2">
																			<label class="label">Swatch&nbsp;Bharat&nbsp;Tax(%)<font color="red">*</font></label>
																			<label class="input">
																				<input type="text" name="swatchBharatCess" id="swatchBharatCess" placeholder="SWatch Bharat" value="0">
																			</label>
																		</section>
																		
																		
																</div>
																
												
																	<div class="row">
																		
																		
																		
																		<c:if test="${applicationType!='MS Building' && applicationType!='Multiple Connection'}">
																		<section class="col col-2" id="mmdorisdId">
																			<label class="label">2MMD/ISD Amount&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="mmd" id="mmd" min="0" value="${isdAmount}" onchange="myFunction2(this.value)">
																			</label>
																		</section>
																		</c:if>
																		
																		
																		
																		<section class="col col-2" id="msdhideid">
																			<label class="label">MSD Amount&nbsp;</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="msd" id="msd" min="0" value="0" onchange="myFunction3(this.value)">
																			</label>
																		</section>
																		
																		
																		
																		<section class="col col-2" id="isdforLT">
																			<label class="label">Infrastructure&nbsp; Charges</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="isd" id="isd" value="0" onchange="myFunction4(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="slcforLT">
																			<label class="label">SLC(Development Charge)&nbsp;</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="slc" id="slc" value="${slcAmount}" min="0" onchange="myFunction5(this.value)">
																			</label>
																		</section>
																		
																		
																		<section class="col col-2" id="supervisionChargesId">
																			<label class="label">Supervision Charges(SC)&nbsp;</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="sc" id="sc" value="${scAmount}" min="0" onchange="myFunction7(this.value)"> 
																			</label>
																		</section>
																		
																		<section class="col col-2">
																			<label class="label">DCWA Charges&nbsp;</label>
																			<label class="input">
																				<input type="number" name="dcwaCharges" id="dcwaCharges" value="0" min="0">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="additionaldevslcforLT">
																			<label class="label">Additional Dev./SLC&nbsp;</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="additionaldevslc" id="additionaldevslc" value="0" min="0" onchange="myFunction6(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2">
																			<label class="label">PC&nbsp;Test&nbsp;Required&nbsp;<font color="red">*</font></label>
																			<label class="select">
																			
																			<form:select  id="pcTestRequired" path="pcTestRequired" name="pcTestRequired" >																		
																	        <option value="0" selected disabled>Select</option>
																	          <option value="No">No</option>																		
																	          <option value="Yes" >Yes</option>
																	        </form:select> <i></i>
																			
																			</label>
																		</section>
																		
																		
																		<section class="col col-2" id="estcostdepositContribution">
																			<label class="label">EST&nbsp;Cost&nbsp;under&nbsp;Deposit<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="depositConWorksCost" autocomplete="off" id="depositConWorksCost" placeholder="EST Cost on Deposit Contribution">
																			</label>
																		</section>
																		
																		
																		<div class="col col-2" id="projectAreaAcresId">
																			<label class="label">Project Area(Acres)&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="projectAreaAcres" autocomplete="off" id="projectAreaAcres" placeholder="Acres">
																			</label>
																		</div>
																		
																		<div class="col col-2" id="projectAreaGuntasId">
																			<label class="label">Project Area(Guntas)&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="projectAreaGuntas" autocomplete="off" id="projectAreaGuntas" placeholder="Guntas">
																			</label>
																		</div>
																		
																		
																		
																		
																		<section class="col col-2" id="singlephasemtrnoforLT">
																			<label class="label">Single phase Meter(NOs)<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="singlephasemtrno" autocomplete="off" id="singlephasemtrno" placeholder="1 phase Meters(NOs)">
																			</label>
																		</section>
																		
																		
																		<section class="col col-2" id="threephasemtrnoforLT">
																			<label class="label">Three phase Meters(NOs)&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="threephasemtrno" autocomplete="off" id="threephasemtrno" placeholder="3 phase Meters(NOs)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="etvmtrnoforLT">
																			<label class="label">ETV Meters(NOs)&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="etvmtrno" autocomplete="off" id="etvmtrno" placeholder="ETV Meters(NOs)">
																			</label>
																		</section>
																		
																		
																		
																		<section class="col col-2" id="topboxnoforLT">
																			<label class="label">Top box(NOs)&nbsp;</label>
																			<label class="input">
																				
																				<input type="text" name="topboxno" autocomplete="off" id="topboxno" placeholder="Top box(NOs)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="ctrnoforLT">
																			<label class="label">CTR (NOs)&nbsp;</label>
																			<label class="input">
																				
																				<input type="text" name="ctrno" autocomplete="off" id="ctrno" placeholder="CTR (NOs)">
																			</label>
																		</section>
																		
																	</div>
																	
																	
																	<div class="row">
																	
																		
																		<section class="col col-2" id="serviceChargeShow">
																			<label class="label">Service Charge &nbsp;</label>
																			<label class="input">
																				
																				<input type="number" name="serviceCharge" autocomplete="off" id="serviceCharge" placeholder="Service Charge" value="0">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="advConsuptionChargeShow">
																			<label class="label">Consumption&nbsp;Charge</label>
																			<label class="input">
																				
																				<input type="number" name="advConsuptionCharge" autocomplete="off" id="advConsuptionCharge" placeholder="Consumption Charge" value="0">
																			</label>
																		</section>
																		
																		
																		
																		<section class="col col-2" id="existingkvaforLT" hidden="true">
																			<label class="label">Existing KVA &nbsp;</label>
																			<label class="input">
																				
																				<input type="text" name="existingkva" autocomplete="off" id="existingkva" placeholder="Existing KVA" value="0" onchange="myFunction13(this.value)">
																			</label>
																		</section>
																		
																		
																		
																		<section class="col col-3" id="tccapacitykvaforLT">
																			<label class="label">Enhancement of TC Capacity(KVA)&nbsp;</label>
																			<label class="input">
																				
																				<input type="text" name="tccapacitykva" autocomplete="off" id="tccapacitykva" placeholder="TC Capacity(KVA)" value="0" onchange="myFunction14(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="newkvaforserviceforLT" hidden="true">
																			<label class="label">New KVA for Service&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				
																				<input type="text" name="newkvaforservice" autocomplete="off" id="newkvaforservice" placeholder="New KVA" value="0" onchange="myFunction15(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="existingsanctionedloadforLT" hidden="true">
																			<label class="label">Existing Sanc. Load(KW)&nbsp;</label>
																			<label class="input">
																				<input type="text" name="existingsanctionedload" autocomplete="off" id="existingsanctionedload" placeholder="Existing Sanctioned Load" value="0" onchange="myFunction10(this.value)">
																			</label>
																		</section>
																		
																		<section class="col col-2" id="prosanctionedloadforLT">
																			<label class="label">Proposed Sanc. Load(KW)&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<input type="text" name="prosanctionedload" autocomplete="off" id="prosanctionedload" placeholder="Proposed Sanctioned Load" value="${proposedSanctionLoadKW}" onchange="myFunction9(this.value)">
																			</label>
																		</section>
																		
																		
																	</div>
																	
																	<div class="row">
																		<section class="col col-10">
																			<label class="textarea">Remarks &nbsp;<font color="red">*</font>										
																				<textarea rows="2" name="remarks" id="remarks" placeholder="Remarks/Comments"></textarea> 
																			</label>
																		</section>
																		
																		
																	</div>
																	
																	<div class="row">
																	
																		<section class="col col-3">
																			<label class="label">Transformer Test Fee&nbsp;</label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<input type="number" name="transformerTestFee" id="transformerTestFee" value="0" min="0"> 
																			</label>
																		</section>
																		
																		<section class="col col-6">
																		<label class="label">Document&nbsp;<font color="red">*</font></label>
																			<div class="input input-file">
																			
																				<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Power Sanction Document" readonly="" autocomplete="off">
																			</div>
																		</section>
																		
																    </div>
																    
																    <c:if test="${applicationType=='MS Building' || applicationType=='Multiple Connection'}">
																    
																    <div class="row">
																	    <section class="col col-12">
																			<table id="allIds">
																				<span onclick="addISDAmount();" style="height: 10px;" id="addISDAmtid"><i
																					class=" fa fa-plus-square"></i> <span
																					style="height: 10px; color: green;cursor: pointer;">Add 2MMD/ISD</span> </span>
																				<tr></tr>
																			</table>
																		</section>
																	</div>
																	
																	</c:if>
										
										
										
																		
																</fieldset>
									
																<footer>
																
																<div id="noteforPowerSanction">
													
																	<label class="col-md-12 control-label">
																		<font style="font-size: 14px; color: red;"><b>Note :</b></font>
																		<font style="font-size: 12px;color: blue;">
																		
																		${PowerSanctionAuthority}
																		</font>
																	</label>
																</div>
																
																
																
															
																
																<button type="submit" class="btn btn-primary" onclick="return  checkValidations();">
																		Submit 
																</button>
																    <a class="btn btn-danger" onclick="return  OnHold('${applicationId}','${appPhase}');" href="javascript:void(0);">On Hold</a>
																    
																    
																   
																    
																	
																</footer>
															</form:form>
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												<!-- end widget -->
												
												</c:if>
												
												
												<c:if test="${not empty powerSanctionServiceDetails}">
												
												
									<article class="col-sm-12">
												  
											<div class="col-sm-6" id="powersanctionEntry">
												
												
												<c:if test="${sessionScope.designation!='AE(Elec)' && sessionScope.designation!='AE(Tech)' && sessionScope.designation!='AEE(Tech)' && sessionScope.designation!='SEE(Tech)' && sessionScope.designation!='EE(Tech)'}">
													<input type="button" value="Approve Power Sanction"
													                class="btn bg-color-pinkDark txt-color-white" onclick="approvePowerSanction(${applicationId});" id="powerSanctionApproveIds">
											     </c:if>              
													                
													                
												  
												 <input type="button" value="Forward Application To Clarifications"
													                class="btn btn-primary" onclick="showForwardDiv();" id="forwardApp">
												 <br>
												<table class="table table-bordered table-striped">
													<tbody>
													
													<c:forEach var="powersanc" items="${powerSanctionServiceDetails}">
														
														<tr>
															<th>Power&nbsp;Sanction&nbsp;Ref&nbsp;No.</th>
															<td>${powersanc.psrefno}</td>
															
														</tr>
														<tr>
															<th>Power&nbsp;Sanction&nbsp;Date  </th>
															<td class="psdate"><fmt:formatDate value="${powersanc.psdate}" pattern="dd-MM-yyyy"/></td>
														</tr>
														<tr>
															<th>Power&nbsp;Sanctioned&nbsp;By</th>
															<td>${powersanc.username}</td>
														</tr>
														
														<tr>
															<th>Power&nbsp;Sanction&nbsp;Submitted&nbsp;On  </th>
															<td><fmt:formatDate value="${powersanc.datestamp}" pattern="dd-MM-yyyy"/></td>
														</tr>
														
														 <tr>
															<th>Account Id</th>
															<td>${powersanc.accountid}</td>
														</tr>
														
														<tr>
														<th>Proposed Sanctioned Load(KW)</th>
														<td>${powersanc.prosanctionedload}</td>
														</tr>
															
														<tr>
														<th>Existing&nbsp;Sanctioned&nbsp;Load(KW)</th>
														<td>${powersanc.existingsanctionedload}</td>
														</tr>
														
													
														
														<c:if test="${tarifftypeltht =='LT' && (applicationType=='MS Building' || applicationType=='Multiple Connection' || applicationType=='Single Connection')}">
													    
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
														<th>New&nbsp;KVA&nbsp;for&nbsp;Service&nbsp;Connection</th>
														<td>${powersanc.newkvaforservice}</td>
														</tr>
														
															
															
														</c:if>
															
														
														<c:if test="${applicationType=='Layout'}">
															<tr>
															<th>Total Project Area in Acres</th>
															<td>${powersanc.projectAreaAcres}</td>
														   </tr>
														   
														   <tr>
															<th>Total Project Area in Guntas</th>
															<td>${powersanc.projectAreaGuntas}</td>
														   </tr>
														   <c:set var="depositcostinder" value="${powersanc.depositConWorksCost}" scope="page"/>
														   <tr>
															<th>Estimated Cost under Deposit Contribution Works</th>
															<td>${powersanc.depositConWorksCost}</td>
														   </tr>
													    </c:if>
														
														
															
															<tr>
																<th>PC Test Required</th>
																<td>${powersanc.pcTestRequired}</td>
															</tr>
															
															<tr>
															<th>Power&nbsp;Supply&nbsp;arranged&nbsp;at&nbsp;(KVA)</th>
															<td>${powersanc.arrangedpowersupply}</td>
															
															</tr>
															
															<tr>
															<th>DCWA Charges</th>
															<td>${powersanc.dcwaCharges}</td>
															
															</tr>
															
															<tr>
															<th>Service Tax(%)</th>
															<td>${powersanc.serviceTax}</td>
															
															</tr>
															
															<tr>
															<th>Swachh Bharat Cess(%)</th>
															<td>${powersanc.swatchBharatCess}</td>
															
															</tr>
																																
														<tr>
														<th>Remarks - Comments</th>
														<td><b><font color="red">${powersanc.remarks}</font></b></td>
													    </tr>
													    
													    
														
													 </c:forEach>
													
														
													    <tr>
														<th>Note: </th>
														<td><b><font color="red">${PowerSanctionAuthority}</font></b></td>
													    </tr>
													    	
													</tbody>
												</table>
										     </div>
										     
										     
										     <div class="col-sm-6" id="requiredDepositId">
												
												 <br><br>
											<c:choose>	
												
											<c:when test="${applicationType=='MS Building' || applicationType=='Multiple Connection'}">	
												
												<table class="table table-bordered table-striped">
													<tbody>
													
													<tr>
														<th>Tariff</th>
														<th>Load(KW - HP)</th>
														<th>Towards</th>
														<th>Amount</th>
													</tr>
													
													<c:forEach var="depositsReq" items="${depositsReqService}">
														<c:if test="${depositsReq.amount!=0}">
														<tr>
															<td >${depositsReq.tariff}</td>
															<td >${depositsReq.loadKW} - ${depositsReq.loadHP}</td>
															<td >${depositsReq.towards}</td>
															<td >${depositsReq.amount}</td>
														</tr>
														</c:if>
														
													</c:forEach>
													
													<c:forEach var="estimation" items="${estimationServiceDetails}">
													    <tr>
													        <td></td>
													        <td></td>
															<td >Estimation Cost</td>
															<td ><fmt:formatNumber type="number" minFractionDigits="2" value="${estimation.estmcost}" /></td>
														</tr>
													</c:forEach>
														
														
													    	
													</tbody>
												</table>
												
											</c:when>
											
											<c:otherwise>
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
																
																<c:if test="${depositcostinder!=0}">
																<td >Estimated Cost under Deposit Contribution Works</td>
																	<td ><fmt:formatNumber type="number" minFractionDigits="2" value="${depositcostinder}" /></td>
																</c:if>
																	
																	
																    	
																</tbody>
															</table>
											</c:otherwise>
											
											
											</c:choose>
												
												
												
												
												
												
												
												
										     </div>
										     
										     
										     
										     
										     
										     
										     </article>
												</c:if>
												</article>

											
											
											
											
											
											<!-- <div class="tab-pane fade active in padding-10 no-padding-bottom" id="showVerificationIds" hidden="true"> -->
											
											    <article class="col-sm-12" id="showVerificationIds" style="display: none;">
													<div class="jarviswidget" id="idforVerication" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
														<h2>Clarifications Required</h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form:form action="./forwardPowerSanctionInsert" method="post" id="powerSanc-form-forward" class="smart-form" enctype="multipart/form-data" >
																<input type="hidden" id="applicationStatus" autocomplete="off" placeholder="" name="applicationStatus" value="In Progress"></input>	
																
																<fieldset>
																
																<div class="row">
																		<section class="col col-3">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<input type="text"	name="applicationid" placeholder="Application ID"  id="applicationid" value="${applicationId}" readonly="readonly"/>
																			</label>
																		</section> 
																		
																		<section class="col col-3">
																			<label class="label">Tariff</label>
																			<label class="input">
																				<input type="text" name="subTariffName"   id="subTariifId" value="${subariff}" readonly="readonly"/>
																			</label>
																		</section> 
																
																	<section class="col col-3" style="display: none;">
																		<label class="label">Reference Number&nbsp;</label>
																		<label class="input">
																			<input type="text" name="refno" id="refno"  autocomplete="off" placeholder="Reference No">
																		</label>
																	</section>
																	
																	
																	<section class="col col-3">
																		<label class="label">Submitted Date&nbsp;<font color="red">*</font></label>
																		<label class="input"> <i class="icon-append fa fa-calendar"></i>
																			<input type="text" name="submitteddate" id="submitteddate" placeholder="Submitted Date" autocomplete="off">
																		</label>
																	</section>
																	
																</div>
																
																
																<section>
																	<label class="textarea">Remarks &nbsp;<font color="red">*</font>										
																		<textarea rows="2" name="remarks" id="remarks" placeholder="Remarks/Comments"></textarea> 
																	</label>
																</section>
																
																<section>
																<label class="label">Document&nbsp;</label>
																	<div class="input input-file">
																	
																		<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Clarifications Document" readonly="" autocomplete="off">
																	</div>
																</section>
																
																<section>
																	<label class="label">Forward To &nbsp;<font color="red">*</font></label>
																	<label class="select">
																	<select  id="officialDesignation"  name="officialDesignation" >																		
															        <option value="0" selected="" disabled="" >Select Official</option>																	      
										                         
										                         <c:forEach items="${forwardOfficials}" var="official">
										                  			<option value="${official.personloginname}-${official.sitecodevalue}-${official.designationname}-${official.personname}-${official.level}">${official.personname} (${official.designationname}) - ${official.level} - ${official.location}</option>
										                     	 </c:forEach>	
										                         																
															        </select> <i></i>
																	</label>
																</section>
																		
																</fieldset>
									
																<footer>
																
																<div id="forwardDiv"">
													
																<c:if test="${not empty forwardApplicationtoNextLevel}">
																	<label class="col-md-12 control-label">
																		<font style="font-size: 14px; color: red;"><b>Note :</b></font>
																		<font style="font-size: 12px;color: blue;">
																		    ${forwardApplicationtoNextLevel}
																		</font>
																	 </label>
																</c:if>
																</div>
																
																
																<button type="submit" class="btn btn-primary">
																		Send to Clarifications 
																</button>
																    <a class="btn btn-danger" onclick="return  cancelForm();" href="javascript:void(0);">Back</a>
																    
																	
																</footer>
															</form:form>
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												<!-- end widget -->
												</article>
											</div>
											<!-- </div> -->
									
										
											
											<div class="tab-pane fade" id="s5">
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
                          </div>
						</article>
					</div>

			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->

		
		<div id="addtab" title="Timeline/Log Details" style="display:none">
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
												<td class="fvdate"><fmt:formatDate value="${fv.fvdate}" pattern="dd-MM-yyyy"/></td>

											</tr>
										</c:forEach>
										<c:forEach var="est" items="${estimationServiceDetails}">
											<tr>
												<td>Estimation</td>
												<td>${est.estmnno}</td>
												<td class="estdate"><fmt:formatDate value="${est.estmndate}" pattern="dd-MM-yyyy"/></td>

											</tr>
											
										</c:forEach>
									</tbody>
															
								</table>  
				

           </div>
           
           
           <div id="addtabforClarifications" title="Clarifications" style="display:none;overflow-y:scroll;overflow-x:hidden;">
    	    <table class="table table-bordered table-striped">
			 <thead>
				 <tr>
					
					<th >Seq&nbsp;No</th>
					<th width="200">From</th>
					<th width="200">To </th>
					<th width="100">Verified&nbsp;Date</th>
					<th width="300">Remarks</th>												
				</tr>
			</thead>
			
				<tbody id="viewClarifications">
	
				</tbody>
				
			
			</table>  
           </div>
           
            
          
		<script type="text/javascript">
		
		var isdId=0;
		var totalConnections=0;
		
		function addISDAmount()
		 {
			 isdId++;
			 if(totalConnections<(isdId+1)){
				 $('#addISDAmtid').hide();
			   }
			 
			 $('#allIds').append('<tr id=fileData'+isdId+'><td><label class=label>'+isdId+'.Tariff</label><select class="form-control" id=listOfTariffs'+isdId+' name=listOfTariffs'+isdId+'><option value="" selected="" disabled="">Select Tariff</option><c:forEach items="${tariffdropdw}" var="tar"><option value="${tar}">${tar}</option></c:forEach></select></td><td><br></td><td><label><section class="col col-4"><label class="label"><br>Load (KW)&nbsp;</label><label class="input"><i class="icon-prepend fa fa-ban"></i><input type="text" name="loadkw'+isdId+'" id="loadkw'+isdId+'" placeholder="Load(KW)" value="0"></label></section><section class="col col-4"><label class="label"><br>Load (HP)&nbsp;</label><label class="input"><i class="icon-prepend fa fa-ban"></i><input type="text" name="loadhp'+isdId+'" id="loadhp'+isdId+'" placeholder="Load(HP)" value="0"></label></section><section class="col col-4"><label class="label"><br>2MMD/ISD Amount&nbsp;<font color="red">*</font></label><label class="input"><i class="icon-append fa fa-inr"></i><input type="number" name="isdamount'+isdId+'" id="isdamount'+isdId+'" min="0" value="0"></label></section></label></td></tr>');
			 
			 /* <button class=close  onclick="return cancelReset(this.name);" name=fileData'+isdId+' style="margin-right: -41px;margin-top: 50px;color:red;"><i class="fa fa-times-circle"></i></button> */
			 $("#countSize").val(isdId); 
		 }
		
		function cancelReset(param)
		  {	
			    isdId--;
			    if(totalConnections>(isdId)){
					 $('#addISDAmtid').show();
				 }
			    
		 	    $('#'+param).remove();
		 	    $("#countSize").val(isdId);
		 	    return false;
		  }

		
		
		
		
	function checkValidations(){
		
		var row="";
		var appType="${applicationType}";
		
		if((appType=='MS Building' || appType=='Multiple Connection') && isdId==0){
			
			 alert("Please Add 2MMD/ISD amount for each Connection Separately");
			 return false;
		}
		
		for(var i = 1;i<=isdId;i++)
		{
			   if(i==1){
				   row="St";
			   }else if(i==2){
				   row="nd";
			   }else if(i==3){
				   row="rd";
			   }else{
				   row="th";
			   }
			
			
			   var listOfTariffs = $("#listOfTariffs"+i).val();
			   var loadkw = $("#loadkw"+i).val();
			   var loadhp = $("#loadhp"+i).val();
			   var isdamount = $("#isdamount"+i).val();
			  
			   var loadRegex = new RegExp("^(?=.)([+-]?([0-9]*)(\.([0-9]+))?)$");
			   
			   if(listOfTariffs=="" || listOfTariffs==null){
				   alert("Please Select Tariff in "+i+" Row");
				   return false;
			   }else if(isdamount=="" || isdamount==null || isdamount==0){
				   alert("Enter ISD Amount in "+i+""+row+" Row");
				   return false;
			   }else if (!(loadRegex.test(isdamount))){
				   alert("Enter Valid ISD Amount in "+i+""+row+" Row");
				   return false;
			   }else if(loadkw=="" || loadkw==null){
				   alert("Enter Load(KW) in "+i+""+row+" Row (If Applicable else Enter 0)");
				   return false;
			   }else if(!(loadRegex.test(loadkw))){
				   alert("Enter Load(KW) in numbers for "+i+""+row+" Row");
				   return false;
			   }else if(loadhp=="" || loadhp==null){
				   alert("Enter Load(HP) in "+i+""+row+" Row (If Applicable else Enter 0)");
				   return false;
			   }else if(!(loadRegex.test(loadhp))){
				   alert("Enter Load(HP) in numbers for "+i+""+row+" Row");
				   return false;
			   }else if(loadhp==0 && loadkw==0){
				   alert("Enter Load(KW) or Load(HP) greater than 0(Zero) in "+i+""+row+" Row");
				   return false;
			   }
			   

			 
		   }
		
		}
		
		
		var prevdate="";
		var dialog = "";
		
		function myFunction(myNumber) {
		 $("#nooffloorsexisting").val(parseInt(myNumber));
		}
		
		function myFunction1(myNumber) {
			 $("#nooffloorsadded").val(parseInt(myNumber));
		}
		
		function myFunction2(myNumber) {
			 $("#mmd").val(parseInt(myNumber));
		}
		
		function myFunction3(myNumber) {
			 $("#msd").val(parseInt(myNumber))
		}
		
		function myFunction4(myNumber) {
			 $("#isd").val(parseInt(myNumber));
		}
		
		
		function myFunction5(myNumber) {
			 $("#slc").val(parseInt(myNumber));
		}
		
		function myFunction6(myNumber) {
			 $("#additionaldevslc").val(parseInt(myNumber));
		}
		
		function myFunction7(myNumber) {
			 $("#sc").val(parseInt(myNumber));
		}
		
		
		function myFunction8(myNumber) {
			 $("#buildingheight").val(parseFloat(myNumber));
		}
		
		function myFunction9(myNumber) {
			 $("#prosanctionedload").val(parseFloat(myNumber));
		}
		
		function myFunction10(myNumber) {
			 $("#existingsanctionedload").val(parseFloat(myNumber));
		}
		
		
		function myFunction13(myNumber) {
			 $("#existingkva").val(parseFloat(myNumber));
		}
		
		function myFunction14(myNumber) {
			 $("#tccapacitykva").val(parseFloat(myNumber));
		}
		
		function myFunction15(myNumber) {
			 $("#newkvaforservice").val(parseFloat(myNumber));
		}
		
		function myFunction16(myNumber) {
			 $("#arrangedpowersupply").val(parseFloat(myNumber));
		}
		function myFunction20(myNumber) {
			 $("#serviceTax").val(parseFloat(myNumber));
		}
		
		$('form').each(function() {
		    this.reset();
		});
		
		function convertToUpperCase(){
			
			$("#accountid").val($("#accountid").val().trim());
			
		}
		

		function getTotalPlinthArea(existedPlinthValue){
			$("#existingplintharea").val(parseFloat(existedPlinthValue));
			var newplintharea=$("#newplintharea").val();
			var abc=parseFloat(existedPlinthValue) + parseFloat(newplintharea);
			$('#totalplintharea').val(abc);
		}
		
		function getTotalPlinthAreaNew(newPlinthValue){
			$("#newplintharea").val(parseFloat(newPlinthValue));
			var existingplintharea=$("#existingplintharea").val();
			var abc=parseFloat(existingplintharea) + parseFloat(newPlinthValue);
			$('#totalplintharea').val(abc);
		}
		
		function cancelForm(){
			$('#showVerificationIds').hide();
			$('#wididpowersanction').show();
			$('#powersanctionEntry').show();
			$('#requiredDepositId').show();
			
		}
		function showForwardDiv(){
			
			$('#showVerificationIds').show();
			$('#wididpowersanction').hide();
			$('#powersanctionEntry').hide();
			$('#requiredDepositId').hide();
		} 
		
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
		}
		
		function  OnHold(appId,phase){
			
			var remarks=$("#remarks").val();
			var remlength=remarks.length;
			
			if(remlength!=0){
			$.ajax({
				type : "GET",
				url : "./updateOnHoldStatus",
				dataType : "text",
				data : {

					remarks : remarks,
					appId:appId,
					phase:phase
				},
				success : function(response) {
					alert(response);
					$('#remarks').val("");
					
				}
			});
			}else{
				alert("Please Enter Remarks");
			}
		}
		
		
		
		function checkduplicate(){
			var refNo=$("#psrefno").val();
			var phasecount=5;			
			$.ajax({
				type : "GET",
				url : "./checkrefDuplicate",
				dataType : "text",
				data : {

					refNo : refNo,
					phasecount:phasecount,
				},
				success : function(response) {
					
					if (response == "NF") {

					} else {
						alert("Reference No all ready registered");
						$("#psrefno").val(" ");
					}
				}
			});
		}
		
		
		function checkduplicateAccountId(){
			var accountid=$("#accountid").val();
						
			$.ajax({
				type : "GET",
				url : "./checkrefDuplicateAccountId",
				dataType : "text",
				data : {

					accountid : accountid,
					
				},
				success : function(response) {
					
					if (response == "NF") {

					} else {
						alert("Account Id already exists");
						$("#accountid").val(" ");
					}
				}
			});
		}
		
	
		function applicationHistoryPopUp(docketNo)
		{
			dialog = $("#addtab").dialog({
				autoOpen : false,
				width : 500,
				resizable : false,
				modal : true,
				
			}).dialog("open");
		}
		
		function applicationClarificationsPopUp(applicationid){
			$("#viewClarifications").empty();
			var tableData = "";
			$.ajax
			({			
				type : "POST",
				url : "./ncms/applicationClarificationsPopUp/"+applicationid,
				async: false,
				dataType : "JSON",
				success : function(response) 
				{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
				              	var obj = response[s];
				              	
				              	tableData += "<tr>"
										+"<td>"+obj.seqid+"</td>"
										+"<td>"+obj.fromperson+"-"+obj.fromdesignation+"-"+obj.fromsitecode+"</td>"
										+"<td>"+obj.toperson+"-"+obj.todesignation+"-"+obj.tositecode+"</td>"
										+"<td>"+obj.submitteddate+"</td>"
										+"<td>"+obj.remarks+"</td>"
										+"</tr>";
						                
						     }
						$('#viewClarifications').append(tableData);
				}
			
		
			});
			
			dialog = $("#addtabforClarifications").dialog({
				autoOpen : false,
				width : 900,
				height : 600,
				resizable : false,
				modal : true,
				
			}).dialog("open");	
		}
		
		function checkViewDocument(docId) {
			window.open("./employeeViewDoc/download/"+docId);
		}
		
		function approvePowerSanction(applicationId){
			  var applicationStatus="${applicationStatus}";
			  
			if(confirm("Are you sure to Approve for Power Sanction?")){
			  window.location.href="./ncms/approvePowerSanction/"+applicationId+"/"+applicationStatus;
			}
			else{
		        return false;
		    }
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
		
		$('#redirectData5').click(function () {
			var appStatus="Clarifications";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });
		
		jQuery.fn.ForceNumericOnly = function() {
			return this
					.each(function() {
						$(this)
								.keydown(
										function(e) {
											var key = e.charCode || e.keyCode || 0;
											return (key == 8 || key == 9
													|| key == 13 || key == 46
													|| key == 110 || key == 190
													|| (key >= 35 && key <= 40)
					              || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
										});
					});
		};
		
		$("#slc").ForceNumericOnly();
		$("#mmd").ForceNumericOnly();
		$("#msd").ForceNumericOnly();
		$("#sc").ForceNumericOnly();
		
		
		$("#file").change(function(){
		    readURL11(this);
		});



		function readURL11(input) {
		    if (input.files && input.files[0]) {
		        
		    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
		    	if(Math.floor(fsize)>parseFloat(1024.0)){
		    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
		            $('#fileid').val("");
		            
		            return false;
		    	}
		    	
		    	
		    	var reader = new FileReader();
		        var regex = /^([a-zA-Z0-9\s_\\.\-:&])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.doc|.xlsx)$/;
		        if (regex.test(input.files[0].name.toLowerCase())) {
		        	
		        	reader.onload = function (e) {
		        		
		        	  reader.readAsDataURL(input.files[0]);
		        	  
		        	 
		            }
		        }else {
		        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
		        	$('#fileid').val("");
		           return false;
		        }
		        
		       
		        } 
		    
		}
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			pageSetUp();
			
			 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
			 
			 var checkMCCon = ApplicationType.indexOf("Multiple Connection") > -1;
			 var checkMSBuild = ApplicationType.indexOf("MS Building") > -1;
			 if(checkMCCon){
				 totalConnections="${totalConnections}";
				 for (i = 0; i <totalConnections; i++) {
					 addISDAmount();
				 }
				 $('#addISDAmtid').hide();
			 }else if(checkMSBuild){
				 totalConnections="${totalConnections}";
				 for (i = 0; i <totalConnections; i++) {
					 addISDAmount();
				 }
				 $('#addISDAmtid').hide();
			 }else{
				 
			 }
			 
			
			
			
			 var existingplintharea=$("#existingplintharea").val();
			 var newplintharea=$("#newplintharea").val();
			 var abc=parseFloat(existingplintharea) + parseFloat(newplintharea);
			 
			 $('#totalplintharea').val(abc);
			 $('#showVerificationIds').hide();
			 var tariffType="${subariff}";
			 var result = tariffType.indexOf("HT") > -1;
			
			 var arrangedPowerSupply="${powersupplyarrangedAtKVA}";
			 $('#arrangedpowersupply').val(arrangedPowerSupply);
			 $('#newkvaforservice').val(arrangedPowerSupply);
			
		  if(result){
			
			
			$('#msdhideid').hide();
			$('#isdforLT').show();
			$('#slcforLT').show();
			$('#additionaldevslcforLT').show();
			//$('#existingkvaforLT').hide();
			$('#tccapacitykvaforLT').hide();
			//$('#newkvaforserviceforLT').hide();
			$('#threephasemtrnoforLT').hide();
			$('#etvmtrnoforLT').hide();
			$('#topboxnoforLT').hide();
			$('#ctrnoforLT').hide();
			$('#existsFloorsforLT').hide();
			$('#addedFloorsforLT').hide();
			$('#existingPlinthforLT').hide();
			$('#newPlinthforLT').hide();
			$('#buildingheightforLT').hide();
			$('#singlephasemtrnoforLT').hide();
			//$('#existingsanctionedloadforLT').hide();
			$('#prosanctionedloadforLT').hide();
			$('#serviceChargeShow').hide();
			$('#advConsuptionChargeShow').hide();
			
			$('#estcostdepositContribution').hide();
			$('#projectAreaAcresId').hide();
			$('#projectAreaGuntasId').hide();
			
			
		  }else{
			
			    var TariffDesc = document.getElementsByClassName('tariffdesc')[0].innerHTML;
			    
			    
			    var tariffMain = document.getElementsByClassName('tariffMain')[0].innerHTML;
				var meterPhaseSelect = document.getElementsByClassName('meterPhaseSelect')[0].innerHTML;
				if(tariffMain=='LT'){
					if(meterPhaseSelect==1){
						$('#singlephasemtrnoforLT').show();
						$('#threephasemtrnoforLT').hide();
					}
					if(meterPhaseSelect==3){
						$('#threephasemtrnoforLT').show();
						$('#singlephasemtrnoforLT').hide();
					}
				}  
				
		    var checkLT7 = TariffDesc.indexOf("LT7") > -1;
		    var checkAppType = ApplicationType.indexOf("Single Connection") > -1;
			  
			
			//$('#existingkvaforLT').show();
			$('#tccapacitykvaforLT').show();
			//$('#newkvaforserviceforLT').show();
			$('#etvmtrnoforLT').show();
			$('#topboxnoforLT').show();
			$('#ctrnoforLT').show();
			$('#existsFloorsforLT').show();
			$('#addedFloorsforLT').show();
			$('#existingPlinthforLT').show();
			$('#newPlinthforLT').show();
			$('#buildingheightforLT').show();
			//$('#existingsanctionedloadforLT').show();
			$('#prosanctionedloadforLT').show();
			$('#msdhideid').show();
			$('#isdforLT').show();
			$('#slcforLT').show();
			$('#additionaldevslcforLT').show();
			$('#serviceChargeShow').hide();
			$('#advConsuptionChargeShow').hide();
			$('#mmdorisdId').show();
			$('#supervisionChargesId').show();
			
			$('#estcostdepositContribution').hide();
			$('#projectAreaAcresId').hide();
			$('#projectAreaGuntasId').hide();
			
			
			if(checkLT7 && checkAppType){
				$('#serviceChargeShow').show();
				$('#advConsuptionChargeShow').show();
				
				$('#existsFloorsforLT').hide();
				$('#addedFloorsforLT').hide();
				$('#existingPlinthforLT').hide();
				$('#newPlinthforLT').hide();
				$('#buildingheightforLT').hide();
				$('#isdforLT').hide();
				$('#mmdorisdId').hide();
				$('#supervisionChargesId').hide();
			}
			
			var checkMCType = ApplicationType.indexOf("Multiple Connection") > -1;
			var checkMSType = ApplicationType.indexOf("MS Building") > -1;
			var layoutType = ApplicationType.indexOf("Layout") > -1;
			
			if(checkMSType){
				$('#supervisionChargesId').hide();
				$('#msdhideid').hide();
			}
			if(checkMCType){
				$('#supervisionChargesId').show();
				$('#msdhideid').hide();
			}
			
			if(layoutType){
				
				$('#existsFloorsforLT').hide();
				$('#addedFloorsforLT').hide();
				$('#existingPlinthforLT').hide();
				$('#newPlinthforLT').hide();
				$('#buildingheightforLT').hide();
				$('#msdhideid').hide();
				$('#isdforLT').hide();
				$('#slcforLT').hide();
				$('#additionaldevslcforLT').hide();
				$('#existingkvaforLT').hide();
				$('#tccapacitykvaforLT').hide();
				$('#mmdorisdId').hide();
				
				$('#supervisionChargesId').hide();
				$('#arrangedpowersupplyId').hide();
				$('#threephasemtrnoforLT').hide();
				$('#etvmtrnoforLT').hide();
				$('#topboxnoforLT').hide();
				$('#ctrnoforLT').hide();
				//$('#newkvaforserviceforLT').hide();
				
				$('#estcostdepositContribution').show();
				$('#projectAreaAcresId').show();
				$('#projectAreaGuntasId').show();
				
			}
			
		  }
			
			$.validator.addMethod("specialCharValidation", function(value, element) {
				 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
			}, "Only letters, numbers allowed");

			jQuery.validator.addMethod("lettersonly", function(value, element) {
				  return this.optional(element) || /^[a-z ]+$/i.test(value);
				}, "Letters only please");
			jQuery.validator.addMethod("noOnly", function(value, element) {
				  return this.optional(element) || /^[0-9 ]+$/i.test(value);
				}, "Only No allowed");
			
			$.validator.addMethod('minStrict', function(value, el, param) {
				return value > param;
			});
			
			$.validator.addMethod("regex", function(value, element, regexpr) {
				return regexpr.test(value);
			}, "");
			
			$.validator.addMethod("dateFormat",
				    function(value, element) {
				        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
				    },
				    "Please enter a date in the format dd/mm/yyyy.");
			
		 	 $('#powerSanc-form').validate({
				// Rules for form validation
					rules : {
						psrefno : {
							required : true,
							specialCharValidation:true,
							maxlength : 50,
							regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
						},
						
						psdate : {
							required : true,
							dateFormat: true
							
						},
						
						remarks : {
							required : true,
							
						},
						
						
						sc : {
							required : true,
							noOnly:true,
						},
						transformerTestFee:{
							required : true,
							noOnly:true,
						},
						additionaldevslc:{
							required : true,
							noOnly:true,
						},
						msd : {
							required : true,
							noOnly:true,
						},
						
						mmd : {
							required : true,
							noOnly:true,
							minStrict : 0
						},
											
						slc : {
							required : true,
							noOnly:true,
						},
						isd:{
							required : true,
							noOnly:true,
							
						},
						dcwaCharges:{
							required : true,
							noOnly:true,
						},
						fileid:{
							required : true,	
						},
						accountid:{
							required : true,
							digits:true,
							maxlength : 12,
							minlength : 6,
							regex : /^[0]*[1-9][0-9]*$/
						},
						
						singlephasemtrno:{
							required : true,
							digits:true,
							maxlength : 20,
						},
						
						threephasemtrno:{
							required : true,
							digits:true,
							maxlength : 20,
						},
						
						etvmtrno:{
							required : true,
							digits:true,
							maxlength : 20,
						},
						
						topboxno:{
							
							digits:true,
							maxlength : 20,
						},
						
						ctrno:{
							
							digits:true,
							maxlength : 20,
						},
						
						existingkva:{
							required : true,
							regex : /^[0-9 .]*$/,
							max : 9999,
							
						},
						
						tccapacitykva:{
							required : true,
							regex : /^[0-9 .]*$/,
							max : 9999,
							
						},
						
						newkvaforservice:{
							required : true,
							regex : /^[0-9 .]*$/,
							
						},
						arrangedpowersupply:{
							required : true,
							regex : /^[0-9 .]*$/,
							
						},
						
						nooffloorsexisting:{
							required : true,
							digits:true,
							max : 9999,
						},
						nooffloorsadded:{
							required : true,
							digits:true,
							max : 9999,
						},
						existingplintharea:{
							required : true,
							regex : /^[0-9 .]*$/,
							maxlength : 8,
						},
						newplintharea:{
							required : true,
							regex : /^[0-9 .]*$/,
							maxlength : 8,
						},
						buildingheight:{
							required : true,
							regex : /^[0-9 .]*$/,
							maxlength: 8,
							//min:3,
						},
						prosanctionedload:{
							required : true,
							regex : /^[0-9 .]*$/,
							max:9999,
							
						},
						serviceTax:{
							required : true,
							regex : /^[0-9 .]*$/,
						},
						swatchbharatcess:{
							required : true,
							regex : /^[0-9 .]*$/,	
						},
						existingsanctionedload:{
							required : true,
							regex : /^[0-9 .]*$/,
							max: 9999,
						},
						pcTestRequired : {
							required : true,
							
						},
						depositConWorksCost:{
							required : true,
							regex : /^[0-9 .]*$/,
							
							
						},
						projectAreaAcres:{
							required : true,
							regex : /^[0-9 .]*$/,
							
							
						},
						projectAreaGuntas:{
							required : true,
							regex : /^[0-9 .]*$/,
							
							
						},
					},
			
					// Messages for form validation
					messages : {
						depositConWorksCost : {
							
							regex:'Enter only digits',
							
						},
						projectAreaAcres : {
							
							regex:'Enter only digits',
							
						},
						projectAreaGuntas : {
							
							regex:'Enter only digits',
							
						},
						existingkva : {
							required : 'please enter this field',
							regex:'Enter only digits',
							
						},
						tccapacitykva : {
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						newkvaforservice : {
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						
						psrefno : {
							required : 'Power Sanction Ref. Number',
							regex:'Please Enter Valid Reference No.',
							
						},
						
						psdate : {
							required : 'Select  Power Sanction Date',
							
						},
						remarks : {
							required : 'Enter Remarks/Comment',
						},
						
						
						sc : {
							required : 'Enter SC Amount(If Applicable Else Enter 0)',
						},
						transformerTestFee:{
							required : 'Enter Amount(If Applicable Else Enter 0)',
						},
						additionaldevslc:{
							required : 'Enter Amount (If Applicable Else Enter 0)',
						},
						
						msd : {
							required : 'Enter MSD Amount (If Applicable Else Enter 0)',
						},
						
						mmd : {
							required : 'Enter MMD Amount',
							minStrict: 'MMD Amount should be > 0',
						},
											
						slc : {
							required : 'Enter SLC Amount (If Applicable Else Enter 0)',
						},
						isd : {
							required : 'Enter Amount(If Applicable Else Enter 0)',
						},
						dcwaCharges:{
							required : 'Enter Amount(If Applicable Else Enter 0)',
						},
						fileid:{
							required : 'Upload Documents',	
						},
						accountid:{
							required : 'Enter Account Id',
							maxlength:'max length is 12',
							regex:'please enter valid account id',
						},
						arrangedpowersupply:{
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						existingplintharea:{
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						newplintharea:{
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						buildingheight:{
							required : 'please enter this field',
							regex:'Enter only digits',
						},
						prosanctionedload:{
							required : 'please enter this field',
							regex:'Enter only digits'
						},
						serviceTax:{
							required : 'Enter Service Tax (If Applicable Else Enter 0)',
							regex:'Enter only digits'
						},
						swatchbharatcess:{
							required : 'Enter Tax (If Applicable Else Enter 0)',
							regex:'Enter only digits'
						},
						existingsanctionedload:{
							required : 'please enter this field',
							regex:'Enter only digits'
						},
						pcTestRequired:{
							required : 'select PC Test required',	
						},
						
					},
			
					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					}
				});
			  
			 
			 
			 
			 
			 $('#powerSanc-form-forward').validate({
					// Rules for form validation
						rules : {
							refno : {
								specialCharValidation:true,
								maxlength : 50,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							
							submitteddate : {
								required : true,
								dateFormat: true
								
							},
							
							remarks : {
								required : true,
								
							},
							
							officialDesignation:{
								required : true,
							},
							
						},
				
						// Messages for form validation
						messages : {
							 refno : {
								regex:'Please Enter Valid Reference No.',
							},
							submitteddate : {
								required : 'Select  Submitted Date',
								
							},
							remarks : {
								required : 'Enter Remarks/Comment',
								
							},
							officialDesignation:{
								required:'Select Official to Forward Application', 
							},		
							
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
			 
			 
			 
			 
			var estreq = document.getElementsByClassName('estreq')[0].innerHTML;			
			if(estreq == 'No'){
			//$("#s3").hide();
			//$("#estli").hide();
			
			  prevdate=document.getElementsByClassName('fvdate')[0].innerHTML;
			}else{
			prevdate=document.getElementsByClassName('estdate')[0].innerHTML;
			}
			
			var monthyear=prevdate.split("-");					 
			var monlast="";
			var year="";
			var date="";
		    for(var i=0;i<=monthyear.length;i++){
		    	 date=monthyear[0];
		    	 monlast=monthyear[1];
		         year=monthyear[2];
		    	
		    }
		  var month=(parseFloat(monlast))-1;					   
		  var minDate=new Date(year,month,date);
		 
		  $("#psdate").datepicker("option","minDate",minDate );
		  $("#submitteddate").datepicker("option","minDate",minDate );
			
		});

		$('#psdate').datepicker({
				dateFormat : 'dd/mm/yy',
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>',
				maxDate: new Date(),
				
		});
		
		$('#submitteddate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			maxDate: new Date(),
			
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