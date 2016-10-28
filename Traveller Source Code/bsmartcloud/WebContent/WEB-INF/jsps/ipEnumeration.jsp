<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Date"%>

<script src="http://maps.google.com/maps/api/js?sensor=true"  type="text/javascript" ></script>
<script src="./resources/gmaps.js"  type="text/javascript" ></script>

  <script src="http://malsup.github.com/jquery.form.js"></script>

<style>
  	 #bcitsBrk{
  	 word-wrap: break-word;
  	 min-width:100px;
  	 white-space:normal;
  	 }
  	</style>
<script type="text/javascript">
var sdoCode1='';
  var feederCode1='';
var tcCode1='';

var ssesdoCode1='';
var ssefeederCode1='';
var ssetcCode1='';
  	$(".page-content").ready(function()
  		   {    	
  		    	App.init();
  		    	TableEditable.init(); 
  		    	//TableManaged.init();
  		      FormComponents.init();
  		    loadSearchAndFilter('table_3');
  		
  		  $('#checkEnum span:first-child').removeClass("checked");
  		    if('${ipEnumerationList.size()}'>'0')
  		    	
  		    	{
  		    	
  		    	$('#ipenumerationtabId').show();
  		    	$('#toDate1').val('${toDate}');
  				$('#fromDate1').val('${fromDate}');
  				$('#Auothorization1').val('${authorised}');
  				$('#tcCode1').val('${tcCode}');
  				$('#siteocde1').val('${sdoCode}');
  				$('#feederocde1').val('${feederCode}');
  		    	}
  		  /* if('${authorised}' == 'N')
  			  {
  			  $("#ipSetVal").html('UIPSETCODE');
  			  } */
  		  $('#Ip_Cesc,#ipEnumerationData').addClass('start active ,selected');
  		    	$("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#MIS-Reports-photoBilling,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		    	
  		    	
  		    	$('#addData').click(function()
	    	             { 
  		    		
  		    		
  		    	 // $('#s2id_rrnoVal').show();
					// $('#rrnoVal').attr('class','form-control selectpicker select2me input-medium');
  		    	rrEditVal='';
  		    	$('#frontFormTR').show();
  		  		$('#rightFormTR').show();
  		  		$('#leftFormTR').show();
  		    	$('#photoDiv').hide();
  		    	$('#authorised').val('select');
  		    	$('#name').val('');
  		    	$('#address1').val('');
  		    	$('#address2').val('');
  		    	$('#village').val('');
  		    	$('#meterfixed').val('select');
  		    	$('#watersource').val('select');
  		    	$('#inststatus').val('select');
  		    	$('#ryvolts').val('');
  		    	$('#ybvolts').val('');
  		    	$('#brvolts').val('');
  		    	$('#ramps').val('');
  		    	$('#yamps').val('');
  		    	$('#bamps').val('');
  		    	$('#longitude').val('');
  		    	$('#latitude').val('');
  		    	$('#trackpoint').val('');
  		    	$('#eastId').hide();
	      		$('#northId').hide();
	      		$('#trackPointId').hide();
  		    	//$('#dtcCodeDiv1').hide();
  		    	$('#settingSessionData').show();
  		    	document.getElementById('ipEnumeration').reset();
  		    	$('#esnumshno').val('');
  		    	$('#formSitecode').hide();
  		    	
  		    	$('#formRrnoDiv').hide();
 				$('#ucodeipsetIp').hide();
  		    	if('${sessSitecode}'!='select')
  	    		{
  	    		 $('#sdoCode1').val('${sessSitecode}');
  	    		  fetchFeederCode('${sessSitecode}');
  	    		  getSectionValuesSesstio('${sessSitecode}');
  	    		}
  		    	else
  		    		{
  		    		  $('#sdoCode1').val('select');
  		    		 $('#dtccode1').val('select');
  		    		 $('#feederCode1').val('select');
  		    		// $('#formRrnoDiv').hide();
  		    		}
  		    	$('#sessionBtId').show();
  		    	document.getElementById('onHoldBtId').style.display='none';
				   document.getElementById('modifyBtId').style.display='none';
				    document.getElementById('ApproveBtId').style.display='none';
					 document.getElementById('modifyApproveBtId').style.display='none';
				   document.getElementById('addBtId').style.display='block';
	    	              });
	    	     
	    	     $("#print").click(function(){ 
	    	    	 printcontent($(".table-scrollable").html());
				});

  		   
  		   });
  	
  	function validateSearchFilter()
  	{
  		if($('#sdoCode').val().trim()=='')
		 {
		   bootbox.alert('Please select the Sdocode.');
		   return false;
		 } 
  		if($('#feederCode').val().trim()=='')
		 {
		   bootbox.alert('Please select the Feedercode.');
		   return false;
		 } 
  		if($('#Auothorization').val().trim()=='')
		 {
		   bootbox.alert('Please select the Authorization.');
		   return false;
		 } 
  	}
  	
  	function getFeederData(sdoCode)
  	{
  		  
  		  if(sdoCode == 0){
  			  $("#mrCode").attr("disabled","true");
  			  
  		  }else{
  			
  			 var feederCode = "";
 			  var i=0;
  			  $.ajax({
  				  
  				  type:"POST",
  				  url:"./getFeederData",
  				  data:{"sdoCode":sdoCode},
  				  dataType:"JSON",
  				  success:function(response)
  				  {
  					feederCode+="<label class=control-label>&nbsp;</label><select id='feederCode' name='feederCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='getTcData(this.value)' value=''><option value='' selected='selected' >Select Feeder Code</option>";
						for( i=0;i<response.length;i++){
							feederCode+="<option id='feederCodeOpt' value='"+response[i].trim()+"'>"+response[i]+"</option>";
						}
						feederCode+="</select><span></span>";
						
						$("#feederCodeDiv").html(feederCode);
						$('#feederCode').select2();
  				  }
  			  });

  				  }
  		  
  		    }
  	
  	function getTcData(feederCode)
  	{
  		 
  			  var tcCode = "";
 			  var i=0;
  			  $.ajax({
  				  type:"POST",
  				  url:"./getTcData",
  				  data:{"feederCode":feederCode},
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
  	
  	 function showApproved(check)
	 {
		  if($('#sdoCodeS').val().trim()=='')
			 {
			   bootbox.alert('Please select SDO Code');
			   return false;
			 }
		$("#showAllAprovedId").attr("action", "./showApprovedIPEnumeration?status="+check);
		$("#showAllAprovedId").submit();
		

		
	 }
  	 
  	 function changeStatus(type){
  		 if(type==3){
  			$('#ApprovedId').show(); 
  			$('#HoldId').hide();
  		 }else{
  			$('#HoldId').show(); 
  			$('#ApprovedId').hide();
  		 }
  	 }
  	 
  	function getFeederDataSearch(sdoCode)
  	{
  		  if(sdoCode == 0){
  			  $("#mrCode").attr("disabled","true");
  		  }else{
  			 var feederCode = "";
 			  var i=0;
  			  $.ajax({
  				  type:"POST",
  				  url:"./getFeederData",
  				  data:{"sdoCode":sdoCode},
  				  dataType:"JSON",
  				  success:function(response)
  				  {
  					feederCode+="<select id='feederCode' name='feederCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='getTcData(this.value)' value=''><option id='feederCodeOpt' vlaue=''>All</option>";
						for( i=0;i<response.length;i++){
							feederCode+="<option id='feederCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						feederCode+="</select><span></span>";
						$("#feederCodeDiv").html(feederCode);
						$("#feederCodeDivA").html(feederCode);
						$("#feederCodeSession").html(feederCode);
						$('#feederCode').select2();
  				  }
  			  });
  			}
  	}
  	 
  	 
  	var dtcCodeVal='';
  	var  feederNoVal='';
  	var rrnoVal1='';
  	var rrEditVal='';
  	function viewMoreMap(param,id)
		{
  		
  		$.ajax({
			  
			  type:"POST",
			  url:"./editIpEnumerationData",
			  data:{"id":id},
			  dataType:"JSON",
			  success:function(response)
			  {
				
				  var map='';
						 
							if(response.latitude.trim()!=null || response.longitude.trim()!=null)
		    				  {
								map = new GMaps({
						            div: '#map-canvas',
						            lat: parseFloat(response.latitude),
						            lng :parseFloat(response.longitude),
						            zoom:10,
						        });	
		    				  }
							 
						 
						
						var labelName='ip';
						
							if(response.authorised=='N')
								{
			    		    	 map.addMarker({
							            lat:response.latitude,
							            lng:response.longitude,
							            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|FF7A59|000000',
							             infoWindow: {
							                content: "<div style=width:290px;>"
							                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
							                         +"<tr><th>Longitude </th><td>"+response.longitude+"</td></tr>"
							                         +"<tr><th>Lattitude</th><td>"+response.latitude+"</td></tr>"
													 +"<tr><th>Location</th><td>"+response.village+"</td></tr>"
													 +"<tr><th>RR Number</th><td>"+response.rrno+"</td></tr>"
													 +"<tr><th>Customer Name</th><td>"+response.name+"</td></tr>"
													 +"<tr><th>Address</th><td>"+response.address1+"</td></tr>"
													 +"<tr><th>Date Of Service</th><td>"+response.dos+"</td></tr>"
													 +"<tr><th>Sanction Load</th><td>"+response.sanctionload+"</td></tr>"
													 +"<tr><th>DTC </th><td>"+response.dtccode+"</td></tr>"
							                         +"</table>"
							                         +"</div>"
							            } 
							        });
								}
							else
								{
								
			    		    	 map.addMarker({
							            lat:response.latitude,
							            lng:response.longitude,
							            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|00CC99|000000',
							             infoWindow: {
							                content: "<div style=width:290px;>"
							                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
							                         +"<tr><th>Longitude </th><td>"+response.longitude+"</td></tr>"
							                         +"<tr><th>Lattitude</th><td>"+response.latitude+"</td></tr>"
													 +"<tr><th>Location</th><td>"+response.village+"</td></tr>"
													 +"<tr><th>RR Number</th><td>"+response.rrno+"</td></tr>"
													 +"<tr><th>Customer Name</th><td>"+response.name+"</td></tr>"
													 +"<tr><th>Address</th><td>"+response.address1+"</td></tr>"
													 +"<tr><th>Date Of Service</th><td>"+response.dos+"</td></tr>"
													 +"<tr><th>Sanction Load</th><td>"+response.sanctionload+"</td></tr>"
													 +"<tr><th>DTC </th><td>"+response.dtccode+"</td></tr>"
							                         +"</table>"
							                         +"</div>"
							            } 
							        });
								
								}
							
			    			  
						
					
				  
			  }
			  
  		});
  		$('#ipenumerationtabId').hide();
  		$('#map-canvas').show();
  		$('#buttonshow').show();
  		//$('#'+param).attr("data-toggle", "modal");//edit button
 		//$('#'+param).attr("data-target","#map12"); //edit button
  		
		}
  	
  	function validateSearchFilterChange() {
		
  		$('#ipenumerationtabId').show();
  		$('#map-canvas').hide();
  		$('#buttonshow').hide();
	}
  	
function closePopDiv() {
	
	 $('#capturemode').attr('disabled',false);
	  $('#longitudeId').show();
	  $('#latitudeId').show();
	 $('#rrnoVal').removeClass('form-control');
}
  	
  		function viewMore(param,id)
  		{
  			//alert("in Edit");
  			$('#photoDiv').show();
  			$('#sessionBtId').hide();
  			$('#frontFormTR').hide();
  			$('#rightFormTR').hide();
  			$('#leftFormTR').hide();
  			 /* $('#Coconut').prop('checked',false);
  			$('#Ragi').prop('checked',false);
  			$('#Jowar').prop('checked',false);
  		  $('#Paddy').prop('checked',false);
  		  $('#Areca').prop('checked',false);
  		  $('#HorticulturalNurseries').prop('checked',false);
  		  $('#Floriculture').prop('checked',false);
  		  $('#bricksmanufacturing').prop('checked',false); */
  			$.ajax({
				  
				  type:"POST",
				  url:"./editIpEnumerationData",
				  data:{"id":id},
				  dataType:"JSON",
				  success:function(response)
				  {
					  
					  feederNoVal=response.feederno;
					  dtcCodeVal=response.dtccode;
					  $('#formSitecode').hide();
					  $('#id').val(response.id);
					  feederCode1=response.feederno;
                      tcCode1=response.dtccode;
					  sdoCode1=response.siteCode;
					  auth=response.authorised;
					  $('#sdoCode1').val(sdoCode1);
					  getSectionValuesEdit(sdoCode1);
					  $('#authorised').val(auth);
					  $('#rrnoVal').val(response.rrno);
					  rrEditVal=response.ucodeipset;
					  $('#village').val(response.village);
					
					 // rrnoVal1=response.rrno; 
					 
					  if(auth=='Y')
						  {
						    rrnoVal1=response.rrno;
						     $('#ucodeipsetIp').hide();
						  }
					  else
						  {
						 
						  rrnoVal1=response.ucodeipset;
						  
						  }
					  $('#s2id_rrnoVal').hide();
					 $('#rrnoVal').attr('class','form-control');
					  showRRnoSelect('empty');
					  $('#doiVal').val(dateFormating(response.doi));
					  $('#esnumshno').val(response.esnumshno);
					  $('#name').val(response.name);
					  $('#address1').val(response.address1);
					  $('#address2').val(response.address2);
					  $('#sanctionload').val(response.sanctionload);
					  $('#meterfixed').val(response.meterfixed);
					  $('#watersource').val(response.watersource);
					  $('#inststatus').val(response.inststatus);
					 
					 /*  var crop=(response.crop).split(',');
					     for( var i=0;i<crop.length;i++)
						{ 
					    	
					    	 $('#uniform-'+crop[i]).addClass('checker focus');
					    	 $('#uniform-'+crop[i]).find('span').addClass('checked');
							 //$('#'+crop[i]).attr('checked',true);

						}  */
					  
					  $('#cropother').val(response.cropother);
					  $('#ryvolts').val(response.ryvolts);
					  $('#ybvolts').val(response.ybvolts);
					  $('#brvolts').val(response.brvolts);
					  $('#ramps').val(response.ramps);
					  $('#yamps').val(response.yamps);
					  $('#bamps').val(response.bamps);
					  $('#capturemode').attr('disabled',true);
					  if(response.capturemode=='Phone'||response.capturemode=='Fxdevice')
						 {
						  $('#northId').hide();
						  $('#eastId').hide();
						  $('#longitudeId').show();
						  $('#latitudeId').show();
						  $('#trackPointId').hide();
						  $('#capturemode').val(response.capturemode);
						  $('#longitude').val(response.longitude);
						  $('#latitude').val(response.latitude);
						 }
					  if(response.capturemode=='etrex10')
						 { 
						  $('#northId').show();
						  $('#eastId').show();
						  $('#longitudeId').hide();
						  $('#latitudeId').hide();
						  $('#capturemode').val(response.capturemode);
						  $('#north').val(response.north);
						  $('#east').val(response.east);
						  $('#trackpoint').val(response.trackpoint);
						  
						 }
					  
					  $('#connectedload').val(response.connectedload);
						$('#emieNumber').val(response.emieNumber);
						$('#apkVersion').val(response.apkVersion);
						$('#syncStatus').val(response.syncStatus);
						$('#devictoserverdt').val(dateTimeFormating(response.devictoserverdt));
						
						$("#frontImageView").html("<img data-magnifyby='10' class='magnify' src='./getFrontPicIp/"+response.id+"' onclick=viewImageDoc('./getFrontPicIp/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
						$("#leftImageView").html("<img data-magnifyby='10' class='magnify' src='./getLeftPicIp/"+response.id+"' onclick=viewImageDoc('./getLeftPicIp/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
						$("#rightImageView").html("<img data-magnifyby='10' class='magnify' src='./getRightPicIp/"+response.id+"' onclick=viewImageDoc('./getRightPicIp/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
					  
				  }
			  });
			 document.getElementById('addBtId').style.display='none';
			 document.getElementById('onHoldBtId').style.display='block';
			   document.getElementById('modifyBtId').style.display='block';
			    document.getElementById('ApproveBtId').style.display='block';
				 document.getElementById('modifyApproveBtId').style.display='block';
  			$('#'+param).attr("data-toggle", "modal");//edit button
     		$('#'+param).attr("data-target","#stack3"); //edit button
     		
  		}
  		
  	 	function viewImageDoc(path)
  	  	{  
  	  	    $('#Imageview').empty();  	    
  	  	    rotation=0;
  	  	    rotateRight();
  	  	    rotateLeft();
  	  	    $('#Imageview').append("<img id=\"tempImg\" style=\"width:500px;height:500px;\" src='"+path+"'/>");
  	  	    	
  	  	}
  		
  		function getDtcCodeByFCodeNSCode(sitecode,feedercode)
  		{
  			$.ajax({
				  
				  type:"POST",
				  url:"./getAllDtcCode",
				  data:{"sitecode":sitecode,"feedercode":feedercode},
				  dataType:"JSON",
				  success:function(response)
				  {
					  var html2 = '<td>UNIQUE DTC CODE</td><td></td><td><select name=udtccode id=udtccode class=form-control   onchange=displayDtcData(this.value)><option value=all>select</option>';
	    	    	   for(var j=0;j<response.length;j++)
			    		{		
			    		     var idName=response[j];
			    		     html2 += "<option value="+idName+" >"+idName+"</option>";
			    		}
			    	html2+='</select></td>';	
			    	$('#udtcodeTr').html(html2);	
			    	$('#udtccode').val(dtcCodeVal);
			    	//displayDtcData($('#udtccode').val());
				  }
  			}
  			);
  		}
  	
  		
  		function displayDtcData(dtccode)
  		{
  			$.ajax({
				  
				  type:"POST",
				  url:"./getDtcCodeDetails",
				  data:{"dtccode":dtccode},
				  dataType:"JSON",
				  success:function(response)
				  {
					  for(var j=0;j<response.length;j++)
			    		{
						  $('#capacity').val(response[j].capacity);
						  $('#tims').val(response[j].tims);
						  $('#loaction').val(response[j].loaction);
			    		}
					
				  }
			}
			);
  		}
  		/* function editSynStatus(param,taskStatus) 
  		{
  	     	 alert("param -- "+param + "taskStatus -- "+taskStatus); 
  	    	 $('#'+param).attr("data-toggle", "modal");
  			 $('#'+param).attr("data-target","#stack4");
  		} */
  		
  		function editSynStatus(id,ipCode,syncStatus)
		{	
			/* bootbox.confirm("Do you want to approve selected IP set", function(result) {
		        if(result == true)
		        	{  */
		        	$.ajax({
						  
						  type:"POST",
						  url:"./approveIpSet",
						  data:{"id":id,"syncStatus":syncStatus},
						  dataType:"text",
						  success:function(response)
		  						{
							      if(syncStatus=='1')
							    	  {
								    	  if(response==id)
										  {
								    		  var oTable = $('#table_3').dataTable();
											  oTable.fnDeleteRow(document.getElementById('row'+response));
										   $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSet '+ipCode+' Approved Successfully.</span></div>');
										  }
										  else
										  {
										   $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSet '+ipCode+' not Approved Successfully.</span></div>');
										  }
							    	  }
							      if(syncStatus=='3')
						    	  {
							    	  if(response==id)
									  {
							    		  $('#onHoldId'+response).remove();
							    		  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSet '+ipCode+' is set to onhold Successfully.</span></div>');
									  }
									  else
									  {
										  ('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSet '+ipCode+' is set to onhold not Approved Successfully.</span></div>');
									  }
						    	  }
									  
		  						}
						  
						 
      	
    });
		       /*  	}
	          }); */
	          return false;
		}
  		
  		
  		/* function approveSelectedIp()
  		{
  			/* var checkboxes = document.getElementsByName('ipEnumeration');
  			var availableIpId = [];
  			var checks = "";
  			for(var i =0;i<checkboxes.length;i++)
  			{
  				if(checkboxes[i].checked)
  				 {
  					checks = checks+checkboxes[i].value+",";
  					availableIpId.push(checkboxes[i].value);
  				 }
  				else
  				{
  					 for(var i = availableIpId.length; i--;) 
  					  {
  						 alert(checkboxes[i].value);
  				          if(availableIpId[i] === checkboxes[i].value) 
  				          {
  				        	  availableIpId.splice(i, 1);
  				          }
  				      }
  				}
  				}
  			if(checks == "")
  				{
  					bootbox.alert("Please Select Atleast one checkbox");
  					return false;
  				}
  			checks = checks.substring(0,checks.length-1);
  			  $.ajax({
  					  type:"POST",
  					  url:"./approveSelected",
  					dataType: "text",
					data: json1,
					contentType: 'application/json',
					cache:false,
					async:false,
					success:function(response)
  					  {
  						  
  						  for(var i=0;i<response.length;i++)
  							{ 
  							  alert(response[i])
  							  $('#row'+response[i]).remove();
  							}    	 
  						  alert("Selected records approved successfully"); 
  					  }
  				  });
  			
  			return false; 
  			var checkboxes = document.getElementsByName('ipEnumeration');
  			if( $('#' + id).is(":checked")==true)
				{
				   availableEmpId.push(empId); 
				}
			else if( $('#' + id).is(":checked")==false)
				{
					  for(var i = availableEmpId.length; i--;) 
					  {
				          if(availableEmpId[i] === empId) 
				          {
				        	  availableEmpId.splice(i, 1);
				          }
				      }
				}
  		} */
  		var availableIpId=[];
  		function getAllIpId(id,ipId)
  		{
  			if( $('#' + id).is(":checked")==true)
			{
			   availableIpId.push(ipId); 
			}
		else if( $('#' + id).is(":checked")==false)
			{
				  for(var i = availableIpId.length; i--;) 
				  {
			          if(availableIpId[i] === ipId) 
			          {
			        	  availableIpId.splice(i, 1);
			          }
			      }
			}
  		}
  		function approveSelectedIp()
  		{
  			if(availableIpId.length==0)
  				{
  				  bootbox.alert('Please select the IP SETS to approve.');
			      return false;
  				}
  			var json1 = JSON.stringify(availableIpId);
  			$.ajax({
				  type:"POST",
				  url:"./approveSelected",
				data: json1,
				contentType: 'application/json',
				cache:false,
				async:false,
				success:function(response)
				  {
					availableIpId.length=0;
					var checkAll=document.getElementsByName('checkme');
					var val=checkAll[0].checked;
					if(val==true)
						{
						$('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSets Approved Successfully.</span></div>');
						$('#checkEnum span:first-child').removeClass("checked");
						 var oTable = $('#table_3').dataTable();
						  for(var i=0;i<=response.length;i++)
							{ 
							  oTable.fnDeleteRow(document.getElementById('row'+response[i]));
							} 
						}
					else if(val==false)
						{
						   var ipSet=$('#check'+response).val();
						  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSet '+ipSet+' Approved Successfully.</span></div>');
						  var oTable = $('#table_3').dataTable();
						  oTable.fnDeleteRow(document.getElementById('row'+response));
						}
					  
				  }
			  });
  		}
  		
  		function onHoldSelectedIp()
  		{
  			var abc=[];
  			for(var i=0;i<availableIpId.length;i++)
 			 {
					if($('#onHoldId'+availableIpId[i]).length==0)
					{
						abc.push(i+1); 
					}
 			}
  			if(availableIpId.length==0)
			{
			  bootbox.alert('Please select the IP SETS to onHold.');
	          return false;
			}
  			if(abc.length==availableIpId.length)
  				{
  				bootbox.alert('Already all ip sets are set to onhold.');
			       return false;
  				}
  			
  			var json1 = JSON.stringify(availableIpId);
  			$.ajax({
				  type:"POST",
				  url:"./onHoldSelectedIp",
				data: json1,
				contentType: 'application/json',
				cache:false,
				async:false,
				success:function(response)
				  {
					 availableIpId.length=0;
					  for(var i=0;i<=response.length;i++)
						{ 
						  $('#onHoldId1'+response[i]).text("");
						}  
					  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >IPSets set to onHold Successfully.</span></div>');
					  $('#tcEnum span:first-child').removeClass("checked");
					  $('#checkEnum span:first-child').removeClass("checked");
				  }
			  });
  		}
  		
  		
  		function validations(value)
  		{
  			
  			var id=$('#id').val();
  			var rrno=$('#rrnoVal').val();
  			var uipcode=$('#ucodeipset').val();
  			if(value=='approve')
  				{
	  				if(rrno!='')
	  				{
	  				    editSynStatus(id,rrno,'1');
	  				}
	  				else if(uipcode!='')
	  				{
	  					editSynStatus(id,uIpCode,'1');
	  				}
	  				$('#closeBtId').click();
  				}
  			if(value=='onhold')
  				{
	  				if(rrno!='')
	  				{
	  				    editSynStatus(id,rrno,'3');
	  				}
	  				else if(uipcode!='')
	  				{
	  					editSynStatus(id,uipcode,'3');
	  				}
	  				$('#closeBtId').click();
  				}
  			if(value=='modify')
				{
  				  $("#ipEnumeration").attr("action","./updateIpEnumertion?status=modify");
  				 return true;
				}
  			if(value=='modifyApprove')
			{
				  $("#ipEnumeration").attr("action","./updateIpEnumertion?status=modifyApprove");
				  return true;
			}
  			if(value=='add')
			{
  			      var latLangPattern = /^[0-9]{2}[.][0-9]{2,6}$/;
  			    var optinalNumericPatter = /^[0-9.]*$/;
  			  var norEastPattern = /^[0-9]{2}[.][0-9]{2}[.][0-9]{2}[.][0-9]{2}$/;
  			  
  			 if($('#sdoCode1').val().trim()=='select')
			 {
			   bootbox.alert('Please select Sub-Divistion.');
			   return false;
			 }
  			 
  			 if($('#feederCode1').val().trim()=='select')
			 {
			   bootbox.alert('Please select FeederCode.');
			   return false;
			 }
  			 
  			 if($('#dtccode1').val().trim()=='select')
			 {
			   bootbox.alert('Please select Dtccode.');
			   return false;
			 }
  			  
  			    if($('#authorised').val().trim()=='select')
  				 {
  				   bootbox.alert('Please Select Authorization.');
  				   return false;
  				 }
  			    if($('#authorised').val().trim()=='Y')
  			    	{
  			    	if($('#rrnoVal').val().trim()=='select')
  					 {
  					   bootbox.alert('Please select RRNO.');
  					   return false;
  					 }
  			    	}
  			   
  			   
  	  			 if($('#doiVal').val().trim()=='')
  				 {
  				   bootbox.alert('Please select Date of inspection.');
  				   return false;
  				 }
  			    
  			    
  			 if($('#esnumshno').val().trim()=='')
			 {
			   bootbox.alert('Please enter sheet no.');
			   return false;
			 }
  			 if(!$('#esnumshno').val().trim().match(optinalNumericPatter))
	   			{
	   			bootbox.alert("Please enter sheet no. in numeric.");
	   			return false;
	   			}
  		    
  			 if($('#name').val().trim()=='')
			 {
			   bootbox.alert('Please enter name .');
			   return false;
			 }
  			 
  			 
  		    
  			 if($('#address1').val().trim()=='')
			 {
			   bootbox.alert('Please enter Address 1. ');
			   return false;
			 }
  			
  			if($('#meterfixed').val().trim()=='select')
			 {
			   bootbox.alert('Please Select MeterFixed.');
			   return false;
			 } 
  			if($('#watersource').val().trim()=='select')
			 {
			   bootbox.alert('Please Select watersource.');
			   return false;
			 }
  			if($('#watersource').val().trim()=='Others')
			 {
  				if($('#watersourceother').val().trim()=='')
  				 {
  				   bootbox.alert('Please Enter watersourceother.');
  				   return false;
  				 }
			 }
  			if($("input[name=crop]").is(":checked")==false)
			 {
			   bootbox.alert('Please Select any crop name.');
			   return false;
			 }
  			
  			if($('#inststatus').val().trim()=='select')
			 {
			   bootbox.alert('Please Select Installation status.');
			   return false;
			 }
  			
  			if(!$('#sanctionload').val().trim().match(optinalNumericPatter)){
  				 bootbox.alert('Sanctionload Should be in Numeric');
  			   return false;
  			  }
	    		 
  			if(!$('#ryvolts').val().trim().match(optinalNumericPatter)){
 				 bootbox.alert('Ryvolts Should be in Numeric');
 			   return false;
 			  }
  			if(!$('#ybvolts').val().trim().match(optinalNumericPatter)){
 				 bootbox.alert('Ybvolts Should be in Numeric');
 			   return false;
 			  }
  			if(!$('#brvolts').val().trim().match(optinalNumericPatter)){
 				 bootbox.alert('Brvolts Should be in Numeric');
 			   return false;
 			  }
  			
  			
  			if(!$('#ramps').val().trim().match(optinalNumericPatter)){
				 bootbox.alert('Ramps Should be in Numeric');
			   return false;
			  }
  			if(!$('#yamps').val().trim().match(optinalNumericPatter)){
				 bootbox.alert('Yamps Should be in Numeric');
			   return false;
			  }
  			if(!$('#bamps').val().trim().match(optinalNumericPatter)){
				 bootbox.alert('Bamps Should be in Numeric');
			   return false;
			  }
  			if($('#ryvolts').val().trim()=='')
  				{
  				$('#ryvolts').val('0');
  				
  				}
  			if($('#ybvolts').val().trim()=='')
				{
				$('#ybvolts').val('0');
				
				}
  			if($('#brvolts').val().trim()=='')
				{
				$('#brvolts').val('0');
				
				}
  			if($('#ramps').val().trim()=='')
			{
			$('#ramps').val('0');
			
			}
  			if($('#yamps').val().trim()=='')
			{
			$('#yamps').val('0');
			
			}
  			if($('#bamps').val().trim()=='')
			{
			$('#bamps').val('0');
			
			}
			
  			
  			 if($('#capturemode').val().trim()=='etrex10')
		    	{
		  				if($('#north').val().trim()=='')
						 {
						   bootbox.alert('Please enter north');
						   return false;
						 }
					   
					   if(!$('#north').val().trim().match(norEastPattern))
						 {
						   bootbox.alert('Northing format should be xx.xx.xx.xx in numbers');
						   return false;
						 }
					  
					   if(!$('#east').val().trim().match(norEastPattern))
						 {
						   bootbox.alert('Please enter east');
						   return false;
						 }
					   
					   if($('#east').val().trim()=='')
						 {
						   bootbox.alert('Easting format should be xx.xx.xx.xx in numbers');
						   return false;
						 }
					   if($('#trackpoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   if(!$('#trackpoint').val().trim().match(optinalNumericPatter))
			   			{
			   			bootbox.alert("Please enter track point in numeric.");
			   			return false;
			   			}
		    	}
  			 else
  				 {
		  				if($('#longitude').val().trim()=='')
						 {
						   bootbox.alert('Please enter longitude');
						   return false;
						 }
		  				if(!$('#longitude').val().trim().match(latLangPattern))
			   			{
			   			bootbox.alert("Longitude format should be xx.xxxxxx in numbers");
			   			return false;
			   			}
		    		 if($('#latitude').val().trim()=='')
					 {
					   bootbox.alert('Please enter latitude');
					   return false;
					 }
	    		 if(!$('#latitude').val().trim().match(latLangPattern))
		   			{
		   			bootbox.alert("Latitude format should be xx.xxxxxx in numbers");
		   			return false;
		   			}
  				 
  				 }
  			
				  $("#ipEnumeration").attr("action","./addIpEnumertion");
				  return true;
			}
  			if(value=='set')
			{
  				   if($('#sdoCode1').val().trim()=='select')
  					   {
  					        bootbox.alert("Please Select sitecode");
  		   			        return false;
  					   }
  				 if($('#feederCode1').val().trim()=='select')
				   {
				        bootbox.alert("Please Select feederCode");
	   			        return false;
				   }
  				 if($('#dtccode1').val().trim()=='select')
				   {
				        bootbox.alert("Please Select dtccode");
	   			        return false;
				   }
  				
  				
				    var sitecode=$('#sdoCode1').val();
				    var dtccode=$('#dtccode1').val();
				 
				    var feedercode=$('#feederCode1').val();
				    $.ajax({
						  
						  type:"POST",
						  url:"./setIPSessionData",
						  cache:false,
						  async:false,
						  data:{"sitecode":sitecode,"dtccode":dtccode,"feedercode":feedercode},
						  dataType:"json",
						  success:function(response)
		  						{
								  $('#sdoCode1').val(response.sitecode);
								  $('#dtccode1').val(response.dtccode);
								  $('#feederCode1').val(response.feedercode);
								    //ssesdoCode1=response.sitecode;
									//ssefeederCode1=response.dtccode;
									//ssetcCode1=response.feedercode;
									showRRnoSelect(response.ipSetsList);
									
				  	  	             bootbox.alert('Details set successfully.');
								  
		  						}
						  
						 
    	
  });
				   return false;
				   
			}
  			return false;
  		}
  		
  		
  		function showRRnoSelect(rrnoList)
  		{
  			//$('#formRrnoDiv').show();
  			if(rrnoList=='empty')
  				{
  				    var s1="";
	  				var sitecode=$('#sdoCode1').val();
				    var dtccode='%';
				    var feedercode=$('#feederCode1').val();
				    $.ajax({
						  
						  type:"POST",
						  url:"./getIPListData",
						  cache:false,
						  async:false,
						  data:{"sitecode":sitecode,"dtccode":dtccode,"feedercode":feedercode},
						  dataType:"json",
						  success:function(rrnoList)
		  						{
							//  alert(rrnoList.length);
								$('#rrnoVal').empty();
				  	  			var rrno="";
				  	  		  rrno+='<option  value=select>Select</option>';
				  				  						for(var i=0;i<rrnoList.length;i++){
				  				  						s1=rrnoList[i];
				  				  							rrno+="<option  value='"+s1[0]+"'>"+s1[1]+" - "+s1[0]+"</option>";
				  				  						}
				  				  						rrno+="</select>";
				  				  						
				  				  						$("#rrnoVal").html(rrno);
				  				  			/* 		$('.select2-input select2-focused').attr('autocorrect','on');
				  				    				$('.select2-input select2-focused').attr('autocomplete','on');
				  				    				$('.select2-input select2-focused').attr('autocapitalize','off'); */
			  				    				
			  				  						//$("#rrnoVal").select2();
							 /*  var rrno="";
			  				  rrno+="<div class=col-md-2><label style=font-weight:600;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RRNO</label><select id='rrnoVal' name='rrnoVal' class=form-control type='text' onchange='getDataByRRno(this.value)' ><option  value='select'>Select</option>";
			  				  						for(var i=0;i<rrnoList.length;i++){
			  				  							rrno+="<option  value='"+rrnoList[i].rrno+"'>"+rrnoList[i].rrno+"</option>";
			  				  						}
			  				  						rrno+="</select><span></span></div>";
			  				  						
			  				  						$("#formRrnoDiv").html(rrno);
			  				  						//$("#rrnoVal").select2(); */
		  						}
						  
						 
  	
});
				    
				    if(rrnoVal1!='')
				    	{
				    	  displayByAuthorization($('#authorised').val());
				    	}
  				}
  			else
  				{
  			//alert("else "+rrnoList.length);
  				//alert(rrnoList+'2');
  				$('#rrnoVal').empty();
  	  			var rrno="";
  	  			var s="";
  	  		  rrno+='<option  value=select>Select</option>';
  	  		  
  				  						for(var i=0;i<rrnoList.length;i++){
  				  							s=rrnoList[i];
  				  							rrno+="<option  value='"+s[0]+"'>"+s[1]+" - "+s[0]+"</option>";
  				  						}
  				  						rrno+="</select>";
  				  						
  				  						$("#rrnoVal").html(rrno);
  				  					/* $('.select2-input select2-focused').attr('autocorrect','on');
  				    				$('.select2-input select2-focused').attr('autocomplete','on');
  				    				$('.select2-input select2-focused').attr('autocapitalize','off'); */
  				  						//$("#rrnoVal").select2();
  				
  				
  	  		/* 	var rrno="";
  				  rrno+="<div class=col-md-2><label style=font-weight:600;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RRNO</label><select id='rrnoVal' name='rrnoVal' class=form-control type='text' onchange='getDataByRRno(this.value)' ><option  value='select'>Select</option>";
  				  						for(var i=0;i<rrnoList.length;i++){
  				  							rrno+="<option  value='"+rrnoList[i].rrno+"'>"+rrnoList[i].rrno+"</option>";
  				  						}
  				  						rrno+="</select><span></span></div>";
  				  						
  				  						$("#formRrnoDiv").html(rrno);
  				  						
  				  						//$("#rrnoVal").select2(); */
  				}
  			
  		}
  		
  		
  		
  		function getDataByRRno(rrno)
  		{
  			var sitecode=$('#sdoCode1').val();
  			var dtccode=$('#dtccode1').val();
  			var feedercode= $('#feederCode1').val();
  			$.ajax({
				  
				  type:"POST",
				  url:"./getDataByRRno",
				  cache:false,
				  async:false,
				  data:{"rrno":rrno,"dtccode":dtccode,"feedercode":feedercode,"sitecode":sitecode},
				  dataType:"json",
				  success:function(response)
						{
					  if(response.length>0)
					  {
					  for(var i=0;i<response.length;i++)
			          {
		    		   if(response[i].name!='')
		    			   {
		    			     $('#name').val(response[i].name);
		    			   }
		    		   else
		    			   {
		    			     $('#name').val('');
		    			   }
						   
						    if(response[i].address!='')
		    			   {
		    			     $('#address1').val(response[i].address);
		    			   }
		    		   else
		    			   {
		    			     $('#address1').val('');
		    			   }
						   
						    if(response[i].address2!='')
		    			   {
		    			     $('#address2').val(response[i].address2);
		    			   }
		    		   else
		    			   {
		    			     $('#address2').val('');
		    			   }
						   
						   if(response[i].villagename!='')
		    			   {
		    			      $('#village').val(response[i].villagename);	
		    			   }
		    		   else
		    			   {
		    			    $('#village').val('');	
		    			   }
						   if(response[i].hp!='')
		    			   {
		    			     $('#sanctionload').val(response[i].hp);
		    			   }
		    		   else
		    			   {
		    			    $('#sanctionload').val('');	
		    			   }

			          }
					  }
				  else
					  {
						  $('#name').val('');
			        	  $('#address1').val('');
			        	  $('#address2').val('');
			        	  $('#village').val('');	
			        	  $('#sanctionload').val('');
					   
					  }
					          
						}
  			});
  		}
  		
  		var checkboxes=[];
  		function selectAll(source,size) 
  		{
 		   var flagChecked = 0;
 			checkboxes = document.getElementsByName('ipEnumeration');
 			for(var i =0;i<checkboxes.length;i++)
 				{
 				checkboxes[i].checked = source.checked;
 				if(checkboxes[i].checked)
 				 {
 					availableIpId.push(checkboxes[i].value);
 					flagChecked = 1;
 				 }
 				else
 					{
 					availableIpId.pop(checkboxes[i].value);
 					}
 				}
 			
 			 if(flagChecked==0)
 			{
 				$('#tcEnum span:first-child').removeClass("checked");
 			}
 			else{
 				$('#tcEnum span:first-child').addClass("checked");
 			} 
 		}
  		
 		function getSectionValuesEdit(sdocode)
  		{
 			//alert("only For Edit Optn");
  			$.ajax({
				  
				  type:"POST",
				  url:"./displaySection",
				  data:{"sdocode":sdocode},
				  dataType:"json",
				  success:function(response)
						{
					      for(var i=0;i<response.length;i++)
					    	  {
					    	    var omUnit=response[i].section;
					    	     $('#oMunit').val(omUnit);
					    	  }
						}

});
  			
  			
  			fetchFeederCode(sdocode);
  		
  		}
  		
 		
 		function getSectionValuesSesstio(sdocode)
  		{
  			$.ajax({
				  
				  type:"POST",
				  url:"./displaySection",
				  data:{"sdocode":sdocode},
				  dataType:"json",
				  success:function(response)
						{
					      for(var i=0;i<response.length;i++)
					    	  {
					    	    var omUnit=response[i].section;
					    	     $('#oMunit').val(omUnit);
					    	  }
						}

});
  			
  		}
 		
  		function getSectionValues(sdocode)
  		{
  			$.ajax({
				  
				  type:"POST",
				  url:"./displaySection",
				  data:{"sdocode":sdocode},
				  dataType:"json",
				  success:function(response)
						{
					      for(var i=0;i<response.length;i++)
					    	  {
					    	    var omUnit=response[i].section;
					    	     $('#oMunit').val(omUnit);
					    	  }
						}

});
  			
  			fetchFeederCode(sdocode);
  			
  		}
  		function fetchFeederCode(sdocode)
  		{
  			var feederCode = "";
			  var i=0;
			  $.ajax({
				  
				  type:"POST",
				  url:"./getFeederFromDTC",
				  data:{"sdoCode":sdocode},
				  dataType:"JSON",
				  success:function(response)
				  {
					feederCode+="<label style=font-weight:600;>&nbsp;&nbsp;&nbsp;&nbsp;Feeder</label><select id='feederCode1' name='feederCode1' class=form-control type='text'onchange='getDTCData(this.value)' ><option id='feederCodeOpt' value='select'>Select</option>";
						for( i=0;i<response.length;i++){
							feederCode+="<option id='feederCodeOpt' value='"+response[i].trim()+"'>"+response[i]+"</option>";
						}
						feederCode+="</select><span></span>";
						
						$("#feederCodeDiv1").html(feederCode);
						//$('#feederCode1').select2();
						if(feederNoVal!='')
							{
							  $('#feederCode1').val(feederNoVal.trim());
							  getDTCData(feederNoVal.trim());
							}
						if('${sessSitecode}'==$('#sdoCode1').val())
						{
							if('${sessFeedercode}'!='')
							{
							  $('#feederCode1').val('${sessFeedercode}');
							  getDTCData('${sessFeedercode}');
							// showRRnoSelect('empty');
							 // $('#dtcCodeDiv1').hide();
							}
						}
						else
							{
							 $('#dtccode1').val('select');
							}
						
				  }
			  });
			 
			  //return false;
  		}
  		function getDTCData(feederCode)
  		{
  			//alert("1");
  			var tcCode = "";
			  var i=0;
			  $.ajax({
				  
				  type:"POST",
				  url:"./getTcFromMaster",
				  data:{
					  "feederCode":feederCode,
					  "sitecode":$('#sdoCode1').val()
					  },
				  dataType:"JSON",
				  success:function(response)
				  {
					tcCode+="<label style=font-weight:600;>DTCCODE</label><select id='dtccode1' name='dtccode1' class=form-control type='text'><option id='tcCodeOpt' value='select'>Select</option>";
						for( i=0;i<response.length;i++){
							tcCode+="<option id='tcCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						tcCode+="</select><span></span>";
						$("#dtcCodeDiv1").html(tcCode);
						if('${sessSitecode}'==$('#sdoCode1').val())
						{
							
							if('${sessDtccode}'!='')
							{
							  $('#dtccode1').val('${sessDtccode}');
							}
							// showRRnoSelect('empty');
							
						}
						if(dtcCodeVal!='')
						{
						  $('#dtccode1').val(dtcCodeVal.trim());
						  //showRRnoSelect('empty');
						}
						
						//$('#dtccode1').select2();
				  }
			  });
  		}
  		
  		function displayWaterSourceOther(value)
  		{
  			if(value=='Others')
  				{
  				  $('#waterOtherDiv').show();
  				}
  			else
  				{
  				 $('#waterOtherDiv').hide();
  				}
  		}
  		function displayByAuthorization(value)
  		{
  			//alert("auth");
  			//alert("in display");
  			if(rrnoVal1!='')
			  {
  				if($('#feederCode1').val().trim()=='select')
 			   {
 			        bootbox.alert("Please set the data first.");
   			        return false;
 			   } 
			  }
  			
  			if(value=='Y')
  				{
  				//alert("In Auth");
  				  $('#formRrnoDiv').show();
  				  $('#ucodeipsetIp').hide();
  				  if(rrnoVal1!='')
  					  {
  					$('#rrnoVal').append($('<option>', { 
  				        value: rrnoVal1,
  				        text : rrnoVal1 
  				    }));  
  					   $('#rrnoVal').val(rrnoVal1.trim());
  					  }
  				}
  			else
  				{
  				if(rrEditVal=='')
  					{
  					//alert("edit 1");
  					//alert("if");
  				$.ajax({
  				  
  				  type:"POST",
  				  url:"./getUcodeipset",
  				  dataType:"text",
  				  success:function(response)
  				  {
  					  $('#ucodeipset').val(response);
  				  }
  			  });
  				 
				
  					}
  				else
  					{
  					//alert("edit 2");
  					//alert("else");
  					$('#ucodeipset').val(rrEditVal);
  					}
  				  
  				  $('#formRrnoDiv').hide();
				  $('#ucodeipsetIp').show();
  				}
  		}
  		
  		function captureMode()
  	    {
  			var capture=$('#capturemode').val().trim();
  	    	if(capture=='etrex10')
  	    	{
  	    		$('#latitudeId').hide();
  	    		$('#longitudeId').hide();
  	    		
  	    		$('#eastId').show();
  	    		$('#northId').show();
  	    		
  	    		$('#trackPointId').show();
  	    		
  	    	}else{
  	    		$('#latitudeId').show();
  	    		$('#longitudeId').show();
  	    		
  	    		$('#eastId').hide();
  	    		$('#northId').hide();
  	    		
  	    		$('#trackPointId').hide();
  	    	} 
  	    }
  		
  		function disableCheckFun() {
			if($("input[id=bricksmanufacturingNew]").is(":checked")==true)
				{
				//alert();
				$("#uniform-Ragi").addClass('checker disabled');
				$("#uniform-Jowar").addClass('checker disabled');
				$("#uniform-Paddy").addClass('checker disabled');
				$("#uniform-Sugarcane").addClass('checker disabled');
				$("#uniform-Areca").addClass('checker disabled');
				$("#uniform-Coconut").addClass('checker disabled');
				$("#uniform-HorticulturalNurseries").addClass('checker disabled');
				$("#uniform-Floriculture").addClass('checker disabled');
				$("#uniform-bricksmanufacturing").addClass('checker disabled');
				}
			else
				{
				$("#uniform-Ragi").attr("class", "checker");
				$("#uniform-Jowar").attr("class", "checker");
				$("#uniform-Paddy").attr("class", "checker");
				$("#uniform-Sugarcane").attr("class", "checker");
				$("#uniform-Areca").attr("class", "checker");
				$("#uniform-Coconut").attr("class", "checker");
				$("#uniform-HorticulturalNurseries").attr("class", "checker");
				$("#uniform-Floriculture").attr("class", "checker");
				$("#uniform-bricksmanufacturing").attr("class", "checker");
				
				}
  			
		}
  		
  		
  		function imagePopDiv(param,id) {
  	  		$('#rightFormEdit').val('');
  	  		$('#leftFormEdit').val('');
  	  		$('#frontFormEdit').val('');
  	  		$("#frontDIV").html("<img data-magnifyby='10' class='magnify' src='./getFrontPicIp/"+id+"' onclick=viewImageDoc('./getFrontPicIp/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
  			$("#leftDIV").html("<img data-magnifyby='10' class='magnify' src='./getLeftPicIp/"+id+"' onclick=viewImageDoc('./getLeftPicIp/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
  			$("#rightDIV").html("<img data-magnifyby='10' class='magnify' src='./getRightPicIp/"+id+"' onclick=viewImageDoc('./getRightPicIp/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
  		  
  	  		$('#hideSetImageVal').val(id);
  	  		$('#'+param).attr("data-toggle", "modal");//edit button
  	 		$('#'+param).attr("data-target","#basicNewOld"); //edit button
  		}
  		
  		
  		//using jquery.form.js
  		function uploadJqueryForm(){
  		   // $('#result').html('');
  		 
  		   $("#form2").ajaxForm({
  		    success:function(data) { 
  		    	bootbox.alert(data);
  		      
  		     },
  		     dataType:"text"
  		   }).submit();
  		   return true;
  		 
  		/*    $('#basicNewOld').attr('class','modal fade');
  		   $('#basicNewOld').attr('aria-hidden','true');
  		 $('#basicNewOld').attr('style','display:none;'); */
  		 
  		}
 	</script>

<div class="page-content">
<%-- 	<%@include file="pagebreadcrum.jsp"%>
 --%>	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
<c:if test = "${results ne 'notDisplay'}"> 			        
			         <div class="alert alert-danger display-show">
							<button class="close" data-close="alert"></button>
							<span style="color:red" id="result">${results}</span>
						</div>
			        </c:if>
			        <div  id="alertMsg" >
							
					</div>
			<!-- <div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler"></div>
				<div class="toggler-close"></div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>THEME COLOR</span>
						<ul>
							<li class="color-black current color-default"
								data-style="default"></li>
							<li class="color-blue" data-style="blue"></li>
							<li class="color-brown" data-style="brown"></li>
							<li class="color-purple" data-style="purple"></li>
							<li class="color-grey" data-style="grey"></li>
							<li class="color-white color-light" data-style="light"></li>
						</ul>
					</div>
					<div class="theme-option">
						<span>Layout</span> <select
							class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Header</span> <select
							class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Sidebar</span> <select
							class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Footer</span> <select
							class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div> -->


			<!-- <div class="row">
				<div class="col-md-12">
					BEGIN PAGE TITLE & BREADCRUMB
					<h3 class="page-title">
						Search Results <small>search results</small>
					</h3>
				</div>
			</div> -->
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom tabbable-full-width">
						
						<div >
							<div id="tab_2_2" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<div class="booking-search">
											<form action="./searchipenumeration" method="post">
												<div >
														<div class="col-md-3" style="width: 250px;"> 
															<label class="control-label">&nbsp;</label>
														 <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederData(this.value)" style="width: 10px;" >
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodes}">
																<option value="${element[0]}" id="sdoCodeOpt">${element[0]}-${element[1]}</option>
															</c:forEach>
														  </select>
														
														</div>
														<div  class="col-md-3" id="feederCodeDiv" style="width: 250px;">
																<label class="control-label">&nbsp;</label>
															<select id="feederCode" class="form-control selectpicker placeholder-no-fix input-medium" name="feederCode">
														    <option value="" selected="selected" >Select Feeder Code</option>
														  	</select><span></span>	
														</div>
														
														<div class="col-md-3" id="tcCodeDiv" style="width: 250px;">
																<label class="control-label">&nbsp;</label>
															<select id="tcCode" class="form-control selectpicker placeholder-no-fix input-medium" name="tcCode">
														    <option value='all' >Select DTC Code</option>
														  	</select><span></span>	
														</div>
														
														<div class="col-md-3"  style="width: 250px;">
																<label class="control-label">&nbsp;</label>
															<select id="Auothorization" class="form-control selectpicker placeholder-no-fix input-medium" name="Auothorization" st>
														    <option value='' >Select Authorization</option>
														    <option value='Y' >Authorized</option>
														    <option value='N' >UnAuthorized</option>
														    <option value='both' selected="selected" >Both</option>
														  	</select><span></span>	
														</div>
														
													</div>
													<!-- <div style="width: 250px;">
										<div class="radio-list">
											<label class="radio-inline">
											<input type="radio" name="Auothorization" id="Auothorized" value="Y" > Authorized
											</label>
											<label class="radio-inline">
											<input type="radio" name="Auothorization" id="UnAuothorized" value="N" > UnAuthorized
											</label>
										</div>
									</div> -->
													
													<div  >
													<div class="col-md-3" style="width: 250px;">
														<label class="control-label">From:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" />
																
														</div>
													</div>
													<div class="col-md-3" style="width: 250px;">
														<label class="control-label">To:</label>
														<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="toDate" />
														</div>
													</div>
													<div class="col-md-2" >
														<label class="control-label">&nbsp;</label>
														<button class="btn green pull-center" style="display: block;margin-left: 10px;" 
														id="" onclick="return validateSearchFilter();">Search&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i>
														</button>
													</div>
													
													<div class="col-md-1" >
														<label class="control-label">&nbsp;</label>
														<button class="btn green pull-center" style="display: block;" id="addData"  data-target="#stack3" data-toggle="modal">Add IP SET
														</button>
													</div>
													
													<div class="col-md-2">
														<label class="control-label">&nbsp;</label>
														<button class="btn blue" data-target="#stack5"  onclick="changeStatus(3)"  data-toggle="modal" style="display: block;">Show Approved</button>
													</div> 
													
													<div class="col-md-2" style="margin-left: 950px;margin-top: -61px;">
														<label class="control-label">&nbsp;</label>
														<button class="btn blue" data-target="#stack5" onclick="changeStatus(4)" data-toggle="modal" style="display: block;">Show OnHold</button>
													</div>
													
												</div>
												
												<!-- <button class="btn green btn-block margin-top-20">
													SEARCH <i class="m-icon-swapright m-icon-white"></i>
												</button> -->
												<!-- <button class="btn green pull-center" style="display: block;" 
														id="" >Search&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i>
														</button> -->
														
											</form>
											
										</div>
									</div>
								</div>
								</div>
							</div>
							
					</div>
				</div>
				<!--end tabbable-->
			</div>
			
			<div class="row" id="ipenumerationtabId" hidden="true">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>IP ENUMERATION
							<c:if test="${not empty sdoCode}">
							&nbsp;&nbsp;&nbsp;[SDOCODE:-${sdoCode}]
							</c:if>
							<c:if test="${not empty feederCode}">
							&nbsp;&nbsp;&nbsp;[FEEDERCODE:-${feederCode}]
							</c:if>
							
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
                                     <!--        <div class="btn-group">
								<button class="btn green" id="viewApprovedId" onclick ="return viewApproved();">viewApproved
								</button>
							</div> -->
							</div>
							<table class="table table-striped table-hover table-bordered" id="table_3">
								<thead>
									<tr >
										<th class="table-checkbox">
									    <div id="checkEnum"><input type="checkbox" id="checkAll" onClick="selectAll(this,${ipEnumerationList.size()})" name="checkme" /></div>
									    </th>
										<!--  <th >View</th>
										<th >Approve</th>  -->
										<th >Action</th>
										  <th id="ipSetVal">RRNO&nbsp;/&nbsp;UIPSETCODE</th>
										  <th >Feeder Code</th>
										<th >Sub<br/>Station</th>
										<th>DtcCode</th>
										<th>Sanction<br/>Load</th>
										<th>MeterFixed</th>
										<th >Authorized/<br/>unAuthorized</th>
										<th >InstStatus</th>
										<th >Village</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="1" scope="page" />
									<c:forEach var="element" items="${ipEnumerationList}">
									<tr class="check" id="row${element.id}">
									 <%-- <c:choose>
										  <c:when test="${empty fn:trim(element.rrno) }">
										  <td><div id="tcEnum"> <input type="checkbox"    autocomplete="off" placeholder="" name="ipEnumeration" id="check${element.id}"  onclick="getAllIpId(this.id,'${element.id}')" value='${element.ucodeipset}'/> </div></td>
										   </c:when>
										   <c:otherwise>
										    <td><div id="tcEnum"> <input type="checkbox"    autocomplete="off" placeholder="" name="ipEnumeration" id="check${element.id}"  onclick="getAllIpId(this.id,'${element.id}')" value='${element.rrno}'/> </div></td>
										    </c:otherwise>
										</c:choose>  --%>
										<td><div id="tcEnum"> <input type="checkbox"    autocomplete="off" placeholder="" name="ipEnumeration" id="check${element.id}"  onclick="getAllIpId(this.id,'${element.id}')" value='${element.id}'/> </div></td>
<%-- 										<td><a href="#" id="view${count}" onclick="return viewMore(this.id,'${element.id}');"><i class="fa fa-edit"></i></a></td>
 --%>									   
									    <c:if test="${element.syncStatus eq 0 || element.syncStatus eq 2 || element.syncStatus eq 3}">
									    <c:choose>
										  <c:when test="${not empty element.rrno}">
										  <td>
										  <a href="#" id="map${count}" title="View Map" onclick="return viewMoreMap(this.id,'${element.id}');"><i class="fa fa-eye"></i></a>
										  <a href="#" id="view${count}" title="Edit"  onclick="return viewMore(this.id,'${element.id}');"><i class="fa fa-edit"></i></a>
										  <a href="#" id="approveId${count}" title="Approve"  onclick ="editSynStatus('${element.id}','${element.rrno}','1');"><i class="fa fa-font"></i></a>
										  <c:if test="${element.syncStatus ne 3}">
										             <span id="onHoldId1${element.id}"> <a href="#" id="onHoldId${element.id}"  title="OnHold" onclick ="editSynStatus('${element.id}','${element.rrno}','3');"><i class="fa fa-h-square"></i></a></span>
										     </c:if>
										  <c:if test="${element.syncStatus eq 3}">
										  </c:if>
										   <a href="#" id="imageNewfds${element.id}" title="Edit Image"  onclick="return imagePopDiv(this.id,'${element.id}');"><i class="fa fa-picture-o"></i></a>
										  </td>
										   </c:when>
										   <c:otherwise>
										   <td>
										    <a href="#" id="map${count}" title="View Map" onclick="return viewMoreMap(this.id,'${element.id}');"><i class="fa fa-eye"></i></a>
										   <a href="#" id="view${count}" title="Edit" onclick="return viewMore(this.id,'${element.id}');"><i class="fa fa-edit"></i></a>
										   <a href="#" id="approveId${element.id}"  title="Approve" onclick ="editSynStatus('${element.id}','${element.ucodeipset}','1');"><i class="fa fa-font"></i></a>
										   <c:if test="${element.syncStatus ne 3}">
										           <span id="onHoldId1${element.id}"> <a href="#" id="onHoldId${element.id}"   title="OnHold" onclick ="editSynStatus('${element.id}','${element.ucodeipset}','3');"><i class="fa fa-h-square"></i></a></span>
										     </c:if>
										    <c:if test="${element.syncStatus eq 3}">
										  </c:if>
										    <a href="#" id="imageNewfds${element.id}" title="Edit Image"  onclick="return imagePopDiv(this.id,'${element.id}');"><i class="fa fa-picture-o"></i></a>
										   </td>
										    </c:otherwise>
										    
										</c:choose>
										</c:if>
										
										<%-- <c:choose>
										    <c:when test="${element.syncStatus eq 3}">
										               <td></td>
										     </c:when>
										     <c:otherwise>
										     <c:if test="${not empty element.rrno}">
										           <td id="tData${element.id}"><a href="#" id="onHoldId"   onclick ="editSynStatus('${element.id}','${element.rrno}','3');">OnHold</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										     </c:if>
										     <c:if test="${empty element.rrno}">
										           <td id="tData${element.id}"><a href="#" id="onHoldId"   onclick ="editSynStatus('${element.id}','${element.ucodeipset}','3');">OnHold</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										     </c:if>
										      </c:otherwise>
										</c:choose> --%>
										<c:choose>
										  <c:when test="${empty fn:trim(element.rrno) }">
										  <td>${element.ucodeipset} </td>
										   </c:when>
										   <c:otherwise>
										    <td>${element.rrno} </td>
										    </c:otherwise>
										</c:choose>
										<td>${element.feederno}</td>
										<td>${element.substation}</td>
										<td>${element.dtccode}</td>
										<td>${element.sanctionload}</td>
										<td>${element.meterfixed}</td>
										<td>${element.authorised}</td>
										<td>${element.inststatus}</td>
										<td>${element.village}</td>
										
									
									        
										
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"/>
											</c:forEach>	
									
								</tbody>
							</table>
							<div class="btn-group">
								<button class="btn green" id="approveIPSetId" onclick ="return approveSelectedIp();">ApproveSelected
								</button>
							</div>
							 <div class="btn-group">
								<button class="btn green" id="onHoldIPSetId" onclick ="return onHoldSelectedIp();">OnHoldSelected
								</button>
							</div>
						</div>
						
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
			</div>
			
			
			<%-- <div class="row" id="ipEnumApprovedId" hidden="true">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>IP ENUMERATION
							<c:if test="${not empty sdoCode}">
							&nbsp;&nbsp;&nbsp;[SDOCODE:-${sdoCode}]
							</c:if>
							<c:if test="${not empty feederCode}">
							&nbsp;&nbsp;&nbsp;[FEEDERCODE:-${feederCode}]
							</c:if>
							
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
                                            <div class="btn-group">
								<button class="btn green" id="viewApprovedId" onclick ="return viewApproved();">viewApproved
								</button>
							</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="table_3">
								<thead>
									<tr >
										<th class="table-checkbox">
									    <input type="checkbox" id="selectall" onClick="selectAll(this,${ipEnumerationList.size()})" name="checkme"/>
									    </th>
										<th hidden="true">Id</th>
										<th >View</th>
										<th >Approve</th>
										<th >Onhold</th>
										  <th id="ipSetVal">RRNO&nbsp;/&nbsp;UIPSETCODE</th>
										  <th >Feeder Code</th>
										<th >Sub<br/>Station</th>
										<th>DtcCode</th>
										<th>Sanction<br/>Load</th>
										<th>MeterFixed</th>
										<th >Authorized/<br/>unAuthorized</th>
										<th >InstStatus</th>
										<th >Village</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="1" scope="page" />
									<c:forEach var="element" items="${ipEnumerationList}">
									<tr class="check" id="row${element.id}">
										<td><div id="tcEnum"> <input type="checkbox"    autocomplete="off" placeholder="" name="ipEnumeration" id="check${element.id}"  onclick="getAllIpId(this.id,'${element.id}')" value='${element.id}'/> </div></td>
										<td hidden="true">${element.id} </td>
										<td><a href="#" id="view${count}" onclick="return viewMore(this.id,'${element.id}');">View & Edit</a></td>
									   
									    <c:if test="${element.syncStatus eq 0 || element.syncStatus eq 2 || element.syncStatus eq 3}">
									    <c:choose>
										  <c:when test="${not empty element.rrno}">
										  <td ><a href="#" id="approveId"   onclick ="editSynStatus('${element.id}','${element.rrno}','1');">Approve</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										   </c:when>
										   <c:otherwise>
										   <td><a href="#" id="approveId"   onclick ="editSynStatus('${element.id}','${element.ucodeipset}','1');">Approve</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										    </c:otherwise>
										</c:choose>
										</c:if>
										
										<c:choose>
										    <c:when test="${element.syncStatus eq 3}">
										               <td></td>
										     </c:when>
										     <c:otherwise>
										     <c:if test="${not empty element.rrno}">
										           <td id="tData${element.id}"><a href="#" id="onHoldId"   onclick ="editSynStatus('${element.id}','${element.rrno}','3');">OnHold</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										     </c:if>
										     <c:if test="${empty element.rrno}">
										           <td id="tData${element.id}"><a href="#" id="onHoldId"   onclick ="editSynStatus('${element.id}','${element.ucodeipset}','3');">OnHold</a></td><!-- data-target="#stack4" data-toggle="modal" -->
										     </c:if>
										      </c:otherwise>
										</c:choose>
										<c:choose>
										  <c:when test="${empty fn:trim(element.rrno) }">
										  <td>${element.ucodeipset} </td>
										   </c:when>
										   <c:otherwise>
										    <td>${element.rrno} </td>
										    </c:otherwise>
										</c:choose>
										<td>${element.feederno}</td>
										<td>${element.substation}</td>
										<td>${element.dtccode}</td>
										<td>${element.sanctionload}</td>
										<td>${element.meterfixed}</td>
										<td>${element.authorised}</td>
										<td>${element.inststatus}</td>
										<td>${element.village}</td>
										
									
									        
										
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"/>
											</c:forEach>	
									
								</tbody>
							</table>
							<div class="btn-group">
								<button class="btn green" id="approveIPSetId" onclick ="return approveSelectedIp();">ApproveSelected
								</button>
							</div>
							 <div class="btn-group">
								<button class="btn green" id="onHoldIPSetId" onclick ="return onHoldSelectedIp();">OnHoldSelected
								</button>
							</div>
						</div>
						
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
			</div>
			 --%>
			
			 		<div class="portlet-body">
			 		<button id="buttonshow" class="btn green pull-center" onclick=" validateSearchFilterChange();" style="display: none;margin-left: 10px;">Back</button>
							<div id="map-canvas" class="gmaps" style="height: 500px;display: none;"></div>				 
               <!-- END PORTLET-->
               </div>
			
			
			<div id="stack3" class="modal fade" data-width="400">
						<div class="modal-dialog" style="width:1200px;height: 1800px">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true" onclick="closePopDiv()"></button>
									<h4 class="modal-title"><b>ENUMERATION FOR IP SETS</b></h4>
									<!-- <p class="padding">
										'<font color="red">*</font>'&nbsp;Indicates mandatory fields.
									</p> -->
								</div>

								<div class="modal-body" id='mainDiv'>
									<div class="row">
										<div class="col-md-12">

											<form:form action="" modelAttribute="ipEnumeration"
												commandName="ipEnumeration" method="post" id="ipEnumeration" enctype="multipart/form-data">

												<%-- <tr>
	 		          <td>
	 			<table style="display: inline-block;">
	 			                                          <tr id="rrnotd" hidden="true">
															<td >RRNO</td>
															<td></td>
															<td style="width:250px;"><form:input path="rrno" id="rrno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="rrno" ></form:input></td>
															
														</tr >
														<tr hidden="true">
															<td >Id</td>
															<td></td>
															<td ><form:input path="id" id="id" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="id" ></form:input></td>
														</tr>
														
														<tr hidden="true">
															<td >Id</td>
															<td></td>
															<td ><form:input path="siteCode" id="siteCode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="siteCode" ></form:input></td>
														</tr>
														<tr id="ucodeipsetTd" hidden="true">
															<td >UCODEIPSET</td>
															<td></td>
															<td ><form:input path="ucodeipset" id="ucodeipset" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="ucodeipset" ></form:input></td>
														</tr>
														<tr >
															<td >DOI</td>
															<td></td>
															<td ><form:input path="doi" id="doi" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="doi" readonly="true"></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >DTCCODE</td>
															<td></td>
															<td ><form:input path="dtccode" id="dtccode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dtccode" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >FEEDERNAME</td>
															<td></td>
															<td ><form:input path="esnumshno" id="esnumshno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="esnumshno" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >FEEDERNO</td>
															<td></td>
															<td ><form:input path="feederno" id="feederno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="feederno" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >FEEDERNAME</td>
															<td></td>
															<td ><form:input path="feedername" id="feedername" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="feedername" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >DOS</td>
															<td></td>
															<td ><form:input path="dos" id="dos" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dos" ></form:input></td>
														</tr>
														
														
														<tr hidden="true" >
															<td >FHNAME</td>
															<td></td>
															<td ><form:input path="fhname" id="fhname" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="fhname" ></form:input></td>
														</tr>
														<tr hidden="true" >
															<td >MOBILENO</td>
															<td></td>
															<td ><form:input path="mobileno" id="mobileno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="mobileno" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >TRACKPOINT</td>
															<td></td>
															<td ><form:input path="trackpoint" id="trackpoint" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="trackpoint" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >CAPTUREMODE</td>
															<td></td>
															<td ><form:input path="capturemode" id="capturemode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="capturemode" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >NORTH</td>
															<td></td>
															<td ><form:input path="north" id="north" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="north" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >EAST</td>
															<td></td>
															<td ><form:input path="east" id="east" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="east" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >ENTERDATE</td>
															<td></td>
															<td ><form:input path="enterdate" id="enterdate" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="enterdate" ></form:input></td>
														</tr>
														<tr hidden="true" >
															<td >DEVICTOSERVERDT</td>
															<td></td>
															<td ><form:input path="devictoserverdt" id="devictoserverdt" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="devictoserverdt" ></form:input></td>
														</tr>
														<tr hidden="true" >
															<td >SYNCSTATUS</td>
															<td></td>
															<td ><form:input path="syncStatus" id="syncStatus" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="syncStatus" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >APKVERSION</td>
															<td></td>
															<td ><form:input path="apkVersion" id="apkVersion" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="apkVersion" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >USERNAME</td>
															<td></td>
															<td ><form:input path="username" id="username" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="username" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >DATESTAMP</td>
															<td></td>
															<td ><form:input path="datestamp" id="datestamp" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="datestamp" ></form:input></td>
														</tr>
														
														<tr hidden="true" >
															<td >CONNECTEDLOAD</td>
															<td></td>
															<td ><form:input path="connectedload" id="connectedload" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="connectedload" ></form:input></td>
														</tr>
														
														<tr>
														    <td>&nbsp;</td>
														</tr>
														<tr>
															<td><u>CONSUMER DETAILS:</u></td>
															<td></td>
														</tr>
														
														<tr>
															<td >Name</td>
															<td></td>
															<td><form:input path="name" id="name" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="name" ></form:input></td>
														</tr>
														
														<tr>
															<td >Address1</td>
															<td></td>
															<td><form:textarea path="address1" id="address1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="address1" ></form:textarea></td>
														</tr>
														
														<tr>
															<td >Address2</td>
															<td></td>
															<td><form:textarea path="address2" id="address2" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="address2" ></form:textarea></td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														    
														</tr>
														<tr>
															<td >SanctionLoad</td>
															<td></td>
															<td><form:input path="sanctionload" id="sanctionload" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="sanctionload" ></form:input></td>
														</tr>
														
														<tr >
															<td>Authorized/UnAuthorized</td>
															<td></td>
															<td><form:select path="authorised"
																	id="authorised"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="authorised"  >
																	<form:option value="select">Select</form:option>
																	<form:option value="Y">Y</form:option>
																	<form:option value="N">N</form:option>
																</form:select></td>
														</tr>
														
														<tr >
															<td>Type of Water Source</td>
															<td></td>
															<td><form:select path="watersource"
																	id="watersource"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="watersource"  >
																	<form:option value="select">Select</form:option>
																	<form:option value="OW">Open well</form:option>
																	<form:option value="CHN">Channel</form:option>
																	<form:option value="BW">Borewell</form:option>
																	<form:option value="Others">Others</form:option>
																</form:select></td>
														</tr>
														
														<tr>
															<td >Water Source Other</td>
															<td></td>
															<td><form:input path="watersourceother" id="watersourceother" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="watersourceother" ></form:input></td>
														</tr>
														
														<tr >
															<td>Installation Status</td>
															<td></td>
															<td><form:select path="inststatus"
																	id="inststatus"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="inststatus"  >
																	<form:option value="select">Select</form:option>
																	<form:option value="USE">In use</form:option>
																	<form:option value="NUSE">Not in use</form:option>
																	<form:option value="DRY">Dry</form:option>
																</form:select></td>
														</tr>
														
														<tr >
															<td>Crop Grown in Land</td>
															<td></td>
															<td><select  id="select2_sample2" class="form-control select2" multiple placeholder="select Products" type="text" name="select2_sample2" onchange=" ">
																	<option value="select">Select</option>
																	<option value="Ragi">Ragi</option>
																	<option value="Jowar">Jowar</option>
																	<option value="Paddy">Paddy</option>
																	<option value="Sugarcane">Sugarcane</option>
																	<option value="Coconut">Coconut</option>
																	<option value="Areca">Areca</option>
																	<option value="Horticultural Nurseries">Horticultural Nurseries</option>
																	<option value="Floriculture">Floriculture</option>
																	<option value="bricks manufacturing">bricks manufacturing</option>
																</select></td>
														</tr>
														
														<tr>
															<td >Crop Other</td>
															<td></td>
															<td><form:input path="cropother" id="cropother" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="cropother" ></form:input></td>
														</tr>
											</table>
												
												 <table  >	
												
												<tr><td>Voltage&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td></td>
															<td></td>
															<td >
									<div class="col-md-2">
									<lable>RY</lable>
										<input type="text"  class="form-control" id="ryvolts" name="ryvolts">
									</div>
									<div class="col-md-2">
									<lable>YB</lable>
										<input type="text"  class="form-control" id="ybvolts" name="ybvolts">
									</div>
									<div class="col-md-2">
									<lable>BR</lable>
										<input type="text"  class="form-control" id="brvolts" name="brvolts">
									</div>
									</td> 
									</tr>
									
									<tr><td>Current&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td></td>
															<td></td>
															<td >
									<div class="col-md-2">
									<lable>R</lable>
										<input type="text"  class="form-control" id="ramps" name="ramps">
									</div>
									<div class="col-md-2">
									<lable>Y</lable>
										<input type="text"  class="form-control" id="yamps" name="yamps">
									</div>
									<div class="col-md-2">
									<lable>B</lable>
										<input type="text"  class="form-control" id="bamps" name="bamps">
									</div>
									</td> 
									</tr>
												</table >
												<table style="margin-left: 500px;margin-top: -675px;">
												<tr>
												     <td>Meter Fixed&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td></td>
															<td style="width:250px;"><form:input path="meterfixed" id="meterfixed" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="meterfixed" ></form:input></td>
												</tr>
												<tr>
														    <td>&nbsp;</td>
														</tr>
												<tr>
															<td><u>Location of IP Set :</u></td>
															<td></td>
														</tr>
														<tr>
															<td >Village</td>
															<td></td>
															<td><form:input path="village" id="village" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="village" ></form:input></td>
														</tr>
														<tr>
															<td >Longitude</td>
															<td></td>
															<td><form:input path="longitude" id="longitude" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="longitude" ></form:input></td>
														</tr>
														
														<tr>
															<td >Latitude</td>
															<td></td>
															<td><form:input path="latitude" id="latitude" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="latitude" ></form:input></td>
														</tr>
														
														<tr>
														    <td>&nbsp;</td>
														</tr>
														<tr>
															<td><u>Details of DTC feeding to this IP set:</u></td>
															<td></td>
														</tr>
														<tr>
															<td >Capcity</td>
															<td></td>
															<td><input  id="capacity" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="capacity" readonly="true"></input></td>
														</tr>
														<tr>
															<td >Location</td>
															<td></td>
															<td><input  id="loaction" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="loaction" readonly="true"></input></td>
														</tr>
														
														<tr>
															<td >TIMS CODE</td>
															<td></td>
															<td><input  id="tims" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="tims" readonly="true"></input></td>
														</tr>
														<tr id="udtcodeTr">
															
														</tr>
														
														<tr>
															<td >FRONT IMAGE</td>
															<td></td>
															<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/1"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','1');" data-target='#popup_image'   height="70" width="70" title="${element.rr_Number}" /></td>
														</tr>
														<tr>
															<td >LEFT IMAGE</td>
															<td></td>
															<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/1"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','1');" data-target='#popup_image'   height="70" width="70" title="${element.rr_Number}" /></td>
														</tr>
														<tr>
															<td >RIGHT IMGAGE</td>
															<td></td>
															<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/1"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','1');" data-target='#popup_image'   height="70" width="70" title="${element.rr_Number}" /></td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														<tr>
														    <td>&nbsp;</td>
														     <td>&nbsp;</td>
														      <td>&nbsp;</td>
														</tr>
														
														<tr hidden="true">
															<td >SITECODE</td>
															<td></td>
															<td><input  id="siteocde1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="siteocde1" ></input></td>
														</tr>
														<tr hidden="true">
															<td >FEEDERCODE</td>
															<td></td>
															<td><input  id="feederocde1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="feederocde1" ></input></td>
														</tr>
														<tr hidden="true">
															<td >DTCCODE</td>
															<td></td>
															<td><input  id="tcCode1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="tcCode1" ></input></td>
														</tr>
														<tr hidden="true">
															<td >AUOTHORIZATION</td>
															<td></td>
															<td><input  id="Auothorization1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="Auothorization1" ></input></td>
														</tr>
														<tr hidden="true">
															<td >FROMDATE</td>
															<td></td>
															<td><input  id="fromDate1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="fromDate1" ></input></td>
														</tr>
														<tr hidden="true">
															<td >TODATE</td>
															<td></td>
															<td><input  id="toDate1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="toDate1" ></input></td>
														</tr>
												</table>
												
												
													
								
								</td>
								</tr> --%>
								
								<div class="row" style="width: 2000px;" id="settingSessionData"> 
									<div class="col-md-2" style="width: 250px;">
									<label style="font-weight:600;">SUBDIVISION <font color="red">*</font></label>
															<label class="control-label">&nbsp;</label>
														 <select id="sdoCode1" class="form-control" name="sdoCode1" onchange="getSectionValues(this.value)" >
														    <option value="select" >Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodesAll}">
																<option value="${element[0]}" id="sdoCodeOpt">${element[0]}-${element[1]}</option>
															</c:forEach>
														  </select>
									</div>
									<div class="col-md-1" >
									<label  style="font-weight:600;">O&M Unit <font color="red">*</font></label>
										<input  type="text" class="form-control" id="oMunit" name="oMunit" style="width: 150px;" readonly="true"></input>
									</div>
									<div class="col-md-2" style="width: 150px;" id="feederCodeDiv1">
									<label style="font-weight:600;">&nbsp;&nbsp;&nbsp;Feeder <font color="red">*</font></label>
										<select type="text" class="form-control" id="feederCode1" name="feederCode1"  >
										<option value="select">Select</option>
										</select>
									</div>
									<div class="col-md-2" style="width: 200px;" id="dtcCodeDiv1">
									<label style="font-weight:600;">&nbsp;&nbsp;&nbsp;DTCCODE<font color="red">*</font><font color="red">*</font></label>
										<select type="text" class="form-control" id="dtccode1" name="dtccode1" >
										<option value="select">Select</option>
										</select>
									</div>
									<!-- <div class="col-md-1">
									<label  style="font-weight:600;">SHEET NO.</label>
										<input  type="text" class="form-control" id="esnumshno1" name="esnumshno1" style="width: 150px;" ></input>
									</div> -->
									<!-- <div class="col-md-2">
									<label  style="font-weight:600;">DOI</label>
									<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="doiVal" id="doiVal"/>
																
														</div>
														</div> -->
											<div class="col-md-2" id="sessionBtId">
									<label  style="font-weight:600;">&nbsp;</label>
									<button class="btn green pull-center" style="display: block;margin-left: 10px;" onclick="return validations('set')"
														id="setSeesionBt" >SET DATA
														</button>
														</div>
														
								</div>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="row" >
								<div class="col-md-1" id="formSitecode">
									<label class="col-md-3 control-label" style="font-weight:600;">SITECODE</label>
										<form:input path="siteCode" id="siteCode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="siteCode" readonly="true"></form:input>
									</div>
									<div class="col-md-2" style="width: 150px;">
									<label style="font-weight:600;">&nbsp;&nbsp;&nbsp;Auth/UnAuth <font color="red">*</font></label>
										<form:select path="authorised" type="text" class="form-control" id="authorised" name="authorised" onchange="displayByAuthorization(this.value);" >
										<form:option value="select">Select</form:option>
																	<form:option value="Y">Y</form:option>
																	<form:option value="N">N</form:option>
										</form:select>
									</div>
									<!-- <div  id="formRrnoDiv" >
									<div class=col-md-2>
									<label class="col-md-3 control-label" style="font-weight:600;">RRNO</label>
									<input id="rrno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="rrnoVal" ></input>
									</div>
									</div> -->
									<div class=col-md-3 id="formRrnoDiv"><label style=font-weight:600;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RRNO <font color="red">*</font></label>
									 <select id="rrnoVal" class="form-control selectpicker select2me input-medium" name="rrnoVal" onchange="getDataByRRno(this.value)">
														  
														  </select>
									</div>
									
									<%-- <div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">DOI</label>
										<form:input path="doi" type="text" class="form-control" id="doi" name="dos" readonly="true"></form:input>
									</div> --%>
									<div class="col-md-2" id="ucodeipsetIp">
									<label class="col-md-3 control-label" style="font-weight:600;" >UCODEIPSET<font color="red">*</font></label>
										<form:input path="ucodeipset" type="text" class="form-control" id="ucodeipset" name="ucodeipset" readonly="true"></form:input>
									</div>
									<div class="col-md-2" style="width: 250px;" >
									<label  style="font-weight:600;">DOI <font color="red">*</font></label>
									<div class="input-icon" >
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="" 
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="doiVal" id="doiVal"/>
																
														</div>
														</div>&nbsp;&nbsp;&nbsp;&nbsp;
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">SHEETNO.<font color="red">*</font></label>
									<form:input path="esnumshno" type="text" class="form-control" id="esnumshno" name="esnumshno"></form:input>
									</div>
								</div>
								&nbsp;&nbsp;
								<div class="row">
								<div class="col-md-2" hidden="true">
									<label class="col-md-3 control-label" style="font-weight:600;">ID</label>
										<input  id="fromDate1" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="fromDate1" ></input>
										<input id="toDate1" class="form-control placeholder-no-fix" type="text" name="toDate1" placeholder="" autocomplete="off">
										<form:input path="id" id="id" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="id" ></form:input>
									</div>
								
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">NAME<font color="red">*</font></label>
										<form:input path="name" type="name" class="form-control" id="name" name="name"></form:input>
									</div>
									<div class="col-md-2" hidden="true">
									<label class="col-md-3 control-label" style="font-weight:600;">FHNAME</label>
										<form:input path="fhname" id="fhname" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="fhname" ></form:input>
									</div>
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">ADDRESS1<font color="red">*</font></label>
										<form:textarea path="address1" type="text" class="form-control" id="address1" name="address1" ></form:textarea>
									</div>
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">ADDRESS2</label>
										<form:textarea path="address2" type="text" class="form-control" id="address2" name="address2"></form:textarea>
									</div>
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">Village</label>
										<form:input path="village" type="village" class="form-control" id="village" name="village"></form:input>
									</div>
									
								</div>
								&nbsp;
								<%-- <div class="row" >
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Village</label>
										<form:input path="village" type="village" class="form-control" id="sanctionload" name="village"></form:input>
									</div>
								</div>&nbsp;&nbsp; --%>
								<div class="row" >
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">SanctionLoad</label>
										<form:input path="sanctionload" type="sanctionload" class="form-control" id="sanctionload" name="sanctionload"></form:input>
									</div>
									
									<%-- <div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Feeder</label>
										<form:input path="feederno" id="feederno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="feederno" ></form:input>
									</div> --%>
									<%-- <div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">DTCCODE</label>
										<form:input path="dtccode" id="dtccode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dtccode" ></form:input>
									</div> --%>
									
									
									<div class="col-md-2" style="width: 150px;">
									<label style="font-weight:600;">&nbsp;&nbsp;&nbsp;Meter Fixed<font color="red">*</font></label>
										<form:select path="meterfixed" type="text" class="form-control" id="meterfixed" name="meterfixed" >
										<form:option value="select">Select</form:option>
																	<form:option value="Y">Y</form:option>
																	<form:option value="N">N</form:option>
										</form:select>
									</div>
								<div class="col-md-2" style="width: 150px;">
										<label style="font-weight:600;">WaterSource<font color="red">*</font></label>
										<form:select path="watersource" type="text" class="form-control" id="watersource" name="watersource" onchange="displayWaterSourceOther(this.value);">
										                            <form:option value="select">Select</form:option>
																	<form:option value="OW">Open well</form:option>
																	<form:option value="CHN">Channel</form:option>
																	<form:option value="BW">Borewell</form:option>
																	<form:option value="Others">Others</form:option>
										</form:select>
										</div>
										<div class="col-md-2" style="width: 150px;display: none;" id="waterOtherDiv" >
										<label style="font-weight:600;" >WateSoucerOther</label>
										<form:input path="watersourceother" id="watersourceother" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="watersourceother" ></form:input>
										</div>
										<div class="col-md-2" style="width: 150px;">
										<label style="font-weight:600;" >InstStatus<font color="red">*</font></label>
										<form:select path="inststatus" type="text" class="form-control" id="inststatus" name="inststatus">
										                            <form:option value="select">Select</form:option>
																	<form:option value="USE">In use</form:option>
																	<form:option value="NUSE">Not in use</form:option>
																	<form:option value="DRY">Dry</form:option>
										</form:select>
										</div>
								</div>
								&nbsp;&nbsp;
								<%-- <div class="row">
								<div class="col-md-2" style="width: 150px;">
									<label style="font-weight:600;">&nbsp;&nbsp;&nbsp;Meter Fixed</label>
										<form:select path="meterfixed" type="text" class="form-control" id="meterfixed" name="meterfixed" >
										<form:option value="select">Select</form:option>
																	<form:option value="Y">Y</form:option>
																	<form:option value="N">N</form:option>
										</form:select>
									</div>
								<div class="col-md-2" style="width: 150px;">
										<label style="font-weight:600;">WaterSource</label>
										<form:select path="watersource" type="text" class="form-control" id="watersource" name="watersource">
										                            <form:option value="select">Select</form:option>
																	<form:option value="OW">Open well</form:option>
																	<form:option value="CHN">Channel</form:option>
																	<form:option value="BW">Borewell</form:option>
																	<form:option value="Others">Others</form:option>
										</form:select>
										</div>
										<div class="col-md-2" style="width: 150px;">
										<label style="font-weight:600;" >InstStatus</label>
										<form:select path="inststatus" type="text" class="form-control" id="inststatus" name="inststatus">
										                            <form:option value="select">Select</form:option>
																	<form:option value="USE">In use</form:option>
																	<form:option value="NUSE">Not in use</form:option>
																	<form:option value="DRY">Dry</form:option>
										</form:select>
										</div>
								</div>
								
								&nbsp;&nbsp; --%>
								<div class="form-group">
										<label  class="" style="font-weight:600;">Crop<font color="red">*</font></label>
										<div class="checkbox-list">
											<label class="checkbox-inline">
											<input type="checkbox"  value="Ragi" id="Ragi" name="crop" class="Ragi"> Ragi
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Jowar" id="Jowar" name="crop" class="Jowar"> Jowar
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Paddy" id="Paddy" name="crop" class="Paddy"> Paddy
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Sugarcane" id="Sugarcane" name="crop" class="Sugarcane"> Sugarcane
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Areca" id="Areca" name="crop" class="Areca"> Areca
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Coconut" id="Coconut" name="crop" class="Coconut"> Coconut
											</label>
											<label class="checkbox-inline">
											<input type="checkbox" value="HorticulturalNurseries" id="HorticulturalNurseries" name="crop" class="HorticulturalNurseries"> HorticulturalNurseries
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Floriculture" id="Floriculture" name="crop" class="Floriculture"> Floriculture
											</label>
											<label class="checkbox-inline">
											<input type="checkbox" value="bricksmanufacturing" id="bricksmanufacturing" name="crop" class="bricksmanufacturing" > bricksManufacturing
											</label>
											<label class="checkbox-inline">
											<input type="checkbox" value="noCrop" id="bricksmanufacturingNew" name="crop" class="bricksmanufacturing" onclick="disableCheckFun()"> noCrop
											</label>
										</div>
									</div>&nbsp;&nbsp;
									
									<!-- <div class="form-group">
										<div class="checkbox-list">
											<label class="checkbox-inline">
											<input type="checkbox"  value="Areca" id="Areca" name="Areca"> Areca
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Coconut" id="Coconut" name="Coconut"> Coconut
											</label>
											<label class="checkbox-inline">
											<input type="checkbox" value="HorticulturalNurseries" id="HorticulturalNurseries" name="HorticulturalNurseries"> HorticulturalNurseries
											</label>
											<label class="checkbox-inline">
											<input type="checkbox"  value="Floriculture" id="Floriculture" name="Floriculture"> Floriculture
											</label>
											<label class="checkbox-inline">
											<input type="checkbox" value="bricksmanufacturing" id="bricksmanufacturing" name="bricksmanufacturing"> bricksManufacturing
											</label>
										</div>
									</div>
									&nbsp; -->
									<div class="row" >
									<div class="col-md-2">
									<label class="col-md-3 control-label" style="font-weight:600;">CropOther</label>
										<form:input path="cropother" type="text" class="form-control" id="cropother" name="cropother"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">&nbsp;</label>
									<label class="col-md-3 control-label" style="font-weight:600;">&nbsp;</label>
									<label class="col-md-3 control-label" style="font-weight:600;">Voltage:-</label>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">RY</label>
										<form:input path="ryvolts" type="text"  class="form-control" id="ryvolts" name="ryvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">YB</label>
										<form:input path="ybvolts" type="text"  class="form-control" id="ybvolts" name="ybvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">BR</label>
										<form:input path="brvolts" type="text"  class="form-control" id="brvolts" name="brvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">&nbsp;</label>
									<label class="col-md-3 control-label" style="font-weight:600;">&nbsp;</label>
									<label class="col-md-3 control-label" style="font-weight:600;">Current:-</label>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">R</label>
										<form:input path="ramps" type="text"  class="form-control" id="ramps" name="ramps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Y</label>
										<form:input path="yamps" type="text"  class="form-control" id="yamps" name="yamps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">B</label>
										<form:input path="bamps" type="bamps"  class="form-control" id="bamps" name="bamps"></form:input>
									</div>
								</div>&nbsp;&nbsp;
								<div class="row">
								<div class="col-md-2" style="width: 150px;">
										<label style="font-weight:600;" >CaptureMode</label>
										<form:select path="capturemode" type="text" class="form-control" id="capturemode" name="capturemode" onchange="captureMode();">
										                            <%-- <form:option value="select">Select</form:option> --%>
																	<form:option value="Phone">Phone</form:option>
																	<form:option value="etrex10">etrex10</form:option>
																	<form:option value="Fxdevice">Fxdevice</form:option>
										</form:select>
										</div>
								<div class="col-md-2" id="longitudeId">
									<label class="col-md-3 control-label" style="font-weight:600;">Longitude<font color="red">*</font></label>
										<form:input path="longitude" type="text" class="form-control" id="longitude" name="longitude" placeholder="XX.XXXXXX"></form:input>
									</div>
									<div class="col-md-2" id="latitudeId">
									<label class="col-md-3 control-label" style="font-weight:600;">Lattitude<font color="red">*</font></label>
										<form:input path="latitude" type="text" class="form-control" id="latitude" name="latitude" placeholder="XX.XXXXXX"></form:input>
									</div>
									<div class="col-md-2" id="northId">
									<label class="col-md-3 control-label" style="font-weight:600;">North<font color="red">*</font></label>
										<form:input path="north" id="north" class="form-control" type="text" autocomplete="off" name="north" placeholder="D.M.SS.SS"></form:input>
									</div>
									<div class="col-md-2" id="eastId">
									<label class="col-md-3 control-label" style="font-weight:600;">East<font color="red">*</font></label>
										<form:input path="east" id="east" class="form-control" type="text" autocomplete="off"  name="east" placeholder="D.M.SS.SS"></form:input>
									</div>
									<div class="col-md-2" id="trackPointId">
									<label class="col-md-3 control-label" style="font-weight:600;">TrackPoint<font color="red">*</font></label>
										<form:input path="trackpoint" type="text" class="form-control" id="trackpoint" name="trackpoint"></form:input>
									</div>
								
									<div class="col-md-2" id="frontFormTR">
														<label class="col-md-3 control-label" style="font-weight:600;">Front image</label>
														<input type="file" name="front" id="frontForm"></input>
												</div>
													
													
													<div class="col-md-2" id="rightFormTR">
													<label class="col-md-3 control-label" style="font-weight:600;">Right image</label>
														<input type="file" name="right" id="rightForm"></input>
													</div>
													
													
													<div class="col-md-2" id="leftFormTR">
													<label class="col-md-3 control-label" style="font-weight:600;">Left image</label>
														<input type="file" name="left" id="leftForm"></input>
													</div>
								
								<div id="photoDiv" style="display: none;">
														<table style="table-layout: fixed; width: 424px;margin-top: 15px;margin-left: 411px;">
														<%-- <colgroup>
														<col style="width: 100px">
														<col style="width: 63px">
														<col style="width: 100px">
														<col style="width: 74px">
														<col style="width: 100px">
														<col style="width: 75px">
														</colgroup> --%>
														  <tr>
														    <td>Front Image</td> 
														     <td><div id="frontImageView">
														     </div>
														     </td>
														    <td>Right Image</td>
														    <td><div id="rightImageView"></div></td>
														    <td>Left Image <br></td>
														    <td><div id="leftImageView"></div></td>
														  </tr>
														</table>
												</div>
											
								</div>
				
							
	
								<%-- <div class="row">
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Voltage</label>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">RY</label>
										<form:input path="ryvolts" type="text"  class="form-control" id="ryvolts" name="ryvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">YB</label>
										<form:input path="ybvolts" type="text"  class="form-control" id="ybvolts" name="ybvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">BR</label>
										<form:input path="brvolts" type="text"  class="form-control" id="brvolts" name="brvolts"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Current</label>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">R</label>
										<form:input path="ramps" type="text"  class="form-control" id="ramps" name="ramps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Y</label>
										<form:input path="yamps" type="text"  class="form-control" id="yamps" name="yamps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">B</label>
										<form:input path="bamps" type="bamps"  class="form-control" id="ryvolts" name="bamps"></form:input>
									</div>
								</div> --%>
								<%-- <div class="row">
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Current</label>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">R</label>
										<form:input path="ramps" type="text"  class="form-control" id="ramps" name="ramps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">Y</label>
										<form:input path="yamps" type="text"  class="form-control" id="yamps" name="yamps"></form:input>
									</div>
									<div class="col-md-1">
									<label class="col-md-3 control-label" style="font-weight:600;">B</label>
										<form:input path="bamps" type="bamps"  class="form-control" id="ryvolts" name="bamps"></form:input>
									</div>
								</div> --%>
								
												<div class="modal-footer">
													<button id="closeBtId" onclick="closePopDiv()" type="button" data-dismiss="modal" class="btn red">Close</button>
													<button class="btn green pull-right" style="display: block;"
														id="modifyBtId" onclick="return validations('modify')">Modify
														</button>
														<button class="btn blue pull-right" style="display: block;"
														id="modifyApproveBtId" onclick="return validations('modifyApprove')">Modify And Approve
														</button>
														<button class="btn green pull-right" style="display: block;"
														id="ApproveBtId" onclick="return validations('approve')">Approve
														</button>
														<button class="btn blue pull-right" style="display: block;"
														id="onHoldBtId" onclick="return validations('onhold')">Onhold
														</button>
														<button class="btn green pull-right" style="display: block;"
														id="addBtId" onclick="return validations('add')">Add
														</button>
												</div>

											</form:form>
										</div>
									</div>

								</div>

							</div>
						</div>
					</div>
					
					
					<div id="stack5" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body">
											<div class="row">
											<div class="col-md-12">
												<form action="" id="showAllAprovedId" method="post">
												<table>
												<tr>
												<td>Site Code</td>
												<td><font color="red">*</font></td>
												<td>
												 <select id="sdoCodeS" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederDataSearch(this.value)">
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodes}">
																<option value="${element[0]}" id="sdoCodeOpt">${element[0]}-${element[1]}</option>
															</c:forEach>
														  </select>
												</td>
												</tr>
												
												<tr>
												<td>Feeder Code</td>
												<td><font color="red">*</font></td>
												<td>
												<select id="feederCodeDivA" class="form-control selectpicker placeholder-no-fix input-medium" name="feederCode">
											    <option value='All' id="feederCodeOpt">Select Feeder Code</option>
												</select>	
												</td>
												</tr>
												
												<tr>
												<td>From Date</td>
												<td><font color="red"></font></td>
												<td>
												<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="<fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy" />" data-date="${currentDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" />
														</div>
												</td>
												</tr>
												
												<tr>
												<td>To Date</td>
												<td><font color="red"></font></td>
												<td>
												<div class="input-icon">
															<i class="fa fa-calendar"></i> <input
																class="form-control date-picker input-medium"  type="text"
																value="<fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy"/>" data-date="${currentDate}" data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="toDate" />
														</div>
												</td>
												</tr>
												
												<tr>
																									  
												</table>
													<div class="modal-footer">
													     <button class="btn blue pull-right" onclick="return showApproved(1)" id="ApprovedId">Show Approved</button>
													      <button class="btn blue pull-right" onclick="return showApproved(0)" id="HoldId">Show Hold</button>
													     <button type="button" data-dismiss="modal" class="btn">Cancel</button>
													</div>
													</form>
											</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>
							
							
					
						<div class="modal fade" id="popup_image" tabindex="-1" data-backdrop="static" data-keyboard="false" style="margin-left: 300px;">
		       <div class="modal-dialog" id="image">
		        <div class="modal-content" style="width:550px;height:660px">
		         <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >
		           &times;
		          </button>
		          <h4>IMAGE</h4>
		          <div>
		          <img id="rl2" src="./resources/assets/img/RotateLeft.jpg" onclick="rotateLeft('0');" style="margin-left:200px; height: 30px; width: 30px;" />&nbsp;&nbsp;&nbsp;&nbsp;
		          <img id="rr1" src="./resources/assets/img/RotateRight.jpg" style="height: 30px;width: 30px;" onclick="rotateRight('0'); "/>
		         </div>
		         </div>
		         <div class="modal-body">
		          <div class="rotatecontrol" id="Imageview" >
		           <img id="tempImg" src="" />
		          </div>
		         </div>
		        </div>		        
		       </div>		       
      </div>
      
      
      <div class="modal fade" id="basicNewOld" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">Edit Images</h4>
										</div>
										<div class="modal-body">
										<form id="form2" method="post" action="./test/upload" enctype="multipart/form-data">
											<input hidden="true" id="hideSetImageVal" type="text" name="hideSetImageVal">
											<table>
												<tr id="frontFormTrewR">
														<td>Edit Front Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="frontFormEdit" id="frontFormEdit"></input></td>
														<td>Previous Front Image</td>
														<td><div id="frontDIV"></div></td>
													</tr>
													
													<tr id="rightForewrrmTR">
														<td>Edit Right Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="rightFormEdit" id="rightFormEdit"></input></td>
													    <td>Previous Right Image</td>
														<td><div id="rightDIV"></div></td>
													</tr>
													
													<tr id="leftFormrewrTR"> 
														<td>Edit Left Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="leftFormEdit" id="leftFormEdit"></input></td>
													    <td>Previous Left Image</td>
														<td><div id="leftDIV"></div></td>
													</tr>
											</table>
											<!-- <div class="col-md-2" id="frontFormTR">
														<label class="col-md-3 control-label" style="font-weight:600;">Front image</label>
														<input type="file" name="frontEdit" id="frontFormEdit"></input>
												</div>
													
													
													<div class="col-md-2" id="rightFormTR">
													<label class="col-md-3 control-label" style="font-weight:600;">Right image</label>
														<input type="file" name="rightEdit" id="rightFormEdit"></input>
													</div>
													
													
													<div class="col-md-2" id="leftFormTR">
													<label class="col-md-3 control-label" style="font-weight:600;">Left image</label>
														<input type="file" name="leftEdit" id="leftFormEdit"></input>
													</div> -->
													</form>
										</div>
										<div class="modal-footer">
											<button type="button" id="btnclose" class="btn default" data-dismiss="modal">Close</button>
											<button type="button" data-dismiss="modal" class="btn blue" onclick="return uploadJqueryForm()">Update changes</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
					<div id="stack4" class="modal fade" tabindex="-1" data-width="400">
							 <div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">Update Sync Status</h4>
										</div>
										
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12">
												<form >
												<table>
												<tr hidden="true">
												<td>Id</td>
												<td></td>
												<td><input type="text" id="editTaskStatusId" name="editTaskStatusId" class="form-control placeholder-no-fix" value=""/></td>
												</tr>
											
													<tr>
													<td>Status</td>
													<td><font color="red">*</font></td>
													<td>
													<select id="editTaskStatus" name="editTaskStatus" class="form-control placeholder-no-fix">
													<option value="select">Select</option>
													<option value="2">Approved</option>
													<option value="1">On Hold</option>
													</select>
													</td>
													</tr>													  
													</table>
													<div class="modal-footer">
															
													     <button class="btn blue pull-right" onclick = "return validateTaskStatus()">Update Sync Status</button>
													     <button type="button" data-dismiss="modal" class="btn">Cancel</button>
													    <!--  <a href="./updateTaskName"></a>	 -->	
															
															</div>
															
															</form>
															</div>
											</div>
											 
										</div>
										
									</div>
								</div>
							</div>
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>

<style type="text/css">
#map-canvas {
        width: 900px;
        height: 400px;
      }
</style>



