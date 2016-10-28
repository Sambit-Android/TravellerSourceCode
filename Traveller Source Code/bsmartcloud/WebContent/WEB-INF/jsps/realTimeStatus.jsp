<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <script src="<c:url value='/resources/bsmart.lib.js/chartscript/js/highcharts.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/bsmart.lib.js/highchartsExporting.js'/>" type="text/javascript"></script> --%>
<!-- <style>
hr {
    display: block;
    height: 1px;
    border: 1;
    border-top: 2px solid #ccc;
}
.container {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
.container1 {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
.container2 {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
</style> -->

 <script  type="text/javascript">
 
  $(".page-content").ready(function()
   	    	   {  
   	    	        App.init();   	    	       
   	    	        TableEditable.init();
   	    	        FormComponents.init();
   	    	     //loadSearchAndFilter('sample_4'); ;
   	    	      $('#MIS-Reports-photoBilling').addClass('start active ,selected');
	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
	    		    });  
 
  
  setInterval(
		  function(){
		    $.ajax(
					{
						type : "GET",
						url : "./GetAllrealTimeStatusData",						
						cache:false,
						async:false,						
						 success : function(response)
						 {
							// var notLive=response[response.length-1];
							 var res=response[response.length-1]; // last list contains groupby sdocode,bmdReading,count, also total billed today, and also total sdocode;
							 setTimeout(function() {
								    $(window).lazyLoadXT();								    
								  }, 50);
							 	    
							 $('#displayValues').empty();
							 var record="<tr>";
							 for(var i=0;i<response.length-1;i++) //response.length-2 contains other Data List as above
								 {//
								 var result=response[i];
								 var gruopRes=res[i];
								 var  mrBilled=gruopRes[2];	
								 var extra6Val=result[6].split(',');
								  // mrData
								 record=record+"<td>"+parseInt(i+1)+"</td><td><a href=# id=mrData"+result[0]+""+result[1]+" onclick=showMrDetails(this.id,'"+result[0]+"','"+result[1]+"') title=View MR Details>"+result[0]+"</a></td><td>"+result[1]+"</td><td>"+result[8]+"</td><td>"+mrBilled+"</td><td>"+result[3]+" "+result[4]+"</td><td><a href=# onclick=getBilledData(this.id,'"+result[0].trim()+"','"+result[1].trim()+"'); id='billedData"+result[0].trim()+""+result[1].trim()+"'><i class='fa fa-sitemap' ></i></a></td><td><a href=# onclick=getMobileHealth(this.id,'"+result[0].trim()+"','"+result[1].trim()+"','"+result[3].trim().replace('/','-').replace('/','-')+"','"+result[8]+"'); id='memoryData"+result[0].trim()+""+result[1].trim()+"'><i class='fa fa-mobile-phone (alias)'></i></a></td><td>"+" "+extra6Val[0]+"&nbsp;%"+"</td><td>"+" "+extra6Val[1]+"&nbsp;%"+"</td><td>"+" "+Math.round((extra6Val[3]/1000)*100)/100+"</td></tr>"; //<td>"+result[2]+"</td><td><a href=#><img class=lazy data-src=./imageDisplay/getImage/"+result[2].trim()+"/"+result[1].trim()+"/"+result[5].trim()+" onclick=viewDocument('"+escape(result[2].trim())+"','"+result[1].trim()+"','"+result[5].trim()+"'); data-toggle=modal  data-target='#popup_image' style='background: url(./resources/bsmart.lib.js/loading.gif) no-repeat 50% 50%';  height=70 width=70 id=imagesId /></a></td>    //<td><a href=# onclick=getGPSData(this.id,'"+result[0]+"','"+result[1]+"','"+result[5]+"','"+result[3]+"'); id='gpsData"+result[0].trim()+""+result[1].trim()+"'><i class='fa fa-map-marker' ></i></a></td>   //<td><a href=# onclick=getMobileData(this.id,'"+result[0].trim()+"','"+result[1].trim()+"','memory'); id='memoryData"+result[0].trim()+""+result[1].trim()+"'><i class='fa fa-mobile-phone (alias)'></i></a>&nbsp;<a href=# onclick=getMobileData(this.id,'"+result[0].trim()+"','"+result[1].trim()+"','signalBatery'); id='signalBateryData"+result[0].trim()+""+result[1].trim()+"'><i class='fa fa-signal'></i></a></td>
								
								 }
							 var html2 = "<thead><tr><th>SlNo.</th><th>SECTION</th><th>SDOCODE</th></tr></thead><tbody>";
							 /* for(var i=0;i<notLive.length;i++) //response.length-1 contains Not live data
							 {
							    		     html2 += "<tr><td>"+(i+1)+"</td>";
							    		     html2 += "<td>"+notLive[i][0]+"</td>";
							    		     html2 += "<td>"+notLive[i][1]+"</td></tr>";
							    		
						     } 
							 $('#sample_4').empty();
						    	html2+="</tbody>";	
						    	$('#sample_4').html(html2);
						    	$('#mobileDiv').show();*/
						    	
								 
							 
							 $('#CountBilled').html(res[res.length-4]);
							 $('#countBmd').html(response.length-1);
							 $('#countSdo').html(res[res.length-3]);
							 //$('#totaldemand').html(res[res.length-2]);
							 $('#totaldeviceCount').html(res[res.length-1]);
							 $('#displayValues').append(record);
						 }
				 }
		  );  
		 
		  }
		  , 20000);
  
  //SELECT * FROM photobilling.hhbm_download WHERE TRIM(consumer_sc_no) LIKE 'ELAEH40297';

  function viewConsumerOnMap(consumerno)
	{
	  var arr=consumerno.split('@');
	  var rrno=arr[0];
	  var yearmonth=arr[1];
	  var sdocode=arr[2];
		$('#gmapsContent').hide();
		$.ajax({
	    	url:'./viewConsumerOnMap'+'/'+rrno+'/'+yearmonth+'/'+sdocode,
	    	type:'GET',
	    	dataType:'json',
	    	asynch:false,
	    	cache:false,
	    	beforeSend: function(){
		        $('#image').show();
		    },
		    complete: function(){
		        $('#image').hide();
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
			             //icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+response[i].consumer_Sc_No+'|FF8000|000000',
			            		  icon:'./resources/bsmart.lib.js/gmapMarker.png',
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
		  function getBilledData(param,mrName,sdoCode)
	        {					
					$.ajax(
							{
									type : "GET",
									url : "./getRealStatusBilledData/"+mrName+"/"+sdoCode,
									dataType : "json",
									async:false,
									beforeSend: function(){
								        $('#imageee').show();
								    },
								    complete: function(){
								        $('#imageee').hide();
								    },
									success : function(response)
																{	
										                             $('#titleId').html('MRwise billed, Section code- '+sdoCode+' MR code- '+mrName);
														    		setTimeout(function() {
																		    $(window).lazyLoadXT();								    
																		  }, 50);
														    		var tableData="<table class='table table-striped table-hover' id=billTable>";
														    		
														    		for(var i=0;i<response.length;i++)
														    			{//<tr><td><a href="#"><img class="lazy" data-src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}/${fn:trim(element.sdoCode)}/${fn:trim(element.billMonth)}" style="background: url('./resources/bsmart.lib.js/loading.gif') no-repeat 50% 50%;" border="0"  data-toggle="modal"  data-target='#popup_image'   height="150" width="200"   title="${fn:trim(element.consumer_Sc_No)}" onclick="viewDocument('${fn:trim(element.consumer_Sc_No)}','${fn:trim(element.sdoCode)}','${fn:trim(element.billMonth)}');"/></a></td></tr>
															    			var billDate=response[i].billDate.split('-');
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
																				  +"<tr><th>&nbsp; </th><td><a href='#' id='"+response[i].consumer_Sc_No+"@"+bilMth+"@"+sdoCode+"' onclick='return viewConsumerOnMap(this.id)' data-toggle=modal data-target=#stack6 >View on Map</a></td>"
																				  +"</table></td></tr></table>";		  
														    			}
														    		tableData=tableData+"</table>";														    		
														    		$('#billedData').html(tableData);
																}
							}
					    );
				$('#'+param).attr("data-toggle", "modal");
			    $('#'+param).attr("data-target","#stack10");
					
		    }
		  function getGPSData(param,mrName,sdoCode,billMonth,billDate)
		    {			  
			  $('#sCode1').val(sdoCode);
			  $('#bmdRr1').val(mrName);
			  $('#bMonth1').val(billMonth);
			  $('#bDate1').val(billDate);
			  //showGPS('bmdButton');			  
			  
			}
          function getMobileData(param,mrName,sdoCode,chartType)
		    { 
        	  $.ajax(
						{
								type : "GET",
								url : "./realTimeStatusDeviceHealth/"+mrName+"/"+sdoCode,
								dataType : "json",
								async:false,								
								success : function(response)
									{
									var batteryData=new Array();
									var signalData=new Array();
									var memoryData=new Array();
									var totalMemory="";
									for(var i=0;i<response.length;i++)
										{
										// var dateVal=new Date(parseInt(response[i].reporteddateandtime)).getHours();
										  var timeVal=(moment(new Date(parseInt(response[i].reporteddateandtime))).format("HH.mm"));
										     var dateVal=new Date(response[i].reporteddateandtime);
										     var utcVal=Date.UTC(dateVal.getFullYear(),dateVal.getMonth(),dateVal.getDate(),dateVal.getHours(),dateVal.getMinutes(),dateVal.getSeconds(),dateVal.getMilliseconds());
										  batteryData.push([utcVal,response[i].batterylevel]);
										  signalData.push([utcVal,response[i].signalstrength]);
										  memoryData.push([utcVal,response[i].memoryinternalavailable]);
										  totalMemory=response[i].memoryinternalaltotal;
										}
									chartGraph(batteryData,signalData,memoryData,totalMemory,chartType);								
								    }
						});
						
						$('#'+param).attr("data-toggle", "modal");
					    $('#'+param).attr("data-target","#stackChart");
		    }
          
          function showMrDetails(param, mrcode, sdocode)
          {
          	$.ajax(
          			{
          					type : "GET",
          					url : "./showMrOracleDetails/"+mrcode+"/"+sdocode,
          					dataType : "json",
          					async:false,	
          					cache:false,
          					success : function(response)
          						{
          							   if(response.mobileNo!=null)
          								   {
          								 $('#mrMobileVal').text(response.mobileNo);
          								   }
          							   else
          								   {
          								 $('#mrMobileVal').text('-');
          								   }
          							 if(response.MrName!=null)
    								   {
          								$('#mrNameVal').text(response.MrName);
    								   }
    							   else
    								   {
    								    $('#mrNameVal').text('-');
    								   }
          						}
          			});	
          	
          	$('#'+param).attr("data-toggle", "modal");
          	$('#'+param).attr("data-target","#stackmrDetails");
          }
  </script>
<div class="page-content" >

<div class="clearfix"></div>

           <div class="top-news">
								
								<a href="#" class="btn blue">
								<span>Meter ReaderWise Live Status</span>
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
							<div class="number" id="CountBilled">
								${billed}
							</div>
							<div class="desc">                           
								Billed Today
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
							<div class="number" id="countBmd">${countBmd}</div>
							<div class="desc">MRs Working</div>
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
							<div class="number" id="countSdo">${totalSdo}</div>
							<div class="desc">Sections Live</div>
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
							<div class="number" id="totaldeviceCount">${deviceCount}</div>
							<div class="desc">Total Mobiles Live</div>
						</div>
						<!-- <a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a> -->                 
					</div>
				</div> 
			</div>
			
      <!-- <a href="#" data-toggle=modal  data-target='#stackChart'>hhh</a> -->

                   <!-- BEGIN MARKERS PORTLET-->
					<!-- <div class="portlet box green" style="display: none;" id="gmapsContent">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Markers</div>
							<div class="tools">								
								<a href="#" class="close" onclick="hideMap()"></a>
							</div>
						</div>
				<div class="portlet-body">
							<div id="gmap_marker" class="gmaps" style="height: 500px"></div>				 
               END PORTLET
               </div>
               </div> -->
               <div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait.
						<!-- <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:10%;height: 10%;"> -->
						</h3>
						</div>
               
			<div class="row ">
				<div class="col-md-12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-bell-o"></i>Live Status of Meter ReaderWise </div>
							
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
											<th >MRcode</th>
											<th >Sdocode</th>
											<th >Section</th>
											<th >Billed Today</th>
											<!-- <th >consumer No</th> -->
											<th >Time&Date</th>
											<!-- <th >Last photo taken</th> -->
											<th>Bill View</th>
											<!-- <th>GPS View</th> -->
											<th>Device</th>
											<th><i class="fa fa-flash (alias)"  title="Battery(in %)"></i></th>
											<th><i class="fa fa-signal" title="Signal(in %)"></i></th>
											<th><i class="fa fa-save (alias)" title="Memeory(in GB)"></i></th>
											
										</tr>
									</thead>
									<tbody id="displayValues">
									 <c:set var="count" value="0" scope="page" />
									 <c:forEach var="element" items="${allvalues}">
									 <tr>
										 	<td>${count+1}</td>
										 	<td><a href="#" id="mrData${element[0]}${element[1]}" onclick="showMrDetails(this.id,'${element[0]}','${element[1]}')" title="View MR Details">${element[0]}</a></td>
										  	<td>${element[1]}</td>
										  	<td>${element[8]}</td>
										  	<c:set value="${fn:split(element[6],',')}" var="extra6Val"/>
										   	<td>${mrBilled[count]}</td>
										   	<%-- <td> ${fn:trim(element[2])}</td> --%>
										    <td>${element[3]} ${element[4]}</td> 
									    <%--  <td><a href="#"><img data-magnifyby="10" class="lazy" data-src="./imageDisplay/getImage/${fn:trim(element[2])}/${fn:trim(element[1])}/${fn:trim(element[5])}" style="background: url('./resources/bsmart.lib.js/loading.gif') no-repeat 50% 50%;" border="0"  data-toggle="modal"  data-target='#popup_image' onclick="viewDocument('${fn:trim(element[2])}','${fn:trim(element[1])}','${fn:trim(element[5])}');"   height="70" width="70"/></a></td> --%>
									   <td><a href="#" onclick="return getBilledData(this.id,'${element[0]}','${element[1]}');" id="billedData${element[0]}${element[1]}"><i class="fa fa-sitemap" ></i></a></td>
									   <%-- <td><a href="#" onclick="return getGPSData(this.id,'${element[0]}','${element[1]}','${fn:trim(element[5])}','${element[3]}');" id="gpsData${element[0]}${element[1]}"><i class="fa fa-map-marker"></i></a></td> --%>
									 <%--   <td><a href="#" onclick="return getMobileData(this.id,'${element[0]}','${element[1]}','memory');" id="memoryData${element[0]}${element[1]}"><i class="fa fa-mobile-phone (alias)"></i></a>&nbsp;&nbsp;<a href="#" onclick="return getMobileData(this.id,'${element[0]}','${element[1]}','signalBatery');" id="signalBateryData${element[0]}${element[1]}"><i class="fa fa-signal"></i></a></td> --%>
									 <fmt:formatDate pattern="dd-MM-yyyy" value="<%=new Date()%>" var="todayDate"/>
									 <td><a href="#" onclick="return getMobileHealth(this.id,'${element[0]}','${element[1]}','${todayDate}','${element[8]}');" id="memoryData${element[0]}${element[1]}"><i class="fa fa-mobile-phone (alias)" title="view on graph"></i></a></td>
									 <td>${extra6Val[0]}&nbsp;%</td>
									  <td>${extra6Val[1]}&nbsp;%</td>
									  <c:set value="${extra6Val[3]/1000}" var="memory" ></c:set>
									   <td><fmt:formatNumber value="${memory}" type="number" pattern="#.##">
									   </fmt:formatNumber></td>
									 </tr>
									 <c:set var="count" value="${count+1}" scope="page"/>
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
		         
							<div id="stack10" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
						        <div class="modal-dialog">
							       <div class="modal-content" style="width:200%;margin-left:-200px">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title" id="titleId">Add User</h4>
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
					
					<form:form id="bmdButton" action="" modelAttribute="hhbmDownLoad" commandName="hhbmDownLoad" method="post" hidden="true">
					
						    <form:input path="" type="text" hidden="true" id="value" name="value" value="meterWise"></form:input>
						    
						    <form:input path="sdoCode" type="text" hidden="true" id="sCode1" name="sCode"></form:input>
						    						    
							<form:input path="billDate" type="text" hidden="true" id="bDate1" name="bDate"></form:input>
							
							<form:input path="billMonth" type="text" hidden="true" id="bMonth1" name="bMonth" ></form:input>
							
							<form:input path="bmd_Reading" type="text" hidden="true" id="bmdRr1" name="bmdRr"></form:input>
							
						  
						</form:form>
						<!-- <div id="stackChart" class="modal fade" tabindex="-1"  data-keyboard="false">
						        <div class="modal-dialog" style="width: 90%;">
							       <div class="modal-content" >
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title" id="titleId">Device Health&nbsp;&nbsp;&nbsp;<span class="label label-warning"><font size="3" color="black"><b>SECTION:-</b><span class="label label-warning" ><font size="3" color="black" ><b id="sdoId"></b></font></span></font></span>
									&nbsp;&nbsp;&nbsp;<span class="label label-success"><font size="3" color="black"><b>MRCODE:-</b><span class="label label-success" ><font size="3" color="black" ><b id="mrcodeId"></b></font></span></font></span>
									</h4>
									
								</div>
								<div class="modal-body">
									 <div id="batterySignal" style="width: 100%; height: 400px;"></div>
									<table class="" id="sample_3" >
									<tr>
									<td >
									<div id="batterySignal" class="container" >
									</div>
									</td>
									<td>
									<div id="batterySignal11"  >
									</div>
									</td>
									</tr>
									
									</table>
									<div id="Signal" class="container1" ></div>
									 <div id="memory" class="container2"></div>
									 
								</div>
							</div>
						</div>
					</div> -->
					
		            </div>
					</div>
				</div>				
			</div>		
			
			<div class="row">
			
			<div id="stack6" class="modal fade" tabindex="-1" data-width="400" data-keyboard="false">
							<div class="modal-dialog" style="width: 60%;" >
								<div class="modal-content">
								
									<div class="modal-header">
									<div id="image" hidden="true" style="text-align: center;height: 100%;width: 100%;" >
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:4%;height: 4%;margin-right: 10px;">
						</div>
									
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
						<%-- <div class="col-md-6" id="mobileDiv" >
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Sections Not Live </div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="sample_4">
								<thead>
									<tr>
										<th>SLNo.</th>
										<th>SDOCODE</th>
										<th>SECTION</th>
									</tr>
								</thead>
								<tbody id="mobileTb">
								<c:set value="1" var="count"/>
									<c:forEach var="element" items="${notLiveSections}">
									<tr>
									    <td>${count}</td>
									    <td>${element[0]}</td>
									    <td>${element[1]}</td>
								    </tr>
								    <c:set value="${count+1}" var="count"/>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div> --%>
						</div>
						
			
			<div id="stackmrDetails" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
							<div class="modal-dialog" >
								<div class="modal-content">
								
									<div class="modal-header">
									<button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
									<h4>MR Details</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-md-12">
											<table class="table table-striped table-hover table-bordered" id="table_4" >
													<tr><th>MRName</th><td ><span id="mrNameVal"></span></td></tr>
													<tr><th>MRMobileNo.</th><td ><span id="mrMobileVal"></span></td></tr>
													<tbody id="mrDataTb">
													</tbody>
													</table>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>
						
						<jsp:include page="deviceHealth.jsp" />	
						<jsp:include page="modalPhoto.jsp" />	
</div>



