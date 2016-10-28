<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
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
   	    	$('#fromDate').val('${cuurentDate}');
   	    	 $('#gpsViewButton').hide();
   	    	
   	    	$('#MIS-Reports-photoBilling').addClass('start active ,selected');
	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
	    		   });
 
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
	    		locCode+="<select id='locCode' name='locCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select "+locType+"</option>";
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
  
   function validation()
  {
	   $('#gmapsContent').hide();
	  var sdCd=document.getElementById("locCode").value;
	  var bMonth=document.getElementById("fromDate").value;
	  var arrear=document.getElementById("arrear").value;

		$.ajax({
	    	url:'./getConsumersArrear'+'/'+sdCd+'/'+bMonth+'/'+arrear,
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
	    		if(response.length==0)
	    			{
	    			   bootbox.alert('No data available.');
	    			   return false;
	    			}
		          var initialLocation=false;
		          var map="";
		          
		    	  for(var i=0;i<response.length;i++)
					{ 
		    		 
		    		  var custName=response[i][6];
		    		  var mob='';
		    		  if(response[i][7]!=null)
		    			  {
		    			    mob=response[i][7];
		    			  }
			          //var mobmr="";
			          var loc=response[i][12];
			          var codeta=response[i][9];
			          var mrcode=response[i][8];
			          var conAddress=response[i][10]+" "+response[i][11];;
		    		  if(!initialLocation)
		    			  {				    			 
		    			  if(response[i][1].trim()!=''  || response[i][0].trim()!='')
		    				  {					    				  
		    				     document.getElementById('gmapsContent').style.display='block';
				    			      map = new GMaps({
						              div: '#gmap_marker',
						              lng:parseFloat(response[i][1]),
						              lat:parseFloat(response[i][0]),
						              zoom:9,
						          });
				    			      
				    			     initialLocation=true;
				    			     
		    			     }
		    			  }
		    		  
		    		if(initialLocation)
		    			 {
		    						    			 
		    		      map.addMarker({
			              lng: parseFloat(response[i][1]),
			              lat: parseFloat(response[i][0]),
			             // icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|FF8000|000000',
			            		   icon:'./resources/bsmart.lib.js/gmapMarker.png',
			              ///icon: 'https://cdn2.iconfinder.com/data/icons/flaturici-set-6/512/invoice-24.png',
			 
			            		  title:"please click on marker to check details",
			              infoWindow:
			              {
			            	  content: 
				                  "<div >"
				                  +"<table  id=billTable1 class='table table-striped table-bordered table-hover' style='background: #99ccff;'>"
				                  +"<tr><th>Amount</th><td>"+parseFloat(response[i][3])+"</td></tr>"
				                  +"<tr><th>RR No. </th><td>"+response[i][2]+"</td></tr>"
				                  +"<tr><th>CustomerName</th><td>"+custName+"</td></tr>"
				                  +"<tr><th>Customer Mobile No.</th><td>"+mob+"</td></tr>"
				                  +"<tr><th>Customer Addr</th><td>"+conAddress+"</td></tr>"
				                  +"<tr><th>Section</th><td>"+loc+"</td></tr>"
				                  +"<tr><th>Sdocode</th><td>"+response[i][5]+"</td></tr>"
								  +"<tr><th>BillMonth   </th><td>"+response[i][4] +"</td></tr>"
								  +"<tr><th>MR Code</th><td>"+mrcode+"</td></tr>"
								  //+"<tr><th>MR mobile No.</th><td>"+mobmr+"</td></tr>"
								  +"<tr><th>TariffCode</th><td>"+codeta+"</td></tr>"
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
  
  </script>
<div class="page-content" >
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
               
                  <div class="portlet box blue" style="width: 100%">
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Arrearswise Consumer view </div>   
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						
						<div class="portlet-body" >
					
						 <div class="col-md-12">
										
										<table  >
										<tr >
										
										<td >
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
										<select   class="form-control" id="arrear" name="arrear" onchange='showViewButton()'>
													<option value="1">Select Arrear</option>
													  <option value="5000 and 49999">Between 5000 and 49999</option>
													  <option value="50000 and 99999">Between 50000 and 99999</option>
													  <option value="100000">More Than 100000</option>
										</select> 
														  </td>
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
					<div class="portlet box purple" style="display: none;" id="gmapsContent" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Consumers</div>
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
               <jsp:include page="modalPhoto.jsp" />
                 
               <!-- END PORTLET-->
</div>