<%@include file="/common/taglibs.jsp"%>

<c:url value="/helpDesk/readSubCategoryUrl" var="readSubCategoryUrl"/>
<c:url value="/helpDesk/createSubCategoryUrl" var="createSubCategoryUrl"/>
<c:url value="/helpDesk/updateSubCategoryUrl" var="updateSubCategoryUrl"/>
<c:url value="/helpDesk/destroySubCategoryUrl" var="destroySubCategoryUrl"/>

<c:url value="/helpDesk/readLevelUrl" var="readLevelUrl"/>
<c:url value="/helpDesk/createLevelUrl" var="saveLevelUrl"/>
<c:url value="/helpDesk/updateLevelUrl" var="updateLevelUrl"/>
<c:url value="/helpDesk/destroyLevelUrl" var="destroyLevelUrl"/>

<c:url value="/helpDesk/readSubCategoryNamesForUniqueness" var="readSubCategoryNames"/>
<c:url value="/helpDesk/getAllActiveCategoryNamesUrl" var="getAllActiveCategoryNamesUrl"/>
<c:url value="/roles/getDesignationNamesUrl" var="getDesignationNamesUrl"/>

<c:url value="/subCategory/filter" var="commonFilterForSubCategoryUrl"/>
<c:url value="/helpDesk/subCategoryStatusFilter" var="subCategoryStatusFilter"/>
<c:url value="/common/getAllChecks" var="allChecksUrl" />

