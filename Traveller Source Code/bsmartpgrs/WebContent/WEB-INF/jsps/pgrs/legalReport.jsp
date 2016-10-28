<%@include file="/common/taglibs.jsp"%>


<c:url value="/legalDetails/reads" var="readUrl" />

<!-- child url -->
<c:url value="/legalDetails/readHearingUrl" var="readHearingUrl" />

<!-- second child url -->
<c:url value="/legalDetails/readDocumentUrl" var="readDocumentUrl" />
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>	
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/exportToExcel.js"></script>

<div id="content">

<div class="jarviswidget" id="wididSearch" data-widget-editbutton="false" data-widget-custombutton="false">
				
				

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form id="orderform123" action="#" method="POST" class="smart-form" novalidate="novalidate">
							<fieldset>
								

								<div class="row">
								
								   <section class="col col-2">
										<label class="select">
											<select  id="circleSiteCode" name="circleSiteCode">
												<option value="0">Select Circle</option>
  												<c:forEach items="${circleList}" var="circles">
												<option value="${circles.circleSiteCode}">${circles.circleName}</option>
												</c:forEach>
										    </select><i></i>
										</label>
									</section>
									<section class="col col-2">
										<label class="select">
											<select  id="divisionSiteCode" name="divisionSiteCode">
												<option value="0" selected="" disabled="">Select Division</option>
										    </select><i></i></label>
									</section>
									
									<section class="col col-2">
										<label class="select">
											<select  name="siteCode" id="siteCode">
												<option value="0" selected="" disabled="">Select Sub Division</option>
											</select> <i></i></label>
									</section>
								
								    <section class="col col-2">
									
										<label class="select">
											<select  name="siteCodeId" id="siteCodeId">
												<option value="0" selected="" disabled="">Select Section</option>
											</select> <i></i></label>
									</section>
								
								    <section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label> 
										
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
									<section class="col col-2">
											<label class="select"> <select name="status" id="status" >
										<option value="" selected="" disabled="disabled">Select Case Status</option>
										<option value="InProgress">InProgress</option>
										<option value="Dismissed">Dismissed</option>
										<option value="Closed">Closed</option>
									</select> <i></i></label>
									<br>
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" id="caseNumber" name="caseNumber" placeholder="Case Number">
										</label>
									</select> <i></i></label>
									<br>
									</section>
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;" onclick="return getDateWiseData()">Search</button>
									</section>
									
									<!-- <section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 150px;" onclick="return generatePDFReport()">PDF Report</button>
									</section> -->
									
								</div>

								
							</fieldset>
							
						</form>

					</div>
					
					
				</div>
				<!-- end widget div -->
				
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
						 <a class="btn btn-primary" id="excelExport" href="#" onclick="tableToExcel('datatable_tabletools5', 'Legal Report')">Export to Excel</a>
						
						<div class="widget-body no-padding" id="monthContent" style='overflow-x: scroll;padding-top: '>
							<div style='text-align: center'>
								<font size='5px'></font>
							</div>		
							<div  class="dropzone" id="mydropzone"><center style="padding-top: 10%;"><span style="font-size: 40px; color: #CBCBC5">Generate Legal Report By Selecting From & To Date</span></center></div>
						</div>
					</div>
				</div>
			</div>
		</div>
			
		
	
	
	<!-- KENDO GRID  -->
	<%-- <kendo:grid name="legalCaseDetails"  change="onChange" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" edit="requestByEvent" scrollable="true" filterable="true">detailTemplate="legalCaseTemplate"
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="5" input="true" numeric="true"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
	    	<kendo:grid-filterable-operators>
			 	<kendo:grid-filterable-operators-string eq="Is equal to" contains="Contains"/>
	    	</kendo:grid-filterable-operators>
	  	</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this Concierge Vendor Service?" />
		<kendo:grid-columns>
			<kendo:grid-column title="Location &nbsp;*" field="location" filterable="true" width="280px"/>
			<kendo:grid-column title="Case Number &nbsp;*" field="case_number" filterable="true" width="130px"/>
			<kendo:grid-column title="Nature&nbsp;*" field="nature" editor="natureEditor" filterable="true" width="80px"/>
			<kendo:grid-column title="Year&nbsp;*" field="year" filterable="true" format="{0:yyyy}" width="80px"/>
			<kendo:grid-column title="Name of the Court/Place&nbsp;" field="name_of_court" filterable="true" width="180px"/>
			<kendo:grid-column title="Plaintiff / Dependent <br>(Names)&nbsp;" field="dependent_name" filterable="true" width="150px"/>
			<kendo:grid-column title="Petitioner / Appellant <br>(Names)&nbsp;" field="appellant_name" filterable="true" width="150px"/>
			<kendo:grid-column title="Name of the Panel Lawyer <br>/ Advocate / Counted / Firm&nbsp;" field="advocate_name" filterable="true" width="200px"/>
			<kendo:grid-column title="Name of the Designation Litigation / <br>Case Conduction officer&nbsp;" field="case_conduction_officer" filterable="true" width="230px"/>
			<kendo:grid-column title="Case Details in Brief&nbsp;" field="case_details" editor="caseDetailsEditor" filterable="true" width="150px"/>
			<kendo:grid-column title="Status&nbsp;" field="case_status" filterable="true" width="150px"/>
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema parse="parse">
				<kendo:dataSource-schema-model id="legalId">
					<kendo:dataSource-schema-model-fields>
						<kendo:dataSource-schema-model-field name="legalId" type="Number" />
						<kendo:dataSource-schema-model-field name="case_number" type="string" />
						<kendo:dataSource-schema-model-field name="nature" type="string" />
						<kendo:dataSource-schema-model-field name="year" type="date" />
						<kendo:dataSource-schema-model-field name="name_of_court" type="string" />
						<kendo:dataSource-schema-model-field name="dependent_name" type="string" />
						<kendo:dataSource-schema-model-field name="appellant_name" type="string" />
						<kendo:dataSource-schema-model-field name="advocate_name" type="string" />
						<kendo:dataSource-schema-model-field name="case_conduction_officer" type="string" />
						<kendo:dataSource-schema-model-field name="case_details" type="string" />
						<kendo:dataSource-schema-model-field name="case_status" type="string" defaultValue="Created"/>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid> --%>

	 
 <%-- <kendo:grid-detailTemplate id="legalCaseTemplate">
	<kendo:tabStrip name="tabStrip_#=legalId#">
	<kendo:tabStrip-animation>
				
	</kendo:tabStrip-animation>
	<kendo:tabStrip-items>
	<kendo:tabStrip-item text="Details of the Case" selected="true">
	<kendo:tabStrip-item-content>
	<div class="wethear" style="width: 60%;">
	<kendo:grid name="hearingGrid_#=legalId#" pageable="true" edit="childRequestByEvent" resizable="true" sortable="true" reorderable="true" selectable="true" filterable="true" scrollable="true">
			<kendo:grid-pageable pageSize="10"></kendo:grid-pageable>
			<kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
		<kendo:grid-columns>
		         <kendo:grid-column title="Date of hearing*" field="date_of_hearing" width="120px" format="{0:dd/MM/yyyy}" filterable="true" />
		         <kendo:grid-column title="Proceedings *" field="proceedings" width="150px" filterable="false" editor="proceedingsEditor"/>	  
		         <kendo:grid-column title="Appeals if Any *" field="appeals" width="150px" editor="appealEditor" filterable="false" />	  
		         <kendo:grid-column title="Case Number *" field="appeal_case_number" width="150px" filterable="false"/>	  
				 <kendo:grid-column title="&nbsp;" width="300px">
			     <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="upload" text="Upload Doc" click="uploadAsset"  />
				  <kendo:grid-column-commandItem name="view" text="View Doc" click="downloadFile" />
			   </kendo:grid-column-command>
			</kendo:grid-column>	
					
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onChildRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-read url="${readHearingUrl}/#=legalId#" dataType="json" type="POST" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema parse="childparse">
				<kendo:dataSource-schema-model id="casedetailid">
					<kendo:dataSource-schema-model-fields>
					 <kendo:dataSource-schema-model-field name="casedetailid" type="number"/>
					 <kendo:dataSource-schema-model-field name="legalId" type="number"/>
					<kendo:dataSource-schema-model-field name="date_of_hearing" type="date" /> 
					  <kendo:dataSource-schema-model-field name="proceedings" type="string" />
					  <kendo:dataSource-schema-model-field name="appeals" type="string" />
					  <kendo:dataSource-schema-model-field name="appeal_case_number" type="string" />
						</kendo:dataSource-schema-model-fields>
						</kendo:dataSource-schema-model>
						</kendo:dataSource-schema>


							</kendo:dataSource>	
							</kendo:grid>
						</div>
					</kendo:tabStrip-item-content>
				</kendo:tabStrip-item>
				
				
				<kendo:tabStrip-item selected="false" text="Upload important documents" >
	<kendo:tabStrip-item-content>
	<div class='wethear' style='width:60%' >
	<kendo:grid name="otherDocuments_#=legalId#" selectable="true" edit="secondChildRequestByEvent" sortable="true" scrollable="true" filterable="true" groupable="true">
			
	<kendo:grid-filterable extra="false">
	   <kendo:grid-filterable-operators>
			<kendo:grid-filterable-operators-string eq="Is equal to"/>
	   </kendo:grid-filterable-operators>
	 </kendo:grid-filterable>
	<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?"/>
	<kendo:grid-columns>
		<kendo:grid-column title="Document Type&nbsp;*" field="doc_type" width="100px" editor="documentTypeEditor" filterable="true"/>		
		<kendo:grid-column title="Document Name&nbsp;*" field="doc_name" width="100px" filterable="true" />		
		<kendo:grid-column title="&nbsp;" width="300px">
					<kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="uploadDocument" text="Upload Doc" click="uploadDocument"  />
				  <kendo:grid-column-commandItem name="viewDocument" text="View Doc" click="downloadDocument" />
					</kendo:grid-column-command>
		</kendo:grid-column>
	</kendo:grid-columns>
			<kendo:dataSource pageSize="5" requestEnd="secondChildRequestEnd">
			<kendo:dataSource-transport>   
			 <kendo:dataSource-transport-read url="${readDocumentUrl}/#=legalId#" dataType="json" type="POST" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema >
				<kendo:dataSource-schema-model id="doc_Id">
					<kendo:dataSource-schema-model-fields>
						<kendo:dataSource-schema-model-field name="doc_Id" type="number" />
						<kendo:dataSource-schema-model-field name="legalId" type="number"/>
						<kendo:dataSource-schema-model-field name="doc_name" type="string"/>
						<kendo:dataSource-schema-model-field name="doc_type" type="string"/>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>

	</kendo:grid>
	</div>

	</kendo:tabStrip-item-content>
	</kendo:tabStrip-item>
			</kendo:tabStrip-items>
		</kendo:tabStrip>
	</kendo:grid-detailTemplate> --%>
