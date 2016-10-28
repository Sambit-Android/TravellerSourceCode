<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 
<script src="./resources/js/exportToExcel.js"></script>
<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>

<div id="content">

	<div class="row" style="margin-top: -9px">
		
		<div class="col-sm-4">
			<a class="btn btn-primary btn-lg"  onclick="openRegisterForm()" style="width:350px;">Register Ticket</a>
		</div>		
										
		<div class="col-sm-4">
			<a class="btn bg-color-pinkDark btn-lg txt-color-white" onclick="resolvedTickets(4)" style="width:350px;">Re-Opened Tickets (<span id="pendigSpanId" style=""><c:choose><c:when test="${reopenedCount=='0'}">${reopenedCount}</c:when><c:otherwise><blink>${reopenedCount}</blink></c:otherwise></c:choose></span>)</a>
		</div> 
		
		<div class="col-sm-4">
			<a class="btn bg-color-redLight btn-lg txt-color-white" onclick="showAllEscalatedTicketsForCCCD(2)" style="width:350px;">Escalated Tickets (<span id="escalationSpanId">${notificationCount2}</span>)</a>
		</div>



<div class="row">	
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 39px; padding-right: 26px;">
			<div class="row">
			<div class="well input-group" style="width: 100%; background: #d6dde7;">
						<div class="" align="center">
							<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
											<input class="form-control" onkeypress="searchDocketNumber(event)" name="docketNo" type="text" style="width: 223px;margin-left: 32px;height: 34px;" data-mask="9999999999" placeholder="Enter Ticket No." id="docketNo" data-original-title="Enter Ticket Number" rel="tooltip">
											
										</div>
									</div>

								</div>
						</div>
						<!-- <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
						<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
										<input class="form-control" name="rrNumber" onkeypress="searchDocketRRNumber(event)" id="rrNumber" type="text" style="width: 223px;margin-left: 32px;height: 34px;"   placeholder="Enter RR Number" id="search-project">
										</div>
									</div>

								</div>
						</div> -->
						<!-- <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
						<div class="form-group has-warning">
									<div class="col-md-10">
										<div class="input-group">
										<input class="form-control"  onkeypress="searchMobileNumber(event)" type="text" name="mobileNo" id="mobileNo" style="width: 223px;margin-left: 32px;height: 34px;"  data-mask="9999999999" placeholder="Enter Mobile No." id="search-project" style="height: 37px; font-size: 13px;" data-original-title="Enter Mobile Number" rel="tooltip">
										</div>
									</div>

								</div>
						</div> -->
			</div>
			</div>
		</div>
	</div>
	</div>
	
	
		<c:if test = "${not empty escalatedComplaintsForCCCDNotifications}"> 			        
		        <script>		        
		            var escalatedComplaintsForCCCDNotifications = "${escalatedComplaintsForCCCDNotifications}";
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	showAllEscalatedTicketsForCCCD(1);
		            });
		        </script>
		    <c:remove var="escalatedComplaintsForCCCDNotifications" scope="session"/>
		 </c:if>

		<c:if test = "${not empty helpDeskSaveResult}"> 			        
		        <script>		        
		            var helpDeskSaveResult = "${helpDeskSaveResult}";
		            alert(helpDeskSaveResult);
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	openRegisterForm();
		            });
		        </script>
		    <c:remove var="helpDeskSaveResult" scope="session"/>
		 </c:if>
		 
		 <c:if test = "${not empty helpDeskUpdateResult}"> 			        
		        <script>		        
		            var helpDeskUpdateResult = "${helpDeskUpdateResult}";
		            alert(helpDeskUpdateResult);
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	pendingForRegistrationForm(2);
		            });
		        </script>
		    <c:remove var="helpDeskUpdateResult" scope="session"/>
		 </c:if>	
		 
		 <c:if test = "${not empty pendingForRegistrationNotification}"> 			        
		        <script>		        
		            var pendingForRegistrationNotification = "${pendingForRegistrationNotification}";
		            
		            $(document).ready(function() {
		            	pageSetUp();
		            	pendingForRegistrationForm(1);
		            });
		        </script>
		    <c:remove var="pendingForRegistrationNotification" scope="session"/>
		 </c:if>

		 <jsp:include page="../genericsRegestration.jsp"/>
	
<div class="row" id="viewTickets" style="display:none">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<!-- <section id="widget-grid" class=""> -->
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"><i class="fa fa-fw fa-search fa-lg"></i></span>
									<h2>Searched Ticket Details</h2>
								</header>
								<!-- widget div-->
								<br>
								<a class="btn bg-color-teal txt-color-white"  href="#"  onclick="return docketDetailsPopUp('MobTicketsSearchCheckBox')">Change Multiple Status</a>
								<div>
									<div class="widget-body no-padding">
										<table class="table table-striped table-bordered table-hover" width="100%" id="mobileTicketsSearch">
											<thead>
												<tr>
												   <th><input type="checkbox" id="selectall"  onClick="selectAllMobSearch(this)" /></th>
													<th data-hide="phone">Ticket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-class="expand">Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Summary</th>
													<!-- <th data-hide="phone,tablet">Assigned To</th> -->
													<th data-hide="phone,tablet">Created By</th>
													<th data-hide="phone,tablet"></th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="searchTicketDiv">
												<c:forEach items="${allTickets}" var="ticket">
												<tr>
													<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td>
													<%-- <td>${ticket.consumerName}</td> --%>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>		
							<!-- </section> -->
			</article>
	</div>
	
	<div class="row" id="viewResolvedTickets" style="display:none">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<!-- <section id="widget-grid" class=""> -->
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"><i class="fa fa fa-table"></i></span>
									<h2><span id="reopenResolveTitle">Complaints</span></h2>
								</header>
								<!-- widget div-->
								<br>
								<a class="btn bg-color-teal txt-color-white"  href="#"  onclick="return docketDetailsPopUp('MobTicketsSearchCheckBox')">Change Multiple Status</a>
								<div>
									<div class="widget-body no-padding">
										<table class="table table-striped table-bordered table-hover" width="100%" id="mobileTicketsSearch">
											<thead>
												<tr>
												   <th><input type="checkbox" id="selectall"  onClick="selectAllMobSearch(this)" /></th>
													<th data-hide="phone">Ticket No.</th>
													<th data-hide="phone">Status</th>
													<th data-class="expand">Created Date</th>
													<th data-class="expand">Category</th>
													<th data-hide="phone">SubCategory</th>
													<th data-hide="phone,tablet">Summary</th>
													<!-- <th data-hide="phone,tablet">Assigned To</th> -->
													<th data-hide="phone,tablet">Created By</th>
													<th data-hide="phone,tablet"></th>
													<th data-hide="phone,tablet"></th>
												</tr>
											</thead>
											<tbody id="resolvedTicketDiv">
												<c:forEach items="${allTickets}" var="ticket">
												<tr>
													<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td>
													<%-- <td>${ticket.consumerName}</td> --%>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>		
							<!-- </section> -->
			</article>
	</div>
	
	<div class="row" id="pendingForRegistrationTickets" style="display:none;">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Resolved Tickets</h2>
								</header>
								<div>
									<div class="widget-body no-padding" id="pendingContent">
									</div>				
								</div>				
							</div>	
			</article>
	</div>
	
	<!-- <section id="widget-grid" class=""> -->
					<div class="row" id="complaintsAgingDiv">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-9">
							<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-clock-o"></i> </span>
									<h2>Tickets Aging As Per SLA</h2>
								</header>
								<div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<!-- <th>Category Name</th> -->
														<th>0 - 1 hr</th>
														<th>2 hr - 4 hrs</th>
														<th>5 hrs - 24 hrs</th>
														<th>One day - 10 days</th>
														<th>11 days - 1 month</th>
														<th>More Than Month</th>
													</tr>
												</thead>
												<tbody>
													<%-- <c:forEach items="${complaintAgingList}" var="ticket"> --%>
													<tr>
													<%-- <td>${ticket.categoryName}</td> --%>
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel1}','1')">${complaintAgingList.level1}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel2}','1')">${complaintAgingList.level2}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel3}','1')">${complaintAgingList.level3}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel4}','1')">${complaintAgingList.level4}</a></td> 
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel5}','1')">${complaintAgingList.level5}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${complaintAgingList.doclevel6}','1')">${complaintAgingList.level6}</a></td>
													</tr>
													<%-- </c:forEach> --%>
												</tbody>
											</table>
											
										</div>
									</div>

								</div>

							</div>
							<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-clock-o"></i> </span>
									<h2>Time Pending For Resolution As Per SLA</h2>
								</header>
								<div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<!-- <th>Category Name</th> -->
														<th>0 - 1 hr</th>
														<th>2 hr - 4 hrs</th>
														<th>5 hrs - 24 hrs</th>
														<th>One day - 10 days</th>
														<th>11 days - 1 month</th>
														<th>More Than Month</th>
													</tr>
												</thead>
												<tbody>
													<%-- <c:forEach items="${complaintAgingList}" var="ticket"> --%>
													<tr>
													<%-- <td>${ticket.categoryName}</td> --%>
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel1}','2')">${comPlaintAgingForPending.level1}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel2}','2')">${comPlaintAgingForPending.level2}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel3}','2')">${comPlaintAgingForPending.level3}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel4}','2')">${comPlaintAgingForPending.level4}</a></td> 
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel5}','2')">${comPlaintAgingForPending.level5}</a></td>
													<td><a href="#" onclick="subRRNumNew123('${comPlaintAgingForPending.doclevel6}','2')">${comPlaintAgingForPending.level6}</a></td>
													</tr>
													<%-- </c:forEach> --%>
												</tbody>
											</table>
											
										</div>
									</div>

								</div>

							</div>
							</article>
							
							<!-- <article class="col-xs-12 col-sm-12 col-md-12 col-lg-9">
							
							</article> -->
							
							<article class="col-sm-12 col-md-12 col-lg-3">
							<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-2" data-widget-editbutton="false">

								<header>
									<span class="widget-icon"> <i class="fa fa-bell"></i> </span>
									<h2>Day Counter<a href='#' style="color: white;" onclick='daycounter(6,${dayCounter.pendingForRegistrationDayCount+dayCounter.registered+dayCounter.resolved})'>(${dayCounter.pendingForRegistrationDayCount+dayCounter.registered+dayCounter.resolved+dayCounter.reopened})</a></h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div class="table-responsive">
											<table class="table table-hover">
												<thead>
													<tr>
														<th>Status</th>
														<th>Count</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Registered&Assigned</td>
														<td style="padding-left: 40px;"><a href='#' onclick='daycounter(1,${dayCounter.registered})'>${dayCounter.registered}</a></td>
													</tr>
													<tr>
														<td>On Hold</td>
														<td style="padding-left: 40px;"><a href='#' onclick='daycounter(2,${dayCounter.onHold})'>${dayCounter.onHold}</a></td>
													</tr>
													<tr>
														<td>Resolved</td>
														<td style="padding-left: 40px;"><a href='#' onclick='daycounter(3,${dayCounter.resolved})'>${dayCounter.resolved}</a></td>
													</tr>
													<tr>
														<td>Reopened</td>
														<td style="padding-left: 40px;"><a href='#' onclick='daycounter(4,${dayCounter.reopened})'>${dayCounter.reopened}</a></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</article>
						<article class="col-sm-12 col-md-12 col-lg-3" style="padding-top: 14px;">
							<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-2" data-widget-editbutton="false">

								<header>
									<span class="widget-icon"> <i class="fa fa-flash"></i> </span>
									<h2>Today's Escalated Tickets(<a href='#' style="color: white;" onclick='daycounter(5,${dayCounter.escalated})'>${dayCounter.escalated}</a>)</h2>
								</header>
							</div>
						</article>
						</div>
						
						<!-- </section> -->
						
						<br>
						
	<!-- 
	<div  id="subdivisionwiseTicketsDiv">

					widget edit box
					<div class="jarviswidget-editbox">
						This area used as dropdown edit box

					</div>
					end widget edit box

					widget content
					<div class="widget-body">
	
					<ul class="nav nav-tabs tabs-pull-right bordered in" id="myTab">
						<a class="btn bg-color-blue txt-color-white" onclick="callDate()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Date</a>
						<a class="btn bg-color-blue txt-color-white" onclick="callCategory()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Category</a>
						<a class="btn bg-color-blue txt-color-white" onclick="callMode()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Mode</a>
						<a class="btn bg-color-blue txt-color-white" onclick="clearFilter()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Clear Filter</a>
							<li id="tab23" class="active"><a data-toggle="tab" href="#s3" onclick="tabClick3(3)"><span
									class="hidden-mobile hidden-tablet">Sub Division wise</span></a></li>
					</ul>

			<div id="myTabContent3" class="tab-content padding-10">
							<div class="tab-pane fade in active" id="s3">
		 						<div class="row">

									NEW WIDGET START
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

									Widget ID (each widget will need unique ID)
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Sub Division Complaint Status </h2>
									</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding" id="monthContent"><div style='text-align:center'><font size='5px'><span id="titleid3"></span></font></div>
						<table id="dt_basic" class="table table-striped table-bordered table-hover monthtab" width="100%">
							<thead>			                
								<tr>
									<th data-hide="phone">Sub Division</th>
									<th data-class="expand"> Total Complaints</th>
									<th data-hide="phone"> Pending For Registration</th>
									<th data-hide="phone,tablet">Registered&Assigned</th>
									<th data-hide="phone,tablet"> OnHold</th>
									<th data-hide="phone,tablet">Resolved</th>
									<th data-hide="phone,tablet"> Reopen</th>
								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
	</div>
	</div> -->

	<div class="row" id="viewEscalatedTickets" style="display:none">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<section>
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">Escalated Tickets</span></h2>
								</header>
								<!-- widget div-->
								<div>
								<br>
									<div class="widget-body no-padding" id="escalatedDiv">
									</div>
								</div>
							</div>
					</section>		
				</article>
		</div>	

