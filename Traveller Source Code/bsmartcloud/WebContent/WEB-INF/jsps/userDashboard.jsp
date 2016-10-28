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
	 	
	
		function viewData(division,name)
		{
			$('#divisionName').text(name);
			$('#mobileDiv').hide();
			  $.ajax({
			    	url:'./getDivisionMobiles'+'/'+division,
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
				    		     html2 += "<td><a href='#' id='"+response[j][1]+"' onclick='return viewMobileData(this.id)' >"+response[j][2]+"</a></td></tr>"; 
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
			 $.ajax({
			    	url:'./getSectionMobiles'+'/'+sitecode,
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
			

<div class="page-content" >

    <%@include file="pagebreadcrum.jsp" %>
    <%-- <c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if> --%>
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->               
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		 
			<!-- BEGIN PAGE HEADER-->
			
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<!-- <div class="row">
			
			   	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-MIS-Reports-photoBilling" hidden="true">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="desc">Photo Billing</div>
						</div>
						<a class="more" href="./realTimeStatus">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-MIS-Reports" hidden="true"> 
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="desc">Consumer App</div>
						</div>
						<a class="more" href="./billingData">
						View<i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-Cash_Collection" hidden="true">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">          
								Cash Collection
							</div>
						</div>
						<a class="more" href="./collectionDetails">
						View<i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>				
			</div>
			<div class="row">
			
			 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-Disconn-Reconn" hidden="true">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-sitemap"></i>
						</div>
						<div class="details">
							<div class="desc">Dis-Reconnection</div>
						</div>
						<a class="more" href="./disReConnDashboard">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-vigilance-management" hidden="true">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">          
								Complaints & Theft
							</div>
						</div>
						<a class="more" href="./complainTheftDashboard">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-Real-vigilance" hidden="true">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">          
								Vigilance
							</div>
						</div>
						<a class="more" href="./assignVigilance">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-Circl_Data" hidden="true">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">          
								Bill Progress Report
							</div>
						</div>
						<a class="more" href="./subDevisionDetails">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="dashboard-Ip_Cesc" hidden="true">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">          
								IP and DTC ENUM
							</div>
						</div>
						<a class="more" href="./showCescIpDashBoard">
						View <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>		bellary
		gangavathi
		hospet
		koppal
		raichur
		sindhanoor
		test
				
			</div> -->
				<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat  purple">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								${bellary+gangavathi+hospet+koppal+raichur+sindhanoor+testLocation1+testLocation2}
							</div>
							<div class="desc">                           
								Total Mobiles
							</div>
						</div>
						<a class="more" href="#">
						Total <!-- <i class="m-icon-swapright m-icon-white"></i> --></a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
							<div class="number" id="CountBilled">
								${bellary}
							</div>
							<div class="desc">                           
								BELLARY
							</div>
						</div>
						<a class="more" href="#"  id="bellary"  onclick="return viewData(this.id,'BELLARY')" title="View Mobiles Registered">
						View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
						<div class="number" id="CountBilled">
						
								${gangavathi}
								<div class="desc"> 
								GANGAVATHI
							</div>
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="gangavathi"  onclick="return viewData(this.id,'GANGAVATHI')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
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
						
								${hospet}
								<div class="desc"> 
								HOSPET
							</div>
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="hospet"  onclick="return viewData(this.id,'HOSPET')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				
				
				
				
			</div>
			<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
						<div class="number" id="CountBilled">
						
								${koppal}
						</div>
						
								<div class="desc"> 
								KOPPAL
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="koppal"  onclick="return viewData(this.id,'KOPPAL')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
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
						
								${raichur}
						</div>
						
								<div class="desc"> 
								RAICHUR
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="raichur"  onclick="return viewData(this.id,'RAICHUR')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
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
						
								${sindhanoor}
						</div>
						
								<div class="desc"> 
								SINDHANOOR
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="sindhanoor"  onclick="return viewData(this.id,'SINDHANOOR')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat red">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
						<div class="number" id="CountBilled">
						
								${testLocation1}
								<div class="desc"> 
								OFFICE LOCATION(TEST)
							</div>
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="test"  onclick="return viewData(this.id,'TEST')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat red">
						<div class="visual">
							<i class="fa fa-eraser"></i>
						</div>
						<div class="details" >
						<div class="number" id="CountBilled">
						
								${testLocation2}
								<div class="desc"> 
								OFFICE LOCATION(TEST2)
							</div>
							</div>
						</div>
						<a class="more" href="#" title="View Mobiles Registered" id="test2"  onclick="return viewData(this.id,'TEST2')">
						   View Mobiles <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				
				
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
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-globe"></i>Mobile Data</div>
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
		</div>
		<!-- END PAGE -->
 
 
  
 
