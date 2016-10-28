<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>

<div id="content">
	<jsp:include page="../newConnection/createLayout.jsp"/>
	<jsp:include page="../newConnection/viewLayout.jsp"/>
		
		<c:if test="${not empty depositPaidManualResult}">
				<script>
					var result = "${depositPaidManualResult}";
					alert(result);
				</script>
						
			   <c:remove var="depositPaidManualResult" scope="session" />
	    </c:if>
	    
	    <c:if test="${not empty recordResult}">
							<script>
								var result = "${recordResult}";
								alert(result);
							</script>
				<c:remove var="recordResult" scope="session" />
		</c:if>
	   
	<div class="row">

		<article class="col-sm-12">

			<div class="jarviswidget" id="wid-id-rootedit"
				data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
				<span class="widget-icon">
					<i class="fa fa-edit"></i>
				</span>
					<h2>Update Application - Application Id : ${applicationId}</h2>

				</header>
				<div>
					
					<div>
					
					
							&nbsp;
							<a class="btn btn-warning" id="personnelDetailsButton"
								style="width:190px;margin-top: -10px;"
								onclick="return editPersonnelDetails();"
								title="click here to Edit personnel details"><font size="2"><b>Edit Personnel details</b></font>
							</a>&nbsp;&nbsp;
							
							<a  class="btn bg-color-pinkDark txt-color-white"
								style="width:170px;margin-top: -10px;"
								onclick="return editConnectionDetails();"
								title="click here to edit Connection & Account details">
								<font size="2"><b>Change Tariff</b></font>
							</a> &nbsp;
							
							<a  class="btn bg-color-redLight txt-color-white"
								style="width:170px;margin-top: -10px;"
								onclick="return editLocationDetails();"
								title="click here to edit Location details">
								<font size="2"><b>Change Location</b></font>
							</a> &nbsp;
					
					
					
					</div>

					<br>
					
					
					
					
					
					
					<div class="widget-body no-padding" style="display: none;" id="connectiondetailsId">

						<form:form id="updateconnection-form" action="" method="POST"
							class="smart-form" novalidate="novalidate" commandName="application" modelAttribute="application">
							
							<form:input type="hidden" id="applicationid" autocomplete="off" placeholder="" path="applicationid" value="${applicationId}"></form:input>
							
							<fieldset>


								<div class="row">
									
									
									<section class="col col-2" id="applicationTypeId">
										<label class="label">Application&nbsp;Type&nbsp;</label>
										<label class="select"> <span> <i
												class="fa fa-chevron-down"></i>
										</span> 
										
											 <form:select class="form-control" id="applicationtype" path="applicationtype" onchange="onChangeMSBuild(this.value)">
												<option value="Single Connection">Single Connection</option>
												<form:option value="MS Building">MS Building</form:option>
												<form:option value="Multiple Connection">Multiple Connection</form:option>
												<form:option value="Layout">Layout</form:option>
											</form:select>


										</label>
									</section>
									
									<div style="display: none;" id="showNoOfconnections" class="col col-2">
										
										<label class="label">No&nbsp;of&nbsp;Connections&nbsp;Req&nbsp;<font
															color="red">*</font></label><label
										class="input">
										<form:input type="text" name="noofconnections"
											placeholder="Enter No of Connections" path="noofconnections"
											id="noofconnections" 
											 ></form:input> </label>
											
									</div>
									
									
									<section class="col col-3">
										<label class="label">Proposed&nbsp;Installation&nbsp;</label>
										<label class="select"> <span> <i
												class="fa fa-chevron-down"></i>
										</span> <form:select class="form-control" id="locality"
												path="locality">
												<option value="" selected disabled>Select</option>
												<form:option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</form:option>
												<form:option value="Village Panchayat">Village Panchayat</form:option>

											</form:select>


										</label>
									</section>

									
										
											<section class="col col-2">
												<label class="label">Category</label>
												<input type="radio" value="ltcategory" id="ltcategory"
														name="lt" checked="checked">&nbsp;&nbsp;<font
														color=blue;><b>LT</b></font>&nbsp;&nbsp;&nbsp;&nbsp;
														
											  <input type="radio" value="htcategory" id="htcategory"
														name="lt">&nbsp;&nbsp;<font color=blue;><b>HT</b></font>
										
										</section>



										
										
										
										<section class="col col-3">
										<label class="label">Nature of installation</label> <label
											class="select"> <span> <i
												class="fa fa-chevron-down"></i>
										</span> <form:select class="form-control" id="natureofinst"
												path="natureofinst"
												onchange="onselectTariffEdit(this.value)">
												<option value="" selected disabled>Select
													Installation</option>
											</form:select>
										</label>
									   </section>
										
										
									
									
									

									</div>
									
									<div class="row">
									
									<div id="dimensionsShowforLayout" style="display: none;" class="col col-2">
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
									
								    </div>
													
									<div id="oddDimensionsEntryShow" class="col col-2" style="display: none;">
										
										<label class="label">New Dimension&nbsp;<font
											color="red">*</font></label><label
										class="input">
										<form:input type="text" name="dimensions"
											placeholder="Total Dim.(Sqft)" path="dimensions"
											id="dimensions" 
											 onchange="onChangeNewDimension(this.value)"></form:input> </label>
											
									</div>
									
									<div id="showNoOfSites" class="col col-2" style="display: none;">
										
										<label class="label">No.&nbsp;Of&nbsp;Sites&nbsp;<font
											color="red">*</font></label><label
										class="input">
													
									<form:input type="text" name="noOfSites" id="noOfSites"
												path="noOfSites" placeholder="No. of Sites" onchange="onChangeNoOfSitesEdit(this.value)"></form:input> </label>
											
									</div>
									
									<section class="col col-2" id="KWANoEntry">
										<label class="label">Desired&nbsp;Load (KW)&nbsp;</label><label
											class="input"><i class="icon-prepend fa fa-ban"></i>
											<form:input type="text" name="loadkw" id="loadkw"
												path="loadkw" placeholder="loadkw"></form:input> </label>
									</section>
									
									<section class="col col-2" id="hpNoEntry">
										<label class="label">Load (HP)</label><label class="input"><i
											class="icon-prepend fa fa-ban"></i> <form:input type="text"
												name="loadhp" id="loadhp" path="loadhp" placeholder="loadhp"></form:input>
										</label>
									</section>

									<section class="col col-2" id="kvaNoEntry">
										<label class="label">Load (KVA)&nbsp;</label><label
											class="input"><i class="icon-prepend fa fa-ban"></i>
											<form:input type="text" name="loadkva" id="loadkva"
												path="loadkva" placeholder="Desired Load(KVA)"></form:input>
										</label>
									</section>
									
									
									
									<section class="col col-2">
										<label class="label">Phase Requirement</label> <label
											class="input" for="select-8"> <form:select
												class="form-control" id="reqphase" path="reqphase">
												<form:option value="1">Single phase</form:option>
												<form:option value="3">Three Phase</form:option>

											</form:select>
										</label>
									</section>

									<section class="col col-2">
										<label class="label">Type of Ownership</label> <label
											class="input" for="select-11"> <form:select
												class="form-control" id="ownership" path="ownership">
												<option value="0" selected disabled>Select</option>
												<form:option value="Individual">Individual</form:option>
												<form:option value="Leased/Rental">Leased/Rental</form:option>
												<form:option value="Co owned">Co owned</form:option>
												<form:option value="Proprietorship">Proprietorship</form:option>
												<form:option value="Public company">Public company</form:option>
												<form:option value="Private Company">Private Company</form:option>
												<form:option value="PartnerShip">PartnerShip</form:option>
												<form:option value="Trust">Trust</form:option>
											</form:select>
										</label>
									</section>

									<section class="col col-2">
										<label class="label">RR Number 1</label> <label class="input">
											<form:input type="text" name="nearbyrrnoone"
												placeholder="RR Number" path="nearbyrrnoone"></form:input>
										</label>
									</section>
									
									

								</div>

								<div class="row">

										<section class="col col-2">
											<label class="label">Account No 1</label> <label class="input">
												<form:input type="text" name="nearbyacnoone"
													placeholder="Account No" path="nearbyacnoone"></form:input>
											</label>
									     </section>

										<section class="col col-2">
											<label class="label">RR Number 2</label> <label class="input">
												<form:input type="text" name="nearbyrrnotwo"
													placeholder="RR Number" path="nearbyrrnotwo"></form:input>
											</label>
										</section>
										
										
										<section class="col col-2">
											<label class="label">Account No 2</label> <label class="input">
												<form:input type="text" name="nearbyacnotwo"
													placeholder="Account No" path="nearbyacnotwo"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">RR Number 3</label> <label class="input">
												<form:input type="text" name="nearbyrrnothree"
													placeholder="RR Number" path="nearbyrrnothree"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Account No 3</label> <label class="input">
												<form:input type="text" name="nearbyacnothree"
													placeholder="Account No" path="nearbyacnothree"></form:input>
											</label>
										</section>
										
										<section class="col col-2" id="plinthAreaNoEntry">
											<label class="label">Plinth Area(Sq.m)&nbsp;<font color="red">*</font></label> <label class="input">
												<form:input type="text" name="plintharea"
																		placeholder="Plinth Area(Sqft)" path="plintharea"
																		id="plintharea" class="form-control"
																		style="height: 37px; font-size: 13px;"
																		data-original-title="Plinth Area(Sqft)" rel="tooltip"></form:input>
											</label>
										</section>
									

								</div>

							</fieldset>
							<footer>

								<button type="submit" class="btn btn-primary" onclick="return updateConnectionDetails();">
									Update</button>
								<a class="btn btn-danger" onclick="return  cancelForm();"
									href="javascript:void(0);">Back</a>

							</footer>




						</form:form>
					</div>
					
					
					
					
					<div class="widget-body no-padding" style="display: none;" id="personnelDetailsId">

						<form:form id="updatepersonneldetails-form" action="" method="POST"
							class="smart-form" novalidate="novalidate" commandName="application" modelAttribute="application">
							
							<form:input type="hidden" id="applicationid" autocomplete="off" placeholder="" path="applicationid" value="${applicationId}"></form:input>
							
							<fieldset>
								
								<div class="row">
									
									<section class="col col-3">
										<label class="label"> Applicant Type</label> <label
											class="input"> <form:select
												class="form-control" id="applicanttype"
												onchange="individualLabel(this.value);"
												path="applicanttype">
												<form:option value="1">Individual</form:option>
												<form:option value="2">Organisation</form:option>


											</form:select>
										</label>
									</section>
									
									<section class="col col-3" id="personNameLabel">
										<label class="label" >Name</label> <label
											class="input"><form:input type="text" name="name"
												placeholder="Name" path="name" id="personName"></form:input>
										</label>
									</section>


									<section class="col col-3" id="fatherNameLabel">
										<label class="label">Father/Husband
											Name</label> <label class="input"> <form:input
												type="text" name="fhname" placeholder="Father Name"
												path="fhname" id="fatherName"></form:input> </label>
									</section>

									<section class="col col-3" id="nomineNameLabel">
										<label class="label">Nominee Name</label> <label class="input"><form:input
												type="text" name="nomineename"
												placeholder="Nominee(Optinal)" path="nomineename"
												id="nomineName"></form:input> </label>
									</section>
									
									
									<section class="col col-3" id="orgNameLabel">
										<label class="label">Organization Name</label> <label class="input"><form:input
												type="text" name="nameoforg"
												placeholder="Organization Name" path="nameoforg"
												id="orgName"></form:input> </label>
									</section>


									<section class="col col-3" id="authSigNameLabel">
										<label class="label">Authorized&nbsp;Signatory&nbsp;Name&nbsp;</label> <label class="input">
										<form:input type="text" name="nameauthsignatory"
												placeholder="Authorized Signatory Name" path="nameauthsignatory"
												id="authSigName"></form:input> </label>
									</section>

									<section class="col col-3" id="authDesNameLabel">
										<label class="label">Designation&nbsp;</label> <label class="input"> <form:input
												type="text" name="desigauthsignatory"
												placeholder="Auth Designation" path="desigauthsignatory"
												id="authDesName"></form:input>
										</label>
									</section>
									
									</div>
									
									<div class="row">
									     <section class="col col-3">
											<label class="label">House&nbsp;No&nbsp;<font color="red">*</font></label><label
												class="input"><form:input type="text"
													name="hnoperm" id="hnoperm" path="hnoperm" placeholder="House No"></form:input>
											</label>
										</section>
										
										
										<section class="col col-3">
											<label class="label">Street&nbsp;Name&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-road"></i> <form:input type="text"
													name="streetperm" id="streetperm" path="streetperm" placeholder="Street Name"></form:input>
											</label>
										</section>
										
										
										<section class="col col-3">
											<label class="label">Area&nbsp;Name&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-map-marker"></i> <form:input type="text"
													name="areaperm" id="areaperm" path="areaperm" placeholder="Area Name"></form:input>
											</label>
										</section>
										
										
										<section class="col col-3">
											<label class="label">City&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-map-marker"></i> <form:input type="text"
													name="cityperm" id="cityperm" path="cityperm" placeholder="City Name"></form:input>
											</label>
										</section>
										
										</div>
										
										<div class="row">
										
										<section class="col col-2">
											<label class="label">Pincode&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-map-marker"></i> <form:input type="text"
													name="pinperm" id="pinperm" path="pinperm" placeholder="Pincode"></form:input>
											</label>
										</section>
										
										<section class="col col-3">
											<label class="label">Mobile&nbsp;No&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-map-marker"></i> <form:input type="text"
													name="mobilenoperm"  path="mobilenoperm" placeholder="Mobile No" id="mobilenoperm" data-mask="9999999999"></form:input>
											</label>
										</section>
										
										<section class="col col-3">
											<label class="label">Landmark&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-map-marker"></i> <form:input type="text"
													name="landmarkperm" id="landmarkperm" path="" placeholder="Landmark"></form:input>
											</label>
										</section>
										
										
										</div>
							</fieldset>
							<footer>

								<button type="submit" class="btn btn-primary" onclick="return updatePersonnelDetails();">Update</button>
								<a class="btn btn-danger" onclick="return  cancelForm();" href="javascript:void(0);">Back</a>

							</footer>




						</form:form>
					</div>
					
					
					
					
					
					
					<div class="widget-body no-padding" id="showupdateSection" style="display: none;">

						<form:form id="updateSection-form" action="" method="POST"
							class="smart-form" novalidate="novalidate" commandName="application" modelAttribute="application">
							
							<form:input type="hidden" id="applicationid" autocomplete="off" placeholder="" path="applicationid" value="${applicationId}"></form:input>
							
							<fieldset>
								
								<div class="row">

									<section class="col col-3">
										<label class="select"> <select id="circleSiteCode"
											name="circleSiteCode">
												<option value="0" selected disabled>Select Circle</option>

												<c:forEach items="${circleList}" var="circle">
													<option value="${circle.circleSiteCode}">${circle.circleName}</option>
												</c:forEach>
										</select><i></i>
										</label>
									</section>
									
									


									<section class="col col-3">
										<label class="select"> <select id="divisionSiteCode"
											name="divisionSiteCode">
												<option value="0" selected disabled>Select Division</option>

										</select><i></i>
										</label>
									</section>

										
									<section class="col col-3">
										<label class="select"> <select
											name="subDivisionSiteCode" id="subDivisionSiteCode"
											class="form-control">
												<option value="0" selected disabled>Select Sub
													Division</option>
										</select><i></i>
										</label>
									</section>


									<section class="col col-3">
										<label class="select"> <select class="form-control"
											id="sitecode" name="sitecode">
												<option value="0" selected disabled>Select Section</option>
										</select><i></i>
										</label>
									</section>
									
								</div>
									
								

							</fieldset>
							<footer>

								<button type="submit" class="btn btn-primary" onclick="return updateSection();">
									Update</button>
								<a class="btn btn-danger" onclick="return  cancelForm();"
									href="javascript:void(0);">Back</a>

							</footer>
						</form:form>
					</div>
					
					
					
				</div>
			</div>





			<div class="jarviswidget jarviswidget-color-greenDark"
				id="wid-id-receiptDataShow" data-widget-editbutton="false"
				data-widget-colorbutton="false">

				<header>
					<span class="widget-icon"> <i class="fa fa-check"></i>
					</span>
					<h2>ARF Details(Enter if ARF Paid Manually)</h2>
				</header>

				<div>

					<div class="jarviswidget-editbox"></div>
					<div class="widget-body">

						<form action="./depositPaidManualInsert" method="post"
							id="depositpaid-form" class="smart-form">
							<input type="hidden" id="applicationid" autocomplete="off"
								placeholder="" name="applicationid" value="${applicationId}"></input>
							<fieldset>
								<div class="row">

									<section class="col col-6">
										<label class="label">Receipt&nbsp;Number&nbsp;<font
											color="red">*</font></label> <label class="input"><i
											class="icon-prepend fa fa-tachometer"></i> <input type="text"
											name="recieptNo" placeholder="Receipt Number" id="recieptNo"
											 onkeyup="trimNumber();"><!-- onchange="checkduplicateReceiptNo()" -->
										</label>
									</section>


									<section class="col col-6">
										<label class="label">Receipt&nbsp;Date&nbsp;<font
											color="red">*</font></label> <label class="input"> <i
											class="icon-append fa fa-calendar"></i> <input type="text"
											name="receiptDate" placeholder="Reciept Date"
											id="receiptDate">
										</label>
									</section>

								</div>


								<div class="row">
									<section class="col col-6">
										<label class="label">Amount&nbsp;<font color="red">*</font></label>
										<label class="input"><i
											class="icon-prepend fa fa-rupee"></i> <input type="text"
											name="payAmount" placeholder="Amount" id="payAmount">
										</label>
									</section>


									<section class="col col-6">
										<label class="label">Pay&nbsp;Towards<font color="red">*</font></label>
										<label class="input"><select class="form-control"
											id="payTowards" name="payTowards">
												<option value="" selected disabled>Select</option>
												<c:forEach items="${towardsList}" var="sections">
													<option value="${sections.description}">${sections.description}</option>
												</c:forEach>

										</select></label>
									</section>


								</div>

							</fieldset>

							<footer>

								<button type="submit" class="btn btn-primary">Submit Receipt</button>

								<a class="btn btn-danger" onclick="return  cancelForm();"
									href="javascript:void(0);">Back to Application</a>

							</footer>
						</form>
					</div>
				</div>
			</div>




			<div class="jarviswidget" id="wid-id-showApplication"
				data-widget-togglebutton="false" data-widget-editbutton="false"
				data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
				data-widget-deletebutton="false">

				<header>
					<ul>
						<li>
						
							&nbsp;
							<a class="btn btn-warning" id="receiptNoButton"
								style="width:105px;margin-top: -10px;"
								onclick="return enterARFreceiptDetails();"
								title="click here to enter ARF Receipt details"><font size="2"><b>ARF Entry</b></font>
							</a>&nbsp;&nbsp;
							
							
							
							<a  class="btn bg-color-greenLight txt-color-white"
								style="width:210px;margin-top: -10px;"
								onclick="return checkData();" title="click here to submit Application to Field Verification if Application details are correct">
								<font size="2"><b>Submit Application To FV</b></font>
							</a> &nbsp;
							
							<a  class="btn bg-color-pinkDark txt-color-white"
								style="width:140px;margin-top: -10px;"
								onclick="return checkData1();"
								title="click here to edit the Application">
								<font size="2"><b>Edit Application</b></font>
							</a> &nbsp;
							
							<a  class="btn bg-color-blueLight txt-color-white"
								style="width:145px;margin-top: -10px;"
								onclick="return downloadARFChallan(${applicationId});"
								title="click here to download Challan">
								<font size="2"><b>Download ARF</b></font>
							</a> &nbsp;
							
							
						</li>
					</ul>

				</header>


				<div class="no-padding">

					<div class="jarviswidget-editbox"></div>


					<div class="widget-body">
						<!-- content -->
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in padding-10 no-padding-bottom"
								id="s1">

								<div class="col-sm-12">
									<br>
									<table class="table table-bordered table-striped">
										<tbody>
											<c:forEach var="element"
												items="${applicationServiceDetailsList}">

												<tr>
													<th colspan="4">
														&nbsp;Circle
														&nbsp;:&nbsp;<b><font color="red">${circleText}</font></b>&nbsp;&nbsp;&nbsp;Division&nbsp;&nbsp;:&nbsp;<b><font
															color="red">${divisionText}&nbsp;&nbsp;&nbsp;&nbsp;</font></b>Sub
														Division&nbsp;:&nbsp;<b><font color="red">${subdivisionText}</font></b>
														&nbsp;&nbsp;Location(Section)&nbsp;&nbsp<b><font
															color="red">${locationText}</font> 
														&nbsp;
														
														<c:if test="${element.applicationtype =='Multiple Connection' || element.applicationtype =='MS Building'}">
																	
																	<a class="btn bg-color-greenLight txt-color-white"
																		style="width:195px;"
																		onclick="return createSubConnection(${applicationId});" title="Create multiple connections for MS Building/Multiple Connection">
																		<font size="2"><b>Add Multiple Connections</b></font>
																	</a> &nbsp;&nbsp;
																	
																	
																	<a class="btn bg-color-redLight txt-color-white"
																		style="width:135px;"
																		onclick="return viewSubConnection(${applicationId});" title="View multiple connections for MS Building/Multiple Connection">
																		<font size="2"><b>View Connections</b></font>
																	</a> &nbsp;&nbsp;
																	
														</c:if>
														
														<c:if test="${element.applicationtype =='Layout'}">
															<a class="btn bg-color-greenLight txt-color-white"
																style="width:150px;"
																onclick="return createLayouts();" title="Create Multiple Layouts with different dimensions">
																<font size="2"><b>Add Multiple Sites</b></font>
															</a> &nbsp;
															
															
															
															<a class="btn bg-color-pinkDark txt-color-white"
																style="width:150px;"
																onclick="return viewLayouts(${applicationId});" title="View Layout Data">
																<font size="2"><b>View Multiple Sites</b></font>
															</a> &nbsp;&nbsp;
													    </c:if>
													    
													    	<a class="btn bg-color-red txt-color-white" href="#" data-toggle="modal" data-target="#myModal" title="Reject Application">
																<font size="2"><b>Reject Application</b></font>
															</a> &nbsp;&nbsp;
														
														</b>
													</th>
												</tr>
												<tr>
													<th colspan="2">Individual Applicants</th>
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
													<td class="tariffmain" hidden="true">${element.tariffmain}</td>
													<td class="tariffdescription">${element.tariffdesc}</td>
													<th>Desired Load</th>
													<td>${element.loadkw}KW${element.loadhp}HP
														${element.loadkva} KVA</td>
												</tr>


												<tr>
													<th colspan="2">Address Where Power Supply Required</th>
													<th colspan="2">Permanent Address/Reg Off Address (In
														Case Of Company)</th>
												</tr>

												<tr>

													<th>House/Flat/Shop/Plot No.</th>
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
													<td colspan="2">${element.areaperm}</td>
												</tr>
												<tr>
													<th>Main - Cross</th>
													<td>${element.mainpres}-${element.crosspres}</td>
													<th>Main - Cross</th>
													<td>${element.mainperm}-${element.crossperm}</td>
												</tr>
												<tr>

													<th>Land Mark</th>
													<td>${element.landmarkpres}</td>
													<td colspan="2">${element.landmarkperm}</td>
												</tr>

												<tr>
													<th>City - Pin</th>
													<td>${element.citypres}-${element.pinpres}</td>
													<th>City-Pin</th>
													<td>${element.cityperm}-${element.pinperm}</td>

												</tr>
												<tr>
													<th>Phone</th>
													<td>${element.phonepres}</td>
													<th>Phone</th>
													<td>${element.phoneperm}</td>
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
													

													<th>Phase Requirement</th>

													<c:choose>
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
													<c:choose>
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
													<c:choose>
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


													<th>Application Registration Date</th>
													<td class="regdate"><fmt:formatDate
															value="${element.appregdate}" pattern="dd-MM-yyyy" /></td>

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
													<th>Remarks</th>
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
													<th colspan="4">LEC&nbsp;Details&nbsp;(Licensed
														Electrical Contractor)</th>
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
													<td><fmt:formatDate value="${element.lecvalidity}"
															pattern="dd-MM-yyyy" /></td>

												</tr>
												<tr>

													<th>Supervisor&nbsp;Permit&nbsp;No</th>
													<td>${element.supervisorpermitno}</td>
													<th>Permit&nbsp;No&nbsp;Validity</th>
													<td><fmt:formatDate
															value="${element.supervisorvalidity}"
															pattern="dd-MM-yyyy" /></td>

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
						</div>
					</div>
				</div>
			</div>
		</article>
	</div>

