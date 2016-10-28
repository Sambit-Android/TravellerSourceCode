<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
				  if('${bmdList}'!="")
					{	  
					  $('#sdoCode').val('${sdcd}');
						$("#billData").show();
					}
				  
				  if('${hhbmList}'!="")
					{	  			
						$("#divTable").show();
					}
	  
   	    	    App.init();
   	    	  TableEditable.init(); 
   	    	 FormComponents.init();
   	    	UIDatepaginator.init();
   	    	if('${sdoCodeval}'!="")
	        {
	        	$('#sdoCode').val('${sdoCodeval}');
	        }
   	    	$('#sdoCode').select2();
   	    	     $('#MIS-Reports-photoBilling').addClass('start active ,selected');
	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
	    		   });
  
  var billdate="";
  var UIDatepaginator = function () {

	    return {

	        //main function to initiate the module
	        init: function () 
	        {
	            //sample #3
	            $("#billDt").val(moment().format("YYYY-MM-DD"));
	            $('#datepaginator_sample_4').datepaginator
	            (
	            	{
	                  onSelectedDateChanged: function(event, date) {
	                  
	                    $("#billDt").val(moment(date).format("YYYY-MM-DD"));
	                }
	            });
	        } 

	    };

	}(); 
	
	function validation()
	  {
		  var sdCd=document.getElementById("sdoCode").value;
		  var billDt=document.getElementById("billDt").value;
		  if(sdCd=="1")
		  {
			    bootbox.alert("Please Select the SDO CODE.");
				return false;
		  }
		
	  }
	
 </script>
