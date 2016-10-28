<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#consComplaints').addClass('start active ,selected');
	   	  		$('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#Disconn-Reconn').removeClass('start active ,selected');
   	    	   });
  
 
  function edit(param,opera)
	{
		var operation=parseInt(opera);
			  $.ajax(
						{
								type : "GET",
								url : "./editConsComplaints/" + operation,
								dataType : "json",
								success : function(response)
															{	
													    		
																document.getElementById("id").value=response.id;
																document.getElementById("sdoCode").value=response.sdoCode;
																document.getElementById("consumerNo").value=response.consumerNo;
																document.getElementById("address").value=response.address;
																document.getElementById("description").value=response.description;
																document.getElementById("issue").value=response.issue;
																document.getElementById("createdBy").value=response.createdBy;
																document.getElementById("status").value=response.status;
																/* $('#status').val(response.status); */
															}
						}
				    );
			$('#'+param).attr("data-toggle", "modal");
		    $('#'+param).attr("data-target","#stack1");
	}
  </script>
<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Consumer Complaints
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						 <!-- <div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="addData" id="em">
								Update Status <i class="fa fa-plus"></i>
							</button>
						</div> --> 
						<div class="btn-group pull-right">
							<!-- <button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button> -->
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
							<tr style="background-color: lightgray">
								<th hidden="true">Id</th>
								<th>SDO CODE</th>
								<th>Consumer No.</th>
								<th>Address</th>
								<th>Description</th>
								<th>Issue</th>
								<th>Created By</th>
								<th>Status</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${complaintList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td>${element.sdoCode}</td>
									<td>${element.consumerNo}</td>
									<td>${element.address}</td>
									<td>${element.description}</td>
									<td>${element.issue}</td>
									<td>${element.createdBy}</td>
									<td>${element.status}</td>
									<td><a href="#" onclick="edit(this.id,${element.id})"
										id="editData${element.id}">Edit</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

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
											 <form:form action="./updateComplStatus" modelAttribute="conComplaints"
																	commandName="conComplaints" method="post" id="conComplaints">
												<table>
												
													<tr hidden="true">
														<td>Id</td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id" ></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>SDO CODE</td>
														<td><form:input path="sdoCode" id="sdoCode"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="sdoCode" readonly="true"></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>Consumer No.</td>
														<td><form:input path="consumerNo" id="consumerNo"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="consumerNo" readonly="true"></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>Address</td>
														<td><form:input path="address" id="address"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="address" readonly="true"></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>Description</td>
														<td><form:input path="description" id="description"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="description" readonly="true"></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>Issue</td>
														<td><form:input path="issue" id="issue"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="issue" readonly="true"></form:input></td>
													</tr>
													
													<tr hidden="true">
														<td>Created By</td>
														<td><form:input path="createdBy" id="createdBy"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="createdBy" readonly="true"></form:input></td>
													</tr>
														
													<tr>
														<td>Status</td>
														<td><select id="status" class="form-control" placeholder="" name="status">
														<c:forEach items="${statusList}" var="statusVal">
																		<option value="${statusVal}"
																			>${statusVal}</option>
														</c:forEach>
														</select></td>
													</tr>

												</table>
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													<button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update Status</button>
												</div>
											</form:form> 

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>