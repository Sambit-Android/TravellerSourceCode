<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%if(session.getAttribute("userType")==null || session.getAttribute("username")==null){ %>
		<script>
		window.location.href="./?sessionVal=expired";
		</script>
		<%} %>
<script>	
function loadPage()
			{		
				var user='<%=(String)session.getAttribute("userType") %>';			
				if(user=='admin' || user=='ADMIN')
				{	
					window.location.href="./adminDashboard";
				}
				else
				{			
					window.location.href="./userDashboard";
				}
			}

		 
		 
	 function dateValidation(objFromDate,objToDate)
	    {   
	  	   objFromDate = objFromDate.trim().split("-");
	  	   objToDate = objToDate.trim().split("-");
	  	   
	  	  if(parseInt(objFromDate[2],10)<=parseInt(objToDate[2],10))
	  		{		
	  		   if(parseInt(objFromDate[2],10)==parseInt(objToDate[2],10))
	  			   {			  
	  			   if(parseInt(objFromDate[1],10)<=parseInt(objToDate[1],10))
	  				   {				   
	  				   if(parseInt(objFromDate[1],10)==parseInt(objToDate[1],10))
	  				   {
	  				   if(parseInt(objFromDate[0],10)>parseInt(objToDate[0],10))
	  				   { 					 
	  				     return false;
	  				   }
	  				   }
	  				   }
	  			   else
	  				   {				  
	  				   return false;
	  				   }
	  			   }
	  		   }
	  	else
	  		{		
	  		return false;
	  		}
	    }
	 
	 function dateFormating(param)
	    {
	    	if(param==null || param=='')
	    		{
	    		return "-";
	    		}
	  	  var date11= new Date(parseInt(param));
	  	  var month=date11.getMonth()+1;
	  	  var datee=date11.getDate();
	  	  if(month<10)
	  		  {
	  		  month="0"+month;
	  		  }
	  	  if(datee<10)
	  		  {
	  		  datee="0"+datee;
	  		  }
	  	  date11=datee+"-"+month+"-"+date11.getFullYear();
	  	  return date11;
	    }
	 
	 function dateTimeFormating(param)
	    {
	    	if(param==null || param=='')
	    		{
	    		return "";
	    		}
	  	  var date11= new Date(parseInt(param));
	  	  var month=date11.getMonth()+1;
	  	  var datee=date11.getDate();
	  	  var hr=date11.getHours();
	  	  var min=date11.getMinutes();
	  	  var sec=date11.getSeconds();
	  	  if(month<10)
	  		  {
	  		  month="0"+month;
	  		  }
	  	  if(datee<10)
	  		  {
	  		  datee="0"+datee;
	  		  }
	  	  if(hr<10)
	  		  {
	  		  hr="0"+hr;
	  		  }
	  	  if(min<10)
	  		  {
	  		  min="0"+min;
	  		  }
	  	  if(sec<10)
	  		  {
	  		  sec="0"+sec;
	  		  }
	  	  date11=datee+"-"+month+"-"+date11.getFullYear()+" "+hr+" : "+min+" : "+sec;
	  	  return date11;
	    }
	 function dateTimeFormating1(param)
	    {
	    	if(param==null || param=='')
	    		{
	    		return "";
	    		}
	  	  var date11= new Date(parseInt(param));
	  	  var month=date11.getMonth()+1;
	  	  var datee=date11.getDate();
	  	  var hr=date11.getHours();
	  	  var min=date11.getMinutes();
	  	  var sec=date11.getSeconds();
	  	  if(month<10)
	  		  {
	  		  month="0"+month;
	  		  }
	  	  if(datee<10)
	  		  {
	  		  datee="0"+datee;
	  		  }
	  	  if(hr<10)
	  		  {
	  		  hr="0"+hr;
	  		  }
	  	  if(min<10)
	  		  {
	  		  min="0"+min;
	  		  }
	  	  if(sec<10)
	  		  {
	  		  sec="0"+sec;
	  		  }
	  	  date11=date11.getFullYear()+"-"+month+"-"+datee+" "+hr+" : "+min+" : "+sec;
	  	  return date11;
	    }
	 
	 function dateTimeStampFormating(param)
	    {
	    	if(param==null || param=='')
	    		{
	    		return "";
	    		}
	  	  var date11= new Date(parseInt(param));
	  	  var month=date11.getMonth()+1;
	  	  var datee=date11.getDate();
	  	  var hr=date11.getHours();
	  	  var min=date11.getMinutes();
	  	  var sec=date11.getSeconds();
	  	var milliSec=date11.getMilliseconds();
	  	  if(month<10)
	  		  {
	  		  month="0"+month;
	  		  }
	  	  if(datee<10)
	  		  {
	  		  datee="0"+datee;
	  		  }
	  	  if(hr<10)
	  		  {
	  		  hr="0"+hr;
	  		  }
	  	  if(min<10)
	  		  {
	  		  min="0"+min;
	  		  }
	  	  if(sec<10)
	  		  {
	  		  sec="0"+sec;
	  		  }
	  	if(milliSec<10)
		  {
	  		milliSec="0"+milliSec;
		  }
	  	  date11=datee+"-"+month+"-"+date11.getFullYear()+" "+hr+" : "+min+" : "+sec+" : "+milliSec;
	  	  return date11;
	    }
	 
	function currentDateValue()
	{
		  var date11= new Date();
	  	  var month=date11.getMonth()+1;
	  	  var datee=date11.getDate();
	  	  if(month<10)
	  		  {
	  		  month="0"+month;
	  		  }
	  	  if(datee<10)
	  		  {
	  		  datee="0"+datee;
	  		  }
	  	  date11=datee+"-"+month+"-"+date11.getFullYear();
	  	  return date11;
	}
	
	function loadPageContent(param)
	{		
		var loadUrl = "./"+param;
		$("#pagecontainer").load(loadUrl);
	}
	
	function validateSdo()
	{
		var sdo=document.getElementById("sdoId").value.trim();
		if(sdo==""||sdo==null)
		{
           bootbox.alert("Please select the SDO CODE");
           return false;
		}
	} 
	
