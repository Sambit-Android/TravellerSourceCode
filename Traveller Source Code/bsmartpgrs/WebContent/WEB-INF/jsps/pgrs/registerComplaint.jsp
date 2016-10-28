<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<div id="content">

<c:if test = "${not empty helpDeskResult}"> 			        
		        <script>		        
		            var helpDeskResult = "${helpDeskResult}";
		            alert(helpDeskResult);
		        </script>
		    <c:remove var="helpDeskResult" scope="session"/>
		 </c:if>

	<section id="widget-grid" class="">
	 <jsp:include page="../genericsRegestration.jsp"/>
<%-- 	<div class="row" id="registerComplaintId">
	
		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<h2>Register Complaint</h2>				
					
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
						
						<form:form id="register-form" action="./createTicket" method="POST" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">

							<fieldset>
								<div class="row">
								<input type="hidden" name="selectedDocket" id="selectedDocket">
								<input type="hidden" name="actionType" id="actionType" value="Save">
							     <section class="col col-2" id="PhoneSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Phone" id="PhoneId"></form:radiobutton>
											<i></i>Phone</label>
								</section>
								
								<section class="col col-2" id="SmsSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Sms" id="SmsId"></form:radiobutton>
											<i></i>SMS</label>
									
								</section>
								<section class="col col-2" id="EmailSection">
									<div class="inline-group">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Email" id="EmailId"></form:radiobutton>
											<i></i>Email</label>
										
									</div>
								</section>
								<section class="col col-3" id="FacebookSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Facebook" id="FacebookId"></form:radiobutton>
											<i></i>Facebook</label>
								</section>
								<section class="col col-2" id="HandWrittenSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Hand Written" id="Hand WrittenId"></form:radiobutton>
											<i></i>Hand Written</label>
								</section>
								
								</div>
								
								<div class="row">
									<section class="col col-3">
										<label class="select">
											<form:select path="categoryId" id="categoryId" name="categoryId">
												<option value="0" selected="" disabled="">Select Category</option>
  												<c:forEach items="${categoryList}" var="categories">
												<form:option value="${categories.categoryId}">${categories.categoryName}</form:option>
												</c:forEach>
										 </form:select><i></i>
										 </label>
											</section>
									
									<section class="col col-3">
										<label class="select">
											<form:select path="subCategoryId" name="subCategoryId" id="subCategoryId">
												<option value="0" selected="" disabled="">Select Sub Category</option>
											</form:select> <i></i> </label>
									</section>
									
									<section class="col col-3">
										<label class="select">
											<form:select path="divisionSiteCode" id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
  												<c:forEach items="${divisionsList}" var="divisions">
												<form:option value="${divisions.divisionSiteCode}">${divisions.divisionName}</form:option>
												</c:forEach>
										    </form:select><i></i></label>
									</section>
									
									<section class="col col-3">
										<label class="select">
											<form:select path="siteCode" name="siteCode" id="siteCode">
												<option value="0" selected="" disabled="">Select Section</option>
											</form:select> <i></i></label>
									</section>
								</div>	
								<div class="row">
									<section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-tachometer"></i>
											<form:input path="rrNumber" name="rrNumber" placeholder="RR Number"></form:input>
											<b class="tooltip tooltip-top-right">Enter RR Number</b> 
										</label>
									</section>
									<section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-phone"></i>
											<form:input path="consumerMobileNo" name="consumerMobileNo" id="consumerMobileNo" placeholder="Mobile Number" data-mask="9999999999"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Mobile No</b> 
										</label>
									</section>
																	
								<section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<form:input path="consumerName" id="consumerName" name="consumerName" placeholder="Consumer name"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Name</b> 
										</label>
								</section>
								
								<section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
											<form:input type="email" path="consumerEmailId" name="consumerEmailId" placeholder="E-mail"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Email Id</b> 
										</label>
									</section>
								</div>
								
								<div class="row">
								<section class="col col-3">
									<label class="textarea"> 										
										<form:textarea rows="2" path="address" name="address" placeholder="Address"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Address</b> 
									</label>
								</section>
								
								<section class="col col-3">
									<label class="textarea"> 										
										<form:textarea rows="2" path="landMark" name="landMark" placeholder="Land Mark"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Land Mark</b> 
									</label>
								</section>
								
								<section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-phone"></i>
											<input type="tel" name="alternativeMobileNo" placeholder="Alternative Number" data-mask="9999999999">
											<b class="tooltip tooltip-top-right">Enter Alternative Mobile No</b> 
										</label>
								</section> 
								
								<!-- <section class="col col-3">
										<div class="input input-file">
											<span class="button"><input type="file" id="file2" onchange="this.parentNode.nextSibling.value = this.value" placeholder="Select File">Browse</span><input type="text" readonly>
										</div>
									<div class="note note-error">File size must be less than 3Mb.</div>
									</section> -->
								</div>

								<section>
									<label class="textarea"> 										
										<form:textarea rows="2" name="docketSummary" path="docketSummary" placeholder="Docket Summary"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Docket Summary</b> 
									</label>
								</section>
							</fieldset>
							
					<footer style="height: 30px">
								<button type="submit" class="btn btn-primary">
									Register
								</button>
							</footer>
						</form:form>

					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->
		</article>
		<!-- END COL -->

	</div> --%>
	</section>
