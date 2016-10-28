<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/customerLeftMenu.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/bootstrap/bootstrap.min.js"></script>
<script src="./resources/js/plugin/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="./resources/js/plugin/fuelux/wizard/wizard.min.js"></script>
<script src="./resources/js/plugin/dropzone/dropzone.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="./resources/css/smartadmin-production.min.css">

<div id="content">

	<section id="widget-grid" class="">
		<div class="row">
			<article class="col-sm-12">
				<div class="jarviswidget jarviswidget-color-greenDark"
					id="wid-id-64" data-widget-editbutton="false"
					data-widget-colorbutton="false">

					<header>
						<span class="widget-icon"> <i class="fa fa-check"></i>
						</span>
						<h2>
							Application Registration&nbsp;&nbsp;<b>Note -</b> All the data
							entered to be submitted.There will not be any provision to submit
							the data later.
						</h2>
					</header>

					<div>

						<div class="jarviswidget-editbox"></div>

						<div class="widget-body">

							<div class="row">
								<form:form action="" method="post" commandName="application"
									modelAttribute="application" enctype="multipart/form-data"
									id="wizard-1" novalidate="novalidate" autocomplete="off">

									<div id="bootstrap-wizard-1" class="col-sm-12">
										<div class="form-bootstrapWizard">
											<ul class="bootstrapWizard form-wizard">
												<li class="active" data-target="#step1" id="tab11"><a
													href="#tab1" data-toggle="tab"> <span class="step">1</span>
														<span class="title">Nature Of Installation</span>
												</a></li>
												<li data-target="#step2" id="tab22"><a href="#tab2"
													data-toggle="tab"> <span class="step">2</span> <span
														class="title">General Particulars</span>
												</a></li>
												<li data-target="#step3" id="tab33"><a href="#tab3"
													data-toggle="tab"> <span class="step">3</span> <span
														class="title">Permanent Address</span>
												</a></li>
												<li data-target="#step4" id="tab44"><a href="#tab4"
													data-toggle="tab"> <span class="step">4</span> <span
														class="title">Connection & Account Details</span>
												</a></li>

												<li data-target="#step5" id="tab55"><a href="#tab5"
													data-toggle="tab"> <span class="step">5</span> <span
														class="title">Additional Details</span>
												</a></li>

												<li data-target="#step6" id="tab66"><a href="#tab6"
													data-toggle="tab"> <span class="step">6</span> <span
														class="title">Particulars of LEC</span>
												</a></li>

												<li data-target="#step7" id="tab77"><a href="#tab7"
													data-toggle="tab"> <span class="step">7</span> <span
														class="title">Required Documents</span>
												</a></li>


												<li data-target="#step9" id="tab99"><a href="#tab9"
													data-toggle="tab"> <span class="step">8</span> <span
														class="title">Save </span>
												</a></li>
											</ul>

											<div class="clearfix"></div>
										</div>
										<div class="tab-content">
											<div class="tab-pane active" id="tab1">
												<br>
												<h6>
													<strong>Step 1 </strong> - Nature Of Installation
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" data-toggle="tab" target="_blank" onclick="window.open('http://www.cescmysorepgrs.com/downloadCescomMap/download');">Click here to find  Your Section on CESC Map</a>
													
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="./downloadDocForApplicationRegistration/download"
														target="_blank"><font style="font-size: 15px;"><b>Click here to download the Application Form</b></font></a>
												</h6>

												<br>
												<div class="row">

													<label class="col-md-2 control-label">Application&nbsp;Reg&nbsp;Date</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-append fa fa-calendar"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="appregdate"
																	id="appregdate" placeholder="ARF Registration Date"
																	path="appregdate" readonly="readonly"
																	class="form-control"></form:input>
															</div>
														</div>
													</div>
													
													<label class="col-md-2 control-label">Circle&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																	
																	<select id="circleSiteCode" name="circleSiteCode" class="form-control">
																		<option value="" selected="" disabled="">Select
																			Circle</option>
																		<c:forEach items="${circleList}" var="circle">
																			<option value="${circle.circleSiteCode}">${circle.circleName}</option>
																		</c:forEach>
																	</select>
															</div>
														</div>
												</div>
												</div>

												<div class="row">
													
													<label class="col-md-2 control-label">Division&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																	
																	<select  id="divisionSiteCode" name="divisionSiteCode" class="form-control">
																		<option value="0" selected disabled>Select Division</option>
						  												
															   		 </select>
															</div>
														</div>
													</div>
													
													
													<label class="col-md-2 control-label">Sub&nbsp;Division&nbsp;<font
														color="red">*</font></label>
													
													<div class="col-sm-3">
														
														
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																	
																	<select  name="subDivisionSiteCode" id="subDivisionSiteCode" class="form-control">
																	<option value="0" selected disabled>Select Sub Division</option>
																	</select>
															</div>
														</div>
												</div>
												</div>

												<div class="row">
													
													<label class="col-md-2 control-label">Location&nbsp;(Section)&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span> 
																	
																<form:select
																	class="form-control" id="sitecode" path="sitecode">
																	<form:option value="0" selected="" disabled="">Select Section</form:option>

																</form:select>
															</div>
														</div>
													</div>
													
													<label class="col-md-2 control-label">Proposed&nbsp;Installation&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>

																<form:select class="form-control" id="locality" path="locality">
																	<option value="0" selected="" disabled="">Select</option>
																	<form:option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</form:option>
																	<form:option value="Village Panchayat">Village Panchayat</form:option>

																</form:select>
															</div>
														</div>


													</div>
												</div>
												<div class="row">


													<label class="col-md-2 control-label">Category&nbsp;<font
														color="red">*</font></label>
													
													<div class="col-sm-1">
														<div class="form-group">
															<div class="input-group">
																<input type="radio" value="ltcategory" id="ltcategory"
																	name="lt" checked="checked">&nbsp;&nbsp;<font
																	color=blue;><b>LT</b></font>
															</div>
														</div>
													</div>

													<div class="col-sm-1">
														<div class="form-group">
															<div class="input-group">
																<input type="radio" value="htcategory" id="htcategory"
																	name="lt">&nbsp;&nbsp;<font color=blue;><b>HT</b></font>
															</div>
														</div>
													</div>
													
													<div class="col-sm-1"></div>
														
													<div id="applicationTypeId"> 
															<label class="col-md-2 control-label">Application&nbsp;Type</label>	
															<div class="col-sm-3">
																<div class="form-group">
																	<div class="input-group">
																		<span class="input-group-addon"><i
																			class="fa fa-chevron-down"
																			style="height: 18px; font-size: 13px;"></i></span>
		
																		<form:select class="form-control" id="applicationtype" path="applicationtype" onchange="onChangeMSBuild(this.value)">
																			<option value="Single Connection">Single Connection</option>
																			<form:option value="MS Building">MS Building</form:option>
																			<form:option value="Multiple Connection">Multiple Connection</form:option>
																			<form:option value="Layout">Layout</form:option>
		
																		</form:select>
																	</div>
																</div>
															</div>
													 </div>	

												</div>
												<div class="row">

													<div  onchange="change1();">
														<label class="col-md-2 control-label">Nature&nbsp;of&nbsp;installation&nbsp;<font
															color="red">*</font></label>
														<div class="col-sm-3">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-chevron-down"
																		style="height: 18px; font-size: 13px;"></i></span>

																	<form:select class="form-control" id="natureofinst" path="natureofinst">
																		<option value="" selected="" disabled="">Select Installation</option>
																	</form:select>
																</div>
															</div>
														</div>

													</div>
													
													<div style="display: none;" id="showNoOfconnections">
														<label class="col-md-2 control-label">No.&nbsp;of&nbsp;Connections&nbsp;Req&nbsp;<br>for&nbsp;this&nbsp;Tariff&nbsp;<font
															color="red">*</font></label>
														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"></span>
																	
																	<form:input  type="text" name="noofconnections"
																		placeholder="Enter No of Connections" path="noofconnections"  id="noofconnections"
																		class="form-control"/>
																</div>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row" >
												<label class="col-md-12 control-label">
												
												<b style="color: red;font-size: 18px;">Note </b>-<font color="#0000CD" size="3px;"> To know your Circle,Division,Sub Division and Section please call to <b style="color: #FF4500;">1912</b></font>
												
												</label>
												
												<label class="col-md-12 control-label">
												
												<b style="color: red;font-size: 15px;">Note </b>-<font color="#0000CD" size="2px;">To Add Multiple Tariff's for<b style="color: #FF4500;"> Multiple Connection/MS Building</b></font> <br>1. Submit the Application with Single Tariff by Selecting Application Type as MS Building or Multiple connection<br>
												2.After Submitting the Application Go to Track Application then click on Application Id and Add Multiple Connections with different loads and Tariff according to your requirement.
												
												</label>
												
												</div>

												<div class="row" >
													<div id="div2a"	style="display: none;" >
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															HANDLOOM WEAVING SILK REARING , REELING , ARTISANS USING MOTORS UPTO 200 WATTS
															CONSULTANCY,ENGINEERING,ARCHITECTURE,MEDICINE,ASTROLOGY,LEGAL
															MATTERS,INCOME TAX, CHARTERED ACCOUNTENTS,JOB TYPING,TAILORINT,POST OFFICE,FOLDSMITHY,CHOWKI
															REARING,PAYING FUESTS,HOMESTY GUESTS,PERSONAL COMPUTERS,DHOBIS,HAND PREPARED PRINTING PRESS,BEAUTY PARLOURS,WATER
															SUPPLY ,HOSPITALS ,DISPENSARIES,HEALTH CENTERS RUN BY STATE/CENTRAL GOVT. LOCAL BODIES HOUSES, SCHOOLS AND
															HOSTELS MENT FOR HANDICAPPED AGED DESTITUTE AND ORPHANS REHABITATATION CENTERS RUN BY CHARITABLE INSTITUTIONS,
															AIDS AND DRUG ADDICTS REHABITATION CENTERS,RAILWAY STAF QUARTERS WITH SINGLE METER, FIRE SERVICE
															STATIONS,TEMPLES,MOSQUES,CHURCHES,GURUDWARS,ASHRAMS,MUTTS AND RELIGIOUS/CHARITABLE INSTITUTIONS, HOSPITALS DISPENSARIES
															AND HEALTH CENTERS RUN BY CHARITABLE INSTITTIONS, JAIL AND PRISONS,SCHOOLS COLLEGES,EDUCATIONAL INSTITUTIONS RUN
															BY STATE/ CENTRAL GOVT/LOCAL BODIES, SEMINARIES,HOSTELS RUN BY THE GOVERNMANT,EDUCATIONAL INSTITUTIONS,CULTURAL
															SCIENTIFIC AND CHARITABLE INSTITUTIONS GUEST HOUSE,TRAVELERSBANGALOWS RUN IN GOVERNMENT BULDINGS OR BY
															STATE/CENTRAL GOVT, PUBLIC LIBRARIES,SILK REARING,MUSEUMS,INSTALLATIONS OF HISTOROCAL MENUMENTS OF
															ARCHEOLOGY DEPARTMENTS, PUBLIC RELEPHONE BOOTHS WITHOUT STD/ISD/FAX FACIILIRY RUN BY HANDICAPPED PEOPLE, SULABHA
															/NIRMAL COUCHALAYAS,VISWA SEDS HAVING LIGHTING LOADS ONLY. </label></font>

													</div>


													<div id="div2b" style="display: none; ">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															PRIVATE PROFESSIONAL AND OTHER PRIVATE EDUCATIONAL INSTITUTIONS INCLUDING AIDED OR NON AIADED INSTATUTIONS
															</label>
															</font>

													</div>
													<div id="div3" style="display: none;">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															CLINICS CIAGNOSTIC CENTERS,X RAY UNITS,SHOPS,STORES,HOTELS,RESTAURENTS,BOARDING,LOADGING
															HOMES, BARS,PRIVATE GEST,MESS,CLUBS,CALYAN MANTAPAS/CHOULTRY,PERMANENT CINEMAS,SEMI PERMANENT
															CINEMAS,THEATERS,PETROL BUNKS,PETROL,DISEL AND OIL STORAGE PLANTS ,SERVICE STATIONS,GARAGES,BANKS,TELEPHONE
															EXCHANGES,TV STATIONS, MICROWAVE STATIONS,ALLINDIA RADIO,DISH ANTENNA,PUBLIC TELEPHONE BOOTHS STD,ISD,FAX,
															COMMUNICATION CENTERS,STUD FARMS,RACE COURSE,ICE CREAM PARLOURS,COUMPUTER CENTERS, PHOTO STUDIO,COLOUR
															LABAORATORY,XEROX COPIERS,RAILWAY INSTALLATIONS EXECPTING WAILWAY WORKSHOP, KSRTC BUS STATIONS EXECPTING
															WORKSHOP,ALL OFFICES,POLICE STATIONS,COMMERCIAL COMPLEXEX, LIFTS OF COMMERCILA COMPLEXEX,BATTERY CHARGING
															UNITS, TYRE VULCANZING CENTERS,POST OFFICES,BAKERY SHOPS,BEAUTY PARLOURS,STADIUMS,OTHER THAN THOSE MAINTENED
															BY GOVERNMENT AND LOCAL BODIES, WATER SUPPLY PUMPS AND STREET LIGHTS NOT COVERD UNDER LT6 CYBER CAFES , INTERNET
															SURFING CAFES,CALL CENTERS,IT BASED MEDICAL TRANSACTION CENTERS, PRIVATE HOSTELS NOT COVERED UNDER LT2 (a),PAYING
															GOUESTS ACCOMMODATION PROVIDED IN AN INDEPENDENT/EXCTUSIVE PREMISES. </label>
															</font>
													</div>

													<div id="div4a"	style="display: none;">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															IPSET UP TO AND INCLUSIVE OF 10 HP </label>
															</font>

													</div>

													<div id="div4b"	style="display: none; ">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
																	IP SETS ABOVE 10 HP </label>
															</font>

													</div>

													<div id="div4c1" style="display: none; ">
													
													<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															PRIVATE HORTICULTURE NURSERIES ,COFFEE,TEA,AND RUBBER
															PLANTATIONS OF SANCTION LOAD UP TO AND INCLUSIVE OF 10 HP </label>
															</font>

													</div>

													<div id="div4c2" style="display: none; ">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															PRIVATE HORTICULTURAL NURSERIES, COFFEE,TEA AND RUBBER
															PLANTATION OF SANCTION LOAD ABOVE 10 HP </label>
															</font>

													</div>


													<div id="div5"	style="display: none; ">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															HEATING AND MOVTIVE POWER (INCLUDING LIGHTING) INDUSTRIAL UNITS, WORKSHOPS, POULTRY FARMS, SUGARCANE CRUSHERS,
															COFFEE PULPING, CARDAMON DRYING, MASHROOM RAISING INSTALLATIONS, FLOUR, HULLER, RICE MILLS, WET GRINDERS,
															MILK DAIRIES, IRONING, DRY CLEANERS, LANDRIES HAVING WASHING, DRYING, IRONING, EXCLUSIVE TAILORING SHOP, BULK
															ICE CREAM AND ICE MANUFACTURING UNITS, COFFEE ROASTING,AND GARINDING WORKS, COLD STROAGE PLANTS, BAKERY PROCUCTS
															MANUFACTURING, KSRTC WORKSHOPS/DEPOTS, RALWAY WORKSHOPS,DRUG MANUFACTURING UNITS AND TESTING LABORATORIES,
															PRINTING PRESSES, GARMENT MANUFACTURING UNITS, BULK VENDING BOOTHS, SWIMMING POOLS OF LOCAL BODIES, TYRE
															RETREADING UNITS, STONE CRUSHERS, STONE CUTTING , CHILLY GRINDERS, PHOVA MILLS, PULVERIZING MILLS, DECORTICATORS,
															IRON AND RED OXIDE CRUSHING UNITS, CREMATORIUMS, HATCHERIES, RISSUE CULTURE, SAW MILLS, TOY WOOD
															INDUSTRIES, VISWA SHEDS WITH MIXED LOD SANCTIONED UNDER VISWA SCHEME, CINEMATIC ACTIVITIES SUCH AS PROCESSING
															PRINTING DEVELOPING,RECORDING THEATRES DUBBING THEATRES AND FILM STUDIOS, AGARBATHI MANUFACTURING UNITS, WATER
															SUPPLY INSTALLATIONS OF KIADB AND INDUSTRIAL UNITS, GEM AND DIAMOND CUTTING UNITS, FLORICULTURE, GREEN HOUSE,
															BIOTECH LABS, HYBRID SEED PROCESSING UNITS, INFORMATION TECHONOLOGY INDUSTRIES ENGAGED IN DEVELOPMENT OF HARD
															WARE AND SOFTWARE, INFORMATION TECHONOLOGY (IT)ENABLED SERVICES START-UPS/ANIMATIONS/GAMING/COMPUTER GRAPHICS AS
															CIRTIFICATED BY IT AND BT DEPARTMANT OF GOK/FOI, SILLK FILATURE UNITS, AQUA CULTURE, PRAWN CULTURE, BRICK
															MANUFACTURING UNITS, SILK COTTON COLOURING DYAING,STADIUMS MAINTEANED BY GOVT AND LOCAL BODIES, FIRE
															SERVICE STATIONS, GOLD/SILVER ARNAMENT MANUFACTURING UNITS, EFFIUENT TREATMENT PLANTS, DRAINAGE WATER
															TREATMENT PLANTS, LPG BOTTING PLANTS , PETROLEUM PIPELINE PROJECTS, PIGGERY FARMS, ANALYTICAL LAB FOR ANALYSIS OF
															ORE METALS, SATELLITE COMUNICATION CENTERS, MINERAL WATER PROCESSIN PLANTS, DRINKING WATER BOTTLING PLANTS , SODA
															FOUNTATION UNITS.  </label>
															</font>

													</div>

													<div id="div6a"	style="display: none; ">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															WATER PURIFYING PLANTS MAINTAINED BY GOVT, VILLAGE
															PANCHAYAT, TOWN PANCHAYAT, TOWN MUNICIPALITIES, CITY
															MUNICIPALITIES, CORPORATIONS, STATE AND CENTRAL GOVT,
															APMC.  </label>
															</font>

													</div>


													<div id="div6b"	style="display: none; ">
													
													<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															TARIFF SIGNALS, SUBWAYS, WATER FOUNTAINS OF LOCAL BODIES,STREET LIGHTS OF RESIDENTIAL CAMPUS OF UNIVERSITIES,
															OTHER EDUCATIONAL INSTITUTIONS, HOUSING COLONIES APPROVED BY LOCALBODIES/DEVELOPMENT AUTHORITY, RELIGIOUS
															INSTITUTIONS, ORGANIZATIONS RUN ON CHARITABLE BASISIS,INDUSTRIAL AREA/ESTATE AND NOTIFIED AREAS, WATER SUPPLY
															INSTALLATIONS IN RESIDENTIAL LAYOUTS, STREET LIGHTS ALONG WITH SIGNAL LIGHTS ASSOCIATED LOAD OF GATEMEN, HUT
															PROVIDED AT RAILWAY LEVEL CROSSING, </label>
															</font>

													</div>

													<div id="div7" style="display: none;">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															APPLICABLE TO TEMPORARY POWER SUPPLY FOR ALL PURPOSES </label>
															</font>

													</div>


													<div id="divht1" style="display: none;">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															SEWERAGE WATER TREATNEBT PLANT, SEWERAGE PUMPING
															INSTALLATIONS, KARNATAKA URBAN WATER SUPPLY AND SEWERAGE
															BOARD OTHER LOCAL BODIES STATE OR CENTRAL GOVT. </label>
															</font>

													</div>


													<div id="divht2a" style="display: none;">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															INDUSTRIES, FACTORIES, WORKSHOPS, RESEARCH AND DEVELOPMENT CENTRES, INDUSTRIAL ESTATES, MILK DAIRIES,
															RICE MILLS, PHOVA MILLS, ROLLER FLOUR MILLS, NEWS PAPERS,PRINTING PRESS, RAILWAY WORKSHOPS , KSRTC WORK SHOPS,
															DEPOTS CREMATORIUMS, COLD STORAGES, ICE AND ICE CREAM MFG UNITS, SWIMMING POOLS OF LOCAL BODIES, WATER SUPPLY
															INSTALLATIONS OF KIADB AND OTHER INDUSTRIES, ALL DEFENCE ESTABLISHMENTS, HATCHERIES, POULTRY FARM, MUSEUM,
															FLORICULTURE, GREEN HOUSE, BIO TECHINICAL LABORATORY,HYBRID SEED PROCESSING UNITS, STONE CRUSHERS, STONE
															CUTTING , BAKERY PRODUCT MANUFACTURING UNITS, MYSORE PALACE ILLUMINATION, FILM STUDIOS, DUBBING
															THEATERS,PROCESSING,PRINTING PRESS,DEVELOPING AND RECORDING, THEATERS, TISSUE CULTURE, AQUA CULTURE, PRAWN
															CULTURE, INFORMATION TECHONOLOGY, INDUSTRIES ENGAGED IN DEVELOPMENT OF HARDWARE AND SOFTWARE INFORMATION
															TECHONOLOGY(IT) ENABLED SERVICES, START UP ANIMATION,GAMING, COMPUTER GARMENT MFG UNITS, TYRE RETERADING
															UNITS, NUCLEAR POWER PORHECTS, STADIUMS MAINTAINED BY GOVERNMENT AND LOCA BODIES, RAILWAY TRACTION, EFFLUENT
															TREATMENT PLANTS AND DRAINAGE WATER TREATMENT PLANT OWNED OTHER THAN BY LOCLA BODIES, LPG BOTTING PLANTS, PETROLEUM
															PIPELINE PROHETS, PIGGERY FARMS, ANALYTICAL LAB FOR ANALYSIS OF RE METALS, SAW MILLS, TOY/WOOD INDUSTRIES,
															SATELLITE COMMUNICATION CENTERS, MINERAL WATER PROCESSING PLANTS, DRINKING WATER BOTTILING PLANTS. </label>
															</font>

													</div>


													<div id="divht2b" style="display: none;">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															COMMERCILAL COMPLEXES, CINEMAS, HOTELS, BOARDING AND LODGING, AMUSEMENT PARKS, TELEPHONE EXCHANGES, RACE
															COURSE, ALL CLUBS, TV STATIONS, ALL INDIA RADIO, RAILWAY STATIONS, AIR PORT, KSRTC BUS STATIONS, ALL OFFICES ,
															BANKS, COMMERCILA MULTI-STORIED BULDINGS, APMC YARDS,STADIUMS, OTHER THAN THOSE MAINTAINED BY GOVERNMENT AND
															LOCAL BODIES, CONSTRUCTION POWER FOR IRRIGATION, POWER PROJECT AND KANKAN RAILWAY PROJECT, PETROL/DISEL AND OIL
															STROGE PLANTS, IT BASED MEDICAL TRANSCRIPTION CENTERS,TELECOM, CALL CENTERS, BPO/KPO. </label>
															</font>

													</div>



													<div id="divht3a1" style="display: none; ">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															APPLICABLE TO LI SCHEMES UNDER GOVERNMENT DEPARTMENT/GOVT. OWNED CORPORATIONS </label>
															</font>

													</div>



													<div id="divht3a2" style="display: none; ">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															APPLICABLE TO PRIVATE LI SCHEMES AND LIFT IRRIGATION SOCIETIES CONNECTED TO URBAN EXPRESS FEEDERS </label>
															</font>

													</div>

													<div id="divht3a3"	style="display: none;">
													
													<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															APPLICABLE TO PRIVATE LI SCHEMES AND LIFT IRRIGATION SOCIETIES OTHER THAN THOSE COVERD UNDER HT3(a)(ii) </label>
															</font>

													</div>

													<div id="divht3b"	style="display: none; ">
													
													<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															APPLICABLE TO IRRIGATION AND AGRICULTURAL FARMS ,GOVERNMENT HORTICULTURAL FARMS, PRIVATE HORTICULTURE NURSERIES, COFFEE, TEA, RUBBER, COCONUT AND ARECANUT
															PLANTATIONS. </label>
															</font>

													</div>


													<div id="divht4ab"		style="display: none;">
													
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															RESIDENTIAL APARTMENTS AND COLONIES </label>
															</font>

													</div>

													<div id="divht5"	style="display: none; ">
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															APPLICABLE TO SANCTION LOAD OF 67 HP AND ABOVE HOARDING AND ADVERTISEMENT BOARDS </label>
															</font>

													</div>
													
													<div id="divltbj"
														style="display: none; ">
														<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															ALL BPL HOUSEHOLDS ARE COVERED UNDER THIS LT1 CATEGORY OF GOVERNMENT SPONSERSED SCHEME BHAGYAJYOTHI AND KUTIRAJYOTHI </label>
															</font>

													</div>


												</div>
												
											</div>
											<!--END OF TAB1  -->
											<div class="tab-pane" id="tab2">

												<br>
												<h6>
													<strong>Step 2 </strong> - General Particulars

													<div id="useProfile1">
														<label class="checkbox"
															style="margin-left: 260px; color: green; margin-top: -18px;">
															<input type="checkbox" name="userProfile"
															id="userProfile" value="1"
															onchange="checkBoxLabelForProfile();" /><i></i>Use My
															Profile Name,Mobile No and E-Mail  </label>
															
													</div>

												</h6>

												<br>

												<div class="row">
													<label class="col-md-2 control-label">Applicant&nbsp;Type&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>


																<form:select class="form-control" id="applicanttype"
																	path="applicanttype">
																	<option value="0" selected="" disabled="">Select</option>
																	<form:option value="1">Individual</form:option>
																	<form:option value="2">Organisation</form:option>


																</form:select>
															</div>
														</div>
													</div>



													<div id="basic1">
														<label class="col-md-2 control-label">Name&nbsp;<font
															color="red">*</font></label>
														<div class="col-sm-4">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-user"
																		style="height: 18px; font-size: 13px;"></i></span>
																	<form:input type="text" name="name" placeholder="Name"
																		path="name" id="personName" class="form-control"
																		style="height: 37px; font-size: 13px;"
																		data-original-title="Person Name" rel="tooltip"
																		onkeyup="convertToUpperCase();"></form:input>

																</div>
															</div>
														</div>
													</div>

													<div id="basic3">
														<label class="col-md-2 control-label">Name&nbsp;Of&nbsp;Organization&nbsp;<font
															color="red">*</font></label>
														<div class="col-sm-4">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-briefcase"
																		style="height: 18px; font-size: 13px;"></i></span>

																	<form:input type="text" name="nameoforg"
																		placeholder="Organization Name" path="nameoforg"
																		id="orgName" class="form-control"
																		style="height: 37px; font-size: 13px;"
																		data-original-title="Organization Name" rel="tooltip"
																		onkeyup="convertToUpperCase();"></form:input>

																</div>
															</div>
														</div>
													</div>




												</div>






												<div class="row" id="basic2">

													<label class="col-md-2 control-label">Father/Husband&nbsp;Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-user"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="fhname"
																	placeholder="Father Name" path="fhname" id="fatherName"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Father Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Nominee&nbsp;Name&nbsp;</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-user"></i></span>

																<form:input type="text" name="nomineename"
																	placeholder="Nominee(Optinal)" path="nomineename"
																	id="nomineName" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Nominee Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

												</div>



												<div class="row" id="basic4">

													<label class="col-md-2 control-label">Authorized&nbsp;Signatory&nbsp;Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-user"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nameauthsignatory"
																	placeholder="Name of Auth Sig" path="nameauthsignatory"
																	id="authSigName" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Authorized Signatory Name"
																	rel="tooltip" onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Designation&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-level-up"
																	style="height: 18px; font-size: 13px;"
																	data-original-title="Designation" rel="tooltip"></i></span>


																<form:input type="text" name="desigauthsignatory"
																	placeholder="Auth Designation"
																	path="desigauthsignatory" id="authDesName"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Designation" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

												</div>



												<h6>Address Where Power Supply is required</h6>

												<br>


												<div class="row">
													<label class="col-md-1 control-label">House&nbsp;No&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prependfa fa-building"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="hnopres"
																	placeholder="House/Flat/Shop No" path="hnopres" id="hnopres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="House/Flat/Shop No" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Floor&nbsp;No&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-building"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="floorpres"
																	placeholder="Floor No" path="floorpres" id="floorpres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Floor No" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Street<br>Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-road"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="streetpres"
																	placeholder="Street Name" path="streetpres"
																	id="streetpres" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Street Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>


												</div>


												<div class="row">
													<label class="col-md-1 control-label">Area&nbsp;Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="areapres"
																	placeholder="Area/Location" path="areapres"
																	id="areapres" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Area Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Cross&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="crosspres"
																	placeholder="Cross" path="crosspres" id="crosspres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Cross" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Main&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="mainpres"
																	placeholder="Main" path="mainpres" id="mainpres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Main" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>


												</div>





												<div class="row">
													<label class="col-md-1 control-label">City&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="citypres"
																	placeholder="City" path="citypres" id="citypres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="City" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Pincode&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="pinpres" placeholder="Pin"
																	path="pinpres" id="pinpres" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="PinCode" rel="tooltip"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Phone</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-phone"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="phonepres"
																	placeholder="Phone No" path="phonepres" id="phonepres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Phone Number" rel="tooltip"></form:input>
															</div>
														</div>
													</div>


												</div>



												<div class="row">
													<label class="col-md-1 control-label">Mobile&nbsp;No&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-mobile"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="mobilenopres"
																	placeholder="Mobile No" path="mobilenopres"
																	id="mobilenopres" data-mask="9999999999"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Mobile Number" rel="tooltip"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Landmark&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="landmarkpres"
																	placeholder="Land mark" path="landmarkpres"
																	id="landmarkpres" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="LandMark" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">E-Mail</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-envelope-o"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="email" name="emailpres"
																	placeholder="E-Mail" path="emailpres" id="emailpres"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="E-Mail" rel="tooltip"></form:input>
															</div>
														</div>
													</div>


												</div>







											</div>
											<!--END OF TAB 2  -->

											<div class="tab-pane" id="tab3">

												<br>
												<h6>
													<strong>Step 3 </strong> - Permanent Address <label
														class="checkbox"
														style="margin-left: 260px; color: green; margin-top: -18px;"><form:checkbox
															name="addresssameas" id="addresssameas"
															path="addresssameas" value="1"
															onchange="checkBoxLabel();"></form:checkbox><i></i>Same
														as the Address Where Power Supply is required </label>

												</h6>


												<br> <br>



												<div class="row">
													<label class="col-md-1 control-label">House&nbsp;No&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-building"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="hnoperm"
																	placeholder="House/Flat/Shop No" path="hnoperm" id="hnoperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="House/Flat/Shop No" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Floor&nbsp;No&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-building"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="floorperm"
																	placeholder="Floor No" path="floorperm" id="floorperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Floor No" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Street<br>Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-road"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="streetperm"
																	placeholder="Street Name" path="streetperm"
																	id="streetperm" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Street Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>


												</div>


												<div class="row">
													<label class="col-md-1 control-label">Area&nbsp;Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="areaperm"
																	placeholder="Area/Location" path="areaperm"
																	id="areaperm" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Area Name" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Cross&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="crossperm"
																	placeholder="Cross" path="crossperm" id="crossperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Cross" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Main&nbsp;</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="mainperm"
																	placeholder="Main" path="mainperm" id="mainperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Main" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>


												</div>





												<div class="row">
													<label class="col-md-1 control-label">City&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="cityperm"
																	placeholder="City" path="cityperm" id="cityperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="City" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Pincode&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="pinperm" placeholder="Pin"
																	path="pinperm" id="pinperm" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="PinCode" rel="tooltip"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Phone</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-phone"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="phoneperm"
																	placeholder="Phone No" path="phoneperm" id="phoneperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Phone Number" rel="tooltip"></form:input>
															</div>
														</div>
													</div>


												</div>



												<div class="row">
													<label class="col-md-1 control-label">Mobile&nbsp;No&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-mobile"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="mobilenoperm"
																	placeholder="Mobile No" path="mobilenoperm"
																	id="mobilenoperm" data-mask="9999999999"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Mobile Number" rel="tooltip"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Landmark&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-map-marker"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="landmarkperm"
																	placeholder="Land mark" path="landmarkperm"
																	id="landmarkperm" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="LandMark" rel="tooltip"
																	onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">E-Mail</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-prepend fa fa-envelope-o"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="email" name="emailperm"
																	placeholder="E-Mail" path="emailperm" id="emailperm"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="E-Mail" rel="tooltip"></form:input>
															</div>
														</div>
													</div>
												</div>


											</div>


											<div class="tab-pane" id="tab4">



												<br>
												<h6>
													<strong>Step 4 </strong> - Connection & Account Details
												</h6>

												<br>


												<div class="row">
													
											      <div id="dimensionsShowforLayout" style="display: none;">
													
													 <label class="col-md-2 control-label">Dimensions&nbsp;(Sqft)&nbsp;<font
														color="red">*</font></label>	
															<div class="col-sm-2">
																<div class="form-group">
																	<div class="input-group">
																		<span class="input-group-addon"><i
																			class="fa fa-chevron-down"
																			style="height: 18px; font-size: 13px;"></i></span>
		
																		<select class="form-control" id="dimensionsdrop" name="dimensionsdrop" onchange="onChangeLayout(this.value)">
																			<option value="">Select</option>
																			<option value="20*30">20*30(600)</option>
																			<option value="30*40">30*40(1200)</option>
																			<option value="60*40">60*40(2400)</option>
																			<option value="50*80">50*80(4000)</option>
																			<option value="Other">Other</option>
		
																		</select>
		
		
		
																	</div>
																</div>
														</div>
													</div>
													
												   <div id="oddDimensionsEntry" style="display: none;">
													<label class="col-md-1 control-label">New Dimension<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																
																<form:input type="text" name="dimensions"
																	placeholder="Total Dim.(Sqft)" path="dimensions"
																	id="dimensions" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Total Dimension(Sqft)" rel="tooltip" onchange="onChangeNewDimension(this.value)"></form:input>

															</div>
														</div>
													</div>
													</div>
													
													<div id="noOfSitesEntry" style="display: none;">
													<label class="col-md-1 control-label">No.&nbsp;Of&nbsp;Sites<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																
																<form:input type="text" name="noOfSites"
																	placeholder="No. of Sites" path="noOfSites"
																	id="noOfSites" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="No Of Sites" rel="tooltip" onchange="onChangeNoOfSites(this.value)"></form:input>

															</div>
														</div>
													</div>
													</div>
													
													
													<div id="KWANoEntry">
													<label class="col-md-1 control-label">Load(KW)</label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-ban"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="loadkw"
																	placeholder="Desired Load(kW)" path="loadkw"
																	id="loadkw" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Desired Load(kW)" rel="tooltip" onchange="myFunction1(this.value)"></form:input>

															</div>
														</div>
													</div>
													</div>
													
													
													
													
													<div id="hpNoEntry">
													<label class="col-md-2 control-label">Load (HP)&nbsp;</label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-ban"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="loadhp"
																	placeholder="Desired Load(HP)" path="loadhp"
																	id="loadhp" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Desired Load(HP)" rel="tooltip" onchange="myFunction2(this.value)"></form:input>

															</div>
														</div>
													</div>
													</div>

													<div id="kvaNoEntry">
														<label class="col-md-2 control-label">Load (KVA)&nbsp;<font color="red">*</font></label>
														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-ban"
																		style="height: 18px; font-size: 13px;"></i></span>
																	<form:input type="text" name="loadkva"
																		placeholder="Desired Load(KVA)" path="loadkva"
																		id="loadkva" class="form-control"
																		style="height: 37px; font-size: 13px;"
																		data-original-title="Desired Load(KVA)" rel="tooltip" onchange="myFunction3(this.value)"></form:input>
																</div>
															</div>
														</div>
													
													</div>


												</div>





												<div class="row">
													
													<%-- <label class="col-md-2 control-label">Purpose&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="purpose"
																	path="purpose">
																	<option value="0" selected="" disabled="">Select</option>
																	<form:option value="1">Lighting</form:option>
																	<form:option value="2">Heating</form:option>
																	<form:option value="3">Lighting & Heating</form:option>

																</form:select>

															</div>
														</div>
													</div> --%>

													<label class="col-md-2 control-label">Phase&nbsp;Requirement&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="reqphase"
																	path="reqphase">
																	<option value="0" selected disabled>Select</option>
																	<form:option value="1">Single Phase</form:option>
																	<form:option value="3">Three Phase</form:option>

																</form:select>

															</div>
														</div>
													</div>

												
													 
													 <label class="col-md-2 control-label">Purpose&nbsp;of&nbsp;Power</label>
														<div class="col-sm-4">
															<div class="form-group">
																<div class="input-group">
																<span class="input-group-addon"><i
																		class="fa fa-power-off"
																		style="height: 18px; font-size: 13px;"></i></span>
																	<form:input type="text" name="supplyfor" path="supplyfor"
																		id="supplyfor" class="form-control"></form:input>
																	
																</div>
															</div>
														</div>
												  </div>
												  
												<div class="row">
														
													<label class="col-md-2 control-label">If&nbsp;Govt.&nbsp;/&nbsp;Private&nbsp;</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="govtpvt"
																	path="govtpvt">
																	<option value="0" selected="">Select</option>
																	<form:option value="1">Central Govt.</form:option>
																	<form:option value="2">State Govt.</form:option>
																	<form:option value="3">Private</form:option>


																</form:select>

															</div>
														</div>
													</div>
													
													<div id="plinthAreaNoEntry">
														<label class="col-md-2 control-label">Plinth Area(Sq.m)&nbsp;<font color="red">*</font></label>
														<div class="col-sm-4">
															<div class="form-group">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-ban"
																		style="height: 18px; font-size: 13px;"></i></span>
																	
																	<form:input type="text" name="plintharea"
																		placeholder="Plinth Area(Sqft)" path="plintharea"
																		id="plintharea" class="form-control"
																		style="height: 37px; font-size: 13px;"
																		data-original-title="Plinth Area(Sqft)" rel="tooltip"></form:input>
																</div>
															</div>
														</div>
													
													</div>
	
											</div>



												<div class="row">
													

													
													
													<div id="strprklgt" style="display: none;">
													<label class="col-md-2 control-label">If&nbsp;Public&nbsp;Street/Park&nbsp;Light&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control"
																	id="publicstreetorparklight"
																	path="publicstreetorparklight">
																	<option value="0" selected="" disabled="">Select</option>
																	<form:option value="1">Self</form:option>
																	<form:option value="2">Rental</form:option>
																	<form:option value="3">Public Use</form:option>
																</form:select>
															</div>
														</div>
													</div>
													</div>
													
												
												</div>
												
												<div class="row">
													<label class="col-md-12 control-label"><b>Note
															-</b> At least one Load should be mandatory(KW/HP/KVA). For LT Applications Max value is less 
															than 5000KVA</label>

												</div>

											</div>




											<div class="tab-pane" id="tab5">

												<br>
												<h6>
													<strong>Step 5 </strong> - Additional Details
												</h6>
												<br>

												<div class="row">
												 
												 <div id="specialGovSchemeId" style="display: none;">
													<label class="col-md-2 control-label">If
														Special&nbsp;Govt&nbsp;Scheme&nbsp;
													</label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="splgovtscheme"
																	path="splgovtscheme">
																	<option value="" selected="">Select</option>
																	<form:option value="BJ">BJ</form:option>
																	<form:option value="KJ">KJ</form:option>
																	<form:option value="RGGVY">RGGVY</form:option>
																	<form:option value="GANGA KALYAN">GANGA KALYAN</form:option>
																	<form:option value="NA">NA</form:option>
																</form:select>

															</div>
														</div>
													</div>
												</div>
												
												
													<label class="col-md-2 control-label">Type&nbsp;of&nbsp;Ownership&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="ownership"
																	path="ownership">
																	<option value="0" selected="" disabled="">Select</option>
																	<form:option value="Individual">Individual</form:option>
																	<form:option value="Leased/Rental">Leased/Rental</form:option>
																	<form:option value="Co owned">Co owned</form:option>
																	<form:option value="Proprietorship">Proprietorship</form:option>
																	<form:option value="Public company">Public company</form:option>
																	<form:option value="Private Company">Private Company</form:option>
																	<form:option value="PartnerShip">PartnerShip</form:option>
																	<form:option value="Trust">Trust</form:option>
																</form:select>

															</div>
														</div>
													</div>
												
												<div id="ipsetWtr" style="display: none;">
													<label class="col-md-2 control-label"> If
														IP&nbsp;Set,Water&nbsp;Source&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-chevron-down"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:select class="form-control" id="ipset"
																	path="ipset">
																	<option value="" selected="" disabled="">Select</option>
																	<form:option value="1">Open well</form:option>
																	<form:option value="2">Bore well</form:option>
																	<form:option value="3">River</form:option>
																	<form:option value="4">Channel</form:option>
																	<form:option value="5">Sub-Channel</form:option>
																	<form:option value="6">Nallah</form:option>
																</form:select>
															</div>
														</div>
													</div>
											  </div>


												</div>







												<div class="row">

													<label class="col-md-2 control-label">RR&nbsp;Number&nbsp;1&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-tachometer"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyrrnoone"
																	placeholder="RR Number" path="nearbyrrnoone"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Account&nbsp;Number&nbsp;1</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-pinterest"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyacnoone"
																	placeholder="Account No" path="nearbyacnoone"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

												</div>


												<div class="row">

													<label class="col-md-2 control-label">RR&nbsp;Number&nbsp;2</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-tachometer"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyrrnotwo"
																	placeholder="RR Number" path="nearbyrrnotwo"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Account&nbsp;Number&nbsp;2</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-pinterest"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyacnotwo"
																	placeholder="Account No" path="nearbyacnotwo"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>




													<br>

												</div>


												<div class="row">

													<label class="col-md-2 control-label">RR&nbsp;Number&nbsp;3</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-tachometer"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyrrnothree"
																	placeholder="RR Number" path="nearbyrrnothree"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>

															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Account&nbsp;Number&nbsp;3</label>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-pinterest"
																	style="height: 18px; font-size: 13px;"></i></span>
																<form:input type="text" name="nearbyacnothree"
																	placeholder="Account No" path="nearbyacnothree"
																	class="form-control" onkeyup="convertToUpperCase();"></form:input>
															</div>
														</div>
													</div>

												</div>

												<div class="row">
													<label class="col-md-12 control-label"><b>Note
															-</b> RR Number and Account Number : Mention the nearest
														Connection Details.</label>

												</div>

											</div>



											<div class="tab-pane" id="tab6">

												<br>
												<h6>
													<strong>Step 6 </strong> - Particulars of LEC (Licensed Electrical Contractor)
												</h6>
												<br>


												<div class="row">
													<label class="col-md-2 control-label">Contractor&nbsp;Name&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-user"
																	style="height: 18px; font-size: 13px;"></i></span> <input
																	type="text" name="lecName"
																	placeholder="Contractor Name" id="lecName"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Name of Licensed Contractor"
																	rel="tooltip" onkeyup="convertToUpperCase();">

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Address</label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">

																<textarea rows="1" name="lecAddress"
																	placeholder="Address Info" id="lecAddress"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Address Of LEC" rel="tooltip"
																	onkeyup="convertToUpperCase();"></textarea>

															</div>
														</div>
													</div>

													<label class="col-md-1 control-label">Class&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-2">
														<div class="form-group">
															<div class="input-group">

																<input type="text" name="lecClass" placeholder="Class"
																	id="lecClass" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Class" rel="tooltip"
																	onkeyup="convertToUpperCase();">

															</div>
														</div>
													</div>




												</div>


												<div class="row">



													<label class="col-md-2 control-label">License&nbsp;Number&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-key"
																	style="height: 18px; font-size: 13px;"></i></span> <input
																	type="text" name="licenseNo"
																	placeholder="License Number" id="licenseNo"
																	class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="License Number" rel="tooltip"
																	onkeyup="convertToUpperCase();">

															</div>
														</div>
													</div>


													<label class="col-md-2 control-label">License&nbsp;Validity&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-append fa fa-calendar"
																	style="height: 18px; font-size: 13px;"></i></span> <input
																	type="text" name="validity1" id="validity1"
																	placeholder="Validity" class="form-control">
															</div>
														</div>
													</div>




												</div>



												<div class="row">



													<label class="col-md-2 control-label">Supervisor&nbsp;Permit&nbsp;No&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="fa fa-key"
																	style="height: 18px; font-size: 13px;"></i></span> <input
																	type="text" name="supervisorPermitNo"
																	placeholder="Supervisor Permit No"
																	id="supervisorPermitNo" class="form-control"
																	style="height: 37px; font-size: 13px;"
																	data-original-title="Supervisor Permit Number"
																	rel="tooltip" onkeyup="convertToUpperCase();">

															</div>
														</div>
													</div>


													<label class="col-md-2 control-label">Permit&nbsp;No&nbsp;Validity&nbsp;<font
														color="red">*</font></label>
													<div class="col-sm-3">
														<div class="form-group">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="icon-append fa fa-calendar"
																	style="height: 18px; font-size: 13px;"></i></span> <input
																	type="text" name="validity2" id="validity2"
																	placeholder="Validity" class="form-control">
															</div>
														</div>
													</div>
												</div>
												
												
												<div class="row">
												
												<label class="col-md-2 control-label">Upload&nbsp;LEC&nbsp;Document&nbsp;<font color="red">*</font></label>
												 <div class="col-sm-3">
												  
												 <span class="button"><input type="file" id="lecDocumentId"
													name="lecfile" onchange="this.parentNode.nextSibling.value = this.value"></span>
											     </div>
												
												</div>




											</div>


											

											


											<div class="tab-pane" id="tab7">

												<br>
												<h6>
													<strong>Step 7</strong> - Required Documents &nbsp;
												</h6>
												<br>

												<div class="row">
													<div class="col-sm-2"></div>

													<label class="col-md-2 control-label">Consumer&nbsp;Photo<font
														color="red">*</font></label>
														
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">

																<div id="imagecontainer">
																	<img id="blah" src="./resources/img/personImage.jpg"
																		alt="your image" />
																</div>

																<input type='file' id="imgInp" name="profileImage" />

															</div>
														</div>
													</div>

													<label class="col-md-2 control-label">Scanned&nbsp;Signature&nbsp;of&nbsp;Consumer&nbsp;<font
														color="red">*</font></label>


													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">

																<div id="imagecontainer1">
																	<img id="scanImage1"
																		src="./resources/img/blankRec.jpg"
																		alt="Scanned Signature" />
																</div>
																<input type='file' id="scannedImage" name="scannedImage" />

															</div>
														</div>
													</div>

												</div>


												<div class="row">

													<div style="margin-left: -54px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox1" id="checkbox1"
																style="margin-left: 55px;" disabled="disabled"
																checked="checked">
														</div>

														<label class="col-md-3 control-label">Proof&nbsp;of&nbsp;OwnerShip&nbsp;<font
															color="red">*</font></label>




														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp1" name="inputfile1">

																</div>
															</div>
														</div>
													</div>
													<div style="margin-left: 90px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox2" id="checkbox2"
																style="margin-left: 55px;" checked="checked" disabled="disabled">
														</div>

														
														
														
														
														<label class="col-md-3 control-label">Resident&nbsp;Address&nbsp;Proof&nbsp;<font
															color="red">*</font></label>
														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp8" name="inputfile8">

																</div>
															</div>
														</div>
														
														
													</div>

												</div>




												<div class="row">
													<div style="margin-left: -54px;display: none;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox3" id="checkbox3"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Wiring&nbsp;Diagram</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp3" name="inputfile3">

																</div>
															</div>
														</div>

													</div>
													<div style="margin-left: -54px;"><!-- <div style="margin-left: 90px;"> -->
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox4" id="checkbox4"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">License&nbsp;from&nbsp;local&nbsp;authority</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp4" name="inputfile4">

																</div>
															</div>
														</div>
													</div>
													<div style="margin-left: 90px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox8" id="checkbox8"
																style="margin-left: 55px;" 
																>
														</div>

														
														
														
														<label class="col-md-3 control-label">Building&nbsp;Plan</label>
														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp2" name="inputfile2">

																</div>
															</div>
														</div>
														
														
													</div>
												</div>



												<div class="row">

													<div style="margin-left: -54px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox5" id="checkbox5"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Registered&nbsp;partnership&nbsp;deed</label>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp5" name="inputfile5">

																</div>
															</div>
														</div>
													</div>
													<div style="margin-left: 90px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox6" id="checkbox6"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Articles&nbsp;of&nbsp;association&nbsp;and&nbsp;Certificate</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp6" name="inputfile6">

																</div>
															</div>
														</div>
													</div>
												</div>


												<div class="row">

													<div style="margin-left: -54px;display: none;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox7" id="checkbox7"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Indemnity&nbsp;Bond</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp7" name="inputfile7">

																</div>
															</div>
														</div>
													</div>
													
												</div>


												<div class="row">

													<div style="margin-left: -54px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox9" id="checkbox9"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">An&nbsp;undertaking&nbsp;not&nbsp;to&nbsp;engage&nbsp;Child&nbsp;Labour</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp9" name="inputfile9">

																</div>
															</div>
														</div>
													</div>
													<div style="margin-left: 90px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox10" id="checkbox10"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Location&nbsp;sketch</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp10" name="inputfile10">

																</div>
															</div>
														</div>
													</div>
												</div>



												<div class="row" style="display: none;">

													<div style="margin-left: -54px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox11" id="checkbox11"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Agreement&nbsp;to&nbsp;install&nbsp;Water&nbsp;heater</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp11" name="inputfile11">

																</div>
															</div>
														</div>
													</div>
													<div style="margin-left: 90px;">
														<div class="col-sm-1">
															<input type="checkbox" name="checkbox12" id="checkbox12"
																style="margin-left: 55px;">
														</div>

														<label class="col-md-3 control-label">Power&nbsp;supply&nbsp;agreement</label>


														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="imgInp12" name="inputfile12">

																</div>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
												<div style="margin-left: -54px;">
														<div class="col-sm-1"></div></div>
												<label class="col-md-3 control-label">Other Document&nbsp;</label>
													<div class="col-sm-2" style="margin-left: 14px;">
														<div class="form-group">
															<div class="input-group">
																
																<input type="text" name="otherDoc"
																	placeholder="Document Name"  id="otherDoc"
																	class="form-control"
																	style="height: 28px; font-size: 13px;"
																	data-original-title="Document Name" rel="tooltip">
															</div>
														</div>
												   </div>
												 <div class="col-sm-1"></div>
												 <label class="col-md-2 control-label">Upload&nbsp;Document&nbsp;</label>
												 <div class="input input-file">
												  <div class="col-sm-1"></div>
												 <span class="button"><input type="file" id="otherDocumentId"
													name="otherDocfile" onchange="this.parentNode.nextSibling.value = this.value"></span>
											     </div>
												
												
												</div>


											</div>


											

											<div class="tab-pane" id="tab9">
												<br>
												<h6>
													<strong>Step 8</strong> - Save
												</h6>
												<br>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Nature Of
																	Installation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
																	class="onoffswitch"> <input type="checkbox"
																		name="onoffswitch" class="onoffswitch-checkbox"
																		id="show-tabs1" readonly="readonly"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="input-group">
															<div class="input-group">
																<span class="saveTab">General Particulars
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
																	class="onoffswitch"> <input type="checkbox"
																		name="onoffswitch" class="onoffswitch-checkbox"
																		readonly="readonly" id="show-tabs2"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Permanent
																	Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
																	class="onoffswitch"> <input type="checkbox"
																		name="onoffswitch" class="onoffswitch-checkbox"
																		id="show-tabs3" readonly="readonly"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Connection & Account
																	Details &nbsp;&nbsp;&nbsp; <span class="onoffswitch">
																		<input type="checkbox" name="onoffswitch"
																		class="onoffswitch-checkbox" readonly="readonly"
																		id="show-tabs4"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>


													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Additional Details
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<span class="onoffswitch"> <input
																		type="checkbox" name="onoffswitch"
																		class="onoffswitch-checkbox" readonly="readonly"
																		id="show-tabs5"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>


													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Particulars of LEC
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
																	class="onoffswitch"> <input type="checkbox"
																		name="onoffswitch" class="onoffswitch-checkbox"
																		readonly="readonly" id="show-tabs6"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>




												</div>

												<div class="row">

												

													<div class="col-sm-4">
														<div class="form-group">
															<div class="input-group">
																<span class="saveTab">Required Documents
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="onoffswitch"> <input
																		type="checkbox" name="onoffswitch"
																		class="onoffswitch-checkbox" readonly="readonly"
																		id="show-tabs7"> <label
																		class="onoffswitch-label" for="show-tabs"> <span
																			class="onoffswitch-inner" data-swchon-text="True"
																			data-swchoff-text="NO"></span> <span
																			class="onoffswitch-switch"></span>
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>
												</div>



  												
												  
												
												   <input
													type="button" value="Preview"
													class="btn btn-primary" onclick="ViewApplicationDetails();"
													style="float:left;" />
												
													<input
													type="submit" value="Submit Application"
													class="btn btn-primary" onclick="return checkData(0);"
													style="float:right;margin-right: 250px;" /><br><br>
											</div>
											<div class="form-actions">
												<div class="row">
													<div class="col-sm-20">
														<ul class="pager wizard no-margin">
															
															<li class="previous disabled" id="previousbuttonId"><a
																href="javascript:void(0);"
																class="btn btn-lg btn-default" rel='tooltip' data-placement='right' data-original-title="Click here for Previous Stage"> Previous </a></li>
															
															
															<li class="next" id="nextButtonId"><a
																id="nextButton" href="javascript:void(0);"
																class="btn btn-lg btn-default" rel='tooltip' data-placement='left' data-original-title="Click here for Next Stage"> Next </a></li>
														</ul>
													</div>
												</div>
											</div>

										</div>
									</div>
								</form:form>
							</div>

						</div>
					

					</div>
					

				</div>
				

			</article>
		</div>
	
	</section>
	