</div>
<div id="treeview1" style="display: none"></div>
<div id="treeview2" style="display: none"></div>
<div id="treeview3" style="display: none"></div>

<div id="data12" hidden="true" style="padding-left: 27px; padding-right: 22px;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2><span id="title">Complaints</span></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding" id="monthContent123">
					</div>
				</div>
			</div>
		</div>
	</div>

<div id="addtab" title="Docket Details Update" style="display:none">

				<form id="updateStatus-form">
					<fieldset>
						<input name="authenticity_token" type="hidden"><br>
						<div class="form-group">
								<input type="radio" id="commentId" checked="checked" name="Comment" onchange="commentValueChange(this.value)" value="Status"><label>&nbsp;Change Status</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="Comment" value="Comment" onchange="commentValueChange(this.value)"><label>&nbsp;Comment</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="Comment" value="Invalid" onchange="commentValueChange(this.value)"><label>&nbsp;Invalidate&nbsp;Complaint</label><br>
							</div>
							<div class="form-group" id="commentStatus">
							<label>Docket Status </label>
							<select class="form-control" id="docketStatus" name="docketStatus">
								<option value="0" selected="" disabled="">Select Status</option>
								<option value="2">On Hold</option>
								<option value="3">Resolved</option>
							</select><i></i>
						</div>
						<div class="form-group">
							<label>Remarks</label>
							<textarea class="form-control" name="remarks" id="remarks" placeholder="Docket Remarks" rows="3"></textarea>
						</div>
					</fieldset>
				</form>

</div>	

<div id="reopenDiv" title="Reopen complaint" style="display:none">

				<form id="reopenStatus-form">
					<fieldset>
						<input name="authenticity_token" type="hidden"><br>
						<div class="form-group">
								<input type="radio" id="commentReopenId" checked="checked" name="CommentReopen" onchange="commentReopenValueChange(this.value)" value="Status"><label>&nbsp;Change Status</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="CommentReopen" value="Comment" onchange="commentReopenValueChange(this.value)"><label>&nbsp;Comment</label><br>
							</div>
							<div class="form-group" id="commentStatusReopen">
							<label>Docket Status </label>
							<select class="form-control" id="docketStatusReopen" name="docketStatusReopen">
								<option value="0" selected="" disabled="">Select Status</option>
								<option value="4">Reopen</option>
							</select><i></i>
						</div>
						<div class="form-group">
							<label>Remarks</label>
							<textarea class="form-control" name="remarksReopen" id="remarksReopen" placeholder="Docket Remarks" rows="3"></textarea>
						</div>
					</fieldset>
				</form>

</div>	
<div class="row" style="display:none" id="selectedDocketDetails">
		
<article class="col-sm-12">
<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon"></span>
								<ul class="nav nav-tabs pull-right in" id="myTab">
									<li id="tabOne" class="active">
										<a data-toggle="tab" href="#s1" onclick="tab1Click()"><i class="fa fa-envelope"></i> <span class="hidden-mobile hidden-tablet">Docket Details</span></a>
									</li>
									<li id="tabTwo">
											<a data-toggle="tab" href="#s2" onclick="tab2Click()"><i class="fa fa-folder"></i> <span class="hidden-mobile hidden-tablet">Docket Histroy</span></a>
									</li>
									<li id="tabThree">
											<a data-toggle="tab" href="#s4" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
									</li>
									</ul>	
										
								</header>
								<div class="no-padding">
									<div class="widget-body" id="tab1">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in padding-12 no-padding-bottom" id="s1"> 	
											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
													<tbody id="viewDocket">
	
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab2" style='overflow: scroll;'>
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s2">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Date</th>
															<th data-class="expand">Status</th>
															<!-- <th>Action</th> -->
															<!-- <th data-hide="phone">Action By</th> -->
															<th data-hide="phone,tablet">Name</th>
															<th data-hide="phone,tablet">User Name</th>
															<!-- <th data-hide="phone,tablet">Office</th> -->
															<th data-hide="phone,tablet">Designation</th>
															<th data-hide="expand">Official Mobile No.</th>
															<th data-hide="phone,tablet">Remarks</th>
														</tr>
														</thead>
													<tbody id="docketHistoryDiv">
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab3">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s4">	
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Created Date</th>
															<th data-hide="phone">Escalated Date</th>
															<th data-class="expand">Level</th>
															<!-- <th data-hide="phone">From Officer</th> -->
															<th data-hide="phone">Escalated To Officer</th>
														</tr>
														</thead>
													<tbody id="docketEscHistoryDiv">
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
<div id="alertsBox" title="Alert"></div>   
<div id="stack123" >
<form action="./slaComplaintSearch" id="submItRecordRRNUm" method="post">

<input type="text" name="agingAsPerSlaPending" id="agingAsPerSlaPending" hidden="true">
<input type="text" name="complaintsAgingSla" id="complaintsAgingSla" hidden="true">

</form>

