<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>




<div id="content">
	<section id="widget-grid" class="">
		
		<div class="row">

			<div class="col-sm-6">

				<div class="jarviswidget jarviswidget-color-greenDark" id="widpayARFOnline"
					data-widget-editbutton="false" data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>ARF Payment</h2>
					</header>

					<div>

						<div class="jarviswidget-editbox"></div>
						<div class="widget-body">
						
						
							<div class="row">
							<label class="col-md-12 control-label">
							Dear <b><font color="red">${custname}</font></b> Your Application has been Submited.Your Application ID is <b><font color="red">${APPID}</font></b>. 
							
							For Processing this application you need to make payment of Application Registration Fee (ARF)
							
							 Which is mandatory (To see ARF click on Download Challan).
							<br><br>
							</label>
							</div>
							<div class="row">
							<label class="col-md-12 control-label"><b>Application Summary</b><br></label>
							</div>
							<div class="row">
							<label class="col-md-5 control-label">Name&nbsp;:&nbsp;${custname}</label>
							<label class="col-md-7 control-label">Nature Of Installation&nbsp;:&nbsp;${CATEGORY}</label>
							</div>
							
							
							<div class="row">
							<label class="col-md-5 control-label">Tariff&nbsp;:&nbsp;${TARIFF}</label>
							<label class="col-md-7 control-label">Required Load&nbsp;:&nbsp;${LOADKW} (KW) :&nbsp;${LOADHP} (HP) :&nbsp;${LOADKVA} (KVA) </label>
							</div>
							
							<br>
							<div class="row">
							<label class="col-md-12 control-label"><b style="color: red;font-size: 18px;">Step 1 </b>-<font color="#0000CD" size="2px;">For <b style="color: #FF4500;">Multiple Connections,MS Building and Layout</b> click on Track Application then click on Application Id <br>and Add Multiple Connections for MS Building & Multiple Connection [if Application is for MS Building & Multiple Connection] and Add Multiple Sites for Layout [If Application is for Layout ]after that download the Challan for Registration Fee</font></label>
							</div>
							
							
							<br>
							<div class="row">
							<label class="col-md-12 control-label"><b style="color: red;font-size: 18px;">Step 2 </b>-<font color="#0000CD" size="3px;"> For manual payment <!-- select <b style="color: #FF4500;">Pay ARF Manually</b> --> take printout of that Challan & pay the ARF in the Cash Counter</font></label>
							
							</div>
							
						    <div>
							   <form action="./applicationChallanDownload/Show" method="post" id="applicationChallanDownload">
							        <input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${APPID}"></input>
							        <button type="submit" class="btn btn-primary" style="margin-left: 230px;">
												<strong>Download ARF Challan To Pay Manually</strong>
									</button>
							      
								</form>			
							</div>
							
							
							
							


							



						</div>
					</div>
				</div>


			</div>
			
			<div class="col-sm-6">
						<div class="jarviswidget jarviswidget-sortable jarviswidget-color-white" id="widpayARFOnline."
									data-widget-editbutton="false" data-widget-colorbutton="false">
				
									<header>
										<span class="widget-icon"> <i class="fa fa-check"></i>
										</span>
										<h2>Online Payment ARF - <b><font color="red">${APPID}</font></b></h2>
									</header>
								
										<form action="./bdpaymentOnlineNC" method="POST" 
											id="online-paymentReg" class="smart-form">
											
											<input name="appId"
														id="appId" hidden="true" value="${APPID}">
														
											<input name="sitecode"
											id="sitecode" hidden="true" value="${sitecode}">

											<fieldset>

											 <div class="row">
												<section class="col col-6">
													<label class="label">Name</label><label class="input"><i
														class="icon-prepend fa fa-user" id="usericon1"></i> <input
														name="firstname" placeholder="Enter First Name"
														id="firstname" value="${custname}"> </label>
												</section>
												</div>

												<div class="row">
												<section class="col col-6">
													<label class="label">Mobile No</label><label class="input"><i
														class="icon-prepend fa fa-phone"></i> <input name="phone"
														id="phone" placeholder="Enter Mobile no" readonly="readonly" value="${phoneno}"></input> </label>
												</section>
												
												<section class="col col-6">
													<label class="label">Amount</label> <label class="input"><i
														class="icon-prepend fa fa-rupee"></i><input name="amount"
														placeholder="Amount" id="amount" value="${arfAmount}"></input> </label>
												</section>
												
								                </div>

												<div class="row">
												
												<section class="col col-6">
													<label class="label">Tariff</label> <label
														class="input"> <input name="tariffdesc"
														id="tariffdesc" readonly="readonly" value="${TARIFF}"></input>
													</label>
												</section>
												
												<section class="col col-6">
													<label class="label">Pay Towards</label> <label
														class="input"> <input name="productinfo"
														id="productinfo" readonly="readonly" value="ARF"></input>
													</label>
												</section>
												</div>

												

											</fieldset>
											
											
											
											

											<footer>

												<button type="submit" class="btn btn-primary" onclick="return validate();">
													<strong>Proceed To Pay</strong>
												</button>
											</footer>
										</form>
								</div>

					
					
					</div>
			
			
			
		</div>
	</section>