</div>



<script type="text/javascript">

function onChangeMSBuild(value){
	
	if(value=='MS Building'){
		$("#plinthAreaNoEntry").show();
		$('#specialGovSchemeId').hide();
		$('#showNoOfconnections').show();
		
	}else if(value=='Layout'){
		$('#specialGovSchemeId').hide();
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
		$('#dimensionsShowforLayout').show();
		$('#showNoOfconnections').hide();
		$("#noofconnections").val('1');
	}else if(value=='Multiple Connection'){
		$('#showNoOfconnections').show();
	}else{
		$("#plinthAreaNoEntry").hide();
		$('#dimensionsShowforLayout').hide();
		$('#specialGovSchemeId').show();
		$('#showNoOfconnections').hide();
		$("#noofconnections").val('1');
	}
	
	
}
function onChangeLayout(value){
 
  if(value=="20*30"){
	  $("#loadkw").val(3);
	  $('#oddDimensionsEntry').hide();
	  $("#dimensions").val('20*30');
	  $('#noOfSitesEntry').show();
	  $("#noOfSites").val('1');
	  
  }else if(value=="30*40"){
	  $("#loadkw").val(6);
	  $('#oddDimensionsEntry').hide();
	  $("#dimensions").val('30*40');
	  $('#noOfSitesEntry').show();
	  $("#noOfSites").val('1');
  }else if(value=="60*40"){
	  $("#loadkw").val(8);
	  $('#oddDimensionsEntry').hide();
	  $("#dimensions").val('60*40');
	  $('#noOfSitesEntry').show();
	  $("#noOfSites").val('1');
  }else if(value=="50*80"){
	  $("#loadkw").val(10);
	  $('#oddDimensionsEntry').hide();
	  $("#dimensions").val('50*80');
	  $('#noOfSitesEntry').show();
	  $("#noOfSites").val('1');
  }else{
	  $("#dimensions").val('');
	  $('#oddDimensionsEntry').show();
	  $('#noOfSitesEntry').hide();
	  $("#noOfSites").val('1');
	  $("#loadkw").val('');
	  
	  
  }
}

