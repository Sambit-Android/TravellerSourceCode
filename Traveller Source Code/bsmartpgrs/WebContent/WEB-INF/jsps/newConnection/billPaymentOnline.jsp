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
						<h2>Search for Bill Payments based on RR Number</h2>

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
										Bill Payments for RR No - <span id="billidspan"></span><b>
											</b>
									</h2>
							
						  </header>

						<div>
							<div class="jarviswidget-editbox"></div>
							<div class="widget-body no-padding" id="monthContent1" style='overflow-x: scroll;'>
								
								
								
								
									
									
								   <!-- <form action="https://pgi.billdesk.com/pgidsk/PGIMerchantPayment" method="POST" name="postForm">
										
										<input type="text" hidden="true" name="msg" value="CESCMYSORE|ARP10234|NA|2.00|NA|NA|NA|INR|NA|R|cescmysore|NA|NA|F|NA|2150|BEGUR|NA|NA|NA|NA|http://122.166.217.53:8101/pgrs/onlinepayment/returnPage|EBB757D31F00E8B738CB24F84044895264616EB10A6A47B9850F90AFC1D5E753">
										
										<input type="submit" value="Pay Online">
										
								     </form>
								 
								
								
								 -->
								
								
								
									
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>

											
											<th>Bill Date</th>
											<th>Bill No</th>
											<th>FR</th>
											<th>IR</th>
											<th>Units</th>
											<th>Status</th>
											<th>FC</th>
											<th>EC</th>
											<th>FAC</th>
											<th>DL Adj</th>
											<th>Arrears</th>
											<th>Tax</th>
										    <th>Net</th>	
											<th>Due Date</th>
											<th></th>
											
											
										</tr>
									</thead>
									<tbody id="billpayonline">
										

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

	
	
	function payOnline(ac,amt,name){
		
		var rrn=document.getElementById("rrnumber").value;
	    var res = rrn.split("-");
		var rrno=res[1];
		
		window.location.href="./billPayOnline/?ac="+ac+"&rr="+rrno+"&amt="+amt+"&name="+name+"&sitecode="+res[0]; 

	}
	
	
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
		 
			;
		 $.ajax({
						url : "./NCMS/getBillHostoryByRRNo",
						type : "GET",
						dataType : "JSON",
						async : false,
						data : {
							rrnumberval : rrnumberval,
							sitecode : res[0]
						},
						success : function(response) {
							var dataNew = response;
							var htmlTable = "<br><br><div class='monthTabDetail1' style='overflow-x: scroll;'>"
								+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
								+"<thead>"
								+"<tr>"
								+"<th>Bill Date</th>"
								+"<th>Bill No</th>"
								+"<th>FR</th>"
								+"<th>IR</th>"
								+"<th>Units</th>"
								+"<th>Status</th>"
								+"<th>FC</th>"
								+"<th>EC</th>"
								+"<th>FAC</th>"
								+"<th>DL Adj</th>"
								+"<th>Arrears</th>"
								+"<th>Tax</th>"
								+"<th>Net</th>"
								+"<th>Due Date</th>"
							    +"</tr>"
							    + "</thead>" + "<tbody>";
						 
							  
								$.each(dataNew, function(index, data) {
									
									if(index==dataNew.length-1){
									htmlTable+= "<tr>"
										+" <td>"+data.billdate+"</td>"
										+" <td>"+data.billno+"</td>"
										+" <td>"+data.presentreading+"</td>"
										+" <td>"+data.previousreading+"</td>"
										+" <td>"+data.units+"</td>"
										+" <td>"+data.meterstatus+"</td>"
										+" <td>"+data.fcamt+"</td>"
										+" <td>"+data.ecamt+"</td>"
										+" <td>"+data.fac+"</td>"
										+" <td>"+data.dladjgiven+"</td>"
										+" <td>"+data.arrears+"</td>"
										+" <td>"+data.tax+"</td>"
										+" <td>"+data.netamtdue+"</td>"
										+" <td><a class='btn btn-primary' onclick=payOnline("+data.accountid+","+data.netamtdue+",'"+data.name+"')>Pay Online</a></td>"
										+"</tr>";
									}
								});
							 
							 htmlTable += "</tbody>";
							  $('.monthtab').hide();
							  $('#datatable_tabletools_wrapper').hide();
							  $('#monthContent1').html(htmlTable);
							  
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
						}
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