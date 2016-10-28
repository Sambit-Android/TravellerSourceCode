
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

<style>
#mrDeposits label.error {
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

			 $('.datepicker').datepicker({
			        format: 'mm-dd-yyyy',
			        endDate: '+0d',
			        autoclose: true
			    });
		});
	

		
	function abc()
	{
		
		$('#myForm').attr('action','./getAllDepositsByDate').submit();
		return false;
	}
function addMRDeposit(param) {

	$("#mrcAvail").remove();
	$("#mrcAvail").hide();
	$("#mrDeposits").trigger("reset");
	// $("#mrCreditMasters").attr("action","./addMRMaster");

	//$("#mrDeposits").attr("action", "./makeDeposit");
	$("#addOption").html(" MR Deposit");
	$("#modelHeader").html("MR Deposit");
	
	
	
	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}
 
function getMatchedData(sdoCode){
	  
	$("#mrcAvail").remove();
	$("#mrcAvail").hide();
	  
	  if(sdoCode == 0){
		  $("#mrCode").attr("disabled","true");
		  
	  }else{
		

		  var mrcds = "";
		  var i=0;
		  
		  $.ajax({
			  
			  type:"POST",
			  url:"./getMrCodeData",
			  data:{"sdoCode":sdoCode},
			  dataType:"JSON",
			  success:function(response){/* 
				if(response.mrCodes.length != 0){
					mrcds+="<select id='mrCode' name='mrCode' class='form-control placeholder-no-fix' type='text' autocomplete='off' placeholder='' onchange='checkMRCAvail()'><option id='mrCodeOpt' vlaue='' selected='selected'>Select MR Code</option>";
					for(i=0;i<response.mrCodes.length;i++){
						mrcds+="<option id='mrCodeOpt' vlaue='"+response.mrCodes[i]+"'>"+response.mrCodes[i]+"</option>";
					}
					mrcds+="</select>";
					
					$("#mrcd").html(mrcds);
					$("#mrcAvail").hide();
					
				}else{
					$("#mrcAvail").remove();
					$("#mrcd").after("<td id='mrcAvail'><span style='color:red'>MR Codes Not Available</span></td>");
							$("#mrCode").attr("disabled","true");
							$("#mrcAvail").show();  
				} */
				  $('#mrcodes').show();
					 if(response.length != 0)
					{
						mrcds+="<select id='mrCode' name='mrCode' class='form-control placeholder-no-fix' type='text' autocomplete='off' placeholder='' onchange='checkMRCAvail()'><option id='mrCodeOpt' value='' selected='selected'>Select MR Code</option>";
						for(i=0;i<response.length;i++)
						{
							mrcds+="<option id='mrCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						mrcds+="</select>";
						
						$("#mrcd").html(mrcds);
						$("#mrCodeError").hide();
						
					}else{
						$("#mrcAvail").remove();
						$("#mrcd").after("<td id='mrcAvail'><span style='color:red'>MR Codes Not Available</span></td>");
								$("#mrCode").attr("disabled","true");
								$("#mrcAvail").show();  
					} 
								  
				
			  }
		  });

			  }
	  
	    }
		

function validateForm(){
	var validator = $("form#mrDeposits").validate({
		rules:{
			sdoCode:"required",
			mrCode:"required",
			amount:"required"
		},
		messages:{
			sdoCode:"Please Select SDO Code",
			mrCode:"Please Select MR Code",
			amount:"Please Enter Amount"
			
		}
	});
	
	if(validator.form()){
		$("form#mrDeposits").attr("action","./makeDeposit");
		$("form#mrDeposits").submit();
		
	}
	$(".cancel").click(function() {
	    validator.resetForm();
	});
}

function getMrmaster(sdocode)
{
	  
	  $.ajax({
	    	url:'./getMrmaster'+'/'+sdocode,
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	success:function(response)
	    	{
	    		var substation='';
	    		 substation+="<td>MRCODE</td><td><font color=red>*</font></td><td><select id='mrCode' name='mrCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select Mrcode</option>";
				for( var i=0;i<response.length;i++){
					substation+="<option  value='"+response[i].trim()+"'>"+response[i]+"</option>";
				}
				substation+="</select></td><span></span>";
				
				$("#mrcodeTr").html(substation);
				$('#mrCode').select2();
	    	}
	  }); 
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

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>MR Deposits
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green"
								 id="addMRDeposit" onclick="addMRDeposit(this.id)">
								New Deposit <i class="fa fa-plus"></i>
							</button>
						</div>
						
					
					
					</div>
					<%-- <div class="container">
						<div class='input-group date input-medium date-picker' id='dpkr' data-date-format="yyyy-mm-dd" >
							<input type='text' class="form-control" id="getDepDate" data-date-format="yyyy-mm-dd" value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd' /> " data-date="${currentDate}" />
							<span class="input-group-btn">
							<span class="glyphicon glyphicon-calendar"></span></span>
						
						</div>
					
					</div> --%>
					<div class="row">
					<form action="" method="post" id="myForm">
						<div class="input-icon col-md-6">
							<i class="fa fa-calendar"></i>
							
								<input class="form-control input-medium date-picker" size="16" type="text" style="width:180px!important;"
								value="<fmt:formatDate value='${currentDate}' pattern='yyyy-MM-dd' />" data-date="${currentDate}" data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="getDeptDate" name="reqDate" onchange="return abc();" />
							
							
							
							 
										
									
						</div>
					
					</form>
					
					</div>
					<div id="depositsDisplay">
						<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
							<thead>
						
							<tr>
								
								<th>SDO Code</th>
								<th>MR Code</th>
								<th>Deposits</th>
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody id="depositsDisplay">
						
							<c:forEach var="element" items="${mrdepositsList}">
								<tr>
									<td>${element.sdoCode}</td>
									<td>${element.mrCode}</td>
									<td>${element.amount}</td>
									<td>${element.remarks}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
						
					
					
					</div>
					
													
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Adding MR Credit Master Access and Editing  Data Form Started -->
	
	<div id="regData" class="modal fade" >
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title"><span id="modelHeader"></span></h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											<form:form action="" modelAttribute="mrDeposits"
																	commandName="mrDeposits" method="post" id="mrDeposits">
												<table >
												
													<tr hidden="true">
														<td><form:label path="id">ID</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id" ></form:input></td>
																												
													</tr>
																																							
													<tr id="sdoc" >
															<td><label path="sdoCode">SDO Code</label> </td>
															<td><font color="red">*</font></td>
															<td><select 
																	id="sdoCode"
																	class="form-control select2me" type="text"
																	autocomplete="off" placeholder="" name="sdoCode" onchange="getMrmaster(this.value)">
																	<option id="sdoCodeOpt" value="1" >Select</option>
																	<c:forEach var="element" items="${sdoCodes}">
													<option value="${element[0]}" id="sdoCodeOpt"
														>${element[0]}-${element[1]}</option>
												</c:forEach>
																</select></td>
														</tr>
														
														<%-- <tr id="mrcodes" style="display: none;">
															<td><form:label path="mrCode">MR Code </form:label></td>
															<td><font color="red">*</font></td>
															<td id="mrcd"><form:select path="mrCode"
																	id="mrCode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="mrCode">
																	<form:option id="sdoCodeOpt" value="" selected="true">Select MR Code</form:option>
																	<form:option value="GVP13" id="mrCodeOpt" >GVP13</form:option>
																	<c:forEach var="element" items="${mrCodes}">
																		<form:option value="${element}" id="mrCodeOpt" selected="">${element}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr> --%>
														<tr id="mrcodeTr">
														</tr>
														<tr>
														<td><form:label path="amount">Amount</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="amount" id="amount"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="amount" ></form:input></td>
																												
													</tr>
													
													<tr>
														<td><form:label path="remarks">Remarks</form:label></td>
														<td><font color="red"></font></td>
														<td><form:textarea path="remarks" id="remarks"
																class="form-control placeholder-no-fix"
																autocomplete="off" placeholder="" name="remarks" ></form:textarea></td>
																												
													</tr>
													
														

												</table>
														
												<div class="modal-footer">
													
													 <button class="btn btn-success pull-right" style="display: block;" id="btnOption" onclick="validateForm()" value=""><span id="addOption"></span></button>
													 <button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													<!-- <button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update User</button> -->
												</div>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
	<!--  Adding MR Credit Master Access and Editing Form Ended -- -->
	
</div>
