<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
p.padding {
	height: 0px;
	padding-left: 365px;
}
</style> 
<script type="text/javascript">	
   	    $(".page-content").ready
   	    (
   	    		
   	    		function()
			   	    	   {     
   	    			$('#admindash').removeClass('Start active ,selected');	
				   	    	  App.init();
				   	    	 TableEditable.init();
				   	    	     FormComponents.init();
				   	    	  	
				   	    	  $('#Circl_Data').addClass('start active ,selected');
					   	    	
				    	   	  $("#dash-board").removeClass('start active ,selected');
			   	    	 				
				   	    	    
							  $('#addData').click
							  (
							  function()
									  { 								  		 
								  		$('#branch').val('0');
								  		$('#holidayName').val('');
					   	    	    	document.getElementById('updateOption').style.display='none';
					 					document.getElementById('addOption').style.display='block'; 
				   	    	           }
							  ); 
				   	    	     
							  $("#print").click
							  (
				   	    	    	function()
				   	    	    	{ 
				   	    	    	   printcontent($(".table-scrollable").html());
				   				    }
				   	    	   );   	    	     
   	    
			   	    	   }  
   	    	); 
   	  function editDetails(param,operation)
	  {
		$('.error').hide(); 
		$("#addOption").hide();
		$("#clearOption").hide();
		$("#updateOption").show();
		
		 $.ajax(
					{
							type : "GET",
							url : "./getSelectedDetails/" + operation,
							dataType : "json",
							cache:false,
							async:false,
							success : function(response)
										{	        
								            
								
								                /*  document.getElementById("sdocode").value=response.slno;
								                 document.getElementById("subdivision").value=response.subdivision; */
								                 $('#sdocodeLoc').attr('disabled', true);
								                 document.getElementById("slno").value=response.slno;
								                 document.getElementById("sdocodeLoc").value=response.sdocode+"-"+response.subdivision;
								                 document.getElementById("company").value=response.company;
								                 document.getElementById("no_bcits_mr").value=response.no_bcits_mr;
								                 document.getElementById("no_jvvnl_mr").value=response.no_jvvnl_mr;
								                 document.getElementById("no_of_binders").value=response.no_of_binders;
								                 document.getElementById("no_cons_to_billed").value=response.no_cons_to_billed;
								                
								                 document.getElementById("sc_date_for_input").value=response.sc_date_for_input;
								                 document.getElementById("input_data_provided_date").value=response.input_data_provided_date;
								                 document.getElementById("ec_given_date").value=response.ec_given_date;
								                 document.getElementById("ec_v_r_date").value=response.ec_v_r_date;
								                 document.getElementById("pr_bill_repo_given_date").value=response.pr_bill_repo_given_date;
								                 document.getElementById("pr_bill_repo_v_r_date").value=response.pr_bill_repo_v_r_date;
								                 document.getElementById("billing_start_date").value=response.billing_start_date;
								                
								                 document.getElementById("no_con_billed").value=response.no_con_billed;
								                 document.getElementById("no_cons_pending").value=response.no_cons_pending;
								               
								                 
												/* if(response != "")
													{ 
													    document.getElementById("d").innerHTML="<font color='red'>Holiday date already exits</font>";
														document.getElementById("holidayDate").value="";
													  	return false;
													} */
										}							
					}
			    );
		  $('#'+param).attr("data-toggle", "modal");
		  $('#'+param).attr("data-target","#stack1");
		  }
   	  
   	function updateSubDivData()
	{
   		  
		   var result=validateForm();
		  
           if(result) 
        	   {
	            $('#myForm').attr('action','./updateSubDivDetails').submit();
        	   }
            else
        	   return false; 
	}	
   	  
     
         
         function hideUpdate()
         {
        	 $("#myForm input").val('');
        	// $("#company").val("Select");
        	 $("#sdocodeLoc").val("Select")
        	 $("#updateOption").hide();
        	 $("#addOption").show();
         }
         function validateForm()
         {
        	 
         	if(document.getElementById("sdocodeLoc").value=="Select")
         		{
         		       bootbox.alert("Please select the SDO Code ");
        		       return false;
         		}
         	else
         		{
         		var split = document.getElementById("sdocodeLoc").value.split('-');
                var  sdocode= split[0];
             		 var subdiv = split[1];
                      
             		 $("#sdocode").attr('disabled',false);
             		 $("#subdivision").attr('disabled',false);
             		 $("#sdocode").val(sdocode);
             		 $("#subdivision").val(subdiv);
         		}
         	 if(document.getElementById("company").value=="Select")
     		  {
     		       bootbox.alert("Please select the Company  ");
    		       return false;
     		  }
         	
         	if(document.getElementById("no_bcits_mr").value!=null && isNaN(document.getElementById("no_bcits_mr").value ))
     		{
     		       bootbox.alert("No Of BCITS Mr must be numeric ");
    		       return false;
     		}
         	
         	if(document.getElementById("no_jvvnl_mr").value!=null && isNaN(document.getElementById("no_jvvnl_mr").value ))
     		{
     		       bootbox.alert("No Of JVVNL Mr must be numeric ");
    		       return false;
     		}
         	
         	if(document.getElementById("no_of_binders").value!=null && isNaN(document.getElementById("no_of_binders").value ))
     		{
     		       bootbox.alert("No Of Binders must be numeric ");
    		       return false;
     		}
         	

         	if(document.getElementById("no_cons_to_billed").value!=null && isNaN(document.getElementById("no_cons_to_billed").value ))
     		{
     		       bootbox.alert("No Of Consumer to bill must be numeric ");
    		       return false;
     		}
         	
         	if(document.getElementById("no_con_billed").value!=null && isNaN(document.getElementById("no_con_billed").value ))
     		{
     		       bootbox.alert("No Of Consumer billed must be numeric ");
     		        return false;
     		}
         	
         	if(document.getElementById("no_cons_pending").value!=null && isNaN(document.getElementById("no_cons_pending").value ))
     		{
     		       bootbox.alert("No Of Consumer pending must be numeric ");
    		       return false;
     		}
         /* 	
        	if(document.getElementById("sdocodeLoc").value!="Select")
        		{
         	var split = document.getElementById("sdocodeLoc").value.split('-');
            var  sdocode= split[0];
         		 var subdiv = split[1];
                  
         		 $("#sdocode").attr('disabled',false);
         		 $("#subdivision").attr('disabled',false);
         		 $("#sdocode").val(sdocode);
         		 $("#subdivision").val(subdiv);
        		} */
        	else
        		return true;
         	
         
         	
         } 
         
         function myFormClear()
         {
        	 $("#myForm input").val('');
        	// $("#company").val("Select");
        	 $("#sdocodeLoc").val("Select")
        	 
         }
