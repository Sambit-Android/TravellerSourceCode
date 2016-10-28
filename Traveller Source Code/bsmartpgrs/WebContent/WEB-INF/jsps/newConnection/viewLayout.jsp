

  <div id="addtabforLayouts" title="Multiple Site Details" style="display:none;overflow-y:scroll;overflow-x:hidden;">
   	    <table class="table table-bordered table-striped">
		 <thead>
			 <tr>
				
				<th width="70">Sl No</th>
				<th width="100">Nature&nbsp;of&nbsp;Installation</th>
				<th width="100">Tariff</th>
				<th width="100">Site Dimension</th>
				<th width="100">No of Sites</th>
				<th width="100">Requested Load</th>
			</tr>
		</thead>
		
			<tbody id="layoutView">

			</tbody>
			
		
		</table>  
      </div>

 <script>

 function viewLayouts(applicationid){
		
		$("#layoutView").empty();
		var tableData = "";
		$.ajax
		({			
			type : "POST",
			url : "./ncms/viewLayoutsPopUp/"+applicationid,
			async: false,
			dataType : "JSON",
			success : function(response) 
			{	    
				for ( var s = 0, len = response.length; s < len; ++s) {
			              	var obj = response[s];
			              	
			              	tableData += "<tr>"
									+"<td>"+(s+1)+"</td>"
									+"<td>"+obj.natureofinstallation+"</td>"
									+"<td>"+obj.tarifftype+"</td>"
									+"<td>"+obj.sitedimension+"</td>"
									+"<td>"+obj.noofsites+"</td>"
									+"<td>"+obj.requestedloadkw+"</td>"
									+"</tr>";
					                
					     }
					$('#layoutView').append(tableData);
			}
		
	
		});
		
		dialog = $("#addtabforLayouts").dialog({
			autoOpen : false,
			width : 800,
			height : 450,
			resizable : false,
			modal : true,
			
		}).dialog("open");	
	}
</script>