function onEnterKey(e)
 {
     var  keyPressed;
 	keyPressed = e.keyCode;
 if(keyPressed==13)  
 {
	document.getElementById("form1").submit();
 	
  }
}

var  rotation=0;
function viewDocument(consumerNo,sdoCode,billMonth)
{  
    $('#Imageview').empty(); 
    var url2="./imageDisplay/getImage/"+consumerNo+"/"+sdoCode+"/"+billMonth;
    rotation=0;
    rotateRight();
    rotateLeft();
    $('#Imageview').append("<img id=\"tempImg\" style=\"width:500px;height:500px;\" src='"+url2+"' title="+consumerNo+"/>");
    	
}

function rotateRight()
{
	rotation =rotation+90;
    var rotate = "rotate(" + rotation + "deg)";
     var trans = "all 0.3s ease-out"; 
    $(".rotatecontrol").css({
        "-webkit-transform": rotate,
        "-moz-transform": rotate,
        "-o-transform": rotate,
        "msTransform": rotate,
        "transform": rotate,
        "-webkit-transition": trans,
        "-moz-transition": trans,
        "-o-transition": trans,
        "transition": trans
    });
    
}


function rotateLeft()
{
	rotation =rotation-90;
    var rotate = "rotate(" + rotation + "deg)";
     var trans = "all 0.3s ease-out"; 
    $(".rotatecontrol").css({
        "-webkit-transform": rotate,
        "-moz-transform": rotate,
        "-o-transform": rotate,
        "msTransform": rotate,
        "transform": rotate,
        "-webkit-transition": trans,
        "-moz-transition": trans,
        "-o-transition": trans,
        "transition": trans
    });
}

function checkDeviceAllcated(id,operatorDevice)
{  
   var res="";
	$.ajax({
		type : "post",
		url : "./checkAllocated/"+operatorDevice+"/"+id,		
		dataType : "JSON",
		async:false,
		success : function(response) { 
			res=response
		}
	});
	return res;
}

