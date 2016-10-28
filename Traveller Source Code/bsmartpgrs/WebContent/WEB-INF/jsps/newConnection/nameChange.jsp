<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<section id="widget-grid" class="">
<div id="content">

<div class="row">
			
			<c:if test="${not empty tcsuccess}">
						<script>
							var result = "${tcsuccess}";
							alert(result);
						</script>
					<c:remove var="tcsuccess" scope="session" />
	         </c:if>
	   
			<article class="col-sm-12 col-md-12">
			<div class="jarviswidget jarviswidget-sortable jarviswidget-color-darken" id="wid-id-21" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-location-arrow"></i> </span>
					<h2>Name Change / Transfer of Connection</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<form:form action="./ncms/createTransferConnection" id="transfer_connection_id" method="post" commandName="application" class="smart-form"
									modelAttribute="transferConnection" enctype="multipart/form-data" novalidate="novalidate" autocomplete="off">
							<fieldset>
								
							<div class="row">
								
								
								<section class="col col-3" >
									<label class="label">RR Number&nbsp;<font color="red">*</font></label> <label
										class="select"> 
										
									<select id="rrnum" name="rrnum" class="form-control" onchange="selectRRNumber(this.value);">
										<option value="" selected="" disabled="">Select</option>
										<c:forEach items="${rrNoList}" var="section">
											<option value="${section.sitecode}-${section.rrnum}">${section.rrnum}</option>
										</c:forEach>
									</select>
									
									
									<i></i></label>
								</section>
										
								
								<section  class="col col-3">
									<label class="label">Name of Existing Consumer&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="pConsumerName" name="pConsumerName" id="pConsumerName" placeholder="Consumer Name"></form:input></label>
								</section>	
								
								<section class="col col-3">
									<label class="label">Email Id</label>
									<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
										<form:input type="email" class="input-md" path="pemail" name="pemail" placeholder="E-mail"></form:input>
									</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Contact No&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
										<form:input type="text" class="input-md" path="pmobileno" name="pmobileno" placeholder="Contact No"></form:input>
									</label>
								</section>
								
							</div>
							
							
							
							<div class="row">

									<section class="col col-6">
										<label class="textarea"> Consumer Address <form:textarea type="text"
												name="pAddress" placeholder="Consumer Address"
												path="pAddress" id="pAddress" rows="1"></form:textarea>
										</label>
									</section>

										<section class="col col-3">
									<label class="label">Reading Date&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-append fa fa-calendar"></i>
										<form:input type="text"	name="readingdate" placeholder="Reading Date" path="readingdate" id="readingdate"></form:input>
									</label>
								</section>
								
								<section  class="col col-3">
									<label class="label">Present Reading&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="pmr" name="pmr" id="pmr" placeholder="Present Reading"></form:input></label>
								</section>
							</div>
							
							 <div class="row">										
						
								<section  class="col col-3">		
									<label class="label">Name Of Transferee&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="tConsumerName" name="tConsumerName" id="tConsumerName" placeholder="To Whom the installation to Tranfered"></form:input></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">Transferee Mobile No&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="tmobileno" name="tmobileno" id="tmobileno" placeholder="Mobile No"></form:input></label>
								</section>
						
								<section  class="col col-3">		
									<label class="label">Address Of Transferee&nbsp;<font color="red">*</font></label>
									<label class="input"><textarea  name="tAddress" name="tAddress" id="tAddress" rows="1"></textarea></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">Reason for Transfer&nbsp;<font color="red">*</font></label>
									<label class="input"><textarea  name="reasonfortranfer" name="reasonfortranfer" id="reasonfortranfer" rows="1" placeholder="Reason to Tranfer"></textarea></label>
								</section>
							
								</div>
								
								
									
							  <section> 
									<label class="label"><font size="3" color="red">Documents Required</font></label>
							  </section>
								
							<div class="row">
								<section  class="col col-3">		
									<label class="label">a.&nbsp;Sale Deed</label>
								</section>
								
								<section class="col col-3">
								<div >

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="saledeed" name="saledeed">

																</div>
															</div>
														</div>
													</div>
								</section>
								<section  class="col col-3">		
									<label class="label">b.&nbsp;Agreement</label>
								</section>
								
								<section class="col col-3">
								<div >

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="agreement" name="agreement">

																</div>
															</div>
														</div>
													</div>
								</section>
								
							</div>
							
							<div class="row">
								<section  class="col col-3">		
									<label class="label">c.&nbsp;Copy of the latest Bill</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="copy" name="copy">

																</div>
															</div>
														</div>
													</div>
								</section>
								<section  class="col col-3">		
									<label class="label">d.&nbsp;Copy of the latest paid receipt</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="copypaidrec" name="copypaidrec">

																</div>
															</div>
														</div>
													</div>
								</section>
							</div>
							
							<div class="row">
								
								<section  class="col col-3">		
									<label class="label">e.&nbsp;Consent latter to transfer<br>&nbsp;&nbsp;&nbsp;the deposit.</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="consentlatter" name="consentlatter">

																</div>
															</div>
														</div>
													</div>
								</section>
							 
							  <section  class="col col-3">		
									<label class="label">f.&nbsp;Any Other document.</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="otherdoc" name="otherdoc">

																</div>
															</div>
														</div>
													</div>
								</section>
								
							 </div>
							 
							 </fieldset>
							
							

							<footer>
								<button type="submit" class="btn btn-primary">
									Submit
								</button>
								 
							</footer>
						</form:form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->

		</article>
		
		
		
		
		
		    <article class="col-sm-12 ">
				
					<div class="jarviswidget"
						id="rrno-reg-history" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered Transfer Connection Details</h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>
											<th>RR Number</th>
											<th>Existing Consumer</th>
											<th>Contact No</th>
											<th>Name Of Transferee </th>
											<th>Transferee Mobile No </th>
											<th>Status </th>
											<th>Reason</th>
											<th>Reading Date </th>
											<th>Present Reading</th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="app" items="${transferConList}">
											
											<tr>
												<td>${app.rrnum}</td>
												<td>${app.pConsumerName}</td>
												<td>${app.pmobileno}</td>
												<td>${app.tconsumername}</td>
												<td>${app.tmobileno}</td>
												<td>${app.status}</td>
												<td>${app.reasonfortranfer}</td>
												<td>${app.readingdate}</td>
												<td>${app.pmr}</td>
												
											</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>

					</div>
			</article> 
		
		
		
		
		
		
		</div>
		</div>
		</section>
		