function onChangeNewDimension(value){
	
	if(value<=600){
		 $("#loadkw").val(3);
	}else{
		
		if(value>4000){
			var newvalue=value-4000;
			var loadkiloWt=parseFloat((newvalue/400));
			$("#loadkw").val((10+Math.ceil(loadkiloWt)));
		}else{
			if(value>600 && value<=1200){
				$("#loadkw").val(6);
			}else if(value>1200 && value<=2400){
				$("#loadkw").val(8);
			}else if(value>2400 && value<=4000){
				$("#loadkw").val(10);
			}
		}
	}
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
	$("#loadkw").val(noOfSites*(load));
	
}
function myFunction1(myNumber) {
	 $("#loadkw").val(parseFloat(myNumber));
}

function myFunction2(myNumber) {
	 $("#loadhp").val(parseFloat(myNumber));
}

function myFunction3(myNumber) {
	 $("#loadkva").val(parseFloat(myNumber));
}


$('select[id*=circleSiteCode]').change(function() {
	var circleSiteCode = $("#circleSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllDivisions/" + circleSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',null).text(" "); 
            $('#divisionSiteCode').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"0").text("Select Division");
            $('#divisionSiteCode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.divisionName);
				$('#divisionSiteCode').append(newOption);
			}));
		}
	});
});





