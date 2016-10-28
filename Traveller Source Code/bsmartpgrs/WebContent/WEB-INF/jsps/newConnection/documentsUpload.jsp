<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<div id="content">
	<section id="widget-grid" class="">
		<c:if test="${not empty customerDocumentsResult}">
			<script>
				var customerDocumentsResult = "${customerDocumentsResult}";
				alert(customerDocumentsResult);
			</script>

			<c:remove var="customerDocumentsResult" scope="session" />
		</c:if>

		<div class="row">

			<article class="col-sm-12 ">
				<div class="jarviswidget" id="wid-id-4"
					data-widget-editbutton="false" data-widget-custombutton="false">

					<header>
						<span class="widget-icon"><i class="fa fa-cloud-upload"></i>
						</span>
						<h2>Upload Documents</h2>

					</header>

					<div>

						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<form:form action="" method="post" id="customer-documets-upload"
								class="smart-form" commandName="customerDocumentsUpload"
								modelAttribute="customerDocumentsUpload"
								enctype="multipart/form-data" novalidate="novalidate"
								autocomplete="off">
								
								<fieldset>
									<div class="row">
										<section class="col col-4">
											<label class="label">Application ID&nbsp;<font color="red">*</font></label> <label
												class="select"> <select id="appId" name="appId">
													<option value="0" selected="" disabled="">Select
														AppId</option>
													<c:forEach items="${appIdsList}" var="appIds">
														<option value="${appIds.applicationId}">${appIds.applicationId}</option>
													</c:forEach>
											</select><i></i></label>
										</section>

										<section class="col col-4" id="id1">
											<label class="label">Document Name&nbsp;<font color="red">*</font></label><label
												class="input"><i class="icon-prepend fa fa-file"></i> <input type="text"
													name="docname" id="docname" placeholder="Document Name"></input>
											</label>
										</section>

										<section class="col col-4">
											<label class="label">Upload Document&nbsp;<font color="red">*</font></label>
											<div class="input input-file">
												<span class="button"><input type="file" id="imgInp"
													name="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</span><input type="text"
													placeholder="Upload" id="docText">
											</div>
										</section>

									</div>


								</fieldset>


								<footer>
									<button type="submit" class="btn btn-primary" id="addOption"
										onclick="return checkData(0);">
										<strong>Upload</strong>
									</button>
								</footer>
							</form:form>

						</div>

					</div>

				</div>
			</article>
			
			<div class="col-sm-12 ">
				<div class="jarviswidget" id="wid-id-5"
					data-widget-editbutton="false" data-widget-custombutton="false">
					<header>
						<span class="widget-icon"><i class="fa fa-search"></i>
						</span>
						<h2>Search for Documents</h2>

					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<form action="" method="post" id="customer-view"
								class="smart-form">


								<fieldset>
									<div class="row">
										<section class="col col-4">
											<label class="label">Application ID&nbsp;<font color="red">*</font></label> <label
												class="select"> <select id="appliid"
												name="appliid">
													<option value="" selected="" disabled="">Select ApplicationId</option>
													<c:forEach items="${appIdsList}" var="applicationIds">
														<option value="${applicationIds.applicationId}">${applicationIds.applicationId}</option>
													</c:forEach>
											</select><i></i></label>
										</section>

										<section class="col col-4">
											<button type="button" onclick="searchApplicationNo()"
												class="btn btn-primary"
												style="height: 31px; width: 150px; margin-left: 0px; margin-top: 24px;">Search</button>
										</section>

									</div>


								</fieldset>


							</form>

						</div>

					</div>

				</div>
		</div>
			<article class="col-sm-12 ">
				<c:if test="${not empty documentDetails}">
					<div class="jarviswidget"
						id="widtryr6" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
							<span class="widget-icon"><i class="fa fa-table"></i>
							</span>
							<c:forEach var="app" items="${documentDetails}" varStatus="status">
								<c:if test="${status.last}">
									<h2>
										Document Uploaded For Application ID - <b>
											${app.applicationid}</b>
									</h2>
								</c:if>
							</c:forEach>
						</header>

						<div>
							<div class="jarviswidget-editbox"></div>
							<div class="widget-body no-padding">

								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>

											<th width="220">Document&nbsp;</th>
											<!-- <th>Abbreviation</th> -->
											<th>Type&nbsp;Of&nbsp;Document</th>
											<!--    <th>File Name</th> -->
											<th>View /Download</th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="app" items="${documentDetails}"
											varStatus="status">
											<tr>
											<c:if test="${status.last}">
												
												
												<td>Application Form</td>
												<td>PDF</td>
											<td><a href='#' onclick="return applicationDocumentView(${app.applicationid});"><strong>View / Download</strong></a></td>
											
											</c:if>
											</tr>
										</c:forEach>
										
										<c:forEach var="app" items="${documentDetails}">
											<tr>
												<td>${app.docname}</td>
												<%-- <td>${app.docabrv}</td> --%>
												<td>${app.typeOfDoc}</td>
												<%--   <td>${app.fileName}</td> --%>
												<%-- <td>${app.remarks}</td> --%>
											<td><a href='#' onclick="return checkViewDocument(${app.id});"><strong>View / Download</strong></a></td>
												
												<%-- <td><button type="submit" class="btn btn-primary"
														onclick="return checkViewDocument(${app.id});"
														style="margin-left: 13px; padding-bottom: 2px; padding-top: 0;">
														<strong>View / Download</strong>
													</button></td> --%>

											</tr>
										</c:forEach>

										
									
										


									</tbody>

								</table>

							</div>

						</div>

					</div>

				</c:if>


			</article>
		</div>
	</section>