</div>
<div id="alertsBox" title="Alert"></div>
<div id="treeview" style="display: none;"></div>
<div id="uploadDialog" title="Upload Document" style="display: none;">
	<kendo:upload name="files" multiple="false" upload="uploadExtraData"
		success="onDocSuccess">
		<kendo:upload-async autoUpload="true" saveUrl="./legalDetails/upload" />
	</kendo:upload>
</div>

 <div id="uploadDialogDocument" title="Upload Document" style="display: none;">
	<kendo:upload name="filesName" multiple="false" upload="uploadExtraForDocument"
		success="onDocSuccessDocument">
		<kendo:upload-async autoUpload="true" saveUrl="./legalDetails/uploadDocument" />
	</kendo:upload>
</div>


















<script>

$('select[id*=divisionSiteCode]').change(function() {
	var divisionSiteCode = $("#divisionSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(""); 
            $('#siteCode').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',0).text("Select Sub Division");
            $('#siteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.subId).text(item.subDivisionName);
				$('#siteCode').append(newOption);
			}));
		}
	});
});
$('select[id*=circleSiteCode]').change(function() {
	var circleSiteCode = $("#circleSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllDivisions/" + circleSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#divisionSiteCode').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',0).text("Select Division");
            $('#divisionSiteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.divisionName);
				$('#divisionSiteCode').append(newOption);
			}));
		}
	});
});


