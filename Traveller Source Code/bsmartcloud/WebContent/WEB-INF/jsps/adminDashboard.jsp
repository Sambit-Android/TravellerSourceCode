<!-- BEGIN PAGE -->
<div class="page-content" >
    <%@include file="pagebreadcrum.jsp" %>
    <style>
	.dd-handle1 {
    
    background: none repeat scroll 0 0 #FAFAFA;
    border: 1px solid #CCCCCC;
    border-radius: 3px;
    color: #333333;
    cursor: context-menu;
    display: block;
    font-weight: 400;
    height: 30px;
    margin: 5px 0;
    padding: 5px 10px;
    
}
.dd-handle1:hover 
{
    background: none repeat scroll 0 0 #FFFFFF;
    color: red;
}
		
		</style>
  	<script>
	 	$(".page-content").ready(function() 
	 	{    
		   App.init(); 
		   Index.initMiniCharts();
		   TableEditable.init();
		   $('#admindash').addClass('start active ,selected');
			 $('#dash,#admin-location,#admin-employee').removeClass('start active ,selected'); 
		}); 
	 	
	 	
	 	function ajaxData(id,digits,recentId,region,requiredLevel)
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
		}
	 	
	
	</script>
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
			<div class="row">
			 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-sitemap"></i>
						</div>
						<div class="details">
							<div class="desc">Company Info</div>
						</div>
						<a class="more" href="./companyInfo">
						View/Add/Update more <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
			   <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="desc">Locations Info</div>
						</div>
						<a class="more" href="./addLocation">
						View/Add/Update more <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="desc">Employee Master</div>
						</div>
						<a class="more" href="./employeeMasterData">
						View/Add/Update more <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="desc">                           
								Tasks/SubTasks
							</div>
						</div>
						<a class="more" href="./ManageStagesAndSubStages">
						View/Add/Update more <i class="m-icon-swapright m-icon-white"></i>
						</a>                 
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12">
				<c:if test = "${results ne 'notDisplay'}"> 			        
			        <div class="alert alert-danger display-show">
							<button class="close" data-close="alert"></button>
							<span style="color:red" >${results}</span>
						</div>
			        </c:if>	
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-comments"></i>Department-wise Tasks and sub Tasks</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>						
						
						<div class="portlet-body " >						
							<div class="dd" id="nestable_list_1">
							<ol class="dd-list">
							
						
							
							<li class="dd-item" data-id="${topMost.locationcode}" id="id${topMost.locationcode }" data-action="collapse-all">
							<input type="hidden" id="key${topMost.locationcode }" value="plus" />
										<div class="dd-handle1" id="${topMost.locationcode }" >
										${topMost.locationname}</div>
								
									
							<ol class="dd-list">
							<c:set var="count" value="90" scope="page" />
							<c:forEach var="element3" items="${LocationCategy}">
							<input type="hidden" id="key${count}" value="plus" />
											<li class="dd-item" data-id="element1" id="id${count}">
											<div class="dd-handle1" id="${count}" onclick="ajaxData(this.id,'0',${count},'${element3}',1);" >
										${element3}</div>												
											</li>
											<c:set var="count" value="${count+1}" scope="page"/>
											</c:forEach>
										</ol>
										</li>
		</ol>
								
							</div>
							
							
						</div>
					</div>
				</div>
				
			</div>	
			 	 
		</div>
		<!-- END PAGE -->
 
 
  
 
