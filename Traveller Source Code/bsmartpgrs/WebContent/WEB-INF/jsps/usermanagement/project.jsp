<%@include file="/common/taglibs.jsp"%>

<c:url value="/project/read" var="readUrl" />
<c:url value="/project/save" var="saveUrl" />
<c:url value="/project/update" var="updateUrl" />
<c:url value="/project/delete" var="destroyUrl" />
<c:url value="/project/company" var="getCompany" />
<c:url value="/project/getMetricsChoice" var="getMetricsChoice" />

<c:url value="/project/getProjectNameForFilter" var="getProjectNameForFilter" />
<c:url value="/project/getCompanyNameForFilter" var="getCompanyNameForFilter" />
<c:url value="/project/getSalesLadgerForFilter" var="getSalesLadgerForFilter" />
<c:url value="/project/getMetricForFilter" var="getMetricForFilter" />
<c:url value="/project/getStatusForFilter" var="getStatusForFilter" />

<c:url value="/project/Contract/readContractUrl" var="readContractUrl" />
<c:url value="/project/Contract/createContractUrl" var="createContractUrl" />
<c:url value="/project/Contract/updateContractUrl" var="updateContractUrl" />
<c:url value="/project/Contract/destroyContractUrl" var="destroyContractUrl" />

<c:url value="/project/template/tree" var="transportReadUrlCat" />

<c:url value="/common/getAllChecks" var="allChecksUrl" />


<c:url value="/project/Milestone/readMilestoneUrl" var="readMilestoneUrl" />
<c:url value="/project/Milestone/createMilestoneUrl" var="createMilestoneUrl" />
<c:url value="/project/Milestone/updateMilestoneUrl" var="updateMilestoneUrl" />

<c:url value="/project/template/level/read" var="readLevelUrl" />
<c:url value="/project/template/level/save" var="saveLevelUrl" />
<c:url value="/project/template/level/update" var="updateLevelUrl" />
<c:url value="/project/template/level/delete" var="destroyLevelUrl" />


<c:url value="/project/Accounts/level/read" var="readAccountsUrl" />
<c:url value="/project/Accounts/level/save" var="saveAccountsUrl" />
<c:url value="/project/Accounts/level/update" var="updateAccountsUrl" />
<c:url value="/project/Accounts/level/delete" var="destroyAccountsUrl" />

<c:url value="/project/Metrices/readMetricesUrl" var="readMetricesUrl" />
<c:url value="/project/Metrices/createMetricesUrl" var="createMetricesUrl" />
<c:url value="/project/Metrices/updateMetricesUrl" var="updateMetricesUrl" />
<c:url value="/project/Metrices/destroyMetricesUrl" var="destroyMetricesUrl" />
<c:url value="/project/Metrices/getAllHealthList" var="getAllHealthList" />

<c:url value="/project/Metrices/createinternalWeightageUrl" var="createinternalWeightageUrl" />
<c:url value="/project/Metrices/readinternalWeightageUrl" var="readinternalWeightageUrl" />
<c:url value="/project/Metrices/updateinternalWeightageUrl" var="updateinternalWeightageUrl" />
<c:url value="/project/Metrices/destroyinternalWeightageUrl" var="destroyinternalWeightageUrl" />


