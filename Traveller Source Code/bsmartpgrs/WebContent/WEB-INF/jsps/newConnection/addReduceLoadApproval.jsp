<%@include file="/common/taglibs.jsp"%>
 <%@include file="/common/commonPage.jsp"%>
<script src="./resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>



<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

			<div id="content">
			<div style="display: none;">
              	<table>
           		<tr><td class="appphase">${appPhase}<td></tr>
            	</table>
            </div>
               
               
				
				
				
				<div class="row" style="margin-top: 5px;margin-left: 5px;margin-right: 5px;margin-bottom: 9px;">
				
							<div class="col-sm-4" >
							<a class="btn bg-color-orange btn-lg txt-color-white" id="redirectData2" style="width:350px;">In Progress </a>							
							</div>		
							
							<div class="col-sm-4">
							<a class="btn bg-color-greenLight btn-lg txt-color-white" id="redirectData3" style="width:350px;">Completed </a>						           
							</div>		
							
							<div class="col-sm-4">
						            <a class="btn bg-color-redLight btn-lg txt-color-white" id="redirectData4" style="width:350px;">On Hold</a>
							</div>		
				
				</div>
				
				
				
			
				
                    <div class="row">
						<article class="col-sm-12">
							<!-- new widget -->
							<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
								
								<header>
									<span class="widget-icon">  </span>
									<h2>Application ID - <b><font color="maroon">${applicationId} </font> </b> </h2>
									
                                    

									<ul class="nav nav-tabs pull-right in" id="myTab">
										
										<li class="active">
											<a data-toggle="tab" href="#s2"><i class=""></i> <span class="hidden-mobile hidden-tablet">Load Verification</span></a>
										</li>
										
										
										
									</ul>

								</header>

								<!-- widget div-->
								<div class="no-padding">
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">

										test
									</div>
									<!-- end widget edit box -->

									<div class="widget-body">
										<!-- content -->
										<div id="myTabContent" class="tab-content">
										
																
											<div>
											
											
											 <article class="col-sm-12 col-md-12 col-lg-6">
			
												<!-- Widget ID (each widget will need unique ID)-->
												<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false" data-widget-custombutton="false">
													
													<header>
														<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
														<h2>Add/Reduce Load Details Verification</h2>				
													</header>
									
													<!-- widget div-->
													<div>
														<!-- widget content -->
														<div class="widget-body no-padding">
															<form action="./fieldVerifactionInsert" method="post" id="fieldVerfication-form" class="smart-form"  enctype="multipart/form-data" autocomplete="off">
																<fieldset>
																	<div class="row">
																		<section class="col col-6">
																			<label class="label">Application ID</label>
																			<label class="input">
																				<input type="text"	name="applicationid" placeholder="Application ID"  id="applicationid" value="${applicationId}" readonly="true">
																			</label>
																		</section> 
																		
																	</div>
																	
																	<div class="row">
																		<section class="col col-6">
																			<label class="label"> Verification Ref Number &nbsp;<font color="red">*</font></label>
																			<label class="input">
																			<input type="text"	name="fvrefno" placeholder=" Verification Ref Number"  id="fvrefno" onchange="checkduplicate()"  autocomplete="off">
																			</label>
																		</section>
																		
																		
																		<section class="col col-6">
																			<label class="label"> Verification Date&nbsp;<font color="red">*</font></label>
																			<label class="input"> <i class="icon-append fa fa-calendar"></i>
																				<input type="text"	name="fvdate" placeholder=" Verification Date" path="fvdate" id="fvdate" >
																			</label>
																		</section>
																		
																	</div>
																	
																	
															
																	<section>
																		<label class="textarea"> Remarks <font color="red">*</font>									
																			<textarea rows="2" cols="50"></textarea>
																		</label>
																	</section>
																	
																	<section>
																	<label class="label">Documents&nbsp;<font color="red">*</font></label>
																		<div class="input input-file">
																			<span class="button"><input id="file" type="file" name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text" id="fileid" name="fileid" placeholder="Field Verification Document (Pdf,Image,Excel and Word)" readonly="">
																		</div>
																	</section>
																	
																	<section class="col col-6">
																		<label class="label">Other Document&nbsp;</label><label
																			class="input"><i class="icon-prepend fa fa-file"></i> <input type="text"
																				name="documentname" id="documentname" placeholder="Document Name"></input>
																		</label>
																	</section>
																	
																	<section class="col col-6">
																	<label class="label">Upload Document&nbsp;</label>
																		<div class="input input-file">
																			<span class="button"><input id="file1" type="file" name="file1" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text"
													                         placeholder="Upload Document" id="docText">
																		</div>
																	</section>
																	
																	
																	
																</fieldset>
									
																<footer>
																	<button type="submit" class="btn btn-primary">
																		Submit 
																	</button>
																	 <a class="btn btn-danger" onclick="return  OnHold('${applicationId}','${appPhase}');" href="javascript:void(0);">On Hold</a>
																</footer>
															</form>
														</div>
														<!-- end widget content -->
													</div>
													<!-- end widget div -->
												</div>
												<!-- end widget -->
												</article>

											</div>
											
											
											<div class="tab-pane fade" id="s3">
                                             <div class="col-sm-6">
                                        			<br>
														
												 	<table class="table table-bordered table-striped">
												 	<thead>
										     <tr>
											<th width="220">Name</th>
											<th>Uploaded ON</th>
											<th>Type</th>
											<!--    <th>File Name</th> -->
											<th>View/Download</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="app" items="${documentDetails}">
									
											<tr>
												<td>${app.docname}</td>
												<td><fmt:formatDate value="${app.uploadedOn}" pattern="dd-MM-yyyy"/></td>
												<td>${app.typeOfDoc}</td>
												<td><a href='#' onclick="return checkViewDocument(${app.id});">View/Download</a>  </td>

											</tr>
										</c:forEach>
									</tbody>
															
														</table>  
													</div>
										
											</div>
										<!-- end content -->
									</div>

								</div>
								<!-- end widget div -->
							</div>
							<!-- end widget -->

						</article>
	         	</div>

			</div>
			<!-- END MAIN CONTENT -->

		
		<!-- END MAIN PANEL -->

		
		
		
		

		<script type="text/javascript">
		
		function applicationDocumentView(appliId){
			
			window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
		}
		
		function checkMeterPhase(){
			var subTariffType="${natureOfSubTariff}";
			var result3 = subTariffType.indexOf("HT") > -1;
			if(result3 && document.getElementById("meterphase").value==1){
				alert("Please select Three Phase for HT Installations"); 
				$('#meterphase').val("0");
			}
		}
		
		function  OnHold(appId,phase){
			//alert(appId);
			//alert(phase);
			var remarks=$("#remarks").val();
			var remlength=remarks.length;
			
			if(remlength!=0){
			$.ajax({
				type : "GET",
				url : "./updateOnHoldStatus",
				dataType : "text",
				data : {

					remarks : remarks,
					appId:appId,
					phase:phase
				},
				success : function(response) {
					alert(response);
					$('#remarks').val("");
				}
			});
			}else{
				alert("Please Enter Remarks");
			}
		}
		function checkduplicate(){
			var refNo=$("#fvrefno").val();
			var phasecount=3;			
			$.ajax({
				type : "GET",
				url : "./checkrefDuplicate",
				dataType : "text",
				data : {

					refNo : refNo,
					phasecount:phasecount,
				},
				success : function(response) {
					
					if (response == "NF") {

					} else {
						alert("Reference No all ready registered");
						$("#fvrefno").val(" ");
					}
				}
			});
		}
		var dialog = "";
		function applicationHistoryPopUp(docketNo)
		{
			dialog = $("#addtab").dialog({
				autoOpen : false,
				width : 500,
				resizable : false,
				modal : true,
				
			}).dialog("open");
		}
		
		function checkViewDocument(docId) {
			window.open("./employeeViewDoc/download/"+docId);
		}
		
		$('#redirectData2').click(function () {
			var appStatus="In Progress";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });

		 
		$('#redirectData3').click(function () {
			var appStatus="Completed";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });
		 
		 
		$('#redirectData4').click(function () {
			var appStatus="Pending";
			var appPhase = document.getElementsByClassName('appphase')[0].innerHTML;
			window.location.href="./dataTab?appStatus="+appStatus+"&appPhase="+appPhase;
		    
		 });
		
		
		$("#file").change(function(){
		    readURL11(this);
		});



		function readURL11(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
		        if (regex.test(input.files[0].name.toLowerCase())) {
		        	
		        	reader.onload = function (e) {
		        		
		        	  reader.readAsDataURL(input.files[0]);
		        	  
		        	 /*  var x = document.getElementById("file").parentNode.nextSibling.innerHTML;
		        	  alert(x);
		        	  x.val(input); */
		            }
		        }else {
		        	alert("Only images,word,pdf and excel files are allowed");
		        	$('#fileid').val("");
		           return false;
		        }
		        
		       
		        } 
		    
		}
		
		
		$("#file1").change(function(){
		    readURL111(this);
		});



		function readURL111(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
		        if (regex.test(input.files[0].name.toLowerCase())) {
		        	
		        	reader.onload = function (e) {
		        	reader.readAsDataURL(input.files[0]);
		        	
		            }
		        }else {
		        	alert("Only images,word,pdf and excel files are allowed");
		        	$('#docText').val("");
		           return false;
		        }
		        
		       
		        } 
		    
		}
		
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
		 var monthyear=appdate.split("-");					 
			var monlast="";
			var year="";
			var date="";
		    for(var i=0;i<=monthyear.length;i++){
		    	 date=monthyear[0];
		    	 monlast=monthyear[1];
		         year=monthyear[2];
		    	
		    }
		 var month=(parseFloat(monlast))-1;					   
		 var minDate=new Date(year,month,date);	
		 $("#fvdate").datepicker("option","minDate",minDate );
			
			
			
			$.validator.addMethod("specialCharValidation", function(value, element) {
				 return this.optional(element) || /^[a-z0-9/ ]+$/i.test(value);
			}, "Only letters, numbers allowed");

			jQuery.validator.addMethod("lettersonly", function(value, element) {
				  return this.optional(element) || /^[a-z ]+$/i.test(value);
				}, "Letters only please");
			
			$.validator.addMethod("regex", function(value, element, regexpr) {
				return regexpr.test(value);
			}, "");
			
			
			 $('#fieldVerfication-form').validate({
					// Rules for form validation
						rules : {
							fvrefno : {
								required : true,
								specialCharValidation:true,
								maxlength : 50,
								regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
							},
							
							fvdate : {
								required : true,
								
							},
							metercapacity : {
								required : true,
								
							},
							meterrating : {
								required : true,
								
							},
							meterphase : {
								required : true,
								
							},
							remarks : {
								required : true,
								
							},
							fileid:{
								required : true,	
							},
							polenumber:{
								required : true,
								maxlength : 11	
							},
						},
				
						// Messages for form validation
						messages : {
							
							fvrefno : {
								required : 'Enter Field Verification Ref. Number',
								regex:'Please Enter Valid Reference No.',
								
							},
							polenumber:{
								required : 'Please Enter Pole Number',
								maxlength : 'maxlength is 11 for Pole Number',
							},
							
							fvdate : {
								required : 'Select Field Verification Date',
								
							},
							metercapacity : {
								required : 'Select Meter  Capacity',
								
							},
							meterrating : {
								required : 'Select Meter Rating',
								
							},
							meterphase : {
								required : 'Select Meter Phase',
								
							},
							remarks : {
								required : 'Enter Remarks/Comment',
								
							},
							fileid : {
								required : 'Upload Document',
								
							}
						},
				
						// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
		
		})

		$('#fvdate').datepicker({
			dateFormat : 'dd/mm/yy',
			prevText : '<i class="fa fa-chevron-left"></i>',
			nextText : '<i class="fa fa-chevron-right"></i>',
			maxDate: new Date(),
				
		});
		    
		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
			var _gaq = _gaq || [];
				_gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
				_gaq.push(['_trackPageview']);
			
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();

		</script>