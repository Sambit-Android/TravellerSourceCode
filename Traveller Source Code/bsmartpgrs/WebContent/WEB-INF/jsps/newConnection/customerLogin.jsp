<%@include file="/common/taglibs.jsp"%>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="./resources/kendo/js/jquery.min.js"></script>		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>		
<!-- JQUERY VALIDATE -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
<div id="main">

	<!-- MAIN CONTENT -->
	<div id="content" class="container">

		
	 <c:remove var="CustomerLoginName" scope="session" />
	 <c:remove var="userName" scope="session" />
	 <c:if test="${not empty invalidLogin}">
						<script>
							var result = "${invalidLogin}";
							alert(result);
						</script>
						
			   <c:remove var="invalidLogin" scope="session" />
     </c:if>
     
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
			<h1 class="txt-color-red login-header-big">&nbsp;&nbsp;</h1>
			

	</div>
			<div  class="col-xs-12 col-sm-12 col-md-5 col-lg-4" style="text-align: center;margin-left:390px;margin-top: -40px;">
				<div class="well no-padding">
					<form  id="checkout-form" method="POST" class="smart-form client-form" novalidate="novalidate" autocomplete="off">
						<header>
							Customer Login
						</header>

						<fieldset style="padding-left: 64px;">
							
							<section>
								<label class="label">Login Name</label>
								<label class="input"> <i class="icon-prepend fa fa-user"></i>
									<input type="text" id="urLoginName" name="urLoginName" placeholder="Enter Login Name" style="width: 230px;" onkeyup="convertToUpperCase();">
									<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Enter your User Name</b> 
								</label><br/>
								
								<label class="label">Login Password</label>
								<label class="input"><i class="icon-prepend fa fa-lock"></i>
									<input type="password" id="password" name="password" placeholder="Enter Password" style="width: 230px;" onkeyup="convertToUpperCase();">
									<b class="tooltip tooltip-top-right">Enter your Password</b> 
								</label><br/>
								
								<label class="checkbox">
								<input type="checkbox" name="remember" checked="">
								<i></i>Stay signed in</label>
								
							</section>
							
							<div class="note">
									<a href="./registration">Customer Registration</a>
							</div>
							<div class="note">
									<a href="#" data-toggle="modal" data-target="#myModal">Activate Account/Forgot Password</a>
							</div>
							<div class="note">
									<a href="./login">Employee Login</a>
							</div>	
								

						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary" onclick="FormSubmit()">
								<strong>Submit</strong>
							</button>
							
						</footer>
					</form>

				</div>
			</div>
		</div>
	</div>

</div>


<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="" id="checkForgotId" method="POST"  novalidate="novalidate" autocomplete="off">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">Activate Account/Forgot Password By Login Id</h4>
							</div>
							<div class="modal-body">
				
								<div class="row">
									<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Your Login ID" name="loginName" id="loginName" onchange="checkduplicateLoginId()" onkeyup="trimLoginId();"/>
									</div>
										
										
									</div>
								</div>
								
								
				
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">
									Cancel
								</button>
								
								<!-- <button id="firstButton" type="button" class="btn btn-primary" onclick="passwordSubmit()">
									Submit
								</button> -->
								
								<button id="submitButton" type="button"  onclick="getPasswordByLoginId()" class="btn btn-primary">
										 Get Password 
								</button>
								<button id="submitButton1" type="button"  onclick="activateAccount()" class="btn bg-color-pinkDark txt-color-white">
										 Activate Account 
								</button>
							</div>
							</form>
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div>
				
				

<script>

var otpCheck;
/* function passwordSubmit(){
	
	var mobileNo=$("#mobileId").val();
	var mobileregex = new RegExp("^[0-9]{10}$");
	if(mobileNo==""){
		alert("Please Enter Mobile Number");
		return false;
	}
	else if (!(mobileregex.test(mobileNo)) && mobileNo!=null && mobileNo!="") {
		alert("Please Enter 10 Digit Mobile No");
	} 
	else{ 
		
	$.ajax
	({
		type : "POST",
		url :"./forgotCustomerPassword?mobileNo="+mobileNo,
		datatype : "JSON",
		async : false,
		success : function(response){
			
			 var obj = response[0];
			 var InvalidMobileNo = obj.InvalidMobileNo;
			 if(InvalidMobileNo!="undefined" && InvalidMobileNo!="" && InvalidMobileNo!=null){
				 alert(InvalidMobileNo);
				 $("#mobileId").val("");
			 }
			 else{
			 $("#mobileId").val(obj.mobile).attr("readonly", "readonly");
			 otpCheck = obj.otp;
			 $('#otpCheckId').show();
			 $('#firstButton').hide();
			 $('#submitButton').show();
			 $('#submitButton1').show();
			 }
			 
			
		}
	  });
    }  
}  */

function checkduplicateLoginId(){
	 var loginname=$("#loginName").val();
			
	$.ajax({
		type : "GET",
		url : "./checkloginnameNameDuplicate",
		dataType : "text",
		data : {

			loginname : loginname,
			
		},
		success : function(response) {
			
			if (response == "NF") {
				alert("Entered Login Id not available");
				$("#loginName").val(" ");
			} else {
				
			}
		}
	});
}


function getPasswordByLoginId(){
	
	var loginname=$("#loginName").val();
	 
	if(loginname==""){
		alert("Please enter Login Id");
		return false;
	}
	else{ 
		$.ajax
		({
			type : "POST",
			url :"./getPasswordByLoginId?loginname="+loginname,
			datatype : "JSON",
			async : false,
			success : function(response){
				alert(response);
				window.location.href="./custumerLoginPage";
				
			}
		  });
	    }  
} 

function activateAccount(){
	
	var loginName=$("#loginName").val();
	if(loginName==""){
		alert("Please enter Login Id");
		return false;
	}
	else{ 
		$.ajax
		({
			type : "POST",
			url :"./activateAccountByLoginId?loginName="+loginName,
			datatype : "JSON",
			async : false,
			success : function(response){
				alert(response);
				window.location.href="./custumerLoginPage";
				
			}
		  });
    }  
} 




function convertToUpperCase(){
	$("#urLoginName").val($("#urLoginName").val().trim());
	$("#password").val($("#password").val().trim());
}


function trimLoginId(){
	$("#loginName").val($("#loginName").val().trim());
}

 function FormSubmit(){
	 
	 $("#checkout-form").attr("action","./customerLogin").submit();  
} 

$(document).ready(function() {
	$.validator.addMethod("regex", function(value, element, regexpr) {
		return regexpr.test(value);
	}, "");
	
	$('#checkout-form').validate({
	// Rules for form validation
		rules : {
			urLoginName : {
				required : true
			},
			password : {
				required : true
			},
			
		},

		// Messages for form validation
		messages : {
			urLoginName : {
				required : 'Please enter your username'
			},
			password : {
				required : 'Please enter your password',
			},
			
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	
	
	
	$('#checkForgotId').validate({
		// Rules for form validation
			rules : {
				loginName : {
					required : true,
				},
				
			},

			// Messages for form validation
			messages : {
				loginName : {
					required : 'Please enter your Login Id',
				},
				
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	
	
	
});	

$(function () {
    $(document).bind('contextmenu', function (e) {
      e.preventDefault();
      alert('Sorry,right click is not allowed');
    });
});   

</script>
<style>
.invalid{
font-size: small;
}
</style>
