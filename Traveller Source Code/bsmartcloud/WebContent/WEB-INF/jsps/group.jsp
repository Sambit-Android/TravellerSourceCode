<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <script>
$(".page-content")
.ready(
		function() {
			App.init();
			TableEditable.init();
			FormComponents.init();
			$('#meterData-Acquisition').addClass('start active ,selected');
			$(
					"#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management")
					.removeClass('start active ,selected');
			
			
		});
		
function edit(param,id)
{	
		  $.ajax(
					{
							type : "GET",
							url : "./editGroup/" + id,
							dataType : "json",
							success : function(response)
						{					    		
								document.getElementById("id").value=response.id;
								document.getElementById("grpName").value=response.grpName;															
								document.getElementById('updateOption').style.display='block';
								document.getElementById('addOption').style.display='none';						
						}								
					}
			    );
		$('#'+param).attr("data-toggle", "modal");
	    $('#'+param).attr("data-target","#stack1");
  }

	function validateForm()
	{ 
		if($('#grpName').val().trim()=='')
			{
			bootbox.alert('please eneter group name');
			return false;
			}
	 	
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
						<i class="fa fa-edit"></i>Group
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" data-toggle="modal" data-target="#stack1" id="addMRMaster" >
								create Group <i class="fa fa-plus"></i>
							</button>
							
						</div>
						
					</div>
					
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
							   <th hidden="true">id</th>
								<th>Group Name</th>
								<th>Edit</th>
								<!-- <th>Delete</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${allGroup}">
								<tr>
								   
									<td hidden="true">${element.id}</td>
									<td>${element.grpName}</td>									
									<td><a href="#" onclick="edit(this.id,${element.id})"
										id="editData${element.id}" >Edit</a></td>
									
									<%-- <td><a href="#" onclick="delete(${element.id})"
										>Delete</a></td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
		</div>
	</div>
	
	<!--MRMaster Registration content started -->
	
	<div  class="modal fade" id="stack1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title"><span id="modelHeader"></span></h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											<form:form action="./updateGroup" modelAttribute="group"	commandName="group" method="post" id="groupId" >
												<table >
												
													<tr hidden="true">
														<td><form:label path="id"> id</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="id" ></form:input></td>
														
													</tr>													
													
													<tr>
														<td><form:label path="grpName">Group Name</form:label></td>
														<td><font color="red">*</font></td>
														<td><form:input path="grpName" id="grpName" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="grpName" ></form:input></td>
													
													</tr>
												</table>
														
												<div class="modal-footer">
													
													 <button class="btn btn-success pull-right" style="display: block;" id="addOption" onclick="return validateForm()" >Add Group</button>
													 <button class="btn btn-success pull-right" style="display: none;" id="updateOption" onclick="return validateForm()" >Edit Group</button>
													 <button type="button" data-dismiss="modal" class="btn cancel">Close</button>
													
												</div>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>	
	<!--MRMaster Registration content ended -->
	
	
