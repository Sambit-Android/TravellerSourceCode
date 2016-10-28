<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>


	<div id="content">
				
				<c:if test="${not empty recordResult}">
					<script>
						var result = "${recordResult}";
						alert(result);
					</script>
				<c:remove var="recordResult" scope="session" />
				</c:if>
						
				       
			<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-createsubconnections" data-widget-editbutton="false" data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>Create Multiple Connections for Application Id : ${applicationId}</h2>
					</header>

		      <div>

					<div class="jarviswidget-editbox"></div>
					<div class="widget-body">

								<form:form action="./ncms/createSubConnections" method="post"
									id="submit-msbulidconnection" class="smart-form"
									commandName="msbuildconnection" modelAttribute="msbuildconnection">
				
									<form:input type="hidden" id="applicationid" autocomplete="off"
										placeholder="" path="applicationid" value="${applicationId}"></form:input>
				
									<fieldset>
				
										<div class="row">
											<section class="col col-3">
												<label class="label">Proposed&nbsp;Installation&nbsp;</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												  </span> 
												<form:select class="form-control" id="proposedinsttype" path="proposedinsttype">
														<option value="" selected disabled>Select</option>
														<form:option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</form:option>
														<form:option value="Village Panchayat">Village Panchayat</form:option>
				
													</form:select>
				
				
												</label>
											</section>
				
											<section class="col col-3">
												<label class="label">Nature of installation</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												</span> <form:select class="form-control" id="natureofinstallation"
														path="natureofinstallation"
														onchange="onselectTariff(this.value)">
														<option value="" selected disabled>Select
															Installation</option>
													</form:select>
												</label>
											</section>
				
											<section class="col col-3" id="KWANoEntry">
												<label class="label">Desired&nbsp;Load (KW)&nbsp;</label><label class="input"><i
													class="icon-prepend fa fa-ban"></i> <form:input type="text"
														name="loadkw" id="loadkw" path="loadkw" placeholder="loadkw"></form:input>
												</label>
											</section>
				
											<section class="col col-3" id="hpNoEntry">
												<label class="label">Load (HP)</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> <form:input
														type="text" name="loadhp" id="loadhp" path="loadhp"
														placeholder="loadhp"></form:input> </label>
											</section>
				
											<section class="col col-3" id="kvaNoEntry">
												<label class="label">Load (KVA)&nbsp;</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> <form:input
														type="text" name="loadkva" id="loadkva" path="loadkva"
														placeholder="Desired Load(KVA)"></form:input> </label>
											</section>
				
											<section class="col col-3">
												<label class="label">No&nbsp;of&nbsp;Connections&nbsp;<font
													color="red">*</font></label><label class="input"><i
													class="icon-prepend fa fa-ban"></i> <form:input type="number"
														name="loadkva" id="noofconnections" path="noofconnections"
														min="1" placeholder="No Of Connections" onchange="trimNoOfConnections(this.value)"></form:input> </label>
											</section>
										</div>
				
									</fieldset>
				
									<footer>
				
										<button type="submit" onclick="return  checkValidationSubConnections();" class="btn btn-primary">Submit</button>
				
										<a class="btn btn-danger" onclick="return  cancelForm();"
											href="javascript:void(0);">Back</a>
				
									</footer>
								</form:form>
					 </div>
				 </div>
			 </div>
			 
			 
			 
			 
			 <div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-layouts" data-widget-editbutton="false" data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>Add Multiple Sites for Layout to Application Id : ${applicationId}</h2>
					</header>

		      <div>

					<div class="jarviswidget-editbox"></div>
					<div class="widget-body">

								<form action="./ncms/createLayouts" method="post" id="submit-layouts" class="smart-form">
				
									<input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${applicationId}">
				
									<fieldset>
				
										<div class="row">
											
											
											<section class="col col-3">
												<label class="label">Proposed&nbsp;Installation&nbsp;</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												  </span> 
												     <select class="form-control" id="proposedinstallationtype" name="proposedinstallationtype">
														<option value="" selected disabled>Select</option>
														<option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</option>
														<option value="Village Panchayat">Village Panchayat</option>
				
													</select>
				
				
												</label>
											</section>
				
											<section class="col col-3">
												<label class="label">Nature of installation</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												</span> 
												<select class="form-control" id="natureofinstal"
														name="natureofinstal"
														onchange="onselectTariffforLayout(this.value)">
														<option value="" selected disabled>Select Installation</option>
												</select>
												</label>
											</section>
											
											
											<section class="col col-3">
												<label class="label">Dimensions&nbsp;(Sqft)&nbsp;</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												  </span> 
												<select class="form-control" id="dimensionsdrop" name="dimensionsdrop" onchange="onChangeLayout(this.value)">
													<option value="">Select</option>
													<option value="20*30">20*30(600)</option>
													<option value="30*40">30*40(1200)</option>
													<option value="60*40">60*40(2400)</option>
													<option value="50*80">50*80(4000)</option>
													<option value="Other">Other</option>
												</select>
												</label>
											</section>
				
											<section class="col col-3" id="oddDimensionsEntry" style="display: none;">
												<label class="label">New Dimension</label><label class="input"><i
													class="icon-prepend fa fa-ban"></i>
													<input type="text" name="sitedimension"
														placeholder="Total Dim.(Sqft)" name="sitedimension"
														id="sitedimension" class="form-control"
														style="height: 37px; font-size: 13px;"
														data-original-title="Total Dimension(Sqft)" rel="tooltip" onchange="onChangeNewDimension(this.value)"/>
												</label>
											</section>
				
											<section class="col col-3" id="noOfSitesEntry">
												<label class="label">No.&nbsp;Of&nbsp;Sites</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> 
													<input type="text" name="noofsites"
														placeholder="No. of Sites" name="noofsites"
														id="noofsites" class="form-control"
														style="height: 37px; font-size: 13px;"
														data-original-title="No Of Sites" rel="tooltip" onchange="onChangeNoOfSites(this.value)"/>
												</label>
											</section>
				
											<section class="col col-3" >
												<label class="label">Requested Load(KW)&nbsp;</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> <input
														type="text" name="requestedloadkw" id="requestedloadkw" name="requestedloadkw"
														placeholder="Requested Load(KW)"/> </label>
											</section>
				
											
										</div>
				
									</fieldset>
				
									<footer>
				
										<button type="submit" class="btn btn-primary">Add</button>
				
										<a class="btn btn-danger" onclick="return  cancelForm();"
											href="javascript:void(0);">Back</a>
				
									</footer>
								</form>
					 </div>
				 </div>
			 </div>
				 
				       
				       
				       <c:if test="${not empty depositPaidResult}">
							<script>
										var result = "${depositPaidResult}";
										alert(result);
									</script>
					
							<c:remove var="depositPaidResult" scope="session" />
						</c:if>
						
						<c:if test="${not empty lecWorkCompletionStatusResult}">
							<script>
										var result = "${lecWorkCompletionStatusResult}";
										alert(result);
									</script>
					
							<c:remove var="lecWorkCompletionStatusResult" scope="session" />
						</c:if>
						
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
										<b><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application Rejected&nbsp;&nbsp;&nbsp;<a href='#' onclick="reasontoOnhold()" rel='tooltip' data-placement='bottom' data-original-title='Click here to check Reason'><b>Click here for Reason</b></a></font></b>
										

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
										
										
										<li >
										    <a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s4"> <span class="hidden-mobile hidden-tablet">Estimation</span></a>
										</li>
										
										<li class="active">
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
										</li>
										
										
										<li>
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Documents</span></a>
										</li>

										
										
										
                                    </c:if>
									<c:if test = "${finalStatus==13 || finalStatus==23}"> 	
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application Details</span></a>
										</li>
										
										<li>
										<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
										<li>
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
									<c:if test = "${finalStatus==17 || finalStatus==27 }">  
										
										<li>
											<a data-toggle="tab" href="#s1"> <span class="hidden-mobile hidden-tablet">Application </span></a>
										</li>
										
										<li>
											<a data-toggle="tab" href="#s3"> <span class="hidden-mobile hidden-tablet">Field Verification</span></a>
										</li>
									
									    <li>
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
											<a data-toggle="tab" href="#s15"><span class="hidden-mobile hidden-tablet">Docs</span></a>
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
											<a data-toggle="tab" href="#sd12"><span class="hidden-mobile hidden-tablet">FV Acceptance</span></a>
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
																	<th colspan="4">&nbsp;&nbsp;Location(Section)
																	&nbsp;:&nbsp;<b><font color="red">${locationText}</font></b>&nbsp;&nbsp;Division&nbsp;&nbsp;:&nbsp;<b><font color="red">${divisionText}</font></b>
																	&nbsp;Sub Division&nbsp;&nbsp;<b><font color="red">${subdivisionText}</font>&nbsp;
																	
																	
																	<c:if test="${(element.applicationtype =='Multiple Connection' || element.applicationtype =='MS Building') && element.finalstatus==85}">
																	<a class="btn bg-color-greenLight txt-color-white"
																		style="width:200px;"
																		onclick="return createSubConnection();" title="Create multiple connections for MS Building/Multiple Connection">
																		<font size="2"><b>Create Multiple Connections</b></font>
																	</a> &nbsp;
																	</c:if>
																	
																	<c:if test="${element.applicationtype =='Multiple Connection' || element.applicationtype =='MS Building'}">
																	<a class="btn bg-color-pinkDark txt-color-white"
																		style="width:140px;"
																		onclick="return viewSubConnection(${applicationId});" title="View multiple connections for MS Building/Multiple Connection">
																		<font size="2"><b>View Connections</b></font>
																	</a> &nbsp;&nbsp;
																	
																	</c:if>
																	
																	<c:if test="${(element.applicationtype =='Layout') && element.finalstatus==85}">
																	
																	  
																	<a class="btn bg-color-greenLight txt-color-white"
																		style="width:150px;"
																		onclick="return createLayouts();" title="Create Multiple Layouts with different dimensions">
																		<font size="2"><b>Add Multiple Sites</b></font>
																	</a> &nbsp;
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
																	
																	<th>No of Connections</th>
																	<td>${element.noofconnections}</td>
																	
																</tr>
																
																<tr>
																	<th>Purpose of Power Supply</th>
																	<td>${element.supplyfor}</td>
																
																	<th>Application Type</th>
																	<td class="ApplicationType">${element.applicationtype}</td>
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
																	<td>${element.watersource}</td>
																	
																	
																		<th>Application Registration Date  </th>
																		<td class="regdate"><fmt:formatDate value="${element.appregdate}" pattern="dd-MM-yyyy"/></td>
																
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
																	<td>
																	 <fmt:formatNumber type="number" minFractionDigits="2" value="${estimation.estmcost}" />
																	</td>
																	
																	
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
																		<td class="psdate"><fmt:formatDate value="${powersanc.psdate}" pattern="dd-MM-yyyy"/></td>
																	</tr>
																	<tr>
																		<th>Power Sanctiond By  </th>
																		<td>${powersanc.username}</td>
																	</tr>
																	
																	<tr>
																		<th>Power Sanction Submitted On  </th>
																		<td><fmt:formatDate value="${powersanc.datestamp}" pattern="dd-MM-yyyy"/></td>
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
											  <div class="tab-pane fade active in padding-10 no-padding-bottom" id="sd12"> 	
											</c:when>
											<c:otherwise>
											  <div class="tab-pane fade" id="sd12">
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
											
											<c:if test = "${finalStatus<=15}">
											<div class="col-sm-5">
			                                              <br>
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget jarviswidget-sortable jarviswidget-color-greenLight" id="id85" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"><i class="icon-prepend fa fa-rupee"></i></span>
														<h2>Online Payment</h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															
															<form action="./depositPaidInsert" method="post" id="depositpaid-form" class="smart-form" >
																<input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${applicationId}"></input>
																<fieldset>
																	
																	<label class="col-md-12 control-label"><font color="red"><b>For Online Payment Click on Pay Online and do the payment </b></font></label>
																	<br><br>
																	<label class="col-md-12 control-label"><font color="blue"><b>For manual payment click on download challan and pay amount in the Cash Counter and forward that Receipt details to AET of your Subdivision. </b></font></label>
																	
																</fieldset>
									
																<footer>
																
																
																<a href='#' onclick="individualPaymentClick(${applicationId});"><font > <b>Pay Online</b> </font></a>
																&nbsp;&nbsp;&nbsp;
																<a href='#' onclick="downloadChallanToPay(${applicationId});"><font > <b style="color: maroon;font-size:14px;background: #FFFAF0">Download Challan To Pay Manually</b> </font></a>
																
																</footer>
															</form>
															
															
															
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												
											</div>
											
											
											
											<div class="col-sm-7">
			                                 <br>
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget jarviswidget-sortable jarviswidget-color-greenLight" id="widid-81" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"></span>
														<h2>Wiring Completion Details</h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															
															<form action="./lecWorkCompetionReportDetails" method="post" id="workcompetionreport-form" class="smart-form" enctype="multipart/form-data">
																<input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${applicationId}"></input>
																<fieldset>
																	<div class="row">
																	
																		<section class="col col-4">
																			<label class="label">Wiring&nbsp;Completion&nbsp;Ref&nbsp;No&nbsp;<font color="red">*</font></label>
																			<label class="input"><i class="icon-prepend fa fa-tachometer"></i>
																			<input type="text"	name="lecwcrefno" placeholder="Wiring Ref No"  id="lecwcrefno">
																			</label>
																		</section>
																		
																		
																		<section class="col col-4">
																			<label class="label">Wiring&nbsp;Completion&nbsp;Date&nbsp;<font color="red">*</font></label>
																			<label class="input"> <i class="icon-append fa fa-calendar"></i>
																				<input type="text"	name="lecwcdate" placeholder="Wiring Completion Dt" id="lecwcdate" >
																			</label>
																		</section>
																		
																		<section class="col col-4">
																			<label class="label">Outlet&nbsp;Points<font color="red">*</font></label>
																			<label class="input">
																				<input type="text"	name="outletpoints" placeholder="Outlet Points"  id="outletpoints" >
																			</label>
																	   </section>
																		
																	</div>
																	
																	
																	<div class="row">
																		
																	
																	  <section class="col col-3">
																			<label class="label">Outlet&nbsp;Watts<font color="red">*</font></label>
																			<label class="input">
																				<input type="text"	name="outletwatts" placeholder="Outlet Watts"  id="outletwatts" >
																			</label>
																	   </section>
																	   
																	   <section class="col col-9">
																			<label class="label">Upload Wiring Document&nbsp;<font color="red">*</font></label>
																			<div class="input input-file">
																				<span class="button"><input type="file" id="imgInp"
																					name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text"
																					placeholder="Upload" id="docText">
																			</div>
																		</section>
				
																  </div>
																
																</fieldset>
									
																<footer>
																	<button type="submit" class="btn btn-primary">Submit</button>
																	
																</footer>
															</form>
															
															
															
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												
											</div>
											
											</c:if>
											</article>
											
											
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
															<td><fmt:formatNumber type="number" minFractionDigits="2" value="${deposit.amount}" /></td>
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
																
															
																<tr >
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
																	<th width="220">Document</th>
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
															<td><a href="#" onclick="return checkViewDocument(${app.id});"><strong>View/Download</strong></a></td></tr>
															
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

		
		

	  <div id="addtabforSubConnections" title="Multiple Connection Details" style="display:none;overflow-y:scroll;overflow-x:hidden;">
   	    <table class="table table-bordered table-striped">
		 <thead>
			 <tr>
				
				<th width="70">Sl No</th>
				<th width="200">InstallationType</th>
				<th width="200">Nature Of Installation</th>
				<th width="100">Tariff</th>
				<th width="100">Load KW</th>
				<th width="100">Load KVA</th>
				<th width="100">Load HP</th>
				<th width="300">No Of Connections</th>												
			</tr>
		</thead>
		
			<tbody id="subConnectionView">

			</tbody>
			
		
		</table>  
      </div>
      
      <div id="addtabforLayouts" title="Multiple Site Details" style="display:none;overflow-y:scroll;overflow-x:hidden;">
   	    <table class="table table-bordered table-striped">
		 <thead>
			 <tr>
				
				<th width="70">Sl No</th>
				<th width="100">Nature&nbsp;of&nbsp;Installation</th>
				<th width="100">Tariff</th>
				<th width="100">Site Dimension</th>
				<th width="100">No of Sites</th>
				<th width="100">Requested Load</th>
			</tr>
		</thead>
		
			<tbody id="layoutView">

			</tbody>
			
		
		</table>  
      </div>

		