function getStatusName(mtrRdg)
{
	mtrRdgSatus="";
	 if(mtrRdg==1)
	 {
	 mtrRdgSatus="Normal";
     }
	 if(mtrRdg==2)
	 {
	 mtrRdgSatus="Door Lock";
	 }
	 if(mtrRdg==3)
	 {
	 mtrRdgSatus= "Meter Not Working";//"Defective";
	 }
	 if(mtrRdg==4)
	 {
	 mtrRdgSatus="Direct Connection";//"Display out";
	 }
	 if(mtrRdg==5)
	 {
	 mtrRdgSatus="Disconnection";//"Area without supply";
	 }
	 if(mtrRdg==6)
	 {
	 mtrRdgSatus="Idle";//"Area without supply";
	 }
	 if(mtrRdg==7)
	 {
	 mtrRdgSatus="Meter Burnt";//"Area without supply";
	 }
	 if(mtrRdg==8)
	 {
	 mtrRdgSatus="Meter Sticky";//"Area without supply";
	 }
	 if(mtrRdg==9)
	 {
		 mtrRdgSatus="Not Visible";//"Area without supply";
	 }
	 return mtrRdgSatus;
}

function hideMap()
{
	$('#gmapsContent').hide();
}

/* function showGPS(form1Id)
{	
	  var str=$('#'+form1Id).serialize();	  
	  $.ajax(
				{
					type : "GET",
					url : "./getGPSBmdAllData",
					data:str,
					cache:false,
					async:false,
					beforeSend: function(){
				        $('#imageee').show();
				    },
				    complete: function(){
				        $('#imageee').hide();
				    },
				    success : function(response)
			  		  {					    	
				          var initialLocation=false;
				          var map="";
				          var path=new Array();
				          var point ="";
				    	  for(var i=0;i<response.length;i++)
							{ 
				    		   var labelName="";
				    		    if(i==0)
				    			  {
				    			  labelName="S";
				    			  }
				    		    if(i==response.length-1)
				    			  {
				    			  labelName="E";
				    			  } 
				    		  if(!initialLocation)
				    			  {				    			 
				    			  if(response[i].lattitude.trim()!=''  || response[i].longitude.trim()!='')
				    				  {					    				  
				    				     document.getElementById('gmapsContent').style.display='block';
						    			      map = new GMaps({
								              div: '#gmap_marker',
								              lng:parseFloat(response[i].longitude),
								              lat:parseFloat(response[i].lattitude),
								              zoom:500,
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
					              icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+labelName+'|FF8000|000000',
					              	  
					              title:"please click on marker to check details",
					              infoWindow: {
					                  content: 
					                  "<div style=width:250px;><table style='margin-left: 10px;margin-bottom: 10px;'><tr ><td><a href=#><img src=./imageDisplay/getImage/"+response[i].consumer_Sc_No.trim()+"/"+response[i].sdoCode+"/"+response[i].billMonth+" title="+response[i].consumer_Sc_No+" onclick=viewDocument('"+escape(response[i].consumer_Sc_No.trim())+"','"+response[i].sdoCode+"','"+response[i].billMonth+"'); data-toggle=modal  data-target='#popup_image' height=150 width=200/></a></td></tr></table>"
					                  +"<table  id=billTable style='margin-left: 10px;margin-bottom: 10px;'><tr><th>Mr Reader</th><td>"+response[i].bmd_Reading+"</td></tr>"
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
									  +"<tr><th>Meter Number    </th><td>"+response[i].meterNo+"</td></tr>"
									  +"<tr><th>Mtr Rndg type   </th><td>" 									 
									  +""+mtrRdgSatus+"</td></tr>" 
									  +" <tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
									  +"<tr><th>Lattitude</th><td>"+response[i].lattitude+"</td></tr>"
									  +" <tr><th>Taken Time </th><td>"+response[i].takenTime+"</td></tr>"
									  +"</table></div>"
					              }
					          });	
				    		      
				    		      point =new google.maps.LatLng(response[i].lattitude.trim(),response[i].longitude.trim());
				    		      path.push(point);
				    			 } 				    		  		    		  
							}
				    	  
				    	   for polyLines 
				    	   if(path.length>0)//if lat and long are available only
				    	   {
				    		   map.drawPolyline({
		  	        	            path: path,
		  	        	            strokeColor: 'red',
		  	        	            strokeOpacity: 0.6,
		  	        	            strokeWeight: 6
		  	        	        });   
				    	   }
				    	  
				    	  if(!initialLocation)
		    			  {
		    			  document.getElementById('gmapsContent').style.display='none';
		    			  bootbox.alert('no proper lattitude and langitude');
		    			  }
			    	}
				}		
		       );
	   return false;
} */

