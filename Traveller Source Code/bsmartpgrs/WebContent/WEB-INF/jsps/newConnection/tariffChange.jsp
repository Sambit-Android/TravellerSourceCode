<%@include file="/common/taglibs.jsp"%>
<script src="./resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="./resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="./resources/js/plugin/jquery-form/jquery-form.min.js"></script> 

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<section id="widget-grid" class="">
<div id="content">

<div class="row">
			<c:if test="${not empty tcsuccess}">
						<script>
							var result = "${tcsuccess}";
							alert(result);
						</script>
			<c:remove var="tcsuccess" scope="session" />
	   		</c:if>
	   
			<article class="col-sm-12 col-md-12">
			<div class="jarviswidget jarviswidget-sortable jarviswidget-color-darken" id="wid-id-21" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
				<header>
					<span class="widget-icon"> <i class="fa fa-pencil-square"></i> </span>
					<h2>Tariff Change</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<form:form action="" id="tariff_change_id" method="post" commandName="application" class="smart-form"
									modelAttribute="tariffChange" enctype="multipart/form-data" novalidate="novalidate" autocomplete="off">
							<fieldset>
								
							<div class="row">
								<section class="col col-3">
									<label class="label">RR Number&nbsp;<font color="red">*</font></label> <label
										class="select"> 
										
									<select id="rrnum" name="rrnum" class="form-control" onchange="selectRRNumber(this.value);">
										<option value="" selected="" disabled="">Select</option>
										<c:forEach items="${rrNoList}" var="section">
											<option value="${section.sitecode}-${section.rrnum}">${section.rrnum}</option>
										</c:forEach>
									</select>
									
									
									<i></i></label>
								</section>
								
								<section  class="col col-3">
									<label class="label">Consumer&nbsp;Name&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="ConsumerName" name="ConsumerName" id="ConsumerName" placeholder="Consumer Name"></form:input></label>
								</section>	
								
								<section class="col col-3">
									<label class="label">Email Id</label>
									<label class="input"> <i class="icon-prepend fa fa-envelope-o"></i>
										<form:input type="email" class="input-md" path="email" name="email" placeholder="E-mail"></form:input>
									</label>
								</section>
								
								<section class="col col-3">
									<label class="label">Contact No&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-prepend fa fa-mobile"></i>
										<form:input type="text" class="input-md" path="mobileno" name="mobileno" placeholder="Contact No"></form:input>
									</label>
								</section>
								
							</div>
							
							<div class="row">
							
								<section class="col col-3">
									<label class="label">Reading Date&nbsp;<font color="red">*</font></label>
									<label class="input"> <i class="icon-append fa fa-calendar"></i>
										<form:input type="text"	name="readingdate" placeholder="Reading Date" path="readingdate" id="readingdate" autocomplete="off"></form:input>
									</label>
								</section>
								
								<section  class="col col-3">
									<label class="label">Present Reading&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="pmr" name="pmr" id="pmr" placeholder="Present Reading"></form:input></label>
								</section>
								
								<section class="col col-6">
								<label class="textarea"> Consumer Address									
									<form:textarea type="text"	name="address" placeholder="Consumer Address" path="address" id="address" rows="1"></form:textarea>
								</label>
							   </section>
								
							</div>
							
							 
							
							 <div class="row">										
						
								<section  class="col col-3">		
									<label class="label">Existing Nature Of Installation&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="existingNatOfIns" name="existingNatOfIns" id="existingNatOfIns" placeholder="Existing Nature Of Installation"></form:input></label>
								</section>
								
								<section  class="col col-3">		
									<label class="label">Existing Tariff&nbsp;<font color="red">*</font></label>
									<label class="input"><form:input class="input-md" type="text" path="existingtariff" name="existingtariff" id="existingtariff" placeholder="Existing Tariff"></form:input></label>
								</section>
						
								<section class="col col-3">
									<label class="label">Proposed Installation&nbsp;<font color="red">*</font></label> <label
										class="select"> <span> <i
											class="fa fa-chevron-down"></i>
									  </span> 
									     <select class="form-control" id="proposedIns" name="proposedIns">
											<option value="" selected disabled>Select</option>
											<option value="CMC And Urban Local Bodies">CMC And Urban Local Bodies</option>
											<option value="Village Panchayat">Village Panchayat</option>
	
										</select>
	
	
									</label>
								</section>
								
								<section class="col col-3" >
									<label class="label">Nature Of Installation&nbsp;<font color="red">*</font></label> <label
										class="select"> <span> <i
											class="fa fa-chevron-down"></i>
									  </span> 
									     <form:select class="form-control" id="newnatofins" path="newnatofins">
											<option value="" selected="" disabled="">Select Installation</option>
										</form:select>
	
	
									</label>
								</section>
								
								</div>
								
								<section>
									<label class="textarea"> Reason to Change <font color="red">*</font>									
										<form:textarea type="text"	name="reasonforchange" placeholder="Reason to Change" path="reasonforchange" id="reasonforchange" rows="1"></form:textarea>
									</label>
								</section>
								
								
								<section> 
									<label class="label"><font size="3" color="red">Documents Required</font></label>
							   </section>
								
								<div class="row">
								<section  class="col col-3">		
									<label class="label">a.&nbsp;Sale Deed</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="saledeed" name="saledeed">

																</div>
															</div>
														</div>
													</div>
								</section>
								<section  class="col col-3">		
									<label class="label">b.&nbsp;Agreement</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="agreement" name="agreement">

																</div>
															</div>
														</div>
													</div>
								</section>
								</div>
								<div class="row">
								<section  class="col col-3">		
									<label class="label">c.&nbsp;Copy of the latest Bill</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="copy" name="copy">

																</div>
															</div>
														</div>
													</div>
								</section>
								<section  class="col col-3">		
									<label class="label">d.&nbsp;Copy of the latest paid receipt</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="copypaidrec" name="copypaidrec">

																</div>
															</div>
														</div>
													</div>
								</section>
								</div>
								<div class="row">
								<section  class="col col-3">		
									<label class="label">e.&nbsp;Consent latter to transfer<br>&nbsp;&nbsp;&nbsp;the deposit.</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="consentlatter" name="consentlatter">

																</div>
															</div>
														</div>
													</div>
								</section>
							 
							  <section  class="col col-3">		
									<label class="label">f.&nbsp;Any Other document.</label>
								</section>
								
								<section class="col col-3">
								<div>

														<div class="col-sm-2">
															<div class="form-group">
																<div class="input-group">
																	<input type="file" id="otherdoc" name="otherdoc">

																</div>
															</div>
														</div>
													</div>
								</section>
								</div>
								
								
								
								<div class="row" >



													<div id="div2a"	style="display: none;" >
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															HANDLOOM WEAVING SILK REARING , REELING , ARTISANS USING MOTORS UPTO 200 WATTS
															CONSULTANCY,ENGINEERING,ARCHITECTURE,MEDICINE,ASTROLOGY,LEGAL
															MATTERS,INCOME TAX, CHARTERED ACCOUNTENTS,JOB
															TYPING,TAILORINT,POST OFFICE,FOLDSMITHY,CHOWKI
															REARING,PAYING FUESTS,HOMESTY GUESTS,PERSONAL COMPUTERS,
															DHOBIS,HAND PREPARED PRINTING PRESS,BEAUTY PARLOURS,WATER
															SUPPLY ,HOSPITALS ,DISPENSARIES,HEALTH CENTERS RUN BY
															STATE/CENTRAL GOVT. LOCAL BODIES HOUSES, SCHOOLS AND
															HOSTELS MENT FOR HANDICAPPED AGED DESTITUTE AND ORPHANS
															REHABITATATION CENTERS RUN BY CHARITABLE INSTITUTIONS,
															AIDS AND DRUG ADDICTS REHABITATION CENTERS,RAILWAY STAF
															QUARTERS WITH SINGLE METER, FIRE SERVICE
															STATIONS,TEMPLES,MOSQUES,CHURCHES,GURUDWARS,ASHRAMS,MUTTS AND
															RELIGIOUS/CHARITABLE INSTITUTIONS, HOSPITALS DISPENSARIES
															AND HEALTH CENTERS RUN BY CHARITABLE INSTITTIONS, JAIL AND PRISONS,SCHOOLS COLLEGES,EDUCATIONAL INSTITUTIONS RUN
															BY STATE/ CENTRAL GOVT/LOCAL BODIES, SEMINARIES,HOSTELS RUN BY THE GOVERNMANT,EDUCATIONAL INSTITUTIONS,CULTURAL
															SCIENTIFIC AND CHARITABLE INSTITUTIONS GUEST HOUSE,TRAVELERSBANGALOWS RUN IN GOVERNMENT BULDINGS OR BY
															STATE/CENTRAL GOVT, PUBLIC LIBRARIES,SILK REARING,MUSEUMS,INSTALLATIONS OF HISTOROCAL MENUMENTS OF
															ARCHEOLOGY DEPARTMENTS, PUBLIC RELEPHONE BOOTHS WITHOUT
															STD/ISD/FAX FACIILIRY RUN BY HANDICAPPED PEOPLE, SULABHA
															/NIRMAL COUCHALAYAS,VISWA SEDS HAVING LIGHTING LOADS
															ONLY. </label></font>

													</div>


													<div id="div2b" style="display: none; ">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															PRIVATE PROFESSIONAL AND OTHER PRIVATE EDUCATIONAL
															INSTITUTIONS INCLUDING AIDED OR NON AIADED INSTATUTIONS
															</label>
															</font>

													</div>


													<div id="div3" style="display: none;">
													
															<label class="col-md-2 control-label">
															<font style="font-size: 14px; color: blue;"><b>Applicable To :</b></font></label> <br>
															<font style="font-size: 12px; ">
															<label class="col-md-12 control-label">
															
															CLINICS CIAGNOSTIC CENTERS,X RAY
															UNITS,SHOPS,STORES,HOTELS,RESTAURENTS,BOARDING,LOADGING
															HOMES, BARS,PRIVATE GEST,MESS,CLUBS,CALYAN
															MANTAPAS/CHOULTRY,PERMANENT CINEMAS,SEMI PERMANENT
															CINEMAS,THEATERS,PETROL BUNKS,PETROL,DISEL AND OIL
															STORAGE PLANTS ,SERVICE STATIONS,GARAGES,BANKS,TELEPHONE
															EXCHANGES,TV STATIONS, MICROWAVE STATIONS,ALLINDIA
															RADIO,DISH ANTENNA,PUBLIC TELEPHONE BOOTHS STD,ISD,FAX,
															COMMUNICATION CENTERS,STUD FARMS,RACE COURSE,ICE CREAM
															PARLOURS,COUMPUTER CENTERS, PHOTO STUDIO,COLOUR
															LABAORATORY,XEROX COPIERS,RAILWAY INSTALLATIONS EXECPTING
															WAILWAY WORKSHOP, KSRTC BUS STATIONS EXECPTING
															WORKSHOP,ALL OFFICES,POLICE STATIONS,COMMERCIAL
															COMPLEXEX, LIFTS OF COMMERCILA COMPLEXEX,BATTERY CHARGING
															UNITS, TYRE VULCANZING CENTERS,POST OFFICES,BAKERY
															SHOPS,BEAUTY PARLOURS,STADIUMS,OTHER THAN THOSE MAINTENED
															BY GOVERNMENT AND LOCAL BODIES, WATER SUPPLY PUMPS AND
															STREET LIGHTS NOT COVERD UNDER LT6 CYBER CAFES , INTERNET
															SURFING CAFES,CALL CENTERS,IT BASED MEDICAL TRANSACTION
															CENTERS, PRIVATE HOSTELS NOT COVERED UNDER LT2 (a),PAYING
															GOUESTS ACCOMMODATION PROVIDED IN AN
															INDEPENDENT/EXCTUSIVE PREMISES. </label>
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
															
															HEATING AND MOVTIVE POWER (INCLUDING LIGHTING) INDUSTRIAL
															UNITS, WORKSHOPS, POULTRY FARMS, SUGARCANE CRUSHERS,
															COFFEE PULPING, CARDAMON DRYING, MASHROOM RAISING
															INSTALLATIONS, FLOUR, HULLER, RICE MILLS, WET GRINDERS,
															MILK DAIRIES, IRONING, DRY CLEANERS, LANDRIES HAVING
															WASHING, DRYING, IRONING, EXCLUSIVE TAILORING SHOP, BULK
															ICE CREAM AND ICE MANUFACTURING UNITS, COFFEE ROASTING,
															AND GARINDING WORKS, COLD STROAGE PLANTS, BAKERY PROCUCTS
															MANUFACTURING, KSRTC WORKSHOPS/DEPOTS, RALWAY WORKSHOPS,
															DRUG MANUFACTURING UNITS AND TESTING LABORATORIES,
															PRINTING PRESSES, GARMENT MANUFACTURING UNITS, BULK
															VENDING BOOTHS, SWIMMING POOLS OF LOCAL BODIES, TYRE
															RETREADING UNITS, STONE CRUSHERS, STONE CUTTING , CHILLY
															GRINDERS, PHOVA MILLS, PULVERIZING MILLS, DECORTICATORS,
															IRON AND RED OXIDE CRUSHING UNITS, CREMATORIUMS,
															HATCHERIES, RISSUE CULTURE, SAW MILLS, TOY WOOD
															INDUSTRIES, VISWA SHEDS WITH MIXED LOD SANCTIONED UNDER
															VISWA SCHEME, CINEMATIC ACTIVITIES SUCH AS PROCESSING
															PRINTING DEVELOPING,RECORDING THEATRES DUBBING THEATRES
															AND FILM STUDIOS, AGARBATHI MANUFACTURING UNITS, WATER
															SUPPLY INSTALLATIONS OF KIADB AND INDUSTRIAL UNITS, GEM
															AND DIAMOND CUTTING UNITS, FLORICULTURE, GREEN HOUSE,
															BIOTECH LABS, HYBRID SEED PROCESSING UNITS, INFORMATION
															TECHONOLOGY INDUSTRIES ENGAGED IN DEVELOPMENT OF HARD
															WARE AND SOFTWARE, INFORMATION TECHONOLOGY (IT)ENABLED
															SERVICES START-UPS/ANIMATIONS/GAMING/COMPUTER GRAPHICS AS
															CIRTIFICATED BY IT AND BT DEPARTMANT OF GOK/FOI, SILLK
															FILATURE UNITS, AQUA CULTURE, PRAWN CULTURE, BRICK
															MANUFACTURING UNITS, SILK COTTON COLOURING DYAING,
															STADIUMS MAINTEANED BY GOVT AND LOCAL BODIES, FIRE
															SERVICE STATIONS, GOLD/SILVER ARNAMENT MANUFACTURING
															UNITS, EFFIUENT TREATMENT PLANTS, DRAINAGE WATER
															TREATMENT PLANTS, LPG BOTTING PLANTS , PETROLEUM PIPELINE
															PROJECTS, PIGGERY FARMS, ANALYTICAL LAB FOR ANALYSIS OF
															ORE METALS, SATELLITE COMUNICATION CENTERS, MINERAL WATER
															PROCESSIN PLANTS, DRINKING WATER BOTTLING PLANTS , SODA
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
															
															TARIFF SIGNALS, SUBWAYS, WATER FOUNTAINS OF LOCAL BODIES,
															STREET LIGHTS OF RESIDENTIAL CAMPUS OF UNIVERSITIES,
															OTHER EDUCATIONAL INSTITUTIONS, HOUSING COLONIES APPROVED
															BY LOCALBODIES/DEVELOPMENT AUTHORITY, RELIGIOUS
															INSTITUTIONS, ORGANIZATIONS RUN ON CHARITABLE BASISIS,
															INDUSTRIAL AREA/ESTATE AND NOTIFIED AREAS, WATER SUPPLY
															INSTALLATIONS IN RESIDENTIAL LAYOUTS, STREET LIGHTS ALONG
															WITH SIGNAL LIGHTS ASSOCIATED LOAD OF GATEMEN, HUT
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
															
															INDUSTRIES, FACTORIES, WORKSHOPS, RESEARCH AND
															DEVELOPMENT CENTRES, INDUSTRIAL ESTATES, MILK DAIRIES,
															RICE MILLS, PHOVA MILLS, ROLLER FLOUR MILLS, NEWS PAPERS,
															PRINTING PRESS, RAILWAY WORKSHOPS , KSRTC WORK SHOPS,
															DEPOTS CREMATORIUMS, COLD STORAGES, ICE AND ICE CREAM MFG
															UNITS, SWIMMING POOLS OF LOCAL BODIES, WATER SUPPLY
															INSTALLATIONS OF KIADB AND OTHER INDUSTRIES, ALL DEFENCE
															ESTABLISHMENTS, HATCHERIES, POULTRY FARM, MUSEUM,
															FLORICULTURE, GREEN HOUSE, BIO TECHINICAL LABORATORY,
															HYBRID SEED PROCESSING UNITS, STONE CRUSHERS, STONE
															CUTTING , BAKERY PRODUCT MANUFACTURING UNITS, MYSORE
															PALACE ILLUMINATION, FILM STUDIOS, DUBBING
															THEATERS,PROCESSING,PRINTING PRESS,DEVELOPING AND
															RECORDING, THEATERS, TISSUE CULTURE, AQUA CULTURE, PRAWN
															CULTURE, INFORMATION TECHONOLOGY, INDUSTRIES ENGAGED IN
															DEVELOPMENT OF HARDWARE AND SOFTWARE INFORMATION
															TECHONOLOGY(IT) ENABLED SERVICES, START UP ANIMATION
															,GAMING, COMPUTER GARMENT MFG UNITS, TYRE RETERADING
															UNITS, NUCLEAR POWER PORHECTS, STADIUMS MAINTAINED BY
															GOVERNMENT AND LOCA BODIES, RAILWAY TRACTION, EFFLUENT
															TREATMENT PLANTS AND DRAINAGE WATER TREATMENT PLANT OWNED
															OTHER THAN BY LOCLA BODIES, LPG BOTTING PLANTS, PETROLEUM
															PIPELINE PROHETS, PIGGERY FARMS, ANALYTICAL LAB FOR
															ANALYSIS OF RE METALS, SAW MILLS, TOY/WOOD INDUSTRIES,
															SATELLITE COMMUNICATION CENTERS, MINERAL WATER PROCESSING
															PLANTS, DRINKING WATER BOTTILING PLANTS. </label>
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
							 
							 
							 </fieldset>
							
							

							<footer>
								<button type="submit" class="btn btn-primary" onclick="return checkData(0);">
									Submit
								</button>
								<!-- <button type="button" class="btn btn-default" onclick="window.history.back();">
									Back
								</button> -->
							</footer>
						</form:form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->

		</article>
		
		
		
		<article class="col-sm-12 ">
				
					<div class="jarviswidget"
						id="tariffchange111" data-widget-editbutton="false"  data-widget-custombutton="false">
						
						   <header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Registered Tariff Change Details</h2>
				
								</header>
								<div style='overflow: scroll;'>
				
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>
											<th>RR&nbsp;No.</th>
											<th>Consumer Name</th>
											<th>Contact No</th>
											<th>Existing Nature Of Ins </th>
											<th>Existing Tariff  </th>
											<th>New Nature Of Ins </th>
											<th>New Tariff </th>
											<th>Status</th>
											<th>Reading Date</th>
											<th>Present Reading</th>
											<th>Reason</th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="app" items="${tariffChangeList}">
											
											<tr>
												<td>${app.rrnum}</td>
												<td>${app.ConsumerName}</td>
												<td>${app.mobileno}</td>
												<td>${app.existingNatOfIns}</td>
												<td>${app.existingtariff}</td>
												<td>${app.newnatofins}</td>
												<td>${app.newtariff}</td>
												<td>${app.status}</td>
												<td><fmt:formatDate value="${app.readingdate}" pattern="dd-MM-yyyy" /></td>
												<td>${app.pmr}</td>
												<td>${app.reasonfortranfer}</td>
												
											</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>

					</div>
			</article> 
		
		
		</div>
		</div>
		</section>
		
