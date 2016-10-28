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


			<div id="content">
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
											<a data-toggle="tab" href="#s2"><i class=""></i> <span class="hidden-mobile hidden-tablet">Field Verified</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s7"><i class=""></i> <span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										<li id="estli">
											<a data-toggle="tab" href="#s3"><i class=""></i> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li> 
										
										<li class="active">
										
											<a data-toggle="tab" href="#s4"><i class=""></i> <span class="hidden-mobile hidden-tablet">Power Sanction</span></a>
										</li> 
										<li >
										
											<a data-toggle="tab" href="#s5"><i class=""></i> <span class="hidden-mobile hidden-tablet">Doc</span></a>
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
																	&nbsp;&nbsp;&nbsp;&nbsp;(Sub Division)&nbsp;&nbsp;&nbsp;:&nbsp;<b><font color="red">${subdivisionText}</font>
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
												    				<td>${element.applicationtype}</td>
																	
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td>${element.supplyfor}</td>
																	
																	<th>No. Of Connections for this Tariff</th>
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
																	<td  colspan="2">${element.meterphase} </td>
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
																 
																 <c:if test = "${empty applicationVerificationDetails &&  (finalStatus==13 || finalStatus==14 || finalStatus==15 || finalStatus==16 || finalStatus==17 || finalStatus==18 || finalStatus==19 || finalStatus==24 || finalStatus==26 || finalStatus==27 || finalStatus==28 || finalStatus==29)}">
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
																			<input type="text" name="submitteddate" id="submitteddate" placeholder="Submitted Date">
																		</label>
																	</section>
																	
																</div>
																
																
																<section>
																	<label class="textarea">Remarks &nbsp;<font color="red">*</font>										
																		<textarea rows="3" name="remarks" id="remarks" placeholder="Remarks/Comments"></textarea> 
																	</label>
																</section>
																
																<section>
																<label class="label">Document&nbsp;</label>
																	<div class="input input-file">
																	
																		<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Power Sanction Document" readonly="">
																	</div>
																</section>
																
																<section>
																	<label class="label">Forward To &nbsp;<font color="red">*</font></label>
																	<label class="select">
																	<select  id="officialDesignation"  name="officialDesignation">																		
															        <option value="0" selected="" disabled="">Select Official</option>																	      
										                         
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
																		Forward 
																</button>
																    <a class="btn btn-danger" onclick="return  cancelForm();" href="javascript:void(0);">Cancel</a>
																    
																	
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
					
					<th width="70">Seq No</th>
					<th width="220">From</th>
					<th width="200">To</th>
					<th width="100">Verified Date</th>
					<th width="220">Remarks</th>												
				</tr>
			</thead>
			<tbody id="viewClarifications">
	
			</tbody>
			<%-- <tbody>
				 <c:forEach var="app" items="${forwardApplicationDetails}">
					<tr>
						<td>${app.seqid}</td>
						<td>${app.fromperson} (${app.fromdesignation}) - ${app.fromsitecode}</td>
						<td>${app.toperson} ${app.todesignation} - ${app.tositecode}</td>
						<td>${app.refno}</td>
						<td class="appdate"><fmt:formatDate value="${app.submitteddate}" pattern="dd-MM-yyyy"/></td>
						<td>${app.remarks}</td>
						</tr>
					
				</c:forEach> 
				
				
				
			</tbody> --%>
			</table>  
           </div>
           
           
          
		<script type="text/javascript">
		var prevdate="";
		var dialog = "";
		function cancelForm(){
			$('#showVerificationId').hide();
			$('#wid-id-81').show();
		}
		function showForwardDiv(){
			$('#showVerificationId').show();
			$('#wid-id-81').hide();
		}
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
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
		        var reader = new FileReader();
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.doc|.xlsx)$/;
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
			
			var premarks="${previousRemarks}";
			/* document.getElementById("previousremarks").value =premarks; */
			
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
								required:'Please select Official to forward Application', 
							},		
							
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
			 
			  $("#submitteddate").datepicker("option","minDate",minDate );
			
		});

		$('#submitteddate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			maxDate: new Date(),
			
	});
		    
</script>
