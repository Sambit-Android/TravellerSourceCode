<%@include file="/common/taglibs.jsp"%>

<!-- user urls -->
<c:url value="/department/getDepartmentNameFilter" var="getDepartmentNameUrl"/>
<c:url value="/users/getDesignation" var="designationUrl" />
<c:url value="/users/getRoles" var="rolesUrl" />

<c:url value="/users/create" var="createUrl" />
<c:url value="/users/read" var="readUrl" />
<c:url value="/users/update" var="updateUrl" />
<c:url value="/users/delete" var="destroyUrl" />
<c:url value="/users/readAllLoginNames" var="readAllLoginNames" />

<c:url value="/users/secondaryOff/read" var="readSecondaryOffUrl" />
<c:url value="/users/secondaryOff/createSecondaryofficeUrl" var="createSecondaryofficeUrl" />
<c:url value="/users/secondaryOff/updateSecondaryOffUrl" var="updateSecondaryOffUrl" />
<c:url value="/users/secondaryOff/destroySecondaryOffUrl" var="destroySecondaryofficeUrl" />
<c:url value="/users/readAllContactNumbers" var="readAllContactNumbers" />
<c:url value="/users/readAllEmailAddress" var="readAllEmailAddress" />

<c:url value="/users/upload/userImage" var="userImage" />
<c:url value="/projecttree/json/read" var="transportReadUrlCat" />
<c:url value="/users/filter" var="commonFilterForUsersUrl" />
<c:url value="/users/userStatusFilter" var="userStatusFilter" />
<c:url value="/users/commonFilterForDepartmentNameUrl" var="commonFilterForDepartmentNameUrl" />
<c:url value="/users/commonFilterForDesignationNameUrl" var="commonFilterForDesignationNameUrl" />
<c:url value="/users/commonFilterForCompanyUrl" var="commonFilterForCompanyUrl" />
<c:url value="/users/commonFilterForProjectUrl" var="commonFilterForProjectUrl" />
<c:url value="/users/commonFilterForOfficeUrl" var="commonFilterForOfficeUrl" />

<script>
	var role_win;
	var selectedPrimaryRoleIds;
	var selectedPrimaryRoleIds1="";
	var selectedSecondaryRoleIds="";
	var officeId="";
	var roleMultiValues;
	var onChangeRoleIds;
	var projectId;
	var nodeid = "";
	var nodeid1="";
	var nodeProjectId = "";

	var name;
	var loginName;
	var contactNo;
	var alternateContactNo;
	var mailId;
	var alternateMailId;
	var officeId1=0;
	var projectHeirarchyId = "";
	var projectHeirarchyId1="";
</script>


