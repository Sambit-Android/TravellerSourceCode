$(".page-content")
		.ready(
				function() {
					App.init();
					TableEditable.init();
					FormComponents.init();
					$('#mrmAccMgmt').addClass('start active ,selected');
					$(
							"#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management")
							.removeClass('start active ,selected');
				});

function validateForm() {
	var validator = $("#mrMasters").validate({
		rules : {
			mrCode : "required",
			mrName : "required",
			mobile : {
				required : true,
				number : true,
				minlength : 10,
				maxlength : 11
			},
			password : "required",
			address : "required",
			sdoCode : "required"

		},
		errorElement : "span",
		messages : {

			mrCode : "MR Code is Required",
			mrName : "MR Name is Required",
			mobile : {
				required : "Mobile Number is Required",
				number : "Mobile should contain digits only",
				minlength : "Mobile should contain 10 digits only",
				maxlength : "Mobile should contain 10 digits only"
			},
			password : "Password is Required",
			address : "Address is Required",
			sdoCode : "Please Select SDO Code"
		}
	});

	if (validator.form()) {

		$("form#mrMasters").attr("action", "./updateMRMaster");
		$("form#mrMasters").submit();
	}
	$(".cancel").click(function() {
		validator.resetForm();
	});

}

function addMRMaster(param) {

	$("#mrCodeCheck").hide();
	$("#mrMasters").trigger("reset");
	// $("#mrMasters").attr("action","./addMRMaster");

	// $("#mrMasters").attr("action","./updateMRMaster");
	$("#addOption").html("Add MR Reader");
	$("#modelHeader").html("Add MR Reader");
	$("#mrCode").removeAttr("readOnly");
	$("#sdoc").removeAttr("hidden");

	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}

function editMRMaster(param, mrCode, sdoCode) {

	$("#mrCodeCheck").hide();
	$("#mrMasters").attr("action", "./updateMRMaster");
	$("#addOption").text("Update MR Reader");
	$("#modelHeader").html("Update MR Reader");
	$("#mrCode").attr("readOnly", "true");
	$("#sdoc").attr("hidden", "true");

	$.ajax({

		type : "post",
		url : "./editMRMaster",
		data : {
			'mrCode' : mrCode,
			'sdoCode' : sdoCode
		},
		dataType : "JSON",
		success : function(response) {

			document.getElementById("mrCode").value = response.mrCode;
			document.getElementById("mobile").value = response.mobile;
			document.getElementById("mrName").value = response.mrName;
			document.getElementById("address").value = response.address;
			document.getElementById("sdoCode").value = response.sdoCode;

		}
	});

	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}

function deleteMRMaster(param, mrCode, sdoCode) {
	if (confirm("Are you Sure to Delete MR Reader..!")) {

		$.ajax({
			type : "post",
			url : "./deleteMRMaster",
			data : {
				"mrCode" : mrCode,
				"sdoCode" : sdoCode
			},
			success : function(response) {
				window.location.href = "./meterReaderAccessManagment";
			}
		});
	} else {
		return false;
	}
}

function checkMRCode() {

	$
			.ajax({
				type : "POST",
				url : "./checkMRCodeAvailability",
				data : {
					"sdoCode" : $("#sdoCode").val(),
					"mrCode" : $("#mrCode").val()
				},
				dataType : "json",
				success : function(response) {

					if (response) {
						$("#mrCodeCheck").show();
						$("#mrCodeCheck")
								.html(
										"<span style='color:red'>MR Code is already existed.</span>");
						$("#mrCode").focus();
						$("#mrCode").select();
					} else {
						$("#mrCodeCheck").hide();
					}
				}
			});

}