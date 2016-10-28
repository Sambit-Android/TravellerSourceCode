<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@page import="java.util.Date"%>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#Ip_Cesc,#LiveStatusData').addClass('start active ,selected');
   	    	   });
  setInterval(
		  function(){
		    $.ajax(
					{
						type : "GET",
						url : "./GetCescIpAllrealTimeStatusData",		
						dataType:'json',
						cache:false,
						 success : function(response)
						 {
							 $('#displayValues').empty();
							  var record="";
							 for(var i=0;i<=response.length;i++) 
							 {
								 var val='';
								 var val1='';
								 var ipLiveCount=response[i].ipCountVal;
		                         var dtcLiveCount=response[i].dtcCountVal;
		                         var deviceLiveCount=response[i].deviceCountVal;
		                         var SubLiveCount=response[i].subDivisionCountVal;
								 var dtcList=response[i].dtcCount;
								 var ipList=response[i].ipCount;
								 /* if (typeof x !== "undefined") {
									    //Do something since x is defined.
									} */
									if(undefined==dtcList[i])
										{
										  val='0';
										}
									else
										{
										  val=dtcList[i];
										}
									if(undefined==ipList[i])
									{
									  val1='0';
									}
								else
									{
									  val1=ipList[i];
									}
									
									record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+val+"</td><td>"+val1+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
									
								 /* if(undefined!=dtcList[i])
									 {
									    record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+dtcList[i]+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
									 }
								 else if(undefined==dtcList[i])
									 {
									    record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+val+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
									 } */
								 
								 //record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+dtcList1+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
								 if((response.length-1)==i)
							    	{
									 $('#displayValues').append(record);
									 $('#CountDtc').html(dtcLiveCount);
									 $('#countIpSets').html(ipLiveCount);
									 $('#countDevice').html(deviceLiveCount);
									 $('#countSdo').html(SubLiveCount);
							    	}
							 }
							 
						 }
				 }
		  );  
		 
		  }
		  , 1000*60*1);
  
  function details() {
	  
	  $('#appnd').html('('+$('#fromDate').val()+')');
	    $.ajax(
				{
					type : "GET",
					url : "./GetCescIpAllrealTimeStatusBAsedData",		
					dataType:'json',
					data:{
						date:$('#fromDate').val()
					},
					cache:false,
					 success : function(response)
					 {
						 $('#displayValues').empty();
						 $('#CountDtc').html(0);
						 $('#countIpSets').html(0);
						 $('#countDevice').html(0);
						 $('#countSdo').html(0);
						  var record="";
						 for(var i=0;i<=response.length;i++) 
						 {
							 var val='';
							 var val1='';
							 var ipLiveCount=response[i].ipCountVal;
	                         var dtcLiveCount=response[i].dtcCountVal;
	                         var deviceLiveCount=response[i].deviceCountVal;
	                         var SubLiveCount=response[i].subDivisionCountVal;
							 var dtcList=response[i].dtcCount;
							 var ipList=response[i].ipCount;
							 /* if (typeof x !== "undefined") {
								    //Do something since x is defined.
								} */
								if(undefined==dtcList[i])
									{
									  val='0';
									}
								else
									{
									  val=dtcList[i];
									}
								if(undefined==ipList[i])
								{
								  val1='0';
								}
							else
								{
								  val1=ipList[i];
								}
								
								record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+val+"</td><td>"+val1+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
								
							 /* if(undefined!=dtcList[i])
								 {
								    record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+dtcList[i]+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
								 }
							 else if(undefined==dtcList[i])
								 {
								    record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+val+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
								 } */
							 
							 //record+="<tr><td>"+parseInt(i+1)+"</td><td>"+response[i].sitecode+"</td><td>"+response[i].username+"</td><td>"+dtcList1+"</td><td>"+ipList[i]+"</td><td>"+response[i].time+" "+response[i].date+"</td></tr>";
							 if((response.length-1)==i)
						    	{
								 $('#displayValues').append(record);
								 $('#CountDtc').html(dtcLiveCount);
								 $('#countIpSets').html(ipLiveCount);
								 $('#countDevice').html(deviceLiveCount);
								 $('#countSdo').html(SubLiveCount);
						    	}
						 }
						 
					 }
			 }
	  ); 
	//  return false;
	
}
  </script>
