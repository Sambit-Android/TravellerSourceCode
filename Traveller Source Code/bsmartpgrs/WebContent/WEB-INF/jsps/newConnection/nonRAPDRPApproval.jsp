<%@include file="/common/taglibs.jsp"%>
<!-- SPARKLINES -->
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">

	    <c:if test="${not empty aeeApproval}">
				<script>
					var result = "${aeeApproval}";
					alert(result);
				</script>
						
			   <c:remove var="aeeApproval" scope="session" />
	    </c:if>

	<div class="row">

		<article class="col-sm-12">

			<div class="jarviswidget  jarviswidget-sortable jarviswidget-color-darken" id="wid-id-rootedit"
				data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i>
					</span>
					<h2>Approval for Name / Tariff / Load Change Approval</h2>

				</header>


				<div style="padding-bottom: 10px; padding-top: 20px;">

					&nbsp;<a class="btn btn-warning" id="NameChangeApproval"
						style="width: 220px; margin-top: -10px;"
						onclick="return nameChangeApproval();"
						title="click here for Name/Transfer Connection Change Approval"><font
						size="2"><b>Name Change Approval (<span id="pendigSpanId" style=""><c:choose><c:when test="${transferInpCount=='0'}">${transferInpCount}</c:when><c:otherwise><blink>${transferInpCount}</blink></c:otherwise></c:choose></span>)</b></font> </a>&nbsp;&nbsp; <a
						class="btn bg-color-pinkDark txt-color-white"
						style="width: 230px; margin-top: -10px;"
						onclick="return tariffChangeApproval();"
						title="click here for Tariff Change Approval"> <font size="2"><b>Tariff Change Approval (<span id="pendigSpanId" style=""><c:choose><c:when test="${tariffInpCounr=='0'}">${tariffInpCounr}</c:when><c:otherwise><blink>${tariffInpCounr}</blink></c:otherwise></c:choose></span>)</b></font>
					</a> &nbsp; <a class="btn bg-color-redLight txt-color-white"
						style="width: 220px; margin-top: -10px;"
						onclick="return loadChangeApproval();"
						title="click here for Load Change Approval"> <font size="2"><b>Load Change Approval (<span id="pendigSpanId" style=""><c:choose><c:when test="${loadInpCount=='0'}">${loadInpCount}</c:when><c:otherwise><blink>${loadInpCount}</blink></c:otherwise></c:choose></span>)</b></font>
					</a> &nbsp;



				</div>





			</div>

		</article>

		<!--Name Change Approval  -->
			<div class="col-sm-12 col-md-12 col-lg-12" id="data12"
		style="padding-left: 13px; padding-right: 11px; padding-bottom: 29px;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-33"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Name Change Approval Applications</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding" id="monthContent" style='overflow-x: scroll;'>
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>

		<!--Tariff Change Approval  -->
		

	<div class="col-sm-12 col-md-12 col-lg-12" id="data13"
		style="padding-left: 13px; padding-right: 11px; padding-bottom: 29px;display: none;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-222"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Tariff Change Approval Applications</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding" id="monthContent1" style='overflow-x: scroll;'>
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	

		<!--Load Change Approval  -->
		
		
		
		<div class="col-sm-12 col-md-12 col-lg-12" id="data14"
		style="padding-left: 13px; padding-right: 11px; padding-bottom: 29px;display: none;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-356"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Load Change Approval Applications</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding" id="monthContent2" style='overflow-x: scroll;'>
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>
						
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
		
		
		
		

	</div>

</div>



