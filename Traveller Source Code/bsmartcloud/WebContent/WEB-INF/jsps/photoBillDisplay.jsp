<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <script type="text/javascript">
</script>
<div class="portlet box purple" id="divTable" style="display: none;" >
                      <div class="portlet-title">
							<div class="caption"><i class="fa fa-info-circle"></i>PHOTO BILLING&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="return getMobileHealth(this.id,'${DeviceBmr}','${DeviceSiteCode}','${dateOfBill}','${DeviceSection}');" id="memoryData${DeviceBmr}${DeviceSiteCode}"><font color="white"> <i class="fa fa-mobile-phone (alias)" title="view on graph"></i></font></a></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="reload"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" >
							<div class="table-responsive">
							<table class="table table-striped table-hover"  id="billTable">
									<c:forEach var="element" items="${hhbmList}">
									<table id="billTable" style="margin-left: 10px;margin-bottom: 10px;"> 
									<tr><td><a href="#">
									 <img  class="lazy" data-src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}/${fn:trim(element.sdoCode)}/${fn:trim(element.billMonth)}" style="background: url('./resources/bsmart.lib.js/loading.gif') no-repeat 50% 50%;" border="0"  data-toggle="modal"  data-target='#popup_image'   height="150" width="200"   title="${fn:trim(element.consumer_Sc_No)}" onclick="viewDocument('${fn:trim(element.consumer_Sc_No)}','${fn:trim(element.sdoCode)}','${fn:trim(element.billMonth)}');"/></a></td></tr>
									
									     
										  <tr><td><table>
											<tr><th>Meter Reader</th><td>${element.bmd_Reading}</td></tr>
											<tr><th>Bill Month</th><td>
											<fmt:parseDate value="${element.billMonth}" type="date" pattern="yyyyMM" var="fmtBlMon"/>
											<fmt:formatDate value="${fmtBlMon}"  type="date" pattern="MMM-YYYY"/>
											</td></tr>
											<tr><th>Bill Date</th>
											<td>
											${element.billDate }
											<%-- <fmt:parseDate value="${fn:substring(element.billDate,0,10)}" type="date" pattern="dd/MM/yyyy" var="formatedDate"/>
											<fmt:formatDate value="${formatedDate}"  type="date" pattern="dd-MMM-YYYY"/> --%>
											</td>
											</tr>
											<%-- <tr><th>Photo           </th><td><img src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}"  height="150" width="200" data-magnifyby="5" class="magnify"  title="${element.consumer_Sc_No}" /></td></tr> --%>
											<%-- <tr><td><a href="#"><img class="lazy" data-src="./imageDisplay/getImage/${fn:trim(element.consumer_Sc_No)}/${fn:trim(element.sdoCode)}/${fn:trim(element.billMonth)}" style="background: url('./resources/bsmart.lib.js/loading.gif') no-repeat 50% 50%;" border="0"  data-toggle="modal"  data-target='#popup_image'   height="150" width="200"   title="${fn:trim(element.consumer_Sc_No)}" onclick="viewDocument('${fn:trim(element.consumer_Sc_No)}','${fn:trim(element.sdoCode)}','${fn:trim(element.billMonth)}');"/></a></td></tr> --%>
											<tr><th>RR Number </th><td >${element.consumer_Sc_No }</td></tr>
											<fmt:parseDate value="${element.readin_Date}" type="date" pattern="MM/dd/yyyy" var="formatedDate"/>
											<tr><th>Reading Date    </th><td><fmt:formatDate value="${formatedDate}"  pattern="dd/MM/YYYY"/></td></tr>
											<tr><th>Present Rdng    </th><td>${element.present_Reading }</td></tr>
											<tr><th>Previous Rdng   </th><td>${element.previous_reading }</td></tr>
											<tr><th>Units Consumed  </th><td>${element.units }</td></tr>
											<tr><th>Net Amount due </th><td>${element.netamountdue }</td></tr>
											<c:set var="version" value="${fn:split(element.bank,'_')}"></c:set>
											<tr><th>APK version    </th><td>${fn:substring(version[1],2,6)}</td></tr>
											<%-- <tr><th>Consumer Status </th><td>${element.consrStatus }</td></tr> --%>
											<tr><th>Mtr Rndg type   </th><td><c:if test="${element.meterReadingType eq '1'}">Normal</c:if>
											                                 <c:if test="${element.meterReadingType eq '2'}">Door Lock</c:if>
											                                 <c:if test="${element.meterReadingType eq '3'}">Meter Not reading</c:if>
											                                 <c:if test="${element.meterReadingType eq '4'}">Direct Connection</c:if>
											                                 <c:if test="${element.meterReadingType eq '5'}">DisConnection</c:if>   
											                                 <c:if test="${element.meterReadingType eq '6'}">Idle</c:if> 
											                                 <c:if test="${element.meterReadingType eq '7'}">Meter Burnt</c:if> 
											                                 <c:if test="${element.meterReadingType eq '8'}">Meter Sticky</c:if>   
											                                 <c:if test="${element.meterReadingType eq '9'}">Not Visible</c:if>      
											
											</td></tr>
											<tr><th>Longitude </th><td>${element.longitude }</td></tr>
											<tr><th>Lattitude</th><td>${element.lattitude }</td></tr>
											<tr><th>Taken Time </th><td>${element.takenTime }</td></tr>
											</table></tr></td>
											</table>
										</c:forEach>
								</table>
				</div>
				</div>
				<jsp:include page="deviceHealth.jsp" />
				</div>
				
	<!-- <div class="modal fade" id="popup_image" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog" id="image">
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >
           &times;
          </button>
          <h4 class="modal-title">IMAGE
          <img  id="rl2" src="./resources/assets/img/RotateLeft.jpg" onclick="rotateLeft('0');" />&nbsp;&nbsp;&nbsp;&nbsp;
         <img  id="rr1" src="./resources/assets/img/RotateRight.jpg" onclick="rotateRight('0');"/>
         <label id="rll">Rotate Left&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rotate Right</label>
          </h4>
         </div>
         <div class="modal-body">
          <div class="rotatecontrol" id="Imageview" >
           <img id="tempImg" src="" />
          </div>
         </div>
        </div>
        /.modal-content
       </div>
       /.modal-dialog
      </div> -->
  
     
        
	
	
	
	