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
			<c:if test="${not empty lcsuccess}">
						<script>
							var result = "${lcsuccess}";
							alert(result);
						</script>
				<c:remove var="lcsuccess" scope="session" />
	   </c:if>
	   
			<article class="col-sm-12 col-md-12">
			<div class="jarviswidget jarviswidget-sortable jarviswidget-color-darken" id="wid-id-41" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa fa-ban"></i> </span>
					<h2>Load Change (Increase Load/Decrease Load)</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<form:form action="" id="load_change_id" method="post" commandName="application" class="smart-form"
									modelAttribute="loadChange" enctype="multipart/form-data" novalidate="novalidate" autocomplete="off">
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
									<label class="label">Consumer&nbsp;Name&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="ConsumerName" name="ConsumerName" id="ConsumerName" placeholder="Consumer Name"></form:input></label>
								</section>	
								
								<section class="col col-3">
									<label class="label">Email Id</label>
									<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
										<form:input type="email" class="input-md" path="email" name="email" placeholder="E-mail"></form:input>
									</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Contact No&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-prepend fa fa-mobile"></i>
										<form:input type="text" class="input-md" path="mobileno" name="mobileno" placeholder="Contact No"></form:input>
									</label>
								</section>
								
							</div>
							
							<div class="row">
							
								<section class="col col-3">
									<label class="label">Reading Date&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-append fa fa-calendar"></i>
										<form:input type="text"	name="readingdate" placeholder="Reading Date" path="readingdate" id="readingdate" autocomplete="off"></form:input>
									</label>
								</section>
								
								<section  class="col col-3">
									<label class="label">Present Reading&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="pmr" name="pmr" id="pmr" placeholder="Present Reading"></form:input></label>
								</section>
								
								<section class="col col-6">
									<label class="textarea"> Consumer Address									
										<form:textarea type="text"	name="address" placeholder="Consumer Address" path="address" id="address" rows="1"></form:textarea>
									</label>
							    </section>
								
							</div>
							
							 
							
							 <div class="row">										
						
								<section  class="col col-3">		
									<label class="label">Existing Load(KW)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="existingloadkw" name="existingloadkw" id="existingloadkw" placeholder="Existing Load(KW)"></form:input></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">Existing Load(HP)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="existingloadhp" name="existingloadhp" id="existingloadhp" placeholder="Existing Load(HP)"></form:input></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">Existing Load(KVA)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="existingloadkva" name="existingloadkva" id="existingloadkva" placeholder="Existing Load(KVA)"></form:input></label>
								</section>
								
								<!-- <section class="col col-3">
								<label class="label">Load&nbsp;Required&nbsp;<font color="red">*</font></label> <label
									class="select"> <select id="loadRequired" name="loadRequired">
										<option value="" selected="" disabled="">Select</option>
											<option value="Addition of Load">Addition of Load</option>
											<option value="Reduction of Load">Reduction of Load</option>
								</select><i></i></label>
							    </section> -->
							    
							    
							    <section  class="col col-3">		
									<label class="label">New Load(KW)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="newloadkw" name="newloadkw" id="newloadkw" placeholder="New Load(KW)"></form:input></label>
								</section>
						
						    </div>
						    
						    <div class="row">
								
								
								
								<section  class="col col-3">		
									<label class="label">New Load(HP)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="newloadhp" name="newloadhp" id="newloadhp" placeholder="New Load(HP)"></form:input></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">New Load(KVA)&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="newloadkva" name="newloadkva" id="newloadkva" placeholder="New Load(KVA)"></form:input></label>
								</section>
								
								<section class="col col-6">
									<label class="textarea"> Reason for Change <font color="red">*</font>									
										<form:textarea type="text"	name="reason" placeholder="Reason for Change" path="reason" id="reason" rows="1"></form:textarea>
									</label>
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
								<div>

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
								<div>

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
								<button type="submit" class="btn btn-primary" onclick="return checkData(0);">
									Submit
								</button>
								<!-- <button type="button" class="btn btn-default" onclick="window.history.back();">
									Back
								</button> -->
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
						id="tariffchange111" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered Load Change Details</h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>
											<th>RR&nbsp;No.</th>
											<th>Consumer&nbsp;Name</th>
											<th>Contact No</th>
											<th>Existing Load(KW)</th>
											<th>Existing Load(HP)</th>
											<th>Existing Load(KVA)</th>
											<th>New Load(KW) </th>
											<th>New Load(HP)</th>
											<th>New Load(KVA)</th>
											<th>Status</th>
											<th>Reason</th>
											<th>Reading Date</th>
											<th>Present MR </th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="app" items="${loadChangeList}">
											
											<tr>
												<td>${app.rrnum}</td>
												<td>${app.ConsumerName}</td>
												<td>${app.mobileno}</td>
												<td>${app.existingloadkw}</td>
												<td>${app.existingloadhp}</td>
												<td>${app.existingloadkva}</td>
												<td>${app.newloadkw}</td>
												<td>${app.newloadhp}</td>
												<td>${app.newloadkva}</td>
												<td>${app.status}</td>
												<td>${app.reasonfortranfer}</td>
												<td><fmt:formatDate value="${app.readingdate}" pattern="dd-MM-yyyy" /></td>
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
			
			$('#ConsumerName').val(data.name);
			if(data.email==0){
				$('#email').val();
			}else{
				$('#email').val(data.email);
			}
			$('#mobileno').val(data.telephoneno);
			$('#readingdate').val();
			$('#pmr').val();
			$('#address').val(data.address+","+data.villagename);
			$('#existingloadkw').val(data.sanctionedkw);
			$('#existingloadhp').val(data.sanctionedhp);
			$('#existingloadkva').val(0);
			
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
	 
		$('#load_change_id')
		.validate(
				{
					// Rules for form validation
					rules : {
						rrnum : {
							required : true
						},
						ConsumerName : {
							required : true,
							maxlength : 50,
							regex : /^[a-zA-Z .]*$/
						},
						address : {
							maxlength : 500
						},
						
						email : {
							maxlength : 40
						},
						mobileno : {
							required : true,
							maxlength : 14,
							digits:true
						},
						readingdate : {
							required : true,
							dateFormat: true
						},
						reason : {
							required : true,
							maxlength:500
						},
						pmr : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						existingloadkw : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						
						existingloadhp : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						
						existingloadkva : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						newloadkw : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						newloadhp : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						newloadkva : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						
					},

					// Messages for form validation
					messages : {
						rrnum : {
							required : 'Please Select RRNumber'
						},
						ConsumerName : {
							required : 'Enter consumer name',
							regex:'Enter Valid Name'
						},
						address : {
							maxlength : 'Address maxlength 500 characters'
						},
						
						email : {
							maxlength : 'maxlength 40 characters'
						},
						mobileno : {
							required : 'Please enter contact number',
							digits:'Enter Numbers',
							maxlength:'maxlength is 14 digits'
						},
						readingdate: {
							required : 'Please select Reading date'
						},
						reason: {
							required : 'Enter Reason for Change',
							maxlength:'maxlength is 500 characters'
						},
						pmr: {
							required : 'Enter present reading',
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						
						existingloadkw: {
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						existingloadhp: {
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						existingloadkva: {
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						newloadkw: {
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						newloadhp: {
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						newloadkva: {
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
function checkData(param) {
	$("#load_change_id").attr("target", "");
	$("#load_change_id").attr("action", "./ncms/createLoadChange").submit();
}
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