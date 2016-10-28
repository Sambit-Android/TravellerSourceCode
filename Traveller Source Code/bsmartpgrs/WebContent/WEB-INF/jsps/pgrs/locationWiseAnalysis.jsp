<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>


<link href="<c:url value='/resources/kendo/css/web/kendo.default.min.css'/>" rel="stylesheet" />
<c:url value="/projecttree/map/read1" var="treeReadUrl" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="content">

					<div class="jarviswidget" id="searchWiseId" data-widget-editbutton="false" data-widget-custombutton="false">

				<div>
					<div class="widget-body no-padding">
						
						<form id="order-form" action="#" method="POST" class="smart-form" novalidate="novalidate">
							<fieldset>
								<div class="row">
								
								<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label> 
										
									</section>
									<section class="col col-2">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
								
									
									<section class="col col-2">
										<label class="select">
											<select  id="categoryId" name="categoryId">
												<option value="0" selected="" disabled="">Select Category</option>
  												<c:forEach items="${categoryList}" var="categories">
												<option value="${categories.categoryId}">${categories.categoryName}</option>
												</c:forEach>
										    </select><i></i></label>
											</section>
											
									<section class="col col-2">
										<label class="select">
											<select  name="subCategoryId" id="subCategoryId">
												<option value="0" selected="" disabled="">Select SubCategory</option>
											</select><i></i></label>
									</section>
										
									<section class="col col-2">
										<button type="submit" class="btn btn-primary" style="height: 32px;width: 140px;" onclick="return  loadAllDetails()">Search</button>
									</section>
									
								</div>

								
							</fieldset>
							
						</form>

					</div>
					
					
				</div>
				<!-- end widget div -->
			</div> 
					
					<!-- row -->
					<div class="row">
				
						<!-- NEW WIDGET START -->
						<article class="col-sm-12 col-md-12 col-lg-6">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blue" id="wid-id-1" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-sitemap"></i> </span>
									<h2>Location Wise View Complaints</h2>
								</header>
								<div style="height: 307px; max-height: 380px; overflow: scroll;">
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
	<kendo:treeView name="treeview" dataTextField="text"  expand="onExpand" select="onSelect" template="<span id='mainText' style='width: 100%'><b>#: item.text # </b></span><input type='hidden' id='naren' class='#: item.text ##: item.id#' value='#: item.id#'/><br><i>Type </i>:<span class='type#:item.id#' id='type#: item.type##:item.id#'> #: item.type#</span><span style='height:100%;display:none' id='hideit_#: item.id#'></span>">
     <kendo:dataSource >
         <kendo:dataSource-transport>
             <kendo:dataSource-transport-read url="${treeReadUrl}" type="POST"  contentType="application/json"/>    
             <kendo:dataSource-transport-parameterMap>
             	<script>
              	function parameterMap(options,type) {
              		return JSON.stringify(options);
              	}
             	</script>
             </kendo:dataSource-transport-parameterMap>         
         </kendo:dataSource-transport>
         <kendo:dataSource-schema>
             <kendo:dataSource-schema-hierarchical-model id="id" hasChildren="hasChilds" />
         </kendo:dataSource-schema>
     </kendo:dataSource>
 </kendo:treeView>
 </div>
		</div>				
							</div>
							<!-- end widget -->
							
						</article>
						<!-- WIDGET END -->
				<div class="col-sm-6">
				
							<!-- well -->
							<div class="well">
								<!-- row -->
								<div class="row">
									<!-- col -->
									<div class="col-sm-12">
										<!-- row -->
										<div class="row">
				
											<div class="col-md-12">
												<div id="monthContent">
												 <table id="locationwise" class="table table-bordered">
													<thead>
														<tr>
															<th style="width:50%">Complaint Status</th>
															<th style="width:50%">No. Of Tickets</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><span class="label label-success"> Pending For Registeration</span></td>
															<td><span id="span1" class="badge bg-color-greenLight">0</span></td>
														</tr>
														<tr>
															<td><span class="label label-info">Registered&Assigned</span></td>
															<td><span id="span2" class="badge bg-color-blueLight">0</span></td>
														</tr>
														<tr>
															<td><span class="label label-warning">OnHold</span></td>
															<td><span id="span3" class="badge bg-color-orange">0</span></td>
														</tr>
														<tr>
															<td><span class="label label-primary">Resolved</span></td>
															<td><span id="span4" class="badge bg-color-darken">0</span></td>
														</tr>
														<tr>
															<td><span class="label label-danger">Reopen</span></td>
															<td><span id="span5" class="badge bg-color-red">0</span></td>
														</tr>
													</tbody>
												</table> 
											</div>
											</div>
										</div>
										<!-- end row -->
									</div>
									<!-- end col -->
								</div>
								<!-- end row -->
							</div>
							<!-- end well -->
				
						</div>
					</div>
				
				<div class="jarviswidget jarviswidget-color-blueDark" id="dockeStas" data-widget-editbutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2 id="statusWise">Status Wise Details</h2>
				
								</header>
				
								<!-- widget div-->
									<!-- <br>
								
								<a class="btn bg-color-redLight txt-color-white"  href="#"  onclick="return docketDetailsPopUp('CHECKBOXYES')">Change Multiple Status</a> -->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body no-padding" style='overflow: scroll;'>
				
										<table id="mobileTicketsSearch" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<!-- <th> <input type="checkbox" id="selectall"  onClick="selectAll(this)" /></th> -->
													<th data-hide="phone">Docket No.</th>
													<th data-class="expand">Docket Status</th>
													<th>Created Date</th>
													<th data-hide="phone">SLA date & Time for resolving</th>
													<th data-hide="phone">Time Pending</th> 
													<th data-hide="phone">Resolved Date & Time</th>
												 <!--  <th data-hide="phone">Time taken for resolving</th> --> 
													<th data-hide="phone,tablet">Category</th>
													<th data-hide="phone,tablet">Sub Category</th>
												<!-- 	<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone,tablet">Mobile No.</th> -->
													<th data-hide="phone,tablet">AssignedTo</th>
													<th></th>
												</tr>
											</thead>
											<tbody id="searchTicketDivDetails">
											<%-- <c:forEach items="${search}" var="searchData">
											<tr>
											<c:choose>
											<c:when test="${searchData.docketStatus eq 'Resolved'}">
											<td></td>
											<td><a style="cursor:pointer;" onclick='docketDetailsValidation()'>${searchData.docketNumber}</a></td>
											</c:when>
											<c:otherwise>
											<td><div id="docketNumDiv"> <input type="checkbox"    autocomplete="off" placeholder="" name="docketNumb" id="${searchData.docketNumber}" value="${searchData.docketNumber}" /> </div></td>
											<td><a style="cursor:pointer;" onclick='docketDetailsPopUp(${searchData.docketNumber})'>${searchData.docketNumber}</a></td>
											</c:otherwise>
											</c:choose>
													<td>${searchData.docketNumber}</td>
													<td>${searchData.docketStatus}</td>
													<td>${searchData.docketCreatedDt}</td>
													<td>${searchData.estResolveTime}</td>
													<td>${searchData.pending}</td>
													<td>${searchData.resolvedDate}</td>
													<td>${searchData.totalTime}</td>
													<td>${searchData.categoryName}</td>
													<td>${searchData.subCategoryName}</td>
													<td>${searchData.consumerName}</td>
													<td>${searchData.consumerMobileNo}</td>
													<td>${searchData.assignedTo}</td>
													
													
													
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${searchData.docketNumber})'>View</a></td>
													
												</tr>
											</c:forEach> 
												 --%>
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
				
		</div>
		
		
		
		<!-- View Popup -->

		<div class="row" style="display:none" id="selectedDocketDetails">
		
