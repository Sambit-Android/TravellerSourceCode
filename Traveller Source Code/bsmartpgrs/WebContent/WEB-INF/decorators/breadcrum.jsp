<%@include file="/common/taglibs.jsp"%>

<div>
	<!-- RIBBON -->
	<div id="ribbon">

		<span class="ribbon-button-alignment"> 
			<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
				<i class="fa fa-refresh"></i>
			</span> 
		</span>

		<!-- breadcrumb -->
		<ol class="breadcrumb">
			
			<c:if test="${not empty userName}">
			
				<c:if test="${modulename ne 'Home'}">
					<li><a href="./index">Home</a></li><li>${modulename}</li>
				</c:if>
				<c:if test="${modulename eq 'Home'}">
					<li><a href="./index">Home</a></li>
				</c:if>
			</c:if>
			
			<c:if test="${not empty CustomerLoginName}">
				
				<c:if test="${modulename ne 'Home'}">
					<li><a href="./welcomeCustomer">Home</a></li><li>${modulename}</li>
				</c:if>
				<c:if test="${modulename eq 'Home'}">
					<li><a href="./welcomeCustomer">Home</a></li>
				</c:if>
			</c:if>
			
		
			
		<%-- <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<c:out value="Welcome"></c:out>
					
			
		<b>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>			
			<c:out value=" ${sessionScope.userName}"></c:out>
     	<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			<c:out value=" ${sessionScope.userDesignation}"> </c:out>
		<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			<c:out value=" ${sessionScope.userDeptName}"> &nbsp;</c:out>
			<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			<c:out value=" ${sessionScope.userOfficeName}"> </c:out> --%>
		</ol>
		
		
		<!-- end breadcrumb -->

		<!-- You can also add more buttons to the
		ribbon for further usability

		Example below:

		<span class="ribbon-button-alignment pull-right">
		<span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
		<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
		<span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
		</span> -->
		
		<c:if test="${not empty userName}">
		<div style="margin-left: 300px;margin-top: -30px">
		<b style="color:white;">
		Welcome - ${sessionScope.userName}&nbsp;&nbsp;&nbsp;Designation - ${sessionScope.designation}&nbsp;&nbsp;&nbsp;</b>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="pull-right">
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<b style="color:white;"><fmt:formatDate type="both" value="${now}" /></b>
		</div>
		
		</div>	
		</c:if>
		
		
		<c:if test="${not empty CustomerLoginName}">
		<div style="margin-left: 300px;margin-top: -30px">
		<font color="white">
		<b>
		Welcome - ${welcomeCustomerName}
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:set var="now" value="<%=new java.util.Date()%>" />
		<fmt:formatDate type="both" value="${now}" />
		 
		</b>
		</font>
		
		</div>	
		</c:if>
		
		

	</div>
	
	<!-- END RIBBON -->
</div>