<script type="text/javascript">

//Multiple Connection and MS Building
	
	
	
	function trimNoOfConnections(myNumber) {
		 $("#noofconnections").val(parseInt(myNumber));
	}
		
	function checkValidationSubConnections(){
	
		var natureofinstallation = $("#natureofinstallation").val();
	
		if(natureofinstallation!="" && natureofinstallation!="null" && natureofinstallation!=null){
			if (document.getElementById("loadkw").value == 0 && document.getElementById("loadhp").value == 0 && document.getElementById("loadkva").value == 0){
				alert("Please enter Desired LOAD(KW/HP/KVA) greater than Zero");
				return false;
		    }
		}
    }
	
	function viewSubConnection(applicationid){
			$("#subConnectionView").empty();
			var tableData = "";
			$.ajax
			({			
				type : "POST",
				url : "./ncms/viewSubConnectionsPopUp/"+applicationid,
				async: false,
				dataType : "JSON",
				success : function(response) 
				{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
				              	var obj = response[s];
				              	
				              	tableData += "<tr>"
										+"<td>"+(s+1)+"</td>"
										+"<td>"+obj.proposedinsttype+"</td>"
										+"<td>"+obj.natureofinstallation+"</td>"
										+"<td>"+obj.tariffdesc+"</td>"
										+"<td>"+obj.loadkw+"</td>"
										+"<td>"+obj.loadkva+"</td>"
										+"<td>"+obj.loadhp+"</td>"
										+"<td>"+obj.noofconnections+"</td>"
										+"</tr>";
						                
						     }
						$('#subConnectionView').append(tableData);
				}
			
		
			});
			
			dialog = $("#addtabforSubConnections").dialog({
				autoOpen : false,
				width : 1100,
				height : 600,
				resizable : false,
				modal : true,
				
			}).dialog("open");	
		}
	
	function viewLayouts(applicationid){
		
		$("#layoutView").empty();
		var tableData = "";
		$.ajax
		({			
			type : "POST",
			url : "./ncms/viewLayoutsPopUp/"+applicationid,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
				for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	
			              	tableData += "<tr>"
									+"<td>"+(s+1)+"</td>"
									+"<td>"+obj.natureofinstallation+"</td>"
									+"<td>"+obj.tarifftype+"</td>"
									+"<td>"+obj.sitedimension+"</td>"
									+"<td>"+obj.noofsites+"</td>"
									+"<td>"+obj.requestedloadkw+"</td>"
									+"</tr>";
					                
					     }
					$('#layoutView').append(tableData);
			}
		
	
		});
		
		dialog = $("#addtabforLayouts").dialog({
			autoOpen : false,
			width : 800,
			height : 450,
			resizable : false,
			modal : true,
			
		}).dialog("open");	
	}
		
	function onChangeLayout(value){
		 
		  if(value=="20*30"){
			  $("#requestedloadkw").val(3);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('20*30');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="30*40"){
			  $("#requestedloadkw").val(6);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('30*40');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="60*40"){
			  $("#requestedloadkw").val(8);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('60*40');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="50*80"){
			  $("#requestedloadkw").val(10);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('50*80');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else{
			  $("#sitedimension").val('');
			  $('#oddDimensionsEntry').show();
			  $("#noofsites").val('1');
			  $("#requestedloadkw").val('');
			  $('#noOfSitesEntry').hide();
			  
			  
		  }
		}
	
	function onChangeNewDimension(value){
		
		if(value<=600){
			 $("#requestedloadkw").val(3);
		}else{
			
			if(value>4000){
				var newvalue=value-4000;
				var loadkiloWt=parseFloat((newvalue/400));
				$("#requestedloadkw").val((10+Math.ceil(loadkiloWt)));
			}else{
				if(value>600 && value<=1200){
					$("#requestedloadkw").val(6);
				}else if(value>1200 && value<=2400){
					$("#requestedloadkw").val(8);
				}else if(value>2400 && value<=4000){
					$("#requestedloadkw").val(10);
				}
			}
		}
	}

	function onChangeNoOfSites(noOfSites){
		
		var value=$('#dimensionsdrop').val();
		var load=0; 
		if(value=="20*30"){
			 load=3;
			  
		  }else if(value=="30*40"){
			  load=6;
		  }else if(value=="60*40"){
			  load=8;
		  }else if(value=="50*80"){
			  load=10;
		  }
		$("#requestedloadkw").val(noOfSites*(load));
		
	}
	
$('select[id*=proposedinsttype]').change(function() {
	
	var proposedinsttype = $("#proposedinsttype").val();
	var voltageSupply="LT";
	var area="";
	
	if(proposedinsttype == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(proposedinsttype=="Village Panchayat"){
		area="RURAL";
	}
	
	
  $.ajax({
	type : "POST",
	url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
	dataType : "json",
	success : function(data) {
		var newOption = $('<option>');
           newOption.attr('value',0).text(" "); 
           $('#natureofinstallation').empty(newOption);
           var defaultOption = $('<option>');
           defaultOption.attr('value',"").text("Select Installtion");
           $('#natureofinstallation').append(defaultOption);
		($.map(data, function(item) {					 
			var newOption = $('<option>'); 					
			newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
			$('#natureofinstallation').append(newOption);
		}));
	}
}); 
	
});

$('select[id*=proposedinstallationtype]').change(function() {
	
	var proposedinsttype = $("#proposedinstallationtype").val();
	var voltageSupply="LT";
	var area="";
	
	if(proposedinsttype == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(proposedinsttype=="Village Panchayat"){
		area="RURAL";
	}
	
	
  $.ajax({
	type : "POST",
	url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
	dataType : "json",
	success : function(data) {
		var newOption = $('<option>');
           newOption.attr('value',0).text(" "); 
           $('#natureofinstal').empty(newOption);
           var defaultOption = $('<option>');
           defaultOption.attr('value',"").text("Select Installtion");
           $('#natureofinstal').append(defaultOption);
		($.map(data, function(item) {					 
			var newOption = $('<option>'); 					
			newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
			$('#natureofinstal').append(newOption);
		}));
	}
}); 
	
});


function onselectTariff(){
	var natureofinstallation = $("#natureofinstallation").val();
	var res = natureofinstallation.split("-"); 
	
	 var result1 = res[1].indexOf("LT1") > -1;
	 var result2 = res[1].indexOf("LT2") > -1;
	 var result6A = res[1].indexOf("LT6A") > -1;
	 var result6B = res[1].indexOf("LT6B") > -1;
	 var result7 = res[1].indexOf("LT7") > -1;

	 var result3 = res[1].indexOf("LT3") > -1;
	 var result5 = res[1].indexOf("LT5") > -1;
	 
	 var result4 = res[1].indexOf("LT4") > -1;
	 
	 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
	
	
	if(ApplicationType=='MS Building'){
		$("#plinthAreaNoEntry").show();
		
		if(result1 || result4 || result6A || result6B || result7){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3and LT5 are applicable for MS Building");
			$("#natureofinstallation").val('');
		}
		
	}
	
	if(result1 || result2 || result6A || result7 || result6B){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
	} 
	if(result3 || result5){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').show();
	}
	if(result4){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').hide();
	}
	
}

function onselectTariffforLayout(){
	var natureofinstallation = $("#natureofinstal").val();
	var res = natureofinstallation.split("-"); 
	
	 var result1 = res[1].indexOf("LT1") > -1;
	 var result2 = res[1].indexOf("LT2") > -1;
	 var result3 = res[1].indexOf("LT3") > -1;
	 var result4 = res[1].indexOf("LT4") > -1;
	 var result5 = res[1].indexOf("LT5") > -1;
	 var result7 = res[1].indexOf("LT7") > -1;
	 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
	
	if(ApplicationType=='Layout'){
		if(result1 || result4 || result2 || result3 || result7 || result5){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinstal").val('');
		}
	}
	
}

function createSubConnection() {
	$('#wid-id-0').hide();
	$('#wid-id-createsubconnections').show();
}

function createLayouts() {
	$('#wid-id-0').hide();
	$('#wid-id-layouts').show();
}

function cancelForm(){
	$('#wid-id-createsubconnections').hide();
	$('#wid-id-layouts').hide();
	$('#wid-id-0').show();
}

function viewpowerSanctionReport(applicationId) {
	window.open("./document/report/view/" + applicationId);
}

function viewMeterProcurementReport(applicationId){
	window.open("./document/meterProcurement/view/" + applicationId);
}
function reasontoOnhold(){
	var str1 = document.getElementsByClassName('remarksContent')[0].innerHTML;	
	var str = str1.replace("null", "");
	alert(str);
}

function individualPaymentClick(appId){
	
	 location.href = './onlinePaymentByApplicationId?applicationId='+appId; 
	
	
}

function downloadChallanToPay(appId){

	 location.href = './downloadChallanToPay/reqDepositDetails?applicationId='+appId;
}

function convertToUpperCase(){
	
	$("#recieptNo").val($("#recieptNo").val().trim());
	
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
            }
        	  reader.readAsDataURL(input.files[0]);
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp').val("");
        	$('#docText').val("");
            return false;
        }
       
        } 
    
}