<article class="col-sm-12">
<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon"></span>
								<ul class="nav nav-tabs pull-right in" id="myTab">
									<li id="tabOne" class="active">
										<a data-toggle="tab" href="#s1" onclick="tab1Click()"><i class="fa fa-envelope"></i> <span class="hidden-mobile hidden-tablet">Docket Details</span></a>
									</li>
									<li id="tabTwo">
											<a data-toggle="tab" href="#s2" onclick="tab2Click()"><i class="fa fa-folder"></i> <span class="hidden-mobile hidden-tablet">Docket Histroy</span></a>
									</li>
									<li id="tabThree">
											<a data-toggle="tab" href="#s4" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
									</li>
									</ul>	
										
								</header>
								<div class="no-padding">
									<div class="widget-body" id="tab1">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in padding-12 no-padding-bottom" id="s1"> 	
											<div class="col-sm-12">
												<br>
													<table class="table table-bordered table-striped">
													<tbody id="viewDocket">
	
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab2">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s2">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<th data-hide="phone">Date</th>
															<th data-class="expand">Status</th>
															<!-- <th>Action</th> -->
															<th data-hide="phone">Action By</th>
															<th data-hide="phone,tablet">Name</th>
															<th data-hide="phone,tablet">User Name</th>
															<th data-hide="phone,tablet">Office</th>
															<th data-hide="phone,tablet">Designation</th>
															<th data-hide="expand">Official Mobile No.</th>
															<th data-hide="phone,tablet">Remarks</th>
														</tr>
														</thead>
													<tbody id="docketHistoryDiv">
													</tbody>
													</table>
												</div>
										</div>
										</div>
										</div>
										<div class="widget-body" id="tab3">
										<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade" id="s4">	
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone">SI No.</th>
															<!-- <th data-hide="phone">Created Date</th> -->
															<th data-hide="phone">Escalated Date</th>
															<th data-class="expand">Level</th>
															<!-- <th data-hide="phone">From Officer</th> -->
															<th data-hide="phone">To Officer</th>
														</tr>
														</thead>
													<tbody id="docketEscHistoryDiv">
													</tbody>
													</table>
												</div>
											</div>
									</div>
							</div>
					</div>
			</div>
		</article>
		
