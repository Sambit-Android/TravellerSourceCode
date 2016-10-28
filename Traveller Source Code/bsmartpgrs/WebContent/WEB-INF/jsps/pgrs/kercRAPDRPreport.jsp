<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/exportToExcel.js"></script>

<div id="content">
			<div class="jarviswidget" id="wid-idkercRAPDRP" data-widget-editbutton="false" data-widget-custombutton="false">
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<form:form id="order-form" action="#" method="POST" class="smart-form" novalidate="novalidate">
							<fieldset>
								<div class="row">	
									 
										<section class="col col-1" id="townNameIds">
											<label class="radio">
												<input type="radio" name="commonname" value="TownName" id="townNameId" checked="checked">
												<i></i>Towns</label>
											
										</section>
										
										<section class="col col-1" id="divisionIds">
											<label class="radio">
												<input type="radio" name="commonname" value="Division" id="divisionId" >
												<i></i>Division</label>
											
										</section>
										<section class="col col-1" id="subDivisionIds">
												<label class="radio">
													<input type="radio" name="commonname" value="Subdivision" id="subDivisionId">
													<i></i>Sub&nbsp;Division</label>
										</section>
										<section class="col col-1" id="sectionIds" style="padding-left: 29px;">
												<label class="radio">
													<input type="radio" name="commonname" value="Section" id="sectionId">
													<i></i>Section</label>
										</section>
										
										
										
								
										<section class="col col-2">
											<label class="input">
												<input type="text" name="selectedMonth" id="selectedMonth" placeholder="Select Month">
											</label> 
											
										</section>
									
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;"  onclick="return loadData()">Generate Report</button>
									</section>
									
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div> 
			
	<div class="col-sm-12 col-md-12 col-lg-12" id="data12"
		style="padding-left: 13px; padding-right: 11px; padding-bottom: 29px;">
		<div class="row">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
				data-widget-editbutton="false">
				<header>
					<span class="widget-icon"> <i></i>
					</span>
					<h2></h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'KERC Report')">Export to Excel</a>
					
					<div class="widget-body no-padding" id="monthContent" style='overflow-x: scroll;'>
						<div style='text-align: center'>
							<font size='5px'></font>
						</div>		
						<div  class="dropzone" id="mydropzone"><center style="padding-top: 10%;"><span style="font-size: 40px; color: #CBCBC5">Generate KERC  Report for (RAPDRP) towns By Selecting Month</span></center></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
				
<div id="alertsBox" title="Alert"></div>				

