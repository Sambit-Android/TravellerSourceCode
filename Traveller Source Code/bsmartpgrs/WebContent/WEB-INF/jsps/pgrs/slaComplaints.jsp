<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<div id="content">
			
				
				<!--search form   -->
					<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
				
				

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form:form id="order-form" action="./slaComplaintSearch" method="POST" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">
							<fieldset>
								<div class="row">
								
								<!-- 	<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label> 
										
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section> -->
									<section class="col col-2">
										<label class="select">
											<form:select path="circleSiteCode" id="circleSiteCode" name="circleSiteCode">
												<option value="0">Select Circle</option>
  												<c:forEach items="${circleList}" var="circles">
												<form:option value="${circles.circleSiteCode}">${circles.circleName}</form:option>
												</c:forEach>
										    </form:select><i></i>
										</label>
									</section>
									<section class="col col-2">
										<label class="select">
											<form:select path="divisionSiteCode" id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
										    </form:select><i></i></label>
									</section>
									
									<section class="col col-2">
										<label class="select">
											<form:select path="siteCode" name="siteCode" id="siteCode">
												<option value="0" selected="" disabled="">Select Sub Division</option>
											</form:select> <i></i></label>
									</section>
									<!-- <section class="col col-2">
										<label class="select">
											<select name="docketStatus" id="docketStatus">
												<option value="" selected="" disabled="">Select Status</option>
												<option value="1">Pending For Registration</option>
												<option value="2">Registered&Assigned</option>
												<option value="3">OnHold</option>
												<option value="4">Resolved</option>
												<option value="5">Re open</option>
												<option value="6">Closed</option>
												
											</select> <i></i></label>
									</section> -->
									<section class="col col-2">
										<label class="select">
											<select name="docketSource" id="docketSource">
												<option value="0" selected="" disabled="">Select Source</option>
												<option value="Phone">Phone</option>
												<option value="Web">Web</option>
												<option value="Sms">Sms</option>
												<option value="Email">Email</option>
												<option value="Facebook">Facebook</option>
												<option value="Hand Written">Hand Written</option>
											</select> <i></i></label>
									</section>
									
									<section class="col col-2">
										<label class="select">
											<form:select path="categoryId" id="categoryId" name="categoryId">
												<option value="0" selected="" disabled="">Select Category</option>
  												<c:forEach items="${categoryList}" var="categories">
												<form:option value="${categories.categoryId}">${categories.categoryName}</form:option>
												</c:forEach>
										    </form:select><i></i></label>
											</section>
									
									<section class="col col-2">
										<label class="select">
											<form:select path="subCategoryId" name="subCategoryId" id="subCategoryId">
												<option value="0" selected="" disabled="">Select SubCategory</option>
											</form:select><i></i></label>
									</section>
									
									<section class="col col-2.5">
									<div class="inline-group">
										<label class="radio">
											<input type="radio" onclick="callradio(1)" name="radio-inline" checked="checked">
											<i></i>Time Pending As Per SLA</label>
										<label class="radio">
											<input type="radio" onclick="callradio(2)" name="radio-inline">
											<i></i>Aging As Per SLA</label>
									</div>
								</section>
									
									
									<section class="col col-2" id="timependingid">
										<label class="select">
											<select  name=agingAsPerSlaPending id="agingAsPerSlaPending">
												<option value="0" selected="" disabled="">Time Pending As Per SLA</option>
												<option value="${agingEscPending.doclevel1}">0-1 hrs</option>
												<option value="${agingEscPending.doclevel2}">2-4 hrs</option>
												<option value="${agingEscPending.doclevel3}">5-24 hrs</option>
												<option value="${agingEscPending.doclevel4}">1-10 days</option>
												<option value="${agingEscPending.doclevel5}">11 days-1 month</option>
												<option value="${agingEscPending.doclevel6}">More Than Month</option>
											</select><i></i></label>
									</section>
									
									
									
										<section class="col col-2" hidden="true" id="agingid">
										<label class="select">
											<select  name=complaintsAgingSla id="complaintsAgingSla">
												<option value="0" selected="" disabled="">Aging As Per SLA</option>
												<option value="${complaintsAging.doclevel1}">0-1 hrs</option>
												<option value="${complaintsAging.doclevel2}">2-4 hrs</option>
												<option value="${complaintsAging.doclevel3}">5-24 hrs</option>
												<option value="${complaintsAging.doclevel4}">1-10 days</option>
												<option value="${complaintsAging.doclevel5}">11 days-1 month</option>
												<option value="${complaintsAging.doclevel6}">More Than Month</option>
											</select><i></i></label>
									</section>
									
									
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 40px;width: 150px;">Search</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form:form>

					</div>
					
					
				</div>
				<!-- end widget div -->
				
			</div> 
				
				
	<div id="addtab" title="Docket Details Update" style="display:none">

				<form id="updateStatus-form">
					<fieldset>
						<input name="authenticity_token" type="hidden"><br>
						<div class="form-group">
								<input type="radio" id="commentId" checked="checked" name="Comment" onchange="commentValueChange(this.value)" value="Status"><label>&nbsp;Change Status</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="Comment" value="Comment" onchange="commentValueChange(this.value)"><label>&nbsp;Comment</label><br>
							</div>
							<div class="form-group" id="commentStatus">
							<label>Docket Status </label>
							<select class="form-control" id="docketStatusNew" name="docketStatusNew">
								<option value="0" selected="" disabled="">Select Status</option>
								<option value="2">On Hold</option>
								<option value="3">Resolved</option>
							</select><i></i>
						</div>
						<div class="form-group">
							<label>Remarks</label>
							<textarea class="form-control" name="remarks" id="remarks" placeholder="Docket Remarks" rows="3"></textarea>
						</div>
					</fieldset>
				</form>

	</div>	
	
	<div id="reopenDiv" title="Reopen complaint" style="display:none">

				<form id="reopenStatus-form">
					<fieldset>
						<input name="authenticity_token" type="hidden"><br>
						<div class="form-group">
								<input type="radio" id="commentReopenId" checked="checked" name="CommentReopen" onchange="commentReopenValueChange(this.value)" value="Status"><label>&nbsp;Change Status</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="CommentReopen" value="Comment" onchange="commentReopenValueChange(this.value)"><label>&nbsp;Comment</label><br>
							</div>
							<div class="form-group" id="commentStatusReopen">
							<label>Docket Status </label>
							<select class="form-control" id="docketStatusReopen" name="docketStatusReopen">
								<option value="0" selected="" disabled="">Select Status</option>
								<option value="4">Reopen</option>
							</select><i></i>
						</div>
						<div class="form-group">
							<label>Remarks</label>
							<textarea class="form-control" name="remarksReopen" id="remarksReopen" placeholder="Docket Remarks" rows="3"></textarea>
						</div>
					</fieldset>
				</form>

