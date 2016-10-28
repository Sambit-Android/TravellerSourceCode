<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
p.padding 
{
    height:0px;
    padding-left:365px;
}
</style>
<script type="text/javascript">	
   	    $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	     TableEditable.init();
   	    	  $('#admin-location').addClass('start active ,selected');
			  $('#dash,#admindash,meterData-Acquisition').removeClass('start active ,selected');					
   	    	    
			  $('#addData').click(function()
   	    	             {   
		   	    	    	  locType='0';
				              document.getElementById('locationManagement').reset();
				              $('#sitecode').attr('readonly',false);
				              $('#locationtype').attr('disabled',false);
				              $("#level").attr("disabled",false);
				              $('#level1').show();
		   	    	    	  $('#level').val('10');
		   	    	    	  $('#locationname1,#sitecode1,#locationType1').hide();
		   	    	    	  $("#company").attr("disabled",true);
		   	    	    	  $("#zone").attr("disabled",true);
			   	    	    	$("#level").removeClass("error");
				    			$("#levelText").text("");
				    			$("#levelText").removeClass("error");
		   	    	    	  $('#selectContainer1,#selectContainer2,#selectContainer3').html('');   	    	    	
		   	    	    	  
		   	    	    	// $('#locationtype,#level').val('0');
		   	    	    	//$('#locationType1,#level1').show();	
		   	    	    	  document.getElementById('updateOption').style.display='none';
		 					  document.getElementById('addOption').style.display='block'; 
   	    	             }); 
   	    	     $("#print").click(function(){ 
   	    	    	 printcontent($(".table-scrollable").html());
   				});   	    	     
   	    	    }); 
   	    
   		 var locType=""; 
   		 var locCat="";
   		 var i=0;
         var  maxLevel="";  
         
         
         
         
        function chooseLocatioType(param)
         {  
        	 /* $('#selectContainer2,#selectContainer3,#locationname').html(''); */
        	 $('#selectContainer1,#selectContainer2,#selectContainer3,#locationname').show();
        	 //$('#locationname1').hide();
        	 //$('#level').val('10');   
        	 locType=param;    
         } 
        
         function chooseLevel(param)
         {         	 
        	 if(param==10)
        		 {
	        		    $('#sitecode1').hide();
	        		    $('#locationname1').hide();
	        		    $('#locationType1').hide();
	        		    $('#selectContainer1,#selectContainer2,#selectContainer3').html('');
	        		    $("#level").addClass("error"); 
			    		$("#levelText").text("Please select the level.");
			    		$("#levelText").addClass("error");
		 	    	    return false; 
        		 } 
        	  else 
        		 {
        		        
        		 		$("#level").removeClass("error");
		    			$("#levelText").text("");
		    			$("#levelText").removeClass("error");
		    			$("#sitecode").removeClass("error");
		    			$('#siteCdText').text("");
	    	    	    $('#siteCdText').removeClass("error");
	    	    	    $("#locationname").removeClass("error");
	     	    	 	$("#locNametext").text("");
	     	    	 	$("#locNametext").removeClass("error");
        		 } 
        	     i=2;
        	 $('#selectContainer1,#selectContainer2,#selectContainer3,#selectContainer4,#selectContainer5').html('');
        	 $('#locationname1').hide();
        	 for (var x = 1; x <='${levelSize}'; x++)
        	 {
        		 if(param==x)
         		 {       
    	     		 $('#sitecode').val('');
    	     		 $('#locationname').val('');
    	     		 $('#locationname1,#sitecode1,#locationType1').show();
    	     		 
    	     		$.ajax({
     				    type : "GET",
     				    url : "./getLocationTypes/"+param,
     				    dataType : "json",
     				    cache:false,
     				    async:false,
     				    success : function(response)
     		    		  {
     				    	for (var i = 0; i < response.length; i++) 
     				    	{
     				    		if(i==(x-1))
     				    			{ 
     				    			  $('#locationtype').val('');
     				    			  $('#locationtype').val(response[i]);
     				    			} 
    						}
     		    		  }
            		 });
    	     		 
    	     		 $("#locationtype").attr("disabled",true);
    	     		 document.forms[0].codeVal.value=10;
    	     		 var operation=locType;       		         		
    	   		     var level=param;
    	   		     maxLevel=param;      		 
    	   		     ajaxCall(operation,level,10);  
         		 }
        		
			 }
        	  /* if(param==1)
        		 {        	
        		  
        		 $('#sitecode').val('');
        		 //$('#locationname').val('');
        		 $('#locationname1,#sitecode1,#locationType1').show();
        		 $("#locationtype").attr("disabled",true);
        		 
        		 $.ajax({
 				    type : "GET",
 				    url : "./getLocationTypes/"+param,
 				    dataType : "json",
 				    cache:false,
 				    success : function(response )
 		    		  {
 				    	
 				    	for (var i = 0; i < response.length; i++) 
 				    	{
 				    		if(i==0)
 				    			{
 				    			  $('#locationtype').val(response[i]);
 				    			}
						}
 		    		  }
        		 });
        		 document.forms[0].codeVal.value=10;
        		 }
        	  if(param==2)
     		 {        		
	     		 $('#sitecode').val('');
	     		 $('#locationname').val('');
	     		 $('#locationname1,#sitecode1,#locationType1').show();
	     		 
	     		$.ajax({
 				    type : "GET",
 				    url : "./getLocationTypes/"+param,
 				    dataType : "json",
 				    cache:false,
 				    success : function(response )
 		    		  {
 				    	
 				    	for (var i = 0; i < response.length; i++) 
 				    	{
 				    		if(i==1)
 				    			{
 				    			  $('#locationtype').val(response[i]);
 				    			}
						}
 		    		  }
        		 });
	     		 
	     		 $("#locationtype").attr("disabled",true);
	     		 document.forms[0].codeVal.value=10;
	     		 var operation=locType;       		         		
	   		     var level=param;
	   		     maxLevel=param;      		 
	   		     ajaxCall(operation,level,10);  
     		 }
        	  if(param==3)
      		 {        		
 	     		 $('#sitecode').val('');
 	     		 $('#locationname').val('');
 	     		 $('#locationname1,#sitecode1,#locationType1').show();
 	     		$.ajax({
 				    type : "GET",
 				    url : "./getLocationTypes/"+param,
 				    dataType : "json",
 				    cache:false,
 				    success : function(response )
 		    		  {
 				    	
 				    	for (var i = 0; i < response.length; i++) 
 				    	{
 				    		if(i==2)
 				    			{
 				    			  $('#locationtype').val(response[i]);
 				    			}
 				    		
						}
 		    		  }
        		 });
 	     		 $("#locationtype").attr("disabled",true);
 	     		 document.forms[0].codeVal.value=10;
 	     		 var operation=locType;       		         		
 	   		     var level=param;
 	   		     maxLevel=param;      		 
 	   		     ajaxCall(operation,level,10);  
      		 }
        	  if(param==4)
      		 {        		
 	     		 $('#sitecode').val('');
 	     		 $('#locationname').val('');
 	     		 $('#locationname1,#sitecode1,#locationType1').show();
 	     		$.ajax({
 				    type : "GET",
 				    url : "./getLocationTypes/"+param,
 				    dataType : "json",
 				    cache:false,
 				    success : function(response )
 		    		  {
 				    	
 				    	for (var i = 0; i < response.length; i++) 
 				    	{
 				    		if(i==3)
 				    			{
 				    			  $('#locationtype').val(response[i]);
 				    			}
 				    		
						}
 		    		  }
        		 });
 	     		 $("#locationtype").attr("disabled",true);
 	     		 document.forms[0].codeVal.value=10;
 	     		 var operation=locType;       		         		
 	   		     var level=param;
 	   		     maxLevel=param;      		 
 	   		     ajaxCall(operation,level,10);  
      		 } */
         } 
         
        
         function ajaxCall(operation,level,digits)
         { 
        	
        	if(i<=maxLevel)
           {
        	 $.ajax({
				    type : "GET",
				    url : "./getAllLocationNames/"+operation+"/"+i+"/"+digits,
				    dataType : "json",
				    cache:false,
				    success : function(response )
		    		  {
		    				  if(response=='' || response==null)
				    	{
				    		var q=i-1;
				    		bootbox.alert('There is no location under level    '+"<font color='red'>"+q+"</font>"+'   Please first add location for level    '+"<font color='red'>"+q+"</font>");	
				    		if(i>2)
				    			{
				    			i--;
				    			}				    		
				    	}
				    	else
				    		{
				    		var p=i-1;
				    	var html = '<td>Level '+p+' </td><td><font color="red">*</font></td> <td><select class=form-control placeholder-no-fix id=abc'+p+' onchange=digitCodes(this.value)><option value=0>Select</option>';
				    	
				    	for(var j=0;j<response.length;j++)
				    		{
				          html += '<option value='+response[j].locationcode+'>'+response[j].locationname+'</option>';
				    		}
				    	html+='...</select></td> <td><font color="red"><span id="level'+p+'Text"></span></font></td>';
				        $('#selectContainer'+p).append(html);
				    		}				    	
				    },
				    error : function(response)
				    {
				    	window.location.href="./logout";
				    }
				   
			});
        	 
          }
        	else
			 {	
        	 i=2;
			 $('#locationname1').show(); 
			 $('#locationType1').show(); 
			 //$('#sitecode').val('');
			 $('#sitecode1').show(); 
			 }
        	
         }         
          function digitCodes(param)
         {    
        	  if($('#abc1').val()!='0')
     		 {
        		$('#abc1').removeClass("error");
               	$('#level1Text').text("");
               	$('#level1Text').removeClass("error");
     		 }
        	  if($('#abc2').val()!='0')
      		 {
         			$('#abc2').removeClass("error");
                	$('#level2Text').text("");
                	$('#level2Text').removeClass("error");
      		 }
        	  if($('#abc3').val()!='0')
      		 {
         			$('#abc3').removeClass("error");
                	$('#level3Text').text("");
                	$('#level3Text').removeClass("error");
      		 }
        	 var k=i-1;
        	 document.forms[0].codeVal.value=param; 
        	 document.getElementById("abc"+k).disabled=true;
    		 i++;    		 
        	 ajaxCall(locType,param,param);       	 
         }         
          var sitecodeValue;
       function edit(param,opera)
		{         	  
    	   
    	   		$('#locationType1').show(); 
    	   		$("#level").removeClass("error");
				$("#levelText").text("");
				$("#levelText").removeClass("error");
    	     	$("#sitecode").removeClass("error");
			  	$('#siteCdText').text("");
			  	$('#siteCdText').removeClass("error"); 
    	       	$("#locationname").removeClass("error");
	    	 	$("#locNametext").text("");
	    	 	$("#locNametext").removeClass("error");
           	   $('#sitecode1').show();
        	   $('#selectContainer1,#selectContainer2,#selectContainer3').html(''); 
        	   $('#locationname1').show();  
        	   $('#sitecode').attr('readonly',true);
	    	  // var operation=parseInt(opera);
	    	$.ajax({
			    type : "GET",
			    url : "./editLocationManagement/" + opera,
			    dataType : "json",
			    cache:false,
			    success : function(response )
	    		  {	    	
			    	  $('#locationtype').attr('disabled',true);
		    	      $("#locationtype").val(response.locationtype);
			    	  //$('#sitecode').attr('readonly',true);
			    	  $("#company").attr("disabled",true);
	        		  $("#zone").attr("disabled",true);
	        		  $("#company").val(response.company);
	        		  $("#zone").val(response.zone); 
	        		  $("#level").attr("disabled",true);
	        		  $("#level").val(response.level); 
			    	  $("#locationcode").val(response.locationcode);
			    	  $("#locationname").val(response.locationname);
			    	  //document.getElementById("locationtype").value=response.locationtype;
			    	  $("#sitecode").val(response.sitecode);
			    	   sitecodeValue=response.sitecode;
			    	  //document.getElementById("level").value=response.level;			    	
			    	  //$('#locationType1').hide();
					  document.getElementById('updateOption').style.display='block';
					  document.getElementById('addOption').style.display='none';
			    },
			    error : function(response)
			    {
			    	window.location.href="./logout";
			    }
	    		  
		});
	    	
	     $('#'+param).attr("data-toggle", "modal");
		 $('#'+param).attr("data-target","#stack1");
	} 
       
         
     //VALIDATIONS  
       var letters = /^([a-zA-Z0-9\s\.\-])+$/;
       var numbers = /^[0-9]+$/;
       function validations(param)
          {        
        		  
        		  var locName=$("#locationname").val().trim();
        		  var siteCd = $("#sitecode").val().trim();
        		  var levelVal = $("#level").val().trim();
        		  
        		  if(levelVal=='10')
   	    		   {
        			  $("#level").addClass("error"); // adding css error class to the control
	   	    		  $("#levelText").text("Please select the level.");//filling up error message
	   	    		  $("#levelText").addClass("error");//add error class to info span
	  	 	    	  return false; 
   	    		   }
        		 
        		  if($('#abc1').val()=='0')
  				{
  				  $('#abc1').addClass("error"); // adding css error class to the control
  	 	    	  $('#level1Text').text('Please select the level 1 location.');//filling up error message
  	 	    	  $('#level1Text').addClass("error");//add error class to info span
  	 	    	  return false;
  				}
        		  if($('#abc2').val()=='0')
  				{
        			  $('#abc1').removeClass("error");
	  	              $('#level1Text').text("");
	  	              $('#level1Text').removeClass("error");
	  				  $('#abc2').addClass("error"); // adding css error class to the control
	  	 	    	  $('#level2Text').text('Please select the level 2 location.');//filling up error message
	  	 	    	  $('#level2Text').addClass("error");//add error class to info span
	  	 	    	  return false;
  				}
        		  
        		  if($('#abc3').val()=='0')
    				{
        			  $('#abc2').removeClass("error");
	  	              $('#level2Text').text("");
	  	              $('#level2Text').removeClass("error");
    				  $('#abc3').addClass("error"); // adding css error class to the control
    	 	    	  $('#level3Text').text('Please select the level 3 location.');//filling up error message
    	 	    	  $('#level3Text').addClass("error");//add error class to info span
    	 	    	  return false;
    				}
        		  
        		  if(locName=='' )
  				  {
	        			  $("#sitecode").removeClass("error");
						  $('#siteCdText').text("");
						  $('#siteCdText').removeClass("error"); 
	        			$('#abc1').removeClass("error");
	  	            	$('#level1Text').text("");
	  	            	$('#level1Text').removeClass("error");
	  	            	$('#abc2').removeClass("error");
		  	            $('#level2Text').text("");
		  	            $('#level2Text').removeClass("error");
			  	        $('#abc3').removeClass("error");
		  	            $('#level3Text').text("");
		  	            $('#level3Text').removeClass("error");
  		    		    $("#locationname").addClass("error"); // adding css error class to the control
  		 	    	    $("#locNametext").text("Location name cannot be empty.");//filling up error message
  		 	    	    $("#locNametext").addClass("error");//add error class to info span
  		 	    	    return false; 
  				  }
        		  else if(!letters.test(locName))
					{
        			    $('#abc1').removeClass("error");
	  	            	$('#level1Text').text("");
	  	            	$('#level1Text').removeClass("error");
	  	            	$('#abc2').removeClass("error");
		  	            $('#level2Text').text("");
		  	            $('#level2Text').removeClass("error");
			  	        $('#abc3').removeClass("error");
		  	            $('#level3Text').text("");
		  	            $('#level3Text').removeClass("error");
        			      $("#sitecode").removeClass("error");
    					  $('#siteCdText').text("");
    					  $('#siteCdText').removeClass("error"); 
        			      $("#locationname").addClass("error"); // adding css error class to the control
			 	    	  $("#locNametext").text("Special characters are not allowed.");//filling up error message
			 	    	  $("#locNametext").addClass("error");//add error class to info span
			 	    	  return false;
			 	    	 
					}
        		  else if(locName!='')
      			{
        			  var validateLocName=null;
        			  
        			  if(param==1)
        				  {
        				 
        				  /* $.ajax(
  		         				{
  		         						type : "GET",
  		         						url : "./valLocationName/" + locName+"/"+levelVal,
  		         						dataType : "text",
  		         						cache:false,
  		         						async:false,
  		         						success : function(response)
  		         									{	  
  					         							validateLocName=response;
  		         									}							
  		         				}
  		         		    ); */
  		         		 
  		         		  if(levelVal==1||levelVal==2||levelVal==3||levelVal==4)
  		         			  {
  		         			   var levels=null;
  		         			   var locCode=0;
  		         				  if(levelVal==1)
  		         					  {
  		         					      levels=levelVal;
  		         					  }
  		         				  else
  		         					  {
  		         					      levels=levelVal-1;
  		         					      locCode=$('#abc'+levels).val().trim();
  		         					  }
  		         			  $.ajax(
  		         						{
  		         								type : "GET",
  		         								url : "./valLocationName/" + locName+"/"+locCode+"/"+levelVal,
  		         								dataType : "text",
  		         								cache:false,
  		         								async:false,
  		         								success : function(response)
  		         											{	        	
  		         												validateLocName=response.trim();
  		         											}							
  		         						}
  		         				    );
  		         			  }
  		         		if(validateLocName!='')
  		  			  {
  		      			   $("#locationname").addClass("error"); 
  				 	    	   $("#locNametext").text("Location "+"  "+validateLocName+"  "+"already exists in level "+levelVal);
  				 	    	  //$("#locNametext").text("Location "+"  "+validateLocName+"  "+"already exists.");
  				 	    	   $("#locNametext").addClass("error");
  				 	    	   return false;
  		  			  }
        				  }
      			
 }
        		  
        		  else
	  				{
	  					$("#locationname").removeClass("error");
	       	    	 	$("#locNametext").text("");
	       	    	 	$("#locNametext").removeClass("error");
	  				}
        		  
        		  if( siteCd== '' )
  				  {
        			  $("#locationname").removeClass("error");
      	    	 	$("#locNametext").text("");
      	    	 	$("#locNametext").removeClass("error");
        			   $('#abc1').removeClass("error");
	  	              $('#level1Text').text("");
	  	              $('#level1Text').removeClass("error");
	  	              $('#abc2').removeClass("error");
	  	              $('#level2Text').text("");
	  	              $('#level2Text').removeClass("error");
        			  $("#sitecode").addClass("error"); // adding css error class to the control
        			  $('#siteCdText').text("Sitecode cannot be empty.");//filling up error message
  		    		  $('#siteCdText').addClass("error");//add error class to info span 
  		 	    	  return false; 
  		 	    	
  				  }
        		  else if(!numbers.test(siteCd))
    			  {
        			  $("#sitecode").addClass("error"); // adding css error class to the control
        			  $('#siteCdText').text("Please enter only numeric values.");//filling up error message
        			  $('#siteCdText').addClass("error");//add error class to info span
    	    		  return false;
    			  } 
        		  
        		  else if($('#level').val()=='4')
        			  {
        			    var siteCODE=$('#sitecode').val().trim();
        			   var level4Val= $('#level').val();
        			    var siteCodeVal='';
        			    if(sitecodeValue!=$('#sitecode').val().trim())
			    		{
        			    /* if(param==1)
      				  { */
        			    	
        			  $.ajax(
		         				{
		         						type : "GET",
		         						url : "./valSiteCode/" + siteCODE+"/"+level4Val,
		         						dataType : "text",
		         						cache:false,
		         						async:false,
		         						success : function(response)
		         									{	  
		         							          siteCodeVal=response;
		         							          
		         									}							
		         				}
		         		    );
      				  
        			  if(siteCodeVal.trim()!='0')
          			  {
        				  		$("#locationname").removeClass("error");
            	    	 		$("#locNametext").text("");
            	    	 		$("#locNametext").removeClass("error");
              			       $("#sitecode").addClass("error"); 
        		 	    	   $("#siteCdText").text("Sitecode "+"  "+siteCodeVal+"  "+"already exists.");
        		 	    	   $("#siteCdText").addClass("error");
        		 	    	   return false;
          			  }
        			 /*  } */
			    		}
        			  }
        		  
  				else
  				{
  					$("#sitecode").removeClass("error");
  					$('#siteCdText').text("");
  					$('#siteCdText').removeClass("error"); 
  				}
        		  
        		  /* if(levelVal=='1'||levelVal=='2'||levelVal=='3'||levelVal=='4')
					{
						  if (siteCd.length != levelVal||siteCd.length != levelVal||siteCd.length != levelVal||siteCd.length != levelVal)
	        	    	  {
							  $("#sitecode").addClass("error"); // adding css error class to the control
							  $('#siteCdText').text("Sitecode should be "+levelVal+" digit number for level"+"\t"+levelVal+".");
							  $('#siteCdText').addClass("error");//add error class to info span
							  $("#locationname").removeClass("error");
			       	    	 	$("#locNametext").text("");
			       	    	 	$("#locNametext").removeClass("error");
							  return false;
	        	    	  }
				    } */

				    for (var m=1; m <='${levelSize}'; m++) 
				    {
				    	if(levelVal==m)
						{
							  if (siteCd.length != levelVal||siteCd.length != levelVal||siteCd.length != levelVal||siteCd.length != levelVal)
		        	    	  {
								  $("#sitecode").addClass("error"); // adding css error class to the control
								  $('#siteCdText').text("Sitecode should be "+levelVal+" digit number for level"+"\t"+levelVal+".");
								  $('#siteCdText').addClass("error");//add error class to info span
								  $("#locationname").removeClass("error");
				       	    	 	$("#locNametext").text("");
				       	    	 	$("#locNametext").removeClass("error");
								  return false;
		        	    	  }
					    }
					}
        	  
        		  
        		  if(param==1)
        		  {
            		  
            		  $('#company').removeAttr('disabled');
            		  $('#zone').removeAttr('disabled');
            		  $("#locationtype").attr("disabled",false);
            		  $('#locationManagement').attr('action',"./addLocationData");
        		  }
            	  else 
            		  {        	
            		     $("#level").attr("disabled",false);
            		     $('#company').removeAttr('disabled');
               		     $('#zone').removeAttr('disabled');
               		  $('#locationtype').removeAttr('disabled');
            		     $('#locationManagement').attr('action',"./updateLocationData");
            		  }
          } 
        		
        	  
          
          
           
        function deleteRecord(opera,level,locName)
       	{         	
        	  
        	
        	for (var i = 1; i <='${levelSize}'-1; i++) 
        	{
        		
        		if(level==i)
      		  {
      		  		bootbox.confirm("You are to deleting level "+"<font color='red'>"+level+"</font>"+" location. It will delete all locations under "+" \t "+"<font color='red'>"+locName+"</font>"+"?", function(confirmed)
      		  
     	   
     	{
     	        if(confirmed==true)
     	        {
     	        	var operation=parseInt(opera);
     		    	$.ajax({
     				    type : "GET",
     				    url : "./deleteLocation/" + operation,
     				    dataType : "json",
     				    cache:false,
     				    success : function(response )
     		    		  {	    	
     				    	if(response=='deleted')
     				    	{
     				    		window.location.href="./adminLocation?flag=0";
     				    	}
     				    	else if(response=='notDeleted')
     				    	{
     				    		window.location.href="./adminLocation?flag=1";
     				    	}
     				    	else
     				    	{
     				    		window.location.href="./adminLocation";
     				    	}
     				    	
     				      }
     			});
     	        }
     	        
     	 });
      		  }
        		
			}
        	if(level=='4')
 			  {
 			  bootbox.confirm("Are you sure to delete "+"<font color='red'>"+locName+"</font>"+" location of level"+"\t"+"<font color='red'>"+level+"</font>"+"?", function(confirmed)
 					  {
 	       	        if(confirmed==true)
 	       	        {
 	       	        	var operation=parseInt(opera);
 	       		    	$.ajax({
 	       				    type : "GET",
 	       				    url : "./deleteLocation/" + operation,
 	       				    dataType : "json",
 	       				    cache:false,
 	       				    success : function(response )
 	       		    		  {	    	
 	       				    	if(response=='deleted')
 	       				    	{
 	       				    		window.location.href="./adminLocation?flag=0";
 	       				    	}
 	       				    	else if(response=='notDeleted')
 	       				    	{
 	       				    		window.location.href="./adminLocation?flag=1";
 	       				    	}
 	       				    	else
 	       				    	{
 	       				    		window.location.href="./adminLocation";
 	       				    	}
 	       				    	
 	       				      }
 	       			});
 	       	        }
 	       	        
 	       	 });
 			  }
       	}
        	
