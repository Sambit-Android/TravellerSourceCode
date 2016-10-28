<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
p.padding 
{
    height:0px;
    padding-left:365px;
}
</style>


<script type="text/javascript">	
   	    $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	     TableEditable.init();
   	    	  $('#admin-location').addClass('start active ,selected');
			  $('#dash,#admindash').removeClass('start active ,selected');					
   	    	    
			  $('#addData').click(function()
   	    	             {
				            //$("#company").attr("disabled",true);
				            //$("#zone").attr("disabled",true);
		   	    	    	$('#locationName1,#locationType1,#siteCode1').hide();
				  
				  /*   
   	    	    	locType='0';
   	    	    	
   	    	    	 $('#locationname1,#levelOne').hide();
   	    	    	 $('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4,#selectContainer5').html('');   
   	    	    	
   	    	    	 document.getElementById('locationManagement').reset();
   	    	    	 $('#locationtype,#level').val('0');
   	    	    	$('#locationType1,#level1').show();	
   	    	    	$('#locationCategory1').show(); */	
   	    	    	  
   	    	    	document.getElementById('updateOption').style.display='none';
 					document.getElementById('addOption').style.display='block'; 
   	    	             }); 
   	    	     $("#print").click(function(){ 
   	    	    	 printcontent($(".table-scrollable").html());
   				});   	    	     
   	    	    }); 
   	    
   		/*  var locType=""; 
   		 var locCat="";
   		 var i=0;
         var  maxLevel="";  
         var operation1=""; 
        function chooseLocatioType(param)
         {  
        	 $('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4,#selectContainer5,#locationname').html('');
        	 $('#locationname1').hide();
        	 $('#level').val('0');        	
        	locType=param;    
         } 
         function chooseLocationCategory(param)
         { 
        	$('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4,#selectContainer5,#locationname').html('');
        	 $('#locationname1').hide();
        	 $('#level').val('0');        	
        	 locCat=param;
        	 operation1=locCat;
         }
         
         
         function chooseLevel(param)
         {         	 
        	 var locType=document.getElementById('locationtype').value;
        	 var locCat=document.getElementById('locationcategory').value;
        	 
        	 if(locType=="0" || locType == "" )
        		 {
        		 bootbox.alert('Please Select The Location Type');
        		 return false;
        		 }
        	 
        	  if(locCat=="0" || locCat == "" )
    		 {
    		 bootbox.alert('Please Select the Location Category');
    		 return false;
    		 } 
    		 
        
        	 if(param == 0)
        		 {
        		 bootbox.alert('Invalid Selection ...PLease select other Level ');
        		 return false;
        		 } 
        	     i=2;
        	 $('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4,#selectContainer5').html('');
        	 $('#locationname1').hide();
        	   
        	  if(param==1)
        		 {        		
        		 $('#locationname1').show();        		 
        		 document.forms[0].codeVal.value=10;
        		 } 
        	
        	else {        		
        		 var operation=locType;       		         		
        		 var level=param;
        		 maxLevel=param;      		 
        		 ajaxCall(operation,level,10);          		
        		 }    
         } 
         
        
         function ajaxCall(operation,level,digits)
         { 
        	if(i<=maxLevel)
           {
        	 $.ajax({
				    type : "GET",
				    url : "./getLocationNames/"+operation+"/"+i+"/"+digits+"/"+operation1,
				    dataType : "json",
				    cache:false,
				    success : function(response )
		    		  {
		    				  if(response=='' || response==null)
				    	{
				    		var q=i-1;
				    		bootbox.alert('There is no Location under level '+q+' Please First add Location for level '+q);	
				    		if(i>2)
				    			{
				    			i--;
				    			}				    		
				    	}
				    	else
				    		{
				    		var p=i-1;
				    	var html = '<td>Level '+p+' </td><td><font color="red">*</font></td> <td><select class=form-control placeholder-no-fix id=abc'+p+' onchange=digitCodes(this.value)><option value=0>Select</option>';
				    	for(var j=0;j<response.length;j++)
				    		{
				    		  
				          html += '<option value='+response[j].locationcode+'>'+response[j].locationname+'</option>';
				    		}
				    	html+='...</select></td>';
				        $('#selectContainer'+p).append(html);
				    		}				    	
				    },
				    error : function(response)
				    {
				    	window.location.href="./logout";
				    }
			});
        	 
          }
        	else
			 {	
        	 i=2;
			 $('#locationname1').show(); 
			 }
        	
         }         
          function digitCodes(param)
         {    
        	  var k=i-1;
        	 document.forms[0].codeVal.value=param; 
        	 document.getElementById("abc"+k).disabled=true;
    		 i++;    	
        	 ajaxCall(locType,param,10);       	 
        		 
         }         
         
          function edit(param,opera)
			 {         	 
        	  
        	  $('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4').html(''); 
        	 $('#locationname1').show();        	
	    	var operation=parseInt(opera);
	    	$.ajax({
			    type : "GET",
			    url : "./editLocationManagement/" + operation,
			    dataType : "json",
			    cache:false,
			    success : function(response )
	    		  {	    	
			    	document.getElementById("locationcode").value=response.locationcode;
			    	document.getElementById("locationname").value=(response.locationname).toUpperCase();
			    	document.getElementById("locationtype").value=response.locationtype;
			    	document.getElementById("locationcategory").value=response.locationcategory;
			    	document.getElementById("level").value=response.level;			    	
			    	$('#locationType1,#level1,#locationCategory1').hide();
					document.getElementById('updateOption').style.display='block';
					document.getElementById('addOption').style.display='none';
			    },
			    error : function(response)
			    {
			    	window.location.href="./logout";
			    }
	    		  
		});
	    	
	     $('#'+param).attr("data-toggle", "modal");
		 $('#'+param).attr("data-target","#stack1");
			
	} 
          
  function deleteRecord(param,opera)
	{         	 
	  bootbox.confirm("Are you sure to delete this Location?", function(confirmed) 
	{
	        if(confirmed==true)
	        {
	        	var operation=parseInt(opera);
		    	$.ajax({
				    type : "GET",
				    url : "./deleteLocation/" + operation,
				    dataType : "json",
				    cache:false,
				    success : function(response )
		    		  {	    	
				    	if(response=='deleted')
				    	{
				    		window.location.href="./adminLocation?flag=0";
				    	}
				    	else if(response=='notDeleted')
				    	{
				    		window.location.href="./adminLocation?flag=1";
				    	}
				    	else
				    	{
				    		window.location.href="./adminLocation";
				    	}
				    	
				      }
			});
	        }
	        
	 });
	    	
			
	} 
          
  function validateLocName()
  {
	  var lcnm=document.getElementById("locationname").value.trim();
	  $.ajax(
				{
						type : "GET",
						url : "./valLocationName/" + lcnm,
						dataType : "json",
						cache:false,
						async:false,
						success : function(response)
									{	        	
							             for (var i = 0; i < response.length; i++) 
							             {
											if(response[i].locationname!=null)
												{
													bootbox.alert("Location name"+"    "+"<font color='red'>"+lcnm+"</font>"+"    "+"already exists.");
												  	    return false;
												}
										
							             }
									}							
				}
		    );
  }
  
  
  
  
  
  
  
          
          
  var letters = /^([a-zA-Z0-9\s\-])+$/;
     function validations(param)
     {        	 
    	
      	
     	  /* $.ajax(
 					{
 							type : "GET",
 							url : "./valLocationName/" + lcnm,
 							dataType : "json",
 							cache:false,
 							async:false,
 							success : function(response)
 										{	               	
 								             for (var i = 0; i < response.length; i++) 
 								             {
 												if(response[i].locationname==lcnm)
 													{
 														alert("Location name already exists");
													  	    
 													}
 											
 								             }
 										}							
 					}
 			    ); 
    	 
    	 
    	 
    	  var locType=document.getElementById('locationtype').value;
        	  if(param=='1')
        {
	           if(locType=="0" || locType == "" )
	     		 {
	     		  bootbox.alert('Please Select The Location Type');
	     		  return false;
	     		 } 
	           if(locCat=="0" || locCat == "" )
	    		 {
	    		 bootbox.alert('Please Select the Location Category');
	    		 return false;
	    		 }
	           
	           var levels=document.getElementById("level").value;
        	  if(levels =='' ||levels==0)
       	        {
       		  bootbox.alert('Please Select the level');
       		  return false;
       	        }
        		  }
        	 var locName=document.getElementById("locationname").value.trim();
        	  if(locName=='')
        	   {
        		  bootbox.alert('Enter location Name');
        		  return false;
        	   }
        	  else if(!letters.test(locName))
        		  {
        		  	bootbox.alert('Please enter only alphabets in location name.');
        		 	 return false;
        		  }
        	  
        	  
        	  if(param==1)
    		  {
        		  $('#locationManagement').attr('action',"./addLocationData");
    		  } 
        	  else
        		  {        		  
        		  $('#locationManagement').attr('action',"./updateLocationData");
        		  } 
          }
          
          
         	  */
           
         	  
         	 function chooseLevel(level)
              {         	 
         		 
         		 if(level==1)
         			 {
         			 	 $('#locationType1').show();
         			 	$('#locationType').val('CIRCLE');
         				 $('#locationName1').show();
           		   		 $('#siteCode1').show();
         			 
         			 }
             	 if(level==2||level==3 ||level==4)
             		 {
             		   $('#locationType1').show();
             		  $('#locationName1').hide();
        		   		 $('#siteCode1').hide();
             		  if(level==2)
        			   {
        			     $('#locationType').val('DIVISION');
        			   }
	             		 if(level==3)
	       			   {
	       			     $('#locationType').val('SUBDIVISION');
	       			   }
	             		if(level==4)
	      			   {
	      			     $('#locationType').val('SECTION');
	      			   }
             		 }
              } 
         	  
         	  
              
         	  var letters = /^([a-zA-Z0-9\s\-])+$/;
         	     function validations(param)
         	     {    
         	    	 
         	    	 alert(document.getElementById('company').value);
         	    	if(param==1)
          		  {
         	    		document.forms[0].codeVal.value=10;
              		  $('#locationdata').attr('action',"./addLocationData");
          		  } 
              	  else
              	  {        		  
              		  $('#locationdata').attr('action',"./updateLocationData");
              		} 
         	    	 
         	     }
         	  
         	  
         	  
       