<script>

function selectRRNumber(rrnumber){

	  var res = rrnumber.split("-");
	  var rrnumberval=res[1];
		
		
		$.ajax({
			url : "./NCMS/getConsumerDetails",
			type : "GET",
			dataType : "JSON",
			async : false,
			data : {
				rrnumberval : rrnumberval,
				sitecode : res[0]

			},
			success : function(response) {
				var data=response[0];
				
				$('#ConsumerName').val(data.name);
				if(data.email==0){
					$('#email').val();
				}else{
					$('#email').val(data.email);
				}
				$('#mobileno').val(data.telephoneno);
				$('#readingdate').val();
				$('#pmr').val();
				$('#address').val(data.address+","+data.villagename);
				$('#existingNatOfIns').val(data.tariffdescription);
				$('#existingtariff').val(data.tariffdescription);
				
				
			}
		});
		
	}
	

$('select[id*=newnatofins]').change(function() {
	
	var natureofins = $("#newnatofins").val();
	
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
		
		$("#div2a").show();
		
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
		$("#div2b").show();
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
		$("#div3").show();
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
		$("#div4a").show();
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
		$("#div4b").show();
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
		
		$("#div4c1").show();
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
		
		$("#div5").show();
		
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
		
		$("#div6a").show();
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
		$("#div6b").show();
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
		$("#div7").show();
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
		$("#divltbj").show();
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
	

});



