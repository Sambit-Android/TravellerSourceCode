<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#MIS-Reports').addClass('start active ,selected');
  	   	  $('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#vigilance-management,#Disconn-Reconn').removeClass('start active ,selected');
 	    	     
 	    		    	   });
  
  </script>
<div class="page-content" >
Energy wise reports
</div>