<!-- MAIN CONTENT -->
<div id="content">

	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i> Manage User
			</h1>
		</div>
	</div>

	<!-- KENDO GRID  -->
	<kendo:grid name="usergrid" change="onChangeUser" edit="userEvent" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" scrollable="true" filterable="true" groupable="true">
		<kendo:grid-pageable pageSizes="true" buttonCount="5" pageSize="5" input="true" numeric="true"></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
	    	<kendo:grid-filterable-operators>
			 	<kendo:grid-filterable-operators-string eq="Is equal to" contains="Contains"/>
	    	</kendo:grid-filterable-operators>
	  	</kendo:grid-filterable>
		<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this Concierge Vendor Service?" />
		<kendo:grid-toolbar> 
			<kendo:grid-toolbarItem name="create" text="Add User" />
			<kendo:grid-toolbarItem template="<a class='k-button' href='\\#' onclick=clearFilterUsers()>Clear Filter</a>"/>
			<kendo:grid-toolbarItem template="<a class='k-button' href='\\#' onclick=activateAllUsers()>Activate All</a>"/>
			<kendo:grid-toolbarItem template="<a class='k-button' href='\\#' onclick=deactivateAllUsers()>De-Activate All</a>"/>
		</kendo:grid-toolbar>
		<kendo:grid-columns>
			
			<kendo:grid-column title="Image" hidden="true" field="image" template="<span onclick='clickToUploadImage()' title='Click to Upload Image' ><img src='./users/getUserimage/#=urId#' id='myImages_#=urId#' alt='No Image to Display' width='80px' height='80px'/></span>" filterable="false" width="94px" sortable="false" />

			<kendo:grid-column title="Title&nbsp;*" field="title" width="60px" editor="titleEditor" hidden="true" />
			<kendo:grid-column title="Name&nbsp;*" field="urName" filterable="true" width="130px">
			<kendo:grid-column-filterable >
				<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter User Name",
											dataSource : {
												transport : {
													read : "${commonFilterForUsersUrl}/urName"
												}
											}
										});
							}
						</script>
				</kendo:grid-column-filterable-ui>
			</kendo:grid-column-filterable>			
			</kendo:grid-column>
			
			<kendo:grid-column title="Login&nbsp;Name&nbsp;*" field="urLoginName" width="110px">
			<kendo:grid-column-filterable >
				<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter Login Name",
											dataSource : {
												transport : {
													read : "${commonFilterForUsersUrl}/urLoginName"
												}
											}
										});
							}
						</script>
				</kendo:grid-column-filterable-ui>
			</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Contact&nbsp;Number&nbsp;*" field="urContactNo" width="140px">
			<kendo:grid-column-filterable >
				<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter Contact No Name",
											dataSource : {
												transport : {
													read : "${commonFilterForUsersUrl}/urContactNo"
												}
											}
										});
							}
						</script>
				</kendo:grid-column-filterable-ui>
			</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Email&nbsp;Id&nbsp;*" field="urEmailId" width="110px">
			<kendo:grid-column-filterable >
				<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter Email Id",
											dataSource : {
												transport : {
													read : "${commonFilterForUsersUrl}/urEmailId"
												}
											}
										});
							}
						</script>
				</kendo:grid-column-filterable-ui>
			</kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Permanent&nbsp;Address&nbsp;*" field="urPermanent" width="160px" editor="permAddressEditor">
			<kendo:grid-column-filterable >
				<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											placeholder : "Enter Address",
											dataSource : {
												transport : {
													read : "${commonFilterForUsersUrl}/urPermanent"
												}
											}
										});
							}
						</script>
				</kendo:grid-column-filterable-ui>
			</kendo:grid-column-filterable>
			</kendo:grid-column>
						
			<kendo:grid-column title="Department&nbsp;*" field="deptName" width="110px" >
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForDepartmentNameUrl}"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   </kendo:grid-column-filterable>
			</kendo:grid-column>
			<kendo:grid-column title="Department&nbsp;*" field="deptId" width="110px" editor="departmentEditor" hidden="true" />	
			<kendo:grid-column title="Designation&nbsp;*" field="dnName" width="110px" >
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForDesignationNameUrl}"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   </kendo:grid-column-filterable>
			</kendo:grid-column>
			<kendo:grid-column title="Designation&nbsp;*" field="dnId" width="110px" editor="designationEditor" hidden="true" />
			
			<%-- <kendo:grid-column title="Office&nbsp;Name&nbsp;*" field="officeName" width="160px" filterable="true">
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
			</kendo:grid-column> --%>
			<%-- <kendo:grid-column title="OfficeId" field="OfficeId" width="120px"	hidden="true"/> --%>
			
			<%-- <kendo:grid-column title="Project&nbsp;Name" field="projectName" width="120px" >
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForProjectUrl}"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   </kendo:grid-column-filterable>
			</kendo:grid-column>
			
			<kendo:grid-column title="Company&nbsp;Name" field="companyName" width="130px" >
			<kendo:grid-column-filterable >
					<kendo:grid-column-filterable-ui >
						<script>
							function ledgerStatusFilter(element) {
								element.kendoAutoComplete({
											dataSource : {
												transport : {
													read : "${commonFilterForCompanyUrl}"
												}
											}
										});
							}
						</script>
					</kendo:grid-column-filterable-ui>
		   </kendo:grid-column-filterable>
			</kendo:grid-column> --%>
			
			<%-- <kendo:grid-column title="Select&nbsp;Office&nbsp;*" field="category2" width="100px" hidden="true" editor="catEditor" /> --%>
		     
		    <%-- <kendo:grid-column title="Primary&nbsp;Role&nbsp;*" field="editPrimaryRole" width="120px" hidden="true" />
		    <kendo:grid-column title="Primary&nbsp;Role&nbsp;*" field="rlName" width="150px" editor="primaryRoleEditor" filterable="false"/>	 --%> 
			<kendo:grid-column title="Status" field="userStatus" template="#:userStatus == 0 ? 'In Active' : 'Active' #" width="100px">
			<kendo:grid-column-filterable>
					<kendo:grid-column-filterable-ui>
						<script type="text/javascript">
						function employeeStatusFilter(element){
							element.kendoComboBox({
								autoBind : true,
								 filter:"startswith",
								dataTextField : "userStatus",
								dataValueField : "statusId", 
								dataSource : {
									transport : {		
										read :  "${userStatusFilter}"
									}
								} 
							});
					   	}
						</script>
					</kendo:grid-column-filterable-ui>	
				</kendo:grid-column-filterable>
			</kendo:grid-column>	
			<kendo:grid-column title="&nbsp;" width="100px">
				<kendo:grid-column-command>
					<kendo:grid-column-commandItem name="edit" />
					
				</kendo:grid-column-command>
			</kendo:grid-column>
			<kendo:grid-column title="" template="<a href='\\\#' id='temPID' class='k-button k-button-icontext btn-destroy k-grid-Activate#=data.urId#'>#= data.userStatus == 1 ? 'Inactivate' : 'Activate' #</a>"	width="120px" />
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-create url="${createUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json" />
				<kendo:dataSource-transport-update url="${updateUrl}" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-destroy url="${destroyUrl}" dataType="json" type="POST" contentType="application/json" />
			</kendo:dataSource-transport>
			<kendo:dataSource-schema>
				<kendo:dataSource-schema-model id="urId">
					<kendo:dataSource-schema-model-fields>
						<kendo:dataSource-schema-model-field name="urId" editable="false" type="Number" />
						<kendo:dataSource-schema-model-field name="title" defaultValue="Mr" />
						<kendo:dataSource-schema-model-field name="urName"/>
						<kendo:dataSource-schema-model-field name="urLoginName"/>
						<kendo:dataSource-schema-model-field name="urContactNo"></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="urEmailId" type="email"></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="urTempAddress" />
						<kendo:dataSource-schema-model-field name="urPermanent" />
						<%-- <kendo:dataSource-schema-model-field name="OfficeId" type="string" /> --%>
						<kendo:dataSource-schema-model-field name="userStatus" type="number" />						
						<kendo:dataSource-schema-model-field name="selectedPrimaryRoleIds" type="string" />
						<kendo:dataSource-schema-model-field name="rlName"><kendo:dataSource-schema-model-field-validation   /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="deptId" type="Number"><kendo:dataSource-schema-model-field-validation /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="dnId" type="Number"><kendo:dataSource-schema-model-field-validation /></kendo:dataSource-schema-model-field>
						<kendo:dataSource-schema-model-field name="createdBy" />
						<kendo:dataSource-schema-model-field name="lastUpdatedBy" />
						<kendo:dataSource-schema-model-field name="lastUpdatedDt" type="date"><kendo:dataSource-schema-model-field-validation /></kendo:dataSource-schema-model-field>
					</kendo:dataSource-schema-model-fields>
				</kendo:dataSource-schema-model>
			</kendo:dataSource-schema>
		</kendo:dataSource>
	</kendo:grid>

	 
