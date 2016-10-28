<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<!-- SPARKLINES -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>


<div id="content">
<section id="widget-grid" class="">
	<div class="row">
		<div class="col-sm-12">
		  <div class="col-sm-100"> 
			<div class="jarviswidget jarviswidget-sortable jarviswidget-color-greenLight" id="widapptrack" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-fighter-jet"></i> </span>
									<h2>Application Tracking</h2>
								</header>
								<div style='overflow: scroll;'>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
									<table id="datatable_fixed_column" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>Application&nbsp;ID</th>
											<th>Application Phase</th>
											<th>Status</th>
											<th>Assigned To</th>
											<th>View History</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="app" items="${appTrackingDetails}">
												<tr>
													<td>${app.applicationid}</td>
													<td>${app.applicationPhase}</td>
													<td>${app.applicationStatus}</td>
													<td>${app.assignedTo}</td>
													<td><a class="btn btn-primary" href='#' onclick='applicationTrackingPopUp(${app.applicationid})'><strong>View</strong></a></td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
									</div>
								</div>
							</div></div>
		</div>
	</div></section>
</div>	


<div id="addtabforClarifications" title="Application Tracking History" style="display:none;overflow-y:scroll;overflow-x:hidden;">
    	    <table class="table table-bordered table-striped">
			 <thead>
				 <tr>
					
					<th width="10">Sl No</th>
					<th width="150">Application Phase</th>
					<th width="100">Status</th>
					<th width="240">Done by</th>
					<th width="70">User Name</th>
					<th width="70">Done On</th>
					<th width="220">Remarks</th>												
				</tr>
			</thead>
			<tbody id="viewapplicationPhases">
	
			</tbody>
			
			</table>  
           </div>
           
           
<script type="text/javascript">

$(document).ready(function() {
	pageSetUp();
});


function applicationTrackingPopUp(applicationid){
	$("#viewapplicationPhases").empty();
	
	
	var tableData = "";
	$.ajax
	({			
		type : "GET",
		url : "./ncms/applicationTrackingByApplicationId/"+applicationid,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
			for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	
		              	tableData += "<tr>"
								+"<td>"+(s+1)+"</td>"
								+"<td>"+obj.applicationPhase+"</td>"
								+"<td>"+obj.applicationStatus+"</td>"
								+"<td>"+obj.personname+"</td>"
								+"<td>"+obj.doneby+"</td>"
								+"<td>"+obj.submitteddate+"</td>"
								+"<td>"+obj.remarks+"</td>"
								+"</tr>";
				                
				     }
				$('#viewapplicationPhases').append(tableData);
		}
	

	});
	
	dialog = $("#addtabforClarifications").dialog({
		autoOpen : false,
		width : 1100,
		height : 600,
		resizable : false,
		modal : true,
		
	}).dialog("open");	
}



var responsiveHelper_datatable_col_reorder = undefined;
var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	

$('#datatable_fixed_column').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"order": [],
	"bPaginate":true,
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});
 

</script>

