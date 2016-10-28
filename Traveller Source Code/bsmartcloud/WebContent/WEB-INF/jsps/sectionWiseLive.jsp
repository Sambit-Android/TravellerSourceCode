<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Date"%>

<style>
.portlet.box.saffron > .portlet-title {
    background-color:  	#ff9966;
}
.portlet.box.saffron {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: -moz-use-text-color  #ff9966  #ff9966;
    border-image: none;
    border-style: none solid solid;
    border-width: 0 1px 1px;
}
</style>
<script type="text/javascript">

  	$(".page-content").ready(function()
    {    	
		App.init();
    	TableEditable.init(); 
        FormComponents.init();
        loadSearchAndFilter('table_3');
    
	    $('#MIS-Reports-photoBilling').addClass('start active ,selected');
		$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  	}); 	
  		    	
</script>

<div class="page-content">
	<%-- <c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if> --%>
	
	<div class="row">
	<div class="col-md-12" id="mobileDiv1">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box saffron" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bar-chart-o"></i>Sectionwise Billed Details
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
										<th style="text-align: center; ">SECTION CODE</th>
										<th style="text-align: center; ">SECTION NAME</th>
										<th style="text-align: center; ">TOTAL CONSUMERS</th>
										<th style="text-align: center; ">TOTAL BILLED</th>
										<th style="text-align: center; ">TOTAL BILLED TODAY</th>
										<th style="text-align: center; ">TOTAL TO BE BILLED</th>
										<th style="text-align: center; ">BILLED PERCENTAGE</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${secRealTimeList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">${element[0]}</td>
									<td style="text-align: center; ">${element[1]}</td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">0</td>
									<td style="text-align: center; ">${element[4]}</td>
									<td style="text-align: center; ">0</td>
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




