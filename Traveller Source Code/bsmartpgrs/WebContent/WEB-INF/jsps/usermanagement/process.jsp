
<%@include file="/common/taglibs.jsp"%>

<c:url value="/process/read" var="readProcessUrl"/>
<c:url value="/process/save" var="saveProcessUrl"/>
<c:url value="/process/update" var="updateProcessUrl"/>
<c:url value="/process/delete" var="deleteProcessUrl" />

<c:url value="/process/escalation/read" var="readEscalationUrl" />
<c:url value="/process/escalation/save" var="saveEscalationUrl" />
<c:url value="/process/escalation/update" var="updateEscalationUrl" />
<c:url value="/process/escalation/delete" var="deleteEscalationUrl"/>

<c:url value="/process/escalation/officetype" var="getOfficeType" />
<c:url value="/process/escalation/designation" var="getDesignation" />

<c:url value="/process/invoiceProcess/filter" var="commonFilterForInvoiceProcessUrl" />
<c:url value="/process/invoiceProcessStatusFilter" var="invoiceProcessStatusFilterUrl"/>

<div id="content">
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark"><i class="fa-fw fa fa-home"></i> Invoice Process</h1>
	</div>
</div>	
 <kendo:grid name="grid" pageable="true" change="onChangeProcess" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" detailTemplate="escalationTemplate" edit="processEvent" dataBound="processDB">				
      <kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10" input="true" numeric="true"></kendo:grid-pageable>
	  <kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators><kendo:grid-filterable-operators-string eq="Is equal to"/></kendo:grid-filterable-operators>
	  </kendo:grid-filterable>		
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add Process" />
		   <kendo:grid-toolbarItem template="<a class='k-button' href='\\#' onclick=clearFilterprocess()>Clear Filter</a>"/>
	   </kendo:grid-toolbar>
	   <kendo:grid-columns>
			<kendo:grid-column title="Process&nbsp;Name&nbsp;*" field="processName" width="140px" filterable="true">
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter Process Name",
											dataSource : {
												transport : {
													read : "${commonFilterForInvoiceProcessUrl}/processName"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		</kendo:grid-column-filterable>
			</kendo:grid-column>
			<kendo:grid-column title="Process Level" field="processLevel" width="140px" filterable="true">
			</kendo:grid-column>
			<kendo:grid-column title="Process&nbsp;Duration&nbsp;*" field="processDuration" format="{0:n0}" filterable="true" width="140px"/>
			<kendo:grid-column title="Status" field="status" width="100px" filterable="true" template="#: status == 1 ? 'Active' : 'Inactive'#">
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
						          read :  "${invoiceProcessStatusFilterUrl}"
						         }
						        } 
						       });
						         }
						</script>
					</kendo:grid-column-filterable-ui>
				</kendo:grid-column-filterable>
			</kendo:grid-column>	
			<kendo:grid-column title="" template="<a href='\\\#' id='temPID' onclick='changeProcStatus(this.text)' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.id#'>#= data.status == 1 ? 'De-activate' : 'Activate' #</a>"	width="100px" />
		    <kendo:grid-column title="&nbsp;" width="220px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy"/>			
			   </kendo:grid-column-command>
			</kendo:grid-column>						
		</kendo:grid-columns>
	   	<kendo:dataSource pageSize="20" requestEnd="onProcessRequestEnd" requestStart="onProcessRequestStart">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-read url="${readProcessUrl}" dataType="json" type="POST" contentType="application/json" />
				<kendo:dataSource-transport-create url="${saveProcessUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-update url="${updateProcessUrl}" dataType="json" type="GET" contentType="application/json" />
			  	<kendo:dataSource-transport-destroy url="${deleteProcessUrl}" dataType="json" type="GET" contentType="application/json" />
		 	</kendo:dataSource-transport>
		 	<kendo:dataSource-schema parse="parseProcessNames">
				<kendo:dataSource-schema-model id="id">
				 	 <kendo:dataSource-schema-model-fields>
				     	<kendo:dataSource-schema-model-field name="id" type="number"/>
				    	<kendo:dataSource-schema-model-field name="processName" type="string"></kendo:dataSource-schema-model-field>
				    	<kendo:dataSource-schema-model-field name="processLevel" type="number"/>
				    	<kendo:dataSource-schema-model-field name="processDuration" type="number"><kendo:dataSource-schema-model-field-validation min="1" max="100"/></kendo:dataSource-schema-model-field>
				    	<kendo:dataSource-schema-model-field name="createdDate" type="date"/>
				    	<kendo:dataSource-schema-model-field name="lastUpdatedDate" type="date"/>
				    	<kendo:dataSource-schema-model-field name="status" type="string"/>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
		 	</kendo:dataSource-schema>
	   </kendo:dataSource>