function ViewApplicationDetails(){
	
	    $("#wizard-1").attr("target", "_blank");
		$("#wizard-1").attr("action", "./applicationPreView/Show").submit();
	
	
}


function convertToUpperCase(){
	
	$("#personName").val($("#personName").val().toUpperCase());
	$("#fatherName").val($("#fatherName").val().toUpperCase());
	$("#nomineName").val($("#nomineName").val().toUpperCase());
	
	$("#orgName").val($("#orgName").val().toUpperCase());
	$("#authSigName").val($("#authSigName").val().toUpperCase());
	$("#authDesName").val($("#authDesName").val().toUpperCase());
	
	$("#hnopres").val($("#hnopres").val().toUpperCase());
	$("#floorpres").val($("#floorpres").val().toUpperCase());
	$("#streetpres").val($("#streetpres").val().toUpperCase());
	$("#areapres").val($("#areapres").val().toUpperCase());
	$("#crosspres").val($("#crosspres").val().toUpperCase());
	$("#mainpres").val($("#mainpres").val().toUpperCase());
	$("#citypres").val($("#citypres").val().toUpperCase());
	$("#landmarkpres").val($("#landmarkpres").val().toUpperCase());
	
	
	$("#hnoperm").val($("#hnoperm").val().toUpperCase());
	$("#floorperm").val($("#floorperm").val().toUpperCase());
	$("#streetperm").val($("#streetperm").val().toUpperCase());
	$("#areaperm").val($("#areaperm").val().toUpperCase());
	$("#crossperm").val($("#crossperm").val().toUpperCase());
	$("#mainperm").val($("#mainperm").val().toUpperCase());
	$("#cityperm").val($("#cityperm").val().toUpperCase());
	$("#landmarkperm").val($("#landmarkperm").val().toUpperCase());
	
	
	$("#lecName").val($("#lecName").val().toUpperCase());
	$("#lecAddress").val($("#lecAddress").val().toUpperCase());
	$("#lecClass").val($("#lecClass").val().toUpperCase());
	$("#licenseNo").val($("#licenseNo").val().toUpperCase());
	$("#supervisorPermitNo").val($("#supervisorPermitNo").val().toUpperCase());
	
	
	$("#lecName").val($("#lecName").val().toUpperCase());
	$("#lecAddress").val($("#lecAddress").val().toUpperCase());
	$("#lecClass").val($("#lecClass").val().toUpperCase());
	$("#licenseNo").val($("#licenseNo").val().toUpperCase());
	$("#supervisorPermitNo").val($("#supervisorPermitNo").val().toUpperCase());
	
	
	
	$("#nearbyrrnoone").val($("#nearbyrrnoone").val().toUpperCase());
	$("#nearbyrrnotwo").val($("#nearbyrrnotwo").val().toUpperCase());
	$("#nearbyrrnothree").val($("#nearbyrrnothree").val().toUpperCase());
	$("#nearbyacnoone").val($("#nearbyacnoone").val().toUpperCase());
	$("#nearbyacnotwo").val($("#nearbyacnotwo").val().toUpperCase());
	$("#nearbyacnothree").val($("#nearbyacnothree").val().toUpperCase());
	
	
	
	
}