$('select[id*=siteCode]').change(function() {
	var subDivisionSiteCode = $("#siteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSections/" + subDivisionSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#siteCodeId').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select Section");
            $('#siteCodeId').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.subId).text(item.sectionName);
				$('#siteCodeId').append(newOption);
			}));
		}
	});
});


function getDateWiseData(){
	var fromDate = $("#fromdate").val();
	var toDate = $("#todate").val();
	var caseNumber=$("#caseNumber").val();
	var status=$("#status").val();
	var circleSiteCode=$("#circleSiteCode").val();
	var divisionSiteCode=$("#divisionSiteCode").val();
	var siteCode=$("#siteCode").val();
	var siteCodeId=$("#siteCodeId").val();
	
	
	
	//alert(fromDate+"============"+toDate);
	if(circleSiteCode==0){
		alert("Please select Circle");
		return false;
	}
	else if(fromDate==""){
		alert("Please select From Date");
		return false;
	}else if(toDate==""){
		alert("Please select To Date");
		return false;
	}else if(toDate==""){
		alert("Please select To Date");
		return false;
	}
	 $.ajax({
		url : "./legalDetails/readDataForSearch",
		type : 'GET',
		dataType : "json",

		contentType : "application/json; charset=utf-8",
		data:{
			fromDate : fromDate,
			toDate : toDate,
			status : status,
			caseNumber : caseNumber,
			
			circleSiteCode : circleSiteCode,
			divisionSiteCode : divisionSiteCode,
			siteCode : siteCode,
			siteCodeId : siteCodeId,
		},
		success : function(response) {
			
			
			$('.monthTabDetail').remove();
			$('#datatable_tabletools5').remove();
			var tableexport='"datatable_tabletools5"';
			var reportName='"Legal Report"';
			var htmlTable = "<div class='monthTabDetail' style='overflow: scroll'>"
			+"<a class='btn btn-primary'  href='#'  onclick='return tableToExcel1("+tableexport+","+reportName+")' id='excelExport1'>Export to Excel</a>"  
			+"<div style='text-align:center'><font size='5px'></font></div>"	
			+"<br><table id='datatable_tabletools5' width='100%' class='table table-striped table-bordered table-hover' style='overflow-x: scroll;'>"
			+"<thead>"
			+"<tr>"
		    +"<th colspan='11' style='text-align: center; font-size: 18px;'>Chamundeswari Electricity Supply Corporation Ltd, Mysore<br>Location : "+response[1]+"&nbsp;&nbsp;<br> Legal Report <br>From Date : "+fromDate+"&nbsp;&nbsp;&nbsp;To Date : "+toDate+"</th>"
		    +"</tr>"
			+"<tr>"
			+"<th data-hide='phone'>Location</th>"
			+"<th data-hide='phone'>Case&nbsp;Number</th>"
			+"<th data-hide='phone'>Nature</th>"
			+"<th data-class='expand'>Year</th>"
			+"<th data-hide='phone'>Name&nbsp;of&nbsp;the Court/Place</th>"
			+"<th data-hide='phone,tablet'>Plaintiff / Dependent <br>(Names)&nbsp;</th>"
			+"<th data-hide='phone,tablet'>Petitioner / Appellant&nbsp;(Names)&nbsp;</th>"
			+"<th data-hide='phone,tablet'>Name&nbsp;of&nbsp;the&nbsp;Panel&nbsp;Lawyer/ Advocate&nbsp;/&nbsp;Counted&nbsp;/&nbsp;Firm&nbsp;</th>"
			+"<th data-hide='phone,tablet'>Name of the Designation Litigation / <br>Case Conduction officer&nbsp;</th>"
			+"<th data-hide='phone,tablet'> Case Details in Brief&nbsp;</th>"
			+"<th>Status</th>"
		    +"</tr>"
					+"</thead>"
					+"<tbody>";
					var dataNew=response[0];
					 $.each(dataNew, function(index, data){
						
						htmlTable+= "<tr>"
							+"<td>"+data.location+"</td>"
							+"<td>"+data.case_number+"</td>"
							+"<td>"+data.nature+"</td>"
							+"<td>"+data.year+"</td>"
							+"<td>"+data.name_of_court+"</td>"
							+"<td>"+data.dependent_name+"</td>"
							+"<td>"+data.appellant_name+"</td> "
							+"<td>"+data.advocate_name+"</td>"
							+"<td>"+data.case_conduction_officer+"</td>"
							+"<td>"+data.case_details+"</td>"
							+"<td>"+data.case_status+"</td>"
							+"</tr>";
						 
		         		});
					 htmlTable += "</tbody>";
					+"</table></div>";
					
					$('#mydropzone').remove();
					$('#monthContent').append(htmlTable);
					
					 pageSetUp();
						
						var responsiveHelper_datatable_tabletools = undefined;
						var reportname="Legal Report";
						var lines="                                               Chamundeswari Electricity Supply Corporation Ltd, Mysore                                                                                                                                                                                                                                                                  "+reportname+"                                                                                                                                	  	     From Date : "+fromDate+"                                                            To Date : "+toDate+"                                                                 ";
						var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};
				$('#datatable_tabletools5')
				.dataTable(
				{
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-3 col-xs-2 hidden-xs'C>l>"+
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
				 $('#fromdate').val("");
				$('#todate').val(""); 
				
			
			
			/* var grid = $("#legalCaseDetails").getKendoGrid();
			var data = new kendo.data.DataSource();
			grid.dataSource.data(result);
			grid.refresh(); */

		},

	}); 
	return false;
}
function generatePDFReport(){
	var fromDate = $("#fromdate").val();
	var toDate = $("#todate").val();
	var caseNumber=$("#caseNumber").val();
	var status=$("#status").val();
	if(fromDate==""){
		alert("Please select From Date");
		return false;
	}else if(toDate==""){
		alert("Please select To Date");
		return false;
	}
	window.open("./legalDetails/generatePDFReport?fromDate="+fromDate+"&toDate="+toDate+"&status="+status+"&caseNumber="+caseNumber);
	return false;
}


$(document).ready(function() {
	
	$.validator.addMethod("regex", function(value, element, regexpr) {
		return regexpr.test(value);
	}, "");
	
	  $("#orderform123").validate({

		// Rules for form validation
		rules : {
			fromdate : {
				required : true
				
			},
			todate : {
				required : true
				
			},
		},

		// Messages for form validation
		messages : {
			fromdate : {
				required : 'Please select From Date'
			},
			todate : {
				required : 'Please select To Date'
			},
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	  
	  
	  pageSetUp();
		
		$('#fromdate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$('#todate').datepicker('option', 'minDate', selectedDate);
			}
		});
		
		$('#todate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$('#fromdate').datepicker('option', 'maxDate', selectedDate);
			}
		});
		$('#excelExport').hide();
});











