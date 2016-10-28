<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

$(".page-content").ready(
		function() {
			App.init();
			// UIBootbox.init();
			TableEditable.init();
			FormComponents.init();
			$('#meterData-Acquisition').addClass('start active ,selected');
			$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
       //$('#sdoCode').select2();
		});
</script>

<!-- <style>
#mrDevices span.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}
</style> -->
<script>



function validateForm() {
	//alert($('#deviceid').val())
	if($('#editVal').val()!='edit')
		{
		   checkDevice($('#deviceid').val());
		}
	
	var validator = $("#mrDevices").validate({
		
		rules : {
			deviceid : {
				//required : true,
				//number : true,
				// minlength:15
				 //maxlength:16
			},
			//gprsSimNum : "required",
			make :"required",
			sdoCode :"required",
			providedby :"required"

		},
		errorElement : "span", 

		messages : {
			sdoCode : "Please Select SDO Code",
			deviceid : {
				//required : "Please Enter IMEI Number",
				//number : "IMEI Number Must be digits Only",
				//minlength : "IMEI Number 15 Digits Required. ",
				
			},
			//gprsSimNum : "Sim Number is Required",
			make : "Please Select Device Name",
				providedby :"Please Select provider name"
		}
	});

	
	if (validator.form()) {
		$("form#mrDevices").attr("action","./updateMRDevice");
		$("form#mrDevices").submit();
	}
	$(".cancel").click(function() {
	    validator.resetForm();
	});

}


function addMRDevice(param) {
	$("#devCheck").hide();
	$("#mrDevices").trigger("reset");
	// $("#mrMasters").attr("action","./addMRMaster");

	$("#mrDevices").attr("action", "./updateMRDevice");
	$("#addOption").html("Add Device");
	$("#modelHeader").html("Add Device");
	$("#imei").removeAttr("readOnly");
	$("#deviceid").val("");
	$("#deviceid").removeAttr("readOnly");
	$("#make").removeAttr("disabled");
	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}
var deviceValue=null;
var providerName=null;
function editMRDevice(param, deviceid,id,editVal) {
	$('#mrDevices')[0].reset();
	$('#editVal').val(editVal);
   var result1=checkDeviceAllcated(deviceid,"device");
	  if(result1)
		  {
		    bootbox.alert('Selected Device already allocated so you cannot edit');
		    return false;
		  }
	  
	$("#devCheck").hide();
	$("#mrDevices").attr("action", "./updateMRDevice");
	$("#addOption").text("Update Device");
	$("#modelHeader").html("Update Device");
	//$("#deviceid").attr("readOnly", "true");
	// $("#sdoc").attr("hidden","true");
	// $("#sdoCode").prop("disabled","true");

	$.ajax({

		type : "post",
		url : "./editMRDevice/"+parseInt(id),		
		dataType : "JSON",
		success : function(response) { 
			document.getElementById("id").value = response.id;
			providerName=response.providedby;
			document.getElementById("providedby").value = response.providedby;
			deviceValue= response.deviceid;
			showDevicesid(response.providedby);
			
			document.getElementById("gprsSimNum").value = response.gprsSimNum;
			document.getElementById("make").value = response.make;
			document.getElementById("allocatedflag").value = response.allocatedflag;
			document.getElementById("approvalStatus").value = response.approvalStatus;
			//document.getElementById("sdoCode").value = response.sdoCode;
			document.getElementById("deviceType").value = response.deviceType;
			document.getElementById("status").value = response.status;
			$.trim("" + response.sdoCode);
			$("#sdoCode").val(response.sdoCode);
			$("#make").val("" + response.make);
			// $("#make").attr("disabled","true");

			// $("#sdoCode").val(""+response.sdoCode);
			// $("#sdoCode").attr("disabled","true");

		}
	});

	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}

function deleteMRDevice(deviceid) {
	
	bootbox.confirm("Are you Sure To Delete MR Device....!",function(result){
		
		if (result) {

			var result1=checkDeviceAllcated(deviceid,"device");
			  if(result1)
				  {
				    bootbox.alert('Selected Device already allocated so you cannot delete');
				    return false;
				  }
			  
			
			$("#deleteID").attr("value",deviceid);
			$("form#DeleteForm").attr("action","./deleteMRDevice");
			$("form#DeleteForm").submit();
			

		}
	});
	
	

}

