<%@include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<script src="./resources/kendo/js/jquery.min.js"></script>	

<div>
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS variables -->
	<aside id="left-panel">
	
		<!-- User info -->
		<div class="login-info">
				
				<a style="margin-top: 10px;height: 30px;margin-left: 24px;cursor: pointer" href="./profile">
					<!-- <img src="./getprofileimage" alt=""/>  -->
					<span>Change Password</span>
				</a> 
		</div>
		<!-- end user info -->
	
		<!-- NAVIGATION : This navigation is also responsive
	
		To make this navigation dynamic please make sure to link the node
		(the reference to the nav > ul) after page load. Or the navigation
		will not initialize.
		-->
		<nav>
			<!-- NOTE: Notice the gaps after each icon usage <i></i>..
			Please note that these links work a bit different than
			traditional href="" links. See documentation for details.
			-->
			<ul id="menu">
				<!-- <li id="dashboard" >
					<a href="./dashboard" title="Dashboard" onclick="setOpenstatus('dashboard')"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard</span></a>
				</li> -->
				<c:forEach var="category" items="${navigation.keySet()}">
					<c:set var="count" value="1" scope="page" />
			       	<c:forEach var="widget" items="${navigation.get(category)}">
			       		<c:if test="${widget.include()}">
			       				<c:set value="${widget.text}" var="className"></c:set>
		       					<li id="${fn:replace(className, ' ','')}">
		       						<a href="#" onclick="setOpenstatus('${fn:replace(className, ' ','')}')"><i class="${widget.image}"></i> <span class="menu-item-parent">${widget.text}</span></a>
		       						<ul class="first">
			       						<c:forEach var="example" items="${widget.items}">
			       							<c:if test="${example.include()}">
			       								<c:set var="item" value="${example.text}"></c:set>
			       								<c:if test="${example.url != ''}">
				       								<li>
														<a href="${example.url}">${example.text}</a>
													</li>
			       									
			       								</c:if>
			       								<c:if test="${example.url == ''}">
				       								<li>
														<a href="#">${example.text}</a>
														<ul>
															<c:forEach var="third" items="${thirdlevel}">
																<c:if test="${third.module eq item}">
																	<li><a href=".${third.url}">${third.name}</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
			       								</c:if>
											</c:if>
										</c:forEach>
									</ul>
								</li>
			       		</c:if>
			       	</c:forEach>
			       	
		        </c:forEach>
		       <!--  <li>
					<a href="./mycalendar">My Calendar</a>
				</li> -->
	       </ul>
	       
			
		</nav>
		<span class="minifyme" data-action="minifyMenu"> 
			<i class="fa fa-arrow-circle-left hit"></i> 
		</span>
	
	</aside>
	<!-- END NAVIGATION -->
</div>

<script type="text/javascript">

$(document).ready(function() {
	
	$('#Administration').addClass('open');
	$('#PGRS').addClass('open');
	$('.first').attr('style','display: block');
	
});

 function setOpenstatus(elem){  
  
  $.ajax({
   type : "GET",
    url : "./setopenstatus",
    data :{
     "status" : elem
    },
    success : function(response){
     
    }
  });   
 } 
 
</script>