$('select[id*=locality]').change(function() {
	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	 if(x){
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	}); 
	  
	}
	 
	 if(y){
		  $.ajax({
			type : "POST",
			url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
	            newOption.attr('value',0).text(" "); 
	            $('#natureofinst').empty(newOption);
	            var defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("Select Installtion");
	            $('#natureofinst').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
					$('#natureofinst').append(newOption);
				}));
			}
		}); 
		  
		}  
	 
});

function readURLLEC(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#lecDocumentId').val("");
            
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
        	$('#lecDocumentId').val("");
            return false;
        }
       
        } 
    
}


$("#lecDocumentId").change(function(){
	readURLLEC(this);
});


function readURLOtherDoc(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#otherDocumentId').val("");
            
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
        	$('#otherDocumentId').val("");
            return false;
        }
       
        } 
    
}


$("#otherDocumentId").change(function(){
    readURLOtherDoc(this);
});

//onchange Drop Down For Division

$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subDivisionSiteCode').empty(newOption);
                var defaultOption = $('<option disabled="" selected="">');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#subDivisionSiteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.subId).text(item.subDivisionName);
					$('#subDivisionSiteCode').append(newOption);
				}));
			}
		});
	});
	
//onchange Drop Down For SubDivision
$('select[id*=subDivisionSiteCode]').change(function() {
	var subDivisionSiteCode = $("#subDivisionSiteCode").val();
	$.ajax({
		type : "POST",
		url : "./helpDesk/getAllSections/" + subDivisionSiteCode,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#sitecode').empty(newOption);
            var defaultOption = $('<option disabled="" selected="">');
            defaultOption.attr('value',0).text("Select Section");
            $('#sitecode').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.siteCode).text(item.sectionName);
				$('#sitecode').append(newOption);
			}));
		}
	});
});



$("#ltcategory").change(function(){

	$("#applicationTypeId").show();
	$('#specialGovSchemeId').show();
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	
	
	
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	});
	  
});	


$("#applicationtype").change(function(){

	$("#applicationTypeId").show();
	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	});
	  
});	

$("#htcategory").change(function(){
	$("#plinthAreaNoEntry").hide();
	$('#specialGovSchemeId').hide();
	$("#applicationTypeId").hide();
	$("#applicationtype").val('Single Connection');
	$('#showNoOfconnections').hide();
	$("#noofconnections").val('1');
	
	
	var locality = $("#locality").val();
	var x = document.getElementById('ltcategory').checked;
	var y = document.getElementById('htcategory').checked;
	
	var voltageSupply="";
	var area="";
	if(locality == "CMC And Urban Local Bodies"){
		area="URBAN";
	}
	if(locality=="Village Panchayat"){
		area="RURAL";
	}
	if(x){
		voltageSupply="LT";
	}
	if(y){
		voltageSupply="HT";
	}
	
	
	
	
	  $.ajax({
		type : "POST",
		url : "./natureofInstallation/getSubTariff/" + area+"/"+voltageSupply,
		dataType : "json",
		success : function(data) {
			var newOption = $('<option>');
            newOption.attr('value',0).text(" "); 
            $('#natureofinst').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#natureofinst').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#natureofinst').append(newOption);
			}));
		}
	});
	
	
});	