$('select[id*=existingtariff]').change(function() {
	
	var existingTariff = $("#existingtariff").val();
	
	var x = existingTariff.indexOf("LT") > -1;
	var y = existingTariff.indexOf("HT") > -1;
	
	var locality = $("#proposedIns").val();
	
	
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
            $('#newnatofins').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#newnatofins').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#newnatofins').append(newOption);
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
	            $('#newnatofins').empty(newOption);
	            var defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("Select Installtion");
	            $('#newnatofins').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
					$('#newnatofins').append(newOption);
				}));
			}
		}); 
		  
		}  
	 
});


$('select[id*=proposedIns]').change(function() {
	
	var existingTariff = $("#existingtariff").val();
	
	var x = existingTariff.indexOf("LT") > -1;
	var y = existingTariff.indexOf("HT") > -1;
	
	var locality = $("#proposedIns").val();
	
	
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
            $('#newnatofins').empty(newOption);
            var defaultOption = $('<option>');
            defaultOption.attr('value',"").text("Select Installtion");
            $('#newnatofins').append(defaultOption);
			($.map(data, function(item) {					 
				var newOption = $('<option>'); 					
				newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
				$('#newnatofins').append(newOption);
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
	            $('#newnatofins').empty(newOption);
	            var defaultOption = $('<option>');
	            defaultOption.attr('value',0).text("Select Installtion");
	            $('#newnatofins').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.tariffnaturevalue).text(item.tariffnature);
					$('#newnatofins').append(newOption);
				}));
			}
		}); 
		  
		}  
	 
});



