<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		
<!-- MAIN CONTENT -->
<div id="content" class="container">

	<div class="row">
		<div class="col-xs-12">
		
			<a class="btn bg-color-pinkDark txt-color-white"
				style="width:150px;float: right;"
				data-toggle="tab" target="_blank" onclick="window.open('./downloadLTHTUserManual/download');">
				<font size="2"><b>New Connection <br> User Manual</b></font>
			</a>
			
	    </div>
	<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4"  style="text-align: center;margin-left:390px;margin-top: -4px;">
	<div class="well no-padding">

							<form:form  action="./customerRegistration" id="customer-form" class="smart-form client-form" method="post" commandName="Customers" modelAttribute="customers" novalidate="novalidate" autocomplete="off">
								<header>
								Customer Registration 
								</header>

								<fieldset>
								
								<section>
										<label class="label">Customer Name&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											
											<form:input type="text"	name="customerName" placeholder="Name" path="customerName" id="customerName" maxlength="25"></form:input>
											<b class="tooltip tooltip-bottom-right">Enter Full Name</b> </label>
									</section>
									<section>
										<label class="label">User Name&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											
											<form:input type="text"	name="customerLoginName" placeholder="Username" path="customerLoginName" id="customerLoginName" maxlength="25" onchange="checkduplicate()" onkeyup="convertToUpperCase();"></form:input>
											<b class="tooltip tooltip-bottom-right">Enter Your User Name</b> </label>
									</section>

									

									<section>
									<label class="label">Password&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											
											<form:input type="password"	name="password" placeholder="Password" path="password" id="password" maxlength="25" onkeyup="convertToUpperCase();"></form:input>
											<b class="tooltip tooltip-bottom-right">Enter Your password</b> </label>
									</section>

									<section>
									<label class="label">Confirm Password&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password"	name="confpassword" placeholder="Confirm password"  id="confpassword" onkeyup="convertToUpperCase();"></input>											
											<b class="tooltip tooltip-bottom-right">Enter Confirm password</b> </label>
									</section>
									<section>
									<label class="label">Mobile Number&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-phone"></i>											
											<form:input type="text"	name="customerContactNo" placeholder="MobileNo" path="customerContactNo" id="customerContactNo" data-mask="+99 (999) 999-9999" data-mask-placeholder= "10" maxlength="10" onchange="checkdmobileNo()"></form:input>
											<b class="tooltip tooltip-bottom-right">Enter Mobile Number</b> </label>
									</section>
									
									<section>
										<label class="label">Email-Id (Optional)</label>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											
											<form:input type="text"	name="customerEmailId" placeholder="Email address" path="customerEmailId" id="customerEmailId" onkeyup="convertToUpperCase();"></form:input>
											<b class="tooltip tooltip-bottom-right">Enter Your Email Address</b> </label>
									</section>
									<section id="otpId" style="display: None">
										<label class="label">OTP&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											<input type="text" name="otp" id="otp" placeholder="Enter OTP">
											<b class="tooltip tooltip-bottom-right">Enter OTP Send to your Mobile</b> </label>
											<div class="note">
								       <a href="#" onclick='resendOtp()'>Resend OTP</a>
							          </div>
									</section>
									
								</fieldset>

                             
								
								<footer>
									<div class="note" style="float: left;">
									<a href="./custumerLoginPage">Back to Customer Login</a>
							        </div>
									<button id="registerButoon" type="submit"  class="btn btn-primary" >
										Submit
									</button>
									<button id="submitButton" type="button"  style="display:None" onclick="validateotp()" class="btn btn-primary">
										 Register 
									</button>
								</footer>

								<div class="message">
									<i class="fa fa-check"></i>
									<p>
										Thank you for your registration!
									</p>
								</div>
							
                            </form:form>

						</div>
			
			
			
		</div>
	</div>
</div>
<c:if test="${not empty loginExists}">
			<script>
				var result = "${loginExists}";
				alert(result);
				$("#customerLoginName").val(" ");
			</script>
	<c:remove var="loginExists" scope="session" />
</c:if>

<c:if test="${not empty contactExists}">
			<script>
				var result = "${contactExists}";
				alert(result);
				$("#customerContactNo").val(" ");
			</script>
   <c:remove var="contactExists" scope="session" />
</c:if>

