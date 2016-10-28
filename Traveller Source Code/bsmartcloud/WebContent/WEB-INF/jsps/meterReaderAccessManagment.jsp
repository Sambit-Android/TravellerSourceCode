<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
#mrMasters span.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}

</style>
<%-- <script src="<c:url value='/resources/bsmart.lib.js/magnifier.js'/>"  type="text/javascript" ></script>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>
 --%>
 <script>
$(".page-content")
.ready(
		function() {
			App.init();
			TableEditable.init();
			FormComponents.init();
			$('#meterData-Acquisition').addClass('start active ,selected');
			$(
					"#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management")
					.removeClass('start active ,selected');
			
			
		});

function validateForm(operation) {	
	
var validator = $("#mrMasters").validate({
rules : {
	mrCode : "required",
	mrName : "required",
	mobile : {
		required : true,
		number : true,
		minlength : 10,
		maxlength : 11
	},
	//password : "required",	
	address : "required"
	

},
errorElement : "span",
messages : {

	mrCode : "MR Code is Required",
	mrName : "MR Name is Required",
	mobile : {
		required : "Mobile Number is Required",
		number : "Mobile should contain digits only",
		minlength : "Mobile should contain 10 digits only",
		maxlength : "Mobile should contain 10 digits only"
	},
	password : "Password is Required",
	address : "Address is Required"		
}
});


document.getElementById('operation').value=operation;

/* var grp=$('#select2_sample2').val();
if(operation=='add' || (operation=='update' &&  grp!=null))
	{
	   grp=grp+"";//to use uppercase function
	   var res = grp.toUpperCase().indexOf("GENERAL");
		 if(res==-1)
			{
			   bootbox.alert('Group, General is mandatory please select it');
			   return false;
			}
	}
	
	if(grp!=null)//while edit if  select group then asssign selected group other wise it will be old group
		{
		$('#groupsData').val(grp);
		} */

if (validator.form()) {

$("form#mrMasters").attr("action", "./updateMRMaster");
$("form#mrMasters").submit();
}
$(".cancel").click(function() {
validator.resetForm();
});

}

function addMRMaster(param) {

$("#mrCodeCheck,#groupEdit").hide();
$("#mrMasters").trigger("reset");
//$("#addOption").html("Add Device Operator");
document.getElementById('addOption').style.display='block';
document.getElementById('updateOption').style.display='none';
$("#modelHeader").html("Add Device Operator");
$("#mrCode").removeAttr("readOnly");

$('#' + param).attr("data-toggle", "modal");
$('#' + param).attr("data-target", "#regData");

}

function editMRMaster(param, mrCode) {
	/* var result1=checkDeviceAllcated(mrCode,"operator");
	  if(result1)
		  {
		    bootbox.alert('Selected Device already allocated so you cannot delete');
		    return false;
		  } */
	  
$("#mrCodeCheck").hide();
$("#modelHeader").html("Update Device Operator");
$("#mrCode").attr("readOnly", "true");

$.ajax({

type : "post",
url : "./editMRMaster",
data : {
	'mrCode' : mrCode	
},
dataType : "JSON",
async:false,
success : function(response) {
	
	 if(response){		
		document.getElementById("mrCode").value = response.mrCode;
		document.getElementById("mobile").value = response.mobile;
		document.getElementById("mrName").value = response.mrName;
		document.getElementById("address").value = response.address;				
		/* var grps=response.mrgroups;
		var assignedGrps="";
		var ids="";
		for(var i=0;i<grps.length;i++)
			{
			assignedGrps=assignedGrps+grps[i].grpName+",";			
			}
		$('#assignedGrp').val(assignedGrps);
		$('#groupsData').val(assignedGrps); */
		//$('#s2id_select2_sample2  .select2-choices').html("<li class=select2-search-choice><div>AAC</div> <a class=select2-search-choice-close tabindex=-1 onclick='return false;' href=#></a></li><li class=select2-search-field> <input id=s2id_autogen4 class=select2-input type=text spellcheck=false autocapitalize=off autocorrect=off autocomplete=off style=width: 22px;></li>");
			
		//$("#select2_sample2 option[value='ABC']").prop("selected", true);
		//$('#groupEdit').show();
		
		document.getElementById('addOption').style.display='none';
		document.getElementById('updateOption').style.display='block';
		
		$('#'+param).attr("data-toggle", "modal");
		$('#'+param).attr("data-target", "#regData");
		$('#regData').modal("show");
		

	}
	 else{
		
		//bootbox.alert("<b style='color:red'>DeAllocate MR Master First</b>");
		 bootbox.alert("group not aasigned");
		 return false;
	} 

	
}
});


}

