<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>

<script src="http://maps.google.com/maps/api/js?sensor=true"  type="text/javascript" ></script>
<script src="./resources/gmaps.js"  type="text/javascript" ></script>

  <script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">

  	$(".page-content").ready(function(){    	
  		    	App.init();
  		    	TableEditable.init(); 
  		    	TableManaged.init(); 
  		        FormComponents.init();
  		  	     var captureMode="Phone";
  		  	     var dtc="N";
  		  	 $('#villageText').hide();
		  		  	if(dtc=='Y')
		  	    	{
		  	    		$('#meterWorkingId').show();
		  				$('#modemId').show();
		  				
		  	    	}else{
		  	    		$('#meterWorkingId').hide();
		  				$('#modemId').hide();
		  	    	} 
		  		  
			      	if(captureMode=='etrex10')
			      	{
			      		$('#latitudeId').hide();
			      		$('#longitudeId').hide();
			      		
			      		$('#eastId').show();
			      		$('#northId').show();
			      		
			      	}else{
			      		$('#latitudeId').show();
			      		$('#longitudeId').show();
			      		
			      		$('#eastId').hide();
			      		$('#northId').hide();
			      	} 
			      	
  		    	 $('#Ip_Cesc,#dtcEnumerationData').addClass('start active ,selected');
  		    	 $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#MIS-Reports-photoBilling,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
  		          $('#addData').click(function(){
  		        	oldDtcCheck="";
  		        	$("#onHoldBtId").hide();
  				   $("#ApproveBtId").hide();
  				   $("#modifyBtId").hide();
  				   $("#modifyApproveBtId").hide();
  				   $("#photoDiv").hide();
  				   $("#addBtId").show();
  				   $("#leftFormTR").show();
  				   $("#rightFormTR").show();
  				   $("#frontFormTR").show();
  		        	    
  		        	     $("#sitecode option[value='${sitecode}']").attr('selected','selected');
  		        	     $("#feederCodeSession option[value='${feederCode}']").attr('selected','selected');
  		        	     $('#feederNoId').hide();
  		        	     $("#headerTable").show();
  		        	       document.getElementById("tcenumerationFormId").reset();
  		        	     check='${feederCode}';
  		        	     $('#oldLoaction').val('${feederCode}');

  		        	 //    alert($('#oldLoaction').val());
	  		        	       var villages = "";
	 	  		 			   var i=0;
	 	  		 			   
	  	  		  			  $.ajax({
	  	  		  				  type:"POST",
	  	  		  				  url:"./getVillageData",
	  	  		  				  data:{"feederCode":'${feederCode}',"sitecode" : '${sitecode}'},
	  	  		  				  dataType:"JSON",
	  	  		  				  success:function(response)
	  	  		  				  {
	  	  		  					   villages+="<select id='village' name='village' onchange=changeVillageVal(this.value) class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''>";
	  	  		  					if(response.length==0)
	  								{
	  								  villages+="<option id='villageOpt' value='select'>select</option>";
	  								}
	  	  		  					   for( i=0;i<response.length;i++){
	  	  									villages+="<option id='villageOpt' value='"+response[i]+"'>"+response[i]+"</option>";
	  	  								}
	  	  								villages+="<option id='villageOpt' value='others'>others</option></select><span></span>";
	  	  								$("#villageDiv").html(villages);
	  	  							   $('#villageDiv').show();
	  	  						       $('#villageText').hide();
	  	  		  				  }
	  	  		  			  });
	  	  		  	
	    	              }); 
  		    
  		   });
  	
  	function getFeederData(sdoCode)
  	{
  		  if(sdoCode == 0){
  			  $("#mrCode").attr("disabled","true");
  		  }else{
  			 var feederCode = "";
 			  var i=0;
  			  $.ajax({
  				  type:"POST",
  				  url:"./getFeederDataTC",
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
  	
  	function getFeederDataSearch(sdoCode)
  	{
  		  if(sdoCode == 0){
  			  $("#mrCode").attr("disabled","true");
  		  }else{
  			 var feederCode = "";
 			  var i=0;
  			  $.ajax({
  				  type:"POST",
  				  url:"./getFeederDataSearch",
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
  	
  	function getVillageData(feederCode)
  	{
  		//alert("1");
  		
  		$('#oldLoaction').val(feederCode);
  		check=$('#oldLoaction').val();
  	      	  var sitecode = $("#sitecode").val();
  			  var villages = "";
 			  var i=0;
  			  $.ajax({
  				  type:"POST",
  				  url:"./getVillageData",
  				  data:{"feederCode":feederCode,"sitecode" : sitecode},
  				  dataType:"JSON",
  				  success:function(response)
  				  {
  					
  					   villages+="<select id='village' onchange=changeVillageVal(this.value) name='village' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''>";
						if(response.length==0)
							{
							  villages+="<option id='villageOpt' value='select'>select</option>";
							}
  					   for( i=0;i<response.length;i++){
							villages+="<option id='villageOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						villages+="<option id='villageOpt' value='others'>others</option></select><span></span>";
						$("#villageDiv").html(villages);
						$('#villageDiv').show();
						$('#villageText').hide();
  				  }
  			  });
  	}
  	function changeVillageVal()
  	{
  		//alert("--"+$('#village').val());
  		if($('#village').val().trim()=='others')
  			{
  			//alert("comin inside if----");
  			$('#villageDiv').hide();
  			$('#villageText').show();
  			}
  		else
  			{
  			//alert("else");
  			$('#villageDiv').show();
  			$('#villageText').hide();
  			
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
  					tcCode+="<select id='tcCode' name='tcCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder=''  value=''><option id='tcCodeOpt' value=''>Select MR Code</option>";
						for( i=0;i<response.length;i++){
							tcCode+="<option id='tcCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
						}
						tcCode+="</select><span></span>";
						
						$("#tcCodeDiv").html(tcCode);
						$('#tcCode').select2();
  				  }
  			  });
 }
  	
  	function showPopup(param,opera) {
  	/*   var tableData="";
    	  $.ajax({
  			type : "GET",
  			url : "./editDTCData/" + opera,
  			dataType : "json",
  			cache:false,
  			async:false,
  			success : function(response)
  				{
  				
  				
  				
  				tableData += "<tr>"	
	              	+"<th colspan='6'> Details</th>"
	              	+"</tr>"						
					+"<tr>"
					+"<th>Feeder No.</th>"
					+"<td>"+response.feederno+"</td>"
					+"<th>Village</th>"
					+"<td>"+response.loaction+"</td>"
					+"<th>DTC Name/location</th>"
					+"<td>"+response.loaction+"</td>"
					+"</tr>"
					+"<tr>"
					+"<th>Old DTC Code</th>"
					+"<td>"+response.oldLoaction+"</td>"
					+"<th>Capacity (KVA).</th>"
					+"<td>"+response.capacity+"</td>"
					+"<th>TIMS Code</th>"
					+"<td>"+response.tims+"</td>"
					+"</tr>"
					+"<tr>"
					+"<th>Make</th>"
					+"<td>"+response.make+"</td>"
					+"<th>Capture Mode.</th>"
					+"<td>"+response.capturemode+"</td>";
					if(response.capturemode=='etrex10')
	  		    	{
						tableData +="<th>Northing</th>"
						+"<td>"+response.north+"</td>"
						+"</tr>"
						+"<tr>"
						+"<th>Easting </th>"
						+"<td>"+response.east+"</td>";
	  					  
	  		    	}else{
	  		    		tableData +="<th>Latitude</th>"
						+"<td>"+response.latitude+"</td>"
						+"</tr>"
						+"<tr>"
						+"<th>Longitude </th>"
						+"<td>"+response.longitude+"</td>";
	  		    		
	  		    	} 
					
					tableData +="<th>Track Point</th>"
					+"<td>"+response.trackPoint+"</td>"
					+"<th>Nature of Load</th>"
					+"<td>"+response.natureld+"</td>"
					+"</tr>"
					+"<tr>"
					+"<th>Total IP'S</th>"
					+"<td>"+response.totalip+"</td>"
					+"<th>Lightning Arresters</th>";
					
					if(response.ligarr=='Y')
	  		    	{
						tableData+="<td >YES</td>";
	  		    	}else{
	  		    		tableData+="<td >NO</td>";
	  		    	} 
					
					
					tableData+="<th>DTC Meter</th>";
					
						if(response.dtcmtr=='Y')
		  		    	{
							tableData+="<td >YES</td>";
		  		    	}else{
		  		    		tableData+="<td >NO</td>";
		  		    	} 
					
					tableData+="</tr>"
					+"<tr>"
					+"<th>11 KV GOS</th>";
					if(response.gos=='Y')
	  		    	{
						tableData+="<td >YES</td>";
	  		    	}else{
	  		    		tableData+="<td >NO</td>";
	  		    	} 
					
					tableData+="<th>HT protection</th>"
					+"<td>"+response.htprot+"</td>"
					+"<th>LT protection</th>"
					+"<td>"+response.ltprot+"</td>"
					+"</tr>"
					+"<tr>"
					+"<th>Grounding</th>"
					+"<td>"+response.grounding+"</td>"
					+"<th>Structure Of Pole</th>"
					+"<td>"+response.strofpole+"</td>"
					+"<th>DTC SL. No.</th>"
					+"<td>"+response.dtcslno+"</td>"
					+"</tr>"
					+"<th>DTC Rating</th>"
					+"<td>"+response.dtcrating+"</td>"
					+"<th>DTC Oil Capacity (Liters) </th>"
					+"<td>"+response.dtcoilcap+"</td>"
					+"<th>Connected Load.</th>"
					+"<td>"+response.load+"</td>"
					+"</tr>";
 }
    	     });
    	  $('#appendViewData').html(tableData);
    		$('#'+param).attr("data-toggle", "modal");
   		$('#'+param).attr("data-target","#stack222");
  		
		 */
		 
		 

  		
  		$.ajax({
			  
  			type : "GET",
  			url : "./editDTCData/" + opera,
  			dataType : "json",
  			cache:false,
  			async:false,
  			success : function(response)
			  {
				  var map='';
						 /* alert(response.latitude+"--"+response.longitude); */
							if(response.latitude.trim()!=null || response.longitude.trim()!=null)
		    				  {
			
								map = new GMaps({
						            div: '#map-canvas',
						            lat: parseFloat(response.latitude),
						            lng :parseFloat(response.longitude),
						            zoom:10,
						        });	
		    				  }
							 
						
						var labelName='tc';
		    		    	 map.addMarker({
		    		    		
						            lat: parseFloat(response.latitude),
						            lng: parseFloat(response.longitude),
						            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|75A3FF|000000',
						            infoWindow: {
						                content: "<div style=width:290px;>"
						                         +"<table style='margin-left: 10px;margin-bottom: 10px;'>"
						                         +"<tr><th>Longitude </th><td>"+response.longitude+"</td></tr>"
						                         +"<tr><th>Lattitude</th><td>"+response.latitude+"</td></tr>"
												  +"<tr><th>Tims</th><td>"+response.tims+"</td></tr>"
												 +"<tr><th>Substation</th><td>"+response.substation+"</td></tr>"
												 +"<tr><th>FeederName</th><td>"+response.feedername+"</td></tr>"
												 +"<tr><th>DTC Code</th><td>"+response.udtccode+"</td></tr>" 
											/* 	 +"<tr><th>NoIPSet</th><td>"+response.noIPSet+"</td></tr>" */
						                         +"</table>"
						                         +"</div>"
						            }
						        });
							
			  }
			  
  		});
		
  		$('#dtccEnumertation').hide();
  		//$('#map-canvas').show();
  		$('#buttonshow').show();
  		
		 
		 
	}
  	
 	function validateSearchFilterChange() {
		
  		$('#dtccEnumertation').show();
  		//$('#map-canvas').hide();
  		$('#buttonshow').hide();
	}
  
  	function edit(param,opera)
  	{
		   $("#onHoldBtId").show();
		   $("#ApproveBtId").show();
		   $("#modifyBtId").show();
		   $("#modifyApproveBtId").show();
		   $("#photoDiv").show();
		   $("#addBtId").hide();
		   $("#leftFormTR").hide();
		   $("#rightFormTR").hide();
		   $("#frontFormTR").hide();
		   $("#headerTable").hide();
		   $("#sdoCodeTR").hide();
		   $("#subStationTR").hide();
		   $("#enumTR").hide();
		   $("#doiTR").hide(); 
		  
  	  $.ajax({
			type : "GET",
			url : "./editDTCData/" + opera,
			dataType : "json",
			cache:false,
			async:false,
			success : function(response)
				{
				oldDtcCheck=response.udtccode;
				check=response.feederno;
				$("#id").val(response.id);
				$("#sitecode").val(response.sitecode);
				$("#doi").val(dateFormating(response.doi));
				$("#esnumshno").val(response.esnumshno);
				$("#substation").val(response.substation);
				$("#feederno option[value='"+response.feederno+"']").attr('selected','selected');
				
				 $('#feederno').click(function(){
					 $('#oldLoaction').val($(this).val());
					 check=$('#oldLoaction').val();
				    	var villages = "";
			 			  var i=0;
			  			  $.ajax({
			  				  type:"POST",
			  				  url:"./getVillageData",
			  				  data:{"feederCode":$(this).val(),"sitecode" : response.sitecode},
			  				  dataType:"JSON",
			  				  success:function(res)
			  				  {
			  					   villages+="<select id='village' name='village' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''>";
									for( i=0;i<res.length;i++){
										villages+="<option id='villageOpt' value='"+res[i]+"'>"+res[i]+"</option>";
									}
									villages+="</select><span></span>";
									$("#villageDiv").html(villages);
			  				  }
			  			  }); 
				    });
				 
				      var villages = "";
		 			  var i=0;
		  			  $.ajax({
		  				  type:"POST",
		  				  url:"./getVillageData",
		  				  data:{"feederCode":response.feederno,"sitecode" : response.sitecode},
		  				  dataType:"JSON",
		  				  success:function(res)
		  				  {
		  					   villages+="<select id='village' name='village' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' value=''>";
								for( i=0;i<res.length;i++){
									if(res[i]==response.village){
										villages+="<option id='villageOpt' value='"+res[i]+"' selected='selected'>"+res[i]+"</option>";
									}else{
										villages+="<option id='villageOpt' value='"+res[i]+"'>"+res[i]+"</option>";
									} 
								}
								villages+="</select><span></span>";
								$("#villageDiv").html(villages);
		  				  }
		  			  }); 
		  		
				$("#feedername").val(response.feedername);
				$("#loaction").val(response.loaction);
				$("#oldLoaction").val(response.udtccode);
				$("#capacity").val(response.capacity);
				$("#tims").val(response.tims);
				$("#udtccode").val(response.udtccode);
				$("#make").val(response.make);
				$("#natureld").val(response.natureld);
				
		    	if(response.natureld=='OTHERS')
		    	{
		    		$('#otherNatureLoadId').show();
		    		$("#natureotherld").val(response.natureotherld);
		    	}else{
		    		$('#otherNatureLoadId').hide();
		    	} 
				
				$("#totalip").val(response.totalip);
				if(response.ligarr=='Y')
		    	{
					$("#ligarr").val("Y");
		    	}else{
		    		$("#ligarr").val("N");
		    	} 
				
				if(response.gos=='Y')
		    	{
					$("#gos").val('Y');
		    	}else{
		    		
		    		$("#gos").val('N');
		    	} 
				
				$("#htprot").val(response.htprot);
				$("#ltprot").val(response.ltprot);
				
				$("#grounding").val(response.grounding);
				$("#enterdate").val(dateFormating(response.enterdate));
				$("#username").val(response.username);
				$("#datestamp").val(response.datestamp);
				$("#load").val(response.load);
			
				if(response.dtcmtr=='Y')
		    	{
					$("#dtcmtr").val('Y');
					
					$('#meterWorkingId').show();
					$('#modemId').show();
					
					if(response.meterworking=='Y')
			    	{
						$("#meterworking").val('Y');
			    	}else{
			    		$("#meterworking").val('N');
			    	} 
					
					if(response.modem=='Y')
			    	{
						$("#modem").val('Y');
			    	}else{
			    		$("#modem").val('N');
			    	} 
					
		    	}else{
		    		$("#dtcmtr").val('N');
		    		$('#meterWorkingId').hide();
					$('#modemId').hide();
		    	} 
				
				$("#strofpole").val(response.strofpole);
				$("#dtcslno").val(response.dtcslno);
				$("#dtcrating").val(response.dtcrating);
				$("#dtcoilcap").val(response.dtcoilcap);
				
				if(response.capturemode=='etrex10')
		    	{
					$('#latitudeId').hide();
					$('#longitudeId').hide();
					
					$('#eastId').show();
					$('#northId').show();
					$("#north").val(response.north);
					$("#east").val(response.east);
					
					$("#capturemode").val(response.capturemode);
					  
		    	}else{
		    		 
		    		$('#latitudeId').show();
					$('#longitudeId').show();
					
					$("#longitude").val(response.longitude);
					$("#latitude").val(response.latitude);
					
		    		$('#eastId').hide();
					$('#northId').hide();
					$("#capturemode").val(response.capturemode);
		    	} 
				
				$("#dtcname").val(response.dtcname);
				$("#apkVersion").val(response.apkVersion);
				$("#emieNumber").val(response.emieNumber);
				$("#trackPoint").val(response.trackPoint);

				$("#frontImageView").html("<img data-magnifyby='10' class='magnify' src='./getFrontPic/"+response.id+"' onclick=viewImageDoc('./getFrontPic/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
				$("#leftImageView").html("<img data-magnifyby='10' class='magnify' src='./getLeftPic/"+response.id+"' onclick=viewImageDoc('./getLeftPic/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
				$("#rightImageView").html("<img data-magnifyby='10' class='magnify' src='./getRightPic/"+response.id+"' onclick=viewImageDoc('./getRightPic/"+response.id+"')  width='50px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
		   }
  	     });
  		$('#'+param).attr("data-toggle", "modal");//edit button
 		$('#'+param).attr("data-target","#stack2"); //edit button
  	}
  	
	function deleteImages(id,image)
  	{  
		$.ajax({
		    type : "POST",
		    data:{"id":id,"image":image},
		    url : "./deleteImage",
		    dataType:"JSON",
		    success : function(response)
	  		  {	
		    	 $("#frontImageView").html("");
		    	 $("#deleteFrontImage").hide();
		    	 bootbox.alert("Left image deleted successfully");
	  		  }
	    }); 
  	}
	
  	function viewImageDoc(path)
  	{  
  	    $('#Imageview').empty();  	    
  	    rotation=0;
  	    rotateRight();
  	    rotateLeft();
  	    $('#Imageview').append("<img id=\"tempImg\" style=\"width:500px;height:500px;\" src='"+path+"'/>");
  	    	
  	}
  	
	function meterFixed()
    {
	var dtcMeter=$('#dtcmtr').val().trim();
    	if(dtcMeter=='Y')
    	{
    		$('#meterWorkingId').show();
			$('#modemId').show();
			
    	}else{
    		$('#meterWorkingId').hide();
			$('#modemId').hide();
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
	
  	function displayOthers()
    {
    	var natureld=$('#natureld').val().trim();
    	if(natureld=='OTHERS')
    	{
    		$('#otherNatureLoadId').show();
    	}else{
    		$('#otherNatureLoadId').hide();
    	} 
    }

    function validations(value)
		{
    	
			  var id=$('#id').val();
			  var floatPatter = /^[0-9]+[0-9.]*$/;
			  var optinalNumericPatter = /^[0-9.]*$/;
			  var latLangPattern = /^[0-9]{2}[.][0-9]{2,6}$/;
			  //var norEastPattern = /^[0-9]{3}[ ][0-9]{2}[ ][0-9]{2}[.][0-9]{2}$/;
			  var norEastPattern = /^[0-9]{2}[.][0-9]{2}[.][0-9]{2}[.][0-9]{2}$/;
			  var timsCodePattern = /^[0-9]{0,2}$/;
			   if(value=='set')
				{
				  
				   var sitecode=document.getElementById("sitecode").value.trim();
				   var dateOfInsepection= document.getElementById("dateOfInsepection").value.trim();
				   var enumSheetNo= document.getElementById("enumSheetNo").value.trim();
				   var subStationCode= document.getElementById("subStationCode").value.trim();
				   var feederCode= document.getElementById("feederCodeSession").value.trim();
				  
				    if(dateOfInsepection=='' ||dateOfInsepection==null )
					 {
					   bootbox.alert('Please enter date of inspection');
					   return false;
					 }
				    if(sitecode==''||sitecode==null)
					 {
					   bootbox.alert('Please select SDO Code');
					   return false;
					 }  
				   
				    if(enumSheetNo==''||enumSheetNo==null)
					 {
					   bootbox.alert('Please enter Enumeration Sheet Number');
					   return false;
					 } 
				    
				    if(subStationCode==''||subStationCode==null)
					 {
					   bootbox.alert('Please enter sub station');
					   return false;
					 }
				  
				    if(feederCode=='0' || feederCode=='' || feederCode=='All' || feederCode==null)
					 {
					   bootbox.alert('Please enter feeder code');
					   return false;
					 }
				   
				    $.ajax({
					    type : "POST",
					    url : "./setToSession",
					    dataType : "json",
					    cache:false,
					    data:{
					    	dateOfInsepection :dateOfInsepection,
					    	sitecode:sitecode,
					    	enumSheetNo:enumSheetNo,
					    	subStationCode:subStationCode,
					    	feederCode:feederCode
					    },
					    success : function(response)
				  		  {
					    	  $('#dateOfInsepection').val(response.dateOfInsepection);
					    	  $('#sitecode').val(response.sitecode);
					    	  $('#enumSheetNo').val(response.enumSheetNo);
					    	  $('#subStationCode').val(response.subStationCode);
					    	  $("#feederCodeSession option[value='"+response.feederCode+"']").attr('selected','selected');
					    	  bootbox.alert('Details set successfully.');
				  		  }
				    });
				    return false;
				}
			   if(value=='add')
				{

				    if(document.getElementById("dateOfInsepection").value.trim()=='')
					 {
					   bootbox.alert('Please enter date of inspection');
					   return false;
					 }
				 
				    if($("#sitecode").val().trim()=='')
					 {
					   bootbox.alert('Please select SDO Code');
					   return false;
					 }  
				   
				    if(document.getElementById("enumSheetNo").value.trim()=='')
					 {
					   bootbox.alert('Please enter Enumeration Sheet Number');
					   return false;
					 } 
				    
				    if(document.getElementById("subStationCode").value.trim()=='')
					 {
					   bootbox.alert('Please enter sub station');
					   return false;
					 }
				    if(document.getElementById("feederCodeSession").value.trim()=='' || document.getElementById("feederCodeSession").value.trim()=='0' || document.getElementById("feederCodeSession").value.trim()=='All')
					 {
					   bootbox.alert('Please enter feeder code');
					   return false;
					 }
				    
				    if($('#village').val().trim()=='others')
					 { 
					   
					   if($('#villageText').val().trim()=='')
						   {
						   bootbox.alert('Please enter Village');
						   return false;
						   }
					  
					 }
				  
				    
				   if($('#loaction').val().trim()=='')
					 {
					   bootbox.alert('Please enter locaton');
					   return false;
					 }
				   if($('#village').val().trim()=='')
					 {
					   bootbox.alert('Please enter village');
					   return false;
					 }
				  // alert("-------"+$('#village').val().trim());
			
				   if($("#oldLoaction").val().trim()=='')
					 {
					   bootbox.alert('Please Enter  Dtc Code ');
					   return false;
					 } 
				   if(bValidate==false)
					   {
					   bootbox.alert('Please Enter  Dtc Code Correctly in the format feedercodeXX');
					   return false;
					   }
				   
				    if(!$('#tims').val().trim().match(timsCodePattern))
		   			{
		   			bootbox.alert("TIMS Code format should be xx in numbers");
		   			return false;
		   			} 
				   
				
				   if($('#capturemode').val().trim()=='')
					 {
					   bootbox.alert('Please enter capacity');
					   return false;
					 }
				   
				   if($('#natureld').val().trim()=='OTHERS')
			    	{
					   if($('#natureotherld').val().trim()=='')
						 {
						   bootbox.alert('Please enter other load');
						   return false;
						 }
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
					    
					   if($('#trackPoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   
			    	}else{
			    		
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
				   
				   if(!$('#trackPoint').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter track point in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#totalip').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter total IP'S in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#dtcoilcap').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter DTC oil capacity in numeric.");
		   			return false;
		   			}
				     
					 if($('#load').val().trim()=='')
					 {
					   bootbox.alert('Please enter connected load');
					   return false;
					 }else{
						 if(!$('#load').val().trim().match(floatPatter))
				   			{
				   			bootbox.alert("Please enter connected load in numeric.");
				   			return false;
				   			}
					 }
				   $('#id').val(0);
				   $("#tcenumerationFormId").attr("action", "./addDTCDetails");
				   $("#tcenumerationFormId").submit();
				}
			  
			if(value=='modify')
				{
				
				validateDtcCode();
				   
				   if($('#loaction').val().trim()=='')
					 {
					   bootbox.alert('Please enter locaton');
					   return false;
					 }
				   if($("#oldLoaction").val().trim()=='')
					 {
					   bootbox.alert('Please Enter  Dtc Code ');
					   return false;
					 } 
				   /* if(bValidate==false)
					   {
					   bootbox.alert('Please Enter  Dtc Code Correctly in the format feedercodeXX');
					   return false;
					   } */
				   
				   if($('#village').val().trim()=='')
					 {
					   bootbox.alert('Please enter village');
					   return false;
					 }
				   
				   if(!$('#tims').val().trim().match(timsCodePattern))
		   			{
		   			bootbox.alert("TIMS Code format should be xx in numbers");
		   			return false;
		   			}
				   
				   if($('#capturemode').val().trim()=='')
					 {
					   bootbox.alert('Please enter capacity');
					   return false;
					 }
				   
				   if($('#natureld').val().trim()=='OTHERS')
			    	{
					   if($('#natureotherld').val().trim()=='')
						 {
						   bootbox.alert('Please enter other load');
						   return false;
						 }
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
					    
					   if($('#trackPoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   
			    	}else{
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
				   
				   if(!$('#trackPoint').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter track point in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#totalip').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter total IP'S in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#dtcoilcap').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter DTC oil capacity in numeric.");
		   			return false;
		   			}
				     
					 if($('#load').val().trim()=='')
					 {
					   bootbox.alert('Please enter connected load');
					   return false;
					 }else{
						 var floatPatter = /^[0-9]+[0-9.]*$/;
						 if(!$('#load').val().trim().match(floatPatter))
				   			{
				   			bootbox.alert("Please enter connected load in numeric.");
				   			return false;
				   			}
					 }
				  
				    var str = $("#tcenumerationFormId").serialize();
				    $.ajax({
					    type : "POST",
					    data:str,
					    url : "./editDTCDetails/"+value,
					    dataType:"JSON",
					    success : function(response)
				  		  {	
					    	 $('#tData'+id).html('Modified');
					    	 $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >Selected records Modifed</span></div>');
				  		  }
				    }); 
				}
			if(value=='modifyApprove')
				{
				validateDtcCode();
				
				   if($('#loaction').val().trim()=='')
					 {
					   bootbox.alert('Please enter locaton');
					   return false;
					 }
				   
				   if($("#oldLoaction").val().trim()=='')
					 {
					   bootbox.alert('Please Enter  Dtc Code ');
					   return false;
					 } 
				   if(bValidate==false)
					   {
					   bootbox.alert('Please Enter  Dtc Code Correctly in the format feedercodeXX');
					   return false;
					   }
				   
				   if($('#village').val().trim()=='')
					 {
					   bootbox.alert('Please enter village');
					   return false;
					 }
				   
				   if(!$('#tims').val().trim().match(timsCodePattern))
		   			{
		   			bootbox.alert("TIMS Code format should be xx in numbers");
		   			return false;
		   			}
				   
				   if($('#capturemode').val().trim()=='')
					 {
					   bootbox.alert('Please enter capacity');
					   return false;
					 }
				   
				   if($('#natureld').val().trim()=='OTHERS')
			    	{
					   if($('#natureotherld').val().trim()=='')
						 {
						   bootbox.alert('Please enter other load');
						   return false;
						 }
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
						   bootbox.alert('Northing format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					  
					   if(!$('#east').val().trim().match(norEastPattern))
						 {
						   bootbox.alert('Please enter east');
						   return false;
						 }
					   
					   if($('#east').val().trim()=='')
						 {
						   bootbox.alert('Easting format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					    
					   if($('#trackPoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   
			    	}else{
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
				   
				   if(!$('#trackPoint').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter track point in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#totalip').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter total IP'S in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#dtcoilcap').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter DTC oil capacity in numeric.");
		   			return false;
		   			}
				     
				     if($('#load').val().trim()=='')
					 {
					   bootbox.alert('Please enter connected load');
					   return false;
					 }else{
						 var floatPatter = /^[0-9]+[0-9.]*$/;
						 if(!$('#load').val().trim().match(floatPatter))
				   			{
				   			bootbox.alert("Please enter connected load in numeric.");
				   			return false;
				   			}
					 }
				     
				    var str = $("#tcenumerationFormId").serialize();
				    $.ajax({
					    type : "POST",
					    data:str,
					    url : "./editDTCDetails/"+value,
					    dataType:"JSON",
					    success : function(response)
				  		  {	
					    	 $('#row'+id).remove();
					    	  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >Selected records Modifed And Approved</span></div>');
				  		  }
				    });
				}
			 if(value=='approve')
				{
					
				   if($('#loaction').val().trim()=='')
					 {
					   bootbox.alert('Please enter locaton');
					   return false;
					 }
				   
				   if($('#village').val().trim()=='')
					 {
					   bootbox.alert('Please enter village');
					   return false;
					 }
				   
				   if(!$('#tims').val().trim().match(timsCodePattern))
		   			{
		   			bootbox.alert("TIMS Code format should be xx in numbers");
		   			return false;
		   			}
				   
				   if($('#capturemode').val().trim()=='')
					 {
					   bootbox.alert('Please enter capacity');
					   return false;
					 }
				   
				   if($('#natureld').val().trim()=='OTHERS')
			    	{
					   if($('#natureotherld').val().trim()=='')
						 {
						   bootbox.alert('Please enter other load');
						   return false;
						 }
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
						   bootbox.alert('Northing format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					  
					   if(!$('#east').val().trim().match(norEastPattern))
						 {
						   bootbox.alert('Please enter east');
						   return false;
						 }
					   
					   if($('#east').val().trim()=='')
						 {
						   bootbox.alert('Easting format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					    
					   if($('#trackPoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   
			    	}else{
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
				   
				   if(!$('#trackPoint').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter track point in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#totalip').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter total IP'S in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#dtcoilcap').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter DTC oil capacity in numeric.");
		   			return false;
		   			}
				     
				     if($('#load').val().trim()=='')
					 {
					   bootbox.alert('Please enter connected load');
					   return false;
					 }else{
						 var floatPatter = /^[0-9]+[0-9.]*$/;
						 if(!$('#load').val().trim().match(floatPatter))
				   			{
				   			bootbox.alert("Please enter connected load in numeric.");
				   			return false;
				   			}
					 }
				 approveStatus(id,'1');
				}
			 if(value=='onhold')
				{
					
				   if($('#loaction').val().trim()=='')
					 {
					   bootbox.alert('Please enter locaton');
					   return false;
					 }
				   
				   if($('#village').val().trim()=='')
					 {
					   bootbox.alert('Please enter village');
					   return false;
					 }
				   
				   if(!$('#tims').val().trim().match(timsCodePattern))
		   			{
		   			bootbox.alert("TIMS Code format should be xx in numbers");
		   			return false;
		   			}
				   
				   if($('#capturemode').val().trim()=='')
					 {
					   bootbox.alert('Please enter capacity');
					   return false;
					 }
				   
				   if($('#natureld').val().trim()=='OTHERS')
			    	{
					   if($('#natureotherld').val().trim()=='')
						 {
						   bootbox.alert('Please enter other load');
						   return false;
						 }
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
						   bootbox.alert('Northing format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					  
					   if(!$('#east').val().trim().match(norEastPattern))
						 {
						   bootbox.alert('Please enter east');
						   return false;
						 }
					   
					   if($('#east').val().trim()=='')
						 {
						   bootbox.alert('Easting format should be xxx xx xx.xx in numbers');
						   return false;
						 }
					    
					   if($('#trackPoint').val().trim()=='')
						 {
						   bootbox.alert('Please enter track point');
						   return false;
						 }
					   
			    	}else{
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
				   
				   if(!$('#trackPoint').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter track point in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#totalip').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter total IP'S in numeric.");
		   			return false;
		   			}
				   
				   if(!$('#dtcoilcap').val().trim().match(optinalNumericPatter))
		   			{
		   			bootbox.alert("Please enter DTC oil capacity in numeric.");
		   			return false;
		   			}
				     
				     if($('#load').val().trim()=='')
					 {
					   bootbox.alert('Please enter connected load');
					   return false;
					 }else{
						 var floatPatter = /^[0-9]+[0-9.]*$/;
						 if(!$('#load').val().trim().match(floatPatter))
				   			{
				   			bootbox.alert("Please enter connected load in numeric.");
				   			return false;
				   			}
					 }
				approveStatus(id,'3');
				}
			$('#closeBtId').click();
			return false;
		}
    	
  	function updateStatus()
	{
		var checkboxes = document.getElementsByName('tcEnumeration');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
			{
				if(checkboxes[i].checked)
				 {
					checks = checks+checkboxes[i].value+",";
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
				  url:"./approveSelectedTC",
				  data:{"checks":checks},
				  dataType:"JSON",
				  success:function(response)
				  {
					  for(var i=0;i<response.length;i++)
						{ 
						  $('#row'+response[i]).remove();
						  $('#tcEnumAll span:first-child').removeClass("checked");
						}  
					  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >Selected records approved successfully</span></div>');
				  }
			  });
		return false;
	}
  	
  	function updateStatusOnHold()
	{
		var checkboxes = document.getElementsByName('tcEnumeration');
		var checks = "";
		for(var i =0;i<checkboxes.length;i++)
		{
			if(checkboxes[i].checked)
			 {
				checks = checks+checkboxes[i].value+",";
			 }
		}
		if(checks == "")
			{
				bootbox.alert("Please Select Atleast one checkbox");
				return false;
			}
		checks = checks.substring(0,checks.length-1);
		  $.ajax({
				  type:"GET",
				  url:"./onHoldSelected",
				  data:{"checks":checks},
				  dataType:"JSON",
				  success:function(response)
				  {
					   for(var i=0;i<response.length;i++)
						{ 
						  $('#tData'+response[i]).html('On Hold');
						  $('#onHoldId'+response[i]).html('');
						  $('#tcEnumAll span:first-child').removeClass("checked");
						  $('#tcEnum span:first-child').removeClass("checked");
						}  
					   $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >Selected records on hold</span></div>');
				  }
			  });
		return false;
	}
  	
  	function selectAll(source) {
		 
		   var flagChecked = 0;
			checkboxes = document.getElementsByName('tcEnumeration');
			for(var i =0;i<checkboxes.length;i++)
				{
				checkboxes[i].checked = source.checked;
				if(checkboxes[i].checked)
				 {
					flagChecked = 1;
				 }
				}
			
			 if(flagChecked==0)
			{
				$('#tcEnum span:first-child').removeClass("checked");
			}else{
				$('#tcEnum span:first-child').addClass("checked");
			} 
		}
  	
  	function approveStatus(id,syncStatus) 
		{ 
    	$.ajax({
			  
			  type:"POST",
			  url:"./approveDTC",
			  data:{"id":id,"syncStatus":syncStatus},
			  dataType:"text",
			  success:function(response)
					{
				      if(syncStatus=='1')
				    	  {
					    	  if(response==id)
							  {
							   $('#row'+response).remove();
							   $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >TC Approved Successfully.</span></div>');
							  }else
							  {
							   $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >TC Not Approved.</span></div>');
							  }
				    	  }
				      if(syncStatus=='3')
			    	  {
				    	  if(response==id)
						  {
				    		  $('#tData'+response).html('On Hold');
				    		  $('#onHoldId'+response).html('');
				    		  $('#tcEnum span:first-child').removeClass("checked");
				    		  
				    		  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >TC is set to onhold Successfully.</span></div>');
						  }else
						  {
							  $('#alertMsg').html('<div class="alert alert-danger display-show"><button class="close" data-close="alert"></button><span style="color:red" >TC On Hold Fail.</span></div>');
						  }
			    	  }
					}
             });
		}
  	
  	function validate()
 	 {
 		  if($('#sdoCode').val().trim()=='')
			 {
			   bootbox.alert('Please select SDO Code');
			   return false;
			 }
 	 }
  	
	 function showApproved(check)
	 {
		  if($('#sdoCodeS').val().trim()=='')
			 {
			   bootbox.alert('Please select SDO Code');
			   return false;
			 }
		$("#showAllAprovedId").attr("action", "./showApproved?status="+check);
		$("#showAllAprovedId").submit();
	 } 
	 
	/*  function getFromDateChange()
 	 { $("#fromDate").datepicker({
             onSelect: function (selected) {
             var dt = new Date(selected);
             dt.setDate(dt.getDate() + 1);
             $("#toDate").datepicker("option", "minDate", dt);
         }
     });
 	 
 	       
  		      $("#toDate").datepicker({
  		          onSelect: function (selected) {
  		        	 alert("to --------");
  		              var dt = new Date(selected);
  		              dt.setDate(dt.getDate() - 1);
  		              $("#fromDate").datepicker("option", "maxDate", dt);
  		          }
  		      });
  		      
  		      
  		      
 	 }
	
	  */
	  var oldDtcCheck="";
	  var check="";
	  var bValidate=false;
	 // var x=$('#feederCodeSession').val();
	  function validateDtcCode() {
	
		 // alert("0");
		var s=$('#oldLoaction').val();
		//alert("old -"+oldDtcCheck+"--alrdy-"+s);
		if(check==''||check==null)
			{
			//alert("1");
			bValidate=false;
			bootbox.alert('Please Select Feeder Code');
			$('#oldLoaction').val('');
			return false;
			}

		else if(s.length!=(check.length+2))
			{
			//alert("2");
			bValidate=false;
			bootbox.alert("Please Enter only Last Two Digits for Dtc Code In the Format FeederCodeXX");
			$('#oldLoaction').val(check);
			  return false;
			  
			}
		else if(/[^a-zA-Z0-9]/.test(s))
			{
			bValidate=false;
			bootbox.alert("DTC Code Only Aplha Numeric Allowed");
			$('#oldLoaction').val(check);
			  return false;
			}
	
		else if(oldDtcCheck.trim()!=s.trim())
			{
			//alert("3");
			var data=s;
			
			  $.ajax({
	  				  type:"POST",
	  				  url:"./validateDtcCodeCheck",
	  				  data:{
	  					  dtcCode:data},
	  				  dataType:"text",
	  				  success:function(response)
	  				  {
	  					  if(response>0)
	  						  {
	  						    bValidate=false;
	  						    bootbox.alert("Dtc Code Already Exists");
	  							$('#oldLoaction').val(check);
	  						    return false;
	  						  }
	  					  else
	  						  {
	  						//  alert("inside return true");
	  						     bValidate=true;
	  						     return true;
	  						  }
	  				  }
	  			  });
			  return true;
			
			}
		else
			{
			//alert("4");
			 bValidate=true;
			return true;
			}
		
		
		 
	}
	  
	  function imagePopDiv(param,id) {
		  $('#rightFormEdit').val('');
	  		$('#leftFormEdit').val('');
	  		$('#frontFormEdit').val('');
	  		
	  		$("#frontDIV").html("<img data-magnifyby='10' class='magnify' src='./getFrontPic/"+id+"' onclick=viewImageDoc('./getFrontPic/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
			$("#leftDIV").html("<img data-magnifyby='10' class='magnify' src='./getLeftPic/"+id+"' onclick=viewImageDoc('./getLeftPic/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
			$("#rightDIV").html("<img data-magnifyby='10' class='magnify' src='./getRightPic/"+id+"' onclick=viewImageDoc('./getRightPic/"+id+"')  width='60px' height='50px' data-toggle='modal' data-target='#popup_image' height='70' width='70'/>");
		  
	  		$('#hideSetImageVal').val(id);
	  		$('#'+param).attr("data-toggle", "modal");//edit button
	 		$('#'+param).attr("data-target","#basicNewOld"); //edit button
		}
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
	  function hideDetaisl(c) {
		  if(c==1)
			  {
			  $('#apprd').show();
			  $('#hold').hide();
			  }
		  else
			  {
			  $('#apprd').hide();
			  $('#hold').show();
			  }
		
	}
</script>

<div class="page-content">
	<%@include file="pagebreadcrum.jsp"%>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
               <c:if test = "${results ne 'notDisplay'}"> 			        
			         <div class="alert alert-danger display-show">
							<button class="close" data-close="alert"></button>
							<span style="color:red" id="result">${results}</span>
						</div>
			   </c:if>
			  
			<div id="alertMsg" ></div>
<!-- 			<div class="theme-panel hidden-xs hidden-sm">
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

			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<div class="tab-content">
							<div id="tab_2_2" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<div class="booking-search">
											<form action="./searchDTCEnumeration" method="post">
												<div class="row form-group">
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
														    <option value='All' id="feederCodeOpt">Select Feeder Code</option>
														  	</select>	
														</div>
														
														<div class="col-md-3">
																<div class="input-group input-large date-picker input-daterange"  data-date-format="dd-mm-yyyy">
																	<input type="text" class="form-control" value="<fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy" />" data-date="${currentDate}"
																    data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="fromDate" id="fromDate">
																	<span class="input-group-addon">
																	to </span>
																	<input type="text" class="form-control" value="<fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy" />" data-date="${currentDate}"
																data-date-format="dd-mm-yyyy" data-date-viewmode="years" name="toDate" id="toDate">
																</div>
																<span class="help-block">
																Select date range </span>
														</div>
														<button class="btn green pull-left" style="display: block;margin-left: 95px;" onclick="return validate()">Search&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button>
													
													</div>
													  <div class="col-md-2">
														<button class="btn green" data-target="#stack2"
															data-toggle="modal" id="addData">
															Add TC <i class="fa fa-plus"></i>
														</button>
													</div>
													
													 <div class="col-md-3">
														<button class="btn blue" data-target="#stack5" data-toggle="modal" onclick="hideDetaisl(1)">Show Approved</button>
														<button class="btn blue" data-target="#stack5" data-toggle="modal" onclick="hideDetaisl(0)">Show Hold</button>
													</div> 
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
			
			<c:choose>
			<c:when test="${tcenumeration ne null}">
			
			<div class="row" id="dtccEnumertation">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>DTC ENUMERATION
							<c:if test="${not empty sdoCode}">
							&nbsp;&nbsp;&nbsp;[SDOCODE:-${sdoCode}]
							</c:if>
							<c:if test="${not empty feederCodeHeader}">
							&nbsp;&nbsp;&nbsp;[FEEDERCODE:-${feederCodeHeader}]
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
									<div class="btn-group pull-right">
										<button class="btn dropdown-toggle" data-toggle="dropdown">
											Tools <i class="fa fa-angle-down"></i>
										</button>
										<ul class="dropdown-menu pull-right">
											<li><a href="#" id="print">Print</a></li>
											<li><a href="#" id="excelExport" onclick="tableToExcel('sample_editable_1', 'DTC Enumeration Data')">Export to Excel</a></li>
										</ul>
									</div>
					
						          <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
												<thead>
													<tr>	
													    <th class="table-checkbox">
													    <div id="tcEnumAll"> <input type="checkbox" id="selectall"  onClick="selectAll(this)"/></div>
													   
													    </th>
													   <th>Action</th>
													   <th>Date Of Inspection</th>
													   <th>Village</th>
													   <th>Location</th>
													   <th>Capacity</th>
													   <th>DTC Code </th>
													   <th>Nature of Load</th>
													   <th>Total IP'S</th>
													   <th>DTC Meter</th>
												       <th>Status</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="element" items="${tcenumeration}">
													<tr id="row${element.id}">
													
													    <td><div id="tcEnum"> <input type="checkbox" autocomplete="off" placeholder="" name="tcEnumeration" id="rId${element.id}" value="${element.id}" /> </div></td>
													    <td>
														<c:choose>
														 <c:when test="${element.syncstatus eq 1 || element.syncstatus eq 4}">
														      <c:out value=""></c:out>
														     </c:when>
														      <c:otherwise>
														        <a href="#" id="view${element.id}" title="View Map" onclick="showPopup(this.id,${element.id})"><i class="fa fa-eye"></i></a>
														        <a href="#" id="editData${element.id}" title="Edit" onclick="edit(this.id,${element.id})"><i class="fa fa-edit"></i></a>
														      	<a href="#" id="approveId" title="Approve" onclick ="approveStatus('${element.id}','1');"><i class="fa fa-font"></i></a>
																 <c:if test="${element.syncstatus ne 3}">
															          <a href="#" id="onHoldId${element.id}" title="On Hold" onclick ="approveStatus('${element.id}','3');"><i class="fa fa-h-square"></i></a>
															     </c:if>
															      <a href="#" id="imageNewqw${element.id}" title="Edit Image"  onclick="return imagePopDiv(this.id,'${element.id}');"><i class="fa fa-picture-o"></i></a>
															     
															    
														      </c:otherwise>
														</c:choose>
														</td>
													    <td><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss"  value="${element.doi}" /></td>
													    <td>${element.village}</td>
													    <td>${element.loaction} </td>
													    <td>${element.capacity}</td>
														<td>${element.udtccode}</td>
														<td>
														<c:if test="${element.natureld eq 'IPSET'}">
														<c:out value="IP Set"></c:out> 
														</c:if>
														
														<c:if test="${element.natureld eq 'WS'}">
														<c:out value="Water supply"></c:out> 
														</c:if>
														
														<c:if test="${element.natureld eq 'VILLAGE'}">
														<c:out value="Village"></c:out> 
														</c:if>
														
														<c:if test="${element.natureld eq 'ML'}">
														<c:out value="Mixed Load"></c:out> 
														</c:if>
														
														<c:if test="${element.natureld eq 'OTHERS'}">
														<c:out value="${element.natureotherld}"></c:out> 
														</c:if>
														
														</td>
														<td>${element.totalip}</td>
														<td>
														<c:if test="${element.dtcmtr eq 'Y'}">
														<c:out value="Yes"></c:out> 
														</c:if>
														
														<c:if test="${element.dtcmtr eq 'N'}">
														<c:out value="No"></c:out> 
														</c:if>
														</td>
														
														<td id="tData${element.id}">
														<c:if test="${element.syncstatus eq 0}">
														<c:out value="New"></c:out> 
														</c:if>
														
														<c:if test="${element.syncstatus eq 2}">
														<c:out value="Modified"></c:out> 
														</c:if>
														
														<c:if test="${element.syncstatus eq 3}">
														<c:out value="On Hold"></c:out> 
														</c:if>
														
														<c:if test="${element.syncstatus eq 1}">
														<c:out value="Approved"></c:out> 
														</c:if>
														
														<c:if test="${element.syncstatus eq 4}">
														<c:out value="Modified And Approved"></c:out> 
														</c:if>
														
														</td>
													</tr>
												</c:forEach>					
												</tbody> 
											</table>
										<c:choose>
										<c:when test="${showApproveButton eq 'notDisplay'}">
									
										</c:when>
										<c:otherwise>
										  <div class="table-toolbar" >  <div class="btn-group">
											<button class="btn green" id="approveIPSetId" onclick="return updateStatus();">Approve Selected
											</button>
										</div>
										 <div class="btn-group">
											<button class="btn green" id="onHoldIPSetId" onclick="return updateStatusOnHold();">On Hold Selected
											</button>
										</div>
										</div>
										</c:otherwise>
										</c:choose>	
						</div>
						
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			  </c:when>
		 </c:choose>
		 
		 
			 
		 <!-- 
						<div id="stack222" class="modal fade" data-width="400">
							<div class="modal-dialog" style="width: 950px;">
								<div class="modal-content">
								<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										<h4 class="modal-title">DTC Enumeration</h4>
										
								</div>
									
									<div class="modal-body" id='mainDiv'>
										<div class="row">
											<div class="col-md-12">
											<table class="table table-bordered table-striped">
													<tbody id="appendViewData">
	
													</tbody>
													</table>
										
											</div>
										</div>

									</div>

								</div>
							</div>
						</div> -->
						
						<div class="modal fade" id="basicNewOld" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">Edit Images</h4>
										</div>
										<div class="modal-body">
										<form id="form2" method="post" action="./testTc/uploadTCData" enctype="multipart/form-data">
											<input hidden="true" id="hideSetImageVal" type="text" name="hideSetImageVal">
											<table>
												<tr id="frontFormTRNew">
														<td>Edit Front Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="frontFormEdit" id="frontFormEdit"></input></td>
														<td>Previous Front Image</td>
														<td><div id="frontDIV"></div></td>
													</tr>
													
													<tr id="rightFormTRNMew">
														<td>Edit Right Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="rightFormEdit" id="rightFormEdit"></input></td>
													    <td>Previous Right Image</td>
														<td><div id="rightDIV"></div></td>
													</tr>
													
													<tr id="leftFormTRNew"> 
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
											<button type="button" class="btn blue" data-dismiss="modal" onclick="uploadJqueryForm()">Update changes</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
						
					
						
						<div id="stack2" class="modal fade" data-width="400">
							<div class="modal-dialog" style="width: 950px;">
								<div class="modal-content">
								<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
										<h4 class="modal-title">DTC Enumeration</h4>
										<p class="padding" style="margin-left: 700px;"><font color="red">*</font>&nbsp;Indicates mandatory fields.</p>
								</div>
									
									<div class="modal-body" id='mainDiv'>
										<div class="row">
											<div class="col-md-12">
											
										<form:form action=""  method="post" modelAttribute="tcEnumerationEntity" commandName="tcEnumerationEntity" id="tcenumerationFormId" enctype="multipart/form-data">
												
											<table id="headerTable">
											       <tr style="border-bottom: 1pt solid rgb(206, 117, 117);">
											            <td>Date Of Inspection</td>
														<td><div style="width: 140px !important;" class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-minviewmode="months">
																<input type="text" class="form-control" name="dateOfInsepection" id="dateOfInsepection" value="${dateOfInsepection}" readonly="true">
																<span class="input-group-btn">
																<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
																</span>
															</div></td>
														
														<td>SDO Code</td>
														<td>
														<select name="sitecode" id="sitecode" class="form-control" style="width: 140px !important;" onchange="getFeederData(this.value)" >
														<option value="">Select SDO Code</option>
														  <c:forEach var="element" items="${sdoCodes}">
																<option value="${element[0]}" >${element[0]}-${element[1]}</option>
															</c:forEach>
														</select>
														</td>
														<td>Sheet No.</td>
														<td><input type="text" id="enumSheetNo" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="enumSheetNo" value="${enumSheetNo}"></td>
														<td>Sub Station</td>
														<td><input type="text" id="subStationCode" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="subStationCode" value="${subStationCode}"></td>
														
														<td>Feeder Code</td>
														<td>
														<select id="feederCodeSession" class="form-control" style="width: 140px !important;" name="feederCode" onchange="getVillageData(this.value)">
														    <option value="">Select Feeder Code</option>
														    <c:forEach var="element" items="${feederCodes}">
																<option value="${element}" >${element}</option>
															</c:forEach>
														  </select></td>
														
														<td><button class="btn blue pull-right"id="setButtonId" onclick="return validations('set')">Set</button></td>
													</tr>
													
											</table>
											
													<table style="margin-top: 10px;">
													 <tr id="feederNoId" style="display: none;">
														<td>Feeder No.</td>
														<td><font color="red">*</font></td>
														<td>
														<select id="feederno" class="form-control" name="feederno">
														    <c:forEach var="element" items="${feederCodes}">
																<option value="${element}">${element}</option>
															</c:forEach>
														  </select>
														</td>
													</tr> 
													
													<tr>
														<td>Village</td>
														<td><font color="red">*</font></td>
														<td> 
														<div id="villageDiv"></div>
														 <input type="text" id="villageText" hidden="true" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="villageText"></input></td>
													</tr>
													
													<tr>
														<td>DTC Name/location</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="loaction" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="loaction"></input></td>
													</tr>
													
														<tr>
														<td> DTC Code</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="oldLoaction" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="oldLoaction" onchange="validateDtcCode()"></input></td>
													</tr>
												
													<tr>
														<td>Capacity (KVA)</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="capacity" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="capacity"></input></td>
													</tr>
													
													<tr>
														<td>TIMS Code</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="tims" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="tims"></input></td>
													</tr>
													<tr>
														<td>Make</td>
														<td><font color="red"></font></td>
														<td>
														 <%-- <select name="make" id="make" class="form-control">
														<!-- <option value="">Select Make</option> -->
														  <c:forEach var="element" items="${make}">
																<option value="${element}" >${element}</option>
															</c:forEach>
														</select> --%> 
													 <input type="text" id="make" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="make"></input> 
														</td>
													</tr>
													
													<tr >
															<td>Capture Mode</td>
															<td><font color="red">*</font></td>
														    <td>
														   <select id="capturemode" class="form-control" name="capturemode" onchange="captureMode();">
														    <option value='Phone'>Phone</option>
														    <option value='etrex10'>etrex10</option>
														    <option value='Fxdevice'>Fxdevice</option>
														    </select>
														    </td>
													</tr>
													
													<tr id="longitudeId" >
														<td>Longitude</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="longitude" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="XX.XXXXXX" name="longitude"></input></td>
													</tr>
													
													<tr id="latitudeId">
														<td>Latitude</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="latitude" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="XX.XXXXXX" name="latitude"></input></td>
													</tr>
													
													<tr id="northId" >
														<td>Northing</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="north" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="D.M.SS.SS" name="north"></input></td>
													</tr>
													
													<tr id="eastId" >
														<td>Easting </td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="east" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="D.M.SS.SS" name="east"></input></td>
													</tr>
													
													<tr>
														<td>Track Point</td>
														<td><font id="trackPointId" color="red" style="display: none;" >*</font></td>
														<td><input type="text" id="trackPoint" class="form-control placeholder-no-fix" type="text" autocomplete="off"  placeholder="" name="trackPoint"></input></td>
													</tr>
													
													<tr>
															<td>Nature of Load</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="natureld" class="form-control" name="natureld" onchange="displayOthers();">
														    <option value='IPSET'>IP Sets</option>
														    <option value='WS'>Water supply</option>
														    <option value='VILLAGE'>Village</option>
														    <option value='ML'>Mixed Load</option>
														    <option value='OTHERS'>Others</option>
														    </select>
														    </td>
													</tr>
													
													<tr style="display: none;" id="otherNatureLoadId">
														<td>Nature of Load Others</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="natureotherld" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="natureotherld"></input></td>
													</tr>
													
													<tr>
														<td>Total IP'S</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="totalip" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="totalip"></input></td>
													</tr>
													
													   <tr>
															<td>Lightning Arresters</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="ligarr" class="form-control" name="ligarr">
														    <option value='Y'>Yes</option>
														    <option value='N'>No</option>
														    </select>
														    </td>
														</tr>
													</table>
													<table style="margin-left: 450px; margin-top: -420px;">
														 <tr >
																<td>11 KV GOS</td>
																<td><font color="red">*</font></td>
															    <td>
															    <select id="gos" class="form-control" name="gos">
															    <option value='Y'>Yes</option>
															    <option value='N'>No</option>
															    </select>
															    </td>
														</tr>
												
														 <tr >
																<td>HT protection</td>
																<td><font color="red">*</font></td>
															    <td>
															    <select id="htprot" class="form-control" name="htprot">
															    <option value='11KVHGFUSE'>11 KV HG fuse</option>
															    <option value='11KVDOLO'>11 KV DOLO</option>
															    <option value='DC'>Direct connection</option>
															    </select>
															    </td>
														</tr> 
													
														<tr >
															<td>LT protection</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="ltprot" class="form-control" name="ltprot">
														    <option value='LTPKIT'>LTP Kit</option>
														    <option value='DISTBRBOX'>Distribution box</option>
														    <option value='FUSE CUTOUTS'>Fuse cutouts</option>
														    <option value='DC'>Direct connection</option>
														    </select>
														    </td>
													</tr> 
													
													<tr>
															<td>DTC Meter</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="dtcmtr" class="form-control" name="dtcmtr" onchange="meterFixed();">
														    <option value='N'>No</option>
														    <option value='Y'>Yes</option>
														    </select>
														    </td>
													</tr>
													<tr id="meterWorkingId">
															<td>Meter Working</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="meterworking" class="form-control" name="meterworking">
														    <option value='Y'>Yes</option>
														    <option value='N'>No</option>
														    </select>
														    </td>
													</tr>
													
													<tr id="modemId">
															<td>Modem</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="modem" class="form-control" name="modem">
														    <option value='Y'>Yes</option>
														    <option value='N'>No</option>
														    </select>
														    </td>
													</tr>
												   <tr>
															<td>Grounding</td>
															<td><font color="red">*</font></td>
														    <td>
														    <select id="grounding" class="form-control" name="grounding">
														    <option value='BODY'>Body</option>
														    <option value='NEUTRAL'>Neutral</option>
														    <option value='LA'>Lightning arresters</option>
														    </select>
														    </td>
													</tr>
													
													<tr>
														<td>Structure Of Pole</td>
														<td><font color="red"></font></td>
														<td>
														    <select id="strofpole" class="form-control" name="strofpole">
														    <option value='RURAL'>Rural</option>
														    <option value='URBAN'>Urban</option>
														    <option value='INDUSTRIAL'>Industrial</option>
														    </select>
														  
														</td>
													</tr>
													
													<tr>
														<td>DTC SL. No.</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="dtcslno" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dtcslno"></input></td>
													</tr>
												
													<tr>
														<td>DTC Rating</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="dtcrating" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dtcrating"></input></td>
													</tr>
													
													<tr>
														<td>DTC Oil Capacity (Liters)</td>
														<td><font color="red"></font></td>
														<td><input type="text" id="dtcoilcap" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="dtcoilcap"></input></td>
													</tr>
													
													<tr>
														<td>Connected Load</td>
														<td><font color="red">*</font></td>
														<td><input type="text" id="load" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="" name="load" value="0" ></input></td>
													</tr>
													
													<tr id="frontFormTR">
														<td>Front Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="front" id="frontForm"></input></td>
													</tr>
													
													<tr id="rightFormTR">
														<td>Right Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="right" id="rightForm"></input></td>
													</tr>
													
													<tr id="leftFormTR"> 
														<td>Left Image</td>
														<td><font color="red"></font></td>
														<td><input type="file" name="left" id="leftForm"></input></td>
													</tr>
													
													<tr style="display: none;"><td>
													<input type="hidden" id="id" name="id">
													</td>
													</tr>
											
												</table> 
												<div id="photoDiv" style="display: none;">
														<table style="undefined;table-layout: fixed; width: 424px;margin-top: 15px;margin-left: 411px;">
														<colgroup>
														<col style="width: 100px">
														<col style="width: 63px">
														<col style="width: 100px">
														<col style="width: 74px">
														<col style="width: 100px">
														<col style="width: 75px">
														</colgroup>
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

												<div class="modal-footer">
													<button id="closeBtId" type="button" data-dismiss="modal" class="btn">Close</button>
											
														<button class="btn red pull-right" style="display: none;"
														id="onHoldBtId" onclick="return validations('onhold')">Onhold
														</button>
														
														<button class="btn blue pull-right" style="display: none;"
														id="modifyApproveBtId" onclick="return validations('modifyApprove')">Modify And Approve
														</button>
														
														<button class="btn btn-warning pull-right" style="display: none;"
														id="modifyBtId" onclick="return validations('modify')">Modify
														</button>
														
														<button class="btn green pull-right" style="display: none;"
														id="ApproveBtId" onclick="return validations('approve')">Approve
														</button>
														
														<button class="btn green pull-right" style="display: block ;"
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
						
						
						
							
						<div class="modal fade" id="popup_image" tabindex="-1" data-backdrop="static" data-keyboard="false">
							       <div class="modal-dialog" id="image">
							        <div class="modal-content" style="width:550px;height:660px">
							         <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >
							           &times;
							          </button>
							          <h4>IMAGE</h4>
							          <div>
							          <img  id="rl2" src="./resources/assets/img/RotateLeft.jpg" onclick="rotateLeft('0');" style="margin-left:200px;" />&nbsp;&nbsp;&nbsp;&nbsp;
							         <img  id="rr1" src="./resources/assets/img/RotateRight.jpg" onclick="rotateRight('0');"/>
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
														    <c:forEach var="element" items="${sdoCodeSearch}">
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
													     <button id="apprd" class="btn blue pull-right" onclick="return showApproved(1)">Show Approved</button>
													     <button id="hold" class="btn blue pull-right" onclick="return showApproved(0)">Show Hold</button>
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
      
							
		</div>
	</div>
			
								<div class="portlet-body">
			 		<button id="buttonshow" class="btn green pull-center" onclick="validateSearchFilterChange();" style="display: none;margin-left: 10px;">Back</button>
							<div id="map-canvas" class="gmaps" style="height: 500px;"></div>				 
               </div>
					
	<!-- END PAGE CONTENT -->
	
	
</div>

<style type="text/css">
#map-canvas {
        width: 900px;
        height: 400px;
      }
</style>



