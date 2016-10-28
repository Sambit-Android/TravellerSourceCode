
<div id="addtabforSubConnections" title="Multiple Connection Details" style="display:none;overflow-y:scroll;overflow-x:hidden;">
   	    <table class="table table-bordered table-striped">
		 <thead>
			 <tr>
				
				<th width="70">Sl No</th>
				<th width="200">InstallationType</th>
				<th width="200">Nature Of Installation</th>
				<th width="100">Tariff</th>
				<th width="100">Load KW</th>
				<th width="100">Load KVA</th>
				<th width="100">Load HP</th>
				<th width="300">No Of Connections</th>												
			</tr>
		</thead>
		
			<tbody id="subConnectionView">

			</tbody>
			
		
		</table>  
 </div>


<script>
function viewSubConnection(applicationid){
	$("#subConnectionView").empty();
	var tableData = "";
	$.ajax
	({			
		type : "POST",
		url : "./ncms/viewSubConnectionsPopUp/"+applicationid,
		async: false,
		dataType : "JSON",
		success : function(response) 
		{	    
			for ( var s = 0, len = response.length; s < len; ++s) {
		              	var obj = response[s];
		              	
		              	tableData += "<tr>"
								+"<td>"+(s+1)+"</td>"
								+"<td>"+obj.proposedinsttype+"</td>"
								+"<td>"+obj.natureofinstallation+"</td>"
								+"<td>"+obj.tariffdesc+"</td>"
								+"<td>"+obj.loadkw+"</td>"
								+"<td>"+obj.loadkva+"</td>"
								+"<td>"+obj.loadhp+"</td>"
								+"<td>"+obj.noofconnections+"</td>"
								+"</tr>";
				                
				     }
				$('#subConnectionView').append(tableData);
		}
	

	});
	
	dialog = $("#addtabforSubConnections").dialog({
		autoOpen : false,
		width : 1100,
		height : 600,
		resizable : false,
		modal : true,
		
	}).dialog("open");	
}

</script>