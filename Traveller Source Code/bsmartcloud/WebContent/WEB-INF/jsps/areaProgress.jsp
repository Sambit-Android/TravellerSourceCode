<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script src="./resources/assets/plugins/fuelux/js/tree.min.js"></script>
   <script src="./resources/assets/scripts/ui-tree.js"></script> 
   
   <script src="http://maps.google.com/maps/api/js?sensor=true"  type="text/javascript" ></script>
<script src="./resources/gmaps.js"  type="text/javascript" ></script>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#Ip_Cesc,#areaProgressData').addClass('start active ,selected');
   	    	treeView($('#MyTree'),myData);
   	    	   });
  var siteCodee="";
var myData = {			
};
var globalCallBack;
var slectedSiteCode="";
var hed="";
function treeView(el, data) {
	
	
  if (typeof el != 'undefined' && typeof data == 'object') 
  {  
	   var DataSourceTree = function (options) {        	 
          this._data  = options.data;
          this._delay = options.delay;
      };
          
      DataSourceTree.prototype = {
          data: function (options, callback) {
        	 
       	   globalCallBack=callback;
       	   var self = this; 
       	    var $data = null;
       	    var param = null;                   
       	    if (!("name" in options) && !("type" in options)) {
       	        param = "cescIp"; //load the first level  
       	        deptId=0;
       	    } else if ("type" in options && options.type == "folder") {
       	        if ("additionalParameters" in options && "id" in options.additionalParameters) {
       	            param = options.additionalParameters["id"];
       	        }
       	      }
       	 slectedSiteCode=param;
       	    if (param != null ) //&& callTree==true
       	    {	
       	    	setTimeout(function () {
       	    		getTreeData(param,callback);
                   }, 000)
       	    	
       	    }            	   
          }
      };
      
      var treeDataSource = new DataSourceTree(data); 
      
      $('#MyTree').on('opened', function (evt, data) {
    	  slectedSiteCode=data.additionalParameters.id;
    	  hed=data.additionalParameters.head;
    	  
    	  /* var x=document.getElementsByName('depSelected');
		  for (i = 0; i < x.length; i++) {
			    x[i].style.display = "none";
			}
		  $('#slectedDept'+slectedSiteCode).show(); */
		  getRelatedSiteCodeData(slectedSiteCode,hed);
		});

		$('#MyTree').on('closed', function (evt, data) {
			slectedSiteCode=data.additionalParameters.id;
			  hed=data.additionalParameters.head;
	    	 /*  var x=document.getElementsByName('depSelected');
			  for (i = 0; i < x.length; i++) {
				    x[i].style.display = "none";
				}
			  $('#slectedDept'+slectedSiteCode).show() */;
			  getRelatedSiteCodeData(slectedSiteCode,hed);
		});

		
      $(el).tree({
          selectable: true,
          cacheItems:true,
          multiSelect:true,
          dataSource: treeDataSource,
          ignoreRedundantOpens:true,
          loadingHTML: '<img src="./resources/assets/img/input-spinner.gif"/>',
      });
	   
  }

};   
function getTreeData(id,callback)
{	
	//alert("1");
  $.ajax({
		type : "GET",
	    url : "./getareaProgressTreeData/"+id,
	    dataType : "json",
	    contentType: "application/json; charset=utf-8",
	    cache : false,
	    async:false,
	    success : function(response )
	  { 	
	    	if(response!=null)
	    		{ 	    			    		
	    		callback({ data: response });
	    		}
	    	else
	    	{ 
	    		$('#notFoundDept'+slectedSiteCode).css("display", "");
	    	    $(".tree-loader").css("display", "none");
	    	  
	    	}
	    	
	  },
	  complete:function(response)
	  { 
		  
	  },
	  error:function(error)
	  {
		  alert('error');
	  }
  });
}