</div> 
<script type="text/javascript">
function daycounter(count,data){
	
		scrollFunction();
		$('#subdivisionwiseTicketsDiv').hide();
		$('#data12').show();
		$.ajax({
			url : "./index/showTodayComplaints",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				status: count,
			},
			success : function(response) {
				if(count==0){
					$('#title').text("Pending For Registration");
					$('.monthTabDetail').remove();
					$("#id2").remove();
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>" 
					+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th data-hide='phone'>Docket No.</th>"
					+"<th data-class='expand'>Created Date</th>"
					+"<th data-class='expand'>Time Lapsed</th>"
					+"<th data-hide='phone,tablet'>Mode</th>"
					+"<th data-hide='phone,tablet'>Consumer Name</th>"
					+"<th data-hide='phone'>Mobile No.</th>"
					+"<th data-hide='phone,tablet'>Email Id</th>"
					+"<th data-hide='phone,tablet'>Facebook Id</th>"
					/* +"<th data-hide='phone,tablet'></th>" */
				    +"</tr>"
							+"</thead>"
							+"<tbody>";
							var dataNew=response;
							 $.each(dataNew, function(index, obj){
								 
								 var d="";
					              	if(obj.docketSource=='Sms')
					              		{
					              		d='"Sms"';
					              		}
					              	else
					              		{
					              		d='"Blank"';
					              		}
								
								htmlTable+= "<tr>"
									+"<td><a style='cursor:pointer;' onclick='docketDetailsUpdatePopUp("+obj.docketNumber+","+obj.consumerMobileNo+","+d+")'>"+obj.docketNumber+"</a></td>"
									+"<td>"+obj.docketCreatedDt+"</td>"
									+"<td>"+obj.elapsedTime+"</td>"
					              	+"<td>"+obj.docketSource+ "</td>"
					              	+"<td>"+obj.consumerName+"</td>"
					              	+"<td>"+obj.consumerMobileNo+ "</td>"
					              	+"<td>"+obj.consumerEmailId+"</td>"			              	
					              	+"<td>"+obj.facebookId+"</td>"
					              	/* +"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>" */
									+"</tr>";
								 
				         		});
						htmlTable +=""		
							+"</tbody>"
							+"</table></div>";
						
					/* $('.monthtab').hide();
					$('#dt_basic_wrapper').hide(); */
					$('#monthContent123').append(htmlTable);
				}else if(count!=5 && count!=6){
					if(count==1){
						$('#title').text("Registered and Assigned Tickets");
					}else if(count==3){
						$('#title').text("Resolved Tickets");
					}else if(count==4){
						$('#title').text("Reopened Tickets");
					} else if(count==2){
						$('#title').text("On Hold Tickets");
					}
					$('.monthTabDetail').remove();
					$("#id2").remove();
					var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>" 
					+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th data-hide='phone'>Docket No.</th>"
					+"<th data-class='expand'>Docket Status</th>"
					+"<th>Created Date</th>"
					+"<th data-hide='phone'>SLA Date&Time for Resolving</th>"
					+"<th data-hide='phone'>Time Pending</th>"
					+"<th data-hide='phone'>Resolved Date & Time</th>"
					<!-- <th data-hide='phone'>Time taken for resolving</th> -->
					+"<th data-hide='phone,tablet'>Category</th>"
					+"<th data-hide='phone,tablet'>Sub Category</th>"
					+"<th data-hide='phone,tablet'>Consumer Name</th>"
					+"<th data-hide='phone,tablet'>Mobile No.</th>"
					+"<th >AssignedTo</th>"
					+"<th></th>"
				    +"</tr>"
							+"</thead>"
							+"<tbody>";
							var dataNew=response;
							 $.each(dataNew, function(index, data){
								
								htmlTable+= "<tr>";
									if(count==1){
										htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
									}else if(count==3){
										htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
									}else if(count==4){
										htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
									} else if(count==2){
										htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
									}
									htmlTable+="<td>"+data.docketStatus+"</td>"
					              	+"<td>"+data.docketCreatedDt+ "</td>"
					              	+"<td>"+data.estResolveTime+ "</td>"	
					            	+"<td>"+data.pending+ "</td>"	
					            	+"<td>"+data.resolvedDate+ "</td>"	
					            	+"<td>"+data.categoryName+ "</td>"	
					            	+"<td>"+data.subCategoryName+ "</td>"
					            	+"<td>"+data.consumerName+ "</td>"
					            	+"<td>"+data.consumerMobileNo+ "</td>";
					            	if(data.assinedName==""){
					            		htmlTable +="<td>"+data.assinedName+"</td>";
					            	}else{
					            		htmlTable +="<td>"+data.assinedName+" - "+data.assignedTo+" - "+data.designation+" - "+data.officialMobileNo+"</td>";
					            	}
					            	htmlTable+="<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
									+"</tr>";
								 
				         		});
						htmlTable +=""		
							+"</tbody>"
							+"</table></div>";
						
					$('.monthtab').hide();
					$('#dt_basic_wrapper').hide();
					$('#monthContent123').append(htmlTable);
					
					
					
					
					
				}else if(count==5){
					$('#title').text("Escalated Tickets");
					$('.monthTabDetail').remove();
					$("#id2").remove();
					var d='"ESCULATEDCHECKBOX"';
					var htmlTable ="<a id='id2' class='btn bg-color-greenDark txt-color-white'  href='#'  onclick='return docketDetailsPopUp("+d+")'>Change Multiple Status</a>" 
					+"<div class='monthTabDetail' style='overflow-x: scroll;'>" 
					+"<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th><input type='checkbox' id='selectall'  onClick='selectAllEsc(this)' /></th>"
					+"<th data-hide='phone'>Docket No.</th>"
					+"<th data-hide='phone'>Status</th>"
					+"<th data-hide='phone'>Created Date</th>"
					+"<th data-class='expand'>Escalated Date</th>"
					+"<th data-hide='phone'>SLA Date&Time for Resolving</th>"
					+"<th data-hide='phone,tablet'>Level</th>" 
					+"<th data-hide='phone,tablet'>Escalated From Officer</th>"
					+"<th data-hide='phone,tablet'>Escalated To Officer</th>"
					+"<th data-hide='phone,tablet'>Officer Name</th>"
					+"<th data-hide='phone,tablet'>Officer Mobile No.</th>"
					/* +"<th data-hide='phone,tablet'>Office</th>" */
					+"<th data-hide='phone,tablet'></th>"
				    +"</tr>"
					+"</thead>"
					+"<tbody>";
					for ( var s = 0, len = response.length; s < len; ++s) {
			            	var obj = response[s];
			            	if(obj.docketStatus=='Resolved')
		              		{
			            		htmlTable+="<td></td>"
		              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
		              		}
		              	else
		              		{
		              		htmlTable+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
		            		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
		              		 
		              		}
			            	
			            	htmlTable+="<td>"+obj.docketStatus+"</td>"
				              	+"<td>"+obj.docketCreatedDt+"</td>"
				              	+"<td>"+obj.escalatedDate+ "</td>"
				              	+"<td>"+obj.estResolveTime+ "</td>"
				              	+"<td>"+obj.level+ "</td>"
				              	+"<td>"+obj.fromUser+ "</td>"
				              	+"<td>"+obj.toUser+ "</td>"
				              	+"<td>"+obj.OfficerName+ "</td>"
				              	+"<td>"+obj.officerMobileNo+ "</td>"
				              	/* +"<td>"+obj.office+ "</td>" */
				              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
				                +"</tr>";
			            		}
				   
					htmlTable +="</tbody>"		
						+"</table></div>";
						$('#monthContent123').append(htmlTable);
			         }else if(count==6){
				        	 $('.monthTabDetail').remove();
								$("#id2").remove();
								var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>" 
								+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
								+"<thead>"
								+"<tr>"
								+"<th data-hide='phone'>Docket No.</th>"
								+"<th data-hide='phone'>Status</th>"
								+"<th data-class='expand'>Created Date</th>"
								+"<th data-hide='phone,tablet'>Summary</th>"
								+"<th data-hide='phone,tablet'>Mode</th>"
								+"<th data-hide='phone,tablet'>Consumer Name</th>"
								+"<th data-hide='phone,tablet'>Mobile No.</th>"
								+"<th data-hide='phone,tablet'></th>"
							    +"</tr>"
										+"</thead>"
										+"<tbody>";
										var dataNew=response;
										 $.each(dataNew, function(index, data){
											
											htmlTable+= "<tr>";
												if(count==1){
													htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
												}else if(count==3){
													htmlTable+="<td>"+data.docketNumber+"</td>";
												}else if(count==4 || count==6){
													htmlTable+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+data.docketNumber+")'>"+data.docketNumber+"</a></td>";
												}
												
												htmlTable+="<td>"+data.docketStatus+"</td>"
												+"<td>"+data.docketCreatedDt+"</td>"
												+"<td>"+data.docketSummary+"</td> "
												+"<td>"+data.docketSource+"</td>"
												+"<td>"+data.consumerName+"</td>"
												+"<td>"+data.consumerMobileNo+"</td>"
												+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
												+"</tr>";
											 
							         		});
									htmlTable +=""		
										+"</tbody>"
										+"</table></div>";
									
								$('.monthtab').hide();
								$('#dt_basic_wrapper').hide();
								$('#monthContent123').append(htmlTable);
				         }
					
				pageSetUp();
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
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
											"sTitle" : "CESC_PDF",
											"sPdfMessage" : "CESC PDF Export",
											"sPdfSize" : "letter"
										},
										{
											"sExtends" : "print",
											"sMessage" : "Generated by CESC <i>(press Esc to close)</i>"
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
						});
			},

		});
		
		
	
}
$(document).ready(function() {
	
	if ($('#donut-graph').length) {
		Morris.Donut({
			element : 'donut-graph',
			data : [{
				value : '${notificationCount1}',
				label : '${notificationName1}',
				events: { 
				      click: function()
				      {
				         alert(
				            'The name is ' + this.name +
				            ' and the identifier is ' + this.options.id
				         );
				      }
				   }
			}, {
				value : '${notificationCount2}',
				label : 'onclick=${notificationName2}'
			}],
			formatter : function(x) {
				return x + "%"
			}
		});
	}

	
	pageSetUp();
	
	 $.ajax({
			url : "./dashBoard/readDashBoardSubDivision",
			type : "GET",
			dataType : "JSON",
			success : function(response) {
				var dataNew=response[0];
				$("#titleid3").text(response[1]);
				var htmlTable="<tbody>";
				 $.each(dataNew, function(index, data){
					 if(data.total!=0){
					htmlTable+= "<tr></td>"
	              	+"<td><a href='#' onclick='getLocationDetails("+data.parentid+")'>"+data.subDivision+"</a></td>"
	              	+"<td>"+data.total+"</td>";
	              	if(data.pending==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails2("+data.parentid+",0)'>"+data.pending+"</a></td>";
	              	}
	              	if(data.assigned==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",1)'>"+data.assigned+"</a></td>";
	              	}
	              	if(data.onhold==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",2)'>"+data.onhold+"</a></td>";
	              	}
	              	if(data.resolved==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",3)'>"+data.resolved+"</a></td>";
	              	}
	              	if(data.reopen==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",4)'>"+data.reopen+"</a></td>";
	              	}
	              	htmlTable+="</tr>";
					 }
	         		});
				 htmlTable+="</tbody>";
				 $('#dt_basic').append(htmlTable);
				 var responsiveHelper_dt_basic = undefined;
					var responsiveHelper_datatable_fixed_column = undefined;
					var responsiveHelper_datatable_col_reorder = undefined;
					var responsiveHelper_datatable_tabletools = undefined;
					
					var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};

					$('#dt_basic').dataTable({
						"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
							"t"+
							"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"preDrawCallback" : function() {
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
			    var otable = $('#datatable_fixed_column').DataTable({
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
							"t"+
							"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
					"autoWidth" : true,
					"preDrawCallback" : function() {
						if (!responsiveHelper_datatable_fixed_column) {
							responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_datatable_fixed_column.respond();
					}		
				
			    });
			    // custom toolbar
			    $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');
			    // Apply the filter
			    $("#datatable_fixed_column thead th input[type=text]").on( 'keyup change', function () {
			        otable
			            .column( $(this).parent().index()+':visible' )
			            .search( this.value )
			            .draw();
			            
			    } );
			    /* END COLUMN FILTER */   
		    
				/* COLUMN SHOW - HIDE */
				$('#datatable_col_reorder').dataTable({
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
							"t"+
							"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
					"autoWidth" : true,
					"preDrawCallback" : function() {
						// Initialize the responsive datatables helper once.
						if (!responsiveHelper_datatable_col_reorder) {
							responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_col_reorder'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_datatable_col_reorder.respond();
					}			
				});
				
				/* END COLUMN SHOW - HIDE */

				/* TABLETOOLS */
				$('#datatable_tabletools').dataTable({
					
					// Tabletools options: 
					//   https://datatables.net/extensions/tabletools/button_options
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
							"t"+
							"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
			        "oTableTools": {
			        	 "aButtons": [
			             "copy",
			             "csv",
			             "xls",
			                {
			                    "sExtends": "pdf",
			                    "sTitle": "SmartAdmin_PDF",
			                    "sPdfMessage": "SmartAdmin PDF Export",
			                    "sPdfSize": "letter"
			                },
			             	{
		                    	"sExtends": "print",
		                    	"sMessage": "Generated by CESC <i>(press Esc to close)</i>"
		                	}
			             ],
			            "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
			        },
					"autoWidth" : true,
					"preDrawCallback" : function() {
						// Initialize the responsive datatables helper once.
						if (!responsiveHelper_datatable_tabletools) {
							responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_datatable_tabletools.respond();
					}
				});
			}
	 
	 
			});

	
	$('#todate').datepicker({
		dateFormat : 'dd/mm/yy',
		changeMonth: true,
        changeYear: true
	});

	$('#fromdate').datepicker({
		dateFormat : 'dd/mm/yy'
	});

	var responsiveHelper_datatable_tabletools = undefined;
	
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};

	$('#datatable_tabletools').dataTable({
		
		"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
		"order": [],
		 "bPaginate":true,
	     "sPaginationType":"full_numbers",
	      "iDisplayLength": 05,
		/* "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>", */
	    /* "oTableTools": {
	    	 "aButtons": [
	         "copy",
	         "csv",
	         "xls",
	            {
	                "sExtends": "pdf",
	                "sTitle": "SmartAdmin_PDF",
	                "sPdfMessage": "SmartAdmin PDF Export",
	                "sPdfSize": "letter"
	            },
	         	{
	            	"sExtends": "print",
	            	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
	        	}
	         ],
	        "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
	    }, */
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_datatable_tabletools) {
				responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_tabletools.respond();
		}
	});
	
 })
 
 	function searchMobileNumber(e)
	{
	
	 if(e.keyCode === 13){
		 var mobileNo = $("#mobileNo").val();
		 var mobileregex = new RegExp("^[0-9]{10}$");
     	if(!mobileregex.test(mobileNo)){
     		$("#alertsBox").html("");
			$("#alertsBox").html("Please Enter 10 digit Mobile Number");
			$("#alertsBox").dialog
			({
				modal : true,
				buttons : {
				   "Close" : function() {
				      $(this).dialog("close");
					}
				}
				});
     		return false;
     	}
	var tableData = "";
	$("#searchTicketDiv").empty();
	$('#liveTicketDiv').hide();
	$('#viewTickets').show();
	$('#searchDocket').show();
	$('#dateWiseDiv').hide();
	$("#subdivisionwiseTicketsDiv").hide();
	$("#viewEscalatedTickets").hide();
	$('#pendingForRegistrationTickets').hide();
	$('#registerComplaintId').hide();
	$("#complaintsAgingDiv").hide();
	$('#data12').hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchMobileNumber/"+mobileNo,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		             	tableData += "<tr>"	
			              	if(obj.docketStatus=='Resolved'){
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}else if(obj.invalidFlag=="Yes"){
			              		tableData +="<td></td><td>"+obj.docketNumber+"<br>(Invalidated)</td>"
			              	}else{
			              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}
		              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
		              	tableData+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+"</td>"
		              	+"<td>"+obj.categoryName+ "</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.docketSummary+ "</td>"
		              	+"<td>"+obj.userName+"</td>"			              	
		              	/* +"<td>"+obj.consumerName+"</td>" */
		              	+"<td>"+obj.consumerMobileNo+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
						$('#searchTicketDiv').html(tableData);
					

		},
		 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			}  
	});
	
	$("#docketNo").val("");
	$("#mobileNo").val("");
	$("#rrNumber").val("");
	 }
}
 function searchDocketRRNumber(e){
	if(e.keyCode === 13){
	var rrNumber = $("#rrNumber").val();
	var tableData = "";
	$("#searchTicketDiv").empty();
	$('#liveTicketDiv').hide();
	$('#viewTickets').show();
	$('#searchDocket').show();
	$('#dateWiseDiv').hide();
	$("#subdivisionwiseTicketsDiv").hide();
	$("#viewEscalatedTickets").hide();
	$('#pendingForRegistrationTickets').hide();
	$('#registerComplaintId').hide();
	$("#complaintsAgingDiv").hide();
	$('#data12').hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketRRNumber/"+rrNumber,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		             	tableData += "<tr>";
			              	if(obj.docketStatus=='Resolved'){
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}else if(obj.invalidFlag=="Yes"){
			              		tableData +="<td></td><td>"+obj.docketNumber+"<br>(Invalidated)</td>"
			              	}else{
			              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}
		              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
		              	tableData+="<td>"+obj.docketStatus+"</td>"
		              	+"<td>"+obj.docketCreatedDt+"</td>"
		              	+"<td>"+obj.categoryName+ "</td>"
		              	+"<td>"+obj.subCategoryName+"</td>"
		              	+"<td>"+obj.docketSummary+ "</td>"
		              	+"<td>"+obj.docketSource+"</td>"			              	
		              	+"<td>"+obj.consumerName+"</td>"
		              	+"<td>"+obj.consumerMobileNo+ "</td>"
		              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
		                +"</tr>";
		         }
					
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
						$('#searchTicketDiv').html(tableData);
					

		},
		 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			}  
	});
	
	$("#docketNo").val("");
	$("#mobileNo").val("");
	$("#rrNumber").val("");
	 }
}

	
	function searchDocketNumber(e)
	{
		
	        if(e.keyCode === 13){
	        	var docketNo = $("#docketNo").val();
	        	var mobileregex = new RegExp("^[0-9]{10}$");
	        	if(!mobileregex.test(docketNo)){
	        		$("#alertsBox").html("");
	    			$("#alertsBox").html("Please Enter 10 digit Ticket Number");
	    			$("#alertsBox").dialog
	    			({
	    				modal : true,
	    				buttons : {
	    				   "Close" : function() {
	    				      $(this).dialog("close");
	    					}
	    				}
	    				});
	        		return false;
	        	}
		var tableData = "";
		$("#searchTicketDiv").empty();
		$('#pendingForRegistrationTickets').hide();
		$('#registerComplaintId').hide();
		$('#viewTickets').show();
		$('#viewResolvedTickets').hide();
		$('#dateWiseDiv').hide();
		$("#subdivisionwiseTicketsDiv").hide();
		$("#viewEscalatedTickets").hide();
		$("#complaintsAgingDiv").hide();
		$('#data12').hide();
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketNumber/"+docketNo,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	tableData += "<tr>";		
			              	if(obj.docketStatus=='Resolved'){
			              		
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+","+obj.consumerMobileNo+")'>"+obj.docketNumber+"</a></td>";
			              	} else if(obj.docketStatus=='Pending for Registration'){
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsUpdatePopUp("+obj.docketNumber+","+obj.consumerMobileNo+")'>"+obj.docketNumber+"</a></td>";
			              	} else if(obj.invalidFlag=="Yes"){
			              		tableData +="<td></td><td>"+obj.docketNumber+"<br>(Invalidated)</td>"
			              	}else{
			              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}
			              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
			              	tableData+="<td>"+obj.docketStatus+"</td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
			              	+"<td>"+obj.categoryName+ "</td>"
			              	+"<td>"+obj.subCategoryName+"</td>"
			              	+"<td>"+obj.docketSummary+ "</td>"
			              	+"<td>"+obj.userName+"</td>"			              	
			              	+"<td><a class='btn btn-primary' onclick='documentDetailsViewPopUp("+obj.docketNumber+")'>View Document</a></td>"
			              	/* +"<td>"+obj.consumerMobileNo+ "</td>" */
			              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
			                +"</tr>";
			         }
						 $('#mobileTicketsSearch').dataTable().fnDestroy();
							$('#searchTicketDiv').html(tableData);
			},
			 complete:function(response)
				{
					loadSearchAndFilter('mobileTicketsSearch');
				}   
		
		});
		
		$("#docketNo").val("");
		$("#mobileNo").val("");
		$("#rrNumber").val("");
		return true;
	        }

	        return false;
	}
	
	function resolvedTickets(status)
	{
		if(status==4){
			$("#reopenResolveTitle").text("Re-Opened Tickets");
		} else{
			$("#reopenResolveTitle").text("Resolved Tickets");
		}
		
		var tableData = "";
		$("#resolvedTicketDiv").empty();
		$('#pendingForRegistrationTickets').hide();
		$('#registerComplaintId').hide();
		$('#viewResolvedTickets').show();
		$('#dateWiseDiv').hide();
		$('#viewTickets').hide();
		$("#subdivisionwiseTicketsDiv").hide();
		$("#viewEscalatedTickets").hide();
		$("#complaintsAgingDiv").hide();
		$('#data12').hide();
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/getTicketDataBasedOnStatus/"+status,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	tableData += "<tr>";		
			              	if(obj.docketStatus=='Resolved'){
			              		
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+","+obj.consumerMobileNo+")'>"+obj.docketNumber+"</a></td>";
			              	} else if(obj.docketStatus=='Pending for Registration'){
			              		tableData+="<td></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsUpdatePopUp("+obj.docketNumber+","+obj.consumerMobileNo+")'>"+obj.docketNumber+"</a></td>";
			              	} else if(obj.invalidFlag=="Yes"){
			              		tableData +="<td></td><td>"+obj.docketNumber+"<br>(Invalidated)</td>"
			              	}else{
			              		tableData+="<td><div id='docketNumMobSearchDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbMobSearch' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>" 
			              			+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
			              	}
			              	//+"<td><a class='btn bg-color-blue txt-color-white' onclick='callDate()' style='padding: 1px 22px; margin-left: 9px; margin-top: -4px;'>"+obj.docketNumber+"</a></td>"
			              	tableData+="<td>"+obj.docketStatus+"</td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
			              	+"<td>"+obj.categoryName+ "</td>"
			              	+"<td>"+obj.subCategoryName+"</td>"
			              	+"<td>"+obj.docketSummary+ "</td>"
			              	+"<td>"+obj.userName+"</td>"			              	
			              	+"<td><a class='btn btn-primary' onclick='documentDetailsViewPopUp("+obj.docketNumber+")'>View Document</a></td>"
			              	/* +"<td>"+obj.consumerMobileNo+ "</td>" */
			              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
			                +"</tr>";
			         }
						 $('#mobileTicketsSearch').dataTable().fnDestroy();
							$('#resolvedTicketDiv').html(tableData);
			},
			 complete:function(response)
				{
					loadSearchAndFilter('mobileTicketsSearch');
				}   
		
		});
		
		$("#docketNo").val("");
		$("#mobileNo").val("");
		$("#rrNumber").val("");
		return true;
	        }

