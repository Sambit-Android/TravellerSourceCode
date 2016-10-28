<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		
<script>
$(".page-content").ready(function()
    	   {     
					if('${hhbmList}'!="")
					{	  			
						$('#sdoCode').val('${sdoCd}');
						$('#consumerNo').val('${consNo}');
						$("#divTable").show();
					}
	                    
	
    	        App.init();
    	        TableEditable.init();
    	        FormComponents.init();
    	        if('${sdoCodeval}'!="")
    	        {
    	        	$('#sdoCode').val('${sdoCodeval}');
    	        }
    	        /* else
    	        {
    	        	$('#sdoCode').val('1');
    	        } */
    	        $('#sdoCode').select2();

    	        $('#MIS-Reports-photoBilling').addClass('start active ,selected');
 	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
 	    	 }); 
    	   
		function validation()
		{
			var stringOnly = /^[A-Za-z0-9\.\s\-]+$/;
			  var sdCd=document.getElementById("sdoCode").value;
			  var consNo=document.getElementById("consumerNo").value.trim();
			  if(sdCd=="1")
			  {
				    bootbox.alert("Please Select the SDO CODE.");
					return false;
			  }
			  if(consNo==""||consNo==null)
			  {
				    bootbox.alert("Please enter the CONSUMER NO.");
					return false;
			  }
			  else if(!stringOnly.test(consNo))
				{
				     bootbox.alert("Special characters are not allowed in CONSUMER NO.");
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
<!-- BEGIN PAGE -->
<div class="portlet box blue">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>Consumer Wise Photo Billing Status&nbsp;&nbsp;&nbsp;&nbsp;[ Photo billing report of consumer for past 6 months ]</div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:;" class="reload"></a>
                     </div>
                  </div>
                  <div class="portlet-body">

                     <div class="portlet-body form">
							<form role="form" >
								<div class="form-body">
								    <div class="form-group">
										<label>SECTION</label>
										<select   class="form-control" id="sdoCode" name="sdoCode" >
													<%-- <c:if test="${not empty sdoVal}">
													<option value="${sdoVal}">${sdoVal}</option>
													</c:if> --%>
											         <option value="1">Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
																		<option value="${sdoVal[0]}">${sdoVal[1]}-${sdoVal[0]}</option>
																	</c:forEach>
										</select>
									</div>
									<div class="form-group" id="consumerId">
									<label>CONSUMER NO</label><br>
									<input type="text" class="form-control"  placeholder="Enter Consumer No" id="consumerNo" name="consumerNo">
									<input type="text" hidden="true" id="value" name="value" value="consumerWise">
									</div>
									
								<div class="form-actions" >
									<button type="submit" class="btn blue"  formaction="./getConsumerDetails" formmethod="post" onclick="return validation();">Show</button>
									<button type="submit" class="btn default" formaction="./consumerWiseView"  formmethod="post">Clear</button>                              
								</div>
								</div>
							</form>
						</div>

                     </div>
         
                  </div>
                      <jsp:include page="photoBillDisplay.jsp" />
                     <jsp:include page="modalPhoto.jsp" />
</div>
  <!-- END PAGE --> 
 
