<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script> 
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 
<!-- MAIN CONTENT -->


<!-- widget grid -->
	<div id="content">
   <section id="widget-grid" class="">
		<div class="row">

			
			        
			<article class="col-sm-12">

				<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-17" data-widget-editbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-edit"></i>
						</span>
						<h2>Application Registration</h2>

					</header>

					<div>


						<div class="jarviswidget-editbox"></div>

						<div class="widget-body no-padding">

							<form:form action="" method="post" id="smart-form-register"
								class="smart-form" commandName="application"
								modelAttribute="application" enctype="multipart/form-data">

								<fieldset>

									<div class="row">
										<section class="col col-2">
											<label class="label">Application ID</label> <label
												class="input"> <form:input type="text"
													name="applicationid" placeholder="Application ID"
													path="applicationid" id="applicationid"></form:input>
											</label>
										</section>



										<section class="col col-2">
											<label class="label">Application Ref. No</label> <label
												class="input"><form:input type="text"
													name="apprefno" placeholder="ARF Reference No"
													path="apprefno" id="apprefno"></form:input> </label>
										</section>
										<section class="col col-2">
											<label class="label"> Application Reg. Date</label><label
												class="input"><i class="icon-append fa fa-calendar"></i>
												<form:input type="text" name="appregdate" id="appregdate"
													placeholder="ARF Registration Date" path="appregdate"
													readonly="readonly"></form:input> </label>
										</section>





										<section class="col col-2">
											<label class="label">ARF Receipt No</label> <label
												class="input"><form:input type="text"
													name="arfreceiptno" placeholder="ARF Reference No"
													path="arfreceiptno" id="arfreceiptno"></form:input> </label>
										</section>
										<section class="col col-2">
											<label class="label"> ARF Receipt Date</label><label
												class="input"><i class="icon-append fa fa-calendar"></i>
												<form:input type="text" name="arfreceiptdt"
													id="arfreceiptdt" placeholder="ARF Receipt Date"
													path="arfreceiptdt"></form:input> </label>
										</section>
										<section class="col col-2">
											<label class="label">ARF Amount</label> <label class="input"><i
												class="icon-prepend fa fa-rupee"></i><form:input
													type="text" name="arfamount" placeholder="ARF Amount"
													path="arfamount" id="arfamount"></form:input> </label>
										</section>

									</div>


									<div class="row">

										<section class="col col-3">
											<label class="label">Proposed Installation Lies Under</label>
											<label class="input" for="select-20"> <form:select
													class="form-control" id="select-20" path="locality">
													<form:option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</form:option>
													<form:option value="Village Panchayat">Village Panchayat</form:option>

												</form:select>
											</label>
										</section>


										<section class="col col-3">
											<label class="label">Nature of installation</label> <label
												class="select"><form:select class="form-control"
													id="natureofinst" path="natureofinst" name="natureofinst">
													<form:option value="">-- LT Category ------</form:option>
													<form:option value="BJ/KJ Installation">BJ/KJ Installation</form:option>
													<form:option value="Domestic Lighting">Domestic Lighting</form:option>
													<form:option value="Domestic Lighting Heating">Domestic Lighting Heating</form:option>
													<form:option value="Educational Inst. Aided/UnAided">Educational Inst. Aided/UnAided</form:option>
													<form:option value="Commercial Lighting">Commercial Lighting</form:option>
													<form:option value="Commercial Power">Commercial Power</form:option>
													<form:option value="Industrial Power">Industrial Power</form:option>
													<form:option value="Irrigation Pump Sets">Irrigation Pump Sets</form:option>
													<form:option value="Water Supply">Water Supply</form:option>
													<form:option value="Street Light">Street Light</form:option>
													<form:option value="Temporary">Temporary</form:option>

													<form:option value="">-- HT Category ------</form:option>
													<form:option
														value="Water Supply and Sewerage
														(Industries/Universities/Educatinal Inst.)">Water Supply and Sewerage
														(Industries/Universities/Educatinal Inst.)</form:option>
													<form:option value="Commercial">Commercial</form:option>
													<form:option value="Govt LI Schemes/Socities">Govt LI Schemes/Socities</form:option>
													<form:option
														value="Private LI Schemes/Socities (URBAN FEEDER)">Private LI Schemes/Socities (URBAN FEEDER)</form:option>
													<form:option
														value="Private LI Schemes/Socities (OTHER THEN
														URBAN FEEDER)">Private LI Schemes/Socities (OTHER THEN
														URBAN FEEDER)</form:option>
													<form:option
														value="Irrigation/Agri Forms/Govt
														Horticulture/Coffee/Tea Plantation">Irrigation/Agri Forms/Govt
														Horticulture/Coffee/Tea Plantation</form:option>
													<form:option value="Res Appartments/Colonies (Urban)">Res Appartments/Colonies (Urban)</form:option>
													<form:option value="Res Appartments/Colonies (Rural)">Res Appartments/Colonies (Rural)</form:option>


												</form:select> </label>
										</section>

										<section class="col col-3">
											<label class="label">Tariff Type</label> <label class="input"
												for="select-2"> <form:select class="form-control"
													id="select-2" path="tariffdesc">

													<form:option value="LT-2a(i)">LT-2a(i)</form:option>
													<form:option value="LT-2a(ii)">LT-2a(ii)</form:option>
													<form:option value="LT-2b(i)">LT-2b(i)</form:option>
													<form:option value="LT-2b(ii)">LT-2b(ii)</form:option>
													<form:option value="LT-3(i)">LT-3(i)</form:option>
													<form:option value="LT-3(ii)">LT-3(ii)</form:option>
													<form:option value="LT-4(a)">LT-4(a)</form:option>
													<form:option value="LT-4(b)">LT-4(b)</form:option>
													<form:option value="LT-4(c)(i)">LT-4(c)(i)</form:option>
													<form:option value="LT-4(c)(ii)">LT-4(c)(ii)</form:option>
													<form:option value="LT-5(a)">LT-5(a)</form:option>
													<form:option value="LT-5(b)">LT-5(b)</form:option>
													<form:option value="LT-6(a)">LT-6(a)</form:option>
													<form:option value="LT-6(b)">LT-6(b)</form:option>

												</form:select>
											</label>
										</section>


										<section class="col col-3">
											<label class="label"> Applicant Type</label> <label
												class="input" for="select-22"> <form:select
													class="form-control" id="select-22"
													onchange="individualLabel(this.value);"
													path="applicanttype">
													<form:option value="0">Individual</form:option>
													<form:option value="1">Organisation</form:option>


												</form:select>
											</label>
										</section>


									</div>


									<div class="row">
									
										<section class="col col-2">
										<label class="checkbox" id="userProfile1" style="color: green;"><input type="checkbox" name="userProfile" id="userProfile"  value="1" onchange="checkBoxLabelForProfile();"/><i></i>Use Profile Details</label>
									    </section>
									    
										<section class="col col-3">
											<label class="label" id="personNameLabel">Name</label> <label
												class="input"><i class="icon-prepend fa fa-user"
												id="usericon"></i> <form:input type="text" name="name"
													placeholder="Name" path="name" id="personName"></form:input>
											</label>
										</section>


										<section class="col col-3">
											<label class="label" id="fatherNameLabel">Father/Husband
												Name</label> <label class="input"><i
												class="icon-prepend fa fa-user" id="usericon1"></i> <form:input
													type="text" name="fhname" placeholder="Father Name"
													path="fhname" id="fatherName"></form:input> </label>
										</section>

										<section class="col col-4">
											<label class="label" id="nomineNameLabel">Name Of
												Nominee</label> <label class="input"><i
												class="icon-prepend fa fa-user" id="usericon2"></i> <form:input
													type="text" name="nomineename"
													placeholder="Nominee(Optinal)" path="nomineename"
													id="nomineName"></form:input> </label>
										</section>
									</div>


									<div class="row">
										<section class="col col-4">
											<label class="label" id="orgNameLabel">Name Of The
												Organisation</label> <label class="input"><form:input
													type="text" name="nameoforg"
													placeholder="Organization Name" path="nameoforg"
													id="orgName"></form:input> </label>
										</section>


										<section class="col col-4">
											<label class="label" id="authSigNameLabel">Name Of
												Authorised Signatory</label> <label class="input"><i
												class="icon-prepend fa fa-user" id="usericon3"></i>
											<form:input type="text" name="nameauthsignatory"
													placeholder="Name of Auth Sig" path="nameauthsignatory"
													id="authSigName"></form:input> </label>
										</section>

										<section class="col col-4">
											<label class="label" id="authDesNameLabel">Designation
												Of Authorised Signatory</label> <label class="input"> <form:input
													type="text" name="desigauthsignatory"
													placeholder="Auth Designation" path="desigauthsignatory"
													id="authDesName"></form:input>
											</label>
										</section>
									</div>


									<div class="row">
										<section class="col col-4">
											<label class="label" style="color: green;"><strong>Permanent Address Where Power
												Supply is required</strong></label>

										</section>


										<section class="col col-2">
											<label class="checkbox"><form:checkbox
													name="addresssameas" id="addresssameas"
													path="addresssameas" value="1" onchange="checkBoxLabel();"></form:checkbox><i></i>Same
												Address</label>
										</section>



										<section class="col col-6">
											<label class="label" style="color: green;"><strong>Permanent Address</strong></label>
										</section>

									</div>


									<div class="row">

										<section class="col col-2">
											<label class="label">House/Flat No</label> <label
												class="input"> <form:input type="text"
													name="floorpres" placeholder="House/Flat No" path="hnopres"
													id="hnopres"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Floor No</label> <label class="input">
												<form:input type="text" name="floorpres"
													placeholder="Floor No" path="floorpres" id="floorpres"></form:input>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Street Name</label> <label class="input"><i class="icon-prepend fa fa-road"></i>
												<form:input type="text" name="streetpres"
													placeholder="Street Name" path="streetpres" id="streetpres"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">House/Flat No</label> <label
												class="input"> <form:input type="text"
													name="floorpres" placeholder="House/Flat No" path="hnoperm"
													id="hnoperm"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Floor No</label> <label class="input">
												<form:input type="text" name="floorperm"
													placeholder="Floor No" path="floorperm" id="floorperm"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Street Name</label> <label class="input"><i class="icon-prepend fa fa-road"></i>
												<form:input type="text" name="streetperm"
													placeholder="Street Name" path="streetperm" id="streetperm"></form:input>
											</label>
										</section>
									</div>


									<div class="row">
										<section class="col col-2">
											<label class="label">Area/Location</label> <label
												class="input"> <form:input type="text"
													name="areapres" placeholder="Area/Location" path="areapres"
													id="areapres"></form:input>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Cross</label> <label class="input">
												<form:input type="text" name="crosspres" placeholder="Cross"
													path="crosspres" id="crosspres"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Main</label> <label class="input">
												<form:input type="text" name="mainpres" placeholder="Main"
													path="mainpres" id="mainpres"></form:input>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Area/Location</label> <label
												class="input"> <form:input type="text"
													name="areaperm" placeholder="Area/Location" path="areaperm"
													id="areaperm"></form:input>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Cross</label> <label class="input">
												<form:input type="text" name="crossperm" placeholder="Cross"
													path="crossperm" id="crossperm"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Main</label> <label class="input">
												<form:input type="text" name="mainperm" placeholder="Main"
													path="mainperm" id="mainperm"></form:input>
											</label>
										</section>

									</div>

									<div class="row">
										<section class="col col-2">
											<label class="label">City</label> <label class="input">
												<form:input type="text" name="citypres" placeholder="City"
													path="citypres" id="citypres"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">Pin</label> <label class="input"><i class="icon-prepend fa fa-map-marker"></i>
												<form:input type="text" name="pinpres" placeholder="Pin"
													path="pinpres" id="pinpres"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Phone</label> <label class="input"><i
												class="icon-prepend fa fa-phone"></i> <form:input
													type="text" name="phonepres" placeholder="Phone No"
													path="phonepres" id="phonepres"></form:input> </label>
										</section>

										<section class="col col-2">
											<label class="label">City</label> <label class="input">
												<form:input type="text" name="cityperm" placeholder="City"
													path="cityperm" id="cityperm"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">Pin</label> <label class="input">
												<form:input type="text" name="pinperm" placeholder="Pin"
													path="pinperm" id="pinperm"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Phone</label> <label class="input"><i
												class="icon-prepend fa fa-phone"></i> <form:input
													type="text" name="phoneperm" placeholder="Phone No"
													path="phoneperm" id="phoneperm"></form:input> </label>
										</section>

									</div>
									<div class="row">
										<section class="col col-2">
											<label class="label">Mobile</label> <label class="input"><i
												class="icon-prepend fa fa-mobile"></i> <form:input
													type="text" name="mobilenopres" placeholder="Mobile No"
													path="mobilenopres" id="mobilenopres" data-mask="9999999999"></form:input> </label>
										</section>

										<section class="col col-2">
											<label class="label">Land mark</label> <label class="input">
												<form:input type="text" name="landmarkpres"
													placeholder="Land mark" path="landmarkpres"
													id="landmarkpres"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">E-Mail</label> <label class="input">
												<i class="icon-prepend fa fa-envelope-o"></i> <form:input
													type="email" name="emailpres" placeholder="E-Mail"
													path="emailpres" id="emailpres"></form:input>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Mobile</label> <label class="input"><i
												class="icon-prepend fa fa-mobile"></i> <form:input
													type="text" name="mobilenoperm" placeholder="Mobile No"
													path="mobilenoperm" id="mobilenoperm" data-mask="9999999999"></form:input> </label>
										</section>


										<section class="col col-2">
											<label class="label">Land mark</label> <label class="input">
												<form:input type="text" name="landmarkperm"
													placeholder="Land mark" path="landmarkperm"
													id="landmarkperm"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">E-Mail</label> <label class="input">
												<i class="icon-prepend fa fa-envelope-o"></i> <form:input
													type="email" name="emailperm" placeholder="E-Mail"
													path="emailperm" id="emailperm"></form:input>
											</label>
										</section>
									</div>







									<div class="row">

										<section class="col col-2">
											<label class="label">Desired Load(kW)</label> <label
												class="input"><i class="icon-prepend fa fa-spinner"></i> <form:input type="text" name="loadkw"
													placeholder="Desired Load(kW)" path="loadkw" id="loadkw"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Desired Load(HP)</label> <label
												class="input"> <form:input type="text" name="loadhp"
													placeholder="Desired Load(HP)" path="loadhp" id="loadhp"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">Desired Load(KVA)</label> <label
												class="input"> <form:input type="text"
													name="loadkva" placeholder="Desired Load(KVA)"
													path="loadkva" id="loadkva"></form:input>
											</label>
										</section>




										<section class="col col-2">
											<label class="label">Power Required For</label> <label
												class="input" for="select-4"> <form:select
													class="form-control" id="select-4" name="supplyfor"
													path="supplyfor">
													<form:option value="Residential">Residential</form:option>
													<form:option value="Commercial">Commercial</form:option>
													<form:option value="Industrial">Industrial</form:option>
													<form:option value="Public Water Supply">Public Water Supply</form:option>
													<form:option value="Agriculture IP Set">Agriculture IP Set</form:option>
													<form:option value="Temporary">Temporary</form:option>
													<form:option value="Kutira Jyothi">Kutira Jyothi</form:option>
													<form:option value="Bhagya Jyothi">Bhagya Jyothi</form:option>
													<form:option value="Ganga Kalyana">Ganga Kalyana</form:option>


												</form:select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">If Govt.</label> <label class="input"
												for="select-5"> <form:select class="form-control"
													id="select-5" path="govtpvt">

													<form:option value="0">Central</form:option>
													<form:option value="1">State</form:option>


												</form:select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Public Street/Park Light</label> <label
												class="input" for="select-6"> <select
												class="form-control" id="select-6">

													<option>Self</option>
													<option>Rental</option>
													<option>Public Use</option>

											</select>
											</label>
										</section>



									</div>


									<div class="row">
										<section class="col col-2">
											<label class="label">Purpose</label> <label class="input"
												for="select-7"> <select class="form-control"
												id="select-7">
													<option>Lighting</option>
													<option>Heating</option>

											</select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Phase Requirement</label> <label
												class="input" for="select-8"> <form:select
													class="form-control" id="select-8" path="reqphase">
													<form:option value="1">Single phase</form:option>
													<form:option value="3">Three Phase</form:option>

												</form:select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Requisition For</label> <label
												class="input" for="select-9"> <form:select
													class="form-control" id="select-9" path="reqfor">
													<form:option value="0">New service</form:option>
													<form:option value="1">A sub-Meter service</form:option>

												</form:select>
											</label>
										</section>


										<section class="col col-2">
											<label class="label">Special Govt Scheme</label> <label
												class="input" for="select-10"> <form:select
													class="form-control" id="select-10" path="splgovtscheme">
													<option>BJ</option>
													<option>KJ</option>
													<option>RGGVY</option>
													<option>GANGA KALYAN</option>
												</form:select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Type of Ownership</label> <label
												class="input" for="select-11"> <form:select
													class="form-control" id="select-11" path="ownership">
													<option>Individual</option>
													<option>Leased/Rental</option>
													<option>Co owned</option>
													<option>Proprietorship</option>
													<option>Public company</option>
													<option>Private Company</option>
													<option>PartnerShip</option>
													<option>Trust</option>
												</form:select>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">IP set, Water Source</label> <label
												class="input" for="select-12"> <form:select
													class="form-control" id="select-12" path="watersource">
													<option>Open well</option>
													<option>Bore well</option>
													<option>River</option>
													<option>Channel</option>

													<option>Sub-Channel</option>
													<option>Nallah</option>
												</form:select>
											</label>
										</section>

									</div>

									<div class="row">
										<section class="col col-2">
											<label class="label">RR Number</label> <label class="input">
												<form:input type="text" name="nearbyrrnoone"
													placeholder="RR Number" path="nearbyrrnoone"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">Account No.</label> <label class="input">
												<form:input type="text" name="nearbyacnoone"
													placeholder="Account No" path="nearbyacnoone"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">RR Number</label> <label class="input">
												<form:input type="text" name="nearbyrrnotwo"
													placeholder="RR Number" path="nearbyrrnotwo"></form:input>
											</label>
										</section>
										<section class="col col-2">
											<label class="label">Account No.</label> <label class="input">
												<form:input type="text" name="nearbyacnotwo"
													placeholder="Account No" path="nearbyacnotwo"></form:input>
											</label>
										</section>

										<section class="col col-2">
											<label class="label">RR Number</label> <label class="input">
												<form:input type="text" name="nearbyrrnothree"
													placeholder="RR Number" path="nearbyrrnothree"></form:input>
											</label>
										</section>



										<section class="col col-2">
											<label class="label">Account No.</label> <label class="input">
												<form:input type="text" name="nearbyacnothree"
													placeholder="Account No" path="nearbyacnothree"></form:input>
											</label>
										</section>

									</div>

									<div class="row">

										<!-- <section class="col col-6">
										<label class="label">List Of Documents</label> <label
											class="input" for="listOfDocs"> <select
											class="form-control" id="listOfDocs" name="listOfDocs">
												<option value="Proof of Ownership">1)Proof of Ownership</option>
												<option value="Building Plan">2)Building Plan</option>
												<option value="Wiring Diagram">3)Wiring Diagram</option>
												<option value="License from local authority">4)General License from local authority</option>
												<option value="Registered partnership deed">5)Registered partnership deed</option>
												<option value="Articles of association and Certificate">6)Memorandum and Articles of association and Certificate</option>
												<option value="Indemnity Bond">7)Indemnity Bond</option>
												<option value="Resident Address proof">8)Proof of the permanent residential address</option>
												<option value="Undertaking not to engage Child Labour">9)An undertaking not to engage Child Labour.</option>
												<option value="Location sketch">10)Location sketch</option>
												<option value="Agreement to install solar Water heater">11)Agreement to install solar Water heater</option>
												<option value="Power supply agreement">12)Power supply agreement</option>
										</select>
										</label>
									</section>
									
									<section class="col col-6">
										<label class="label">Upload File</label>
										<div class="input input-file">
											<span class="button"><input type="file" id="file"
												name="file"
												>Browse</span><input
												type="text" placeholder="Upload">
										</div>
									</section> -->


										<section class="col col-12">
											<table id="allFiles">
												<span onclick="addFileUploads();" style="height: 10px;"><i
													class=" fa fa-plus-square"></i> <span
													style="height: 10px; color: green;">Add files to
														Upload</span> </span>
												<tr></tr>
											</table>
										</section>

									</div>

								</fieldset>



								<footer>
									<button type="submit" class="btn btn-primary" id="addOption"
										onclick="return checkData(0);">
										<strong><i class="fa fa-arrow-circle-right"
											style="margin-top: 10px"></i>Submit</strong>
									</button>

								</footer>


							</form:form>

						</div>


					</div>


				</div>


			</article>
		</div>