</kendo:grid>
<kendo:grid-detailTemplate id="escalationTemplate">
    <kendo:tabStrip name="tabStrip_#=id#">
        <kendo:tabStrip-items>
            <kendo:tabStrip-item text="Escalation" selected="true">
                <kendo:tabStrip-item-content>
                    <kendo:grid name="grid_#=id#" pageable="true" sortable="true" scrollable="false" edit="escalationEvent" selectable="true" change="onChangeEscalation" dataBound="escalationDB">
                        <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
                        <kendo:grid-toolbar>
		  					<kendo:grid-toolbarItem name="create" text="Add Escalation" />
	  					 </kendo:grid-toolbar>
                        <kendo:grid-columns>
                            <kendo:grid-column title="Level" field="level" width="56px" />
                            <kendo:grid-column title="Office&nbsp;Type&nbsp;*" field="officeTypeId" width="56px" editor="officeTypeEditor" hidden="true"/>
                            <kendo:grid-column title="Office&nbsp;Type&nbsp;*" field="officeTypeName" width="56px" />
                            <kendo:grid-column title="Designation&nbsp;*" field="designationId" width="56px" editor="designationEditor" hidden="true"/>
                            <kendo:grid-column title="Designation&nbsp;*" field="designationName" width="56px" />
                            <kendo:grid-column title="SLA&nbsp;*" field="sla" width="56px" format="{0:n0}" />
                           	<kendo:grid-column title="Status" field="statusText" hidden="true" width="110px"/>
                      		<kendo:grid-column title="&nbsp;" width="220px">
			   					<kendo:grid-column-command>
				  					<kendo:grid-column-commandItem name="edit"/>	
				  					<kendo:grid-column-commandItem name="destroy"/>	
				  					<%-- <kendo:grid-column-commandItem name="Activate" click="activateEscalation"/>	
				  					<kendo:grid-column-commandItem name="Deactivate" click="deactivateEscalation"/>	 --%>
				  					<kendo:grid-column-commandItem name="Notification"  click="escalationNotification" />	
				  							
			   					</kendo:grid-column-command>
							</kendo:grid-column>	
                        </kendo:grid-columns>
                        <kendo:dataSource pageSize="5" requestEnd="onEscalationRequestEnd" requestStart="onEscalationRequestStart">
                            <kendo:dataSource-schema>
                            	<kendo:dataSource-schema-model id="id">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="id" type="number"/>
						    			<kendo:dataSource-schema-model-field name="processId" type="number" defaultValue="#=id#"/>
						    			<kendo:dataSource-schema-model-field name="level" type="number"/>
						   		 		<kendo:dataSource-schema-model-field name="officeTypeId" type="number"/>
										<kendo:dataSource-schema-model-field name="designationId" type="number"/>
										<kendo:dataSource-schema-model-field name="sla" type="number"><kendo:dataSource-schema-model-field-validation min="1" max="100"/></kendo:dataSource-schema-model-field>
										<kendo:dataSource-schema-model-field name="status" type="number"/>
										<kendo:dataSource-schema-model-field name="createdDate" type="date"/>
				    					<kendo:dataSource-schema-model-field name="lastUpdatedDate" type="date"/>
									</kendo:dataSource-schema-model-fields>
								</kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                               <kendo:dataSource-transport-read url="${readEscalationUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
                               <kendo:dataSource-transport-create url="${saveEscalationUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
                               <kendo:dataSource-transport-update url="${updateEscalationUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
                               <kendo:dataSource-transport-destroy url="${deleteEscalationUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
							</kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
        </kendo:tabStrip-items>
    </kendo:tabStrip>
</kendo:grid-detailTemplate>

</div><div id="alertsBox" title="Alert"></div>
<div id="popNot" title="Notify Client Officers" ></div>
<script>

var processNameArray = [];