</div>


<script>


function validate(){
	if(confirm("Are you sure to do for Payment")){
		
	} else{
        return false;
    }
}

function trimNumber(){
	
	$("#recieptNo").val($("#recieptNo").val().trim());
}


$('#receiptDate').datepicker({
	  dateFormat : 'dd/mm/yy',
	  maxDate: new Date()

}); 
function checkduplicate(){
	var refNo=$("#recieptNo").val();
	var phasecount=15;			
	$.ajax({
		type : "GET",
		url : "./checkrefDuplicate",
		dataType : "text",
		data : {

			refNo : refNo,
			phasecount:phasecount,
		},
		success : function(response) {
			
			if (response == "NF") {

			} else {
				alert("Receipt No already exists");
				$("#recieptNo").val(" ");
			}
		}
	});
}

/* $("#payOnline").change(function(){
	$('#button2').hide();
	$('#button1').show();
}); */

$("#payManuaally").change(function(){
	
	$('#button1').hide();
	$('#button2').show();
});
$(document).ready(function() {
	pageSetUp();
	//$('#button2').hide();
	//$('#button1').show();
});

$(document).ready(function() {
	 
	
	$('#button2').show();
	
	

  
	
   $.validator.addMethod("regex", function(value, element, regexpr) {
		return regexpr.test(value);
	}, "");
   $.validator.addMethod('minStrict', function(value, el, param) {
		return value > param;
	});
   $.validator.addMethod("dateFormat",
		    function(value, element) {
		        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
		    },
		    "Please enter a date in the format dd/mm/yyyy.");
	  
    $("#online-paymentReg").validate({

		// Rules for form validation
		rules : {
			firstname : {
				required : true,
				maxlength : 50,
				regex : /^[a-zA-Z .]*$/
			},
			amount : {
				required : true,
				digits:true,
				minStrict:0
				
			},
			
			
			
		},

		// Messages for form validation
		messages : {
			amount : {
				required : 'please enter amount',
				minStrict:'Amount should be > 0',
				
			},
			firstname : {
				required : 'enter name',
				regex:'Enter Valid Name'
			},
		
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
    
  /*   $('#depositpaid-form').validate({
		// Rules for form validation
			rules : {
				recieptNo : {
					required : true,
					maxlength : 25,
					regex : /^[0-9]*$/
				},
				
				receiptDate : {
					required : true,
					dateFormat: true
				},
				payAmount : {
					required : true,
					maxlength : 12,
					digits:true,
					minStrict : 0
					
				},
				payTowards : {
					required : true
					
					
				},
				
			},
	
			// Messages for form validation
			messages : {
				
				recieptNo : {
					required : 'please enter receipt no',
					regex:'please enter valid receipt no (only numbers)',
					
				},
				
				receiptDate : {
					required : 'please select receipt date',
					
				},
				payAmount : {
					required : 'please enter amount',
					minStrict:'Amount should be > 0',
					
				},
				payTowards : {
					required : 'select Pay Towards',
					
				},
				
			},
	
			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		}); */

    var appdate1="${APPRegDate}";
    var monthyear=appdate1.split("-");					 
	var monlast="";
	var year="";
	var date="";
    for(var i=0;i<=monthyear.length;i++){
    	 date=monthyear[0];
    	 monlast=monthyear[1];
         year=monthyear[2];
    	
    }
 	var month=(parseFloat(monlast))-1;					   
 	var minDate=new Date(year,month,date);	
 	$("#receiptDate").datepicker("option","minDate",minDate );
	
 	$('#payTowards').val("ARF");
	}); 
</script>