<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>


		<div class="jarviswidget jarviswidget-color-greenDark" id="wid-id-layouts" data-widget-editbutton="false" data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>Create Layouts for Application Id : ${applicationId}</h2>
					</header>

		      <div>

					<div class="jarviswidget-editbox"></div>
					<div class="widget-body">

								<form action="./ncms/createLayoutsforOfficial" method="post" id="submit-layouts" class="smart-form">
				
									<input type="hidden" id="applicationid" autocomplete="off" placeholder="" name="applicationid" value="${applicationId}">
				
									<fieldset>
				
										<div class="row">
											
											
											<section class="col col-3">
												<label class="label">Proposed&nbsp;Installation&nbsp;</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												  </span> 
												     <select class="form-control" id="proposedinstallationtype" name="proposedinstallationtype">
														<option value="" selected disabled>Select</option>
														<option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</option>
														<option value="Village Panchayat">Village Panchayat</option>
				
													</select>
				
				
												</label>
											</section>
				
											<section class="col col-3">
												<label class="label">Nature of installation</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												</span> 
												<select class="form-control" id="natureofinstal"
														name="natureofinstal"
														onchange="onselectTariffforLayout(this.value)">
														<option value="" selected disabled>Select Installation</option>
												</select>
												</label>
											</section>
											
											
											<section class="col col-3">
												<label class="label">Dimensions&nbsp;(Sqft)&nbsp;</label> <label
													class="select"> <span> <i
														class="fa fa-chevron-down"></i>
												  </span> 
												<select class="form-control" id="dimensionsdrop" name="dimensionsdrop" onchange="onChangeSiteDimesion(this.value)">
													<option value="">Select</option>
													<option value="20*30">20*30(600)</option>
													<option value="30*40">30*40(1200)</option>
													<option value="60*40">60*40(2400)</option>
													<option value="50*80">50*80(4000)</option>
													<option value="Other">Other</option>
												</select>
												</label>
											</section>
				
											<section class="col col-3" id="oddDimensionsEntry" style="display: none;">
												<label class="label">New Dimension</label><label class="input"><i
													class="icon-prepend fa fa-ban"></i>
													<input type="text" name="sitedimension"
														placeholder="Total Dim.(Sqft)" 
														id="sitedimension" class="form-control"
														style="height: 37px; font-size: 13px;"
														data-original-title="Total Dimension(Sqft)" rel="tooltip" onchange="onChangeNewDimension(this.value)"/>
												</label>
											</section>
				
											<section class="col col-3" id="noOfSitesEntry">
												<label class="label">No.&nbsp;Of&nbsp;Sites</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> 
													<input type="text" name="noofsites"
														placeholder="No. of Sites"
														id="noofsites" class="form-control"
														style="height: 37px; font-size: 13px;"
														data-original-title="No Of Sites" rel="tooltip" onchange="onChangeNoOfSites(this.value)"/>
												</label>
											</section>
				
											<section class="col col-3" >
												<label class="label">Requested Load(KW)&nbsp;</label><label
													class="input"><i class="icon-prepend fa fa-ban"></i> <input
														type="text" name="requestedloadkw" id="requestedloadkw" 
														placeholder="Requested Load(KW)"/> </label>
											</section>
				
											
										</div>
				
									</fieldset>
				
									<footer>
				
										<button type="submit" class="btn btn-primary">Add</button>
				
										<a class="btn btn-danger" onclick="return  cancelForm();"
											href="javascript:void(0);">Back</a>
				
									</footer>
								</form>
					 </div>
				 </div>
			 </div>
			 
			 
	 <script>
	 
	 function onChangeSiteDimesion(value){
		 
		  if(value=="20*30"){
			  $("#requestedloadkw").val(3);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('20*30');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="30*40"){
			  $("#requestedloadkw").val(6);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('30*40');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="60*40"){
			  $("#requestedloadkw").val(8);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('60*40');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else if(value=="50*80"){
			  $("#requestedloadkw").val(10);
			  $('#oddDimensionsEntry').hide();
			  $("#sitedimension").val('50*80');
			  $("#noofsites").val('1');
			  $('#noOfSitesEntry').show();
		  }else{
			  $("#sitedimension").val('');
			  $('#oddDimensionsEntry').show();
			  $("#noofsites").val('1');
			  $("#requestedloadkw").val('');
			  $('#noOfSitesEntry').show();
			  
			  
		  }
		}
	
	function onChangeNewDimension(value){
		
		if(value<=600){
			 $("#requestedloadkw").val(3);
		}else{
			
			if(value>4000){
				var newvalue=value-4000;
				var loadkiloWt=parseFloat((newvalue/400));
				$("#requestedloadkw").val((10+Math.ceil(loadkiloWt)));
			}else{
				if(value>600 && value<=1200){
					$("#requestedloadkw").val(6);
				}else if(value>1200 && value<=2400){
					$("#requestedloadkw").val(8);
				}else if(value>2400 && value<=4000){
					$("#requestedloadkw").val(10);
				}
			}
		}
	}

	function createLayouts() {
		$('#wid-id-rootedit').hide();
		$('#wid-id-showApplication').hide();
		$('#wid-id-receiptDataShow').hide();
		$('#wid-id-createsubconnections').hide();
		$('#wid-id-layouts').show();
	}
	
	function onChangeNoOfSites(noOfSites){
		
		var value=$('#dimensionsdrop').val();
		var load=0; 
		if(value=="20*30"){
			 load=3;
			  
		  }else if(value=="30*40"){
			  load=6;
		  }else if(value=="60*40"){
			  load=8;
		  }else if(value=="50*80"){
			  load=10;
		  }
		$("#requestedloadkw").val(noOfSites*(load));
		
	}
	$('select[id*=proposedinstallationtype]').change(function() {
		
		var proposedinsttype = $("#proposedinstallationtype").val();
		var voltageSupply="LT";
		var area="";
		
		if(proposedinsttype == "CMC And Urban Local Bodies"){
			area="URBAN";
		}
		if(proposedinsttype=="Village Panchayat"){
			area="RURAL";
		}
		
		
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
	           newOption.attr('value',0).text(" "); 
	           $('#natureofinstal').empty(newOption);
	           var defaultOption = $('<option>');
	           defaultOption.attr('value',"").text("Select Installtion");
	           $('#natureofinstal').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinstal').append(newOption);
			}));
		}
	}); 
		
	});
	
	function onselectTariffforLayout(){
		var natureofinstallation = $("#natureofinstal").val();
		var res = natureofinstallation.split("-"); 
		
		 var result1 = res[1].indexOf("LT1") > -1;
		 var result2 = res[1].indexOf("LT2") > -1;
		 var result3 = res[1].indexOf("LT3") > -1;
		 var result4 = res[1].indexOf("LT4") > -1;
		 var result5 = res[1].indexOf("LT5") > -1;
		 var result7 = res[1].indexOf("LT7") > -1;
		 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
		
		if(ApplicationType=='Layout'){
			if(result1 || result4 || result2 || result3 || result7 || result5){
				alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
				$("#natureofinstal").val('');
			}
		}
		
	}
	function onselectTariffforLayout(){
		var natureofinstallation = $("#natureofinstal").val();
		var res = natureofinstallation.split("-"); 
		
		 var result1 = res[1].indexOf("LT1") > -1;
		 var result2 = res[1].indexOf("LT2") > -1;
		 var result3 = res[1].indexOf("LT3") > -1;
		 var result4 = res[1].indexOf("LT4") > -1;
		 var result5 = res[1].indexOf("LT5") > -1;
		 var result7 = res[1].indexOf("LT7") > -1;
		 var ApplicationType = document.getElementsByClassName('ApplicationType')[0].innerHTML;
		
		if(ApplicationType=='Layout'){
			if(result1 || result4 || result2 || result3 || result7 || result5){
				alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
				$("#natureofinstal").val('');
			}
		}
		
	}

	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	
	$(document).ready(function() {
	
		$('#wid-id-layouts').hide();
		
		$.validator.addMethod("specialCharValidation", function(value, element) {
			 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
		}, "Only letters, numbers allowed");
		
		$.validator.addMethod('minStrict', function(value, el, param) {
			return value > param;
		});
		
		$.validator.addMethod("regex", function(value, element, regexpr) {
			return regexpr.test(value);
		}, "");
		
		$.validator.addMethod("dateFormat",
			    function(value, element) {
			        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
			    },
			    "Please enter a date in the format dd/mm/yyyy.");
		
	
		 
		 $('#submit-layouts').validate({
				// Rules for form validation
					rules : {
						dimensionsdrop : {
							required : true,
						},
						proposedinstallationtype : {
							required : true,
						},
						natureofinstal : {
							required : true,
						},
						noofsites : {
							required : true,
							digits : true,
						},
						sitedimension : {
							required : true,
							regex : /^[0-9 .]*$/,
						},
						requestedloadkw : {
							required : true,
							regex : /^[0-9 .]*$/,
							max:10000
						},
						
						
					},
			
					// Messages for form validation
					messages : {
						requestedloadkw : {
							required : 'Please enter load(kw)',
							regex : 'please enter numbers',
							max : 'max value is 10000',
						},

						noofsites : {
							required : 'Please enter No. of sites',
							digits : 'Please enter numbers',
						},
						
					},
			
					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					}
				});
		 
	});
	 </script>