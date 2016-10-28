<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#Ip_Cesc,#cescipdashboard').addClass('start active ,selected');
   	    	   });
  
  
  function viewFeederData(id,name,param)
	 {
	   var html="";
		  $.ajax({
			  type:"POST",
			  url:"./getFeederDataDashboard",
			  data:{"name":name,"param":param},
			  dataType:"json",
			  success:function(response)
			  {
				if(response!=null)
				{
				  for(var i=0;i<response.length;i++)
					  {
					  var res=response[i];
					  html=html+"<tr><td>"+res[1] +"-"+res[2]+"</td><td>"+res[0]+"</td></tr>";
					  }
				}
				
				$('#table_3').dataTable().fnClearTable();
  				$('#feederDataDetailedId').html(html);	  
  				  },
  				complete:function(response)
				{	
					loadSearchAndFilter('table_3');
				}
		  });  
		    $('#'+id).attr("data-toggle", "modal");//edit button
			$('#'+id).attr("data-target","#stack5"); //edit button
			
	 }
  
  function viewDTCData(id,name,param)
	 {
	   var html="";
		  $.ajax({
			  type:"POST",
			  url:"./getDTCDataDashboard",
			  data:{"name":name,"param":param},
			  dataType:"json",
			  success:function(response)
			  {
				if(response!=null)
				{
				  for(var i=0;i<response.length;i++)
					  {
					  var res=response[i];
					  html=html+"<tr><td>"+dateFormating(res[0]) +"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td></tr>";
					  }
				}
				
				$('#table_4').dataTable().fnClearTable();
				$('#DTCDataDetailedId').html(html);	  
				  },
				complete:function(response)
				{	
					loadSearchAndFilter('table_4');
				}
		  });  
		    $('#'+id).attr("data-toggle", "modal");//edit button
			$('#'+id).attr("data-target","#stack6"); //edit button
	 }
	 
  function viewIPData(id,name,param)
	 {
	   var html="";
		  $.ajax({
			  type:"POST",
			  url:"./getIPDataDashboard",
			  data:{"name":name,"param":param},
			  dataType:"json",
			  success:function(response)
			  {
				if(response!=null)
				{
				  for(var i=0;i<response.length;i++)
					  {
					  var res=response[i];
					  html=html+"<tr><td>"+res[0] +"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td><td>"+res[6]+"</td></tr>";
					  }
				}
				
				$('#table_5').dataTable().fnClearTable();
				$('#IPDataDetailedId').html(html);	  
				  },
				complete:function(response)
				{	
					loadSearchAndFilter('table_5');
				}
		  });  
		    $('#'+id).attr("data-toggle", "modal");//edit button
			$('#'+id).attr("data-target","#stack7"); //edit button
	 }
  
  function viewIPAuthorizedData(id,name,param)
	 {
	   var html="";
		  $.ajax({
			  type:"POST",
			  url:"./getIPAuthorizedDataDashboard",
			  data:{"name":name,"param":param},
			  dataType:"json",
			  success:function(response)
			  {
				if(response!=null)
				{
					for(var i=0;i<response.length;i++)
					  {
					  var res=response[i];
					  html=html+"<tr><td>"+res[0] +"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td><td>"+res[6]+"</td></tr>";
					  }
				}
				
				$('#table_6').dataTable().fnClearTable();
				$('#IPAuthorizedDataDetailedId').html(html);	  
				  },
				complete:function(response)
				{	
					loadSearchAndFilter('table_6');
				}
		  });  
		    $('#'+id).attr("data-toggle", "modal");//edit button
			$('#'+id).attr("data-target","#stack8"); //edit button
	 }
  
  function viewIPUnAuthorizedData(id,name,param)
	 {
	   var html="";
		  $.ajax({
			  type:"POST",
			  url:"./getIPUnAuthorizedDataDashboard",
			  data:{"name":name,"param":param},
			  dataType:"json",
			  success:function(response)
			  {
				if(response!=null)
				{
					for(var i=0;i<response.length;i++)
					  {
					  var res=response[i];
					  html=html+"<tr><td>"+res[0] +"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td><td>"+res[6]+"</td></tr>";
					  }
				}
				
				$('#table_7').dataTable().fnClearTable();
				$('#IPUnAuthorizedDataDetailedId').html(html);	  
				  },
				complete:function(response)
				{	
					loadSearchAndFilter('table_7');
				}
		  });  
		    $('#'+id).attr("data-toggle", "modal");//edit button
			$('#'+id).attr("data-target","#stack9"); //edit button
	 }
  
  </script>
