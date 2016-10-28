<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>DashBoard
			</h1>
		</div>
	</div>

	<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body">
	
					<ul class="nav nav-tabs tabs-pull-right bordered in" id="myTab">
						<a class="btn bg-color-blue txt-color-white" onclick="callDate()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Date</a>
						<a class="btn bg-color-blue txt-color-white" onclick="callCategory()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Category</a>
						<a class="btn bg-color-blue txt-color-white" onclick="callMode()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Select Mode</a>
						<a class="btn bg-color-blue txt-color-white" onclick="clearFilter()" style="padding: 7px 25px; margin-left: 3px; margin-top: 3px;">Clear Filter</a>
							<!-- <li id="tab24"><a data-toggle="tab" href="#s4" onclick="tabClick4(4)"><span
									class="hidden-mobile hidden-tablet">Section wise</span></a></li> -->
									
							<li id="tab23"><a data-toggle="tab" href="#s3" onclick="tabClick3(3)"><span
									class="hidden-mobile hidden-tablet">Sub Division wise</span></a></li>
						
								<li id="tab22"><a data-toggle="tab" href="#s2" onclick="tabClick2(2)"><span
								class="hidden-mobile hidden-tablet">Division wise</span></a></li>
								
								<li id="tab21"><a data-toggle="tab" href="#s1" onclick="tabClick1(1)"><span
								class="hidden-mobile hidden-tablet">Circle wise</span></a></li>
					</ul>

			<div id="myTabContent3" class="tab-content padding-10">
				<div class="tab-pane fade in active" id="s1">
					<div class="row">

									<!-- NEW WIDGET START -->
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Circle Wise Complaint Status </h2>
									</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding" id="monthContent1"><div style='text-align:center'><font size='5px'><span id="titleid1"></span></font></div>
						<table id="dt_basic1" class="table table-striped table-bordered table-hover monthtab1" width="100%">
							<thead>			                
								<tr>
									<th data-hide="phone">Circles</th>
									<th data-class="expand"> Total Complaints</th>
									<th data-hide="phone"> Pending For Registration</th>
									<th data-hide="phone,tablet">Registered&Assigned</th>
									<th data-hide="phone,tablet"> OnHold</th>
									<th data-hide="phone,tablet">Resolved</th>
									<th data-hide="phone,tablet"> Reopen</th>
								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			</div>
					</div>

				</div>





				
							<div class="tab-pane fade in padding-10 no-padding-bottom" id="s2">
		 						<div class="row">

									<!-- NEW WIDGET START -->
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Division Wise Complaint Status </h2>
									</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding" id="monthContent2"><div style='text-align:center'><font size='5px'><span id="titleid2"></span></font></div>
						<table id="dt_basic2" class="table table-striped table-bordered table-hover monthtab2" width="100%">
							<thead>			                
								<tr>
									<th data-hide="phone">Division</th>
									<th data-class="expand"> Total Complaints</th>
									<th data-hide="phone"> Pending For Registration</th>
									<th data-hide="phone,tablet">Registered&Assigned</th>
									<th data-hide="phone,tablet"> OnHold</th>
									<th data-hide="phone,tablet">Resolved</th>
									<th data-hide="phone,tablet"> Reopen</th>
								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			</div>
			</div>
							<div class="tab-pane fade in padding-10 no-padding-bottom" id="s3">
		 						<div class="row">

									<!-- NEW WIDGET START -->
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Sub Division Complaint Status </h2>
									</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding" id="monthContent3"><div style='text-align:center'><font size='5px'><span id="titleid3"></span></font></div>
						<table id="dt_basic3" class="table table-striped table-bordered table-hover monthtab3" width="100%">
							<thead>			                
								<tr>
									<th data-hide="phone">Sub Division</th>
									<th data-class="expand"> Total Complaints</th>
									<th data-hide="phone"> Pending For Registration</th>
									<th data-hide="phone,tablet">Registered&Assigned</th>
									<th data-hide="phone,tablet"> OnHold</th>
									<th data-hide="phone,tablet">Resolved</th>
									<th data-hide="phone,tablet"> Reopen</th>
								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			</div>
			</div>
			
			<%-- <div class="tab-pane fade in padding-10 no-padding-bottom" id="s4">
		 						<div class="row">

									<!-- NEW WIDGET START -->
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Section Wise Complaint Status </h2>
									</header>
				<div>
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding" id="monthContent4"><div style='text-align:center'><font size='5px'>${title}</font></div>
						<table id="dt_basic4" class="table table-striped table-bordered table-hover monthtab4" width="100%">
							<thead>			                
								<tr>
									<th data-hide="phone">Section</th>
									<th data-class="expand"> Total Complaints</th>
									<th data-hide="phone"> Pending For Registration</th>
									<th data-hide="phone,tablet">Registered&Assigned</th>
									<th data-hide="phone,tablet"> OnHold</th>
									<th data-hide="phone,tablet">Resolved</th>
									<th data-hide="phone,tablet"> Reopen</th>
								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			</div>
			</div> --%>
			
		</div>
	</div>
	</div>