$("#imgInp").change(function(){
    readURL(this);
});


		
function checkduplicate(){
	var refNo=$("#recieptNo").val();
	var phasecount=15;			
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
				alert("Receipt No already exists");
				$("#recieptNo").val(" ");
			}
		}
	});
}
		
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
		}
		
		function checkViewDocument(docId) {
			window.open("./customerUploadedDoc/download/"+docId);
		}
		
		function viewServiceCertificate(applicationId) {
			window.open("./viewServiceCertificate/download/"+applicationId);
		}

		$('#receiptDate').datepicker({
			  dateFormat : 'dd/mm/yy',
			  maxDate: new Date()

		}); 
	
		$('#lecwcdate').datepicker({
			  dateFormat : 'dd/mm/yy',
			  maxDate: new Date(),

		}); 
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			//Ms Building
			$('#wid-id-createsubconnections').hide();
			
			//Layouts
			$('#wid-id-layouts').hide();
			
			$('#kvaNoEntry').hide();
			$('#hpNoEntry').hide();
			$('#KWANoEntry').hide();
			
			
			
			
			$.validator.addMethod("specialCharValidation", function(value, element) {
				 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
			}, "Only letters, numbers allowed");
			
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
			
			 $('#depositpaid-form').validate({
					// Rules for form validation
						rules : {
							recieptNo : {
								required : true,
								maxlength : 25,
								regex : /^[0-9]*$/
							},
							
							receiptDate : {
								required : true,
								dateFormat: true
								
							},
							payAmount : {
								required : true,
								maxlength : 12,
								digits:true,
								minStrict : 0
								
							},
							payTowards : {
								required : true
								
							},
							
						},
				
						// Messages for form validation
						messages : {
							
							recieptNo : {
								required : 'please enter receipt no',
								regex:'Please enter valid receipt no (only numbers)',
							},
							
							receiptDate : {
								required : 'please enter receipt date',
								
							},
							payAmount : {
								required : 'please enter amount',
								minStrict:'Amount should be > 0',
								
								
							},
							payTowards : {
								required : 'please select this field',
								
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
			 
			 
			 
			 $('#submit-msbulidconnection').validate({
					// Rules for form validation
						rules : {
							proposedinstallationtype : {
								required : true,
							},
							
							natureofinstal : {
								required : true,
							},
							noofconnections : {
								required : true,
								digits : true,
							},
							loadhp : {
								required : true,
								maxlength : 12,
								regex : /^[0-9 .]*$/,
								max:10000
							},

							loadkva : {
								required : true,
								maxlength : 12,
								regex : /^[0-9 .]*$/,
								max:10000
							},
							loadkw : {
								required : true,
								maxlength : 12,
								regex : /^[0-9 .]*$/,
								max:10000
							},
							
							
						},
				
						// Messages for form validation
						messages : {
							loadkw : {
								required : 'Please enter load(kw)',
								maxlength : 'maxlength is 12',
								regex : 'please enter numbers',
								max : 'max value is 10000',
							},

							loadhp : {
								required : 'Please enter load(hp)',
								regex : 'please enter numbers',
								maxlength : 'maxlength is 12',
								max : 'max value is 10000',
							},
							loadkva : {
								required : 'Please enter load(kva)',
								regex : 'please enter numbers',
								maxlength : 'maxlength is 12',
								max : 'max value is 10000',
							},
							noofconnections : {
								digits : 'Please enter numbers',
								
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
			 
			 
			 $('#submit-layouts').validate({
					// Rules for form validation
						rules : {
							dimensionsdrop : {
								required : true,
							},
							proposedinstallationtype : {
								required : true,
							},
							natureofinstal : {
								required : true,
							},
							noofsites : {
								required : true,
								digits : true,
							},
							sitedimension : {
								required : true,
								regex : /^[0-9 .]*$/,
							},
							requestedloadkw : {
								required : true,
								regex : /^[0-9 .]*$/,
								max:10000
							},
							
							
						},
				
						// Messages for form validation
						messages : {
							requestedloadkw : {
								required : 'Please enter load(kw)',
								regex : 'please enter numbers',
								max : 'max value is 10000',
							},

							noofsites : {
								required : 'Please enter No. of sites',
								digits : 'Please enter numbers',
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
			 $('#workcompetionreport-form').validate({
					// Rules for form validation
						rules : {
							
							lecwcrefno : {
								required : true,
								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							
							lecwcdate : {
								required : true,
								
							},
							
							outletpoints : {
								required : true,
								minStrict : 0
								
							},
							outletwatts : {
								required : true,
								minStrict : 0
								
							},
							file : {
								required : true
								
								
							},
							
						},
				
						// Messages for form validation
						messages : {
							lecwcrefno:{
								required : 'please enter reference no',
								regex:'Please Enter Valid Reference No.',
							},
							lecwcdate : {
								required : 'please select this field',
							},
							
							outletpoints : {
								required : 'please enter outlet points',
								minStrict:'value should be > 0',
							},
							file : {
								required : 'please upload document',
								
							},
							outletwatts : {
								required : 'please enter outlet watts',
								minStrict:'value should be > 0',
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
			     var regdate1=document.getElementsByClassName('regdate')[0].innerHTML;
				 var monthyear=regdate1.split("-");					 
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
				$("#receiptDate").datepicker("option","minDate",minDate );
				
				
				var regdate=document.getElementsByClassName('regdate')[0].innerHTML;
				 var monthyear=regdate.split("-");					 
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
				$("#lecwcdate").datepicker("option","minDate",minDate );
			 

		});
		
		
	
	
		
			
		$('#servrdate').datepicker({
				dateFormat : 'dd/mm/yy'
				
		});
		    
		</script>

	