
<%@include file="/common/taglibs.jsp"%>

<c:url value="/department/readDepartmentUrl" var="readDepartmentUrl"/>
<c:url value="/department/createDepartmentUrl" var="createDepartmentUrl"/>
<c:url value="/department/updateDepartmentUrl" var="updateDepartmentUrl"/>
<c:url value="/department/deleteDepartmentUrl" var="deleteDepartmentUrl"/>

<c:url value="/department/filter" var="commonFilterForDepartmentsUrl" />
<c:url value="/department/readDepartmentNamesForUniqueness" var="readDepartmentNames" />

<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i>Manage Department</h1>
			</div>
		</div>
   <kendo:grid name="departmentGrid" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true" edit="departmentGridEvent" remove="removeDepartment">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true" ></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Department" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
			<kendo:grid-column title="Department&nbsp;Name&nbsp;*" field="deptName" width="75px" filterable="true">
			   <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${commonFilterForDepartmentsUrl}/deptName"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Department&nbsp;Description&nbsp;*" field="deptDesc" editor="deptDescriptionEditor" filterable="false" width="80px">
			    <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${commonFilterForDepartmentsUrl}/deptDesc"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
			</kendo:grid-column>
			
		    <kendo:grid-column title="Department&nbsp;Status&nbsp;*" field="departmentStatus" width="75px" filterable="true">
		       <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${commonFilterForDepartmentsUrl}/departmentStatus"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>
		    </kendo:grid-column>
		    
			<kendo:grid-column title=""
				template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.deptId#'>#= data.departmentStatus == 'Active' ? 'De-activate' : 'Activate' #</a>"
				width="30px" />
				
		    <kendo:grid-column title="&nbsp;" width="50px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy" />		
			   </kendo:grid-column-command>
			</kendo:grid-column>						
	   </kendo:grid-columns>
	   
	   <kendo:dataSource pageSize="20" requestEnd="onDepartmentRequestEnd" requestStart="onDepartmentRequestStart">
		 <kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readDepartmentUrl}" dataType="json" type="POST" contentType="application/json" />
			<kendo:dataSource-transport-create url="${createDepartmentUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-update url="${updateDepartmentUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-destroy url="${deleteDepartmentUrl}" dataType="json" type="GET" contentType="application/json" />
		 </kendo:dataSource-transport>

		 <kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="deptId">
			  <kendo:dataSource-schema-model-fields>
			    <kendo:dataSource-schema-model-field name="deptId" type="number"/>
			    <kendo:dataSource-schema-model-field name="deptName" type="string"/>
			    <kendo:dataSource-schema-model-field name="deptDesc" type="string"/>
			    <kendo:dataSource-schema-model-field name="departmentStatus" type="string" defaultValue="Inactive"/>
			  </kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		 </kendo:dataSource-schema>
	   </kendo:dataSource>
	</kendo:grid>	
	</div>
	<div id="alertsBox" title="Alert"></div>
	
	<script>
	var res1 = new Array();
	$("#departmentGrid").on("click", ".k-grid-Clear_Filter", function()
	{
		 $("form.k-filter-menu button[type='reset']").slice().trigger("click");
		 var grid = $("#departmentGrid").data("kendoGrid");
		 grid.dataSource.read();
		 grid.refresh();
	});
	
	
	  
	  function departmentGridEvent(e)
	  {
		 
			
		  $('a[id="temPID"]').remove();		 
		  
		  $('label[for=status]').parent().hide();
		  $('div[data-container-for="status"]').hide(); 
		  
		  $('label[for=departmentStatus]').parent().hide();
		  $('div[data-container-for="departmentStatus"]').hide(); 
		  
		  $(".k-grid-cancel").click(function () {
				 var grid = $("#departmentGrid").data("kendoGrid");
				 grid.dataSource.read();
				 grid.refresh();
		 });
		  
		  if (e.model.isNew()) 
		  {
			  securityCheckForActions("./userManagement/department/createButton");
			  res1 = [];
				 $.ajax
				 ({
				      type : "GET",
					  dataType:"text",
					  url : '${readDepartmentNames}',
					  dataType : "JSON",
					  success : function(response) 
					  {
						 for(var i = 0; i<response.length; i++) 
						 {
						   res1[i] = response[i];	
					     }
					  }
				  });  
			  $(".k-window-title").text("Add Department Details");
			  $(".k-grid-update").text("Save");
		  }
		  else
		  {
			  var gview = $("#departmentGrid").data("kendoGrid");
			  var selectedItem = gview.dataItem(gview.select());
			  var deptName= selectedItem.deptName;
			  res1 = [];
			   $.ajax({
			    type : "GET",
			    dataType : "text",
			    url : '${readDepartmentNames}',
			    dataType : "JSON",
			    success : function(response) {
			     var j = 0;
			     for (var i = 0; i < response.length; i++) {
			      if (response[i] != deptName) {

			       res1[j] = response[i];
			       j++;
			      }
			     }
			    }
			   });  
			  $(".k-window-title").text("Edit Department Details");
			  $(".k-grid-update").text("Update");
			  securityCheckForActions("./userManagement/department/updateButton");
		  }
		  
	  }
	  function deptDescriptionEditor(container,options)
	  {
		  $('<textarea name="deptDesc" data-text-field="deptDesc" data-value-field="deptDesc" data-bind="value:' + options.field + '" style="width: 161px; height: 46px;"/>').appendTo(container);
		  $('<span class="k-invalid-msg" data-for="deptDesc"></span>').appendTo(container); 
	  }
	  function onDepartmentRequestEnd(e)
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
						alert("Error: Creating the Department Details\n\n" + errorInfo);
					}
					if (e.type == "update") 
					{
						alert("Error: Updating the Department Details\n\n" + errorInfo);
					}
					var grid = $("#departmentGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
					return false;
				}			
				if (e.type == "update" && !e.response.Errors) 
				{	
					e.sender.read();
					$("#alertsBox").html("Alert");
					$("#alertsBox").html("Department Updated successfully");
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
					$("#alertsBox").html("Department Created successfully");
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
				if (e.response.status == "AciveDepartmentMasterDestroyError") {
		  			$("#alertsBox").html("");
		  			$("#alertsBox").html("Active Department Cannot be Deleted");
		  			$("#alertsBox").dialog({
		  				modal : true,
		  				buttons : {
		  					"Close" : function() {
		  						$(this).dialog("close");
		  					}
		  				}
		  			});
		  			var grid = $("#departmentGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
		  		return false;
		  	    }
				if (e.response.status == "CHILD") {
		  			$("#alertsBox").html("");
		  			$("#alertsBox").html("Can't delete Department, child record found");
		  			$("#alertsBox").dialog({
		  				modal : true,
		  				buttons : {
		  					"Close" : function() {
		  						$(this).dialog("close");
		  					}
		  				}
		  			});
		  			var grid = $("#departmentGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
		  		return false;
		  	    }
				if(e.type == "destroy" && !e.response.Errors)
				{
					$("#alertsBox").html("Alert");
					$("#alertsBox").html("Department Deleted successfully");
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
					var grid = $("#departmentGrid").data("kendoGrid");
					grid.dataSource.read();
					grid.refresh();
				} 
			 } 
	  }
	  function onDepartmentRequestStart(e)
	  {
		  var grid= $("#departmentGrid").data("kendoGrid");
		  grid.cancelRow();
	  }	
	  
	  function removeDepartment(){
			var conf = confirm("Are you sure want to delete this Department?");
			    if(!conf){
			    $('#departmentGrid').data().kendoGrid.dataSource.read();
			    throw new Error('deletion aborted');
			     } 
		}
	  
	  $("#departmentGrid").on("click", "#temPID", function(e) 
	  {
		 var button = $(this), enable = button.text() == "Activate";
		 var widget = $("#departmentGrid").data("kendoGrid");
		 var dataItem = widget.dataItem($(e.currentTarget).closest("tr"));
		
		 var result=securityCheckForActionsForStatus("./userManagement/department/statusButton");			
	     if(result=="success")
	     {
			 if (enable)
			 {
				$.ajax
				({
					type : "POST",
					url : "./department/DepartmentStatus/" + dataItem.id + "/activate",
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
						$('#departmentGrid').data('kendoGrid').dataSource.read();
					}
				});
			 }
			 else 
			 {
			      $.ajax
				  ({
					   type : "POST",
					   url : "./department/DepartmentStatus/" + dataItem.id + "/deactivate",
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
							  $('#departmentGrid').data('kendoGrid').dataSource.read();
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
						
						deptNameNullValidator : function(input,params) { 
	  					  if (input.attr("name") == "deptName") {
							     return $.trim(input.val()) !== "";
							  }
							  return true;
	  		           	},  
						departmentNamevalidation : function(input, params) { 
							 if (input.filter("[name='deptName']").length && input.val()) {
								return /^[a-zA-Z0-9_()&. ]*$/.test(input.val());
							 }
							 return true;
						  },
						  departmentNameMaxvalidation : function(input, params){ 
							 if (input.filter("[name='deptName']").length && input.val()) {
								return /^[\s\S]{1,45}$/.test(input.val());
							 }
							 return true;
						  }, 
						  deptDescRequiredValidation : function(input,params) { 
	    					  if (input.attr("name") == "deptDesc") {
	  						     return $.trim(input.val()) !== "";
	  						  }
	  						  return true;
	    		           },
	    		           deptDescLengthValidation : function(input, params) {
								if (input.filter("[name='deptDesc']").length && input.val() != "") {
									return /^[\s\S]{1,500}$/.test(input.val());
								}
								return true;
							},
							departmentNameUniqueness : function(input,params) {
						        if (input.filter("[name='deptName']").length && input.val()){
						          enterdService = input.val().toUpperCase(); 
						          for(var i = 0; i<res1.length; i++) {
						            if ((enterdService == res1[i].toUpperCase()) && (enterdService.length == res1[i].length) ) {								            
						              return false;								          
						            }
						          }
						         }
						         return true;
						    },
						  
						  },
						  messages : 
						  {
							  departmentNamevalidation : "Department name can't allow special symbols except(_ .&)",
							  deptNameNullValidator:"Department Name Required",						
							  departmentNameUniqueness:"Department Name already exists, please try with some other name ",	
							  deptDescRequiredValidation:"Description is required",
							  deptDescLengthValidation:"Description field allows max 500 characters",
							  departmentNameMaxvalidation:"Department field allows max 45 Characters",
						  }
					 });
			})(jQuery, kendo);
	</script>
	
	