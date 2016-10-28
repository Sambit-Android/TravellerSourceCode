<%@include file="/common/taglibs.jsp"%>

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/font-awesome.min.css">

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-skins.min.css">

<!-- SmartAdmin RTL Support is under construction
	 This RTL CSS will be released in version 1.5 -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-rtl.min.css">

<!-- We recommend you use "your_style.css" to override SmartAdmin
     specific styles this will also ensure you retrain your customization with each SmartAdmin update. -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/your_style.css">

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/demo.min.css">

<!-- FAVICONS -->
<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">

<!-- GOOGLE FONT -->
<!-- <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700"> -->

<!-- Specifying a Webpage Icon for Web Clip 
	 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon" href="./resources/img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76" href="./resources/img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120" href="./resources/img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152" href="./resources/img/splash/touch-icon-ipad-retina.png">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image" href="./resources/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image" href="./resources/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image" href="./resources/img/splash/iphone.png" media="screen and (max-device-width: 320px)">

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script data-pace-options='{ "restartOnRequestAfter": true }' src="./resources/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="./resources/js/libs/jquery-2.0.2.min.js"></script>


<script src="./resources/js/libs/jquery-ui-1.10.3.min.js"></script>


<!-- IMPORTANT: APP CONFIG -->
<script src="./resources/js/app.config.js"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
<script src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> 

<!-- BOOTSTRAP JS -->
<script src="./resources/js/bootstrap/bootstrap.min.js"></script>

<!-- CUSTOM NOTIFICATION -->
<script src="./resources/js/notification/SmartNotification.min.js"></script>

<!-- JARVIS WIDGETS -->
<script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>

<!-- EASY PIE CHARTS -->
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>



<!-- Demo purpose only -->
<!-- ------------------------------------------------------------------------------------------------------------------------------ -->

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- Demo purpose only -->
<!-- <script src="./resources/js/demo.min.js"></script> -->

<script type="text/javascript" src="./resources/js/app.min.js"></script>


<!-- Voice command : plugin -->
<script src="./resources/js/speech/voicecommand.min.js"></script>
		
<!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>

<!-- Vector Maps Plugin: Vectormap engine, Vectormap language -->
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- Full Calendar -->
<script src="./resources/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 
<script src="./resources/js/exportToExcel.js"></script>
<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>
<script src="./resources/js/bootbox.min.js"></script>

<!-- <div id="content"> -->

<div id="main" role="main">

