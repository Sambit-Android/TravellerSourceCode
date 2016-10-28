<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>

 <style>
#onairverupdt label.error {
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
   	    	   });
  
  function addAPKVersion(param) {

		
		$("#onairverupdt").trigger("reset");
		// $("#mrCreditMasters").attr("action","./addMRMaster");
		

		
		$("#addOption").html("Add APK Version ");
		$("#modelHeader").html("Add APK Version ");
		
		
		
		$('#' + param).attr("data-toggle", "modal");
		$('#' + param).attr("data-target", "#regData");

	}
  
  
  function validateForm(){	
		
		var validator = $("form#onairverupdt").validate({
			
			rules:{
				apkName:"required",
				apkVersion:{
					required:true,
					number:true,
					
				},
				uploadedBy:"required",
				uploadedapk:{
					required:true,
					extension:'apk',
					uploadFile:true
				},
				
			},
			messages:{
				apkName:"APK Name is Required",
				apkVersion:{
					required:"APK Version is Required",
					number:"version should be number only"
				},
				uploadedBy:"Uploaded Person Name is Required",
				uploadedapk:{
					required:"upload file is required",
					extension:"you should upload apk files"
							
				}
			}
			
		});
		var chk=$('#radio_0').attr('checked');		
		if(chk=='checked')
			{			
			   $('#groupValues').html("<option  value='All' selected='true'>All</option>");			   
			}
		else
			{			
			if(groupValues.value=='')
				{
				bootbox.alert('please select atleast one Device');
				return false;
				}
			}
	
		if (validator.form()) {
		
			 $("#onairverupdt").attr("action","./addNewVersionUpdate");
			$("form#onairverupdt").submit();
		}
		$(".cancel").click(function() {
		    validator.resetForm();
		});
	}
  
  function downloadAPK(apkid){
	  $("#apkid").attr("value",apkid);
	  $("form#sprtForm").attr("action","./downloadAPKFile");
	  
	  $("form#sprtForm").submit();
  }
  function releaseAPKVersion(apkid){
	  $("#apkid").attr("value",apkid);
	  $("form#sprtForm").attr("action","./releaseAPKVersion");
	  
	  $("form#sprtForm").submit();
  }
  
  function radioSelected(id)
  {
	  if(id=='radio_1')
		  {
		  $('#grpData').attr('hidden',false);
		  }
	  else
         {
		  $('#grpData').attr('hidden',true);
         }
	  
  }
</script>