function deleteMRMaster(param, mrCode) {
bootbox.confirm("Are you Sure to Delete Device operator..!",function(result){
	if(result){
		var result1=checkDeviceAllcated(mrCode,"operator");
		  if(result1)
			  {
			    bootbox.alert('Selected Device operator already allocated so you cannot delete');
			    return false;
			  }
		  
		$("#delMRCode").attr("value",mrCode);
		$("form#delMRForm").attr("action","./deleteMRMaster");
		$("form#delMRForm").submit();
		
	}
});
		
}

function checkMRCode() {

$.ajax({
		type : "POST",
		url : "./checkMRCodeAvailability",
		data : {
			
			"mrCode" : $("#mrCode").val()
		},
		dataType : "json",
		success : function(response) {

			if (response) {
				$("#mrCodeCheck").show();
				$("#mrCodeCheck").html("<span style='color:red'>MR Master already existed.</span>");
				$("#mrCode").attr("value","");
				$("#mrCode").focus();
				//$("#mrCode").select();
			} else {
				$("#mrCodeCheck").hide();
			}
		}
	});

}
</script>

<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<c:if test="${results eq 'allocated' }">
				<script>
					bootbox.alert("<span style='color:red'>Deallocate MR Master First.</span>");
				</script>
	</c:if>
	
	<div class="row">
		<div class="col-md-12">
			 
			<c:if test="${(not empty results) && (results ne 'allocated') }">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Device Operator Master
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" onclick="addMRMaster(this.id)"
								data-toggle="modal" id="addMRMaster" >
								Add Device Operator <i class="fa fa-plus"></i>
							</button>
							
						</div>
						
					</div>
					
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
							   <th>Photo</th>
								<th>Device Operator Code</th>
								<!-- <th>SDOCode</th> -->
								<th>Device Operator Name</th>
								<th>Address</th>
								<th>Mobile</th>
								<!-- <th>Group</th> -->
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${mrmList}">
								<tr>
								    <td><img data-magnifyby="10" class="magnify" src="./getOperatorImage/getImage/${element.mrCode}"  height="50" width="50"  /></td>
									<td id="srdmrCode">${element.mrCode}</td>
									<%-- <td>${element.sdoCode}</td> --%>
									<td>${element.mrName}</td>
									<td>${element.address}</td>
									<td>${element.mobile}</td>
									         <%-- <c:set scope="page" value="" var="count"/> 
									            <c:forEach items="${element.mrgroups}" var="groups">
						         			      <c:set scope="page" value="${count} ${groups.grpName}," var="count"/>  
						  				          </c:forEach>
						  				          <td>${fn:substring(count, 0, fn:length(count)-1)}</td> --%>
									<td><a href="" onclick="return editMRMaster(this.id,this.name)"
										id="editData${element.mrCode}" name="${element.mrCode}" data-toggle="modal">Edit</a></td>
									
									<td><a href="#" onclick="return deleteMRMaster(this.id,this.name)"
										id="deleteData${element.mrCode}" name="${element.mrCode}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
		</div>
	</div>
	
	<!--MRMaster Registration content started -->
	
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
											<form:form action="" modelAttribute="mrMasters"
																	commandName="mrMasters" method="post" id="mrMasters" enctype="multipart/form-data">
												<table >
												
													<tr>
														<td><form:label path="mrCode"> Code</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="mrCode" id="mrCode"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="mrCode" required="true"></form:input></td>
														<td id="mrCodeCheck"></td>
														
													</tr>													
													
													<tr>
														<td><form:label path="mrName"> Name</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="mrName" id="mrName"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="mrName" ></form:input></td>
													
													</tr>
													
													
													<%-- <tr hidden="true">
														<td><form:label path="password">Password</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="password" id="password"
																class="form-control placeholder-no-fix" type="password"
																autocomplete="off" placeholder="" name="password" ></form:input></td>
													
													</tr> --%>
													
													<tr>
														<td><form:label path="mobile">Mobile</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="mobile" id="mobile"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="mobile" maxlength="10"></form:input></td>
													
													</tr>
													
													
													<tr>
														<td><form:label path="address">Address</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:textarea path="address" id="address" name ="address" class="form-control placeholder-no-fix"/>
														
														</td>
														
													</tr>
													
													
													
													<%-- <tr id="sdoc">
															<td><form:label path="sdoCode">SDO Code </form:label></td>
															<td><font color="red">*</font></td>
															<td>
													                                    											<!-- onchange="checkMRCode()" -->
																<form:select path="sdoCode"	id="sdoCode"	class="form-control select2me" 	autocomplete="on" placeholder="" name="sdoCode" >
																	<form:option id="sdoCodeOpt" value="" selected="true">Select</form:option>
																	
																	<c:forEach var="element" items="${sdoCodes}">
																		<form:option value="${element['0']}" id="sdoCodeOpt">${element['0']}-${element['1']}</form:option>
																	</c:forEach>
																</form:select>
															</td>
														</tr> --%>
														
														<tr hidden="true"> <td><input type="text" name="operation" id="operation"/></td>
														                   <td><input type="text" name="groupsData" id="groupsData"/></td>
														</tr>
														
														
														<!-- <tr id="groupEdit" hidden="true">
															<td><label>Assigned Group</label></td>
															<td>&nbsp;</td>
															<td>
															
															<textarea type="text" id="assignedGrp" name="assignedGrp" class="form-control placeholder-no-fix" readonly="readonly"></textarea>
															</td>
														</tr> -->
														
														<%-- <tr id="groups">
															<td><label>Group</label></td>
															<td><font color="red">*</font></td>
															<td>
															
															<select id="select2_sample2" class="form-control select2" multiple autocomplete="on" placeholder="" name="groups">
										                    <!-- <option id="groupDefault" value="General" selected="true">General</option> -->
										                    <c:forEach items="${groupData}" var="groupVal">
														    <option value="${groupVal.grpName}">${groupVal.grpName}</option>
														
															</c:forEach>
										                       </select>
															</td>
														</tr> --%>
														
                                                     <tr id="imageId">
													<td>upload Image</td>		
													<td><font color="red">*</font></td>											
														<td><input  type="file" id="image1"  name="image1"/></td>
														
														</tr>
												</table>
														
												<div class="modal-footer">
													
													 <button class="btn btn-success pull-right" style="display: block;" id="addOption" onclick="return validateForm('add')" value="">Add Device Operator</button>
													 <button class="btn btn-success pull-right" style="display: none;" id="updateOption" onclick="return validateForm('update')" value="">update Device Operator</button>
													 <button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													<!-- <button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update User</button> -->
												</div>
											</form:form>
											
											<form id="delMRForm" action="" method="post">
												<input type="hidden" name="mrCode" value="" id="delMRCode">
												<!-- <input type="hidden" name="sdoCode" value="" id="delSDOCode"> -->
											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
	<!--MRMaster Registration content ended -->
	
	<!-- <div class="modal fade" id="popup_image" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog" id="image">
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="resetRotation();">
           &times;
          </button>
          <h4 class="modal-title">IMAGE
          <img  id="rl2" src="./resources/assets/img/RotateLeft.jpg" onclick="rotateLeft('0');" />&nbsp;&nbsp;&nbsp;&nbsp;
         <img  id="rr1" src="./resources/assets/img/RotateRight.jpg" onclick="rotateRight('0');"/>
         <label id="rll">Rotate Left&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rotate Right</label>
          </h4>
         </div>
         <div class="modal-body">
          <div class="rotatecontrol" id="Imageview" >
           <img id="tempImg" src="" />
          </div>
         </div>
        </div>
        /.modal-content
       </div>
       /.modal-dialog
      </div> -->
</div>