function parseProcessNames(response) {   
    var data = response; 
    processNameArray = [];
	 for (var idx = 0, len = data.length; idx < len; idx ++) {
		var res1 = (data[idx].processName);
		processNameArray.push(res1);
	 }  
	 return response;
} 
	
	var id = "";
	var procStatus = "";
	function onChangeProcess(arg){
		 var gview = $("#grid").data("kendoGrid");
		 var selectedItem = gview.dataItem(gview.select());
		 id = selectedItem.id;
		 procStatus = selectedItem.status;
		 this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}
	
	var eId = "";
	var officeTypeName = "";
	var designationName = "";
	function onChangeEscalation(arg){
		 var gview = $("#grid_"+id).data("kendoGrid");
		 var selectedItem = gview.dataItem(gview.select());
		 eId = selectedItem.id;
		 officeTypeName = selectedItem.officeTypeName;
		 designationName = selectedItem.designationName;
		 this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}

	function clearFilterprocess(){
	    $("form.k-filter-menu button[type='reset']").slice().trigger("click");
	    var gridStoreIssue = $("#grid").data("kendoGrid");
	    gridStoreIssue.dataSource.read();
	    gridStoreIssue.refresh();
	}
	
	var flagProcessName = "";
	function processEvent(e){
		$('div[data-container-for="processLevel"]').remove();
		$('label[for="processLevel"]').closest('.k-edit-label').remove();
		$('label[for="undefined"]').closest('.k-edit-label').remove();
		$('div[data-container-for="status"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		$('a[id=temPID]').remove();
		if (e.model.isNew()) {
			flagProcessName = true;
			/* securityCheckForActions("./invoice/pm/create"); */
			$(".k-window-title").text("Add New Process");
			$(".k-grid-update").text("Save");
		}else{
			flagProcessName = false;
			/* securityCheckForActions("./invoice/pm/update"); */
			$(".k-window-title").text("Edit "+e.model.get("name")+" Details");
		}
	}
	
	function changeProcStatus(val){
		$.ajax({
			type : "POST",
			url : "./process/status/"+id+"/"+val,
			dataType:"text",
			success :function(response){
				alert(response);
				$('#grid').data('kendoGrid').dataSource.read();
			}
		});
	}
	
	
	function escalationEvent(e){
		$('div[data-container-for="level"]').hide();
		$('label[for="level"]').closest('.k-edit-label').hide();
		$('div[data-container-for="designationName"]').hide();
		$('label[for="designationName"]').closest('.k-edit-label').hide();
		$('div[data-container-for="officeTypeName"]').hide();
		$('label[for="officeTypeName"]').closest('.k-edit-label').hide();
		$('div[data-container-for="statusText"]').hide();
		$('label[for="statusText"]').closest('.k-edit-label').hide();
		
		if (e.model.isNew()) {
			$(".k-window-title").text("Add New Escalation");
			$(".k-grid-update").text("Save");
		}else{
			$(".k-window-title").text("Edit "+e.model.get("name")+" Details");
		}
	}
	
	function processDB(e){

		var data = this.dataSource.view();
		var grid = $("#grid").data("kendoGrid");
		for (var i = 0; i < data.length; i++) {
			var currentUid = data[i].uid;
			row = this.tbody.children("tr[data-uid='" + data[i].uid + "']");
			var status = data[i].status;
	
			var currenRow = grid.table.find("tr[data-uid='" + currentUid+ "']");
			if(status == 1){
				$(currenRow).find(".k-grid-delete").hide();
				$(currenRow).find(".k-grid-edit").hide();
				row.addClass("bgGreenColor");
			}
		}	
	}
	
	function escalationDB(e){
		
		if (procStatus == 0) {
			$(".k-grid-add", "#grid_" + id).hide();
		}
		var data = this.dataSource.view();
		var grid = $("#grid_"+id).data("kendoGrid");
		for (var i = 0; i < data.length; i++) {
			var currentUid = data[i].uid;
			row = this.tbody.children("tr[data-uid='" + data[i].uid + "']");
			var status = data[i].statusText;
		}
	}

	function escalationProcess(e){
		$('div[data-container-for="escId"]').hide();
		$('label[for="escId"]').closest('.k-edit-label').hide();

		var currenRow = grid.table.find("tr[data-uid='" + currentUid+ "']");
		if(status == 'Active'){
				$(currenRow).find(".k-grid-delete").hide();
				$(currenRow).find(".k-grid-edit").hide();
				$(currenRow).find(".k-grid-Activate").hide();
				row.addClass("bgGreenColor");
		}else{
				$(currenRow).find(".k-grid-Deactivate").hide();
				$(currenRow).find(".k-grid-Notification").hide();
		}
	}	

	
	function activateEscalation(e){
		$.ajax({			
			type : "POST",
			url : "./process/escalation/status/"+eId+"/1",
			dataType:"text",
			success : function(response){
				alert(response);
				$('#grid_'+id).data('kendoGrid').dataSource.read();
			}
		});
	}
	
	function deactivateEscalation(e){
		$.ajax({			
			type : "POST",
			url : "./process/escalation/status/"+eId+"/0",
			dataType:"text",
			success : function(response){
				alert(response);
				$('#grid_'+id).data('kendoGrid').dataSource.read();
			}
		});
	}
	
	function escalationNotification(e){
		$.ajax({			
			type : "POST",
			url : "./process/escalation/designationmaster",
			dataType:"json",
			success : function(response){
				var htmlTable = "";
				htmlTable="<table class='table table-bordered' style='width:100%'><tr style='background:#fe761b;color:white'><th><input type='checkbox' id=master onclick='masterCheckClick()'/></th><th>Designation</th></tr>";
				for ( var s = 0, len = response.length; s < len; ++s) {
	              	var obj = response[s];
	              	htmlTable += "<tr><td style='width:10%'><input class='notidesig' id='notidesig"+obj.designationId+"' type='checkbox' value='"+obj.designationId+"'></td><td style='width:80%'>"+obj.designationName+"</td></tr>";
				}
				htmlTable+="</table>";
				htmlTable+="<div id='buttonArea'><button style='float:right' id='submitBtn' class='k-button' onclick='submitNotifications()'>Submit</button>&nbsp;&nbsp;</div>";
				$("#popNot").html(" ");
				$("#popNot").append(htmlTable);
			 	$("#popNot").dialog({
					modal : true,
					draggable: false,
					resizable: false
			    }); 
			 	
			 	$.ajax({			
					type : "GET",
					url : "./process/escalation/getescalationnotification/"+eId,
					dataType:"json",
					success : function(response){
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj2 = response[s];
							$('input[id="notidesig'+obj2.dmId+'"]').prop("checked",true);
						}
					}
			 	});

		
			}
		});
	}
	
	function masterCheckClick(){
		if($("input[id=master]").prop('checked') == true){
			$('input[class^=notidesig]').prop('checked',true);
		}else{
			$('input[class^=notidesig]').prop('checked',false);
			
		}
		
	}
	
	function submitNotifications(){
	
		var str = "";
		var checked = $(".notidesig:checked");
		var checkedValues = checked.map(function(i) {
					return $(this).val();
		}).get();
		if (checked.length) {
			$('#submitBtn').hide();
			$('#buttonArea').append('<span style="float:right"><img src="./resources/img/preloader.GIF" alt="please wait" style="verticle-align:middle"/>&nbsp;&nbsp;Keep Calm..&nbsp;&nbsp;&nbsp;&nbsp;</span>');
			str = checkedValues.join();
			$.ajax({			
				type : "GET",
				url : "./process/escalation/notification/"+eId+"?str="+str,
				dataType:"text",
				success : function(response){
					alert(response);
					$('#popNot').dialog('close');
					$('#grid_'+id).data('kendoGrid').dataSource.read();
				}
			});
		}else{
			alert("Please select Designations");
		}
		
		
	}
	function officeTypeEditor(container, options) {
		$('<input name="Office Type" data-text-field="officeTypeName" data-value-field="officeTypeId" id="officeTypeId" data-bind="value:' + options.field + '" required="true"/>')
		.appendTo(container).kendoDropDownList({
			optionLabel : {
				officeTypeName : "Select",
				officeTypeId : "",
			},
			defaultValue : false,
			sortable : true,
			dataSource : {
				transport : {
					read : {
						url : "${getOfficeType}",
						type : "POST"
					}
				}
			}
		});
		$('<span class="k-invalid-msg" data-for="Office Type"></span>').appendTo(container);
	}
	
	function designationEditor(container, options) {
		$('<input name="Designation" data-text-field="designation" data-value-field="designationId" id="designationId" data-bind="value:' + options.field + '" required="true"/>')
		.appendTo(container).kendoDropDownList({
			optionLabel : {
				designation : "Select",
				designationId : "",
			},
			defaultValue : false,
			sortable : true,
			dataSource : {
				transport : {
					read : {
						url : "${getDesignation}",
						type : "POST"
					}
				}
			}
		});
		$('<span class="k-invalid-msg" data-for="Designation"></span>').appendTo(container);
	}
	
	function companyEditor(container, options) {
		$('<input name="Company" data-text-field="companyName" data-value-field="companyId" id="companyId" data-bind="value:' + options.field + '" required="true"/>')
		.appendTo(container).kendoDropDownList({
			optionLabel : {
				companyName : "Select",
				companyId : "",
			},
			defaultValue : false,
			sortable : true,
			dataSource : {
				transport : {
					read : {
						url : "${getCompany}",
						type : "POST"
					}
				}
			}
		});
		$('<span class="k-invalid-msg" data-for="Company Name"></span>').appendTo(container);
	}
	
	
	
	
	function onProcessRequestEnd(e){
		if(e.type == 'create'){
			alert("Addded Successfully");
			var grid = $('#grid').data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}else if(e.type == 'update'){
			alert("Updated Successfully");
			var grid = $('#grid').data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}else if(e.type == 'destroy'){
			alert("Deleted Successfully");
			var grid = $('#grid').data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
	}
	
	function onProcessRequestStart(e){
		$('.k-grid-update').hide();
		$('.k-edit-buttons').append('<img src="./resources/img/preloader.GIF" alt="please wait" style="verticle-align:middle"/>&nbsp;&nbsp;Keep Calm..&nbsp;&nbsp;&nbsp;&nbsp;');
		$('.k-grid-cancel').hide();
	}
	
	
	function onEscalationRequestEnd(e){
		if(e.type == 'create'){
			alert("Addded Successfully");
			var grid = $('#grid_'+id).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}else if(e.type == 'update'){
			alert("Updated Successfully");
			var grid = $('#grid_'+id).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}else if(e.type == 'destroy'){
			alert("Deleted Successfully");
			var grid = $('#grid_'+id).data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
	}
	
	function onEscalationRequestStart(e){
		$('.k-grid-update').hide();
		$('.k-edit-buttons').append('<img src="./resources/img/preloader.GIF" alt="please wait" style="verticle-align:middle"/>&nbsp;&nbsp;Keep Calm..&nbsp;&nbsp;&nbsp;&nbsp;');
		$('.k-grid-cancel').hide();
	}
	
	//register custom validation rules

	(function ($, kendo) 
		{  
	    $.extend(true, kendo.ui.validator, 
	    {
	         rules: 
	         { // custom rules
	        	 processNameRequired : function(input,params){
							    	   if (input.attr("name") == "processName")
				                       {
				                        return $.trim(input.val()) !== "";
				                       }
				                       return true;
							       } ,
				processNameUniquevalidation : function(input, params){
										if(flagProcessName){
											if (input.filter("[name='processName']").length && input.val()) 
											{
												var flag = true;
												$.each(processNameArray, function(idx1, elem1) {
													if(elem1.toLowerCase() == input.val().toLowerCase())
													{
														flag = false;
													}	
												});
												return flag;
											}
										}
										return true;
									},
						processNameLengthValidation : function (input, params) 
							          {         
							              if (input.filter("[name='processName']").length && input.val() != "") 
							              {
							             	 return /^[a-zA-Z ]{1,45}$/.test(input.val());
							              }        
							              return true;
							          },
						processDurationRequired: function (input, params) 
						             {         
							if (input.attr("name") == "processDuration")
		                       {
		                        return $.trim(input.val()) !== "";
		                       }
		                       return true;      
						    },
					slaRequired: function (input, params) 
				             {         
						    if (input.attr("name") == "sla")
                      		 {
                      		  return $.trim(input.val()) !== "";
                      		 }
                      		 return true;      
				   			},
	            },
	         messages: 
	         {
				//custom rules messages
	        	 processNameRequired : "Process name is required",
	        	 processNameUniquevalidation : "Process name is already exists",
	        	 processNameLengthValidation : "Process name can contain alphabets,spaces and max 45 characters",
	        	 processDurationRequired : "Process duration is required",
	        	 slaRequired : "SLA is required"
			 }
	    });
	    
	})(jQuery, kendo);
		
</script>

<style>

	#grid {
		font-size: 12px !important;
		font-weight: normal;
		color: black;
	}

	.bgGreenColor{
		background: #CCEBD6;
		color: #000000 
	}
	.k-window-titlebar{
		height: 25px;
	}
	.ui-dialog .ui-dialog-title {
		font-weight: lighter;
	}
	
	
</style>
	