<div class="jarviswidget jarviswidget-color-greenDark"
				id="wid-id-createsubconnections" data-widget-editbutton="false"
				data-widget-colorbutton="false">

				<header>
					<span class="widget-icon"> <i class="fa fa-check"></i>
					</span>
					<h2>Create Multiple Connections for Application Id : ${applicationId} - Applicable to MS Building & Multiple Connections</h2>
				</header>

		<div>

			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">

				<form:form action="./ncms/createSubConnectionsByOfficial" method="post"
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

							

							<section class="col col-3">
								<label class="label">No&nbsp;of&nbsp;Connections&nbsp;<font
									color="red">*</font></label><label class="input"><i
									class="icon-prepend fa fa-ban"></i> <form:input type="number"
										name="loadkva" id="noofconnection" path="noofconnections"
										min="1" placeholder="No Of Connections" onchange="trimNoOfConnections(this.value)"></form:input> </label>
							</section>
						</div>

					</fieldset>

					<footer>

						<button type="submit"  class="btn btn-primary">Submit</button>

						<a class="btn btn-danger" onclick="return  cancelForm();"
							href="javascript:void(0);">Back</a>

					</footer>
				</form:form>
					</div>
				</div>
			</div>
			
			
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">Application Reject</h4>
							</div>
							<div class="modal-body">
				
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<textarea rows="3" name="remarks" id="remarks" placeholder="Reason"></textarea> 
										</div>
									</div>
								</div>
				
							</div>
							<div class="modal-footer">
								<button id="firstButton" type="button" class="btn btn-primary" onclick="rejectApplication()">Reject</button>
							</div>
							
						</div>
					</div>
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

