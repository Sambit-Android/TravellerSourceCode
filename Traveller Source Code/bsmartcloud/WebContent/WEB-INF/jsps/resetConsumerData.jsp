<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

<%@page import="java.util.Date"%>

<style>
#hhbmcons span.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}
</style>
<script type="text/javascript">
  	$(".page-content").ready(function()
  		   {    	
  		    	App.init();
  		    	TableEditable.init(); 
  		      FormComponents.init();
  		    $('#meterData-Acquisition').addClass('start active ,selected');
			$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	 
  		   
  		   });
  	
  	
  	function getMatchedData(sdoCode){
  	  
  		
  		  
  		  if(sdoCode == 0){
  			 
  			  $("#bmdReading").attr("disabled","true");
  			  $("#billMonths").attr("disabled","true");
  			  
  		  }else{
  			  var mrcds = "";
  			  var i=0;
  			  
  			  $.ajax({
  				  
  				  type:"POST",
  				  url:"./getMatchedBillMonths",
  				  data:{"sdoCode":sdoCode},
  				  dataType:"JSON",
  				  success:function(response){
  					if(response.length != 0){
  						mrcds+="<select id='billMonths' name='billMonths' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='getMatchedBmdReadings()' value=''><option id='mrCodeOpt' vlaue=''>Select Bill Month</option>";
  						for(i=0;i<response.length;i++){
  							mrcds+="<option vlaue='"+response[i]+"'>"+response[i]+"</option>";
  						}
  						mrcds+="</select><span></span>";
  						
  						$("#billM").html(mrcds);
  						$("#blm").hide();
  						
  					}else{
  						$("#blm").remove();
  						$("#billMonths span").html("<span style='color:red' id='blm'>Bill Months Not Available</span>");
  								$("#billMonths").attr("disabled","true");
  								$("#blm").show();  
  					}
  					
  									  
  					
  				  }
  			  });

  				  }
  		  
  		    }
  	function getMatchedBmdReadings(){
  		var sdoCode = $("#sdoCode").val();
  		var billMonth = $("#billMonths").val();
  		
  		if(sdoCode != 0 && billMonth != 0){
  			var i=0;
  			var bmdRd = "";
  			$.ajax({
  				type:"POST",
  				url:"./getMatchedBmdReadings",
  				data:{"sdoCode":sdoCode,"billMonth":billMonth},
  				dataType:"JSON",
  				success:function(response){
  					
  					if(response.length != 0){
  						bmdRd+="<select id='bmdReading' name='bmdReading' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''><option  vlaue=''>Select MR Code</option>";
  						for(i=0;i<response.length;i++){
  							bmdRd+="<option vlaue='"+response[i]+"'>"+response[i]+"</option>";
  						}
  						bmdRd+="</select><span></span>";
  						
  						$("#brdRdg").html(bmdRd);
  						$("#brdm").hide();
  						
  					}else{
  						$("#brdm").remove();
  						$("#bmdReading span").html("<span style='color:red' id='brdm'>MR Codes Not Available</span>");
  								$("#bmdReading").attr("disabled","true");
  								$("#brdm").show();  
  					}
  				}
  			});
  		}else{
  			bootbox.alert("Select SDO Code and Bill Month First");
  		}
  	}
  	
  	
/*   	function validateForm() {
  		//alert("working");
  		var validator = $("#hhbmcons").validate({
  			
  			rules : {
  				sdoCode:"required",
  				bmdReading:"required",
  				billMonths:"required"
  				

  			},
  			errorElement : "span", 

  			messages : {
  				billMonths:"Select Bill Month",
  				bmdReading:"Select MR Code ",
  				sdoCode : "Select SDO Code"
  			}
  		});

  		
  		if (validator.form()) {
  			if(validateAllData()){
  				$("form#hhbmcons").attr("action","./getConsumerData");
  	  			$("form#hhbmcons").submit();	
  			}
  			
  		}
  		$(".cancel").click(function() {
  		    validator.resetForm();
  		});

  	} */
  	
  	
  	function validateAllData(){
  		//alert($("#sdoCode").val()+"-----"+$("#billMonths").val()+"-----"+$("#bmdReading").val());
  		
  		if($("#sdoCode").val() =='' || $("#bmdReading").val() =='' || $("#billMonths").val() =='' || $("#bmdReading").val() =='Select MR Code' || $("#billMonths").val() =='Select Bill Month'){
  		 // alert("working.........");
  		  bootbox.alert("<span style='color:red;'>All Fields Are Required</span>");
  		  return false;
  	  }else{
  		  return true;
  	  }
  	}
  	
  	function submitSprtForm(){
  		bootbox.confirm("Are you Sure to Reset Consumer Data..!",function(result){
  			if(result){
  		
  				$("form#sprtForm").attr("action","./updateDownStatus");
  		  		$("form#sprtForm").submit();
  			}
  		});
  		
  	}
 	</script>