</div>	
				
		<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Complaints</h2>
				
								</header>
				
								<!-- widget div-->
									<br>
								
								<a class="btn bg-color-redLight txt-color-white"  href="#"  onclick="return docketDetailsPopUp('CHECKBOXYES')">Change Multiple Status</a>
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body no-padding" style='overflow: scroll;'>
				
										<table id="datatable_tabletools" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th> <input type="checkbox" id="selectall"  onClick="selectAll(this)" /></th>
													<th data-hide="phone">Docket No.</th>
													<th data-class="expand">Docket Status</th>
													<th>Created Date</th>
													<th data-hide="phone">SLA Date&Time for Resolving</th>
													<th data-hide="phone">Time Pending</th>
													<th data-hide="phone">Resolved Date & Time</th>
													<!-- <th data-hide="phone">Time taken for resolving</th> -->
													<th data-hide="phone,tablet">Category</th>
													<th data-hide="phone,tablet">Sub Category</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th>
													<th >AssignedTo</th>
													<th></th>
													
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${search}" var="searchData">
											<tr>
											<c:choose>
											<c:when test="${searchData.docketStatus eq 'Resolved'}">
											<td></td>
											<td><a style="cursor:pointer;" onclick='docketDetailsValidation(${searchData.docketNumber})'>${searchData.docketNumber}</a></td>
											</c:when>
											<c:otherwise>
											<td><div id="docketNumDiv"> <input type="checkbox"    autocomplete="off" placeholder="" name="docketNumb" id="${searchData.docketNumber}" value="${searchData.docketNumber}" /> </div></td>
											<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${searchData.docketNumber})'>${searchData.docketNumber}</a></td>
											</c:otherwise>
											</c:choose>
											
													<%-- <td>${searchData.docketNumber}</td> --%>
													<td>${searchData.docketStatus}</td>
													<td>${searchData.docketCreatedDt}</td>
													<td>${searchData.estResolveTime}</td>
													<td>${searchData.pending}</td>
													<td>${searchData.resolvedDate}</td>
													<%-- <td>${searchData.totalTime}</td> --%>
													<td>${searchData.categoryName}</td>
													<td>${searchData.subCategoryName}</td>
													<td>${searchData.consumerName}</td>
													<td>${searchData.consumerMobileNo}</td>
													<td colspan='5'>${searchData.assinedName} - ${searchData.assignedTo} - ${searchData.designation} - ${searchData.officialMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${searchData.docketNumber})'>View</a></td>
													<%-- 
													 --%>
													
													
													
												</tr>
											</c:forEach> 
												
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
						</article>
						<!-- WIDGET END -->
				
					</div>
					
					<!-- Added By Raju For View In Each Row-->
					<div class="row" style="display:none" id="selectedDocketDetails">
		
