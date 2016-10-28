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
				   	 
				  if('${bndrList}'!="" )
					{	  			
					  $("#billData").show();
					  $('#binderNo').val('${binderNo}');
					  $('#sdoCode').val('${sdocode}');
					  $('#billMonth').val('${billMonth}');
					}
				  if('${binderList}'!="" )
					{	  			
					  $("#binderNumber").show();
					}
				  
				  if('${hhbmList}'!="")
					{	  			
						$("#divTable").show();
					}
				  
   	    	if('${sdoCodeval}'!="" )
	        {
	        	$('#sdoCode').val('${sdoCodeval}');
	        }
   	    	   		$('#MIS-Reports-photoBilling').addClass('start active ,selected');
   	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	   });
 
  
    
  function validation()
  {
	  var sdcd=document.getElementById("sdoCode").value;
	  var bNo=document.getElementById("binderNo").value;
	  var bMonth=document.getElementById("billMonth").value;
	  if(sdcd=="1")
	  {
		    bootbox.alert("Please Select the SDOCODE");
			return false;
	  }
	  if(bNo=="1")
	  {
		    bootbox.alert("Please Select the BINDER NO.");
			return false;
	  }
	  if(bMonth=="1")
	  {
		    bootbox.alert("Please Select the BILL MONTH");
			return false;
	  }
	  
  }
	
  
  function displayBinder(sdoCode)
  {
	  $.ajax(
				{
					type : "GET",
					url : "./getDistinctBinder/" + sdoCode,
					cache:false,
				    success : function(response )
			  		  {
				    	  if(response!=null ||response!=" ")
				    	{
				    		$("#binderNumber").show(); 
				    		var html='<div><label>BINDER NO.</label><select   class="form-control" id="binderNo" name="binderNo"><option value="1">Select</option>';
					    	for(var j=0;j<response.length;j++)
				    		{
					    		html += '<option value="'+response[j]+'" >'+response[j].toUpperCase()+'</option>';
				    		}
				    		html+='</div>';
				    		$('#binderNumber').html(html);
				    	} 
				    	 
			    	}
				}		
		       );
  }
</script>
<div class="page-content" >
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
<div class="portlet box grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>Binder Wise Photo Billing Status for past 6 months</div>
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
													<%-- <c:if test="${not empty sdoVal}">
													<option value="${sdoVal}">${sdoVal}</option>
													</c:if> --%>
													<option value='1'>Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
													  <option value="${sdoVal}">${sdoVal}</option>
													</c:forEach>
										</select>
									</div>
								    <div class="form-group" id="binderNumber" style="display: none;">
										<label >BINDER NO.</label>
										<select   class="form-control" id="binderNo" name="binderNo" >
													<option value="1">Select</option>
													<c:forEach items="${binderList}" var="binderVal">
																		<option value="${binderVal}"
																			>${binderVal}</option>
																	</c:forEach>
										</select>
									</div>
									
									<div class="form-group">
										<label>BILL MONTH</label>
										<select   class="form-control" id="billMonth" name="billMonth" onkeypress="if(event.keyCode==13)focusOnFields(event)" >
													<option value="1">Select</option>
													<c:forEach items="${billMonthList}" var="billMonthVal">
																		<option value="${billMonthVal}"
																			>${billMonthVal}</option>
																	</c:forEach>
										</select>
									</div>
								<div class="form-actions" >
									<button type="submit"  class="btn dark" formaction="./getBinderBillData" formmethod="post" onclick="return validation();" >Show</button>
									<button type="submit" class="btn default" formaction="./binderWiseView" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>

                     </div>
         
                  </div>
       
       <div class="portlet box green"  id="billData" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Binder Wise Bill Details</div>
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
								<th style="text-align: center;">SI NO.</th>
								<th style="text-align: center;">Bill Month</th>
								<th style="text-align: center;">Bill Date</th>
								<th style="text-align: center;">Number of Connections billed</th>
								<th style="text-align: center;"></th>
								
							</tr>
						</thead>
						<tbody>
						<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${bndrList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">
									<fmt:parseDate value="${element[2]}" type="date" pattern="yyyyMM" var="fmtBlMon"/>
									<fmt:formatDate value="${fmtBlMon}"  type="date" pattern="MMM-YYYY"/>
									</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">
									<form>
									<input type="text" hidden="true" id="value" name="value" value="binderWise">
									<input type="text" hidden="true" id="billDate" name="billDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${element[0]}"/>'>
									<input type="text" hidden="true" id="binderNo" name="binderNo" value="${bndNo}">
									<input type="text" hidden="true" id="billMonth" name="billMonth" value="${bllMon}">
									<input type="text" hidden="true" id="sdocode" name="sdocode" value="${sdocode}">
									<!-- <button  type="submit" class="clsActionButton" style="background-color:lightgreen" formaction="./getBndrWiseBillData" formmethod="post" >View Details</button> -->
									<button  type="submit" class="btn green"  formaction="./getBndrWiseBillData" formmethod="post" >View Details</button>
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
</div>