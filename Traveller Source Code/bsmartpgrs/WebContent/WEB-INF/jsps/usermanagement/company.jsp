<%@include file="/common/taglibs.jsp"%>

<c:url value="/company/read" var="readUrl" />
<c:url value="/company/save" var="saveUrl" />
<c:url value="/company/update" var="updateUrl" />
<c:url value="/company/delete" var="destroyUrl" />
<c:url value="/company/company" var="getCompany" />

<c:url value="/company/getCompanyNameForFilter" var="getCompanyNameForFilter" />
<c:url value="/company/getAddressForFilter" var="getAddressForFilter" />
<c:url value="/company/getEmailForFilter" var="getEmailForFilter" />
<c:url value="/company/getBankNameFilter" var="getBankNameFilter" />
<c:url value="/company/getIfcsCodeFilter" var="getIfcsCodeFilter" />
<c:url value="/company/getServiceTaxFilter" var="getServiceTaxFilter" />
<c:url value="/company/getPanNoFilter" var="getPanNoFilter" />
<c:url value="/company/getTinNoFilter" var="getTinNoFilter" />
<c:url value="/company/getCompanyStatusFilter" var="getCompanyStatusFilter" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark"><i class="fa fa-lg fa-fw fa-university"></i> Manage Company</h1>
		</div>
	</div>
		
	<kendo:grid name="grid" resizable="true" selectable="true" pageable="true" sortable="true" scrollable="true" edit="companyEvent" change="onChange" dataBound="dataBound"  remove="removeCompany">
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
			<kendo:grid-filterable-operators>
				<kendo:grid-filterable-operators-string eq="Is equal to" />
			</kendo:grid-filterable-operators>
		</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" />
		<kendo:grid-toolbar>
			<kendo:grid-toolbarItem name="create" text="Add Company"/>
			  <kendo:grid-toolbarItem text="Clear_Filter" />
			
			 
		</kendo:grid-toolbar>
		<kendo:grid-columns>
		
			<kendo:grid-column title="Company Name" field="companyName" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getCompanyNameForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Address" field="companyAddress1" width="130px" template="#:companyAddress1#, #:companyAddress2#">
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getAddressForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Address 2" field="companyAddress2" width="130px" hidden="true"/> 
			<kendo:grid-column title="Phone No." field="phoneNumber" width="130px" />
			<kendo:grid-column title="Mobile No." field="mobileNumber" width="130px" />
			<kendo:grid-column title="Fax No." field="faxNumber" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getAddressForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Email Id" field="emailId" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getEmailForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
		    	
			<kendo:grid-column title="Bank Name" field="bankName" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getBankNameFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="IFSC Code" field="ifscCode" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getIfcsCodeFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
		    	<kendo:grid-column title="Branch Code" field="branchCode" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getIfcsCodeFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Account No." field="accountNumber" width="130px" />
			<kendo:grid-column title="Service Tax No." field="serviceTaxNumber" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getServiceTaxFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Tin No." field="tinNumber" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getTinNoFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Pan No." field="panNumber" width="130px" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getPanNoFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Status" field="status" width="130px" template="#:status == 0 ? 'Inactive' : 'Active' #" >
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
						function helpDeskTicketStatusFilter(element){
						       element.kendoComboBox({
						        autoBind : true,
						        filter:"startswith",
						        placeholder : "Select Status",
						        dataTextField : "status",
						        dataValueField : "statusId", 
						        dataSource : {
						         transport : {  
						          read :  "${getCompanyStatusFilter}"
						         }
						        } 
						       });
						         }
						</script>
					</kendo:grid-column-filterable-ui>
				</kendo:grid-column-filterable>
			</kendo:grid-column>	
			
			<kendo:grid-column title="&nbsp;" width="300px">
				<kendo:grid-column-command>
					<kendo:grid-column-commandItem name="edit" />
					<kendo:grid-column-commandItem name="destroy" />
					<kendo:grid-column-commandItem name="Activate" click="activateCompany"/>
					<kendo:grid-column-commandItem name="De-Activate" click="deActivateCompany" />
				</kendo:grid-column-command>
			</kendo:grid-column>
			
		</kendo:grid-columns>
		<kendo:dataSource pageSize="20" requestEnd="onRequestEnd">

			<kendo:dataSource-transport>
				<kendo:dataSource-transport-create url="${saveUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json" />
				<kendo:dataSource-transport-update url="${updateUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-destroy url="${destroyUrl}" dataType="json" type="GET" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema>
				<kendo:dataSource-schema-model id="id">
					<kendo:dataSource-schema-model-fields>
						<kendo:dataSource-schema-model-field name="id" type="number"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="companyName"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="companyAddress1"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="companyAddress2"/>
						<kendo:dataSource-schema-model-field name="phoneNumber"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="mobileNumber"/>
						<kendo:dataSource-schema-model-field name="faxNumber"/>
						<kendo:dataSource-schema-model-field name="emailId"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="bankName"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="ifscCode"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="accountNumber"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="serviceTaxNumber"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="tinNumber"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="panNumber"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
					    <kendo:dataSource-schema-model-field name="status" type="number"/>
					    
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid>
</div>

	<script>
	var id = "";
	function onChange(arg) {
		var gview = $("#grid").data("kendoGrid");
		var selectedItem = gview.dataItem(gview.select());
		id = selectedItem.id;
		this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}
	function removeCompany(){
		var conf = confirm("Are you sure want to delete this Company?");
		    if(!conf){
		    $('#grid').data().kendoGrid.dataSource.read();
		    throw new Error('deletion aborted');
		     } 
    }
	
	function companyEvent(e){
		$('div[data-container-for="status"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		
		$('div[data-container-for="branchCode"]').hide();
		$('label[for="branchCode"]').closest('.k-edit-label').hide();
		
		$(".k-edit-form-container").css({
			"width" : "550px"
		});
		$(".k-window").css({
			"top": "150px"
		});
		$('.k-edit-label:nth-child(14n+1)').each(
				function(e) {
					$(this).nextAll(':lt(13)').addBack().wrapAll(
							'<div class="wrappers"/>');
				});
		
		$(".k-grid-cancel").click(function () {
			 var grid = $("#grid").data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	    });
		
		if (e.model.isNew()) {
			$(".k-window-title").text("Add Company");
			$(".k-grid-update").text("Save");
		}else{
			$(".k-window-title").text("Edit "+e.model.get("companyName")+" Details");
		}
	}
	
	
	function activateCompany(){
		$.ajax({
			type : "GET",
			url : "./company/status/"+1+"/"+id,
			dataType : "text",
			success : function(response) {
				alert(response);
				$('#grid').data('kendoGrid').dataSource.read();
			}
		});
	}
	
	function deActivateCompany(){
		$.ajax({
			type : "GET",
			url : "./company/status/"+0+"/"+id,
			dataType : "text",
			success : function(response) {
				alert(response);
				$('#grid').data('kendoGrid').dataSource.read();
			}
		});
	}
	
	function dataBound(e) {

		var data = this.dataSource.view(), row;
		var grid = $("#grid").data("kendoGrid");
		for (var i = 0; i < data.length; i++) {

			row = this.tbody.children("tr[data-uid='" + data[i].uid + "']");
			var status = data[i].status;
			var currentUid = data[i].uid;
			var currenRow = grid.table.find("tr[data-uid='" + currentUid+ "']");
			if (status === 1) {
				row.addClass("bgGreenColor");
				var activateButton = $(currenRow).find(".k-grid-Activate");
				var deleteButton = $(currenRow).find(".k-grid-delete");
				var editButton = $(currenRow).find(".k-grid-edit");
				activateButton.hide();
				deleteButton.hide();
				editButton.hide();
			}else{
				var deActivateButton = $(currenRow).find(".k-grid-De-Activate");
				deActivateButton.hide();
			}
			
		}
	}
	
	function onRequestEnd(r){
		if (r.type == 'create') {
			alert("Added Successfully");
			$('#grid').data('kendoGrid').dataSource.read();
			$('#grid').data('kendoGrid').refresh();
		} else if (r.type == 'update') {
			alert("Updated Successfully");
			$('#grid').data('kendoGrid').dataSource.read();
			$('#grid').data('kendoGrid').refresh();
		}else if (r.type == 'destroy') {
			alert("Deleted Successfully");
		}
	}
	
	$("#grid").on("click", ".k-grid-Clear_Filter", function() {
		$("form.k-filter-menu button[type='reset']").slice().trigger("click");
		var grid = $("#grid").data("kendoGrid");
		grid.dataSource.read();
		grid.refresh();
	});

	</script>
	
<style>
.k-edit-form-container {
	text-align: center;
	position: relative;
}

.wrappers {
	display: inline;
	float: left;
	width: 250px;
	padding-top: 10px;
	position: relative;
}
.bgGreenColor{
	background: #2b908f;
	color: #FFFFFF
}
.k-window-titlebar{
	height: 25px;
}
	</style>