<c:if test="${not empty registrationOtp}">
				<script>
					var result = "${registrationOtp}";
					$("#otpId").show();
					$("#registerButoon").hide();
					$("#submitButton").show();
				</script>
						
	<c:remove var="registrationOtp" scope="session" />
</c:if>



<script type="text/javascript">
	
	
	function resendOtp(){
		 var loginname=$("#customerLoginName").val();
		 var contactNo=$("#customerContactNo").val();
		 var customerName=$("#customerName").val();
		 
		 
		 $.ajax({
				type : "GET",
				url : "./resendOtp",
				dataType : "text",
				data : {

					loginname : loginname,
					contactNo:contactNo,
					customerName:customerName
				},
				success : function(response) {
					
					
				}
			});
	}
	
 function  validateotp(){
	
	 var loginname=$("#customerLoginName").val();
	 var otp=$("#otp").val();
	 
	 
	 $.ajax({
			type : "GET",
			url : "./ValidateOtp",
			dataType : "text",
			data : {

				loginname : loginname,
				otp:otp,
			},
			success : function(response) {
				
				if (response == "Success") {
					alert("Registered Successfully");
					window.location.href="./custumerLoginPage";
				} else {
					alert("Invalid OTP");
					$("#otp").val(" ");
				}
			}
		});
 }

 function convertToUpperCase(){
		
		$("#customerLoginName").val($("#customerLoginName").val().trim());
		$("#password").val($("#password").val().trim());
		$("#confpassword").val($("#confpassword").val().trim());
		$("#customerEmailId").val($("#customerEmailId").val().trim());
		
	}
	
function checkduplicate(){
	 var loginname=$("#customerLoginName").val();
			
 	$.ajax({
		type : "GET",
		url : "./checkloginnameNameDuplicate",
		dataType : "text",
		data : {

			loginname : loginname,
			
		},
		success : function(response) {
			
			if (response == "NF") {

			} else {
				alert("User Name Already Exist");
				$("#customerLoginName").val(" ");
			}
		}
	});
}
	
	
function checkdmobileNo(){
 	
	var mobileNo=$("#customerContactNo").val();
			
	$.ajax({
		type : "GET",
		url : "./checkmobileNoDuplicate",
		dataType : "text",
		data : {

			mobileNo : mobileNo,
			
		},
		success : function(response) {
			
			if (response == "NF") {

			} else {
				alert("Mobile No already Registered");
				$("#customerContactNo").val(" ");
			}
		}
	}); 
}
	
	
	function otpvalidate(){
		$("#otpId").show();
		$("#registerButoon").hide();
		$("#submitButton").show();
	}

	$(document).ready(function() {
		
		 var register='${register}';
		 if(register=='yes'){
			 otpvalidate();
		 }
	
	});
	
	jQuery.validator.addMethod("noOnly", function(value, element) {
		  return this.optional(element) || /^[0-9 ]+$/i.test(value);
		}, "please enter numbers");
	
	$.validator.addMethod("regex", function(value, element, regexpr) {          
	     return regexpr.test(value);
	   }, ""); 
	
	
	$("#customer-form").validate({
		// Rules for form validation
		rules : {
			customerName : {
				required : true
			},
			customerLoginName : {
				required : true
			},
			customerContactNo : {
				required : true,
				digits:true,
				regex:/^[0-9]{10}$/
				
			},
			
			password : {
				required : true,
				minlength : 8,
				maxlength : 20
			},
			confpassword : {
                minlength : 8,
                maxlength:20,
                equalTo : "#password"
            },
            customerEmailId :{
            	email : true,
            }
		},

		// Messages for form validation
		messages : {
			customerName:{
				required:'Please Enter Your Full Name',	
			},
			customerLoginName:{
				required:'Please Enter Your User Name',
			},
				
			
			password : {
				required : 'Please Enter Your Password'
			},
			confpassword : {
				required : 'Please Enter Password again',
				equalTo : 'Confirm Password and Password should be same'
			},
			customerContactNo : {
				required : 'Please Enter Your Mobile No',
				regex:'Please Enter 10 Digit Mobile No',
				digits:'Please Enter Numbers',
				
			}
		},

		// Do not change code below  
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		
		/* success: function(label) {
			otpvalidate();   
		  } */
	});
		
</script>