function uploadExtraForDocument(e) {
	var filesName = e.files;
	// Check the extension of each file and abort the upload if it is not .jpg
	$
			.each(
					filesName,
					function() {
						if (this.extension.toLowerCase() != ".exe") {
							e.data = {
								filename: this.name,
								doc_Id : doc_Id
							};
						}

						else {
							alert("Invalid Document Type:\nAcceptable formats is other than .exe File");
							e.preventDefault();
							return false;
						}
					});
}
var doc_Id="";
function uploadDocument() {
	var gview = $('#otherDocuments_' + SelectedRowId).data("kendoGrid");
	 var selectedItem = gview.dataItem(gview.select());
	if (selectedItem != null) {
		doc_Id = selectedItem.doc_Id;
	}
	$('#uploadDialogDocument').dialog({
		modal : true,
	});
	return false;
}
function onDocSuccessDocument(e) {
	alert("Uploaded Successfully");
	$(".k-upload-files.k-reset").find("li").remove();
	$(".k-upload-status-total").remove();
	$("#otherDocuments_"+SelectedRowId).data("kendoGrid").dataSource.read();
}
function downloadDocument() {

	var gview = $("#otherDocuments_"+SelectedRowId).data("kendoGrid");
	var selectedItem = gview.dataItem(gview.select());
	window.open("./legalDetails/downloadDocument/" + selectedItem.doc_Id);
}
$("#legalCaseDetails").on("click",".k-grid-status",
		function(e) {
	
				var btDialog = $("#treeview");
				btDialog.kendoWindow({
					width : "290",
					height : "auto",
					modal : true,
					draggable : true,
					position : {
						top : -50
					},
					title : "Change Legal Case Status"
				}).data("kendoWindow").center().open();

				btDialog.kendoWindow("open");
				$("#treeview").html("");
					 $("#treeview").append("<form id='getRegisterForm'><table id='getRegister' style='margin-top: 5px;height: 100px'></table> </form>");
					$("#getRegister").append("<tr><td> <label for='status'>Status * :</label></td> <td><input id='status' style='width:200px'/></td> </tr>");
					 
					 $("#status").kendoDropDownList({
					    dataTextField: "parentName",
					    dataValueField: "parentId",
					    dataSource: [
					        { parentName: "Select", parentId: "" },
					        { parentName: "InProgress", parentId: "InProgress" },
					        { parentName: "Dismissed", parentId: "Dismissed"},
					        { parentName: "Closed", parentId: "Closed" }
					    ],
					});
				 $("#getRegister").append("<tr><td>&nbsp;</td><td><button type='submit' id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='getEmloyeefuncion()'>Change Status</button> </td></tr>");
		});
