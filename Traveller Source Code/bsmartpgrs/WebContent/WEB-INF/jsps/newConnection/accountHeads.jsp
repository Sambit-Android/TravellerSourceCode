<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">
<section id="widget-grid" class="">

	<div class="row">
		<div class="col-sm-12">

			<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-29"
				data-widget-editbutton="false" >



				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Account Heads  Details</h2>

				</header>

				<div>

					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">

						<table id="datatable_fixed_column"
							class="table table-striped table-bordered" width="100%">

							<thead>

								<tr>
									<th>Account Heads</th>
									<th>Description</th>
									<th>Remarks</th>
								
									
							    </tr>
							</thead>

							<tbody>
								<c:forEach var="app" items="${accountHeadsDetails}">
									<tr>

										<td>${app.accountheads}</td>
										<td>${app.description}</td>
										<td>${app.remarks}</td>
										
										
									</tr>
								</c:forEach>
							</tbody>

						</table>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
		</div>
	</div>
	</section>
</div>


<script type="text/javascript">
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



 
 
$(document).ready(function() {
	pageSetUp();
});





</script>
<style>
th {
	vertical-align: middle !important
}

.popover-content {
	overflow: overlay;
	max-height: 250px;
}

.sparkline {
	width: 100% !important
}

.easy-pie-title {
	overflow: visible !important;
}
</style>

