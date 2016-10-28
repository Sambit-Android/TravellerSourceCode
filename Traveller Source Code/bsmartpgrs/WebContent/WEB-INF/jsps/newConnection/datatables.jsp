<%@include file="/common/taglibs.jsp"%>
 <%@include file="/common/commonPage.jsp"%>


<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<!-- SPARKLINES -->
<script src="./resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<script src="./resources/js/plugin/superbox/superbox.min.js"></script>
<script src="./resources/js/plugin/pace/pace.min.js"></script>
<script src="./resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
<script src="./resources/js/notification/SmartNotification.min.js"></script>
<script src="./resources/js/smartwidgets/jarvis.widget.min.js"></script>
<script src="./resources/js/speech/voicecommand.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="./resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="./resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>

					
				
<div style="display: none;">
<table>
<tr><td class="appphase">${appPhase}<td></tr>
<tr><td class="appStatus">${appStatus}<td></tr>

</table>
</div>

<div id="content">
<section id="widget-grid" class="">
           
		   
		
	<div class="row">
		<div class="col-sm-12">
		
		 <div>
           
           <c:if test="${not empty fVerification}">
				<script>
					var result = "${fVerification}";
					alert(result);
				</script>
						
			   <c:remove var="fVerification" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty htAuthenticateforWorkOrder}">
				<script>
					var result = "${htAuthenticateforWorkOrder}";
					alert(result);
				</script>
						
			   <c:remove var="htAuthenticateforWorkOrder" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty htAuthenticate}">
				<script>
					var result = "${htAuthenticate}";
					alert(result);
				</script>
						
			   <c:remove var="htAuthenticate" scope="session" />
		   </c:if>
           
           </div>
           
           
           <div>
           
           <c:if test="${not empty htAuthenticateMeterPurchase}">
				<script>
					var result = "${htAuthenticateMeterPurchase}";
					alert(result);
				</script>
						
			   <c:remove var="htAuthenticateMeterPurchase" scope="session" />
		   </c:if>
           
           </div>
           
           
           <div>
             <c:if test="${not empty taqcinsertionResult}">
				<script>
					var result = "${taqcinsertionResult}";
					alert(result);
				</script>
						
			  <c:remove var="taqcinsertionResult" scope="session" />
		   </c:if>
           
           </div>
           
           
           
            <div>
           
           <c:if test="${not empty applicationVerification}">
				<script>
					var result = "${applicationVerification}";
					 alert(result);
				</script>
						
			   <c:remove var="applicationVerification" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty estmn}">
				<script>
					var result = "${estmn}";
					alert(result);
				</script>
						
			   <c:remove var="estmn" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty powersenction}">
				<script>
					var result = "${powersenction}";
					 alert(result);
				</script>
						
			   <c:remove var="powersenction" scope="session" />
		   </c:if>
           
           </div>
           
            <div>
           
           <c:if test="${not empty worder}">
				<script>
					var result = "${worder}";
					alert(result);
				</script>
						
			   <c:remove var="worder" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty mporder}">
				<script>
					var result = "${mporder}";
					alert(result);
				</script>
						
			   <c:remove var="mporder" scope="session" />
		   </c:if>
           
           </div>
           
           <div>
           
           <c:if test="${not empty wcReport}">
				<script>
					var result = "${wcReport}";
					alert(result);
				</script>
						
			   <c:remove var="wcReport" scope="session" />
		   </c:if>
           
           </div>
           
            <div>
           
           <c:if test="${not empty installtiionService}">
				<script>
					var result = "${installtiionService}";
					alert(result);
				</script>
						
			   <c:remove var="installtiionService" scope="session" />
		   </c:if>
		   
		   <c:if test="${not empty sendingToWorkOrder}">
				<script>
					var result = "${sendingToWorkOrder}";
					alert(result);
				</script>
						
			   <c:remove var="sendingToWorkOrder" scope="session" />
		   </c:if>
		   
		   
           
           </div>
           
		  <div class="col-sm-100"> 
			<div class="jarviswidget jarviswidget-color-blueDark"  data-widget-editbutton="true" data-widget-colorbutton="true">
								
								
								
								 <header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><b>${appPhase} - ${appStatus} </b></h2> 
				
								</header> 
				
								<!-- widget div-->
								<div style='overflow: scroll;'>
						
							<c:choose >
								<c:when test="${appPhase =='Power Sanction'}">
								
										<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
											<div class="col-sm-3" >
											
											 <c:choose >
												<c:when test="${sessionScope.designation =='AE(Elec)'}">
													<a class="btn bg-color-orange btn-lg txt-color-white" style="width:300px;" onclick='applicationauthority()'>In Progress </a>
												</c:when>
												<c:otherwise>
													<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:300px;">In Progress </a>	
												</c:otherwise>
											</c:choose>
											
											
											
																	
											</div>		
											
											<div class="col-sm-3">
											<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:300px;">Completed </a>						           
											</div>		
											
											<div class="col-sm-3">
										            
										     <c:choose >
												<c:when test="${sessionScope.designation =='AE(Elec)'}">
													<a class="btn bg-color-redLight btn-lg txt-color-white" style="width:300px;" onclick='applicationauthority()'>On Hold </a>
												</c:when>
												<c:otherwise>
													<a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:300px;">On Hold </a>	
												</c:otherwise>
											</c:choose>
										            
										            
										            
											</div>
											
											<div class="col-sm-3">
										            <a class="btn bg-color-blueLight btn-lg txt-color-white" id="redirectData5" style="width:300px;">Clarifications </a>
											</div>
											
								         </div>
								
								</c:when>
								<c:otherwise>
										<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
											<div class="col-sm-4" >
											<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:300px;">In Progress </a>							
											</div>		
											
											<div class="col-sm-4">
											<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:300px;">Completed </a>						           
											</div>		
											
											<div class="col-sm-4">
										            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:300px;">On Hold </a>
											</div>
											
								         </div>

								</c:otherwise>
							</c:choose>
							
							
						
				                    
									<!-- widget content -->
									<div class="widget-body no-padding">
				                        
										<table id="datatable_fixed_column" class="table table-striped table-bordered">
					
									        <thead>
									        
									            <tr>
								                    <th >ApplicationID</th>
								                    <th>Reg.Date</th>
								                    <th> TimeLine <br>Log Details</th>
								                    <th >Days&nbsp;Left&nbsp;For ${appPhase}</th>
								                    <th data-hide="phone">Name</th>
								                     <th data-hide="phone">City</th>
								                    <th data-hide="phone,tablet">Load KW&nbsp;/&nbsp;HP&nbsp;/&nbsp;KVA</th>
								                    <th data-hide="phone,tablet">Tariff</th>
								                    <th data-hide="phone,tablet">Nature OF Inst</th>
								                    <!-- <th data-hide="phone,tablet">Locality</th> -->
									            </tr>
									        </thead>
				
									        <tbody>
									        <c:forEach var="app" items="${appServiceDetailsListOnStatus}">
									            <tr>
									                <c:choose>
									                <c:when test="${appStatus=='Completed' || appStatus=='Delay'}">
									                <td><b>${app.applicationid}</b></td>
									                </c:when>
									                <c:otherwise>
									                  <td class='appClass'><b><a href='#' onclick="redirect('${app.applicationid}','${app.tariffdesc}','${app.loadkw}','${app.loadhp}','${app.loadkva}')">${app.applicationid}</a></b></td>
									                </c:otherwise>
									                </c:choose>
                                                      
									                <td><fmt:formatDate value="${app.appregdate}" pattern="dd-MM-yyyy"/> </td>
									                <td ><a href='#' onclick="timeline('${app.applicationid}')"><p style="color:green "><jsp:useBean id="now" class="java.util.Date" />
                                                      <fmt:parseNumber
                                                       value="${(now.time - app.appregdate.time) / (1000*60*60*24) }"
                                                      integerOnly="true" /> </p></a></td>
                                                     <td><b>${app.daysleft}</b></td>
                                                     <%-- <c:choose>
                                                      <c:when test="${app.daysleft == 'NA'}">
                                                       <td><b>${app.daysleft}</b></td>
                                                      </c:when>
                                                        <c:when test="${app.daysleft >= '0' }">
                                                       <td><b>${app.daysleft}</b></td>
                                                       </c:when>
                                                       <c:otherwise>
                                                       <td style="color: red"><b>${app.daysleft}</b></td>
                                                       
                                                       </c:otherwise>
                                                       </c:choose> --%>
									                <td>${app.name}${app.nameoforg}</td>
									               <td>${app.citypres}</td>
									               
									               
									               <td>${app.loadkw}&nbsp;/&nbsp;${app.loadhp}&nbsp;/&nbsp;${app.loadkva}</td>
									               
									               <%--  <td>${app.loadkw}</td>
									                <td>${app.loadhp}</td>
									                <td>${app.loadkva}</td> --%>
									                
									                
									                
									                <td>${app.tariffdesc}</td>
									                <td>${app.natureofinst}</td>
									              <%--   <td>${app.locality}</td> --%>
									                
									                
									               
									            </tr>
									             </c:forEach>
									          					        </tbody>
									
										</table>
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div></div>
			 
			
			
		</div>


	</div></section>
