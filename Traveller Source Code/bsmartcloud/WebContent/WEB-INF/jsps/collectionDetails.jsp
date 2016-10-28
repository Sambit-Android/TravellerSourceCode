<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>
<script type="text/javascript">
  	$(".page-content").ready(function()
  		   {    	
  		    	App.init();
  		    	TableEditable.init(); 
  		      FormComponents.init();
  		    	 $('#Cash_Collection').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#MIS-Reports-photoBilling,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		     $('#addData').click(function()
	    	             { 
	    	           document.getElementById('updateOption').style.display='none';
					   document.getElementById('addOption').style.display='block';
	    	              });
	    	     
	    	     $("#print").click(function(){ 
	    	    	 printcontent($(".table-scrollable").html());
				});

  		   
  		   });
  	
  	
  	function getMatchedData(sdoCode){
  	  
  		$("#mrcAvail").remove();
  		$("#mrcAvail").hide();
  		  
  		  if(sdoCode == 0){
  			  $("#mrCode").attr("disabled","true");
  			  
  		  }else{
  			

  			  var mrcds = "";
  			  var i=0;
  			  
  			$.ajax({
  		    	url:'./getMrmaster'+'/'+sdoCode,
  		    	type:'GET',
  		    	dataType:'json',
  		    	asynch:false,
  		    	cache:false,
  		    	success:function(response)
  		    	{
  		    		var substation='';
  		    		 substation+="<td>MRCODE</td><td><font color=red></font></td><td><select id='mrCode' name='mrCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select Mrcode</option>";
  					for( var i=0;i<response.length;i++){
  						substation+="<option  value='"+response[i].trim()+"'>"+response[i]+"</option>";
  					}
  					substation+="</select></td><span></span>";
  					
  					$("#mrcodeTr").html(substation);
  					$('#mrCode').select2();
  		    	}
  		  });

  				  }
  		  
  		    }
 	</script>

<div class="page-content">
	<%@include file="pagebreadcrum.jsp"%>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->

			<!-- <div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler"></div>
				<div class="toggler-close"></div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>THEME COLOR</span>
						<ul>
							<li class="color-black current color-default"
								data-style="default"></li>
							<li class="color-blue" data-style="blue"></li>
							<li class="color-brown" data-style="brown"></li>
							<li class="color-purple" data-style="purple"></li>
							<li class="color-grey" data-style="grey"></li>
							<li class="color-white color-light" data-style="light"></li>
						</ul>
					</div>
					<div class="theme-option">
						<span>Layout</span> <select
							class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Header</span> <select
							class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Sidebar</span> <select
							class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Footer</span> <select
							class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div> -->


			<!-- <div class="row">
				<div class="col-md-12">
					BEGIN PAGE TITLE & BREADCRUMB
					<h3 class="page-title">
						Search Results <small>search results</small>
					</h3>
				</div>
			</div> -->
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom tabbable-full-width">
						
						<div class="tab-content">
							<div id="tab_2_2" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<div class="booking-search">
											<form action="./searchPaymentsByDates" method="post">
												<div class="row form-group">
														<div class="col-md-6"> 
															
														 <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getMatchedData(this.value)">
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodes}">
													<option value="${element[0]}" id="sdoCodeOpt"
														>${element[0]}-${element[1]}</option>
												</c:forEach>
														  </select>
														
														</div>
														<div class="col-md-6" id="mrcodeTr">
																
															
														</div>
													
														
													</div>
													<div class="row form-group">
													<div class="col-md-6">
														<label class="control-label">Start Date:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value=" <fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy" /> " data-date="${currentDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" />
																
														</div>
													</div>
													<div class="col-md-6">
														<label class="control-label">Check-out:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="<fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy" /> " data-date="${currentDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="toDate" />
														</div>
													</div>
												</div>
												
												<button class="btn blue btn-block margin-top-20">
													SEARCH <i class="m-icon-swapright m-icon-white"></i>
												</button>
											</form>
										</div>
									</div>
								</div>
								</div>
							</div>
							<div class="row">
					<div class="col-md-12">

                 <c:choose>
                 <c:when test="${paymentsList ne null}">
                             <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
								<thead>
									<tr>	
									<th hidden="true">Id</th>
										<th>RR Number</th>
										<th>REC Number</th>
										<th>REC Date</th>
										<th>Last Updated</th>
										<th>Bank Name</th>
										<th>Payment Mode</th>
										<th>Amount</th>
									
									</tr>
								</thead>
								<tbody>
								<c:forEach var="element" items="${paymentsList}">
									<tr>
										<td hidden="true">${element.id}</td>
										<td>${element.rrno} </td>
										<td>${element.recno}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss"  value="${element.rdate}" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss"  value="${element.datestamp}" /></td>
										<td>${element.bankname}</td>
										<td>${element.paymode}</td>
									    <td>${element.amount}</td>
										
									</tr>
											</c:forEach>						
								</tbody>
							</table>
                 </c:when>
                 </c:choose>
							</div>
							<!--end tab-pane-->
						</div>
					</div>
				</div>
				<!--end tabbable-->
			</div>
			<!-- <div id="stack1" class="modal fade" tabindex="-1" data-width="400">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Add Machine Details</h4>
						</div>

						<div class="modal-body">
							<div class="row">
								<div class="col-md-12"></div>
							</div>

						</div>

					</div>
				</div>
			</div> -->

		</div>

	</div>

	<!-- END PAGE CONTENT -->
</div>





