<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">
	
	<section id="widget-grid" class="">
		

		<div class="row">

			
			
			<div class="col-sm-12 ">
				<div class="jarviswidget" id="wid-id-bhis"
					data-widget-editbutton="false" data-widget-custombutton="false">
					<header>
						<span class="widget-icon"><i class="fa fa-search"></i>
						</span>
						<h2>Search Deposit History based on RR Number</h2>

					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<form action="" method="post" id="customer-view"
								class="smart-form">


								<fieldset>
									<div class="row">
										
										  <section class="col col-3">
											<label class="label">RR Number&nbsp;<font color="red">*</font></label> <label
												class="select"> 
												
											<select id="rrnumber" name="rrnumber" class="form-control">
												<option value="" selected="" disabled="">Select</option>
												<c:forEach items="${rrNoList}" var="section">
													<option value="${section.sitecode}-${section.rrnum}">${section.rrnum}</option>
												</c:forEach>
											</select>
											
											
											<i></i></label>
											</section>
										
									

										<section class="col col-2">
											<button type="button" onclick="searchApplicationNo()"
												class="btn btn-primary"
												style="height: 31px; width: 150px; margin-left: 0px; margin-top: 24px;">Search</button>
										</section>

									</div>


								</fieldset>


							</form>

						</div>

					</div>

				</div>
		</div>
			<article class="col-sm-12 ">
				
					<div class="jarviswidget"
						id="widget-billhistory" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
							<span class="widget-icon"><i class="fa fa-table"></i>
							</span>
							
									<h2>
										Deposit History for RR No -<span id="billidspan"></span> <b>
											</b>
									</h2>
							
						  </header>

						<div>
							<div class="jarviswidget-editbox"></div>
							
							<div class="widget-body no-padding" id="monthContent" style='overflow-x: scroll;'>
										
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>

											<th>Receipt Date</th>
											<th>Receipt No</th>
											<th>Amount</th>
											<th>Remarks</th>
											<th>Status</th>
											
											
										</tr>
									</thead>
									<tbody id="deposit">
										


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
	});
	
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
	
	

	function searchApplicationNo()
	{
		
		    var rrn=document.getElementById("rrnumber").value;
		    var res = rrn.split("-");
			var rrnumberval=res[1];
			$('#billidspan').html(rrnumberval);
			
		 if(rrnumberval=="" || rrnumberval==null){
			 alert("Please Select RR No");
		 }else{
		
			
			$.ajax({
				url : "./NCMS/getDepositDetailsByRRNo",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					rrnumberval : rrnumberval,
					sitecode : res[0]
				},
				success : function(response) {
					$('.monthTabDetail1').remove();
					var dataNew = response;
					var htmlTable = "<br><br><div class='monthTabDetail1' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
							+ "<thead>"
							+"<tr>"
							+"<th data-hide='phone' width='40'>Receipt Date</th>"
							+"<th data-hide='phone'>Receipt No</th>"
							+"<th data-class='expand'>Amount</th>"
							+"<th>Remarks</th>"
							+"<th data-hide='phone'>Status</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						   
					$.each(dataNew, function(index, data) {
						
						htmlTable+= "<tr>"
							+" <td>"+data.recdate+"</td>"
							+" <td>"+data.recno+"</td>"
							+" <td>"+data.amount+"</td>"
							+" <td></td>"
							+" <td>"+data.status+"</td>"
							
							+"</tr>";
						
					});
					htmlTable += "</tbody>";
					
						
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').html(htmlTable);

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
</style>