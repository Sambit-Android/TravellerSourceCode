<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>


<section id="widget-grid" class="">
<div id="content">


<c:if test="${not empty notificationSentSuccess}">
						<script>
							var notificationSentSuccess = "${notificationSentSuccess}";
							alert(notificationSentSuccess);
						</script>
						
			   <c:remove var="notificationSentSuccess" scope="session" />
</c:if>
		   
		   
		   
 <div class="row">
 


 <article class="col-sm-12 ">
			<!-- Widget ID (each widget will need unique ID)-->
	
			<div class="jarviswidget jarviswidget-color-greenLight jarviswidget-sortable" id="wwid12" data-widget-editbutton="false" data-widget-custombutton="false">
				
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>Customer Notification</h2>				
					
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
						
						<form action="" method="post" id="customer-notification" class="smart-form">
							<input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${applicationid}"></input>
							<input type="hidden" id="customerLogin" autocomplete="off" placeholder="" name="customerLogin" value="${customerLogin}"></input>
							<fieldset>
							
							
							
							<div class="row">
									
								<section class="col col-2">
									<label class="label">Application ID</label>
									<label class="input"><input class="form-control" name="applicationNo" type="text" style="width: 150px;" placeholder="Enter Application ID." id="applicationNo">
									</label>
								</section>
								
						      <section class="col col-2">
									<button type="button" onclick="searchApplicationNo()" class="btn btn-primary" style="height: 32px;width: 150px;margin-top: 24px;margin-left: -16px;">Search</button>
							  </section>
							
							</div>
							
							<div class="row">
							
							
							  
								
								
								<section class="col col-4">
									<label class="label">Name</label><label
											class="input"><i class="icon-prepend fa fa-user" id="usericon"></i> 
											<input type="text" name="customerName" id="customerName" placeholder="Name"></input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">Father Name</label><label
											class="input"><i class="icon-prepend fa fa-user" id="usericon"></i> 
											<input type="text" name="fatherName" id="fatherName" placeholder="Father Name"></input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">Contact Number</label><label
											class="input"><i class="icon-prepend fa fa-phone"></i>
											<input type="text" name="customerContactNo" id="customerContactNo" placeholder="Mobile No" ></input>
										</label>
								</section>
								</div>
								
								<div class="row">
								
								
							
								<section class="col col-4">
									<label class="label">Email Id</label><label
											class="input"><i class="icon-prepend fa fa-envelope-o"></i>
											<input type="email" name="customerEmailId" id="customerEmailId" placeholder="Email Id" ></input>
										</label>
								</section>
								
								
								
								<section class="col col-4">
									<label class="label">Load</label><label
											class="input">
											<input type="text" name="load" id="load" placeholder="Load" ></input>
										</label>
								</section>
								
								<section class="col col-4">
									<label class="label">Tariff</label><label
											class="input">
											<input type="text" name="tariff" id="tariff" placeholder="Tariff" ></input>
										</label>
								</section>
								
								
							
								
							</div>	
							
							
							 <div class="row">
									


									<section class="col col-2">
										<label class="checkbox"><input type="checkbox" 	name="smsId" id="smsId"  value="1" /><i></i>SMS</label>
									</section>
									
									
									<section class="col col-2">
										<label class="checkbox"><input type="checkbox"	name="emailId" id="emailId" value="1" /><i></i>E-Mail</label>
									</section>
									
									<section class="col col-4">
									<label class="label">Notification</label><label
											class="input"><i class="icon-prepend fa fa-envelope-o"></i>
											<input type="text" name="notificationSub" id="notificationSub" placeholder="Subject" ></input>
										</label>
								     </section>
									
									<section class="col col-4">
									<label class="label">Notification Details</label>
									
									<label class="textarea"> 										
										<textarea rows="1" name="notificationInfo" placeholder="Status of Application" id="notificationInfo"></textarea> 
									</label>
								     </section>

									
									
						    </div>
							
							   <footer>
							   <button type="submit" class="btn btn-primary"  onclick="return sendNotification();"><strong>Send Notification</strong></button>
								
							    </footer>
						
							
							</fieldset>

							
							
						</form>						
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			
			
			
			
										


		</article> 
		</div></div>
		</section>
		
		
<script>


$(document).ready(function() {
	pageSetUp();
	var customerName= "${customerName}";
	var fatherName= "${fatherName}";
	var customerContactNo= "${customerContactNo}";
	var customerEmailId= "${customerEmailId}";
	var loadkW= "${loadkW}";
	var tariff= "${tariff}";
	
	
	$('#customerName').val(customerName).attr("readonly", "readonly");
	$('#fatherName').val(fatherName).attr("readonly", "readonly");
	$('#customerContactNo').val(customerContactNo).attr("readonly", "readonly");
	$('#customerEmailId').val(customerEmailId);
	$('#load').val(loadkW).attr("readonly", "readonly");
	$('#tariff').val(tariff).attr("readonly", "readonly");
	

});


	
function searchApplicationNo()
{
	 var applicationNo=document.getElementById("applicationNo").value;
	 if(applicationNo==""){
		 alert("Enter Application ID");
	 }
	 else{
		 window.location.href="./getDetailsByApplicationId?appId="+applicationNo; 
	 }
	
	
}
	
	
	
	
function sendNotification() {
	
	var smsStatus=document.getElementById('smsId').checked;
	var emailStatus=document.getElementById('emailId').checked;
	
	var SMS="";
	var emailType="";
	
	 if (!smsStatus) {
		   
		 SMS="smsempty";
	        
	 } 
	
	 if (!emailStatus) {
		   
		 emailType="emptyEmail";
	        
	 } 
	 
	 if (SMS=="smsempty" && emailType=="emptyEmail") {
		   
		 alert("Please Select SMS or E-Mail");
	     return false;
	        
	 } 
	 
	 if (emailStatus) {
		 
		 var mailId=document.getElementById("customerEmailId").value;
		 if(mailId==""){
			alert("Please enter mailid");
			return false;
		 }
	        
	 } 
	 
	 
	$("#customer-notification").attr("action","./customerNotification/create").submit();
}


	
	
	
	
	
	$(document).ready(function() {
		
		 
	      $("#customer-notification").validate({

			// Rules for form validation
			rules : {
				customerName : {
					required : true,
					
					
				},
				fatherName : {
					required : true,
					
					
				},
				customerContactNo : {
					required : true,
					
					
				},
				notificationSub : {
					required : true,
					maxlength : 100
					
				},
				notificationInfo : {
					required : true,
					maxlength : 1000
					
				},
				
				
				
			},

			// Messages for form validation
			messages : {
				
				notificationSub : {
					required : 'Please enter notification subject'
				},
				notificationInfo : {
					required : 'Please enter notification',
				},
				
				
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

		
	     
		}); 
	
	
	
	
	
	
	
	      
	</script>