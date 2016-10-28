<%@include file="/common/taglibs.jsp"%>

<c:url value="/helpDesk/readCategoryUrl" var="readCategoryUrl"/>
<c:url value="/helpDesk/createCategoryUrl" var="createCategoryUrl"/>
<c:url value="/helpDesk/updateCategoryUrl" var="updateCategoryUrl"/>
<c:url value="/helpDesk/destroyCategoryUrl" var="destroyCategoryUrl"/>

<c:url value="/helpDesk/readCategoryNamesForUniqueness" var="readCategoryNames"/>

<c:url value="/category/filter" var="commonFilterForCategoryUrl"/>
<c:url value="/helpDesk/categoryStatusFilter" var="categoryStatusFilter"/>

<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Manage Categories</h1>
			</div>
		</div>
   <kendo:grid name="categoryGrid" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true" edit="categoryGridEvent" remove="removeCategories">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true" ></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string contains="Contains" eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this Category?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Category" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
	   
	   		<kendo:grid-column title="Category&nbsp;Id" field="categoryId" hidden="true" width="100px">
			</kendo:grid-column>
	   
	   		<kendo:grid-column title="Category&nbsp;Name&nbsp;*" field="categoryName" width="60px" filterable="true">
	   		<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForCategoryUrl}/categoryName"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   	</kendo:grid-column-filterable>
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
										read :  "${categoryStatusFilter}"
									}
								} 
							});
					   	}
						</script>
					</kendo:grid-column-filterable-ui>	
			</kendo:grid-column-filterable>
	    	</kendo:grid-column>
		    
			<kendo:grid-column title="" template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.categoryId#'>#= data.status == 1 ? 'De-activate' : 'Activate' #</a>" width="40px" />
				
		    <kendo:grid-column title="&nbsp;" width="120px">
			   <kendo:grid-column-command>
				  <%-- <kendo:grid-column-commandItem name="edit"/> --%>
				  <kendo:grid-column-commandItem name="destroy" />			
			   </kendo:grid-column-command>
			</kendo:grid-column>						
	   </kendo:grid-columns>
	   
	   <kendo:dataSource pageSize="20" requestEnd="onCategoryRequestEnd" requestStart="onCategoryRequestStart">
		 <kendo:dataSource-transport>
			<kendo:dataSource-transport-read url="${readCategoryUrl}" dataType="json" type="POST" contentType="application/json" />
			<kendo:dataSource-transport-create url="${createCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-update url="${updateCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
			<kendo:dataSource-transport-destroy url="${destroyCategoryUrl}" dataType="json" type="GET" contentType="application/json" />
		 </kendo:dataSource-transport>

		 <kendo:dataSource-schema>
			<kendo:dataSource-schema-model id="categoryId">
			  <kendo:dataSource-schema-model-fields>
			    <kendo:dataSource-schema-model-field name="categoryId" type="number"/>
			    <kendo:dataSource-schema-model-field name="categoryName" type="string"/>
			  </kendo:dataSource-schema-model-fields>
			</kendo:dataSource-schema-model>
		 </kendo:dataSource-schema>
	   </kendo:dataSource>
	</kendo:grid>	
	</div>
	<div id="alertsBox" title="Alert"></div>
	
	<script>
	
	
	/********************************************************************************/
	 $("#categoryGrid").on("click", "#temPID", function(e) 
	 {
	 	 var button = $(this), enable = button.text() == "Activate";
		 var widget = $("#categoryGrid").data("kendoGrid");
		 var dataItem = widget.dataItem($(e.currentTarget).closest("tr"));
		 
		 /* var result=securityCheckForActionsForStatus("./userManagement/roles/statusButton");			
	     if(result=="success")
	     {  */
			 if (enable)
			 {
				$.ajax
				({
					type : "POST",
					url : "./helpDesk/categoryStatus/" + dataItem.id + "/activate",
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
						$('#categoryGrid').data('kendoGrid').dataSource.read();
				     }
				 });
			  }
			  else 
			  {
				  $.ajax
				  ({
					   type : "POST",
					   url : "./helpDesk/categoryStatus/" + dataItem.id + "/deactivate",
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
						   $('#categoryGrid').data('kendoGrid').dataSource.read();
					   }
				 });
			  }	
	      /* } */
		});
	/**********************************************************************************/
	
	var res1 = new Array();
	function categoryGridEvent(e)
	{		
		 $('a[id="temPID"]').remove();	
		 
		 $('label[for="categoryId"]').parent().hide();
		 $('div[data-container-for="categoryId"]').hide();
		 
		 $('label[for="status"]').parent().hide();
		 $('div[data-container-for="status"]').hide();
		 
		 $(".k-grid-cancel").click(function () {
			 var grid = $("#categoryGrid").data("kendoGrid");
			 grid.dataSource.read();
			 grid.refresh();
	     });
		 
		 if (e.model.isNew()) 
	     {
			// securityCheckForActions("./userManagement/roles/createButton");
			 $(".k-window-title").text("Add Category Details");
			 $(".k-grid-update").text("Save");
			 res1 = [];
			 $.ajax
			 ({
			      type : "GET",
				  dataType:"text",
				  url : '${readCategoryNames}',
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
			  var gview = $("#categoryGrid").data("kendoGrid");
			  var selectedItem = gview.dataItem(gview.select());
			  var rlName= selectedItem.rlName;
			  res1 = [];
			   $.ajax({
			    type : "GET",
			    dataType : "text",
			    url : '${readCategoryNames}',
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
			//securityCheckForActions("./userManagement/roles/updateButton");
			$(".k-window-title").text("Edit Category Details");
		    $(".k-grid-update").text("Update");
		 }
	 }

	 
	function removeCategories(){
		var conf = confirm("Are you sure want to delete this Category?");
		    if(!conf){
		    $('#categoryGrid').data().kendoGrid.dataSource.read();
		    throw new Error('deletion aborted');
		     } 
	}
	
	 function onCategoryRequestEnd(e)
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
				var grid = $("#categoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}			
			if (e.type == "update" && !e.response.Errors) 
			{	
				e.sender.read();
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Category updated successfully");
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
				$("#alertsBox").html("Category created successfully");
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
			if (e.response.status == "AciveCategoryDestroyError") {
	  			$("#alertsBox").html("");
	  			$("#alertsBox").html("Active category cannot be deleted");
	  			$("#alertsBox").dialog({
	  				modal : true,
	  				buttons : {
	  					"Close" : function() {
	  						$(this).dialog("close");
	  					}
	  				}
	  			});
	  			var grid = $("#categoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
	  		return false;
	  	    }
			if(e.type == "destroy" && !e.response.Errors)
			{
				
				
				$("#alertsBox").html("Alert");
				$("#alertsBox").html("Category deleted successfully");
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
				var grid = $("#categoryGrid").data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			} 
		 }
	 }
	 function onCategoryRequestStart(e)
	 {
	     var grid= $("#categoryGrid").data("kendoGrid");
	     grid.cancelRow();
	 }
	 
	 $("#categoryGrid").on("click", ".k-grid-Clear_Filter", function()
	 {
		 $("form.k-filter-menu button[type='reset']").slice().trigger("click");
		 var grid = $("#categoryGrid").data("kendoGrid");
		 grid.dataSource.read();
		 grid.refresh();
	 });
	 
	//Validator Function
	 (function($, kendo) 
	  {
		 $.extend(true,kendo.ui.validator,
		 {
			rules : { 
		  		      
					 categoryNameNullValidator : function(input,params) { 
					  if (input.attr("name") == "categoryName") {
					     return $.trim(input.val()) !== "";
					  }
					  return true;
		             }, 
					 categoryNamevalidation : function(input, params) { 
					   if (input.filter("[name='categoryName']").length && input.val()) {
						   return /^[\s\S]{1,100}$/.test(input.val());
					   }
						 return true;
					  },
    				  categoryNameUniqueness : function(input,params)  {
					        if (input.filter("[name='categoryName']").length && input.val()) {
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
						  categoryNameNullValidator : "Category name required",						
						  categoryNamevalidation : "Category name allows max 100 characters",
						  categoryNameUniqueness : "Category name already exist",
					  }
				 });
		})(jQuery, kendo);
	 
	</script>