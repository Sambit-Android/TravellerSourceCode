<%@include file="/common/taglibs.jsp"%>
<c:url value="/invoicePayments/getAllBankNames" var="getAllBankNames" />
<c:url value="/registration/getState" var="stateUrl" />
<c:url value="/registration/getDist" var="distUrl" />
<c:url value="/registration/getDesignation" var="getAllDesignation" />
<c:url value="/registration/getSalaryComponents" var="getAllSalaryComponents" />
<c:url value="/registration/getLeaveType" var="getAllLeaveType" />
<c:url value="/registration/getSalaryTemplete" var="getAllSalaryTemplete" />
<c:url value="/projecttree/map/read" var="transportReadUrlCat" />
<c:url value="/projecttree/getonlyoffices" var="readUrl" />

					<!-- MAIN PANEL -->
		<div id="main" role="main">
		
			<!-- MAIN CONTENT -->
			<div id="content" style="width: 1500px;">
								
				<!-- widget grid -->
				<section id="widget-grid" class="">
				
					<!-- row -->
					<div class="row">
				
						<!-- NEW WIDGET START -->
						<article class="col-sm-12 col-md-12 col-lg-6">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false" data-widget-deletebutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
								
									<span class="widget-icon"> <i class="fa fa-check"></i> </span>
									<h2>Customer Application Status</h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
				
										<div class="row">
											<form id="wizard-1" action="./employeeRegistration1" method="post" enctype="multipart/form-data" novalidate="novalidate" autocomplete="off">
												
												<div id="bootstrap-wizard-1" class="col-sm-12">
													<div class="form-bootstrapWizard">
														<ul class="bootstrapWizard form-wizard">
															<li class="active" data-target="#step1" id="tab11">
																<a href="#tab1" data-toggle="tab"> <span class="step">1</span> <span class="title">Application Detail</span> </a>
															</li>
															<li data-target="#step2" id="tab22">
																<a href="#tab2" data-toggle="tab"> <span class="step">2</span> <span class="title">FIeld Verification Detail</span> </a>
															</li>
															<li data-target="#step3" id="tab33">
																<a href="#tab3" data-toggle="tab"> <span class="step">3</span> <span class="title">Power Sanction</span> </a>
															</li>
															<li data-target="#step4" id="tab44">
																<a href="#tab4" data-toggle="tab"> <span class="step">4</span> <span class="title">Estimation Detail</span> </a>
															</li>
															 <li data-target="#step5" id="tab55">
																<a href="#tab5" data-toggle="tab"> <span class="step">5</span> <span class="title">Deposit/Wiring Completion Detail</span> </a>
															</li>
															<li data-target="#step6" id="tab66">
																<a href="#tab6" data-toggle="tab"> <span class="step">6</span> <span class="title">Work Order</span> </a>
															</li>
															<li data-target="#step7" id="tab77">
																<a href="#tab7" data-toggle="tab"> <span class="step">7</span> <span class="title">Meter/PC Test</span> </a>
															</li>
															<li data-target="#step8" id="tab88">
																<a href="#tab8" data-toggle="tab"> <span class="step">8</span> <span class="title">Estimation WCR </span> </a>
															</li>
															<li data-target="#step9" id="tab99" >
																<a href="#tab9" data-toggle="tab"> <span class="step">9</span> <span class="title">Installation Service </span> </a>
															</li> 
														</ul>
														
														<div class="clearfix"></div>
													</div>
													<div class="tab-content">
														<div class="tab-pane active" id="tab1">
															<br>
															<h3>Application Detail</h3>
				
															<!-- -->
				
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-user" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="Employee Name" type="text" name="empName" id="empName" style="height: 37px; font-size: 13px;" data-original-title="Employee Name" rel="tooltip">
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-user" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="Father's Name" type="text" name="empFatherName" id="empFatherName" style="height: 37px; font-size: 13px;" data-original-title="Employee Father Name" rel="tooltip" >
																		</div>
																	</div>
																</div>
															</div>
				                                          <div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-calendar" class="fa fa-user" style="height: 18px; font-size: 13px;" ></i></span>
																			<input class="form-control input-lg" type="text" name="empDateOfBirth" id="empDateOfBirth" placeholder="Date Of Birth" style="height: 37px; font-size: 13px;" >
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-calendar" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" type="text" name="empDateOfjoining" id="empDateOfjoining" placeholder="Date Of Joining" style="height: 37px; font-size: 13px;" >
																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa fa-smile-o" style="height: 18px; font-size: 13px;" data-original-title="Martial Status" rel="tooltip" ></i></span>
																			<select name="martialStatus" id="martialStatus" class="form-control input-lg" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value=""  selected="selected">Select Martial Status</option>
																				<option value="Single">Single</option>
																				<option value="Married">Married</option>
				                                                                  </select>
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-circle-thin" style="height: 18px; font-size: 13px;" data-original-title="Gender" rel="tooltip"></i></span>
																			<select name="gender" id="gender" class="form-control input-lg" style="height: 37px; font-size: 13px; padding-top: 6px;">
																				<option value=""  selected="selected">Gender</option>
																				<option value="Male">Male</option>
																				<option value="Female">Female</option>
				                                                                  </select>
																		</div>
																	</div>
																</div>
															</div>
																<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-plus-square" style="height: 18px; font-size: 13px;" data-original-title="Blood Group" rel="tooltip"></i></span>
																			<select class="form-control input-lg"  name="bloodgroup" id="bloodgroup"  style="height: 37px; font-size: 13px; padding-top: 6px;">
																			<option value=""  selected="selected">Blood Group</option>
																				<option value="A+">A+</option>
																				<option value="B+">B+</option>
																				<option value="AB+">AB+</option>
																				<option value="AB-">AB-</option>
																				<option value="O+">O+</option>
																				<option value="O-">O-</option>
				                                                                  </select>
																		</div>
																	</div>
																</div>																
															</div>
														</div><!--END OF TAB1  -->
														<div class="tab-pane" id="tab2">
															<br>
															<h3>Field Veification Detail</h3>
				
				                                            <div class="row">
				
																<div class="col-sm-12">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-lg fa-fw fa-home" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="Address" type="text" name="address" id="address" style="height: 37px; font-size: 13px;" data-original-title="Address" rel="tooltip">
																		</div>
																	</div>
				
																</div>
				
															</div> 
															<div class="row">
															
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-flag fa-lg fa-fw" style="height: 18px; font-size: 13px;" data-original-title="State" rel="tooltip"></i></span>
																			<select name="stateName" id="stateName" class="form-control input-lg" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">State Name</option>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-map-marker fa-lg fa-fw" style="height: 18px; font-size: 13px;" data-original-title="City" rel="tooltip"></i></span>
																			<select class="form-control input-lg" name="distName" id="distName" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Select City</option>												
																			</select>
																		</div>
																	</div>
																</div>																
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-envelope-o fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" data-mask="999999" data-mask-placeholder= "X" placeholder="Postal Code" type="text" name="pinCode" id="pinCode" style="height: 37px; font-size: 13px;" data-original-title="Postal Code" rel="tooltip">
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-mobile fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" data-mask="9999999999" data-mask-placeholder= "X" placeholder="Mobile Number" type="text" name="mobileNo" id="mobileNo" style="height: 37px; font-size: 13px;" data-original-title="Mobile No." rel="tooltip">
																		</div>
																	</div>
																</div>
															</div>
															 <div class="row">				
																<div class="col-sm-12">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-envelope fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="email@address.com" type="text" name="email" id="email" style="height: 37px; font-size: 13px;" data-original-title="Email Address" rel="tooltip">
				
																		</div>
																	</div>				
																</div>
				
															</div>
														</div><!--END OF TAB 2  -->
														<div class="tab-pane" id="tab3">
														<br>
															<h3>Power Sanction</h3>
							                            <div class="row">
							                            <div class="col-sm-8">
							                            	<div class="row">
																<div class="col-sm-9">
																	<div class="form-group">
																		<div class="input-group">																	
					                                   <span class="input-group-addon"><i class="fa fa-university" style="height: 18px; font-size: 13px;" data-original-title="Office" rel="tooltip"></i></span>
					                                         <input class="form-control input-lg" type="text" id="officeId" name="officeId" placeholder="Select office" list="myofficeId" style="height: 37px; font-size: 13px;"  />
                                                               <datalist id="myofficeId"></datalist>				
																		</div>
																	</div>				
																</div>																
																</div>
							                                
							                                  <div class="row">
				                                                <div class="col-sm-9">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-delicious" style="height: 18px; font-size: 13px;" data-original-title="Type Of Employment" rel="tooltip"></i></span>
																			<select class="form-control input-lg" name="typeOfEmployment" id="typeOfEmployment" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Employment Type</option>
																				<option>Temporary</option>
																				<option>Permanent</option>																
																			</select>
																		</div>
																	</div>
																</div>			
				                                                
				                                                </div>
							                                <div class="row">
				                                                <div class="col-sm-9">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-level-up" style="height: 18px; font-size: 13px;" data-original-title="Employee Designation" rel="tooltip"></i></span>
																			<select class="form-control input-lg" name="designation" id="designation" style="height: 37px; font-size: 13px;padding-top: 6px;" >
																				<option value="" selected="selected">Employee Designation</option>
																																				
																			</select>
																		</div>
																	</div>
																</div>			
				                                                
				                                                </div>
				                                                <div class="row">
				                                                <div class="col-sm-9">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa  fa-square  fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg"  placeholder="Passport No" type="text" name="passportNo" id="passportNo" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="Passport No." rel="tooltip">
																		</div>
																	</div>
																</div>
				                                                
				                                                </div>
							                            </div>
							                            			<div class="col-sm-4">
																	<div class="form-group">
																		<div class="input-group">
																			
																		<div id="imagecontainer">
																		<img id="blah" src="./resources/img/person.jpg" alt="your image" />
																		</div>	
                                                                       
                                                                       <input type='file' id="imgInp"  name="file" />
                                                                      
																		</div>
																	</div>
																</div>								                            
							                            </div>	
							                            <div class="row">							                            
							                            <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-sort-numeric-asc" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="Pan no" type="text" name="panNo" id="panNo" style="height: 37px; font-size: 13px;" data-original-title="Pan No." rel="tooltip" pattern="[a-zA-Z0-9\s]*">
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-sort-numeric-desc" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg"  placeholder="No Of Paid Days" type="text" name="noOfPaidDays" id="noOfPaidDays" style="height: 37px; font-size: 13px;" data-original-title="No. Of Paid Day's" rel="tooltip">
																		</div>
																	</div>
																</div>
							                            </div>
							                             <div class="row">							                            
							                            <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-file-text-o" style="height: 18px; font-size: 13px;" data-original-title="Salary Template" rel="tooltip"></i></span>
																			<select class="form-control input-lg"  name="salaryTemplateName"   id="salaryTemplateName" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="Select Salary Template">
																		   <option value="" selected="selected">Select Salary Template</option>
																		</select>
																		</div>
																	</div>
																</div>
																 <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-file-text-o" style="height: 18px; font-size: 13px;" data-original-title="Bonus Type" rel="tooltip"></i></span>
																			<select class="form-control input-lg"  name="bonusType" id="bonusType" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="Bonus Type">
																		   <option value="" selected="selected">Select Bonus Type</option>
																		   <option>Yearly</option>
																		   <option>Monthly</option>	
																		</select>
																		</div>
																	</div>
																</div>
							                            </div>						                               
														</div>
														<div class="tab-pane" id="tab4">
															<br>
															<h3>Estimation Detail</h3>
															<div class="row">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-dollar" style="height: 18px; font-size: 13px;" data-original-title="Salary Template" rel="tooltip"></i></span>
																			<select class="form-control input-lg" name="modeOfPay" id="modeOfPay" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Mode Of Pay</option>
																				<option>Cash</option>
																				<option>Cheque</option>
																				<option>Account Transfer</option>																
																				</select>
																		</div>
																	</div>
																</div>			
				                                                <div class="col-sm-6" style="display: none;" id="bankNameDiv">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-bank" style="height: 18px; font-size: 13px;" data-original-title="Bank Name" rel="tooltip"></i></span>
																			<select class="form-control input-lg" name="bankName" id="bankName" style="height: 37px; font-size: 13px; padding-top: 6px;">
																				<option value="" selected="selected">Bank Name</option>
																																				
																			</select>
																		</div>
																	</div>
																</div>	
				                                                </div>
				                                                <div class="row" id="branchNameDiv" style="display: none">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-building" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" name="branchName" id="brachName" placeholder="branchName" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="Branch Name" rel="tooltip">																																	
																			</div>
																	</div>
																</div>			
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-sort-numeric-asc" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" name="bankAccountNo" id="bankAccountNo" placeholder="bank Account No" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="Bank Account No." rel="tooltip">																			
																		</div>
																	</div>
																</div>	
				                                                </div>
				                                                <div class="row" id="ifscDiv" style="display:none">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-code" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="IFSC Code" name="ifscCode" id="ifscCode" style="height: 37px; font-size: 13px;padding-top: 6px;" data-original-title="IFSC CODE" rel="tooltip">																
																		</div>
																	</div>
																</div>
				                                                </div>
														</div>
				                                        <div class="tab-pane" id="tab5">
															<br>
															<h3>Deposit Detail/Wiring Completion Detail</h3>
															<div class="row">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-codepen" style="height: 18px; font-size: 13px;" data-original-title="PF Enrolled" rel="tooltip"></i></span>
																			<select class="form-control input-lg"  name="pfEnrolled" id="pfEnrolled" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">PF Enrolled</option>
																				<option>Yes</option>
																				<option>No</option>																																				
																			</select>																																	
																			</div>
																	</div>
																</div>			
				                                                <div class="col-sm-6" style="display: none;" id="pfDiv">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-map-marker fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" name="pfAcoNo" id="pfAcoNo" placeholder="PF Account NO" style="height: 37px; font-size: 13px;">																			
																		</div>
																	</div>
																</div>	
				                                                </div>
				                                                <div class="row" style="display: none;" id="pvpfDiv">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-map-marker fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" placeholder="Previous PF No" name="prevoiosPfNo" id="prevoiosPfNo" style="height: 37px; font-size: 13px;">																
																										
																		</div>
																	</div>
																</div>
				                                                </div>
														</div>
														<div class="tab-pane" id="tab6">
															<br>
															<h3>Work Order</h3>
															<div class="row">
				                                                <div class="col-sm-12">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-check" style="height: 18px; font-size: 13px;"></i></span>
																			<select class="form-control input-lg"  name="insuranceApplicable" id="insuranceApplicable" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Insurance Applicable</option>
																				<option>Yes</option>
																				<option>No</option>																																				
																			</select>																																	
																			</div>
																	</div>
																</div>
				                                                </div>
				                                                <div class="row" style="display:none" id="insTypeDiv">
				                                                <div class="col-sm-12">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-medkit" style="height: 18px; font-size: 13px;"></i></span>
																			<select class="form-control input-lg"  name="insuranceType" id="insuranceType" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Insurance Type</option>
																				<option>ESI</option>
																				<option>Mediclaim</option>																																				
																			</select>																																	
																			</div>
																	</div>
																</div>
				                                                </div>
				                                                <div class="row" style="display: none" id="esiDiv">
				                                                 <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa  fa-briefcase  fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg"  name="esiNo" id="esiNo" placeholder="ESI NO" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				
																		</div>
																	</div>
																</div>
				                                                   <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-check-square" style="height: 18px; font-size: 13px;"></i></span>
																			<select class="form-control input-lg"  name="biometricType" id="biometricType" style="height: 37px; font-size: 13px;padding-top: 6px;">
																				<option value="" selected="selected">Biometric Done</option>
																				<option>Yes</option>
																				<option>No</option>																																				
																			</select>
																		</div>
																	</div>
																</div>	
				                                                </div>
				                                                <div class="row" id="mediclaimDiv" style="display: none">
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-building" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg"  name="insuranceCompanyName" id="insuranceCompanyName" placeholder="Insurance Company Name" style="height: 37px; font-size: 13px;">
																																																					
																			</div>
																	</div>
																</div>			
				                                                <div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa  fa-briefcase  fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg"  name="insurancePolicyNo" id="insurancePolicyNo" placeholder="Policy No." style="height: 37px; font-size: 13px;">
																				
																		</div>
																	</div>
																</div>	
				                                                </div>
				                                                 <div class="row" style="display: none;" id="timeDiv">
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-calendar" class="fa fa-user" style="height: 18px; font-size: 13px;" ></i></span>
																			<input class="form-control input-lg" type="text" name="validFrom" id="validFrom" placeholder="Valid From" style="height: 37px; font-size: 13px;">
				
																		</div>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-calendar" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" type="text" name="validTo" id="validTo" placeholder="Valid To" style="height: 37px; font-size: 13px;">
				
																		</div>
																	</div>
																</div>
															</div>
															<div class="row"  style="display:none" id="amountDiv">
															<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="icon-append fa fa-rupee" style="height: 18px; font-size: 13px;"></i></span>
																			<input class="form-control input-lg" type="text" name="premiumAmount" id="premiumAmount" placeholder="Premium Amount" style="height: 37px; font-size: 13px;">
				
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab7">
															<br>
															<h3>Meter Purchase Order</h3>
															<div class="row">
															<div id="leaveDiv1" class="col-sm-6">
																	<!-- <div class="form-group">
																		<div  class="input-group">												
																		</div>
																	</div> -->
																</div>
																<div id="leaveDiv2"  class="col-sm-6">
																	<!-- <div class="form-group">
																		<div class="input-group">												
																		</div>
																	</div> -->
																</div>
															</div>
														<div  class="row">
															<div id="leaveDiv3" class="col-sm-6">
																	<!-- <div class="form-group">
																		<div id="leaveDiv3" class="input-group">												
																		</div>
																	</div> -->
																</div>
																<div id="leaveDiv4" class="col-sm-6">
																	<!-- <div  class="form-group">
																		<div  class="input-group">												
																		</div>
																	</div> -->
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab8">
															<br>
															<h3>Estimation Work Completion Detail</h3>
													<div class="alert alert-info fade in">
																<button class="close" data-dismiss="alert">
																	×
																</button>
																<i class="fa-fw fa fa-info"></i>
																<strong>Info!</strong> Browse to Upload Employee Document
															</div>
													<div class="row">
															<div class="col-sm-6">
																	<div class="form-group">
																		<div class="input-group">
																			<span class="input-group-addon"><i class="fa fa-folder-open-o fa-lg fa-fw" style="height: 18px; font-size: 13px;"></i></span>
																		<span class="button" style=" ">	<input   type="file" name="file" id="file"  style="height: 37px; font-size: 13px; color:blue;" multiple="multiple"></span>
				
																		</div>
																	</div>
																</div>
															</div>
													
												</div>
														<div class="tab-pane" id="tab9">
															<br>
															<h3>Installation Service</h3>
															<div class="row">
															<div class="col-sm-4">
																	<div class="form-group">
																	<div class="input-group">
																		<span class="saveTab">Basic Information
																		<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-tabs1" readonly="readonly">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>
                                              </span>
													</div>
														</div>
														</div>
														<div class="col-sm-4">
														<div class="input-group">
														<div class="input-group">
													<span class="saveTab">Address  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs2">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
				                               	</div>
				                               	<div class="col-sm-4">
																	<div class="form-group">
																	<div class="input-group">
																		<span class="saveTab">Employment Details
																		<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-tabs3" readonly="readonly">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>
                                              </span>
													</div>
														</div>
														</div>
												</div>
												<div class="row">												
														<div class="col-sm-4">
														<div class="form-group">
														<div class="input-group">
													<span class="saveTab">Payment Details  &nbsp;&nbsp;  	
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs4">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
												</div>
												<div class="col-sm-4">
														<div class="form-group">
														<div class="input-group">
													<span class="saveTab">PF Details  &nbsp;&nbsp;&nbsp;   	
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs5">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
												</div>
												<div class="col-sm-4">
														<div class="form-group">
														<div class="input-group">
													<span class="saveTab">Insurance &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  	
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs6">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
												</div>
												</div>
														<div class="row">												
														<div class="col-sm-4">
														<div class="form-group">
														<div class="input-group">
													<span class="saveTab">Leave Details  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 	
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs7">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
												</div>
												<div class="col-sm-4">
														<div class="form-group">
														<div class="input-group">
													<span class="saveTab">Document  &nbsp;&nbsp;   	
													<span class="onoffswitch">
                                                 <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" readonly="readonly" id="show-tabs8">
                                          <label class="onoffswitch-label" for="show-tabs"> 
                                           <span class="onoffswitch-inner" data-swchon-text="True" data-swchoff-text="NO"></span> 
                                           <span class="onoffswitch-switch"></span> 
                                         </label> 
                                              </span>	
                                              </span>			
				                               </div>
				                               	</div>
												</div>											
												</div>
													<input type="submit" value="Submit" class="btn btn-info btn-lg" />
														</div>
														<div class="form-actions">
															<div class="row">
																<div class="col-sm-20">
																	<ul class="pager wizard no-margin">
																		<!--<li class="previous first disabled">
																		<a href="javascript:void(0);" class="btn btn-lg btn-default"> First </a>
																		</li>-->
																		<li class="previous disabled">
																			<a href="javascript:void(0);" class="btn btn-lg btn-default"> Previous </a>
																		</li>
																		<!--<li class="next last">
																		<a href="javascript:void(0);" class="btn btn-lg btn-primary"> Last </a>
																		</li>-->
																		<li class="next">
																			<a id="nextButton" href="javascript:void(0);"  class="btn btn-lg txt-color-darken"> Next </a>
																		</li>
																	</ul>
																</div>
															</div>
														</div>
				
													</div>
												</div>
											</form>
										</div>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
						</article>
						<!-- WIDGET END -->
													
				</section>
				<!-- end widget grid -->

			</div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->
		
		<div id="salaryTempDiv">
		
		
		</div>
		<div id="salTempDiv" style="display: none;"></div>
		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<!-- <script data-pace-options='{ "restartOnRequestAfter": true }' src="./resources/js/plugin/pace/pace.min.js"></script>
 -->
		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script> -->
		<!-- <script>
			if (!window.jQuery) {
				document.write('<script src="./resources/js/libs/jquery-2.0.2.min.js"><\/script>');
			}
		</script> -->