function checkDevice(devId){
	if(devId.trim()=='')
		{
		bootbox.alert('please enter IMEI NO');
		return false;
		}
	$.ajax({
		type:"POST",
		url:"./checkDeviceAvailability",
		data:{"deviceid":$("#deviceid").val()},
		dataType:"json",
		async:false,
		cache:false,
		success:function(response){
			if(response)
			{
				
				bootbox.alert('Entered device has already registered');
				return false;
				
				/* $("#devCheck").show();
				$("#devCheck").html("<span style='color:red'>Device has already registered..</span>");
				$("#deviceid").focus();
				$("#deviceid").select(); */
			}
			/* else{
				$("#devCheck").hide();
			} */
		}
	});
}

function approval(deviceId)
		{	
	bootbox.confirm("Are you want to approve selected device", function(result) {
        if(result == true)
        	{
        	window.location.href="./approveDevice?deviceid="+deviceId;
        	}
	});
	return false;
		}
		
		function showDevicesid(provider)
		{
			$.ajax({
				type:"GET",
				url:"./getDeviceFromMobileMaster/"+provider,
				dataType:"json",
				cache:false,
				async:false,
				success:function(response)
				{
					var substation='';
		    		 substation+="<td>IMEI NO</td><td><font color=red>*</font></td><td><select id='deviceid' name='deviceid' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' maxlength='15'><option value='select' selected='selected' >Select Device</option>";
				  for(var i=0;i<response.length;i++)
				  {
					  substation+="<option  value='"+response[i].trim()+"'>"+response[i]+"</option>";
				  }
				  $("#mrcodeTr").html(substation);
					$('#deviceid').select2();
				}
			});
			if(deviceValue!=null)
				{
					if(providerName==provider)
					{
					$('#s2id_deviceid .select2-chosen').html(deviceValue);
				     $('#deviceid').val(deviceValue); 
					}
				}
			else
				{
					$('#s2id_deviceid .select2-chosen').html('select');
					$('#deviceid').val('select'); 
					
				}
			
			 
			
		}