function getEmloyeefuncion() {
	status = $("#status").val();
	if(status==""){
		alert("Please select status");
		return false;
	}
	$.ajax({
		type : "GET",
		url : "./legalDetails/updateStaus",
		dataType : 'text',
		data :{
			legalId : SelectedRowId,
			status : status,
		},
		success : function(response) {
		closeDialog();
		$("#alertsBox").html("");
		$("#alertsBox").html("Status changed successfully");
		$("#alertsBox").dialog(
						{
							modal : true,
							buttons : {
								"Close" : function() {
									$(this).dialog("close");
						}
					}
			});
		var grid = $("#legalCaseDetails").data("kendoGrid");
		grid.dataSource.read();
		grid.refresh();
	}
});
		 function closeDialog() {
			var btDialog = $("#treeview");
			btDialog.kendoWindow({
				width : "400",
				height : "auto",
				modal : true,
				draggable : true,
				position : {
					top : -50
				},
				title : "Change Legal Case Status"
			}).data("kendoWindow").center().close();
			btDialog.kendoWindow("close");
		}  
}
function natureEditor(container, options) {
	var data = [ {
		text : "WP",
		value : "WP"
	}, {
		text : "OS",
		value : "OS"
	}, {
		text : "RA",
		value : "RA"
	} ];

	$(
			'<input data-text-field="text" data-value-field="value" data-bind="value:' + options.field + '"/>')
			.appendTo(container).kendoDropDownList({
				dataTextField : "text",
				dataValueField : "value",
				placeholder : "Select ",
				dataSource : data
			});
}	
var doctype="";
function documentTypeEditor(container, options) 
{
	var data = [ {
		text : "Petition copy(Plaintiff copy)",
		value : "Petition copy(Plaintiff copy)"
	}, {
		text : "Written statement / Affidavit",
		value : "Written statement / Affidavit"
	}, {
		text : "Copies of Mahazar Inspection report",
		value : "Copies of Mahazar Inspection report"
	}, {
		text : "Other Documents",
		value : "Other Documents"
	}  ];
		$('<select data-text-field="text" name="Document Type" data-value-field="value" id="doc_type" required="true" data-bind="value:' + options.field + '"/>')
            .appendTo(container)
            .kendoComboBox
            ({
            	dataTextField : "text",
				dataValueField : "value",
				placeholder : "Select Document type ",
             dataSource: data,
   	         change : function (e) {
   	        	 if(this.value() && this.selectedIndex == -1){
 						alert("Document Type doesn't exist!");
 		                $("#doc_type").data("kendoComboBox").value("");
   	        	 }else{
   	        	 if(this.value()=="Other Documents"){
   	        		$('div[data-container-for="doc_name"]').show();
   	     			$('label[for="doc_name"]').closest('.k-edit-label').show();
   	     		doctype="other";
   	        	 }else{
   	        		doctype="true";
   	        		 $('input[name=doc_name]').val("");
   	        		$('div[data-container-for="doc_name"]').hide();
   	     			$('label[for="doc_name"]').closest('.k-edit-label').hide();
   	     			
   	        	 }
   	        	 }
	 			}
            });
		$('<span class="k-invalid-msg" data-for="Document Type"></span>').appendTo(container);
}