<script type="text/javascript">

function rejectApplication(){
	 var applicationid="${applicationId}";
	 var remarks= document.getElementById("remarks").value;
	
	if(remarks==null || remarks==""){
		alert("Please Enter Remarks");
		return false;
	}else if(remarks.length>500){
		alert("Maxlength is 500 characters");
		return false;
	}else{
	 
		if(confirm("Are you sure to Reject Application")){
			window.location.href="./ncms/rejectApplication/?applicationId="+applicationid+"&remarks="+remarks;
		} else{
	        return false;
	    }
	}
}


function trimNoOfConnections(myNumber) {
	 $("#noofconnection").val(parseInt(myNumber));
}

function onChangeLayout(value){
	 
	  if(value=="20*30"){
		  $("#loadkw").val(3);
		  $('#oddDimensionsEntryShow').hide();
		  $("#dimensions").val('20*30');
		  $('#showNoOfSites').show();
		  $("#noOfSites").val('1');
		  
	  }else if(value=="30*40"){
		  $("#loadkw").val(6);
		  $('#oddDimensionsEntryShow').hide();
		  $("#dimensions").val('30*40');
		  $('#showNoOfSites').show();
		  $("#noOfSites").val('1');
	  }else if(value=="60*40"){
		  $("#loadkw").val(8);
		  $('#oddDimensionsEntryShow').hide();
		  $("#dimensions").val('60*40');
		  $('#showNoOfSites').show();
		  $("#noOfSites").val('1');
	  }else if(value=="50*80"){
		  $("#loadkw").val(10);
		  $('#oddDimensionsEntryShow').hide();
		  $("#dimensions").val('50*80');
		  $('#showNoOfSites').show();
		  $("#noOfSites").val('1');
	  }else{
		 
		  $("#dimensions").val('');
		  $('#oddDimensionsEntryShow').show();
		  $('#showNoOfSites').hide();
		  $("#noOfSites").val('');
		  $("#loadkw").val('');
		  
		  
	  }
	}
	
