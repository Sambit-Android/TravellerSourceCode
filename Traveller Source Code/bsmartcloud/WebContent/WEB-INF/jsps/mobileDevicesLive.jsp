<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Date"%>


<script type="text/javascript">

  	$(".page-content").ready(function()
  		   {    	
  		$('#fromDate').val('${cuurentDate}');
  		    	App.init();
  		    	TableEditable.init(); 
  		      FormComponents.init();
  		    loadSearchAndFilter('table_3');
  		    
  		
  		  $('#MIS-Reports-photoBilling').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		    	
  		    	if('${mobileLiveList.size()}'>0)
  		    	 	{
  		    		   $('#mobileDiv1').show();
  		    		}
  		   }); 	
  		    	
  	var divCode;
  	var sectionname;
  	function viewMobileData(divSdo, divname)
  	{
  		if($("#SpanHeader"+divCode).hasClass( "label label-success" ))
  			{
  			   $("#SpanHeader"+divCode).attr('class', 'label label-warning');
  			}
  		
  			   $("#SpanHeader"+divSdo).attr('class', 'label label-success');
  			
  		
  		$('#sample_4').empty();
  		$('#mobileDiv').hide();
  		$.ajax({
	    	url:'./showMobileDevicesLiveBySection'+'/'+divSdo+'/'+$('#fromDate').val(),
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	beforeSend: function(){
		        $('#imageee').show();
		    },
		    complete: function(){
		        $('#imageee').hide();
		    },
	    	success:function(response)
	    	{
	    		var html2 = "<thead ><tr style='background-color: lightgray;'><th style='text-align: center;'>SLNO.</th><th style='text-align: center;'>SECTION</th><th style='text-align: center;'>TOTALDEVICES</th><th style='text-align: center;'>DEVICESLIVE</th><th style='text-align: center;'>SPARE DEVICES</th><th style='text-align: center;'>TODAYBILLISSUED</th></tr></thead><tbody>";
	    	       		 
	    	    	   for(var j=0;j<response.length;j++)
			    		{	
	    	    		   
			    		     html2 += "<tr style='text-align: center;'><td>"+(j+1)+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][0]+"</td>";
			    		     html2 += "<td style='text-align: center;'>"+response[j][2]+"</td>"; 
			    		     html2 += "<td style='text-align: center;'><a href='#' id='"+response[j][1]+"@"+response[j][0]+"' onclick='return viewMobileMrData(this.id)' title='Click to view' data-toggle=modal data-target=#stack6 ><span class='label label-primary' ><font size='2' color=''#222211'><b >"+response[j][3]+"</b></font></span></a></td>";
			    		     //html2 += "<td style='text-align: center;'>"+(response[j][2]-response[j][3])+"</td>";
			    		    // html2 += "<td style='text-align: center;'><a href='#' id='"+response[j][1]+"@"+response[j][0]+"' onclick='return viewMobileNotLiveMrData(this.id)' title='Click to view' data-toggle=modal data-target=#stack6 ><span class='label label-primary' ><font size='2' color=''#222211'><b >"+(response[j][2]-response[j][3])+"</b></font></span></a></td>";
			    		     html2 += "<td style='text-align: center;'><a href='#' id='"+response[j][1]+"@"+response[j][0]+"' onclick='return viewMobileNotLiveMrData(this.id)' title='Click to view' data-toggle=modal data-target=#stack6 ><span class='label label-primary' ><font size='2' color=''#222211'><b >"+response[j][5]+"</b></font></span></a></td>"; 
			    		    html2 += "<td style='text-align: center;'>"+response[j][4]+"</td></tr>";
			    		}
		    		
		    	html2+="</tbody>";	
		    	$('#sample_4').html(html2);
		    	$('#mobileDiv').show();
		    	$('#divisionName').text(divname);
		    	
		    	loadSearchAndFilter('sample_4'); ; 
	    	}
	  }); 
  		
  		divCode=divSdo;
  	}
  	
  	function viewMobileMrData(sectioncode)
  	{
  		$('#closeShow').hide();
  		 $('#closeShow1').hide();
  		var arr=sectioncode.split('@');
  		$('#table_4').html('');
  		$.ajax({
	    	url:'./showDeviceMrBilled'+'/'+arr[0]+'/'+$('#fromDate').val(),
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
		    },
	    	success:function(response)
	    	{
	    		
	    		 var m=1;
		    	 var html2 = "<thead><tr><th>SLNO</th><th>DEVICEID</th><th>BILLED TODAY</th><th>TOTAL BILLED</th></tr></thead><tbody>";
	    	       for(var j=0;j<response.length;j++)
		    		{	
		    		     html2 +="<tr><td>"+m+"</td><td>"+response[j][0]+"</td><td>"+response[j][2]+"</td><td>"+response[j][3]+"</td></tr>";
		    		      m=m+1; 
		    		}
		    	html2+="</tbody>";	
		    	
			      $('#table_4').html(html2);
			      $('#billdateSapn').text($('#fromDate').val());
			      $('#sectionNameSpan').text(arr[1]);
			      loadSearchAndFilter('table_4'); 
	    		
	    	}
	  }); 
  		
  	}
  	
  	
  	/* function viewMobileNotLiveMrData(sectioncode)
  	{
  		var arr=sectioncode.split('@');
  		$('#closeShow').hide();
 		 $('#closeShow1').hide();
  		$('#table_4').empty();
  		$.ajax({
	    	url:'./showDeviceMrNotBilled'+'/'+arr[0]+'/'+$('#fromDate').val(),
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
		    },
	    	success:function(response)
	    	{
	    		 var m=1;
		    	 var html2 = "<thead><tr><th>SLNO</th><th>DEVICEID</th><th>MRCODE</th><th>BILLED TODAY</th><th>TOTAL BILLED</th></tr></thead><tbody>";
	    	       for(var j=0;j<response.length;j++)
		    		{	
		    		     html2 +="<tr><td>"+m+"</td><td>"+response[j][0]+"</td><td>"+response[j][1]+"</td><td>"+response[j][2]+"</td><td>"+response[j][3]+"</td></tr>";
		    		      m=m+1; 
		    		}
		    	html2+="</tbody>";	
			      $('#table_4').html(html2);
			      $('#billdateSapn').text($('#fromDate').val());
			      $('#sectionNameSpan').text(arr[1]);
			      loadSearchAndFilter('table_4'); 
	    		
	    	}
	  }); 
  	} */
  	
  	function viewMobileNotLiveMrData(sectioncode)
  	{
  		var arr=sectioncode.split('@');
  		$('#closeShow').hide();
 		 $('#closeShow1').hide();
  		$('#table_4').empty();
  		$.ajax({
	    	url:'./showDeviceMrNotBilled'+'/'+arr[0]+'/'+$('#fromDate').val(),
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
		    },
	    	success:function(response)
	    	{
	    		 var m=1;
		    	 var html2 = "<thead><tr><th>SLNO</th><th>DEVICEID</th><th>BILLED TODAY</th><th>TOTAL BILLED</th></tr></thead><tbody>";
	    	       for(var j=0;j<response.length;j++)
		    		{	
		    		     html2 +="<tr><td>"+m+"</td><td>"+response[j][0]+"</td><td>"+response[j][3]+"</td><td>"+response[j][2]+"</td></tr>";
		    		      m=m+1; 
		    		}
		    	html2+="</tbody>";	
			      $('#table_4').html(html2);
			      $('#billdateSapn').text($('#fromDate').val());
			      $('#sectionNameSpan').text(arr[1]);
			      loadSearchAndFilter('table_4'); 
	    		
	    	}
	  }); 
  	}
 	</script>

