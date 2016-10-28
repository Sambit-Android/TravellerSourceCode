<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	 $('#userLevel').attr('disabled',true);
   	    	$('#meterData-Acquisition').addClass('start active ,selected');
			$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	  	  
   	    	   $('#addData').click(
						function()
								{ 	
									document.getElementById("users").reset();
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
  
  function edit(param,opera)
	{
		var operation=parseInt(opera);
			  $.ajax(
						{
								type : "GET",
								url : "./editUser/" + operation,
								dataType : "json",
								success : function(response)
															{	
									 $('#userLevel').attr('disabled',false);
																document.getElementById("id").value=response.id;
																document.getElementById("username").value=response.username;
																document.getElementById("userPassword").value=response.userPassword;
																document.getElementById("confirmPassword").value=response.userPassword;																
																document.getElementById("userMailId").value=response.userMailId;
																document.getElementById("address").value=response.address;
																document.getElementById("userMobileNo").value=response.userMobileNo;
																document.getElementById("userType").value=response.userType;
																document.getElementById("userCreatedDate").value=dateFormating(response.userCreatedDate);
																document.getElementById("userStatus").value=response.userStatus;
																document.getElementById("userLevel").value=response.userLevel;
																document.getElementById('updateOption').style.display='block';
																document.getElementById('addOption').style.display='none';						
															}
						}
				    );
			$('#'+param).attr("data-toggle", "modal");
		    $('#'+param).attr("data-target","#stack1");
	}
  
  
  function validation(form1,operation)
  {	  
	  
	  if($('#image1').val().trim()!='')
	   {
	   var ext = $('#image1').val().split('.').pop().toLowerCase();
	   if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
		  bootbox.alert('please select image type');
		   return false;
	  }
	   }
	    else
	   {  
		   $('#image1').val('./resources/assets/img/avatar1_small.jpg');
	   }   
	   
	  
	  
	  
	    if(form1.username.value.trim()=='')
		  {
		   bootbox.alert('please enter username');
		   return false;
		  }
	    
	    var pwd=form1.userPassword.value.trim();
	    var cpwd=form1.confirmPassword.value.trim();
	    if(pwd=='')
		  {
	    	bootbox.alert('please enter password');
		   return false;
		  }
	    if(cpwd=='')
		  {
	    	bootbox.alert('please enter confirmPassword');
		   return false;
		  }
	    if(pwd!=cpwd)
	    	{
	    	 bootbox.alert('password and confirmPassword not matching');
			   return false;
	    	}
	    
	    if(form1.userMailId.value.trim()=='')
		  {
	    	bootbox.alert('please enter emailId');
		   return false;
		  }
	    
	    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{1,80})?$/;
	    if( !emailReg.test( form1.userMailId.value.trim() ) ) 
	    {
	    	bootbox.alert('please enter valid emailId');
	        return false;
	    }
	    
	    /* if(form1.address.value.trim()=='')
		  {
	    	bootbox.alert('please enter address');
		   return false;
		  } */
	    var mobile=form1.userMobileNo.value.trim();
	    /* if(mobile=='' || isNaN(mobile) || mobile.length!=10)
		  {
	    	bootbox.alert('please enter Mobile number, should contain only 10 digits');
		   return false;
		  } */
	    if(form1.userType.value=='0')
		  {
	    	bootbox.alert('please select usertype');
		   return false;
		  }
	    if(form1.userStatus.value=='0')
		  {
	    	bootbox.alert('please select userStatus');
		   return false;
		  }
		  
		  if(form1.userLevel.value=='0')
		  {
			bootbox.alert('please select userLevel');
		   return false;
		  }
		  $('#userLevel').attr('disabled',false);
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
						<i class="fa fa-edit"></i>Users
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
								data-toggle="modal" id="addData">
								Add User <i class="fa fa-plus"></i>
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
								<th >Image</th>
								<th>User Name</th>
								<th hidden="true">User Password</th>
								<th>User Email Id</th>
								<th >Address</th>
								<th>User MobileNo.</th>
								<th>User Type</th>
								<th>User Status</th>
								<th>User Level</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${usersList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td><img data-magnifyby="10" class="magnify" src="./getProfileImage/getImage/${element.id}"  height="50" width="50"  /></td>
									<td>${element.username}</td>
									<td hidden="true">${element.userPassword}</td>
									<td>${element.userMailId}</td>
									<td>${element.address}</td>
									<td>${element.userMobileNo}</td>
									<td>${element.userType}</td>
									<td>${element.userStatus}</td>
									<td>${element.userLevel}</td>
									<td><a href="#" onclick="edit(this.id,${element.id})"
										id="editData${element.id}">Edit</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Add User</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											<form:form action="./updateUser" modelAttribute="users" enctype="multipart/form-data"
																	commandName="users" method="post" id="users">
												<table>
												
													<tr hidden="true">
														<td>Id</td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id"></form:input></td>
													</tr>
													<tr>
														<td>EmailId</td>
														<td><font color="red">*</font></td>
														<td><form:input path="userMailId" id="userMailId"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="userMailId"></form:input></td>
													</tr>
													
													
													<tr>
														<td>Password</td>
														<td><font color="red">*</font></td>
														<td><form:input path="userPassword" id="userPassword"
																class="form-control placeholder-no-fix" type="password"
																autocomplete="off" placeholder="" name="userPassword"></form:input></td>
													</tr>
													
													<tr>
														<td>confirm password</td>
														<td><font color="red">*</font></td>
														<td><input id="confirmPassword"
																class="form-control placeholder-no-fix" type="password"
																autocomplete="off" placeholder="" name="confirmPassword"></input></td>
													</tr>
                                                    
                                                    <tr>
														<td>User Name</td>
														<td><font color="red">*</font></td>
														<td><form:input path="username" id="username"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="username"></form:input></td>
													</tr>
													
													<tr>
														<td>Address</td>
														<td><font color="red"></font></td>
														<td><form:input path="address" id="address"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="address"></form:input></td>
													</tr>
													
													<tr>
														<td>MobileNo.</td>
														<td><font color="red"></font></td>
														<td><form:input path="userMobileNo" id="userMobileNo"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="userMobileNo"></form:input></td>
													</tr>
													
													<tr>
														<td>User Type</td>
														<td><font color="red">*</font></td>
														<td> <form:select path="userType"
																	id="userType"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="userType" >
																	<form:option value="0">Select</form:option>
																	<form:option value="USER">USER</form:option>
																	<form:option value="ADMIN">ADMIN</form:option>	
																</form:select>
																
																</td>
													</tr>
													
													
													  <tr hidden="true">
															<td>User Created Date</td>
															<td></td>
															<td><form:input path="userCreatedDate" id="userCreatedDate"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="userCreatedDate"></form:input></td>
													   </tr> 
													<tr>
														<td>Status</td>
														<td><font color="red">*</font></td>
														<td><form:select path="userStatus"
																	id="userStatus"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="userStatus" >
																	<form:option value="0">Select</form:option>
																	<form:option value="ACTIVE">ACTIVE</form:option>
																	<form:option value="INACTIVE">INACTIVE</form:option>	
																</form:select>
																</td>
													</tr>
													
													<tr id="UserLevel1">
															<td>User Level</td>
															<td><font color="red">*</font></td>
															<td><form:select path="userLevel"
																	id="userLevel"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="userLevel" >
																	<form:option value="NONE">NONE</form:option>
																	<%-- <c:forEach items="${userLevelCheck}" var="userLevels">
																		<form:option value="${userLevels}"
																			>${userLevels}</form:option>
																	</c:forEach> --%>
																</form:select></td>
														</tr>
														
														<tr id="imageId">
													<td> upload Image</td>		
													<td><font >&nbsp;</font></td>											
														<td><input  type="file" id="image1"  name="image1" /></td>
														
														</tr>

												</table>
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													 <button class="btn blue pull-right" style="display: block;" id="addOption"  onclick="return validation(this.form,'add');">Add User</button>
													<button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return validation(this.form,'update');">Update User</button>
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