<c:if test = "${not empty helpDeskSaveResult}"> 			        
		        <script>		        
		            var helpDeskSaveResult = "${helpDeskSaveResult}";
		            bootbox.alert(helpDeskSaveResult);
		        </script>
		    <c:remove var="helpDeskSaveResult" scope="session"/>
		 </c:if>

	<div id="content">

	<div class="row" id="registerComplaintId">
	
		<!-- NEW COL START -->
		<article class="col-sm-12 col-md-12 col-lg-12">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="widdsads123" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<h2><span id="spanId">Register Ticket</span></h2>				
				</header>
				<div>
					<div class="widget-body no-padding">
						
						<form:form id="register-form" action="./createTicketTrm" method="POST" enctype="multipart/form-data" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">

							<fieldset>
								<div class="row">
								<input type="hidden" name="selectedDocket" id="selectedDocket">
								<input type="hidden" name="actionType" id="actionType">
									<input type="hidden" name="docSourceUpdate" id="docSourceUpdate">
							     <%-- <section class="col col-3" id="PhoneSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Phone" id="PhoneId"></form:radiobutton>
											<i></i>Phone</label>
								</section> --%>
								<%-- <section class="col col-3" id="EmailSection">
									<div class="inline-group">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Email" id="EmailId"></form:radiobutton>
											<i></i>Email</label>
										
									</div>
								</section> --%>
								<%-- <section class="col col-3" id="FacebookSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Facebook" id="FacebookId"></form:radiobutton>
											<i></i>Facebook</label>
								</section> --%>
								<%-- <section class="col col-3" id="HandWrittenSection">
										<label class="radio">
											<form:radiobutton path="docketSource" value="Hand Written" id="Hand WrittenId"></form:radiobutton>
											<i></i>Hand Written</label>
								</section> --%>
								
								</div>
								
								<div class="row">
								
								<section hidden="true" class="col col-3">
										<label class="label">&nbsp;Section&nbsp;<font color="red">*</font></label>
											<form:input path="siteCode" id="siteCode" name="siteCode"/>
									</section>
									
									<section hidden="true" class="col col-3">
										<label class="label">&nbsp;Consumer&nbsp;Name&nbsp;<font color="red">*</font></label>
										<label class="input"> <i class="icon-prepend fa fa-user"></i>
											<form:input path="consumerName" id="consumerName" name="consumerName" placeholder="Consumer name"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Name</b> 
										</label>
									</section>
									
									<section hidden="true" class="col col-3">
										<label class="label">&nbsp;Mobile&nbsp;No&nbsp;<font color="red">*</font></label>
										<label class="input" id="testEmail"> <i class="icon-prepend fa fa-phone"></i>
											<form:input path="consumerMobileNo" name="consumerMobileNo" id="consumerMobileNo" placeholder="Mobile Number" data-mask="9999999999"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Mobile No</b> 
										</label>
									</section>
								
									<section hidden="true" class="col col-3">
									<label class="label">&nbsp;Email&nbsp;</label>
										<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
											<form:input type="email" path="consumerEmailId" name="consumerEmailId" placeholder="E-mail"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Email Id</b> 
										</label>
									</section>
																	
								</div>
								
								<div class="row">
								
									<%-- <section class="col col-3">
										<label class="label">&nbsp;Division&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="divisionSiteCode" id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
  												<c:forEach items="${divisionsList}" var="divisions">
												<form:option value="${divisions.divisionSiteCode}">${divisions.divisionName}</form:option>
												</c:forEach>
										    </form:select><i></i></label>
									</section>
									
									<section class="col col-3">
										<label class="label">&nbsp;Sub&nbsp;Division&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="subDivisionSiteCode" name="subDivisionSiteCode" id="subDivisionSiteCode">
												<option value="0" selected="" disabled="">Select Sub Division</option>
											</form:select> <i></i></label>
									</section>
									
									<section class="col col-3">
									<label class="label">&nbsp;Section&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="siteCode" name="siteCode" id="siteCode">
												<option value="0" selected="" disabled="">Select Section</option>
											</form:select> <i></i></label>
									</section> --%>
								
									<section class="col col-6">
									<label class="label">&nbsp;Category&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="categoryId" id="categoryId" name="categoryId">
												<option value="0" selected="" disabled="">Select Category</option>
  												<c:forEach items="${categoryList}" var="categories">
												<form:option value="${categories.categoryId}">&nbsp;${categories.categoryName}</form:option>
												</c:forEach>
										 </form:select><i></i>
									</label>
									</section>
									
									<section class="col col-6">
									<label class="label">&nbsp;Sub&nbsp;Category&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="subCategoryId" name="subCategoryId" id="subCategoryId">
												<option value="0" selected="" disabled="">&nbsp;&nbsp;&nbsp;Select Sub Category</option>
											</form:select> <i></i> </label>
									</section>

								</div>	
								
								<div class="row">
								
								<%-- <section class="col col-3">
								<label class="label">&nbsp;Email&nbsp;</label>
										<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
											<form:input type="email" path="consumerEmailId" name="consumerEmailId" placeholder="E-mail"></form:input>
											<b class="tooltip tooltip-top-right">Enter Consumer Email Id</b> 
										</label>
									</section>
								
								<section class="col col-3">
								<label class="label">&nbsp;Address</label>
									<label class="textarea"> 										
										<form:textarea rows="2" path="address" name="address" placeholder="Address"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Address</b> 
									</label>
								</section> --%>
								
								<%-- <section class="col col-3">
									<label class="label">&nbsp;Pole&nbsp;No./Land&nbsp;Mark&nbsp;</label>
									<label class="textarea"> 										
										<form:textarea rows="2" path="landMark" name="landMark" placeholder="Enter Pole No./Land Mark"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Pole No./Land Mark</b> 
									</label>
								</section> --%>
								
								<section class="col col-6">
									<label class="label">&nbsp;Ticket&nbsp;Description&nbsp;<font color="red">*</font></label>
									<label class="textarea"> 										
										<form:textarea rows="2" name="docketSummary" path="docketSummary" placeholder="Ticket Description"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Ticket Description</b> 
									</label>
								</section>
								
								<section class="col col-6">
									<label class="label">File Upload</label>
										<div class="input input-file">
											<span class="button"><input type="file" id="ticketDoc" name="ticketDoc" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" placeholder="Upload file" readonly="">
										</div>
								</section>
								
								<%-- <section class="col col-10" id="showRemarks">
									<label class="label">&nbsp;Remarks&nbsp;</label>
									<label class="textarea"> 										
										<form:textarea rows="2" name="remarks" path="remarks" placeholder="Enter Remarks" id="remarksSMS"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Remarks</b> 
									</label>
								</section> --%>
								
								<!-- <section class="col col-3">
										<label class="input"> <i class="icon-prepend fa fa-phone"></i>
											<input type="tel" name="alternativeMobileNo" placeholder="Alternative Number" data-mask="9999999999">
											<b class="tooltip tooltip-top-right">Enter Alternative Mobile No</b> 
										</label>
								</section>  -->
								
								<!-- <section class="col col-3">
										<div class="input input-file">
											<span class="button"><input type="file" id="file2" onchange="this.parentNode.nextSibling.value = this.value" placeholder="Select File">Browse</span><input type="text" readonly>
										</div>
									<div class="note note-error">File size must be less than 3Mb.</div>
									</section> -->
								</div>

								<%-- <section>
									<label class="textarea"> 										
										<form:textarea rows="2" name="docketSummary" path="docketSummary" placeholder="Docket Summary"></form:textarea> 
										<b class="tooltip tooltip-top-right">Enter Docket Summary</b> 
									</label>
								</section> --%>
							</fieldset>
							
					<footer style="height: 30px">
								<!-- <button type="button" id="invalidButtonId" onclick="invalidateComplaint()" class="btn btn-labeled btn-danger">
									Invalid
								</button> -->
								
								<button type="submit" class="btn btn-primary" id="rgStr">
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

	</div>
	
	
	
	
	</div>
	</div>
	