<%-- <kendo:grid-detailTemplate id="userTemplate">
	<kendo:tabStrip name="tabStrip_#=urId#">
	<kendo:tabStrip-animation>
				
	</kendo:tabStrip-animation>
	<kendo:tabStrip-items>
	<kendo:tabStrip-item text="Secondary Office Details" selected="true">
	<kendo:tabStrip-item-content>
	<div class="wethear" style="width: 45%;">
	<kendo:grid name="officegrid_#=urId#" pageable="true" resizable="true" sortable="true" reorderable="true" selectable="true" filterable="true" scrollable="true"  change="onSecondaryChange" edit="secondaryOfficeEditor">
			<kendo:grid-pageable pageSize="10"></kendo:grid-pageable>
			<kendo:grid-filterable extra="false">
	    <kendo:grid-filterable-operators>
		 <kendo:grid-filterable-operators-string eq="Is equal to"/>
	    </kendo:grid-filterable-operators>
	  </kendo:grid-filterable>
				
	 <kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?" />
	   <kendo:grid-toolbar>
		  <kendo:grid-toolbarItem name="create" text="Add secondary office Details" />
		  <kendo:grid-toolbarItem text="Clear_Filter"/>
	   </kendo:grid-toolbar>
	  
		<kendo:grid-columns>
		         <kendo:grid-column title="Secondary Role*" field="editSecondaryRole" width="120px" hidden="true" />
		          <kendo:grid-column title="Secondary Role*" field="secRlName" width="150px" editor="secondaryRoleEditor" filterable="false"/>	  
		          
				
			<kendo:grid-column title="Secondary Office&nbsp;Name&nbsp;*" field="secondaryOffice"	width="160px" filterable="false"/>
		<kendo:grid-column title="OfficeId" field="secondaryOfficeId" width="120px"	hidden="true"/>
		<kendo:grid-column title="Select&nbsp;Office&nbsp;*" field="category2" width="100px" hidden="true" editor="catEditor" />
			
			<kendo:grid-column title="&nbsp;" width="50px">
			   <kendo:grid-column-command>
				  <kendo:grid-column-commandItem name="edit"/>	
				  <kendo:grid-column-commandItem name="destroy" />		
			   </kendo:grid-column-command>
			</kendo:grid-column>	
					
		</kendo:grid-columns>
		<kendo:dataSource requestEnd="onSecondaryRequestEnd">
			<kendo:dataSource-transport>
				<kendo:dataSource-transport-create url="${createSecondaryofficeUrl}/#=urId#" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-read url="${readSecondaryOffUrl}/#=urId#" dataType="json" type="POST" contentType="application/json" />
				 <kendo:dataSource-transport-update url="${updateSecondaryOffUrl}/#=urId#" dataType="json" type="GET" contentType="application/json" />
				<kendo:dataSource-transport-destroy url="${destroySecondaryofficeUrl}/#=urId#" dataType="json" type="GET" contentType="application/json" /> 
			</kendo:dataSource-transport>
			<kendo:dataSource-schema>
				<kendo:dataSource-schema-model id="secondaryRoleId">
					<kendo:dataSource-schema-model-fields>
					 <kendo:dataSource-schema-model-field name="secondaryRoleId" type="number"/>
					 <kendo:dataSource-schema-model-field name="urId" type="number"/>
					<kendo:dataSource-schema-model-field name="secondaryOfficeId" type="string" /> 
					  <kendo:dataSource-schema-model-field name="selectedSecondaryRoleIds" type="string" />
					<kendo:dataSource-schema-model-field name="rlName" type="string" />  
					
						
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
	</kendo:grid-detailTemplate> --%>