<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%>
	<c:if test="${(results ne 'notDisplay') && (results ne 'allocated') }">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>	
		<div class="row "></div>
			<div class="row "></div>
		
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->

			
		
			<!-- BEGIN PAGE CONTENT-->
		
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom tabbable-full-width">
						
						<div class="tab-content">
							<div id="tab_2_2" class="tab-pane active">
								
								<div class="row">
									<div class="col-md-12">
										<div class="booking-search">
											<form action="./getConsumerData" method="post" id="hhbmcons">
												<div class="row form-group">
														<div class="col-md-4"> 
															
														 <select id="sdoCode" class="form-control selectpicker select2me input-sm" name="sdoCode" onchange="getMatchedData(this.value)">
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodesList}">
																<option value="${element}" id="sdoCodeOpt">${element}</option>
															</c:forEach>
														  </select>
														
														</div>
														<div class="col-md-4" id="billM">
																
															<select id="billMonths" class="form-control selectpicker placeholder-no-fix input-sm " name="billMonths" disabled="disabled">
														    <option value='' >Select Bill Month</option>
														    
														  	</select><span></span>	
														</div>
														
														<div class="col-md-4" id="brdRdg">
																
															<select id="bmdReading" class="form-control selectpicker placeholder-no-fix input-sm" name="bmdReading" disabled="disabled">
														    <option value='' >Select MR Code</option>
														    
														  	</select><span></span>	
														</div>
													
													
														
													</div>
													
												
												<button class="btn blue btn-block margin-top-20" onclick="return validateAllData()">
													Show <i class="m-icon-swapright m-icon-white"></i>
												</button>
											</form>
											</div>
										</div>
									</div>
								</div>
								</div>
							</div>
							</div>
							<div class="row"></div>
							<div class="row">
					<div class="col-md-12">

                 <c:choose>
                 <c:when test="${totalConnections ne null}">
                 			<form:form action="" id="sprtForm" method="POST" commandName="hhbmcons" modelAttribute="hhbmcons">
                 				<form:input type="hidden" path="sdoCode" name="sdoCode" value="${sdoCode}"></form:input>
                 				<form:input type="hidden" name="billMonth" path="billMonth" value="${billMonth}"></form:input>
                 				<form:input type="hidden" name="bmdReading" path="bmdReading" value="${bmdReading}"></form:input>
                 				<form:input type="hidden" name="downStatus" path="downStatus" value="0"></form:input>
                 			</form:form>
                             <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
								<thead>
									<tr>	
										<th hidden="true">SDO Code</th>
										<th>Bill Month</th>
										<th hidden="true">Bmd Reading</th>
										<th>Total Connections</th>
										<th>Billed Count</th>
										<th>Down Count</th>
										<th>Reset</th>
									
									</tr>
								</thead>
								<tbody>
									<tr>
										<td hidden="true"><c:out value="${sdoCode}"></c:out></td>
										<td hidden="true"><c:out value="${bmdReading}"></c:out></td>
										<td><c:out value="${billMonth}"></c:out></td>
										<td><c:out value="${totalConnections}"></c:out></td>
										<td><c:out value="${billedConns}"></c:out></td>
										<td><c:out value="${totalDownloaded}"></c:out></td>
										
										<td>
										<c:choose>
											<c:when test="${totalDownloaded eq '0'}">
											<button class=" btn-default" >Reset</button>
											</c:when>
											<c:otherwise><button class="btn-success" onclick="submitSprtForm()">Reset</button></c:otherwise>
										</c:choose>
										
										</td>
									</tr>						
								</tbody>
							</table>
                 </c:when>
                 </c:choose>
							</div>
								
							<!--end tab-pane-->
						</div>
					</div>
				</div>
				<!--end tabbable-->
			</div>
			<!-- <div id="stack1" class="modal fade" tabindex="-1" data-width="400">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Add Machine Details</h4>
						</div>

						<div class="modal-body">
							<div class="row">
								<div class="col-md-12"></div>
							</div>

						</div>

					</div>
				</div>
			</div> -->

		

	</div>

	<!-- END PAGE CONTENT -->