<!-- </div> -->




<!-----------------------------------------------Scripts----------------------------------------------------------------->


<script>
$(document).ready(function(){
	pageSetUp();
	
	$("#register-form").submit(function(){
		if ($('#register-form').valid()) {
			$('button[type=submit]').attr("disabled", "disabled");
		      $('a').unbind("click").click(function(e) {
		          e.preventDefault();
		          // or return false;
		      });
		}else{
			$('button[type=submit]').removeAttr('disabled');
		}
	    });
	
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
						docketSummary : {
							required : true,
							minlength : 10,
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
						docketSummary : {
							required : 'Ticket summary is required'
						},
					},

					// Do not change code below
					errorPlacement : function(error,
							element) {
						error.insertAfter(element
								.parent());
					}
				});
	
	
});

// OnChange Drop Down For Category

$('select[id*=categoryId]').change(function() {
	var categoryId = $("#categoryId").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSubCategories/" + categoryId,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#subCategoryId').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select SubCategory");
            $('#subCategoryId').append(defaultOption);
			($.map(data, function(item) {
				var newOption = $('<option>'); 
				newOption.attr('value', item.subCategoryId).text('   '+item.subCategoryName);
				$('#subCategoryId').append(newOption);
			}));
		}
	});
});


// onchange Drop Down For Division

$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subDivisionSiteCode').empty(newOption);
                var defaultOption = $('<option disabled="" selected="">');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#subDivisionSiteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.subId).text(item.subDivisionName);
					$('#subDivisionSiteCode').append(newOption);
				}));
			}
		});
	});
	
$('select[id*=subDivisionSiteCode]').change(function() {
	var subDivisionSiteCode = $("#subDivisionSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSections/" + subDivisionSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#siteCode').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select Section");
            $('#siteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.sectionName);
				$('#siteCode').append(newOption);
			}));
		}
	});
});
	
var name = "";	
var address = "";
function loadRRNumberDetails() {
	
	var sitCode=$('#siteCode').val();
	if(sitCode==null)
		{
		  alert("Please Select Section");
		  $('#rrNumberReg').val("");
		  return false;
		}
	var rrNum=$('#rrNumberReg').val();
	var tableData="";
	
	$.ajax({
		type : "POST",
		url : "./helpDesk/loadDetailsBasedOnRRNum/"+sitCode+"/"+rrNum ,
		dataType : "json",
		success : function(response) {
			var tabl="";
			if(response.length==0)
				{
				$('#rrNumBasedDetailsNew').hide();
				  alert("RR-Number Not Found");
				 $('#rrNumberReg').val(''); 
				  return false;
				}
			else
				{
				scrollFunction();
				$('#rrNumBasedDetailsNew').show();
			for(var i=0;i<response.length;i++)
				{
				 var obj = response[i];
				 name = obj.name;
				 address = obj.address;
				 tableData+="<tr>"
					  +"<td>"+rrNum+"</td>"
				      +"<td>"+obj.name+"</td>"
				      +"<td>"+obj.mobile+"</td>"
				      +"<td>"+obj.address+"</td>"
				      +"<td>"+obj.tarriff+"</td>"
				      +"<td>"+obj.poleNum+"</td>"
				      +"<td><a class='btn btn-primary' onclick='copyRRNumberDetails("+obj.mobile+","+obj.poleNum+")'>Copy</a></td>"
				      +"</tr>";
				}
			
			 $('#getRrNumDetails').html(tableData);
			}
			  
		}
	});
	
}	

function scrollFunction(){
	$('html, body').animate({scrollTop: '+=150px'}, 800);
}

function copyRRNumberDetails(mobile,poleNum){
	
	$('#consumerName').val(name);
	$('#consumerMobileNo').val(mobile);
	$('#address').val(address);
	$('#landMark').val('Pole No : '+poleNum+';'); 
	
}

$("#ticketDoc").change(function(){
    readURL1(this);
});

function readURL1(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#ticketDoc').val("");
            
            return false;
    	}
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#ticketDoc').val("");
            return false;
        }
        } 
    
}

</script>