</script>


  <div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	
	<c:if test="${allocated ne null }">
	<script>
		bootbox.alert("<span style='color:red;'>Device Has allocated.<c:out value='${allocated}'></c:out></span>");
	
	</script></c:if>
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
						<i class="fa fa-edit"></i>Devices Registered
						<c:if test="${userType eq 'admin_subdivision' || userType eq 'ADMIN_SUBDIVISION'}">
						<fmt:parseDate value="${currentMonth}"  var="billMonth" pattern="yyyyMM"/>
						&nbsp;&nbsp;&nbsp;<span class="label label-warning"><font size="3" color="black"><b>SDOCODE:-${sdocodeval}</b></font></span>&nbsp;<span class="label label-warning"><font size="3" color="black"><b>&nbsp;&nbsp;BILLMONTH:-<fmt:formatDate pattern="yyyy-MMM" value="${billMonth}"/></b></font></span>
						</c:if>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
					
					<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
						<div class="btn-group">
							<button class="btn green" onclick="addMRDevice(this.id)"
								data-toggle="modal" id="addMRMaster" data-target="#regData">
								Add Device <i class="fa fa-plus"></i>
							</button>
						</div>
						</c:if>
						<!-- <div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
										to Excel</a></li>
							</ul>
						</div> -->
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
						<thead>
							<tr>
								<th>DEVICE ID</th>
								<th>GPRS SIM NO</th>
								<th>MAKE</th>
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>ApproveStatus</th>
								</c:if>
								<th>ALLOCATION STATUS</th>
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>SDOCODE</th>
								<!-- <th>SECTION</th>  -->
								</c:if>
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>EDIT</th>
								<!-- <th>DELETE</th> -->
								</c:if>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${mrdList}">
								<tr>
									<td>${element.deviceid}</td>
									<td>${element.gprsSimNum}</td>
									<td>${element.make}</td>
									<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									<td><c:choose>
									   <c:when test="${element.approvalStatus eq 'NOT APPROVED'}">
									   <a href="#" onclick="return approval('${element.deviceid}');">${element.approvalStatus}</a>
									   </c:when>
									   <c:otherwise>
									   ${element.approvalStatus}
									   </c:otherwise>
									    </c:choose>
									 </td>
									 </c:if>
									 <td>${element.allocatedflag}</td>
									 <c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									 <td>${element.sdoCode}</td>
									 <%--  <td>${element.siteLocationEntity.section}</td>  --%>
									 </c:if>
									 <c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									<td><a href="#" onclick="return editMRDevice(this.id,'${fn:trim(element.deviceid)}','${fn:trim(element.id)}','edit')" id="editData${element.deviceid}">Edit</a></td>
									<%-- <td><a href="#" id="${element.id}" onclick="return deleteMRDevice(this.id)" >Delete</a></td> --%>
								    </c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<form id="DeleteForm" method="POST" >
						<input type="hidden"  name="deviceid" value="" id="deleteID"/>
						<input type="hidden"  name="editVal" value="" id="editVal"/>
					</form>
					
				</div>
			</div>
		</div>
	</div>
	<!-- Adding Device Access and Editing Device Type Data Form Started -->
	
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
											<form:form action="" modelAttribute="mrDevices"
																	commandName="mrDevices" method="post" id="mrDevices">
												<table >
												
												<tr id="idTr" hidden="true">
														<td>IMEI NO</td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id"></form:input></td>
														
													</tr>
													
													<tr >
															<td>SDOCODE</td>
															<td><font color="red">*</font></td>
															<td><form:select path="sdoCode"
																	id="sdoCode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="sdoCode" >
																	<form:option id="sdoCode" value="" selected="true">Select</form:option>
																	<c:forEach var="element" items="${sdoCodes}">
													<form:option value="${element[0]}">${element[1]}-${element[0]}</form:option>
												</c:forEach>
																</form:select>
																</td>
														</tr>
													
													<tr >
															<td>PROVIDER</td>
															<td><font color="red">*</font></td>
															<td><form:select path="providedby"
																	id="providedby"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="providedby"  onchange="showDevicesid(this.value)">
																	<form:option   value="" >Select</form:option>
																	<form:option   value="BCITS" >BCITS</form:option>
																	<form:option   value="CESC" >CESC</form:option>
																	<form:option   value="OTHERS" >OTHERS</form:option>
																</form:select>
																</td>
														</tr>
														
														<tr id="mrcodeTr">
														</tr>
													
													<%-- <tr id="imei">
														<td>IMEI NO</td>
														<td><font color="red">*</font></td>
														<td><form:input path="deviceid" id="deviceid"
																class="form-control input-medium" type="text"
																autocomplete="off" placeholder="" name="deviceid" maxlength="15" ></form:input></td>
														<td id="devCheck"></td>
														
													</tr> --%>
													
													
													<tr >
															<td>Model No.</td>
															<td><font color="red">*</font></td>
															<td><form:select path="make"
																	id="make"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="make" >
																	<form:option id="mk" value="" selected="true">Select</form:option>
																	
																	<form:options items="${makeConstrts}" />
																	<%-- <c:forEach var="element" items="${makeConstrts}">
																		<form:option value="${element}" id="makeOpt">${element}</form:option>
																	</c:forEach>
																	 --%>
																</form:select>
																</td>
														</tr>
														
														<tr>
														<td>GPRS SIM NO</td>
														<td><font color="red"></font></td>
														<td><form:input path="gprsSimNum" id="gprsSimNum"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="gprsSimNum" ></form:input></td>
													
													<tr >
													
													<tr  hidden="true">
														<td>allocatedflag</td>
														<td><font color="red"></font></td>
														<td><form:input path="allocatedflag" id="allocatedflag"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="allocatedflag" ></form:input></td>
													
													<tr >
													
													<tr  hidden="true">
														<td>approvalStatus</td>
														<td><font color="red"></font></td>
														<td><form:input path="approvalStatus" id="approvalStatus"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="approvalStatus" ></form:input></td>
													
																									
													<%-- <tr>
															<td>SDO Code </td>
															<td><font color="red">*</font></td>
															<td><form:select path="sdoCode"
																	id="sdoCode"
																	class="form-control select2me"
																	autocomplete="on" placeholder="" name="sdoCode" >
																	<form:option id="sdc" value="" selected="selected">Select SDO Code</form:option>
																	<form:options items="${sdoCodes}"/>
																	 <c:forEach var="element" items="${sdoCodes}">
																		<form:option value="${element['0']}" id="sdoCodeOpt">${element['0']}-${element['1']}</form:option>
																	</c:forEach> 
																</form:select></td>
														</tr> --%>
														
                                           <tr  hidden="true">
														<td>Device Type</td>
														<td><font color="red"></font></td>
														<td><form:input path="deviceType" id="deviceType"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="deviceType" readonly="true"></form:input></td>
													
													<tr >
													
													<tr  hidden="true">
														<td>Status</td>
														<td><font color="red"></font></td>
														<td><form:input path="status" id="status"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="status" readonly="true"></form:input></td>
													
													<tr >
												</table>
														
												<div class="modal-footer">
													
													 <button class="btn btn-success pull-right" style="display: block;" id="btnOption"  onclick="validateForm()" value=""><span id="addOption"></span></button>
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
	
	<!-- Adding Device Access and Editing Device Type Data Form Ended -->
	
</div>
	