<article class="col-sm-12">
<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon"></span>
								<ul class="nav nav-tabs pull-right in" id="myTab">
									<li id="tabOne" class="active">
										<a data-toggle="tab" href="#s1" onclick="tab1Click()"><i class="fa fa-envelope"></i> <span class="hidden-mobile hidden-tablet">Docket Details</span></a>
									</li>
									<li id="tabTwo">
											<a data-toggle="tab" href="#s2" onclick="tab2Click()"><i class="fa fa-folder"></i> <span class="hidden-mobile hidden-tablet">Docket Histroy</span></a>
									</li>
									<li id="tabThree">
											<a data-toggle="tab" href="#s4" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
									</li>
									</ul>	
										
								</header>
								<div class="no-padding">
									<div class="widget-body" id="tab1">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in padding-12 no-padding-bottom" id="s1"> 	
											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
													<tbody id="viewDocket">
	
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab2" style='overflow: scroll;'>
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s2">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Date</th>
															<th data-class="expand">Status</th>
															<!-- <th>Action</th> -->
															<th data-hide="phone">Action By</th>
															<th data-hide="phone,tablet">Name</th>
															<th data-hide="phone,tablet">User Name</th>
															<th data-hide="phone,tablet">Office</th>
															<th data-hide="phone,tablet">Designation</th>
															<th data-hide="expand">Official Mobile No.</th>
															<th data-hide="phone,tablet" style="min-width: 600px;">Remarks</th>
														</tr>
														</thead>
													<tbody id="docketHistoryDiv">
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab3">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s4">	
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<!-- <th data-hide="phone">Created Date</th> -->
															<th data-hide="phone">Escalated Date</th>
															<th data-class="expand">Level</th>
															<!-- <th data-hide="phone">From Officer</th> -->
															<th data-hide="phone">To Officer</th>
														</tr>
														</thead>
													<tbody id="docketEscHistoryDiv">
													</tbody>
													</table>
												</div>
											</div>
									</div>
							</div>
					</div>
			</div>
		</article>
		
</div>


<script>


	//document.getElementById("order-form").reset();


