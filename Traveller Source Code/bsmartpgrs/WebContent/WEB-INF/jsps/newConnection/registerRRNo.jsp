<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">
	
	<section id="widget-grid" class="">
		
		<c:if test="${not empty rrnoRegResult}">
			<script>
				var result = "${rrnoRegResult}";
				alert(result);
			</script>

			<c:remove var="rrnoRegResult" scope="session" />
		</c:if>
		

		<div class="row">

			<article class="col-sm-12 ">
				<div class="jarviswidget" id="wid-id-rrno"
					data-widget-editbutton="false" data-widget-custombutton="false">

					<header>
						<span class="widget-icon"><i class="fa fa-database"></i>
						</span>
						<h2>Register RR Number</h2>

					</header>

					<div>

						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<form:form action="./ncms/createRRNo" method="post" id="rr-num-registraion"
								class="smart-form" commandName="registerRRNo"
								modelAttribute="registerRRNo"
								enctype="multipart/form-data" novalidate="novalidate"
								autocomplete="off">
								
								<fieldset>
									<div class="row">
										
										<section class="col col-4">
											<label class="label">Location&nbsp;<font color="red">*</font></label> <label
												class="select"> 
												
											<select id="sitecode" name="sitecode" class="form-control">
												<option value="" selected="" disabled="">Select
													Location</option>
												<c:forEach items="${sectionList}" var="section">
													<option value="${section.sitecode}">${section.sectionname}</option>
												</c:forEach>
											</select>
											
											
											<i></i></label>
										</section>

										<section class="col col-4">
											<label class="label">RR No&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-tachometer"></i> <input type="text"
													name="rrnum" id="rrnum" placeholder="RR Number" onchange="checkduplicateRRNo(this.value)" onkeyup="convertToUpperCase();"></input>
											</label>
										</section>

										

									</div>


								</fieldset>


								<footer>
									<button type="submit" class="btn btn-primary" id="addOption">
										<strong>Add</strong>
									</button>
								</footer>
							</form:form>

						</div>

					</div>

				</div>
			</article>
			
			
			
			<article class="col-sm-12 ">
				
					<div class="jarviswidget"
						id="rrno-reg-history" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered RR Number's</h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">


					               <thead>
										<tr>
											<th>Location</th>
											<th>RR No.</th>
											
											
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="app" items="${regRRNoList}">
											
											<tr>
												<td>${app.location}</td>
												<td>${app.RRNumber}</td>
											</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>

					</div>
			</article>
			
		
		</div>
	</section>
</div>


<script>

function convertToUpperCase(){
	
	$("#rrnum").val($("#rrnum").val().trim().toUpperCase());
	
}

function checkduplicateRRNo(rrno){
	
	var sitecode=$("#sitecode").val();
	var sitecode=1223;
	
	$.ajax({
		url : "./NCMS/getConsumerDetails",
		type : "GET",
		dataType : "JSON",
		async : false,
		data : {
			rrnumberval : rrno,
			sitecode : sitecode

		},
		success : function(response) {
			var data=response[0];
			
			if(data==null){
				alert("RR No not available");
				$("#rrnum").val(" ");
			}
			
		}
	});
	
		
	$.ajax({
		type : "GET",
		url : "./checkDuplicateRegRRNo",
		dataType : "text",
		data : {

			rrno : rrno,
			sitecode:sitecode
		},
		success : function(response) {
			
			if (response == "NF") {

			} else {
				alert("RR No already exists in this Location");
				$("#rrnum").val(" ");
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
				return regexpr.test(value);
			}, "");
		
	      $("#rr-num-registraion").validate({

			// Rules for form validation
			rules : {
				sitecode : {
					required : true
				},
				
				rrnum : {
					required : true,
					maxlength:15,
					regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
				},
				
			},

			// Messages for form validation
			messages : {
				
				
				
				sitecode : {
					required : 'please select location',
				
				},
				
				rrnum : {
					required : 'Enter RR No',
					maxlength:'maxlength is 15 characters',
					regex : 'Enter valid RR No'
				},
				
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

	   
	     
		}); 

	
	      
	</script>
	
	<style>
th {
	vertical-align: middle !important
}

.popover-content {
	overflow: overlay;
	max-height: 250px;
}

.sparkline {
	width: 100% !important
}

.easy-pie-title {
	overflow: visible !important;
}
</style>