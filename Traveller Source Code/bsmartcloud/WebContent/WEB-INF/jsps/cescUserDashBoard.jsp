<!-- BEGIN PAGE -->
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <script>
	 	$(".page-content").ready(function() 
	 	{ 		 		
		    App.init(); 
		    Index.initMiniCharts();
		    TableEditable.init();
		    
		    //display dashboards only having access rights
		    <c:forEach var="elem" items="${activeModules}">
			  var ac = '<c:out value="${elem.id}"></c:out>';
			  $('#dashboard-'+ac).show();
			  </c:forEach>
		    // end of display
			  
		    $('#dash-board').addClass('start active ,selected');
			$('#dash,#user-location,#admin-employee,#MIS-Reports,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
			
		}); 
	 	
	 	 /* function displaySearchAndFilter()
	 	  {
	 		  
	 		  $('#sample_3').dataTable().fnDestroy();
	 		  $('#sample_3').dataTable();
	 		  jQuery('#sample_3_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input
	 	      jQuery('#sample_3_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown
	 	      jQuery('#sample_3_wrapper .dataTables_length select').select2(); // initialize select2 dropdown 
	 	      
	 	     $('#sample_4').dataTable().fnDestroy();
	 		  $('#sample_4').dataTable();
	 		  jQuery('#sample_4_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input
	 	      jQuery('#sample_4_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown
	 	      jQuery('#sample_4_wrapper .dataTables_length select').select2(); // initialize select2 dropdown 
	 	  } */
	 	
	 	/* function ajaxData(id,digits,recentId,region,requiredLevel)
		{
	 		
			var key = document.getElementById("key"+recentId).value;			
			if(key == "plus")
				{
			$.ajax({
				type : "GET",
			    url : "./getAllLocationNamesInTree/"+recentId+"/"+region+"/"+requiredLevel,
			    dataType : "json",
			    cache:false,
			    success : function(response )
	    		  {
			    	if(response=='' || response==null)
			    	{				    		
			    		bootbox.alert(' There are no Locations under selected one');
			    		return false;				    					    		
			    	}
			    	
			    	var exex=10;
			    	 var html = "<ol class='dd-list' id='ol"+recentId+"'>";
			    	for(var i = 0;i < response.length;i++)
			    		{			    		
			    		var list = response[i];
			    		
			    		html = html + "<li class='dd-item' data-id='"+list[1]+"' id='id"+list[1]+"' ><input type='hidden' id='key"+list[1]+"' value='plus' name='"+list[0]+"'/><div class='dd-handle1' id='"+list[0]+"' value onclick = 'ajaxData("+id+","+list[1]+","+list[1]+",this.id,"+(list[2]+1)+")'>"+list[3]+"</div></li>";
			    		}
			    	html = html + "</ol>";//salert(id + " recentId" + recentId);
			    	$('#id'+recentId).removeClass( "dd-item dd-collapsed" ).addClass( "dd-item" );		    	
			    	$('#id'+recentId).append(html); 
	    			  }
			    
			});
			document.getElementById("key"+recentId).value = "minus";
				}
			 else{
				$('#ol'+recentId).remove();
				$('#id'+recentId).removeClass( "dd-item" ).addClass( "dd-item dd-collapsed" );
				document.getElementById("key"+recentId).value = "plus";
			} 
		} */
	 	
	
		function viewData(division,name,sitecode)
		{
			$('#divisionName').text(name);
			$('#mobileDiv').hide();
			  $.ajax({
			    	url:'./getDivisionMobilesCesc'+'/'+division+'/'+sitecode,
			    	type:'GET',
			    	dataType:'json',
			    	asynch:false,
			    	cache:false,
			    	success:function(response)
			    	{
			    		var html2 = "<thead><tr><th>SlNo.</th><th>Sections</th><th>Mobiles</th></tr></thead><tbody>";
			    	       for(var j=0;j<response.length;j++)
				    		{		
			    	    	   
				    		     html2 += "<tr><td>"+(j+1)+"</td>";
				    		     html2 += "<td>"+response[j][0]+"</td>";
				    		     html2 += "<td><a href='#' id='"+response[j][1]+"@"+response[j][0]+"' onclick='return viewMobileData(this.id)' >"+response[j][2]+"</a></td></tr>"; 
				    		}
				    	html2+="</tbody>";	
				    	$('#sample_3').html(html2);
				    	$('#sectionDiv').show();
				    	loadSearchAndFilter('sample_3'); 
			    	}
			  });  
		}
		
		function viewMobileData(sitecode)
		{
			var data=sitecode.split('@');
			$('#sectionName').text(data[1]);
			 $.ajax({
			    	url:'./getSectionMobiles'+'/'+data[0],
			    	type:'GET',
			    	dataType:'json',
			    	asynch:false,
			    	cache:false,
			    	success:function(response)
			    	{
			    		var html2 = "<thead><tr><th>SlNo.</th><th>IMEINO.</th><th>GPRSSIM NO.</th><th>MAKE</th></tr></thead><tbody>";
			    	       for(var j=0;j<response.length;j++)
				    		{		 
			    	    	   for(var j=0;j<response.length;j++)
					    		{		
					    		     html2 += "<tr><td>"+(j+1)+"</td>";
					    		     html2 += "<td>"+response[j][0]+"</td>";
					    		     html2 += "<td>"+response[j][1]+"</td>"; 
					    		     html2 += "<td>"+response[j][2]+"</td></tr>";
					    		}
				    		}
				    	html2+="</tbody>";	
				    	$('#sample_4').html(html2);
				    	$('#mobileDiv').show();
				    	loadSearchAndFilter('sample_4'); ; 
			    	}
			  }); 
		}
	</script>
			

<div class="page-content">



<div class="row">
<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								${totMobile}
							</div>
							<div class="desc">                           
								Total Mobiles
							</div>
						</div>
						<a class="more" href="#" >
						Total
						</a>                 
					</div>
				</div>
				
<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								423
							</div>
							<div class="desc">                           
								Cesc
							</div>
						</div>
						<a class="more" href="#" >
						&nbsp;
						</a>     
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								544
							</div>
							<div class="desc">                           
								Bcits
							</div>
						</div>
						<a class="more" href="#" >
						&nbsp;
						</a>     
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								8
							</div>
							<div class="desc">                           
								Others
							</div>
						</div>
						<a class="more" href="#" >
						&nbsp;
						</a>     
					</div>
				</div>


<c:forEach var="element" items="${cescdashList}">
<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat ${element[3]}">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								${element[2]}
							</div>
							<div class="desc">                           
								${element[0]}
							</div>
						</div>
						<a class="more" href="#"  id="${element[0]}"  onclick="return viewData(this.id,'${element[0]}','${element[1]}')" title="View Mobiles Registered">
						View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				</c:forEach>
				
				
				
</div>

<div class="row">
			 	 <div class="col-md-6" id="sectionDiv" style="display: none;">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Sectionwise Mobiles Registered&nbsp;&nbsp;-<span class="label label-success" ><font size="3" color="#222211"><b id="divisionName" ></b></font></span></div>
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
										<th >Sections</th>
										<th >Mobiles</th>
									</tr>
								</thead>
								<tbody id="sectionTb">
									
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div class="col-md-6" id="mobileDiv" style="display: none;">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Mobile Data &nbsp;&nbsp;-<span class="label label-success" ><font size="3" color="#222211"><b id="sectionName" ></b></font></span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-bordered table-hover" id="sample_4">
								<thead>
									<tr>
										<th>SlNo.</th>
										<th >IMEINO.</th>
										<th >GPRSSIM NO.</th>
										<th >MAKE</th>
									</tr>
								</thead>
								<tbody id="mobileTb">
									
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
		</div>
		<!-- END PAGE -->
 
 </div>
  
 
