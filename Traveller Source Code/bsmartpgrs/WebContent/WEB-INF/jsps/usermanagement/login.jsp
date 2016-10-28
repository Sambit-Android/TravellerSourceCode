<%@include file="/common/taglibs.jsp"%>
		
<!-- MAIN CONTENT -->
<div id="content" class="container">
	<c:remove var="CustomerLoginName" scope="session" />
	<c:remove var="userName" scope="session" />
	
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
			<img src="./resources/img/hero.jpg" class="pull-left display-image" alt="" style="width:700px;margin-top:0px;height:375px">		
		</div>
		
	<!-- </div> -->
	<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4" id="loginPageId" align="center">
		<div class="well no-padding">
			<form action="<c:url value='j_spring_security_check' />" id="login-form" class="smart-form client-form" method="post" autocomplete="off">
					<header>
					Login
					</header>

					<fieldset>
						<!-- <section>
							<label class="label">Login Into</label>
							<label class="select"> <i class="icon-append fa fa-user"></i>
								<select name="project" id="project" >												
												<option value="0" selected="selected">PGRS</option>
												<option value="1">NCMS</option>
												
											</select> 
								<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Enter your Project</b></label>
						</section> -->
						<section>
							<label class="label">Username</label>
							<label class="input"> <i class="icon-append fa fa-user"></i>
								<input type="text" id="username" name="j_username">
								<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Enter your username</b></label>
						</section>
						

						<section>
							<label class="label">Password</label>
							<label class="input"> <i class="icon-append fa fa-lock"></i>
								<input type="password" name="j_password">
								<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Enter your password</b> </label>
							<div class="note">
								<a href="./forgotpassword">Forgot password?</a>
							</div>
							<!-- <div class="note">
								<a href="./custumerLoginPage">Customer Login</a>
							</div> -->
						</section>

						<section>
							<label class="checkbox">
								<input type="checkbox" name="remember" checked="">
								<i></i>Stay signed in</label>
						</section>
					</fieldset>
					<footer>
						<button type="submit" class="btn btn-primary" name="sub">
							Sign in
						</button>
					</footer>
				</form>

			</div>
			
			<!-- <h5 class="text-center"> - Or sign in using -</h5>
												
				<ul class="list-inline text-center">
					<li>
						<a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
					</li>
					<li>
						<a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
					</li>
					<li>
						<a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
					</li>
				</ul> -->
			
		</div>
		</div>
	</div>
	
<div align="center" style="margin-top: 75px;">
<jsp:useBean id="now" class="java.util.Date"/>
 Copyright © <fmt:formatDate value="${now}" pattern="yyyy" /> BCITS All Rights Reserved.<br> 
<!-- <div style="margin-top: 4px;"><img src="./resources/img/bcitsLogo.png" style="width: 85px;"></img></div> -->
</div>
 <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
	<div aria-hidden="false" role="basic" tabindex="-1" id="ajax" class="modal fade in" style="display: block;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
					<h4 class="modal-title">Alert</h4>
				</div>
				<div class="modal-body">
					${SPRING_SECURITY_LAST_EXCEPTION.message}	
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn default" type="button" onclick="closePopup()">Close</button>					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>	
	
</c:if>	
<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />

<script>



	runAllForms();
	
	$(function() {
		// Validation
		$("#login-form").validate({
			// Rules for form validation
			rules : {
				username : {
					required : true
				},
				password : {
					required : true,
					minlength : 3,
					maxlength : 20
				}
			},

			// Messages for form validation
			messages : {
				email : {
					required : 'Please enter your email address',
					email : 'Please enter a VALID email address'
				},
				password : {
					required : 'Please enter your password'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	});
</script>
 
<script>

	/* $("#content").mousedown(function(ev){
    	if(ev.which == 3)
    	{
    		alert('Sorry,right click is not allowed');
    	}
	});  */

	function closePopup(){		
		$("#ajax").hide();
	}
	
	function projectselected(){
		var project=$("#project").find("option:selected").text();
		$.ajax
		({			
			type : "POST",
			url : "./projectSelected",
			async: false,
			dataType : "JSON",
			data : {

				project : project,
				
			},
			success : function(response) 
			{	    
			
			}
		
		});
		
		
	}
</script> 