<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script  type="text/javascript">
  $(".page-sidebar").ready(function()
  {
	  <c:forEach var="elem" items="${activeModules}">
	  var ac = '<c:out value="${elem.id}"></c:out>';
	  if(ac=='Cash_Collection'||ac=='vigilance-management' ||ac=='Disconn-Reconn')
		  {
		  if('${userType}'=='admin'||'${userType}'=='ADMIN')
			  {
			  document.getElementById(ac).style.display='block';
			  }
		  }
	  else
		  {
		  document.getElementById(ac).style.display='block';
		  }
	  
	  </c:forEach>
  });
  
</script> 
 
<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar navbar-collapse collapse" >
			<!-- BEGIN SIDEBAR MENU -->   
						
			 <ul class="page-sidebar-menu" id="userSideBar">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				
				<li class=" " id="dash-board">
					<a href="./userDashboard" id="dashBoard">
					<i class="fa fa-home"></i> 
					<span class="title">Dashboard</span>					
					<span class="selected"></span>
					</a>
				</li>							
				
				<c:if test="${userType eq 'admin' || userType eq 'ADMIN' || userType eq 'admin_subdivision'||userType eq 'ADMIN_SUBDIVISION'}">
				<li class=" " id="meterData-Acquisition"  >
					<a href="./meterDataAcquisition" id="meterDataAcquisition">
					<i class="fa fa-cloud-upload"></i> 
					
					<c:choose>
					   <c:when test="${userType eq 'admin' || userType eq 'ADMIN'}">
					   <span class="title">Core Module</span>	
					   </c:when>
					   <c:otherwise>
					   <span class="title">Device Management</span>	
					   </c:otherwise>
					</c:choose>
						
					
					<span class="arrow "></span>			
					</a>
					<ul class="sub-menu">
						
						<!-- <li id="mrmAccMgmt">
							<a href="./meterReaderAccessManagment">
							<span class="title">Device Operator Master </span>					
							<span class="selected"></span>
							</a>
						</li> -->
						
						
						<li id="mrDeviceAccMgmt">
							<a href="./mrDeviceAccessManagment">
							<c:choose>
					   <c:when test="${userType eq 'admin' || userType eq 'ADMIN'}">
					   <span class="title">Device Registration</span>
					   </c:when>
					   <c:otherwise>
					   <span class="title">Device Master</span>	
					   </c:otherwise>
					</c:choose>
							<span class="selected"></span>
							</a>
						</li>
						<!-- <li id="deviceStatus">
							<a href="./updateDeviceStatus">
					   <span class="title">Device Status</span>	
							<span class="selected"></span>
							</a>
						</li> -->
						
						<li id="mrDeviceAllocationMgmt">
							<a href="./mrDeviceAllocationManagment">
							<span class="title">Device Allocate/DeAllocate</span>					
							<span class="selected"></span>
							</a>
						</li>
						
						
						<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
						<li id="moduleConfig">
							<a href="./modulesConfiguration">
							<span class="title">Manage Menu List</span>					
							</a>
						</li>
						
						<c:choose>
						     <c:when test="${projectName eq 'gescom' || projectName eq 'GESCOM'}">
						             <li id="onAirVerUpd">
								<a href="./onAirVersionUpdate">
								<span class="title">OnAir Version Update GESC </span>					
								<span class="selected"></span>
								</a>
							</li>
						     </c:when>
						     <c:otherwise>
						              <li id="onAirVerUpd">
								<a href="./onAirVersionUpdateCesc">
								<span class="title">OnAir Version Update CESC</span>					
								<span class="selected"></span>
								</a>
							</li>
						     
						     </c:otherwise>
						</c:choose>
						
						<!-- <li id="allowVer">
							<a href="./allowedVersions">
							<span class="title">Allowed Versions</span>					
							<span class="selected"></span>
							</a>
						</li> -->
						 <li id="userAccMang">
							<a href="./userAccessManagment">
							<span class="title">User Access Management</span>					
							<span class="selected"></span>
							</a>
						</li>
						<c:if test="${projectName eq 'cescom' || projectName eq 'CESCOM'}">
						<li id="securityLauncher">
							<a href="./viewSecurityLauncher">
							<span class="title">Security Launcher PWD</span>					
							<span class="selected"></span>
							</a>
						</li>
						</c:if>
						</c:if>
						
						<!-- <li id="resetConsumerData">
								<a href="./resetConsumerData">
								<span class="title">Reset Consumer Data</span>					
								<span class="selected"></span>
								</a>
							</li> -->
						<!-- <li id="appActLog">
							<a href="./appActivityLog">
							<span class="title">App Activity Log</span>					
							<span class="selected"></span>
							</a>
						</li>-->
						<!-- <li id="groupId">
							<a href="./getGroup">
							<span class="title">Groups</span>					
							<span class="selected"></span>
							</a>
						</li>  -->
						<!-- <li id="assetdetails">
							<a href="./assetDetails">
							<span class="title">Asset Details</span>					
							<span class="selected"></span>
							</a>
						</li> -->
					</ul>
				</li>
				<c:if test="${userType eq 'admin' || userType eq 'ADMIN'}">
				<li class=" " id="admin-location"  >
					<a href="./siteLocations" id="location">
					<i class="fa fa-leaf"></i> 
					<span class="title">Locations Info</span>					
					<span class="selected "></span>
					</a>
				</li>
				<!-- <li id="mobileLiveDevices">
							<a href="./showMobileDevicesLive" id="mobileLiveDevicesId">
							<i class="fa fa-eraser"></i>
							<span class="title">DeviceDashboard</span>
							<span class="selected"></span>
							</a>
						</li> -->
				</c:if>
				</c:if>
				
				<li class=" " id="MIS-Reports" style="display: none;">
					<a href="#" id="MISReports">
					<i class="fa fa-bar-chart-o"></i> 
					<span class="title">Consumer App</span>	
					<span class="arrow "></span>				
					</a>
					
					<ul class="sub-menu">
						<li id="billingData">
							<a href="./billingData" >
							<span class="title">Billing Data</span>					
							<span class="selected"></span>
							</a>
						</li>
						<li id="paymentsData">
							<a href="./paymentsData" >
							<span class="title">Consumer Transaction</span>					
							<span class="selected"></span>
							</a>
						</li>
						<li id="billingAlerts">
							<a href="./billingAlerts" >
							<span class="title">Push Notification</span>					
							<span class="selected"></span>
							</a>
						</li>
						<li id="notification">
							<a href="./notifications" >
							<span class="title">Announcements</span>					
							<span class="selected"></span>
							</a>
						</li>
						
						<li id="searchListItem">
							<a href="./searchListItem" >
							<span class="title">Search List Item</span>					
							<span class="selected"></span>
							</a>
						</li>
					</ul>
					</li>
					<c:if test="${userType eq 'admin' || userType eq 'ADMIN' || userType eq 'admin_subdivision'||userType eq 'ADMIN_SUBDIVISION'}">
					<li class=" " id="MIS-Reports-photoBilling" style="display: none;">
						<a href="#" id="MISReports">
						<i class="fa fa-bar-chart-o"></i> 
						<c:choose>
					   <c:when test="${userType eq 'admin' || userType eq 'ADMIN'}">
					   <span class="title" >Mobile Billing</span>
					   <span class="arrow "></span>
					   </c:when>
					   <c:otherwise>
					   <span class="title" >Photo Billing</span>
					   <span class="arrow "></span>
					   </c:otherwise>
					</c:choose>
						</a>
						
						<ul class="sub-menu">
							 <li id="realTimeStatus">
								<a href="./realTimeStatus">
								<span class="title">MRwise Live status</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li id="sectionRealTimeStatus" style="display: none;">
								<a href="./sectionRealTimeStatus">
								<span class="title">Sectionwise Billed Report</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li id="meterReaderView">
								<a href="./meterReaderwiseView">
								<span class="title">MRwise Billing View</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li id="mrDaywiseRprt">
							<a href="./showMrDaywiseReport" id="mrDaywiseRprtId">
							<span class="title">MR Daywise Report</span>
							<span class="selected"></span>
							</a>
						</li>
							
							<li id="gpsWiseView">
								<a href="./gpsWiseView">
								<span class="title">GPS wise View</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li id="DeviceWiseReport">
								<a href="./deviceWiseReport">
								<span class="title">Device Wise Report</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li id="readingDayView">
								<a href="./readingDayWiseView">
								<span class="title">Reading Daywise View</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li id="deviceWiseView">
								<a href="./deviceWiseView">
								<span class="title">Device Wise View</span>					
								<span class="selected"></span>
								</a>
							</li >
							<li id="consumerwiseView">
								<a href="./consumerWiseView">
								<span class="title">Consumer Wise View</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<!--  <li id="billingSummaryReports">
								<a href="./billingSummaryReport">
								<span class="title">Billing Summary Reports</span>					
								<span class="selected"></span>
								</a>
							</li> -->
							
						<li id="mobileLiveDevices">
							<a href="./showMobileDevicesLive" id="mobileLiveDevicesId">
							<span class="title">Device Utilization Report</span>
							<span class="selected"></span>
							</a>
						</li>
						
						<li id="deviceUsageRpt" >
								<a href="./deviceUsageRpt">
								<span class="title">Device Usage Report</span>					
								<span class="selected"></span>
								</a>
						</li>
						
						<li id="deviceProviderRpt" >
								<a href="./deviceProviderRpt">
								<span class="title">Device Providerwise Report</span>					
								<span class="selected"></span>
								</a>
						</li>
						
						<!-- <li id="mrTypeRpt" >
								<a href="./mrTypeRpt">
								<span class="title">MRType Report</span>					
								<span class="selected"></span>
								</a>
						</li> -->
							
						<li id="mrPerformanceView">
								<a href="./showMrPerformanceView">
								<span class="title">MR Performance</span>					
								<span class="selected"></span>
								</a>
							</li>
						
						<li id="mrTrackLive">
							<a href="./showMeterReadersLive" id="mrTrackLiveId">
							<span class="title">Tracking MeterReader</span>
							<span class="selected"></span>
							</a>
						</li>
						
						<li id="arrearWise">
							<a href="./showArrearswise" id="arrearWiseId">
							<span class="title">Arrears wise view</span>
							<span class="selected"></span>
							</a>
						</li>
						
						<!-- <li id="collectionViewMap">
							<a href="./showCollectionViewMap" id="collectionViewMapId">
							<span class="title">Collection view On Gmap</span>
							<span class="selected"></span>
							</a>
						</li> -->
						
						<!-- <li id="enrgyLosses">
							<a href="./showenrgyLosses" id="enrgyLossesId">
							<span class="title">Energy Losses on GMAP</span>
							<span class="selected"></span>
							</a>
						</li> -->
						
						<!-- <li id="trackMrPerformance">
							<a href="./trackMrPerformance" id="trackMrPerformanceId">
							<span class="title">Track Mr Performacne</span>
							<span class="selected"></span>
							</a>
						</li> -->
						
						<!-- <li id="meterReplacement">
							<a href="./showMeterReplacement" id="meterReplacementId">
							<span class="title">Meter Replacement View on Gmap</span>
							<span class="selected"></span>
							</a>
						</li> -->
						
						</ul>
	
				  </li>
				  </c:if>
				   <li class=" " id="Disconn-Reconn" style="display: none;">
						<a href="#" id="Disconn-Reconn">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title">Dis-Reconnection</span>	
						<span class="arrow"></span>				
						</a>
						<ul class="sub-menu">
							<li >
								<a href="./disReConnDashboard">Dashboard</a>
							</li>
							<li >
								<a href="./disConnList">Manage DissConnection List</a>
							</li>
						</ul>
	
	
				  </li>	
				  
				  <li class=" " id="vigilance-management" style="display: none;">
						<a href="#" id="vigilance-management1">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title">Complaints & Theft</span>					
						<span class="arrow"></span>	
						</a>
						
						<ul class="sub-menu">
						    <!-- <li id="vigiDash">
							<a href="./complainTheftDashboard" >
							<span class="title">Dashboard</span>					
							<span class="selected"></span>
							</a>
						    </li> -->
							<li id="vigiComplaints">
							<a href="./consumerComplaints" >
							<span class="title">View Complaints</span>					
							<span class="selected"></span>
							</a>
						    </li>
							<li id="theftRecording">
								<a href="./theftRecording" >
								<span class="title">View Theft</span>					
								<span class="selected"></span>
								</a>
							</li>
						</ul>
				  </li>
				  
				  <li class=" " id="Real-vigilance" style="display: none;">
						<a href="#" id="Real-vigilance1">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title">Vigilance</span>
						<span class="arrow"></span>						
						</a>
						
						   <ul class="sub-menu">
						    <li id="vigiDash">
							<a href="./assignVigilance" >
							<span class="title">Assign Vigilance Patrol</span>					
							<span class="selected"></span>
							</a>
						    </li>
							
						</ul>
				  </li>
				   <li class=" " id="Cash_Collection" style="display: none;">
						<a href="#" id="Cash_Collection">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title" >Cash Collection</span>	
						<span class="arrow"></span>				
						</a>
						
						<ul class="sub-menu">
							<li  id="collection_Details"  >
								<a href="./collectionDetails" id="collectionDetails">
								<i class="fa fa-plus-square"></i> 
								<span class="title">Collection&nbsp;Details</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li  id="mrcredit-master"  >
								<a href="./mrCreditMasters" id="mrCreditMasters">
								<i class="fa fa-plus-square"></i> 
								<span class="title">MR&nbsp;Credit&nbsp;Master</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li  id="mrdeposit-master"  >
								<a href="./mrDepositMasters" id="mrDepositMasters">
								<i class="fa fa-plus-square"></i> 
								<span class="title">MR&nbsp;Deposits</span>					
								<span class="selected"></span>
								</a>
							</li>
							<!-- <li  id="mraudit-master"  >
								<a href="./mrAuditDetails" id="mrAuditDetails">
								<i class="fa fa-plus-square"></i> 
								<span class="title">Audit&nbsp;Details</span>					
								<span class="selected"></span>
								</a>
							</li> -->
							
						</ul>
				  </li> 
				  <li class=" " id="Circl_Data" style="display: none;">
						<a href="#" id="Circl_Data">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title" >Bill Progress Report</span>	
						<span class="arrow"></span>				
						</a>
						
						<ul class="sub-menu">
							<li  id="Subdivision_Details"  >
								<a href="./subDevisionDetails" id="subDevisionDetails">
								<i class="fa fa-plus-square"></i> 
								<span class="title">Sub Division Wise Report</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li  id="MeterReaderWise_reports"  >
								<a href="./meterReaderWiseReports" id="meterReaderWiseReports">
								<i class="fa fa-plus-square"></i> 
								<span class="title">Meter Reader wise Report</span>					
								<span class="selected"></span>
								</a>
							</li>
						</ul>
				  </li> 
				  
				  <li class=" " id="Ip_Cesc" style="display: none;">
						<a href="#" id="Ip_Cesc">
						<i class="fa fa-bar-chart-o"></i> 
						<span class="title" >IP and DTC ENUM</span>	
						<span class="arrow"></span>	
						</a>
						
						<ul class="sub-menu">
						<li id="cescipdashboard"  >
								<a href="./showCescIpDashBoard" id="cescipdashboardId">
								<span class="title">Dashboard</span>					
								<span class="selected"></span>
								</a>
							</li>
						<li id="LiveStatusData"  >
								<a href="./showCescIpLiveStatusData" id="LiveStatusDataId">
								<span class="title">Live Status View</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li id="feederData"  >
								<a href="./showFeederMasterData" id="feederDataId">
								<span class="title">FeederMaster</span>					
								<span class="selected"></span>
								</a>
							</li>
							
								<li id="feederDataNew"  >
								<a href="./feederDetailsNew" id="feederDataNewId">
								<span class="title">Manage FeederMaster</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li id="ipMasterData"  >
								<a href="./showIpMasterData" id="ipMasterDataId">
								<span class="title">IP Master</span>					
								<span class="selected"></span>
								</a>
							</li>
							<!-- <li id="villageMasterData"  >
								<a href="./showSubdivisionMasterData" id="villageMasterDataId">
								<span class="title">Subdivision Master</span>					
								<span class="selected"></span>
								</a>
							</li> -->
							<li id="DTCMasterData">
								<a href="./dtcMaster" id="DTCMasterDataId">
								<span class="title">DTC Master</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li  id="ipEnumerationData"  >
								<a href="./showIpnumerationData" id="ipEnumerationDataId">
								<span class="title">IP Enumeration</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li  id="dtcEnumerationData"  >
								<a href="./tcenumeration" id="dtcEnumerationDataId">
								<span class="title">DTC Enumeration</span>					
								<span class="selected"></span>
								</a>
							</li>
							<li  id="areaProgressData"  >
								<a href="./areaProgress" id="areaProgressDataId">
								<span class="title">Area Progress</span>					
								<span class="selected"></span>
								</a>
							</li>
							
							<li  id="ipgpsViewData"  >
								<a href="./gpsView" id="gpsViewId">
								<span class="title">GPS View</span>					
								<span class="selected"></span>
								</a>
							</li>
						</ul>
						
				  </li> 
			   </ul> 
			
			<!-- END SIDEBAR MENU -->
		</div>
		<!-- END SIDEBAR -->