<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
  $(".page-content").ready(function(){     
   	App.init();
   	TableEditable.init();
   	FormComponents.init();
   	$('#meterData-Acquisition').addClass('start active ,selected');
   	$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	var adminSdo='${sdocodeval}';
		   	  if(adminSdo!='noValue'&& adminSdo!='' )
		   		  {
		   		  $('#sdoCode').val(adminSdo);
		   		getMrmaster(adminSdo);
		   		  $("#sdoCode").attr("disabled",true);
		   		   
		   		  }
		   	$('#sdoCode').select2();	  
   	});
  function validation()
  {
	  if($('#sdoCode').val()==0)
		  {
		  bootbox.alert('please select Sdocode');
		  return false;
		  }
	  
	  if($('#mrCode').val()=='select')
		  {
		  bootbox.alert('please select Device operator code');
		  return false;
		  }
	  if($('#deviceid').val()=='select')
	  {
	  bootbox.alert('please select Device');
	  return false;
	  }
	  
	  $("#sdoCode").attr("disabled",false);
  }
  
  function removeDevAlloc(id)
  {
	  bootbox.confirm("Are you Sure to DeAllocate device ...!",function(result){
			if(result){
				$("#deviceidPk").val(id);
				$("#sprtForm").attr("action","./deAllocateDevice");
				$("#sprtForm").submit();
				
			}
		});
  }
  
  
  function getMrmaster(sdocode)
  {
	  
	  $.ajax({
	    	url:'./getMrmaster'+'/'+sdocode,
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	success:function(response)
	    	{
	    		var substation='';
	    		 substation+="<td>MRCODE</td><td><font color=red>*</font></td><td><select id='mrCode' name='mrCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select Mrcode</option>";
				for( var i=0;i<response.length;i++){
					substation+="<option  value='"+response[i].trim()+"'>"+response[i]+"</option>";
				}
				substation+="</select></td><span></span>";
				
				$("#mrcodeTr").html(substation);
				$('#mrCode').select2();
	    	}
	  }); 
	  getDevicesBySdoCode(sdocode);
	  
  }
  
  function getDevicesBySdoCode(sdocode)
  {
	  $.ajax({
	    	url:'./getDevicesBySdoCode'+'/'+sdocode,
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	success:function(response)
	    	{
	    		var substation='';
	    		 substation+="<td>DEVICE ID</td><td><font color=red>*</font></td><td><select id='deviceid' name='deviceid' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select deviceid</option>";
				for( var i=0;i<response.length;i++){
					
					substation+="<option  value='"+response[i].deviceid+"'>"+response[i].deviceid+"</option>";
				}
				substation+="</select></td><span></span>";
				
				$("#deviceidTr").html(substation);
				$('#deviceid').select2();
	    	}
	  }); 
	  
  }
  </script>
<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->


	<div class="row">
		<div class="col-md-12">
			<c:if test="${not empty results}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
					<fmt:parseDate value="${currentMonth}"  var="billMonth" pattern="yyyyMM"/>
						<i class="fa fa-edit"></i>MR Device Allocations 
						<c:if test="${userType eq 'admin_subdivision' || userType eq 'ADMIN_SUBDIVISION'}">
						<fmt:parseDate value="${currentMonth}"  var="billMonth" pattern="yyyyMM"/>
						&nbsp;&nbsp;&nbsp;<span class="label label-warning"><font size="3" color="black"><b>SDOCODE:-${sdocodeval}</b></font></span>&nbsp;<span class="label label-warning"><font size="3" color="black"><b>&nbsp;&nbsp;BILLMONTH:-<fmt:formatDate pattern="yyyy-MMM" value="${billMonth}"/></b></font></span>
						</c:if>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
					
						<div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="addData">
								Allocate Device <i class="fa fa-plus"></i>
							</button>
						</div>
						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
										to Excel</a></li>
							</ul>
						</div>
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
							    <th hidden="true">Id</th>
								<th>Device ID</th>
								<th>Device operator Code</th>
								<!-- <th>Device operator name</th> -->
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>SDO Code</th>
								<%--  <th>SECTION</th> 
								--%></c:if> 
								<c:if test="${userType eq 'admin_subdivision' || userType eq 'ADMIN_SUBDIVISION'}">
								<th>Consumers</th>
								</c:if>
								<th>DeAllocate</th>

							</tr>
						</thead>
						<tbody id="tableBody">
                        <c:set var="count" value="0" scope="page" />
							<c:forEach var="element" items="${mrdaList}">
								<tr>
								    <td hidden="true">${element.id}</td>
									<td>${element.deviceid}</td>
									<td>${element.mrCode}</td>
									<%-- <td>${element.mrMaster.mrName}</td> --%>
									<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									<td>${element.sdoCode}</td>
									<%-- <td>${element.siteLocationEntity.section}</td>  --%>
                                    </c:if>
                                    <c:if test="${userType eq 'ADMIN_SUBDIVISION' || userType eq 'ADMIN_SUBDIVISION'}">
                                    <td>${consumersCount[count]}</td>
                                    </c:if>
									<td><a href="#" id="${element.deviceid}" onclick="return removeDevAlloc(this.id)">DeAllocate</a></td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<form method="POST" id="sprtForm">
			<input type="hidden" name="deviceidPk" value="" id="deviceidPk"/>
		</form>

	</div>
	<!-- Adding Device Allocation and Editing Device Type Data Form Started -->

	
	<!-- Adding Device Allocation and Editing Device Type Data Form Ended -->

	<div class="modal fade form-group" id="stack1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<span id="modelHeader"></span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form:form action="./allocateNewDevice" modelAttribute="mrDevAllocs"
								commandName="mrDevAllocs" method="post" id="mrDevAllocs" >
								<table>
									<tr id="sdoc">
										<td>SDO Code</td>
										<td><font color="red">*</font></td>
										<td><form:select path="sdoCode"
												class="form-control input-medium"  id="sdoCode" onchange="getMrmaster(this.value)">
												<form:option id="sdoCodeOpt" value="0" selected="true" >Select SDO Code</form:option>
												 <c:forEach var="element" items="${sdoCodes}">
													<form:option value="${element[0]}" id="sdoCodeOpt"
														selected="">${element[1]}-${element[0]}</form:option>
												</c:forEach> 
											</form:select></td>
									</tr>
									
									<tr id="mrcodeTr">
										<%-- <td>Device operator Code</td>
										<td><font color="red">*</font></td>
										<td id="mrcd"><form:select path="mrCode" id="mrCode"
												class="form-control placeholder-no-fix" type="text"
												autocomplete="off" placeholder="" name="mrCode">
												<form:option value="MR01" >MR02</form:option>
												<c:forEach var="element" items="${operators}">
													<form:option value="${element[0]}" 
														>${element[1]}-(${element[0]})</form:option>
												</c:forEach>
											</form:select></td> --%>
									</tr>
									
									<tr id="deviceidTr">
										<%-- <td>Device ID</td>
										<td><font color="red">*</font></td>
										<td id="dvid"><form:select path="deviceid" id="deviceid"
												class="form-control placeholder-no-fix" type="text"
												autocomplete="off" placeholder="" name="deviceid">
												<form:option value="0" >select</form:option>
												<c:forEach var="element" items="${deviceids}">
													<form:option value="${element.deviceid}">${element.deviceid}</form:option>
												</c:forEach>

											</form:select></td> --%>
										
									</tr>
									
									



								</table>
								<div class="modal-footer">

									<button class="btn btn-success pull-right" id="btnOption" onclick="return validation();" > Allocate Device To MR</button>
									<button type="button" data-dismiss="modal" class="btn cancel">Close</button>									
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
