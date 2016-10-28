<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

 <style>
#allowedVersion label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}
</style>



 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#meterData-Acquisition').addClass('start active ,selected');
  	   	  $("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	   	  
   	    	   $('#addData').click(
						function()
								{ 	
									document.getElementById("allowedVersion").reset();
									document.getElementById('updateOption').style.display='none';
									document.getElementById('addOption').style.display = 'block';
								}
						);
				$("#print").click(
						function()
								{
									printcontent($(".table-scrollable").html());
								}
	
					 );
   	    	   });
  
  function removeAppVersion(appVerID){
	  bootbox.confirm("Are you Sure to Remove This App Version ",function(res){
		  if(res){
		
			  $("#removeAPKid").attr("value",appVerID);
			  $("form#sprtForm").attr("action","./removeAppVersion");
			  $("form#sprtForm").submit();
			  /* $.ajax({
				  type:"POST",
				  url:"./removeAppVersion",
				  data:{"appVerID":appVerID},
				  success:function(response){
					  window.location.href="./allowedVersions";
				  }
				  
			  }); */
		  }
		  
	  });
		

		  
		 
	  
  }
  
  function validateForm(){
		
		//alert("working");
		var validator = $("form#allowedVersion").validate({
			
			rules:{
				appName:"required",
				remarks:"required",
				
			},
			messages:{
				appName:" Select APK Name",
				remarks:"Remarks are required"
				
			}
			
		});
		//alert("working"+validator);
		if (validator.form()) {
		
			 $("form#allowedVersion").attr("action","./updateAllowedVersion");
			$("form#allowedVersion").submit();
		}
		$(".cancel").click(function() {
		    validator.resetForm();
		});
	}
  
  
 function common_function()
 {
	 var stringOnly = /^[A-Za-z0-9\.\s\-]+$/;
	 
	 var ver_nm=document.getElementById("version_name").value.trim();
	 if(ver_nm==''||ver_nm==null)
	 {
		 bootbox.alert("Please enter the version name");
		 return false;
	 }
	 else if(!stringOnly.test(ver_nm))
 	 {
 		 bootbox.alert("Special characters are not allowed in version name");
		 return false;
 	 } 
	 
	 var rmrks=document.getElementById("remarks").value.trim();
	 if(rmrks==''||rmrks==null)
	 {
		 bootbox.alert("Please enter the remarks");
		 return false;
	 }
	 if(!stringOnly.test(rmrks))
 	 {
 		 bootbox.alert("Special characters are not allowed in remarks");
		 return false;
 	 } 
	 
 }
  </script>
<div class="page-content">
		
		<c:if test="${results eq 'releaseFirst'}">
			<script type="text/javascript">
				bootbox.alert("<span style='color:red'>Release at least one version...</span>");
			</script>
		</c:if>

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay' && results ne 'releaseFirst'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Allowed Versions
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="addData" id="em">
								Add Allowed Versions <i class="fa fa-plus"></i>
							</button>
						</div>
						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
										to Excel</a></li>
							</ul>
						</div>
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th hidden="true">Id</th>
								<th>App Name</th>
								<th>Version_Number</th>
								<th>Remarks</th>
								<th>Remove App Version</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${allowedVersionList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td>${element.appName}</td>
									<td>${element.version_name}</td>
									<td>${element.remarks}</td>
									<td>
									<c:forEach var="updated" items="${latest}">
										
										<c:choose>
											
											<c:when test="${element.id eq updated.id}">
												<button class="btn-default">Remove App Version</button>
											</c:when>
											<c:otherwise>
												<button class="btn-primary" onclick="removeAppVersion(${element.id})"
													id="removeData${element.id}">Remove App Version</button>		
											</c:otherwise>
										</c:choose>
									</c:forEach>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<form id="sprtForm" method="POST" action="" >
					
						<input type="hidden" id="removeAPKid" name="apkid" value="">
					</form>
					<div id="stack1" class="modal fade" >
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Add Allowed Versions</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											<form:form action="" modelAttribute="allowedVersion"
																	commandName="allowedVersion" method="post" id="allowedVersion">
												<table>
												
													
													
													<tr>
														<td>App Name</td>
														<td><font color="red">*</font></td>
														<td><form:select path="appName" class="form-control select2me"  onchange="getMatchedData(this.value)">
															<form:option id="appNameOpt" value="" selected="true">Select App Name</form:option>
																<c:forEach var="element" items="${allownewList}">
																	<form:option value="${element.id}_${element.appName}_${element.version_name}"><c:out value="${element.appName}_${element.version_name}"></c:out></form:option>
																</c:forEach>
														</form:select>
														<%-- <form:input path="appName" id="appName"
																class="form-control select2me" type="text"
																autocomplete="on" placeholder="select" name="appName"></form:input>
														 --%>		
													</td>
													</tr>
													<%-- <tr id="sdoc" >
													<td>SDO Code </td>
													<td><font color="red">*</font></td>
													<td>
														<form:select path="appName" class="form-control select2me" data-placeholder="Select..." onchange="getMatchedData(this.value)">
															<form:option id="appNameOpt" value="" selected="true">Select App Name</form:option>
																<c:forEach var="element" items="${appNames}">
																	<form:option value="${element['0']}" id="sdoCodeOpt" selected="">${element['0']}-${element['1']}</form:option>
																</c:forEach>
															</form:select>
													</td>
												</tr> --%>

													<%-- <tr>
														<td>Version_Name</td>
														<td><font color="red">*</font></td>
														<td><form:input path="version_name" id="version_name"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="version_name"></form:input></td>
													</tr> --%>

													<%-- <tr hidden="true">
															<td>Date TimeStamp</td>
															<td><font color="red">*</font></td>
															<td></td>
															<td><form:input path="dateTimeStamp" id="dateTimeStamp"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="dateTimeStamp"></form:input></td>
													   </tr> --%>

													<tr>
														<td>Remarks</td>
														<td><font color="red">*</font></td>
														<td><form:textarea path="remarks" id="remarks"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="remarks"></form:textarea>
														</td>
													</tr>

												</table>
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													 <button class="btn blue pull-right" style="display: block;" id="addOption"  onclick="validateForm();">Add Allowed Versions</button>
													<button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="validateForm();">Update Allowed Versions</button>
												</div>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>