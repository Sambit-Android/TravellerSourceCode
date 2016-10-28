<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
  <style>
#bmdButton
{
	margin-bottom: 10px;
	display:inline-block; 
}
#btnId
{
  font-size: 14px;
 background-color: #AAD4FF;
}
</style>
 
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
	  
	  /* var imagesize = $('#imgid').width();
	    
	    $('.zoomout').on('click', function(){
	        imagesize = imagesize - 50;
	        $('#imgid').width(imagesize);
	    });
	    
	    $('.zoomin').on('click', function(){
	        imagesize = imagesize + 50;
	        $('#imgid').width(imagesize);
	    });  */
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
				    		var html='<div><label>MR READER</label><select   class="form-control" id="bmdReader" name="bmdReader"><option value="1">Select</option>';
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
	  if(sdCd=="1")
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
                     <div class="caption"><i class="fa fa-reorder"></i>Meter Reader Wise Photo Billing Status for past 30 days </div>
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
													<option value='1'>Select</option>
													<c:forEach items="${sdoList}" var="sdoVal">
													  <option value="${sdoVal[0]}">${sdoVal[1]}-${sdoVal[0]}</option>
													</c:forEach>
										</select>
									</div>
								  <%-- <div class="form-group"  id="bmdRdrsId" style="display: none;" >
										<label >MR READER</label>
										<select   class="form-control" id="bmdReaders" name="bmdReaders" >
													<option value="1">Select</option>
													<c:forEach items="${bmdReadersList}" var="bmdList1">
													  <option value="${bmdList1}">${fn:toUpperCase(bmdList1)}</option>
													</c:forEach>
										</select>
									</div> --%>
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
									<button type="submit" class="btn green" formaction="./getBmdReadersData" formmethod="post"  onclick="return validation();">Show</button>
									<button type="submit" class="btn default" formaction="./meterReaderwiseView" formmethod="post" >Clear</button>                              
								</div>
								</div>
							</form>
						</div>
                     </div>
                  </div>
                  <%-- <div id="divTable" style="display: none;">
                      <div class="portlet-title" align="center"><h3><font color="black">PHOTO BILLING</font></h3></div>
								<table class="table table-striped table-hover"  id="billTable">
								
									<c:forEach var="element" items="${bmdDataList}">
											<table  id="billTable" border="1"    cellPadding='6'>
											<tr><th>Bill Date</th><td align="center">${element[1]}</td></tr>
											<tr><th>No.of Bills</th><td  align="center">${element[0]}</td></tr>
											</table>
										</c:forEach>
										
								</table>
				</div> --%>
				
				
				<%-- <div class="portlet box blue" id="bmdListId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>MR Reader Wise Bill Details</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
						<c:forEach var="element" items="${bmdDataList}">
						<form id="bmdButton">
						    <input type="text" hidden="true" id="sCode" name="sCode" value="${sCode}">
							<input type="text" hidden="true" id="bDate" name="bDate" value='<fmt:formatDate pattern="dd-MM-yyyy" value="${element[1]}"/>'/>
							<input type="text" hidden="true" id="bmdRdr" name="bmdRdr" value="${bmdReader}"/> 
							<button id="btnId" type="submit" title="Click here to see bill details" formaction="./getBmdAllData" formmethod="post" style="text-align: left;">Bill Date&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;<fmt:formatDate pattern="MMM-dd"  value="${element[1]}" /><br><br>No.of Bills &nbsp;:&nbsp;&nbsp;&nbsp;${element[0]}</button>
						</form>	
						</c:forEach>
						</div>
					</div> --%>
					<div class="portlet box blue" id="bmdListId" style="display: none;">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>MR Reader Wise Bill Details for past 30 days</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
						<c:forEach var="element" items="${bmdDataList}">
						<form id="bmdButton" action="./getBmdAllData" method="post">
						    <input type="text" hidden="true" id="value" name="value" value="meterWise">
						    <input type="text" hidden="true" id="sCode" name="sCode" value="${sCode}">
							<input type="text" hidden="true" id="bDate" name="bDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${element[1]}"/>'/>
							<input type="text" hidden="true" id="bMonth" name="bMonth" value="${element[2]}"/> 
							<input type="text" hidden="true" id="bmdRr" name="bmdRr" value="${bmdReader}"/> 
							<button id="btnId" type="submit" title="Click here to see bill details"  style="text-align: left;">Bill Date&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;<fmt:formatDate pattern="MMM-dd"  value="${element[1]}" /><br><br>No.of Bills &nbsp;:&nbsp;&nbsp;&nbsp;${element[0]}</button>
						</form>	
						</c:forEach>
						</div>
					</div>
				<!-- <div><img data-src="./imageDisplay/getImage/VP12366/5112/201512" id="imgid"/></div>
<button class="zoomout">Zoom Out</button > <button class="zoomin">Zoom In</button > -->
				 
				 <jsp:include page="photoBillDisplay.jsp" />
				<jsp:include page="modalPhoto.jsp" />
               <!-- END PORTLET-->
</div>