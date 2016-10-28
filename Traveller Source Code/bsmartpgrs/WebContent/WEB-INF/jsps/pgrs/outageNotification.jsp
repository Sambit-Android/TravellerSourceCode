<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>

<script src="./resources/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="./resources/js/plugin/maxlength/bootstrap-maxlength.min.js"></script>
<script src="./resources/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="./resources/js/plugin/clockpicker/clockpicker.min.js"></script>
<script src="./resources/js/plugin/bootstrap-tags/bootstrap-tagsinput.min.js"></script>
<script src="./resources/js/plugin/noUiSlider/jquery.nouislider.min.js"></script>
<script src="./resources/js/plugin/ion-slider/ion.rangeSlider.min.js"></script>
<script src="./resources/js/plugin/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="./resources/js/plugin/knob/jquery.knob.min.js"></script>
<script src="./resources/js/plugin/x-editable/moment.min.js"></script>
<script src="./resources/js/plugin/x-editable/jquery.mockjax.min.js"></script>
<script src="./resources/js/plugin/x-editable/x-editable.min.js"></script>
<script src="./resources/js/plugin/typeahead/typeahead.min.js"></script>
<script src="./resources/js/plugin/typeahead/typeaheadjs.min.js"></script>
		
<div id="content">


<c:if test = "${not empty outageNotificationSaveResult}"> 			        
		        <script>		        
		        var outageNotificationSaveResult = "${outageNotificationSaveResult}";
	            alert(outageNotificationSaveResult);
		        </script>
		    <c:remove var="outageNotificationSaveResult" scope="session"/>
		 </c:if>

<!-- widget grid -->
<section id="widget-grid" class="">
	<div class="row">

		<article class="col-sm-12 col-md-12 col-lg-6">