<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	
	
	<div class="row">
		<div class="col-md-12">
			<c:if test="${not empty results}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>OnAir Version Updation
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" onclick="addAPKVersion(this.id)"
								data-toggle="modal" id="addAPKVersion" >
								Add New APK Version<i class="fa fa-plus"></i>
							</button>
						</div>
						
					</div>
					
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								
								<th hidden="true">Apk Version ID</th>
								<td>APK Name</td>
								<th>APK Version</th>
								<th>Uploaded On</th>
								<!-- <th>Group</th> -->
								<th>Remarks</th>
								<th>Status</th>
								
								<th>Download</th>
								
								
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${onAirVerList}">
								<tr>
									
									<td hidden="true">${element.id}</td>
									<td>${element.apkName}</td>
									<td>${element.apkVersion}</td>
									<td>${element.timestamp}</td>
									<%-- <td>${element.grp}</td> --%>
									
									
									<td>
									<c:choose>
										<c:when test="${element.remarks eq ''}">
											<c:out value="NO Remarks"></c:out>
										</c:when>
										<c:otherwise>
											<c:out value="${element.remarks}"></c:out>
										</c:otherwise>
									</c:choose></td>	
									<td>
									<c:choose>
										<c:when test="${element.releaseFlag eq 'not released' }"><button class="btn btn-md btn-success" onclick="releaseAPKVersion(${element.id})">Release Now</button></c:when>
										<c:otherwise><button class="btn btn-sm btn-default">Released</button></c:otherwise>
									
									</c:choose></td>								
									<td>
									<c:choose>
										<c:when test="${element.releaseFlag eq 'released' }">
									<button type="button" class="btn btn-default btn-sm" id="${element.id}" onclick="downloadAPK(${element.id})" >
  										<span class="glyphicon glyphicon-save"></span> Download
									</button>		
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-default btn-sm" id="${element.id}" onclick="downloadAPK(${element.id})" disabled="disabled">
  										<span class="glyphicon glyphicon-save"></span> Download
									</button>		
									
									</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					</div>
			</div>
		</div>
	</div>
	
		<!-- Adding MR Credit Master Access and Editing  Data Form Started -->
	
	<div id="regData" class="modal fade" tabindex="-1" data-width="400">
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
											<form:form action="./addNewVersionUpdate" modelAttribute="onairverupdt"
																	commandName="onairverupdt" method="post" id="onairverupdt"  enctype="multipart/form-data">
												<table >
												
													<tr hidden="true">
														<td><form:label path="id">ID</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id" ></form:input></td>
																												
													</tr>
													
														
														<tr>
														<td><form:label path="apkName">APK Name</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="apkName" id="apkName"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="apkName" ></form:input></td>
																												
													</tr>
													
													<tr>
														<td><form:label path="apkVersion">APK Version</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="apkVersion" id="apkVersion"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="apkVersion" maxlength="5" ></form:input></td>
																												
													</tr>
													<!-- <tr>
														<td>Group</td>
														<td><font color="red">*</font></td>
														<td><label>All <input type="radio" value="0" name="grping" checked="true" id="radio_0" onclick="radioSelected(this.id);"></label></td>
														<td><label>Selected<input type="radio" value="1" name="grping" id="radio_1" onclick="radioSelected(this.id);"> </label></td>
																												
													</tr> -->
													<%-- <tr hidden="true" id="grpData"> 
														<td><form:label path="grp">Select Device</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:select path="grp" class="form-control select2me" multiple="true" id="groupValues">
															<form:option id="appNameOpt" value="0" selected="">Select Device</form:option> 
																<c:forEach var="element" items="${allDevices}">
																	<form:option value="${element.deviceid}"><c:out value="${element.deviceid}"></c:out></form:option>
																</c:forEach>
														</form:select></td>
																												
													</tr> --%>
																									
													
													<tr>
														<td><form:label path="remarks">Remarks</form:label></td>
														<td><font color="red"></font></td>
														<td><form:textarea path="remarks" id="remarks"
																class="form-control placeholder-no-fix"
																autocomplete="off" placeholder="" name="remarks" ></form:textarea></td>
																												
													</tr>
													
													<tr>
														<td><label for="uploadedapk">Upload APK File</label></td>
														<td><font color="red">*</font></td>
														<td>
														<!-- <input class="form-control placeholder-no-fix" type="file" autocomplete="off" placeholder="" name="uploadedapk" /> -->
														<div class="fileupload fileupload-new" data-provides="fileupload">
																<span class="btn default btn-file">
																<span class="fileupload-new"><i class="fa fa-paper-clip"></i> Upload APK file</span>
																<span class="fileupload-exists"><i class="fa fa-undo"></i> Change</span>
																<input type="file" class="default" name="uploadedapk" />
																</span>
																<span class="fileupload-preview" style="margin-left:5px;"></span>
																<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none; margin-left:5px;"></a>
														</div>
										
										
											
								
														</td>
																												
													</tr>
													
														

												</table>
														
												<div class="modal-footer">
													
													 <button class="btn btn-success pull-right" style="display: block;" id="btnOption" onclick="return validateForm()" value=""><span id="addOption"></span></button>
													 <button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													<!-- <button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update User</button> -->
												</div>
											</form:form>
											
											<form id="sprtForm" method="POST" action="">
												<input type="hidden" name="apkid" value="" id="apkid"/>
												
											</form>

										
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
	<!--  Adding MR Credit Master Access and Editing Form Ended -- -->
	
</div>
