<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	     TableEditable.init();
   	    	     FormComponents.init();
   	    	     loadSearchAndFilter('table_3');
   	    	     $('#Ip_Cesc,#DTCMasterData').addClass('start active ,selected');
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
					feederCode+="<select id='feederCode' name='feederCode' class='form-control selectpicker placeholder-no-fix input-medium' type='text' autocomplete='off' placeholder='' onchange='getDTCData(this.value)' value=''> <option value='select' id='feederCodeOpt'>Select Feeder Code</option> <option value='All' id='feederCodeOpt'>All</option>";
					for( i=0;i<response.length;i++){
						feederCode+="<option id='feederCodeOpt' value='"+response[i]+"'>"+response[i]+"</option>";
					}
					feederCode+="</select><span></span>";
					$("#feederCodeDiv").html(feederCode);
				  },
				  complete:function(response)
					{	
						loadSearchAndFilter('table_3');
					}
			  });
			}
	}

function getDTCData(feederCode)
  	{
	       var sitecode = $("#sdoCode").val();
	      
  			  $.ajax({
  				  type:"POST",
  				  url:"./getDTCData",
  				  data:{"feederCode":feederCode,"sitecode" : sitecode},
  				  dataType:"JSON",
  				  success:function(response)
  				  {
  					var html="";
  					if(response!=null)
  					{
  					  for(var i=0;i<response.length;i++)
  						  {
  						   var res=response[i];
  						   html=html+"<tr><td>"+res[3]+"- "+res[4]+"</td><td>"+res[1]+"</td><td>"+res[0]+"</td><td>"+res[2]+"</td></tr>";
  						  }
  					}
  				$('#table_3').dataTable().fnClearTable();
  				$('#ipMasterDataDetailedId').html(html);	  
  				  },
  				complete:function(response)
				{	
					loadSearchAndFilter('table_3');
				}
  			  }); 
  			  
  			  
  			  
  			  
  			/* $.ajax({
  			  type:"POST",
  			  url:"./getIPmasterDetailedData/"+param,
  			  dataType:'json',
  			  cache:false,
  			  async:false,
  			success:function(response)
  			  {
  				if(response!=null)
  					{
  					  var html="";
  					  for(var i=0;i<response.length;i++)
  						  {
  						  var res=response[i];
  						  html=html+"<tr><td>"+res[0]+"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td><td>"+res[6]+" - "+res[7]+"</td><td>"+res[8]+"</td></tr>";
  						  }
  					}
  				$('#table_3').dataTable().fnClearTable();
  				$('#ipMasterDataDetailedId').html(html);			
  				},
  			complete:function(response)
  			{	
  				loadSearchAndFilter('table_3');
  			}
  		  }); */
  	}
	
  </script>
<div class="page-content" >
<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-cogs"></i>DTC-Master Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						
						<div class="portlet-body flip-scroll">
								<div class="btn-group pull-right">
									<button class="btn dropdown-toggle" data-toggle="dropdown">
										Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li><a href="#" id="print">Print</a></li>
										<li><a href="#" id="excelExport"
											onclick="tableToExcel('table_3', 'DTC Master Data')">Export
												to Excel</a></li>
									</ul>
								</div>
											<div class="row form-group">
														<div class="col-md-3"> 
															
														 <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederData(this.value)">
														    <option value="" selected="selected">Select SDO Code</option>
														    <c:forEach var="element" items="${sdoCodes}">
																<option value="${element[0]}" id="sdoCodeOpt">${element[0]}-${element[1]}</option>
															</c:forEach>
														  </select>
														
														</div>
														<div class="col-md-3" id="feederCodeDiv">
															<select id="feederCode" class="form-control selectpicker placeholder-no-fix input-medium" name="feederCode">
														  	</select>	
														</div>
													</div>					
						    <br/>
							<table class="table table-striped table-hover table-bordered" id="table_3">
								<thead class="flip-content">
								     <tr>
										<th>Site Code</th>
										<th>DTC Code</th>
										<th>Feeder Code</th>										
										<th>Feeder Type</th>
									</tr>
								</thead >
									<tbody id="ipMasterDataDetailedId">
								</tbody>
							</table>
						</div>
					</div>
</div>