</script> 
<div class="page-content">
	<%@include file="pagebreadcrum.jsp"%> 
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN SUCCESS MESSAGE-->
			<c:if test = "${not empty msg}">
			         <div class="alert alert-danger display-show">
					 <button class="close" data-close="alert"></button>
					 <span style="color:red" >${msg}</span>
			       </div>
	                </c:if>
	                <!-- END BEGIN SUCCESS MESSAGE-->

			<!-- BEGIN PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Subdivision Details  
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					
					<div class="portlet-body">
						<div class="table-toolbar">
					 <!-- <div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="importEmployee" onclick="hideUpdate()">
								Add SudDiv Details 
							</button>
						</div>	 -->
							
							 <div class="btn-group pull-right">
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li><a href="#" id="print">Print</a></li>
									<li><a href="#" id="excelExport"
										onclick="tableToExcel('sample_editable_1', 'Holiday Info')">Export
											to Excel</a></li>
								</ul>
							</div> 
						</div>

                  <table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th hidden="true">Id</th>
								<th>Edit</th>
								<th>SDO<br>Code</th>
								<th>SubDiv</th>
								<th>Spot<br>Billingby<br>BCITS/JVVNL</th>
								<th>No.Of<br>BCITS<br>MR</th>
								<th>No  .Of<br>JVVNL<br>MR</th>
								<th>No.Of<br>Binders</th>
								<th>No.Of<br>Cons <br>To Billed</th>
								<th>Schedule<br>date for<br>providing<br>Input<br>Data</th>
								<th>Input<br>data provided<br>date</th>
								<th>Exception<br>given date</th>
								<th>Excptn<br>Verified<br>&Return<br>Date</th>
								<th>Pre-Bill<br>Report<br>Given<br>Date</th>
								<th>Pre-Bill<br>Report<br>Verified<br>&Returned<br>Date</th>
								<th>Billing<br>Start<br>Date</th>
								<th>No.of<br>Consumer<br>Billed</th>
								<th>No.of<br>Consumer<br>Pending</th>
								
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${allDetails}"> 
								 <tr>
								    <td hidden="true">${element.slno}</td>
								    <td><a href="#" onclick="editDetails(this.id,${element.slno})" id="editData${element.slno}">Edit</a></td>
									<td>${element.sdocode}</td>
									<td>${element.subdivision}</td>
									<td>${element.company}</td>
									<td>${element.no_bcits_mr}</td>
									<td>${element.no_jvvnl_mr}</td>
									<td>${element.no_of_binders}</td>
									<td>${element.no_cons_to_billed}</td>
								
									<td>${element.sc_date_for_input}</td>
									<td>${element.input_data_provided_date}</td>
									<td>${element.ec_given_date}</td> 
								    <td>${element.ec_v_r_date}</td>
								    <td>${element.pr_bill_repo_given_date}</td>
								    <td>${element.pr_bill_repo_v_r_date}</td>
								    
								    <td>${element.billing_start_date}</td>
								    <td>${element.no_con_billed}</td>
								    <td>${element.no_cons_pending}</td>
								    
								</tr> 
							 </c:forEach> 
							 <%--  this.id,${element.id}  --%>
						</tbody>
					</table>


					