function change1(){
	
	var natureofins = $("#natureofinst").val();
	var applicationtype = $("#applicationtype").val();
	
	
	if(natureofins=="DOMESTIC LIGHTING - LT2A(II) - LT2A" || natureofins=="DOMESTIC LIGHTING - LT2A(I) - LT2A" || 
			natureofins=="DOMESTIC LIGHTING & HEATING - LT2A(I) - LT2A" || natureofins=="DOMESTIC LIGHTING & HEATING - LT2A(II) - LT2A"){
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		
		
        $("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divltbj").hide();
		$("#applicationTypeId").show();
		
		if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div2a").show();
		}
		
	}
	
	
	if(natureofins=="PRIVATE EDUCATIONAL INSTITUTIONS AIDED/UNAIDED - LT2B(II) - LT2B" || natureofins=="PRIVATE EDUCATIONAL INSTITUTIONS AIDED/UNAIDED - LT2B(I) - LT2B"){
		
		$("#div2a").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		$("#applicationTypeId").show();
		
		if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div2b").show();
		}
	}
	
	
	
	if(natureofins=="COMMERCIAL LIGHTING - LT3(II) - LT3" || natureofins=="COMMERCIAL LIGHTING - LT3(I) - LT3" || natureofins=="COMMERCIAL POWER - LT3(I) - LT3" || natureofins=="COMMERCIAL POWER - LT3(II) - LT3"){
		
		$("#div2b").hide();
		$("#div2a").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		$("#applicationTypeId").show();
		if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div3").show();
		}	
	}
	
	if(natureofins=="IRRIGATION PUMPSETS - LT4A - LT4A"){
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div2a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div4a").show();
		}	
	}
	
	if(natureofins=="IRRIGATION PUMPSETS - LT4B - LT4B"){
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div2a").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div4b").show();
		}	
	}
	
	if(natureofins=="IRRIGATION PUMPSETS - LT4C(I) - LT4C"){
		
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div2a").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div4c1").show();
		}
			
	}
	
	if(natureofins=="IRRIGATION PUMPSETS - LT4C(II) - LT4C"){
		$("#div4c2").show();
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div2a").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			
		}
	}
	
	if(natureofins=="INDUSTRIAL POWER - LT5 - LT5"){
		
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div2a").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		$("#applicationTypeId").show();
		
		if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div5").show();
		}	
	}
	
	if(natureofins=="WATER SUPPLY - LT6A - LT6A"){
		
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div2a").hide();
		$("#div6b").hide();
		$("#divltbj").hide();
		$("#div7").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}else{
			$("#div6a").show();
		}
	}
	
	if(natureofins=="STREET LIGHT - LT6B - LT6B"){
		
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div2a").hide();
		$("#div7").hide();
		$("#divltbj").hide();
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}else{
			$("#div6b").show();
		}
	}
	
	if(natureofins=="TEMPORARY - LT7 - LT7"){
		
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divltbj").hide();
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#div7").show();
		}
	}
	
	if(natureofins=="DOMESTIC LIGHTING(BJ/KJ) - LT1 - LT1"){
		$("#div7").hide();
		
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		
		
		$("#divht5").hide();
        $("#divht1").hide();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		
		
		
		if(applicationtype=='MS Building'){
			alert("MS Building is not Applicable for the selected Tariff only LT2,LT3 and LT5 are Applicable for MS Building");
			$("#natureofinst").val('');
		}
		else if(applicationtype=='Layout'){
			alert("Layout is not Applicable for the selected Tariff only LT6A and LT6B are applicable for Layout");
			$("#natureofinst").val('');
		}else{
			$("#divltbj").show();
		}
	}


	
	
	if(natureofins=="WATER SUPPLY & SEWERAGE - HT1 - HT1"){
		
		$("#divht1").show();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		$("#divltbj").hide();
		
		
        $("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		
	}
	
	
	if(natureofins=="INDUSTRIES,UNIVERSITIES & EDUCATIONAL INSTITUTIONS - HT2A - HT2A"){
		
		$("#divht2a").show();
		$("#divht1").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	if(natureofins=="COMMERCIAL - HT2B - HT2B"){
		$("#divht2b").show();
		
		$("#divht2a").hide();
		$("#divht1").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	
	if(natureofins=="LIFT IRRIGATION (GOVT LI SCHEMES) - HT3A(I) - HT3A"){
		
		$("#divht3a1").show();
		
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	
	if(natureofins=="LIFT IRRIGATION(PRIVATE LI SCHEMES URBAN FEEDER) - HT3A(II) - HT3A" || natureofins=="LIFT IRRIGATION (PRIVATE LI SCHEMES URBAN FEEDER) - HT3A(II) - HT3A"){
		$("#divht3a2").show();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht1").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	if(natureofins=="LIFT IRRIGATION (PRIVATE LI SCHEMES RURAL FEEDER) - HT3A(III) - HT3A"){
		$("#divht3a3").show();
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht1").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	
	if(natureofins=="IRRIGATION,AGRICULTURE,GOV HORTICULTURE FARMS,COFFEE & TEA PLANTS - HT3B - HT3B"){
		$("#divht3b").show();
		
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht1").hide();
		$("#divht4ab").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	if(natureofins=="RESIDENTIAL APARTMENTS & COLONIES IN URBAN - HT4A - HT4A" || natureofins=="RESIDENTIAL APARTMENTS & COLONIES IN RURAL - HT4B - HT4B"){
		$("#divht4ab").show();
		
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht1").hide();
		$("#divht5").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	if(natureofins=="TEMPORARY - HT5 - HT5"){
		$("#divht5").show();
		
		$("#divht2a").hide();
		$("#divht2b").hide();
		$("#divht3a1").hide();
		$("#divht3a2").hide();
		$("#divht3a3").hide();
		$("#divht3b").hide();
		$("#divht4ab").hide();
		$("#divht1").hide();
		
		$("#div7").hide();
		$("#div2b").hide();
		$("#div3").hide();
		$("#div4a").hide();
		$("#div4b").hide();
		$("#div4c1").hide();
		$("#div4c2").hide();
		$("#div5").hide();
		$("#div6a").hide();
		$("#div6b").hide();
		$("#div2a").hide();
		$("#divltbj").hide();
	}
	
	
	 var res = natureofins.split("-"); 
	 var powerReq=res[0];
	 $('#supplyfor').val(powerReq).attr("readonly", "readonly");;
	
	
	 
	 var result1 = res[1].indexOf("LT1") > -1;
	 var result2 = res[1].indexOf("LT2") > -1;
	 var result6A = res[1].indexOf("LT6A") > -1;
	 var result6B = res[1].indexOf("LT6B") > -1;
	 var result7 = res[1].indexOf("LT7") > -1;

	 var result3 = res[1].indexOf("LT3") > -1;
	 var result5 = res[1].indexOf("LT5") > -1;
	 
	 var result4 = res[1].indexOf("LT4") > -1;
	 
	 var resultHT = res[1].indexOf("HT") > -1;
	 var result8 = natureofins.indexOf("IRRIGATION") > -1;
	 
	if(result1 || result2 || result6A || result7 || result6B){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').show();
	} 
	if(result3 || result5){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').show();
	}
	if(result4){
		$('#kvaNoEntry').hide();
		$('#hpNoEntry').show();
		$('#KWANoEntry').hide();
	}
	if(resultHT){
		$('#specialGovSchemeId').hide();
		//$('#purpose').val('3');  //.attr("disabled", true);
		$('#reqphase').val('3').attr("disabled", true);
		
	} 
	
	if(result6B){
		$('#strprklgt').show();
	}
	if(!result6B){
		$('#strprklgt').hide();
	}
	if(result8){
		$('#ipsetWtr').show();
	}
	if(!result8){
		$('#ipsetWtr').hide();
	}
	
	if(!resultHT){
	    $('#reqphase').val('0').removeAttr('disabled');
    }
	
	if(resultHT){
		$('#kvaNoEntry').show();
		$('#hpNoEntry').hide();
		$('#KWANoEntry').hide();
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


$('#arfreceiptdt').datepicker({
	  dateFormat : 'dd/mm/yy',
	  maxDate: new Date()

}); 

$('#validity1').datepicker({
	  dateFormat : 'dd/mm/yy',
	  minDate: new Date()

}); 

$('#validity2').datepicker({
	  dateFormat : 'dd/mm/yy',
	  minDate: new Date()

}); 



$(document).ready(
		function() {

			$("#appregdate").val(getPresentMonthDate('${selectedMonth1}'))
					.attr("readonly", "readonly");

		});
		
		
$.ajaxSetup({cache: false});

function checkBoxLabelForProfile(){
	var x = document.getElementById('userProfile').checked;
	if (x) {
		var customername= "${customername}";
		var mobilenumber= "${mobilenumber}";
		var emailId= "${emailId}";
		
		$('#personName').val(customername);
		$('#mobilenopres').val(mobilenumber);
		$('#emailpres').val(emailId);
	}

	else {
		$('#personName').val("");
		$('#mobilenopres').val("");
		$('#emailpres').val("");
	}

}


$(document).ready(function() {
	$("#basic1").show();
	$("#basic2").show();
	$("#useProfile1").show();
	$("#basic3").hide();
	$("#basic4").hide();

});


 
 
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
    	
    	 
    	
    	var photoSize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(photoSize>parseFloat(512.0)){
    		alert(" Photo size can't be greater than 512KB\n Steps to Reduce Size of Photo if >512KB [For Image]\n1.Right Click on the Photo -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
    		$('#blah').attr('src', "./resources/img/personImage.jpg");
            $('#imgInp').val("");
            return false;
    	}
      
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        		//$('#blah').show();
        		
                $('#blah').attr('src', e.target.result);
            }
        	  reader.readAsDataURL(input.files[0]);
        }else {
        	alert(input.files[0].name + " is not a valid image file.");
            $('#blah').attr('src', "./resources/img/personImage.jpg");
            $('#imgInp').val("");
            //$('#blah').hide();
            return false;
        }
        } 
    
}


$("#imgInp").change(function(){
    readURL(this);
});

function readScannedImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
        
        var signatureSize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(signatureSize>parseFloat(512.0)){
    		alert(" Signature size can't be greater than 512KB\n Steps to Reduce Size of Signature if >512KB [For Image]\n1.Right Click on the Signature -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
    		$('#scanImage1').attr('src', "./resources/img/blankRec.jpg");
            $('#scannedImage').val("");
            return false;
    	}
        
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
                $('#scanImage1').attr('src', e.target.result);
            }
        	  reader.readAsDataURL(input.files[0]);
        }else {
        	alert(input.files[0].name + " is not a valid scanned image file.");
            $('#scanImage1').attr('src', "./resources/img/blankRec.jpg");
            $('#scannedImage').val("");
            return false;
        }
        } 
    
}


$("#scannedImage").change(function(){
    readScannedImage(this);
});
$('#btnCrop').click(function () {
    var x1 = $('#imgX1').val();
    var y1 = $('#imgY1').val();
    var width = $('#imgWidth').val();
    var height = $('#imgHeight').val();
    var canvas = $("#canvas")[0];
    var context = canvas.getContext('2d');
    var img = new Image();
    img.onload = function () {
        canvas.height = height;
        canvas.width = width;
        context.drawImage(img, x1, y1, width, height, 0, 0, width, height);
        $('#imgCropped').val(canvas.toDataURL());
    };
    img.src = $('#Image1').attr("src");
});
 
function SetCoordinates(c) {
    $('#imgX1').val(c.x);
    $('#imgY1').val(c.y);
    $('#imgWidth').val(c.w);
    $('#imgHeight').val(c.h);
    $('#btnCrop').show();
};

function readURL1(input) {
    if (input.files && input.files[0]) {
    	
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp1').val("");
            
            return false;
    	}
    	
        var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp1').val("");
            return false;
        }
        } 
}


$("#imgInp1").change(function(){
    readURL1(this);
});


function readURL2(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp2').val("");
            return false;
    	}
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp2').val("");
            return false;
        }
        } 
    
}


$("#imgInp2").change(function(){
    readURL2(this);
});

function readURL3(input) {
    if (input.files && input.files[0]) {
       
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp3').val("");
            return false;
    	}
    	
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp3').val("");
            return false;
        }
        } 
}


$("#imgInp3").change(function(){
    readURL3(this);
});
function readURL4(input) {
    if (input.files && input.files[0]) {
       
    	
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp4').val("");
            
            return false;
    	}
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp4').val("");
            return false;
        }
        } 
    
}


$("#imgInp4").change(function(){
    readURL4(this);
});

function readURL5(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp5').val("");
            return false;
    	}
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp5').val("");
            return false;
        }
        } 
}


$("#imgInp5").change(function(){
    readURL5(this);
});



function readURL6(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp6').val("");
            return false;
    	}
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	reader.onload = function (e) {
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp6').val("");
            return false;
        }
        } 
    
}


$("#imgInp6").change(function(){
    readURL6(this);
});

function readURL7(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp7').val("");
            
            return false;
    	}
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp7').val("");
            return false;
        }
        } 
    
}


$("#imgInp7").change(function(){
    readURL7(this);
});



function readURL8(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp8').val("");
            
            return false;
    	}
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp8').val("");
            return false;
        }
        } 
    
}


$("#imgInp8").change(function(){
    readURL8(this);
});

function readURL9(input) {
    if (input.files && input.files[0]) {
       
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp9').val("");
            
            return false;
    	}
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp9').val("");
            return false;
        }
        } 
    
}


$("#imgInp9").change(function(){
    readURL9(this);
});



function readURL10(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp10').val("");
            
            return false;
    	}
    	
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp10').val("");
            return false;
        }
        
       
        } 
    
}


$("#imgInp10").change(function(){
    readURL10(this);
});


function readURL11(input) {
    if (input.files && input.files[0]) {
        
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp11').val("");
            
            return false;
    	}
    	
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp11').val("");
            return false;
        }
        } 
    
}


$("#imgInp11").change(function(){
    readURL11(this);
});

function readURL12(input) {
    if (input.files && input.files[0]) {
       
    	var fsize=parseFloat(input.files[0].size/1024).toFixed(2);
    	if(Math.floor(fsize)>parseFloat(1024.0)){
    		alert(" File size can't be greater than 1MB\n Steps to Reduce Size of Image File if >1MB [For Image]\n1.Right Click on the image -->open with Paint-->Resize-->select Pixels-->Reduce pixels and Save then upload");
            $('#imgInp12').val("");
            
            return false;
    	}
    	
    	
    	var reader = new FileReader();
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.docx|.xls|.pdf|.xlsx|.doc)$/;
        if (regex.test(input.files[0].name.toLowerCase())) {
        	
        	reader.onload = function (e) {
        		
        	  reader.readAsDataURL(input.files[0]);
            }
        }else {
        	alert("Only images,word,pdf and excel files are allowed with Valid File name");
        	$('#imgInp12').val("");
            return false;
        }
        } 
    
}


function checkMeterPhase(){
	var nature=document.getElementById("natureofinst").value;
	var result3 = nature.indexOf("HT") > -1;
	if(result3 && document.getElementById("reqphase").value==1){
		alert("Please select Three Phase for HT Installations"); 
		$('#reqphase').val("0");
		
	}
}

$("#imgInp12").change(function(){
    readURL12(this);
});