<div class="page-content">
	<div class="row" style="margin-left: 300px;">
						
						<form id="meterMasterForm" action="./showMobileDevicesLive" method="post">
										<table >
										<tr >
										<td><label class="control-label">Select Date:-</label></td>
										  <td>
										      <div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" id="fromDate" />
																
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
	<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>
	
	<div class="row">
	<div class="col-md-12" id="mobileDiv1" style="display: none;">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Divisionwise Mobile Details
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport" onclick="tableToExcel('table_3', 'DeviceUtilizationReport')">Export
										to Excel</a></li>
							</ul>
						</div>
							<table class="table table-striped table-bordered table-hover" id="table_3">
								<thead>
									<tr style="background-color: lightgray;">
									<th style="text-align: center; ">SLNO.</th>
										<th style="text-align: center; ">DIVISION</th>
										<th style="text-align: center; ">TOTAL DEVICES</th>
										<th style="text-align: center; ">DEVICES LIVE</th>
										<th style="text-align: center; " >
										SPARE DEVICES<br>
										(Billed < 500)
										</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${mobileLiveList}">
								<tr>
									<td style="text-align: center; ">${count}</td>
									<td style="text-align: center; ">
									
									<a href='#' id='${element[1]}' onclick="return viewMobileData(this.id,'${element[0]}');" >
									<span class="label label-warning" id='SpanHeader${element[1]}'><font size="2" color="#222211"><b >${element[0]}</b></font></span></a></td>
									<td style="text-align: center; ">${element[2]}</td>
									<td style="text-align: center; ">${element[3]}</td>
									<td style="text-align: center; ">${element[4]}</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
							<tr style="text-align: center; ">
									<td style="background-color: lightgray;">${count}</td>
									<td style="background-color: lightgray;"><b>TOTAL</b></td>
									<td style="background-color: lightgray;"><b>${totaDevices}</b></td>
									<td style="background-color: lightgray;"><b>${liveDdevices}</b></td>
									<td style="background-color: lightgray;"><b>${spareDevices}</b></td>
									</tr>
								</tbody>
								
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
	
	</div>
	<div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div>
	<div class="row">
	<div class="col-md-12" id="mobileDiv" style="display: none;" >
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Sectionwise Mobile Details
							&nbsp;&nbsp;-<span class="label label-success" ><font size="3" color="#222211"><b id="divisionName" ></b></font></span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="sample_4">
								<thead >
									<tr style="background-color: lightgreen; ">
									</tr>
								</thead>
								<tbody id="mobileTb">
									
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
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
										<h4 class="modal-title">DEVICE-MR BILL DETAILS&nbsp;&nbsp;&nbsp;<span class="label label-info">VIEWED DATE:-</span><span class="label label-info" id="billdateSapn"></span>
										&nbsp;&nbsp;&nbsp;<span class="label label-info">VIEWED SECTION:-</span><span class="label label-info" id="sectionNameSpan"></span>
										</h4>
										</div>
									</div>

									<div class="modal-body" id="closeShow1">
										<div class="row">
											<div class="col-md-12">
											<table class="table table-striped table-hover table-bordered" id="table_4" >
													</table>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>
						</div>
	
</div>




