<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	loadSearchAndFilter('table_3');
   	    	   		$('#Ip_Cesc,#feederData').addClass('start active ,selected');
   	    	   });
  
  function getSiteCodeData(param)
	{
		$.ajax({
			  type:"POST",
			  url:"./getSectionCodes/"+param,
			  dataType:'json',
			  cache:false,
			  async:false,
			success:function(response)
			  {
				if(response!=null)
					{
					  var html="<option value=0 selected='selected'>Select section</option>";
					  for(var i=0;i<response.length;i++)
						  {
						   var res=response[i];
						   html=html+"<option value="+res[1]+">"+res[0]+" - "+res[1]+"</option>";
						  }
					}
				/* html=html+"<option value='All'>All</option>"; */
				$('#sdoCode').html(html);
				$('#sdoCode').select2();
				}
		  });
		return false;
	}
  
  function getFeederData(param)
  {
	  $.ajax({
		  type:"POST",
		  url:"./getFeederDetailedData/"+param,
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
					  html=html+"<tr><td>"+res[0]+"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[3]+" - "+res[4]+"</td></tr>";
					  }
				}
			$('#table_3').dataTable().fnClearTable();
			$('#feederDataDetailedId').html(html);			
			},
		complete:function(response)
		{	
			loadSearchAndFilter('table_3');
		}
	  });
	return false;
	  
  }
  
  
  </script>
<div class="page-content" >
<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-cogs"></i>Feeder Master Details</div>
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
											onclick="tableToExcel('table_3', 'Feeder Master Data')">Export
												to Excel</a></li>
									</ul>
						</div>
						<div>
						<select id="sdoType" class="form-control selectpicker select2me input-medium" name="sdoType" onchange="return getSiteCodeData(this.value);"  >
														    <option value="0" selected="selected">Select Type</option>
														    <option value="circle" >Circle</option>
														    <option value="division" >Division</option>
														    <option value="subdivision" >subdivision</option>
														    <option value="section" >All</option>														    
														  </select>
						
						  <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederData(this.value)" >
						  <option value='0' selected=selected>Select section</option>    
						  </select>
						</div><br/>
							<table class="table table-striped table-hover table-bordered" id="table_3">
								<thead class="flip-content">
									<tr>
										<th>FeederCode</th>
										<th>FeederName</th>
										<th>FeederType</th>
										<th>sitecode-Name</th>
									</tr>
								</thead>
								<tbody id="feederDataDetailedId">
									
								</tbody>
							</table>
						</div>
					</div>
</div>