$('#readingdate').datepicker({
	dateFormat : 'dd/mm/yy',
	prevText : '<i class="fa fa-chevron-left"></i>',
	nextText : '<i class="fa fa-chevron-right"></i>',
	maxDate: new Date(),
		
});

function getDetailsByRRNumber(rrnumber){
	
	
	$.ajax({
		type : "GET",
		url : "./checkRRNumberExists",
		dataType : "json",
		data : {
			rrno : rrnumber,
		},
		success : function(response) {
			
			if(response!="NA"){
				$("#pConsumerName").val(response[5]);
				$("#pAddress").val(response[6]);
				
			}else{
				alert("RR Number does not exist");
				$("#rrnum").val("");
				$("#pConsumerName").val("");
				$("#pAddress").val("");
				
			}
		}
	});
	
	
}

var responsiveHelper_datatable_col_reorder = undefined;
var breakpointDefinition = {
	tablet : 1024,
	phone : 480
};

$('#datatable_fixed_column').dataTable({
	"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
			"t"+
			"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	"autoWidth" : true,
	"order": [],
	"preDrawCallback" : function() {
		// Initialize the responsive datatables helper once.
		if (!responsiveHelper_datatable_col_reorder) {
			responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
		}
	},
	"rowCallback" : function(nRow) {
		responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
	},
	"drawCallback" : function(oSettings) {
		responsiveHelper_datatable_col_reorder.respond();
	}			
});