function callradio(radio){
	if(radio==1){
		$('#agingid').hide();
		$('#timependingid').show();
	}else{
		$('#agingid').show();
		$('#timependingid').hide();
	}
}
$(document).ready(function() {
	$.validator.addMethod("regex", function(value, element, regexpr) {
		return regexpr.test(value);
	}, "");


	  $("#order-form").validate({

		// Rules for form validation
		rules : {
			fromdate : {
				required : true
				
			},
			todate : {
				required : true
				
			},
		},

		// Messages for form validation
		messages : {
			fromdate : {
				required : 'Please select From Date'
			},
			todate : {
				required : 'Please select To Date'
			},
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
                $('#siteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#siteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.subDivisionName);
					$('#siteCode').append(newOption);
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
	});
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
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Category");
                $('#subCategoryId').append(defaultOption);
				($.map(data, function(item) {
					var newOption = $('<option>'); 
					newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
					$('#subCategoryId').append(newOption);
				}));
			}
		});
	});
	
	pageSetUp();
	
	$('#fromdate').datepicker({
		dateFormat : 'mm/dd/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#todate').datepicker('option', 'minDate', selectedDate);
		}
	});
	
	$('#todate').datepicker({
		dateFormat : 'mm/dd/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#fromdate').datepicker('option', 'maxDate', selectedDate);
		}
	});
	
		var responsiveHelper_dt_basic = undefined;
		var responsiveHelper_datatable_fixed_column = undefined;
		var responsiveHelper_datatable_col_reorder = undefined;
		var responsiveHelper_datatable_tabletools = undefined;
		
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

	/* END BASIC */
	
	/* COLUMN FILTER  */
    var otable = $('#datatable_fixed_column').DataTable({
    	//"bFilter": false,
    	//"bInfo": false,
    	//"bLengthChange": false
    	//"bAutoWidth": false,
    	//"bPaginate": false,
    	//"bStateSave": true // saves sort state using localStorage
		"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_datatable_fixed_column) {
				responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_fixed_column.respond();
		}		
	
    });
    
    // custom toolbar
    $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');
    	   
    // Apply the filter
    $("#datatable_fixed_column thead th input[type=text]").on( 'keyup change', function () {
    	
        otable
            .column( $(this).parent().index()+':visible' )
            .search( this.value )
            .draw();
            
    } );
    /* END COLUMN FILTER */   

	/* COLUMN SHOW - HIDE */
	$('#datatable_col_reorder').dataTable({
		"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_datatable_col_reorder) {
				responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_col_reorder'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_col_reorder.respond();
		}			
	});
	
	/* END COLUMN SHOW - HIDE */

	/* TABLETOOLS */
	$('#datatable_tabletools').dataTable({
		
		// Tabletools options: 
		//   https://datatables.net/extensions/tabletools/button_options
		"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
        "oTableTools": {
        	 "aButtons": [
             "copy",
             "csv",
             "xls",
                {
                    "sExtends": "pdf",
                    "sTitle": "SmartAdmin_PDF",
                    "sPdfMessage": "SmartAdmin PDF Export",
                    "sPdfSize": "letter"
                },
             	{
                	"sExtends": "print",
                	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
            	}
             ],
            "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
        },
		"autoWidth" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_datatable_tabletools) {
				responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_tabletools.respond();
		}
	});
	
	/* END TABLETOOLS */

})

// Added By raju

function selectAll(source) {
	 
	   var flagChecked = 0;
		checkboxes = document.getElementsByName('docketNumb');
		for(var i =0;i<checkboxes.length;i++)
			{
			checkboxes[i].checked = source.checked;
			if(checkboxes[i].checked)
			 {
				flagChecked = 1;
			}
			}
		
		 if(flagChecked==0)
		{
			$('#docketNumDiv span:first-child').removeClass("checked");
		}
		else{
			$('#docketNumDiv span:first-child').addClass("checked");
		} 
		
		
	}