function showGPS(form1Id)
{	
	  var str=$('#'+form1Id).serialize();	  
	  $.ajax(
				{
					type : "GET",
					url : "./getGPSBmdAllData",
					data:str,
					cache:false,
					async:false,
					beforeSend: function(){
				        $('#imageee').show();
				        $('#gmapsContent').hide(); 
				    },
				    complete: function(){
				        $('#imageee').hide();
				        $('#gmapsContent').show();
				    },
				    success : function(response)
			  		  {					    	
				          var initialLocation=false;
				          var map="";
				    	  for(var i=0;i<response.length;i++)
							{ 
				    		  var count=i+1;
				    		  
				    		   /* var labelName="";
				    		    if(i==0)
				    			  {
				    			  labelName="S";
				    			  }
				    		    if(i==response.length-1)
				    			  {
				    			  labelName="E";
				    			  }  */
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
								              zoom:18,
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
					             // with count ==>
				    		     // icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+(count)+'|FF8000|000000',
					            
				    		      // without count ==>
				    		     // icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|FF8000|000000',
				    		    		 icon:'./resources/bsmart.lib.js/gmapMarker.png',
					             // icon: 'https://cdn2.iconfinder.com/data/icons/online-shopping-color/150/22-32.png',
					             // icon: 'https://cdn1.iconfinder.com/data/icons/unigrid-bluetone-finance-vol-3/60/014_140_bill_pay_payment_money_finance_invoice_receipt_receive-32.png',	  
				    		      
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
									  +" <tr><th>Longitude </th><td>"+response[i].longitude+"</td></tr>"
									  +"<tr><th>Lattitude</th><td>"+response[i].lattitude+"</td></tr>"
									  +" <tr><th>Taken Time </th><td>"+response[i].takenTime+"</td></tr>"
									  +"</table></div>"
					              }
					          });	
				    		      count=0;
				    			 } 				    		  		    		  
							}
				    	  
				    	  if(!initialLocation)
		    			  {
		    			  document.getElementById('gmapsContent').style.display='none';
		    			  bootbox.alert('no proper lattitude and langitude');
		    			  }
			    	}
				}		
		       );
	   return false;
}

//graphs and charts

function chartGraph(batteryData,signalData,memoryData,totalMemory,chartType) {
	
	 $('#batterySignal,#memory').empty();// clear two charts then display
	if(chartType=='signalBatery')
		{		
	 $('#batterySignal').highcharts({
	        chart: {
	            type: 'spline'
	        },
	        title: {
	            text: 'Battery And Signal Strength '
	        },
	        subtitle: {
	            //text: 'Irregular time data in Highcharts JS'
	        },
	        xAxis: {        	            
	            title: {
	                text: 'Time in Hrs'
	            },
	            type: 'datetime',
	            //min:0,
	            //max:24
	        },
	        yAxis: {
	            title: {
	                text: 'Battery/Signal in %'
	            },
	            lineWidth: 2,
	            min: 0,
	            max:100
	        },
	        tooltip: {
	            headerFormat: '<b>{series.name}</b><br>',
	            pointFormat: '{point.x:%e-%b-%Y %H:%M} <br/>{point.y} %'
	        },

	        series: [{
	            name: 'Battery',
	            // Define the data points. All series have a dummy year
	            // of 1970/71 in order to be compared on the same x axis. Note
	            // that in JavaScript, months start at 0 for January, 1 for February etc.
	            data:batteryData
	        }, {
	            name: 'Signal',
	            data: signalData
	        }]
	    });		
	}
	if(chartType=='memory')
	{	
	 $('#memory').highcharts({
        
       chart: {
        	type: 'spline'
        },
        title: {
            text: 'Memory space (Total memory'+totalMemory+')'
        },
       
        xAxis: {
        	title: {
                text: 'Time in Hrs'
            },
            type: 'datetime',
           //minRange:24 * 3600000 // fourteen days
        },
        yAxis: {
        	 title: {
                 text: 'Memory in MB'
             },
             lineWidth: 2,
        },
        legend: {
            enabled: false
        },
        tooltip: {
        	formatter: function() {
                return  this.series.name +'<br/>' +
                    Highcharts.dateFormat('%e-%b-%Y %H:%M',
                                          new Date(this.x))
                 +'<br/>'+ this.y + '  MB';
            }            
        },
            
        series: [{
           
            name: 'memory',
            //pointInterval: 1 * 3600 * 1000,
            //pointStart: Date.UTC(2014,11,18),
            data: memoryData
        }]
        
      
        
    }); 
	}
	}
	
	
