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
  		    	getLocationTypeVal('section');
  		   }); 	
  		    	
  	 var divCode;
  	var sectionname;
  	function viewMrwiseData()
  	{
  		$('#gmapsContent').show();
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
  	    		locCode+="<select id='locCode' name='locCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' ><option value='select' selected='selected' >Select "+locType+"</option>";
  				for( var i=0;i<response.length;i++){
  					locCode+="<option  value='"+response[i][1].trim()+"'>"+response[i][0]+"</option>";
  				}
  				locCode+="</select><span></span>";
  				
  				$("#locationData").html(locCode);
  				$('#locCode').val('5121');
  				$('#locCode').select2();
  	    	}
  	  }); 
    }
  	
  	/* function showViewButton()
  	{
  		 $('#gpsViewButton').show();
  	} */
  	
 	</script>

<div class="page-content">

	<div class="portlet box blue">
	 
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Energy Losses  </div>   
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
										
										<td style="display: none;">
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
		<div id="imageee" style="display: none;"  style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait. 
						 </h3> 
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:3%;height: 3%;">
						</div>
		<div class="row">				
			
					<!-- BEGIN MARKERS PORTLET-->
					<div class="portlet box green" style="display: none;height: 100%; width: 100%" id="gmapsContent" >
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>GMAP</div>
							<div class="tools">								
								<a href="#" class="close" onclick="hideMap()"></a>
							</div>
						</div>
				<div class="portlet-body" >
               <img alt="image" src="./resources/bsmart.lib.js/energy_Losses.PNG" style="width:70%;height: 70%;" align="centre">
               </div>
               </div>
              
               </div>
	
</div>




