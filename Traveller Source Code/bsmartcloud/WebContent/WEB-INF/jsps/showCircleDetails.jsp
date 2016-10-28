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
				   	    	  	
				   	    	  $('#admin-holiday').addClass('start active ,selected');
							  			
				   	    	    
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
   	  /*   function edit(param,opera)
     	{ 
        	 document.getElementById("holidayInfo1").reset();
     		 var operation=parseInt(opera); 
     			  $.ajax(
     						{
     								type : "GET",
     								url : "./editHolidayData/" + operation,
     								dataType : "json",
     								cache:false,
     								async:false,
     								success : function(response)
     											{ 
     																document.getElementById("hid").value=response.hid;
     																document.getElementById("holidayName").value=response.holidayName;     																 
     																document.getElementById("branch").value=response.branch;
     																document.getElementById("holidayDate").value=dateFormating(response.holidayDate);
     																document.getElementById('updateOption').style.display='block';
    															    document.getElementById('addOption').style.display = 'none';  
     															}							
     						}
     				    );
     			  
     		$('#'+param).attr("data-toggle", "modal");//edit button
     		$('#'+param).attr("data-target","#stack2"); //edit button
     	}  */
       /*       
         function xyz()
         { 
        	  var holidayDate=document.getElementById("holidayDate").value.trim(); 
        	  var branch= document.getElementById("branch").value.trim(); 
       	  $.ajax(
   					{
   							type : "GET",
   							url : "./getHolidayDetails/" + holidayDate + "/" + branch,
   							dataType : "json",
   							cache:false,
   							asynch:false,
   							success : function(response)
   										{	        
   								document.getElementById("d").innerHTML="";
   												if(response != "")
   													{ 
   													    document.getElementById("d").innerHTML="<font color='red'>Holiday date already exits</font>";
   														document.getElementById("holidayDate").value="";
													  	return false;
   													}
   										}							
   					}
   			    );
         } */
   /*       function  validations()
         {
        	 var letters = /^([a-zA-Z\s])+$/;
        	 var branch=document.getElementById("branch").value.trim();
        	  
		    	if(branch=='' || branch == 0)
				{					
			    	 
			    		 bootbox.alert("Please choose branch name");
						 return false;
			    	 
				}
		    	var holidatDate=document.getElementById("holidayDate").value.trim();
		    	if(holidatDate=='')
				{
					 					    	 
			    		 bootbox.alert("Please select the holiday date");
						 return false;
			    	 
				}
		    	 var holidayName=document.getElementById("holidayName").value.trim();
			    	if(holidayName=='')
					{					 
				    	 
				    		 bootbox.alert("Please enter the holiday Name");
							 return false;
				    	 
					}
			    	else if(!letters.test(holidayName))
 			    	{
			    		 bootbox.alert("Special Characters are not allowed in Holiday Name");
						 return false;
			    	}	 
         }   
         */
         
         function validateFile()
         {
        	
        
        	
         	 if(extension.trim()==''|| extension.trim()==null)
         		{
         		       bootbox.alert("Please select the file to upload ");
        		       return false;
         		}
         	
         } 
</script> 
<div class="page-content">
	<%-- <%@include file="pagebreadcrum.jsp"%> --%>
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
						<i class="fa fa-edit"></i>Circle Details 
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					
					<div class="portlet-body">
						<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="importEmployee" >
								Import Circle Details 
							</button>
						</div>	
							
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


					<a href="#" id="editbutton"></a>
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
<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
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
					</div>
					
				</div>
			</div>

</div>

			<!-- END PORTLET-->
		</div>
	</div>

</div>