</script>



	<div class="page-content" >
		<%@include file="pagebreadcrum.jsp"%>
		<div class="row">
			<div class="col-md-12">
					<c:if test = "${results ne 'notDisplay'}"> 			        
			         <div class="alert alert-danger display-show">
							<button class="close" data-close="alert"></button>
							<span style="color:red" >${results}</span>
						</div>
			        </c:if>	
				<!-- BEGIN PORTLET-->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-edit"></i>Location management
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
							<a href="javascript:;"
								class="remove"></a>
						</div>
					</div>
					<div class="portlet-body">

						<div class="table-toolbar">
							<div class="btn-group">
								<button class="btn green" data-target="#stack1"
									data-toggle="modal" id="addData">
									Add New Location <i class="fa fa-plus"></i>
								</button>
								<!-- <a class="btn green" data-target="#stack1" data-toggle="modal"  style="color: green" id="addData">Add New Department &nbsp;<i class="fa fa-plus"></i></a> -->
							</div>
							<div class="btn-group pull-right">
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li><a href="#" id="print">Print</a></li>
									<li><a href="#" id="excelExport"
										onclick="tableToExcel('sample_editable_1', 'Locations')">Export
											to Excel</a></li>
								</ul>
							</div>
						</div>


						

						<table class="table table-striped table-hover table-bordered"
							id="sample_editable_1">
							<thead>
								<tr>
									<th>Company</th>
									<th>Zone</th>
									<th>Location Type</th>																
									<th>Location Name</th>
									<th>Location Code</th>
									<th>Level</th>
									<th>SiteCode</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="element" items="${locationList}">
									<tr>
									    <td>${fn:toUpperCase(element.company)}</td>
										<td>${fn:toUpperCase(element.zone)}</td>
										<td>${element.locationType}</td>
										<td>${element.locationName}</td>
										<td>${element.locationCode}</td>
										<td>${element.level}</td>
										<td>${element.siteCode}</td>
										<td><a href="#" onclick="edit(this.id,${element.locationCode})" id="editData${element.locationCode}">Edit</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true"></button>
										<h4 class="modal-title">Add Location Details</h4>
										<p class="padding">'<font color="red">*</font>'&nbsp;Indicates mandatory fields.</p>
									</div>

									<div class="modal-body" id='mainDiv'>
										<div class="row">
											<div class="col-md-12">
												<form:form action="" modelAttribute="locationdata"
													commandName="locationdata" method="post"
													id="locationdata">
													<table>
														<tr hidden="true" id="locationcode1">
															<td>Location Id</td>
															<td><form:input path="locationCode"
																	id="locationCode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationCode"></form:input></td>
														</tr>
														
														<tr id="company1"  >
															<td>Company</td>
															<td><font color="red">*</font></td>
															<td><form:select  path="company" id="company"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="company" >
																	<form:option value="0">Select</form:option>
																	<c:forEach items="${companyList}" var="companyVal" >
																		<form:option value="${companyVal}"
																			>${companyVal}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="zone1">
															<td>Zone</td>
															<td><font color="red">*</font></td>
															<td><form:select path="zone" id="zone"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="zone" >
																	<c:forEach items="${zoneList}" var="zoneVal">
																	<form:option value="0">Select</form:option>
																		<form:option value="${zoneVal}"
																			>${zoneVal}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="level1">
															<td>Level</td>
															<td><font color="red">*</font></td>
															<td><form:select path="level" id="level"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="level" onchange="chooseLevel(this.value)">
																	<form:option value="0">Select</form:option>
																	<c:forEach items="${LocationLevel}" var="locLevel">
																		<form:option value="${locLevel}"
																			>${locLevel}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="locationType1">
															<td>Location Type</td>
															<td><font color="red">*</font></td>
															<td><form:select path="locationType"
																	id="locationType"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationType" onchange="chooseLocatioType(this.value);">
																	<form:option value="0">Select</form:option>
																	<c:forEach items="${LocationTypes}" var="location">
																		<form:option value="${location}"
																			>${location}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														

														<tr id="selectContainer1">
														</tr>
														<tr id="selectContainer2">
														</tr>
														<tr id="selectContainer3">
														</tr>
														
														
														<tr id="locationName1">
															<td>Location Name</td>
															<td><font color="red">*</font></td>
															<td><form:input path="locationName"
																	id="locationName"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationName"  ></form:input></td>
														</tr>
														
														<tr id="siteCode1">
															<td>SiteCode</td>
															<td><font color="red">*</font></td>
															<td><form:input path="siteCode"
																	id="siteCode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="siteCode"></form:input></td>
														</tr>
														
														<tr>
														<tr>
															<td><input type="hidden" name="codeVal" value=""  /></td>
														</tr>
														
														
													</table>
													 <div class="modal-footer">
														<button type="button" data-dismiss="modal" class="btn"
															id="closeData" >Close</button>
														<button class="btn blue pull-right"
															style="display: block;" id="updateOption"															
															onclick="return validations('0');">Update Location
															</button>
														<button class="btn blue pull-right"
															style="display: block;" id="addOption"															
															onclick="return validations('1');">ADD Location
															</button>
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
				<!-- END PORTLET-->
				</div>
				</div>
				</div>
				