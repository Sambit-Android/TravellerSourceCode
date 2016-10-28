<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<!-- SPARKLINES -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
<section id="widget-grid" class="">
	<div class="row">
		<div class="col-sm-12">
				        
		     
		  <div class="col-sm-100"> 
			<div class="jarviswidget" id="wid-id-55" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa fa-envelope-o"></i> </span>
									<h2>Employee Notifications</h2>
				
								</header>
								<div>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">
									<thead>
										<tr>
											<th>Application ID</th>
											<th>Sent On</th>
											<th>Subject</th>
											<th>Notification</th>
											<th>Sent Via</th>
											<th>Mobile/Email</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="app" items="${employeeNotificationDetails}">
											
											<c:choose>
											<c:when test = "${app.status==0}"> 
												<tr onclick="notificationUpdateStatus(${app.id})">
												<td style="color: maroon;font-size:14px;background: #FFFAF0">${app.applicationid}</td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0"><fmt:formatDate value="${app.senton}" pattern="dd-MM-yyyy" /></td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0">${app.subject}</td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0">${app.message}</td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0">
													<c:if test="${app.nottificationtype=='E'}">E-Mail</c:if>
													<c:if test="${app.nottificationtype=='S'}">SMS</c:if>
												</td>
												<td style="color: maroon;font-size:14px;background: #FFFAF0">
													<c:if test="${app.nottificationtype=='E'}">${app.email}</c:if>
													<c:if test="${app.nottificationtype=='S'}">${app.mobileno}</c:if>
												</td>
												
												</tr>
											</c:when>
											<c:otherwise>
											<tr>
												<td>${app.applicationid}</td>
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
											
											
											</c:otherwise>
											</c:choose>
											
											
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

function notificationUpdateStatus(notificationId)
{
	
	$.ajax({
	type : "POST",
	url : "./notificationUpdate/updateStatus/"+notificationId,
	async: false,
	dataType : "text",
	success:function(response){
  			
			window.location.reload(true);
		 }
});

}

 
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
