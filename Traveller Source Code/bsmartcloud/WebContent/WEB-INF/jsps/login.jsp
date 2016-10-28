
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0
Version: 1.5.3
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<!-- <title>Administrative Web Portal</title> -->
	<title>BsmartCloud</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<!-- BEGIN GLOBAL MANDATORY STYLES -->          
	<link href="<c:url value='/resources/assets/plugins/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/plugins/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/plugins/uniform/css/uniform.default.css' />" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/assets/plugins/select2/select2_metro.css' />" />
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME STYLES --> 
	<link href="<c:url value='/resources/assets/css/style-metronic.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/css/style.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/css/style-responsive.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/css/plugins.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/css/themes/default.css'/>" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="<c:url value='/resources/assets/css/pages/login-soft.css'/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value='/resources/assets/css/custom.css'/>" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="<c:url value='/resources/bsmart.lib.js/favicon.ico'/>" />
	<script type="text/javascript">
	 function focusOnFields(e)
       {
      
        
        var  keyPressed;
    keyPressed = e.keyCode;
    if(keyPressed==13)  
    {
     //window.location.href = "./login";
    	document.getElementById("login").submit();
     }
       }
	 
	 function focusOnFields1(e)
     {
    
     
      var  keyPressed;
  keyPressed = e.keyCode;
  if(keyPressed==13)  
  {
   window.location.href = "./sendPassword";
   
   }
     }
    </script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
		<div class="logo" >
		 
		
			<%-- <img src="<c:url value='/resources/assets/img/logo.png'/>" alt="logo" class="img-responsive" /> --%>
			<c:if test="${userLogout ne 'userLogout'}">
		 <font color="white" size="4" style="text-align: center;margin-left: 0px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bsmart </font><font color="red" size="4">Cloud</font> 
			</c:if>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<style type="text/css">
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 8px;
}
</style>
	<div class="content">
		 <c:if test = "${msg ne 'notDisplay'}"> 	
        <%-- <span class="errorblock" style="color: red;"><spring:message code="label.invalidUser"></spring:message></span>  --%>
        <div class="alert alert-danger display-show">
				<button class="close" data-close="alert"></button> 
				<span style="color:red" >${msg}</span>
			</div>
        </c:if>  
        
        <%-- <c:if test = "${msg eq 'invalidSession'}">        
        <div class="alert alert-danger display-show">
				<button class="close" data-close="alert"></button>
				<span style="color:red" >your Session has been expired please login</span>
			</div>
        </c:if>  --%>
		<!-- BEGIN LOGIN FORM -->
		<%-- <form class="login-form" action="./login" method="post"> --%>
		<%-- <form:form class="login-form" action="./j_spring_security_check" modelAttribute="user" commandName="user" method="post" id="login">
		 <form:errors path="*" cssClass="errorblock" element="div" /> 
			<h3 class="form-title">Login to your account</h3>
			<!-- <div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>Enter any username and password.</span>
			</div> -->
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					
					<form:input path="username" id="nameInput" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name='j_username' ></form:input>
                    <font color="red"><form:errors path="username" cssclass="error"></form:errors></font>
    
					<!-- <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username"/> -->
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<form:input path="password" id="passwordInput" class="form-control placeholder-no-fix" 
					onkeypress="if(event.keyCode==13)focusOnFields(event)" type="password" autocomplete="off" placeholder="Password"  name='j_password'></form:input>
                    <font color="red"><form:errors path="password" cssclass="error"></form:errors></font>
    
					<!-- <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password"/> -->
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<form:checkbox path="password" name="remember" value="1"/> Remember me
				</label>
				  
				
				<form:button class="btn blue pull-right">
				Login <i class="m-icon-swapright m-icon-white"></i>
				</form:button>          
			</div>
			 <div class="forget-password">
				<h4>Forgot your password ?</h4>
				<p>
					no worries, click <a href="javascript:;"  id="forget-password">here</a>
					to reset your password.
				</p>
			</div> 
			<!-- <div class="create-account">
				<p>
					Don't have an account yet ?&nbsp; 
					<a href="javascript:;" id="register-btn" >Create an account</a>
				</p>
			</div> -->		
		</form:form> --%>
			<%-- <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">		
		<div id="errorMessage" title="Alert">
			<b>${SPRING_SECURITY_LAST_EXCEPTION.message}</b>
		</div>
		
		<script type="text/javascript">

		$("#errorMessage").dialog({
			modal: true,
			draggable: false,
			resizable: false,
			draggable: true,
			buttons: {
				"Close": function() {
					$( this ).dialog( "close" );
				}
			}
		}); 
		</script>		
	</c:if>	 --%>
		<%-- <form action="<c:url value='j_spring_security_check' />" id="login"	method="POST">				
						
			<input type="text" id="userid" name='j_username' placeholder="Your User Name" class="loginUsername" style="width: 200px" maxlength="25" required />
			<input id="password" type="password" name='j_password'	placeholder="Password" class="loginPassword" style="width: 200px" maxlength="25" required />
			<input type="submit" name="submit" value="Login" class="buttonM bBlue" onsubmit="checkCookie();"/>
			
		</form>
		 --%>
		<!-- END LOGIN FORM -->        
		<!-- BEGIN FORGOT PASSWORD FORM -->
		<%-- <form class="forget-form" action="./sendPassword" method="post">
			<h3 >Forget Password ?</h3>
			<p>Enter your e-mail address below to send your password.</p>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-envelope"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="emailAddress" />
				</div>
			</div>
			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
				<i class="m-icon-swapleft"></i> Back
				</button>
				<button type="submit" class="btn blue pull-right" 
				onkeypress="if(event.keyCode==13)focusOnFields1(event)">
				Submit <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>
		</form>
		 --%>
		
		
		<c:if test="${userLogout ne 'userLogout'}">
		 <form:form class="login-form" action="./login" modelAttribute="user" commandName="user" method="post" id="login">
		  
			<h3 class="form-title">Login</h3>			
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<!-- <input type="text" id="userid" name='j_username' placeholder="Your User Name" class="loginUsername" style="width: 200px" maxlength="25" required /> -->
					<form:input path="userMailId" id="userMailId" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="userMailId" name="userName" ></form:input>
					<!-- <input  id="nameInput" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email-Id" name='username' ></input> -->                   
    				
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<!-- <input id="password" type="password" name='j_password'	placeholder="Password" class="loginPassword" style="width: 200px" maxlength="25" required /> -->
					<form:input path="userPassword"  class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="userPassword"  name="userPassword" onkeypress="if(event.keyCode==13)focusOnFields(event)" ></form:input>
					
					<!-- <input  id="passwordInput" class="form-control placeholder-no-fix" 
					onkeypress="if(event.keyCode==13)focusOnFields(event)" type="password" autocomplete="off" placeholder="Password"  name='userPassword'></input> -->
                   
    
					<!-- <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password"/> -->
				</div>
			</div>
			<div class="form-actions">
				<%-- <label class="checkbox">
				<form:checkbox path="password" name="remember" value="1"/> Remember me
				</label> --%>
				<!--  <input type="submit" name="submit" value="Login" class="buttonM bBlue" onsubmit="checkCookie();"/> --> 
				<button class="btn blue pull-right" type="submit">
				Login
				<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<!-- <input class="btn blue pull-right" type="submit" name="Login" value="Login">
				 <i class="m-icon-swapright m-icon-white"></i>
				</input>   -->        
			</div>
			 <!-- <div class="forget-password">
				<h4>Forgot your password ?</h4>
				<p>
					no worries, click <a href="javascript:;"  id="forget-password">here</a>
					to reset your password.
				</p>
			</div>  -->
			<!-- <div class="create-account">
				<p>
					Don't have an account yet ?&nbsp; 
					<a href="javascript:;" id="register-btn" >Create an account</a>
				</p>
			</div> -->		
		</form:form>
		
		</c:if>
		
		
		
		<!-- END FORGOT PASSWORD FORM -->
		<!-- BEGIN REGISTRATION FORM -->
		<form class="register-form" action="" method="post">
			<h3 >Sign Up</h3>
			<p>Enter your personal details below:</p>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Full Name</label>
				<div class="input-icon">
					<i class="fa fa-font"></i>
					<input class="form-control placeholder-no-fix" type="text" placeholder="Full Name" name="fullname"/>
				</div>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Email</label>
				<div class="input-icon">
					<i class="fa fa-envelope"></i>
					<input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Address</label>
				<div class="input-icon">
					<i class="fa fa-check"></i>
					<input class="form-control placeholder-no-fix" type="text" placeholder="Address" name="address"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">City/Town</label>
				<div class="input-icon">
					<i class="fa fa-location-arrow"></i>
					<input class="form-control placeholder-no-fix" type="text" placeholder="City/Town" name="city"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Country</label>
				<select name="country" id="select2_sample4" class="select2 form-control">
					<option value=""></option>
					<option value="AF">Afghanistan</option>
					<option value="AL">Albania</option>
					<option value="DZ">Algeria</option>
					<option value="AS">American Samoa</option>
					<option value="AD">Andorra</option>
					<option value="AO">Angola</option>
					<option value="AI">Anguilla</option>
					<option value="AQ">Antarctica</option>
					<option value="AR">Argentina</option>
					<option value="AM">Armenia</option>
					<option value="AW">Aruba</option>
					<option value="AU">Australia</option>
					<option value="AT">Austria</option>
					<option value="AZ">Azerbaijan</option>
					<option value="BS">Bahamas</option>
					<option value="BH">Bahrain</option>
					<option value="BD">Bangladesh</option>
					<option value="BB">Barbados</option>
					<option value="BY">Belarus</option>
					<option value="BE">Belgium</option>
					<option value="BZ">Belize</option>
					<option value="BJ">Benin</option>
					<option value="BM">Bermuda</option>
					<option value="BT">Bhutan</option>
					<option value="BO">Bolivia</option>
					<option value="BA">Bosnia and Herzegowina</option>
					<option value="BW">Botswana</option>
					<option value="BV">Bouvet Island</option>
					<option value="BR">Brazil</option>
					<option value="IO">British Indian Ocean Territory</option>
					<option value="BN">Brunei Darussalam</option>
					<option value="BG">Bulgaria</option>
					<option value="BF">Burkina Faso</option>
					<option value="BI">Burundi</option>
					<option value="KH">Cambodia</option>
					<option value="CM">Cameroon</option>
					<option value="CA">Canada</option>
					<option value="CV">Cape Verde</option>
					<option value="KY">Cayman Islands</option>
					<option value="CF">Central African Republic</option>
					<option value="TD">Chad</option>
					<option value="CL">Chile</option>
					<option value="CN">China</option>
					<option value="CX">Christmas Island</option>
					<option value="CC">Cocos (Keeling) Islands</option>
					<option value="CO">Colombia</option>
					<option value="KM">Comoros</option>
					<option value="CG">Congo</option>
					<option value="CD">Congo, the Democratic Republic of the</option>
					<option value="CK">Cook Islands</option>
					<option value="CR">Costa Rica</option>
					<option value="CI">Cote d'Ivoire</option>
					<option value="HR">Croatia (Hrvatska)</option>
					<option value="CU">Cuba</option>
					<option value="CY">Cyprus</option>
					<option value="CZ">Czech Republic</option>
					<option value="DK">Denmark</option>
					<option value="DJ">Djibouti</option>
					<option value="DM">Dominica</option>
					<option value="DO">Dominican Republic</option>
					<option value="EC">Ecuador</option>
					<option value="EG">Egypt</option>
					<option value="SV">El Salvador</option>
					<option value="GQ">Equatorial Guinea</option>
					<option value="ER">Eritrea</option>
					<option value="EE">Estonia</option>
					<option value="ET">Ethiopia</option>
					<option value="FK">Falkland Islands (Malvinas)</option>
					<option value="FO">Faroe Islands</option>
					<option value="FJ">Fiji</option>
					<option value="FI">Finland</option>
					<option value="FR">France</option>
					<option value="GF">French Guiana</option>
					<option value="PF">French Polynesia</option>
					<option value="TF">French Southern Territories</option>
					<option value="GA">Gabon</option>
					<option value="GM">Gambia</option>
					<option value="GE">Georgia</option>
					<option value="DE">Germany</option>
					<option value="GH">Ghana</option>
					<option value="GI">Gibraltar</option>
					<option value="GR">Greece</option>
					<option value="GL">Greenland</option>
					<option value="GD">Grenada</option>
					<option value="GP">Guadeloupe</option>
					<option value="GU">Guam</option>
					<option value="GT">Guatemala</option>
					<option value="GN">Guinea</option>
					<option value="GW">Guinea-Bissau</option>
					<option value="GY">Guyana</option>
					<option value="HT">Haiti</option>
					<option value="HM">Heard and Mc Donald Islands</option>
					<option value="VA">Holy See (Vatican City State)</option>
					<option value="HN">Honduras</option>
					<option value="HK">Hong Kong</option>
					<option value="HU">Hungary</option>
					<option value="IS">Iceland</option>
					<option value="IN">India</option>
					<option value="ID">Indonesia</option>
					<option value="IR">Iran (Islamic Republic of)</option>
					<option value="IQ">Iraq</option>
					<option value="IE">Ireland</option>
					<option value="IL">Israel</option>
					<option value="IT">Italy</option>
					<option value="JM">Jamaica</option>
					<option value="JP">Japan</option>
					<option value="JO">Jordan</option>
					<option value="KZ">Kazakhstan</option>
					<option value="KE">Kenya</option>
					<option value="KI">Kiribati</option>
					<option value="KP">Korea, Democratic People's Republic of</option>
					<option value="KR">Korea, Republic of</option>
					<option value="KW">Kuwait</option>
					<option value="KG">Kyrgyzstan</option>
					<option value="LA">Lao People's Democratic Republic</option>
					<option value="LV">Latvia</option>
					<option value="LB">Lebanon</option>
					<option value="LS">Lesotho</option>
					<option value="LR">Liberia</option>
					<option value="LY">Libyan Arab Jamahiriya</option>
					<option value="LI">Liechtenstein</option>
					<option value="LT">Lithuania</option>
					<option value="LU">Luxembourg</option>
					<option value="MO">Macau</option>
					<option value="MK">Macedonia, The Former Yugoslav Republic of</option>
					<option value="MG">Madagascar</option>
					<option value="MW">Malawi</option>
					<option value="MY">Malaysia</option>
					<option value="MV">Maldives</option>
					<option value="ML">Mali</option>
					<option value="MT">Malta</option>
					<option value="MH">Marshall Islands</option>
					<option value="MQ">Martinique</option>
					<option value="MR">Mauritania</option>
					<option value="MU">Mauritius</option>
					<option value="YT">Mayotte</option>
					<option value="MX">Mexico</option>
					<option value="FM">Micronesia, Federated States of</option>
					<option value="MD">Moldova, Republic of</option>
					<option value="MC">Monaco</option>
					<option value="MN">Mongolia</option>
					<option value="MS">Montserrat</option>
					<option value="MA">Morocco</option>
					<option value="MZ">Mozambique</option>
					<option value="MM">Myanmar</option>
					<option value="NA">Namibia</option>
					<option value="NR">Nauru</option>
					<option value="NP">Nepal</option>
					<option value="NL">Netherlands</option>
					<option value="AN">Netherlands Antilles</option>
					<option value="NC">New Caledonia</option>
					<option value="NZ">New Zealand</option>
					<option value="NI">Nicaragua</option>
					<option value="NE">Niger</option>
					<option value="NG">Nigeria</option>
					<option value="NU">Niue</option>
					<option value="NF">Norfolk Island</option>
					<option value="MP">Northern Mariana Islands</option>
					<option value="NO">Norway</option>
					<option value="OM">Oman</option>
					<option value="PK">Pakistan</option>
					<option value="PW">Palau</option>
					<option value="PA">Panama</option>
					<option value="PG">Papua New Guinea</option>
					<option value="PY">Paraguay</option>
					<option value="PE">Peru</option>
					<option value="PH">Philippines</option>
					<option value="PN">Pitcairn</option>
					<option value="PL">Poland</option>
					<option value="PT">Portugal</option>
					<option value="PR">Puerto Rico</option>
					<option value="QA">Qatar</option>
					<option value="RE">Reunion</option>
					<option value="RO">Romania</option>
					<option value="RU">Russian Federation</option>
					<option value="RW">Rwanda</option>
					<option value="KN">Saint Kitts and Nevis</option>
					<option value="LC">Saint LUCIA</option>
					<option value="VC">Saint Vincent and the Grenadines</option>
					<option value="WS">Samoa</option>
					<option value="SM">San Marino</option>
					<option value="ST">Sao Tome and Principe</option>
					<option value="SA">Saudi Arabia</option>
					<option value="SN">Senegal</option>
					<option value="SC">Seychelles</option>
					<option value="SL">Sierra Leone</option>
					<option value="SG">Singapore</option>
					<option value="SK">Slovakia (Slovak Republic)</option>
					<option value="SI">Slovenia</option>
					<option value="SB">Solomon Islands</option>
					<option value="SO">Somalia</option>
					<option value="ZA">South Africa</option>
					<option value="GS">South Georgia and the South Sandwich Islands</option>
					<option value="ES">Spain</option>
					<option value="LK">Sri Lanka</option>
					<option value="SH">St. Helena</option>
					<option value="PM">St. Pierre and Miquelon</option>
					<option value="SD">Sudan</option>
					<option value="SR">Suriname</option>
					<option value="SJ">Svalbard and Jan Mayen Islands</option>
					<option value="SZ">Swaziland</option>
					<option value="SE">Sweden</option>
					<option value="CH">Switzerland</option>
					<option value="SY">Syrian Arab Republic</option>
					<option value="TW">Taiwan, Province of China</option>
					<option value="TJ">Tajikistan</option>
					<option value="TZ">Tanzania, United Republic of</option>
					<option value="TH">Thailand</option>
					<option value="TG">Togo</option>
					<option value="TK">Tokelau</option>
					<option value="TO">Tonga</option>
					<option value="TT">Trinidad and Tobago</option>
					<option value="TN">Tunisia</option>
					<option value="TR">Turkey</option>
					<option value="TM">Turkmenistan</option>
					<option value="TC">Turks and Caicos Islands</option>
					<option value="TV">Tuvalu</option>
					<option value="UG">Uganda</option>
					<option value="UA">Ukraine</option>
					<option value="AE">United Arab Emirates</option>
					<option value="GB">United Kingdom</option>
					<option value="US">United States</option>
					<option value="UM">United States Minor Outlying Islands</option>
					<option value="UY">Uruguay</option>
					<option value="UZ">Uzbekistan</option>
					<option value="VU">Vanuatu</option>
					<option value="VE">Venezuela</option>
					<option value="VN">Viet Nam</option>
					<option value="VG">Virgin Islands (British)</option>
					<option value="VI">Virgin Islands (U.S.)</option>
					<option value="WF">Wallis and Futuna Islands</option>
					<option value="EH">Western Sahara</option>
					<option value="YE">Yemen</option>
					<option value="ZM">Zambia</option>
					<option value="ZW">Zimbabwe</option>
				</select>
			</div>
			<p>Enter your account details below:</p>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="Password" name="password"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>
				<div class="controls">
					<div class="input-icon">
						<i class="fa fa-check"></i>
						<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Re-type Your Password" name="rpassword"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>
				<input type="checkbox" name="tnc"/> I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>
				</label>  
				<div id="register_tnc_error"></div>
			</div>
			<div class="form-actions">
				<button id="register-back-btn" type="button" class="btn">
				<i class="m-icon-swapleft"></i>  Back
				</button>
				<button type="submit" id="register-submit-btn" class="btn blue pull-right">
				Sign Up <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>
		</form>
		<!-- END REGISTRATION FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<c:if test="${userLogout ne 'userLogout'}">
	<div class="copyright">
		<!-- 2015-2016 &copy; Administrative Web Portal. Owned by BCITS -->
		2015-2016 &copy; BsmartCloud Owned by BCITS
	</div>
	</c:if>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->   
	
	<script src="<c:url value='/resources/assets/plugins/jquery-1.10.2.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/jquery-migrate-1.2.1.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/bootstrap/js/bootstrap.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js' />" type="text/javascript" ></script>
	<script src="<c:url value='/resources/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/jquery.blockui.min.js' />" type="text/javascript"></script>  
	<script src="<c:url value='/resources/assets/plugins/jquery.cookie.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/uniform/jquery.uniform.min.js' />" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value='/resources/assets/plugins/jquery-validation/dist/jquery.validate.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/plugins/backstretch/jquery.backstretch.min.js' />" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/resources/assets/plugins/select2/select2.min.js' />"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<c:url value='/resources/assets/scripts/app.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/assets/scripts/login-soft.js' />" type="text/javascript"></script>      
	<script src="<c:url value='/resources/assets/plugins/bootbox/bootbox.min.js'/>" type="text/javascript" ></script>
	<!-- END PAGE LEVEL SCRIPTS --> 
	<script>
		jQuery(document).ready(function() {     
		  App.init();
		  Login.init();
		});
	</script>
	<!-- <script>
	function validateLogin()
   {	
		 if(document.getElementById('nameInput').value=='')
			{
			 bootbox.alert('Please Enter userName');
			   return false;
			}
		 
		 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        
         if (reg.test(document.getElementById('nameInput').value) == false) 
         {
             alert('Invalid Email Address');
             return (false);
         }
		 if(document.getElementById('passwordInput').value=='')
			{
			 bootbox.alert('Please Enter password');
			   return false;
			}
		
		
	}
	</script> -->
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>