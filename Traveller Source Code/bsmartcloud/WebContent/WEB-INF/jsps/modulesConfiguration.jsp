<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
 <script  type="text/javascript">
 

 
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableManaged.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	   		$('#meterData-Acquisition').addClass('start active ,selected');
   	    	   		$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  
   	    	 
   	    	  
   	    	});
  	    		
 
   function activateModule(actID){
	  var ckdmdls=[];
	   
	  
	  $("#"+actID).attr("checked","checked");
	  
	  
		 var i=0; 
		 
		 $(".mchx:checked").each(function(){
			var activated = $(this).val();
			ckdmdls[i++]=activated;
			//$("#"+activated).removeClass( "module-deactivated" ).addClass( "module-activated" );
			
			
			
		});
		 ckdmdls[ckdmdls.length]=actID;
		//alert("all="+ckdmdls);
		if(ckdmdls.length>0){
		$("#activateMdls").attr("value",ckdmdls);
		//alert($("#activateMdls").val());
		$("form#activeMdls").attr("action","./activeSelectedModules");
		$("form#activeMdls").submit();
		}else{
			
		 	$("#activateMdls").attr("value",null);
			//alert($("#activateMdls").val());
			$("form#activeMdls").attr("action","./activeSelectedModules");
			$("form#activeMdls").submit();
	 	}
  } 
  
 function activeSelectedModules(){
	 var ckdmdls=[];
	 var i=0; 
	 
	 $(".mchx:checked").each(function(){
		var activated = $(this).val();		
		ckdmdls[i++]=activated;		
	});
	 
	 $("#activateMdls").attr("value",ckdmdls);	
		$("form#activeMdls").attr("action","./activeSelectedModules");
		$("form#activeMdls").submit();
		
	/* if(ckdmdls.length>0){
	$("#activateMdls").attr("value",ckdmdls);	
	$("form#activeMdls").attr("action","./activeSelectedModules");
	$("form#activeMdls").submit();
	}else{
		
	 	$("#activateMdls").attr("value",null);
		//alert($("#activateMdls").val());
		$("form#activeMdls").attr("action","./activeSelectedModules");
		$("form#activeMdls").submit();
 	}
	return false; */
 }
 
 function deactiveModule(deactModId){
	 //alert("working=="+deactModId);
	 
	 var ckdmdls=[];
	 ckdmdls[ckdmdls.length] = deactModId;
	 //alert("id==="+ckdmdls[0]+"length==="+ckdmdls.length);
	 $("#activateMdls").attr("value",ckdmdls);
		//alert($("#activateMdls").val());
		$("form#activeMdls").attr("action","./deactiveModule");
		$("form#activeMdls").submit();
		
 }
 
 
 function changeAllModulesStatus(status){
	 $("#activateMdls").attr("value",status);
		//alert($("#activateMdls").val());
		$("form#activeMdls").attr("action","./updateAllModulesStatus");
		$("form#activeMdls").submit();
	 //$.post("./updateAllModulesStatus",{"status":status});
 }
 
 </script>
 
<div class="page-content" >
<%@include file="pagebreadcrum.jsp"%>
<div class="row">
				<div class="col-md-12">
						<c:if test="${not empty results}">
							<div class="alert alert-danger display-show">
								<button class="close" data-close="alert"></button>
								<span style="color: red">${results}</span>
							</div>
						</c:if>
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-user"></i>Menu List</div>
							
						</div>
						<div class="portlet-body">
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped table-bordered table-hover" id="sample_2">
								<thead>
									<tr>
										<th style="width1:8px;">
											<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="activeAllModules" name="chkall"/>
										</th>
										<th>Menu Name</th>
										<th >Status</th>
									</tr>
								</thead>
								<tbody>
										<c:forEach var="element" items="${mdmasters}">
										
											<tr class="odd gradeX">
												
												<td><input type="checkbox" class="checkboxes mchx" value="${element.id}" name='chk[]' id="${element.id}"
												 <c:if test="${element.status eq 'Active'}"><c:out value="checked='checked'"></c:out></c:if> />
											    </td>
												
												<td><c:out value="${element.moduleName}"></c:out></td>
												<td>
												<c:choose>
												<c:when test="${fn:toUpperCase(element.status) eq 'ACTIVE'}">
												<span class="label label-success">${fn:toUpperCase(element.status)}</span>
												</c:when>
												<c:otherwise>
												<span class="label label-warning">${fn:toUpperCase(element.status)}</span>
												</c:otherwise>
												</c:choose>
												
												</td>
												
											</tr>
											
										</c:forEach>
										
									
								</tbody>
							</table>
							
							</div>
							<form id="activeMdls" method="POST">
								<input type="hidden" name="activateMdls" value="" id="activateMdls"/>
							</form>
							<div class="modal-footer">
							<button id="updateOption" class="btn btn-success pull-right" value="" onclick="return activeSelectedModules();">update</button>
							</div>
							<!-- <div class="row col-md-18">
								<div class="col-sm-2"></div>
								<div class="col-sm-3">
									<span class="btn btn-primary btn-md" onclick="return activeSelectedModules()">Update</span>
								</div>
								
								<div class="col-sm-2" style="float:left" >
									<span class="btn btn-primary btn-md" id="Active" onclick="changeAllModulesStatus(this.id)">Activate All</span>
								</div>
								<div class="col-sm-2" style="float:left" >
									<span class="btn btn-default btn-md" id="In Active" onclick="changeAllModulesStatus(this.id)">De Activate All</span>
								</div>
								
							
							</div> -->
							
						</div>
							
						</div>
					</div>
					</div>
				</div>
			


</div>