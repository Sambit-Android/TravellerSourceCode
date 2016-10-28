<%@include file="/common/taglibs.jsp"%>


<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>    

<div id="content">
		<c:if test="${not empty updateResult}">
			<script>
						var result = "${updateResult}";
						alert(result);
					</script>
	
			<c:remove var="updateResult" scope="session" />
		</c:if>
				
<section id="widget-grid" class="">
           
	  <div class="row">
		<div class="col-sm-12">
		
		  <div class="col-sm-100"> 
			<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-18" data-widget-editbutton="false" data-widget-colorbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered Applications </h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column" class="table table-striped table-bordered">
					
									        <thead>
									            <tr>
								                    <th>Application&nbsp;Id</th>
								                    <th>Reg.&nbsp;Date&nbsp;&nbsp;</th>
								                    <th data-hide="phone">Name</th>
								                    <th data-hide="phone">City</th>
								                    <th data-hide="phone,tablet">Load(Kw)</th>
								                    <th data-hide="phone,tablet">Load(Hp)</th>
								                    <th data-hide="phone,tablet">Load(Kva)</th>								                    
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">App.&nbsp;Type</th>
								                    <th data-hide="phone,tablet">Nature&nbsp;Of&nbsp;Inst</th>
								                    <th data-hide="phone,tablet">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Locality&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								                    
									            </tr>
									        </thead>
				
									        <tbody>
									        <c:forEach var="app" items="${applicationDetailsByLogin}">
									            <tr>
									                <td><a href='#' onclick="trackApplicationByAppId(${app.applicationid},'${app.paytoclick}')" rel='tooltip' data-placement='right' data-original-title='Click here to Verify Application and enter ARF details'>${app.applicationid}</a></td>
									                <td><fmt:formatDate value="${app.appregdate}" pattern="dd-MM-yyyy"/></td>
									                <td>${app.name}${app.organizationname}</td>
									                <td>${app.city}</td>
									                <td>${app.loadkw}</td>
									                <td>${app.loadhp}</td>
									                <td>${app.loadkva}</td>
									                <td>${app.tariffdesc}</td>
									                <td>${app.applicationtype}</td>
									                <td>${app.natureofinst}</td>
									                <td>${app.locality}</td>
									                
									                
									               
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



$('#redirectData2').click(function () {
	
	window.location.href="./applicationRegistration";
    
 });
 
 
$(document).ready(function() {
	pageSetUp();
});

function trackApplicationByAppId(applicationId,multiconnectionStatus){
	
	window.location.href="./applicationRootVerification?applicationId="+applicationId;
}


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

.input-group-addon{
height: 18px;
 font-size: 13px;
}


  
    
</style>