<!-- 
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="./resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script> -->

		<!-- IMPORTANT: APP CONFIG -->
	<!-- 	<script src="./resources/js/app.config.js"></script> -->

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
		<!-- <script src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>  -->

		<!-- BOOTSTRAP JS -->
		<script src="./resources/js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<!-- <script src="./resources/js/notification/SmartNotification.min.js"></script>
 -->
		<!-- JARVIS WIDGETS -->
		<!-- <script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script> -->

		<!-- SPARKLINES -->
		<!-- <script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script> -->

		<!-- JQUERY VALIDATE -->
		<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<!-- <script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
            <script type="text/javascript" src="http://cdn.rawgit.com/tapmodo/Jcrop/master/js/jquery.Jcrop.min.js"></script> -->
	
		<!--[if IE 8]>

		<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

		<![endif]-->


		<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
		<!-- Voice command : plugin -->
		<!-- <script src="./resources/js/speech/voicecommand.min.js"></script> -->

		<!-- PAGE RELATED PLUGIN(S) -->
		<script src="./resources/js/plugin/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
		<script src="./resources/js/plugin/fuelux/wizard/wizard.min.js"></script>
		<script src="./resources/js/plugin/dropzone/dropzone.min.js"></script>
      <!--   <script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>  -->
       <!--  <script type="text/javascript">
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

		</script> -->
		<script type="text/javascript">
		$.ajaxSetup({cache: false});
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		var officeId="";
		var i=0;
		$(document).ready(function() {
			pageSetUp();
			
			
			Dropzone.autoDiscover = false;
			$("#mydropzone").dropzone({
				//url: "/file/post",
				addRemoveLinks : true,
				maxFilesize: 0.5,
				dictResponseError: 'Error uploading file!'
			});
			
			
			
		        $("#officeId").autocomplete({
		            minlength: 1,
		            source: function (request, response) {
		                $.ajax({
		                    url: "./projecttree/getalloffices",
		                    	/* "./projecttree/getonlyoffices", */
		                    dataType: "json",
		                    success: function (data) {
		                    	var dataList = $("#myofficeId");
		                    	dataList.empty(); 
		                    	
		                    	response($.map(data, function(item) {		                    		
		                    		var opt = $("<option></option>").attr("value", item.text);
		                    		officeId=item.id;
		                    		dataList.append(opt);
								}));
		                    	
		                    }
		                });
		            },
		        });
		        
		       
		        $.ajax({
	                type: "GET",
	                contentType: "application/json; charset=utf-8",
	                url: "./employeeMaster/getAllBankNames",
	                data: "{}",
	                dataType: "Json",
	                success: function (data) {
	                	($.map(data, function(item) {	
	                		 var newOption = $('<option>');
		                        newOption.attr('value',item.bankName).text(item.bankName);
		                        $('#bankName').append(newOption);
						}));
	                }
	            });
		        $.ajax({
	                type: "POST",
	                contentType: "application/json; charset=utf-8",
	                url: "./registration/getState",
	                data: "{}",
	                dataType: "Json",
	                success: function (data) {
	                	($.map(data, function(item) {	
	                		//alert(item.stateId);
	                		 var newOption = $('<option>');
		                        newOption.attr('value',item.stateId).text(item.stateName);
		                        
		                        $('#stateName').append(newOption);
						}));
	                }
	            });
		      
		        $.ajax({
	                type: "POST",
	                contentType: "application/json; charset=utf-8",
	                url: "./registration/getDesignation",
	                data: "{}",
	                dataType: "Json",
	                success: function (data) {
	                	($.map(data, function(item) {	
	                		 
	                		 var newOption = $('<option>');
		                        newOption.attr('value',item.dnId).text(item.dnName);
		                        
		                        $('#designation').append(newOption);
						}));
	                }
	            });
		        $.ajax({
	                type: "POST",
	                contentType: "application/json; charset=utf-8",
	                url: "./registration/getLeaveType",
	                data: "{}",
	                dataType: "Json",
	                success: function (data) {
	                		($.map(data, function(item) {	
	                			
	                			if(i==0 && i<=data.length)
	                				{
	                				$("#leaveDiv1").append("<div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='icon-append fa fa-check' style='height: 18px; font-size: 13px;'></i></span><input class='form-control input-lg' type='text' name='leaveName"+item.leaveId+"' id='leaveName"+item.leaveId+"' placeholder='"+item.leaveName+"' style='height: 37px; font-size: 13px;'></div></div>");
	                				}
	                			
	                			if(i==1 && i<=data.length)
                				{
                				$("#leaveDiv2").append("<div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='icon-append fa fa-check' style='height: 18px; font-size: 13px;'></i></span><input class='form-control input-lg' type='text' name='leaveName"+item.leaveId+"' id='leaveName"+item.leaveId+"' placeholder='"+item.leaveName+"' style='height: 37px; font-size: 13px;'></div></div>");
                				}
	                			
	                			if(i==2 && i<=data.length)
                				{
	                				$("#leaveDiv3").append("<div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='icon-append fa fa-check' style='height: 18px; font-size: 13px;'></i></span><input class='form-control input-lg' type='text' name='leaveName"+item.leaveId+"' id='leaveName"+item.leaveId+"' placeholder='"+item.leaveName+"' style='height: 37px; font-size: 13px;'></div></div>");	
                				}
	                			
	                			if(i==3 && i<=data.length)
                				{
	                				$("#leaveDiv4").append("<div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='icon-append fa fa-check' style='height: 18px; font-size: 13px;'></i></span><input class='form-control input-lg' type='text' name='leaveName"+item.leaveId+"' id='leaveName"+item.leaveId+"' placeholder='"+item.leaveName+"' style='height: 37px; font-size: 13px;'></div></div>");	
                				}
		                		i=++i;
						}));
	                }
	            });
	 
$.validator.addMethod("specialCharValidation", function(value, element) {
	 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
}, "Only letters, numbers allowed");

jQuery.validator.addMethod("lettersonly", function(value, element) {
	  return this.optional(element) || /^[a-z ]+$/i.test(value);
	}, "Letters only please");


			  var $validator = $("#wizard-1").validate({
			    
			    rules: {
			      email: {
			        required: true,
			        email: "Your email address must be in the format of name@domain.com"
			      },
			      empName: {
			        required: true,
			        lettersonly:true,
			      },
			      empFatherName: {
			        required: true,
			        lettersonly:true,
			      },
			      stateName: {
			        required: true,
			      },
			      distName: {
			        required: true,
			      },
			      pinCode: {
			        required: true,
			        minlength: 6,
			        maxlength:6,
			        digits : 'Digits only',
			      },
			      wphone: {
			        required: true,
			        minlength: 10,
			      },
			      mobileNo: {
			        required: true,
			        minlength: 10,
			    	maxlength:10,
			    	digits : 'Digits only',
			      },
			      address: {
				        required: true,
				        minlength: 10,
				      },
			      noOfPaidDays:{
			    	  required: true,
				        minlength: 2,
				        digits : 'Digits only',
				        max:31,
			      },
				     panNo:{
				    	  required:true,
				    	  minlength:10,
				    	  maxlength:10,
				    	  specialCharValidation:true,
				      },
				      
				      officeId:{
				    	  required: true, 
				      },
				      typeOfEmployment:{
				    	  required: true, 
				      },
				      designation:{
				    	  required: true, 
				      },
				      salaryTemplateName:{
				    	  required: true, 
				      },
				      insuranceApplicable:{
				    	  required: true,
				      },
				      pfEnrolled:{
				    	  required: true,
				      },
				      empDateOfBirth:{
				    	  required: true,  
				      },
				      empDateOfBirth:{
				    	  required: true,  
				      },
				      empDateOfjoining:{
				    	  required: true,  
				      },
				      martialStatus:{
				    	  required: true,  
				      },
				      gender:{
				    	  required: true,  
				      },
				      bloodgroup:{
				    	  required: true,  
				      },
				     modeOfPay:{
				    	  required: true,  
				      },
				      bonusType:{
				    	  required: true,  
				      },
				    bankName:{
				    	required: {
			    	        depends: function(element){
			    	            return $("#modeOfPay").val()=="Account Transfer";
			    	        }
			    	    },
				    	        maxlength:45,
				            }, 
				   branchName:{
					   required: {
			    	        depends: function(element){
			    	            return $("#modeOfPay").val()=="Account Transfer";
			    	        }
			    	    },
					    	    
					    	    maxlength:45,
					    	    specialCharValidation:true,
					            },
					bankAccountNo:{
						required: {
			    	        depends: function(element){
			    	            return $("#modeOfPay").val()=="Account Transfer";
			    	        }
			    	    },
							    maxlength:16,
							    minlength:6,
							    digits : 'Digits only',
							  },		            
						ifscCode:{
							required: {
				    	        depends: function(element){
				    	            return $("#modeOfPay").val()=="Account Transfer";
				    	        }
				    	    },
									 maxlength:13,
									 minlength:4,
									 specialCharValidation:true,
								},
						pfAcoNo:{
							required: {
				    	        depends: function(element){
				    	            return $("#pfEnrolled").val()=="Yes";
				    	        }
				    	    },
									maxlength:45,
									specialCharValidation:true,
									minlength:6,
								},
						prevoiosPfNo:{
									specialCharValidation:true,
									maxlength:45,
									minlength:6,
								},
								
						esiNo:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="ESI";
				    	        }
				    	    },
									maxlength:45,
									minlength:6,
									digits : 'Digits only',
								},
						biometricType:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="ESI";
				    	        }
				    	    },
									
								},
						insuranceCompanyName:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="Mediclaim";
				    	        }
				    	    },
									maxlength:45,
									specialCharValidation:true,
									minlength:3,
								},
						insurancePolicyNo:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="Mediclaim";
				    	        }
				    	    },
									maxlength:45,
									specialCharValidation:true,
									minlength:3,
								},
						validFrom:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="Mediclaim";
				    	        }
				    	    },
								},
						validTo:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="Mediclaim";
				    	        }
				    	    },
								},
						premiumAmount:{
							required: {
				    	        depends: function(element){
				    	            return $("#insuranceType").val()=="Mediclaim";
				    	        }
				    	    },
									maxlength:10,
								    minlength:1,
								    digits : 'Digits only',
								},	
								
			    },
			    			    
			    messages: {
			    	empName:{
			    		required:"Please specify employee name",
			    		lettersonly:"Only letters allowed",
			    	},
			    empFatherName:{
			    		required:"Please specify employee father's  name",
			    		lettersonly:"Only letters allowed",
			    	},
			      email: {
			        required: "We need your email address to contact you",
			        email: "Your email address must be in the format of name@domain.com",			       
			      },
			      address:"Please specify employee address",
			      noOfPaidDays:{
			    	  required:"No of paid days is required",
			    	  max:"Max No.  Of paid days is 31"
			      },
			      stateName: {
				        required: "Please specify state name",
				      },
				      
				distName: {
					  required: "Please specify district name",
					    },
				 pinCode: {
					    required: "Please specify pin code",
					    minlength: "Minimum 6 characters allowed",
					    maxlength:"Maximum 6 characters allowed",
					    digits : 'Only number are allowed',
					  },				    
			      panNo:{
			    	  required:"Please specify pan card number",
			    	  minlength:"Minimum 10 characters allowed",
			    	  maxlength:"Maximum 10 characters allowed",
			    	  specialCharValidation:"Special characters not allowed",
			      },
			      mobileNo: {
				        required: "Please specify mobile number",
				        minlength:"Minimum 10 characters allowed",
				    	maxlength:"Maximum 10 characters allowed",
				    	digits : 'Only number are allowed',
				      },
				 modeOfPay :{
					 required: "Please specify mode of pay",
				 },
				 bankName :{
					 required: "Please specify bank name",
					 maxlength:"Maximum 45 characters allowed",
				 },
				 branchName :{
					 required: "Please specify branch name",
					 maxlength:"Maximum 45 characters allowed",
					 specialCharValidation:"Special characters not allowed",
				 },
				 bankAccountNo :{
					 required: "Please specify bank account number",
					 maxlength:"Maximum 16 characters allowed",
					 minlength: "Minimum 6 characters allowed",
					 digits : 'Only number are allowed',
				 },
				 ifscCode :{
					 required: "Please specify bank IFSC code",
					 maxlength:"Maximum 13 characters allowed",
					 minlength: "Minimum 4 characters allowed",
					 specialCharValidation:"Special characters not allowed",
				 },
				 pfAcoNo :{
					 required: "Please specify PF account number",
					 maxlength:"Maximum 45 characters allowed",
					 minlength: "Minimum 6 characters allowed",
					 specialCharValidation:"Special characters not allowed",
				 },
				 
			prevoiosPfNo :{
					 maxlength:"Maximum 45 characters allowed",
					 minlength: "Minimum 6 characters allowed",
					 specialCharValidation:"Special characters not allowed",
				 },
			esiNo :{
					 required: "Please specify ESI number",
					 maxlength:"Maximum 45 characters allowed",
					 minlength: "Minimum 6 characters allowed",
					 digits : 'Only number are allowed',
					 specialCharValidation:"Special characters not allowed",
				 },
			biometricType :{
					 required: "Please specify biometric done",
				 },	 
			 insuranceCompanyName :{
					 required: "Please specify insurance company name",
					 maxlength:"Maximum 45 characters allowed",
					 minlength: "Minimum 3 characters allowed",
					 specialCharValidation:"Special characters not allowed",
				 },	
			insurancePolicyNo :{
					 required: "Please specify insurance policy number",
					 maxlength:"Maximum 45 characters allowed",
					 minlength: "Minimum 3 characters allowed",
					 specialCharValidation:"Special characters not allowed"
				 },
			 validFrom :{
					 required: "Please specify insurance valid from",
				 },
			 validTo :{
					 required: "Please specify insurance valid to",
				 },
				premiumAmount :{
					 required: "Please specify insurance premium amount",
					 maxlength:"Maximum 10 characters allowed",
					 minlength: "Minimum 1 characters allowed",
					 digits : 'Only number are allowed',
				 },
				 bonusType: {
				        required: "Please specify Bonus Type",
				      },		     
			      officeId: "Please specify the office name",
			      typeOfEmployment: "Please select type of employment",
			      designation: "Please Select designation",
			      salaryTemplateName: "Please select salary template",
			      insuranceApplicable: "Please specify insurance applicable or not",
			      pfEnrolled:"Please specify PF enrolled or not",			      
			      empDateOfBirth:"Please specify employee date of birth",
			      empDateOfjoining:"Please specify Employee date of joining",
			      martialStatus:"Please specify martial status",
			      gender:"Please specify employee gender",
			      bloodgroup:"Please specify Employee blood group"	  
			    },
			    
			    highlight: function (element) {
			      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			    },
			    unhighlight: function (element) {
			      $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			    },
			    errorElement: 'span',
			    errorClass: 'help-block',
			    errorPlacement: function (error, element) {
			      if (element.parent('.input-group').length) {
			        error.insertAfter(element.parent());
			      } else {
			        error.insertAfter(element);
			      }
			    }
			  });
			  
			  $('#bootstrap-wizard-1').bootstrapWizard({
			    'tabClass': 'form-wizard',
			    'onNext': function (tab, navigation, index) {
			      var $valid = $("#wizard-1").valid();
			      if (!$valid) {
			        $validator.focusInvalid();
			        return false;
			      } else {
			        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
			          'complete');
			        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
			        .html('<i class="fa fa-check"></i>');
			      }
			    }
			  });
			  
		
			// fuelux wizard
			  var wizard = $('.wizard').wizard();
			  
			  wizard.on('finished', function (e, data) {
			    //$("#fuelux-wizard").submit();
			    //console.log("submitted!");
			    $.smallBox({
			      title: "Congratulations! Your form was submitted",
			      content: "<i class='fa fa-clock-o'></i> <i>1 seconds ago...</i>",
			      color: "#5F895F",
			      iconSmall: "fa fa-check bounce animated",
			      timeout: 4000
			    });
			    
			  });
		});
		 
		var d = new Date();
		d.setMonth(d.getMonth() - 180);
		
		$('#empDateOfBirth').datepicker({
			dateFormat : 'dd/mm/yy',
			maxDate: d,
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
			var	dateOfJoin = new Date(selectedDate);
			dateOfJoin.setMonth(dateOfJoin.getMonth() + 168);
			$("#empDateOfjoining").datepicker("option", "minDate", dateOfJoin);
			}
		});
		
		$('#empDateOfjoining').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
			}
		});
		
		$('#validFrom').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				 $("#validTo").datepicker("option","minDate", selectedDate);
			}
		});
		
		$('#validTo').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				 $("#validFrom").datepicker("option","maxDate", selectedDate)
			}
		});
		 function readURL(input) {
		        if (input.files && input.files[0]) {
		            var reader = new FileReader();
		            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
		            if (regex.test(input.files[0].name.toLowerCase())) {
		            	reader.onload = function (e) {
		            		//$('#blah').show();
		            		
			                $('#blah').attr('src', e.target.result);
			            }
		            	 reader.readAsDataURL(input.files[0]);
		            }else {
                        alert(input.files[0].name + " is not a valid image file.");
                        $('#blah').attr('src', "");
                        //$('#blah').hide();
                        return false;
                    }
		            
		           
		            } 
		        
		    }
		/*  function imagechange(){
			 alert("this");
			 readURL(this);
		 } */
		    
		    $("#imgInp").change(function(){
		        readURL(this);
		    });
		    $('#btnCrop').click(function () {
		        var x1 = $('#imgX1').val();
		        var y1 = $('#imgY1').val();
		        var width = $('#imgWidth').val();
		        var height = $('#imgHeight').val();
		        var canvas = $("#canvas")[0];
		        var context = canvas.getContext('2d');
		        var img = new Image();
		        img.onload = function () {
		            canvas.height = height;
		            canvas.width = width;
		            context.drawImage(img, x1, y1, width, height, 0, 0, width, height);
		            $('#imgCropped').val(canvas.toDataURL());
		        };
		        img.src = $('#Image1').attr("src");
		    });
		
		function SetCoordinates(c) {
		    $('#imgX1').val(c.x);
		    $('#imgY1').val(c.y);
		    $('#imgWidth').val(c.w);
		    $('#imgHeight').val(c.h);
		    $('#btnCrop').show();
		};
		$('select[id*=stateName]').change(function(){
		var stateId=$("#stateName").val();
		$.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "./registration/getDistrictName/"+stateId,
            data: "{}",
            dataType: "Json",
            success: function (data) {
            	 var newOption = $('<option>');
                 newOption.attr('value',0).text(" ");
                 
                 $('#distName').empty(newOption);
            	($.map(data, function(item) {	
            		//alert(item.stateId);
            		
            		 var newOption = $('<option>');
                        newOption.attr('value',item.distId).text(item.distName);
                        
                        $('#distName').append(newOption);
				}));
                    
              
            }
        });
		    });
		 $("#officeId").change(function(){
			 var officeName=$("#officeId").val();
			 $.ajax({
		            type: "POST",
		            contentType: "application/json; charset=utf-8",
		            url: "./registration/getSalaryTemplate/"+officeName,
		            data: "{}",
		            dataType: "Json",
		            success: function (data) {
		            	 var newOption = $('<option>');
		                 newOption.attr('value',0).text(" ");
		                 $('#salaryTemplateName').empty(newOption);
		                
		            	($.map(data, function(item) {	
		            		 

		            		 var newOption = $('<option>');
		                        newOption.attr('value',item.id).text(item.templateName);
		                        
		                        $('#salaryTemplateName').append(newOption);
		            	    
		            	   /*  var option = $('<option/>');
		            	    
		            	    $('#salaryTemplateName').append(option); */
		            		
		            		/*  var newOption = $('<option>');
		                        newOption.attr('value',item.id).html(satemp);
		                        
		                        $('#salaryTemplateName').append(newOption);  */
						})); 
		            }
		        });
		 });
		
		 $("#modeOfPay").change(function(){
		var modeOfPay=$("#modeOfPay").val();
		
		if(modeOfPay == 'Account Transfer'){
			$("#bankNameDiv").show();
			$("#branchNameDiv").show();
			$("#ifscDiv").show();
		}else{
			$("#bankNameDiv").hide();
			$("#branchNameDiv").hide();
			$("#ifscDiv").hide();
		}
		 });
		 $("#pfEnrolled").change(function(){
			 var pfenrolled=$("#pfEnrolled").val();
			 if(pfenrolled == 'Yes'){
				 $("#pfDiv").show();
				 $("#pvpfDiv").show();
				 
			 }else{
				 $("#pfDiv").hide();
				 $("#pvpfDiv").hide(); 
			 }
		 });
		 
		 $("#insuranceApplicable").change(function(){
		var insApp=	 $("#insuranceApplicable").val();
		if(insApp == 'Yes'){
			
			$("#insTypeDiv").show();
		}else{
			$("#insTypeDiv").hide();
			 $("#esiDiv").hide();
				$("#amountDiv").hide();
				$("#timeDiv").hide();
				$("#mediclaimDiv").hide();
		}
		 });
		 $("#insuranceType").change(function(){
			 var insuType=$("#insuranceType").val();
			 if(insuType == 'ESI'){
				$("#esiDiv").show();
				$("#amountDiv").hide();
				$("#timeDiv").hide();
				$("#mediclaimDiv").hide();
			 }else{
				 $("#esiDiv").hide();
					$("#amountDiv").show();
					$("#timeDiv").show();
					$("#mediclaimDiv").show();
			 }
		 });
		 var validate=new Array();
		 
		 $("#tab99").click(function(){
			 /* alert("step1");
			  var className = $('#tab88').attr('class'); */
			 var className1=$('#tab11').attr('class');
			 var className2=$('#tab22').attr('class');
			 var className3=$('#tab33').attr('class');
			 var className4=$('#tab44').attr('class');
			 var className5=$('#tab55').attr('class');
			 var className6=$('#tab66').attr('class');
			 var className7=$('#tab77').attr('class');
			 var className8 = $('#tab88').attr('class');
			
			 if(className1 == 'complete'){	
				 
				 $("#show-tabs1").prop('checked', true);
			 }
			 else{
				 $("#show-tabs1").prop('checked', false); 
			 }
			 if(className2 == 'complete'){
				
				 $("#show-tabs2").prop('checked', true);
			 }else{
				 $("#show-tabs2").prop('checked', false);
			 }
			 if(className3 == 'complete'){
				
				 $("#show-tabs3").prop('checked', true);
			 }else{
				 $("#show-tabs3").prop('checked', false);
			 }
			 if(className4 == 'complete'){
				
				 $("#show-tabs4").prop('checked', true);
			 }else{
				 $("#show-tabs4").prop('checked', false); 
			 }
			 if(className5 == 'complete'){				 
				 $("#show-tabs5").prop('checked', true);
			 }else{
				 $("#show-tabs5").prop('checked', false); 
			 }
			 if(className6 == 'complete'){				 
				 $("#show-tabs6").prop('checked', true);
			 }else{
				 $("#show-tabs6").prop('checked', false); 
			 }
			 if(className7 == 'complete'){				 
				 $("#show-tabs7").prop('checked', true);
			 }else{
				 $("#show-tabs7").prop('checked', false); 
			 }
			 if(className8 == 'complete'){				 
				 $("#show-tabs8").prop('checked', true);
			 }else{
				 $("#show-tabs8").prop('checked', false); 
			 }
			 
		 });
		 
		 $("#nextButton").click(function(){
			
			 // var className = $('#tab88').attr('class'); */
			 var className1=$('#tab11').attr('class');
			 var className2=$('#tab22').attr('class');
			 var className3=$('#tab33').attr('class');
			 var className4=$('#tab44').attr('class');
			 var className5=$('#tab55').attr('class');
			 var className6=$('#tab66').attr('class');
			 var className7=$('#tab77').attr('class');
			 var className8 = $('#tab88').attr('class');
			
			 if(className1 == 'complete'){	
				 
				 $("#show-tabs1").prop('checked', true);
			 }
			 if(className2 == 'complete'){
				
				 $("#show-tabs2").prop('checked', true);
			 }
			 if(className3 == 'complete'){
				
				 $("#show-tabs3").prop('checked', true);
			 }
			 if(className4 == 'complete'){
				
				 $("#show-tabs4").prop('checked', true);
			 }
			 if(className5 == 'complete'){				 
				 $("#show-tabs5").prop('checked', true);
			 }
			 if(className6 == 'complete'){				 
				 $("#show-tabs6").prop('checked', true);
			 }
			 if(className7 == 'complete'){				 
				 $("#show-tabs7").prop('checked', true);
			 }
			 if(className8 == 'complete' || className8 == 'active'){				 
				 $("#show-tabs8").prop('checked', true);
			 }
			 
		 });
		var tableData="";
		  $('#salaryTemplateName').change(function () {
				var salval=$('#salaryTemplateName').val();
				if(salval.length>0){
					
					
					$("#salTempDiv").empty();
					 var todcal=$( "#salTempDiv" );
					  todcal.kendoWindow({
					      width: 650,
					      height: 'auto',
					      modal: true,
					      draggable: true,
					      position: { top: 150,left:380 },
					      title: "Salary Template Information"
					  }).data("kendoWindow").open();

					   todcal.kendoWindow("open");  
					
					$.ajax
					({			
						type : "POST",
						url : "./registration/getEmployeesSalaryTemplateDes/"+salval,
						async: false,
						dataType : "JSON",
						success : function(response) 
						{	    
							tableData="<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' >"
						
								+"<thead>"
								+"<tr>"			
									+"<th  data-hide='phone'>Template Name</th>"									
									+"<th  data-hide='phone'>Basic</th>"
									+"<th  data-hide='phone'>Other Allowance</th>"
									+"<th  data-hide='phone'>Net Salary</th>"
									+"</tr>"
									+"</thead>"
									+"<tbody>";
							
									for ( var s = 0, len = response.length; s < len; ++s) {
						              	var obj = response[s];
						              	
						              	tableData += "<tr>"							              	
						              	+"<td>"+obj.templateName+"</td>"
						              	+"<td>"+obj.basic+"</td>"						              
						              	+"<td>"+obj.OtherAllowance+"</td>"
						            	+"<td>"+obj.netSalary+ "</td>"
						                +"</tr>";
						         }
									tableData +=""		
										+"</tbody>"
										+"</table>";
										$('#salTempDiv').append(tableData);
										
										
										pageSetUp();
										var responsiveHelper_dt_basic = undefined;
										var responsiveHelper_datatable_fixed_column = undefined;
										var responsiveHelper_datatable_col_reorder = undefined;
										var responsiveHelper_datatable_tabletools = undefined;
										var breakpointDefinition = {
												tablet : 1024,
												phone : 480
											};
								/* 		$('#datatable_tabletools5')
										.dataTable(
												{
													"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
															+ "t"
															+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
													"oTableTools" : {
														"aButtons" : [
																"copy",
																"csv",
																"xls",
																{
																	"sExtends" : "pdf",
																	"sTitle" : "SmartAdmin_PDF",
																	"sPdfMessage" : "SmartAdmin PDF Export",
																	"sPdfSize" : "letter"
																},
																{
																	"sExtends" : "print",
																	"sMessage" : "Generated by BCITS <i>(press Esc to close)</i>"
																} ],
														"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
													},
													"autoWidth" : true,
													"preDrawCallback" : function() {
														// Initialize the responsive datatables helper once.
														if (!responsiveHelper_datatable_tabletools) {
															responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
																	$('#datatable_tabletools5'),
																	breakpointDefinition);
														}
													},
													"rowCallback" : function(nRow) {
														responsiveHelper_datatable_tabletools
																.createExpandIcon(nRow);
													},
													"drawCallback" : function(oSettings) {
														responsiveHelper_datatable_tabletools
																.respond();
													}
												}); */
									
						} 
					});
				}
		  }); 
		
		</script>
<style>
.input-group-addon{
height: 18px;
 font-size: 13px;
}
.form-control input-lg {
height: 37px; 
font-size: 13px;
}
.bootstrapWizard li {
    display: block;
    float: left;
    padding-left: 0;
    text-align: center;
    width: 11.1%;
}
/*  #imagecontainer{
    width:200px;
    height:150px;
    position:relative;
    display:inline-block;
    overflow:hidden;
}

div > img {
    display: block;
    left: -15%;
    min-height: 100%;
    min-width: 100%;
    position: initial;
    top: 50%;
}
 */
 #blah {
        border: 8px solid #ccc;
        width: 200px;
        height: 200px;
    }

  .fa-plus-square{
        color: red;
        opacity: 0.8;
    }
    .saveTab{
    font-size: 15px;
    color: #669999;
    }
</style>
