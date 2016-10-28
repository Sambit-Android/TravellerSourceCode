<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
   	    		
   	    		
   	    			if("${dom}"==28)
   	    		     {
   	    				$("#dayy29").hide();
   	    		        $("#dayy30").hide();
   	    		        $("#dayy31").hide();
   	    		       
   	    		     }
   	    			 
   	    			if("${dom}"==29)
  	    		     {
  	    			
  	    				
  	    		        $("#dayy30").hide();
  	    		        $("#dayy31").hide();
  	    		       
  	    		     }
   	    			
   	    			if("${dom}"==30)
 	    		     {
 	    				
 	    				 $("#dayy31").hide();
 	    			}
   	    		 
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
        	 var group=document.getElementById("group").value;
        	
         	 if(group=="select")
         		{
         	
         		       bootbox.alert("Please select the Group ");
        		       return false;
         		}
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
						<i class="fa fa-edit"></i>Meter Reader Wise Reports 
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div> 
				</div>
				 
					
					<div class="portlet-body">
					
					<form:form action="./meterReaderWiseSearchReport" modelAttribute="hhbmEntity" commandName="hhbmEntity">
					  <table>
					  
					   <tr>
											 <td>
											 <div class="form-group"> 
											 <label class="control-label col-md-11">Billed Month</label>
											 <div class="input-group input-medium date date-picker"  data-date-format="yyyymm" data-date-viewmode="years" id="five">
																<form:input path="billMonth" type="text" class="form-control" name="billMonth" id="billMonth" value="${month}" readonly="true"></form:input>
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
											 <label class="control-label col-md-11">Group</label>
											 
												<select name="group" class="form-control" id="group">
												 <option value="select">--------Select-------</option>
												 <option value="01">01</option>
												  <option value="02">02</option>
												   <option value="03">03</option>
												    <option value="04">04</option>
												     <option value="05">05</option>
												      <option value="06">06</option>
												       <option value="07">07</option>
												        <option value="08">08</option>
												         <option value="15">15</option>
												          <option value="16">16</option>
												           <option value="17">17</option>
												            <option value="18">18</option>
												             <option value="21">21</option>
												              <option value="22">22</option>
												               <option value="23">23</option>
												                <option value="24">24</option>
												               
												</select>				
											</div>
											 
											   </td>
											   
											 
						              </tr>
					    
					  
					<%--    <tr>
											 <td>
											 <div class="form-group"> 
											   <label class="control-label col-md-11">Consumer Number</label>
											   <form:input path="consumer_sc_no" class="form-control" type="text" name="consumer_sc_no"></form:input>
											   </div>
											   </td>
											   
											 </tr> --%>
											 
					  </table>
					  <div class="modal-footer">
											
											<button type="submit" class="btn blue pull-right" id="serachID" onclick="return validateFile()">Search</button> &nbsp;&nbsp;&nbsp;
											<!-- <button type="button" class="btn blue pull-right" id="clearID">Clear</button> -->
										</div>
					  </form:form>
					
						<!-- <div class="table-toolbar"> -->
						<!--  <div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="importEmployee" >
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
						<!-- </div> -->

                  <table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th hidden="true">Id</th>
								<th>SDOCode</th>
								<th>SubDiv</th>
								<th>BCITS/JVVNL</th>
								<th>Name of MR</th>
								<!-- <th>Binders</th> -->
								<th>No.Of Cons To Billed</th>
								<th>No.Of Cons Billed</th>
								<th>No.Of Cons Pending</th>
							   <c:forEach var="elem" items="${dayss}">
                                         <th>${elem}</th>
                               </c:forEach>
								<!-- <th>Day 11</th>
								<th>Day 12</th>
								<th>Day 13</th>
								<th>Day 14</th>
								<th>Day 15</th>
								<th>Day 16</th>
								<th>Day 17</th>
								<th>Day 18</th>
								<th>Day 19</th>
								<th>Day 20</th>
								<th>Day 21</th>
								<th>Day 22</th>
								<th>Day 23</th>
							
								<th>Day 24</th>
								<th>Day 25</th>
								<th>Day 26</th>
								<th>Day 27</th>
								<th>Day 28</th>
								<th id="dayy29">Day 29</th>
								<th id="dayy30">Day 30</th>
								<th id="dayy31">Day 31</th>
								<th>Day 26</th> -->
								
								
								<%-- <th>Billing Start Date</th>
								<th>No. of Consumers Billed</th>
								<th>No. of Consumers Pending</th>
								 <c:set var="count" scope="page" value="1"/>
								<c:forEach var="element" items="${serachOne}" >
                                 <c:forEach var="i" items="${searchThree}">
								     <c:if test="${element[0] eq i[0] && element[3] eq i[1]}">
								     <td>Day ${count}</td>
								     <c:set var="count" scope="page" value="${count+1}"/>
								     </c:if>
								      
								    </c:forEach>
                                 <c:set var="count" scope="page" value="1"/>
                                </c:forEach>
                                       
                                      
                                                
                               
       
								 --%>
								 
								
							</tr>
						</thead>
						<tbody>
						    
						 <c:if test="${not empty serachOne}">
						 <c:set value="0" var="counts" scope="page"></c:set>
						 <%-- <c:set value="0" var="billedd" scope="page"></c:set> --%>
						   <c:forEach var="element" items="${serachOne}" >
						   
						    <tr>
						     <td hidden="true"></td>
									<td><c:out value="${element[0]}"></c:out></td>
									<td><c:out value="${element[1]}"></c:out></td>
									<td><c:out value="${element[2]}"></c:out></td>
									<td><c:out value="${element[3]}"></c:out></td>
									<td><c:out value="${element[4]}"></c:out><c:set var="nctb" value="${element[4]}"></c:set></td>
									<td><c:out value="${element[5]}"></c:out><c:set var="ncb" value="${element[5]}"></c:set></td>
									
									<td><c:out value="${nctb-ncb}"></c:out></td>
									  
                                   <c:set var="bills" value="${fn:split(newList[counts], '$')}" />
									<c:forEach var="elem" items="${dayss}">	
									<c:set value="0" var="billedd" scope="page"></c:set>								
									<c:forEach var="i" begin="0" end="${fn:length(bills)}">
									<c:set var="billParts" value="${fn:split(bills[i], '@')}" />
									
									<c:choose>
									<c:when test="${billParts[1] eq elem}">
									<td>${billParts[0]}</td>
									<c:set value="1" var="billedd" scope="page"></c:set>									
									</c:when>									
									</c:choose>									
									</c:forEach>
                                    <c:if test="${billedd eq '0'}">
                                    <td></td>                                    
                                    </c:if>
                                    </c:forEach>
									 
									</tr>
								<c:set value="${counts+1}" var="counts" scope="page"></c:set>	 
						    
						   </c:forEach>
						  
						    </c:if> 
						  
						     <%--   <c:if test="${not empty serachOne}">
						       
						       <c:set var="count" scope="page" value="0"/>
						        <c:forEach var="element" items="${serachOne}" >
						         <c:set var="eleTwo" scope="page" value="${searchTwo[count]}"/>
								  <tr>
								  
								    <td hidden="true"></td>
									<td><c:out value="${element[0]}"></c:out></td>
									<td><c:out value="${element[1]}"></c:out></td>
									<td><c:out value="${element[2]}"></c:out></td>
									<td><c:out value="${element[3]}"></c:out></td>
									<td><c:out value=" "></c:out></td>
									<td><c:out value="${element[4]}"></c:out><c:set var="nbtp"  value="${element[4]}"></c:set></td>
								    
								    <td><c:out value=""></c:out></td>
			
								    <td><c:out value="${eleTwo[2]}"></c:out><c:set var="nbp" value="${eleTwo[2]}"></c:set></td>
								    <td><c:out value="${nbtp-nbp}"></c:out></td> 
								    
								     <c:forEach var="i" items="${searchThree}">
								     <c:if test="${element[0] eq i[0] && element[3] eq i[1]}">
								     <td>${i[3]}</td>
								     </c:if>
								     
								    </c:forEach> 
								    <c:forEach var="i" begin="1" end="${10}">
								      
                                         <td></td>
                                     
                                    </c:forEach> 
								   </tr> 
								    <c:set var="count" scope="page" value="${count+1}"/>
								   </c:forEach> 
							    </c:if>
							    	
								  <c:if test="${not empty searchTwo}">
							        <c:forEach var="elements" items="${searchTwo}" >
							        <tr>
									<td><c:out value="${elements[2]}"></c:out></td>
									<td><c:out value="${elements[3]}"></c:out><c:set var="nbp" value="${elements[3]}"></c:set></td>
									<td><c:out value="${nbtp-nbp}"></c:out></td>
									<td><c:out value="${elements[3]}"></c:out></td>
									</tr>
									</c:forEach>
								 </c:if> 
								 
								 <c:if test="${empty searchTwo}">
						
									<td><c:out value=" "></c:out></td>
									<td><c:out value=" "></c:out></td>
									<td><c:out value=" "></c:out></td>
								 </c:if>  
								  
							      <c:forEach var="i" begin="1" end="${dom}">
                                      <td>${i}</td>
                                  </c:forEach>  
								    
								     
								   
								 --%>
								
							 
					
							 
						</tbody>
					</table>
					
					


					

				
					
					
				</div>
			 

</div>

			<!-- END PORTLET-->
		</div>
	</div>

</div>