/* 
$('select[id*=categoryId]').change(function() {
	var categoryId = $("#categoryId").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSubCategories/" + categoryId,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#subCategoryId').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',0).text("Select SubCategory");
            $('#subCategoryId').append(defaultOption);
			($.map(data, function(item) {
				var newOption = $('<option>'); 
				newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
				$('#subCategoryId').append(newOption);
			}));
		}
	});
});
 */
function commentReopenValueChange(value){
	if(value=='Comment'){
		$("#commentStatusReopen").hide();
	}else{
		$("#commentStatusReopen").show();
	}
}

function commentValueChange(value){
	if(value=='Comment'){
		$("#commentStatus").hide();
		$('#docketStatus').val(0);
	}else if(value=='Status'){
		$("#commentStatus").show();
	}else if(value=='Invalid'){
		$("#commentStatus").hide();
	}
}

var dialog = "";
function docketDetailsPopUp(docketNo)
{
	
	
	if(docketNo=='ESCULATEDCHECKBOX')//This is for Esculated Complaint CheckBox
	{
	
	var checkboxes = document.getElementsByName('docketNumbEsc');
	var checks = "";
	for(var i =0;i<checkboxes.length;i++)
	{
		
		if(checkboxes[i].checked)
		 {
			checks = checks+checkboxes[i].value+",";
			
		}
	}
	if(checks == "")
		{
			alert("Please Select the Docket Number ...");
			return false;
		}
	checks = checks.substring(0,checks.length-1);
	var s=checks.split(",");
	  var result = [];
	  $.each(s, function(i, e) {
	    if ($.inArray(e, result) == -1) result.push(e);
	  });
	  var sa="";
	for(var j=0;j<result.length;j++)
		{
		 sa+=result[j]+",";
		}
	docketNo=sa.substring(0,sa.length-1);
	
	}
	
	if(docketNo=='MobTicketsSearchCheckBox')//for Mobile Based Search Detail
	{
		var checkboxes = document.getElementsByName('docketNumbMobSearch');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
		{
			
			if(checkboxes[i].checked)
			 {
				checks = checks+checkboxes[i].value+",";
				
			}
			}
		if(checks == "")
			{
				alert("Please Select the Docket Number ...");
				return false;
			}
		checks = checks.substring(0,checks.length-1);
		
		docketNo=checks;
		
	}

	
	$("#commentStatus").show();
	$('#commentId').prop('checked', true);
	dialog = $("#addtab").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{

			html : "Update",
			"class" : "btn btn-primary",
			click : function() {				
			var status = ticketStatusUpdateClick(docketNo);			
			if(status!=false){
				$(this).dialog("close");
			}
				
			}
		},{
			html : "<i class='fa fa-times'></i>&nbsp; Cancel",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");

			}
		}]
	}).dialog("open");
}

function ticketStatusUpdateClick(docketNo){
	
	var commentValue = $("input[name=Comment]:checked").val();	
	var docketStatus = $("#docketStatus").val();
	var remarks = $("#remarks").val();
	
	if(commentValue == "Status"){
		if(docketStatus==0 || docketStatus=="" || docketStatus==null){
			alert("Please select status");
			return false;
		}else if(remarks==null || remarks == ""){
			alert("Please enter remarks");
			return false;
		}
		
	}else if(remarks==null || remarks == ""){
		alert("Please enter remarks");
		return false;
	}
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/updateDocket",
		dataType : "text",
		data:{
			docketNo : docketNo,
			docketStatus : docketStatus,
			remarks : remarks,
			commentValue : commentValue,
		},
		success : function(response){	
			/* $("#alertsBox").html("");
			$("#alertsBox").html(response);
			$("#alertsBox").dialog
			({
				modal : true,
				buttons : {
				   "Close" : function() {
				      $(this).dialog("close");
					}
				}
				}); */
				alert(response);	
				window.location.reload();
		} 
	});
	$("#docketStatus").val(0);
	$("#remarks").val("");
}

function documentDetailsViewPopUp(docNo){
	window.open("./helpDesk/download/"+docNo);
}

var docketNum;
function docketDetailsViewPopUp(docNo)
{
	docketNum = docNo;
	dialog = $("#selectedDocketDetails").dialog({
		autoOpen : false,
		width : 1000,
		height : 580,
		resizable : false,
		modal : true,
	}).dialog("open");
	$("#tabOne").addClass("active");
	$("#tabTwo").removeClass();
	$("#tabThree").removeClass();
	tab1Click();	
}