<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Manage Sub Categories</h1>
			</div>
		</div>
   <kendo:grid name="subCategoryGrid" change="onChange" detailTemplate="escalationTemplate" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true" edit="subCategoryGridEvent" remove="removeSubCategories">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true" ></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string contains="Contains" eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this SubCategory?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add SubCategory" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
	   
	   		<kendo:grid-column title="SubCategory&nbsp;Id" field="subCategoryId" hidden="true" width="100px">
			</kendo:grid-column>
			
			<kendo:grid-column title="Category&nbsp;*" field="categoryId" editor="categoryEditor" hidden="true" width="100px">
			</kendo:grid-column>
	   
	   		<kendo:grid-column title="Category&nbsp;*" field="categoryName" width="100px">
				<kendo:grid-column-filterable>
					<kendo:grid-column-filterable-ui>
						<script>
							function DepartmentNameFilter(element) {
								element.kendoAutoComplete({
									dataSource : {
										transport : {
											read : "${getDepartmentForDesignationFilter}"
										}
									}
								});
							}
						</script>
					</kendo:grid-column-filterable-ui>
				</kendo:grid-column-filterable>
			</kendo:grid-column>
	   
	   		<kendo:grid-column title="Sub&nbsp;Category&nbsp;Name&nbsp;*" field="subCategoryName" width="100px" filterable="true">
	   		<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForSubCategoryUrl}/subCategoryName"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   	</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="No.&nbsp;of&nbsp;Esc.&nbsp;Levels&nbsp;*" field="noLevels" format="{0:n0}" width="70px">
	 		</kendo:grid-column>
	   
			<kendo:grid-column title="Status" field="status" template="#:status == 0 ? 'In Active' : 'Active' #" width="75px" filterable="true">
			<kendo:grid-column-filterable>
					<kendo:grid-column-filterable-ui>
						<script type="text/javascript">
						function categoryStatusFilter(element){
							element.kendoComboBox({
								autoBind : true,
								 filter:"startswith",
								dataTextField : "status",
								dataValueField : "statusId", 
								dataSource : {
									transport : {		
										read :  "${subCategoryStatusFilter}"
									}
								} 
							});
					   	}
						</script>
					</kendo:grid-column-filterable-ui>	
			</kendo:grid-column-filterable>
	    	</kendo:grid-column>
		    
			<kendo:grid-column title="" template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.subCategoryId#'>#= data.status == 1 ? 'De-activate' : 'Activate' #</a>" width="80px" />
				
		    <kendo:grid-column title="&nbsp;" width="120px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>
				  <kendo:grid-column-commandItem name="destroy" />			
			   </kendo:grid-column-command>
			</kendo:grid-column>						
	   </kendo:grid-columns>
	   
	   <kendo:dataSource pageSize="20" requestEnd="onSubCategoryRequestEnd" requestStart="onSubCategoryRequestStart">
		 <kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readSubCategoryUrl}" dataType="json" type="POST" contentType="application/json" />
			<kendo:dataSource-transport-create url="${createSubCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-update url="${updateSubCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-destroy url="${destroySubCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
		 </kendo:dataSource-transport>

		 <kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="subCategoryId">
			  <kendo:dataSource-schema-model-fields>
			    <kendo:dataSource-schema-model-field name="subCategoryId" type="number"/>
			    <kendo:dataSource-schema-model-field name="categoryId" type="number"/>
			    <kendo:dataSource-schema-model-field name="subCategoryName" type="string"/>
			    <kendo:dataSource-schema-model-field name="noLevels" type="number" defaultValue=""/>
			  </kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		 </kendo:dataSource-schema>
	   </kendo:dataSource>
	</kendo:grid>	
	
	<kendo:grid-detailTemplate id="escalationTemplate">
		<kendo:tabStrip name="tabStrip_#=subCategoryId#">
		<kendo:tabStrip-animation></kendo:tabStrip-animation>
	<kendo:tabStrip-items>
	<kendo:tabStrip-item text="Escalation Details" selected="true">
                <kendo:tabStrip-item-content>
                    <kendo:grid name="escalation_#=subCategoryId#" pageable="true" sortable="true" scrollable="false" edit="escalationGridEvent" selectable="true" dataBound="escalationdatabound">
                    	<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
						<kendo:grid-filterable extra="false">
							<kendo:grid-filterable-operators>
								<kendo:grid-filterable-operators-string eq="Is equal to" />
							</kendo:grid-filterable-operators>
						</kendo:grid-filterable>
						<kendo:grid-editable mode="popup" />
						<kendo:grid-toolbar>
							<kendo:grid-toolbarItem name="create" text="Add Escalation Level"/>
						</kendo:grid-toolbar>
                        <kendo:grid-columns>
                            <kendo:grid-column title="escId" field="escId" hidden="true" width="110px"/>
                            
                            <kendo:grid-column title="subCategoryId" field="subCategoryId" hidden="true" width="110px"/>
                            
                          	<kendo:grid-column title="Level&nbsp;*"  field="level" width="80px"/>
                          
                          	<kendo:grid-column title="Designation&nbsp;*" field="dnId" editor="designationEditor" hidden="true" width="100px">
							</kendo:grid-column>
	   
	   						<kendo:grid-column title="Designation&nbsp;*" field="dnName" width="80px">
							</kendo:grid-column>
							
							<kendo:grid-column title="Notification&nbsp;Type" field="notificationType" editor="dropDownChecksTODEditor" width="100px">
							</kendo:grid-column>
							
							<kendo:grid-column title="Assignment&nbsp;Type" field="assignmentType" editor="dropDownChecksTODEditor" width="100px">
							</kendo:grid-column>
							
							<kendo:grid-column title="Time Lines(In hrs)*"  field="urbanTimeLines" width="140px"/>
							
							<%-- <kendo:grid-column title="Rural Time Lines(In hrs)*"  field="ruralTimeLines" width="140px"/> --%>
                          	
                            <kendo:grid-column title="&nbsp;" width="140px">
								<kendo:grid-column-command>
									<kendo:grid-column-commandItem name="edit" />
									<kendo:grid-column-commandItem name="destroy" />
								</kendo:grid-column-command>
							</kendo:grid-column>
                        </kendo:grid-columns>
                        <kendo:dataSource pageSize="5" requestEnd="onLevelRequestEnd" requestStart="onLevelRequestStart">
                           	<kendo:dataSource-transport>
									<kendo:dataSource-transport-create url="${saveLevelUrl}/#=subCategoryId#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-read url="${readLevelUrl}/#=subCategoryId#" dataType="json" type="POST" contentType="application/json" />
									<kendo:dataSource-transport-update url="${updateLevelUrl}/#=subCategoryId#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-destroy url="${destroyLevelUrl}/#=subCategoryId#" dataType="json" type="GET" contentType="application/json" />
							</kendo:dataSource-transport>
							<kendo:dataSource-schema>
								<kendo:dataSource-schema-model id="escId">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="escId" type="number"/>
										<kendo:dataSource-schema-model-field name="subCategoryId" type="number"/>
										<kendo:dataSource-schema-model-field name="level" type="number" defaultValue=""/>
										<kendo:dataSource-schema-model-field name="dnId" type="number"/>
										<kendo:dataSource-schema-model-field name="notificationType" type="string" defaultValue="None"/>
										<kendo:dataSource-schema-model-field name="assignmentType" type="string" defaultValue="Assigned"/>
										<kendo:dataSource-schema-model-field name="dnName" type="string"/>
										<kendo:dataSource-schema-model-field name="urbanTimeLines" type="number" defaultValue=""/>
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
	<div id="alertsBox" title="Alert"></div>
	
	<script>
	var level="";
	var selectedRowId = "";
	function onChange(arg) {
		var gview = $("#subCategoryGrid").data("kendoGrid");
		var selectedItem = gview.dataItem(gview.select());
		selectedRowId = selectedItem.subCategoryId;
		level=selectedItem.noLevels;
		this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}	
	
	/********************************************************************************/
	var childlevel="";
	function escalationdatabound(){
		var data = this.dataSource.view(), row;
		if(level == data.length ){
					$(".k-grid-add", "#escalation_" + selectedRowId)
					.hide();
				}
		childlevel=data.length;
		
	}
	
	/********************************************************************************/
	 $("#subCategoryGrid").on("click", "#temPID", function(e) 
	 {
	 	 var button = $(this), enable = button.text() == "Activate";
		 var widget = $("#subCategoryGrid").data("kendoGrid");
		 var dataItem = widget.dataItem($(e.currentTarget).closest("tr"));
		 
		 /* var result=securityCheckForActionsForStatus("./userManagement/roles/statusButton");			
	     if(result=="success")
	     {  */
			 if (enable)
			 {
				$.ajax
				({
					type : "POST",
					url : "./helpDesk/subCategoryStatus/" + dataItem.id + "/activate",
					dataType : 'text',
					success : function(response) 
					{
						$("#alertsBox").html("");
						$("#alertsBox").html(response);
						$("#alertsBox").dialog
						({
							modal : true,
							buttons : {
							   "Close" : function() {
							      $(this).dialog("close");
								}
							}
							});
						button.text('Deactivate');
						$('#subCategoryGrid').data('kendoGrid').dataSource.read();
				     }
				 });
			  }
			  else 
			  {
				  $.ajax
				  ({
					   type : "POST",
					   url : "./helpDesk/subCategoryStatus/" + dataItem.id + "/deactivate",
					   dataType : 'text',
					   success : function(response) 
					   {
							$("#alertsBox").html("");
							$("#alertsBox").html(response);
							$("#alertsBox").dialog({
								modal : true,
								buttons : 
								{
									"Close" : function() {
										$(this).dialog("close");
									 }
								}
						   });
						   button.text('Activate');
						   $('#subCategoryGrid').data('kendoGrid').dataSource.read();
					   }
				 });
			  }	
	      /* } */
		});
	/**********************************************************************************/
	
	function categoryEditor(container, options) 
		{
		  $('<input name = "Category Name" data-text-field="categoryName" id="categoryId" required="true" validationMessage="Category is required" data-value-field="categoryId" data-bind="value:' + options.field + '"  />')
					.appendTo(container).kendoDropDownList({
						optionLabel : "Select",
						dataSource : {
							transport : {
								read: "${getAllActiveCategoryNamesUrl}"
							}
						}
					});
			$('<span class="k-invalid-msg" data-for="Category Name"></span>').appendTo(container);
		}
	
	function designationEditor(container, options) 
	{
	  $('<input name = "designation" data-text-field="dnName" id="dnId" required="true" validationMessage="Designation is required" data-value-field="dnId" data-bind="value:' + options.field + '"  />')
				.appendTo(container).kendoDropDownList({
					optionLabel : "Select",
					dataSource : {
						transport : {
							read: "${getDesignationNamesUrl}"
						}
					}
				});
		$('<span class="k-invalid-msg" data-for="designation"></span>').appendTo(container);
	}
	
	function dropDownChecksTODEditor(container, options) {
		var res = (container.selector).split("=");
		var attribute = res[1].substring(0,res[1].length-1);
		
		$('<input data-text-field="text" data-value-field="value" data-bind="value:' + options.field + '" name = "'+attribute+'" id = "'+attribute+'Id"/>')
				.appendTo(container).kendoDropDownList({
					defaultValue : false,
					sortable : true,
					dataSource : {
						transport : {
							read : "${allChecksUrl}/"+attribute,
						}
					}
				});
		 $('<span class="k-invalid-msg" data-for="'+attribute+'"></span>').appendTo(container);
	}
	
	var res1 = new Array();
	function subCategoryGridEvent(e)
	{		
		 $('a[id="temPID"]').remove();	
		 
		 $('label[for="categoryName"]').parent().hide();
		 $('div[data-container-for="categoryName"]').hide();
		 
		 $('label[for="subCategoryId"]').parent().hide();
		 $('div[data-container-for="subCategoryId"]').hide();
		 
		 $('label[for="status"]').parent().hide();
		 $('div[data-container-for="status"]').hide();
		 
		 $(".k-grid-cancel").click(function () {
			 var grid = $("#subCategoryGrid").data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	     });
		 
		 if (e.model.isNew()) 
	     {
			// securityCheckForActions("./userManagement/roles/createButton");
			 $(".k-window-title").text("Add SubCategory Details");
			 $(".k-grid-update").text("Save");
			 res1 = [];
			 $.ajax
			 ({
			      type : "GET",
				  dataType:"text",
				  url : '${readSubCategoryNames}',
				  dataType : "JSON",
				  success : function(response) 
				  {
					 for(var i = 0; i<response.length; i++) 
					 {
					   res1[i] = response[i];	
				     }
				  }
			  });
		  }
		 else
		 {
			  var gview = $("#subCategoryGrid").data("kendoGrid");
			  var selectedItem = gview.dataItem(gview.select());
			  var subCategoryName = selectedItem.subCategoryName;
			  res1 = [];
			   $.ajax({
			    type : "GET",
			    dataType : "text",
			    url : '${readSubCategoryNames}',
			    dataType : "JSON",
			    success : function(response) {
			     var j = 0;
			     for (var i = 0; i < response.length; i++) {
			      if (response[i] != subCategoryName) {

			       res1[j] = response[i];
			       j++;
			      }
			     }
			    }
			   });
			//securityCheckForActions("./userManagement/roles/updateButton");
			$(".k-window-title").text("Edit SubCategory Details");
		    $(".k-grid-update").text("Update");
		 }
	 }
	
	function escalationGridEvent(e)
	{		
		 
		 $('label[for="escId"]').parent().hide();
		 $('div[data-container-for="escId"]').hide();
		 
		 $('label[for="subCategoryId"]').parent().hide();
		 $('div[data-container-for="subCategoryId"]').hide();
		 
		 $('label[for="dnName"]').parent().hide();
		 $('div[data-container-for="dnName"]').hide();
		 
		 $('label[for="status"]').parent().hide();
		 $('div[data-container-for="status"]').hide();
		 
		 $(".k-grid-cancel").click(function () {
			 if(level==childlevel){
				$(".k-grid-add", "#escalation_" + selectedRowId)
				.show();
		 	}
			 var grid = $("#escalation_"+selectedRowId).data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	     });
		 $(".k-i-close").click(function () {
			 if(level==childlevel){
				$(".k-grid-add", "#escalation_" + selectedRowId)
				.show();
		 	}
			 var grid = $("#escalation_"+selectedRowId).data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	     });
		 
		 if (e.model.isNew()) 
	     {
			// securityCheckForActions("./userManagement/roles/createButton");
			 $(".k-window-title").text("Add SubCategory Details");
			 $(".k-grid-update").text("Save");
			 
			 
			 
		  }
		 else {
			//securityCheckForActions("./userManagement/roles/updateButton");
			$(".k-window-title").text("Edit SubCategory Details");
		    $(".k-grid-update").text("Update");
		 }
	 }
	 
	function removeSubCategories(){
		var conf = confirm("Are you sure want to delete this SubCategory?");
		    if(!conf){
		    $('#subCategoryGrid').data().kendoGrid.dataSource.read();
		    throw new Error('deletion aborted');
		     } 
	}
	$(document).on('mouseover', 'input[name="level"]', function() {
		if(childlevel==1){
			 $('input[name="level"]').val(childlevel);
			 $('input[name="level"]').prop('readonly', true); 
				var naren=$('div[data-container-for="level"]');
				naren.find(".k-link")
			    .addClass("k-state-disabled").unbind("mousedown");
				$('input[name="level"]').prop('readonly', true);
				$("#assignmentTypeId").data("kendoDropDownList").select(0);
				var dropdownlist = $("#assignmentTypeId").data("kendoDropDownList");
				dropdownlist.readonly();
		 }
		});
	 function onSubCategoryRequestEnd(e)
	 {
		 if (typeof e.response != 'undefined')
		 {
			if (e.response.status == "FAIL")
			{
				errorInfo = "";			
				errorInfo = e.response.result.invalid;		
				var i = 0;
				for (i = 0; i < e.response.result.length; i++) 
				{
					errorInfo += (i + 1) + ". "	+ e.response.result[i].defaultMessage + "\n";
				}
				if (e.type == "create") 
				{
					alert("Error: Creating the Role Details\n\n" + errorInfo);
				}
				if (e.type == "update") 
				{
					alert("Error: Updating the Role Details\n\n" + errorInfo);
				}
				var grid = $("#subCategoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}			
			if (e.type == "update" && !e.response.Errors) 
			{	
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("SubCategory updated successfully");
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
			}
			if (e.type == "create" && !e.response.Errors)
			{
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("SubCategory created successfully");
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
			}
			if (e.response.status == "AciveSubCategoryDestroyError") {
	  			$("#alertsBox").html("");
	  			$("#alertsBox").html("Active sub category cannot be deleted");
	  			$("#alertsBox").dialog({
	  				modal : true,
	  				buttons : {
	  					"Close" : function() {
	  						$(this).dialog("close");
	  					}
	  				}
	  			});
	  			var grid = $("#subCategoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
	  		return false;
	  	    }
			if(e.type == "destroy" && !e.response.Errors)
			{
				
				
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("SubCategory deleted successfully");
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
				var grid = $("#subCategoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			} 
		 }
	 }
	 
	 function onSubCategoryRequestStart(e)
	 {
	     var grid= $("#subCategoryGrid").data("kendoGrid");
	     grid.cancelRow();
	 }
	 
	 function onLevelRequestEnd(e)
	 {
		 if (typeof e.response != 'undefined')
		 {
			if (e.response.status == "FAIL")
			{
				errorInfo = "";			
				errorInfo = e.response.result.invalid;		
				var i = 0;
				for (i = 0; i < e.response.result.length; i++) 
				{
					errorInfo += (i + 1) + ". "	+ e.response.result[i].defaultMessage + "\n";
				}
				if (e.type == "create") 
				{
					alert("Error: Creating the Role Details\n\n" + errorInfo);
				}
				if (e.type == "update") 
				{
					alert("Error: Updating the Role Details\n\n" + errorInfo);
				}
				var grid = $("#escalation_" + selectedRowId).data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}			
			if (e.type == "update" && !e.response.Errors) 
			{	
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Escalation Level updated successfully");
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
			}
			if (e.type == "create" && !e.response.Errors)
			{
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Escalation Level created successfully");
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
			}
			if (e.response.status == "AciveSubCategoryDestroyError") {
	  			$("#alertsBox").html("");
	  			$("#alertsBox").html("Active sub category cannot be deleted");
	  			$("#alertsBox").dialog({
	  				modal : true,
	  				buttons : {
	  					"Close" : function() {
	  						$(this).dialog("close");
	  					}
	  				}
	  			});
	  			var grid = $("#escalation_" + selectedRowId).data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
	  		return false;
	  	    }
			if(e.type == "destroy" && !e.response.Errors)
			{
				
				
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Escalation Level deleted successfully");
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
				var grid =$("#escalation_" + selectedRowId).data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			} 
		 }
	 }
	 
	 function onLevelRequestStart(e)
	 {
	     var grid= $("#escalation_"+selectedRowId).data("kendoGrid");
	     grid.cancelRow();
	 }
	 
	 $("#subCategoryGrid").on("click", ".k-grid-Clear_Filter", function()
	 {
		 $("form.k-filter-menu button[type='reset']").slice().trigger("click");
		 var grid = $("#subCategoryGrid").data("kendoGrid");
		 grid.dataSource.read();
		 grid.refresh();
	 });
	 
	//Validator Function
	 (function($, kendo) 
	  {
		 $.extend(true,kendo.ui.validator,
		 {
			rules : { 
		  		      
					 subCategoryNameNullValidator : function(input,params) { 
					  if (input.attr("name") == "subCategoryName") {
					     return $.trim(input.val()) !== "";
					  }
					  return true;
		             }, 
		             subCategoryNamevalidation : function(input, params) { 
					   if (input.filter("[name='subCategoryName']").length && input.val()) {
						   return /^[\s\S]{1,100}$/.test(input.val());
					   }
						 return true;
					  },
					  subCategoryNameUniqueness : function(input,params)  {
					        if (input.filter("[name='subCategoryName']").length && input.val()) {
					          enterdService = input.val().toUpperCase(); 
					          for(var i = 0; i<res1.length; i++) {
					            if ((enterdService == res1[i].toUpperCase()) && (enterdService.length == res1[i].length) ) 
					            {								            
					              return false;								          
					            }
					          }
					         }
					         return true;
					    },
					    noLevelsRequiredValidation : function(input, params){
			                 if (input.attr("name") == "noLevels")
			                 {
			                  return $.trim(input.val()) !== "";
			                 }
			                 return true;
			                },
					    noLevelsLengthValidation: function (input, params) 
			             {         
			                 if (input.filter("[name='noLevels']").length && input.val() != "") 
			                 {
			                	 return /^[0-9]{1,2}$/.test(input.val());
			                 }        
			                 return true;
			             }, 
			             levelLengthValidation: function (input, params) 
			             {         
			                 if (input.filter("[name='level']").length && input.val() != "") 
			                 {
			                	 return /^[0-9]{1,2}$/.test(input.val());
			                 }        
			                 return true;
			             },
			             urbanTimeLinesLengthValidation: function (input, params) 
			             {         
			                 if (input.filter("[name='urbanTimeLines']").length && input.val() != "") 
			                 {
			                	 return /^[0-9]{1,5}$/.test(input.val());
			                 }        
			                 return true;
			             },
			             ruralTimeLinesLengthValidation: function (input, params) 
			             {         
			                 if (input.filter("[name='ruralTimeLines']").length && input.val() != "") 
			                 {
			                	 return /^[0-9]{1,5}$/.test(input.val());
			                 }        
			                 return true;
			             },
			             levelRequiredValidation : function(input, params){
			                 if (input.attr("name") == "level")
			                 {
			                  return $.trim(input.val()) !== "";
			                 }
			                 return true;
			                },
			                urbanTimeLinesRequiredValidation : function(input, params){
				             if (input.attr("name") == "urbanTimeLines")
				             {
				               return $.trim(input.val()) !== "";
				              }
				             return true;
				            },
					  },
					  messages : 
					  {
						  subCategoryNameNullValidator : "SubCategory name required",						
						  subCategoryNamevalidation : "SubCategory name allows max 100 characters",
						  subCategoryNameUniqueness : "SubCategory name already exist",
						  noLevelsRequiredValidation : "No. of escalation levels is required",
						  noLevelsLengthValidation : "No. of escalation levels max 2 digit number",
						  levelLengthValidation : "Level max 2 digit number",
						  urbanTimeLinesLengthValidation : "Time lines max 5 digit number",
						  ruralTimeLinesLengthValidation : "Rural time lines max 5 digit number",
						  levelRequiredValidation : "Level is required",
						  urbanTimeLinesRequiredValidation : "Time lines is required"
					  }
				 });
		})(jQuery, kendo);
	 
	</script>