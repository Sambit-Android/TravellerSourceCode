<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
 <style>
#btnId
{
  font-size: 14px;
 background-color: #AAD4FF;
}
</style> 
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
				  if('${bmdDataList}'!="")
					{	  			
						$("#bmdListId").show();
						$('#sdoCode').val('${sdcd}');
						$('#bmdReader').val('${bmdRdr}');
					}
				  
				   if('${hhbmList}'!="")
					{	  			
						$("#divTable").show();
						$('#sdoCode').val('${sdcd}');
						$('#bmdReader').val('${bmdRdr}');
					} 
				 
				  
					   if('${bmdList}'!="")
						{
							$("#bmdId").show();
						} 
					 
					 
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	  // sss();
   	    	if('${sdoCodeval}'!="" && '${sdcd}'=="")
	        {
	        	$('#sdoCode').val('${sdoCodeval}');
	        }
	        /* else if('${sdoCodeval}'=="")
	        {
	        	$('#sdoCode').val('1');
	        } */
	        $('#sdoCode,#bmdReader').select2();
   	    	$('#MIS-Reports-photoBilling').addClass('start active ,selected');
	    	   	  $("#dash-board,#user-location,#moduleConfig,#meterData-Acquisition,#MIS-Reports,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
	    		   });
  
  function displayBmd(sdoCode)
  {
	  
	  $.ajax(
				{
					type : "GET",
					url : "./getDistinctBmds/" + sdoCode,
					cache:false,
				    success : function(response )
			  		  {
				    	  if(response!=null ||response!=" ")
				    	{
				    		$("#bmdId").show(); 
				    		var html='<div><label>MR READER</label><select   class="form-control" id="bmdReader" name="bmdReader"><option value="noValue">Select</option>';
					    	for(var j=0;j<response.length;j++)
				    		{
					    		html += '<option value="'+response[j]+'" >'+response[j]+'</option>';
				    		}
				    		html+='</div>';
				    		$('#bmdId').html(html);
				    		$('#bmdReader').select2();
				    	} 
				    	 
			    	}
				}		
		       );
  }
  
  function validation()
  {
	  var sdCd=document.getElementById("sdoCode").value;
	  var bmdRd=document.getElementById("bmdReader").value;
	  if(sdCd=="noValue")
	  {
		    bootbox.alert("Please Select the SDO CODE.");
			return false;
	  }
	  if(bmdRd=="noValue")
	  {
		    bootbox.alert("Please Select the MR READER");
			return false;
	  }
  }
  
  
  </script>
<div class="page-content" >
				<c:if test="${results ne 'notDisplay'}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span style="color: red;font-size:15px;">${results}</span>
					</div>
				</c:if>
 <!-- BEGIN PORTLET-->   
               <div class="portlet box green">
                  <div class="portlet-title">
                     <div class="caption"><i class="fa fa-reorder"></i>GPS-Meter Reader Wise Photo Billing Status for past 30 days </div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:;" class="reload"></a>
                     </div>
                  </div>
                  <div class="portlet-body">
                   <div class="portlet-body form">
                     
							<form role="form" id="hhbmInfo" >

								<div class="form-body">
								
								    <div class="form-group" >
										<label >SECTION</label>
										<select   class="form-control" id="sdoCode" name="sdoCode" onchange="displayBmd(this.value);">
													<%-- <c:if test="${not empty sdoVal}">
													<option value="${sdoVal}">${sdoVal}</option>
													</c:if> --%>
													<option value='noValue'>Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
													  <option value="${sdoVal[0]}">${sdoVal[1]}-${sdoVal[0]}</option>
													</c:forEach>
										</select>
									</div>
								
									 <div class="form-group" id="bmdId" style="display: none;">
										<label >MR CODE</label>
										<select   class="form-control" id="bmdReader" name="bmdReader" >
													<option value="noValue">Select</option>
													<c:forEach items="${bmdList}" var="bmdList1">
													  <option value="${bmdList1}">${bmdList1}</option>
													</c:forEach>
										</select> 
									</div> 
									
								 <div class="form-actions" >
									<button type="submit" class="btn green" formaction="./getGPSReadersData" formmethod="post"  onclick="return validation();">Show</button>
									<button type="submit" class="btn default" formaction="./meterReaderwiseView" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>
                     </div>
                  </div>
                 
                        
                 
					<div class="portlet box blue" id="bmdListId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>GPS- MR Reader Wise Bill Details for past 30 days</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div> -->
						</div>
						<div class="portlet-body" >
						<c:set var="count" value="0" scope="page" />
						<c:forEach var="element" items="${bmdDataList}">
						<form:form id="bmdButton${count}" action="" modelAttribute="hhbmDownLoad" commandName="hhbmDownLoad" method="post" cssStyle="margin-left:5px;display:inline-block;">
						    <form:input path="" type="text" hidden="true" id="value" name="value" value="meterWise"></form:input>
						    <form:input path="sdoCode" type="text" hidden="true" id="sCode" name="sCode" value="${sCode}"></form:input>
						    <fmt:formatDate pattern="yyyy-MM-dd" value="${element[1]}" var="billDateVal"/>
							<form:input path="billDate" type="text" hidden="true" id="bDate" name="bDate"  value='${billDateVal}'></form:input>
							<form:input path="billMonth" type="text" hidden="true" id="bMonth" name="bMonth" value="${element[2]}"></form:input>
							<form:input path="bmd_Reading" type="text" hidden="true" id="bmdRr" name="bmdRr" value="${bmdReader}"></form:input>
						 <button id="btnId" type="submit" title="Click here to see bill details"  style="text-align: left;" onclick="return showGPS(this.form.id);">Bill Date&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;<fmt:formatDate pattern="MMM-dd"  value="${element[1]}" /><br><br>No.of Bills &nbsp;:&nbsp;&nbsp;&nbsp;${element[0]}</button> 
						</form:form>
						<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
						</div>
					</div>
					<div id="imageee" hidden="true" style="text-align: center;">
                         <h3 id="loadingText">Loading..... Please wait.
						</h3>
						 <img alt="image" src="./resources/bsmart.lib.js/loading.gif" style="width:4%;height: 4%;"> 
						</div>
					<div class="row">				
			
					<!-- BEGIN MARKERS PORTLET-->
					<div class="portlet box green" style="display: none;" id="gmapsContent">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>Markers</div>
							<div class="tools">								
								<a href="#" class="close" onclick="hideMap()"></a>
							</div>
						</div>
				<div class="portlet-body">
							<div id="gmap_marker" class="gmaps" style="height: 500px"></div>				 
               <!-- END PORTLET-->
               </div>
               </div>
              
              <jsp:include page="modalPhoto.jsp" />
               </div>
               
</div>