</div>
<div id="alertsBox" title="Alert"></div>
<script>

function clearFilterUsers()
{
   $("form.k-filter-menu button[type='reset']").slice().trigger("click");
   var gridStoreIssue = $("#usergrid").data("kendoGrid");
   gridStoreIssue.dataSource.read();
   gridStoreIssue.refresh();
}

var glb ="";
var SelectedRowId = "";
var res1 = new Array();
var res2 = new Array();
var res3 = new Array();
var enterdLoginName = "";
var glbal="";

function onChangeUser(arg) {
	var gview = $("#usergrid").data("kendoGrid");
	var selectedItem = gview.dataItem(gview.select());
	SelectedRowId = selectedItem.urId;
}
	
function userEvent(e) {
	
	//e.model.set("OfficeId", nodeid);
	
	$(".k-edit-form-container").css({
		"width" : "700px"
	});
	$(".k-window").css({
		"top": "150px"
	});
	$('.k-edit-label:nth-child(12n+1)').each(
			function(e) {
				$(this).nextAll(':lt(11)').addBack().wrapAll(
						'<div class="wrappers"/>');
			});
	$(".k-window").css({ "position" : "fixed" });
	$(".wrappers").css({ "display" : "inline", "float" : "left", "width" : "350px", "padding-top" : "10px" });

	$('label[for="image"]').closest('.k-edit-label').remove();
	$('label[for="undefined"]').closest('.k-edit-label').remove();
	$('div[data-container-for="image"]').remove();
	$('label[for="selectedPrimaryRoleIds"]').closest('.k-edit-label').remove();
	$('div[data-container-for="selectedPrimaryRoleIds"]').remove();
	$('label[for="userStatus"]').closest('.k-edit-label').remove();
	$('div[data-container-for="userStatus"]').remove();
	$('label[for="deptName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="deptName"]').remove();
	$('label[for="dnName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="dnName"]').remove();
	 $('label[for="OfficeId"]').parent().hide();
	$('div[data-container-for="OfficeId"]').hide();
	 $('label[for="officeName"]').parent().hide();
	$('div[data-container-for="officeName"]').hide(); 
	$('label[for="projectName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="projectName"]').remove();
	$('label[for="companyName"]').closest('.k-edit-label').remove();
	$('div[data-container-for="companyName"]').remove();
	$('label[for="fileName"]').parent().hide();
	$('div[data-container-for="fileName"]').hide(); 

	if (e.model.isNew()) {
		//e.model.set("OfficeId", nodeid);
		securityCheckForActions("./userManagement/users/createButton");
		$(".k-window-title").text("Add User");
		$('label[for="editPrimaryOffice"]').closest('.k-edit-label').remove();
		$('div[data-container-for="editPrimaryOffice"]').remove();
		$('label[for="editPrimaryRole"]').closest('.k-edit-label').remove();
		$('div[data-container-for="editPrimaryRole"]').remove();
		$(".k-window-title").text("Add User Details");
		$(".k-grid-update").text("Save");
		$(".k-edit-field").each(function() {
			$(this).find("#temPID").parent().remove();
		});
		/*  $(".k-grid-create").click(function() {
			e.model.set("OfficeId", nodeid);
		});  */
		
		 res1 = [];
		 $.ajax
		 ({
		      type : "GET",
			  dataType:"text",
			  url : '${readAllLoginNames}',
			  dataType : "JSON",
			  success : function(response) 
			  {
				 for(var i = 0; i<response.length; i++) 
				 {
				   res1[i] = response[i];	
			     }
		      }
		 });
		 
		 res2 = [];
		 $.ajax
		 ({
		      type : "GET",
			  dataType:"text",
			  url : '${readAllContactNumbers}',
			  dataType : "JSON",
			  success : function(response) 
			  {
				 for(var i = 0; i<response.length; i++) 
				 {
				   res2[i] = response[i];	
			     }
		      }
		 });
		 
		 res3 = [];
		 $.ajax
		 ({
		      type : "GET",
			  dataType:"text",
			  url : '${readAllEmailAddress}',
			  dataType : "JSON",
			  success : function(response) 
			  {
				 for(var i = 0; i<response.length; i++) 
				 {
				   res3[i] = response[i];	
			     }
		      }
		 });
		
		 
			$(".k-grid-update").click(function() {
				//e.model.set("OfficeId", nodeid);
				e.model.set("selectedPrimaryRoleIds", glbal);
			}); 
			
	} else {
		//securityCheckForActions("./userManagement/users/updateButton");
		$(".k-window-title").text("Edit User Details");
		$(".k-grid-update").text("Update");
		
		
		var gview = $("#usergrid").data("kendoGrid");
		  var selectedItem = gview.dataItem(gview.select());
		  var urLoginName= selectedItem.urLoginName;
		  var urContactNo= selectedItem.urContactNo;
		  var urEmailId= selectedItem.urEmailId;
		  
		  var officehierarchyId=selectedItem.OfficeId;
		  
		  
		  res1 = [];
		   $.ajax({
		    type : "GET",
		    dataType : "text",
		    url : '${readAllLoginNames}',
		    dataType : "JSON",
		    success : function(response) {
		     var j = 0;
		     for (var i = 0; i < response.length; i++) {
		      if (response[i] != urLoginName) {

		       res1[j] = response[i];
		       j++;
		      }
		     }
		    }
		   });
		   
		   
		   
		   res2 = [];
		   $.ajax({
		    type : "GET",
		    dataType : "text",
		    url : '${readAllContactNumbers}',
		    dataType : "JSON",
		    success : function(response) {
		     var j = 0;
		     for (var i = 0; i < response.length; i++) {
		      if (response[i] != urContactNo) {

		       res2[j] = response[i];
		       j++;
		      }
		     }
		    }
		   });
		   
		   
		   
		   res3 = [];
		   $.ajax({
		    type : "GET",
		    dataType : "text",
		    url : '${readAllEmailAddress}',
		    dataType : "JSON",
		    success : function(response) {
		     var j = 0;
		     for (var i = 0; i < response.length; i++) {
		      if (response[i] != urEmailId) {

		       res3[j] = response[i];
		       j++;
		      }
		     }
		    }
		   });
		   
		   
		$('div[data-container-for="urLoginName"]').hide();
		$('label[for="urLoginName"]').parent().hide();
		
		$('div[data-container-for="category2"]').remove();
		$('label[for="category2"]').remove();
		
		
		
		

		$('label[for="primaryOffice"]').closest('.k-edit-label').remove();
		$('div[data-container-for="primaryOffice"]').remove();
		$('label[for="rlName"]').closest('.k-edit-label').remove();
		$('div[data-container-for="rlName"]').remove();
		

		
		$('[name="editPrimaryRole"]').attr("disabled", true);
	//	$('[name="editPrimaryOffice"]').attr("disabled", true);
		$('div[data-container-for="editPrimaryOffice"]').append("<input type='button' id='saveButton' title='Edit' value='...' onclick='editPrimaryOffice()'/>");
		$(".k-edit-field").each(function() {
			$(this).find("#temPID").parent().remove();
		});

		$(".k-grid-update").click(function() {
			//e.model.set("OfficeId", officehierarchyId);
		}); 
	}
	

}
	