<div class="page-content" >
<div class="top-news">
								
								<a id="tesss" class="btn blue" data-target="#stack5" data-toggle="modal">
								<span>Cesc Ip Live Status</span>
								<!-- <em>Date: September 02, 2014</em> -->
								<em>Date:&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="<%=new Date()%>"/></em>
								<em>
								<i class="fa fa-tags"></i>
								Live updated from the server .......................
								</em>
								<i class="fa fa-globe top-news-icon"></i>                             
								</a>
								
							</div> 
							<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountDtc">${dtcCountVal}
							</div>
							<div class="desc">                           
								Toatal DTC
							</div>
						</div>
						<!-- <a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>  -->                
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-user"></i>
						</div>
						<div class="details" >
							<div class="number" id="countIpSets">${ipCountVal}</div>
							<div class="desc">Total Ip Sets</div>
						</div>
						<!-- <a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>  -->                
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="number" id="countDevice">${deviceCountVal}</div>
							<div class="desc">Total Devices</div>
						</div>
						<!-- <a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a> -->                 
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number" id="countSdo">${subDivisionCountVal}</div>
							<div class="desc">Total Subdivision</div>
						</div>
						<!-- <a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a> -->                 
					</div>
				</div> 
			</div>
			<div class="row ">
				<div class="col-md-12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bell-o"></i>Live Status of Meter ReaderWise &nbsp;<span id="appnd"></span></div>
							
						</div>
	<div class="portlet-body">
			<div class="scroller" style="height: 500px;" data-always-visible="1" data-rail-visible="0">
					<ul class="feeds">
							<li>
								<div class="col1">
									<div class="cont">
													 
						<div class="cont-col1">
						<div class="desc">						
						<table class="responsive table table-bordered" id="displayData">
									<thead>
										<tr>
										    <th>SlNo</th>
											<th >Subdivision</th>
											<th >Device<br>Operator</th>
											<th >Totoal Dtc</th>
											<th >Totoal Ip Sets</th>
											<th >Time and Date</th>
											
										</tr>
									</thead>
									<tbody id="displayValues">
									 <c:set var="count" value="0" scope="page" />
									 <c:forEach var="element" items="${listOfValues}">
									 <tr>
										 	<td>${count+1}</td>
										 	<td>${element.sitecode}</td>
										  	<td>${element.username}</td>
										  	<c:choose >
										  	   <c:when test="${not empty element.dtcCount[count]}">
										  	   <td>${element.dtcCount[count]}</td>
										  	   </c:when>
										  	   <c:otherwise>
										  	   <td>0</td>
										  	   </c:otherwise>
										  	</c:choose>
										  	<c:choose >
										  	   <c:when test="${not empty element.ipCount[count]}">
										  	   <td>${element.ipCount[count]}</td>
										  	   </c:when>
										  	   <c:otherwise>
										  	   <td>0</td>
										  	   </c:otherwise>
										  	</c:choose>
										  	<td>${element.time}&nbsp;&nbsp;${element.date}</td>
									 </tr>
									 <c:set var="count" value="${count + 1}" scope="page"/>
									 </c:forEach>
									</tbody>
								</table>
															
														</div>
													</div>
												</div>
											</div>
											<!-- <div class="col2">
												<div class="date">
													20 mins
												</div>
											</div> -->
										
									</li>
									
									
								</ul>
		</div>
		         
					
					
		            </div>
					</div>
				</div>				
			</div>
			
				
</div>

<div id="stack5" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body">
											<div class="row">
											<div class="col-md-12">
											
												<table>
											
												<tr>
												<td> Date</td>
												<td><font color="red"></font></td>
												<td>
												<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text" id="fromDate"
																value="<fmt:formatDate value="${currentDate}" pattern="dd/MM/yyyy" />" data-date="${currentDate}"
																data-date-format="dd/mm/yyyy" data-date-viewmode="years" name="fromDate" />
														</div>
												</td>
												</tr>
												
												
												
											
																									  
												</table>
													<div class="modal-footer">
													     <button class="btn blue pull-right" data-dismiss="modal" onclick="return details()">Show Details</button>
													     <button type="button" data-dismiss="modal" class="btn">Cancel</button>
													</div>
													
											</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>