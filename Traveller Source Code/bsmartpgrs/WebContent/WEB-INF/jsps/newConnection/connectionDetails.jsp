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
				<div class="jarviswidget" id="wid-id-bcs6s"
					data-widget-editbutton="false" data-widget-custombutton="false">
					<header>
						<span class="widget-icon"><i class="fa fa-search"></i>
						</span>
						<h2>Search Connection details based on RR Number</h2>

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
						id="widget-billhisst" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
							<span class="widget-icon"><i class="fa fa-table"></i>
							</span>
							
									<h2>
										Connection Details<b>
											</b>
									</h2>
							
						  </header>

						<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
															<tbody id="consumertbody">
															
	
	
														
															
															    <tr>
																	<th colspan="6"><b>RR Number-</b></th>
																</tr>
																
																<tr>
																	<th>Name</th>
																	<td><></td>
																	<th>Address</th>
																	<td></td>
																	<th>Address1</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Place</th>
																	<td></td>
																	<th>LC/Folio</th>
																	<td></td>
																	<th>FC</th>
																	<td></td>
																</tr>
																
																
																
																<tr>
																	<th>Meter Constant</th>
																	<td></td>
																	<th>Corporation</th>
																	<td></td>
																	<th>Area Name</th>
																	<td></td>
																</tr>
																
																
																<tr>
																	<th>SO Code</th>
																	<td></td>
																	<th>Feeder Code</th>
																	<td></td>
																	<th>TC Code</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Pole No</th>
																	<td></td>
																	<th>MR Code</th>
																	<td></td>
																	<th>TOD</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>LD Date</th>
																	<td></td>
																	<th>Ward</th>
																	<td></td>
																	<th>BKWH Meter</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Tariff Desc</th>
																	<td></td>
																	<th>Tariff Code</th>
																	<td></td>
																	<th>Date Of Service</th>
																	<td></td>
																</tr>
																
																
																<tr>
																	<th>Sanctioned KW</th>
																	<td></td>
																	<th>Sanctioned HP</th>
																	<td></td>
																	<th>Contract Demand</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>PD Date</th>
																	<td></td>
																	<th>Taluk</th>
																	<td></td>
																	<th>No.Of Houses/Flats</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Inst Status</th>
																	<td></td>
																	<th>Status Date</th>
																	<td></td>
																	<th>Average</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Reading Day</th>
																	<td></td>
																	<th>Side RRNO</th>
																	<td></td>
																	<th>PF</th>
																	<td></td>
																</tr>
																
																<tr>
																	<th>Telephone</th>
																	<td></td>
																	<th>Panchayath</th>
																	<td></td>
																	<th>No.Of Meters</th>
																	<td></td>
																</tr>
																
																
																
																
															</tbody>
														</table>
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
	 
	 if(rrnumberval=="" || rrnumberval==null){
		 alert("Please Select RR No");
	 }else{
	
		 var htmldata="";
	$.ajax({
		url : "./NCMS/getConsumerDetails",
		type : "GET",
		dataType : "JSON",
		async : false,
		data : {
			rrnumberval : rrnumberval,
			sitecode : res[0]

		},
		success : function(response) {
			var data=response[0];
			
			var tod="NO";
			if(data.tod==0){
				tod="NO";
			}else{
				tod="YES";
			}
			
			var bkwh="NO";
			if(data.bkwh==0){
				bkwh="NO";
			}else{
				bkwh="YES";
			}
			
		     htmldata="<tr>"
				+" <th colspan='6'><b>RR Number-"+data.rrno+"</b></th>"
				+" </tr>"
			
				+" 	<tr>"
				+" <th>Name</th>"
				+" <td>"+data.name+"</td>"
				+" <th>Address</th>"
				+" <td>"+data.address+"</td>"
				+" <th>Address1</th>"
				+" <td></td>"
				+" </tr>"
				+" <tr>"
				+" <th>Place</th>"
				+" <td>"+data.place+"</td>"
				+" <th>LC/Folio</th>"
				+" <td></td>"
				+" <th>FC</th>"
				+" <td>"+data.fc+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Meter Constant</th>"
				+" <td></td>"
				+" <th>Corporation</th>"
				+" <td>"+data.corporation+"</td>"
				+" <th>Area Name</th>"
				+" <td>"+data.villagename+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>SO Code</th>"
				+" <td>"+data.socode+"</td>"
				+" <th>Feeder Code</th>"
				+" <td>"+data.feedercode+"</td>"
				+" <th>TC Code</th>"
				+" <td>"+data.tccode+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Pole No</th>"
				+" <td>"+data.poleno+"</td>"
				+" <th>MR Code</th>"
				+" <td>"+data.mrcode+"</td>"
				+" <th>TOD</th>"
				+" <td>"+tod+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>LD Date</th>"
				+" <td>"+data.lddate+"</td>"
				+" <th>Ward</th>"
				+" <td>"+data.ward+"</td>"
				+" <th>BKWH Meter</th>"
				+" <td>"+bkwh+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Tariff Desc</th>"
				+" <td>"+data.tariffdescription+"</td>"
				+" <th>Tariff Code</th>"
				+" <td>"+data.tccode+"</td>"
				+" <th>Date Of Service</th>"
				+" <td>"+data.dateofservice+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Sanctioned KW</th>"
				+" <td>"+data.sanctionedkw+"</td>"
				+" <th>Sanctioned HP</th>"
				+" <td>"+data.sanctionedhp+"</td>"
				+" <th>Contract Demand</th>"
				+" <td>"+data.contractdemand+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>PD Date</th>"
				+" <td>"+data.pddate+"</td>"
				+" <th>Taluk</th>"
				+" <td>"+data.taluk+"</td>"
				+" <th>No.Of Houses/Flats</th>"
				+" <td>"+data.noofhouses+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Inst Status</th>"
				+" <td></td>"
				+" <th>Status Date</th>"
				+" <td></td>"
				+" <th>Average</th>"
				+" <td>"+data.average+"</td>"
				+" </tr>"
				+" <tr>"
				+" <th>Reading Day</th>"
				+" <td>"+data.readindday+"</td>"
				+" <th>Side RRNO</th>"
				+" <td></td>"
				+" <th>PF</th>"
				+" <td></td>"
				+" </tr>"
			
				+" <tr>"
				+" <th>Telephone</th>"
				+" <td>"+data.telephoneno+"</td>"
				+" <th>Panchayath</th>"
				+" <td>"+data.panchayath+"</td>"
				+" <th>No.Of Meters</th>"
				+" <td></td>"
				+" </tr>";
			$('#consumertbody').html(htmldata);
		}
	});
	if(htmldata==""){
		$('#consumertbody').html("<tr><td>No Record Found</td></tr>");
	}
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