<div class="jarviswidget" id="wid-id-8" data-widget-editbutton="false" data-widget-custombutton="false">

				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>Outage Notification</h2>				
					
				</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<form:form action="./createOutageNotification" method="POST" id="outageForm" commandName="outageNotification" modelAttribute="outageNotification" class="smart-form">
							<fieldset>			
								<div class="row">
									<section class="col col-6">
										<label class="label">Circle&nbsp;<font color="red">*</font></label>
										<label class="select">
											<form:select path="circleSiteCode" id="circleSiteCode" name="circleSiteCode">
												<option value="0" selected="" disabled="">Select Circle</option>
												<option value="888888">ALL Circles</option>
  												<c:forEach items="${circleList}" var="circles">
												<form:option value="${circles.circleSiteCode}">${circles.circleName}</form:option>
												</c:forEach>
										    </form:select><i></i>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">Division</label>
										<label class="select">
											<form:select path="divisionSiteCode" id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
										    </form:select><i></i></label>
									</section>
								</div>
								
								<div class="row">
									<section class="col col-6">
										<label class="label">Sub Division</label>
										<label class="select">
											<form:select path="subDivisionSiteCode" name="subDivisionSiteCode" id="subDivisionSiteCode">
												<option value="0" selected="" disabled="">Select Sub Division</option>
											</form:select> <i></i></label>
									</section>
									<section class="col col-6">
										<label class="label">Section</label>
										<label class="select">
											<form:select path="sectionSiteCode" name="sectionSiteCode" id="sectionSiteCode">
												<option value="0" selected="" disabled="">Select Section</option>
											</form:select> <i></i></label>
									</section>
								</div>
								
								<div class="row">
								
									<section class="col col-4">
										<label class="label">Feeder&nbsp;Code&nbsp;</label>
										<label class="select" id="feederId">
											<form:select path="feederCode" name="feederCode" id="feederCode">
												<option value="0" selected="" disabled="">Select FeederCode</option>
											</form:select> <i></i></label>
									</section>
								
									<section class="col col-4">
									<label class="label">Expired Date&nbsp;<font color="red">*</font></label>
									<div class="input-group">
										<form:input path="expiredDate" type="text" name="expiredDate" placeholder="Select a date" class="form-control datepicker" data-dateformat="dd/mm/yy"></form:input>
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										</div>
									</section>
									
									<section class="col col-4">
									<label class="label">Expired Time&nbsp;<font color="red">*</font></label>
									<div class="input-group">
										<input class="form-control" id="expTime" name="expTime" type="text" placeholder="Select time">
										<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
									</div>
									</section>
									
								</div>
								
								<section>
									<label class="label">Title&nbsp;<font color="red">*</font></label>
									<label class="input">
										<i class="icon-append fa fa-tag"></i>
										<form:input type="text" path="title" name="title" id="title" placeholder="Eneter Title"></form:input>
									</label>
								</section>
								
								<section>
									<label class="label">Message&nbsp;<font color="red">*</font></label>
									<label class="textarea">
										<i class="icon-append fa fa-comment"></i>
										<form:textarea rows="4" path="notificationDetails" name="notificationDetails" id="notificationDetails" placeholder="Enter message"></form:textarea>
									</label>
								</section>
							</fieldset>
							
							<footer>
								<button type="submit" class="btn btn-primary">Submit Notification</button>
							</footer>
						</form:form>						
					</div>
				</div>
			</div>
		</article>
		<article class="col-sm-12 col-md-12 col-lg-3">
		<div class="col-lg-3">
			<a class="btn alert-success btn-lg" onclick="liveNotification()" style="width: 256px;margin-left: -13px;">Live Notifications</a>
		</div>
		</article>
		<article class="col-sm-12 col-md-12 col-lg-3">		
										
		<div class="col-lg-3">
			<a class="btn alert-danger btn-lg txt-color-white" onclick="expiredNotification()" style="width: 256px;margin-left: -13px;">Expired Notifications</a>
		</div> 
		</article>
		<br>
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
				
							<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><span id="spanId">Live Notifications</span></h2>
				
								</header>
								<div>
									<div class="widget-body no-padding" style="overflow: scroll">
				
										<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th data-class="expand">Title</th>
													<th data-class="expand">Notification Details</th>
													<th data-class="expand">Created Date</th>
													<th data-class="expand">Expired Date</th>
													<th data-class="expand">Location</th>
													<th data-class="expand">Feeder Code</th>
												</tr>
											</thead>
											<tbody id="notificationDiv" >
												<c:forEach items="${liveNotifications}" var="notification">
												<tr>
													<td>${notification.title}</td>
													<td>${notification.notificationDetails}</td>
													<td>${notification.datePublished}</td>
													<td>${notification.expDate}</td>
													<td>${notification.location}</td> 
													<td>${notification.feederCode}</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
				</article>
</div>
</section>
</div>		

