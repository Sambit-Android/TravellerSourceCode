<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

$(".page-content").ready(
		function() {
			App.init();
			TableEditable.init();
			FormComponents.init();
			$('#meterData-Acquisition').addClass('start active ,selected');
			$("#dash-board,#user-location,#MIS-Reports,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn").removeClass('start active ,selected');
			$('#securityLauncherPwd').attr('disabled',true);
		});
</script>

<script>
function myFunction()
{
	$('#securityLauncherPwd').val('${securityPwd}');
	$('#securityLauncherPwd').attr('disabled',true);
}
function changePassword()
{
	$.ajax({
		type : "get",
		url : "./changeSecurityLauncherPassword",	
		dataType : "text",
		success : function(response)
		{
			if(response=='error')
				{
				   
				}
			$('#securityLauncherPwd').val(response);
		}
	});
	return false;
}
</script>


  <div class="page-content">
	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red" id="errorId">${results}</span>
				</div>
			</c:if>
<table>
<tr>
<td>Current Password:</td>
<td><input type="text" class="form-control input-medium" value="${securityPwd}" name="securityLauncherPwd" id="securityLauncherPwd" onkeyup="myFunction()"></td>
<td><button id="securityPwdBtn"  class="btn green" onclick="changePassword();" >Change Password</button></td></tr>
</table>
			
		</div>
	</div>
</div>
	