<%@include file="/common/taglibs.jsp"%>
 <%@include file="/common/commonPage.jsp"%>

<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<div id="content">
			<jsp:include page="../newConnection/viewLayout.jsp"/>
			<jsp:include page="../newConnection/viewMultipleConnections.jsp"/>
			<div style="display: none;">
              	<table>
           		<tr><td class="appphase">${appPhase}<td></tr>
            	</table>
            </div>
               
				<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
							<div class="col-sm-4" >
							<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:350px;">In Progress </a>							
							</div>		
							
							<div class="col-sm-4">
							<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:350px;">Completed </a>						           
							</div>		
							
							<div class="col-sm-4">
						            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:350px;">On Hold </a>
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
											<a data-toggle="tab" href="#s1"><i class=""></i> <span class="hidden-mobile hidden-tablet">Application Details</span></a>
										</li>
										<li>
											<a data-toggle="tab" href="#s2"><i class=""></i> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>

										<li class="active">
											<a data-toggle="tab" href="#s3"><i class=""></i> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										<li >
											<a data-toggle="tab" href="#s4"><i class=""></i> <span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>
										
									</ul>

								</header>

								<!-- widget div-->
								<div class="no-padding">
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">

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
																	&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${locationText}</font></b>&nbsp;&nbsp;&nbsp;&nbsp;(Division)&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${divisionText}</font></b>
																	&nbsp;&nbsp;&nbsp;&nbsp;Sub Division&nbsp;:&nbsp;<b><font color="red">${subdivisionText}</font>&nbsp;&nbsp;
																	
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
												    				<td class="ApplicationType">${element.applicationtype}</td>
																	
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td>${element.supplyfor}</td>
																	
																	<th>No. Of Connections for this Tariff</th>
																	<td>${element.noofconnections}</td>
																	
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
																	<td  colspan="2">No</td>
																	</c:when>
																	<c:otherwise>
																	<td  colspan="2">Yes</td>
																	</c:otherwise>
																	</c:choose>
																	
																</tr>
																<%-- <tr>
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
																 --%>
																
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
											
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s3">
											
											
											 
											 
									<c:if test = "${not empty estimationServiceDetails}">	
											 
											    <article class="col-sm-12 col-md-12 col-lg-5">

														<div class="jarviswidget" id="wid-id-estimationfound"
															data-widget-editbutton="false"
															data-widget-custombutton="false">
															<header>
																<span class="widget-icon"> <i class="fa fa-asterisk"></i>
																</span>
																<h2>Last Estimation Details</h2>
															</header>
															<div>
																<div class="widget-body no-padding">
																   
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
																						<td class="estiCost">${estimation.estmcost}</td>
																					</tr>
																					<tr>
																						<th>Estimation Done By </th>
																						<td>${estimation.username}</td>
																					</tr>
																					<tr>
																						<th>Remarks - Comments</th>
																						<td><b><font color="red">${element.remarks}</font></b></td>
																					</tr>
																					
																					</c:forEach>
																				</tbody>
																			</table>
																</div>
															</div>
														</div>
								</article>
						 </c:if>
											 
											 
											 <article class="col-sm-12 col-md-12 col-lg-7">
			
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
														<h2>Estimation Details </h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form:form action="./estimationInsert" method="post" id="estimation-form" class="smart-form" commandName="Estimation" modelAttribute="estimation" enctype="multipart/form-data">
																<fieldset>
																	
																	
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Application ID</label>
																			<label class="input">
								                                     <form:input type="text"	name="applicationid" placeholder="Application ID" path="applicationid" id="applicationid" value="${applicationId}" readonly="true"></form:input>
																			</label>
																		</section> 
																	
																	
																	  <section class="col col-6">
																			<label class="label">Estimation Cost&nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<i class="icon-append fa fa-inr"></i>
																				<form:input type="text"	name="estmcost" placeholder="Estimation Cost" path="estmcost" id="estmcost" maxlength="12" autocomplete="off"></form:input>
																			</label>
																	  </section>
																	
																	</div>
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Estimation Ref Number&nbsp;<font color="red">*</font></label>
																			<label class="input"> 
																				<form:input type="text"	name="estmnno" placeholder="Estimation Ref Number" path="estmnno" id="estmnno" maxlength="50" onchange="checkduplicate()" autocomplete="off"></form:input>
																				
																			</label>
																		</section>
																		
																		
																		<section class="col col-6">
																			<label class="label">Estimation Date&nbsp;<font color="red">*</font></label>
																			<label class="input"> <i class="icon-append fa fa-calendar"></i>
																				<form:input type="text"	name="estmndate" placeholder="Estimation Date" path="estmndate" id="estmndate" autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																	</div>
																	
																	<div class="row">
																		<section class="col col-4" id="existsFloorsforLT">
																			<label class="label">No. of Floors exists</label>
																			<label class="input">
																				<input type="number"name="nooffloorsexisting" autocomplete="off" placeholder="Floors Existed"  id="nooffloorsexisting" min="0" value="0"/>
																			</label>
																		</section> 
																		
																		<section class="col col-4" id="addedFloorsforLT">
																			<label class="label">No. of Floors Added/New</label>
																			<label class="input">
																				<input type="number" name="nooffloorsadded"  autocomplete="off" id="nooffloorsadded" placeholder="Floors Added/New" min="0" value="0"/>
																			</label>
																		</section>
																		
																		<section class="col col-4" id="buildingheightforLT">
																			<label class="label">Building height(mtr)&nbsp;</label>
																			<label class="input">
																				<input type="text" name="buildingheight" autocomplete="off" id="buildingheight" placeholder="Building height (mtr)" value="0" />
																			</label>
																		</section>
																		
																	</div>
																	
																	<div class="row">
																		<section class="col col-4" id="existingPlinthforLT">
																			<label class="label">Existing Plinth Area(Sq.m)&nbsp;</label>
																			<label class="input">
																				<input type="text" name="existingplintharea" autocomplete="off" id="existingplintharea" placeholder="Area(Sq.m)" value="${plintharea}"/>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4" id="newPlinthforLT">
																			<label class="label">New Plinth Area(Sq.m)&nbsp;</label>
																			<label class="input">
																				<input type="text" name="newplintharea" autocomplete="off" id="newplintharea" placeholder="Area(Sq.m)" value="0"/>
																			</label>
																		</section>
																	</div>
																	
																	
																  
																
																	<section >
																			<label class="label">Estimation Description&nbsp;<font color="red">*</font></label>
																			<label class="textarea">
																				
																				<form:textarea type="text"	name="estmndesc" placeholder="Estimation Description" path="estmndesc" id="estmndesc" maxlength="255" autocomplete="off"></form:textarea>
																			</label>
																		</section>
																	
																	<section >
																			<label class="label">Remarks/Comments&nbsp;<font color="red">*</font></label>
																			<label class="textarea">
																				
																				<form:textarea type="text"	name="remarks" placeholder="Remarks/Comments" path="remarks" id="remarks" maxlength="255" autocomplete="off"></form:textarea>
																			</label>
																		</section>
																	
																	<section>
																	<label class="label">Documents&nbsp;<font color="red">*</font></label>
																		<div class="input input-file">
																			<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Estimation Document" readonly="" autocomplete="off">
																		</div>
																	</section>
																	
																	
																	
																</fieldset>
									
																<footer>
																<div id="forwardDiv"">
													
																<c:if test="${not empty estimationCostRange}">
																	<label class="col-md-12 control-label">
																		<font style="font-size: 14px; color: red;"><b>Note :</b></font>
																		<font style="font-size: 12px;color: blue;">
																		    ${estimationCostRange}
																		</font>
																	 </label>
																</c:if>
																</div>
																
																	<button type="submit" class="btn btn-primary" onclick="return submitEstimation();">
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
												</article>

											</div>
											
											
													<div class="tab-pane fade" id="s4">
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
											<script>
											var fvdate=document.getElementsByClassName('fvdate')[0].innerHTML;
											
											</script>
										</c:forEach>
										
									</tbody>
									</table>  
				

           </div>
           
           
           <div id="addtabforClarifications" title="Clarification Details" style="display:none">
    	    <table class="table table-bordered table-striped">
			 <thead>
				 <tr>
					
					<th >Sl No</th>
					<th width="200">From</th>
					<th>To</th>
					<th>Reference No</th>
					<th>Submitted Date</th>
					<th>Remarks</th>												
				</tr>
			</thead>
			<tbody id="viewClarifications">
	
			</tbody>
			</table>  


           </div>
          
		<script type="text/javascript">
		
		
		function submitEstimation(){
			if(confirm("Are you sure to submit details?")){
				
			}
			else{
		        return false;
		    }
		}
	
		
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
		}
		
		function  OnHold(appId,phase){
			//alert(appId);
			//alert(phase);
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
			var refNo=$("#estmnno").val();
			var phasecount=4;			
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
						alert("Reference No already exists");
						$("#estmnno").val(" ");
					}
				}
			});
		}
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
										+"<td>"+obj.refno+"</td>"
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
				resizable : false,
				modal : true,
				
			}).dialog("open");	
		}
		
		function checkViewDocument(docId) {
			window.open("./employeeViewDoc/download/"+docId);
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
		
		$("#estmcost").ForceNumericOnly();
		
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

		 var tariffType="${natureOfSubTariff}";
		 var result = tariffType.indexOf("HT") > -1;
		 var checkLT7 = tariffType.indexOf("checkLT7") > -1;
		 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
			
		  if(result){
			$('#existsFloorsforLT').hide();
			$('#addedFloorsforLT').hide();
			$('#existingPlinthforLT').hide();
			$('#newPlinthforLT').hide();
			$('#buildingheightforLT').hide();
		  }else{
			
			var layoutType = ApplicationType.indexOf("Layout") > -1;
			
			$('#existsFloorsforLT').show();
			$('#addedFloorsforLT').show();
			$('#existingPlinthforLT').show();
			$('#newPlinthforLT').show();
			$('#buildingheightforLT').show();
			
			if(checkLT7){
				$('#existsFloorsforLT').hide();
				$('#addedFloorsforLT').hide();
				$('#existingPlinthforLT').hide();
				$('#newPlinthforLT').hide();
				$('#buildingheightforLT').hide();
			}
			
			if(layoutType){
				
				$('#existsFloorsforLT').hide();
				$('#addedFloorsforLT').hide();
				$('#existingPlinthforLT').hide();
				$('#newPlinthforLT').hide();
				$('#buildingheightforLT').hide();
				
			}
			
		  }
			
			var monthyear=fvdate.split("-");					 
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
			$("#estmndate").datepicker("option","minDate",minDate );
			
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

	   $('#estimation-form').validate({
		// Rules for form validation
			rules : {
				estmnno : {
					required : true,
					specialCharValidation:true,
					maxlength : 50,
					regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
				},
				
				estmcost : {
					required : true,
					noOnly:true,
					minStrict : 0,
					maxlength : 8,
				},
				estmndate : {
					required : true,
					dateFormat: true
				},
				remarks : {
					required : true,
					
				},
				fileid : {
					required : true,
					
				},
				estmndesc: {
					required : true,
					
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
			},
	
			// Messages for form validation
			messages : {
				
				estmnno : {
					required : 'Enter Estimation Ref. Number',
					regex:'Please Enter Valid Reference No.',
					
				},
				
				estmcost : {
					required : 'Enter Estimation Cost',
					minStrict: 'Estimation Cost should be > 0',
					maxlength:'maxlength is 8 digits',
				},
				estmndate : {
					required : 'please select date',
					
				},
				remarks : {
					required : 'Enter Remarks/Comment',
					
				},
				fileid : {
					required : 'Uploaded Document',
					
				},
				estmndesc:{
					required : 'Estimation Description Required',
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
			},
	
			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
		
		});

		$('#estmndate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			maxDate: new Date(),
			
		});
		    
		</script>

		