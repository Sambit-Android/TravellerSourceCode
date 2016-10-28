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
  		if('${mrDaywiseList.size()}'>0)
  			{
  			 $('#mrDayDiv').show();
  			}
  		 sectionName='${sectionname}';
  		    	App.init();
  		    	TableEditable.init(); 
  		      FormComponents.init();
  		    //UIExtendedModals.init();
  		   loadSearchAndFilter('table_3'); 
  		  $('#MIS-Reports-photoBilling').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		   }); 	
  		    	

  	var billdateVal=null;
  	function getBilledData(param,mrName,sdoCode,billdate)
    {	
  		$('#billedData').empty();
  		$('#titleId').empty();
			$.ajax(
					{
							type : "GET",
							url : "./getRealStatusBilledData1/"+mrName+"/"+sdoCode+'/'+billdate,
							dataType : "json",
							async:false,
							cache:false,
							 beforeSend: function(){
						        $('#imageee').show();
						    },
						    complete: function(){
						       $('#imageee').hide();
						    },
							success : function(response)
														{	
												    		/* setTimeout(function() {
																    $(window).lazyLoadXT();								    
																  }, 50); */
												    		var tableData="<table class='table table-striped table-hover' id=billTable>";
												    		
												    		for(var i=0;i<response.length;i++)
												    			{//<tr><td><a href="#"><img class="lazy" data-src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}/${fn:trim(element.sdoCode)}/${fn:trim(element.billMonth)}" style="background: url('./resources/bsmart.lib.js/loading.gif') no-repeat 50% 50%;" border="0"  data-toggle="modal"  data-target='#popup_image'   height="150" width="200"   title="${fn:trim(element.consumer_Sc_No)}" onclick="viewDocument('${fn:trim(element.consumer_Sc_No)}','${fn:trim(element.sdoCode)}','${fn:trim(element.billMonth)}');"/></a></td></tr>
													    			//var billDate=response[i].billDate.split('-');
													    			 billdateVal=response[i].billDate.substring(0,10)
													    			var bilMth=response[i].billMonth;															    			
													    		    var mtrRdg=response[i].meterReadingType;
													    			mtrRdgSatus=getStatusName(mtrRdg);
													    			var versionVal=response[i].bank.split('_');
												    			tableData=tableData+"<table id=billTable style='margin-left: 10px;margin-bottom: 10px;'>"   //tableData=tableData+"<table id=billTable style='margin-left: 10px;margin-bottom: 10px;'><tr ><td><a href=#><img class=lazy data-src=./imageDisplay/getImage/"+response[i].consumer_Sc_No.trim()+"/"+response[i].sdoCode+"/"+response[i].billMonth+" onclick=viewDocument('"+escape(response[i].consumer_Sc_No.trim())+"','"+response[i].sdoCode+"','"+response[i].billMonth+"'); data-toggle=modal  data-target='#popup_image' height=150 width=200 style='background: url(./resources/bsmart.lib.js/loading.gif) no-repeat 50% 50%';/></a></td></tr>"
														                   /* +"<tr ><td><a href=#><img class=lazy data-src=./imageDisplay/getImage/"+response[i].consumer_Sc_No.trim()+"/"+response[i].sdoCode+"/"+response[i].billMonth+" onclick=viewDocument('"+escape(response[i].consumer_Sc_No.trim())+"','"+response[i].sdoCode+"','"+response[i].billMonth+"'); data-toggle=modal  data-target='#popup_image' height=150 width=200 style='background: url(./resources/bsmart.lib.js/loading.gif) no-repeat 50% 50%';/></a></td></tr>" */
												    			           +"<tr><td><table id=billTable style='margin-left: 10px;margin-bottom: 10px;'><tr><th>MR Code</th><td>"+response[i].bmd_Reading+"</td></tr>"
														                  +"<tr><th>Bill Month</th><td>"
														                  +moment(new Date(bilMth.substring(0,4),bilMth.substring(4,bilMth.length)-1,1)).format('MMM-YYYY')+"</td></tr>"									  
																		   +"<tr><th>Bill Date</th>"
																		  +"<td>"
																		  +response[i].billDate.substring(0,10)+"</td>" //+moment(new Date(billDate[2],billDate[1]-1,billDate[0])).format('DD-MMM-YYYY')+"</td>"
																 	      +"</tr>"
																		  +"<tr><th>RR Number</th><td >"+response[i].consumer_Sc_No+"</td></tr>"
																		  +"<tr><th>Reading Date    </th><td>"+moment(response[i].readin_Date).format('DD/MM/YYYY')+"</td></tr>"
																		  +"<tr><th>Present Rdng    </th><td>"+response[i].present_Reading +"</td></tr>"
																		  +"<tr><th>Previous Rdng   </th><td>"+response[i].previous_reading+"</td></tr>"
																		  +"<tr><th>Units Consumed  </th><td>"+response[i].units+"</td></tr>"
																		   
																		  +"<tr><th>Apk version  </th><td>"+versionVal[1].substring(2,6)+"</td></tr>" 
																		  +"<tr><th>Mtr Rndg type  </th><td>"+mtrRdgSatus+"</td></tr>"  //Mtr Rndg type
																		  /* +" <tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
																		  +"<tr><th>Lattitude</th><td>"+response[i].lattitude+"</td></tr>" */
																		  +" <tr><th>TakenTime </th><td>"+response[i].takenTime+"</td></tr>" //+response[i].takenTime+
																		  +"<tr><th>&nbsp; </th><td><a href='#' id='"+response[i].consumer_Sc_No+"@"+bilMth+"@"+sdoCode+"@"+billdateVal+"' onclick='return viewConsumerOnMap(this.id)' data-toggle=modal data-target=#stack6 >View on Map</a></td>"
																		  +"</table></td></tr></table>";		  
												    			}
												    		
												    		tableData=tableData+"</table>";		
												    		$('#billedData').html(tableData);
												    		$('#titleId').html('MRwise billed, Section code- '+sdoCode+' MR code- '+mrName+'&nbsp;&nbsp;&nbsp;<a href=# onclick=getMobileHealth(this.id,"'+mrName+'","'+sdoCode+'","'+billdateVal.replace('/','-').replace('/','-')+'","'+sectionName+'") id=memoryData'+mrName+''+sdoCode+'>ViewDeviceHealth</a>');
														}
					}
			    );
		//$('#'+param).attr("data-toggle", "modal");
	   // $('#'+param).attr("data-target","#stack10");
			
    }
  	
  	function viewConsumerOnMap(consumerno)
	{
	  var arr=consumerno.split('@');
	  var rrno=arr[0];
	  var yearmonth=arr[1];
	  var sdocode=arr[2];
	  var bDate=arr[3].replace('/','-').replace('/','-');
		$('#gmapsContent').hide();
		$.ajax({
	    	url:'./viewConsumerOnMap'+'/'+rrno+'/'+yearmonth+'/'+sdocode+'/'+bDate,
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	beforeSend: function(){
		        $('#imageee').show();
		    },
		    complete: function(){
		        $('#imageee').hide();
		        $('#gmapsContent').show();
		    },
	    	success:function(response)
	    	{
		    	
		          var initialLocation=false;
		          var map="";
		    	  for(var i=0;i<response.length;i++)
					{ 
		    		   
		    		  if(!initialLocation)
		    			  {				    			 
		    			  if(response[i].lattitude.trim()!=''  || response[i].longitude.trim()!='')
		    				  {					    				  
		    				     document.getElementById('gmapsContent').style.display='block';
				    			      map = new GMaps({
						              div: '#gmap_marker',
						              //lng:parseFloat(response[i].longitude),
						              //lat:parseFloat(response[i].lattitude),
						              center: {lat: parseFloat(response[i].lattitude), lng: parseFloat(response[i].longitude)},
						              zoom:9,
						          });
				    			      
				    			     initialLocation=true;
				    			     
		    			     }
		    			  }
		    		  
		    		if(initialLocation)
		    			 {
		    			var billDate=response[i].billDate;
		    			var bilMth=response[i].billMonth;		
		    		    var mtrRdg=response[i].meterReadingType;
		    			 mtrRdgSatus=getStatusName(mtrRdg);				    			 
		    		      map.addMarker({
			              lng: parseFloat(response[i].longitude),
			              lat: parseFloat(response[i].lattitude),
			             icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+response[i].consumer_Sc_No+'|FF8000|000000',
			            //icon:'http://maps.gstatic.com/mapfiles/markers/marker_sprite.png&chld='+(i+1),
			              	  
			              title:"Click-"+response[i].consumer_Sc_No,
			              infoWindow: {
			                  content: 
			                  "<div style=width:250px;>"
			                  +"<table style='margin-left: 10px;margin-bottom: 10px;'><tr ><td><a href=#><img src=./imageDisplay/getImage/"+response[i].consumer_Sc_No.trim()+"/"+response[i].sdoCode+"/"+response[i].billMonth+" title="+response[i].consumer_Sc_No+" onclick=viewDocument('"+escape(response[i].consumer_Sc_No.trim())+"','"+response[i].sdoCode+"','"+response[i].billMonth+"'); data-toggle=modal  data-target='#popup_image' height=150 width=200/></a></td></tr></table>"
			                  +"<table  id=billTable style='margin-left: 10px;margin-bottom: 10px;'><tr><th>MR Code</th><td>"+response[i].bmd_Reading+"</td></tr>"
							  +"<tr><th>Bill Month</th><td>"
							  +moment(new Date(bilMth.substring(0,4),bilMth.substring(4,bilMth.length)-1,1)).format('MMM-YYYY')+"</td></tr>"									  
							  +"<tr><th>Bill Date</th>"
							  +"<td>"
							  +billDate+"</td>"//moment(new Date(billDate[2],billDate[1]-1,billDate[0])).format('DD-MMM-YYYY')+"</td>"
					 	      +"</tr>"							
							  
							  +"<tr><th>Consumer Number </th><td >"+response[i].consumer_Sc_No+"</td></tr>"
							  +"<tr><th>Reading Date    </th><td>"+response[i].readin_Date+"</td></tr>"
							  +"<tr><th>Present Rdng    </th><td>"+response[i].present_Reading +"</td></tr>"
							  +"<tr><th>Previous Rdng   </th><td>"+response[i].previous_reading+"</td></tr>"
							  +"<tr><th>Units Consumed  </th><td>"+response[i].units+"</td></tr>"
							 /*  +"<tr><th>Meter Number    </th><td>"+response[i].meterNo+"</td></tr>" */
							  +"<tr><th>Mtr Rndg type   </th><td>" 									 
							  +""+mtrRdgSatus+"</td></tr>" 
							  +" <tr><th>TariffCode </th><td>"+response[i].tariff+"</td></tr>"
							  +" <tr><th>Taken Time </th><td>"+response[i].takenTime+"</td></tr>"
							  +"</table></div>"
			              }
			          });	
		    		      
		    			 } 				    		  		    		  
					}
		    	  
		    	  if(!initialLocation)
  			  {
  			  document.getElementById('gmapsContent').style.display='none';
  			  bootbox.alert('no proper lattitude and langitude');
  			  }
	    	}
	  }); 
		
	}
  	
  	function loadImage()
  	{
  		$('#mrDayDiv').hide();
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
							<div class="caption"><i class="fa fa-reorder"></i>MR Daywise view</div>   
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
														  <td><div><button class="btn blue pull-left" id="gpsViewButton" onclick="return loadImage();"  formaction="./showMrDaywiseBySection" formmethod="post">view</button></div></td>
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
		<div id="mrDayDiv" class="portlet box green" style="display:none;" >
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>MR Daywise view</div>   
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
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('table_4', '${sdocodeVal}_${cuurentDate}_')">Export
										to Excel</a></li>
							</ul>
						</div>
						 <div class="col-md-16">
		 <table class="table table-striped table-hover table-bordered" id="table_3" border="1">
							<thead >
								<tr   >
								<th style="text-align: center;"><font size="2" >SRNO.</font></th>
								<th style="text-align: center;"><font size="2" >MRcode</font></th>
								<th style="text-align: center;"><font size="2" >MRName</font></th>
								<th style="text-align: center;"><font size="2" >Contact No.</font></th>
								<c:if test="${dispalyTH eq 'Yes'}">
								    <c:forEach var="i" begin="1" end="${daysOfMonth}">
   												<th style="background-color: lightgray;text-align: center;"><font size="2" >Day${i}</font></th>
									</c:forEach> 
								</c:if>
									<th style="text-align: center;background-color: lightgray;"><font size="2" >Total</font></th>
								</tr>
								
							</thead>
							<tbody>
							<c:set var="count" value="1" scope="page" />
							<c:forEach var="element" items="${mrDaywiseList}">
							<tr style="text-align: center;"> 
							<td>${count}</td>
							<td>${element[0]}</td>
							<td>${element[newArrLength-2]}</td>
							<td>${element[newArrLength-1]}</td>
							<c:forEach var="i" begin="1" end="${daysOfMonth}">
							<c:choose>
							<c:when test="${i<=9}">
							
							<c:choose>
								   <c:when test="${element[i] eq 0}">
								          <td>${element[i]}</td>
								   </c:when>
								   <c:otherwise>
								   <td><span class="label label-primary" id='SpanHeader${element[1]}'><b ><a href="#" onclick="return getBilledData(this.id,'${element[0]}','${sdocodeVal}','${cuurentDate}0${i}');" id="billedData${element[0]}${sdocodeVal}${i}" data-toggle="modal" data-target="#stack10">
									 <font size="2" color="white">${element[i]}</font></a></b></span></td>
								   </c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
							<c:choose>
								   <c:when test="${element[i] eq 0}">
								          <td>${element[i]}</td>
								   </c:when>
								   <c:otherwise>
								   <td><span class="label label-primary" id='SpanHeader${element[1]}'><b ><a href="#" onclick="return getBilledData(this.id,'${element[0]}','${sdocodeVal}','${cuurentDate}${i}');" id="billedData${element[0]}${sdocodeVal}${i}" data-toggle="modal" data-target="#stack10">
									 <font size="2" color="white">${element[i]}</font></a></b></span></td>
								   </c:otherwise>
								</c:choose>
							</c:otherwise>
							
							</c:choose>
							
								</c:forEach>
								<td style="background-color: lightgray;"> <b><font size="2" >${element[newArrLength-3]}</font></b></td>
							</tr>
								
								 <c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
								<tr style="text-align: center;" >
								<td >${count}</td>
								<td ></td> 
								<td ></td>
								<td ></td>
								<c:forEach var="grandSum" items="${totalSum}">
								<c:forEach var="i" begin="1" end="${daysOfMonth}">
								<td style="background-color: lightgray;"><b><font size="2" >${grandSum[i-1]}</font></b></td>
								</c:forEach>
								</c:forEach>
								<td style="text-align: center;background-color: lightgray;"><b><font size="2" >${finalSum}</font></b></td>
								</tr>
							</tbody>
		</table>
		 <table  id="table_4" border="1" hidden="true" style="border-style: solid;">
							<thead >
								<tr>
								<th>MRcode</th>
								<th>MRName</th>
								<th>Contact No.</th>
								<c:forEach var="i" begin="1" end="${daysOfMonth}">
   												<th>Day${i}</th>
									</c:forEach>
									<th>Total</th>
								</tr>
								
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="element" items="${mrDaywiseList}">
							
							<tr>
							<td>${element[0]}</td>
							<td>${element[newArrLength-2]}</td>
							<td>${element[newArrLength-1]}</td>
							<c:forEach var="i" begin="1" end="${daysOfMonth}">
							<c:choose>
							<c:when test="${i<=9}">
							
							<c:choose>
								   <c:when test="${element[i] eq 0}">
								          <td>${element[i]}</td>
								   </c:when>
								   <c:otherwise>
								   <td>${element[i]}</td>
								   </c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
							<c:choose>
								   <c:when test="${element[i] eq 0}">
								          <td>${element[i]}</td>
								   </c:when>
								   <c:otherwise>
								   <td>${element[i]}</td>
								   </c:otherwise>
								</c:choose>
							</c:otherwise>
							
							</c:choose>
							
								</c:forEach>
								<td><b><font size="3" >${element[newArrLength-3]}</font></b></td>
							</tr>
								 <c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
							<tr style="text-align: center;" >
								<td ></td> 
								<td></td>
								<td></td>
								<c:forEach var="grandSum" items="${totalSum}">
								<c:forEach var="i" begin="1" end="${daysOfMonth}">
								<td style="background-color: lightgray;"><b><font size="2" >${grandSum[i-1]}</font></b></td>
								</c:forEach>
								</c:forEach>
								<td style="text-align: center;"><font size="2" >${finalSum}</font></td>
								</tr>
							</tbody>
		</table> 
		</div>
		</div>
		</div>
		<div class="row">
		<div id="stack10" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
						        <div class="modal-dialog">
						        
							       <div class="modal-content" style="width:200%;margin-left:-200px">
								<div class="modal-header">
								<!-- <div id="imageee1" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div> -->
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title" id="titleId"></h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12" id="billedData">
											

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</div>
					<div class="row">
			
			<div id="stack6" class="modal fade" tabindex="-1" data-width="400" data-keyboard="false">
							<div class="modal-dialog" style="width: 60%;" >
								<div class="modal-content">
								
									<div class="modal-header">
									
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true"></button>
										<!-- <h4 class="modal-title">DEVICE-MR BILL DETAILS&nbsp;&nbsp;&nbsp;<span class="label label-info">VIEWED DATE:-</span><span class="label label-info" id="billdateSapn"></span>
										&nbsp;&nbsp;&nbsp;<span class="label label-info">VIEWED SECTION:-</span><span class="label label-info" id="sectionNameSpan"></span>
										</h4> -->
										
									</div>

									<div class="modal-body" id="closeShow1">
										<div class="row">				
			
					<!-- BEGIN MARKERS PORTLET-->
					<div class="portlet box green" style="display: none;" id="gmapsContent">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>GMAP VIEW</div>
							
						</div>
				<div class="portlet-body">
							<div id="gmap_marker" class="gmaps" style="height: 500px"></div>				 
               <!-- END PORTLET-->
               </div>
               </div>
              
               </div>

									</div>

								</div>
							</div>
						</div>
						</div>
		<jsp:include page="deviceHealth.jsp" />	
		<jsp:include page="modalPhoto.jsp" />	
	
</div>




