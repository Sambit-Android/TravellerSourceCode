
<%@include file="/common/taglibs.jsp"%>

<c:url value="/roles/readRoleUrl" var="readRoleUrl"/>
<c:url value="/roles/createRoleUrl" var="createRoleUrl"/>
<c:url value="/roles/updateRoleUrl" var="updateRoleUrl"/>
<c:url value="/roles/destroyRoleUrl" var="destroyRoleUrl"/>

<c:url value="/roles/readRoleNamesForUniqueness" var="readRoleNames"/>
<c:url value="/roles/getDesignationNamesUrl" var="getDesignationNamesUrl"/>

<c:url value="/roles/getRoleNameFilter" var="getRoleNameFilter"/>
<c:url value="/roles/getRoleDescriptionFilter" var="getRoleDescriptionFilter"/>
<c:url value="/roles/getRoleStatusFilter" var="getRoleStatusFilter"/>

<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Manage Roles</h1>
			</div>
		</div>
   <kendo:grid name="roleGrid" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true" edit="roleGridEvent" remove="removeRoles">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true" ></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this Role?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Role" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
	   
	   		<kendo:grid-column title="Designation&nbsp;*" field="dnId" editor="designationEditor" hidden="true" width="100px">
			</kendo:grid-column>
	   
	   		<kendo:grid-column title="Designation&nbsp;*" field="dnName" width="60px">
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
	   
			<kendo:grid-column title="Role Name&nbsp;*" field="rlName" width="75px" filterable="true">
				<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getRoleNameFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
	    	</kendo:grid-column>
			
			<kendo:grid-column title="Role Description&nbsp;*" field="rlDescription" editor="roleDescriptionEditor" filterable="false" width="80px">
			   <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getRoleDescriptionFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
			</kendo:grid-column>
		    <kendo:grid-column title="Role Status&nbsp;*" field="roleStatus" width="60px" filterable="true">
		       <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getRoleStatusFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
		    </kendo:grid-column> 
		    
			<kendo:grid-column title="" template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.rlId#'>#= data.roleStatus == 'Active' ? 'De-activate' : 'Activate' #</a>" width="40px" />
				
		    <kendo:grid-column title="&nbsp;" width="120px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>
				  <kendo:grid-column-commandItem name="destroy" />			
			   </kendo:grid-column-command>
			</kendo:grid-column>						
	   </kendo:grid-columns>
	   
	   <kendo:dataSource pageSize="20" requestEnd="onRoleRequestEnd" requestStart="onRoleRequestStart">
		 <kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readRoleUrl}" dataType="json" type="POST" contentType="application/json" />
			<kendo:dataSource-transport-create url="${createRoleUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-update url="${updateRoleUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-destroy url="${destroyRoleUrl}" dataType="json" type="GET" contentType="application/json" />
		 </kendo:dataSource-transport>

		 <kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="rlId">
			  <kendo:dataSource-schema-model-fields>
			    <kendo:dataSource-schema-model-field name="rlId" type="number"/>
			    <kendo:dataSource-schema-model-field name="rlName" type="string"/>
			    <kendo:dataSource-schema-model-field name="dnName" type="string"/>
			    <kendo:dataSource-schema-model-field name="dnId" type="number"/>
			    <kendo:dataSource-schema-model-field name="rlDescription" type="string"/>
			    <kendo:dataSource-schema-model-field name="roleStatus" type="string" defaultValue="Inactive"/>
			  </kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		 </kendo:dataSource-schema>
	   </kendo:dataSource>
	</kendo:grid>	
	</div>
	<div id="alertsBox" title="Alert"></div>
	
	<script>
	
	
	/********************************************************************************/
	 $("#roleGrid").on("click", "#temPID", function(e) 
	 {
	 	 var button = $(this), enable = button.text() == "Activate";
		 var widget = $("#roleGrid").data("kendoGrid");
		 var dataItem = widget.dataItem($(e.currentTarget).closest("tr"));
		 
		 var result=securityCheckForActionsForStatus("./userManagement/roles/statusButton");			
	     if(result=="success")
	     { 
			 if (enable)
			 {
				$.ajax
				({
					type : "POST",
					url : "./role/RoleStatus/" + dataItem.id + "/Activate",
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
						$('#roleGrid').data('kendoGrid').dataSource.read();
				     }
				 });
			  }
			  else 
			  {
				  $.ajax
				  ({
					   type : "POST",
					   url : "./role/RoleStatus/" + dataItem.id + "/Deactivate",
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
						   $('#roleGrid').data('kendoGrid').dataSource.read();
					   }
				 });
			  }	
	      }
		});
	/**********************************************************************************/
	var dnId = "";
	function designationEditor(container, options) 
		{
		  $('<input name = "designation" data-text-field="dnName" id="dnId" required="true" validationMessage="Designation is required" data-value-field="dnId" data-bind="value:' + options.field + '"  />')
					.appendTo(container).kendoDropDownList({
						optionLabel : "Select",
						dataSource : {
							transport : {
								read: "${getDesignationNamesUrl}"
							}
						},
						change : function (e) {
							dnId = this.value();
					    }
					});
			$('<span class="k-invalid-msg" data-for="designation"></span>').appendTo(container);
		}
	
	var res1 = new Array();
	function roleGridEvent(e)
	{
		 $(".k-window-title").text("Add Role");
		 $(".k-grid-update").text("Save");
		
		 $('a[id="temPID"]').remove();	
		 
		 $('label[for=status]').parent().hide();
		 $('div[data-container-for="status"]').hide();
		 
		 $('label[for="dnName"]').parent().hide();
		 $('div[data-container-for="dnName"]').hide();
		 
		 $('label[for=roleStatus]').parent().hide();
		 $('div[data-container-for="roleStatus"]').hide();
		 
		 $(".k-grid-cancel").click(function () {
			 var grid = $("#roleGrid").data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	     });
		 
		 if (e.model.isNew()) 
	     {
			 securityCheckForActions("./userManagement/roles/createButton");
			 $(".k-window-title").text("Add Role Details");
			 $(".k-grid-update").text("Save");
			 res1 = [];
			 $.ajax
			 ({
			      type : "GET",
				  dataType:"text",
				  url : '${readRoleNames}/'+dnId,
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
			  var gview = $("#roleGrid").data("kendoGrid");
			  var selectedItem = gview.dataItem(gview.select());
			  var rlName= selectedItem.rlName;
			  res1 = [];
			   $.ajax({
			    type : "GET",
			    dataType : "text",
			    url : '${readRoleNames}'+dnId,
			    dataType : "JSON",
			    success : function(response) {
			     var j = 0;
			     for (var i = 0; i < response.length; i++) {
			      if (response[i] != rlName) {

			       res1[j] = response[i];
			       j++;
			      }
			     }
			    }
			   });
			securityCheckForActions("./userManagement/roles/updateButton");
			$(".k-window-title").text("Edit Role Details");
		    $(".k-grid-update").text("Update");
		 }
	 }

	 
	function removeRoles(){
		var conf = confirm("Are you sure want to delete this Role?");
		    if(!conf){
		    $('#roleGrid').data().kendoGrid.dataSource.read();
		    throw new Error('deletion aborted');
		     } 
	}
	
	 function roleDescriptionEditor(container, options) 
	 {
		 $('<textarea name="rlDescription" data-text-field="rlDescription" data-value-field="rlDescription" data-bind="value:' + options.field + '" style="width: 161px; height: 46px;"/>').appendTo(container);
	     $('<span class="k-invalid-msg" data-for="rlDescription"></span>').appendTo(container);
	 }
	 function onRoleRequestEnd(e)
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
				var grid = $("#roleGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}			
			if (e.type == "update" && !e.response.Errors) 
			{	
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Role Updated successfully");
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
				$("#alertsBox").html("Role Created successfully");
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
				var grid = $("#roleGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			}
			if (e.response.status == "AciveRoleDestroyError") {
	  			$("#alertsBox").html("");
	  			$("#alertsBox").html("Active Role Cannot be Deleted");
	  			$("#alertsBox").dialog({
	  				modal : true,
	  				buttons : {
	  					"Close" : function() {
	  						$(this).dialog("close");
	  					}
	  				}
	  			});
	  			var grid = $("#roleGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
	  		return false;
	  	    }
			if(e.type == "destroy" && !e.response.Errors)
			{
				
				
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Role Deleted successfully");
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
				var grid = $("#roleGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			} 
		 }
	 }
	 function onRoleRequestStart(e)
	 {
	     var grid= $("#roleGrid").data("kendoGrid");
	     grid.cancelRow();
	 }
	 
	 $("#roleGrid").on("click", ".k-grid-Clear_Filter", function()
	 {
		 $("form.k-filter-menu button[type='reset']").slice().trigger("click");
		 var grid = $("#roleGrid").data("kendoGrid");
		 grid.dataSource.read();
		 grid.refresh();
	 });
	 
	//Validator Function
	 (function($, kendo) 
	  {
		 $.extend(true,kendo.ui.validator,
		 {
			rules : { 
		  		      
					 roleNameNullValidator : function(input,params) { 
					  if (input.attr("name") == "rlName") {
					     return $.trim(input.val()) !== "";
					  }
					  return true;
		             }, 
					 roleNamevalidation : function(input, params) { 
					   if (input.filter("[name='rlName']").length && input.val()) {
						   return /^[a-zA-Z0-9_()&. ]*$/.test(input.val());
					   }
						 return true;
					  },
					  roleNameMaxvalidation : function(input, params){ 
						 if (input.filter("[name='rlName']").length && input.val()) {
							return /^[\s\S]{1,45}$/.test(input.val());
						 }
						 return true;
					  },
					  rlDescriptionNullValidator : function(input,params) { 
    					  if (input.attr("name") == "rlDescription") {
  						     return $.trim(input.val()) !== "";
  						  }
  						  return true;
    		           },
					  RoleDescriptionMaxLength : function(input,params) { 
						 if (input.filter("[name='rlDescription']").length&& input.val()) {
						     return /^[\s\S]{1,500}$/.test(input.val());
						 }
						 return true;
					  },
    				  roleNameUniqueness : function(input,params)  {
					        if (input.filter("[name='rlName']").length && input.val()) {
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
					  },
					  messages : 
					  {
						  roleNameNullValidator:"Role Name Required",						
						  roleNamevalidation : "Role name  can't allow special symbols except(_ .&)",
						  roleNameMaxvalidation:"Role Name allows max 45 Characters",
						  rlDescriptionNullValidator:"Role description required",
						  RoleDescriptionMaxLength:"Role Description allows max 500 characters",
						  roleNameUniqueness:"Role Name already exists, please try with some other name ",
					  }
				 });
		})(jQuery, kendo);
	 
	</script>