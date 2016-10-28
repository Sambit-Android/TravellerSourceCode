<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  	TableEditable.init();
   	    	 	FormComponents.init();
   	    	
   	    	 	$('#MIS-Reports').addClass('start active ,selected');
   	   	  $('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
  	    	     
  	    	 	  
   	    	   $('#addData').click(
						function()
								{ 	
									document.getElementById("notifications").reset();
									document.getElementById('updateOption').style.display='none';
									document.getElementById('addOption').style.display = 'block';
								}
						);
   	    	   	  
   	    	   });
  
  
  
   function common_validation()
  {
	  var letters = /^([a-zA-Z0-9\s\.\/\-])+$/;
	  var title=document.getElementById("title").value.trim();
	  	if(title=='')
		  {
		     bootbox.alert("Please enter the Title");
		     return false;
		  }
	  	else if(!letters.test(title))
	  	  {
	  		  bootbox.alert("Special Characters are not allowed in Title");
		      return false;
	  	  }
	  	var notiDetails=document.getElementById("notificationDetails").value.trim();
	  	if(notiDetails=='')
		  {
		     bootbox.alert("Please enter the Notification Details");
		     return false;
		  }
	  	else if(!letters.test(notiDetails))
	  	  {
	  		  bootbox.alert("Special Characters are not allowed in Notification Details");
		      return false;
	  	  }
  }  
  
  
  
  
  </script>
<div class="page-content" >
<%@include file="pagebreadcrum.jsp"%>
<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red;font-size:15px;">${results}</span>
				</div>
			</c:if>

			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Announcements
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						<div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="addData" >
								Send Announcement <i class="fa fa-plus"></i>
							</button>
						</div>
						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
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
								<th>Title</th>
								<th>Announcement Detail</th>
								<th>Date Published</th>
								<th>Expire Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="element" items="${notificList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td>${element.title}</td>
									<td>${element.notificationDetails}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${element.datePublished}"/></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${element.expireDate}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Enter Details</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											<form:form action="./updateNotifications" modelAttribute="notifications"
																	commandName="notifications" method="post" id="notifications">
												<table>
													<tr hidden="true">
														<td>Id</td>
														<td><font color="red">*</font></td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id"></form:input></td>
													</tr>
													
													<tr>
														<td>Title</td>
														<td><font color="red">*</font></td>
														<td><form:input path="title" id="title"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="title"></form:input></td>
													</tr>

													<tr>
														<td>Announcement Details</td>
														<td><font color="red">*</font></td>
														<td><form:textarea path="notificationDetails" id="notificationDetails"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="notificationDetails"></form:textarea></td>
													</tr>
													
													<!-- <tr>
														<td>Date Published</td>
														<td>														
															<div class="input-group input-medium date date-picker"  data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-minviewmode="months">
																<input type="text" class="form-control" name="datePublished" id="datePublished">
																<span class="input-group-btn">
																<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
																</span>
															</div>															                          
														</td>
													</tr> -->
													
													<!-- <tr>
														<td>Expire Date</td>
														<td>														
															<div class="input-group date form_datetime" >                                       
															<input type="text" size="16"  class="form-control" name="expireDate" id="expireDate">
															<span class="input-group-btn">
															<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
															</span>
															</div>															                          
														</td>
													</tr> -->
													
												</table>
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													 <button class="btn blue pull-right" style="display: block;" id="addOption"  onclick="return common_validation();">Send Announcement</button>
												</div>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>