<div class="page-content" >
 				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
 <!-- BEGIN PORTLET--> 
 
               <div class="portlet box purple">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>Reading Day Wise Photo Billing Status</div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:;" class="reload"></a>
                     </div>
                  </div>
                  <div class="portlet-body">
                  <%-- <input type="hidden"  id="sdoCode" name="sdoCode" value="${sCode}"/>
                   <input type="hidden" id="billDate"  name="billDate"value="${bDate}"/> --%>
                   
                  
                     <h4>Select Date</h4>
                     <div id="datepaginator_sample_4" >
                     
                     </div>
                     

                     <div class="portlet-body form">
                     
							<form role="form" action="./getBmdList" method="post" >

								<div class="form-body">
								
								    <div class="form-group">
										<label >SECTION</label>
										<select   class="form-control" id="sdoCode" name="sdoCode" >
													<option value="1">Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
																		<option value="${sdoVal[0]}">${sdoVal[1]}-${sdoVal[0]}</option>
																	</c:forEach>
										</select>
									</div>
									 <input type="text" hidden="true" id="billDt" name="billDt"> 
									 <input type="text" hidden="true" id="value" name="value" value="readingDayWise">
								<div class="form-actions" >
									<button type="submit" class="btn purple"  formaction="./getBmdList" formmethod="post" onclick="return validation();">Show</button>
									<button type="submit" class="btn default" formaction="./readingDayWiseView" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>

                     </div>
                  </div>
                 <%-- <div class="portlet box green" style="display:none" id="billData">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Reading Day Wise Bill Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">
								<table class="table table-striped table-hover"  >
									<thead>
										<tr>
											
											<th>SI No.</th>
									        <th>MR Reader</th>
											<th>No.of Connections Billed</th>
											<th></th>
											
										</tr>
									</thead>
									<tbody>
										<c:set var="count" value="1" scope="page" />
								        <c:forEach var="element" items="${bmdList}">
										<tr>
										     
										     <td>${count}</td>											
											<td>${element[0]}</td>
											<td>${element[1]}</td>
										<td><a href="#" style="text-decoration: underline;" onclick="loadDetails('${element[0]}');">View Details</a></td>
										<td><button  type="submit"  style="background-color:lightgreen" onclick="loadDetails('${element[0]}');">View Details</button></td>			
										<td>
										<form >
										<input type="hidden"  id="sdoCode" name="sdoCode" value="${sCode}"/>
                                        <input type="hidden" id="billDate"  name="billDate"value="${bDate}"/>
                                        <input type="hidden" id="bmdReader"  name="bmdReader"value="${element[0]}"/>
                                        <button  type="submit" class="clsActionButton" style="background-color:lightgreen" formaction="./getBmdBillData" formmethod="post" onclick="displayTable();">View Details</button>
										</form>
										</td>
										</tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div> --%>
          
          
          <div class="portlet box blue"  id="billData" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Reading Day Wise Bill Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">
          <table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th style="text-align: center; background-color: lightgray; ">SI NO.</th>
								<th style="text-align: center; background-color: lightgray; ">BillDate</th>
								<th style="text-align: center; background-color: lightgray; ">MR Reader</th>
								<th style="text-align: center; background-color: lightgray;">No.of Connections Billed</th>
								<th style="text-align: center; background-color: lightgray;"></th>
								
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${bmdList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">
										<fmt:parseDate value="${element[3]}" type="date" pattern="yyyy-MM-dd" var="formatedDate"/>
										<fmt:formatDate value="${formatedDate}"  type="date" pattern="dd-MMM-YYYY"/>
									</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">
										<form action="./getBmdBillData" method="post">
										    <input type="text" hidden="true" id="value" name="value" value="readingDayWise">
											<input type="hidden"  id="sdoCode" name="sdoCode" value="${sCode}"/>
	                                        <input type="hidden" id="billDate"  name="billDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${element[3]}"/>'/>
	                                        <input type="hidden" id="billMonth"  name="billMonth"value="${element[2]}"/>
	                                        <input type="hidden" id="bmdReader"  name="bmdReader"value="${element[0]}"/>
	                                        <!-- <button  type="submit" class="clsActionButton" style="background-color:lightgreen"  onclick="displayTable();">View Details</button> -->
										    <button  type="submit" class="btn blue"   >View Details</button>
										</form>
									</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>
      </div> 
      </div>
      </div> 
       
       <jsp:include page="photoBillDisplay.jsp" />  
       <jsp:include page="modalPhoto.jsp" /> 
                  
                  <%-- <div class="portlet box green" id="divTable" style="display: none;">
                  <div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>PHOTO BILLING</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
                      <div class="portlet-body" >
							<div class="table-responsive">
							<table class="table table-striped table-hover"  id="billTable">
									<c:forEach var="element" items="${dataList}">
											<table  id="billTable" border="1" cellPadding='6'>
											<tr><th>Bill Month</th><td>
											<fmt:parseDate value="${element.billMonth}" type="date" pattern="yyyyMM" var="fmtBlMon"/>
											<fmt:formatDate value="${fmtBlMon}"  type="date" pattern="MMM-YYYY"/>
											</td></tr>
											<tr><th>Bill Date</th>
											<td>
											<fmt:parseDate value="${element.billDate}" type="date" pattern="dd-MM-yyyy" var="formatedDate"/>
											<fmt:formatDate value="${formatedDate}"  type="date" pattern="dd-MMM-YYYY"/>
											</td>
											</tr>
											<tr><th>Photo           </th><td><img src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}"  height="150" width="200" data-magnifyby="5" class="magnify"  title="${element.consumer_Sc_No}" /></td></tr>
											<tr><th>Consumer Number </th><td style="font-size:  ">${element.consumer_Sc_No }</td></tr>
											<tr><th>Reading Date    </th><td>${element.readin_Date }</td></tr>
											<tr><th>Present Rdng    </th><td>${element.present_Reading }</td></tr>
											<tr><th>Previous Rdng   </th><td>${element.previous_reading }</td></tr>
											<tr><th>Units Consumed  </th><td>${element.units }</td></tr>
											<tr><th>Meter Number    </th><td>${element.meterNo }</td></tr>
											<tr><th>Consumer Status </th><td>${element.cStatus }</td></tr>
											<tr><th>Mtr Rndg type   </th><td>${element.meterReadingType }</td></tr>
											</table>
										</c:forEach>
								</table>
				</div>
               </div>
               </div>
               </div> --%>
               
               <!-- END PORTLET-->
</div>