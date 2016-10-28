<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<script src="http://maps.google.com/maps/api/js?sensor=true"  type="text/javascript" ></script>
<script src="./resources/gmaps.js"  type="text/javascript" ></script>

 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	     TableEditable.init();
   	    	     FormComponents.init();
   	    	   	 $('#Ip_Cesc,#ipgpsViewData').addClass('start active ,selected');
   	    	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#MIS-Reports-photoBilling,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	   });
  
  function getFeederDataSearch(sdoCode)
	{
			 var feederCode = "";
			  var i=0;
			  $.ajax({
				  type:"POST",
				  url:"./getFeederDataSearch",
				  data:{"sdoCode":sdoCode},
				  dataType:"JSON",
				  success:function(response)
				  {
					feederCode+="<select id='feederCode' name='feederCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''>";
						for( i=0;i<response.length;i++){
							feederCode+="<option id='feederCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						feederCode+="</select><span></span>";
						$("#feederCodeDiv").html(feederCode);
						$('#feederCode').select2();
				  }
			  });
	}
  
  function UpdateDTC()
	 {
	  if($('#sdoCode').val().trim()=='')
		 {
		   bootbox.alert('Please select SDO Code');
		   return false;
		 }
	  
	  if($('#feederCode').val().trim()=='')
		 {
		   bootbox.alert('Please select Feeder Code');
		   return false;
		 }
	  
	  $.ajax({
		  type:"POST",
		  url:"./updateLatLang",
		  data:{"sdoCode":$('#sdoCode').val().trim(),"feederCode":$('#feederCode').val().trim()},
		  dataType:"JSON",
		  success:function(response)
		  {
			  alert("Latitude and Longitude updated succesffully")
		  }
	  });
	 }
  
  
	
  function viewIPSets()
	 {
	 
	  if($('#sdoCode').val().trim()=='')
		 {
		   bootbox.alert('Please select SDO Code');
		   return false;
		 }
	  
	  if($('#feederCode').val().trim()=='')
		 {
		   bootbox.alert('Please select Feeder Code');
		   return false;
		 }
	  
	  var tcCode = "";
		  var i=0;
		  $.ajax({
			  type:"POST",
			  url:"./getgpsTCData",
			  data:{"sdoCode":$('#sdoCode').val().trim(),"feederCode":$('#feederCode').val().trim()},
			  dataType:"JSON",
			  success:function(response)
			  {
				tcCode+="<label class=control-label>&nbsp;</label><select id='tcCode' name='tcCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder=''  value=''><option id='tcCodeOpt' value='all'>Select TC Code</option>";
				for( i=0;i<response.length;i++){
					tcCode+="<option id='tcCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
				}
				tcCode+="</select><span></span>";
				
				$("#tcCodeDiv").html(tcCode);
				$('#tcCode').select2();
				if('${tcCode}'!='')
				{
				$('#tcCode').val('${tcCode}'); 
				}
			  }
		  });
	 }
  
  /* function viewBoth(){
	  if($('#sdoCode').val().trim()=='')
		 {
		   bootbox.alert('Please select SDO Code');
		   return false;
		 }
	  
	  if($('#feederCode').val().trim()=='')
		 {
		   bootbox.alert('Please select Feeder Code');
		   return false;
		 }
	  
	  $.ajax({
		  type:"POST",
		  url:"./getGPSBothSearch",
		  data:{"sdoCode":$('#sdoCode').val().trim(),"feederCode":$('#feederCode').val().trim(),"dtcCode":$('#tcCode').val().trim(),"Auothorization":$('#Auothorization').val().trim()},
		  dataType:"JSON",
		  success:function(response)
		  {
				if(response.length ==0)
				{
				alert("No Records Found");
				}else{
					 var map = new GMaps({
				            div: '#map-canvas',
				            lat: response[0].latitude,
				            lng :response[0].longitude,
				            zoom:10,
				        });	
					 
					for(var i=0;i<response.length;i++)
					{ 
		    		     if(response[i].letter=='I')
		    			  {
		    			  labelName="I";
		    			  
		    			  if(response[i].autharized=='Yes')
		    			  {
		    		    	 map.addMarker({
						            lat: response[i].latitude,
						            lng: response[i].longitude,
						            icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
						            infoWindow: {
						                content: "<div style=width:290px;>"
						                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
						                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
						                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
												 +"<tr><th>Location</th><td>"+response[i].location+"</td></tr>"
												 +"<tr><th>RR Number</th><td>"+response[i].rrNo+"</td></tr>"
												 +"<tr><th>Customer Name</th><td>"+response[i].name+"</td></tr>"
												 +"<tr><th>Address</th><td>"+response[i].address+"</td></tr>"
												 +"<tr><th>Date Of Service</th><td>"+response[i].dateOfService+"</td></tr>"
												 +"<tr><th>Sanction Load</th><td>"+response[i].sanctionLoad+"</td></tr>"
												 +"<tr><th>DTC </th><td>"+response[i].transformer+"</td></tr>"
						                         +"</table>"
						                         +"</div>"
						            }
						        });
		    			  }else{
		    				  map.addMarker({
						            lat: response[i].latitude,
						            lng: response[i].longitude,
						            icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
						            infoWindow: {
						                content: "<div style=width:290px;>"
						                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
						                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
						                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
												 +"<tr><th>Location</th><td>"+response[i].location+"</td></tr>"
												 +"<tr><th>RR Number</th><td>"+response[i].rrNo+"</td></tr>"
												 +"<tr><th>Customer Name</th><td>"+response[i].name+"</td></tr>"
												 +"<tr><th>Address</th><td>"+response[i].address+"</td></tr>"
												 +"<tr><th>Date Of Service </th><td>"+response[i].dateOfService+"</td></tr>"
												 +"<tr><th>Sanction Load </th><td>"+response[i].sanctionLoad+"</td></tr>"
												 +"<tr><th>DTC </th><td>"+response[i].transformer+"</td></tr>"
						                         +"</table>"
						                         +"</div>"
						            }
						        });
		    			  }
		    			  }else{
		    				  map.addMarker({
						            lat: response[i].latitude,
						            lng: response[i].longitude,
						            icon: 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png',
						            infoWindow: {
						                content: "<div style=width:260px;>"
						                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
						                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
						                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
												 +"<tr><th>DTC Code</th><td>"+response[i].dtc+"</td></tr>"
												 +"<tr><th>TIMS Code</th><td>"+response[i].tims+"</td></tr>"
												 +"<tr><th>Sub Station</th><td>"+response[i].substation+"</td></tr>"
												 +"<tr><th>Feeder Number</th><td>"+response[i].feederName+"</td></tr>"
												 +"<tr><th>Number Of IP Sets&nbsp;&nbsp;</th><td>"+response[i].noIPSet+"</td></tr>"
						                         +"</table>"
						                         +"</div>"
						            }
						        });
		    			  }
					}
				}
		  } 
	  });
  } */
  function viewDTC()
	 {
		  if($('#sdoCode').val().trim()=='')
			 {
			   bootbox.alert('Please select SDO Code');
			   return false;
			 }
		  
		  if($('#feederCode').val().trim()=='')
			 {
			   bootbox.alert('Please select Feeder Code');
			   return false;
			 }
		  
		  $.ajax({
			  type:"POST",
			  url:"./getGPSDTCViewSearch",
			  data:{"sdoCode":$('#sdoCode').val().trim(),"feederCode":$('#feederCode').val().trim()},
			  dataType:"JSON",
			  success:function(response)
			  {
				  var map='';
				  
				  for(var i=0;i<response.length;i++)
					{
					  if(response[i].latitude.trim()!=null || response[i].longitude.trim()!=null)
    				  {
					  map= new GMaps({
				            div: '#map-canvas',
				            lat: response[i].latitude,
				            lng :response[i].longitude,
				            zoom:10,
				        });	
    				  }
					}
				  
				  if(response.length ==0)
					{
					alert("No Records Found");
					}else{
						for(var i=0;i<response.length;i++)
						{
							var labelName='tc';
			    		    	 map.addMarker({
							            lat: parseFloat(response[i].latitude),
							            lng: parseFloat(response[i].longitude),
							            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|75A3FF|000000',
							            infoWindow: {
							                content: "<div style=width:290px;>"
							                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
							                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
							                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
													 +"<tr><th>Tims</th><td>"+response[i].tims+"</td></tr>"
													 +"<tr><th>Substation</th><td>"+response[i].substation+"</td></tr>"
													 +"<tr><th>FeederName</th><td>"+response[i].feederName+"</td></tr>"
													 +"<tr><th>DTC Code</th><td>"+response[i].dtc+"</td></tr>"
													 +"<tr><th>NoIPSet</th><td>"+response[i].noIPSet+"</td></tr>"
							                         +"</table>"
							                         +"</div>"
							            }
							        });
			    			  }
			    			  }
					}
		  });
	 }
  
  function showIPSets()
	 {
		 
		  
		  if($('#tcCode').val().trim()=='')
			 {
			   bootbox.alert('Please select DTC Code');
			   return false;
			 }
		  
		  $.ajax({
			  type:"POST",
			  url:"./getGPSIPViewSearch",
			  data:{"sdoCode":$('#sdoCode').val().trim(),"feederCode":$('#feederCode').val().trim(),"dtcCode":$('#tcCode').val().trim(),"Auothorization":$('#Auothorization').val().trim()},
			  dataType:"JSON",
			  success:function(response)
			  {
				  var map='';
					if(response.length ==0)
					{
					alert("No Records Found");
					}else{
						for(var i=0;i<response.length;i++)
						{ 
							if(response[i].latitude.trim()!=null || response[i].longitude.trim()!=null)
		    				  {
								map = new GMaps({
						            div: '#map-canvas',
						            lat: parseFloat(response[i].latitude),
						            lng :parseFloat(response[i].longitude),
						            zoom:10,
						        });	
		    				  }
							 
						 
						}
						var labelName='ip';
						for(var i=0;i<response.length;i++)
						{ 
							if(response[i].autharized=='N')
								{
			    		    	 map.addMarker({
							            lat:response[i].latitude,
							            lng:response[i].longitude,
							            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|FF7A59|000000',
							             infoWindow: {
							                content: "<div style=width:290px;>"
							                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
							                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
							                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
													 +"<tr><th>Location</th><td>"+response[i].location+"</td></tr>"
													 +"<tr><th>RR Number</th><td>"+response[i].rrNo+"</td></tr>"
													 +"<tr><th>Customer Name</th><td>"+response[i].name+"</td></tr>"
													 +"<tr><th>Address</th><td>"+response[i].address+"</td></tr>"
													 +"<tr><th>Date Of Service</th><td>"+response[i].dateOfService+"</td></tr>"
													 +"<tr><th>Sanction Load</th><td>"+response[i].sanctionLoad+"</td></tr>"
													 +"<tr><th>DTC </th><td>"+response[i].transformer+"</td></tr>"
							                         +"</table>"
							                         +"</div>"
							            } 
							        });
								}
							else
								{
								
			    		    	 map.addMarker({
							            lat:response[i].latitude,
							            lng:response[i].longitude,
							            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|00CC99|000000',
							             infoWindow: {
							                content: "<div style=width:290px;>"
							                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
							                         +"<tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
							                         +"<tr><th>Lattitude</th><td>"+response[i].latitude+"</td></tr>"
													 +"<tr><th>Location</th><td>"+response[i].location+"</td></tr>"
													 +"<tr><th>RR Number</th><td>"+response[i].rrNo+"</td></tr>"
													 +"<tr><th>Customer Name</th><td>"+response[i].name+"</td></tr>"
													 +"<tr><th>Address</th><td>"+response[i].address+"</td></tr>"
													 +"<tr><th>Date Of Service</th><td>"+response[i].dateOfService+"</td></tr>"
													 +"<tr><th>Sanction Load</th><td>"+response[i].sanctionLoad+"</td></tr>"
													 +"<tr><th>DTC </th><td>"+response[i].transformer+"</td></tr>"
							                         +"</table>"
							                         +"</div>"
							            } 
							        });
								
								}
							
			    			  }
						
					}
			  } 
		  });
		  
		  $('#closeBtn').click();
	 }
	
  
  
  </script>
<div class="page-content" >

				<div class="portlet-body flip-scroll">
					<div>
					<div class="col-md-3"> 
					 <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederDataSearch(this.value)">
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodeSearch}">
																<option value="${element[0]}" id="sdoCodeOpt">${element[0]}-${element[1]}</option>
															</c:forEach>
														  </select>
														  </div>
						<div class="col-md-3" id="feederCodeDiv">
						  <select id="feederCode" class="form-control selectpicker placeholder-no-fix input-medium" name="feederCode">
						  <option value='0' selected=selected>Select Feeder Code</option>    
						  </select>
						  </div>
						  <button class="btn green pull-left" style="display: block;margin-left: 20px;" onclick="return viewDTC()">View DTC&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button>
						  <button class="btn green pull-left" data-target="#stack5" data-toggle="modal" style="display: block;margin-left: 20px;" onclick="return viewIPSets()">View IP Set'S&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button>
						  <button class="btn green pull-left"  style="display: none;margin-left: 20px;" onclick="return viewBoth()">Both&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button>
						  <button class="btn green pull-left"  style="display: block;margin-left: 20px;" onclick="return UpdateDTC()">Update IP&DTC&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button>
						  
						 <!--  UpdateDTC -->
					</div>
				</div>
				<br>
				


							
					<div id="stack5" class="modal fade" tabindex="-1" data-width="400" style="display: none;">
							 <div class="modal-dialog">
									<div class="modal-content">
										 
										<div class="modal-body">
											<div class="row">
											<div class="col-md-12">
												
												<table>
												<tr>
												<td>DTC Code</td>
												<td><font color="red">*</font></td>
												<td>
														  <div class="col-md-3" id="tcCodeDiv" style="width: 250px;">
															<label class="control-label">&nbsp;</label>
															<select id="tcCode" class="form-control selectpicker placeholder-no-fix input-medium" name="tcCode">
														    <option value='all'>Select DTC Code</option>
														  	</select><span></span>	
														</div>
												</td>
												</tr>
												
												<tr>
												<td>Authorization</td>
												<td><font color="red">*</font></td>
												<td>
														  <div class="col-md-3" id="tcCodeDiv" style="width: 250px;">
															<label class="control-label">&nbsp;</label>
															<select id="Auothorization" class="form-control selectpicker placeholder-no-fix input-medium" name="Auothorization">
														    <option value='' >Select Authorization</option>
														    <option value='Y' >Authorized</option>
														    <option value='N' >UnAuthorized</option>
														    <option value='both'>Both</option>
														  	</select>	
														</div>
												</td>
												</tr>
												
												<tr>
																									  
												</table>
													<div class="modal-footer">
													     <button class="btn blue pull-right" onclick="return showIPSets()">View</button>
													     <button type="button" data-dismiss="modal" class="btn" id="closeBtn">Cancel</button>
													</div>
													
											</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>
							
							        <div class="portlet box green" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Google Map</div>
							<div class="tools">								
								<a href="#" class="close" onclick="hideMap()"></a>
							</div>
						</div>
				<div class="portlet-body">
							<div id="map-canvas" class="gmaps" style="height: 500px"></div>				 
               <!-- END PORTLET-->
               </div>
               </div>
							
							</div>

<style type="text/css">
#map-canvas {
        width: 900px;
        height: 400px;
      }
</style>