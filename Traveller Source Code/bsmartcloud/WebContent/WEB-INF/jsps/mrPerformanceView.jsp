<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Date"%>


<script type="text/javascript">
var sectionName;
  	$(".page-content").ready(function()
  		   {    	
  		$('#fromDate').val('${cuurentDate}');
  		if('${sdocodeVal}'!='')
			{
			$('#siteCodeval').val('${sdocodeVal}');
			}
		if('${mrPerformanceList.size()}'>0)
			{
			 $('#deviceDiv').show();
			}
  		    	App.init();
  		    	TableEditable.init(); 
  		      FormComponents.init();
  		    loadSearchAndFilter('table1');
  		  $('#MIS-Reports-photoBilling').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		   }); 	
  		    	
  	function loadImage()
  	{
  		$('#deviceDiv').hide();
  		var sdCd=document.getElementById("siteCodeval").value;
  	  if(sdCd=="select")
  	  {
  		    bootbox.alert("Please Select the Section.");
  			return false;
  	  }
  	$("#imageee").show();
  	}
 	</script>
 	

<div class="page-content">
	<div class="portlet box blue">
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>View MR Performance</div>   
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						
						<div class="portlet-body">
					
						 <div class="col-md-12">
										<form role="form" id="hhbmInfo" >
										<table >
										<tr >
										
										<td>
										      <select id="siteCodeval" class="form-control select2me input-medium" name="siteCodeval"  >
														    <option value="select" selected="selected">Select Section</option>
															<c:forEach var="element" items="${sectionList}">
													<option value="${element[1]}">${element[0]}-${element[1]}</option>
												</c:forEach>
														  </select>
													</td>
										
												<td id="locationData">
												
														  </td>
														  <td>
										      <div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="yyyymm" data-date-viewmode="years" name="fromDate" id="fromDate" readonly="true" />
																
														</div>
													</td>
														  <td><div><button class="btn blue pull-left" id="gpsViewButton" onclick="return loadImage();"  formaction="./displayMrPerformance" formmethod="post">view</button></div></td>
										</tr>
								
										</table>
										</form>
									
						</div>
						</div>
		</div>
		<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
				<div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div>
						<div class="portlet box green" id="deviceDiv" style="display: none;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>MR Performance 
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
				<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('table1', '${sdocodeVal}_${cuurentDate}_')">Export
										to Excel</a></li>
							</ul>
						</div>
					<table class="table table-striped table-hover table-bordered"
						id="table1">
						<thead>
							<tr style="background-color: lightgray; ">
								
								<th>SLNO.</th>
								<th>MRCODE</th>
								<th>MRNAME</th>
								<th>CONTACT NO.</th>
								<th>TOTAL BILLED</th>
								<th>NO OF DAYS BILLED</th>
								<th>AVG BILLS PER DAY</th>
								<th>NORMAL</th>
								<th>DL</th>
								<th>MNR</th>
								<th>OTHERS</th>
								<th>EXPECTED BILLS</th>
								<th>DEVICE UTILIZATION(in %)</th>
								
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${mrPerformanceList}">
								<tr>
									<td>${element[0]}</td>
									<td>${element[1]}</td>
									<td>${element[11]}</td>
									<td>${element[12]}</td>
									<td>${element[2]}</td>
									<td>${element[3]}</td>
									<td>${element[4]}</td>
									<td>${element[5]}</td>
								    <td>${element[6]}</td>
								    <td>${element[7]}</td>
								    <td>${element[8]}</td>
								    <td>${element[9]}</td>
								    <td>${element[10]}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
		
		
					
	
</div>




