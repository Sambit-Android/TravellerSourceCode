<%@include file="/common/taglibs.jsp"%>
<!-- PAGE RELATED PLUGIN(S) -->
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="./resources/js/exportToExcel.js"></script>


<div id="content">
<section id="widget-grid" class="">
				
							<div class="jarviswidget" id="reportnameId12" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>${reportName}</h2>
								</header>
								<div>
									<!-- widget edit box -->
									<div class="jarviswidget-editbox"></div>
									<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools', '${reportName}')">Export to Excel</a><br><br>
									<div class="widget-body no-padding" style='overflow: scroll;'>
										<table id="datatable_tabletools" class="table table-striped table-bordered table-hover">
											<thead>
											
											    
									       	 <c:if test = "${reportName=='Applications Registered'}"> 
									       	
									       	 	<tr><th data-hide='phone' colspan='14' style='text-align:center;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Application Registration Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext}</th></tr>
									            <tr>
								                    <th >Location</th>
								                    <th >Application ID</th>
								                    <th> Registered Date</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone">Name</th>
								                    <th data-hide="phone">City</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th >Contractor Name</th>
								                    
									            </tr>
									         </c:if> 
									         <c:if test = "${reportName=='Applications Rejected'}"> 
									       	
									       	 	<tr><th data-hide='phone' colspan='14' style='text-align:center;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Applications Rejected Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>Selected Month : <fmt:formatDate value="${fromdate}" pattern="yyyy MMMM"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext}</th></tr>
									            <tr>
								                    <th >Application ID</th>
								                    <th> Registered Date</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone">Name</th>
								                    <th data-hide="phone">City</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th> Rejected Date</th>
								                    
									            </tr>
									         </c:if> 
									         <%-- <c:if test = "${reportName=='Applications Accepted'}"> 
									         		<tr><td data-hide='phone' colspan='14' style='text-align:left;color: maroon;font-size:14px;background: #FFFAF0; font-size: 18px;'>Application Acceptance Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;Location :&nbsp;${locationtext} </td></tr>
									         		<tr>
									         		<th >Application ID</th>
								                    <th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Sub Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">ARF Date</th>
								                    <th data-hide="phone">ARF Receipt Number</th>
								                    <th data-hide="phone,tablet">Amount</th>
								                    <th data-hide="phone,tablet">Pay Towards</th>
								                    </tr>
									         </c:if> --%>
									         
									         <c:if test = "${reportName=='Field Verification Completed'}"> 
									         		<tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:17px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Field Verification Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application&nbsp;Id</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Load&nbsp;(KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">FV&nbsp;Ref&nbsp;No.</th>
								                    <th data-hide="phone">FV&nbsp;Date</th>
								                    <th data-hide="phone,tablet">Estimation&nbsp;Req</th>
								                    <th data-hide="phone,tablet">Pole&nbsp;No.</th>
								                    <th data-hide="phone,tablet">Feeder&nbsp;Name</th>
								                    <th data-hide="phone,tablet">Feeder&nbsp;Code</th>
								                    <th data-hide="phone,tablet">DTC&nbsp;Name</th>
								                    <th data-hide="phone,tablet">DTC&nbsp;Code</th>
								                    
								                    </tr>
									         		
									         </c:if>
									         
									         
									         <c:if test = "${reportName=='Estimation Completed'}"> 
									         		<tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Estimation Completed Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application Id</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">EST Ref No</th>
								                    <th data-hide="phone">EST Date</th>
								                    <th data-hide="phone">EST Cost</th>
								                    </tr>
									         		
									         </c:if>
									         
									         <c:if test = "${reportName=='Power Sanction Completed'}"> 
									         		<tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Power Sanction Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Load(KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">PS Ref No</th>
								                    <th data-hide="phone">PS Date</th>
								                    <th data-hide="phone,tablet">Sanctioned By</th>
								                    <th data-hide="phone,tablet">PS Done On</th>
								                    
								                    
								                    </tr>
								                   
									         		
									         </c:if>
									         
									         <%-- <c:if test = "${reportName=='Applications Deposit Received'}"> 
									         		<tr><td data-hide='phone' colspan='14' style='text-align:left;font-size:14px;background: #FFFAF0; font-size: 18px;'>Applications Deposit Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;Location :&nbsp;${locationtext}</td></tr>
									         		<tr>
									         		<th >Application ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Sub Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
									         		<th data-hide="phone,tablet">Towards</th>
								                    <th data-hide="phone,tablet">Deposit Head</th>
								                    <th data-hide="phone">Receipt No.</th>
								                    <th data-hide="phone,tablet">Receipt Date</th>
								                    <th data-hide="phone,tablet">Amount</th>
								                    </tr>
									         </c:if> --%>
									         
									         
									         <c:if test = "${reportName=='Work Order Completed'}"> 
									                <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Work Order Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Sub Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">Work Order Ref No.</th>
								                    <th data-hide="phone">Work Order Date </th>
								                    
								                    </tr>
								                    
									         </c:if>
									         
									         <c:if test = "${reportName=='Meter/PC Test'}"> 
									         		<tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Meter/PC Test Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application&nbsp;ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Load&nbsp;(KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">Ref No.</th>
								                    <th data-hide="phone">PC Date</th>
								                    <th data-hide="phone,tablet">Done By </th>
								                    <th data-hide="phone,tablet">Meter SlNo </th>
								                    <th data-hide="phone,tablet">CT&nbsp;Ratio</th>
								                    <th data-hide="phone,tablet">PT&nbsp;Ratio</th>
								                    </tr>
									         </c:if>
									         
									          <c:if test = "${reportName=='Work Completion Report'}"> 
									         		<tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Work Completion Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Sub Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">Work Completion Ref No</th>
								                    <th data-hide="phone">Work Completion Date</th>
								                    
								                    </tr>
									         </c:if>
									         
									         
									         
									         <c:if test = "${reportName=='Installations Serviced'}"> 
									         
									           <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Installations Serviced Report&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application ID</th>
									         		<th data-hide="phone">Name</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Load (KW/HP/KVA)</th>
								                    <th data-hide="phone,tablet">Service Reference No</th>
								                    <th data-hide="phone">Service Date</th>
								                    <th data-hide="phone">RR Number(s)</th>
								                    
								                    </tr>
								                    
									         </c:if>
									         
									         
									           <c:if test = "${reportName=='Application Status Wise Report'}"> 
									         
									               <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Application Status Wise Report<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Application Status</th>
									         		<th data-hide="phone">Completed</th>
								                    <th data-hide="phone,tablet">In Progress</th>
								                    <th data-hide="phone,tablet">On Hold</th>
								                    </tr>
								                    
									            </c:if>
									            
									            <c:if test = "${reportName=='Tariff Wise Application Report'}">
									            <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Tariff Wise Report<br>Location :&nbsp;${locationtext} </th></tr>
									            <tr style="font-size:14px;background: #FFFAF0">
														<th>Tariff</th>
														<th>Total</th>
														<th>LT 1</th>
														<th>LT 2</th>
														<th>LT 3</th>
														<th>LT 4</th>
														<th>LT 5</th>
														<th>LT 6</th>
														<th>LT 7</th>
														<th>HT 1</th>
														<th>HT 2</th>
														<th>HT 3</th>
														<th>HT 4</th>
												</tr>
									            
									            
									            </c:if>
									         
									         
									         <c:if test = "${reportName=='Application Stage Wise Report'}"> 
									         
									           <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Application Phase Wise Report with Tariff&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>Month : <fmt:formatDate value="${fromdate}" pattern="MMM yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext} </th></tr>
									         		<tr>
									         		<th >Tariff</th>
										         		<th data-hide="phone">Previous Pending</th>
									                    <th data-hide="phone,tablet">Registered</th>
									                    <th data-hide="phone,tablet">ARF Not Paid</th>
									                    <th data-hide="phone,tablet">Estimation Pending</th>
									                    <th data-hide="phone">Intimation Pending</th>
									                    <th data-hide="phone">Deposit Pending</th>
														<th data-hide="phone">Work Order Pending</th>
														<th data-hide="phone">Meter/PC Test Pending</th>
								                    </tr>
								                    
									         </c:if>
									         
									         
									         <c:if test = "${reportName=='Phase Wise Circle Report'}"> 
									         
									           <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Circle wise Phase Count Report</tr>
									         		<tr>
									         		<th style="min-width: 105px;">Circle</th>
									         		<th data-hide="phone">Registered</th>
								                    <th data-hide="phone,tablet" style="min-width: 73px;">ARF Not Paid</th>
								                    <th data-hide="phone,tablet" style="min-width: 63px;">FV InProgress</th>
								                    <th data-hide="phone">Estimation InProgress</th>
													<th data-hide="phone">FVA InProgress</th>
													<th data-hide="phone">Power&nbsp;Sanction InProgress</th>
													<th data-hide="phone">Clarifications</th>
													<th data-hide="phone">Deposit InProgress</th>
													<th data-hide="phone">WorkOrder InProgress</th>
													<th data-hide="phone">Meter/PC&nbsp;Test InProgress</th>
													<th data-hide="phone">WCR InProgress</th>
													<th data-hide="phone">Installation InProgress</th>
													<th data-hide="phone">Installation Completed</th>
													<th data-hide="phone">Applications Rejected</th>
													<th data-hide="phone,tablet">FV OnHold</th>
								                    <th data-hide="phone">Estimation OnHold</th>
													<th data-hide="phone">FVA OnHold</th>
													<th data-hide="phone">Power Sanction OnHold</th>
													<th data-hide="phone">WorkOrder OnHold</th>
													<th data-hide="phone">Meter/PC&nbsp;Test OnHold</th>
													<th data-hide="phone">WCR OnHold</th>
													<th data-hide="phone">Installation OnHold</th>
													
								                    </tr>
								                    
									         </c:if>
									         
									          <c:if test = "${reportName=='Phase Wise Sub Division Report'}"> 
									         
									           <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Sub Division Wise Phase Count Report </th></tr>
									         		<tr>
									         		<th style="min-width: 105px;">Sub Division</th>
									         		<th data-hide="phone">Registered</th>
								                    <th data-hide="phone,tablet" style="min-width: 73px;">ARF Not Paid</th>
								                    <th data-hide="phone,tablet" style="min-width: 63px;">FV InProgress</th>
								                    <th data-hide="phone">Estimation InProgress</th>
													<th data-hide="phone">FVA InProgress</th>
													<th data-hide="phone">Power&nbsp;Sanction InProgress</th>
													<th data-hide="phone">Clarifications</th>
													<th data-hide="phone">Deposit InProgress</th>
													<th data-hide="phone">WorkOrder InProgress</th>
													<th data-hide="phone">Meter/PC&nbsp;Test InProgress</th>
													<th data-hide="phone">WCR InProgress</th>
													<th data-hide="phone">Installation InProgress</th>
													<th data-hide="phone">Installation Completed</th>
													<th data-hide="phone">Applications Rejected</th>
													<th data-hide="phone,tablet">FV OnHold</th>
								                    <th data-hide="phone">Estimation OnHold</th>
													<th data-hide="phone">FVA OnHold</th>
													<th data-hide="phone">Power Sanction OnHold</th>
													<th data-hide="phone">WorkOrder OnHold</th>
													<th data-hide="phone">Meter/PC&nbsp;Test OnHold</th>
													<th data-hide="phone">WCR OnHold</th>
													<th data-hide="phone">Installation OnHold</th>
													
								                    </tr>
								                    
									         </c:if>
									         
									         
									         <c:if test = "${reportName=='Tariff Wise Registration to Service'}"> 
									         
									           <tr><th data-hide='phone' colspan='14' style='text-align:center;font-size:14px;background: #FFFAF0; font-size: 17px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Tariff Wise Registration to Service &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>&nbsp;&nbsp;&nbsp;<br>Location :&nbsp;${locationtext}</th></tr>
									         		<tr>
									         		<th style="min-width: 105px;">Tariff</th>
									         		<th data-hide="phone">Registered</th>
								                    <th data-hide="phone,tablet" style="min-width: 73px;">ARF Not Paid</th>
								                    <th data-hide="phone,tablet" style="min-width: 63px;">FV InProgress</th>
								                    <th data-hide="phone">Estimation InProgress</th>
													<th data-hide="phone">FVA InProgress</th>
													<th data-hide="phone">Power&nbsp;Sanction InProgress</th>
													<th data-hide="phone">Deposit InProgress</th>
													<th data-hide="phone">WorkOrder InProgress</th>
													<th data-hide="phone">TAQC InProgress</th>
													<th data-hide="phone">Meter/PC&nbsp;Test InProgress</th>
													<th data-hide="phone">WCR InProgress</th>
													<th data-hide="phone">Installation InProgress</th>
													<th data-hide="phone">Installation Completed</th>
													<th data-hide="phone,tablet">FV OnHold</th>
								                    <th data-hide="phone">Estimation OnHold</th>
													<th data-hide="phone">FVA OnHold</th>
													<th data-hide="phone">Power Sanction OnHold</th>
													<th data-hide="phone">WorkOrder OnHold</th>
													<th data-hide="phone">Meter/PC&nbsp;Test OnHold</th>
													<th data-hide="phone">WCR OnHold</th>
													<th data-hide="phone">Installation OnHold</th>
													
								                    </tr>
								                    
									         </c:if>
									         
									         
									        </thead>
				
									        <tbody>
									      <c:if test = "${reportName=='Applications Registered'}"> 
									        <c:forEach var="app" items="${applicationReportDetails}">
									            <tr>
									            
									            	<td >${app.locationName}</td>
									                <td >${app.applicationid}</td>
									                <td><fmt:formatDate value="${app.appregdate}" pattern="dd-MM-yyyy"/></td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.name}</td>
									                <td>${app.citypres}</td>
									                <td>${app.loadkw}/${app.loadhp}/${app.loadkva}</td>
									                <td>${app.lecContName}</td>
									                
									            </tr>
									        </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Applications Rejected'}"> 
									        <c:forEach var="app" items="${applicationRejectDetails}">
									            <tr>
									            
									                <td >${app.applicationid}</td>
									                <td><fmt:formatDate value="${app.appregdate}" pattern="dd-MM-yyyy"/></td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.name}</td>
									                <td>${app.citypres}</td>
									                <td>${app.loadkw}/${app.loadhp}/${app.loadkva}</td>
									                <td><fmt:formatDate value="${app.lastUpdatedDate}" pattern="dd-MM-yyyy"/></td>
									                
									            </tr>
									        </c:forEach>
									       </c:if>
									       
									       <%-- <c:if test = "${reportName=='Applications Accepted'}"> 
									        <c:forEach var="app" items="${applicationReportDetails}">
									            <tr>
									            
									                <td>${app.applicationid}</td>
									                <td>${app.name}</td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.loadkw}/${app.loadhp}/${app.loadkva}</td>
									                <td><fmt:formatDate value="${app.arfreceiptdt}" pattern="dd-MM-yyyy"/></td>
									                <td>${app.arfreceiptno}</td>
									                <td>${app.arfamount}</td>
									                <td>${app.arftowards}</td>
									               
									            </tr>
									        </c:forEach>
									       </c:if> --%>
									       
									       <c:if test = "${reportName=='Field Verification Completed'}"> 
									       
									        <c:forEach var="app" items="${fieldVerificationDetails}">
									            <tr>
									                <td>${app.applicationid}</td>
									                <td>${app.name}</td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.loadkw}/${app.loadhp}/${app.loadkva}</td>
									                <td>${app.fvrefno}</td>
									                <td><fmt:formatDate value="${app.fvdate}" pattern="dd-MM-yyyy"/></td>
													<c:choose >
														<c:when test="${app.estmnreq ==0}">
														<td>No</td>
														</c:when>
														<c:otherwise>
														<c:if test="${app.estmnreq ==1}">
														  <td>Yes</td>	
														</c:if>																
														</c:otherwise>
													</c:choose>
													
													<td>${app.polenumber}</td>
													<td>${app.feedername}</td>
													<td>${app.feedercode}</td>
													<td>${app.dtcname}</td>
													<td>${app.dtccode}</td>
													
									            </tr>
									        </c:forEach>
									       
									       </c:if>
									       
									       
									       <c:if test = "${reportName=='Estimation Completed'}"> 
										        <c:forEach var="estCompleted" items="${estimationCompletedDetails}">
										            <tr>
										                <td>${estCompleted.applicationid}</td>
										                <td>${estCompleted.name}</td>
									                    <td>${estCompleted.tariffdesc}</td>
									                    <td>${estCompleted.loadkw}/${estCompleted.loadhp}/${estCompleted.loadkva}</td>
										                <td>${estCompleted.servrefno}</td>
														<td>${estCompleted.servrdate}</td>
														<td>${estCompleted.estimationCost}</td>
										            </tr>
										      </c:forEach>
									       </c:if>
									       
									       
									       
									        <c:if test = "${reportName=='Power Sanction Completed'}"> 
										       
										        <c:forEach var="powersanc" items="${powerSanctionDetails}">
										            <tr>
										                <td>${powersanc.applicationid}</td>
										                <td>${powersanc.name}</td>
									                    <td>${powersanc.tariffdesc}</td>
									                    <td>${powersanc.loadkw}/${powersanc.loadhp}/${powersanc.loadkva}</td>
										                <td>${powersanc.psrefno}</td>
										                <td><fmt:formatDate value="${powersanc.psdate}" pattern="dd-MM-yyyy"/></td>
														<td>${powersanc.username}</td>
														<td><fmt:formatDate value="${powersanc.datestamp}" pattern="dd-MM-yyyy"/></td>
										               
										            </tr>
										        </c:forEach>
										        
										        
									       </c:if>
		
									        
									       
									         <c:if test = "${reportName=='Work Order Completed'}"> 
										        <c:forEach var="workOrder" items="${workOrderDetails}">
										            <tr>
										                <td>${workOrder.applicationid}</td>
										                <td>${workOrder.name}</td>
									                    <td>${workOrder.tariffdesc}</td>
									                    <td>${workOrder.loadkw}/${workOrder.loadhp}/${workOrder.loadkva}</td>
										                <td>${workOrder.worefno}</td>
														<td>${workOrder.wocdate}</td>
										            </tr>
										       </c:forEach>
									       </c:if>
									         
									        <c:if test = "${reportName=='Meter/PC Test'}"> 
									         
										        <c:forEach var="meterPro" items="${meterPurchaseDetails}">
										            <tr>
										                <td>${meterPro.applicationid}</td>
										                <td>${meterPro.name}</td>
									                    <td>${meterPro.tariffdesc}</td>
									                    <td>${meterPro.loadkw}/${meterPro.loadhp}/${meterPro.loadkva}</td>
										                <td>${meterPro.porefno}</td>
														<td>${meterPro.podate}</td>
														<td>${meterPro.username}</td>
														<td>${meterPro.meterslno} </td>
														<td>${meterPro.ctratio} </td>
														<td>${meterPro.ptratio}</td>
										            </tr>
										        </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Work Completion Report'}"> 
										        <c:forEach var="workComp" items="${workCompletionDetails}">
										            <tr>
										                <td>${workComp.applicationid}</td>
										                <td>${workComp.name}</td>
									                    <td>${workComp.tariffdesc}</td>
									                    <td>${workComp.loadkw}/${workOrder.loadhp}/${workOrder.loadkva}</td>
										                <td>${workComp.wcrrefno}</td>
														<td>${workComp.wcrdate}</td>
										            </tr>
										       </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Installations Serviced'}"> 
										        <c:forEach var="insService" items="${installationServiceDetails}">
										            <tr>
										                <td>${insService.applicationid}</td>
										                <td>${insService.name}</td>
									                    <td>${insService.tariffdesc}</td>
									                    <td>${insService.loadkw}/${insService.loadhp}/${insService.loadkva}</td>
										                <td>${insService.servrefno}</td>
														<td>${insService.servrdate}</td>
														<td>${insService.rrnumbers}</td>
										            </tr>
										        </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Application Status Wise Report'}"> 
										       
						                     <c:forEach var="appCount" items="${applicationStatusCount}">
						                        
						                        <tr>
						                            <td><b>Filed Verification</b></td>
						                            <td>${appCount.estInProgress}</td>
						                            <td>${appCount.fvProgress}</td>
						                            <td>${appCount.fvPending}</td>
						                        </tr>
						                        
						                        <tr >
						                            <td><b>Estimation</b></td>
						                            <td>${appCount.powerSancInProgress}</td>
						                            <td>${appCount.estInProgress}</td>
						                            <td>${appCount.estPending}</td>
						                        </tr>
						                        
						                        <tr>
						                            <td><b>Power Sanction</b></td>
						                            <td>${appCount.depInProgress}</td>
						                            <td>${appCount.powerSancInProgress}</td>
						                            <td>${appCount.powerSancpding}</td>
						                        </tr>
						                        
						                        <tr >
						                            <td><b>Deposit Acceptance</b></td>
						                            <td>${appCount.workOrderInProgress}</td>
						                            <td>${appCount.depInProgress}</td>
						                            <td>${appCount.depPending}</td>
						                        </tr>
						                        <tr>
						                            <td><b>Work Order</b></td>
						                            <td>${appCount.meterPOInProgress}</td>
						                            <td>${appCount.workOrderInProgress}</td>
						                            <td>${appCount.workOrderPending}</td>
						                        </tr>
						                        <tr>
						                            <td><b>Meter/PC Test</b></td>
						                            <td>${appCount.wcrInProgress}</td>
						                            <td>${appCount.meterPOInProgress}</td>
						                            <td>${appCount.meterPOPending}</td></tr>
						                        <tr>
						                            <td><b>WCR</b></td>
						                            <td>${appCount.serviceInProgress}</td>
						                            <td>${appCount.wcrInProgress}</td>
						                            <td>${appCount.wcrPending}</td>
						                        </tr>
						                        <tr>
						                            <td><b>Installation Service</b></td>
						                            <td>${appCount.serviceCompleted}</td>
						                            <td>${appCount.serviceInProgress}</td>
						                            <td>${appCount.servicePending}</td>
						                        </tr>
						                        </c:forEach>
						                       </c:if>
						                       
						                       <c:if test = "${reportName=='Tariff Wise Application Report'}">
						                       
						                       <tr>
														<td><b>In Progress</b></td>
														<td ><b>${applicationInProgress}</b></td>
														
														
														<td>
														<c:set var="lt1" value="0" />
														 <c:forEach var="appPro" items="${appInProgressOnTariff}">
														
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT1')}">
														    
														   		 <c:set var="lt1" value="${appPro[1] + lt1}"/>
														   </c:if>
														   
													   </c:forEach>
													   <c:out value="${lt1}"></c:out>
													   
													    </td>
													   
													    <td><c:set var="lt2" value='0' />
													     <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT2')}">
														   		<c:set var="lt2" value="${appPro[1]+lt2}"/>
														   </c:if>
													   </c:forEach>
													   <c:out value="${lt2}"></c:out>
													   
													    </td>
													    <td> 
													    <c:set var="lt3" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT3')}">
														   		<c:set var="lt3" value="${appPro[1] + lt3}"/>
														   </c:if>
													   </c:forEach>
													   <c:out value="${lt3}"></c:out>
													   
													    </td>
													    <td>
													    <c:set var="lt4" value="0" />
													     <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT4')}">
														   <c:set var="lt4" value="${appPro[1] + lt4}"/>
														   </c:if>
													   </c:forEach>
														  <c:out value="${lt4}"></c:out>
													   
													   
													    </td>
													    <td>
													    <c:set var="lt5" value="0" />
													     <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT5')}">
														   		<c:set var="lt5" value="${appPro[1] + lt5}"/>
														   		
														   </c:if>
														   
													   </c:forEach>
													    <c:out value="${lt5}"></c:out>
													   
													    </td>
													    <td> 
													    <c:set var="lt6" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														 <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT6')}">
														   		<c:set var="lt6" value="${appPro[1] + lt6}"/>
														   		
														   </c:if>
														   
													   </c:forEach>
													    <c:out value="${lt6}"></c:out>
													   
													    </td>
													    <td>
													    <c:set var="lt7" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT7')}">
														   		<c:set var="lt7" value="${appPro[1] + lt7}"/>
														   		
														   </c:if>
														   
													   </c:forEach>
													    <c:out value="${lt7}"></c:out>
													   
													    </td>
													    <td>
													    <c:set var="ht1" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT1')}">
														   		<c:set var="ht1" value="${appPro[1] + ht1}"/>
														   		 
														   </c:if>
														   
													   </c:forEach>
													   <c:out value="${ht1}"></c:out>
													   
													    </td>
													    <td>
													    <c:set var="ht2" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT2')}">
														   		<c:set var="ht2" value="${appPro[1] + ht2}"/>
														   </c:if>
														   
													   </c:forEach>
														<c:out value="${ht2}"></c:out>
													   
													    </td>
													    <td>
													    <c:set var="ht3" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT3')}">
														   		<c:set var="ht3" value="${appPro[1] + ht3}"/>
														   		
														   </c:if>
														   
													   </c:forEach>
													    <c:out value="${ht3}"></c:out>
													  
													    </td>
													    <td>
													    <c:set var="ht4" value="0" />
													    <c:forEach var="appPro" items="${appInProgressOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT4')}">
														   	<c:set var="ht4" value="${appPro[1] + ht4}"/>
														   		 
														   </c:if>
													   </c:forEach>
													   <c:out value="${ht4}"></c:out>
													   
													    </td>
													</tr>
													<tr>
														
														<td><b>Completed</b></td>
														<td ><b>${applicationCompleted}</b></td>
														<td>
														<c:set var="lt1c" value="0" />
														 <c:forEach var="appPro" items="${appCompletedOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT1')}">
														   		<c:set var="lt1c" value="${appPro[1] + lt1c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt1c}"></c:out>
													    </td>
													   
													    <td>
													    <c:set var="lt2c" value="0" />
													     <c:forEach var="appPro" items="${appCompletedOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT2')}">
														   		<c:set var="lt2c" value="${appPro[1] + lt2c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt2c}"></c:out>
													    </td>
													    <td>
													    <c:set var="lt3c" value="0" />
													     <c:forEach var="appPro" items="${appCompletedOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT3')}">
														   		<c:set var="lt3c" value="${appPro[1] + lt3c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt3c}"></c:out>
													    </td>
													    <td>
													    <c:set var="lt4c" value="0" />
													     <c:forEach var="appPro" items="${appCompletedOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT4')}">
														   		<c:set var="lt4c" value="${appPro[1] + lt4c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt4c}"></c:out>
													    </td>
													    <td>
													    <c:set var="lt5c" value="0" />
													     <c:forEach var="appPro" items="${appCompletedOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT5')}">
														   		<c:set var="lt5c" value="${appPro[1] + lt5c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt5c}"></c:out>
													    </td>
													    <td>
													    <c:set var="lt6c" value="0" />
													     <c:forEach var="appPro" items="${appCompletedOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT6')}">
														   		<c:set var="lt6c" value="${appPro[1] + lt6c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt6c}"></c:out>
													    </td>
													    <td>
													    <c:set var="lt7c" value="0" />
													    <c:forEach var="appPro" items="${appCompletedOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT7')}">
														   		<c:set var="lt7c" value="${appPro[1] + lt7c}"/>
														   </c:if>														   
													   </c:forEach>
													  <c:out value="${lt7c}"></c:out>
													    </td>
													    <td>
													    <c:set var="ht1c" value="0" />
													    <c:forEach var="appPro" items="${appCompletedOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT1')}">
														  <c:set var="ht1c" value="${appPro[1] + ht1c}"/>
														   </c:if>														   
													   </c:forEach>
													  <c:out value="${ht1c}"></c:out>
													    </td>
													    <td>
													    <c:set var="ht2c" value="0" />
													    <c:forEach var="appPro" items="${appCompletedOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT2')}">
														   		<c:set var="ht2c" value="${appPro[1] + ht2c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${ht2c}"></c:out>
													    </td>
													    <td>
													    <c:set var="ht3c" value="0" />
													    <c:forEach var="appPro" items="${appCompletedOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT3')}">
														   		<c:set var="ht3c" value="${appPro[1] + ht3c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${ht3c}"></c:out>
													    </td>
													    <td>
													    <c:set var="ht4c" value="0" />
													    <c:forEach var="appPro" items="${appCompletedOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT4')}">
														   		<c:set var="ht4c" value="${appPro[1] + ht4c}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${ht4c}"></c:out>
													    </td>
														
													</tr>
													<tr>
														
														<td><b>On Hold</b></td>
														<td ><b>${applicationPending}</b></td>
														<td>
														<c:set var="lt1p" value="0" />
														 <c:forEach var="appPro" items="${appPendingOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT1')}">
														   		<c:set var="lt1p" value="${appPro[1] + lt1p}"/>
														   		</c:if>	
														  </c:forEach>													   		
                                                          <c:out value="${lt1p}"></c:out>  
													  
													    </td>
													   
													    <td>
													    <c:set var="lt2p" value="0" />
													     <c:forEach var="appPro" items="${appPendingOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT2')}">
														   		<c:set var="lt2p" value="${appPro[1] + lt2p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${lt2p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="lt3p" value="0" />
													     <c:forEach var="appPro" items="${appPendingOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT3')}">
														   		<c:set var="lt3p" value="${appPro[1] + lt3p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${lt3p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="lt4p" value="0" />
													     <c:forEach var="appPro" items="${appPendingOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT4')}">
														   		<c:set var="lt4p" value="${appPro[1] + lt4p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${lt4p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="lt5p" value="0" />
													     <c:forEach var="appPro" items="${appPendingOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT5')}">
														  <c:set var="lt5p" value="${appPro[1] + lt5p}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${lt5p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="lt6p" value="0" />
													     <c:forEach var="appPro" items="${appPendingOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT6')}">
														   		<c:set var="lt6p" value="${appPro[1] + lt6p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${lt6p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="lt7p" value="0" />
													    <c:forEach var="appPro" items="${appPendingOnTariff}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT7')}">
														   		<c:set var="lt7p" value="${appPro[1] + lt7p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${lt7p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ht1p" value="0" />
													    <c:forEach var="appPro" items="${appPendingOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT1')}">
														   		<c:set var="ht1p" value="${appPro[1] + ht1p}"/>
														   		</c:if>	
														   		</c:forEach>													   		
                                                            
													   <c:out value="${ht1p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ht2p" value="0" />
													    <c:forEach var="appPro" items="${appPendingOnTariff}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT2')}">
														   		<c:set var="ht2p" value="${appPro[1] + ht2p}"/>
														   </c:if>														   
													   </c:forEach>
													   
													    <c:out value="${ht1p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ht3p" value="0" />
													    <c:forEach var="appPro" items="${appPendingOnTariff}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT3')}">
														   		<c:set var="ht3p" value="${appPro[1] + ht3p}"/>
														   </c:if>														   
													   </c:forEach>
													  <c:out value="${ht3p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ht4p" value="0" />
													    <c:forEach var="appPro" items="${appPendingOnTariff}">
														 <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT4')}">
														   		<c:set var="ht4p" value="${appPro[1] + ht4p}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${ht4p}"></c:out>  
													    </td>
													</tr>
						                       
						                       </c:if>
									       
									       <c:if test = "${reportName=='Application Stage Wise Report'}"> 
									        <c:forEach var="appStage" items="${applicationStageWiseDetails}">
									           
									            <tr>
									                <td>${appStage.tariff}</td>
									                <td>${appStage.previouspending}</td>
									                <td>${appStage.registered}</td>
									                <td>${appStage.arfnotpaid}</td>
									                <td>${appStage.estpending}</td>
									                <td>${appStage.intimationpen}</td>
									                <td>${appStage.depositPen}</td>
									                <td>${appStage.woPen}</td>
									                <td>${appStage.mpPen}</td>
									                
									            </tr>
									        </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Phase Wise Circle Report'}"> 
										        <c:forEach var="cir" items="${circleWiseDetails}">
										            <tr>
										                <td>${cir.type}</td>
										                <td>${cir.registered}</td>
									                    <td>${cir.arfnotpaid}</td>
									                    <td>${cir.fvinprogress}</td>
										                <td>${cir.estinprogress}</td>
														<td>${cir.fvainprogress}</td>
														<td>${cir.intimationinprog}</td>
														<td>${cir.clarifications}</td>
														<td>${cir.depositinprog}</td>
										                <td>${cir.woinprog}</td>
									                    <td>${cir.mpinprog}</td>
									                    <td>${cir.wcrinprog}</td>
										                <td>${cir.insinprog}</td>
														<td>${cir.inscompleted}</td>
														<td>${cir.intimationinprog}</td>
														<td>${cir.rejected}</td>
										                <td>${cir.fvOnhold}</td>
									                    <td>${cir.estOnhold}</td>
									                    <td>${cir.powersacOnhold}</td>
										                <td>${cir.workOnhold}</td>
														<td>${cir.meterOnhold}</td>
														<td>${cir.wcrOnhold}</td>
														<td>${cir.insonhold}</td>
														
														
														
										            </tr>
										      </c:forEach>
									       </c:if>
									       
									       <c:if test = "${reportName=='Phase Wise Sub Division Report'}"> 
										        <c:forEach var="subDiv" items="${SDWiseDetails}">
										            <tr>
										                <td>${subDiv.type}</td>
										                <td>${subDiv.registered}</td>
									                    <td>${subDiv.arfnotpaid}</td>
									                    <td>${subDiv.fvinprogress}</td>
										                <td>${subDiv.estinprogress}</td>
														<td>${subDiv.fvainprogress}</td>
														<td>${subDiv.intimationinprog}</td>
														<td>${subDiv.clarifications}</td>
														<td>${subDiv.depositinprog}</td>
										                <td>${subDiv.woinprog}</td>
									                    <td>${subDiv.mpinprog}</td>
									                    <td>${subDiv.wcrinprog}</td>
										                <td>${subDiv.insinprog}</td>
														<td>${subDiv.inscompleted}</td>
														<td>${subDiv.intimationinprog}</td>
														<td>${subDiv.rejected}</td>
										                <td>${subDiv.fvOnhold}</td>
									                    <td>${subDiv.estOnhold}</td>
									                    <td>${subDiv.powersacOnhold}</td>
										                <td>${subDiv.workOnhold}</td>
														<td>${subDiv.meterOnhold}</td>
														<td>${subDiv.wcrOnhold}</td>
														<td>${subDiv.insonhold}</td>
										            </tr>
										      </c:forEach>
									       </c:if>
									       
									       
									       <c:if test = "${reportName=='Tariff Wise Registration to Service'}"> 
										        <c:forEach var="subDiv" items="${tariffWiseRegToServiceList}">
										            <tr>
										            
										                <td>${subDiv.tariff}</td>
										                <td>${subDiv.registered}</td>
										                <td>${subDiv.waitingforARF}</td>
									                    <td>${subDiv.fieldverinprog}</td>
									                   
										                <td>${subDiv.estimationinprog}</td>
														<td>${subDiv.fvainprog}</td>
														<td>${subDiv.powinprog}</td>
														<td>${subDiv.depaccinpr}</td>
														<td>${subDiv.workorinpr}</td>
										                
									                    <td>${subDiv.taqcinpr}</td>
									                    <td>${subDiv.mpoinpro}</td>
										                <td>${subDiv.wcrinprog}</td>
														<td>${subDiv.instinpr}</td>
														<td>${subDiv.inscomp}</td>
														<td>${subDiv.fieldveronHold}</td>
										                <td>${subDiv.fvaonHold}</td>
									                    <td>${subDiv.estonHold}</td>
									                    <td>${subDiv.psonHold}</td>
										                <td>${subDiv.woonHold}</td>
														<td>${subDiv.mpoonHold}</td>
														<td>${subDiv.wcronHold}</td>
														<td>${subDiv.insonHold}</td>
										            </tr>
										      </c:forEach>
									       </c:if>
									             
									        </tbody>
									
										</table>
				
									</div>
									
						
									
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
						<!-- WIDGET END -->
				</section>
					</div>