<script>
	function nameChangeApproval() {
	
		$('#data12').show();
		$('#data13').hide();
		$('#data14').hide();
		
		
		 $.ajax({
			url : "./getNameChangeApproval",
			type : "GET",
			dataType : "JSON",
			async : false,
			success : function(response) {
				$('.monthTabDetail').remove();
				var dataNew = response;
				var htmlTable = "<div class='monthTabDetail' style='overflow-x: scroll;'>"
						+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover'>"
						+ "<thead>"
						
						+"<tr>"
						+"<th></th>"
						+"<th data-hide='phone'>RR No.</th>"
						+"<th data-class='expand' width='140'>Existing&nbsp;Consumer&nbsp;Name</th>"
						+"<th>Contact No</th>"
						+"<th data-hide='phone'>Transferee Name</th>"
						+"<th data-hide='phone,tablet'>Transferee Mobile No</th>"
						+"<th data-hide='phone,tablet'>Reason</th>"
						+"<th data-hide='phone,tablet'>Reading Date</th>"
						+"<th data-hide='phone,tablet'>Present MR</th>"
					    +"</tr>"
					    + "</thead>" + "<tbody>";
					
					    
					    
				 	   
				    $.each(dataNew, function(index, data) { 

					htmlTable+= "<tr>"
						
						
						+"<td><a class='btn btn-primary' onclick='approveTransferConn("+data.id+")'>Approve</a></td>"
						+"<td>"+data.rrnum+"</td>"
						+"<td>"+data.pConsumerName+"</td>"
						+"<td>"+data.pmobileno+"</td>"
						+"<td>"+data.tconsumername+"</td>"
						+"<td>"+data.tmobileno+"</td>"
						+"<td>"+data.reasonfortranfer+"</td>"
						+"<td>"+data.readingdate+"</td>"
						+"<td>"+data.pmr+"</td>"
					
						+"</tr>";
				 }); 
				htmlTable += "</tbody>";
				$('.monthtab').hide();
				$('#datatable_tabletools_wrapper').hide();
				$('#monthContent').append(htmlTable);

				 pageSetUp();
				 
				 var responsiveHelper_datatable_col_reorder = undefined;
					var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
					
					$('#datatable_tabletools5').dataTable({
						"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
								"t"+
								"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
						"autoWidth" : true,
						"order": [],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_datatable_col_reorder) {
								responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_tabletools5'), breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_datatable_col_reorder.respond();
						}			
					});
				

					},

				});

	}
	
	function approveTransferConn(id){
		
		 $.ajax({
				url : "./approveTransferConnection/"+id,
				type : "GET",
				dataType : "text",
				async : false,
				success : function(response) {
				   alert(response);
				   window.location.reload(true);
				   
				   },

				});
		 
	}

	function tariffChangeApproval() {

		$('#data12').hide();
		$('#data13').show();
		$('#data14').hide();
		
		
		 $.ajax({
				url : "./getTariffChangeApproval",
				type : "GET",
				dataType : "JSON",
				async : false,
				success : function(response) {
					$('.monthTabDetail1').remove();
					var dataNew = response;
					var htmlTable = "<div class='monthTabDetail1' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools7' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							
							+"<tr>"
							+"<th></th>"
							+"<th data-hide='phone'>RR No.</th>"
							+"<th data-class='expand' width='140'>Consumer Name</th>"
							+"<th>Contact No</th>"
							+"<th data-hide='phone'>Existing Nature of Ins</th>"
							+"<th data-hide='phone,tablet'>Existing Tariff</th>"
							+"<th data-hide='phone,tablet'>New Nature of Ins</th>"
							+"<th data-hide='phone,tablet'>New Tariff</th>"
							+"<th data-hide='phone,tablet'>Reason</th>"
							+"<th data-hide='phone,tablet'>Reading Date</th>"
							+"<th data-hide='phone,tablet'>Present MR</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						
					    $.each(dataNew, function(index, data) { 

						htmlTable+= "<tr>"
							+"<td><a class='btn btn-primary' onclick='approveTariffChange("+data.id+")'>Approve</a></td>"
							+"<td>"+data.rrnum+"</td>"
							+"<td>"+data.ConsumerName+"</td>"
							+"<td>"+data.mobileno+"</td>"
							+"<td>"+data.existingNatOfIns+"</td>"
							+"<td>"+data.existingtariff+"</td>"
							+"<td>"+data.newnatofins+"</td>"
							+"<td>"+data.newtariff+"</td>"
							+"<td>"+data.reasonfortranfer+"</td>"
							+"<td>"+data.readingdate+"</td>"
							+"<td>"+data.pmr+"</td>"
						
							+"</tr>";
					 }); 
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent1').append(htmlTable);

					 pageSetUp();
					 
					 var responsiveHelper_datatable_col_reorder = undefined;
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
						
						$('#datatable_tabletools7').dataTable({
							"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
									"t"+
									"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
							"autoWidth" : true,
							"order": [],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_datatable_col_reorder) {
									responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_tabletools7'), breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_datatable_col_reorder.respond();
							}			
						});
					

						},

					});
		 
		 
	}