</div>


	<div class="row" id="pendingForRegistrationTickets" style="display:none;">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2 id="statusWiseReg">Status Wise Details</h2>
								</header>
								<div>
									<div class="widget-body no-padding" id="pendingContent">
										<%-- <table id="datatable_tabletools" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th data-hide="phone">Docket No.</th>
													<th data-class="expand">Created Date</th>
													<th data-hide="phone,tablet">Mode</th>
													<th data-hide="phone,tablet">Consumer Name</th>
													<th data-hide="phone">Mobile No.</th>
													<th data-hide="phone,tablet">Email Id</th>
													<th data-hide="phone,tablet">Facebook Id</th>
												</tr>
											</thead>
											<tbody id="pendingRegistrationDiv">
												<c:forEach items="${allTickets}" var="ticket">
												<tr>
													<td><a class="btn btn-primary" onclick='docketDetailsPopUp(${ticket.docketNumber})'>${ticket.docketNumber}</a></td>
													<td>${ticket.docketStatus}</td>
													<td>${ticket.docketCreatedDt}</td>
													<td>${ticket.categoryName}</td>
													<td>${ticket.subCategoryName}</td>
													<td>${ticket.docketSummary}</td> 
													<td>${ticket.docketSource}</td>
													<td>${ticket.consumerName}</td>
													<td>${ticket.consumerMobileNo}</td>
													<td><a class="btn btn-primary" onclick='docketDetailsViewPopUp(${ticket.docketNumber})'>View</a></td>
												</tr>
												</c:forEach>
											</tbody>
										</table>		 --%>		
									</div>				
								</div>				
							</div>	
			</article>
	</div>
	
<script type="text/javascript">

