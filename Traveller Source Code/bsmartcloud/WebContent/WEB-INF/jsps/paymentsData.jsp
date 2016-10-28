<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	      App.init();
   	    	   displaySearchAndFilter();
	   	    	  if('${paymentsList}'!="")
	   	    		  {
	   	    		       $('#paymentId').show();
	   	    		  }
	   	    	$('#MIS-Reports').addClass('start active ,selected');
	    	   	  $('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
	   	    	   	     
	   	    	   });
  
  
 
 function validation()
 {
	 var stringOnly = /^[A-Za-z0-9\.\s\-]+$/;
	 var numbers = /^[0-9]+$/;
	 var sdCd=document.getElementById("sdoCode").value.trim();
	 	if(sdCd!="" )
	 	{
	 		if(!numbers.test(sdCd))
	 		{
	 			bootbox.alert("Please enter only numbers in SDO CODE");
	 			return false;
	 		}
	 	}
	 	else if($('#colValue').val()=="4" )
	 	{
	 		bootbox.alert("Please enter SDO CODE");
 			return false;
	 	}
	 	var payMode=document.getElementById("paymentMode").value.trim();
		 if(payMode=='select')
			{
			    bootbox.alert("Please select Payment mode.");
				return false;
			}
	 var srDt=document.getElementById("searchData").value.trim();
	 if(srDt=='')
		{
		    bootbox.alert("Please Enter the Data for Search");
			return false;
		}
	 else if(!stringOnly.test(srDt))
		{
		    bootbox.alert("Special characters are not allowed in Search Data");
			return false;
		}
	
	 
 }
 
 function displaySearchAndFilter()
 {  
		 $('#table_3').dataTable().fnDestroy();
	  $('#table_3').dataTable(
			  {
				 
				               "aLengthMenu": [
				                    [10, 50, 100, -1],
				                    [10, 50, 100, "All"] // change per page values here
				                    
				                ],
			  "iDisplayLength": 20
			  });
	  jQuery('#table_3_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input
     jQuery('#table_3_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
     jQuery('#table_3_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
 } 
 
  
 function showPaymentMode()
 {
	 if($('#colValue').val()=="4")
		 {
		       $('#searchDataId').hide();
		       $('#searchPayementId').show();
		 }else
			 {
			    $('#searchDataId').show();
		       $('#searchPayementId').hide();
			 }
 } 
  
  
  </script>
<div class="page-content" >
    
    	<div class="row">
				<%-- <div class="col-md-12 ">
					<!-- BEGIN SAMPLE FORM PORTLET-->   
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Payments Data Import/Export/Search
							</div>
							<div class="tools">
								<a href="" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="" class="reload"></a>
								<a href="" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<form role="form">
								<div class="form-body">
									
								
								<div class="form-actions">
									<button type="submit" class="btn blue">Import Payments Data</button>
									<button type="submit" class="btn blue">Export Payments Data</button>                              
								</div>
							</form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
		</div> --%>
		<div class="row">
				<div class="col-md-12 ">
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
					<!-- BEGIN SAMPLE FORM PORTLET-->   
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>View Transaction History
							</div>
							<div class="tools">
								<a href="" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="" class="reload"></a>
								<a href="" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<form role="form">
								<div class="form-body">
								
								    <div class="form-group">
										<label >Enter SDO Code</label>
										<input type="text" class="form-control input-lg"  placeholder="" id="sdoCode" name="sdoCode">
									</div>
									
									<div class="form-group">
										<label >Search By</label>
										<select   class="form-control" id="colValue" name="colValue" onchange="return showPaymentMode();">
													<option value="1">Account Number</option>
													<option value="2">Receipt Number</option>
													<option value="3">Amount</option>
													<!-- <option value="4">Mode of Payment</option> -->
										</select>
									</div>
									<div class="form-group" id="searchDataId"  >
										<label >Enter Data for Search</label>
										<div class="form-control">
											<input type="text" class="form-control"  placeholder="" id="searchData" name="searchData" >
										</div>
									</div>
									<div class="form-group"  >
										<label >Select Mode of Payment</label>
										<select   class="form-control" id="paymentMode" name="paymentMode" >
										            <option value="select">Select</option>
										            <option value="all">All</option>
													<option value="cash">Cash</option>
													<option value="cheque">Cheque</option>
													<option value="online">Online</option>
										</select>
									</div>
								<div class="form-actions" >
									<button type="submit" class="btn blue"  formaction="./getPaymentsData" formmethod="post" onclick="return validation();">View</button>
									<button type="submit" class="btn default" formaction="./paymentsData" formmethod="post" >Cancel</button>                              
								</div>
								</div>
							</form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
		</div>
		
			
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green" id="paymentId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Transaction Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="table_3">
									<thead>
										<tr style="background-color: lightgray;">
										    <th hidden="true"></th>
									        <th>SlNo</th>
											<th>SDOCode</th>
											<th>Consumer No.</th>
											<th>Payment Date</th>
											<th>Amount</th>
											<th>Receipt No.</th>
											<th>Mode of Payment</th>
											<th>Vendor</th>
										</tr>
									</thead>
									<tbody>
									<c:set var="count" value="1" scope="page" />
								<c:forEach var="element" items="${paymentsList}">
										<tr>
										     <td hidden="true"></td>
										     <td>${count}</td>											
											<td>${element[0]}</td>
											<td>${element[1]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[2]}"/></td>
											<td>${element[3]}</td>
											<td>${element[4]}</td>
											<td>${element[5]}</td>
											<td>${element[6]}</td>
										</tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
								
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
				
			</div>
</div>
</div>