function onChangeNewDimension(value){
	
	if(value<=600){
		 $("#loadkw").val(3);
	}else{
		
		if(value>4000){
			var newvalue=value-4000;
			var loadkiloWt=parseFloat((newvalue/400));
			$("#loadkw").val((10+Math.ceil(loadkiloWt)));
		}else{
			if(value>600 && value<=1200){
				$("#loadkw").val(6);
			}else if(value>1200 && value<=2400){
				$("#loadkw").val(8);
			}else if(value>2400 && value<=4000){
				$("#loadkw").val(10);
			}
		}
	}
}

function onChangeNoOfSitesEdit(noOfSites){
	
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
	$("#loadkw").val(noOfSites*(load));
	
}

$("#ltcategory").change(function(){

	$("#applicationTypeId").show();
	$('#kvaNoEntry').hide();
	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	
	
	
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	});
	  
});	

$("#htcategory").change(function(){

	
	$("#applicationTypeId").hide();
	$("#applicationtype").val('Single Connection');
	$('#kvaNoEntry').show();

	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	
	
	
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	});
	
	
});	

$('select[id*=locality]').change(function() {
	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	 if(x){
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	}); 
	  
	}
	 
	 if(y){
		  $.ajax({
			type : "POST",
			url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
	            newOption.attr('value',0).text(" "); 
	            $('#natureofinst').empty(newOption);
	            var defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("Select Installtion");
	            $('#natureofinst').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
					$('#natureofinst').append(newOption);
				}));
			}
		}); 
		  
		}  
	 
});

