<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Date"%>



<script type="text/javascript">

  	$(".page-content").ready(function()
    {    	
  		$('#fromDate').val('${currentMonth}');
		App.init();
    	TableEditable.init(); 
        FormComponents.init();
        loadSearchAndFilter('table_3');
        loadSearchAndFilter('table_4');
    
	    $('#MIS-Reports-photoBilling').addClass('start active ,selected');
		$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  	}); 	
  		    	
</script>

<div class="page-content">
	 <c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
	
	<div class="row" style="margin-left: 300px;">
						
						<form id="meterMasterForm" action="./deviceUsageRpt" method="post">
										<table >
										<tr >
										<td><label class="control-label">Select Date :&nbsp;</label></td>
										  <td>
										      <div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="yyyymm" data-date-viewmode="years" name="fromDate" id="fromDate" readonly="true" />
																
														</div>
													</td>
												<td >
												<button class="btn green pull-center" style="display: block;margin-left: 10px;" 
														id="" >View&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i>
														</button>
														  </td>
										</tr>
								
										</table>
										</form>							
													
	</div>
	 <br>
	
	<div class="row">
	<div class="col-md-12" id="mobileDiv1">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bar-chart-o"></i>Divisionwise Device Usage Report
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<!-- <a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a> -->
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="table_3">
								<thead>
									<tr style="background-color: lightgray; ">
									<th style="text-align: center; ">SLNO.</th>
										<th style="text-align: center; ">DIVISION NAME</th>
										<th style="text-align: center; ">DIVISION CODE</th>
										<th style="text-align: center; ">Total Device</th>
										<th style="text-align: center; ">Usage to 500 Bills</th>
										<th style="text-align: center; ">500 to 1000</th>
										<th style="text-align: center; ">1000 to 2500</th>
										<th style="text-align: center; ">>2500</th>
									</tr>
								</thead>
								<tbody>
								  <c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${divisionDivUsageRptList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[6]}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">${element[4]}</td>
									<td style="text-align: center; ">${element[5]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
	</div>
	
	<div class="row">
	<div class="col-md-12" id="mobileDiv2">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bar-chart-o"></i>Sectionwise Device Usage Report
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<!-- <a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a> -->
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="table_4">
								<thead>
									<tr style="background-color: lightgray; ">
									<th style="text-align: center; ">SLNO.</th>
										<th style="text-align: center; ">SECTION NAME</th>
										<th style="text-align: center; ">SECTION CODE</th>
										<th style="text-align: center; ">Total Device</th>
										<th style="text-align: center; ">Usage to 500 Bills</th>
										<th style="text-align: center; ">500 to 1000</th>
										<th style="text-align: center; ">1000 to 2500</th>
										<th style="text-align: center; ">>2500</th>
									</tr>
								</thead>
								<tbody>
								  <c:set var="count" value="1" scope="page" />
							 <c:forEach var="element" items="${divUsageRptList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">${element[4]}</td>
									<td style="text-align: center; ">${element[5]}</td>
									<td style="text-align: center; ">${element[6]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>   
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
	</div>
	
	
</div>




