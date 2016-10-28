<%@include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<div>
	
	<aside id="left-panel">
		
		<c:if test = "${not empty userId}"> 			        
		    <script>
				window.location.href="./custumerLoginPage";
			</script>
			
        </c:if>
        
		<div class="login-info">
				<div style="margin-top: 5px;height: 30px;margin-left: 10px;">
					 <img src="./resources/img/personImage.jpg" alt=""/> 
					<span>${CustomerLoginName}</span>
				</div> 
		</div>
		
		<nav>
			
			<ul id="menu">
				
				<!--  NEW CONNECTION-->
				
				<!-- 
				<li><a href="./welcomeCustomer"><i class="fa fa-lg fa-fw fa-home">
				<c:if test="${countNotifications!=0}">
					<em>${countNotifications}</em>
				</c:if>
				</i>Home</a>
				</li>
				 -->
				
				
				<li id="newConsunmer">
						<a href="#"><i class="fa fa-lg fa-fw fa fa-book"></i> <span class="menu-item-parent">New Connection</span></a>
						
					<ul class="second">
						<li><a href="./welcomeCustomer"><i class="fa fa-lg fa-fw fa-home"></i>Home</a>
						</li>
						 <li><a href="./onlinePayBillDesk"><i class="fa fa-lg fa-fw fa-rupee"></i>Online Payment</a></li>
						<li><a href="./applicationRegistration"><i class="fa fa-lg fa-fw fa-key"></i>Register Application</a></li>
						<li><a href="./trackApplication"><i class="fa fa-lg fa-fw fa-paperclip"></i>Track Application</a></li>
						<li><a href="./myNotifications"><i class="fa fa-lg fa-fw  fa fa-envelope-o"></i>Notifications</a></li>
						<li><a href="./documentsUpload"><i class="fa fa-lg fa-fw fa-file"></i>Documents</a></li>
						<li><a href="./myProfile"><i class="fa fa-lg fa-fw fa-user"></i>My Profile</a></li>
						<li><a href="./paymentHistory"><i class="fa fa-lg fa-fw fa-credit-card custom"></i>Payment History</a></li>
					</ul>
				</li>
				
				 
				<!--  EXISTING CONSUMER FROM TRM-->
				
				<li id="existingConsunmer">
						<a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i> <span class="menu-item-parent">Existing Consumer</span></a>
						<ul class="first">
						
						<li><a href="./registerRRNo"><i class="fa fa-lg fa-fw fa-database"></i>Register RR No</a></li>
						<li><a href="./onlinepayment"><i class="fa fa-lg fa-fw fa-rupee"></i>Bill Payment Online</a></li>
						<li><a href="./connectionDetails"><i class="fa fa-lg fa-fw fa-hand-o-up"></i>Connection Details</a></li>
						<li><a href="./billHistory"><i class="fa fa-lg fa-fw fa fa-asterisk"></i>Bill History</a></li>
						<li><a href="./depositDetails"><i class="fa fa-lg fa-fw fa fa-th-large"></i>Deposit Details</a></li>
						<li><a href="./paymentDetails"><i class="fa fa-lg fa-fw fa-credit-card custom"></i>Bill Payments</a></li>
						<li><a href="./loadChange"><i class="fa fa-lg fa-fw fa fa-ban"></i>Load Change</a></li>
						<li><a href="./nameChange"><i class="fa fa-lg fa-fw fa fa-location-arrow"></i>Name Change</a></li>
						<li><a href="./tariffChange"><i class="fa fa-lg fa-fw fa-pencil-square"></i>Tariff Change</a></li> 
						</ul>
				</li>
				
	        </ul>
		</nav>
		
		<span class="minifyme" data-action="minifyMenu"> 
			<i class="fa fa-arrow-circle-left hit"></i> 
		</span>
		
	</aside>
	<!-- END NAVIGATION -->
</div>

<script>
 
$(document).ready(function() {
	
	$('#newConsunmer').addClass('open');
	$('#existingConsunmer').addClass('open');
	$('.first').attr('style','display: block');
	$('.second').attr('style','display: block');
	
});


	 
</script>