function editPersonnelDetails(){
	$('#showupdateSection').hide();
	$('#connectiondetailsId').hide();
	$('#personnelDetailsId').show();
	
	
}
function editLECDetails(){
	$('#showupdateSection').hide();
	$('#connectiondetailsId').hide();
	$('#personnelDetailsId').hide();
}
function editConnectionDetails(){
	$('#showupdateSection').hide();
	$('#connectiondetailsId').show();
	$('#personnelDetailsId').hide();
}
function editLocationDetails(){
	$('#showupdateSection').show();
	$('#connectiondetailsId').hide();
	$('#personnelDetailsId').hide();
}
function downloadARFChallan(appliId){
	 window.open("./applicationChallanDownload/Show?applicationid=" + appliId);
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
			
		if(result1 || result4 || result6A || result6B || result7){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 applicable for MS Building");
			$("#natureofinstallation").val('');
		}
			
	}
	 
	if(result1 || result2 || result6A || result7 || result6B){
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
	} 
	if(result3 || result5){
		$('#hpNoEntry').show();
		$('#KWANoEntry').show();
	}
	if(result4){
		$('#hpNoEntry').show();
		$('#KWANoEntry').hide();
	}
	
}

function onselectTariffEdit(){
	var natureofinstallation = $("#natureofinst").val();
	var ApplicationType = $("#applicationtype").val();
	var res = natureofinstallation.split("-"); 
	
	 var result1 = res[1].indexOf("LT1") > -1;
	 var result2 = res[1].indexOf("LT2") > -1;
	 var result3 = res[1].indexOf("LT3") > -1;
	 var result4 = res[1].indexOf("LT4") > -1;
	 var result5 = res[1].indexOf("LT5") > -1;
	 var result6A = res[1].indexOf("LT6A") > -1;
	 var result6B = res[1].indexOf("LT6B") > -1;
	 var result7 = res[1].indexOf("LT7") > -1;
     var desireloadkwedit= "${desireloadkwedit}";
	 var desireloadhpedit= "${desireloadhpedit}";
	 
	if(result1 || result2 || result6A || result7 || result6B){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
		
		$('#loadkw').val(desireloadkwedit);
		$('#loadhp').val(0);
		$('#loadkva').val(0);
	} 
	if(result3 || result5){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').show();
		
		$('#loadkw').val(desireloadkwedit);
		$('#loadhp').val(desireloadhpedit);
		$('#loadkva').val(0);
	}
	if(result4){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').hide();
		
		$('#loadkw').val(0);
		$('#loadhp').val(desireloadhpedit);
		$('#loadkva').val(0);
	}
	
	if(ApplicationType=='Layout'){
		if(result1 || result4 || result2 || result3 || result7 || result5){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}
	}
	
	if(ApplicationType=='MS Building'){
		
		if(result1 || result4 || result6A || result6B || result7){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are applicable for MS Building");
			$("#natureofinst").val('');
		}
		
	}
	
}
function onChangeMSBuild(value){
	
	if(value=='MS Building'){
		$("#plinthAreaNoEntry").show();
		$('#showNoOfconnections').show();
		$('#dimensionsShowforLayout').hide();
	    $('#showNoOfSites').hide();
	}else if(value=='Multiple Connection'){
		$('#dimensionsShowforLayout').hide();
	    $('#showNoOfSites').hide();
		$('#showNoOfconnections').show();
	}else if(value=='Layout'){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
		$('#dimensionsShowforLayout').show();
		$('#showNoOfSites').show();
		$('#showNoOfconnections').hide();
		$("#noofconnections").val('1');
	}else{
		$("#plinthAreaNoEntry").hide();
		$('#dimensionsShowforLayout').hide();
	    $('#showNoOfSites').hide();
	    $('#showNoOfconnections').hide();
	    $("#noofconnections").val('1');
	}
	
}

