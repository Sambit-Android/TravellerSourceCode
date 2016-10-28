<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	      App.init();
	   	    	  //TableManaged.init();
	   	    	 TableEditable.init();
	   	    	  if('${BillingDataList}'!="")
	   	    	 {
	   	    		  $('#billId').show();
	   	    	 }
	   	    	  $('#MIS-Reports').addClass('start active ,selected');
	    	   	  $('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
	   	    	     
	   	    	   });
  
  function validation()
  {
	 var stringOnly = /^[A-Za-z0-9\.\s\-]+$/;
	 var numbers = /^[0-9]+$/;
	 var sdCd=document.getElementById("sdoCode").value.trim();
	 	if(sdCd!="")
	 	{
	 		if(!numbers.test(sdCd))
	 		{
	 			bootbox.alert("Please enter only numbers in SDO CODE");
	 			return false;
	 		}
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
  
  </script>
<div class="page-content" >
    
    	<div class="row">
				<div class="col-md-12 ">
				
					<!-- BEGIN SAMPLE FORM PORTLET-->   
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Billing Data Import/Export/Search
							</div>
							<div class="tools">
								<a href="" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="" class="reload"></a>
								<a href="" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<form role="form" action="#">
								<div class="form-body">
									
								
								<div class="form-actions">
									<button type="submit" class="btn blue">Import Billing Data</button>
									<button type="submit" class="btn blue">Export Billing Data</button>                              
								</div>
								</div>
							</form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
		</div>
		</div>
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
								<i class="fa fa-reorder"></i>Billing Data
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
										<select  class="form-control" id="colValue" name="colValue">
													<option value="1">Account Number</option>
													<option value="2">Bill Number</option>
													<option value="3">Bill Amount</option>
										</select>
									
									</div>
									
									<div class="form-group">
										<label >Enter Data for Search</label>
										<div class="form-control">
											<input type="text" class="form-control"  placeholder="" id="searchData" name="searchData">
										</div>
									</div>
								<div class="form-actions">
									<button type="submit" class="btn blue" formaction="./getBillingData" formmethod="post" onclick="return validation();">Submit</button>
									<button type="submit" class="btn default" formaction="./billingData" formmethod="post" >Cancel</button>                       
								</div>
								</div>
							</form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
		</div>
		
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<%-- <div class="portlet box purple" id="billId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Bill Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr style="background-color: lightgray; ">
											<th hidden="true"></th>
									        <th>SlNo</th>
											<th>SDOCode</th>
											<th>Account No.</th>
											<th>Bill Month</th>
											<th>Bill Amount Before Due Date</th>
											<th>Bill Amount After Due Date</th>
											<th>Bill Issue Date</th>
											<th>Bill No.</th>
											<th>Due Date Cash</th>
											<th>Units Consumed</th>
											<th>Vendor</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="count" value="1" scope="page" />
								<c:forEach var="element" items="${BillingDataList}">
										<tr>
										     <td hidden="true"></td>
										     <td>${count}</td>											
											<td>${element[0]}</td>
											<td>${element[1]}</td>
											<td>${element[2]}</td>
											<td>${element[3]}</td>
											<td>${element[4]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[5]}"/></td>
											<td>${element[6]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[7]}"/></td>
											<td>${element[8]}</td>
											<td>${element[9]}</td>
										</tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div> --%>
					<div class="portlet box purple" id="billId" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-edit"></i>Bill Details
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
							<a href="javascript:;"
								class="remove"></a>
						</div>
					</div>
					<div class="portlet-body">

						<div class="table-toolbar">
							<div class="btn-group pull-right">
								<!-- <button class="btn dropdown-toggle" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button> -->
								<!-- <ul class="dropdown-menu pull-right">
									<li><a href="#" id="print">Print</a></li>
									<li><a href="#" id="excelExport"
										onclick="tableToExcel('sample_editable_1', 'Project Management Table')">Export
											to Excel</a></li>
								</ul> -->
							</div>
						</div>


						<a href="#" id="editbutton"></a>

						<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
									<thead>
										<tr style="background-color: lightgray; ">
											<th hidden="true"></th>
									        <th>SlNo</th>
											<th>SDOCode</th>
											<th>Account No.</th>
											<th>Bill Month</th>
											<th>Bill Amount Before Due Date</th>
											<th>Bill Amount After Due Date</th>
											<th>Bill Issue Date</th>
											<th>Bill No.</th>
											<th>Due Date Cash</th>
											<th>Units Consumed</th>
											<th>Vendor</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="count" value="1" scope="page" />
								<c:forEach var="element" items="${BillingDataList}">
										<tr>
										     <td hidden="true"></td>
										     <td>${count}</td>											
											<td>${element[0]}</td>
											<td>${element[1]}</td>
											<td>${element[2]}</td>
											<td>${element[3]}</td>
											<td>${element[4]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[5]}"/></td>
											<td>${element[6]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[7]}"/></td>
											<td>${element[8]}</td>
											<td>${element[9]}</td>
										</tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
									</tbody>
								</table>
					</div>
				</div>
</div>
</div>

<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green" id="paymentId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>Payment Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
								<table class="table table-striped table-hover table-bordered">
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