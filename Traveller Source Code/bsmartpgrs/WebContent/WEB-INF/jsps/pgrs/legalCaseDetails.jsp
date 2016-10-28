<%@include file="/common/taglibs.jsp"%>

<c:url value="/legalDetails/read" var="readUrl" />
<c:url value="/legalDetails/create" var="createUrl" />
<c:url value="/legalDetails/update" var="updateUrl" />
<c:url value="/legalDetails/delete" var="destroyUrl" />
<c:url value="/legalDetails/readCaseNumberForUniqueness" var="readCaseNumberForUniqueness" />

<!-- child url -->
<c:url value="/legalDetails/createHearingUrl" var="createHearingUrl" />
<c:url value="/legalDetails/readHearingUrl" var="readHearingUrl" />
<c:url value="/legalDetails/updateHearingUrl" var="updateHearingUrl" />
<c:url value="/legalDetails/destroyHearingUrl" var="destroyHearingUrl" />
<!-- second child url -->
<c:url value="/legalDetails/createDocumentUrl" var="createDocumentUrl" />
<c:url value="/legalDetails/readDocumentUrl" var="readDocumentUrl" />
<c:url value="/legalDetails/updateDocumentUrl" var="updateDocumentUrl" />
<c:url value="/legalDetails/destroyDocumentUrl" var="destroyDocumentUrl" />
<c:url value="/users/commonFilterForOfficeUrl" var="commonFilterForOfficeUrl" />

<c:url value="/legal/getCircleNames" var="getCircleNamesUrl" />
<c:url value="/legal/getDivisionNames" var="divisionUrl" />

<!-- <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script> -->

<script src="./resources/js/plugin/jquery.tagsinput.min.js"></script>