</div>	

<div id="addtab" title="Timeline" style="display:none">
        <table class="table table-bordered table-striped">
												 	<thead>
										     <tr>
											<th width="220">Application Phase</th>
											<th>Reference No.</th>
											<th>Date</th>											
										</tr>
									</thead>
									<tbody id="searchTicketDiv">
									
									
									</tbody>
															
									</table>   
				

 </div>
           
  <div id="addtab1" title="Sanction Authority" style="display:none;text-align: center;">
  Sorry, You don't have authority to access this link
 </div>


<script type="text/javascript">


function applicationauthority(docketNo) {
	dialog = $("#addtab1").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,

	}).dialog("open");
}

function timeline(applicationId){
	//alert(appId);
	var tableData = "";
	$("#searchTicketDiv").empty();
var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;	
	$.ajax
	({			
		type : "POST",
		url : "./Timeline",
		async: false,
		dataType : "JSON",
		data : {

			applicationId : applicationId,
			appPhase:appPhase,
		},
		success : function(response) 
		{	    
					for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		             	tableData += "<tr>"				              	
		              	tableData+="<td>"+obj.PhaseCompleted+"</td>"
		              	+"<td>"+obj.refNo+"</td>"
		              	+"<td>"+obj.date+ "</td>"	              	
		              	
		                +"</tr>";
		         }
					
						$('#searchTicketDiv').append(tableData);
						
						//callTableSearch();
						x=1;

		} 
	
	});
	openTimeline(applicationId);
}