//end graphs and charts
function loadSearchAndFilter(param) 
		  	{ 
		  		$('#'+param).dataTable().fnDestroy(); 
		  		$('#'+param).dataTable(
		  				 { "aLengthMenu": [
				                    [20, 50, 100, -1],
				                    [20, 50, 100, "All"] // change per page values here
				                ],
				                "iDisplayLength": 50
			  }); 
		  		jQuery('#'+param+'_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input 
		  		jQuery('#'+param+'_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown 
		  		jQuery('#'+param+'_wrapper .dataTables_length select').select2(); // initialize select2 dropdown 
		  	}
		  	
var takeTimeVal1=[];
var Battery1=[];
var Signal1=[];
var MemoryAvl1=[];
var yValues=[];
var countBilled=null;
function getMobileHealth(param,mrName,sdoCode,date, section)
{
 $('#sdoId').text(section);
 $('#mrcodeId').text(mrName);
   Battery1.length=0;
	  Signal1.length=0;
	  MemoryAvl1.length=0;
	  takeTimeVal1.length=0;
	  //yValues.length=0;
  $.ajax(
			{
					type : "GET",
					url : "./getMobileHealth/"+mrName+"/"+sdoCode+'/'+date,
					dataType : "json",
					async:false,	
					cache:false,
					success : function(response)
						{
						countBilled=response.countBilled;
						 for(var i=0;i<response.takeTimeVal.length;i++)
							{
							 takeTimeVal1.push(response.takeTimeVal[i]);
							} 
						 for(var i=0;i<response.Battery.length;i++)
							{
							 Battery1.push(parseFloat(response.Battery[i]));
							}
						 for(var i=0;i<response.Signal.length;i++)
							{
							 Signal1.push(parseFloat(response.Signal[i]));
							}
						 for(var i=0;i<response.MemoryAvl.length;i++)
							{
							 MemoryAvl1.push(parseInt(response.MemoryAvl[i]));
							}
						 
						  /* for (var i=0; i<100; (i-1))
					    {
							  if(i==0)
							  {
								  yValues.push(i);
							  }
							  i=i+20;
							  yValues.push(i);
					    } */
						}
			});
  displayDeviceHealthGrapg(param,takeTimeVal1,Battery1,Signal1,MemoryAvl1,countBilled);
}
function displayDeviceHealthGrapg(param,takeTimeVal,Battery,Signal,MemoryAvl,countBilled)
{
	$('#batterySignal11').empty();
	var html2 = "<table class='table table-bordered table-hover'><thead><tr><th>Time</th><th>Battery(in %)</th><th>TotalBilled</th></tr></thead><tbody>";
    for(var j=0;j<countBilled.length;j++)
		{		
		     html2 += "<tr><td>"+countBilled[j][1]+"</td>";
		     html2 += "<td>"+countBilled[j][2]+"</td>";
		     html2 += "<td>"+countBilled[j][3]+"</a></td></tr>"; 
		}
	html2+="</tbody></table>";	
	$('#batterySignal11').html(html2);
	$('#batterySignal').empty();
	$('#memory').empty();
	$('#Signal').empty();
	
	$('#batterySignal').highcharts({
        title: {
            text: 'Battery Percentage',
            x: -20 //center
        },
        /* subtitle: {
            text: 'Source: WorldClimate.com',
            x: -20
        }, */
        xAxis: {
        	title: {
                text: 'Time in Hrs'
            },
            categories: takeTimeVal,
            tickWidth: 1,
            
        },
        yAxis: {
            title: {
                text: 'Battery (in %)'
            },
        },
        tooltip: {
        	formatter: function() {
                return  this.series.name +':'+ this.y + '%';
            }            
        },
        
        /* legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        } */
        series: [{
            name: 'Battery',
             color: '#009933',
            data: Battery1
        }]
    });
	
	$('#Signal').highcharts({
        title: {
            text: 'Signal Strength',
            x: -20 //center
        },
        /* subtitle: {
            text: 'Source: WorldClimate.com',
            x: -20
        }, */
        xAxis: {
        	title: {
                text: 'Time in Hrs'
            },
            categories: takeTimeVal,
            tickWidth: 1,
            
        },
        yAxis: {
            title: {
                text: 'Signal (in %)'
            },
        },
        tooltip: {
        	formatter: function() {
                return  this.series.name +':'+ this.y + '%';
            }            
        },
        
        /* legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        } */
        series: [{
            name: 'Signal',
             color: '#0040ff',
            data: Signal
        }]
    });
	
	$('#memory').highcharts({
        title: {
            text: 'Memory Available',
            x: -20 //center
        },
        /* subtitle: {
            text: 'Source: WorldClimate.com',
            x: -20
        }, */
        xAxis: {
        	title: {
                text: 'Time in Hrs'
            },
            categories: takeTimeVal,
            tickWidth: 1,
        },
        yAxis: {
            title: {
                text: 'Memeory (in MB)'
            },
        },
       /*  tooltip: {
            valueSuffix: 'MB'
        }, */
        tooltip: {
        	formatter: function() {
                return  this.series.name +':'+ this.y + 'MB';
            }            
        },
        
        /* legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        } */
        series: [{
            name: 'Memeory',
             color: '#ff3300',
            data: MemoryAvl1
        }]
    });
	
	
	
	 $('#'+param).attr("data-toggle", "modal");
	  $('#'+param).attr("data-target","#stackChart");
}



</script>	

	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="header-inner">			
			<a class="navbar-brand" href="#" style="cursor: default;">			
			<font color="white" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bsmart</font><font color="red" size="3">Cloud</font>
			</a>
			
					
		<c:if test="${userType eq 'admin_subdivision'||userType eq 'ADMIN_SUBDIVISION'}">
		<font color="white" size="3" >&nbsp;&nbsp;&nbsp;&nbsp;SECTION:-</font><font color="red" size="3">${sectionVal}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</font>
					<font color="white" size="3" >&nbsp;&nbsp;&nbsp;&nbsp;SITECODE:-</font><font color="red" size="3">${sdocodeval}
					</font>
		<ul class="nav navbar-nav pull-right">				
				
						<li ><a href="./logout" id="logOut" ><i class="fa fa-key"></i> <font color="white" size="3">Log Out</font></a>
						</li>
							
			</ul>
		</c:if>
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<img src="<c:url value='/resources/assets/img/menu-toggler.png'/>" alt="" />
			</a>
			<%-- <%if(session.getAttribute("userType")!=null ){ %>
			<%String userType1 = (String)session.getAttribute("userType");%>			
			<% if(userType1.equalsIgnoreCase("admin") || userType1.equalsIgnoreCase("ADMIN")) { %> 			
		 --%>
		 <c:if test="${(userType eq 'admin' || userType eq 'ADMIN')||(userType eq 'user' || userType eq 'USER')}">	
		 <ul class="nav navbar-nav pull-right">				
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<%-- <img alt="" src="<c:url value='/resources/assets/img/avatar1_small.jpg'/>"/> --%>
					<img data-magnifyby="15" class="magnify" src="./getProfilePic" width="30px" height="30px"/>
					<span class="username"><%=(String)session.getAttribute("username") %></span>
					<i class="fa fa-angle-down"></i>
					</a>
					
					<ul class="dropdown-menu">
										
						 <li ><a href="#" id="profile" ><i class="fa fa-user"></i> My Profile</a>
						</li>
						
						<li ><a href="javascript:;" id="trigger_fullscreen" ><i class="fa fa-move"></i> Full Screen</a>
						</li>
						<li > <a href="./lockScreen" id="lock" ><i class="fa fa-lock"></i> Lock Screen</a>
						</li>
						<li ><a href="./logout" id="logOut" ><i class="fa fa-key"></i> Log Out</a>
						</li>
					</ul>
				</li>				
			</ul>
		</c:if>	
		
		
		</div>		
	</div>	
	<!-- <div class="modal fade" id="popup_image" tabindex="-1" data-backdrop="static" data-keyboard="false">
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
		        /.modal-content
		       </div>
		       /.modal-dialog
      </div> -->
	<div class="clearfix"></div>