</div>
<div id="treeview1" style="display: none"></div>
<div id="treeview2" style="display: none"></div>
<div id="treeview3" style="display: none"></div>
<div class="row" style="display:none" id="selectedDocketDetails">
		
<article class="col-sm-12">
<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon"></span>
								<ul class="nav nav-tabs pull-right in" id="myTab">
									<li id="tabOne" class="active">
										<a data-toggle="tab" href="#s12" onclick="tab1Click()"><i class="fa fa-envelope"></i> <span class="hidden-mobile hidden-tablet">Docket Details</span></a>
									</li>
									<li id="tabTwo">
											<a data-toggle="tab" href="#s22" onclick="tab2Click()"><i class="fa fa-folder"></i> <span class="hidden-mobile hidden-tablet">Docket Histroy</span></a>
									</li>
									<li id="tabThree">
											<a data-toggle="tab" href="#s32" onclick="tab3Click()"><i class="fa fa-clock-o"></i> <span class="hidden-mobile hidden-tablet">Escalation Details</span></a>
									</li>
									</ul>	
										
								</header>
								<div class="no-padding">
									<div class="widget-body" id="tab1">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in padding-12 no-padding-bottom" id="s12"> 	
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
										<div class="tab-pane fade" id="s22">
											<div class="col-sm-12" >
												<br>
													<table class="table table-bordered table-striped">
													<thead>
													<tr>
															<th data-hide="phone,tablet">SI No.</th>
															<th data-hide="phone,tablet">Created Date</th>
															<th data-class="phone,tablet">Status</th>
															<!-- <th>Action</th> -->
															<th data-hide="phone,tablet">Action By</th>
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
										<div class="tab-pane fade" id="s32">	
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
<script>
	var tab123=1;
 var title="";
 var title1="";
 function tabClick1(temp){
	 tab123=temp;
	 $('#s3').hide();
	 $('#s1').show();
	 $('#s2').hide();
	 $('#s4').hide();
	 
 }
 function tabClick2(temp){
	 tab123=temp;
	 $('#s3').hide();
	 $('#s2').show();
	 $('#s1').hide();
	 $('#s4').hide();
 }
 function tabClick3(temp){
	 tab123=temp;
	 $('#s1').hide();
	 $('#s3').show();
	 $('#s2').hide();
	 $('#s4').hide();
 }
 function tabClick4(temp){
	 tab123=temp;
	 $('#s1').hide();
	 $('#s3').hide();
	 $('#s2').hide();
	 $('#s4').show();
 }
 var tab=0;
 var datatitle="";
 $(document).ready(function() {
	 $.ajax({
			url : "./dashBoard/readDashBoardCircle",
			type : "GET",
			dataType : "JSON",
			success : function(response) {
				var dataNew=response[0];
				datatitle=response[1];	
				$("#titleid1").text(response[1]);
				$("#titleid2").text(response[1]);
				$("#titleid3").text(response[1]);
				var htmlTable="<tbody>";
				 $.each(dataNew, function(index, data){
					 if(data.total!=0){
					htmlTable+= "<tr>"
	              	+"<td><a href='#' onclick='getLocationDetails("+data.circleId+",1)'>"+data.circle+"</a></td>"
	              	+"<td>"+data.total+"</td>";
	              	if(data.pending==undefined || data.pending=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails2("+data.circleId+",0,1)'>"+data.pending+"</a></td>";
	              	}
	              	if(data.assigned==undefined || data.assigned=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",1,1)'>"+data.assigned+"</a></td>";
	              	}
	              	if(data.onhold==undefined || data.onhold=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",2,1)'>"+data.onhold+"</a></td>";
	              	}
	              	if(data.resolved==undefined || data.resolved=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",3,1)'>"+data.resolved+"</a></td>";
	              	}
	              	if(data.reopen==undefined || data.reopen=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",4,1)'>"+data.reopen+"</a></td>";
	              	}
	              	htmlTable+="</tr>";
					 }
	         		});
				 htmlTable+="</tbody>";
				 $('#dt_basic1').append(htmlTable);
					var responsiveHelper_datatable_tabletools1 = undefined;
					var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
					$('#dt_basic1')
					.dataTable(
							{
								"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
										+ "t"
										+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
								"oTableTools" : {
									"aButtons" : [
											"copy",
											"csv",
											"xls",
											{
												"sExtends" : "pdf",
												"sTitle" : "SmartAdmin_PDF",
												"sPdfMessage" : "SmartAdmin PDF Export",
												"sPdfSize" : "letter"
											},
											{
												"sExtends" : "print",
												"sMessage" : "<i>(press Esc to close)</i>"
											} ],
									"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
								},
								"autoWidth" : true,
								"preDrawCallback" : function() {
									// Initialize the responsive datatables helper once.
									if (!responsiveHelper_datatable_tabletools1) {
										responsiveHelper_datatable_tabletools1 = new ResponsiveDatatablesHelper(
												$('#dt_basic1'),
												breakpointDefinition);
									}
								},
								"rowCallback" : function(nRow) {
									responsiveHelper_datatable_tabletools1
											.createExpandIcon(nRow);
								},
								"drawCallback" : function(oSettings) {
									responsiveHelper_datatable_tabletools1
											.respond();
								}
							});
			} 
	 });
	 $.ajax({
			url : "./dashBoard/readDashBoardDivision",
			type : "GET",
			dataType : "JSON",
			success : function(response) {
				var dataNew=response;
				var htmlTable="<tbody>";
				 $.each(dataNew, function(index, data){
					 if(data.total!=0){
					htmlTable+= "<tr>"
	              	+"<td><a href='#' onclick='getLocationDetails("+data.circleId+",2)'>"+data.circle+"</a></td>"
	              	+"<td>"+data.total+"</td>";
	              	if(data.pending==undefined || data.pending=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails2("+data.circleId+",0,2)'>"+data.pending+"</a></td>";
	              	}
	              	if(data.assigned==undefined || data.assigned=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",1,2)'>"+data.assigned+"</a></td>";
	              	}
	              	if(data.onhold==undefined || data.onhold=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",2,2)'>"+data.onhold+"</a></td>";
	              	}
	              	if(data.resolved==undefined || data.resolved=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",3,2)'>"+data.resolved+"</a></td>";
	              	}
	              	if(data.reopen==undefined || data.reopen=="0"){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.circleId+",4,2)'>"+data.reopen+"</a></td>";
	              	}
	              	htmlTable+="</tr>";
					 }
	         		});
				 htmlTable+="</tbody>";
				 $('#dt_basic2').append(htmlTable);
				 var responsiveHelper_datatable_tabletools1 = undefined;
					var breakpointDefinition1 = {
							tablet : 1024,
							phone : 480
						};
					$('#dt_basic2')
					.dataTable(
							{
								"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
										+ "t"
										+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
								"oTableTools" : {
									"aButtons" : [
											"copy",
											"csv",
											"xls",
											{
												"sExtends" : "pdf",
												"sTitle" : "SmartAdmin_PDF",
												"sPdfMessage" : "SmartAdmin PDF Export",
												"sPdfSize" : "letter"
											},
											{
												"sExtends" : "print",
												"sMessage" : "<i>(press Esc to close)</i>"
											} ],
									"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
								},
								"autoWidth" : true,
								"preDrawCallback" : function() {
									// Initialize the responsive datatables helper once.
									if (!responsiveHelper_datatable_tabletools1) {
										responsiveHelper_datatable_tabletools1 = new ResponsiveDatatablesHelper(
												$('#dt_basic2'),
												breakpointDefinition1);
									}
								},
								"rowCallback" : function(nRow) {
									responsiveHelper_datatable_tabletools1
											.createExpandIcon(nRow);
								},
								"drawCallback" : function(oSettings) {
									responsiveHelper_datatable_tabletools1
											.respond();
								},
							});
			}
			});
	 
	 
	 $.ajax({
			url : "./dashBoard/readDashBoardSubDivision",
			type : "GET",
			dataType : "JSON",
			success : function(response) {
				var dataNew=response[0];
				var htmlTable="<tbody>";
				 $.each(dataNew, function(index, data){
					 if(data.total!=0){
					htmlTable+= "<tr>"
	              	+"<td><a href='#' onclick='getLocationDetails("+data.parentid+",3)'>"+data.subDivision+"</a></td>"
	              	+"<td>"+data.total+"</td>";
	              	if(data.pending==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails2("+data.parentid+",0,3)'>"+data.pending+"</a></td>";
	              	}
	              	if(data.assigned==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",1,3)'>"+data.assigned+"</a></td>";
	              	}
	              	if(data.onhold==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",2,3)'>"+data.onhold+"</a></td>";
	              	}
	              	if(data.resolved==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",3,3)'>"+data.resolved+"</a></td>";
	              	}
	              	if(data.reopen==undefined){
	              		htmlTable+="<td>0</td>";
	              	}else{
	              		htmlTable+="<td><a href='#' onclick='getLocationDetails1("+data.parentid+",4,3)'>"+data.reopen+"</a></td>";
	              	}
	              	htmlTable+="</tr>";
					 }
	         		});
				 htmlTable+="</tbody>";
				 $('#dt_basic3').append(htmlTable);
					var responsiveHelper_dt_basic = undefined;
					var responsiveHelper_datatable_fixed_column = undefined;
					var responsiveHelper_datatable_col_reorder = undefined;
					var responsiveHelper_datatable_tabletools = undefined;
					var breakpointDefinition = {
							tablet : 1024,
							phone : 480
						};
					$('#dt_basic3')
					.dataTable(
							{
								"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
										+ "t"
										+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
								"oTableTools" : {
									"aButtons" : [
											"copy",
											"csv",
											"xls",
											{
												"sExtends" : "pdf",
												"sTitle" : "SmartAdmin_PDF",
												"sPdfMessage" : "SmartAdmin PDF Export",
												"sPdfSize" : "letter"
											},
											{
												"sExtends" : "print",
												"sMessage" : "<i>(press Esc to close)</i>"
											} ],
									"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
								},
								"autoWidth" : true,
								"preDrawCallback" : function() {
									// Initialize the responsive datatables helper once.
									if (!responsiveHelper_datatable_tabletools) {
										responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
												$('#dt_basic3'),
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
								}
							});
			}
			}); 
	  tab =${tabdata};
		if("2"==tab){
			$("#tab22").addClass("active");
		 	$("#tab21").removeClass();
		 	$("#tab23").removeClass();
		 	$("#tab24").removeClass();
		 	
			$("#s2").addClass("tab-pane fade in active");
		 	 $("#s1").removeClass();
		 	$("#s3").removeClass();
		 	$("#s4").removeClass(); 
		 	
		 	 $('#s1').hide();
			 $('#s2').show();
			 $('#s3').hide();
			 $('#s4').hide();
		}else if("3"==tab){
			$("#tab23").addClass("active");
		 	$("#tab21").removeClass();
		 	$("#tab22").removeClass();
		 	$("#tab24").removeClass();
		 	
			$("#s3").addClass("tab-pane fade in active");
		 	$("#s1").removeClass();
		 	$("#s2").removeClass();
		 	$("#s4").removeClass();
		 	
		 	 $('#s2').hide();
			 $('#s3').show();
			 $('#s1').hide();
			 $('#s4').hide();
		} else if("4"==tab){
			$("#tab24").addClass("active");
		 	$("#tab21").removeClass();
		 	$("#tab22").removeClass();
		 	$("#tab23").removeClass();
		 	
			$("#s4").addClass("tab-pane fade in active");
		 	$("#s1").removeClass();
		 	$("#s2").removeClass();
		 	$("#s3").removeClass();
		 	
		 	 $('#s2').hide();
			 $('#s4').show();
			 $('#s3').hide();
			 $('#s1').hide();
		}else{
			 $("#tab21").addClass("active");
		 	$("#tab23").removeClass();
		 	$("#tab22").removeClass();
		 	$("#tab24").removeClass();
		 	
		 	$("#s1").addClass("tab-pane fade in active");
		 	$("#s2").removeClass();
		 	$("#s3").removeClass();
		 	$("#s4").removeClass();
		 	
		 	 $('#s3').hide();
			 $('#s1').show();
			 $('#s2').hide();
			 $('#s4').hide(); 
		}  
		
 });
 
 function getLocationDetails(sitecode,tab){
	 var buttonName="";
	 if(tab==1){
		 buttonName="Back to Circle";
	 }else if(tab==2){
		 buttonName="Back to Division";
	 }else if(tab==3){
		 buttonName="Back to Sub Division";
	 }else  if(tab==4){
		 buttonName="Back to Section";
	 }
	 
		$.ajax({
			type : "POST",
			url : "./dashboard/getSubDivisionBasedOnSiteCode/" +sitecode,
			dataType : "JSON",
			success : function(response) {
			$('.monthTabDetail'+tab).remove();
			var htmlTable = "<div class='monthTabDetail"+tab+"' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth("+tab+")' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>"+buttonName+"</a>" 
			+"<div style='text-align:center'><font size='5px'>"+title+"</font></div>"	
			+"<br><table id='datatable_tabletools"+tab+"' width='100%' class='table table-striped table-bordered table-hover'>"
			+"<thead>"
			+"<tr>"
			+"<th data-hide='phone'>Docket No.</th>"
			+"<th data-hide='phone'>Status</th>"
			+"<th data-class='expand'>Created Date</th>"
			+"<th>Category</th>"
			+"<th data-hide='phone'>SubCategory</th>"
			+"<th data-hide='phone,tablet'>Summary</th>"
			+"<th data-hide='phone,tablet'>Mode</th>"
			+"<th data-hide='phone,tablet'>Consumer Name</th>"
			+"<th data-hide='phone,tablet'>Mobile No.</th>"
			+"<th data-hide='phone,tablet'></th>"
		    +"</tr>"
					+"</thead>"
					+"<tbody>";
					var dataNew=response;
					 $.each(dataNew, function(index, data){
						
						htmlTable+= "<tr>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketStatus+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.docketSource+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
							+"</tr>";
						 
		         		});
				htmlTable +=""		
					+"</tbody>"
					+"</table></div>";
				
			$('.monthtab'+tab).hide();
			$("#dt_basic"+tab+"_wrapper").hide();
			$('#monthContent'+tab).append(htmlTable);
			
			pageSetUp();
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};
			$('#datatable_tabletools'+tab)
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
						"oTableTools" : {
							"aButtons" : [
									"copy",
									"csv",
									"xls",
									{
										"sExtends" : "pdf",
										"sTitle" : "SmartAdmin_PDF",
										"sPdfMessage" : "SmartAdmin PDF Export",
										"sPdfSize" : "letter"
									},
									{
										"sExtends" : "print",
										"sMessage" : "<i>(press Esc to close)</i>"
									} ],
							"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
						},
						"autoWidth" : true,
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_datatable_tabletools) {
								responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
										$('#datatable_tabletools'+tab),
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
						}
					});
			}
		});
	}
 function getLocationDetails1(sitecode,categoryId,tab){
	 var buttonName="";
	 if(tab==1){
		 buttonName="Back to Circle";
	 }else if(tab==2){
		 buttonName="Back to Division";
	 }else if(tab==3){
		 buttonName="Back to Sub Division";
	 }else  if(tab==4){
		 buttonName="Back to Section";
	 }
		$.ajax({
			type : "POST",
			url : "./dashboard/getSubDivisionBasedOnCategory/"+sitecode+"/"+categoryId,
			dataType : "JSON",
			success : function(response) {
			$('.monthTabDetail'+tab).remove();
			var htmlTable = "<div class='monthTabDetail"+tab+"' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth("+tab+")' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>"+buttonName+"</a>" 
			+"<br><table id='datatable_tabletools"+tab+"' width='100%' class='table table-striped table-bordered table-hover'>"
			+"<thead>"
			+"<tr>"
			+"<th data-hide='phone'>Docket No.</th>"
			+"<th data-hide='phone'>Status</th>"
			+"<th data-class='expand'>Created Date</th>"
			+"<th>Category</th>"
			+"<th data-hide='phone'>SubCategory</th>"
			+"<th data-hide='phone,tablet'>Summary</th>"
			+"<th data-hide='phone,tablet'>Mode</th>"
			+"<th data-hide='phone,tablet'>Consumer Name</th>"
			+"<th data-hide='phone,tablet'>Mobile No.</th>"
			+"<th data-hide='phone,tablet'>Assigned To.</th>"
			+"<th data-hide='phone,tablet'></th>"
		    +"</tr>"
					+"</thead>"
					+"<tbody>";
					var dataNew=response;
					 $.each(dataNew, function(index, data){
						
						htmlTable+= "<tr>"
							+"<td>"+data.docketNumber+"</td>"
							+"<td>"+data.docketStatus+"</td>"
							+"<td>"+data.docketCreatedDt+"</td>"
							+"<td>"+data.categoryName+"</td>"
							+"<td>"+data.subCategoryName+"</td>"
							+"<td>"+data.docketSummary+"</td> "
							+"<td>"+data.docketSource+"</td>"
							+"<td>"+data.consumerName+"</td>"
							+"<td>"+data.consumerMobileNo+"</td>"
							+"<td colspan='5'>"+data.assinedName+" - "+data.officialEmaill+" - "+data.designation+" - "+data.officialMobileNo+"</td>"
							+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+data.docketNumber+")'>View</a></td>"
							+"</tr>";
						 
		         		});
				htmlTable +=""		
					+"</tbody>"
					+"</table></div>";
				
			$('.monthtab'+tab).hide();
			$("#dt_basic"+tab+"_wrapper").hide();
			$('#monthContent'+tab).append(htmlTable);
			
			pageSetUp();
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};
			$('#datatable_tabletools'+tab)
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
						"oTableTools" : {
							"aButtons" : [
									"copy",
									"csv",
									"xls",
									{
										"sExtends" : "pdf",
										"sTitle" : "SmartAdmin_PDF",
										"sPdfMessage" : "SmartAdmin PDF Export",
										"sPdfSize" : "letter"
									},
									{
										"sExtends" : "print",
										"sMessage" : "Generated by BCITS <i>(press Esc to close)</i>"
									} ],
							"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
						},
						"autoWidth" : true,
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_datatable_tabletools) {
								responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
										$('#datatable_tabletools'+tab),
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
						}
					});
			}
		});
	}
 function getLocationDetails2(sitecode,categoryId,tab){
	 var buttonName="";
	 if(tab==1){
		 buttonName="Back to Circle";
	 }else if(tab==2){
		 buttonName="Back to Division";
	 }else if(tab==3){
		 buttonName="Back to Sub Division";
	 }else  if(tab==4){
		 buttonName="Back to Section";
	 }
		$.ajax({
			type : "POST",
			url : "./dashboard/getSubDivisionBasedOnCategory1/"+sitecode+"/"+categoryId,
			dataType : "JSON",
			success : function(response) {
			$('.monthTabDetail').remove();
			var htmlTable = "<div class='monthTabDetail"+tab+"' style='overflow: scroll;'><br><a href='javascript:void(0);' onclick='backToMonth("+tab+")' class='btn btn-labeled btn-default'> <span class='btn-label'><i class='glyphicon glyphicon-chevron-left'></i></span>"+buttonName+"</a>" 
			+"<br><table id='datatable_tabletools"+tab+"' width='100%' class='table table-striped table-bordered table-hover'>"
			+"<thead>"
			+"<tr>"
			+"<th data-hide='phone'>Docket No.</th>"
			+"<th data-class='expand'>Created Date</th>"
			+"<th data-class='expand'>Time Lapsed</th>"
			+"<th data-hide='phone,tablet'>Mode</th>"
			+"<th data-hide='phone,tablet'>Consumer Name</th>"
			+"<th data-hide='phone'>Mobile No.</th>"
			+"<th data-hide='phone,tablet'>Email Id</th>"
			+"<th data-hide='phone,tablet'>Facebook Id</th>"
			/* +"<th data-hide='phone,tablet'></th>" */
		    +"</tr>"
					+"</thead>"
					+"<tbody>";
					var dataNew=response;
					 $.each(dataNew, function(index, obj){
						
						htmlTable+= "<tr>"
							+"<td>"+obj.docketNumber+"</td>"
							+"<td>"+obj.docketCreatedDt+"</td>"
							+"<td>"+obj.elapsedTime+"</td>"
			              	+"<td>"+obj.docketSource+ "</td>"
			              	+"<td>"+obj.consumerName+"</td>"
			              	+"<td>"+obj.consumerMobileNo+ "</td>"
			              	+"<td>"+obj.consumerEmailId+"</td>"			              	
			              	+"<td>"+obj.facebookId+"</td>"
			              /* 	+"<td><a class='btn btn-primary' onclick='docketDetailsViewPopUp("+obj.docketNumber+")'>View</a></td>" */
							+"</tr>";
						 
		         		});
				htmlTable +=""		
					+"</tbody>"
					+"</table></div>";
				
			$('.monthtab'+tab).hide();
			$("#dt_basic"+tab+"_wrapper").hide();
			$('#monthContent'+tab).append(htmlTable);
			
			pageSetUp();
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};
			$('#datatable_tabletools'+tab)
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
						"oTableTools" : {
							"aButtons" : [
									"copy",
									"csv",
									"xls",
									{
										"sExtends" : "pdf",
										"sTitle" : "SmartAdmin_PDF",
										"sPdfMessage" : "SmartAdmin PDF Export",
										"sPdfSize" : "letter"
									},
									{
										"sExtends" : "print",
										"sMessage" : "Generated by BCITS <i>(press Esc to close)</i>"
									} ],
							"sSwfPath" : "./resources/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
						},
						"autoWidth" : true,
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_datatable_tabletools) {
								responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
										$('#datatable_tabletools'+tab),
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
						}
					});
			}
		});
	}
 function docketDetailsViewPopUp(docNo)
 {
 	docketNo = docNo;
 	dialog = $("#selectedDocketDetails").dialog({
 		autoOpen : false,
 		width : 'auto',
 		height : 'auto',
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
 		url : "./helpDesk/searchDocketNumber/"+docketNo,
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
 								+"<td colspan='5'>"+obj.assinedName+" - "+obj.assignedToEmail+" - "+obj.designation+" - "+obj.officialMobileNo+" - "+obj.officeName+"</td>"
 								/* +"<th>Official Mobile No.</th>"
 								+"<td>"+obj.officialMobileNo+"</td>" */
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
 		url : "./helpDesk/searchDocketHistory/"+docketNo,
 		async: false,
 		dataType : "JSON",
 		success : function(response) 
 		{	    
 					for ( var s = 0, len = response.length; s < len; ++s) {
 		              	var obj = response[s];
 		              	docketHistroyTable += "<tr>"		
 		              	+"<td>"+obj.serialNo+"</td>"
 		              	+"<td>"+obj.docketCreatedDt+"</td>"
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
 		url : "./helpDesk/searchDocketEscHistory/"+docketNo,
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
 function backToMonth(tab){
		$('.monthtab'+tab).show();
		$('.monthTabDetail'+tab).remove();
		$("#dt_basic"+tab+"_wrapper").show();
	}
 
function clearFilter(){
	$.ajax({
		url : "./dashboard/clearFilter",
		type : "GET",
		dataType : "JSON",
		async : false,
});	
	window.location.reload(true);
}

function callDate(){
	var btDialog = $("#treeview1");
	btDialog.kendoWindow({
		width : "400",
		height : "auto",
		modal : true,
		draggable : true,
		position : {
			top : 100
		},
		title : "Select Date To Filter"
	}).data("kendoWindow").center().open();
	
	btDialog.kendoWindow("open");
	$("#treeview1").html("");		
	$("#treeview1").append("<form id='exportToExcelFileForm'><table id='exportExcel' style='margin-top: 8px;height: 130px'></table> </form>");
	$("#exportExcel").append("<tr><td> From Date*</td> <td><input id='fromDate1' style='width:200px' required='true' data-errormessage-value-missing='From Date required' /></td> </tr> <tr><td> To Date*</td> <td><input id='toDate1' required='true' data-errormessage-value-missing='To Date required'  style='width:200px'/></td> </tr>");
	$("#fromDate1").kendoDatePicker({    
		 start: "year",                             
       depth: "month",
		format: "dd/MM/yyyy"
	 });
	$("#toDate1").kendoDatePicker({    
		 start: "year",                             
       depth: "month",
		format: "dd/MM/yyyy"
  	 	
        
 });
	$("#exportExcel").append("<tr><td>&nbsp;</td><td><button id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='getDataBasedOnDate()'>View</button> </td></tr>");
}
var date1="";
var date2="";
function getDataBasedOnDate() {
	date1 = $("#fromDate1").val();
	date2 = $("#toDate1").val();
	if(date1==""){
		return false;
	}
	else if(date2==""){
		return false;
	}
	 $.ajax({
			url : "./registerMaster/readRegisterBasedOnDate",
			type : "GET",
			dataType : "text",
			async : false,
		data:{
			fromDate:date1,
			toDate:date2,
			tab:tab123,
		},
		success : function(result) {
			//closeDialog();
			window.location.reload(true);
		},

	});
		
		function closeDialog() {
			var btDialog = $("#treeview1");
			btDialog.kendoWindow({
				width : "400",
				height : "auto",
				modal : true,
				draggable : true,
				position : {
					top : 100
				},
				title : "Select Date To Filter"
			}).data("kendoWindow").center().close();
			btDialog.kendoWindow("close");
		} 
	
	} 
var Cat="";
function callCategory(){
	var btDialog = $("#treeview2");
	btDialog.kendoWindow({
		width : "400",
		height : "auto",
		modal : true,
		draggable : true,
		position : {
			top : 100
		},
		title : "Select Category To Filter"
	}).data("kendoWindow").center().open();
	
	btDialog.kendoWindow("open");
	$("#treeview2").html("");
		 $("#treeview2").append("<form id='getRegisterForm1'><table id='getRegister2' style='margin-top: 5px;height: 100px'></table> </form>");
		 $("#getRegister2").append("<tr><td> <label for='category'>Category * :</label></td> <td><input id='category' required='true' style='width:200px'/></td> </tr><tr><td>&nbsp;</td</tr>");
		 $("#category").kendoDropDownList({
		    dataTextField: "categoryName",
		    dataValueField: "categoryId",
		    optionLabel : {
		    	categoryName : "Select",
		    	categoryId : "",
			},
		    dataSource : {
				transport : {
					read : {
						url : "./dashboard/categoryList",
						type : "POST"
					}
				}
			},
		    change: function(e) {
		        var value = this.value();
		        cat=this.text();
		      }
		});
	 $("#getRegister2").append("<tr><td>&nbsp;</td><td><button type='submit' id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='categoryfunction()'>View</button> </td></tr>");
}

function categoryfunction(){
	status = $("#category").val();
	if(status==""){
		return false;
	}
	$.ajax({
		url : "./dashboard/category",
		type : "GET",
		dataType : "text",
		async : false,
	data:{
		category:status,
		categoryName:cat,
		tab:tab123,
	},
	success : function(result) {
		//closeDialog();
		window.location.reload(true);
	},
});
}
function callMode(){
	var btDialog = $("#treeview3");
	btDialog.kendoWindow({
		width : "400",
		height : "auto",
		modal : true,
		draggable : true,
		position : {
			top : 100
		},
		title : "Select Mode To Filter"
	}).data("kendoWindow").center().open();
	
	btDialog.kendoWindow("open");
	$("#treeview3").html("");
		 $("#treeview3").append("<form id='getRegisterForm2'><table id='getRegister3' style='margin-top: 5px;height: 100px'></table> </form>");
		 $("#getRegister3").append("<tr><td> <label for='category'>Mode * :</label></td> <td><input id='mode' style='width:200px'/></td> </tr><tr><td>&nbsp;</td</tr>");
		 
		 $("#mode").kendoDropDownList({
		    dataTextField: "parentName1",
		    dataValueField: "parentId1",
		    dataSource: [
					        { parentName1: "Phone", parentId1: "Phone"},
					        { parentName1: "Web", parentId1: "Web"},
					        { parentName1: "Sms", parentId1: "Sms"},
					        { parentName1: "Email", parentId1: "Email"},
					        { parentName1: "Facebook", parentId1: "Facebook"},
					        { parentName1: "Hand Written", parentId1: "Hand Written" }
					    ],
		});
	 $("#getRegister3").append("<tr><td>&nbsp;</td><td><button type='submit' id='getEmployeeButton' class='k-button' style='padding-left: 10px' onclick='modefunction()'>View</button> </td></tr>");
}
function modefunction(){
	status = $("#mode").val();
	if(status==""){
		return false;
	}
	$.ajax({
		url : "./dashboard/mode",
		type : "GET",
		dataType : "text",
		async : false,
	data:{
		category:status,
		tab:tab123,
	},
	success : function(result) {
		//closeDialog();
		window.location.reload(true);
	},

});
}

</script>
<style>
 .padding-10 {
   padding: 0 !important;
}

 .jarviswidget {
    margin: 0px 0px 0px;
    position: relative;
    border-radius: 0px;
    padding: 0px;
} 



</style>