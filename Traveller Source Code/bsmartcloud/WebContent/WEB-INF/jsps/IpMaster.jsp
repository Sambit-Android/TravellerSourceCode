<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	loadSearchAndFilter('table_3');
   	    	$('#Ip_Cesc,#ipMasterData').addClass('start active ,selected');
   	    	   });
  
  /* function getSiteCodeData(param)
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
				html=html+"<option value='All'>All</option>";
				$('#sdoCode').html(html);
				$('#sdoCode').select2();
				}
		  });
		return false;
	} */

function getFeederData(param)
{
	  $.ajax({
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
					  html=html+"<tr><td>"+res[0]+"</td><td>"+res[1]+"</td><td>"+res[2]+"</td><td>"+res[9]+"</td><td>"+res[10]+"</td><td>"+res[11]+"</td><td>"+res[8]+"</td><td>"+res[3]+"</td><td>"+res[4]+"</td><td>"+res[5]+"</td><td>"+res[6]+" - "+res[7]+"</td></tr>";
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
	return false;
	  
}
  </script>
<div class="page-content" >
<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-cogs"></i>IP-Master Details</div>
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
											onclick="tableToExcel('table_3', 'IP Master Data')">Export
												to Excel</a></li>
									</ul>
						</div>
								
						<div>
						<!-- <select id="sdoType" class="form-control selectpicker select2me input-medium" name="sdoType" onchange="return getSiteCodeData(this.value);"  >
														    <option value="0" selected="selected">Select Type</option>
														    <option value="circle" >Circle</option>
														    <option value="division" >Division</option>
														    <option value="subdivision" >subdivision</option>
														    <option value="section" >All</option>														    
														  </select> -->
						
						  <select id="sdoCode" class="form-control selectpicker select2me input-medium" name="sdoCode" onchange="getFeederData(this.value)" >
						  <option value='0' selected=selected>Select section</option>    
						  <c:forEach var="element" items="${sectionCodes}">
						  <option value='${element[1]}' >${element[0]}-${element[1]}</option>
						  </c:forEach>
						  </select>
						</div><br/>
							<table class="table table-striped table-hover table-bordered" id="table_3">
								<thead class="flip-content">
								     <tr>
										<th >RRNO&nbsp;/&nbsp;UIPSETCODE</th>
										<th >Feeder Code</th>										
										<th>DtcCode</th>
										<th>Name</th>
										<th>Address</th>
										<th>Sanction Load</th>
										<th >Village</th>
										<th>FeederType</th>
										<th>DateOfService</th>
										<th>Installation<br/>status</th>
										<th>sitecode-Name</th>										
										
									</tr>
								</thead >
									<tbody id="ipMasterDataDetailedId">
								</tbody>
							</table>
						</div>
					</div>
</div>