<script>
$(document).ready(function() {
	
	pageSetUp();
	
	$('#expTime').timepicker();
	
	$("#outageForm").submit(function(){
		if ($('#outageForm').valid()) {
			$('button[type=submit]').attr("disabled", "disabled");
		      $('a').unbind("click").click(function(e) {
		          e.preventDefault();
		          // or return false;
		      });
		}else{
			$('button[type=submit]').removeAttr('disabled');
		}
	    });
	
	document.getElementById("outageForm").reset();
	
	$("#outageForm").validate({
		// Rules for form validation
		rules : {
			circleSiteCode : {
				required : true,
			},
			expiredDate : {
				required : true,
			},
			expTime : {
				required : true,
			},
			title : {
				required : true,
				maxlength : 100
			},
			notificationDetails : {
				required : true,
				minlength : 10,
				maxlength : 500
			},
			feederCode : {
	            required: {
	                depends: function(element) {
	                	var result = "";
	                	if($("#sectionSiteCode").val()==0){
	                		result = false;
	                	}else if($("#sectionSiteCode").val()==null){
	                		result = false;
	                	}else{
	                		result = true;
	                	}
	                	return (result);
	                }
	            }
	        } 
		},

		// Messages for form validation
		messages : {
			circleSiteCode : {
				required : 'Please select Circle',
			},
			expiredDate : {
				required : 'Please select expired date',
			},
			expTime : {
				required : 'Please select expired time',
			},
			title : {
				required : 'Title is required',
				maxlength : 'Title max length is 100 characters'
			},
			notificationDetails : {
				required : 'Please enter message',
			},
			feederCode : {
				required : 'Please select feeder code',
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
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
		
		var newOption2 = $('<option>');
	    newOption2.attr('value',0).text(" "); 
	    $('#sectionSiteCode').empty(newOption2);
	    var defaultOption2 = $('<option>');
	    defaultOption2.attr('value',0).text("Select Section");
	    $('#sectionSiteCode').append(defaultOption2);
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
            $('#sectionSiteCode').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select Section");
            $('#sectionSiteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.sectionName);
				$('#sectionSiteCode').append(newOption);
			}));
		}
	});
	
});
	$('select[id*=circleSiteCode]').change(function() {
		var circleSiteCode = $("#circleSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllDivisions/" + circleSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#divisionSiteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Division");
                
                $('#divisionSiteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.divisionName);
					$('#divisionSiteCode').append(newOption);
				}));
			}
		});
		
		var newOption1 = $('<option>');
        newOption1.attr('value',0).text(" "); 
        $('#subDivisionSiteCode').empty(newOption1);
        var defaultOption1 = $('<option>');
        defaultOption1.attr('value',0).text("Select Sub Division");
        $('#subDivisionSiteCode').append(defaultOption1);
        
        var newOption2 = $('<option>');
        newOption2.attr('value',0).text(" "); 
        $('#sectionSiteCode').empty(newOption2);
        var defaultOption2 = $('<option>');
        defaultOption2.attr('value',0).text("Select Section");
        $('#sectionSiteCode').append(defaultOption2);
	});
	
	$('select[id*=sectionSiteCode]').change(function() {
		var sectionSiteCode = $("#sectionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getFeederCodes/" + sectionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
	            newOption.attr('value',0).text(" "); 
	            $('#feederCode').empty(newOption);
	            var defaultOption = $('<option disabled="" selected="">');
	            defaultOption.attr('value',0).text("Select FeederCode");
	            
	            var defaultOption2 = $('<option>');
	            defaultOption2.attr('value',"All").text("All Feeders");
	            
	            $('#feederCode').append(defaultOption);
	            $('#feederCode').append(defaultOption2);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.feederCode).text(item.feederCode);
					$('#feederCode').append(newOption);
				}));
			}
		});
	});

	var responsiveHelper_dt_basic = undefined;
	
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};

	$('#dt_basic').dataTable({
		"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_dt_basic) {
				responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_dt_basic.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_dt_basic.respond();
		}
	});
	
});

function expiredNotification(){
	
	$("#spanId").text("Expired Notifications");
	
	$("#notificationDiv").empty();
	var tableData = "";
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/expiredNotifications",
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"	
			            	+"<td>"+obj.title+"</td>"
			            	+"<td>"+obj.notificationDetails+"</td>"
			            	+"<td>"+obj.datePublished+"</td>" 
			            	+"<td>"+obj.expDate+"</td>"
			            	+"<td>"+obj.location+"</td>"
			            	+"<td>"+obj.feederCode+"</td>"
			                +"</tr>";	 
						}
				$('#notificationDiv').append(tableData);
		}
	});
}

function liveNotification(){
	
	$("#spanId").text("Live Notifications");
	
	$("#notificationDiv").empty();
	var tableData = "";
	$.ajax
	({			
		type : "POST",
		url : "./helpDesk/liveNotifications",
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	tableData += "<tr>"	
			            	+"<td>"+obj.title+"</td>"
			            	+"<td>"+obj.notificationDetails+"</td>"
			            	+"<td>"+obj.datePublished+"</td>" 
			            	+"<td>"+obj.expDate+"</td>"
			            	+"<td>"+obj.location+"</td>"
			            	+"<td>"+obj.feederCode+"</td>"
			                +"</tr>";	 
						}
				$('#notificationDiv').append(tableData);
		}
	});
}
</script>
