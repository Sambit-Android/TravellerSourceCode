<%@include file="/common/taglibs.jsp"%>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="./resources/kendo/js/jquery.min.js"></script>		
		
<!-- JQUERY VALIDATE -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
<div id="main">

	<!-- MAIN CONTENT -->
	<div id="content" class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
			<img src="./resources/img/hero.jpg" class="pull-left display-image" alt="" style="width:700px;margin-top:0px;height:375px">		
		</div>
		
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
				<div class="well no-padding">
					<form  id="checkout-form" method="POST" class="smart-form client-form" novalidate="novalidate" autocomplete="off">
						<header>
							Forgot Password
						</header>

						<fieldset style="padding-left: 64px;">
							
							<section>
								<!-- <label class="label">Your UserName</label> -->
								<label class="input"> <i class="icon-prepend fa fa-user"></i>
									<input type="text" id="urLoginName" name="urLoginName" placeholder="Enter User Name" style="width: 230px;">
									<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Enter your User Name</b> 
								</label><br/>
								
								<!-- <label class="label">Your Email Address</label> -->
								<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
									<input type="text" id="urEmail" name="urEmail" placeholder="Enter Email" style="width: 230px;">
									<b class="tooltip tooltip-top-right">Enter your Email</b> 
								</label><br/>
								
								<!-- <label class="label">Your Mobile Number</label> -->
								<label class="input"> <i class="icon-prepend fa fa-phone"></i>
									<input type="text" id="mobileNo" name="mobileNo" placeholder="Enter Mobile No" style="width: 230px;">
									<b class="tooltip tooltip-top-right">Enter your Mobile No</b> 
								</label>
							</section>
								<div class="note">
									<a href="./login">Back to Login</a>
								</div>

						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary" onclick="FormSubmit()">
								<strong><i class="fa fa-arrow-circle-right" style="margin-top: 10px"></i>Submit</strong>
							</button>
							<!-- <a class="btn btn-primary" style="width: 100px;height: 31px;margin-left: 30px">
								<strong><i class="fa fa-arrow-circle-right" style="margin-top: 10px"></i>Submit</strong>
							</a> -->
						</footer>
					</form>

				</div>
			</div>
		</div>
	</div>
	
</div>

<div align="center">
<jsp:useBean id="now" class="java.util.Date"/>
 Copyright © <fmt:formatDate value="${now}" pattern="yyyy" /> BCITS All Rights Reserved.<br>
<!-- <div style="margin-top: 4px;"><img src="./resources/img/bcitsLogo.png" style="width: 85px;"></img></div> -->
</div>

<script>
 function FormSubmit(){
	var urLoginName=$("#urLoginName").val();
	var urEmail=$("#urEmail").val();
	var mobileNo=$("#mobileNo").val();
	 
	if(urLoginName==""){
		
	}
	else  if(urEmail==""){
		
	}
	else if(mobileNo==""){

	} else{ 
		
	$.ajax
	({
		type : "POST",
		url : "./recoverPassword/"+urLoginName+"/"+urEmail+"/"+mobileNo,
		datatype : "JSON",
		async : false,
		success : function(response){
			if(response.contains("true")){
	    		 alert(response.replace("true",""));
		    	 window.location.href = './login';
	    	 }else{
	    		 alert(response.replace("false",""));
	    	 }
		}
	  });
    }  
} 

$(document).ready(function() {

	$('#checkout-form').validate({
	// Rules for form validation
		rules : {
			urLoginName : {
				required : true
			},
			urEmail : {
				required : true,
				email : true
			},
			mobileNo : {
				required : true,
				digits : true,
				minlength : 10,
				maxlength : 10
			}
		},

		// Messages for form validation
		messages : {
			urLoginName : {
				required : 'Please enter your username'
			},
			urEmail : {
				required : 'Please enter your email id',
				email : 'Please enter a valid email id'
			},
			mobileNo : {
				required : 'Please enter your mobile number',
				digits : 'Please enter only digits',
				minlength : 'Please enter your 10 digit mobile number',
				maxlength : 'Please enter your 10 digit mobile number'
			}
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

