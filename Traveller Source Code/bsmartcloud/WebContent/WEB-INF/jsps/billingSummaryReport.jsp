<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
				  App.init();
			   	  TableEditable.init();
			   	 FormComponents.init();
				  if('${reportList}'!="")
				  {
					  $('#billData').show();
					  $('#sdoCode').val('${sdocode}');
					  $('#billMonth').val('${billMonth}');
					  $('#reportName').val('${reportName}');
				  }
			   	    	 
   	    	if('${sdoCodeval}'!="" &&'${sdocode}'=="" )
			 {
			   $('#sdoCode').val('${sdoCodeval}');
			 }
   	    	$('#MIS-Reports-photoBilling').addClass('start active ,selected');
  	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		  	    	   });
		  
  
  function validation()
  {
	  var sdcd=document.getElementById("sdoCode").value;
	  var reportName=document.getElementById("reportName").value;
	  var bMonth=document.getElementById("billMonth").value;
	  if(sdcd=="1")
	  {
		    bootbox.alert("Please Select the SDOCODE");
			return false;
	  }
	  if(bMonth=="1")
	  {
		    bootbox.alert("Please Select the BILL MONTH");
			return false;
	  }
	  if(reportName=="1")
	  {
		    bootbox.alert("Please Select the REPORT NAME.");
			return false;
	  }
  }
  
  
  
  
  </script>
<div class="page-content" >
<%-- <%@include file="pagebreadcrum.jsp"%> --%>
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
<div class="portlet box grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>Billing Summary Reports</div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:;" class="reload"></a>
                     </div>
                  </div>
                  <div class="portlet-body">

                     <div class="portlet-body form">
                     
							<form role="form" id="form1"  >

								<div class="form-body">
								
								  <div class="form-group">
									<label >SDO CODE</label>
										<select   class="form-control" id="sdoCode" name="sdoCode" onchange="displayBinder(this.value);">
													<option value='1'>Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
													  			<option value="${sdoVal[0]}">${sdoVal[1]}-${sdoVal[0]}</option>
													</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label>BILL MONTH</label>
										<select   class="form-control" id="billMonth" name="billMonth" >
													<option value="1">Select</option>
													<c:forEach items="${billMonthList}" var="billMonthVal">
																		<option value="${billMonthVal}"
																			>${billMonthVal}</option>
																	</c:forEach>
										</select>
									</div>
								    <div class="form-group"  >
										<label >REPORTS</label>
										<select   class="form-control" id="reportName" name="reportName" >
													<option value="1">Select</option>
													<!-- <option value="binderwise">BINDER WISE</option> -->
													<option value="mrwise">	MR WISE</option>
													<!-- <option value="bindermrwise">BINDER-MR WISE</option> -->
													<option value="billdatewise">  BILLDATEWISE BILLED</option>
													<!-- <option value="binderbilldatewise">BINDER-BILLDATE WISE BILLED</option> -->
													<option value="mrbilldatewise">	MR-BILLDATE WISE BILLED</option>
													<!-- <option value="bindermrbilldatewise">BINDER-MR-BILLDATE WISE BILLED</option> -->
										</select>
									</div>
									
									
								<div class="form-actions" >
									<button type="submit"  class="btn dark" formaction="./getReportDetails" formmethod="post" onclick="return validation();" >Show</button>
									<button type="submit" class="btn default" formaction="./billingSummaryReport" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>

                     </div>
         
                  </div>
       
     <c:if test="${reportName == 'mrbilldatewise'}">
       <div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>MR-BILLDATEWISE BILLED DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">MR</th>
								<th style="text-align: center;">BillMonth</th>
								<th style="text-align: center;">BillDate</th>
								<th style="text-align: center;">Billed</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; "><fmt:formatDate value="${element[1]}" pattern="dd-MMM-yyyy"/></td>
									<td style="text-align: center; ">${element[3]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
			</div> 
      </div>
      </div>		
	</c:if>	
	
	   <c:if test="${reportName == 'billdatewise'}">
					<div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>BILLDATEWISE BILLED DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">		
         
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">BillMonth</th>
								<th style="text-align: center;">BillDate</th>
								<th style="text-align: center;">Billed</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; "><fmt:formatDate value="${element[0]}" pattern="dd-MMM-yyyy"/></td>
									<td style="text-align: center; ">${element[2]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
				</div> 
      </div>
      </div>	
	</c:if>	
						
						<c:if test="${reportName == 'binderbilldatewise'}">
							<div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>BINDER-BILLDATE WISE BILLED DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">BINDER</th>
								<th style="text-align: center;">BILLMONTH</th>
								<th style="text-align: center;">BILLDATE</th>
								<th style="text-align: center;">BILLED</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
				</div> 
      </div>
      </div>			
	</c:if>						
							
						<c:if test="${reportName == 'bindermrbilldatewise'}">	
						<div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>BINDER-MR-BILLDATE WISE BILLED DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">	
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">BINDER</th>
								<th style="text-align: center;">MR</th>
								<th style="text-align: center;">BILLMONTH</th>
								<th style="text-align: center;">BILLDATE</th>
								<th style="text-align: center;">BILLED</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">${element[4]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
      </div> 
      </div>
      </div>
   </c:if>  
   
   
   <c:if test="${reportName == 'binderwise'}">	
						<div class="portlet box green" style="display: none;" id="billData" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>BINDERWISE DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">	
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">BINDER</th>
								<th style="text-align: center;">CONSUMERS</th>
								<th style="text-align: center;">BILLED</th>
								<th style="text-align: center;">UNBILLED</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
      </div> 
      </div>
      </div>
   </c:if>        
   
   
             <c:if test="${reportName == 'mrwise'}">	
						<div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>MRWISE DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">	
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">MR</th>
								<th style="text-align: center;">CONSUMERS</th>
								<th style="text-align: center;">BILLED</th>
								<th style="text-align: center;">UNBILLED</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
      </div> 
      </div>
      </div>
   </c:if> 
   
   
   <c:if test="${reportName == 'bindermrwise'}">	
						<div class="portlet box green"  id="billData" style="display: none;" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>BINDER-MR-WISE DETAILS</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">	
							
							<table class="table table-striped table-hover table-bordered"
						id="id1">
						<thead>
							<tr style="background-color: lightgray; ">
								<th style="text-align: center;">SINO.</th>
								<th style="text-align: center;">SDOCODE</th>
								<th style="text-align: center;">BINDER</th>
								<th style="text-align: center;">MR</th>
								<th style="text-align: center;">CONSUMERS</th>
								<th style="text-align: center;">BILLED</th>
								<th style="text-align: center;">UNBILLED</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${reportList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${sdocode}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">${element[4]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
      </div> 
      </div>
      </div>
   </c:if>
            
            
           
                  
</div>