<%@include file="/common/taglibs.jsp"%>

<c:url value="/designation/readDesignationUrl" var="readDesignationUrl"/>
<c:url value="/designation/createDesignationUrl" var="createDesignationUrl"/>
<c:url value="/designation/updateDesignationUrl" var="updateDesignationUrl"/>
<c:url value="/designation/deleteDesignationUrl" var="deleteDesignationUrl"/>

<c:url value="/department/getDepartmentNameFilter" var="getDepartmentNamesUrl"/>

<c:url value="/designation/getDesignationNameFilter" var="getDesignationNameFilter"/>
<c:url value="/designation/getDesignationDescriptionFilter" var="getDesignationDescriptionFilter"/>
<c:url value="/designation/getDesignationStatusFilter" var="getDesignationStatusFilter"/>
<c:url value="/designation/getDepartmentForDesignationFilter" var="getDepartmentForDesignationFilter"/>

<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Manage Designation</h1>
			</div>
		</div>
   <kendo:grid name="designationGrid" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true" edit="designationGridEvent" remove="removeDesignation">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true" ></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Designation" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
	   
	   		<kendo:grid-column title="Department&nbsp;*" field="deptId" editor="departmentEditor" hidden="true" width="100px">
			</kendo:grid-column>
	   
	   		<kendo:grid-column title="Department&nbsp;*" field="deptName" width="100px">
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
	   
			<kendo:grid-column title="Designation Name&nbsp;*" field="dnName" width="75px" filterable="true">
			   <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getDesignationNameFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Designation Description&nbsp;*" field="dnDescription" editor="designationDescriptionEditor" filterable="false" width="80px">
			    <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getDesignationDescriptionFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
			</kendo:grid-column>

		    <kendo:grid-column title="Status" field="designationStatus" width="75px" filterable="true">
		       <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getDesignationStatusFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
		    </kendo:grid-column>
		    
			<kendo:grid-column title=""
				template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.dnId#'>#= data.designationStatus == 'Active' ? 'De-activate' : 'Activate' #</a>"
				width="45px" />
				
		    <kendo:grid-column title="&nbsp;" width="120px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy" />		
			   </kendo:grid-column-command>
			</kendo:grid-column>						
	   </kendo:grid-columns>
	   
	   <kendo:dataSource pageSize="20" requestEnd="onDesignationRequestEnd" requestStart="onDesignationRequestStart">
		 <kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readDesignationUrl}" dataType="json" type="POST" contentType="application/json" />
			<kendo:dataSource-transport-create url="${createDesignationUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-update url="${updateDesignationUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-destroy url="${deleteDesignationUrl}" dataType="json" type="GET" contentType="application/json" />
		 </kendo:dataSource-transport>

		 <kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="dnId">
			  <kendo:dataSource-schema-model-fields>
			    <kendo:dataSource-schema-model-field name="dnId" type="number"/>
			    <kendo:dataSource-schema-model-field name="deptId" type="number"/>
			    <kendo:dataSource-schema-model-field name="dnName" type="string"/>
			    <kendo:dataSource-schema-model-field name="deptName" type="string"/>
			    <kendo:dataSource-schema-model-field name="dnDescription" type="string"/>
			    <kendo:dataSource-schema-model-field name="designationStatus" type="string" defaultValue="Inactive"/>
			  </kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		 </kendo:dataSource-schema>
	   </kendo:dataSource>
	</kendo:grid>	
	</div>
	<div id="alertsBox" title="Alert"></div>
	
	<script>
	$("#designationGrid").on("click", ".k-grid-Clear_Filter", function()
	{
		 $("form.k-filter-menu button[type='reset']").slice().trigger("click");
		 var grid = $("#designationGrid").data("kendoGrid");
		 grid.dataSource.read();
		 grid.refresh();
	});
	  
	  function departmentEditor(container, options) 
		{
		  $('<input name = "departmant" data-text-field="deptName" id="deptId" required="true" validationMessage="Department is required" data-value-field="deptId" data-bind="value:' + options.field + '"  />')
					.appendTo(container).kendoDropDownList({
						optionLabel : "Select",
						dataSource : {
							transport : {
								read: "${getDepartmentNamesUrl}"
							}
						}
					});
			$('<span class="k-invalid-msg" data-for="departmant"></span>').appendTo(container);
		}
	  
	  function removeDesignation(){
			var conf = confirm("Are you sure want to delete this Designation?");
			    if(!conf){
			    $('#designationGrid').data().kendoGrid.dataSource.read();
			    throw new Error('deletion aborted');
			     } 
	  }
	  
	  function designationGridEvent(e)
	  {
		  $(".k-window-title").text("Add Designation Details");
		  $(".k-grid-update").text("Save");
		  
		  $('div[data-container-for="deptName"]').hide(); 
		  $('label[for=deptName]').parent().hide();
			
		  $('a[id="temPID"]').remove();		 
		  $('label[for=status]').parent().hide();
		  $('div[data-container-for="status"]').hide(); 
		  $('label[for=designationStatus]').parent().hide();
		  $('div[data-container-for="designationStatus"]').hide(); 
		  
		  $('label[for=deptName]').parent().hide();
		  $('div[data-container-for="deptName"]').hide();
		  
		  $(".k-grid-cancel").click(function () {
				 var grid = $("#designationGrid").data("kendoGrid");
				 grid.dataSource.read();
				 grid.refresh();
		  });
		  
		  if (e.model.isNew()) 
		  {
			
			  securityCheckForActions("./userManagement/designation/createButton");
			  $(".k-window-title").text("Add Designation Details");
			  $(".k-grid-update").text("Save");
			  
			  var designationName=[];
			    
				$(".k-grid-update").click(function () {
						
					 	var deptId =$("#deptId").val();
					 	var desigName = $('input[name="dnName"]').val();
					 	
						$.ajax({
						     url:'./designation/readDesignationNamesForUniqueness/'+deptId,
						     async: false,
						     success: function (response) {
						    	 
						    	 for(var i = 0; i<response.length; i++) {
						    		 designationName[i] = response[i];
								} 
						    }
						     
						});
					 	
					 	if((desigName)!=null || (desigName)!=""){
					 	   for(var i = 0; i<designationName.length; i++){
					 		   if(desigName==designationName[i]){
									  alert("Designation already exists");
									   return false;

								}
								}
						   }  
					 	
					});
		  }
		  else
		  {
			  securityCheckForActions("./userManagement/designation/updateButton");
			  	 $(".k-window-title").text("Edit Designation Details");
				 $(".k-grid-update").text("Update");
		  }
		  
		   
	  }
	  function designationDescriptionEditor(container,options)
	  {
		  $('<textarea name="dnDescription" data-text-field="dnDescription" data-value-field="dnDescription" data-bind="value:' + options.field + '" style="width: 161px; height: 46px;"/>').appendTo(container);
		  $('<span class="k-invalid-msg" data-for="dnDescription"></span>').appendTo(container); 
	  }
	  function onDesignationRequestEnd(e)
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
						alert("Error: Creating the Designation Details\n\n" + errorInfo);
					}
					if (e.type == "update") 
					{
						alert("Error: Updating the Designation Details\n\n" + errorInfo);
					}
					var grid = $("#designationGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
					return false;
				}			
				if (e.type == "update" && !e.response.Errors) 
				{	
					e.sender.read();
					$("#alertsBox").html("Alert");
					$("#alertsBox").html("Designation Updated successfully");
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
					$("#alertsBox").html("Designation Created successfully");
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
				if (e.response.status == "AciveDesignationMasterDestroyError") {
		  			$("#alertsBox").html("");
		  			$("#alertsBox").html("Active Designation Cannot be Deleted");
		  			$("#alertsBox").dialog({
		  				modal : true,
		  				buttons : {
		  					"Close" : function() {
		  						$(this).dialog("close");
		  					}
		  				}
		  			});
		  			var grid = $("#designationGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
		  		return false;
		  	    }
				if (e.response.status == "CHILD") {
		  			$("#alertsBox").html("");
		  			$("#alertsBox").html("Can't delete Designation, child record found");
		  			$("#alertsBox").dialog({
		  				modal : true,
		  				buttons : {
		  					"Close" : function() {
		  						$(this).dialog("close");
		  					}
		  				}
		  			});
		  			var grid = $("#designationGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
		  		return false;
		  	    }
				if(e.type == "destroy" && !e.response.Errors)
				{
					$("#alertsBox").html("Alert");
					$("#alertsBox").html("Designation Deleted successfully");
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
					var grid = $("#designationGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
				} 
			 } 
	  }
	  function onDesignationRequestStart(e)
	  {
		  var grid= $("#designationGrid").data("kendoGrid");
		  grid.cancelRow();
	  }	
	  
	  
	  $("#designationGrid").on("click", "#temPID", function(e) 
	  {
		 var button = $(this), enable = button.text() == "Activate";
		 var widget = $("#designationGrid").data("kendoGrid");
		 var dataItem = widget.dataItem($(e.currentTarget).closest("tr"));
			
		 var result=securityCheckForActionsForStatus("./userManagement/designation/statusButton");			
	     if(result=="success")
	     {		 
			 if (enable)
			 {
				$.ajax
				({
					type : "POST",
					url : "./designation/designationStatus/" + dataItem.id + "/activate",
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
						$('#designationGrid').data('kendoGrid').dataSource.read();
					}
				});
			 }
			 else 
			 {
			      $.ajax
				  ({
					   type : "POST",
					   url : "./designation/designationStatus/" + dataItem.id + "/deactivate",
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
							  $('#designationGrid').data('kendoGrid').dataSource.read();
							 }
							});
			 }
	     }
	   });
	  
	  
	//Validator Function
		(function($, kendo) 
		  {
			 $.extend(true,kendo.ui.validator,
			 {
				rules : { 
							desigNameNullValidator : function(input,params) { 
		  					  if (input.attr("name") == "dnName") {
								     return $.trim(input.val()) !== "";
							   }
							 return true;
		  		             }, 
		  		            desigNamevalidation : function(input, params) { 
							 if (input.filter("[name='dnName']").length && input.val()) {
								 return /^[a-zA-Z0-9_()&. ]*$/.test(input.val());
							 }
							 return true;
						    },
						    desigNameMaxvalidation : function(input, params){ 
								 if (input.filter("[name='dnName']").length && input.val()) {
									return /^[\s\S]{1,45}$/.test(input.val());
								 }
								 return true;
							},
							desigDescriptionNullValidator : function(input,params) { 
		    					  if (input.attr("name") == "dnDescription") {
		  						     return $.trim(input.val()) !== "";
		  						  }
		  						  return true;
		    		        },
						    desigMaxLengthDescription : function(input,params) { 
							 if (input.filter("[name='dnDescription']").length&& input.val()) {
							     return /^[\s\S]{1,500}$/.test(input.val());
							 }
							 return true;
						     },
		    		     
						   },
						  messages : 
						  {
							  desigNameNullValidator:"Designation name is required",
							  desigNamevalidation : "Designation name can't allow special symbols except(_ .&)",
							  desigNameMaxvalidation:"Department field allows max 45 Characters",
							  desigDescriptionNullValidator:"Description is required",
							  desigMaxLengthDescription:"Description field allows max 500 characters",
						  }
					 });
			})(jQuery, kendo); 
			
	</script>
	
	