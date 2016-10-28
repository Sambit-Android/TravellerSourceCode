<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

<style>
#mrCreditMasters label.error {
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
		
function addMRCMaster(param) {

	
	$("#mrCreditMasters").trigger("reset");
	// $("#mrCreditMasters").attr("action","./addMRMaster");
	

	$("#mrCreditMasters").attr("action", "./updateMRDevice");
	$("#addOption").html("Add MR Credit Master");
	$("#modelHeader").html("Add MR Credit Master");
	
	
	
	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}



function getMatchedData(sdoCode){
	  
	  $("#mrCodeError").hide();
	  $("#mrCode :selected").val("");
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
			  success:function(response)
			  {
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
					$("#mrCodeError").html("<span style='color:red'>MR Codes Not Available</span>");
					$("#mrCode :selected").val("");		
					$("#mrCode").attr("disabled","true");
							$("#mrCodeError").show();  
				} 
				
								  
				
			  }
		  });

			  }
	  
	    }



function validateForm(){
	
	//alert("working");
	var validator = $("form#mrCreditMasters").validate({
		
		rules:{
			sdoCode:"required",
			//mrCode:"required",
			totalCredit:{
			 required:true,
			 number:true
			}
		},
		messages:{
			sdoCode:"Please Select SDO Code",
			mrCode:"Please Select MR Code",
			totalCredit:{
				required:"Please Enter Credit Amount",
				number:"Amount in Numbers Only"
			}
		}
		
	});
	//alert("working"+validator);
	if (validator.form()) {
		$("form#mrCreditMasters").attr("action","./updateMRCreditMaster");
		$("form#mrCreditMasters").submit();
	}
	$(".cancel").click(function() {
	    validator.resetForm();
	});
}


function checkMRCAvail(){
	//alert("working");
	
	$.ajax({
		
		type:"POST",
		url:"./checkMRCAvail",
		data:{"sdoCode":parseInt($("#sdoCode").val()),"mrCode":$("#mrCode").val()},
		dataType:"json",
		success:function(response){
			if(response){
				$("#mrCodeError").html("<span style='color:red'>Already Credited to this Master</span>");
				$("#mrCodeError").show();
			}else{
				$("#mrCodeError").hide();
			}
		}
	
		
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
	
	
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>MR Credit Masters
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" onclick="addMRCMaster(this.id)"
								data-toggle="modal" id="addMRCreditMaster" >
								Add MR Credit Master <i class="fa fa-plus"></i>
							</button>
						</div>
						
					</div>
					
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								
								<th>SDO Code</th>
								<td>MR Code</td>
								<th>Available Credit</th>
								<th>Total Credit</th>
								<th>CashCounterId</th>
								<th>Remarks</th>
								
								
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${mrcmasters}">
								<tr>
									
									<td>${element.sdoCode}</td>
									<td>${element.mrCode}</td>
									<td>${element.availCredit}</td>
									<td>${element.totalCredit }</td>
									<td>${element.cashcounterid }</td>
									<td>
									<c:choose>
										<c:when test="${element.remarks eq ''}">
											<c:out value="NO Remarks"></c:out>
										</c:when>
										<c:otherwise>
											<c:out value="${element.remarks}"></c:out>
										</c:otherwise>
									</c:choose></td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
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
											<form:form action="" modelAttribute="mrCreditMasters"
																	commandName="mrCreditMasters" method="post" id="mrCreditMasters">
												<table >
												
													<tr hidden="true">
														<td><form:label path="id">ID</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id" ></form:input></td>
																												
													</tr>
																																							
													<tr id="sdoc" >
															<td><label >SDO Code</label> </td>
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
																	autocomplete="off" placeholder="" name="mrCode" >
																	<form:option id="sdoCodeOpt" value="" selected="true">Select MR Code</form:option>
																	
																	<c:forEach var="element" items="${mrCodes}">
																		<form:option value="${element}" id="mrCodeOpt" selected="">${element}</form:option>
																	</c:forEach>
																	<form:option value="GVP13"  >GVP13</form:option>
																</form:select></td><td id="mrCodeError"></td>
														</tr> --%>
														<tr id="mrcodeTr">
														</tr>
														<tr>
														<td><form:label path="totalCredit">Total Credit</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="totalCredit" id="totalCredit"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="totalCredit" ></form:input></td>
																												
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