function onRequestEnd(e) {
	if (typeof e.response != 'undefined') {
		if (e.type == "create") {
			$("#alertsBox").html("");
			$("#alertsBox").html("User record created successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $("#usergrid").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		if (e.type == "update") {
			$("#alertsBox").html("");
			$("#alertsBox").html("User record updated successfully");
			$("#alertsBox").dialog({
				modal : true,
				buttons : {
					"Close" : function() {
						$(this).dialog("close");
					}
				}
			});
			var grid = $("#usergrid").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		}
		if (e.response.status == "AciveUserDestroyError") {
  			$("#alertsBox").html("");
  			$("#alertsBox").html("Active User Cannot be Deleted");
  			$("#alertsBox").dialog({
  				modal : true,
  				buttons : {
  					"Close" : function() {
  						$(this).dialog("close");
  					}
  				}
  			});
  			var grid = $("#usergrid").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
  		return false;
  	    }
		
		if (e.response.status == "CHILD") {
  			$("#alertsBox").html("");
  			$("#alertsBox").html("Can't delete User, child record found");
  			$("#alertsBox").dialog({
  				modal : true,
  				buttons : {
  					"Close" : function() {
  						$(this).dialog("close");
  					}
  				}
  			});
  			var grid = $("#usergrid").data("kendoGrid");
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
			var grid = $("#usergrid").data("kendoGrid");
			grid.dataSource.read();
			grid.refresh();
		} 
	}
}
	
function titleEditor(container, options) {
	var data = [ {
		text : "Mr",
		value : "Mr"
	}, {
		text : "Mrs",
		value : "Mrs"
	}, {
		text : "Miss",
		value : "Miss"
	}, {
		text : "Ms",
		value : "Ms"
	}, {
		text : "M/S",
		value : "M/S"
	} ];

	$(
			'<input data-text-field="text" data-value-field="value" data-bind="value:' + options.field + '"/>')
			.appendTo(container).kendoDropDownList({
				dataTextField : "text",
				dataValueField : "value",
				placeholder : "Select ",
				dataSource : data
			});
}	
	
function tempAddressEditor(container, options) {
	$(
			'<textarea data-text-field="urTempAddress" data-value-field="urTempAddress" data-bind="value:' + options.field + '" style="width: 148px; height: 46px;" placeholder=" " "/>')
			.appendTo(container);
}

function permAddressEditor(container, options) {
	$('<textarea name="urPermanent" data-text-field="urPermanent" data-value-field="urPermanent" data-bind="value:' + options.field + '" style="width: 148px; height: 46px;" placeholder=" " />')
			.appendTo(container);
	  $('<span class="k-invalid-msg" data-for="urPermanent"></span>').appendTo(container);  
}
function departmentEditor(container, options) {
	$('<input name = "departmant" data-text-field="deptName" required="true" validationMessage="Department is required" id="deptId" data-value-field="deptId" data-bind="value:' + options.field + '"  />')
			.appendTo(container).kendoDropDownList({
				optionLabel : "Select",
				dataSource : {
					transport : {
						read : "${getDepartmentNameUrl}"
					}
				}
			});
	$('<span class="k-invalid-msg" data-for="departmant"></span>')
			.appendTo(container);
}

function designationEditor(container, options) {
	$(
			'<input name="designation" data-text-field="dnName" required="true" validationMessage="Designation is required" id = "dnId" data-value-field="dnId" data-bind="value:' + options.field + '"  />')
			.appendTo(container).kendoDropDownList({
				optionLabel : "Select",
				cascadeFrom : "deptId",
				dataSource : {
					transport : {

						read : "${designationUrl}"

					}
				}
			});
	$('<span class="k-invalid-msg" data-for="designation"></span>')
			.appendTo(container);
}
/* 
function primaryRoleEditor(container, options) {
	var model = options.model;
	model.jobAssets = model.jobAssetsDummy;
	$('<select multiple="multiple" id="roleId" name="roles" required="true" validationMessage="Roles is required" data-text-field="rlName" style="width:180px;" data-value-field="rlId" data-bind="value:' + options.field + '" />')
			.appendTo(container).kendoMultiSelect({
				optionLabel : {
					rlName : "Select",
					rlId : "",
				},
				cascadeFrom : "dnId",
				dataSource : {
					transport : {
						read : {
							url : "./users/getRoles",
							dataType : "json",
							type : 'GET'
						}
					},
					dataTextField : "rlName",
					dataValueField : "rlId",
				},
				change : onChangePrimaryRole,
			});
	$('<span class="k-invalid-msg" data-for="roles"></span>')	.appendTo(container);
}
function onChangePrimaryRole(e) {

	var multiSelect = $("#roleId").data("kendoMultiSelect");
	multiSelect.dataSource.filter({});

	var firstItem = $('#usergrid').data().kendoGrid.dataSource.data()[0];
	glbal = multiSelect.value();
	//firstItem.set('selectedPrimaryRoleIds', multiSelect.value());
	
	
} */

function catEditor(container, options) {
	

	$('<div style="max-height: 250px; overflow: auto;  border:1px solid red; border-radius:7px; "></div>')
			.appendTo(container)
			.kendoTreeView(
					{
						dataTextField : "text",
						template : " #: item.text # <input type='hidden' id='hiddenId' class='#: item.text ##: item.id#' value='#: item.id#'/>",
						name : "treeview",
						select : onTreeSelect,
						loadOnDemand : true,
						dataSource : {
							transport : {
								read : {
									url : "${transportReadUrlCat}",
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

function onTreeSelect() {
	nodeid= $('.k-state-hover').find('#hiddenId').val();
	nodeid1= $('.k-state-hover').find('#hiddenId').val();
} 


$("#usergrid").on("click","#temPID",function(e) {
	var button = $(this), enable = button.text() == "Activate";
	var widget = $("#usergrid").data("kendoGrid");
	var dataItem = widget.dataItem($(e.currentTarget)
			.closest("tr"));
	var result = securityCheckForActionsForStatus( "./userManagement/users/statusButton" );
	if (result == "success") {
		if (enable){
			$.ajax({
					type : "POST",
					dataType : "text",
					url : "./users/userStatus/"+ dataItem.id + "/activate",
					dataType : 'text',
					success : function(response) {
							$("#alertsBox").html("");
							$("#alertsBox").html(response);
							$("#alertsBox")
									.dialog(
											{
												modal : true,
												buttons : {
													"Close" : function() {
														$(
																this)
																.dialog(
																		"close");
													}
												}
											});
							button.text('ppp');
							$('#usergrid')
									.data('kendoGrid').dataSource
									.read();
						}
					});
		} else {
			$
					.ajax({
						type : "POST",
						dataType : "text",
						url : "./users/userStatus/"
								+ dataItem.id
								+ "/deactivate",
						success : function(response) {
							$("#alertsBox").html("");
							$("#alertsBox").html(response);
							$("#alertsBox")
									.dialog(
											{
												modal : true,
												buttons : {
													"Close" : function() {
														$(
																this)
																.dialog(
																		"close");
													}
												}
											});
							button.text('Activate');
							$('#usergrid')
									.data('kendoGrid').dataSource
									.read();
						}
					});
		}
	}

});
//child grid----------------------------------------------------------------

var selectedItem1="";
var SelectedRowId1="";
function onSecondaryChange()
	{
		var gview = $("#officegrid_"+SelectedRowId).data("kendoGrid");
		 selectedItem1 = gview.dataItem(gview.select());
		SelectedRowId1 = selectedItem1.secondaryRoleId;
		//alert(SelectedRowId1);
		
	
	} 

function secondaryOfficeEditor(e)
{   
	
	if (e.model.isNew())
	{
		
		/* $(".k-grid-create").click(function() {
			e.model.set("secondaryOfficeId", nodeid1);
		}); */
		$(".k-grid-create").click(function() {
			//e.model.set("selectedSecondaryRoleIds",selectedSecondaryRoleIds);
		}); 
		
	$('label[for="fileName1"]').parent().hide();
	$('div[data-container-for="fileName1"]').hide();
	$('label[for="secondaryOfficeId"]').parent().hide();
	$('div[data-container-for="secondaryOfficeId"]').hide();
	$('label[for="editSecondaryRole"]').closest('.k-edit-label').remove();
	$('div[data-container-for="editSecondaryRole"]').remove();
	$('label[for="secondaryOffice"]').closest('.k-edit-label').remove();
	$('div[data-container-for="secondaryOffice"]').remove();
	 $(".k-window-title").text("Add secondary office Details");
	  $(".k-grid-update").text("Save");

	}
	
	else
		
	{
		$('label[for="fileName1"]').parent().hide();
		$('div[data-container-for="fileName1"]').hide(); 
		$('label[for="secondaryOfficeId"]').parent().hide();
		$('div[data-container-for="secondaryOfficeId"]').hide();
		$('label[for="editSecondaryRole"]').closest('.k-edit-label').remove();
		$('div[data-container-for="editSecondaryRole"]').remove();
		$('label[for="secondaryOffice"]').closest('.k-edit-label').remove();
		$('div[data-container-for="secondaryOffice"]').remove();
		$(".k-grid-update").click(function() {
			//e.model.set("selectedSecondaryRoleIds",selectedSecondaryRoleIds);
			//alert(nodeid1);
			e.model.set("secondaryOfficeId", nodeid1);
			//e.model.set("selectedSecondaryRoleIds",selectedSecondaryRoleIds);
		});
		
	
		$(".k-window-title").text("Edit secondary office Details");
		 $(".k-grid-update").text("Update");
		 
	}
	
	$(".k-grid-update").click(function() {
		e.model.set("selectedSecondaryRoleIds", glb);
		e.model.set("secondaryOfficeId", nodeid1);
		
		
	});
	

	
}

function secondaryRoleEditor(container, options) {
	var model = options.model;
	model.jobAssets = model.jobAssetsDummy;
	$('<select multiple="multiple" id="secId" name="Roles" data-text-field="rlName" style="width:180px;" data-value-field="rlId" data-bind="value:' + options.field + '" />')
			.appendTo(container).kendoMultiSelect({
				optionLabel : {
					rlName : "Select",
					rlId : "",
				},
				dataSource : {
					transport : {
						read : {
							url : "./users/getRoles",
							dataType : "json",
							type : 'GET'
						}
					},
					dataTextField : "rlName",
					dataValueField : "rlId",
				},
				change : onChangeSecondaryRole,
			});

}

function onChangeSecondaryRole(e) {
	
	

	var multiSelect = $("#secId").data("kendoMultiSelect");
	multiSelect.dataSource.filter({});

	//alert(SelectedRowId);
	var firstItem = $("#officegrid_"+ SelectedRowId).data().kendoGrid.dataSource.data()[0];
	glb = multiSelect.value();
	
	//firstItem.set('selectedSecondaryRoleIds', multiSelect.value());
		
	
}




	function onSecondaryRequestEnd(e) {
		
			if (e.type == "create") {
				alert("Added successfully");
				var grid = $("#usergrid").data(
				"kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			}
			if (e.type == "update") {
				alert("Added successfully");
				var grid = $("#usergrid").data(
				"kendoGrid");
				grid.dataSource.read();
				grid.refresh();
			}
		
	}	
	
	//Validator Function
	 (function($, kendo) 
	  {
		 $.extend(true,kendo.ui.validator,
		 {
			rules : { 
				
  		              loginNameUniqueness : function(input,params) {
					        if (input.filter("[name='urLoginName']").length && input.val())  {
					          enterdService = input.val().toUpperCase(); 
					          for(var i = 0; i<res1.length; i++) {
					            if ((enterdService == res1[i].toUpperCase()) && (enterdService.length == res1[i].length) ) {								            
					              return false;								          
					            }
					          }
					         }
					         return true;
					    },
					    urPermanentValidation : function(input, params) {
							if (input.attr("name") == "urPermanent") {
								return $.trim(input.val()) !== "";
							}
							return true;

						},
						urPermanentLengthValidation : function(input, params) {
							if (input.filter("[name='urPermanent']").length
									&& input.val() != "") {
								return /^[\s\S]{1,500}$/.test(input.val());
							}
							return true;
						},
						NameMinvalidation : function(input, params) { 
							 if (input.filter("[name='urName']").length && input.val()) {
								 return /^[\s\S]{3,45}$/.test(input.val());
							 }
					    return true;
					    },  
					    Namevalidation : function(input, params) { 
							 if (input.filter("[name='urName']").length && input.val()) {
								 return /^[a-zA-Z0-9_. ]*$/.test(input.val());
							 }
					    return true;
					    },  
						NameNullValidator : function(input,params) { 
		    					  if (input.attr("name") == "urName") {
		  						     return $.trim(input.val()) !== "";
		  						  }
	 					 return true;
  		         		},
						PrimaryContactNullValidator : function(input,params) { 
	    					  if (input.attr("name") == "urContactNo") {
	  						     return $.trim(input.val()) !== "";
	  						  }
		  			    return true;
 		        	   
		    		    },
		    		    PrimaryContactValidator : function(input,params)  { 
	    					  if (input.attr("name") == "urContactNo") {
	  						     return /^(\+91-|\+91|0|9)?\d{10}$/.test(input.val());
	  						  }
	  				    return true;
	    		        },    		            
   		            PrimaryEmailNullValidator : function(input,params) { 
	    					  if (input.attr("name") == "urEmailId") {
	  						     return $.trim(input.val()) !== "";
	  						  }
	  				    return true;
	    		        }, 
	    		      
 		                PrimaryEmailValidator : function(input,params) { 
	    					  if (input.attr("name") == "urEmailId") {
	  						     return /^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-zA-Z]{2,4})$/.test(input.val());
	  						  }
	  				    return true;
	    		        },
		         		urLoginNameNullValidator : function(input,params) { 
	    					  if (input.attr("name") == "urLoginName") {
	  						     return $.trim(input.val()) !== "";
	  						  }
	  				    return true;
	    		        },
	    		        urLoginNameValidator : function(input,params) { 
	    		        	 if (input.filter("[name='urLoginName']").length && input.val()) {
									return /^[a-zA-Z0-9_.@ ]*$/.test(input.val());
							 }
							return true;
	    		        },
	    		        urLoginNameMaxvalidation : function(input, params){ 
							 if (input.filter("[name='urLoginName']").length && input.val()) {
								return /^[\s\S]{3,45}$/.test(input.val());
							 }
							 return true;
						  },
	    		        PrimaryRoleNullValidator : function(input,params) { 
	    					  if (input.attr("name") == "rlName") {
	  						     return $.trim(input.val()) !== "";
	  						  }
	  				    return true;
	    		        }, 
	    		        contactNumberUniqueness : function(input,params) {
					        if (input.filter("[name='urContactNo']").length && input.val()) {
					         var enterdService = input.val().toUpperCase(); 
					          for(var i = 0; i<res2.length; i++) {
					            if ((enterdService == res2[i].toUpperCase()) && (enterdService.length == res2[i].length) ) 
					            {								            
					              return false;								          
					            }
					          }
					         }
					         return true;
						    },				    
					    primaryEmailUniqueness : function(input,params) {
					        if (input.filter("[name='urEmailId']").length && input.val()) {
					         var enterdService = input.val().toUpperCase(); 
					          for(var i = 0; i<res3.length; i++) {
					            if ((enterdService == res3[i].toUpperCase()) && (enterdService.length == res3[i].length) ) 
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
						 
						  loginNameUniqueness:"Login name already exist",					
						  contactNumberUniqueness: "Contact number already exists",
						  primaryEmailUniqueness:"Email Id already exists",
						  urPermanentValidation: "Permanent address required",
						  urPermanentLengthValidation: "Address field allows max 500 characters",
						  NameMinvalidation:"Only alphabets min 3 and max 45 letters allowed",
						  NameNullValidator:"Please Enter Name ",
						  PrimaryContactNullValidator:"Please Enter Mobile Number ",
						  PrimaryContactValidator:"Please Enter 10 Digit Mobile No",
						  PrimaryEmailNullValidator:"Please Enter Primary Mail",
						  PrimaryEmailValidator:"Please Enter Valid Mail",
						  urLoginNameNullValidator:"Please Enter Login Name ",
						  PrimaryRoleNullValidator:"Primary Role is Required",
						  urLoginNameValidator : "Login name can't allow special symbols except(_@.)",
						  urLoginNameMaxvalidation :"Login name  allow min 3 & max 45 letters",
						  Namevalidation:"Name can't allow special symbols except(_ .)",
						  
					  }
				 });
		})
      (jQuery, kendo);

	
	// Added By raju
	
	function activateAllUsers() {
		var actAll="Activate";
		
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "./users/StatusChangeAll/"+actAll,
			dataType : 'text',
			success : function(response) {
					$("#alertsBox").html("");
					$("#alertsBox").html("All users are activated successfully");
					$("#alertsBox").dialog({
										modal : true,
										buttons : {
										"Close" : function() {
												$(this).dialog("close");
											}
										}
									});
				
					$('#usergrid').data('kendoGrid').dataSource.read();
				}
			});
		
	}
	
function deactivateAllUsers() {
    var actAll="InActivate";
		
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "./users/StatusChangeAll/"+actAll,
			dataType : 'text',
			success : function(response) {
					$("#alertsBox").html("");
					$("#alertsBox").html("All users are deactivated successfully");
					$("#alertsBox")
							.dialog(
									{
										modal : true,
										buttons : {
											"Close" : function() {
												$(
														this)
														.dialog(
																"close");
											}
										}
									});
					
					$('#usergrid')
							.data('kendoGrid').dataSource
							.read();
				}
			});
		
	}
	
</script>
<style>	
	
	.k-edit-form-container {	
		text-align: left;
		position: relative;		
	}
	
	.wrappers {
		display: inline;
		float: left;
		width: 370px;	
	}
</style>