//MS Building and Multiple Connection
	
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
		
function individualLabel(labelName) {

	if (labelName == "1") {

		$("#personNameLabel").show();
		$("#fatherNameLabel").show();
		$("#nomineNameLabel").show();

		$("#orgNameLabel").hide();
		$("#authSigNameLabel").hide();
		$("#authDesNameLabel").hide();
	}

	if (labelName == "2") {

		$("#personNameLabel").hide();
		$("#fatherNameLabel").hide();
		$("#nomineNameLabel").hide();

		$("#orgNameLabel").show();
		$("#authSigNameLabel").show();
		$("#authDesNameLabel").show();

	}
}

function trimNumber(){
	
	$("#recieptNo").val($("#recieptNo").val().trim());
}


$('#receiptDate').datepicker({
	  dateFormat : 'dd/mm/yy',
	  maxDate: new Date()

}); 
function checkduplicateReceiptNo(){
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


function updateConnectionDetails(){
	
	 var kwload= document.getElementById("loadkw").value;
	 var kvaload=document.getElementById("loadkva").value;
	 var hpload=document.getElementById("loadhp").value;
	 var applicationType = document.getElementById('applicationtype').value;
	 var applType= applicationType.indexOf("MS Building") > -1;
	 var plinthArea = document.getElementById('plintharea').value;
	 var totalloadinHP=((kwload*1.34048)+(kvaload*1.0724)+(hpload*1.0));
	 
	  if(applType){
			if(totalloadinHP>=47 || plinthArea>800){
				
			}else{
				alert("Total Load should be greater than 35KW or Plinth Area greater than 800Sq.m for MS Building"); 
				return false;
			}
		}
	
	$("#updateconnection-form").attr("action","./updateConnectionDetails").submit();
}

function updateSection() {
	if(confirm("Are you sure you want to Change Location?")){
		$("#updateSection-form").attr("action","./updateApplicationSection").submit();
	}
	else{
        return false;
    }
}
function updatePersonnelDetails(){
	$("#updatepersonneldetails-form").attr("action","./updateApplicationPersonnelDetails").submit();
}


function checkData() {
	
	var applicationid="${applicationId}";
	var depositExists="${ARFpaymentExists}";
	var sendtofieldverification="${sendtofieldverification}";
	
	if(depositExists==0){
		alert("Please Submit ARF Receipt details before sending to Field Verification");
		return false;
	}else if(sendtofieldverification=='notok'){
		
		
		if(confirm("Have u filled Multiple Connections (If Yes click OK to send for Field Verication")){
			window.location.href="./SendAppToFieldVerication?applicationId="+applicationid;
		} else{
	        return false;
	    }
		
	}
	else{
		if(confirm("Are you sure you want to send Application to Field Verification?")){
			window.location.href="./SendAppToFieldVerication?applicationId="+applicationid;
		} else{
	        return false;
	    }
	}
}

function checkData1() {
	$('#wid-id-rootedit').show();
	$('#wid-id-showApplication').hide();
	$('#wid-id-receiptDataShow').hide();
	$('#wid-id-createsubconnections').hide();
	
}

function enterARFreceiptDetails() {
	$('#wid-id-rootedit').hide();
	$('#wid-id-showApplication').hide();
	$('#wid-id-receiptDataShow').show();
	$('#wid-id-createsubconnections').hide();
	
	
}

function cancelForm(){
	$('#wid-id-rootedit').hide();
	$('#wid-id-showApplication').show();
	$('#wid-id-receiptDataShow').hide();
	$('#wid-id-createsubconnections').hide();
	$('#wid-id-layouts').hide();
}

function createSubConnection() {
	$('#wid-id-rootedit').hide();
	$('#wid-id-showApplication').hide();
	$('#wid-id-receiptDataShow').hide();
	$('#wid-id-createsubconnections').show();
}


$('select[id*=circleSiteCode]').change(function() {
	var circleSiteCode = $("#circleSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllDivisions/" + circleSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',null).text(" "); 
            $('#divisionSiteCode').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"0").text("Select Division");
            $('#divisionSiteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.divisionName);
				$('#divisionSiteCode').append(newOption);
			}));
		}
	});
});