<div id="content">

	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i> Legal Case Details
			</h1>
		</div>
	</div>

	<!-- KENDO GRID  -->
	<kendo:grid name="legalCaseDetails" detailTemplate="legalCaseTemplate" change="onChange" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" edit="requestByEvent" scrollable="true" filterable="true" groupable="true">
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="5" input="true" numeric="true"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
	    	<kendo:grid-filterable-operators>
			 	<kendo:grid-filterable-operators-string eq="Is equal to" contains="Contains"/>
	    	</kendo:grid-filterable-operators>
	  	</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this Concierge Vendor Service?" />
		<kendo:grid-toolbar> 
			<kendo:grid-toolbarItem name="create" text="Add Case Details" />
			<kendo:grid-toolbarItem template="<a class='k-button' href='\\#' onclick=clearFilter()>Clear Filter</a>"/>
			<kendo:grid-toolbarItem template="<a class='k-button' href='http://aravindavk.in/ascii2unicode/' target='_blank' rel='tooltip' title='copy the kannada content from editor(ex:kannada baraha) then click this button then paste that content and convert it to get actual Kannada content'>1.Click here to get actual Kannada Content</a>"/>
			<kendo:grid-toolbarItem template="<a class='k-button' href='http://code.cside.com/3rdpage/us/javaUnicode/converter.html' target='_blank' rel='tooltip' title='copy the Kannada Content whick we get previously after conversion and click this button to get unicode format'>2.Click here to get Unicode for Kannada</a>"/>
			
		</kendo:grid-toolbar>
		<kendo:grid-columns>
			
			<kendo:grid-column title="Location&nbsp;*" field="location" width="250px">
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForOfficeUrl}"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   </kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Circle&nbsp;*" field="circleName" width="110px"   hidden="true"/>
			<kendo:grid-column title="Division&nbsp;" field="divisionName" width="110px"    hidden="true"/>
			<kendo:grid-column title="Sub Division&nbsp;" field="subDivisionName" width="110px"    hidden="true"/>
			<kendo:grid-column title="Section&nbsp;" field="sectionName" width="110px"   hidden="true"/>
			
			
			
			
			
			<kendo:grid-column title="Circle&nbsp;*" field="circleId" width="110px" editor="circleEditor"  hidden="true"/>
			<kendo:grid-column title="Division&nbsp;" field="divisionId" width="110px" editor="divisionEditor"  hidden="true" />
			<kendo:grid-column title="Sub Division&nbsp;" field="subId" width="110px" editor="subdivisionEditor"   hidden="true"/>
			<kendo:grid-column title="Section&nbsp;" field="sectionId" width="110px" editor="sectionEditor"   hidden="true"/>
			
			<kendo:grid-column title="Case Number &nbsp;*" field="case_number" filterable="true" width="130px"/>
			<kendo:grid-column title="Nature&nbsp;*" field="nature" editor="natureEditor" filterable="true" width="130px"/>
			<kendo:grid-column title="Year&nbsp;*" field="year" filterable="true" format="{0:yyyy}" width="80px"/>
			<kendo:grid-column title="Name of the Court/Place&nbsp;" field="name_of_court" filterable="true" width="180px"/>
			
			<kendo:grid-column title="Case Details in Brief&nbsp;" field="content" editor="caseDetailsEditor" filterable="true" width="150px"/>
			 
			<kendo:grid-column title="Petitioner / Appellant <br>/Plaintiff (Names)&nbsp;" field="appellant_name" filterable="true" width="150px" editor="commonNamesEditor"/>
			<kendo:grid-column title="Name of the Panel Lawyer <br>/ Advocate / Counted / Firm&nbsp;" field="advocate_name" filterable="true" width="200px"/>
			<kendo:grid-column title="Name of the Designation Litigation / <br>Case Conduction officer&nbsp;" field="case_conduction_officer" filterable="true" width="230px"/>
			
			<kendo:grid-column title=" Dependent <br>(Names)&nbsp;" field="dependent_name" filterable="true" width="150px" editor="dependentNamesEditor"/>
			
			
			
			<kendo:grid-column title="Status&nbsp;" field="case_status" filterable="true" width="150px"/> 
			
			
			
			<%-- <kendo:grid-column title="&nbsp;" width="100px">
					<kendo:grid-column-command>
							<kendo:grid-column-commandItem name="Case Details" click="ckeditorCaseDetails"/>
							
					</kendo:grid-column-command>
			</kendo:grid-column> --%>
			
			<kendo:grid-column title="&nbsp;" width="100px">
					<kendo:grid-column-command>
							<kendo:grid-column-commandItem name="status" text="Change Status" /> 
					</kendo:grid-column-command>
			</kendo:grid-column>
			<kendo:grid-column title="&nbsp;" width="100px">
				<kendo:grid-column-command>
					<kendo:grid-column-commandItem name="edit" />
					
					
					<%--  <kendo:grid-column-commandItem name="destroy" /> --%>
				</kendo:grid-column-command>
			</kendo:grid-column>
			<kendo:grid-column title="&nbsp;" width="100px">
					<kendo:grid-column-command>
							<kendo:grid-column-commandItem name="pdfreport" text="PDF Report" /> 
					</kendo:grid-column-command>
			</kendo:grid-column>
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json" />
				 <kendo:dataSource-transport-create url="${createUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-update url="${updateUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-destroy url="${destroyUrl}" dataType="json" type="GET" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema parse="parse">
				<kendo:dataSource-schema-model id="legalId">
					<kendo:dataSource-schema-model-fields>
						<kendo:dataSource-schema-model-field name="legalId" type="Number" />
						<kendo:dataSource-schema-model-field name="case_number" type="string" />
						<kendo:dataSource-schema-model-field name="nature" type="string" defaultValue="A.C (Arbitration Case)" />
						<kendo:dataSource-schema-model-field name="year" type="date" />
						<kendo:dataSource-schema-model-field name="name_of_court" type="string" />
						<kendo:dataSource-schema-model-field name="dependent_name" type="string" />
						<kendo:dataSource-schema-model-field name="appellant_name" type="string" />
						<kendo:dataSource-schema-model-field name="advocate_name" type="string" />
						<kendo:dataSource-schema-model-field name="case_conduction_officer" type="string" />
						<kendo:dataSource-schema-model-field name="content" type="string" />
						<kendo:dataSource-schema-model-field name="case_status" type="string" defaultValue="Created"/>
						
						<kendo:dataSource-schema-model-field name="circleId" type="number" defaultValue=""/>
						<kendo:dataSource-schema-model-field name="divisionId" type="number" defaultValue="0"/>
						<kendo:dataSource-schema-model-field name="subId" type="number" defaultValue="0"/>
						<kendo:dataSource-schema-model-field name="sectionId" type="number" defaultValue="0"/>
						
						<kendo:dataSource-schema-model-field name="circleName" />
						<kendo:dataSource-schema-model-field name="divisionName"/>
						<kendo:dataSource-schema-model-field name="subDivisionName" />
						<kendo:dataSource-schema-model-field name="sectionName"/>
						
						
						
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid>

	 
 <kendo:grid-detailTemplate id="legalCaseTemplate">
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
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Hearings" />		
	   </kendo:grid-toolbar>
	  
		<kendo:grid-columns>
		         <kendo:grid-column title="Date of hearing*" field="date_of_hearing" width="120px" format="{0:dd/MM/yyyy}" filterable="true" />
		         <kendo:grid-column title="Proceedings *" field="proceedings" width="150px" filterable="false" editor="proceedingsEditor"/>	  
		         <kendo:grid-column title="Appeals if Any *" field="appeals" width="150px" editor="appealEditor" filterable="false" />	  
		         <kendo:grid-column title="Case Number *" field="appeal_case_number" width="150px" filterable="false"/>	  
				 <kendo:grid-column title="&nbsp;" width="300px">
			     <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy" />
				  <kendo:grid-column-commandItem name="upload" text="Upload Doc" click="uploadAsset"  />
				  <kendo:grid-column-commandItem name="view" text="View Doc" click="downloadFile" />
			   </kendo:grid-column-command>
			</kendo:grid-column>	
					
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onChildRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-create url="${createHearingUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-read url="${readHearingUrl}/#=legalId#" dataType="json" type="POST" contentType="application/json" />
				 <kendo:dataSource-transport-update url="${updateHearingUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-destroy url="${destroyHearingUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" /> 
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
	<kendo:grid-toolbar>
			<kendo:grid-toolbarItem name="create" text="Add Document" />
		</kendo:grid-toolbar>
	<kendo:grid-columns>
		<kendo:grid-column title="Document Type&nbsp;*" field="doc_type" width="100px" editor="documentTypeEditor" filterable="true"/>		
		<kendo:grid-column title="Document Name&nbsp;*" field="doc_name" width="100px" filterable="true" />		
		<kendo:grid-column title="&nbsp;" width="300px">
					<kendo:grid-column-command>
							<kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy" />
				  <kendo:grid-column-commandItem name="uploadDocument" text="Upload Doc" click="uploadDocument"  />
				  <kendo:grid-column-commandItem name="viewDocument" text="View Doc" click="downloadDocument" />
					</kendo:grid-column-command>
		</kendo:grid-column>
	</kendo:grid-columns>
			<kendo:dataSource pageSize="5" requestEnd="secondChildRequestEnd">
			<kendo:dataSource-transport>   
			 <kendo:dataSource-transport-read url="${readDocumentUrl}/#=legalId#" dataType="json" type="POST" contentType="application/json" />
				  <kendo:dataSource-transport-create url="${createDocumentUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" />
				 <kendo:dataSource-transport-update url="${updateDocumentUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" />
				 <kendo:dataSource-transport-destroy url="${destroyDocumentUrl}/#=legalId#" dataType="json" type="GET" contentType="application/json" />
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
	</kendo:grid-detailTemplate>
	
	
	
	
	<!-- <div id="addtabforClarifications" title="Enter Case Details" style="display:none;overflow-y:scroll;overflow-x:hidden;">
			
			<form action="" id="checkForgotId" method="POST"  novalidate="novalidate" autocomplete="off">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
						<textarea id="content" name="content" class="ckeditor form-control" rows="2"></textarea> 
					
						</div>
					</div>
				</div>
				 <div class="modal-footer">
					<button id="firstButton" type="button" class="btn btn-primary" onclick="CaseDetailsSubmit()">
						Submit
					</button>
				</div>
			</form>
			 
</div> -->
	
	
	
	
	
	
	
	
	
	
	
	
</div>

<!-- <div class="checkbox">
	<label>
		<input type="checkbox">
		Checkbox 2 </label>
</div> -->

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


 
<!-- <textarea id="content" name="content" class="ckeditor form-control" rows="2"></textarea> --> 

<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="" id="checkForgotId" method="POST"  novalidate="novalidate" autocomplete="off">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">Enter Case Details</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
								<div class="form-group">
								<textarea id="editor1" name="editor1" class="ckeditor form-control" rows="2"></textarea> 
								</div>
									
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="firstButton" type="button" class="btn btn-primary" onclick="CaseDetailsSubmit()">
								Submit
							</button>
						</div>
					</form>
				</div>
			</div>
	</div> -->
				
				
           
<!-- <textarea id="editor1" name="editor1" class="ckeditor form-control" rows="2"></textarea>  -->





<script>
var legalId="";
var casedetailid="";
var SelectedRowId="";
$(document).on('focusin', function(e) {
    e.stopImmediatePropagation();
});

function CaseDetailsSubmit(){
	
	
	$.ajax
	({			
		type : "GET",
		url : "./legal/updateCaseDetailsDescription/"+SelectedRowId,
		async: false,
		dataType : "text",
		success : function(response) 
		{	    
			alert(response);
		}
	

	});
	
	
	
}
/* function ckeditorCaseDetails() {
	
	dialog = $("#addtabforClarifications").dialog({
		autoOpen: false,
	    height: 450,
	    width: 'auto',
	    resize: 'auto',
	    modal: false,
	    zIndex : -1,
	    show: {
	        effect: "drop",
	        complete: function() {
	            setTimeout(function(){
	                $( "#content" ).ckeditor();
	            },50);
	        }
	    },
	    hide: "drop",
	}).dialog("open");	 
	
}
 */
function dependentNamesEditor(container, options) {
	
	$('<input id="names_designation" class="k-edit-field form-control tagsinput" data-role="tagsinput" name=' + options.field + ' data-bind="value:' + options.field + '" />')
			.appendTo(container);
	
}

function commonNamesEditor(container, options) {
	
	$(
			'<input id="names_common" class="k-edit-field" name='+ options.field+ ' data-bind="value:'+ options.field+'" />')
			.appendTo(container);
}
function natureEditor(container, options) {
	
	var data = ["A.C (Arbitration Case)" , "A. S (Arbitration suit)","CCC (Civil Contempt Petition)",
	            "CRL A (Criminal Appeal)","C P (Criminal Petition)","MFA (Miscellaneous  First  Appeal)","MSA (Miscellaneous  Second Appeal)",
	            "O S (Original Suit)","RA (Review Appeal)","EX (Execution petition)","SPL C (Special  Case)",
	            "RA (Regular Appeal)","MFA (Miscellaneous  First Appeal)","MSA (Miscellaneous Second Appeal)","R P (Review Petition)",
	            "SCLAP (Supreme Court Leave Petition)","WA (Writ Appeal)","WP (Writ Petition)","District Consumer Disputes redressal forum (CC)","State Consumer Disputes redressal commission","National  Consumer Disputes redressal commission","  Preliminary Miscellaneous (P.MISC)"];
	
	
   	$('<input name="nature" data-text-field=""  data-value-field="" data-bind="value:' + options.field + '" required="true" />')
     .appendTo(container).kendoComboBox({   	    
      dataSource :data            	                 	      
   	});
   	$('<span class="k-invalid-msg" data-for="nature"></span>').appendTo(container);
}


var divisionId=0;
var subId=0;
var sectionId=0;



function circleEditor(container, options) {
		
	$('<input name="circle" id="circleId" data-text-field="circleName" required="true" validationMessage="Please Select Circle" data-value-field="circleId" data-bind="value:' + options.field + '" />')
	.appendTo(container).kendoComboBox({
		autoBind : false,			
		placeholder : "Select",
		headerTemplate : '<div class="dropdown-header">'
			+ '<span class="k-widget k-header">Photo</span>'
			+ '<span class="k-widget k-header">Contact info</span>'
			+ '</div>',
		template : '<table><tr>'
			+ '<td align="left"><span class="k-state-default"><b>#: data.circleName #</b></span><br>'
			+ '</td></tr></table>',
		dataSource : {
			transport : {		
				read :  "${getCircleNamesUrl}"
			}
		}, change : function (e) {
			
			divisionId=0;
			subId=0;
			sectionId=0;
			if(this.value() && this.selectedIndex == -1){
					alert("Circle doesn't exist!");
	                $("#circleId").data("kendoComboBox").value("");
     	    }
		  }
	});
	
	$('<span class="k-invalid-msg" data-for="circle"></span>').appendTo(container);

}

function divisionEditor(container, options) {
	
	
	$('<input name="division" id="divisionId" data-text-field="divisionName" data-value-field="divisionId" data-bind="value:' + options.field + '" />')
	.appendTo(container).kendoComboBox({
		autoBind : false,
		cascadeFrom : "circleId",
		placeholder : "Select",
		headerTemplate : '<div class="dropdown-header">'
			+ '<span class="k-widget k-header">Photo</span>'
			+ '<span class="k-widget k-header">Contact info</span>'
			+ '</div>',
		template : '<table><tr>'
			+ '<td align="left"><span class="k-state-default"><b>#: data.divisionName #</b></span><br>'
			+ '</td></tr></table>',
		dataSource : {
			transport : {		
				read :  "./legal/getDivisionNames"
			}
		},change : function (e) {
			divisionId=this.value();
			subId=0;
			sectionId=0;	 
			if(this.selectedIndex == -1){
						alert("Divsion doesn't exist!");
		                $("#divisionId").data("kendoComboBox").value("");
	        	 }
	 			}
	});
	
	$('<span class="k-invalid-msg" data-for="division"></span>').appendTo(container);
	
}

function subdivisionEditor(container, options) {
	
	$('<input name="sub division" id="subId" data-text-field="subDivisionName" data-value-field="subId" data-bind="value:' + options.field + '" />')
	.appendTo(container).kendoComboBox({
		autoBind : false,
		cascadeFrom : "divisionId",
		placeholder : "Select",
		headerTemplate : '<div class="dropdown-header">'
			+ '<span class="k-widget k-header">Photo</span>'
			+ '<span class="k-widget k-header">Contact info</span>'
			+ '</div>',
		template : '<table><tr>'
			+ '<td align="left"><span class="k-state-default"><b>#: data.subDivisionName #</b></span><br>'
			+ '</td></tr></table>',
		dataSource : {
			transport : {		
				read :  "./legal/getAllSubDivisions"
			}
		},
		change : function (e) {
			subId=this.value();
			sectionId=0;
			if(this.selectedIndex == -1){
					alert("Sub Division doesn't exist!");
	                $("#subId").data("kendoComboBox").value("");
    	    }
		  }
	});
	
	$('<span class="k-invalid-msg" data-for="sub division"></span>').appendTo(container);
	

}


function sectionEditor(container, options) {
	
	$('<input name="section" id="sectionId" data-text-field="sectionName" data-value-field="sectionId" data-bind="value:' + options.field + '" />')
	.appendTo(container).kendoComboBox({
		autoBind : false,
		cascadeFrom : "subId",
		placeholder : "Select",
		headerTemplate : '<div class="dropdown-header">'
			+ '<span class="k-widget k-header">Photo</span>'
			+ '<span class="k-widget k-header">Contact info</span>'
			+ '</div>',
		template : '<table><tr>'
			+ '<td align="left"><span class="k-state-default"><b>#: data.sectionName #</b></span><br>'
			+ '</td></tr></table>',
		dataSource : {
			transport : {		
				read :  "./legal/getAllSections"
			}
			
		}, change : function (e) {
			sectionId=this.value();
			if(this.selectedIndex == -1){
					alert("Section doesn't exist!");
	                $("#sectionId").data("kendoComboBox").value("");
     	    }
		  }
	});
	
	$('<span class="k-invalid-msg" data-for="section"></span>').appendTo(container);
	

}




$("#legalCaseDetails").on("click",".k-grid-pdfreport",
		function(e) {
	window.open("./legalDetails/pdfReport/"+SelectedRowId);
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


function onChange(arg) {
	var gview = $("#legalCaseDetails").data("kendoGrid");
	var selectedItem = gview.dataItem(gview.select());
	SelectedRowId = selectedItem.legalId;
	circleId = selectedItem.circleId;
	
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
		 $('<textarea id="content" name="content" class="ckeditor form-control"  row="3" data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" style="width: 257px; height: 75px;"/>').appendTo(container);
	     $('<span class="k-invalid-msg" data-for="content"></span>').appendTo(container);
	 }
function clearFilter()
{
   $("form.k-filter-menu button[type='reset']").slice().trigger("click");
   var grid = $("#legalCaseDetails").data("kendoGrid");
   grid.dataSource.read();
   grid.refresh();
}
function requestByEvent(e) {
	
	$('#names_designation').tagsInput({}); 
	$('#names_common').tagsInput(); 
	
	
	$('label[for="location"]').parent().hide();
	$('div[data-container-for="location"]').hide();
	
	
	$('label[for="circleName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="circleName"]').remove();
	
	$('label[for="divisionName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="divisionName"]').remove();
	
	
	$('label[for="subDivisionName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="subDivisionName"]').remove();
	
	$('label[for="sectionName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="sectionName"]').remove(); 
	
	
	
	
	  $('input[name="year"]').kendoDatePicker({
	        start: "decade",
	        depth: "decade",
	        format: "yyyy",
	          readonly : true,
	    }).data("kendoDatePicker");
	 $('div[data-container-for="case_status"]').hide();
	$('label[for="case_status"]').closest('.k-edit-label').hide(); 
	
	$(".k-edit-form-container").css({
		"width" : "700px",
		
	});
	$(".k-window").css({
		"top": "150px"
	});
	$('.k-edit-label:nth-child(20n+1)').each(
			function(e) {
				$(this).nextAll(':lt(19)').addBack().wrapAll(
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
		
		$(".k-grid-update").click(function() {
			e.model.set("divisionId", divisionId);
			e.model.set("subId", subId);
			e.model.set("sectionId", sectionId);
		}); 
		
	}
	
	$(".k-grid-update").click(function () {
		e.model.set("dependent_name", $('input[name="dependent_name"]').val());
		e.model.set("appellant_name", $('input[name="appellant_name"]').val());
	 });
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
				    proceedings : function(input,params) {
				    	if (input.attr("name") == "proceedings")
	                       {
				    		if(input.val().length>=5000){
				    			return false;
				    		}
	                       }
	                       return true; 
				    },
				    yearNullValidator : function(input,params) {
				    	if (input.attr("name") == "year")
	                       {
	                        return $.trim(input.val()) !== "";
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
					  proceedings : "Maximum 5000 Charecters Allowed",
					  yearNullValidator:"Please select year",
				  }
			 });
	})(jQuery, kendo);
</script>
<style type="text/css">

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
.k-edit-form-container .k-edit-buttons {
    border-width: 1px 0 0;
    border-style: none;
    bottom: -1em;
    clear: both;
    padding: 0.6em;
    position: relative;
    text-align: right;
}


                
 .k-upload-button input {
	z-index: 100000
}
                
                
.wrappers {
	display: inline;
	float: left;
	width: 350px;
	padding-top: 10px;

}

div.tagsinput span.tag {
    background: none repeat scroll 0 0 #0F6899;
    color: white;
    font-size: 13px;
    line-height: 20px;
    border: 0;
    display: block;
    float: left;
    margin: 5px;
    padding: 0 8px;
    border: medium none;
    

}

div.tagsinput input {
    background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
    border: medium none;
    margin: 0;
    padding: 0;
    height: 28px;
}

div.tagsinput span.tag a {
    color: white;
    float: right;
    font-size: 16px;
    font-weight: bold;
    
}

div.tagsinput {
    background: none repeat scroll 0 0 #FDFDFD;
    border: 2px solid #DDDDDD;
    box-sizing: border-box;
    padding: 0;
    width: 71%;
    border-radius: 6px;
    
}

.cke_dialog
{
    z-index: 10055 !important;
}
.cke_panel {
z-index: 1000002 !important;
} 
</style>