</section>
	</div>



<script type="text/javascript">
$(document).ready(function() {
	pageSetUp();
});



var fileId=0;
	  	 
 function updateSize(fileId,id) {   		 
  var nBytes = 0,
      oFiles = document.getElementById(fileId).files;   	      
      nFiles = oFiles.length;
  for (var nFileId = 0; nFileId < nFiles; nFileId++) {
    nBytes += oFiles[nFileId].size;
  }
  
  var sOutput = nBytes + " bytes";
  // optional code for multiples approximation
  for (var aMultiples = ["KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
    sOutput = nApprox.toFixed(2) + " " + aMultiples[nMultiple];  //  + " (" + nBytes + " bytes)";
  }
   document.getElementById("fileInfo"+id).className='btn yellow';
   document.getElementById("fileInfo"+id).innerHTML = sOutput;  
  
}   	


 function addFileUploads()
	 {
		 fileId++;
		 $('#allFiles').append('<tr id=fileData'+fileId+'><td><label class=label>List Of Documents</label> <label class=input for=listOfDocs><select class="form-control" id=listOfDocs'+fileId+' name=listOfDocs'+fileId+'><option value="Proof of Ownership">1)Proof of Ownership</option><option value="Building Plan">2)Building Plan</option><option value="Wiring Diagram">3)Wiring Diagram</option><option value="License from local authority">4)General License from local authority</option><option value="Registered partnership deed">5)Registered partnership deed</option><option value="Articles of association and Certificate">6)Memorandum and Articles of association and Certificate</option><option value="Indemnity Bond">7)Indemnity Bond</option><option value="Resident Address proof">8)Proof of the permanent residential address</option><option value="Undertaking not to engage Child Labour">9)An undertaking not to engage Child Labour.</option><option value="Location sketch">10)Location sketch</option><option value="Agreement to install solar Water heater">11)Agreement to install solar Water heater</option><option value="Power supply agreement">12)Power supply agreement</option></select></label></td><td><br></td><td><label><input type=file name=files id=file'+fileId+' onchange="return updateSize(this.id,'+fileId+')" style="height: 29px; font-size: 15px; color:blue;padding-top: 26px;margin-left: 220px;"/><button class=close  onclick="return cancelReset(this.name);" name=fileData'+fileId+' style="margin-right: -41px;margin-top: -21px;color:red;"><i class="fa fa-times-circle"></i></button></label></td><td><span  id=fileInfo'+fileId+' style="margin-left:30px;"></span></td></tr>');
	 }

function cancelReset(param)
  {	
 	    $('#'+param).remove();
 	    return false;
  }

	 
	$('#arfreceiptdt').datepicker({
		  dateFormat : 'dd/mm/yy',
		  maxDate: new Date()
	
	}); 
	 
	
    $(".content").ready(function() {

		document.getElementById("smart-form-register").reset();

	});   
 
    
$(document).ready(function() {
		
		var maxApplicationId= "${maxApplicationId}";
		
		$('#applicationid').val(maxApplicationId).attr("readonly", "readonly");
		
	
	});
	
	
	
 
   function checkData(param) {
	   
	   
	   var count=0;
	   for(var i = 1;i<=fileId;i++)
	   {
		   var temp = $("#listOfDocs"+i).val();
	       var fileinfo = $("#file"+i).val();
	      
	      if(fileinfo=="" || fileinfo==null){
	    	  alert("please upload document for :"+temp);
	    	  return false;
	      } 
	    
	      for(var j =1;j<=fileId;j++)
	    	  {
	    	  var temp1 = $("#listOfDocs"+j).val();
	    	
	    	  	if(temp == temp1)
	    	  		{
	    	  			count++;
	    	  			
	    	  		}
	    	  }
	      if(count > 1 && temp != 0)
	    	  {
	    	  alert("Duplicate Documents not allowed");
	    	  return false;
	    	  }
	      count = 0;
	  
	}
	

		if (document.getElementById("mobilenopres").value != ''
				&& document.getElementById("mobilenopres").value != null) {

			if (document.getElementById("loadkw").value != null
					&& document.getElementById("loadhp").value != null
					&& document.getElementById("loadkva").value != null) {

				if (document.getElementById("loadkw").value == 0
						&& document.getElementById("loadhp").value == 0
						&& document.getElementById("loadkva").value == 0) {

					alert("Please enter at least one Desired LOAD greater than Zero");
					return false;
				}
			}

			if (fileId == 0) {
				alert("please add at least one file");
			}

		}

		if (param == 0) {
			$("#smart-form-register").attr("action", "./application/create")
					.submit();
		}

	}

	function getPresentMonthDate(param) {
		var date = new Date();

		var month = date.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var year = date.getFullYear();
		if (param == null || param == "")
			return currentDate = year + "" + month;
		else
			return param;

	}

	$(document).ready(
			function() {

				$("#appregdate").val(getPresentMonthDate('${selectedMonth1}'))
						.attr("readonly", "readonly");

				document.getElementById("select-22").value = 'Individual';
				$("#usericon3").hide();
				$("#orgName").hide();
				$("#authSigName").hide();
				$("#authDesName").hide();

				$("#orgNameLabel").hide();
				$("#authSigNameLabel").hide();
				$("#authDesNameLabel").hide();

			});

	function checkBoxLabel() {

		var x = document.getElementById('addresssameas').checked;

		if (x) {

			var hnopres = document.getElementById('hnopres').value;
			var floorpres = document.getElementById('floorpres').value;
			var streetpres = document.getElementById('streetpres').value;

			var areapres = document.getElementById('areapres').value;
			var crosspres = document.getElementById('crosspres').value;
			var mainpres = document.getElementById('mainpres').value;

			var citypres = document.getElementById('citypres').value;
			var pinpres = document.getElementById('pinpres').value;
			var emailpres = document.getElementById('emailpres').value;

			var phonepres = document.getElementById('phonepres').value;
			var mobilenopres = document.getElementById('mobilenopres').value;
			var landmarkpres = document.getElementById('landmarkpres').value;

			$('#hnoperm').val(hnopres).attr("readonly", "readonly");

			$('#floorperm').val(floorpres).attr("readonly", "readonly");
			$('#streetperm').val(streetpres).attr("readonly", "readonly");
			$('#areaperm').val(areapres).attr("readonly", "readonly");
			$('#crossperm').val(crosspres).attr("readonly", "readonly");
			$('#mainperm').val(mainpres).attr("readonly", "readonly");

			$('#cityperm').val(citypres).attr("readonly", "readonly");
			$('#pinperm').val(pinpres).attr("readonly", "readonly");
			$('#emailperm').val(emailpres).attr("readonly", "readonly");
			$('#phoneperm').val(phonepres).attr("readonly", "readonly");
			$('#mobilenoperm').val(mobilenopres).attr("readonly", "readonly");
			$('#landmarkperm').val(landmarkpres).attr("readonly", "readonly");

		}

		else {

			$('#hnoperm').val('').removeAttr("readonly");
			$('#floorperm').val('').removeAttr("readonly");
			$('#streetperm').val('').removeAttr("readonly");
			$('#areaperm').val('').removeAttr("readonly");
			$('#crossperm').val('').removeAttr("readonly");
			$('#mainperm').val('').removeAttr("readonly");

			$('#cityperm').val('').removeAttr("readonly");
			$('#pinperm').val('').removeAttr("readonly");
			$('#emailperm').val('').removeAttr("readonly");
			$('#phoneperm').val('').removeAttr("readonly");
			$('#mobilenoperm').val('').removeAttr("readonly");
			$('#landmarkperm').val('').removeAttr("readonly");
		}

	}
	
	function checkBoxLabelForProfile(){
		var x = document.getElementById('userProfile').checked;
		if (x) {
			var customername= "${customername}";
			var mobilenumber= "${mobilenumber}";
			
			$('#personName').val(customername);
			$('#mobilenopres').val(mobilenumber);
		}

		else {
			$('#personName').val("");
			$('#mobilenopres').val("");
		}

	}

	function individualLabel(labelName) {

		if (labelName == "0") {

			$("#personName").show();
			$("#fatherName").show();
			$("#nomineName").show();
			$("#userProfile1").show();

			$("#personNameLabel").show();
			$("#fatherNameLabel").show();
			$("#nomineNameLabel").show();

			$("#usericon3").hide();

			$("#orgName").hide();
			$("#authSigName").hide();
			$("#authDesName").hide();

			$("#orgNameLabel").hide();
			$("#authSigNameLabel").hide();
			$("#authDesNameLabel").hide();

			$("#usericon").show();
			$("#usericon1").show();
			$("#usericon2").show();

		}

		if (labelName == "1") {

			$("#personName").hide();
			$("#fatherName").hide();
			$("#nomineName").hide();
			$("#userProfile1").hide();
			$("#usericon").hide();
			$("#usericon1").hide();
			$("#usericon2").hide();

			$("#usericon3").show();

			$("#personNameLabel").hide();
			$("#fatherNameLabel").hide();
			$("#nomineNameLabel").hide();

			$("#orgName").show();
			$("#authSigName").show();
			$("#authDesName").show();

			$("#orgNameLabel").show();
			$("#authSigNameLabel").show();
			$("#authDesNameLabel").show();

		}

	}

	$(document).ready(function() {

		$.validator.addMethod("regex", function(value, element, regexpr) {
			return regexpr.test(value);
		}, "");

		$.validator.addMethod('minStrict', function(value, el, param) {
			return value > param;
		});

		$("#smart-form-register").validate({

			// Rules for form validation
			rules : {
				applicationid : {
					required : true,
					digits : true,
					maxlength : 10
				},

				apprefno : {
					//required : true,
					regex : /^[a-zA-Z0-9]*$/,
					maxlength : 50
				},

				arfreceiptno : {
					required : true,
					//digits : true,
					regex : /^[a-zA-Z0-9]*$/,
					maxlength : 25
				},

				appregdate : {
					required : true
				},

				arfreceiptdt : {
					required : true
				},

				arfamount : {
					required : true,
					minStrict : 0,
					number : true
				},

				name : {
					required : true,
					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},
				fhname : {
					required : true,
					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},

				nomineename : {

					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},

				nameoforg : {
					required : true,
					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},
				nameauthsignatory : {
					required : true,
					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},
				desigauthsignatory : {
					required : true,
					maxlength : 100,
					regex : /^[a-zA-Z0-9 .]*$/
				},

				hnopres : {
					required : true,
					maxlength : 50
				},
				floorpres : {
					required : true,
					maxlength : 20
				},
				streetpres : {
					required : true,
					maxlength : 100
				},
				areapres : {
					required : true,
					maxlength : 100
				},
				crosspres : {
					required : true,
					maxlength : 20
				},

				mainpres : {
					required : true,
					maxlength : 20
				},
				citypres : {
					required : true,
					maxlength : 100
				},
				pinpres : {
					required : true,
					digits : true,
					regex : /^[0-9]{6}$/
				},
				mobilenopres : {
					required : true,
					digits : true,
					regex : /^[0-9]{10}$/
				},

				locality : {
					required : true,
					maxlength : 100
				},

				landmarkpres : {
					required : true,
					maxlength : 100
				},
				landmarkperm : {
					required : true,
					maxlength : 100
				},

				hnoperm : {
					required : true,
					maxlength : 50
				},
				floorperm : {
					required : true,
					maxlength : 20
				},
				streetperm : {
					required : true,
					maxlength : 100
				},
				areaperm : {
					required : true,
					maxlength : 100
				},
				crossperm : {
					required : true,
					maxlength : 20
				},

				mainperm : {
					required : true,
					maxlength : 20
				},
				cityperm : {
					required : true,
					maxlength : 100
				},
				pinperm : {
					required : true,
					digits : true,
					regex : /^[0-9]{6}$/
				},
				mobilenoperm : {
					required : true,
					digits : true,
					regex : /^[0-9]{10}$/
				},

				nearbyrrnoone : {
					required : true,
					maxlength : 25
				},

				loadkw : {
					required : true,
					maxlength : 12
				},

				loadhp : {
					required : true,
					maxlength : 12
				},

				loadkva : {
					required : true,
					maxlength : 12
				},

				nearbyrrnotwo : {

					maxlength : 25
				},
				nearbyrrnothree : {

					maxlength : 25
				},
				nearbyacnoone : {

					maxlength : 25
				},
				nearbyacnotwo : {

					maxlength : 25
				},
				nearbyacnothree : {

					maxlength : 25
				},

				emailpres : {
					email : true,
					maxlength : 100
				},

				natureofinst : {
					required : true
				},

				file : {
					required : true

				},

			},

			// Messages for form validation
			messages : {

				applicationid : {
					required : 'Please enter appl. id',
					digits : 'Numbers only please',
					maxlength : 'maxlength is 10'

				},
				apprefno : {
					//required : 'Please enter appl. ref no',
					regex : 'special characters not allowed',
					maxlength : 'maxlength is 50'
				},

				arfreceiptno : {
					required : 'Please enter appl. receipt no',
					//digits : 'Numbers only please',
					regex : 'special characters not allowed',
					maxlength : 'maxlength is 25'
				},

				appregdate : {
					required : 'Please select appl. reg date'
				},
				arfreceiptdt : {
					required : 'Please select appl. receipt date'
				},
				arfamount : {
					required : 'Please enter ARF amount',
					minStrict : 'amount must be >Zero'
				},

				name : {
					required : 'Please enter name',
					maxlength : 'maxlength is 100',
					regex : "please enter valid name"

				},
				fhname : {
					required : 'Please enter father name',
					maxlength : 'maxlength is 100',
					regex : "please enter valid name"
				},

				nomineename : {

					maxlength : 'maxlength is 100',
					regex : "please enter valid name"
				},

				nameoforg : {
					required : 'Please enter organization name',
					maxlength : 'maxlength is 100',
					regex : "please enter valid name"
				},
				nameauthsignatory : {
					required : 'Please enter auth. sig name',
					maxlength : 'maxlength is 100',
					regex : "please enter valid name"
				},
				desigauthsignatory : {
					required : 'Please enter designation name',
					maxlength : 'maxlength is 100',
					regex : "please enter valid name"
				},

				hnopres : {
					required : 'Please enter house/flat no'
				},
				floorpres : {
					required : 'Please enter floor no'
				},
				streetpres : {
					required : 'Please enter street name'
				},
				areapres : {
					required : 'Please enter area name'
				},
				crosspres : {
					required : 'Please enter cross name'
				},

				mainpres : {
					required : 'Please enter main'
				},
				citypres : {
					required : 'Please enter city'
				},
				pinpres : {
					required : 'Please enter pin',
					regex : "Enter 6 digit pincode",
				},
				mobilenopres : {
					required : 'Please enter mobile no',
					regex : "Enter 10 digit mobile no"
				},

				hnoperm : {
					required : 'Please enter house/flat no'
				},
				floorperm : {
					required : 'Please enter floor no'
				},
				streetperm : {
					required : 'Please enter street name'
				},
				areaperm : {
					required : 'Please enter area name'
				},
				crossperm : {
					required : 'Please enter cross name'
				},

				mainperm : {
					required : 'Please enter main'
				},
				cityperm : {
					required : 'Please enter city'
				},
				pinperm : {
					required : 'Please enter pin',
					regex : "Enter 6 digit pincode",
				},
				mobilenoperm : {
					required : 'Please enter mobile no',
					regex : "Enter 10 digit mobile no"
				},

				landmarkperm : {
					required : 'Please enter landmark'
				},

				locality : {
					required : 'Please select proposed installation'
				},

				landmarkpres : {
					required : 'Please enter landmark'
				},
				nearbyrrnoone : {
					required : 'Please enter RR no'
				},

				loadkw : {
					required : 'Please enter load(kw)',
					maxlength : 'maxlength is 12'
				},

				loadhp : {
					required : 'Please enter load(hp)'
				},
				loadkva : {
					required : 'Please enter load(kva)'
				},

				emailpres : {

					email : 'Please enter a valid email address'
				},

				nearbyrrnotwo : {

					maxlength : 'maxlength is 25 for RR No'
				},

				nearbyrrnothree : {

					maxlength : 'maxlength is 25 for RR No'
				},

				nearbyacnoone : {

					maxlength : 'maxlength is 25 for AC No'
				},

				nearbyacnotwo : {

					maxlength : 'maxlength is 25 for AC No'
				},

				nearbyacnothree : {

					maxlength : 'maxlength is 25 for AC No'
				},

				natureofinst : {

					required : 'please select nature of Inst.'
				},
				file : {
					required : 'Please upload doccument'
				},
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});

	});
</script>