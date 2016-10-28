<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

$(".page-content").ready(
		function() {
			App.init();
			// UIBootbox.init();
			TableEditable.init();
			FormComponents.init();
			$('#meterData-Acquisition').addClass('start active ,selected');
			$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
       //$('#sdoCode').select2();
		});
</script>

<!-- <style>
#mrDevices span.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	color:red;
}
</style> -->
<script>
var statusId;
function updateStatus(param,value,devid,sdocode)
{
	statusId=param;
	 $('#status').val(value);
	 $('#mobileid').val(devid);
	 $('#sitecodeVal').val(sdocode);
	$('#'+param).attr("data-toggle", "modal");
  $('#'+param).attr("data-target","#stack1");	
}
function updateMobileStatus()
{
		var url = "./updateMobileStatus";
		$.ajax({
	        type: "POST",
	        url: url,
	        data:{"sitecodeVal":$("#sitecodeVal").val(),
	        	"mobileid":$("#mobileid").val(),
	        	"status":$("#status").val(),
	        	"remark":$("#remark").val()},
	        cache:false,
			async:false,
		    dataType: "text",
	        success: function(data)
	        {
	            
	           alert(data);
	        }
	      });

	   return false;
	
}
</script>


  <div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	
	<c:if test="${allocated ne null }">
	<script>
		bootbox.alert("<span style='color:red;'>Device Has allocated.<c:out value='${allocated}'></c:out></span>");
	
	</script></c:if>
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Devices Status
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
					
						<!-- <div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
										to Excel</a></li>
							</ul>
						</div> -->
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
						<thead>
							<tr>
								<th>DEVICE ID</th>
								<th>GPRS SIM NO</th>
								<th>MAKE</th>
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>ApproveStatus</th>
								</c:if>
								<th>ALLOCATION STATUS</th>
								<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
								<th>SDOCODE</th>
								</c:if>
								<th>STATUS</th>
								<th>DEVICE TYPE</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${mrdList}">
								<tr>
									<td>${element.deviceid}</td>
									<td>${element.gprsSimNum}</td>
									<td>${element.make}</td>
									<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									<td><c:choose>
									   <c:when test="${element.approvalStatus eq 'NOT APPROVED'}">
									   <a href="#" onclick="return approval('${element.deviceid}');">${element.approvalStatus}</a>
									   </c:when>
									   <c:otherwise>
									   ${element.approvalStatus}
									   </c:otherwise>
									    </c:choose>
									 </td>
									 </c:if>
									 <td>${element.allocatedflag}</td>
									 <c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
									 <td>${element.sdoCode}</td>
									 </c:if>
								    <td><a href="#" onclick="updateStatus(this.id,'${element.status}','${element.deviceid}','${element.sdoCode}')"
										id="editData${element.deviceid}">${element.status}</a></td>
								    <td>${element.deviceType}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	<!-- Adding Device Access and Editing Device Type Data Form Started -->
	
	
	<!-- Adding Device Access and Editing Device Type Data Form Ended -->
	<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Update Status</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											 <form action=""  method="post" id="updateMobileStatus">
												<table>
													<tr>
														<td>Status</td>
														<td></td>
														<td><select id="status" class="form-control" placeholder="" name="status">
														<c:forEach items="${statusList}" var="statusVal">
																		<option value="${statusVal}"
																			>${statusVal}</option>
														</c:forEach>
														</select></td>
													</tr>
													<tr hidden="true">
														<td>Sdocode</td>
														<td></td>
														<td><input id="sitecodeVal" class="form-control" placeholder="" name="sitecodeVal" value=""/>
														</td>
													</tr>
													<tr hidden="true">
														<td>MobileId</td>
														<td></td>
														<td><input id="mobileid" class="form-control" placeholder="" name="mobileid" value=""/>
														</td>
													</tr>
													<tr >
														<td>Remarks</td>
														<td><font color="red">*</font></td>
														<td><textarea id="mobileid" class="form-control" placeholder="" name="mobileid" ></textarea>
														</td>
													</tr>

												</table>
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													<button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return updateMobileStatus();">Update Status</button>
												</div>
											</form> 

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
</div>
	