function tab1Click(){
	$("#tab1").show();
	$("#tab2").hide();
	$("#tab3").hide();
	$("#viewDocket").empty();
	var tableData = "";
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketNumber/"+docketNum,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"	
				              	+"<th colspan='6'>Docket Details</th>"
				              	+"</tr>"						
								+"<tr>"
								+"<th>Docket No.</th>"
								+"<td>"+obj.docketNumber+"</td>"
								+"<th>Docket Status</th>"
								+"<td>"+obj.docketStatus+"</td>"
								+"<th>Created Date</th>"
								+"<td>"+obj.docketCreatedDt+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Section Name</th>"
								+"<td>"+obj.section+"</td>"
								+"<th>Raised By</th>"
								+"<td>"+obj.consumerName+"</td>"
								+"<th>Mobile No.</th>"
								+"<td>"+obj.consumerMobileNo+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Category</th>"
								+"<td>"+obj.categoryName+"</td>"
								+"<th>Sub Category</th>"
								+"<td>"+obj.subCategoryName+"</td>"
								+"<th>Created By</th>"
								+"<td>"+obj.userName+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Assigned To</th>"
								+"<td>"+obj.urName+" - "+obj.designation+"</td>"
								+"<th>SLA Date&Time for Resolving</th>"
								+"<td>"+obj.estResolveTime+"</td>"
								+"<th>Resolved Date</th>"
								+"<td>"+obj.resolvedDate+"</td>"
								+"</tr>"
								+"<tr>"
								+"<th>Re-Opened Date</th>"
								+"<td>"+obj.docketUpdatedDt+"</td>"
								+"<th>Docket Summary</th>"
								+"<td colspan='5'>"+obj.docketSummary+"</td>"
								+"</tr>";
				                
				     }
				$('#viewDocket').append(tableData);
		}
	});
}

function tab2Click(){
	var docketHistroyTable = "";
	$("#docketHistoryDiv").empty();
	$("#tab2").show();
	$("#tab1").hide();
	$("#tab3").hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketHistory/"+docketNum,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	docketHistroyTable += "<tr>"		
		              	+"<td>"+obj.serialNo+"</td>"
		              	+"<td>"+obj.docketUpdatedDt+"</td>"
		              	+"<td>"+obj.docketStatus+ "</td>"
		              	/* +"<td>"+obj.action+"</td>" */
		              	/* +"<td>"+obj.actionBy+ "</td>" */
		              	+"<td>"+obj.personName+"</td>"			              	
		              	+"<td>"+obj.userName+"</td>"
		              	/* +"<td>"+obj.office+ "</td>" */
		              	+"<td>"+obj.designation+ "</td>"
		              	+"<td>"+obj.officialMobileNo+ "</td>"
		              	+"<td>"+obj.remarks+ "</td>"
		                +"</tr>";
		         }
						$('#docketHistoryDiv').append(docketHistroyTable);
		} 
	});
}

function tab3Click(){
	var docketEscHistroyTable = "";
	$("#docketEscHistoryDiv").empty();
	$("#tab3").show();
	$("#tab1").hide();
	$("#tab2").hide();
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/searchDocketEscHistory/"+docketNum,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              		docketEscHistroyTable += "<tr>"		
			              	+"<td>"+obj.serialNo+"</td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
			              	+"<td>"+obj.escalatedDate+"</td>"
			              	+"<td>"+obj.escLevel+ "</td>"
			              	/* +"<td>"+obj.fromUserData+"</td>" */
			              	+"<td>"+obj.toUserData+ "</td>"	
			                +"</tr>";
		         }
						$('#docketEscHistoryDiv').append(docketEscHistroyTable);
		} 
	});
}

function docketDetailsValidation(docketNo)
{
	$("#commentStatusReopen").show();
	$('#commentReopenId').prop('checked', true);
	dialog = $("#reopenDiv").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		buttons : [{

			html : "Update",
			"class" : "btn btn-primary",
			click : function() {				
			var status = reopenDocketClick(docketNo);			
			if(status!=false){
				$(this).dialog("close");
			}
				
			}
		},{
			html : "<i class='fa fa-times'></i>&nbsp; Cancel",
			"class" : "btn btn-default",
			click : function() {
				$(this).dialog("close");

			}
		}]
	}).dialog("open");
}

function reopenDocketClick(docketNo){
	
	var commentValue = $("input[name=CommentReopen]:checked").val();	
	var docketStatus = $("#docketStatusReopen").val();
	var remarks = $("#remarksReopen").val();
	
	if(commentValue == "Status"){
		if(docketStatus==0 || docketStatus=="" || docketStatus==null){
			alert("Please select status");
			return false;
		}else if(remarks==null || remarks == ""){
			alert("Please enter remarks");
			return false;
		}
		
	}else if(remarks==null || remarks == ""){
		alert("Please enter remarks");
		return false;
	}
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/updateDocket",
		dataType : "text",
		data:{
			docketNo : docketNo,
			docketStatus : docketStatus,
			remarks : remarks,
		},
		success : function(response){	    
				alert(response);	
				window.location.reload();
		} 
	});
	$("#docketStatusReopen").val(0);
	$("#remarksReopen").val("");
}
	
