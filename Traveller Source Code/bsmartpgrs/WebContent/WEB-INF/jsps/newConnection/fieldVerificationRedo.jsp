<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/commonPage.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

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
						            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:350px;">On Hold</a>
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
											<a data-toggle="tab" href="#s1"><i class=""></i> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
									

										<li class="active">
											<a data-toggle="tab" href="#s2"><i class=""></i> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										<li >
											<a data-toggle="tab" href="#s3"><i class=""></i> <span class="hidden-mobile hidden-tablet">Documents</span></a>
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
																	<td class="tariffmain">${element.tariffmain}</td>
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
																
											<div class="tab-pane fade active in padding-10 no-padding-bottom" id="s2">
											
											
											 
						 <c:if test = "${not empty fieldVerificationServiceDetails}">		 
								<article class="col-sm-12 col-md-12 col-lg-5">

									<div class="jarviswidget" id="wid-id-1"
										data-widget-editbutton="false"
										data-widget-custombutton="false">
										<header>
											<span class="widget-icon"> <i class="fa fa-asterisk"></i>
											</span>
											<h2>Last Field Verification Details</h2>
										</header>
										<div>
											<div class="widget-body no-padding">
											   
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
														<h2>Field Verification Details </h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form:form action="./fieldVerifactionRedoInsert" method="post" id="fieldVerfication-form" class="smart-form" commandName="FieldVerification" modelAttribute="fieldVerfication" enctype="multipart/form-data" autocomplete="off">
																<fieldset>
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<form:input type="text"	name="applicationid" placeholder="Application ID" path="applicationid" id="applicationid" value="${applicationId}" readonly="true"></form:input>
																			</label>
																		</section> 
																		<section class="col col-6">
																			<label class="label">Pole Number &nbsp;<font color="red">*</font></label>
																			<label class="input">
																				<form:input type="text"	name="polenumber" placeholder="Pole Number" path="polenumber" id="polenumber"></form:input>
																			</label>
																		</section> 
																	</div>
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Field Verification Ref Number &nbsp;<font color="red">*</font></label>
																			<label class="input">
																			<form:input type="text"	name="fvrefno" placeholder="Field Verification Ref Number" path="fvrefno" id="fvrefno" onchange="checkduplicate()"  autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-6">
																			<label class="label">Field Verification Date&nbsp;<font color="red">*</font></label>
																			<label class="input"> <i class="icon-append fa fa-calendar"></i>
																				<form:input type="text"	name="fvdate" placeholder="Field Verification Date" path="fvdate" id="fvdate" ></form:input>
																			</label>
																		</section>
																		
																	</div>
																	
																	
																	<%-- <div class="row">
																		<section class="col col-4">
																			<label class="label">Meter Phase &nbsp;<font color="red">*</font></label>
																			<label class="select">
																				<form:input type="text"	name="meterphase" placeholder="Meter Phase" path="meterphase" id="meterphase" ></form:input>
																			
																			<form:select  id="meterphase"  path="meterphase" name="meterphase" onchange="checkMeterPhase();">																		
																	        <option value="0" selected="" disabled="">Select Phase</option>
																	         <option value="1" >Single Phase</option>
																	          <option value="3">Three Phase</option>																		
																	        </form:select> <i></i>
																			
																			</label>
																		</section>																		
																		
																		<section class="col col-4">
																			<label class="label">Meter Capacity &nbsp;<font color="red">*</font></label>
																			<label class="select">																			
																			<form:select  id="metercapacity"  path="metercapacity" name="metercapacity">																		
																	        <option value="0" selected="" disabled="">Select Meter Capacity</option>
																	        <c:forEach items="${metercapacity}" var="mcapacity">
												                  <option value="${mcapacity.metercapacity}">${mcapacity.metercapacity}</option>
												                     </c:forEach>																	
																	        </form:select> <i></i>
																			
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Meter Rating &nbsp;<font color="red">*</font></label>
																			<label class="select">
																			<form:input type="text"	name="meterrating" placeholder="Meter Rating" path="meterrating" id="meterrating" ></form:input>
																			<form:select  id="meterrating"  path="meterrating" name="meterrating">																		
																	        <option value="0" selected="" disabled="">Select Meter Rating</option>																	      
												                         <c:forEach items="${meterrating}" var="mrating">
												                  <option value="${mrating.meterrating}">${mrating.meterrating}</option>
												                     </c:forEach>	
												                         																
																	        </form:select> <i></i>
																			</label>
																		</section>
																		
																  </div> --%>
																	
																	<div class="row">
																		<section class="col col-4">
																			<label class="label">Nearest RR Number - 1</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyrrnoone" placeholder="Nearest RR Number - 1" path="nearbyrrnoone" id="nearbyrrnoone" value="${nearbyrrno1}"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Nearest RR Number - 2</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyrrnotwo" placeholder="Nearest RR Number - 2" path="nearbyrrnotwo" id="nearbyrrnotwo" value="${nearbyrrno2}"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Nearest RR Number - 3</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyrrnothree" placeholder="Nearest RR Number - 3" path="nearbyrrnothree" id="nearbyrrnothree" value="${nearbyrrno3}"></form:input>
																			</label>
																		</section>
																		
																  </div>
																	
																	
																	<div class="row">
																		<section class="col col-4">
																			<label class="label">Nearest Account ID - 1</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyacnoone" placeholder="Nearest Account ID - 1" path="nearbyacnoone" id="nearbyacnoone" value="${nearbyacno1}"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Nearest Account ID - 2</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyacnoone" placeholder="Nearest Account ID - 1" path="nearbyacnotwo" id="nearbyacnotwo" value="${nearbyacno2}"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Nearest Account ID - 3</label>
																			<label class="input">
																				<form:input type="text"	name="nearbyacnoone" placeholder="Nearest Account ID - 3" path="nearbyacnothree" id="nearbyacnothree" value="${nearbyacno3}"></form:input>
																			</label>
																		</section>
																		
																  </div>
																  
																     <div class="row">
																		<section class="col col-4">
																			<label class="label">${feedernaming}</label>
																			<label class="input">
																				<form:input type="text"	name="feedername" placeholder="Feeder Name" path="feedername" id="feedername" autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">${feederCoding}</label>
																			<label class="input">
																				<form:input type="text"	name="feedercode" placeholder="Feeder Code" path="feedercode" id="feedercode" autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																		<section class="col col-4">
																			<label class="label">${dtcCoding}</label>
																			<label class="input">
																				<form:input type="text"	name="dtccode"  path="dtccode" id="dtccode" autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																  </div>
																  
																  
																  
																  <div class="row" id="showthisforLTApplication">
																		
																		<section class="col col-4">
																			
																			<label class="label">${dtcnaming}</label>
																			<label class="input">
																				<form:input type="text"	name="dtcname" placeholder="DTC Name" path="dtcname" id="dtcname" autocomplete="off"></form:input>
																			</label>
																		</section>
																		
																		
																		
																  </div>
																	<div class="row" style="display: none;">
																        <section class="col col-6">
																			<label class="label">
																			
																				<form:input name="estmnreq"  path="estmnreq" id="estmnreq"  value="1"  readonly="true" ></form:input><i></i>
																			
																			</label>
																		</section>
																   </div>
																	<section>
																		<label class="textarea"> Remarks <font color="red">*</font>									
																			<form:textarea type="text"	name="remarks" placeholder="Remarks/Comments" path="remarks" id="remarks" ></form:textarea>
																		</label>
																	</section>
																	
																	<section>
																	<label class="label">Documents&nbsp;<font color="red">*</font></label>
																		<div class="input input-file">
																			<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Field Verification Document (Pdf,Image,Excel and Word)" readonly="">
																		</div>
																	</section>
																	
																	<section class="col col-6">
																		<label class="label">Other Document&nbsp;</label><label
																			class="input"><i class="icon-prepend fa fa-file"></i> <input type="text"
																				name="documentname" id="documentname" placeholder="Document Name"></input>
																		</label>
																	</section>
																	
																	<section class="col col-6">
																	<label class="label">Upload Document&nbsp;</label>
																		<div class="input input-file">
																			<span class="button"><input id="file1" type="file" name="file1" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text"
													                         placeholder="Upload Document" id="docText">
																		</div>
																	</section>
																	
																	
																	
																</fieldset>
									
																<footer>
																	<button type="submit" class="btn btn-primary">
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
											
											
											<div class="tab-pane fade" id="s3">
                                             <div class="col-sm-8">
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
												<td class="appdate"><fmt:formatDate value="${applist.appregdate}" pattern="dd-MM-yyyy"/></td>
												</tr>
												<script>
											var appdate=document.getElementsByClassName('appdate')[0].innerHTML;
											
											</script>
										</c:forEach>
										
									</tbody>
									</table>  
				

           </div>
           
         <div id="addtabforClarifications" title="Clarifications" style="display:none;overflow-y:scroll;overflow-x:hidden;">
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
		
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
		}
		
		function checkMeterPhase(){
			var subTariffType="${natureOfSubTariff}";
			var result3 = subTariffType.indexOf("HT") > -1;
			if(result3 && document.getElementById("meterphase").value==1){
				alert("Please select Three Phase for HT Installations"); 
				$('#meterphase').val("0");
			}
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
			var refNo=$("#fvrefno").val();
			var phasecount=3;			
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
						$("#fvrefno").val(" ");
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
				width : 1000,
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
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
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
		
		
		$("#file1").change(function(){
		    readURL111(this);
		});



		function readURL111(input) {
		    if (input.files && input.files[0]) {
		       
		    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
		    	if(Math.floor(fsize)>parseFloat(1024.0)){
		    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
		            $('#docText').val("");
		            
		            return false;
		    	}
		    	var reader = new FileReader();
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
		        if (regex.test(input.files[0].name.toLowerCase())) {
		        	
		        	reader.onload = function (e) {
		        	reader.readAsDataURL(input.files[0]);
		        	
		            }
		        }else {
		        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
		        	$('#docText').val("");
		           return false;
		        }
		        
		       
		        } 
		    
		}
		
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
		 var monthyear=appdate.split("-");					 
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
		 $("#fvdate").datepicker("option","minDate",minDate );
			
		 var tariffMain = document.getElementsByClassName('tariffmain')[0].innerHTML;
		 if(tariffMain=='LT'){
			  $('#showthisforLTApplication').show();
			  
				
			}else{
				 $('#showthisforLTApplication').hide();
			}  
			
			$.validator.addMethod("specialCharValidation", function(value, element) {
				 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
			}, "Only letters, numbers allowed");

			jQuery.validator.addMethod("lettersonly", function(value, element) {
				  return this.optional(element) || /^[a-z ]+$/i.test(value);
				}, "Letters only please");
			
			$.validator.addMethod("regex", function(value, element, regexpr) {
				return regexpr.test(value);
			}, "");
			
			
			 $('#fieldVerfication-form').validate({
					// Rules for form validation
						rules : {
							fvrefno : {
								required : true,
								specialCharValidation:true,
								maxlength : 50,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							
							fvdate : {
								required : true,
								
							},
							metercapacity : {
								required : true,
								
							},
							meterrating : {
								required : true,
								
							},
							meterphase : {
								required : true,
								
							},
							remarks : {
								required : true,
								
							},
							fileid:{
								required : true,	
							},
							polenumber:{
								required : true,
								maxlength : 11	
							},
							feedername:{
								//required : true,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
							feedercode:{
								//required : true,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
							dtcname:{
								//required : true,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
							dtccode:{
								//required : true,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
						},
				
						// Messages for form validation
						messages : {
							
							fvrefno : {
								required : 'Enter Field Verification Ref. Number',
								regex:'Please Enter Valid Reference No.',
								
							},
							polenumber:{
								required : 'Please Enter Pole Number',
								maxlength : 'maxlength is 11 for Pole Number',
							},
							
							fvdate : {
								required : 'Select Field Verification Date',
								
							},
							metercapacity : {
								required : 'Select Meter  Capacity',
								
							},
							meterrating : {
								required : 'Select Meter Rating',
								
							},
							meterphase : {
								required : 'Select Meter Phase',
								
							},
							remarks : {
								required : 'Enter Remarks/Comment',
								
							},
							fileid : {
								required : 'Upload Document',
								
							},
							feedername : {
								required : 'Enter Feeder Name',
								regex:'Please enter Valid Feeeder Name',
								
							},
							feedercode : {
								required : 'Enter Feeder Code',
								regex:'Please enter Valid Feeeder Code',
								
							},
							dtcname : {
								required : 'Enter DTC Name',
								regex:'Please enter Valid DTC Name',
								
							},
							dtccode : {
								required : 'Enter DTC Code',
								regex:'Please enter Valid DTC Code',
							},
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
		
		})

		$('#fvdate').datepicker({
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