$(document).ready(function(){
	$('#fromdate').datepicker({
		dateFormat : 'dd/mm/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#todate').datepicker('option', 'minDate', selectedDate);
		}
	});
	
	$('#todate').datepicker({
		dateFormat : 'dd/mm/yy',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#fromdate').datepicker('option', 'maxDate', selectedDate);
		}
	});
	$('#searchWiseId').hide();
	
	$('#dockeStas').hide();
	$('#pendingForRegistrationTickets').hide();
});
var id='';
var x=0;
		function onSelect(e) {
			
			
			$('span[id=hideit_2]').remove();
			$('#deltypeProject').remove();
			$('#deltypeProjects').remove();
			$('#editypeProject').remove();
			$('#addtypeProject').remove();
			$('span[id^=hideit_]').hide();

		
			id = $('.k-state-hover').find('#naren').val();
			var nn = $('.k-state-hover').html();
			var spli = nn.split(" <input");
			 var kitems = $(e.node).add(
					$(e.node).parentsUntil('.k-treeview',
							'.k-item'));
			texts = $.map(kitems, function(kitem) {
				var resVal = $(kitem).find('>div span.k-in').text();
				var resArr = resVal.split(" Type :");
				return resArr[0].trim();
			}); 
			$('#hideit_'+id).show();
			 $.ajax({
				type : "POST",
				url : "./location/locationWiseAnalysis",
				data : {
					id : id
				},
				success : function(response) {
					
					$('#locationwise').hide();
					$('.monthTabDetail').remove();
					var htmlTable = "<div class='monthTabDetail'>" 
					+"<table id='datatable_tabletools5' class='table table-striped table-bordered table-hover'>"
					+"<thead>"
					+"<tr>"
					+"<th data-hide='phone'>Complaint Status</th>"
					+"<th data-hide='phone'>No. Of Tickets</th>"
				    +"</tr>"
							+"</thead>"
							+"<tbody>";
							var dataNew=response;
							 $.each(dataNew, function(index, data){
								if(data.pending!=undefined){
									htmlTable+= "<tr>"
										+"<td><span class='label label-success'> Pending For Registeration</span></td>"
										+"<td><a href='#' onclick='getStatusDetails(0)'><span id='span1' class='badge bg-color-greenLight'>"+data.pending+"</a></span></td>"
										+"</tr>";
								}
								if(data.assigned!=undefined){
									htmlTable+= "<tr>"
									+"<td><span class='label label-info'>Registered&Assigned</span></td>"
									+"<td><a href='#' onclick='getStatusDetails(1)'><span id='span2' class='badge bg-color-blueLight'>"+data.assigned+"</span></td>"
									+"</tr>";
								}
								if(data.onhold!=undefined){
									htmlTable+= "<tr>"
									+"<td><span class='label label-warning'>OnHold</span></td>"
									+"<td><a href='#' onclick='getStatusDetails(2)'><span id='span3' class='badge bg-color-orange'>"+data.onhold+"</span></td>"
									+"</tr>";
								}
								if(data.resolved!=undefined){
									htmlTable+= "<tr>"
									+"<td><span class='label label-primary'>Resolved</span></td>"
									+"<td><a href='#' onclick='getStatusDetails(3)'><span id='span4' class='badge bg-color-darken'>"+data.resolved+"</span></td>"
									+"</tr>";
								}
								if(data.reopen!=undefined){
									htmlTable+= "<tr>"
									+"	<td><span class='label label-danger'>Reopen</span></td>"
									+"<td><a href='#' onclick='getStatusDetails(4)'><span id='span5' class='badge bg-color-red'>"+data.reopen+"</span></td>"
									+"</tr>";
								}
								
				         		});
						htmlTable +=""		
							+"</tbody>"
							+"</table></div>";
					
					
					
					$('#monthContent').append(htmlTable);
			}
		}); 
		
		}
			var myflag = 0;
			function onExpand(e){
				if(myflag == 0){
		            $("span[id^=typeDivision]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-th'></p>"); 
					$("span[id^=typeOffice]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-briefcase'></p>"); 
					$("span[id^=typeRegion]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-star-half-o'></p>"); 
					$("span[id^=typeZone]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-globe'></p>");
					$("span[id^=typeProject]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-star-half-o'></p>");
					myflag = 1;
					
				}
			}
			
			//added By Raju
			var statd='';
			function getStatusDetails(x) {
				
				statd=x;
				if(x==0)
					{
					$('#searchWiseId').hide();
					var count=0;
					var tableHead="Status Wise Details (";
					var tabApnd="Pending For Registeration- ";
					
					$('#pendingForRegistrationTickets').show();
					$('#dockeStas').hide();
					$.ajax
					({			
						type : "POST",
						url : "./helpDesk/pendingForRegistrationTicketsBasedOnSectionCode",
						async: false,
						dataType : "JSON",
						data : {
							id : id,
						},
						success : function(response) 
						{	 
							count=response.length;
							var htmlTable = "<div class='pendingTabDetail' style='overflow: scroll;'>" 
								+"<br><table id='datatable_tabletools6' width='100%' class='table table-striped table-bordered table-hover'>"
								+"<thead>"
								+"<tr>"
								+"<th data-hide='phone'>Docket No.</th>"
								+"<th data-class='expand'>Created Date</th>"
								+"<th data-hide='phone,tablet'>Mode</th>"
								+"<th data-hide='phone,tablet'>Consumer Name</th>"
								+"<th data-hide='phone'>Mobile No.</th>"
								+"<th data-hide='phone,tablet'>Email Id</th>"
								+"<th data-hide='phone,tablet'>Facebook Id</th>"
							    +"</tr>"
										+"</thead>"
										+"<tbody>";
									for ( var s = 0, len = response.length; s < len; ++s) {
						              	var obj = response[s];
						              	htmlTable += "<tr>"		
						              	+"<td>"+obj.docketNumber+"</td>"
						              	+"<td>"+obj.docketCreatedDt+"</td>"
						              	+"<td>"+obj.docketSource+ "</td>"
						              	+"<td>"+obj.consumerName+"</td>"
						              	+"<td>"+obj.consumerMobileNo+ "</td>"
						              	+"<td>"+obj.consumerEmailId+"</td>"			              	
						              	+"<td>"+obj.facebookId+"</td>"
						                +"</tr>";
						         }
									htmlTable +=""		
										+"</tbody>"
										+"</table></div>";
										
										 $('#datatable_tabletools6').dataTable().fnDestroy();
										$('#pendingContent').html(htmlTable);
										$('#statusWiseReg').html(tableHead+tabApnd+count+")");
								
						} ,
						 complete:function(response)
							{
								loadSearchAndFilter('datatable_tabletools6');
							} 
					});
					
					}
				
				else
					{
					$('#searchWiseId').show();
				$('#dockeStas').show();
				$('#pendingForRegistrationTickets').hide();
				var count=0;
				var tableHead="Status Wise Details (";
				var tabApnd="";
				if(x==0)
					tabApnd="Pending For Registeration- ";
				else if(x==1)
					tabApnd="Registered & Assigned- ";
				else if(x==2)
					tabApnd="On Hold- ";
				else if(x==3)
					tabApnd="Resolved- ";
				else 
					tabApnd="Reopen- ";
				
			 var tableData = "";
				var status=x;
				$.ajax({
					type : "POST",
					url : "./readDocketAllDetails",
					dataType : "json",
					data : {
						id : id,
						status:status
					},
					success : function(response) {
						count=response.length;
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	tableData += "<tr>"	
			                +"<td>"+obj.docketNumber+"</td>"
			              	+"<td>"+obj.docketStatus+"</td>"
			              	+"<td>"+obj.docketCreatedDt+"</td>"
			              	+"<td>"+obj.estResolveTime+ "</td>"
			             	+"<td>"+obj.pending+"</td>"
			              	+"<td>"+obj.resolvedDate+ "</td>"
			             /*  	+"<td>"+obj.totalTime+"</td>"	 */		              	
			              	+"<td>"+obj.categoryName+"</td>"
			              	+"<td>"+obj.subCategoryName+ "</td>"
			             /*  	+"<td>"+obj.consumerName+ "</td>"
			              	+"<td>"+obj.consumerMobileNo+ "</td>" */
			            	+"<td>"+obj.assignedTo+ "</td>"
			              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
			                +"</tr>";
			         }
						
							 $('#mobileTicketsSearch').dataTable().fnDestroy();
							$('#searchTicketDivDetails').html(tableData);
							
							$('#statusWise').html(tableHead+tabApnd+count+")");
							
						
					},
					 complete:function(response)
					{
						loadSearchAndFilter('mobileTicketsSearch');
					} 
				});
				
					}
				
				 
			}
			
			function loadSearchAndFilter(param) // This Function Is For Multiple Table Id Search and Pagination
		  	{ 
			
			  $('#'+param).dataTable().fnDestroy();
		  	   pageSetUp();
				
				var responsiveHelper_datatable_tabletools = undefined;
				var breakpointDefinition = {
						tablet : 1024,
						phone : 480
					};
				$('#'+param)
				.dataTable(
						{
							"autoWidth" : true,
							"aLengthMenu": [[5, 10, 15, 25, 50, 100 , -1], [05, 10, 15, 25, 50, 100, "All"]],
							"order": [],
							"preDrawCallback" : function() {
								if (!responsiveHelper_datatable_tabletools) {
									responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
											$('#'+param),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_datatable_tabletools
										.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_datatable_tabletools
										.respond();
							},
							"aoColumnDefs": [{
				                'bSortable': false,
				                'aTargets': [0]
				            }
				        ]
						});
		  	
		  	}

			 
			  //--------------------------------For View Popup----------------------------------------------------
			  var docketNum;
	function docketDetailsViewPopUp(docNo)
	{
		docketNum = docNo;
		dialog = $("#selectedDocketDetails").dialog({
			autoOpen : false,
			width : 1000,
			height : 580,
			resizable : false,
			modal : true,
		}).dialog("open");
		$("#tabOne").addClass("active");
		$("#tabTwo").removeClass();
		$("#tabThree").removeClass();
		tab1Click();	
	}

	function tab1Click(){
		$("#tab1").show();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#viewDocket").empty();
		var tableData = "";
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketNumber/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	tableData += "<tr>"	
					              	+"<th colspan='6'>Docket Details</th>"
					              	+"</tr>"						
									+"<tr>"
									+"<th>Docket No.</th>"
									+"<td>"+obj.docketNumber+"</td>"
									+"<th>Docket Source</th>"
									+"<td>"+obj.docketSource+"</td>"
									+"<th>Docket Status</th>"
									+"<td>"+obj.docketStatus+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Consumer Name</th>"
									+"<td>"+obj.consumerName+"</td>"
									+"<th>Consumer Mobile No.</th>"
									+"<td>"+obj.consumerMobileNo+"</td>"
									+"<th>Email Id</th>"
									+"<td>"+obj.consumerEmailId+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>RR Number</th>"
									+"<td>"+obj.rrNumber+"</td>"
									+"<th>Consumer Other Mobile No.</th>"
									+"<td>"+obj.alternativeMobileNo+"</td>"
									+"<th>Facebook Id</th>"
									+"<td>"+obj.facebookId+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Address</th>"
									+"<td>"+obj.address+"</td>"
									+"<th>Category</th>"
									+"<td>"+obj.categoryName+"</td>"
									+"<th>Sub Category</th>"
									+"<td>"+obj.subCategoryName+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Sub Division</th>"
									+"<td>"+obj.divisionName+"</td>"
									+"<th>Section</th>"
									+"<td>"+obj.subDivisionName+"</td>"
									+"<th>Land Mark</th>"
									+"<td>"+obj.landMark+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Created By</th>"
									+"<td>"+obj.userName+"</td>"
									+"<th>Created Date</th>"
									+"<td>"+obj.docketCreatedDt+"</td>"
									+"<th>Est. Resolved Date</th>"
									+"<td>"+obj.estResolveTime+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Resolved Date</th>"
									+"<td>"+obj.resolvedDate+"</td>"
									+"<th>Asigned To</th>"
									+"<td>"+obj.assinedName+" - "+obj.designation+"</td>"
									+"<th>Official Mobile No.</th>"
									+"<td>"+obj.officialMobileNo+"</td>"
									+"</tr>"
									+"<tr>"
									+"<th>Docket Summary</th>"
									+"<td>"+obj.docketSummary+"</td>"
									+"<th>Service Station</th>"
									+"<td colspan='5'>"+obj.serviceStation+"</td>"
									+"</tr>";
					                
					     }
					$('#viewDocket').append(tableData);
			}
		});
	}

	function tab2Click(){
		var docketHistroyTable = "";
		$("#docketHistoryDiv").empty();
		$("#tab2").show();
		$("#tab1").hide();
		$("#tab3").hide();
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketHistory/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	docketHistroyTable += "<tr>"		
			              	+"<td>"+obj.serialNo+"</td>"
			              	+"<td>"+obj.docketUpdatedDt+"</td>"
			              	+"<td>"+obj.docketStatus+ "</td>"
			              	/* +"<td>"+obj.action+"</td>" */
			              	+"<td>"+obj.actionBy+ "</td>"
			              	+"<td>"+obj.personName+"</td>"			              	
			              	+"<td>"+obj.userName+"</td>"
			              	+"<td>"+obj.office+ "</td>"
			              	+"<td>"+obj.designation+ "</td>"
			              	+"<td>"+obj.officialMobileNo+ "</td>"
			              	+"<td>"+obj.remarks+ "</td>"
			                +"</tr>";
			         }
							$('#docketHistoryDiv').append(docketHistroyTable);
			} 
		});
	}

	function tab3Click(){
		var docketEscHistroyTable = "";
		$("#docketEscHistoryDiv").empty();
		$("#tab3").show();
		$("#tab1").hide();
		$("#tab2").hide();
		$.ajax
		({			
			type : "POST",
			url : "./helpDesk/searchDocketEscHistory/"+docketNum,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
						for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              		docketEscHistroyTable += "<tr>"		
				              	+"<td>"+obj.serialNo+"</td>"
				              	/* +"<td>"+obj.docketCreatedDt+"</td>" */
				              	+"<td>"+obj.escalatedDate+"</td>"
				              	+"<td>"+obj.escLevel+ "</td>"
				              	/* +"<td>"+obj.fromUserData+"</td>" */
				              	+"<td>"+obj.toUserData+ "</td>"	
				                +"</tr>";
			         }
							$('#docketEscHistoryDiv').append(docketEscHistroyTable);
			} 
		});
	}
	
	$('select[id*=categoryId]').change(function() {
		var categoryId = $("#categoryId").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubCategories/" + categoryId,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subCategoryId').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Category");
                $('#subCategoryId').append(defaultOption);
                defaultOption = $('<option>');
                defaultOption.attr('value',0).text("All");
                $('#subCategoryId').append(defaultOption);
				($.map(data, function(item) {
					var newOption = $('<option>'); 
					newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
					$('#subCategoryId').append(newOption);
				}));
			}
		});
	});
	
	function loadAllDetails() {
		var count=0;
		var tableData="";
		var fromDate=$('#fromdate').val();
		var toDate=$('#todate').val();
		var catagoryId=$('#categoryId').val();
		var subCatId=$('#subCategoryId').val();
		$.ajax({
			type : "POST",
			url : "./readAllDetailsBasedOnSearchWise",
			data:{
				fromDate:fromDate,
				toDate:toDate,
				catagoryId:catagoryId,
				subCatId:subCatId,
				statd:statd,
				id:id
			},
			dataType : "json",
			success : function(response) {
				count=response.length;
				for ( var s = 0, len = response.length; s < len; ++s) {
	              	var obj = response[s];
	              	tableData += "<tr>"	
	                +"<td>"+obj.docketNumber+"</td>"
	              	+"<td>"+obj.docketStatus+"</td>"
	              	+"<td>"+obj.docketCreatedDt+"</td>"
	              	+"<td>"+obj.estResolveTime+ "</td>"
	             	+"<td>"+obj.pending+"</td>" 
	              	+"<td>"+obj.resolvedDate+ "</td>"
	            /*   	+"<td>"+obj.totalTime+"</td>"			 */              	
	              	+"<td>"+obj.categoryName+"</td>"
	              	+"<td>"+obj.subCategoryName+ "</td>"
	             /*  	+"<td>"+obj.consumerName+ "</td>"
	              	+"<td>"+obj.consumerMobileNo+ "</td>" */
	            	+"<td>"+obj.assignedTo+ "</td>"
	              	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>"
	                +"</tr>";
	         }
				
					 $('#mobileTicketsSearch').dataTable().fnDestroy();
					$('#searchTicketDivDetails').html(tableData);
					$('#statusWise').html("Filter Wise Details -"+count);
				
					
				
			},
			 complete:function(response)
			{
				loadSearchAndFilter('mobileTicketsSearch');
			} 
		});
		
		return false;
	}
		
		</script>
						