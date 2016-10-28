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
  		
  		  $('#MIS-Reports-photoBilling').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		   $('#gpsViewButton').hide();
  		   
  		   }); 	
  		    	
  	 var divCode;
  	var sectionname;
  	function viewMrwiseData()
  	{
  		$('#gmapsContent').hide();
  		var locCode=$('#locCode').val();
  		$.ajax({
	    	url:'./showMrTrackLive'+'/'+locCode+'/'+$('#fromDate').val(),
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
		    		   if(response[i][13]==null)
		    			  {
		    			  response[i][13]='';
		    			  }
		    		  var mrname=response[i][13];
			          var mob=response[i][14]; 
		    		  if(!initialLocation)
		    			  {				    			 
		    			  if(response[i][7].trim()!=''  || response[i][6].trim()!='')
		    				  {					    				  
		    				     document.getElementById('gmapsContent').style.display='block';
				    			      map = new GMaps({
						              div: '#gmap_marker',
						               /* lng:parseFloat(response[i][6]),
						              lat:parseFloat(response[i][7]),  */
						              center: {lat: parseFloat(response[i][7]), lng: parseFloat(response[i][6])},
						              zoom:9,
						          });
				    			      
				    			     initialLocation=true;
				    			     
		    			     }
		    			  }
		    		  
		    		if(initialLocation)
		    			 {
		    						    			 
		    		      map.addMarker({
			              lng: parseFloat(response[i][6]),
			              lat: parseFloat(response[i][7]),
			              //icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|FF8000|000000',
			             icon:'./resources/bsmart.lib.js/gmapMarker.png',
			              //icon: 'https://cdn3.iconfinder.com/data/icons/human-resources-2-1/128/51-24.png',
		    		      
			              title:"please click on marker to check details",
			              infoWindow: {
			                  content: 
			                  "<div >"
			                  +"<table  id=billTable1 class='table table-striped table-bordered table-hover' style='background: #99ccff;'>"
							  +"<tr><th>MR code   </th><td>"+response[i][0]+"</td></tr>"
							  +"<tr><th>MR Name   </th><td>"+mrname+"</td></tr>"
							  +"<tr><th>Mobile No   </th><td>"+mob+"</td></tr>" 
							  +"<tr><th>Section    </th><td>"+response[i][10]+"</td></tr>"
							  +"<tr><th>Sdocode   </th><td>"+response[i][1] +"</td></tr>"
							  +"<tr><th>Last Billed RR No. </th><td>"+response[i][2] +"</td></tr>"
							  +"<tr><th>Total Billed today</th><td>"+response[i][11] +"</td></tr>"
							  +"<tr><th>Total Billed in the Month </th><td>"+response[i][12] +"</td></tr>" 
							  +"<tr><th>Last Billed</th><td>"+response[i][4]+"</td></tr>"
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
  	
  	function getLocationTypeVal(locType)
    {
  	  $.ajax({
  	    	url:'./getLocationTypeVal'+'/'+locType,
  	    	type:'GET',
  	    	dataType:'json',
  	    	asynch:false,
  	    	cache:false,
  	    	success:function(response)
  	    	{
  	    		var locCode='';
  	    		locCode+="<select id='locCode' name='locCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='showViewButton()'><option value='select' selected='selected' >Select "+locType+"</option>";
  				for( var i=0;i<response.length;i++){
  					locCode+="<option  value='"+response[i][1].trim()+"'>"+response[i][0]+"</option>";
  				}
  				locCode+="</select><span></span>";
  				
  				$("#locationData").html(locCode);
  				$('#locCode').select2();
  	    	}
  	  }); 
    }
  	
  	function showViewButton()
  	{
  		 $('#gpsViewButton').show();
  	}
  	
 	</script>

<div class="page-content">

	<div class="portlet box blue">
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>GPS MR Tracking </div>   
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						
						<div class="portlet-body">
					
						 <div class="col-md-12">
										
										<table >
										<tr >
										
										<td>
										      <select id="locationType" class="form-control select2me input-medium" name="locationType" onchange="getLocationTypeVal(this.value)" >
														    <option value="select" selected="selected">Select LocationType</option>
															<option value="circle" >Circle</option>
															<option value="division" >Division</option>
															<option value="subdivision" >Subdivision</option>
															<option value="section" >Section</option>
														  </select>
													</td>
										
												<td id="locationData">
												
														  </td>
														  <td>
										      <div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" id="fromDate" readonly="true" />
																
														</div>
													</td>
														  <td><div><button class="btn blue pull-left" id="gpsViewButton" onclick="viewMrwiseData();">view</button></div></td>
										</tr>
								
										</table>
										
									
						</div>
						</div>
		</div>
		<div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div>
		<div class="row">				
			
					<!-- BEGIN MARKERS PORTLET-->
					<div class="portlet box green" style="display: none;" id="gmapsContent">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Markers</div>
							<div class="tools">								
								<a href="#" class="close" onclick="hideMap()"></a>
							</div>
						</div>
				<div class="portlet-body">
							<div id="gmap_marker" class="gmaps" style="height: 500px"></div>				 
               <!-- END PORTLET-->
               </div>
               </div>
              
               </div>
	
</div>




