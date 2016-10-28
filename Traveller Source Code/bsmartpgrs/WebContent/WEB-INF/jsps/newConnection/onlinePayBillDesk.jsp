<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>



<html>
<body onload="submitPayuForm();">
<section id="widget-grid" class="">
<div id="content">
  <div class="row">

<div id="paymentid">
<article class="col-sm-12 col-md-12 col-lg-8">
			
						<div class="jarviswidget jarviswidget-color-greenDark" id="widpayARFOnline"
									data-widget-editbutton="false" data-widget-colorbutton="false">
				
									<header>
										<span class="widget-icon"> <i class="fa fa-check"></i>
										</span>
										<h2>Online Payment</h2>
									</header>
								
										<form action="./bdpaymentOnlineNC" method="POSt" 
											id="online-payment" class="smart-form">

											<fieldset>
											
											<input name="sitecode"
														id="sitecode" readonly="readonly" hidden="true">

											 <div class="row">
												
												<section class="col col-6">
													<label class="label">Application ID&nbsp;<font color="red">*</font></label> <label
														class="select"> <select id="appId" name="appId" onchange="getDataById(this.value)">
															<option value="0" selected="" disabled="">Select
																AppId</option>
															<c:forEach items="${appIdsList}" var="appIds">
																<option value="${appIds.applicationId}">${appIds.applicationId}</option>
															</c:forEach>
													</select><i></i></label>
												</section>
												
												<section class="col col-6">
													<label class="label">Name</label><label class="input"><i
														class="icon-prepend fa fa-user" id="usericon1"></i> <input
														name="firstname" placeholder="Enter First Name"
														id="firstname"> </label>
												</section>
												</div>

												<div class="row">
												<section class="col col-6">
													<label class="label">Mobile No</label><label class="input"><i
														class="icon-prepend fa fa-phone"></i> <input name="phone"
														id="phone" placeholder="Enter Mobile no" readonly="readonly"></input> </label>
												</section>
												
												<section class="col col-6">
													<label class="label">Amount</label> <label class="input"><i
														class="icon-prepend fa fa-rupee"></i><input name="amount"
														placeholder="Amount" id="amount"></input> </label>
												</section>
												
								                </div>

												<div class="row">
												
												<section class="col col-6">
													<label class="label">Tariff</label> <label
														class="input"> <input name="tariff"
														id="tariff" readonly="readonly"></input>
													</label>
												</section>
												
												<section class="col col-6">
													<label class="label">Pay Towards</label>
															<label class="input"><select
																class="form-control" id="productinfo" name="productinfo">
																	<option value="" selected="" disabled="">Select</option>
																	<c:forEach items="${towardsList}" var="sections">
																	    <option value="${sections.description}">${sections.description}</option>
																	</c:forEach>
				
															</select>
															</label>
												</section>
												
												</div>

												

											</fieldset>
											
											<footer>

												<button type="submit" class="btn btn-primary" onclick="return validate();">
													<strong>Proceed To Pay</strong>
												</button>
											</footer>
										</form>
								</div>

					
							


</article></div>
	
		</div>
		</div>
		</section>
		
		
		
	<script>
	
	function validate(){
		if(confirm("Are you sure to do for the Payment")){
			
		} else{
	        return false;
	    }
	}
	
	function getDataById(applicationId){
		$.ajax({
			url : "./NCMS/getDataByApplicationId",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				applicationId : applicationId,
			},
			success : function(response) {
				var data=response[0];
				
				$('#firstname').val(data.name);
				$('#phone').val(data.mobileno);
				$('#tariff').val(data.tariff);
				$('#sitecode').val(data.sitecode);
				
				
			}
		});
		
	}
	
	
	$(document).ready(function() {
     
		  $.validator.addMethod("regex", function(value, element, regexpr) {
				return regexpr.test(value);
			}, "");
		   $.validator.addMethod('minStrict', function(value, el, param) {
				return value > param;
			});
		
		$("#online-payment").validate({

		// Rules for form validation
		rules : {
			appId : {
				required : true
			},
			amount : {
				required : true,
				digits:true,
				minStrict:0
				
			},
			productinfo : {
				required : true
				
			},
			firstname : {
				required : true,
				maxlength : 50,
				regex : /^[a-zA-Z .]*$/
			},
			
			
		},

		// Messages for form validation
			messages : {
				
				appId : {
					required : 'Select Application Id to which payment to be done',
				},
				amount : {
					required : 'please enter amount',
					minStrict:'Amount should be > 0',
					
				},
				productinfo : {
					required : 'select Pay Towards',
					
				},
				firstname : {
					required : 'enter name',
					regex:'Enter Valid Name'
				},
				
			},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	});

	
		
		</script>
		</body>
		
</html>