<!-- BEGIN PAGE -->
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<script>
	 	$(".page-content").ready(function() 
	 	{ 
	 	    App.init(); 		  
	 	    $('#Disconn-Reconn').addClass('start active ,selected');
			$('#dash-board,#user-location,#admin-employee,#MIS-Reports,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management').removeClass('start active ,selected');
			
		}); 
	 	
	
	</script>
			

<div class="page-content" >
    <%@include file="pagebreadcrum.jsp" %>
			
			manage-Reconnection List under progress	
			 	 
		</div>
		<!-- END PAGE -->
 
 
  
 