function appealEditor(container, options) {
	$(
			'<input type="checkbox" name=' + options.field + ' value="Yes"/>')
			.appendTo(container);
}
var appeals="";
$(document).on('change', 'input[name="appeals"]:checkbox', function() {
	appeals="";
	var radioValue = $('input[name=appeals]:checked').val();
	if(radioValue!='Yes'){
		$('div[data-container-for="appeal_case_number"]').hide();
		$('label[for="appeal_case_number"]').closest('.k-edit-label').hide();
		appeals="false";
	}else{
		appeals="true";
		$('div[data-container-for="appeal_case_number"]').show();
		$('label[for="appeal_case_number"]').closest('.k-edit-label').show();
	}
});
var legalId="";
var casedetailid="";
var SelectedRowId="";
function onChange(arg) {
	var gview = $("#legalCaseDetails").data("kendoGrid");
	var selectedItem = gview.dataItem(gview.select());
	SelectedRowId = selectedItem.legalId;
	this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
}
function uploadAsset() {
	var gview = $('#hearingGrid_' + SelectedRowId).data("kendoGrid");
	 var selectedItem = gview.dataItem(gview.select());
	if (selectedItem != null) {
		legalId = selectedItem.legalId;
		casedetailid = selectedItem.casedetailid;
	}
	$('#uploadDialog').dialog({
		modal : true,
	});
	return false;
}