//onchange Drop Down For Division

$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subDivisionSiteCode').empty(newOption);
                var defaultOption = $('<option disabled="" selected="">');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#subDivisionSiteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.subId).text(item.subDivisionName);
					$('#subDivisionSiteCode').append(newOption);
				}));
			}
		});
	});
	
//onchange Drop Down For SubDivision
$('select[id*=subDivisionSiteCode]').change(function() {
	var subDivisionSiteCode = $("#subDivisionSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSections/" + subDivisionSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#sitecode').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select Section");
            $('#sitecode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.sectionName);
				$('#sitecode').append(newOption);
			}));
		}
	});
});
	
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			var applicanttypeedit= "${applicanttypeedit}";
			$("#plinthAreaNoEntry").hide();
			
			var nameedit= "${nameedit}";
			var fhnameedit= "${fhnameedit}";
			var nomineeedit= "${nomineeedit}";
			
			var orgedit= "${orgedit}";
			var authSigedit= "${authSigedit}";
			var designationedit= "${designationedit}";
			
			
			var hnoedit= "${hnoedit}";
			var streetnameedit= "${streetnameedit}";
			var areanameedit= "${areanameedit}";
			var citynameedit= "${citynameedit}";
			var pincodeedit= "${pincodeedit}";
			var mobilenoedit= "${mobilenoedit}";
			
			var desireloadkwedit= "${desireloadkwedit}";
			var desireloadkvaedit= "${desireloadkvaedit}";
			var desireloadhpedit= "${desireloadhpedit}";
			
			var phaseedit= "${phaseedit}";
			var ownershipedit= "${ownershipedit}";
			
			var rrno1edit= "${rrno1edit}";
			var acno1edit= "${acno1edit}";
			var rrno2edit= "${rrno2edit}";
			var acno2edit= "${acno2edit}";
			var rrno3edit= "${rrno3edit}";
			var acno3edit= "${acno3edit}";
			
			
			if(applicanttypeedit==1){
				
				$('#applicanttype').val(1);
				$('#personName').val(nameedit);
				$('#fatherName').val(fhnameedit);
				$('#nomineName').val(nomineeedit);
				
				$("#orgNameLabel").hide();
				$("#authSigNameLabel").hide();
				$("#authDesNameLabel").hide();
				
			}
			else{
				$('#applicanttype').val(2);
				$('#orgName').val(orgedit);
				$('#authSigName').val(authSigedit);
				$('#authDesName').val(designationedit);
				
				$("#orgNameLabel").show();
				$("#authSigNameLabel").show();
				$("#authDesNameLabel").show();
			}
			
			
			
			$('#hnoperm').val(hnoedit);
			$('#streetperm').val(streetnameedit);
			$('#areaperm').val(areanameedit);
			$('#cityperm').val(citynameedit);
			
			$('#pinperm').val(pincodeedit);
			$('#mobilenoperm').val(mobilenoedit);
			$('#landmarkperm').val(citynameedit);
			
			$('#loadkw').val(desireloadkwedit);
			$('#loadkva').val(desireloadkvaedit);
			$('#loadhp').val(desireloadhpedit);
			
			$('#nearbyrrnoone').val(rrno1edit);
			$('#nearbyacnoone').val(acno1edit);
			$('#nearbyrrnotwo').val(rrno2edit);
			
			$('#nearbyacnotwo').val(acno2edit);
			$('#nearbyrrnothree').val(rrno3edit);
			$('#nearbyacnothree').val(acno3edit);
			
			$('#reqphase').val(phaseedit);
			$('#ownership').val(ownershipedit);
			
			
			
			$('#wid-id-rootedit').hide();
			$('#wid-id-showApplication').show();
			$('#wid-id-receiptDataShow').hide();
			$('#wid-id-createsubconnections').hide();
			
			var tariffdescription = document.getElementsByClassName('tariffdescription')[0].innerHTML;
			
			var lt1 =tariffdescription.indexOf("LT1") > -1;
			var lt7 =tariffdescription.indexOf("LT7") > -1;
			
			if(lt1 || lt7){
				$('#receiptNoButton').hide();
			}
			else{
				$('#receiptNoButton').show();
			}
			
			$('#payTowards').val("ARF");
			
			
			$.validator.addMethod("regex", function(value, element, regexpr) {
				if(value==""){
					
					return "notmandatory";
				}else{
				return regexpr.test(value);
				}
			}, "");
			   $.validator.addMethod('minStrict', function(value, el, param) {
					return value > param;
				});
			   $.validator.addMethod("dateFormat",
					    function(value, element) {
					        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
					    },
					    "Please enter a date in the format dd/mm/yyyy.");
			
			 
			   
			   
			     $('#updateSection-form').validate({
					// Rules for form validation
						rules : {
							
							circleSiteCode : {
								required : true,
								
							},
							
							divisionSiteCode : {
								required : true,
								
								
							},
							subDivisionSiteCode : {
								required : true,
								
								
							},
							sitecode : {
								required : true
								
							}
							
						},
				
						// Messages for form validation
						messages : {
							
							circleSiteCode : {
								required : 'please select circle',
								
							},
							
							divisionSiteCode : {
								required : 'please select division',
								
							},
							subDivisionSiteCode : {
								required : 'please select subdivision',
								
							},
							sitecode : {
								required : 'please select section',
								
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			 
			 
	
			 
			 
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
								regex:'please enter valid receipt no (only numbers)',
								
							},
							
							receiptDate : {
								required : 'please select receipt date',
								
							},
							payAmount : {
								required : 'please enter amount',
								minStrict:'Amount should be > 0',
								
							},
							payTowards : {
								required : 'select Pay Towards',
								
							},
							
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			  
			  
			  	$('#updateconnection-form').validate({
					// Rules for form validation
						rules : {
							locality : {
								required : true,
								maxlength : 100
							},
							natureofinst : {
								required : true
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
							
							nearbyrrnotwo : {
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/,
								maxlength : 25
							},
							nearbyrrnothree : {
								
								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							nearbyacnoone : {

								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							nearbyacnotwo : {

								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							nearbyacnothree : {

								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							nearbyrrnoone : {
								required : true,
								maxlength : 25,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							noofconnections:{
								required : true,
								digits : true,
								minStrict : 0
							}
							
						},
				
						// Messages for form validation
						messages : {
							noofconnections:{
								minStrict : 'minimum 1 connection is required',
							},
							nearbyrrnoone : {
								required : 'Please enter RR no',
								regex : 'please enter valid RR No'
							},

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
							nearbyrrnotwo : {

								maxlength : 'maxlength is 25 for RR No',
								regex : 'please enter valid RR No'
							},

							nearbyrrnothree : {

								maxlength : 'maxlength is 25 for RR No',
								regex : 'please enter valid RR No'
							},

							nearbyacnoone : {

								maxlength : 'maxlength is 25 for AC No',
								regex : 'please enter valid AC No'
							},

							nearbyacnotwo : {

								maxlength : 'maxlength is 25 for AC No',
								regex : 'please enter valid AC No'
							},

							nearbyacnothree : {

								maxlength : 'maxlength is 25 for AC No',
								regex : 'please enter valid AC No'
							},

					        natureofinst : {

								required : 'please select nature of Installation'
							},
							locality : {
								required : 'Please select proposed installation'
							},
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
			  
			  
			  $('#updatepersonneldetails-form').validate({
					// Rules for form validation
						rules : {
							applicanttype:{
								required : true
							},
							
							name : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z .]*$/
							},
							fhname : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z .]*$/
							},
							nomineename : {

								maxlength : 100,
								regex : /^[a-zA-Z .]*$/
							},

							nameoforg : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 .]*$/
							},
							nameauthsignatory : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 .]*$/
							},
							desigauthsignatory : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 .]*$/
							},
							
							landmarkperm : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 ._]*$/
							},

							hnoperm : {
								required : true,
								maxlength : 50,
								regex : /^[a-zA-Z0-9 #._/-]*$/
							},
							
							streetperm : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
							areaperm : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z0-9 ._]*$/
							},
							
							cityperm : {
								required : true,
								maxlength : 100,
								regex : /^[a-zA-Z .]*$/
							},
							pinperm : {
								required : true,
								digits : true,
								regex : /^[0-9]{6}$/
							},
							mobilenoperm : {
								required: {
					                depends: function(element) {
					                	
					                	if($('#mobilenoperm').val()=="0000000000"){
					                		    alert("All digits cannot be Zero");
					                		    $('#mobilenoperm').val("");
					                            return false;
					                		}
					                	return true;
					                }
					            },
								digits : true,
								regex : /^[0-9]{10}$/
							},

						},
				
						// Messages for form validation
						messages : {
							
							applicanttype : {
								required : 'Please select applicant type',

							},
							
							name : {
								required : 'Please enter name',
								maxlength : 'maxlength is 100',
								regex : "please enter valid name"

							},
							fhname : {
								required : 'Please enter father name',
								maxlength : 'maxlength is 100',
								regex : "please enter valid name"
							},

							nomineename : {

								maxlength : 'maxlength is 100',
								regex : "please enter valid name"
							},

							nameoforg : {
								required : 'Please enter organization name',
								maxlength : 'maxlength is 100',
								regex : "please enter valid name"
							},
							nameauthsignatory : {
								required : 'Please enter auth. sig name',
								maxlength : 'maxlength is 100',
								regex : "please enter valid name"
							},
							desigauthsignatory : {
								required : 'Please enter designation name',
								maxlength : 'maxlength is 100',
								regex : "please enter valid name"
							},
							hnoperm : {
								required : 'Please enter house/flat no',
								regex : "Special characters not allowed except space dot(.) underscore(_) - / and #"
							},
							
							streetperm : {
								required : 'Please enter street name',
								regex : "Special characters not allowed except space dot(.) and underscore(_)"
							},
							areaperm : {
								required : 'Please enter area name',
								regex : "Special characters not allowed except space dot(.) and underscore(_)"
							},
							
							cityperm : {
								required : 'Please enter city',
								regex : 'Please enter valid city'
							},
							pinperm : {
								required : 'Please enter pin',
								regex : "Enter 6 digit pincode",
							},
							mobilenoperm : {
								required : 'Please enter mobile no',
								digits : 'Please enter only digits(10 digit No.)',
								regex : "Enter 10 digit mobile no"
							},

							landmarkperm : {
								required : 'Please enter landmark',
								regex : "Special characters not allowed except space dot(.) and underscore(_)"
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
							proposedinsttype : {
								required : true,
							},
							
							natureofinstallation : {
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
			  
			     /* var regdate1=document.getElementsByClassName('regdate')[0].innerHTML;
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
				$("#receiptDate").datepicker("option","minDate",minDate ); */
				
				
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
		
	
		    
		</script>

		