function openTimeline(applicationId){
	var tle="Timeline/Log Details For Application ID - "+applicationId;
	dialog = $("#addtab").dialog({
		autoOpen : false,
		width : 500,
		resizable : false,
		modal : true,
		title:tle,
		
	}).dialog("open");	
}

var responsiveHelper_datatable_col_reorder = undefined;
var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	
$('#redirectData2').click(function () {
	var appStatus="In Progress";
	var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
	window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
    
 });

 
$('#redirectData3').click(function () {
	var appStatus="Completed";
	var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
	window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
    
 });
 
 
$('#redirectData4').click(function () {
	var appStatus="Pending";
	var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
	window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
    
 });
 
$('#redirectData5').click(function () {
	var appStatus="Clarifications";
	var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
	window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
    
 });


function redirect(applicationId,tariff,loadKW,loadHP,loadKVA){
    var desig="${designation}";
    var result = tariff.indexOf("HT") > -1;
	var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
	var appStatus = document.getElementsByClassName('appStatus')[0].innerHTML;
	
	if(appStatus=='Completed'){
		
	}else if(appStatus=='Delay'){
		
	}else{
		if(appPhase=='Field Verification Acceptance' && appStatus=='In Progress'){
			
			if(desig=='AE(Tech)' || desig=='NCMS Super User'){
				 window.location.href="./finalForm?applicationId="+applicationId+"&appPhase="+appPhase+"&appStatus="+appStatus;
			}else {
		    	 alert("AE(Tech) only can do Field Verification Acceptance");
			}
		}else if(desig=='AEE(Tech)' && result && appPhase=='Power Sanction' && appStatus=='In Progress'){
			    alert("You don't have access to Sanction Power for HT Application");
		}else{
		        window.location.href="./finalForm?applicationId="+applicationId+"&appPhase="+appPhase+"&appStatus="+appStatus;	
		}
	}
}
 
 
 
$('#datatable_fixed_column').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});



 
			// DO NOT REMOVE : GLOBAL FUNCTIONS!
$(document).ready(function() {
	pageSetUp();
});

var cells = document.getElementsByClassName('appClass');


$(document).on('click','.appClass' ,function(){
	//alert(this.textContent);
    /* var $item = $(this).closest("tr").find('td');
    $.each($item, function(key, value){
        alert($(value).text()); 
    })*/
});


</script>
<style>

th {
	vertical-align: middle !important
}

.popover-content {
	overflow: overlay;
	max-height: 250px;
}

.sparkline {
	width: 100% !important
}

.easy-pie-title {
	overflow: visible !important;
}
</style>
