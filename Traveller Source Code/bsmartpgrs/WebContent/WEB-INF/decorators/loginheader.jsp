<%@ include file="/common/taglibs.jsp"%>

<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">
<link href="http://fonts.googleapis.com/css?family=Lobster%3A400" rel="stylesheet" property="stylesheet" type="text/css" media="all" />

<header id="header" style="height: 85px;">

	<div id="logo-group">
		<span id="logo"> <img src="./resources/img/cesclogo.png" alt=""></span>
		<!--  -->
	</div>
	
	<div>
		<span data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']" 
			 data-y="['top','top','top','top']" data-voffset="['201','201','201','150']" 
						data-fontsize="['30','30','30','18']"
			data-width="none"
			data-height="none"
			data-whitespace="nowrap"
			data-transform_idle="o:1;"
 
			 data-transform_in="z:0;rX:0;rY:0;rZ:0;sX:0.8;sY:0.8;skX:0;skY:0;opacity:0;s:500;e:Power4.easeOut;" 
			 data-transform_out="opacity:0;s:300;"  
			data-splitin="none"  
			data-splitout="none" 
			data-responsive_offset="on" 			
			style="padding-left:150px;white-space: nowrap; font-size: 50px; font-weight: 400; color: #2C699D;font-family:Lobster;"> bSmart - Support Ticket System </span>
	</div>

</header>
<div id="alertsBox" title="Alert"></div>
<script>
function accountClick(){
	$("#alertsBox").html("Alert");
	$("#alertsBox").html("Feature Not Enabled");
	$("#alertsBox").dialog({
		modal : true,
		draggable: false,
		resizable: false,
		buttons : {
			"Close" : function() {
			$(this).dialog("close");
		 }
	    }
	});
}
</script>
<style>
#logoId {
  height: 80px;
  width: 460px;
  margin-left: 177px;
}
</style>		