<%@include file="/common/taglibs.jsp"%>

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
<script src="./resources/js/plugin/morris/raphael.min.js"></script>
<script src="./resources/js/plugin/morris/morris.min.js"></script>
<div id="content" style="margin-top: -18px;">

				    <div class="row" >
						<div class="col-xs-12 col-sm-6 col-md-6">
							<h3 >  <span><b class="appphase">${appPhase}</b></span></h3>
						</div>
						
						<div class="col-xs-12 col-sm-6 col-md-6" style="margin-left: -337px;margin-top:7px; ">
							 <%@include file="/common/commonPage.jsp"%>
						</div>
					</div>
					
					
					
					
					<c:if test="${not empty showClarificationsTab}">
					
					<div class="row">
								
								
								
								<div class="col-xs-12 col-sm-6 col-md-2">
						             <div class="panel panel-purple">
						                <div class="panel-heading">
						                     <h3 class="panel-title" align="center">
						                        <b>Total</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                   
						                    <table class="table">
						                        
						                        <tr class="active">
						                            <td>
						                               <b style="font-size: medium;"> ${total} </b><br>Applications
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>		
						        
						        <div class="col-xs-12 col-sm-6 col-md-2">
						            <div class="panel panel-orange">
						                <div class="panel-heading">
						                   <h3 class="panel-title" align="center">
						                        <b>In Progress</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                   
						                    <table class="table">
						                        
						                        <tr class="active">
						                            <td>
						                                
						                                <c:choose >
															<c:when test="${sessionScope.designation =='AE(Elec)'}">
																<b><a href="#" style="text-decoration: underline;font-size: medium;" onclick='applicationauthority()'>${applicationInProgress}</a></b> <br>Applications
															</c:when>
															<c:otherwise>
																<b id="redirectData2"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationInProgress}</a></b> <br>Applications
															</c:otherwise>
														</c:choose>
																	
						                                 
						                                
						                                
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>	
						        
						        	
						        <div class="col-xs-12 col-sm-6 col-md-2">
						            <div class="panel panel-greenLight">
						                <div class="panel-heading">
						                     <h3 class="panel-title" align="center">
						                        <b>Completed</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                   
						                    <table class="table">
						                        
						                        <tr class="active">
						                            <td>
						                              
						                               <b id="redirectData3"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationCompleted}</a></b> <br>Applications
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>	
						        	
						        <div class="col-xs-12 col-sm-6 col-md-2">
						            <div class="panel panel-redLight">
						                <div class="panel-heading" >
						                    <h3 class="panel-title" align="center">
						                        <b>On Hold</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                   
						                    <table class="table">
						                        
						                        <tr class="active">
						                            <td>
						                               
						                                  <c:choose >
															<c:when test="${sessionScope.designation =='AE(Tech)' || sessionScope.designation =='AE(Elec)'}">
																<b><a href="#" style="text-decoration: underline;font-size: medium;" onclick='applicationauthority()'>${applicationPending}</a></b> <br>Applications
															</c:when>
															<c:otherwise>
																<b id="redirectData4"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationPending}</a></b> <br>Applications
															</c:otherwise>
														</c:choose>
						                               
						                               
						                                
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>
						          <div class="col-xs-12 col-sm-6 col-md-2">
						            <div class="panel panel-pink">
						                <div class="panel-heading" >
						                    <h3 class="panel-title" align="center">
						                        <b>Delay</b></h3>
						                </div>
						                   <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                               <b id="redirectData9"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationDelay}</a></b> <br>Applications
								                                
								                            </td>
								                        </tr>
								                    </table>
								           </div>
						                
						            </div>
						        </div>
						        
						        
						        
						        <div class="col-xs-12 col-sm-6 col-md-2">
						            <div class="panel panel-blueLight">
						                <div class="panel-heading" >
						                    <h3 class="panel-title" align="center">
						                        <b>Clarifications</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                   
						                    <table class="table">
						                        
						                        <tr class="active">
						                            <td>
						                               <b id="redirectData5"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationClarification}</a></b> <br>Applications
						                                
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						            </div>
						        </div>		
								
						        
					</div>
					
					</c:if>
					
					<c:if test="${empty showClarificationsTab}">
					
							<div class="row">
										
										<div class="col-xs-12 col-sm-6 col-md-3">
								             <div class="panel panel-purple">
								                <div class="panel-heading">
								                     <h3 class="panel-title" align="center">
								                        <b>Total</b></h3>
								                </div>
								                <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                               <b style="font-size: medium;"> ${total} </b><br>Applications
								                            </td>
								                        </tr>
								                    </table>
								                </div>
								                
								            </div>
								        </div>		
								        <div class="col-xs-12 col-sm-6 col-md-2">
								            <div class="panel panel-orange">
								                <div class="panel-heading">
								                   <h3 class="panel-title" align="center">
								                        <b>In Progress</b></h3>
								                </div>
								                <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                                <b id="redirectData2"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationInProgress}</a></b> <br>Applications
								                            </td>
								                        </tr>
								                    </table>
								                </div>
								                
								            </div>
								        </div>		
								        <div class="col-xs-12 col-sm-6 col-md-2">
								            <div class="panel panel-greenLight">
								                <div class="panel-heading">
								                     <h3 class="panel-title" align="center">
								                        <b>Completed</b></h3>
								                </div>
								                <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                              
								                               <b id="redirectData3"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationCompleted}</a></b> <br>Applications
								                            </td>
								                        </tr>
								                    </table>
								                </div>
								                
								            </div>
								        </div>		
								        <div class="col-xs-12 col-sm-6 col-md-2">
								            <div class="panel panel-redLight">
								                <div class="panel-heading" >
								                    <h3 class="panel-title" align="center">
								                        <b>On Hold</b></h3>
								                </div>
								                <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                               <b id="redirectData4"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationPending}</a></b> <br>Applications
								                                
								                            </td>
								                        </tr>
								                    </table>
								                </div>
								                
								            </div>
								        </div>
								        
								        <div class="col-xs-12 col-sm-6 col-md-3">
								            <div class="panel panel-pink">
								                <div class="panel-heading" >
								                    <h3 class="panel-title" align="center">
								                        <b>Delay</b></h3>
								                </div>
								                <div class="panel-body no-padding text-align-center">
								                   
								                    <table class="table">
								                        
								                        <tr class="active">
								                            <td>
								                               <b id="redirectData9"><a href="#" style="text-decoration: underline;font-size: medium;">${applicationDelay}</a></b> <br>Applications
								                                
								                            </td>
								                        </tr>
								                    </table>
								                </div>
								                
								            </div>
								        </div>
								      
							</div>
					
					</c:if>
					
					<%-- <div class="row">
					
					
					
						<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-purple">
						                <div class="panel-heading">
						                        <h3 class="panel-title" style="margin-left: 105px" ><b>Total</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table" >
						                        <tr class="active" height="50px" >
						                            <td align="center" >
						                                 <h1>${total} </h1>
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						        </div>
						</div>
						
						
						<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-orange">
						                <div class="panel-heading" >
						                        <h3 class="panel-title" style="margin-left: 85px" ><b>In Progress</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table" >
						                        <tr class="active" height="50px" >
						                            <td align="center" >
						                                 <h1 id="redirectData2"><a href='#'>${applicationInProgress} </a></h1>
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						        </div>
						</div>
						
					 	<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-greenLight">
						                <div class="panel-heading">
						                    <h3 class="panel-title" style="margin-left: 90px" ><b>Completed</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table" >
						                        <tr class="active" height="50px" >
						                            <td align="center" >
						                                 <h1 id="redirectData3"><a href='#'>${applicationCompleted}</a> </h1>
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						        </div>
						</div>
						
						<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-redLight">
						                <div class="panel-heading">
						                          <h3 class="panel-title" style="margin-left: 95px" ><b>Pending</b></h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <table class="table" >
						                        <tr class="active" height="50px" >
						                            <td align="center" >
						                                 <h1 id="redirectData4"><a href="#"> ${applicationPending}</a> 	 </h1>
						                            </td>
						                        </tr>
						                    </table>
						                </div>
						                
						        </div>
						</div>
					</div> --%>
					
				<div class="row">	
					<article class="col-xs-6 col-sm-6 col-md-6 col-lg-12">	
					<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false">
								
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Tariff Wise Application Progress</h2>
								</header>

								<!-- widget div-->
								<div>

									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<!-- end widget edit box -->

									<!-- widget content -->
									<div class="widget-body no-padding">

										
										<div class="table-responsive">
												
											<table class="table table-bordered table-striped">
												<thead>
													<tr style="color: maroon;font-size:14px;background: #FFFAF0">
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
												</thead>
												<tbody>
													<tr>
														
														<c:if test="${appPhase =='Power Sanction'}">
														<c:choose>
											                <c:when test="${sessionScope.designation =='AE(Tech)' || sessionScope.designation =='AE(Elec)'}">
											               		<td  style="background:#B8860B;" ><a onclick="applicationauthority()" href="javascript:void(0)" ><b ><font color="white">In Progress</font></b></a></td>
											               		<td ><a onclick="applicationauthority()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationInProgress}</b></a></td>
											                </c:when>
											                <c:otherwise>
															<td  style="background:#B8860B;" ><a onclick="redirectData2()" href="javascript:void(0)" ><b ><font color="white">In Progress</font></b></a></td>
															<td ><a onclick="redirectData2()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationInProgress}</b></a></td>
											                </c:otherwise>
										                </c:choose>
														</c:if>
														
														<c:if test="${appPhase !='Power Sanction'}">
														
															<td  style="background:#B8860B;" ><a onclick="redirectData2()" href="javascript:void(0)" ><b ><font color="white">In Progress</font></b></a></td>
															<td ><a onclick="redirectData2()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationInProgress}</b></a></td>
											               
										              
														</c:if>
														
														
														<td>
														<c:set var="lt1" value="0" />
														 <c:forEach var="appPro" items="${appInProgressOnTariff}">
														
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT1')}">
														    
														   		 <c:set var="lt1" value="${appPro[1] + lt1}"/>
														   </c:if>
														   
													   </c:forEach>
													   <c:out value="${lt1}"></c:out>
													   
													    </td>
													   
													    <td><c:set var="lt2" value='0'/>
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
														
														<td  style="background:#556B2F;"><a onclick="redirectData3()" href="javascript:void(0)" > <b><font color="white">Completed</font></b></a></td>
														<td ><a onclick="redirectData3()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationCompleted}</b></a></td>
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
														
														
														<c:if test="${appPhase =='Power Sanction'}">
															<c:choose>
											                <c:when test="${sessionScope.designation =='AE(Tech)' || sessionScope.designation =='AE(Elec)'}">
											               		<td  style="background:#AB4747;" ><a onclick="applicationauthority()" href="javascript:void(0)" ><b ><font color="white">On Hold</font></b></a></td>
											               		<td ><a onclick="applicationauthority()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationPending}</b></a></td>
											                </c:when>
											                <c:otherwise>
															<td style="background:#AB4747;"><a onclick="redirectData4()" href="javascript:void(0)" ><b><font color="white">On Hold</font></b></a></td>
															<td ><a onclick="redirectData4()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationPending}</b></a></td>
											                </c:otherwise>
										                    </c:choose>
														</c:if>
														<c:if test="${appPhase !='Power Sanction'}">
															
															<td style="background:#AB4747;"><a onclick="redirectData4()" href="javascript:void(0)" ><b><font color="white">On Hold</font></b></a></td>
															<td ><a onclick="redirectData4()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationPending}</b></a></td>
											                
														</c:if>
														
														
														
														
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
													   
													    <c:out value="${ht2p}"></c:out>  
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
													
													
												<c:if test="${not empty showClarificationsTab}">	
													<tr>
														
														<td style="background:#92A2A8;"><a onclick="redirectData5()" href="javascript:void(0)" ><b><font color="white">Clarifications</font></b></a></td>
														<td ><a onclick="redirectData5()" href="javascript:void(0)" style="text-decoration: underline;"><b>${applicationClarification}</b></a></td>
														<td>
														<c:set var="ltc1p" value="0" />
														 <c:forEach var="appPro" items="${appInClarification}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT1')}">
														   		<c:set var="ltc1p" value="${appPro[1] + ltc1p}"/>
														   		</c:if>	
														  </c:forEach>													   		
                                                          <c:out value="${ltc1p}"></c:out>  
													  
													    </td>
													   
													    <td>
													    <c:set var="ltc2p" value="0" />
													     <c:forEach var="appPro" items="${appInClarification}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT2')}">
														   		<c:set var="ltc2p" value="${appPro[1] + ltc2p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${ltc2p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ltc3p" value="0" />
													     <c:forEach var="appPro" items="${appInClarification}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT3')}">
														   		<c:set var="ltc3p" value="${appPro[1] + ltc3p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${ltc3p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ltc4p" value="0" />
													     <c:forEach var="appPro" items="${appInClarification}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT4')}">
														   		<c:set var="ltc4p" value="${appPro[1] + ltc4p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${ltc4p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ltc5p" value="0" />
													     <c:forEach var="appPro" items="${appInClarification}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT5')}">
														  <c:set var="ltc5p" value="${appPro[1] + ltc5p}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${ltc5p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ltc6p" value="0" />
													     <c:forEach var="appPro" items="${appInClarification}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT6')}">
														   		<c:set var="ltc6p" value="${appPro[1] + ltc6p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${ltc6p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="ltc7p" value="0" />
													    <c:forEach var="appPro" items="${appInClarification}">
														  <c:if test="${fn:containsIgnoreCase(appPro[0], 'LT7')}">
														   		<c:set var="ltc7p" value="${appPro[1] + ltc7p}"/>
														   </c:if>														   
													   </c:forEach>
													    <c:out value="${ltc7p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="htc1p" value="0" />
													    <c:forEach var="appPro" items="${appInClarification}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT1')}">
														   		<c:set var="htc1p" value="${appPro[1] + htc1p}"/>
														   		</c:if>	
														   		</c:forEach>													   		
                                                            
													   <c:out value="${htc1p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="htc2p" value="0" />
													    <c:forEach var="appPro" items="${appInClarification}">
														   <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT2')}">
														   		<c:set var="htc2p" value="${appPro[1] + htc2p}"/>
														   </c:if>														   
													   </c:forEach>
													   
													    <c:out value="${htc2p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="htc3p" value="0" />
													    <c:forEach var="appPro" items="${appInClarification}">
														    <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT3')}">
														   		<c:set var="htc3p" value="${appPro[1] + htc3p}"/>
														   </c:if>														   
													   </c:forEach>
													  <c:out value="${htc3p}"></c:out>  
													    </td>
													    <td>
													    <c:set var="htc4p" value="0" />
													    <c:forEach var="appPro" items="${appInClarification}">
														 <c:if test="${fn:containsIgnoreCase(appPro[0], 'HT4')}">
														   		<c:set var="htc4p" value="${appPro[1] + htc4p}"/>
														   </c:if>														   
													   </c:forEach>
													   <c:out value="${htc4p}"></c:out>  
													    </td>
													</tr>
												</c:if>
													
													
													
													
													
													
												</tbody>
											</table>
											
										</div>
									</div>
									<!-- end widget content -->

								</div>
								<!-- end widget div -->

							</div>
							<!-- end widget -->
							</article>
					
					 <article class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<!-- row -->
					<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
								
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
									<h2> ${appPhase} Status Graph (In Number)</h2>

								</header>

								<!-- widget div-->
								<div>

									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<!-- end widget edit box -->

									<!-- widget content -->
									<div class="widget-body no-padding">

										<div id="normal-bar-graph" class="chart no-padding"></div>

									</div>
									<!-- end widget content -->

								</div>
								<!-- end widget div -->

							</div>
							<!-- end widget -->
						</article>
							
							
						<article class="col-xs-6 col-sm-6 col-md-6 col-lg-6">	
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
								

								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
									<h2> ${appPhase} Status Graph (In %)</h2>

								</header>

								<!-- widget div-->
								<div>

									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<!-- end widget edit box -->

									<!-- widget content -->
									<div class="widget-body no-padding">

										<div id="donut-graph" class="chart no-padding"></div>

									</div>
									<!-- end widget content -->

								</div>
								<!-- end widget div -->

							</div>
							<!-- end widget -->
					
					</article>
					 
					
					
					</div>
					
					

							
						
