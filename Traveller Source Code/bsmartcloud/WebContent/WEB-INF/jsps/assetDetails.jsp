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

</script>
<div class="page-content" >				
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-edit"></i>Asset Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
								<thead>
									<tr>
										<th>SlNo</th>
										<th>Category</th>
										<th>Location</th>
										<th>Assigned to</th>
										<th>Action</th>
										
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${assetData}"  var="data" >
									<tr>
										<td>${data.slno}</td>
										<td>${data.make}</td>
										<td>${data.section}</td>
										<td>${data.mrName}</td>
										<td>
											<a href="#" class="btn btn-xs blue">Transfer</a>           
											<a href="#" class="btn btn-xs yellow" onclick="deregisterDevice('${data.deviceid}',${data.sdoCode})">Deregister</a>
											<a href="#" data-target="#stack5" data-toggle="modal" class="btn btn-xs purple">Not Working</a>
											<a href="#" class="btn btn-xs dark">Send For Repair</a>
											<a href="#" class="btn btn-xs green">View History</a>
										</td>
									</tr>								
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			<div id="stack5" class="modal fade" tabindex="-1" data-width="300">
							 <div class="modal-dialog">
									<div class="modal-content">
									<div class="modal-header">
									<h4 class="modal-title">Not Working Action</h4>
									</div>
										<div class="modal-body">
											<div class="row">
											<div class="col-md-12">
												<form action="" id="notWorkingId" method="post">
												<table>
												<tr>
												<td>
														<label>
															<input type="radio" name="optionsRadios1" id="optionsRadios1" value="Not Working" checked="checked" disabled> Not Working
														</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
													<td>
														<label>
															<input type="radio" name="optionsRadios2" id="optionsRadios2" value="DeRegistered" checked="checked" disabled> De-Register
														</label><br>
													</td>
													
												</tr>
												<tr>
												
												<td>
												 <select id="notworkID" class="form-control selectpicker select2me input-medium" name="notworkID">
														    <option value="" selected="selected" disabled="disabled">Select reason</option>
															<option value="Device Fault" id="reason">Device Fault</option>
															<option value="No Sim" id="reason">No Sim</option>
															<option value="No Internet" id="reason">No Internet</option>
														  </select>
												</td>
												</tr>
												</table>
													<div class="modal-footer">
													     <button id="submitId" class="btn blue pull-right" onclick="return submitData()">Submit</button>
													     <button type="button" data-dismiss="modal" class="btn">Cancel</button>
													</div>
													</form>
											</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>
			<div id="stack6" class="modal fade" tabindex="-1" data-width="300">
							 <div class="modal-dialog">
									<div class="modal-content">
									<div class="modal-header">
									<h4 class="modal-title">Not Working Action</h4>
									</div>
										<div class="modal-body">
											<div class="row">
											<div class="col-md-12">
												<form action="" id="showAllAprovedId" method="post">
												<table>
												<tr>
												<td>
														<label>
															<input type="radio" name="optionsRadios1" id="optionsRadios1" value="Not Working" checked="checked" disabled> Not Working
														</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
													<td>
														<label>
															<input type="radio" name="optionsRadios2" id="optionsRadios2" value="DeRegistered" checked="checked" disabled> De-Register
														</label><br>
													</td>
													
												</tr>
												<tr>
												
												<td>
												 <select id="notworkID" class="form-control selectpicker select2me input-medium" name="notworkID">
														    <option value="" selected="selected" disabled="disabled">Select reason</option>
															<option value="Device Fault" id="reason">Device Fault</option>
															<option value="No Sim" id="reason">No Sim</option>
															<option value="No Internet" id="reason">No Internet</option>
														  </select>
												</td>
												</tr>
												</table>
													<div class="modal-footer">
													     <button id="submitId" class="btn blue pull-right" onclick="return submitData()">Submit</button>
													     <button type="button" data-dismiss="modal" class="btn">Cancel</button>
													</div>
													</form>
											</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>
			
			
</div>
<script>
function submitData() {
		alert($('#notworkID').val())
		
}
function deregisterDevice(deviceId,sdoCode){
	bootbox.confirm("Are you Sure to Deregister Device ..!",function(result){
	if(result){
		$.ajax({
			type:"POST",
			url:"./deregisterDevice",
			data:{
				deviceid : deviceId,
				sitecode : sdoCode,
				},
			dataType:"json",
			async:false,
			success:function(response){
				location.reload(); 
			}	
		});
	  }
	});
}
</script>