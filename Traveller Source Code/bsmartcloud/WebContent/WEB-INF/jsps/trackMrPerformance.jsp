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
  		    loadSearchAndFilter('sample_3'); 
  		  $('#MIS-Reports-photoBilling').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		    	$('#gpsViewButton').hide();
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
							<div class="caption"><i class="fa fa-reorder"></i>Tracking MR Performance </div>   
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						
						<div class="portlet-body">
					
						 <div class="col-md-12">
										<form method="post" action="./showMrPerformance">
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
										      <div class="input-icon" >
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" id="fromDate" readonly="true" />
																
														</div>
													</td>
														  <td><div><button class="btn blue pull-left" id="gpsViewButton" >view</button></div></td>
										</tr>
								
										</table>
										</form>
									
						</div>
						</div>
		</div>
		 <div class="row">
		<div class="col-md-12" id="sectionDiv" >
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>MR Performance Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>SlNo.</th>
										<th>MRCODE</th>
										<th>DEVICE UTILIZATION</th>
										<th>BILLING EFFECIENCY</th>
										<th>AVERAGE BILLS PER DAY</th>
										<th>DL</th>
										<th>MNR</th>
										<th>NORMAL</th>
										<th>OTHER CASES</th>
										
									</tr>
								</thead>
								<tbody id="sectionTb">
									
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
		
		</div>
	
</div>