</div>
<div style="display: none;">
<table>
<tr><td class="appphase">${appPhase}<td></tr>
<tr><td class="appStatus">${appStatus}<td></tr>

</table>
</div>


           <div id="addtab" title="Sanction Authority" style="display:none;text-align: center;">
				Sorry, You don't have authority to access this link
		   </div>
           
<script type="text/javascript">
	function applicationauthority(docketNo) {
		dialog = $("#addtab").dialog({
			autoOpen : false,
			width : 500,
			resizable : false,
			modal : true,

		}).dialog("open");
	}

	function redirectData2() {
		var appStatus = "In Progress";
		var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
		window.location.href = "./dataTab?appStatus=" + appStatus
				+ "&appPhase=" + appPhase;
	}

	function redirectData3() {
		var appStatus = "Completed";
		var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
		window.location.href = "./dataTab?appStatus=" + appStatus
				+ "&appPhase=" + appPhase;
	}

	function redirectData4() {
		var appStatus = "Pending";
		var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
		window.location.href = "./dataTab?appStatus=" + appStatus
				+ "&appPhase=" + appPhase;
	}

	function redirectData5() {
		var appStatus = "Clarifications";
		var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
		window.location.href = "./dataTab?appStatus=" + appStatus
				+ "&appPhase=" + appPhase;
	}

	$('#redirectData2')
			.click(
					function() {
						var appStatus = "In Progress";
						var appPhase = document
								.getElementsByClassName('appphase')[0].innerHTML;
						window.location.href = "./dataTab?appStatus="
								+ appStatus + "&appPhase=" + appPhase;

					});

	$('#redirectData3')
			.click(
					function() {
						var appStatus = "Completed";
						var appPhase = document
								.getElementsByClassName('appphase')[0].innerHTML;
						window.location.href = "./dataTab?appStatus="
								+ appStatus + "&appPhase=" + appPhase;

					});

	$('#redirectData4')
			.click(
					function() {
						
						var appStatus = "Pending";
						var appPhase = document
								.getElementsByClassName('appphase')[0].innerHTML;
						window.location.href = "./dataTab?appStatus="
								+ appStatus + "&appPhase=" + appPhase;

					});

	$('#redirectData5')
			.click(
					function() {
						var appStatus = "Clarifications";
						var appPhase = document
								.getElementsByClassName('appphase')[0].innerHTML;
						window.location.href = "./dataTab?appStatus="
								+ appStatus + "&appPhase=" + appPhase;

					});
	
	$('#redirectData9')	.click(
			function() {
				
				var appStatus = "Delay";
				var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
				window.location.href = "./dataTab?appStatus="
						+ appStatus + "&appPhase=" + appPhase;

			});

	// PAGE RELATED SCRIPTS

	/*
	 * Run all morris chart on this page
	 */
	$(document)
			.ready(
					function() {

						// DO NOT REMOVE : GLOBAL FUNCTIONS!
						pageSetUp();

						var phasestage = "${appPhase}";

						if (phasestage == "Power Sanction") {

							// Use Morris.Bar
							if ($('#normal-bar-graph').length) {

								Morris.Bar({
									element : 'normal-bar-graph',
									data : [ {
										x : 'Completed',
										y : '${applicationCompleted}',
										z : 0,
										a : 0,
										w : 0
									}, {
										x : 'In Progress',
										y : 0,
										z : '${applicationInProgress}',
										a : 0,
										w : 0
									}, {
										x : 'Pending',
										y : 0,
										z : 0,
										a : '${applicationPending}',
										w : 0
									}, {
										x : 'Clarifications',
										y : 0,
										z : 0,
										a : 0,
										w : '${applicationClarification}'
									} ],
									xkey : 'x',
									ykeys : [ 'y', 'z', 'a', 'w' ],
									labels : [ 'Completed', 'In Progress',
											'Pending', 'Clarifications' ]
								});

							}

							/* var total=(parseFloat('${applicationCompleted}')+parseFloat('${applicationInProgress}')+parseFloat('${applicationPending}'));
							var completed=((parseFloat('${applicationCompleted}')/total)*100);
							var progress=((parseFloat('${applicationInProgress}')/total)*100);
							var pending=((parseFloat('${applicationPending}')/total)*100); */
							var completed = parseFloat('${completedPerc}');
							var progress = parseFloat('${progressPerc}');
							var pending = parseFloat('${pendingPerc}');
							var clarificationcount = '${clarificationprogress}';
							var clarificationcount1=0;
							if(clarificationcount=='NaN'){
								clarificationcount1=0;
							}else{
								clarificationcount1=parseFloat(clarificationcount);
							}
							
							// donut
							if ($('#donut-graph').length) {

								Morris.Donut({
									element : 'donut-graph',
									data : [ {
										value : completed,
										label : 'Completed'
									}, {
										value : progress,
										label : 'In Progress'
									}, {
										value : pending,
										label : 'Pending'
									}, {
										value : clarificationcount1,
										label : 'Clarifications'
									} ],
									formatter : function(x) {
										return x + "%"
									}
								});
							}

							// time formatter
							// interval
						} else {
							// Use Morris.Bar
							if ($('#normal-bar-graph').length) {

								Morris.Bar({
									element : 'normal-bar-graph',
									data : [ {
										x : 'Completed',
										y : '${applicationCompleted}',
										z : 0,
										a : 0
									}, {
										x : 'In Progress',
										y : 0,
										z : '${applicationInProgress}',
										a : 0
									}, {
										x : 'Pending',
										y : 0,
										z : 0,
										a : '${applicationPending}'
									} ],
									xkey : 'x',
									ykeys : [ 'y', 'z', 'a' ],
									labels : [ 'Completed', 'In Progress',
											'Pending' ]
								});

							}

							/* var total=(parseFloat('${applicationCompleted}')+parseFloat('${applicationInProgress}')+parseFloat('${applicationPending}'));
							var completed=((parseFloat('${applicationCompleted}')/total)*100);
							var progress=((parseFloat('${applicationInProgress}')/total)*100);
							var pending=((parseFloat('${applicationPending}')/total)*100); */
							var completed = parseFloat('${completedPerc}');
							var progress = parseFloat('${progressPerc}');
							var pending = parseFloat('${pendingPerc}');

							// donut
							if ($('#donut-graph').length) {

								Morris.Donut({
									element : 'donut-graph',
									data : [ {
										value : completed,
										label : 'Completed'
									}, {
										value : progress,
										label : 'In Progress'
									}, {
										value : pending,
										label : 'Pending'
									} ],
									formatter : function(x) {
										return x + "%"
									}
								});
							}

							// time formatter
							// interval
						}

					});

	var cells = document.getElementsByClassName('redirect');
	for (var i = 0; i <= cells.length; i++) {
		cells[i].addEventListener('click', clickHandler);
	}

	function clickHandler() {
		var appStatus = this.textContent;

		var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;

		// var appphae=;
		// window.location.href="./finalForm?applicationId="+applicationId;
		// window.location.href="./dataTab?appStatus="+appStatus;
		window.location.href = "./dataTab?appStatus=" + appStatus
				+ "&appPhase=" + appPhase;
	}

	//setup_flots();
	/* end flot charts */
</script>
		
		
   

