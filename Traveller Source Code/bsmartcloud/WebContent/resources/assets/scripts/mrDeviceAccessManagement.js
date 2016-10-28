$(".page-content")
		.ready(
				function() {
					App.init();
					TableEditable.init();
					FormComponents.init();
					$('#mrDeviceAccMgmt').addClass('start active ,selected');
					$(
							"#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling")
							.removeClass('start active ,selected');

					$(".makeError").hide();
					$(".sdError").hide();
				});

function validateForm() {
	var validator = $("#mrDevices").validate({

		errorPlacement : function(error, element) {
			error.appendTo(element.parent("td").next("td"));
		},
		debug : true,
		rules : {
			deviceid : {
				required : true,
				number : true,
			// minlength:16,
			// maxlength:16
			},
			gprsSimNum : "required",
			make : {
				required : $("#make option:selected").val() == ""
			},
			sdoCode : {
				required : {
					depends : function(element) {

						if ($("input:hidden[name='sdoCode']").val() == "") {
							return true;
						}
					}
				}
			}

		},
		/* errorElement : "span", */

		messages : {
			deviceid : {
				required : "Please Enter IMEI Number",
				number : "IMEI Number Must be digits Only",
				minlength : "IMEI Length Must be 16 digits Only"
			},
			gprsSimNum : "Sim Number is Required",
			make : "Please Select Device Name",
			sdoCode : "Please Select SDO Code"
		}
	});

	if ($("input:hidden[name='sdoCode']").val() == "") {
		$(".sdError").show();

	}
	if ($("input:hidden[name='make']").val() == "") {
		$(".makeError").show();
	}

	if (validator.form()) {
		$("form#mrDevices").attr("./updateMRDevice");
		$("form#mrDevices").submit();
	}
}

function addMRDevice(param) {

	$("#mrDevices").trigger("reset");
	// $("#mrMasters").attr("action","./addMRMaster");

	$("#mrDevices").attr("action", "./updateMRDevice");
	$("#addOption").html("Add MR Device");
	$("#modelHeader").html("Add MR Device");
	$("#imei").removeAttr("readOnly");
	$("#deviceid").removeAttr("readOnly");
	$("#make").removeAttr("disabled");
	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}

function editMRDevice(param, deviceid) {

	$("#mrDevices").attr("action", "./updateMRDevice");
	$("#addOption").text("Update MR Device");
	$("#modelHeader").html("Update MR Device");
	$("#deviceid").attr("readOnly", "true");
	// $("#sdoc").attr("hidden","true");
	// $("#sdoCode").prop("disabled","true");

	$.ajax({

		type : "post",
		url : "./editMRDevice",
		data : {
			'deviceid' : deviceid
		},
		dataType : "JSON",
		success : function(response) {

			document.getElementById("deviceid").value = response.deviceid;
			document.getElementById("gprsSimNum").value = response.gprsSimNum;
			document.getElementById("make").value = response.make;
			document.getElementById("sdoCode").value = response.sdoCode;

			$.trim("" + response.sdoCode);
			$("#sdoCode").val("" + response.sdoCode);
			$("#make").val("" + response.make);
			// $("#make").attr("disabled","true");

			// $("#sdoCode").val(""+response.sdoCode);
			// $("#sdoCode").attr("disabled","true");

		}
	});

	$('#' + param).attr("data-toggle", "modal");
	$('#' + param).attr("data-target", "#regData");

}

function deleteMRDevice(deviceid) {
	if (confirm("Are you Sure To Delete MR Device....!")) {

		$.ajax({
			type : "post",
			url : "./deleteMRDevice",
			data : {
				"deviceid" : deviceid
			},
			success : function(response) {
				window.location.href = "./mrDeviceAccessManagment";
			}
		});

	} else {
		return false;
	}

}
