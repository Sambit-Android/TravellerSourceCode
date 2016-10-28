<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<div id="content">
				
		<div class="input-group" style="float: right;margin-top: -8px;"> 
			<input class="form-control" name="applicationId" type="text" style="width: 160px;height: 29px;" placeholder="Application Number" id="applicationId" data-mask="9999999999" rel="tooltip" data-original-title="Enter Application Number">
			<button type="button" onclick="searchApplicationNo()" class="btn btn-primary" style="margin-left: 6px;width: 80px;height: 28px;">Search</button>
		</div>
						
</div>
	
	
	<script>
	
	function searchApplicationNo()
	{
		var applicationIdregex = new RegExp("^[0-9]*$");
		var applicationId=$("#applicationId").val().trim();
        if(applicationId==""){
   		 alert("Please Enter Application ID");
   	    }
        else if(!(applicationIdregex.test(applicationId))){
        	alert("Please enter only numbers");
        }
	   	else{
	   		window.location.href="./applicationStatus?applicationId="+applicationId; 
	   	}
	}
	</script>
	