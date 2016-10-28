
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

<style>
#mrAudit span.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}
</style>
<script>

$(".page-content")
.ready(
		function() {
			App.init();
			TableEditable.init();
			FormComponents.init();
			
			$('#Cash_Collection').addClass('start active ,selected');
			$(
					"#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#meterData-Acquisition,#vigilance-management,#Disconn-Reconn")
					.removeClass('start active ,selected');

			
		});
	

		

function getMatchedData(sdoCodeAndLocation){
	
	var sdonloc = sdoCodeAndLocation.split('-');
	var sdoCode = sdonloc[0];
	  
	$("#mrcAvail").remove();
	$("#mrcAvail").hide();
	  
	  if(sdoCode == 0){
		  $("#mrCode").attr("disabled","true");
		  
	  }else{
		

		  var mrcds = "";
		  var i=0;
		  
		  $.ajax({
			  
			  type:"POST",
			  url:"./getMatchedData",
			  data:{"sdoCode":sdoCode},
			  dataType:"JSON",
			  success:function(response){
				if(response.mrCodes.length != 0){
					mrcds+="<select id='mrCode' name='mrCode' class='form-control placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='checkMRCAvail()'><option id='mrCodeOpt' vlaue='' selected='selected'>Select MR Code</option>";
					for(i=0;i<response.mrCodes.length;i++){
						mrcds+="<option id='mrCodeOpt' vlaue='"+response.mrCodes[i]+"'>"+response.mrCodes[i]+"</option>";
					}
					mrcds+="</select><span></span>";
					
					$("#mrcd").html(mrcds);
					$("#mrcAvail").hide();
					
				}else{
					$("#mrcAvail").remove();
					$("#mrcd span").html("<span style='color:red' id='mrcAvail'>MR Codes Not Available</span>");
							$("#mrCode").attr("disabled","true");
							$("#mrcAvail").show();  
				}
				
								  
				
			  }
		  });

			  }
	  
	    }
		

function validateForm(){
	var validator = $("form#mrAudit").validate({
		rules:{
			sdoCode:"required",
			mrCode:"required"
			
		},
		errorElement:"span",
		messages:{
			sdoCode:"Please Select SDO Code",
			mrCode:"Please Select MR Code",
			
			
		}
	});
	
	if(validator.form()){
		
			$("form#mrAudit").attr("action","./mrAuditByDates");
			$("form#mrAudit").submit();
			
	}
	
}
</script>

<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	
	
	<div class="row" >
		<div class="col-md-12" id="depdt">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<div class="tab-content">
							<div id="tab_2_2" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<div class="booking-search">
											<form action="./mrAuditByDates" id="mrAudit" method="POST">
							
							<div class="row form-group">
								<div class="col-md-6"> 
									
								 <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getMatchedData(this.value)">
								    <option value="" selected="selected">Select SDO Code</option>
								    <c:forEach var="element" items="${sdoCodes}">
										<option value="${element['0']}-${element['1']}" id="sdoCodeOpt">${element['0']}-${element['1']}</option>
									</c:forEach>
								  </select>
								
								</div>
								<div class="col-md-6" id="mrcd">
										
									<select id="mrCode" class="form-control selectpicker placeholder-no-fix input-medium" name="mrCode">
								    <option value="" selected="selected">Select MR Code</option>
								    
								  	</select><span></span>	
								</div>
							
								
							</div>
							<div class="row form-group">
													<div class="col-md-6">
														<label class="control-label">Start Date:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium" size="16" type="text"
																value=" <fmt:formatDate value="${fromDate}" pattern="dd-MM-yyyy" /> " data-date="${fromDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" />
																
														</div>
													</div>
													<div class="col-md-6">
														<label class="control-label">End Date:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium" size="16" type="text"
																value="<fmt:formatDate value="${toDate}" pattern="dd-MM-yyyy" /> " data-date="${toDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="toDate" />
														</div>
													</div>
												</div>
												<button class="btn blue btn-block margin-top-20" onclick="validateForm()">
													SEARCH <i class="m-icon-swapright m-icon-white"></i>
												</button>
							
							
							</form>
						
						</div>
					
					</div>
				</div>
					</div>
					</div>
				</div>
			</div>
		</div>
				
				<div class="row">
					<div class="col-md-12">
						<c:choose>
			                 <c:when test="${dates ne null}">
			    					<div class="row col-md-12"><div class="col-md-8">SDO Code:-&nbsp;&nbsp; <span style="background-color:#eee;border:1px solid #f9f9f9;padding:10px;">${sdoCode}&nbsp;-&nbsp;${slocation}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MR Code:-&nbsp;&nbsp;<b>${mrCode}</b></div><div class="col-md-4">Avail Bal:-&nbsp;&nbsp;<b>${availBalance}</b></div><br/><br/></div>             			
			                            <div class="row"></div>
			                             <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
											<thead>
												<tr>	
												
													<th>Date</th>
													<th>Debit</th>
													<th>Credit</th>
												
												</tr>
											</thead>
											<tbody>
												<c:forEach var="sdate" items="${dates}" varStatus="status" begin="${status.count}">
												<tr>
													
												<td>
													<%-- <fmt:formatDate pattern="dd-MM-yyyy"  value="${element}" /> --%>
													<c:out value="${sdate}" ></c:out>
												</td>
												<td>
													<c:forEach var="mrdepts" items="${mrdeposits}">
														<%-- <c:out value="${mrdepts['0']}"></c:out> --%>
														<c:choose>
															<c:when test="${sdate==mrdepts['1']}">
																<c:out value="${mrdepts['0']}" default="No Deposits"></c:out><br/>
															</c:when>
															
														</c:choose>
													</c:forEach>
																										
													</td>
													<td>
														<c:forEach var="payl" items="${paylist}">
															<%-- <c:out value="${payl['0'] }"></c:out> --%>
															<c:choose>
																<c:when test="${sdate==payl['1']}">
																	<c:out value="${payl['0']}" default="NO Payments"></c:out><br/>
																</c:when>
																
														</c:choose>
															
														</c:forEach>
													</td>
												    
													
												</tr>
												
													
												</c:forEach>
																			
											</tbody>
								</table>
			                 </c:when>
			                </c:choose>
					
					</div>
				</div>
					
					
				
	</div>
</div>
</div>
	