</script>



	<div class="page-content" >
		<%@include file="pagebreadcrum.jsp"%>
		<div class="row">
			<div class="col-md-12">
					<c:if test = "${results ne 'notDisplay'}"> 			        
			         <div class="alert alert-danger display-show">
							<button class="close" data-close="alert"></button>
							<span style="color:red" >${results}</span>
						</div>
			        </c:if>	
				<!-- BEGIN PORTLET-->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-edit"></i>Location management
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
							<a href="javascript:;"
								class="remove"></a>
						</div>
					</div>
					<div class="portlet-body">

						<div class="table-toolbar">
							<div class="btn-group">
								<button class="btn green" data-target="#stack1"
									data-toggle="modal" id="addData">
									Add Location <i class="fa fa-plus"></i>
								</button>
								<!-- <a class="btn green" data-target="#stack1" data-toggle="modal"  style="color: green" id="addData">Add New Department &nbsp;<i class="fa fa-plus"></i></a> -->
							</div>
							<div class="btn-group pull-right">
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li><a href="#" id="print">Print</a></li>
									<li><a href="#" id="excelExport"
										onclick="tableToExcel('sample_editable_1', 'Project Management Table')">Export
											to Excel</a></li>
								</ul>
							</div>
						</div>


						<a href="#" id="editbutton"></a>

						<table class="table table-striped table-hover table-bordered"
							id="sample_editable_1">
							<thead>
								<tr>
								   <th>Company</th>
									<th>Zone</th>
									<th>Location Type</th>																
									<th>Location Name</th>
									<th>Sitecode</th>
									<th>Location Code</th>
									<th>Level</th>
									<th>Edit</th>
									<th>Delete</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="element" items="${locationList}">
									<tr>
									    <td>${fn:toUpperCase(element.company)}</td>
										<td>${fn:toUpperCase(element.zone)}</td>
										<td>${element.locationtype}</td>
										<td>${element.locationname}</td>
										<td>${element.sitecode}</td>
										<td>${element.locationcode}</td>
										<td>${element.level}</td>
										<td><a href="#" onclick="edit(this.id,${element.sitecode})" id="editData${element.locationcode}">Edit</a></td>
									    <%-- <td><a  href="#" onclick="deleteRecord(this.id,${element.locationcode},${element.level})" id="deleteData${element.locationcode}">Delete</a></td> --%>
									    <td><a href="#" onclick="deleteRecord(${element.locationcode},${element.level},'${element.locationname}')" id="deleteData">Delete</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

						<div id="stack1" class="modal fade" tabindex="-1" data-width="400" >
							<div class="modal-dialog" style="width: 650px">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true"></button>
										<h4 class="modal-title">Add Location Details</h4>
										<p class="padding">'<font color="red">*</font>'&nbsp;Indicates mandatory fields.</p>
									</div>

									<div class="modal-body" id='mainDiv' >
										<div class="row">
											<div class="col-md-12">
												<form:form action="" modelAttribute="locationManagement"
													commandName="locationManagement" method="post"
													id="locationManagement">
													<table>
														<tr hidden="true" id="locationcode1">
															<td>Location Id</td>
															<td><form:input path="locationcode"
																	id="locationcode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationcode"></form:input></td>
														</tr>
														
														
														<tr id="company1">
															<td>Company</td>
															<td><font color="red">*</font></td>
															<td><form:select path="company"
																	id="company"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="company" >
																	<%-- <form:option value="0">select</form:option> --%>
																	<c:forEach items="${companyList}" var="companyVal">
																		<form:option value="${companyVal}"
																			>${companyVal}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="zone1">
															<td>Zone</td>
															<td><font color="red">*</font></td>
															<td><form:select path="zone"
																	id="zone"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="zone" >
																	<%-- <form:option value="0">select</form:option> --%>
																	<c:forEach items="${zoneList}" var="zoneVal">
																		<form:option value="${zoneVal}"
																			>${zoneVal}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="level1">
															<td>Level</td>
															<td><font color="red">*</font></td>
															<td><form:select path="level" id="level"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="level" onchange="chooseLevel(this.value)">
																	<form:option value="10">select</form:option>
																	<c:forEach items="${LocationLevel}" var="locLevel">
																		<form:option value="${locLevel}"
																			>${locLevel}</form:option>
																	</c:forEach>
																</form:select></td>
																<td><font color="red"><span id="levelText"></span></font></td>
														</tr>
														
														
														
														<tr id="selectContainer1">
														
														</tr>
														<tr id="selectContainer2">
														
														</tr>
														<tr id="selectContainer3">
														
														</tr>
														
														<tr id="locationType1">
															<td>Location Type</td>
															<td><font color="red">*</font></td>
															<td><form:select path="locationtype"
																	id="locationtype"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationtype" onchange="chooseLocatioType(this.value);">
																	<form:option value="0">select</form:option>
																	<c:forEach items="${LocationTypes}" var="LocationTypeVal">
																		<form:option value="${LocationTypeVal}"
																			>${LocationTypeVal}</form:option>
																	</c:forEach>
																</form:select></td>
														</tr>
														
														<tr id="locationname1">
															<td>Location Name</td>
															<td><font color="red">*</font></td>
															<td><form:input path="locationname"
																	id="locationname"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="locationname"  ></form:input></td>
															<td><font color="red"><span id="locNametext"></span></font></td>
														</tr>
														
														<tr id="sitecode1">
															<td>SiteCode</td>
															<td><font color="red">*</font></td>
															<td><form:input path="sitecode"
																	id="sitecode"
																	class="form-control placeholder-no-fix" type="text"
																	autocomplete="off" placeholder="" name="sitecode" ></form:input></td>
														   <td><font color="red"><span id="siteCdText"></span></font></td>
														</tr>
														
														<tr>
														<div>
															<input type="hidden" name="codeVal" value="" />
														</div>
													</table>
													 <div class="modal-footer">
														<button type="button" data-dismiss="modal" class="btn"
															id="closeData" >Close</button>
														<button class="btn blue pull-right"
															style="display: block;" id="updateOption"															
															onclick="return validations('0');">Update Location
															</button>
														<button class="btn blue pull-right"
															style="display: block;" id="addOption"															
															onclick="return validations('1');">ADD Location
															</button>
													</div> 

												</form:form>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END PORTLET-->
				</div>
				</div>
				</div>
				