</div>


<script>

	function readURL(input) {
	    if (input.files && input.files[0]) {
	        
	    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
	    	if(Math.floor(fsize)>parseFloat(1024.0)){
	    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
	    		$('#imgInp').val("");
	        	$('#docText').val("");
	            return false;
	    	}
	    	
	    	var reader = new FileReader();
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.xlsx|.pdf|.doc)$/;
	        if (regex.test(input.files[0].name.toLowerCase())) {
	        	
	        	reader.onload = function (e) {
	        		
	            }
	        	  reader.readAsDataURL(input.files[0]);
	        }else {
	        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
	        	$('#imgInp').val("");
	        	$('#docText').val("");
	            return false;
	        }
	       
	        } 
	    
	}


	$("#imgInp").change(function(){
	    readURL(this);
	});

	
	$(document).ready(function() {
		pageSetUp();
	});
	
	function searchApplicationNo()
	{
		
	 var applicationid=document.getElementById("appliid").value;

	 if(applicationid=="" || applicationid==null){
		 alert("Please select Application Id");
	 }else{
	 window.location.href="./getDocumentDetailsByApplicationId?appId="+applicationid;
	 }
		
	}
	
	function checkData(param) {
		$("#customer-documets-upload").attr("action","./customerDocuments/upload").submit();
	}
	
	function checkViewDocument(docId) {
		window.open("./customerUploadedDoc/download/"+docId);
	}
	
	function applicationDocumentView(appliId){
		
		window.open("./applicationDocumentViewByApplicationId/download/"+appliId);
	}

	
	$(document).ready(function() {
		
		$.validator.addMethod("regex", function(value, element, regexpr) {
			return regexpr.test(value);
		}, "");
		
		
	      $("#customer-documets-upload").validate({

			// Rules for form validation
			rules : {
				appId : {
					required : true
					
				},
				
				docname : {
					required : true,
					maxlength : 225,
					regex : /^[a-zA-Z0-9 ._]*$/
					
				},
				
				/* remarks : {
					required : true
					
				}, */
				file : {
					required : true
					
				},
				
				/* typeOfDoc : {
					required : true
					
				}, */
				
				
				
			},

			// Messages for form validation
			messages : {
				
				
				
				docname : {
					required : 'Please enter document name',
					maxlength:"maxlength is 225 characters",
					regex:"please enter valid document name"
					
				
				},
				
				file : {
					required : 'Please upload doccument'
				},
				
				appId : {
					required : 'Please select application Id'
				},
				
				typeOfDoc : {
					required : 'Please select type of document'
				},
				
				
				
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

	   
	     
		}); 
	      
	</script>
	
	<style>
th {
	vertical-align: middle !important
}

.popover-content {
	overflow: overlay;
	max-height: 250px;
}

.sparkline {
	width: 100% !important
}

.easy-pie-title {
	overflow: visible !important;
}
</style>