<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@page import="java.util.Date"%>
  <style>
#bmdButton
{
	margin-bottom: 10px;
	display:inline-block; 
}
#btnId
{
  font-size: 14px;
 background-color: #AAD4FF;
}
</style>
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#fromDate').val('${currentMonth}');
   	    	$('#MIS-Reports-photoBilling').addClass('start active ,selected');
	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
	    		   if('${deviceWiseReport.size()}'>0)
	    			   {
	    			      $('#deviceDiv').show();
	    			   }
	    		   else
	    			   {
	    			   $('#deviceDiv').hide();
	    			   }
   	    	   });
 
 function validation()
 {
	 $('#deviceDiv').hide();
	 $('#imageee').show();
	 $("form#mrDevices").attr("action","./updateMRDevice");
 }
	
 function viewMobileData()
 {
	 $('#sample_4').empty();
	 $('#excelExportDiv').hide();
	 $('#closeShow1').hide();
	 $('#closeShow').hide();
	 $.ajax({
	    	url:'./showDevicesNotLiveToday'+'/'+$('#fromDate').val(),
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	beforeSend: function(){
		        $('#image').show();
		    },
		    complete: function(){
		        $('#image').hide();
		        $('#closeShow').show();
		        $('#closeShow1').show();
		        $('#excelExportDiv').show();
		    },
	    	success:function(response)
	    	{
	    	 
	    		var html2 = "<thead ><tr style='background-color: lightgray;'><th style='text-align: center;'>SLNO.</th><th style='text-align: center;'>DEVICE ID</th><th style='text-align: center;'>APPROVAL STATUS</th><th style='text-align: center;'>ALLOCATE FLAG</th><th style='text-align: center;'>SDOCODE</th><th style='text-align: center;'>SECTION</th><th style='text-align: center;'>MRCODE</th></tr></thead><tbody>";
	    	       		 
	    	    	   for(var j=0;j<response.length;j++)
			    		{	
			    		     html2 += "<tr style='text-align: center;'><td>"+(j+1)+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][0]+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][1]+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][2]+"</td>"; 
			    		     html2 += "<td style='text-align: center;'>"+response[j][3]+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][4]+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][5]+"</td></tr>";
			    		}
		    		
		    	html2+="</tbody>";	
		    	$('#sample_4').html(html2);
		    	$('#mobileDiv').show();
		    	
		    	searchAndFilter('sample_4'); 
	    	}
	  }); 
 }
  
 function searchAndFilter(param) 
	{ 
		$('#'+param).dataTable().fnDestroy(); 
		$('#'+param).dataTable(
				 { "aLengthMenu": [
		                    [20, 50, 100, -1],
		                    [20, 50, 100, "All"] // change per page values here
		                ],
		                "iDisplayLength": 500
	  }); 
		jQuery('#'+param+'_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input 
		jQuery('#'+param+'_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown 
		jQuery('#'+param+'_wrapper .dataTables_length select').select2(); // initialize select2 dropdown 
	}
  </script>
<div class="page-content" >
               <c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
                  <div class="portlet box purple" >
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Device wise Report </div>   
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						
						<div class="portlet-body" >
					
						 <div class="col-md-12">
										<form action="./deviceWiseReport" method="post">
										<table  >
										<tr >
														  <td>
														  
										      <div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="yyyymm" data-date-viewmode="years" name="fromDate" id="fromDate" readonly="true" />
																
														</div>
													</td>
														  <td><div><button class="btn blue pull-left" id="gpsViewButton" onclick="validation();">view</button></div></td>
										</tr>
								
										</table>
										</form>
									
						</div>
						</div>
		</div>
		<div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div>
                  <div class="row">				
			<div class="portlet box green" id="deviceDiv">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Device Wise Report
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
				
					<div class="table-toolbar">
						<div class="btn-group" style="margin-left:45%;">
							<span id="SpanHeader72" class="label label-warning"><a href='#' id='deviceNotLiveId' data-toggle='modal' data-target='#stack6' onclick="return viewMobileData();" >
							<font size="2" color="#222211"><b>DEVICES NOT USED </b></font></a></span>
						</div>
						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<!-- <li><a href="#" id="print">Print</a></li> -->
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('table1', 'DeviceWiseReport')">Export
										to Excel</a></li>
							</ul>
						</div>
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered"
						id="table1">
						<thead>
							<tr style="background-color: lightgray; ">
								
								<th>SLNO.</th>
								<th>DEVICE ID</th>
								<th>SECTION</th>
								<th>SDOCODE</th>
								<th>NO OF BILLS ISSUED</th>
								<th>NO OF DAYS BILLED</th>
								<th>AVG. BILL PER DAY</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${deviceWiseReport}">
								<tr>
									
									<td>${element[0]}</td>
									<td>${element[2]}&nbsp;</td>
									<td>${element[1]}</td>
									<td>${element[6]}</td>
									<td>${element[3]}</td>
									<td>${element[4]}</td>
								    <td>${element[5]}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
			
			<div class="row">
			
			<div id="stack6" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
							<div class="modal-dialog" style="width: 60%;" >
							
								<div class="modal-content">
								
									<div class="modal-header">
									<div id="image" hidden="true" style="text-align: center;height: 100%;width: 100%;" >
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:4%;height: 4%;margin-right: 10px;">
						</div>
									<div id="closeShow" hidden="true">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true"></button>
										<h4 class="modal-title">DEVICES NOT USED  <%-- IN THE MONTH<span class="label label-success" >
										<fmt:parseDate pattern="yyyyMM" value="${currentMonth}" var="monthVal" type="date"/>
										<font size="3" color="#222211"><b><fmt:formatDate pattern="YYYY-MMM" value="${monthVal}" var="monthVal1"/>
										${monthVal1}</b></font></span> --%>
										</h4>
										</div>
									</div>

									<div class="modal-body" id="closeShow1" style="display: none;">
									<div id="excelExportDiv"   hidden="true">
									<span id="SpanHeader72" class="label label-primary">
									<a href="#" id="excelExport1" 
									onclick="tableToExcel1('sample_4', 'DevicesNotLiveReport')"><font size="2" color="white"><b>EXPORT
										TO EXCEL</b></font></a></span>
										</div><br>
										<div class="row">
											<div class="col-md-12">
											<table class="table table-striped table-hover table-bordered" id="sample_4" >
													</table>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>
						</div>
						</div>
					</div>
					
					
</div>