<script>


function selectRRNumber(rrnumber){

	  var res = rrnumber.split("-");
	  var rrnumberval=res[1];
		
		
		$.ajax({
			url : "./NCMS/getConsumerDetails",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				rrnumberval : rrnumberval,
				sitecode : res[0]

			},
			success : function(response) {
				var data=response[0];
				
				$('#pConsumerName').val(data.name);
				if(data.email==0){
					$('#pemail').val();
				}else{
					$('#pemail').val(data.email);
				}
				$('#pmobileno').val(data.telephoneno);
				$('#readingdate').val();
				$('#pmr').val();
				$('#pAddress').val(data.address+","+data.villagename);
				
			}
		});
		
	}
	
	
	
$('#readingdate').datepicker({
	dateFormat : 'dd/mm/yy',
	prevText : '<i class="fa fa-chevron-left"></i>',
	nextText : '<i class="fa fa-chevron-right"></i>',
	maxDate: new Date(),
		
});

function getDetailsByRRNumber(rrnumber){
	
	
	$.ajax({
		type : "GET",
		url : "./checkRRNumberExists",
		dataType : "json",
		data : {
			rrno : rrnumber,
		},
		success : function(response) {
			
			if(response!="NA"){
				$("#pConsumerName").val(response[5]);
				$("#pAddress").val(response[6]);
				
			}else{
				alert("RR Number does not exist");
				$("#rrnum").val("");
				$("#pConsumerName").val("");
				$("#pAddress").val("");
				
			}
		}
	});
	
	
}

var responsiveHelper_datatable_col_reorder = undefined;
var breakpointDefinition = {
	tablet : 1024,
	phone : 480
};

$('#datatable_fixed_column').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"order": [],
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});




$(document).ready(function() {

	pageSetUp();
	
	$.validator.addMethod("regex", function(value, element, regexpr) {
		if(value==""){
			
			return "notmandatory";
		}else{
		return regexpr.test(value);
		}
	}, "");
	
	$.validator.addMethod("dateFormat",
    function(value, element) {
        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
    },
    "Please enter a date in the format dd/mm/yyyy."); 	
	
		$('#transfer_connection_id')
		.validate(
				{
					// Rules for form validation
					rules : {
						rrnum : {
							required : true
						},
						pConsumerName : {
							required : true,
							maxlength : 50,
							regex : /^[a-zA-Z .]*$/
						},
						pAddress : {
							maxlength : 500
						},
						tConsumerName : {
							required : true,
							maxlength : 50,
							regex : /^[a-zA-Z .]*$/
						},
						tAddress : {
							maxlength : 500
						},
						pemail : {
							maxlength : 40
						},
						pmobileno : {
							required : true,
							maxlength : 14,
							digits:true
						},
						readingdate : {
							required : true,
							dateFormat: true
						},
						reasonfortranfer : {
							required : true,
							maxlength:500
						},
						pmr : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						tmobileno:{
							required : true,
							maxlength : 14,
							digits:true
						},
					},

					// Messages for form validation
					messages : {
						rrnum : {
							required : 'Please Select RRNumber'
						},
						pConsumerName : {
							required : 'Enter consumer name',
							regex:'Enter Valid Name'
						},
						pAddress : {
							maxlength : 'Address maxlength 500 characters'
						},
						tConsumerName : {
							required : 'Enter consumer name',
							regex:'Enter Valid Name'
						},
						tAddress : {
							maxlength : 'Address maxlength 500 characters'
						},
						pemail : {
							maxlength : 'maxlength 40 characters'
						},
						pmobileno : {
							required : 'Please enter contact number',
							digits:'Enter Numbers',
							maxlength:'maxlength is 14 digits'
						},
						tmobileno : {
							required : 'Please enter contact number',
							digits:'Enter Numbers',
							maxlength:'maxlength is 14 digits'
						},
						readingdate: {
							required : 'Please select Reading date'
						},
						reasonfortranfer: {
							required : 'Enter Reason for Change',
							maxlength:'maxlength is 500 characters'
						},
						pmr: {
							required : 'Enter present reading',
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						
					},

					// Do not change code below
					errorPlacement : function(error,
							element) {
						error.insertAfter(element
								.parent());
					}
				});



});
/* function checkData(param) {
	$("#transfer_connection_id").attr("target", "");
	$("#transfer_connection_id").attr("action", "./ncms/createTransferConnection").submit();
} */
function readScannedImage(input,id) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        //var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.pdf)$/;
        	reader.onload = function (e) {
                $('#'+id).attr('src', e.target.result);
            }
        	  reader.readAsDataURL(input.files[0]);
        } 
}
$("#premisesOwnedfile1").change(function(){
    readScannedImage(this,this.id);
});
$("#existOwnWishFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#partitionFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#successionFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#tenentedFile1").change(function(){
    readScannedImage(this,this.id);
});
</script>		