var dialog = "";
	
	function docketDetailsPopUp(docketNo) {
		
		if(docketNo=='CHECKBOXYES')//This is for Assign Complaint CheckBox
		{
	var checkboxes = document.getElementsByName('docketNumb');
	var checks = "";
	for(var i =0;i<checkboxes.length;i++)
	{
		
		if(checkboxes[i].checked)
		 {
			checks = checks+checkboxes[i].value+",";
			
		}
		}
	if(checks == "")
		{
			alert("Please Select the Docket Number ...");
			return false;
		}
	checks = checks.substring(0,checks.length-1);
	
	docketNo=checks;
	
		}
		
		

		$("#commentStatus").show();
		$('#commentId').prop('checked', true);
		dialog = $("#addtab").dialog({
			autoOpen : false,
			width : 500,
			resizable : false,
			modal : true,
			buttons : [{

				html : "Update",
				"class" : "btn btn-primary",
				click : function() {				
				var status = ticketStatusUpdateClick(docketNo);			
				if(status!=false){
					$(this).dialog("close");
				}
					
				}
			},{
				html : "<i class='fa fa-times'></i>&nbsp; Cancel",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");

				}
			}]
		}).dialog("open");
		
	}
	
	function ticketStatusUpdateClick(docketNo){
		
		var commentValue = $("input[name=Comment]:checked").val();
		
		var docketStatus = $("#docketStatusNew").val();
		var remarks = $("#remarks").val();
		
		if(commentValue == "Status"){
			if(docketStatus==0 || docketStatus=="" || docketStatus==null){
				alert("Please select status");
				return false;
			}else if(remarks==null || remarks == ""){
				alert("Please enter remarks");
				return false;
			}
			
		}else if(remarks==null || remarks == ""){
			alert("Please enter remarks");
			return false;
		}
		
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/updateDocket",
			dataType : "text",
			data:{
				docketNo : docketNo,
				docketStatus : docketStatus,
				remarks : remarks,
			},
			success : function(response){	    
					alert(response);
					window.location.reload();
			} 
		});
		$("#docketStatusNew").val(0);
		$("#remarks").val("");
	}
	
	function commentReopenValueChange(value){
		if(value=='Comment'){
			$("#commentStatusReopen").hide();
		}else{
			$("#commentStatusReopen").show();
		}
	}
	
	function commentValueChange(value){
		if(value=='Comment'){
			$("#commentStatus").hide();
			$('#docketStatusNew').val(0);
		}else{
			$("#commentStatus").show();
		}
	}
	
	function docketDetailsValidation(docketNo)
	{
		$("#commentStatusReopen").show();
		$('#commentReopenId').prop('checked', true);
		dialog = $("#reopenDiv").dialog({
			autoOpen : false,
			width : 500,
			resizable : false,
			modal : true,
			buttons : [{

				html : "Update",
				"class" : "btn btn-primary",
				click : function() {				
				var status = reopenDocketClick(docketNo);			
				if(status!=false){
					$(this).dialog("close");
				}
					
				}
			},{
				html : "<i class='fa fa-times'></i>&nbsp; Cancel",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");

				}
			}]
		}).dialog("open");
	}
	
	function reopenDocketClick(docketNo){
		
		var commentValue = $("input[name=CommentReopen]:checked").val();	
		var docketStatus = $("#docketStatusReopen").val();
		var remarks = $("#remarksReopen").val();
		
		if(commentValue == "Status"){
			if(docketStatus==0 || docketStatus=="" || docketStatus==null){
				alert("Please select status");
				return false;
			}else if(remarks==null || remarks == ""){
				alert("Please enter remarks");
				return false;
			}
			
		}else if(remarks==null || remarks == ""){
			alert("Please enter remarks");
			return false;
		}
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/updateDocket",
			dataType : "text",
			data:{
				docketNo : docketNo,
				docketStatus : docketStatus,
				remarks : remarks,
			},
			success : function(response){	    
					alert(response);
					window.location.reload();
			} 
		});
		$("#docketStatusReopen").val(0);
		$("#remarksReopen").val("");
	}
	
	var docketNum;
	function docketDetailsViewPopUp(docNo)
	{
		docketNum = docNo;
		dialog = $("#selectedDocketDetails").dialog({
			autoOpen : false,
			width : 1000,
			height : 580,
			resizable : false,
			modal : true,
		}).dialog("open");
		$("#tabOne").addClass("active");
		$("#tabTwo").removeClass();
		$("#tabThree").removeClass();
		tab1Click();	
	}

	function tab1Click(){
		$("#tab1").show();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#viewDocket").empty();
		var tableData = "";
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketNumber/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	tableData += "<tr>"	
					              	+"<th colspan='6'>Docket Details</th>"
					              	+"</tr>"						
									+"<tr>"
									+"<th>Docket No.</th>"
									+"<td>"+obj.docketNumber+"</td>"
									+"<th>Docket Source</th>"
									+"<td>"+obj.docketSource+"</td>"
									+"<th>Docket Status</th>"
									+"<td>"+obj.docketStatus+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Consumer Name</th>"
									+"<td>"+obj.consumerName+"</td>"
									+"<th>Consumer Mobile No.</th>"
									+"<td>"+obj.consumerMobileNo+"</td>"
									+"<th>Email Id</th>"
									+"<td>"+obj.consumerEmailId+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>RR Number</th>"
									+"<td>"+obj.rrNumber+"</td>"
									+"<th>Consumer Other Mobile No.</th>"
									+"<td>"+obj.alternativeMobileNo+"</td>"
									+"<th>Facebook Id</th>"
									+"<td>"+obj.facebookId+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Address</th>"
									+"<td>"+obj.address+"</td>"
									+"<th>Category</th>"
									+"<td>"+obj.categoryName+"</td>"
									+"<th>Sub Category</th>"
									+"<td>"+obj.subCategoryName+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Sub Division</th>"
									+"<td>"+obj.divisionName+"</td>"
									+"<th>Section</th>"
									+"<td>"+obj.subDivisionName+"</td>"
									+"<th>Land Mark</th>"
									+"<td>"+obj.landMark+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Created By</th>"
									+"<td>"+obj.userName+"</td>"
									+"<th>Created Date</th>"
									+"<td>"+obj.docketCreatedDt+"</td>"
									+"<th>SLA Date&Time for Resolving</th>"
									+"<td>"+obj.estResolveTime+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Resolved Date</th>"
									+"<td>"+obj.resolvedDate+"</td>"
									+"<th>Asigned To</th>"
									+"<td colspan='5'>"+obj.assinedName+" - "+obj.assignedToEmail+" - "+obj.designation+" - "+obj.officialMobileNo+" - "+obj.officeName+"</td>"
									/* +"<th>Official Mobile No.</th>"
									+"<td>"+obj.officialMobileNo+"</td>" */
									+"</tr>"
									+"<tr>"
									+"<th>Docket Summary</th>"
									+"<td>"+obj.docketSummary+"</td>"
									+"<th>Service Station</th>"
									+"<td colspan='5'>"+obj.serviceStation+"</td>"
									+"</tr>";
					                
					     }
					$('#viewDocket').append(tableData);
			}
		});
	}

	function tab2Click(){
		var docketHistroyTable = "";
		$("#docketHistoryDiv").empty();
		$("#tab2").show();
		$("#tab1").hide();
		$("#tab3").hide();
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketHistory/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	docketHistroyTable += "<tr>"		
			              	+"<td>"+obj.serialNo+"</td>"
			              	+"<td>"+obj.docketUpdatedDt+"</td>"
			              	+"<td>"+obj.docketStatus+ "</td>"
			              	/* +"<td>"+obj.action+"</td>" */
			              	+"<td>"+obj.actionBy+ "</td>"
			              	+"<td>"+obj.personName+"</td>"			              	
			              	+"<td>"+obj.userName+"</td>"
			              	+"<td>"+obj.office+ "</td>"
			              	+"<td>"+obj.designation+ "</td>"
			              	+"<td>"+obj.officialMobileNo+ "</td>"
			              	+"<td>"+obj.remarks+ "</td>"
			                +"</tr>";
			         }
							$('#docketHistoryDiv').append(docketHistroyTable);
			} 
		});
	}

	function tab3Click(){
		var docketEscHistroyTable = "";
		$("#docketEscHistoryDiv").empty();
		$("#tab3").show();
		$("#tab1").hide();
		$("#tab2").hide();
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketEscHistory/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              		docketEscHistroyTable += "<tr>"		
				              	+"<td>"+obj.serialNo+"</td>"
				              	/* +"<td>"+obj.docketCreatedDt+"</td>" */
				              	+"<td>"+obj.escalatedDate+"</td>"
				              	+"<td>"+obj.escLevel+ "</td>"
				              	/* +"<td>"+obj.fromUserData+"</td>" */
				              	+"<td>"+obj.toUserData+ "</td>"	
				                +"</tr>";
			         }
							$('#docketEscHistoryDiv').append(docketEscHistroyTable);
			} 
		});
	}


</script>
		