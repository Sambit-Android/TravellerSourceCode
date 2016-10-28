<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>




<section id="widget-grid" class="">
<div id="content">

	   <c:if test="${not empty customerProfileResult}">
						<script>
							var customerProfileResult = "${customerProfileResult}";
							alert(customerProfileResult);
						</script>
						
			          <c:remove var="customerProfileResult" scope="session" />
			   
	   </c:if>
	   
	    <c:if test="${not empty customerPasswordResult}">
						<script>
							var customerPasswordResult = "${customerPasswordResult}";
							alert(customerPasswordResult);
						</script>
						
			          <c:remove var="customerPasswordResult" scope="session" />
			   
			            <script>
			               window.location.href="./custumerLoginPage";
						</script>
	   </c:if>
		   
  <div class="row">

<article class="col-sm-12 ">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-21" data-widget-editbutton="false" data-widget-custombutton="false">
				
				<header>
					<span class="widget-icon"><i class="fa fa-user"></i></span>
					<h2>My Profile </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form:form action="" method="post" id="customer-profile" class="smart-form" commandName="customerProfile" modelAttribute="customerProfile">
							<input type="hidden" id="customerId" autocomplete="off" placeholder="" name="customerId" value="${customerId}"></input>
							<input type="hidden" id="password" autocomplete="off" placeholder="" name="password" value="${password}"></input>
							<input type="hidden" id="userActive" autocomplete="off" placeholder="" name="userActive" value="${userActive}"></input>	
						   
							<fieldset>
							<div class="row">
								<section class="col col-3">
									<label class="label">Name&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-user" id="usericon"></i> 
											<form:input type="text" name="customerName" id="customerName" placeholder="Name" path="customerName"></form:input>
										</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Login Name&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-user" id="usericon"></i> 
											<form:input type="text" name="customerLoginName" id="customerLoginName" placeholder="Login Name" path="customerLoginName"></form:input>
										</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Contact Number&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-mobile"></i>
											<form:input type="text" name="customerContactNo" id="customerContactNo" placeholder="Mobile No" path="customerContactNo" data-mask="9999999999"></form:input>
										</label>
								</section>
								
							
								
						   
								<section class="col col-3">
									<label class="label">Alternative Contact Number</label><label
											class="input"><i class="icon-prepend fa fa-mobile"></i>
											<form:input type="text" name="customerAlternateContactNo" id="customerAlternateContactNo" placeholder="Alternative Mobile No" path="customerAlternateContactNo"></form:input>
										</label>
								</section>
								
								
							</div>	
							
							
							<div class="row">
								<section class="col col-4">
									<label class="label">Email Id</label><label
											class="input"><i class="icon-prepend fa fa-envelope"></i>
											<form:input type="email" name="customerEmailId" id="customerEmailId" placeholder="Email Id" path="customerEmailId"></form:input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">Alternative Email Id</label><label
											class="input"><i class="icon-prepend fa fa-envelope"></i>
											<form:input type="email" name="customerAlternateEmailId" id="customerAlternateEmailId" placeholder="Alt Email Id" path="customerAlternateEmailId"></form:input>
										</label>
								</section>
								
								
							</div>	
							
							
						
							
							</fieldset>

							<footer>
								<button type="button" class="btn btn-primary"  onclick="return checkData(0);">
												<strong>Update Profile</strong>
								</button>
							</footer>
							
							
						</form:form>						
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			
			
			
			
			<div class="jarviswidget" id="wid-id-4" data-widget-editbutton="false" data-widget-custombutton="false">
				
				<header>
					<span class="widget-icon"><i class="fa fa-lock"></i></span>
					<h2>Change password </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form:form action="" method="post" id="change-password" class="smart-form" commandName="customerProfile" modelAttribute="customerProfile">
							<input type="hidden" id="customerId" autocomplete="off" placeholder="" name="customerId" value="${customerId}"></input>
							<input type="hidden" id="customerName" autocomplete="off" placeholder="" name="customerName" value="${customerName}"></input>
							<input type="hidden" id="customerLoginName" autocomplete="off" placeholder="" name="customerLoginName" value="${customerLoginName}"></input>
							<input type="hidden" id="customerContactNo" autocomplete="off" placeholder="" name="customerContactNo" value="${customerContactNo}"></input>
							<input type="hidden" id="customerAlternateContactNo" autocomplete="off" placeholder="" name="customerAlternateContactNo" value="${customerAlternateContactNo}"></input>
							<input type="hidden" id="customerEmailId" autocomplete="off" placeholder="" name="customerEmailId" value="${customerEmailId}"></input>
							<input type="hidden" id="customerAlternateEmailId" autocomplete="off" placeholder="" name="customerAlternateEmailId" value="${customerAlternateEmailId}"></input>
							<input type="hidden" id="userActive" autocomplete="off" placeholder="" name="userActive" value="${userActive}"></input>
							
							
						<fieldset>
							<div class="row">
								<section class="col col-4">
									<label class="label">Old Password&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-lock"></i> 
											<form:input type="password" name="password" id="password2"  path="password"></form:input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">New Password&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-lock"></i>  
											<input type="password" name="newPassword" id="newPassword" placeholder="New Password"></input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">Confirm Password&nbsp;<font color="red">*</font></label><label
											class="input"><i class="icon-prepend fa fa-lock"></i> 
											<input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password"></input>
										</label>
								</section>
								
							</div>	
							
							</fieldset>
							
							<footer>
								<button type="button" class="btn btn-primary"  onclick="return checkChangePassword(0);" id="passwordButton"><strong>Update Password</strong></button>
							</footer>
							
							
						</form:form>						
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			
			
			
			
			<!-- end widget -->								


		</article>
		</div></div>
		</section>
		
		
	<script>
	 
	$(document).ready(function() {
		pageSetUp();
	});
	
	
	function checkData(param) {
		$("#customer-profile").attr("action","./customerProfile/update").submit();
	}
	
	function checkChangePassword(param) {
		if($('#change-password').valid()){
		 $("#change-password").attr("action","./customerPasswordChange/update").submit();
		 document.getElementById("passwordButton").disabled = true;
	   }
		else{
			return false;
		}
	}
	
	
	$(document).ready(function() {
		
		var customerId= "${customerId}";
		var password2= "${password}";
		var customerName= "${customerName}";
		var customerLoginName= "${customerLoginName}";
		var customerContactNo= "${customerContactNo}";
		var customerAlternateContactNo= "${customerAlternateContactNo}";
		var customerEmailId= "${customerEmailId}";
		var customerAlternateEmailId= "${customerAlternateEmailId}";
		
		$('#customerId').val(customerId).attr("readonly", "readonly");
		$('#customerName').val(customerName);
		$('#customerLoginName').val(customerLoginName).attr("readonly", "readonly");
		$('#customerContactNo').val(customerContactNo);
		$('#customerAlternateContactNo').val(customerAlternateContactNo);
		$('#customerEmailId').val(customerEmailId);
		$('#customerAlternateEmailId').val(customerAlternateEmailId);
		$('#password2').val(password2).attr("readonly", "readonly");
		
	
	});
	
	
	
	
	$(document).ready(function() {
		
		 
	      $("#change-password").validate({

			// Rules for form validation
			rules : {
				newPassword : {
					required : true,
					minlength : 3,
					maxlength : 20
				},
				confirmPassword : {
					required : true,
					minlength : 3,
					maxlength : 20,
					equalTo : '#newPassword'
				},
				
				
				
			},

			// Messages for form validation
			messages : {
				
				newPassword : {
					required : 'Please enter your new password'
				},
				confirmPassword : {
					required : 'Please enter your password one more time',
					equalTo : 'Please enter the same password as above'
				},
				
				
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

		
	     
		}); 
	
	
	
	
	
	
	
	$(document).ready(function() {
		
		$.validator.addMethod("regex", function(value, element, regexpr) {          
		     return regexpr.test(value);
		   }, ""); 
		  
		 
	      $("#customer-profile").validate({

			// Rules for form validation
			rules : {
				customerName : {
					required : true,
					maxlength : 225,
					regex:/^[a-zA-Z0-9 .]*$/
				},
				
				customerContactNo : {
					required : true,
					digits:true,
					regex:/^[0-9]{10}$/
				},
				
				customerAlternateContactNo : {
					/* regex:/^[0-9]{10}$/, */
					digits : true
					
				},
				
				customerEmailId : {
					/* required : true, */
					email : true,
					maxlength : 100
					
				},
				
				customerAlternateEmailId : {
					email : true,
					maxlength : 100
					
				},
				
			},

			// Messages for form validation
			messages : {
				
				customerName : {
					required : 'Please enter name',
					regex:"please enter valid name",
					maxlength:"maxlength is 225"
				},
				
				customerContactNo : {
					required : 'Please enter mobile no',
					regex:"Enter 10 digit mobile no"
				},
				
				customerAlternateContactNo : {
					regex:"Enter 10 digit mobile no"
				},

				customerEmailId : {
					required : 'Please enter mail id',
					email : 'Please enter a valid email address'
				},
				
				customerAlternateEmailId : {
					email : 'Please enter a valid email address'
				},
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

		
	     
		}); 
	      
	</script>