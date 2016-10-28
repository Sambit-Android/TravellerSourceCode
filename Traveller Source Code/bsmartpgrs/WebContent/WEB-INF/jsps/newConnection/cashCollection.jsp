<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>


<section id="widget-grid" class="">
<div id="content">

        <c:if test="${not empty cashCollectionResult}">
						<script>
							var cashCollectionResult = "${cashCollectionResult}";
							alert(cashCollectionResult);
						</script>
						
			   <c:remove var="cashCollectionResult" scope="session" />
	   </c:if>

  <div class="row">

<article class="col-sm-12 ">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-14" data-widget-editbutton="false" data-widget-custombutton="false">
				
				<header>
					<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
					<h2>Cash Collection </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form:form id="deposit-collection" class="smart-form" commandName="cashCollection" modelAttribute="cashCollection">
							

							<fieldset>
							<div class="row">
								
								<section class="col col-3">
									<label class="label">Location</label><label
											class="input">
											<select class="form-control" id="sitecode" name="sitecode">
													<option value="0" selected="" disabled="">Select Location</option>
													<c:forEach items="${sectionsList}" var="sections">
														<option value="${sections.sitecode}">${sections.text}</option>
													</c:forEach>

											</select>
											</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Receipt Number</label><label
											class="input">
											<form:input type="text" name="recno" id="recno" placeholder="Receipt Number" path="recno"></form:input>
										</label>
								</section>
								
								<section class="col col-2">
									<label class="label">Receipt Date</label><label
											class="input"><i class="icon-append fa fa-calendar"></i>
											<form:input type="text" name="recdate" id="recdate" placeholder="Receipt Date" path="recdate"></form:input>
										</label>
								</section>
								
								<section class="col col-2">
									<label class="label">Amount</label><label
											class="input">
											<form:input type="text" name="amount" id="amount" placeholder="Amount" path="amount"></form:input>
										</label>
								</section>
								
								<section class="col col-2">
											<label class="label"> Towards</label>
											<label class="input">
											
											 <form:select class="form-control" id="towards" name="towards" path="towards">
													<option value="0" selected="" disabled="">Select</option>
													<c:forEach items="${towardsList}" var="sections">
														<form:option value="${sections.description}">${sections.description}</form:option>
													</c:forEach>

											</form:select>
											
											</label>
								</section>
								
								
								
							</div>	
								
							<div class="row">
							
							
								 <section class="col col-3">
											<label class="label">Mode Of Pay</label>
											<label class="input"  > <form:select
												class="form-control" id="paymentmode" path="paymentmode">
													<option value="0" selected="" disabled="">Select</option>
													<option value="CASH">CASH</option>
													<option value="CHEQUE/DD">CHEQUE/DD</option>
													<option value="MO">MO</option>
											</form:select>
											</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Bank/PO Name</label><label
											class="input">
											<form:input type="text" name="bankname" id="bankname" placeholder="Bank Name" path="bankname"></form:input>
										</label>
								</section>
								
								
								<div  id="basic1">
								 
										<section class="col col-3">
											<label class="label">CHEQUE/DD/MO No.</label><label
													class="input">
													<form:input type="text" name="cdmno" id="cdmno" placeholder="CHEQUE/DD/MO No." path="cdmno"></form:input>
												</label>
										</section>
										
										
										<section class="col col-3">
											<label class="label">CHEQUE/DD/MO Date</label><label
													class="input"><i class="icon-append fa fa-calendar"></i>
													<form:input type="text" name="chequedate" id="chequedate" placeholder="CHEQUE/DD/MO Date" path="chequedate"></form:input>
												</label>
										</section>
								 </div>
								
								
								</div>
								
								
							
							
							<div class="row">
								<section class="col col-3">
									<label class="label">Name</label><label
											class="input">
											<form:input type="text" name="name" id="name" placeholder="Name" path="name"></form:input>
										</label>
								</section>
								
								
								<section class="col col-6">
									<label class="label">Address</label><label class="textarea"> 										
										<form:textarea rows="1" name="address" placeholder="Address info" path="address" id="address"></form:textarea> 
									</label>
								</section>
								
								
								<section class="col col-3">
								<button type="button" class="btn btn-primary"
												id="addOption"  onclick="return checkData(0);" style=" margin-left: 131px;height: 31px;margin-top: 39px;padding: 7px 9px 18px 14px;">
												<strong>Submit</strong>
								</button>
								</section>
								
							
							</div>
							
							
							
							</fieldset>

							
						</form:form>						
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			
			
			
			
			
		 
			<div class="jarviswidget" id="wid-id-74" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Cash Collection details</h2>
				
								</header>
								<div>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">
									<thead>
										<tr>
											<th>Receipt Date</th>
											<th>Receipt Number</th>
											<th>Amount</th>
											<th>Name</th>
											
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="app" items="${cashCollectionList}">
											
												<tr>
												<td><fmt:formatDate value="${app.recdate}" pattern="dd-MM-yyyy" /></td>
												<td>${app.recno}</td>
												<td>${app.amount}</td>
												<td>${app.name}</td>
												
												
												</tr>
											
											
										</c:forEach>
									</tbody>

								</table>
				
									</div>
				
								</div>
							</div>
			
			
			
			<!-- end widget -->								


		</article>
		</div></div>
		</section>
		
		
	<script>
	
	$("#paymentmode").change(function(){
		var paymentmode=$("#paymentmode").val();

		if(paymentmode == 'CASH'){
			
			$("#basic1").hide();
			

			
		}else{
		    
			$("#basic1").show();
			

		}
	});	
	
	
	
	
	
	$('#recdate').datepicker({
		  dateFormat : 'dd/mm/yy'
		
	
	}); 
	
	$('#chequedate').datepicker({
		  dateFormat : 'dd/mm/yy'
		
	
	}); 
	
	function checkData(param) {
		$("#deposit-collection").attr("action","./deposit/insertCashCollection").submit();
	}
	
	 $(".content").ready(function() {

			document.getElementById("deposit-collection").reset();

		});   
	 
	
	function getPresentMonthDate(param) {
		var date = new Date();

		var month = date.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var year = date.getFullYear();
		if (param == null || param == "")
			return currentDate = year + "" + month;
		else
			return param;

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
		
		$("#recdate").val(getPresentMonthDate('${selectedMonth1}'));
		
		
		$.validator.addMethod('minStrict', function (value, el, param) {
			    return value > param;
			});
		  
		 
	      $("#deposit-collection").validate({

			// Rules for form validation
			rules : {
				sitecode : {
					required : true
					
				},
				
				recno : {
					required : true
					
				},
				
				recdate : {
					required : true
					
				},
				
				amount : {
					required : true
					
				},
				towards : {
					required : true
					
				},
				paymentmode : {
					required : true
					
				},
				
				bankname : {
					required : true
					
				},
				
				name : {
					required : true
					
				},
				
				address : {
					required : true
					
				},
				
				
				
			},

			// Messages for form validation
			messages : {
				
				
				
				sitecode : {
					required : 'Please select location',
				},
						
				recno : {
					required : 'Please enter receipt number'
				},
				recdate : {
					required : 'Please enter receipt date',
					
				},
				
				amount : {
					required : 'Please enter amount',
					
				},
				
				towards : {
					required : 'Please select this field',
					
				},
				paymentmode : {
					required : 'Please select mode of pay',
					
				},
				
				bankname : {
					required : 'Please enter this field',
					
				},
				
				name : {
					required : 'Please enter this field',
					
				},
				
				address : {
					required : 'Please enter this field',
					
				},
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

		
	     
		}); 
	      
	</script>