$("#applicanttype").change(function(){
	var applicanttype=$("#applicanttype").val();

	if(applicanttype == '1'){
		$("#basic1").show();
		$("#basic2").show();
		$("#basic3").hide();
		$("#basic4").hide();
		$("#useProfile1").show();

		
	}else{
		$("#basic1").hide();
		$("#basic2").hide();
		$("#basic3").show();
		$("#basic4").show();
		$("#useProfile1").hide();

	}
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

		$('#hnoperm').val(hnopres);

		$('#floorperm').val(floorpres);
		$('#streetperm').val(streetpres);
		$('#areaperm').val(areapres);
		$('#crossperm').val(crosspres);
		$('#mainperm').val(mainpres);

		$('#cityperm').val(citypres);
		$('#pinperm').val(pinpres);
		$('#emailperm').val(emailpres);
		$('#phoneperm').val(phonepres);
		$('#mobilenoperm').val(mobilenopres);
		$('#landmarkperm').val(landmarkpres);
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

function checkData(param) {
	   
	 var className1=$('#tab11').attr('class');
	 var className2=$('#tab22').attr('class');
	 var className3=$('#tab33').attr('class');
	 var className4=$('#tab44').attr('class');
	 var className5=$('#tab55').attr('class');
	 var className6=$('#tab66').attr('class');
	 var className7=$('#tab77').attr('class');
	
	 var nature=document.getElementById("natureofinst").value; 
	 var res = nature.split("-"); 
	 var resultLT = res[1].indexOf("LT") > -1;
	 
	 var kwload= document.getElementById("loadkw").value;
	 var kvaload=document.getElementById("loadkva").value;
	 var hpload=document.getElementById("loadhp").value;
	 
	 var totalloadinKVA=((kwload*1.25)+(kvaload*1.0)+(hpload*0.9325));
	 
	 var applicationType = document.getElementById('applicationtype').value;
	 var applType= applicationType.indexOf("MS Building") > -1;
	 var plinthArea = document.getElementById('plintharea').value;
	 
	 
	 var resultLT7 = res[1].indexOf("LT7") > -1;
	 var totalloadinHP=((kwload*1.34048)+(kvaload*1.0724)+(hpload*1.0));
	 if(className1 != 'complete'){	
		 
		 alert("Please fill Nature Of Installation Details");
		 
		 return false;
	 }
	 else if(className2 != 'complete'){
		
		 alert("Please fill General Particulars Details");
		 return false;
	 }
	 else if(className3 != 'complete'){
		
		 alert("Please fill Permanent Address Details");
		 return false;
	 }
	 else if(className4 != 'complete'){
		
		 alert("Please fill Connection & Account Details");
		 return false;
	 }
	 else if(className5 != 'complete'){				 
		 alert("Please fill Additional Details");
		 return false;
	 }
	 else  if(className6 != 'complete'){				 
		 alert("Please fill Particulars of LEC");
		 return false;
	 }
	 
	 else  if(className7 != 'complete'){				 
		 alert("Please fill Required Document Details");
		 return false;
	 }
	
	   if (document.getElementById("loadkw").value != null && document.getElementById("loadhp").value != null && document.getElementById("loadkva").value != null) {
			if (document.getElementById("loadkw").value == 0 && document.getElementById("loadhp").value == 0 && document.getElementById("loadkva").value == 0) {
					alert("Please enter at least one Desired LOAD greater than Zero in Connection & Account Details");
					return false;
			}
		}
	 
	   /* if(document.getElementById("loadkw").value>5 || document.getElementById("loadhp").value>5 || document.getElementById("loadkva").value>5){
		   if(document.getElementById("reqphase").value==1){
			   alert("Please select Three Phase if Load(KW/HP/KVA) greater than 5 in Connection & Account Details");
			   return false;
		   }
	   } */
	   if(resultLT7 && totalloadinHP>100){
			alert("Load(KW) should be less than or equal to 74.6KW for selected Nature of Installation in Connection & Account Details");
			return false;
	   }
	   
	
	   else if(applType){
			
		   if(totalloadinHP>=46.9 || plinthArea>800){
				
			}else{
				alert("Total Load should be greater than 35KW or Plinth Area greater than 800Sq.m for MS Building"); 
				return false;
			}
		}
	   if(((totalloadinKVA>=5000) && resultLT)){
			alert("Total Load should be less than 5000KVA in Connection & Account Details"); 
			return false;
	   }
	   
	   if (param == 0) {
			
		   if(confirm("Are you sure you want to submit Application?")){
			   $("#wizard-1").attr("target", "");
			   $("#wizard-1").attr("action", "./applicationRegistration/create").submit();
		    }
		    else{
		        return false;
		    }
		}

	}
		
$(document).ready(function() {
			pageSetUp();
			
			$("#plinthAreaNoEntry").hide();
			
			Dropzone.autoDiscover = false;
			
			$("#file").dropzone({
				
				addRemoveLinks : true,
				maxFilesize: 0.5,
				dictResponseError: 'Error uploading file!'
			});
			
	
			$.validator.addMethod("regex", function(value, element, regexpr) {
				if(value==""){
					
					return "notmandatory";
				}else{
				return regexpr.test(value);
				}
			}, "");

			$.validator.addMethod('minStrict', function(value, el, param) {
				return value > param;
			});
			
			$.validator.addMethod("dateFormat",
				    function(value, element) {
				        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
				    },
				    "Please enter a date in the format dd/mm/yyyy.");
			
  var $validator=$("#wizard-1").validate({

	// Rules for form validation
	errorContainer: "#errorbox",
    errorLabelContainer: "#errorbox ul",
    wrapper: "li",
	rules : {
		name : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z .]*$/
		},
		fhname : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z .]*$/
		},
		nomineename : {

			maxlength : 100,
			regex : /^[a-zA-Z .]*$/
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
			maxlength : 50,
			regex : /^[a-zA-Z0-9 #._/-]*$/
		},
		floorpres : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		streetpres : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		areapres : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		crosspres : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},

		mainpres : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		citypres : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z .]*$/
		},
		pinpres : {
			required : true,
			digits : true,
			regex : /^[0-9]{6}$/
		},
		mobilenopres : {
			required: {
                depends: function(element) {
                	
                	if($('#mobilenopres').val()=="0000000000"){
                		    alert("All digits cannot be Zero");
                		    $('#mobilenopres').val("");
                            return false;
                		}
                	return true;
                }
            },
			
			
			digits : true,
			regex : /^[0-9]{10}$/
		},

		locality : {
			required : true,
			maxlength : 100
		},

		landmarkpres : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		landmarkperm : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},

		hnoperm : {
			required : true,
			maxlength : 50,
			regex : /^[a-zA-Z0-9 #._/-]*$/
		},
		floorperm : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		streetperm : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		areaperm : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		crossperm : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},

		mainperm : {
			
			maxlength : 20,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		cityperm : {
			required : true,
			maxlength : 100,
			regex : /^[a-zA-Z .]*$/
		},
		pinperm : {
			required : true,
			digits : true,
			regex : /^[0-9]{6}$/
		},
		mobilenoperm : {
			required: {
                depends: function(element) {
                	
                	if($('#mobilenoperm').val()=="0000000000"){
                		    alert("All digits cannot be Zero");
                		    $('#mobilenoperm').val("");
                            return false;
                		}
                	return true;
                }
            },
			digits : true,
			regex : /^[0-9]{10}$/
		},

		nearbyrrnoone : {
			required : true,
			maxlength : 25,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},

		loadkw : {
			required : true,
			maxlength : 12,
			regex : /^[0-9 .]*$/,
			max:10000
		},
		circleSiteCode : {
			required : true
		},
		divisionSiteCode : {
			required : true
		},
		subDivisionSiteCode : {
			required : true
		},

		loadhp : {
			required : true,
			maxlength : 12,
			regex : /^[0-9 .]*$/,
			max:10000
		},

		loadkva : {
			required : true,
			maxlength : 12,
			regex : /^[0-9 .]*$/,
			max:10000
		},

		nearbyrrnotwo : {
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/,
			maxlength : 25
		},
		nearbyrrnothree : {
			
			maxlength : 25,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},
		nearbyacnoone : {

			maxlength : 25,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},
		nearbyacnotwo : {

			maxlength : 25,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},
		nearbyacnothree : {

			maxlength : 25,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},

		emailpres : {
			email : true,
			maxlength : 100
		},

		natureofinst : {
			required : true
		},
		applicanttype:{
			required : true
		},

		profileImage : {
		 required : true 

		},
		scannedImage:{
			required : true 
		},
		/* splgovtscheme : {
			required : true

		}, */
		ownership : {
			required : true

		},
		watersource : {
			required : true

		},
		
		/* govtpvt : {
			required : true

		}, */
		
		/* supplyfor : {
			required : true

		}, */
		reqfor : {
			required : true

		},
		reqphase : {
			required : true

		},
		
		sitecode : {
			required : true

		},
		
		
		publicstreetorparklight:{
			/* required : true */
		},
		purpose:{
			required : true
		},
		
		lecName:{
			required : true,
			maxlength : 50,
			regex : /^[a-zA-Z .]*$/
		},
		
		
		lecClass:{
			required : true,
			maxlength : 25,
			regex : /^[a-zA-Z0-9 ._]*$/
		},
		
		
		licenseNo:{
			required : true,
			maxlength : 50,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},
		
		
		supervisorPermitNo:{
			required : true,
			maxlength : 50,
			regex : /^[0]*[1-9A-Za-z][0-9A-Za-z]*$/
		},
		
		validity1:{
			required : true,
			dateFormat: true
		},
		
		validity2:{
			required : true,
			dateFormat: true
		},
		
		arfreceiptno:{
			maxlength : 25
		},
		arfamount:{
			maxlength : 12
		},
		phonepres:{
			digits : true,
		},
		phoneperm:{
			digits : true,
		},
		plintharea:{
			required : true,
			regex : /^[0-9 .]*$/,
			maxlength : 12,
			
		},
		dimensions:{
			required : true,	
		},
		noOfSites:{
			required : true,
			digits : true,
			minStrict : 0
		},
		noofconnections:{
			required : true,
			digits : true,
			minStrict : 0
		}
		
		
	},

	// Messages for form validation
	messages : {
		noofconnections:{
			minStrict : 'minimum 1 connection is required',
		},
		noOfSites:{
			minStrict : 'minimum value is 1',
		},
		lecName : {
			required : 'Please enter contractor name',
			regex : "please enter valid name"
		},
		
		lecClass : {
			required : 'Please enter this field',
			regex : "Special characters not allowed except space dot(.) and underscore(_)",

		},
		natureofinst:{
			required : 'Please select nature of installation',
		},
	
		licenseNo : {
			required : 'Please enter license no',
			regex : "please enter valid Licence No"

		},
		
		supervisorPermitNo : {
			required : 'Please enter supervisor permit no',
			regex : "please enter valid Permit No"

		},
		
		validity1 : {
			required : 'Please select license no valididty',

		},
		
		validity2 : {
			required : 'Please select permit no validity',

		},
		
		
		
		govtpvt : {
			required : 'Please select this field',

		},
		
		
		publicstreetorparklight : {
			required : 'Please select this field',

		},
		
		purpose : {
			required : 'Please select this field',

		},
		
		/* supplyfor : {
			required : 'Please select this field',

		}, */
		
		reqfor : {
			required : 'Please select this field',

		},
		
		reqphase : {
			required : 'Please select this field',

		},
		
		
		splgovtscheme : {
			required : 'Please select this field',

		},
		
		
		ownership : {
			required : 'Please select Ownership',

		},
		
		watersource : {
			required : 'Please select this field',

		},
		applicanttype : {
			required : 'Please select applicant type',

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
			required : 'Please enter house/flat no',
			regex : "Special characters not allowed except space dot(.) underscore(_) - / and #"
		},
		floorpres : {
			required : 'Please enter floor no',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		streetpres : {
			required : 'Please enter street name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		areapres : {
			required : 'Please enter area name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		crosspres : {
			required : 'Please enter cross name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},

		mainpres : {
			required : 'Please enter main',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		citypres : {
			required : 'Please enter city',
			regex : 'Please enter valid city'
		},
		pinpres : {
			required : 'Please enter pin',
			regex : "Enter 6 digit pincode",
		},
		mobilenopres : {
			required : 'Please enter mobile no',
			regex : "Enter 10 digit mobile no",
			digits : 'Please enter only digits(10 digit No.)',
		},

		hnoperm : {
			required : 'Please enter house/flat no',
			regex : "Special characters not allowed except space dot(.) underscore(_) - / and #"
		},
		floorperm : {
			required : 'Please enter floor no',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		streetperm : {
			required : 'Please enter street name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		areaperm : {
			required : 'Please enter area name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		crossperm : {
			required : 'Please enter cross name',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},

		mainperm : {
			required : 'Please enter main',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		cityperm : {
			required : 'Please enter city',
			regex : 'Please enter valid city'
		},
		pinperm : {
			required : 'Please enter pin',
			regex : "Enter 6 digit pincode",
		},
		mobilenoperm : {
			required : 'Please enter mobile no',
			digits : 'Please enter only digits(10 digit No.)',
			regex : "Enter 10 digit mobile no"
		},

		landmarkperm : {
			required : 'Please enter landmark',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},

		locality : {
			required : 'Please select proposed installation'
		},
		circleSiteCode:{
			required : 'Please select Circle',
		},
		divisionSiteCode : {
			required : 'Please select division',
		},
		subDivisionSiteCode : {
			required : 'Please select sub division'
		},

		landmarkpres : {
			required : 'Please enter landmark',
			regex : "Special characters not allowed except space dot(.) and underscore(_)"
		},
		nearbyrrnoone : {
			required : 'Please enter RR no',
			regex : 'please enter valid RR No'
		},

		loadkw : {
			required : 'Please enter load(kw)',
			maxlength : 'maxlength is 12',
			regex : 'please enter numbers',
			max : 'max value is 10000',
		},

		loadhp : {
			required : 'Please enter load(hp)',
			regex : 'please enter numbers',
			maxlength : 'maxlength is 12',
			max : 'max value is 10000',
		},
		loadkva : {
			required : 'Please enter load(kva)',
			regex : 'please enter numbers',
			maxlength : 'maxlength is 12',
			max : 'max value is 10000',
		},

		emailpres : {

			email : 'Please enter a valid email address'
		},

		nearbyrrnotwo : {

			maxlength : 'maxlength is 25 for RR No',
			regex : 'please enter valid RR No'
		},

		nearbyrrnothree : {

			maxlength : 'maxlength is 25 for RR No',
			regex : 'please enter valid RR No'
		},

		nearbyacnoone : {

			maxlength : 'maxlength is 25 for AC No',
			regex : 'please enter valid AC No'
		},

		nearbyacnotwo : {

			maxlength : 'maxlength is 25 for AC No',
			regex : 'please enter valid AC No'
		},

		nearbyacnothree : {

			maxlength : 'maxlength is 25 for AC No',
			regex : 'please enter valid AC No'
		},

        natureofinsst : {

			required : 'please select nature of Installation'
		},
		profileImage : {
			required : 'Please upload profile image'
		},
		scannedImage:{
			required : 'Please upload Scanned Signature'
		},
		sitecode : {
			required : 'Please select section'
		},
		plintharea:{
			required : 'please enter plinth area',
			regex : 'please enter numbers'
		}
	},
	
	    highlight: function (element) {
		      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		    },
		    unhighlight: function (element) {
		      $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		    },
		    errorElement: 'span',
		    errorClass: 'help-block',
		    errorPlacement: function (error, element) {
		      if (element.parent('.input-group').length) {
		        error.insertAfter(element.parent());
		      } else {
		        error.insertAfter(element);
		      }
		    }

	
});


			
			
			
			  
  $('#bootstrap-wizard-1').bootstrapWizard({
    'tabClass': 'form-wizard',
    'onNext': function (tab, navigation, index) {
      var $valid = $("#wizard-1").valid();
   
      if (!$valid) {
        $validator.focusInvalid();
        return false;
      } else {
        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).addClass(
          'complete');
        $('#bootstrap-wizard-1').find('.form-wizard').children('li').eq(index - 1).find('.step')
        .html('<i class="fa fa-check"></i>');
      }
    }
  });
  

});
 

		

 var validate=new Array();
 
 $("#previousbuttonId").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 
 $("#tab11").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab22").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab33").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab44").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab55").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab66").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });
 $("#tab77").click(function(){
	 $("#nextButtonId").show();
	 $("#showPreviewId").show();
 });

 
 $("#tab99").click(function(){
	
	 $("#nextButtonId").hide();
	 $("#showPreviewId").hide();
	 
	 var className1=$('#tab11').attr('class');
	 var className2=$('#tab22').attr('class');
	 var className3=$('#tab33').attr('class');
	 var className4=$('#tab44').attr('class');
	 var className5=$('#tab55').attr('class');
	 var className6=$('#tab66').attr('class');
	 var className7=$('#tab77').attr('class');
	 
	 
	
	 if(className1 == 'complete'){	
		 
		 $("#show-tabs1").prop('checked', true);
	 }
	 else{
		 $("#show-tabs1").prop('checked', false); 
	 }
	 if(className2 == 'complete'){
		
		 $("#show-tabs2").prop('checked', true);
	 }else{
		 $("#show-tabs2").prop('checked', false);
	 }
	 if(className3 == 'complete'){
		
		 $("#show-tabs3").prop('checked', true);
	 }else{
		 $("#show-tabs3").prop('checked', false);
	 }
	 if(className4 == 'complete'){
		
		 $("#show-tabs4").prop('checked', true);
	 }else{
		 $("#show-tabs4").prop('checked', false); 
	 }
	 
	 if(className5 == 'complete'){				 
		 $("#show-tabs5").prop('checked', true);
	 }else{
		 $("#show-tabs5").prop('checked', false); 
	 }
	 if(className6 == 'complete'){				 
		 $("#show-tabs6").prop('checked', true);
	 }else{
		 $("#show-tabs6").prop('checked', false); 
	 }
	 
	 if(className7 == 'complete'){				 
		 $("#show-tabs7").prop('checked', true);
	 }else{
		 $("#show-tabs7").prop('checked', false); 
	 }
	 
	 
	 
 });
		 
 $("#nextButton").click(function(){

	 var className1=$('#tab11').attr('class');
	 var className2=$('#tab22').attr('class');
	 var className3=$('#tab33').attr('class');
	 var className4=$('#tab44').attr('class');
	 var className5=$('#tab55').attr('class');
	 var className6=$('#tab66').attr('class');
	 var className7=$('#tab77').attr('class');
	 
	 var locality = $("#locality").val();
	 var lecfile = $("#lecDocumentId").val();
	 var nature=document.getElementById("natureofinst").value; 
	
	 if(className1=='active'){
	  if(locality!='null' && nature=="" && locality!=null){
		 alert("Please Select Nature Of Installation");
		 return false;
	  }
	 }
	 
	 if(className6=='active'){
		 if(lecfile=='null' || lecfile=="" || lecfile==null){
			 alert("Please upload LEC Document");
			 return false;
		  }
		 }
	 
	 if(className1 == 'complete'){	
		 
		 $("#show-tabs1").prop('checked', true);
	 }
	 if(className2 == 'complete'){
		
		 $("#show-tabs2").prop('checked', true);
	 }
	 if(className3 == 'complete'){
		
		 $("#show-tabs3").prop('checked', true);
	 }
	 if(className4 == 'complete'){
		
		 $("#show-tabs4").prop('checked', true);
	 }
	 
	 if(className5 == 'complete'){				 
		 $("#show-tabs5").prop('checked', true);
	 }
	 if(className6 == 'complete'){				 
		 $("#show-tabs6").prop('checked', true);
	 }
	 if(className7 == 'complete'){				 
		 $("#show-tabs7").prop('checked', true);
	 }
	 
	
	 if(className4=='active'){
		 var nature=document.getElementById("natureofinst").value; 
		 var res = nature.split("-"); 
		 var psl=document.getElementById("publicstreetorparklight").value;
		 var result1 = nature.indexOf("LT4A") > -1;
		 var result2 = nature.indexOf("LT4B") > -1;
		 var result3 = nature.indexOf("LT4C(I)") > -1;
		 var result4 = nature.indexOf("LT4C(II)") > -1;
		 var result5 = nature.indexOf("LT6B") > -1;
		 var result6 = res[1].indexOf("HT") > -1;
		 
		 var resultforLT = res[1].indexOf("LT") > -1;
		 var resultLT7 = res[1].indexOf("LT7") > -1;
		 
		 var kwload= document.getElementById("loadkw").value;
		 var kvaload=document.getElementById("loadkva").value;
		 var hpload=document.getElementById("loadhp").value;
		
		 var totalloadinKVA=((kwload*1.25)+(kvaload*1.0)+(hpload*0.9325));
		 
		 var totalloadinHP=((kwload*1.34048)+(kvaload*1.0724)+(hpload*1.0));
		 
		 var applicationType = document.getElementById('applicationtype').value;
		 var applType= applicationType.indexOf("MS Building") > -1;
		 var plinthArea = document.getElementById('plintharea').value;
			
		
		if (document.getElementById("loadkw").value == 0 && document.getElementById("loadhp").value == 0 && document.getElementById("loadkva").value == 0 && !(result1 || result2 || result3 || result4)) {
				alert("Please enter at least one Desired LOAD greater than Zero");
				return false;
		}
		else if(resultLT7 && totalloadinHP>100){
			alert("Load(KW) should be less than or equal to 74.6KW for selected Nature of Installation");
			return false;
		}
		
		/* else if(result5 && psl==0){
			alert("Please Select Public Street Light or Park Light");
			return false;
		} */
		else if(result6 && document.getElementById("reqphase").value==1){
			alert("Please select Three Phase for HT Installations"); 
			$('#reqphase').val("0");
			   return false;
		}
		else if(result6 && (document.getElementById("purpose").value==1 || document.getElementById("purpose").value==2)){
			alert("Please select Lighting & Heating as Purpose for HT Installations"); 
			$('#purpose').val("0");
			   return false;
		}
		else if(applType){
			if(totalloadinHP>=46.9 || plinthArea>800){
				
			}else{
				alert("Total Load should be greater than 35KW or Plinth Area greater than 800Sq.m for MS Building"); 
				return false;
			}
		}
		else if((totalloadinKVA>5000 && resultforLT)){
			alert("Total Load should be less than 5000KVA to process LT Application."); 
			return false;
		} 

		
	 }
	 
	 if(className5=='active'){
		 var nature=document.getElementById("natureofinst").value; 
		 var ip=document.getElementById("ipset").value; 
		 var res = nature.split("-");
		 
		
		 if(res[0].trim()=="IRRIGATION PUMPSETS" || res[0].trim()=="LIFT IRRIGATION (GOVT LI SCHEMES)" || res[0].trim()=="LIFT IRRIGATION(PRIVATE LI SCHEMES URBAN FEEDER)" || res[0].trim()=="LIFT IRRIGATION (PRIVATE LI SCHEMES RURAL FEEDER)"){
			
			 if(ip=="" || ip==null){
				 alert("please select IP Set,Water Source");
				 return false;
			 }
		 }
	 }
	 
	 if(className7=='active' || className7=='complete active'){
		 
		 var x1 = document.getElementById('checkbox1').checked;
		 var x2 = document.getElementById('checkbox2').checked;
		 var x3 = document.getElementById('checkbox3').checked;
		 var x4 = document.getElementById('checkbox4').checked;
		 var x5 = document.getElementById('checkbox5').checked;
		 var x6 = document.getElementById('checkbox6').checked;
		 var x7 = document.getElementById('checkbox7').checked;
		 var x8 = document.getElementById('checkbox8').checked;
		 var x9 = document.getElementById('checkbox9').checked;
		 var x10 = document.getElementById('checkbox10').checked;
		 var x11 = document.getElementById('checkbox11').checked;
		 var x12 = document.getElementById('checkbox12').checked;
		 var y1=document.getElementById("imgInp1").value;
		 var y2=document.getElementById("imgInp2").value;
		 var y3=document.getElementById("imgInp3").value;
		 var y4=document.getElementById("imgInp4").value;
		 var y5=document.getElementById("imgInp5").value;
		 var y6=document.getElementById("imgInp6").value;
		 var y7=document.getElementById("imgInp7").value;
		 var y8=document.getElementById("imgInp8").value;
		 var y9=document.getElementById("imgInp9").value;
		 var y10=document.getElementById("imgInp10").value;
		 var y11=document.getElementById("imgInp11").value;
		 var y12=document.getElementById("imgInp12").value;
		 
		 var otherDocValue=document.getElementById("otherDocumentId").value;
		
		 if(otherDocValue!="" && (document.getElementById("otherDoc").value=="" || document.getElementById("otherDoc").value=="null" || document.getElementById("otherDoc").value==null)){
			 alert("Please enter document name for the uploaded Document");
			 return false;
		 }
		 
		 if(x1){
			 
			 if(y1=="" || y1==null){
				 alert("please upload Proof of Ownership document");
				 return false;
			 }
		 }
		  if(x2){
			
			 if(y8=="" || y8==null){
				 alert("please upload  Resident Address proof document");
				 return false;
			 }
		 }
		  if(x3){
			 if(y3=="" || y3==null){
				 alert("please upload Wiring Diagram document");
				 return false;
			 }
		 }
		  if(x4){
			 if(y4=="" || y5==null){
				 alert("please upload License from local authority document");
				 return false;
			 } 
		 }
		  if(x5){
			 if(y5=="" || y5==null){
				 alert("please upload Registered partnership deed document");
				 return false;
			 }
		 }
		  if(x6){
			 if(y6=="" || y6==null){
				 alert("please upload Articles of association and Certificate document");
				 return false;
			 }
		 }
		   if(x7){
			 if(y7=="" || y7==null){
				 alert("please upload Indemnity Bond document");
				 return false;
			 }
		 }
		  if(x8){
			 if(y2=="" || y2==null){
				 alert("please upload Building Plan document");
				 return false;
			 }
		 }
		  if(x9){
			 if(y9=="" || y9==null){
				 alert("please upload Undertaking not to engage Child Labour document");
				 return false;
			 }
		 }
		  if(x10){
			 if(y10=="" || y10==null){
				 alert("please upload Location sketch document");
				 return false;
			 }
		 }
		  if(x11){
			 if(y11=="" || y11==null){
				 alert("please upload Agreement to install solar Water heater document");
				 return false;
			 }
		 }
		  if(x12){
			 if(y12=="" || y12==null){
				 alert("please upload Power supply agreement document");
				 return false;
			 }
		 }
	 }
 });
		</script>
<style>
.input-group-addon{
height: 18px;
 font-size: 13px;
}
.form-control input-lg {
height: 37px; 
font-size: 13px;
}
.bootstrapWizard li {
    display: block;
    float: left;
    padding-left: 0;
    text-align: center;
    width: 12.1%;
}

#blah {
       border: 5px solid #ccc;
       width: 80px;
       height: 80px;
   }
    
 #scanImage1 {
       border: 1px solid #ccc;
       width: 240px;
       height: 50px;
   }
  .fa-plus-square{
        color: red;
        opacity: 0.8;
    }
    .saveTab{
    font-size: 15px;
    color: #669999;
    }
</style>
