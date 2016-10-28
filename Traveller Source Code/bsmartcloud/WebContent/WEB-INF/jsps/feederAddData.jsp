<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(".page-content").ready(function() {
		App.init();
		TableEditable.init();
		FormComponents.init();
		$('#Ip_Cesc,#feederDataNew').addClass('start active ,selected');
		//loadSearchAndFilter('table_3');
		//	$('#Ip_Cesc,#feederData').addClass('start active ,selected');
		$('#addMRMaster').click(function() {
			document.getElementById('feederMaster').reset();
			$('#btnOption').show();
			$('#upOption').hide();
		});
	});
</script>

<script>
	function validateForm(temp) {
		var validator = $("#feederMaster").validate({

			rules : {
				feederCode : {
					required : true,
					minlength : 4
				//maxlength:16
				},
				siteCode : "required",
				/* 	feederName :"required", */
				feederType : "required"

			},
			errorElement : "span",

			messages : {
				feederCode : {
					required : "Feeder Code is Required",
					minlength : "At least 4 Values Required. ",
				/* number : "IMEI Number Must be digits Only",
				minlength : "At least 14 Digits Required. ", */

				},
				siteCode : "Please Select Section",
				/* 	feederName : "Please enter feeder Name", */
				feederType : "Please enter feeder Type"
			}
		});

		if (validator.form()) {
			if (temp == 'add') {
				//alert("inside add");
				/* 	$("form#feederMaster").attr("action
						","./updateMRDevice");
					$("form#feederMaster").submit(); */

				$.ajax({
					type : "GET",
					url : "./addFederDetails/save",
					data : {
						"siteCode" : $('#siteCode').val(),
						"feederCode" : $('#feederCode').val(),
						"feederName" : $('#feederName').val(),
						"feederType" : $('#feederType').val()
					},
					dataType : "text",
					success : function(response) {
						bootbox.confirm(response, function(result) {
							if (result == true) {
								location.reload();
							}

						});
						return false;

					}
				});
			} else {
				//alert("inside Update");
				$.ajax({
					type : "GET",
					url : "./addFederDetails/update",
					data : {
						"siteCode" : $('#siteCode').val(),
						"feederCode" : $('#feederCode').val(),
						"feederName" : $('#feederName').val(),
						"feederType" : $('#feederType').val(),
						"id" : $('#upId').val()
					},
					dataType : "text",
					success : function(response) {
						bootbox.confirm(response, function(result) {
							if (result == true) {
								location.reload();
							}

						});
						return false;

					}
				});

			}

		}

		return false;

	}

	$(".cancel").click(function() {
		validator.resetForm();
	});

	function validateFeederCode() {
		var fCode = $('#feederCode').val();
		var sitecode = $('#siteCode').val();
		if (sitecode == '' || sitecode == null) {
			bootbox.alert("Select Site Code ");
			$('#feederCode').val('');
			return false;
		} else if (fCode == '' || fCode == null) {
			bootbox.alert("Please Enter FeederCode");
			$('#feederCode').val('');
			return false;
		} else if (fCode.length > 4 || fCode.length < 4) {
			bootbox.alert("Maximum  4 Values Required");
			$('#feederCode').val('');
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : "./checkFeedCodeValid",
				data : {
					"feederCode" : fCode,
					"siteCode" : sitecode
				},
				dataType : "text",
				success : function(response) {
					if (response > 0) {
						bootbox.alert("Feeder Code Already Exists");
						$('#feederCode').val('');
						return false;
					} else {
						return true;
					}

				}
			});
		}

	}

	function editfeederCdoe(param, deviceid) {
		$('#btnOption').hide();
		$('#upOption').show();

		$.ajax({

			type : "post",
			url : "./editFeederDevice/" + deviceid,
			dataType : "JSON",
			success : function(response) {
				//alert(response)
				$('#siteCode').val(response.siteCode);
				$('#feederCode').val(response.feederCode);
				$('#feederType').val(response.feederType);
				$('#upId').val(response.id);

			}
		});

		$('#' + param).attr("data-toggle", "modal");
		$('#' + param).attr("data-target", "#regData");

	}

	function changeFeederCode() {
		$('#feederCode').val('');

	}
</script>



<div class="page-content">
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
						<i class="fa fa-edit"></i>Feeder Master Details
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" data-toggle="modal" id="addMRMaster"
								data-target="#regData">
								Add Feeder Details <i class="fa fa-plus"></i>
							</button>

						</div>

					</div>

					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th>Sitecode Name</th>
								<th>Feeder Code</th>
								<th>Feeder Type</th>
								<!-- 	<th>Feeder Name</th> -->
								<!-- <th>Edit</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${feederDetails}">
								<tr>

									<td id="srdmrCode">${element[1]}-${element[5]}</td>
									<td>${element[2]}</td>
									<td>${element[3]}</td>
									<%-- 	<td>${element[4]}</td> --%>

								<%-- 	<td><a href=""
										onclick="return editfeederCdoe(this.id,this.name)"
										id="editData${element[0]}" name="${element[0]}"
										data-toggle="modal">Edit</a></td> --%>

								</tr>
							</c:forEach>
						</tbody>
					</table>


				</div>
			</div>
		</div>
	</div>


	<div id="regData" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<span id="modelHeader">Add Feeder Details</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form action="" method="get" id="feederMaster">
								<input type="text" id="upId" name="upId" hidden="true" />
								<table>

									<tr id="imei">
										<td>Section</td>
										<td><font color="red">*</font></td>
										<td><select name="siteCode" id="siteCode"
											class="form-control selectpicker placeholder-no-fix input-medium"
											onchange="changeFeederCode()">
												<option value="">Select SDO Code</option>
												<c:forEach var="element" items="${sdoCodes}">
													<option value="${element[0]}">${element[0]}-${element[1]}</option>
												</c:forEach>
										</select></td>

									</tr>


									<tr>
										<td>Feeder Code</td>
										<td><font color="red">*</font></td>
										<td><input type="text" id="feederCode"
											class="form-control placeholder-no-fix" type="text"
											autocomplete="off" placeholder="" name="feederCode"
											onchange="validateFeederCode()"></input></td>
									</tr>

									<!-- 	
													<tr >
															<td>Feeder Name</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="feederName" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="feederName"></input></td>
														</tr> -->

									<tr>
										<td>Feeder Type</td>
										<td><font color="red"></font></td>
										<td><input type="text" id="feederType"
											class="form-control placeholder-no-fix" type="text"
											autocomplete="off" placeholder="" name="feederType"></input></td>
									</tr>


								</table>

								<div class="modal-footer">
									<button class="btn btn-success pull-right"
										style="display: none;" id="upOption"
										onclick="return validateForm('update')" value="">
										<span id="upOption">Update</span>
									</button>
									<button class="btn btn-success pull-right"
										style="display: block;" id="btnOption"
										onclick="return validateForm('add')" value="">
										<span id="addOption">Save</span>
									</button>
									<button type="button" data-dismiss="modal" class="btn cancel">Close</button>
									<!-- <button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update User</button> -->
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>