</div>		

<script>
$(document).ready(function(){
	$('#PhoneId').prop('checked', true);
	$('#registerComplaintId').show();
	$('#invalidButtonId').hide();
	$("#actionType").val("Save");
});


</script>


<!-- <script type="text/javascript">


	$('select[id*=categoryId]').change(function() {
		var categoryId = $("#categoryId").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubCategories/" + categoryId,
			dataType : "json",
			success : function(data) {
				($.map(data, function(item) {
					var newOption = $('<option>'); 
					newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
					$('#subCategoryId').append(newOption);
				}));
			}
		});
	});
	
	$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#siteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Section");
                $('#siteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.subDivisionName);
					$('#siteCode').append(newOption);
				}));
			}
		});
	});

	function openRegisterForm() {
		
		$('#liveTicketDiv').hide();
		$("#actionType").val("Save");
		$('#registerComplaintId').show();
		$('#PhoneId').prop('checked', true);
	}

	$(document).ready(function() {

						pageSetUp();
						
						$('#PhoneId').prop('checked', true);

						jQuery.validator.addMethod("lettersonly", function(value, element) {
							  return this.optional(element) || /^[a-z ]+$/i.test(value);
							}, "Letters only please");
						 
							$('#register-form')
							.validate(
									{
										// Rules for form validation
										rules : {
											categoryId : {
												required : true
											},
											subCategoryId : {
												required : true
											},
											divisionSiteCode : {
												required : true
											},
											siteCode : {
												required : true
											},
											rrNumber : {
												/* required : true, */
												maxlength : 10
											},
											consumerMobileNo : {
												required : true
											},
											consumerName : {
												required : true,
												maxlength : 50,
												lettersonly : true
											},
											consumerEmailId : {
												email : true,
												maxlength : 50
											},
											address : {
												maxlength : 250
											},
											landMark : {
												maxlength : 100
											}, 
											docketSummary : {
												required : true,
												maxlength : 500
											}
										},

										// Messages for form validation
										messages : {
											categoryId : {
												required : 'Please select category'
											},
											subCategoryId : {
												required : 'Please select sub category'
											},
											divisionSiteCode : {
												required : 'Please select division',
											},
											siteCode : {
												required : 'Please select section'
											},
											rrNumber : {
												/* required : 'Please enter RR number', */
												maxlength : 'Max length is 10'
											},
											consumerMobileNo : {
												required : 'Please enter mobile number'
											},
											consumerName : {
												required : 'Please enter consumer name',
												maxlength : 'Max length is 50 characters',
												lettersonly : 'Please enter only alphabetical characters'
											},
											consumerEmailId : {
												maxlength : 'Max length is 50 characters'
											},
											address : {
												maxlength : 'Max length is 250 characters'
											},
											landMark : {
												maxlength : 'Max length is 100 characters'
											}, 
											docketSummary : {
												required : 'Please enter docket summary',
												maxlength : 'Max length is 500 characters'
											}
										},

										// Do not change code below
										errorPlacement : function(error,
												element) {
											error.insertAfter(element
													.parent());
										}
									});



					});
</script> -->