<script>
	$(document)
			.ready(
					function() {
						
						pageSetUp();
					
						$("#selectedMonth").keypress(function(event) {event.preventDefault();});
						  pageSetUp();
						  $("#selectedMonth").kendoDatePicker({
						        start: "decade",
						        depth: "year",
						        format: "MMM-yyyy",
						        min: new Date(2015, 1, 1),
						          max: new Date(),
						          readonly : true,
						    }); 
					$('#excelExport').hide();

					});
	
	var selectedMonth="";
	var type="";
	function loadData() {
		
		selectedMonth = $('#selectedMonth').val();
		
		var y = document.getElementById('divisionId').checked;
		var z = document.getElementById('subDivisionId').checked;
		var s = document.getElementById('sectionId').checked;
		var r = document.getElementById('townNameId').checked;
		
		if(y){
			type=$('#divisionId').val();
			
		}
		if(z){
			type=$('#subDivisionId').val();
			
		}
		if(s){
			type=$('#sectionId').val();
		}
		if(r){
			type=$('#townNameId').val();
		}
		
		if(selectedMonth==""){
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Please select Month");
			$("#alertsBox").dialog({
				modal : true,
				draggable: false,
				resizable: false,
				buttons : {
					"Close" : function() {
					$(this).dialog("close");
				 }
			    }
		    });
			return false;
		}
		
	   else{
			$.ajax({
				url : "./reports/KERCRAPDRPReport",
				type : "GET",
				dataType : "JSON",
				async : false,
				data : {
					selectedMonth : selectedMonth,
					type : type,
				},
				success : function(response) {
					$('.monthTabDetail1').remove();
					var dataNew = response;
					var totalPenPrev=0;
					var totalReg=0;
					var totalPendCom=0;
					var totalResCom=0;
					var totalyetToCom=0;
					var totalwithinCom=0;
					var totalwithoutCom=0;
					var totalPercentage=0;
					
					var htmlTable = "<br><br><div class='monthTabDetail1' style='overflow-x: scroll;'>"
							+ "<table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
							+ "<thead>"
							+"<tr>"
						    +"<th colspan='14' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Location : "+type+"<br> KERC Report for (RAPDRP) Towns<br>Name of State : Karnataka&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Frequency : Monthly<br>Name of Discom : CESC&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selected Month : "+selectedMonth+"</th>"
						    +"</tr>"
							+"<tr>"
							+"<th data-hide='phone' width='40'>Sl No</th>"
							+"<th data-hide='phone'>Name of Town</th>"
							+"<th data-class='expand'>Complaints pending from previous period</th>"
							+"<th>Complaints registered in current period</th>"
							+"<th data-hide='phone'>Total pending complaints</th>"
							+"<th data-hide='phone,tablet'>Complaints closed in current period</th>"
							+"<th data-hide='phone,tablet'>Complaints pending period&nbsp;(Hr:min)</th>"
							+"<th data-hide='phone,tablet'>Complaints yet to be closed</th>"
							+"<th data-hide='phone,tablet'>Complaints closed within KERC time limit</th>"
							+"<th data-hide='phone,tablet'>Complaints closed beyond KERC time limit</th>"
							+"<th data-hide='phone,tablet'>% of complains closed within KERC time limit</th>"
						    +"</tr>"
						    + "</thead>" + "<tbody>";
						    var i=1;
					$.each(dataNew, function(index, data) {
						var town='"'+data.townName+'"';
						htmlTable+= "<tr>"
							+"<td>"+(i++)+"</td>"
							+"<td>"+data.townName+"</td>"
							+"<td>"+data.pendingFromPrev+"</td>"
							+"<td>"+data.currentRegistered+"</td>"
							+"<td>"+data.totalPending+"</td>"
							+"<td>"+data.resovledComplaints+"</td>"
							+"<td>"+data.pendingPeriod+"</td>"
							+"<td><a href='#' rel='tooltip' data-placement='top' data-original-title='Click to view "+data.townName+" pending complaints' onclick=getComplaintClosed('"+data.siteCode+"',"+data.year+","+data.month+","+town+")>"+data.complaintsYetToClosed+"</a></td>"
							+"<td>"+data.withinKERC+"</td>"
							+"<td>"+data.beyondKERC+"</td> "
							+"<td>"+data.percentWitninKERC+"</td>"
							+"</tr>";
						totalPenPrev+=data.pendingFromPrev;
						totalReg+=data.currentRegistered;
						totalPendCom+=data.totalPending;
						totalResCom+=data.resovledComplaints;
						totalwithinCom+=data.withinKERC;
						totalwithoutCom+=data.beyondKERC;
						totalyetToCom+=data.complaintsYetToClosed;
						totalPercentage+=data.percentWitninKERC;
						
					});
					htmlTable += "</tbody>";
					htmlTable += "<tfoot>"
						+ "<tr>"
						+ " <th></th>"
						+ " <th>Grand&nbsp;Total:</th>" 
						+ " <th>"+totalPenPrev+"</th>"
						+ " <th>"+totalReg+"</th>"
						+ " <th>"+totalPendCom+"</th>"
						+ " <th>"+totalResCom+"</th>"
						+ " <th></th>"
						+ " <th>"+totalyetToCom+"</th>"
						+ " <th>"+totalwithinCom+"</th>"
						+ " <th>"+totalwithoutCom+"</th>"
						+ " <th>"+(totalPercentage/(i-1)).toFixed(2)+"&nbsp;%</th>"
					
						+ " </tr>"
						+ "</tfoot>";
						
					$('.monthtab').hide();
					$('#datatable_tabletools_wrapper').hide();
					$('#monthContent').html(htmlTable);

					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="KERC RAPDRP Towns Report";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                                       Location : "+type+"                                                                                                                                              "+reportname+"                                                                                                                                	  	     Selected Month : "+selectedMonth+"                                                                                                                            ";
						var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};
				$('#datatable_tabletools5')
				.dataTable(
				{
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-4'f><'col-sm-4 col-xs-4 hidden-xs'T><'col-sm-2 col-xs-2 hidden-xs'C>l>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-4 col-xs-12 hidden-xs'i><'col-sm-4 col-xs-12'p>>",
						  "oTableTools": {
						     	 "aButtons": [
						          
						             {
						                 "sExtends": "pdf",
						                 "sButtonText":"<font color='#FF0000' size='3px'><i class='fa fa-file-pdf-o'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Export to Pdf</font>",
						                 "sFileName": reportname+".pdf",
						                 "sPdfMessage":lines,
						                 "sPdfSize": "letter"
						                 
						             },
						            
						          	{
						             	"sExtends": "print",
						             	"sButtonText":"<font color='#000000' size='3px'><i class='fa fa-print'></i></font>&nbsp;&nbsp;<font color='blue' size='3px'>Print</font>",
						             	"sMessage": "Generated by PGRS_CESC <i>(press Esc to close)</i>"
						         	}
						          ],
						         "sSwfPath": "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
						     },
					"autoWidth" : true,
					"aLengthMenu": [[10, 15, 25, 50, 100 , -1], [10, 15, 25, 50, 100, "All"]],
					"order": [],
					"preDrawCallback" : function() {
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
					},
					"aoColumnDefs": [{
		                'bSortable': false,
		                'aTargets': [0]
		            }
		        ]
				});
				$('#selectedMonth').val("");
				},

			});
		}
		$('#excelExport').show();
		return false;
	}
	function getComplaintClosed(sitecode,year,month,townName){
		$('.monthTabDetail1').hide();		
		$('#datatable_tabletools_wrapper').hide();
		$('#excelExport').hide();
		$.ajax({
			url : "./reports/getComplaintClosed",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				sitecode : sitecode,
				month : month,
				year : year,
			},
			success : function(response) {
			$('.monthTabDetail').remove();
			$('#datatable_tabletools').remove();
			var tableexport='"datatable_tabletools"';
			var reportName='"KERC_Report"';
			var htmlTable = "<div class='monthTabDetail' style='overflow: scroll; margin-top: -20px;'><a href='javascript:void(0);' onclick='backToMonth()' style='float:right;' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>Back to Main Report</a>"
			+"<a class='btn btn-primary'  href='#'  onclick='return tableToExcel1("+tableexport+","+reportName+")' id='excelExport1'>Export to Excel</a>"  
			+"<div style='text-align:center'><font size='5px'></font></div>"	
			+"<br><table id='datatable_tabletools' width='100%' class='table table-striped table-bordered table-hover'>"
			+"<thead>"
			+"<tr>"
		    +"<th colspan='9' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Location : "+type+"( "+townName+" )<br> KERC Report(Pending Complaints) <br>Name of State : Karnataka&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Frequency : Monthly<br>Name of Discom : CESC&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Selected Month : "+selectedMonth+"</th>"
		    +"</tr>"
			+"<tr>"
			+"<th data-hide='phone'>Docket No.</th>"
			+"<th data-hide='phone'>Status</th>"
			+"<th data-class='expand'>Created Date</th>"
			+"<th>Category</th>"
			+"<th data-hide='phone'>SubCategory</th>"
			+"<th data-hide='phone,tablet'>Summary</th>"
			+"<th data-hide='phone,tablet'>Mode</th>"
			+"<th data-hide='phone,tablet'>Consumer Name</th>"
			+"<th data-hide='phone,tablet'>Mobile No.</th>"
		    +"</tr>"
					+"</thead>"
					+"<tbody>";
					var dataNew=response;
					 $.each(dataNew, function(index, data){
						
						htmlTable+= "<tr>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketStatus+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.docketSource+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"</tr>";
						 
		         		});
				htmlTable +=""		
					+"</tbody>"
					+"</table></div>";
				
			$('.monthtab').hide();
			$("#dt_basic_wrapper").hide();
			$('#monthContent').append(htmlTable);
			
			
			 pageSetUp();
				
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
		$('#datatable_tabletools')
		.dataTable(
		{
			"autoWidth" : true,
			"aLengthMenu": [[10, 15, 25, 50, 100 , -1], [10, 15, 25, 50, 100, "All"]],
			"order": [],
			"preDrawCallback" : function() {
				if (!responsiveHelper_datatable_tabletools) {
					responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
							$('#datatable_tabletools'),
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
			},
			"aoColumnDefs": [{
             'bSortable': false,
             'aTargets': [0]
         }
     ]
		});
			}
		});
	}
	function backToMonth(tab){
		$('.monthtab').show();
		$('#excelExport').show();
		$('.monthTabDetail').remove();
		$('#datatable_tabletools_wrapper').hide();
		$('.monthTabDetail1').show();
		$("#dt_basic_wrapper").show();
	}
</script>
<style>
#content {
    padding: 2px 0px;
    position: relative;
}
.jarviswidget {
    margin: 0px 0px 0px;
}
.smart-form section {
    margin-bottom: 0px;
    position: relative;
}
.smart-form .state-error + em {
    margin-top: -2px;
    padding: 0px 0px; 
}
</style>		