$(document).ready(function() {

	pageSetUp();
	
	
	
			$.validator.addMethod("regex", function(value, element, regexpr) {
				if(value==""){
					
					return "notmandatory";
				}else{
				return regexpr.test(value);
				}
			}, "");
			
			$.validator.addMethod("dateFormat",
				    function(value, element) {
				        return value.match(/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/);
				    },
				    "Please enter a date in the format dd/mm/yyyy.");
	 
		$('#tariff_change_id')
		.validate(
				{
					// Rules for form validation
					rules : {
						rrnum : {
							required : true
						},
						ConsumerName : {
							required : true,
							maxlength : 50,
							regex : /^[a-zA-Z .]*$/
						},
						address : {
							maxlength : 500
						},
						
						email : {
							maxlength : 40
						},
						mobileno : {
							required : true,
							maxlength : 14,
							digits:true
						},
						readingdate : {
							required : true,
							dateFormat: true
						},
						reasonforchange : {
							required : true,
							maxlength:500
						},
						pmr : {
							required : true,
							maxlength : 10,
							regex : /^[0-9 .]*$/,
						},
						proposedIns : {
							required : true,
						
						},
						newnatofins : {
							required : true,
						
						},
						existingNatOfIns : {
							required : true,
						
						},
						existingtariff : {
							required : true,
						
						},
						
						
						
					},

					// Messages for form validation
					messages : {
						rrnum : {
							required : 'Please Select RRNumber'
						},
						proposedIns : {
							required : 'select proposed Installation'
						},
						newnatofins : {
							required : 'select nature of Installation'
						},
						ConsumerName : {
							required : 'Please enter present consumer name',
							regex:'Enter Valid Name',
						},
						address : {
							maxlength : 'Address maxlength 500 characters'
						},
						
						email : {
							maxlength : 'maxlength 40 characters'
						},
						mobileno : {
							required : 'Please enter contact number',
							digits:'Enter Numbers',
							maxlength:'maxlength is 14 digits'
							
						},
						readingdate: {
							required : 'Please select Reading date'
						},
						reasonforchange: {
							required : 'Enter Reason for Change',
							maxlength:'maxlength is 500 characters'
						},
						pmr: {
							required : 'Enter present reading',
							regex:'enter numbers only',
							maxlength:'maxlength 10 numbers only'
						},
						
						
					},

					// Do not change code below
					errorPlacement : function(error,
							element) {
						error.insertAfter(element
								.parent());
					}
				});



});
function checkData(param) {
	$("#tariff_change_id").attr("target", "");
	$("#tariff_change_id").attr("action", "./ncms/createTariffChange").submit();
}
function readScannedImage(input,id) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        //var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.pdf)$/;
        	reader.onload = function (e) {
                $('#'+id).attr('src', e.target.result);
            }
        	  reader.readAsDataURL(input.files[0]);
        } 
}
$("#premisesOwnedfile1").change(function(){
    readScannedImage(this,this.id);
});
$("#existOwnWishFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#partitionFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#successionFile1").change(function(){
    readScannedImage(this,this.id);
});
$("#tenentedFile1").change(function(){
    readScannedImage(this,this.id);
});
</script>		