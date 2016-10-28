<%@include file="/common/taglibs.jsp"%>

<c:url value="/project/template/read" var="readUrl" />
<c:url value="/project/template/save" var="saveUrl" />
<c:url value="/project/template/update" var="updateUrl" />
<c:url value="/project/template/delete" var="destroyUrl" />


<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa fa-lg fa-fw fa-gears"></i> Manage Projects Templates</h1>
			</div>
		</div>
		
		
<kendo:grid name="grid" resizable="true" pageable="true" sortable="true" scrollable="true" edit="projectEvent" change="onChange" selectable="true" dataBound="dataBound" detailTemplate="detailedTemplateProject">
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
			<kendo:grid-filterable-operators>
				<kendo:grid-filterable-operators-string eq="Is equal to" />
			</kendo:grid-filterable-operators>

		</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" />
		<kendo:grid-toolbar>
			<kendo:grid-toolbarItem name="create" text="Add Template"/>
		</kendo:grid-toolbar>
		<kendo:grid-columns>
			<kendo:grid-column title="Name" field="templateName" width="110px" />
			<kendo:grid-column title="Type" field="type" editor="typeEditor" width="100px"/>
			<kendo:grid-column title="Status" field="status" width="110px" template="#: status == '1' ? 'Active' :'Inactive'#"/>
			
			<kendo:grid-column title="&nbsp;" width="160px">
				<kendo:grid-column-command>
					<kendo:grid-column-commandItem name="edit" />
					<kendo:grid-column-commandItem name="destroy" />
					<kendo:grid-column-commandItem name="Activate" click="activateProject"/>
					<kendo:grid-column-commandItem name="De-Activate" click="deActivateProject" />
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
						<kendo:dataSource-schema-model-field name="id" type="number"/>
						<kendo:dataSource-schema-model-field name="templateName"/>
						<kendo:dataSource-schema-model-field name="createdDate" type="date"/>
						<kendo:dataSource-schema-model-field name="lastUpdatedDate" type="date"/>
						<kendo:dataSource-schema-model-field name="type"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid>
	
	<kendo:grid-detailTemplate id="detailedTemplateProject">
    <kendo:tabStrip name="tabStrip_#=id#">
        <kendo:tabStrip-items>
            <kendo:tabStrip-item text="Levels Details" selected="true">
                <kendo:tabStrip-item-content>
                    <kendo:grid name="grid_#=id#" pageable="true" sortable="true" scrollable="false" edit="levelEvent">
                    	<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
						<kendo:grid-filterable extra="false">
							<kendo:grid-filterable-operators>
								<kendo:grid-filterable-operators-string eq="Is equal to" />
							</kendo:grid-filterable-operators>
						</kendo:grid-filterable>
						<kendo:grid-editable mode="popup" />
						<kendo:grid-toolbar>
							<kendo:grid-toolbarItem name="create" text="Add Level"/>
						</kendo:grid-toolbar>
                        <kendo:grid-columns>
                            <kendo:grid-column title="Name" field="name" width="110px" />
                          <kendo:grid-column title="level"  field="level" width="100px" />
                            <kendo:grid-column title="&nbsp;" width="160px">
								<kendo:grid-column-command>
									<kendo:grid-column-commandItem name="edit" />
									<kendo:grid-column-commandItem name="destroy" />
								</kendo:grid-column-command>
							</kendo:grid-column>
                        </kendo:grid-columns>
                        <kendo:dataSource pageSize="5" requestEnd="onRequestEndChild">
                           	<kendo:dataSource-transport>
									<kendo:dataSource-transport-create url="${saveLevelUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-read url="${readLevelUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
									<kendo:dataSource-transport-update url="${updateLevelUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-destroy url="${destroyLevelUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
							</kendo:dataSource-transport>
							<kendo:dataSource-schema>
								<kendo:dataSource-schema-model id="phlId">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="phlId" type="number"/>
										<kendo:dataSource-schema-model-field name="name"/>
										<kendo:dataSource-schema-model-field name="level" type="number"></kendo:dataSource-schema-model-field>
									</kendo:dataSource-schema-model-fields>
								</kendo:dataSource-schema-model>
							</kendo:dataSource-schema>
                        </kendo:dataSource>
                    </kendo:grid>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
        </kendo:tabStrip-items>
    </kendo:tabStrip>
</kendo:grid-detailTemplate>
</div>

<script>
	
	var id = "";
	function onChange(arg) {
		var gview = $("#grid").data("kendoGrid");
		var selectedItem = gview.dataItem(gview.select());
		id = selectedItem.id;
		this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}
	function projectEvent(e){
		$('div[data-container-for="status"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		$('div[data-container-for="companyName"]').remove();
		$('label[for="companyName"]').closest('.k-edit-label').remove();
		if (e.model.isNew()) {
			securityCheckForActions("./master/project/create");
			$(".k-window-title").text("Add New Template");
			$(".k-grid-update").text("Save");
		}else{
			securityCheckForActions("./master/project/update");
			$(".k-window-title").text("Edit "+e.model.get("name")+" Details");
		}
	}
	
	
	function activateProject(){
		 var result=securityCheckForActionsForStatus("./master/project/approve");			
	     if(result=="success")
	     {	
			$.ajax({
				type : "POST",
				url : "./project/template/status/1/"+id,
				dataType : "text",
				success : function(response) {
					alert(response);
					$('#grid').data('kendoGrid').dataSource.read();
				}
			});
	     }
	}
	
	
	
	function deActivateProject(){
		$.ajax({
			type : "POST",
			url : "./project/template/status/0/"+id,
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
	
	
	</script>
	
	<style>
	.bgGreenColor{
	background: #2b908f;
	color: #FFFFFF
}
.k-window-titlebar{
	height: 25px;
}
	</style>