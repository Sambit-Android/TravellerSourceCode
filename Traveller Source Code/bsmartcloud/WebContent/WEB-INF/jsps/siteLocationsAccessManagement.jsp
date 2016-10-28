<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <style>
#siteLocations span.error,label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}

</style> -->
<%-- <script src="<c:url value='/resources/assets/plugins/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/assets/plugins/additional-methods.min.js'/>" type="text/javascript"></script>
 --%>
<script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	  $('#admin-location').addClass('start active ,selected');    	   	 
   	    	 $("#dash-board,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	   });

  
  
  function validateForm(){
	  var validator= $("#siteLocations").validate({
		  
		  rules:{
			  siteCode:"required",
			  company:"required",
			  zone:"required",
			  division:"required",
			  circle:"required",
			  section:"required",
			  subDivision:"required",
			  dbUser:"required",
			  emailid:{
				required:true,
				email:true
			  },
			  phone:{
				  number:true,
				  required:true
				  
			  }
		  },
		  errorElement:"span",
		  messages:{
			  siteCode:"Site Code is Required",
			  company:"Company Name is Required",
			  zone:"Please Enter zone",
			  division:"Division is Required..",
			  circle:"Please Provide Circle",
			  section:"please Provide Section",
			  subDivision:"Please Enter SubDivision..",
			  duUser:"Please Specify dbUser..",
			  emailid:{
				required:"Email ID is Required",
				email:"Invalid Email ID"
			  	},
		  		phone:{
		  			required:"Phone Number is Required.",
		  			number:"Phone Number Must be in Digits"
		  		}
		  }
	  });
	  
	  if(validator.form()){
		  $("form#siteLocations").attr("action","./updateSiteLocation");
		  $("form#siteLocations").submit();
	  }
	  $(".cancel").click(function() {
		    validator.resetForm();
		});
  }
  
  
  function addSiteLocaion(param){
	
	  $("#siteLocations").trigger("reset");
	  $("#siteCodeCheck").hide();
	  
	  //$("#siteLocations").attr("action","./addSiteLocation");
	 // $("#siteLocations").attr("action","./updateSiteLocation");
	  $("#addOption").html("Add Location");
	  $("#modelHeader").html("Add Location");
	  $("#siteCode").removeAttr("readonly");
	  $('#'+param).attr("data-toggle", "modal");
	  $('#'+param).attr("data-target","#regData");
	    
  
  }
  
  function editSiteLocation(param,siteCode){
	  $("#siteCodeCheck").hide();
	  $("#siteLocations").attr("action","./updateSiteLocation");
	  $("#siteCode").attr("readonly","true");
	  $("#addOption").text("Update Location");
	  $("#modelHeader").html("Update Location");
	  
	  $.ajax({
		  
		  type:"post",
		  url:"./editSiteLocation",
		  data:{'siteCode':siteCode},
		  dataType:"JSON",
		  success:function(response){
			  
			  document.getElementById("siteCode").value = response.siteCode;
			  document.getElementById("company").value = response.company;
			  document.getElementById("zone").value = response.zone;
			  document.getElementById("dbUser").value = response.dbUser;
			  document.getElementById("emailid").value = response.emailid;
			  document.getElementById("phone").value = response.phone;
			  document.getElementById("circle").value = response.circle;
			  document.getElementById("division").value = response.division;
			  document.getElementById("subDivision").value = response.subDivision;
			  document.getElementById("section").value = response.section;
			  
		  }
	  });
	  
	  $('#'+param).attr("data-toggle", "modal");
	  $('#'+param).attr("data-target","#regData");
	    
  }
  
  function checkAvailabity(siteCode){
	  $.ajax({
		  type:"POST",
		  url:"./checkSiteCodeAvailability",
		  data:{"siteCode":siteCode},
		  dataType:"json",
		  success:function(response){
			  if(response){
				  $("#siteCodeCheck").show();
				  $("#siteCodeCheck").html("<span style='color:red'>Site Code has already existed.</span>");
				  $("#siteCode").focus();
				  $("#siteCode").select();
			  }else{
				  $("#siteCodeCheck").hide();
			  }
		  }
	  });
  
  }
  
  
</script>
<div class="page-content">

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
						<i class="fa fa-edit"></i>Location Details
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<!-- <div class="btn-group">
							<button class="btn green" onclick="addSiteLocaion(this.id)"
								data-toggle="modal" id="addSiteLocation" >
								Add Location <i class="fa fa-plus"></i>
							</button>
						</div> -->
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
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr style="background-color: lightgray; ">
								
								<th>Circle</th>
								
								<th>Division</th>
								<th>Subdivision</th>
								<th>Section</th>
								<th>Section Code</th>
								<!-- <th>Zone</th> -->
								<!-- <th>Edit</th> -->
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${sitelocs}">
								<tr>
									
									<td>${element.circle}</td>
									<td>${element.division}</td>
									<td>${element.subDivision}</td>
									<td>${element.section}</td>
									<td>${element.siteCode}</td>
									<%-- <td>${element.zone }</td> --%>
									<%-- <td><a href="#" onclick="editSiteLocation(this.id,${element.siteCode})"
										id="editData${element.siteCode}">Edit</a></td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
		</div>
	</div>
	
	<!--MRMaster Registration content started -->
	
	<%-- <div id="regData" class="modal fade" tabindex="-1" data-width="400">
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
											<form:form action="" modelAttribute="siteLocations"
																	commandName="siteLocations" method="post" id="siteLocations">
												<table >
												
													<tr id="hid">
														<td ><form:label path="siteCode">Site Code</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="siteCode" id="siteCode"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="siteCode" onblur="(this.value !='')?checkAvailabity(this.value):''" maxlength="4"></form:input></td>
																<td id="siteCodeCheck"></td>
													</tr>
													
													
													<tr>
														<td><form:label path="company">Company Name</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="company" id="company"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="company"></form:input></td>
													</tr>
													
													<tr>
														<td><form:label path="zone">Zone</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="zone" id="zone"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="zone"></form:input></td>
													</tr>
													
													<tr>
														<td><form:label path="circle">Circle</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="circle" id="circle"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="circle"></form:input></td>
													</tr>
													
													<tr>
														<td><form:label path="division">Division</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="division" id="division"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="division"></form:input></td>
													</tr>
													
													<tr>
														<td><form:label path="subDivision">SubDivision</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="subDivision" id="subDivision"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="subDivision"></form:input></td>
													</tr>
													
													<tr>
														<td><form:label path="section">Section</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="section" id="section"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="section"></form:input></td>
													</tr>
													
													
													<tr>
														<td><form:label path="dbUser"> DB User</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="dbUser" id="dbUser"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="dbUser"></form:input></td>
													</tr>
													<tr>
														<td><form:label path="emailid"> Email ID</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="emailid" id="emailid"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="emailid"></form:input></td>
													</tr>
													<tr>
														<td><form:label path="phone">Phone</form:label> </td>
														<td><font color="red">*</font></td>
														<td><form:input path="phone" id="phone"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="phone"></form:input></td>
													</tr>
													
													
													
												</table>
														
												<div class="modal-footer">
													
													 <button class="btn blue pull-right" style="display: block;" id="btnOption"  onclick="validateForm()" value=""><span id="addOption"></span></button>
													 <button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													<!-- <button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update User</button> -->
												</div>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
	
	<!--MRMaster Registration content ended -->
</div>