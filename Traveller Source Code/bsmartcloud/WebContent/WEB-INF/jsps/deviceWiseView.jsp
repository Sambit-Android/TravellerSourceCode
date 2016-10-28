<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!-- <style>
#divTable 
{
    display: inline-block;
}

#billTable 
{
	display:inline-block;
	border-collapse: separate;
	border-spacing: 1px;
	border-color: gray; 
	background: #D3D3FF;
	margin-left: 60px;
	margin-bottom: 20px;
	font-size: 14px;
}

#deviceButton
{
	margin-bottom: 10px;
	display:inline-block; 
}
#btnId
{
   background-color: #AAD4FF;
   font-size: 14px;
}
</style> --> 
		
		
<script>
$(".page-content").ready(function()
    	   {     
					if('${deviceData}'!="")
					{	  			
						$("#deviceId").val('${deviceId}');
						$("#deviceDivId").show();
					}
					if('${hhbmList}'!="")
					{	  			
						$("#divTable").show();
					}    
	
    	        App.init();
    	        TableEditable.init();
    	        FormComponents.init();
    	        $('#MIS-Reports-photoBilling').addClass('start active ,selected');
 	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
 	    	  }); 
    	   
		function validation()
		{
				 var numbers = /^[0-9]+$/;
			  var devId=document.getElementById("deviceId").value.trim();
			  if(devId==""|| devId==null)
				 {
				  bootbox.alert("Please enter DEVICE ID");
					return false;
				 }
			  else if(!numbers.test(devId))
			  {
				  bootbox.alert("Please enter only numbers in DEVICE ID");
					return false;
			  } 
				else if(devId.length>=20)
				  {
					  bootbox.alert("Invalid length of DEVICE ID");
						return false;
				  }  
		}
    	   
</script>



<%-- <!-- BEGIN PAGE -->
<div class="page-content" >
     <%@include file="pagebreadcrum.jsp" %> 
    
  	
			
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->    
		 <c:forEach items="${obj}" var="item"> 	
			<img src="./consumerWiseView/getImage/${item.consumer_Sc_No}">
	     </c:forEach> 
	      
			<img src="<c:url value='/consumerWiseView/getImage'/>" width="100px;" height="200px;"/>	
			 	 
		</div>
		<!-- END PAGE --> --%>
 
<div class="page-content" >
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
<!-- BEGIN PAGE -->
<div class="portlet box yellow">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>Device Wise Photo Billing Status for past 30 days </div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:;" class="reload"></a>
                     </div>
                  </div>
                  <div class="portlet-body">

                     <div class="portlet-body form">
							<form role="form" >
								<div class="form-body">
								    <%-- <div class="form-group">
										<label>SDO CODE</label>
										<select   class="form-control" id="sdoCode" name="sdoCode" onchange="displayConNo(this.value);" >
													<option value="1">Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
																		<option value="${sdoVal}"
																			>${sdoVal}</option>
																	</c:forEach>
										</select>
									</div> --%>
									<div class="form-group" id="consumerId">
									<label>DEVICE ID</label><br>
									<input type="text" class="form-control"  placeholder="Enter Device Id" id="deviceId" name="deviceId"/> <!-- onkeyup="return validation(); -->
									</div>
									
								<div class="form-actions" >
									<button type="submit" class="btn yellow" formaction="./getDeviceWiseData" formmethod="post" onclick="return validation();">Show</button>
									<button type="submit" class="btn default" formaction="./deviceWiseView" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>

                     </div>
         
                  </div>
            
					<div class="portlet box blue" id="deviceDivId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Device Wise Bill Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
						<c:forEach var="element" items="${deviceData}">
						<form id="deviceButton">
						    <input type="text" hidden="true" id="value" name="value" value="deviceWise">
							<input type="text" hidden="true" id="bDate" name="bDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${element[1]}"/>'/>
							<input type="text" hidden="true" id="dvceId" name="dvceId" value="${deviceId}"/> 
							<button id="btnId" type="submit" title="Click here to see bill details" formaction="./getDeviceAllData" formmethod="post" style="text-align: left;">Bill Date&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;<fmt:formatDate pattern="MMM-dd"  value="${element[1]}" /><br><br>No.of Bills &nbsp:&nbsp;&nbsp;&nbsp;${element[0]}</button>
						</form>	
						</c:forEach>
						</div>
					</div>
					
				<jsp:include page="photoBillDisplay.jsp" />	
				<jsp:include page="modalPhoto.jsp" />	
					<%-- <div class="portlet box purple" id="divTable" style="display: none;">
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
									<c:forEach var="element" items="${deviceBillList}">
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
				</div> --%>
					
</div>
  <!-- END PAGE --> 
 
