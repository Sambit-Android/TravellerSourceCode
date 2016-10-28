<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {  	  	
	  			if('${billingDataAlert}'!="")
	  			{	  				
	  				$("#billDatatable").show();
	  			}
   	    	     App.init();
   	    	     TableManaged.init();
   	    	  $('#MIS-Reports').addClass('start active ,selected');
    	   	  $('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
   	    	     
   	    	   });
  
  
 function validation1()
  {
	  var numbers = /^[0-9]+$/;
	  var stringOnly = /^[A-Za-z0-9]+$/;
	  var sdocode=document.getElementById("sdoCode").value.trim();
		 if(sdocode=='')
			 {
				   bootbox.alert("Please enter SDO Code");
				   return false;
			 }
		 else if(!numbers.test(sdocode))
			 {
				 bootbox.alert("Please enter only numbers in SDO Code");
				 return false;
			 }
		 
	var consNo=document.getElementById("consumerNo").value.trim();
		 if(consNo=='')
		 {
			   bootbox.alert("Please enter Consumer No.");
			   return false;
		 }
	 else if(!stringOnly.test(consNo))
		 {
			 bootbox.alert("Special characters are not allowed in Consumer No.");
			 return false;
		 }
  }
  
  
</script>

<div class="page-content" >
	<!-- BEGIN PAGE CONTENT-->
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
								<i class="fa fa-reorder"></i> Push Notification Alerts
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
										<label >Enter Consumer Number</label>
										<input type="text" class="form-control input-lg"  placeholder="" id="consumerNo" name="consumerNo">
									</div>
									
								<div class="form-actions" >
									<button type="submit" class="btn blue" formaction="./getBillAlert" formmethod="post" onclick="return validation1();">View and Send Notification</button>
								</div>
								
								</div>
							</form>
						</div>
					</div>
				</div>	
		</div>		
		
		 <div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box purple" id="billDatatable" style="display:none" >
						
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
								<table class="table table-striped table-hover">
									<thead>
										<tr>
											
									        
											<th>SDOCode</th>
											<th>AccNo.</th>
											<th>BillMonth</th>
											<th>BillAmount Before DueDate</th>
											<th>BillAmount After DueDate</th>
											<th>BillIssue Date</th>
											<th>BillNo.</th>
											<th>DueDateCash</th>
											<th>UnitsConsumed</th>
											<th>Vendor</th>
											<!-- <th>Date/Time</th> -->
											<th>Notification</th>
										</tr>
									</thead>
									<tbody>
										
								<c:forEach var="element" items="${billingDataAlert}">
										<tr>
											<td>${element[0]}</td>
											<td>${element[1]}</td>
											<td>${element[2]}</td>
											<td>${element[3]}</td>
											<td>${element[4]}</td>
											<td>${element[5]}</td>
											<td>${element[6]}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy" value="${element[7]}"/></td>
											<td>${element[8]}</td>
											<td>${element[9]}</td>
											
											<td>
											<form>
											<input type="hidden" name="sdoCode" value="${element[0]}"/>
											<input type="hidden" name="accNo" value="${element[1]}"/>
											<input type="hidden" name="billMonth" value="${element[2]}"/>
											<input type="hidden" name="billIssueDate" value="${element[5]}"/>
											<button  type="submit" class="clsActionButton" style="background-color:lightgreen"   formaction="./updateBillAlertDate" formmethod="post">Send Alert</button>
											</form>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
			</div> 
		
		
		
		<div class="row">
		<!-- <div id="loading" style="display:none;"><img src="resources/assets/img/ajax-loading.gif" alt="" /></div> -->
				<div class="col-md-12 ">
					<!-- BEGIN SAMPLE FORM PORTLET-->   
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> Notification Alert Configuration Manager
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
										<label  class="">What to Read</label>
										<div class="radio-list">
											<label>
											<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> 
											Enable nightly Notification Alerts
											</label>
											<label>
											<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" > 
											Enable Instantaneous Notification Alerts
											</label>
											<label>
											<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" > 
											Disable Notification Alerts
											</label>
											
										</div>
									
									<div class="form-actions">
									<button type="submit" class="btn blue">Set Notification Alerts</button>
									                            
									</div>
								
							</form>
						</div>
					</div>
				</div>	
		</div>		
</div>