<div class="page-content" >
<div class="row">
 <div class="col-md-6">					
					<div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-coffee"></i>Enumeration As Per Work Awarded</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>Lot/District</th>
											<th>DTC</th>
											<th >IPsets</th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>HASSAN</td>
											<td>23000</td>
											<td >87000</td>
											<td>110000</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Mandya</td>
											<td>22800</td>
											<td >85000</td>
											<td>107800</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Mysore</td>
											<td>16000</td>
											<td >61000</td>
											<td>77000</td>
										</tr>
										<tr>
											<td>4</td>
											<td>Works(Mandya,Chamarajanagar)</td>
											<td>21000</td>
											<td >70000</td>
											<td>91000</td>	</tr>
											<tr>
											<td>5</td>
											<td>Total</td>
											<td>82800</td>
											<td>303000</td>
											<td>385800</td>	</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END BORDERED TABLE PORTLET -->
				</div>
				
				<div class="col-md-6">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-cogs"></i>Progress As On Date</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>Circle</th>
											<th>Feeders</th>
											<th>DTC</th>
											<th>IPsets</th>
											<th>Authorised</th>
											<th>UnAuthorised</th>
										</tr>
									</thead>
									<tbody>
									<c:set var="count" value="0" scope="page"></c:set>
									<c:set var="totDtc" value="0" scope="page"></c:set>
									<c:set var="totFeed" value="0" scope="page"></c:set>
									<c:set var="totAuth" value="0" scope="page"></c:set>
									<c:set var="totUnAuth" value="0" scope="page"></c:set>
									<c:forEach items="${circleData}" var="element">
									    <c:set var="count" value="${count+1}" scope="page"></c:set>
									     <tr>
											<td>${count}</td>
											<td>${element[0]}-${element[1]}</td>
											<td ><a href='#' name="${element[1]}" id="showFeeders${element[1]}"   onclick="return viewFeederData(this.id,this.name,'Non Total')">${element[2]}</a></td>
											<td ><a href='#' name="${element[1]}"  id="showDTCS${element[1]}"      onclick="return viewDTCData(this.id,this.name,'Non Total')">${element[3]}</a></td>
											<td ><a href='#' name="${element[1]}"  id="showTotalIPS${element[1]}"  onclick="return viewIPData(this.id,this.name,'Non Total')" >${element[4]+element[5]}</a></td>
											<td ><a href='#' name="${element[1]}"  id="showTotalAuthorizedIPS${element[1]}"  onclick="return viewIPAuthorizedData(this.id,this.name,'Non Total')">${element[4]}</a></td>
											<td ><a href='#' name="${element[1]}"  id="showTotalUnAuthorizedIPS${element[1]}"  onclick="return viewIPUnAuthorizedData(this.id,this.name,'Non Total')">${element[5]}</a></td>
										</tr>
										<c:set var="totDtc" value="${totDtc+element[3]}" scope="page"></c:set>
										<c:set var="totFeed" value="${totFeed+element[2]}" scope="page"></c:set>
										<c:set var="totAuth" value="${totAuth+element[4]}" scope="page"></c:set>
										<c:set var="totUnAuth" value="${totUnAuth+element[5]}" scope="page"></c:set>
									</c:forEach>
									    <tr>
											<td>${count+1}</td>
											<td>Total</td>
											<td><a id="totFeed" name="totFeed" href="#" onclick="return viewFeederData(this.id,this.name,'Total')">${totFeed}</a></td>
											<td><a id="totDtc" name="totDtc" href="#" onclick="return viewDTCData(this.id,this.name,'Total')">${totDtc}</a></td>
											<td><a id="totalIp" name="totalIp" href="#" onclick="return viewIPData(this.id,this.name,'Total')">${totAuth+totUnAuth}</a></td>
											<td><a id="IPAuthorized" name="Authorized" href="#" onclick="return viewIPAuthorizedData(this.id,this.name,'Total')">${totAuth}</a></td>
											<td><a id="IPUnAuthorized" name="IPUnAuthorized" href="#" onclick="return viewIPUnAuthorizedData(this.id,this.name,'Total')">${totUnAuth}</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
				
			</div>

           <div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								${totDtc}
							</div>
							<div class="desc">                           
								Total DTC
							</div>
						</div>						           
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-user"></i>
						</div>
						<div class="details" >
							<div class="number" id="countBmd">${totAuth}</div>
							<div class="desc">Authorised IP</div>
						</div>						              
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="number" id="countSdo">${totUnAuth}</div>
							<div class="desc">UnAuthorised IP</div>
						</div>						              
					</div>
				</div>				
			</div>
		
					<div id="stack5" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body">
											<div class="row">
			                          <table class="table table-striped table-hover table-bordered" id="table_3">
											<thead class="flip-content">
												<tr>
												    <th>sitecode-Name</th>
													<th>FeederCode</th>
													<!-- <th>FeederName</th> -->
												</tr>
											</thead>
											<tbody id="feederDataDetailedId">
											</tbody>
										</table>
									</div>
										</div>
									</div>
								</div>
					</div>
					
					<div id="stack6" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body">
											<div class="row">
			                          <table class="table table-striped table-hover table-bordered" id="table_4">
											<thead class="flip-content">
												<tr>
												    <th>Date Of Inspection</th>
													<th>Village</th>
													<th>Location</th>
													<th>Capacity</th>
													<th>DTC Code</th>
													<th>Nature of Load</th>
													<!-- <th>Total IP'S</th>
													<th>DTC Meter</th> -->
												</tr>
											</thead>
											<tbody id="DTCDataDetailedId">
											</tbody>
										</table>
									</div>
										</div>
									</div>
								</div>
					</div>
					
					<div id="stack7" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body">
											<div class="row">
			                            <table class="table table-striped table-hover table-bordered" id="table_5">
											<thead class="flip-content">
												<tr>
												    <th>RRNO</th>
													<th>Feeder Code</th>
													<th>Sub<br>Station</th>
													<th>DTC Code</th>
													<th>Sanction<br/>Load</th>
													<th>Authorized/<br/>unAuthorized</th>
													<th>Village</th> 
												</tr>
											</thead>
											<tbody id="IPDataDetailedId">
											</tbody>
										</table>
									</div>
										</div>
									</div>
								</div>
					</div>
					
					<div id="stack8" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body">
											<div class="row">
			                            <table class="table table-striped table-hover table-bordered" id="table_6">
											<thead class="flip-content">
												<tr>
												    <th>RRNO</th>
													<th>Feeder Code</th>
													<th>Sub<br>Station</th>
													<th>DTC Code</th>
													<th>Sanction<br/>Load</th>
													<th>Authorized/<br/>unAuthorized</th>
													<th>Village</th> 
												</tr>
											</thead>
											<tbody id="IPAuthorizedDataDetailedId">
											</tbody>
										</table>
									</div>
										</div>
									</div>
								</div>
					</div>
					
					<div id="stack9" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										</div>
										<div class="modal-body">
											<div class="row">
			                            <table class="table table-striped table-hover table-bordered" id="table_7">
											<thead class="flip-content">
												<tr>
												    <th>RRNO</th>
													<th>Feeder Code</th>
													<th>Sub<br>Station</th>
													<th>DTC Code</th>
													<th>Sanction<br/>Load</th>
													<th>Authorized/<br/>unAuthorized</th>
													<th>Village</th> 
												</tr>
											</thead>
											<tbody id="IPUnAuthorizedDataDetailedId">
											</tbody>
										</table>
									</div>
										</div>
									</div>
								</div>
					</div>
</div>