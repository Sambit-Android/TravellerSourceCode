<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>


<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<div id="content">
	<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body no-padding">
				<form:form id="order-form" action="#" method="POST" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">
					<fieldset>
						<div class="row">
							<section class="col col-3">
								<label class="input"> <i class="icon-append fa fa-calendar"></i> <input name="fromdate" id="fromdate" required="required" style="width: 100%; height: 30px;" placeholder="Select Year">
								</label>
							</section>
						
							<!-- <section class="col col-4">
								<label class="select"> <select name="docketSource"
									id="docketSource">
										<option value="0" selected="" disabled="">Select
											Source</option>
										<option value="Phone">Phone</option>
										<option value="Web">Web</option>
										<option value="Sms">Sms</option>
										<option value="Email">Email</option>
										<option value="Facebook">Facebook</option>
										<option value="Hand Written">Hand Written</option>
								</select> <i></i></label>
							</section> -->

							<section class="col col-3">
								<label class="select"> <form:select path="categoryId"
										id="categoryId" name="categoryId">
										<option value="0" selected="" disabled="">Select
											Category</option>
										<option value="0">All</option>
										<c:forEach items="${categoryList}" var="categories">
											<form:option value="${categories.categoryId}">${categories.categoryName}</form:option>
										</c:forEach>
									</form:select><i></i></label>
							</section>

							<section class="col col-3">
								<label class="select"> <form:select path="subCategoryId"
										name="subCategoryId" id="subCategoryId">
										<option value="0" selected="" disabled="">Select
											SubCategory</option>
									</form:select><i></i></label>
							</section>


							<section class="col col-3">
								<button type="submit" class="btn btn-primary"
									style="height: 31px; width: 180px;" onclick="return loadData()">Filter</button>
							</section>

						</div>


					</fieldset>

				</form:form>

			</div>


		</div>
		
	</div>
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div id="monthdata">
					<div class="widget-body no-padding" id="monthContent">
						<table id="dt_basic" class="table table-striped table-bordered table-hover monthtab" width="100%">
							<thead>
								<tr>
									<th data-hide="phone">Circle</th>
									<th data-class="expand">Total Complaints</th>
									<th data-hide="phone">Resolved as per SLA</th>
									<th data-hide="phone,tablet">Resolved Beyond SLA</th>
								</tr>
							</thead>
						</table>

					</div>
					</div>
				</div>
			</div>
	</div>
</div>
<div id="alertsBox" title="Alert"></div>

<script>
var dochketStatus="";
var docketSource="";
var categoryId=0;
var subCategoryId=0;
function loadData(){
	dochketStatus=$('#fromdate').val();
	docketSource=$('#docketSource').val();
	categoryId=$('#categoryId').val();
	subCategoryId=$('#subCategoryId').val();
	if(dochketStatus==""){
		$("#alertsBox").html("");
		$("#alertsBox").html("Please select Month");
		$("#alertsBox").dialog({
			modal : true,
			resizable : false,
			buttons : {
				"Close" : function() {
					$(this).dialog("close");
				}
			}
		});
		return false;
	}
	$.ajax({
			url : "./reports/kercReport",
			type : "GET",
			dataType : "JSON",
			async : false,
		data:{
			year:dochketStatus,
			docketSource:docketSource,
			categoryId:categoryId,
			subCategoryId:subCategoryId,
		},
		success : function(response) {
			$('.monthTabDetail').remove();
			var dataNew=response;
			var htmlTable = "<div class='monthTabDetail' style='overflow: scroll;'>" 
				+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
				+"<thead>"
				+"<tr>"
				+"<th data-hide='phone'>Circle</th>"
				+"<th data-hide='phone'>Total Complaints</th>"
				+"<th data-hide='phone,tablet'>Resolved as per SLA</th>"
				+"<th data-hide='phone,tablet'>Resolved Beyond SLA</th>"
			    +"</tr>"
						+"</thead>"
						+"<tbody>";
			 $.each(dataNew, function(index, data){
				 htmlTable+= "<tr></td>"
		              	+"<td>"+data.text+"</td>"
		              	+"<td>"+data.total+"</td>"
		              	+"<td>"+data.asPerSLA+"</td>"
		              	+"<td>"+data.beyondSLA+"</td>"
			 });
			 htmlTable+="</tbody>";
			 $('.monthtab').hide();
				$('#dt_basic_wrapper').hide();
				$('#monthContent').append(htmlTable);
				
				pageSetUp();
				var responsiveHelper_dt_basic = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#datatable_tabletools5')
				.dataTable(
						{
							"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
									+ "t"
									+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
							"oTableTools" : {
								"aButtons" : [
										"copy",
										"csv",
										"xls",
										{
											"sExtends" : "pdf",
											"sTitle" : "SmartAdmin_PDF",
											"sPdfMessage" : "SmartAdmin PDF Export",
											"sPdfSize" : "letter"
										},
										{
											"sExtends" : "print",
											"sMessage" : "<i>(press Esc to close)</i>"
										} ],
								"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
							},
							"autoWidth" : true,
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_datatable_tabletools) {
									responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
											$('#datatable_tabletools5'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_datatable_tabletools
										.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_datatable_tabletools
										.respond();
							}
						});
		},

	});
	return false;
}


$(document).ready(function() {
	$("#fromdate").keypress(function(event) {event.preventDefault();});
	pageSetUp();
	  $("#fromdate").kendoDatePicker({
	        start: "decade",
	        depth: "year",
	        format: "MMM-yyyy",
	        min: new Date(2010, 1, 1),
	          max: new Date(),
	          readonly : true,
	    }); 
	  $('select[id*=categoryId]').change(function() {
			var categoryId = $("#categoryId").val();
			if(categoryId==1){
				var newOption = $('<option>');
	            newOption.attr('value',0).text(" "); 
	            $('#subCategoryId').empty(newOption);
	            var defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("Select Sub Category");
	            $('#subCategoryId').append(defaultOption);
	            defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("All");
	            $('#subCategoryId').append(defaultOption);
			}else{
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
	  }
		});
	  
});

</script>
<script type="text/javascript">
	pageSetUp();
	var pagefunction = function() {
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
	    var otable = $('#datatable_fixed_column').DataTable({
			"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
			"autoWidth" : true,
			"preDrawCallback" : function() {
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
	            "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
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

	};

	// load related plugins
	
	loadScript("./resources/js/plugin/datatables/jquery.dataTables.min.js", function(){
		loadScript("./resources/js/plugin/datatables/dataTables.colVis.min.js", function(){
			loadScript("./resources/js/plugin/datatables/dataTables.tableTools.min.js", function(){
				loadScript("./resources/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
					loadScript("./resources/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
				});
			});
		});
	});


</script>
<style>
#content {
    padding: 2px 0px;
    position: relative;
}
.jarviswidget {
    margin: 0px 0px 1px;
}
</style>
		