function approveTariffChange(id){
	

	 $.ajax({
			url : "./approveTariffChange/"+id,
			type : "GET",
			dataType : "text",
			async : false,
			success : function(response) {
			   alert(response);
			   window.location.reload(true);
			   
			   },

			});
}
	
	function loadChangeApproval() {

		$('#data12').hide();
		$('#data13').hide();
		$('#data14').show();

		
		
		 $.ajax({
				url : "./getLoadChangeApproval",
				type : "GET",
				dataType : "JSON",
				async : false,
				success : function(response) {
					$('.monthTabDetail2').remove();
					var dataNew = response;
					var htmlTable = "<div class='monthTabDetail2' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
							+ "<thead>"
							+"<tr>"
							+"<th></th>"
							+"<th data-hide='phone'>RR&nbsp;No.</th>"
							+"<th data-class='expand' width='140'>Consumer Name</th>"
							+"<th>Contact No</th>"
							+"<th data-hide='phone'>Existing Load(KW)</th>"
							+"<th data-hide='phone,tablet'>Existing Load(HP)</th>"
							+"<th data-hide='phone,tablet'>Existing Load(KVA)</th>"
							+"<th data-hide='phone,tablet'>Load Type</th>"
							+"<th data-hide='phone,tablet'>New Load(KW)</th>"
							+"<th data-hide='phone,tablet'>New Load(HP)</th>"
							+"<th data-hide='phone,tablet'>New Load(KVA)</th>"
							+"<th data-hide='phone,tablet'>Reason</th>"
							+"<th data-hide='phone,tablet'>Reading Date</th>"
							+"<th data-hide='phone,tablet'>Present MR</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
					 	   
					    $.each(dataNew, function(index, data) { 

						htmlTable+= "<tr>"
							+"<td><a class='btn btn-primary' onclick='approveLoadChange("+data.id+")'>Approve</a></td>"
							+"<td>"+data.rrnum+"</td>"
							+"<td>"+data.ConsumerName+"</td>"
							+"<td>"+data.mobileno+"</td>"
							+"<td>"+data.existingloadkw+"</td>"
							+"<td>"+data.existingloadhp+"</td>"
							+"<td>"+data.existingloadkva+"</td>"
							+"<td>"+data.loadrequired+"</td>"
							+"<td>"+data.newloadkw+"</td>"
							+"<td>"+data.newloadhp+"</td>"
							+"<td>"+data.newloadkva+"</td>"
							+"<td>"+data.reasonfortranfer+"</td>"
							+"<td>"+data.readingdate+"</td>"
							+"<td>"+data.pmr+"</td>"
						
							+"</tr>";
					 }); 
					htmlTable += "</tbody>";
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent2').append(htmlTable);

					 pageSetUp();
					 
					 var responsiveHelper_datatable_col_reorder = undefined;
						var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
						
						$('#datatable_tabletools6').dataTable({
							"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
									"t"+
									"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
							"autoWidth" : true,
							"order": [],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_datatable_col_reorder) {
									responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_tabletools6'), breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_datatable_col_reorder.respond();
							}			
						});
					

						},

					});
		 
		 
	}

	
	function approveLoadChange(id){
		
		 $.ajax({
				url : "./approveLoadChange/"+id,
				type : "GET",
				dataType : "text",
				async : false,
				success : function(response) {
				   alert(response);
				   window.location.reload(true);
				   
				   },

				});
	}
	
	

	$(document).ready(function() {
		pageSetUp();
		nameChangeApproval();
		
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
</style>
