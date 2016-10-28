<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#appActLog').addClass('start active ,selected');
  	   	  $("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
   	    	   	  
   	    	   
   	    	   });
  
  
  </script>
<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>App Activity
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div hidden="true" class="btn-group">
						</div>
						<div class="btn-group pull-right">
							<!-- <button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button> -->
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'AppActivityLog')">Export
										to Excel</a></li>
							</ul>
						</div>
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr>
								<th hidden="true">Id</th>
								<th>App Name</th>
								<th>Trans Time</th>
								<th>Activity</th>
								
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${appActitvityList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td>${element.appName}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${element.transTime}"/></td>
									<td>${element.activity}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					
				</div>
			</div>
		</div>
	</div>
</div>