function uploadExtraData(e) {
	var files = e.files;
	// Check the extension of each file and abort the upload if it is not .jpg
	$
			.each(
					files,
					function() {
						if (this.extension.toLowerCase() != ".exe") {
							e.data = {
								legalId : legalId,
								filename: this.name,
								casedetailid : casedetailid
							};
						}

						else {
							alert("Invalid Document Type:\nAcceptable formats is other than .exe File");
							e.preventDefault();
							return false;
						}
					});
}

function onDocSuccess(e) {
	alert("Uploaded Successfully");
	$(".k-upload-files.k-reset").find("li").remove();
	$(".k-upload-status-total").remove();
	$("#hearingGrid_"+SelectedRowId).data("kendoGrid").dataSource.read();
}

function downloadFile() {

	var gview = $("#hearingGrid_"+SelectedRowId).data("kendoGrid");
	//Getting selected item
	var selectedItem = gview.dataItem(gview.select());
	window.open("./legalDetails/download/" + selectedItem.casedetailid);
}

function onRequestEnd(e) {
	if (typeof e.response != 'undefined') {
		if (e.type == "create") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Legal Case Details created successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $("#legalCaseDetails").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		if (e.type == "update") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Legal Case Details updated successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $("#legalCaseDetails").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		
		if(e.type == "destroy" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Legal Case Details Deleted successfully");
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
			var grid = $("#legalCaseDetails").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		} 
	}
}
function onChildRequestEnd(e) {
	if (typeof e.response != 'undefined') {
		if (e.type == "create") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Hearing Details created successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $('#hearingGrid_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		if (e.type == "update") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Hearing Details updated successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $('#hearingGrid_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		
		if(e.type == "destroy" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Hearing Details Deleted successfully");
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
			var grid =$('#hearingGrid_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		} 
	}
}
function secondChildRequestEnd(e) {
	if (typeof e.response != 'undefined') {
		if (e.type == "create") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Document created successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $('#otherDocuments_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		if (e.type == "update") {
			$("#alertsBox").html("");
			$("#alertsBox").html("Document updated successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $('#otherDocuments_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		
		if(e.type == "destroy" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Document Deleted successfully");
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
			var grid =$('#otherDocuments_' + SelectedRowId).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		} 
	}
}
function parse(response) {
	$.each(response, function(idx, elem) {
		if (elem.year === null) {
			elem.year = "";
		} else {
			elem.year = kendo.parseDate(new Date(elem.year),
					'dd/MM/yyyy HH:mm');
		}
	});
	return response;
}
function childparse(response) {
	$.each(response, function(idx, elem) {
		if (elem.date_of_hearing === null) {
			elem.date_of_hearing = "";
		} else {
			elem.date_of_hearing = kendo.parseDate(new Date(elem.date_of_hearing),
					'dd/MM/yyyy HH:mm');
		}
	});
	return response;
}
function proceedingsEditor(container, options) 
{
	 $('<textarea name="proceedings" id="proceedings" data-text-field="proceedings" data-value-field="proceedings" required="true" data-bind="value:' + options.field + '" style="width: 211px; height: 125px;"/>').appendTo(container);
    $('<span class="k-invalid-msg" data-for="proceedings"></span>').appendTo(container);
}
 function caseDetailsEditor(container, options) 
	 {
		 $('<textarea name="case_details" data-text-field="case_details" data-value-field="case_details" data-bind="value:' + options.field + '" style="width: 211px; height: 125px;"/>').appendTo(container);
	     $('<span class="k-invalid-msg" data-for="case_details"></span>').appendTo(container);
	 }
function clearFilter()
{
   $("form.k-filter-menu button[type='reset']").slice().trigger("click");
   var grid = $("#legalCaseDetails").data("kendoGrid");
   grid.dataSource.read();
   grid.refresh();
}
function requestByEvent(e) {
	
	  $('input[name="year"]').kendoDatePicker({
	        start: "decade",
	        depth: "decade",
	        format: "yyyy",
	          readonly : true,
	    }).data("kendoDatePicker");
	 $('div[data-container-for="case_status"]').hide();
	$('label[for="case_status"]').closest('.k-edit-label').hide(); 
	$(".k-edit-form-container").css({
		"width" : "700px"
	});
	$(".k-window").css({
		"top": "150px"
	});
	$('.k-edit-label:nth-child(12n+1)').each(
			function(e) {
				$(this).nextAll(':lt(11)').addBack().wrapAll(
						'<div class="wrappers"/>');
			});
	$(".k-window").css({ "position" : "fixed" });
	$(".wrappers").css({ "display" : "inline", "float" : "left", "width" : "350px", "padding-top" : "10px" });
	
	if (e.model.isNew()) {
		 res1 = [];
		 $.ajax
		 ({
		      type : "GET",
			  dataType:"text",
			  url : '${readCaseNumberForUniqueness}',
			  dataType : "JSON",
			  success : function(response) 
			  {
				 for(var i = 0; i<response.length; i++) 
				 {
				   res1[i] = response[i];	
			     }
			  }
		  }); 
		$(".k-window-title").text("Add Legal Case Details");
		$(".k-grid-update").text("Save");
	} else {
		var gview = $("#legalCaseDetails").data("kendoGrid");
		  var selectedItem = gview.dataItem(gview.select());
		  var case_number= selectedItem.case_number;
		  res1 = [];
		   $.ajax({
		    type : "GET",
		    dataType : "text",
		    url : '${readCaseNumberForUniqueness}',
		    dataType : "JSON",
		    success : function(response) {
		     var j = 0;
		     for (var i = 0; i < response.length; i++) {
		      if (response[i] != case_number) {

		       res1[j] = response[i];
		       j++;
		      }
		     }
		    }
		   });  
		$(".k-window-title").text("Edit Legal Case Details");
		$(".k-grid-update").text("Update");
	}
}
function childRequestByEvent(e) {
	
	$('div[data-container-for="appeal_case_number"]').hide();
	$('label[for="appeal_case_number"]').closest('.k-edit-label').hide(); 
	
	  $('input[name="date_of_hearing"]').kendoDatePicker({
	        start: "year",
	        depth: "month",
	        format: "dd/MM/yyyy",
	          readonly : true,
	    }).data("kendoDatePicker");
	/* $('div[data-container-for="empName"]').hide();
	$('label[for="empName"]').closest('.k-edit-label').hide(); */
	
	if (e.model.isNew()) {
		$(".k-window-title").text("Add Hearing Details");
		$(".k-grid-update").text("Save");
	} else { 
		$(".k-window-title").text("Edit Hearing Details");
		$(".k-grid-update").text("Update");
	}
}
function secondChildRequestByEvent(e) {
	
	$('div[data-container-for="doc_name"]').hide();
	$('label[for="doc_name"]').closest('.k-edit-label').hide(); 
	
	/* $('div[data-container-for="empName"]').hide();
	$('label[for="empName"]').closest('.k-edit-label').hide(); */
	
	if (e.model.isNew()) {
		$(".k-window-title").text("Add document");
		$(".k-grid-update").text("Save");
	} else { 
		$(".k-window-title").text("Edit document");
		$(".k-grid-update").text("Update");
	}
}
//Validator Function
(function($, kendo) 
 {
	 $.extend(true,kendo.ui.validator,
	 {
		rules : { 
			caseNumberUniqueness : function(input,params) {
				        if (input.filter("[name='case_number']").length && input.val()){
				          enterdService = input.val().toUpperCase(); 
				          for(var i = 0; i<res1.length; i++) {
				            if ((enterdService == res1[i].toUpperCase()) && (enterdService.length == res1[i].length) ) {								            
				              return false;								          
				            }
				          }
				         }
				         return true;
				    },
				    caseNumber : function(input,params) {
				    	if (input.attr("name") == "case_number")
	                       {
	                        return $.trim(input.val()) !== "";
	                       }
	                       return true; 
				    },
				    appeal_case_number : function(input,params) {
				    	if (input.attr("name") == "appeal_case_number")
	                       {
				    		if(appeals=="true" && $.trim(input.val())==""){
				    			return false;
				    		}
	                       }
	                       return true; 
				    },
				    doc_name : function(input,params) {
				    	if (input.attr("name") == "doc_name")
	                       {
				    		if($.trim(input.val())=="" && doctype=="other"){
				    			return false;
				    		}
	                       }
	                       return true; 
				    },
				  },
				  messages : 
				  {					
					  caseNumberUniqueness:"Case Number already exists",	
					  caseNumber : "Please enter Case Number",
					  appeal_case_number : "Please enter Case Number ",
					  doc_name : "Please Enter Document Name ",
				  }
			 });
	})(jQuery, kendo);
</script>
<style type="text/css">
.jarviswidget {
    margin: 0px;
    position: relative;
    border-radius: 0px;
    padding: 0px;
}

.k-datepicker span {
	width: 70%
}
.k-datepicker{
background: white;
}
#grid {
	font-size: 11px !important;
	font-weight: normal;
}


	.k-window-titlebar {
	height: 25px;
}
</style>