function getRelatedSiteCodeData(code,name)
{
	$('#attch').html('('+name+')');
siteCodee=code;	
//alert(siteCodee);
	$.ajax({
		type : "GET",
	    url : "./getIpDataForSelectedTreeSiteCode/"+code,
	    dataType : "json",	   
	    cache : false,
	    async:false,
	    success : function(response )
	  {  
	     	if(response.length>0)
	     		{
	     		var res=response[0];	     		
	     		$('#CountFeeders').html(res[0]);
	     		$('#CountDtc').html(res[1]);
	     		$('#countAuthIp').html(res[2]);
	     		$('#countUnAuthIp').html(res[3]);
	     		}
	  }
	});
}
function abc(param)
{
	alert(param);
}

function viewDTCMap(t) {
	//alert(t);
	//return false;
	//alert(siteCodee);
	$.ajax({
		type : "POST",
		data:{
			status:t
		},
	    url : "./getLongtiudeAndLatideude?siteCode="+siteCodee,
	    dataType : "json",	   
	   
	    success : function(response)
	  {  

			  var map='';
				if(response.length ==0)
				{
					$('#map-canvas').hide();
				alert("No Records Found");
				}else{
					for(var i=0;i<response.length;i++)
					{ 
						$('#map-canvas').show();
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
					if(t=='Y'||t=='N')
			{
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
					else
						{
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
					
				}
	  }
	});
	return false;
	
}
</script>

  <div class="page-content" >										
					<div class="row">
				<div class="col-md-3">
					<div class="portlet blue box" style="max-width: 270px;">
						<div class="portlet-title">
							<div class="caption">Area Progress</span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>								
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" style="max-width: 268px;min-height: 630px;max-height:630px;overflow: auto;">
							
							<div id="MyTree" class="tree tree-plus-minus tree-solid-line tree-unselectable">
								<div class = "tree-folder" style="display:none;">
									<div class="tree-folder-header">
										<i class="fa fa-folder"></i>
										<div class="tree-folder-name"></div>
									</div>
									<div class="tree-folder-content"></div>
									<div class="tree-loader" style="display:none"></div>
								</div>
								<div class="tree-item" style="display:none;">
									<i class="tree-dot"></i>
									<div class="tree-item-name"></div>
								</div>
							</div>
								
						</div>
						
					</div>
				</div>
				
				<div class="col-md-9" style="margin-left:-25px;">
					<div class="portlet green box">
						<div class="portlet-title">
							<div class="caption">Area Progress&nbsp;<span id="attch"></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>								
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" style="min-height: 630px;max-height:630px;overflow: auto;">
							
							<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountFeeders">
								
							</div>
							<div class="desc">                           
								Total Feeders
							</div>
						</div>						           
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountDt7c">
								<a href="#" id="CountDtc" style="color: white;" onclick="return viewDTCMap('dtc')"></a>
							</div>
							<div class="desc">                           
								Total DTC
							</div>
						</div>						           
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-user"></i>
						</div>
						<div class="details" >
							<div class="number" id="countAufdthIp">
							<a href="#" id="countAuthIp" style="color: white;" onclick="return viewDTCMap('Y')"></a>
							</div>
							<div class="desc">Authorised IP</div>
						</div>						              
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="number" id="counsdtUnAuthIp">
							<a href="#" id="countUnAuthIp" style="color: white;" onclick="return viewDTCMap('N')"></a>
							</div>
							<div class="desc">UnAuthorised IP</div>
						</div>						              
					</div>
				</div>	
				<!-- <button class="btn green pull-left" style="display: block;" onclick="return viewDTCMap()">View DTC&nbsp;&nbsp;&nbsp;<i class="m-icon-swapright m-icon-white"></i></button> -->			
			</div> 
								
									<div class="portlet-body">
							<div id="map-canvas" class="gmaps" style="height: 500px"></div>				 
               <!-- END PORTLET-->
               </div>
						</div>
						
					</div>
					
				</div>
				
				
						
						</div></div>
<style type="text/css">
#map-canvas {
        width: 900px;
        height: 400px;
      }
</style>
