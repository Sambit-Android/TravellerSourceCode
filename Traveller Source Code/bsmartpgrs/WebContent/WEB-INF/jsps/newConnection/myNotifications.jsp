<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>

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
			<div class="jarviswidget" id="wid-id-55" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Application Notifications</h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>Application&nbsp;ID</th>
											<th>Sent&nbsp;On&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
											<th>Subject</th>
											<th>Notification</th>
											<th>Sent&nbsp;Via</th>
											<th>Mobile/Email</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="app" items="${myNotificationDetails}">
											
												<tr>
												<td>${app.applicationid}</td>
												<%-- <td class='appClass'><button type="submit" class="btn btn-primary"  style="margin-left: 13px;padding-bottom: 2px; padding-top: 0;"><strong>${app.applicationid}</strong></button></td> --%>
												<td><fmt:formatDate value="${app.senton}" pattern="dd-MM-yyyy" /></td>
												<td>${app.subject}</td>
												<td>${app.message}</td>
												<td>
													<c:if test="${app.nottificationtype=='E'}">E-Mail</c:if>
													<c:if test="${app.nottificationtype=='S'}">SMS</c:if>
												</td>
												<td>
													<c:if test="${app.nottificationtype=='E'}">${app.email}</c:if>
													<c:if test="${app.nottificationtype=='S'}">${app.mobileno}</c:if>
												</td>
												
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
	"bPaginate":true,
	"sPaginationType":"full_numbers",
	"iDisplayLength": 05,
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