/* 
	$('select[id*=categoryId]').change(function() {
		var categoryId = $("#categoryId").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubCategories/" + categoryId,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subCategoryId').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select SubCategory");
                $('#subCategoryId').append(defaultOption);
				($.map(data, function(item) {
					var newOption = $('<option>'); 
					newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
					$('#subCategoryId').append(newOption);
				}));
			}
		});
	}); */
	
	/* $('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#siteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Section");
                $('#siteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.subDivisionName);
					$('#siteCode').append(newOption);
				}));
			}
		});
	});
 */
	function openRegisterForm() {
	 $('#data12').hide();
	 document.getElementById("register-form").reset();
	 $('#showRemarks').hide();
	 
		if ($("#registerComplaintId").hasClass('ui-dialog-content')) {                     // If Condition For To Destroy Jquery Dialog Box ( Dont Remove This One)
			$("#registerComplaintId").dialog("destroy");
		    } 
		
		$("#PhoneSection").show();
		$("#EmailSection").show();
		$("#FacebookSection").show();
		$("#HandWrittenSection").show();
		
		$('#consumerMobileNo').val("");
		
		$("#invalidButtonId").hide();
		
		$('#consumerMobileNo').attr("disabled", false);
		$('#registerComplaintId').show();
		$('#pendingForRegistrationTickets').hide();
		$('#PhoneId').prop('checked', true);
		$('#viewTickets').hide();
		$("#actionType").val("Save");
		$('#dateWiseDiv').hide();
		$("#subdivisionwiseTicketsDiv").hide();
		$("#viewEscalatedTickets").hide();
		$("#complaintsAgingDiv").hide();
	
	}
	
	function pendingForRegistrationForm(temp){
		
		$('#pendingForRegistrationTickets').show();
		$('#registerComplaintId').hide();
		$('#showRemarks').show();
		$('#viewTickets').hide();
		$('#dateWiseDiv').hide();
		$("#subdivisionwiseTicketsDiv").hide();
		$("#viewEscalatedTickets").hide();
		$("#complaintsAgingDiv").hide();
		$('#data12').hide();
		$('#pendingRegistrationDiv').empty();
		$('.pendingTabDetail').remove();
		
		if('${docNumForNotify}'!=''&&temp=='1')
			{
			var data1='';
			var data2='';
			
			$.ajax
			({			
				type : "POST",
				url : "./helpDesk/pendingForRegistrationTickets",
				async: false,
				dataType : "JSON",
				success : function(response) 
				{	    
					var htmlTable = "<div class='pendingTabDetail' style='overflow: scroll;'>" 
						+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
						+"<thead>"
						+"<tr>"
						+"<th data-hide='phone'>Ticket No.</th>"
						+"<th data-class='expand'>Status</th>"
						+"<th data-class='expand'>Created Date</th>"
						+"<th data-hide='phone,tablet'>Category</th>"
						+"<th data-hide='phone,tablet'>Sub Category</th>"
						+"<th data-hide='phone'>Summary</th>"
						+"<th data-hide='phone,tablet'>Created By</th>"
						+"<th data-hide='phone,tablet'></th>"
					    +"</tr>"
								+"</thead>"
								+"<tbody id='appendDateBy'>";
						data1+="<tr hidden='true'>"
						       +"<td></td>"
						       +"<td></td>"
						       +"<td></td>" 
						       +"<td></td>" 
						       +"<td></td>" 
						       +"<td></td>" 
						       +"<td></td>" 
						       +"<td></td>"
						       +"</tr>";
						       
							for ( var s = 0, len = response.length; s < len; ++s) {
				              	var obj = response[s];
				              	var d="";
				              	if(obj.docketSource=='Sms')
				              		{
				              		d='"Sms"';
				              		}
				              	else
				              		{
				              		d='"Blank"';
				              		}
				              	var x="";
				              	if('${docNumForNotify}'==obj.docketNumber)
				              		{
				              		
				              		data1+="<tr  style='font-size:14px;'>";
				              		
				              		if(obj.docketStatus=='Resolved')
				              		{
				              			data1+="<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
				              		} else {
				              			data1+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
				              		}
				              		data1+=	""
						              	+"<td>"+obj.docketStatus+"</td>"
						            	+"<td>"+obj.docketCreatedDt+"</td>"
						              	+"<td>"+obj.categoryName+ "</td>"
						              	+"<td>"+obj.subCategoryName+"</td>"
						              	+"<td>"+obj.docketSummary+ "</td>"
						              	+"<td>"+obj.userName+"</td>"			              	
						              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
				              		    +"</tr>";
						                

				              		} else {
				              		
				              			data2+="<tr  style='font-size:14px;'>";
					              		
					              		if(obj.docketStatus=='Resolved')
					              		{
					              			data2+="<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
					              		} else {
					              			data2+="<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
					              		}
					              		data2+=	""
							              	+"<td>"+obj.docketStatus+"</td>"
							            	+"<td>"+obj.docketCreatedDt+"</td>"
							              	+"<td>"+obj.categoryName+ "</td>"
							              	+"<td>"+obj.subCategoryName+"</td>"
							              	+"<td>"+obj.docketSummary+ "</td>"
							              	+"<td>"+obj.userName+"</td>"			              	
							              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
					              		    +"</tr>";
				              		}
				              
				             
				         }
							data1+="</tbody>";
						          
						    data2+="</tbody>";
						          
						          
							htmlTable +=""		
								+"</table></div>";
								$('#pendingContent').append(htmlTable);
								$('#appendDateBy').append(data1);
								$('#appendDateBy').append(data2);
								
								pageSetUp();
								var responsiveHelper_dt_basic = undefined;
								var responsiveHelper_datatable_fixed_column = undefined;
								var responsiveHelper_datatable_col_reorder = undefined;
								var responsiveHelper_datatable_tabletools = undefined;
								var breakpointDefinition = {
										tablet : 1024,
										phone : 480
									};
								$('#datatable_tabletools5')
								.dataTable(
										{
											
											"order": [],
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
										});
				} 
			});
			
			 '<c:remove var="docNumForNotify" scope="session"/>'
			}
		else
			{
			
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/pendingForRegistrationTickets",
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
				var htmlTable = "<div class='pendingTabDetail' style='overflow: scroll;'>" 
					+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th data-hide='phone'>Docket No.</th>"
					+"<th data-class='expand'>Created Date</th>"
					+"<th data-class='expand'>Time Lapsed</th>"
					+"<th data-hide='phone,tablet'>Mode</th>"
					+"<th data-hide='phone,tablet'>Consumer Name</th>"
					+"<th data-hide='phone'>Mobile No.</th>"
					+"<th data-hide='phone,tablet'>Email Id</th>"
					+"<th data-hide='phone,tablet'>Facebook Id</th>"
				    +"</tr>"
							+"</thead>"
							+"<tbody>";
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	var d="";
			              	if(obj.docketSource=='Sms')
			              		{
			              		d='"Sms"';
			              		}
			              	else
			              		{
			              		d='"Blank"';
			              		}
			              	var x="";
			              	if('${docNumForNotify}'==obj.docketNumber)
			              		{
			              		x="<tr bgcolor='#FFFF00'>"
			              		}
			              	else
			              		{
			              		x="<tr>"
			              		}
			              	htmlTable +=x		
			              	+"<td><a style='cursor:pointer;' onclick='docketDetailsUpdatePopUp("+obj.docketNumber+","+obj.consumerMobileNo+","+d+")'>"+obj.docketNumber+"</a></td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
			            	+"<td>"+obj.elapsedTime+"</td>"
			              	+"<td>"+obj.docketSource+ "</td>"
			              	+"<td>"+obj.consumerName+"</td>"
			              	+"<td>"+obj.consumerMobileNo+ "</td>"
			              	+"<td>"+obj.consumerEmailId+"</td>"			              	
			              	+"<td>"+obj.facebookId+"</td>"
			                +"</tr>";
			         }
						htmlTable +=""		
							+"</tbody>"
							+"</table></div>";
							$('#pendingContent').append(htmlTable);
							
							pageSetUp();
							var responsiveHelper_dt_basic = undefined;
							var responsiveHelper_datatable_fixed_column = undefined;
							var responsiveHelper_datatable_col_reorder = undefined;
							var responsiveHelper_datatable_tabletools = undefined;
							var breakpointDefinition = {
									tablet : 1024,
									phone : 480
								};
							$('#datatable_tabletools5')
							.dataTable(
									{
										
										"order": [],
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
									});
			} 
		});
	}
	
	}
	
	function docketDetailsUpdatePopUp(docketNo,mobileNo,source)
	{		
		
			var smsRemarks = "";
			if(source!='Sms')
				{
					$.ajax({
					type : "POST",
					datatype:'json',
					url : "./getDetailsOnSearchBasedWeb/"+docketNo,
					success : function(response) {
						var d=response;
						var response1=d[0];	
						loadSubDivision(response1.divisionId,response1.subDivisionId);
						loadSiteCode(response1.subDivisionId,response1.sectionId);
						$('#categoryId').val(response1.categoryId);
						loadSubCatgory(response1.categoryId,response1.subCategoryId);
						$('#divisionSiteCode').val(response1.divisionId);
						$('#subDivisionSiteCode').val(response1.subDivisionId);
						$('#siteCode').val(response1.sectionId);
						$('#consumerName').val(response1.consumerName);
						$('#rrNumberReg').val(response1.rrNumber);
						$('#consumerEmailId').val(response1.consumerEmailId);
						$('#address').val(response1.address);
						$('#docketSummary').val(response1.docketSummary);
						$('#landMark').val(response1.landMark);
						$('#docSourceUpdate').val(response1.docketSource); // Docket Value Set
						
					}
				});
			
				}
			else// This For Sms Value Set
				{
				
				$.ajax({
					type : "POST",
					datatype:'JSON',
					global: false,
				    async:false,
					url : "./getRemarksBaseOnModeAndMobileNo/"+docketNo+"/"+mobileNo,
					success : function(response) {
						smsRemarks = response;
						$('#docSourceUpdate').val("Sms");
						$('#register-form').trigger("reset");
					}
				});	
				
				}
		
		
		$("#consumerMobileNo").val(mobileNo);
		$("#selectedDocket").val(docketNo);
		$('#remarksSMS').val(smsRemarks);
		$("#actionType").val("Update");
		$("#spanId").text("Pending For Registration");
		$('#SmsId').prop('checked', true);
		
		$("#invalidButtonId").show();
		$('#consumerMobileNo').attr("disabled", true);
		
		$("#PhoneSection").hide();
		$("#EmailSection").hide();
		$("#FacebookSection").hide();
		$("#HandWrittenSection").hide();
		
		
		dialog = $("#registerComplaintId").dialog({
			autoOpen : false,
			width : 800,
			resizable : false,
			modal : true,
		}).dialog("open");
	}
	
	 var tab123=1;
	 var title="";
	 var title1="";
	 function tabClick1(temp){
		 tab123=temp;
		 $('#s3').show();
		 
	 }
	 function tabClick2(temp){
		 tab123=temp;
		
		 $('#s3').hide();
		 $('#s2').show();
		 $('#s1').hide();
	 }
	 function tabClick3(temp){
		 tab123=temp;
		 $('#s1').hide();
		 $('#s3').show();
		 $('#s2').hide();
	 }
	 function scrollFunction(){
			$('html, body').animate({scrollTop: '+=250px'}, 800);
		}
	 function getLocationDetails(sitecode){
		 scrollFunction();
			$.ajax({
				type : "POST",
				url : "./dashboard/getSubDivisionBasedOnSiteCode/" +sitecode,
				dataType : "JSON",
				success : function(response) {
				$('.monthTabDetail').remove();
				var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth()' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>Back to Sub Division</a>" 
				+"<div style='text-align:center'><font size='5px'>"+title+"</font></div>"	
				+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th data-hide='phone'>Docket No.</th>"
				+"<th data-hide='phone'>Status</th>"
				+"<th data-class='expand'>Created Date</th>"
				+"<th>Category</th>"
				+"<th data-hide='phone'>SubCategory</th>"
				+"<th data-hide='phone,tablet'>Summary</th>"
				+"<th data-hide='phone,tablet'>Mode</th>"
				+"<th data-hide='phone,tablet'>Consumer Name</th>"
				+"<th data-hide='phone,tablet'>Mobile No.</th>"
				+"<th data-hide='phone,tablet'></th>"
			    +"</tr>"
						+"</thead>"
						+"<tbody>";
						var dataNew=response;
						 $.each(dataNew, function(index, data){
							
							htmlTable+= "<tr>"
								+"<td>"+data.docketNumber+"</td>"
								+"<td>"+data.docketStatus+"</td>"
								+"<td>"+data.docketCreatedDt+"</td>"
								+"<td>"+data.categoryName+"</td>"
								+"<td>"+data.subCategoryName+"</td>"
								+"<td>"+data.docketSummary+"</td> "
								+"<td>"+data.docketSource+"</td>"
								+"<td>"+data.consumerName+"</td>"
								+"<td>"+data.consumerMobileNo+"</td>"
								+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
								+"</tr>";
							 
			         		});
					htmlTable +=""		
						+"</tbody>"
						+"</table></div>";
					
				$('.monthtab').hide();
				$('#dt_basic_wrapper').hide();
				$('#monthContent').append(htmlTable);
				
				pageSetUp();
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
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
											"sMessage" : "Generated by CESC <i>(press Esc to close)</i>"
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
						});
				}
			});
		}
	 function getLocationDetails2(sitecode,categoryId){
		 scrollFunction();
			$.ajax({
				type : "POST",
				url : "./dashboard/getSubDivisionBasedOnCategory1/"+sitecode+"/"+categoryId,
				dataType : "JSON",
				success : function(response) {
				$('.monthTabDetail').remove();
				var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth()' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>Back to Sub Division</a>" 
				+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th data-hide='phone'>Docket No.</th>"
				+"<th data-class='expand'>Created Date</th>"
				+"<th data-class='expand'>Time Lapsed</th>"
				+"<th data-hide='phone,tablet'>Mode</th>"
				+"<th data-hide='phone,tablet'>Consumer Name</th>"
				+"<th data-hide='phone'>Mobile No.</th>"
				+"<th data-hide='phone,tablet'>Email Id</th>"
				+"<th data-hide='phone,tablet'>Facebook Id</th>"
				/* +"<th data-hide='phone,tablet'></th>" */
			    +"</tr>"
						+"</thead>"
						+"<tbody>";
						var dataNew=response;
						 $.each(dataNew, function(index, obj){
							
							htmlTable+= "<tr>"
								+"<td>"+obj.docketNumber+"</td>"
								+"<td>"+obj.docketCreatedDt+"</td>"
								+"<td>"+obj.elapsedTime+"</td>"
				              	+"<td>"+obj.docketSource+ "</td>"
				              	+"<td>"+obj.consumerName+"</td>"
				              	+"<td>"+obj.consumerMobileNo+ "</td>"
				              	+"<td>"+obj.consumerEmailId+"</td>"			              	
				              	+"<td>"+obj.facebookId+"</td>"
				              	/* +"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>" */
								+"</tr>";
							 
			         		});
					htmlTable +=""		
						+"</tbody>"
						+"</table></div>";
					
				$('.monthtab').hide();
				$('#dt_basic_wrapper').hide();
				$('#monthContent').append(htmlTable);
				
				pageSetUp();
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
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
											"sMessage" : "Generated by CESC <i>(press Esc to close)</i>"
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
						});
				}
			});
		}
	 function getLocationDetails1(sitecode,categoryId){
		 scrollFunction();
			$.ajax({
				type : "POST",
				url : "./dashboard/getSubDivisionBasedOnCategory/"+sitecode+"/"+categoryId,
				dataType : "JSON",
				success : function(response) {
				$('.monthTabDetail').remove();
				var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth()' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>Back to Sub Division</a>" 
				+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th data-hide='phone'>Docket No.</th>"
				+"<th data-hide='phone'>Status</th>"
				+"<th data-class='expand'>Created Date</th>"
				+"<th>Category</th>"
				+"<th data-hide='phone'>SubCategory</th>"
				+"<th data-hide='phone,tablet'>Summary</th>"
				+"<th data-hide='phone,tablet'>Mode</th>"
				+"<th data-hide='phone,tablet'>Consumer Name</th>"
				+"<th data-hide='phone,tablet'>Mobile No.</th>"
				+"<th data-hide='phone,tablet'>Assigned To.</th>"
				+"<th data-hide='phone,tablet'></th>"
			    +"</tr>"
						+"</thead>"
						+"<tbody>";
						var dataNew=response;
						 $.each(dataNew, function(index, data){
							
							htmlTable+= "<tr>"
								+"<td>"+data.docketNumber+"</td>"
								+"<td>"+data.docketStatus+"</td>"
								+"<td>"+data.docketCreatedDt+"</td>"
								+"<td>"+data.categoryName+"</td>"
								+"<td>"+data.subCategoryName+"</td>"
								+"<td>"+data.docketSummary+"</td> "
								+"<td>"+data.docketSource+"</td>"
								+"<td>"+data.consumerName+"</td>"
								+"<td>"+data.consumerMobileNo+"</td>"
								+"<td>"+data.assinedName+" - "+data.officialEmaill+" - "+data.designation+" - "+data.officialMobileNo+"</td>"
								+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
								+"</tr>";
							 
			         		});
					htmlTable +=""		
						+"</tbody>"
						+"</table></div>";
					
				$('.monthtab').hide();
				$('#dt_basic_wrapper').hide();
				$('#monthContent').append(htmlTable);
				
				pageSetUp();
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
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
											"sMessage" : "Generated by CESC <i>(press Esc to close)</i>"
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
						});
				}
			});
		}
	 
	 function backToMonth(){
			$('.monthtab').show();
			$('.monthTabDetail').remove();
			$('#dt_basic_wrapper').show();
		}
	 
	function clearFilter(){
		$.ajax({
			url : "./dashboard/clearFilter",
			type : "GET",
			dataType : "JSON",
			async : false,
	});	
		window.location.reload(true);
	}

	function callDate(){
		var btDialog = $("#treeview1");
		btDialog.kendoWindow({
			width : "400",
			height : "auto",
			modal : true,
			draggable : true,
			position : {
				top : 100
			},
			title : "Select Date To Filter"
		}).data("kendoWindow").center().open();
		
		btDialog.kendoWindow("open");
		$("#treeview1").html("");		
		$("#treeview1").append("<form id='exportToExcelFileForm'><table id='exportExcel' style='margin-top: 8px;height: 130px'></table> </form>");
		$("#exportExcel").append("<tr><td> From Date*</td> <td><input id='fromDate1' style='width:200px' required='true' data-errormessage-value-missing='From Date required' /></td> </tr> <tr><td> To Date*</td> <td><input id='toDate1' required='true' data-errormessage-value-missing='To Date required'  style='width:200px'/></td> </tr>");
		$("#fromDate1").kendoDatePicker({    
			 start: "year",                             
	       depth: "month",
			format: "dd/MM/yyyy"
		 });
		$("#toDate1").kendoDatePicker({    
			 start: "year",                             
	       depth: "month",
			format: "dd/MM/yyyy"
	  	 	
	        
	 });
		$("#exportExcel").append("<tr><td>&nbsp;</td><td><button id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='getDataBasedOnDate()'>View</button> </td></tr>");
	}
	var date1="";
	var date2="";
	function getDataBasedOnDate() {
		date1 = $("#fromDate1").val();
		date2 = $("#toDate1").val();
		if(date1==""){
			return false;
		}
		else if(date2==""){
			return false;
		}
		 $.ajax({
				url : "./registerMaster/readRegisterBasedOnDate",
				type : "GET",
				dataType : "text",
				async : false,
			data:{
				fromDate:date1,
				toDate:date2,
				tab:tab123,
			},
			success : function(result) {
				//closeDialog();
				window.location.reload(true);
			},

		});
			
			function closeDialog() {
				var btDialog = $("#treeview1");
				btDialog.kendoWindow({
					width : "400",
					height : "auto",
					modal : true,
					draggable : true,
					position : {
						top : 100
					},
					title : "Select Date To Filter"
				}).data("kendoWindow").center().close();
				btDialog.kendoWindow("close");
			} 
		
		} 
	var Cat="";
	function callCategory(){
		var btDialog = $("#treeview2");
		btDialog.kendoWindow({
			width : "400",
			height : "auto",
			modal : true,
			draggable : true,
			position : {
				top : 100
			},
			title : "Select Category To Filter"
		}).data("kendoWindow").center().open();
		
		btDialog.kendoWindow("open");
		$("#treeview2").html("");
			 $("#treeview2").append("<form id='getRegisterForm1'><table id='getRegister2' style='margin-top: 5px;height: 100px'></table> </form>");
			 $("#getRegister2").append("<tr><td> <label for='category'>Category * :</label></td> <td><input id='category' required='true' style='width:200px'/></td> </tr><tr><td>&nbsp;</td</tr>");
			 $("#category").kendoDropDownList({
			    dataTextField: "categoryName",
			    dataValueField: "categoryId",
			    optionLabel : {
			    	categoryName : "Select",
			    	categoryId : "",
				},
			    dataSource : {
					transport : {
						read : {
							url : "./dashboard/categoryList",
							type : "POST"
						}
					}
				},
			    change: function(e) {
			        var value = this.value();
			        cat=this.text();
			      }
			});
		 $("#getRegister2").append("<tr><td>&nbsp;</td><td><button type='submit' id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='categoryfunction()'>View</button> </td></tr>");
	}

	function categoryfunction(){
		status = $("#category").val();
		if(status==""){
			return false;
		}
		$.ajax({
			url : "./dashboard/category",
			type : "GET",
			dataType : "text",
			async : false,
		data:{
			category:status,
			categoryName:cat,
			tab:tab123,
		},
		success : function(result) {
			//closeDialog();
			window.location.reload(true);
		},
	});
	}
	function callMode(){
		var btDialog = $("#treeview3");
		btDialog.kendoWindow({
			width : "400",
			height : "auto",
			modal : true,
			draggable : true,
			position : {
				top : 100
			},
			title : "Select Mode To Filter"
		}).data("kendoWindow").center().open();
		
		btDialog.kendoWindow("open");
		$("#treeview3").html("");
			 $("#treeview3").append("<form id='getRegisterForm2'><table id='getRegister3' style='margin-top: 5px;height: 100px'></table> </form>");
			 $("#getRegister3").append("<tr><td> <label for='category'>Mode * :</label></td> <td><input id='mode' style='width:200px'/></td> </tr><tr><td>&nbsp;</td</tr>");
			 
			 $("#mode").kendoDropDownList({
			    dataTextField: "parentName1",
			    dataValueField: "parentId1",
			    dataSource: [
						        { parentName1: "Phone", parentId1: "Phone"},
						        { parentName1: "Web", parentId1: "Web"},
						        { parentName1: "Sms", parentId1: "Sms"},
						        { parentName1: "Email", parentId1: "Email"},
						        { parentName1: "Facebook", parentId1: "Facebook"},
						        { parentName1: "Hand Written", parentId1: "Hand Written" }
						    ],
			});
		 $("#getRegister3").append("<tr><td>&nbsp;</td><td><button type='submit' id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='modefunction()'>View</button> </td></tr>");
	}
	function modefunction(){
		status = $("#mode").val();
		if(status==""){
			return false;
		}
		$.ajax({
			url : "./dashboard/mode",
			type : "GET",
			dataType : "text",
			async : false,
		data:{
			category:status,
			tab:tab123,
		},
		success : function(result) {
			//closeDialog();
			window.location.reload(true);
		},

	});
	}


	</script>
	<script type="text/javascript">
		pageSetUp();
		/* var pagefunction = function() {
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				
				var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};

				$('#dt_basic').dataTable({
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
					"autoWidth" : true,
					"preDrawCallback" : function() {
						if (!responsiveHelper_dt_basic) {
							responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_dt_basic.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_dt_basic.respond();
					}
				});
		    var otable = $('#datatable_fixed_column').DataTable({
				"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
				"autoWidth" : true,
				"preDrawCallback" : function() {
					if (!responsiveHelper_datatable_fixed_column) {
						responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_datatable_fixed_column.respond();
				}		
			
		    });
		    // custom toolbar
		    $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');
		    // Apply the filter
		    $("#datatable_fixed_column thead th input[type=text]").on( 'keyup change', function () {
		        otable
		            .column( $(this).parent().index()+':visible' )
		            .search( this.value )
		            .draw();
		            
		    } );
		    
			$('#datatable_col_reorder').dataTable({
				"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
				"autoWidth" : true,
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_datatable_col_reorder) {
						responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_col_reorder'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_datatable_col_reorder.respond();
				}			
			});
			
			
			$('#datatable_tabletools').dataTable({
				
				// Tabletools options: 
				//   https://datatables.net/extensions/tabletools/button_options
				"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
		        "oTableTools": {
		        	 "aButtons": [
		             "copy",
		             "csv",
		             "xls",
		                {
		                    "sExtends": "pdf",
		                    "sTitle": "SmartAdmin_PDF",
		                    "sPdfMessage": "SmartAdmin PDF Export",
		                    "sPdfSize": "letter"
		                },
		             	{
	                    	"sExtends": "print",
	                    	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
	                	}
		             ],
		            "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
		        },
				"autoWidth" : true,
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_datatable_tabletools) {
						responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_datatable_tabletools.respond();
				}
			});
			
			
		};

		// load related plugins
		
		loadScript("./resources/js/plugin/datatables/jquery.dataTables.min.js", function(){
			loadScript("./resources/js/plugin/datatables/dataTables.colVis.min.js", function(){
				loadScript("./resources/js/plugin/datatables/dataTables.tableTools.min.js", function(){
					loadScript("./resources/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
						loadScript("./resources/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
					});
				});
			});
		}); */
		
		function showAllEscalatedTicketsForCCCD(temp){
			$('#pendingForRegistrationTickets').hide();
			$("#viewEscalatedTickets").show();
			$("#viewResolvedTickets").hide();
			$("#viewTickets").hide();
			$("#searchDocket").hide();
			$("#subdivisionwiseTicketsDiv").hide();
			$("#pendingForRegistration").hide();
			$('#registerComplaintId').hide();
			$('.escalatedTicketsTabDetail').remove();
			$("#escalatedDiv").empty();
			$("#complaintsAgingDiv").hide();
			$('#data12').hide();
			if('${escDocNum}'!=''&&temp==1)
				{
				var data1="";
				var data2="";
				$.ajax
				({			
					type : "POST",
					url : "./helpDesk/getAllEscalatedTicketsForCCCD",
					async: false,
					dataType : "JSON",
					success : function(response) 
					{	    
						var d='"ESCULATEDCHECKBOX"';
						var tableexport='"datatable_tabletools6"';
						var reportName='"EscalatedReport"';
						var htmlTable ="<a class='btn bg-color-greenDark txt-color-white'  href='#'  onclick='return docketDetailsPopUp("+d+")'>Change Multiple Status</a>&nbsp;&nbsp;&nbsp;"
						    +"<a class='btn btn-primary'  href='#'  onclick='return tableToExcel("+tableexport+","+reportName+")' id='excelExport'>Export to Excel</a>" 
							+"<div class='escalatedTicketsTabDetail' style='overflow: scroll;'>" 
							+"<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
							+"<thead>"
							+"<tr>"
							+"<th><input type='checkbox' id='selectall'  onClick='selectAllEsc(this)' /></th>"
							+"<th data-hide='phone'>Docket No.</th>"
							+"<th data-hide='phone'>Complaint Category</th>"
							+"<th data-hide='phone'>Status</th>"
							+"<th data-hide='phone'>Created Date</th>"
							+"<th data-class='expand'>Escalated Date</th>"
							+"<th data-hide='phone'>SLA Date&Time for Resolving</th>"
							+"<th data-hide='phone,tablet'>Level</th>" 
							+"<th data-hide='phone,tablet'>Escalated From Officer</th>"
							+"<th data-hide='phone,tablet'>Escalated To Officer</th>"
							+"<th data-hide='phone,tablet'>Officer Name</th>"
							+"<th data-hide='phone,tablet'>Officer Mobile No.</th>"
							/* +"<th data-hide='phone,tablet'>Office</th>" */
							+"<th data-hide='phone,tablet'></th>"
						    +"</tr>"
							+"</thead>"
							+"<tbody id='escTBody'>";
							for ( var s = 0, len = response.length; s < len; ++s) {
					            	var obj = response[s];
					            	if('${escDocNum}'==obj.docketNumber)
					            		{
						              	data1 += "<tr  style='color: maroon;font-size:14px;background: #FFFAF0'>"
						              	
						              		if(obj.docketStatus=='Resolved')
						              		{
						              			data1+="<td></td>"
						              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
						              		}
						              	else
						              		{
						              		data1+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
						              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
						              		 
						              		}
						              	data1+="<td>"+obj.complaintCatagory+"</td>"
						              	+"<td>"+obj.docketStatus+"</td>"
						              	+"<td>"+obj.docketCreatedDt+"</td>"
						              	+"<td>"+obj.escalatedDate+ "</td>"
						              	+"<td>"+obj.estResolveTime+ "</td>"
						              	+"<td>"+obj.level+ "</td>"
						              	+"<td>"+obj.fromUser+ "</td>"
						              	+"<td>"+obj.toUser+ "</td>"
						              	+"<td>"+obj.OfficerName+ "</td>"
						              	+"<td>"+obj.officerMobileNo+ "</td>"
						              	/* +"<td>"+obj.office+ "</td>" */
						              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
						                +"</tr>";
					            		
					            		}
					            	else
					            		
					            		{

						              	data2 += "<tr>"
						              	
						              		if(obj.docketStatus=='Resolved')
						              		{
						              			data2+="<td></td>"
						              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
						              		}
						              	else
						              		{
						              		data2+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
						              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
						              		 
						              		}
						              	data2+="<td>"+obj.complaintCatagory+"</td>"
						              	+"<td>"+obj.docketStatus+"</td>"
						              	+"<td>"+obj.docketCreatedDt+"</td>"
						              	+"<td>"+obj.escalatedDate+ "</td>"
						              	+"<td>"+obj.estResolveTime+ "</td>"
						              	+"<td>"+obj.level+ "</td>"
						              	+"<td>"+obj.fromUser+ "</td>"
						              	+"<td>"+obj.toUser+ "</td>"
						              	+"<td>"+obj.OfficerName+ "</td>"
						              	+"<td>"+obj.officerMobileNo+ "</td>"
						              	/* +"<td>"+obj.office+ "</td>" */
						              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
						                +"</tr>";
					            		
					            		
					            		}
					            	
					         }
							    data1+="</tbody>";
							    data2+="</tbody>";
								htmlTable +=""		
									+"</table></div>";
									$('#escalatedDiv').append(htmlTable);
									$('#escTBody').append(data1);
									$('#escTBody').append(data2);
									
									pageSetUp();
									
									var responsiveHelper_datatable_tabletools = undefined;
									var breakpointDefinition = {
											tablet : 1024,
											phone : 480
										};
									$('#datatable_tabletools6')
									.dataTable(
											{
												"autoWidth" : true,
												"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
												"order": [],
												"preDrawCallback" : function() {
													if (!responsiveHelper_datatable_tabletools) {
														responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
																$('#datatable_tabletools6'),
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
												},
												"aoColumnDefs": [{
									                'bSortable': false,
									                'aTargets': [0]
									            }
									        ]
											});
					} 
				});
				
				
				 '<c:remove var="docNumForNotify" scope="session"/>'
				
				}
			else
				{
			$.ajax
			({			
				type : "POST",
				url : "./helpDesk/getAllEscalatedTicketsForCCCD",
				async: false,
				dataType : "JSON",
				success : function(response) 
				{	    
					var d='"ESCULATEDCHECKBOX"';
					var tableexport='"datatable_tabletools6"';
					var reportName='"EscalatedReport"';
					var htmlTable ="<a class='btn bg-color-greenDark txt-color-white'  href='#'  onclick='return docketDetailsPopUp("+d+")'>Change Multiple Status</a>&nbsp;&nbsp;&nbsp;" 
					+"<a class='btn btn-primary'  href='#'  onclick='return tableToExcel("+tableexport+","+reportName+")' id='excelExport'>Export to Excel</a>"  
					+"<div class='escalatedTicketsTabDetail' style='overflow: scroll;'>" 
						+"<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
						+"<thead>"
						+"<tr>"
						+"<th><input type='checkbox' id='selectall'  onClick='selectAllEsc(this)' /></th>"
						+"<th data-hide='phone'>Docket No.</th>"
						+"<th data-hide='phone'>Complaint Category</th>"
						+"<th data-hide='phone'>Status</th>"
						+"<th data-hide='phone'>Created Date</th>"
						+"<th data-class='expand'>Escalated Date</th>"
						+"<th data-hide='phone'>SLA Date&Time for Resolving</th>"
						+"<th data-hide='phone,tablet'>Level</th>" 
						+"<th data-hide='phone,tablet'>Escalated From Officer</th>"
						+"<th data-hide='phone,tablet'>Escalated To Officer</th>"
						+"<th data-hide='phone,tablet'>Officer Name</th>"
						+"<th data-hide='phone,tablet'>Officer Mobile No.</th>"
						/* +"<th data-hide='phone,tablet'>Office</th>" */
						+"<th data-hide='phone,tablet'></th>"
					    +"</tr>"
						+"</thead>"
						+"<tbody>";
						for ( var s = 0, len = response.length; s < len; ++s) {
				            	var obj = response[s];
				              	htmlTable += "<tr>"
				              	
				              		if(obj.docketStatus=='Resolved')
				              		{
				              		htmlTable+="<td></td>"
				              			+"<td><a style='cursor:pointer;' onclick='docketDetailsValidation("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
				              		}
				              	else
				              		{
				              		htmlTable+="<td><div id='docketNumEscDiv'> <input type='checkbox'    autocomplete='off' placeholder='' name='docketNumbEsc' id="+obj.docketNumber+" value="+obj.docketNumber+" /> </div></td>"
				              		+"<td><a style='cursor:pointer;' onclick='docketDetailsPopUp("+obj.docketNumber+")'>"+obj.docketNumber+"</a></td>";
				              		 
				              		}
				              	htmlTable+="<td>"+obj.complaintCatagory+"</td>"
				              	+"<td>"+obj.docketStatus+"</td>"
				              	+"<td>"+obj.docketCreatedDt+"</td>"
				              	+"<td>"+obj.escalatedDate+ "</td>"
				              	+"<td>"+obj.estResolveTime+ "</td>"
				              	+"<td>"+obj.level+ "</td>"
				              	+"<td>"+obj.fromUser+ "</td>"
				              	+"<td>"+obj.toUser+ "</td>"
				              	+"<td>"+obj.OfficerName+ "</td>"
				              	+"<td>"+obj.officerMobileNo+ "</td>"
				              	/* +"<td>"+obj.office+ "</td>" */
				              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
				                +"</tr>";
				         }
							htmlTable +=""		
								+"</tbody>"
								+"</table></div>";
								$('#escalatedDiv').append(htmlTable);
								
								pageSetUp();
								
								var responsiveHelper_datatable_tabletools = undefined;
								var breakpointDefinition = {
										tablet : 1024,
										phone : 480
									};
								$('#datatable_tabletools6')
								.dataTable(
										{
											"autoWidth" : true,
											"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
											"order": [],
											"preDrawCallback" : function() {
												if (!responsiveHelper_datatable_tabletools) {
													responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
															$('#datatable_tabletools6'),
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
											},
											"aoColumnDefs": [{
								                'bSortable': false,
								                'aTargets': [0]
								            }
								        ]
										});
				} 
			});
	}
		}
		
		function invalidateComplaint(){
			
			var conf = confirm("Are you sure want to invalidate this complaint?");
		    if(conf){
		    	var invalidDocketNo = $("#selectedDocket").val();
		    	$.ajax({
		    		url : "./helpDesk/invalidateDocketDetails/"+invalidDocketNo,
		    		type : "GET",
		    		dataType : "text",
		    		async : false,
		    		success : function(response) {
		    			alert(response);
		    			pendingForRegistrationForm(2);
		    			$('#registerComplaintId').hide();
		    			
		    			dialog = $("#registerComplaintId").dialog({
		    				autoOpen : false,
		    				width : 800,
		    				resizable : false,
		    				modal : true,
		    			}).dialog("close");
		    			
		    		}
		    		}); 
		     }
		}
		 // For Esculated Search Select all Check box
		 function selectAllEsc(source) {
			 
			   var flagChecked = 0;
				checkboxes = document.getElementsByName('docketNumbEsc');
				for(var i =0;i<checkboxes.length;i++)
					{
					checkboxes[i].checked = source.checked;
					if(checkboxes[i].checked)
					 {
						flagChecked = 1;
					}
					}
				
				 if(flagChecked==0)
				{
					$('#docketNumEscDiv span:first-child').removeClass("checked");
				}
				else{
					$('#docketNumEscDiv span:first-child').addClass("checked");
				} 
				
				
			}
		 
		 // For Mobile Search Select all Check box
		 function selectAllMobSearch(source) {
			 
			   var flagChecked = 0;
				checkboxes = document.getElementsByName('docketNumbMobSearch');
				for(var i =0;i<checkboxes.length;i++)
					{
					checkboxes[i].checked = source.checked;
					if(checkboxes[i].checked)
					 {
						flagChecked = 1;
					}
					}
				
				 if(flagChecked==0)
				{
					$('#docketNumMobSearchDiv span:first-child').removeClass("checked");
				}
				else{
					$('#docketNumMobSearchDiv span:first-child').addClass("checked");
				} 
				
				
			}
		 
			function loadSearchAndFilter(param) // This Function Is For Multiple Table Id Search and Pagination
		  	{ 
			
			  $('#'+param).dataTable().fnDestroy();
		  	   pageSetUp();
				
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#'+param)
				.dataTable(
						{
							"autoWidth" : true,
							"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
							"order": [],
							"preDrawCallback" : function() {
								if (!responsiveHelper_datatable_tabletools) {
									responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
											$('#'+param),
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
							},
							"aoColumnDefs": [{
				                'bSortable': false,
				                'aTargets': [0]
				            }
				        ]
						});
		  	
		  	}

			
			function loadSubCatgory(categoryId,subCategoryId) {
				$.ajax({
					type : "POST",
					url : "./helpDesk/getAllSubCategories/" + categoryId,
					dataType : "json",
					success : function(data) {
						var newOption = $('<option>');
			            newOption.attr('value',0).text(" "); 
			            $('#subCategoryId').empty(newOption);
			            var defaultOption = $('<option>');
			            defaultOption.attr('value',0).text("Select SubCategory");
			            $('#subCategoryId').append(defaultOption);
						($.map(data, function(item) {
							var newOption = $('<option>'); 
							newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
							$('#subCategoryId').append(newOption);
						}));
						$('#subCategoryId').val(subCategoryId);// Load SubCategory After Loading All SubcategoryData Data 
					}
				});
			}
			
			function loadSiteCode(subDivisionId,sectionId) {
							
				$.ajax({
					type : "POST",
					url : "./helpDesk/getAllSections/" + subDivisionId,
					dataType : "json",
					success : function(data) {
						var newOption = $('<option>');
			            newOption.attr('value',0).text(" "); 
			            $('#siteCode').empty(newOption);
			            var defaultOption = $('<option>');
			            defaultOption.attr('value',0).text("Select Section");
			            $('#siteCode').append(defaultOption);
						($.map(data, function(item) {					 
							var newOption = $('<option>'); 					
							newOption.attr('value', item.siteCode).text(item.sectionName);
							$('#siteCode').append(newOption);
						}));
						$('#siteCode').val(sectionId);
					}
				});
			}
			
			function loadSubDivision(divisionId,subDivisionId){
				$.ajax({
					type : "POST",
					url : "./helpDesk/getAllSubDivisions/" + divisionId,
					dataType : "json",
					success : function(data) {
						var newOption = $('<option>');
		                newOption.attr('value',0).text(" "); 
		                $('#subDivisionSiteCode').empty(newOption);
		                var defaultOption = $('<option>');
		                defaultOption.attr('value',0).text("Select Sub Division");
		                $('#subDivisionSiteCode').append(defaultOption);
						($.map(data, function(item) {					 
							var newOption = $('<option>'); 					
							newOption.attr('value', item.subId).text(item.subDivisionName);
							$('#subDivisionSiteCode').append(newOption);
						}));
						$('#subDivisionSiteCode').val(subDivisionId);
					}
				});
			}
			
			
			function subRRNumNew123(tem,check) {
				
				if(check=='1')
					{
					$('#agingAsPerSlaPending').val("");	
				$('#complaintsAgingSla').val(tem);
				$('#submItRecordRRNUm').submit()
				}
				else
					{
					$('#complaintsAgingSla').val("");
					$('#agingAsPerSlaPending').val(tem);
					$('#submItRecordRRNUm').submit()
					
					}
				
			}	
		
</script>
<style>
 .padding-10 {
   padding: 0 !important;
}

 .jarviswidget {
    margin: 0px 0px 0px;
    position: relative;
    border-radius: 0px;
    padding: 0px;
} 

blink {
  -webkit-animation: blink 1s steps(5, start) infinite;
  -moz-animation:    blink 1s steps(5, start) infinite;
  -o-animation:      blink 1s steps(5, start) infinite; 
  animation:         blink 1s steps(5, start) infinite;
}

@-webkit-keyframes blink {
  to { visibility: hidden; }
}
@-moz-keyframes blink {
  to { visibility: hidden; }
}
@-o-keyframes blink {
  to { visibility: hidden; }
}
@keyframes blink {
  to { visibility: hidden; }
}

.row {
    margin-left: -13px;
    margin-right: -7px;
}
</style>