<c:url value="/project/template" var="getTemplate" />
<div id="content">

		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark"><i class="fa fa-lg fa-fw fa-gears"></i> Manage Projects</h1>
			</div>
		</div>
		
		
	<kendo:grid name="grid" detailTemplate="contractDetailTemp" resizable="true" pageable="true" sortable="true"  scrollable="true" edit="projectEvent" change="onChange" selectable="true" dataBound="dataBound" remove="removeProject">
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
			<kendo:grid-filterable-operators>
				<kendo:grid-filterable-operators-string eq="Is equal to" />
			</kendo:grid-filterable-operators>

		</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" />
		<kendo:grid-toolbar>
			<kendo:grid-toolbarItem name="create" text="Add Project"/>
			 <kendo:grid-toolbarItem text="Clear_Filter" />
		</kendo:grid-toolbar>
		<kendo:grid-columns>
			<kendo:grid-column title="Project Name" field="name" width="110px" filterable="true" >
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getProjectNameForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Company Name" field="companyId" editor="companyEditor"  hidden="true"/>
			<kendo:grid-column title="Company Name" field="companyName" width="160px" filterable="true">
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
			<kendo:grid-column title="Sales Ledger" field="salesLedger" width="110px" filterable="true">
			<kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getSalesLadgerForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			<kendo:grid-column title="Enclosure Details" field="enclosuresDetails" width="110px" hidden="true" editor="infoTextEditor"/>
			<kendo:grid-column title="Status" field="status" width="110px" template="#: status == 1 ? 'Active' :'Inactive'#">
			<%-- <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getStatusForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column> --%>
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
						          read :  "${getStatusForFilter}"
						         }
						        } 
						       });
						         }
						</script>
					</kendo:grid-column-filterable-ui>
				</kendo:grid-column-filterable>
			</kendo:grid-column>	
			  <kendo:grid-column title="Metric" field="metric" width="110px" editor="metricsList" filterable="true">
			  <kendo:grid-column-filterable>
		    			<kendo:grid-column-filterable-ui>
	    					<script> 
								function statusFilter(element) {
									element.kendoAutoComplete({
										dataType: 'JSON',
										dataSource: {
											transport: {
												read: "${getMetricForFilter}"
											}
										}
									});
						  		}
						  	</script>		
		    			</kendo:grid-column-filterable-ui>
		    	</kendo:grid-column-filterable>  
		    	</kendo:grid-column>
			
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
						<kendo:dataSource-schema-model-field name="companyId" type="number"/>
						<kendo:dataSource-schema-model-field name="enclosuresDetails" type="string"/>
						<kendo:dataSource-schema-model-field name="salesLedger" type="string">
						
						<kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="name"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="metric" type="string"/>
							<kendo:dataSource-schema-model-field name="status" type="number"/>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid>
	
	<!-- ..........................contract Details...................................................................... -->
	
	 <kendo:grid-detailTemplate id="contractDetailTemp">
		<kendo:tabStrip name="tabStrip_#=id#">
		<kendo:tabStrip-animation></kendo:tabStrip-animation>
	<kendo:tabStrip-items>
	<%--<kendo:tabStrip-item selected="true" text="Contract Details ">
	<kendo:tabStrip-item-content>
	<kendo:grid name="contractDetailGrid_#=id#" pageable="true" resizable="true" reorderable="true" selectable="true" sortable="true" scrollable="true" edit="contractEvent" dataBound="contractEventDatabound" editable="true">
	<kendo:grid-pageable pageSize="10"></kendo:grid-pageable>
								<kendo:grid-filterable extra="false">
									<kendo:grid-filterable-operators>
										<kendo:grid-filterable-operators-string eq="Is equal to" />
									</kendo:grid-filterable-operators>
								</kendo:grid-filterable>
	
			<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?"  />
			<kendo:grid-toolbar>
				<kendo:grid-toolbarItem name="create" text="Add Contract Details"/>
			</kendo:grid-toolbar>

			<kendo:grid-columns>
				<kendo:grid-column title="Office&nbsp;Name&nbsp;*" field="officeName" filterable="false" width="180px"/>
				<kendo:grid-column title="Select&nbsp;Office&nbsp;" field="office" width="100px" hidden="true" editor="officeTreeEditor" />
				<kendo:grid-column title="Select&nbsp;Office&nbsp;" hidden="true" field="projectHeirarchyId" width="100px" />
				<kendo:grid-column title="Contract Number&nbsp;*" field="contracNumber" width="145px" />
				<kendo:grid-column title="Contract Date&nbsp;*" field="contractDate"  format="{0: dd/MM/yyyy}" width="130px" />
				<kendo:grid-column title="Start Date&nbsp;*" field="startDate" format="{0: dd/MM/yyyy}"  width="130px" />
				<kendo:grid-column title="End Date&nbsp;*" field="endDate" format="{0: dd/MM/yyyy}"  width="130px" />
				<kendo:grid-column title="Type Of Contract&nbsp;*" field="typeOfContract" width="140px" filterable="true" editor="dropDownChecksEditor"/>
				<kendo:grid-column title="Billing Cycle&nbsp;*" field="billingCycle" width="130px" filterable="true" editor="dropDownChecksEditor"/>
				<kendo:grid-column title="Contract Status&nbsp;*" field="contractStatus" width="140px" filterable="true" editor="dropDownChecksEditor"/>
				 <kendo:grid-column title="projectHeirarchyId" field="projectHeirarchyId" width="140px" filterable="true" hidden="true"/>
               
		<kendo:grid-column title="&nbsp;" width="100px">
					<kendo:grid-column-command>
						<kendo:grid-column-commandItem name="edit" />
						<kendo:grid-column-commandItem name="destroy"/>
					</kendo:grid-column-command>
		</kendo:grid-column>
			</kendo:grid-columns>

			<kendo:dataSource requestEnd="onContractRequestEnd" requestStart="onContractRequestStart">
				<kendo:dataSource-transport>
					<kendo:dataSource-transport-read url="${readContractUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
					<kendo:dataSource-transport-create url="${createContractUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
					<kendo:dataSource-transport-update url="${updateContractUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
					<kendo:dataSource-transport-destroy url="${destroyContractUrl}" dataType="json" type="GET" contentType="application/json" />					
				</kendo:dataSource-transport>

				<kendo:dataSource-schema>
					<kendo:dataSource-schema-model id="contractId">
						<kendo:dataSource-schema-model-fields>
						    <kendo:dataSource-schema-model-field name="contractId" type="number"/>
						    <kendo:dataSource-schema-model-field name="id" type="number"/>
						    <kendo:dataSource-schema-model-field name="projectHeirarchyId" type="number"/>
							<kendo:dataSource-schema-model-field name="contracNumber" type="string"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="contractDate" type="date"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="startDate" type="date"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="endDate" type="date"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="typeOfContract" defaultValue="Original"/>
							<kendo:dataSource-schema-model-field name="billingCycle" defaultValue="Monthly"/>
							<kendo:dataSource-schema-model-field name="contractStatus" defaultValue="valid"/>
							
						</kendo:dataSource-schema-model-fields>
					</kendo:dataSource-schema-model>
				</kendo:dataSource-schema>
			</kendo:dataSource>
		</kendo:grid>	
	</kendo:tabStrip-item-content>
	</kendo:tabStrip-item>	
	
	
	
	
		<kendo:tabStrip-item text="Payment Milestone">
					<kendo:tabStrip-item-content>
							<kendo:grid name="projectMilestone_#=id#" pageable="true" resizable="true" sortable="true" edit="milestoneEvent" reorderable="true" selectable="true" scrollable="true">
								<kendo:grid-pageable pageSize="10"></kendo:grid-pageable>
	
			<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?"  />
			<kendo:grid-toolbar>
				<kendo:grid-toolbarItem name="create" text="Add ProjectMilestone Details"/>
			</kendo:grid-toolbar>

			<kendo:grid-columns>
				<kendo:grid-column title="Office&nbsp;Name&nbsp;*" field="officeName" filterable="false" width="180px"/>
				<kendo:grid-column title="Select&nbsp;Office&nbsp;" field="projectHeirarchyId" width="100px" hidden="true" editor="officeTreeEditor" />
				<kendo:grid-column title="Select&nbsp;Office&nbsp;" hidden="true" field="projectHeirarchyId" width="100px"  editor="officeComboBoxEditor" />
				
				<kendo:grid-column title="Milestone SlNumber&nbsp;*" field="msSlNo" width="140px" filterable="true"/>
				<kendo:grid-column title="Milestone Name&nbsp;*" field="msName" filterable="true" width="130px" />
				<kendo:grid-column title="Milestone Type&nbsp;*" field="msType" filterable="true"  width="130px" editor="dropDownChecksEditor"/>
							<kendo:grid-column title="Quantity&nbsp;*" field="quantity" filterable="true" width="130px" />
				<kendo:grid-column title="Rate&nbsp;*" field="msRate" filterable="true" width="85px" /> 
				<kendo:grid-column title="Payment TermOn&nbsp;*" field="paymentTermOn" width="140px" filterable="true" editor="dropDownChecksEditor"/>
				<kendo:grid-column title="MileStone&nbsp;*" field="mileStone" width="140px" filterable="true"/>
				<kendo:grid-column title="Ideal Days;*" field="idealDays" width="140px" filterable="true"/>
				<kendo:grid-column title="Max Days;*" field="maxDays" width="140px" filterable="true" />
				<kendo:grid-column title="Health Metric Weightage;*" field="healthMetricWeightage" width="140px" filterable="true"/>
				
				<kendo:grid-column title="projectHeirarchyId" field="projectHeirarchyId" width="140px" filterable="true" hidden="true"/>
               
		<kendo:grid-column title="&nbsp;" width="100px">
					<kendo:grid-column-command>
						<kendo:grid-column-commandItem name="edit" />
						<kendo:grid-column-commandItem name="destroy"/>
					</kendo:grid-column-command>
		</kendo:grid-column>
			</kendo:grid-columns>

			<kendo:dataSource requestEnd="onprojectMilestoneRequestEnd" >
				<kendo:dataSource-transport>
					<kendo:dataSource-transport-read url="${readMilestoneUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
					<kendo:dataSource-transport-create url="${createMilestoneUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
					<kendo:dataSource-transport-update url="${updateMilestoneUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
					<kendo:dataSource-transport-destroy url="${destroyContractUrl}" dataType="json" type="GET" contentType="application/json" />					
				</kendo:dataSource-transport>

				<kendo:dataSource-schema>
					<kendo:dataSource-schema-model id="pmId">
						<kendo:dataSource-schema-model-fields>
						    <kendo:dataSource-schema-model-field name="pmId" type="number"/>
						    <kendo:dataSource-schema-model-field name="id" type="number"/>
						    <kendo:dataSource-schema-model-field name="projectHeirarchyId" type="number"/>
							<kendo:dataSource-schema-model-field name="msSlNo" type="string"/>
							<kendo:dataSource-schema-model-field name="msName" />
							<kendo:dataSource-schema-model-field name="msType" defaultValue="Percentage" />
							<kendo:dataSource-schema-model-field name="msRate" type="number">
							<kendo:dataSource-schema-model-field-validation min="0"/>
							</kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="paymentTermOn" defaultValue="Basic Value"/>
							<kendo:dataSource-schema-model-field name="mileStone" type="number" >
							<kendo:dataSource-schema-model-field-validation min="0"/>
							</kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="idealDays" type="number" >
							<kendo:dataSource-schema-model-field-validation min="0"/>
							</kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="maxDays" type="number" >
							<kendo:dataSource-schema-model-field-validation min="0"/>
							</kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="healthMetricWeightage" type="number" >
							<kendo:dataSource-schema-model-field-validation min="0"/>
								</kendo:dataSource-schema-model-field>
							<kendo:dataSource-schema-model-field name="quantity" type="number" >
							<kendo:dataSource-schema-model-field-validation min="0"/>
							</kendo:dataSource-schema-model-field>
							
						</kendo:dataSource-schema-model-fields>
					</kendo:dataSource-schema-model>
				</kendo:dataSource-schema>
			</kendo:dataSource>
		</kendo:grid>	
	</kendo:tabStrip-item-content>
	</kendo:tabStrip-item>	
	 --%>
	
	 <kendo:tabStrip-item text="Levels Details" selected="true">
                <kendo:tabStrip-item-content>
                    <kendo:grid name="grid_#=id#" pageable="true" sortable="true" scrollable="false" edit="levelEvent" selectable="true">
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
                            <%-- <kendo:grid-column title="Type" field="type"  editor="typeEditor" width="100px"/> --%>
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
										<<%-- kendo:dataSource-schema-model-field name="type"><kendo:dataSource-schema-model-field-validation required="true" /></kendo:dataSource-schema-model-field> --%>
									</kendo:dataSource-schema-model-fields>
								</kendo:dataSource-schema-model>
							</kendo:dataSource-schema>
                        </kendo:dataSource>
                    </kendo:grid>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
            
            
            
            
           <%--  <kendo:tabStrip-item text="Accounts Details" >
                <kendo:tabStrip-item-content>
                    <kendo:grid name="gridAcc_#=id#" pageable="true" sortable="true" scrollable="false" edit="AccountsEvent">
                    	<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
						<kendo:grid-filterable extra="false">
							<kendo:grid-filterable-operators>
								<kendo:grid-filterable-operators-string eq="Is equal to" />
							</kendo:grid-filterable-operators>
						</kendo:grid-filterable>
						<kendo:grid-editable mode="popup" />
						<kendo:grid-toolbar>
							<kendo:grid-toolbarItem name="create" text="Add Account Details"/>
						</kendo:grid-toolbar>
                        <kendo:grid-columns>
                             <kendo:grid-column title="Company Name" field="companyName" width="110px"/>
                              <kendo:grid-column title="Project Name" field="name" width="110px"/>
                            <kendo:grid-column title="User Name" field="uName" width="110px"/>
                            <kendo:grid-column title="Password" field="pwd" width="110px" />
                            <kendo:grid-column title="Ip Address" field="ipAddress" width="110px" />
                            <kendo:grid-column title="Port Number" field="portNumber" width="110px" />
                            <kendo:grid-column title="Request" field="request" editor="requestEditor" width="100px"/>
                          
                          
                          	
                            <kendo:grid-column title="&nbsp;" width="160px">
								<kendo:grid-column-command>
									<kendo:grid-column-commandItem name="edit" />
									<kendo:grid-column-commandItem name="destroy" />
								</kendo:grid-column-command>
							</kendo:grid-column>
                        </kendo:grid-columns>
                        <kendo:dataSource pageSize="5" requestEnd="onRequestEndAccounts">
                           	<kendo:dataSource-transport>
									<kendo:dataSource-transport-create url="${saveAccountsUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-read url="${readAccountsUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
									<kendo:dataSource-transport-update url="${updateAccountsUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-destroy url="${destroyAccountsUrl}"  dataType="json" type="POST" contentType="application/json" />
							</kendo:dataSource-transport>
							<kendo:dataSource-schema>
								<kendo:dataSource-schema-model id="accId">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="accId" type="number" />
										<kendo:dataSource-schema-model-field name="uName" type="string">
										<kendo:dataSource-schema-model-field-validation required="true" />
										</kendo:dataSource-schema-model-field>
										<kendo:dataSource-schema-model-field name="companyName" type="string"/>
										<kendo:dataSource-schema-model-field name="name" type="string"/>
										  <kendo:dataSource-schema-model-field name="projectId" type="number"/>
										<kendo:dataSource-schema-model-field name="pwd" type="string">
										<kendo:dataSource-schema-model-field-validation required="true" />
										</kendo:dataSource-schema-model-field>
										<kendo:dataSource-schema-model-field name="ipAddress" type="string"/>
										<kendo:dataSource-schema-model-field name="createdBy" type="string" />
										<kendo:dataSource-schema-model-field name="createdDate" type="date"/>
										<kendo:dataSource-schema-model-field name="portNumber" type="string"/>
										<kendo:dataSource-schema-model-field name="request" type="string"/>
										
									</kendo:dataSource-schema-model-fields>
								</kendo:dataSource-schema-model>
							</kendo:dataSource-schema>
                        </kendo:dataSource>
                    </kendo:grid>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
            
            
              <kendo:tabStrip-item text="InternalWeightage" >
              <kendo:tabStrip-item-content>
                    <kendo:grid name="internalWeightage_#=id#" pageable="true" sortable="true" scrollable="false" edit="internalWeightageEvent" selectable="true">
                    	<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
						<kendo:grid-filterable extra="false">
							<kendo:grid-filterable-operators>
								<kendo:grid-filterable-operators-string eq="Is equal to" />
							</kendo:grid-filterable-operators>
						</kendo:grid-filterable>
						<kendo:grid-editable mode="popup" />
						<kendo:grid-toolbar>
							<kendo:grid-toolbarItem name="create" text="Add internalWeightage Details"/>
						</kendo:grid-toolbar>
						 <kendo:grid-columns>
                          
                              <kendo:grid-column title="metrics name" field="metricsName" width="110px" />
                            <kendo:grid-column title="HealthweightageValue" field="healthWeightage" width="110px" />
                            
                             <kendo:grid-column title="&nbsp;" width="160px">
								<kendo:grid-column-command>
									<kendo:grid-column-commandItem name="edit" />
									<kendo:grid-column-commandItem name="destroy" />
								</kendo:grid-column-command>
							</kendo:grid-column>
                            </kendo:grid-columns>
                              <kendo:dataSource pageSize="5" requestEnd="onRequestEndinternalWeightage">
                            
                            <kendo:dataSource-transport>
                            
									<kendo:dataSource-transport-create url="${createinternalWeightageUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-read url="${readinternalWeightageUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
									<kendo:dataSource-transport-update url="${updateinternalWeightageUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-destroy url="${destroyinternalWeightageUrl}/#=id#"  dataType="json" type="POST" contentType="application/json" />
							</kendo:dataSource-transport>
								<kendo:dataSource-schema>
								<kendo:dataSource-schema-model id="internalWeightageId">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="internalWeightageId" type="number" />
										
										<kendo:dataSource-schema-model-field name="metricsName" type="string"/>
										<kendo:dataSource-schema-model-field name="healthWeightage" type="number" >
										<kendo:dataSource-schema-model-field-validation min="0"/>
			                           </kendo:dataSource-schema-model-field>
										
										</kendo:dataSource-schema-model-fields>
										</kendo:dataSource-schema-model>
										</kendo:dataSource-schema>
										</kendo:dataSource>
            
                                  </kendo:grid>
			                      </kendo:tabStrip-item-content>			
                                 </kendo:tabStrip-item>
            
            
            
            
            
            
            <kendo:tabStrip-item text="Metrics" >
                <kendo:tabStrip-item-content>
                    <kendo:grid name="gridMetrics_#=id#" pageable="true" sortable="true" scrollable="false" edit="MetricsEvent" dataBound="metricesDataBound" selectable="true">
                    	<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="10"></kendo:grid-pageable>
						<kendo:grid-filterable extra="false">
							<kendo:grid-filterable-operators>
								<kendo:grid-filterable-operators-string eq="Is equal to" />
							</kendo:grid-filterable-operators>
						</kendo:grid-filterable>
						<kendo:grid-editable mode="popup" />
						<kendo:grid-toolbar>
							<kendo:grid-toolbarItem name="create" text="Add Metrics Details"/>
						</kendo:grid-toolbar>
						 <kendo:grid-columns>
                             <kendo:grid-column title="Metric Score" field="metriceScore" width="110px" hidden="true"/>
                              <kendo:grid-column title="Health" field="health" width="110px" editor="healthList"/>
                            <kendo:grid-column title="Min" field="min" width="110px" />
                            <kendo:grid-column title="Max" field="max" width="110px" />
                             <kendo:grid-column title="&nbsp;" width="160px">
								<kendo:grid-column-command>
									<kendo:grid-column-commandItem name="edit" />
									<kendo:grid-column-commandItem name="destroy" />
								</kendo:grid-column-command>
							</kendo:grid-column>
                            </kendo:grid-columns>
                            <kendo:dataSource pageSize="5" requestEnd="onRequestEndMetrices">
                            
                            <kendo:dataSource-transport>
                            
									<kendo:dataSource-transport-create url="${createMetricesUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-read url="${readMetricesUrl}/#=id#" dataType="json" type="POST" contentType="application/json" />
									<kendo:dataSource-transport-update url="${updateMetricesUrl}/#=id#" dataType="json" type="GET" contentType="application/json" />
									<kendo:dataSource-transport-destroy url="${destroyMetricesUrl}/#=id#"  dataType="json" type="POST" contentType="application/json" />
							</kendo:dataSource-transport>
								<kendo:dataSource-schema>
							<kendo:dataSource-schema-model id="metricId">
									<kendo:dataSource-schema-model-fields>
										<kendo:dataSource-schema-model-field name="metricId" type="number" />
										<kendo:dataSource-schema-model-field name="metriceScore" type="number" />
										<kendo:dataSource-schema-model-field name="health" type="string"/>
										<kendo:dataSource-schema-model-field name="min" type="number" defaultValue="1">
										<kendo:dataSource-schema-model-field-validation min="0"/>
			                           </kendo:dataSource-schema-model-field>
										<kendo:dataSource-schema-model-field name="max" type="number">
										<kendo:dataSource-schema-model-field-validation max="100"/>
			                                </kendo:dataSource-schema-model-field>
										</kendo:dataSource-schema-model-fields>
										</kendo:dataSource-schema-model>
										</kendo:dataSource-schema>
										</kendo:dataSource>
										
							       </kendo:grid>
			                      </kendo:tabStrip-item-content>			
                                 </kendo:tabStrip-item> --%>
            
            
            
	</kendo:tabStrip-items>
	</kendo:tabStrip>
	</kendo:grid-detailTemplate>
	</div>
   
 

<script>
	
	

	


    var companyName="";
    var name="";
	var id = "";
	var nodeid = "";
	function onChange(arg) {
		var gview = $("#grid").data("kendoGrid");
		var selectedItem = gview.dataItem(gview.select());
		id = selectedItem.id;
		companyName=selectedItem.companyName;
		name=selectedItem.name;
		this.collapseRow(this.tbody.find(":not(tr.k-state-selected)"));
	}
	function projectEvent(e){
		$('div[data-container-for="status"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		$('div[data-container-for="companyName"]').remove();
		$('label[for="companyName"]').closest('.k-edit-label').remove();
		$('div[data-container-for="projectTemplateName"]').remove();
		$('label[for="projectTemplateName"]').closest('.k-edit-label').remove();
		
		$(".k-grid-cancel").click(function () {
			 var grid = $("#grid").data("kendoGrid");
			 grid.dataSource.read();cancel
			 
			 grid.refresh();
	    });
		
		if (e.model.isNew()) {
			//securityCheckForActions("./master/project/create");
			$(".k-window-title").text("Add New Project");
			$(".k-grid-update").text("Save");
		}else{
			//securityCheckForActions("./master/project/update");
			$(".k-window-title").text("Edit "+e.model.get("name")+" Details");
		}
	}
	
	$("#grid").on("click", ".k-grid-Clear_Filter", function() {
		$("form.k-filter-menu button[type='reset']").slice().trigger("click");
		var grid = $("#grid").data("kendoGrid");
		grid.dataSource.read();
		grid.refresh();
	});
	
	function levelEvent(e){
		$('div[data-container-for="level"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		$('div[data-container-for="level"]').remove();
		$('label[for="level"]').closest('.k-edit-label').remove();
		if (e.model.isNew()) {
			//securityCheckForActions("./master/project/create");
			$(".k-window-title").text("Add New Project");
			$(".k-grid-update").text("Save");
		}else{
			//securityCheckForActions("./master/project/update");
			$(".k-window-title").text("Edit "+e.model.get("name")+" Details");
		}
	}
	
	function removeProject(){
		var conf = confirm("Are you sure want to delete this Project?");
		    if(!conf){
		    $('#grid').data().kendoGrid.dataSource.read();
		    throw new Error('deletion aborted');
		     } 
    }
	
	
	function typeEditor(container, options) {

		var data = [ {
			text : "Billing Office",
			value : "Billing Office"
		}, {
			text : "Payment Office",
			value : "Payment Office"
		}, {
			text : "Reporting Office",
			value : "Reporting Office"
		} ];

		$(
				'<input name="type" data-text-field="text" id="dept"  data-value-field="value" data-bind="value:' + options.field + '""required"/>')
				.appendTo(container).kendoDropDownList({

					dataTextField : "text",
					dataValueField : "value",
					optionLabel : {
						text :  "Select",
						value : "",
					},
					defaultValue : false,
					sortable : true,
					dataSource : data
				});
		
       

		$('<span class="k-invalid-msg" data-for="type"></span>').appendTo(container);

	}
	
	
	   
	function activateProject(){
		// var result=securityCheckForActionsForStatus("./master/project/approve");			
	    // if(result=="success")
	    // {	
			$.ajax({
				type : "GET",
				url : "./project/status/"+1+"/"+id,
				dataType : "text",
				success : function(response) {
					alert(response);
					$('#grid').data('kendoGrid').dataSource.read();
				}
			});
	    // }
	}
	
	function deActivateProject(){
		$.ajax({
			type : "GET",
			url : "./project/status/"+0+"/"+id,
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
	
	function templateEditor(container, options) {
		$('<input name="Company" data-text-field="projectTemplateName" data-value-field="projectTemplateId" id="projectTemplateId" data-bind="value:' + options.field + '" required="true"/>')
		.appendTo(container).kendoDropDownList({
			optionLabel : {
				projectTemplateName : "Select",
				projectTemplateId : "",
			},
			defaultValue : false,
			sortable : true,
			dataSource : {
				transport : {
					read : {
						url : "${getTemplate}",
						type : "POST"
					}
				}
			}
		});
		$('<span class="k-invalid-msg" data-for="Company Name"></span>').appendTo(container);
	}
	
	function officeComboBoxEditor(container, options) {
		$(
				'<input name="Office" data-text-field="text" id="offcomboId" data-value-field="id" data-bind="value:' + options.field + '" required="required"/>')
				.appendTo(container).kendoComboBox({
					autoBind : false,
					optionLabel : "Select",
					select : onSelectCombooffice,
					dataSource : {
						transport : {
							read : "./project/template/getoffices/"+id
						}
					}

				});
		$('<span class="k-invalid-msg" data-for="Office"></span>').appendTo(
				container);
	}
	
	function officeTreeEditor(container, options) {

		$(
				'<div style="max-height:200px; overflow: auto;  border:1px solid red; border-radius:7px; "></div>')
				.appendTo(container)
				.kendoTreeView(
						{
							dataTextField : "text",
							template : " #: item.text # <input type='hidden' id='hiddenId' class='#: item.text ##: item.id#' value='#: item.id#'/>",
							name : "treeview",
							select : onTreeSelectProject,
							loadOnDemand : true,
							dataSource : {
								transport : {
									read : {
										url : "${transportReadUrlCat}/"+id,
										contentType : "application/json",
										type : "GET"
									}
								},
								schema : {
									model : {
										id : "id",
										hasChildren : "hasChilds"
									}
								}
							}
						});
	}

	function onTreeSelectProject(e) {
		nodeid = $('.k-state-hover').find('#hiddenId').val();
		$('div[data-container-for="periodOfWork"]').show();
		$('label[for="periodOfWork"]').closest('.k-edit-label').show();

		$('input[name="periodOfWork"]').prop("readonly", true);

		var kitems = $(e.node).add(
				$(e.node).parentsUntil('.k-treeview', '.k-item'));
		texts = $.map(kitems,
				function(kitem) {
					return $(kitem).find(
							'>div span.k-in > input[id="hiddenId"]').val();
				});

		 dropDownDataSource2 = new kendo.data.DataSource({
			transport : {
				read : {
					url : "./project/template/getofficesonid/" + nodeid+"/"+id,
					dataType : "json",
					type : 'GET'
				}
			}
		});

		$("#offcomboId").kendoComboBox({
			dataSource : dropDownDataSource2,
			dataTextField : "text",
			select : onSelectCombooffice,
			dataValueField : "id",
			optionLabel : {
				text : "Select",
				id : "",
			},
		}); 

	}
	
	function onSelectCombooffice(e) {
		var dataItem = this.dataItem(e.item.index());
		var officeId = dataItem.id;
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
			$('#grid').data('kendoGrid').dataSource.read();
			$('#grid').data('kendoGrid').refresh();
		}
	}
	
	function onRequestEndChild(r){
		if (r.type == 'create') {
			alert("Added Successfully");
			$('#grid_'+id).data('kendoGrid').dataSource.read();
			$('#grid_'+id).data('kendoGrid').refresh();
		} else if (r.type == 'update') {
			alert("Updated Successfully");
			$('#grid_'+id).data('kendoGrid').dataSource.read();
			$('#grid_'+id).data('kendoGrid').refresh();
		}else if (r.type == 'destroy') {
			alert("Deleted Successfully");
		}
	}
	
	
	
	
	
	
	
	
	function contractEventDatabound(e)
	{
		
	}
	
	function contractEvent(e)
	{
		
	 	$('div[data-container-for="officeName"]').remove();
		$('label[for="officeName"]').closest('.k-edit-label').remove();
		
		$('div[data-container-for="projectHeirarchyId"]').remove();
		$('label[for="projectHeirarchyId"]').closest('.k-edit-label').remove();
		 
		
		if (e.model.isNew()) 
		{				
			 $(".k-window-title").text("Add Contract Details");
			 $(".k-grid-update").text("Save");
			
			$('label[for=status]').hide();
			$('div[data-container-for="status"]').hide();
			
		}
		else
		{
			$(".k-window-title").text("Edit Contract Details");
	    }
	
	}
	
	

	function onContractRequestEnd(e)
	{
		if (typeof e.response != 'undefined') 
		{
			if (e.response.status == "ERROR") 
			{
				errorInfo = "";
				errorInfo = e.response.result.deleteBaseUomError;
				alert(errorInfo);
				
				var grid = $('#projectMilestone_' + SelectedRowId).data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}
		}
		if (e.type == "update" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("projectMilestone Details Updated Successfully");
			$("#alertsBox").dialog
			({
			   modal : true,
			   buttons : {
				"Close" : function(){
					$(this).dialog("close");
				 }
			   }
			});
			e.sender.read();
		}
		if (e.type == "create" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("projectMilestone Details Added Successfully");
			$("#alertsBox").dialog
			({
			   modal : true,
			   buttons : {
				"Close" : function(){
					$(this).dialog("close");
				 }
			   }
			});
			e.sender.read();
		}
	}
	
	function onContractRequestStart(e)
	{
		
	}
    
	function dropDownChecksEditor(container, options) {
		var res = (container.selector).split("=");
		var attribute = res[1].substring(0,res[1].length-1);
		
		$('<input data-text-field="text" data-value-field="value" data-bind="value:' + options.field + '"required ="true" name = "'+attribute+'" id = "'+attribute+'Id"/>')
				.appendTo(container).kendoDropDownList({
					/* optionLabel : {
						text : "Select",
						value : "",
					}, */
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
	
	
	
	
	function milestoneEvent(e)
	{
		
		$('div[data-container-for="officeName"]').remove();
		$('label[for="officeName"]').closest('.k-edit-label').remove();
		$('label[for=maxDays]').hide();
		$('div[data-container-for="maxDays"]').hide();
		
		if (e.model.isNew()) 
		{				
			 $(".k-window-title").text("Add ProjectMilestone Details");
			 $(".k-grid-update").text("Save");
			
			$('label[for=status]').hide();
			$('div[data-container-for="status"]').hide();
			$(".k-grid-update").click(function() {
				e.model.set("projectHeirarchyId",nodeid);
			});
		}
		else
		{
			$(".k-grid-update").click(function() {
			$(".k-window-title").text("Edit ProjectMilestone Details");
			if(nodeid == "")
				e.model.set("projectHeirarchyId",e.model.get("projectHeirarchyId"));
			else
				e.model.set("projectHeirarchyId",nodeid);
			});
		}
		
	
	}
	
	function onprojectMilestoneRequestEnd(e)
	{
		if (typeof e.response != 'undefined') 
		{
			if (e.response.status == "ERROR") 
			{
				errorInfo = "";
				errorInfo = e.response.result.deleteBaseUomError;
				alert(errorInfo);
				
				var grid = $('#contractDetailGrid_' + SelectedRowId).data("kendoGrid");
				grid.dataSource.read();
				grid.refresh();
				return false;
			}
		}
		if (e.type == "update" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Contract Details Updated Successfully");
			$("#alertsBox").dialog
			({
			   modal : true,
			   buttons : {
				"Close" : function(){
					$(this).dialog("close");
				 }
			   }
			});
			e.sender.read();
		}
		if (e.type == "create" && !e.response.Errors)
		{
			$("#alertsBox").html("Alert");
			$("#alertsBox").html("Contract Details Added Successfully");
			$("#alertsBox").dialog
			({
			   modal : true,
			   buttons : {
				"Close" : function(){
					$(this).dialog("close");
				 }
			   }
			});
			e.sender.read();
		}
	}
	
	function infoTextEditor(container, options) {
		$(
				'<textarea name="Enclosure Description" data-text-field="enclosuresDetails" data-value-field="enclosuresDetails" data-bind="value:' + options.field + '" style="width: 161px; height: 46px;" required="true"/>')
				.appendTo(container);
		$(
				'<span class="k-invalid-msg" data-for="Enclosure Description"></span>')
				.appendTo(container);
	}
	
	
	
//:::::::::::::::::::::::::::::::::::::::::Accounts Scripts::::::::::::::::::::::::::::::::::::::::::::::::
	
	function requestEditor(container, options) {

		var data = [ {
			text : "HTTP",
			value : "HTTP"
		}, {
			text : "HTTP Basis",
			value : "HTTP Basis"
		}, {
			text : "HTTP Digest",
			value : "HTTP Digest"
		} ];

		$('<input name="request" data-text-field="text"   data-value-field="value" data-bind="value:' + options.field + '"required"required"/>')
				.appendTo(container).kendoDropDownList({

					dataTextField : "text",
					dataValueField : "value",
					optionLabel : {
						text :  "Select",
						value : "",
					},
					defaultValue : false,
					sortable : true,
					dataSource : data
				});
		
       

		$('<span class="k-invalid-msg" data-for="type"></span>').appendTo(container);

	}
	function AccountsEvent(e){
		$('div[data-container-for="level"]').hide();
		$('label[for="status"]').closest('.k-edit-label').hide();
		$('div[data-container-for="level"]').remove();
		$('label[for="level"]').closest('.k-edit-label').remove();
		
		
		  
		if (e.model.isNew()) {
			//securityCheckForActions("./master/project/create");
			$(".k-window-title").text("Add New Accounts Details");
			$(".k-grid-update").text("Save");
			$('input[name="companyName"]').val(companyName);
			  $('input[name="companyName"]').attr('readonly', 'readonly');
			  
			  
			  $('input[name="name"]').val(name);
			  $('input[name="name"]').attr('readonly', 'readonly');
		}else{
			//securityCheckForActions("./master/project/update");
			$(".k-window-title").text("Edit Accounts Details");
			$(".k-grid-update").text("Update");
			$('input[name="companyName"]').val(companyName);
			  $('input[name="companyName"]').attr('readonly', 'readonly')
			  $('input[name="name"]').val(name);
			  $('input[name="name"]').attr('readonly', 'readonly');
		}
	}
	
	
	function onRequestEndAccounts(r){
		if (r.type == 'create') {
			alert("Accounts Added Successfully");
			$('#gridAcc_'+id).data('kendoGrid').dataSource.read();
			$('#gridAcc_'+id).data('kendoGrid').refresh();
		} else if (r.type == 'update') {
			alert("Accounts Updated Successfully");
			$('#gridAcc_'+id).data('kendoGrid').dataSource.read();
			$('#gridAcc_'+id).data('kendoGrid').refresh();
		}else if (r.type == 'destroy') {
			alert("Accounts Deleted Successfully");
		}
	}
	
	function onRequestEndMetrices(r){
		if (r.type == 'create') {
			alert("Metrics Added Successfully");
			$('#gridMetrics_'+id).data('kendoGrid').dataSource.read();
			$('#gridMetrics_'+id).data('kendoGrid').refresh();
		} else if (r.type == 'update') {
			alert("Metrics Updated Successfully");
			$('#gridMetrics_'+id).data('kendoGrid').dataSource.read();
			$('#gridMetrics_'+id).data('kendoGrid').refresh();
		}else if (r.type == 'destroy') {
			alert("Metrics Deleted Successfully");
		}
	}
	
	
	function onRequestEndinternalWeightage(r){
		if (r.type == 'create') {
			alert("internalWeightage Added Successfully");
			$('#gridMetrics_'+id).data('kendoGrid').dataSource.read();
			$('#gridMetrics_'+id).data('kendoGrid').refresh();
		} else if (r.type == 'update') {
			alert("internalWeightage Updated Successfully");
			$('#gridMetrics_'+id).data('kendoGrid').dataSource.read();
			$('#gridMetrics_'+id).data('kendoGrid').refresh();
		}else if (r.type == 'destroy') {
			alert("internalWeightage Deleted Successfully");
		}
	}
	var maximum=0;
	function metricesDataBound(e)
	{
		var data = this.dataSource.view(), row;
		//maximum=data[i].max;
		var grid = $("#gridMetrics_").data("kendoGrid");
		for (var i = 0; i < data.length; i++) {
			row = this.tbody.children("tr[data-uid='" + data[i].uid + "']");
			if(data[i].max >=maximum){
				maximum=data[i].max;
			}
		}
		
	}
	function MetricsEvent(e)
	{
		$(".k-window-title").text("Add New Metrics Details");
		$(".k-grid-update").text("Save");
		  $('label[for=min]').parent().hide(); 
		  $('div[data-container-for="min"]').hide();
		  
		  
		  
		  
		  if (e.model.isNew()) 
		  {
			  
			 //securityCheckForActions("./userManagement/designation/createButton");
			 res1 = [];
			 $.ajax
			 ({
			      type : "GET",
				  dataType:"text",
				  url : "./read/allHealth/uniqueNess/"+id,
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
			  res1 = [];
				 $.ajax
				 ({
				      type : "GET",
					  dataType:"text",
					  url : "./read/allHealth/uniqueNess/"+id,
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
		
		  $(".k-window-title").text("Edit Metrics Details");
		  $(".k-grid-update").text("update");
		  
		 }
	
	function internalWeightageEvent(e)
	{ if (e.model.isNew()) 
	  {
		$(".k-window-title").text("Add New interNalWeightage Details");
		$(".k-grid-update").text("Save");
	  }
	else
		{
		 $(".k-window-title").text("Edit interNalWeightage Details");
		  $(".k-grid-update").text("update");  
		}
	}
	
	
	function healthList(container, options) {
		$(
				'<input name="health" id="health" data-text-field="health" required="true" validationmessage="Action is required" data-value-field="health" data-bind="value:' + options.field + '"/>')
				.appendTo(container).kendoComboBox({
					autoBind : false,
					placeholder : "Select Health",
					dataSource : {
						transport : {
							read : "${getAllHealthList}"
						}
					},
					
					
				 change :function(e) {
				     value = this.value();
				    if (this.value() && this.selectedIndex == -1) {                    
		                 alert(" doesn't exist!");
		                 $("#approvalAction").data("kendoComboBox").value("");
		                 
		          } 
				   
				    }
				    
				    	
				 
				});

		$('<span class="k-invalid-msg" data-for="approvalAction"></span>')
				.appendTo(container);
	}
	
	function metricsList(container, options) {
		$('<input name="metric" data-text-field="metric" data-value-field="metric"  data-bind="value:' + options.field + '" required="true"/>')
		.appendTo(container).kendoDropDownList({
			optionLabel : "Select",
			defaultValue : false,
			sortable : true,
			dataSource : {
				transport : {
					read : {
						url : "${getMetricsChoice}",
						type : "GET"
					}
				}
			}
		});
		$('<span class="k-invalid-msg" data-for="Metric"></span>').appendTo(container);
	}
	
	  //Validator Function for Accountss section
	 (function($, kendo) 
	  {
		 $.extend(true,kendo.ui.validator,
		 {
			rules : { 
		  		     
					  Ipvalidation : function(input,params) 
   				  { 
   					  if (input.attr("name") == "ipAddress") 
   					  {
 						     return $.trim(input.val()) !== "";
 						  }
 						  return true;
   		           },
   		        portvalidation : function(input,params) 
 				  { 
 					  if (input.attr("name") == "portNumber") 
 					  {
						     return $.trim(input.val()) !== "";
						  }
						  return true;
 		           }, 
 		           
 		          healthNameUniqueness : function(input,params) 
				   {    // alert("");
				        //check for the name attribute 
				        if (input.filter("[name='health']").length && input.val()) 
				        {
				          enterdService = input.val().toUpperCase(); 
				          for(var i = 0; i<res1.length; i++) 
				          {
				            if ((enterdService == res1[i].toUpperCase()) && (enterdService.length == res1[i].length) ) 
				            {								            
				              return false;								          
				            }
				          }
				         }
				         return true;
				    },
				    
				    maxValidation : function(input,params) 
					   {    // alert("");
					        //check for the name attribute 
					         enterdService = input.val().toUpperCase();
					        //alert(enterdService);
					        if (input.filter("[name='max']").length && input.val()) 
					        { 
					        	maximum=maximum+1;
					          enterdService = input.val().toUpperCase(); 
					          if(enterdService<maximum && maximum!=0)
					        	  {
					        	  return false;
					        	  }
					         
					         }
					        return true; 
					    },
 		           
 		           
			 },
			        minValidation:function(input,params)
			        {  
			        	
			        	if(input.attr("name") == "max")
			        		{
			        		//alert(value);
			        		}
			        	
			        },
					  messages : 
					  {
						  
						  Ipvalidation:"Ip Address is Required",						
						  portvalidation:"Port Number is required ",
						  healthNameUniqueness:"already exist",
						  maxValidation:"enter more than minimum value",
					  }
			
		 
				 });
		})(jQuery, kendo);
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