<%-- 
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th>Edit</th>
								<th hidden="true">Holiday Id</th>
								<th>Holiday Name</th>
								<th>BRANCH</th>
								<th>HOLIDAYDATE</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${getHolidayDetails}">
								<tr>
									<td><a href="#" onclick="edit(this.id,${element.hid})"
										id="editData${element.hid}">Edit</a></td>

									<td hidden="true">${element.hid}</td>

									<td>${element[0]}</td>
									<td>${element.holidayName}</td>
									<td>${element.branch}</td>
									<td><fmt:formatDate value="${element.holidayDate}"
											pattern="dd-MM-YYYY" /></td>

								</tr>
							</c:forEach>

						</tbody>
					</table> --%>
<%-- <div id="stack1" class="modal fade" tabindex="-1" data-width="400">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Upload Circle Details</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
										
			<form:form action="./circleDetailsUpload" modelAttribute="holidayUpload" commandName="holidayUpload" enctype="multipart/form-data" method="post" id="holidayInfo1">
				
				
	 			<div class="form-group">
						<div class="col-md-13">
							<label for="exampleInputFile" class="col-md-5 control-label">Choose File</label>							
						<input type="file" name="file" id="uploadFile" />	
											
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													<button class="btn blue pull-right" style="display: block;" id="uploadOption"  onclick="return validateFile();">Upload</button>
												</div>
				                        </div>
	               </div>
	
	
	</form:form>
																	
											  

										</div>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
					
					<div id="stack1" class="modal fade" tabindex="-1" data-replace="true">
								<div class="modal-dialog modal-wide">
									<div class="modal-content">
									   <div class="modal-header">
									        <h4 class="modal-title">Sub Division Details </h4>
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											
										</div> 
										<form:form action="./addSudDivDivDetailsManualy" modelAttribute="manualsubDivDetails" commandName="manualsubDivDetails" method="post" id="myForm">
										 <div class="modal-body">
										
										
											<table>
											
											 <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">SDOCode</label>
											     <select class="form-control" name="sitecode" id="sdocodeLoc" >
											       <option value="Select">-----------------Select --------------</option>
											       <c:forEach items="${details}" var="elements">
											       <option value="${elements.sitecode}-${elements.locationtype}">${elements.sitecode}-${elements.locationtype}</option>
											       </c:forEach>
											      </select>
											      </div></td>
											 </tr>
											 
											
											 <tr hidden="true">
											 <td>
											 <div class="form-group"> 
											   <label class="control-label col-md-11"></label>
											   <form:input path="slno" class="form-control" type="text" name="slno" id="slno"></form:input>
											   </div>
											   </td>
											   
											 </tr>
											
											
											
											 <tr hidden="true">
											 <td>
											 <div class="form-group"> 
											   <label class="control-label col-md-11"></label>
											   <form:input path="sdocode" class="form-control" type="text" name="sdocode" id="sdocode"></form:input>
											   </div>
											   </td>
											   
											 </tr>
											 
											<tr hidden="true">
											 <td>
											 <div class="form-group"> 
											   <label class="control-label col-md-11"></label>
											   <form:input path="subdivision" class="form-control" type="text" name="subdivision" id="subdivision"></form:input>
											   </div>
											   </td>
											   
											 </tr>
											
											
										  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Spot Billing by BCITS/JVVNL</label>
											   <form:select path="company" class="form-control" name="company" id="company">
											       <form:option value="Select">-------------------Select---------------</form:option> 
											       <form:option value="JVVNL">JVVNL</form:option>
											       <form:option value="BCITS">BCITS</form:option>
											   </form:select>
											  </div>
											  </td>
											 </tr> 
											  <tr>
											 <td>
											 <div class="form-group"> 
											   <label class="control-label col-md-11">No.Of BCITS MR</label>
											   <form:input path="no_bcits_mr" class="form-control" type="text" name="no_bcits_mr" id="no_bcits_mr"></form:input>
											   </div>
											   </td>
											   
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">No.Of JVVNL MR</label>
											  <form:input path="no_jvvnl_mr" class="form-control" type="text" name="no_jvvnl_mr" id="no_jvvnl_mr"></form:input>
											  </div>
											  </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">No.Of Binders</label>
											  <form:input path="no_of_binders" class="form-control" type="text" name="no_of_binders" id="no_of_binders"></form:input>
											  </div>
											  </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">No.Of Cons To be billed</label>
											  <form:input path="no_cons_to_billed" class="form-control" type="text" name="no_cons_to_billed" id="no_cons_to_billed"></form:input>
											  </div>
											  </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Schedule Date for providing Input Data</label>
											 <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="sc_date_for_input" type="text" class="form-control" name="sc_date_for_input" id="sc_date_for_input" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											  </div>
											  </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Input Data Provided Date</label>
											   <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="input_data_provided_date" type="text" class="form-control" name="input_data_provided_date" id="input_data_provided_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											  </div></td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Exceptions Given Date</label>
											  <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="ec_given_date" type="text" class="form-control" name="ec_given_date" id="ec_given_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											  </div>
											  </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Exceptions Verified & Returned Date</label>
											   <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="ec_v_r_date" type="text" class="form-control" name="ec_v_r_date" id="ec_v_r_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											  </div></td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Pre-Bill Report Given Date</label>
											  <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="pr_bill_repo_given_date" type="text" class="form-control" name="pr_bill_repo_given_date" id="pr_bill_repo_given_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											  </div></td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group"> 
											 <label class="control-label col-md-11">Pre-Bill Report Verified & Returned Date</label>
											 <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="pr_bill_repo_v_r_date" type="text" class="form-control" name="pr_bill_repo_v_r_date" id="pr_bill_repo_v_r_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											 </div>
											 </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">Billing Start Date</label>
											 <div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yy" data-date-viewmode="years" id="five">
																<form:input path="billing_start_date" type="text" class="form-control" name="billing_start_date" id="billing_start_date" ></form:input>
																<span class="input-group-btn">
																<button class="btn default" type="button" id="six" > <i class="fa fa-calendar"></i></button>
																</span>
											</div>
											 </div>
											 </td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">No. of Consumers Billed</label>
											  <form:input path="no_con_billed" class="form-control" type="text" name="no_con_billed"></form:input>
											  </div></td>
											 </tr>
											  <tr>
											 <td> 
											 <div class="form-group">
											  <label class="control-label col-md-11">No. of Consumers Pending</label>
											 <form:input path="no_cons_pending" class="form-control" type="text" name="no_cons_pending" id="no_cons_pending"></form:input>
											 </div>
											 </td>
											 </tr>
											</table>
											
											
										</div>
										<div class="modal-footer">
											<button type="button" data-dismiss="modal" class="btn">Close</button>
											<button type="submit" class="btn blue pull-right" id="addOption" onclick="return validateForm();">Add Details</button>
											<button type="button" class="btn blue pull-right" id="updateOption" onclick="updateSubDivData()">Update Details</button>
											<button type="button" class="btn blue pull-right" id="clearOption" onclick="return myFormClear();">Clear</button>
										</div>
										</form:form>
									</div>
								</div>
							</div>
					
					
				</div>
			</div>

</div>

			<!-- END PORTLET-->
		</div>
	</div>

</div>