<script>

$(document).ready(function() {
	
	var reportname="${reportName}";

	$('#todate').datepicker({
		dateFormat : 'dd/mm/yy'
		
	});
	
	$('#fromdate').datepicker({
		dateFormat : 'dd/mm/yy'
		
	});
	
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
				// Initialize the responsive datatables helper once.
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

	/* END BASIC */
	
	/* COLUMN FILTER  */
    var otable = $('#datatable_fixed_column').DataTable({
		"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
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
		"aLengthMenu": [[-1,5, 10, 15, 25, 50, 100,500], ["All",05, 10, 15, 25, 50, 100,500]],
	 	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-3 col-xs-3 hidden-xs'C>l>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
        "oTableTools": {
        	 "aButtons": [
             
                {
                    "sExtends": "pdf",
                    "sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
                    "sFileName": reportname+".pdf",
                    "sPdfMessage":"                                                   Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                    "+reportname+"                                                                                                        Location : ${locationtext}                                                                      From Date : <fmt:formatDate value="${fromdate}" pattern="dd-MM-yyyy"/>                 To Date : <fmt:formatDate value="${todate}" pattern="dd-MM-yyyy"/>",
                    "sPdfSize": "letter"
                },
               
             	{
                	"sExtends": "print",
                	"sButtonText":"<font color='#000000' size='3px'><i class='fa fa-print'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Print</font>",
                	"sMessage": "Generated by PGRS_CESC <i>(press Esc to close)</i>"
            	}
             ],
            "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
        }, 
		"autoWidth" : true,
		"order": [],
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
	
	/* END TABLETOOLS */

});
$(document).ready(function() {
	pageSetUp();
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
		