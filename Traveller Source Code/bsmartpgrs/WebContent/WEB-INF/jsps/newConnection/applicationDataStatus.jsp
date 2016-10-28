<%@include file="/common/taglibs.jsp"%>	
			 
	
			

<c:url value="/newConnection/applicationStatusData" var="readUrl" />
<div id="content">

				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark">
							<i class="fa fa-table fa-fw "></i> 
							List Of Applications 
							
						</h1>
					</div>
					<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
						<ul id="sparks" class="">
							<li class="sparks-info">
								<h4 id="redirectData"><a href="javascript:void(0);"> Received</a> <span class="txt-color-blue">500</span></h4>
							</li>
							<li class="sparks-info">
								<h4 id="redirectData2"> In Progress <span class="txt-color-purple"><i  data-rel="bootstrap-tooltip" title="Increased"></i>&nbsp;50</span></h4>
								
							</li>
							<li class="sparks-info">
								<h4 id="redirectData3"> Completed  <span class="txt-color-greenDark">&nbsp; 	400</span></h4>
								
							</li>
							<li class="sparks-info">
								<h4 id="redirectData4"> Pending  <span class="txt-color-greenDark">&nbsp;50</span></h4>
								
							</li>
						</ul>
					</div>
				</div>
				</div>
				
			<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
				
				

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<form id="order-form" class="smart-form" novalidate="novalidate">
							<fieldset>
								

								<div class="row">
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="fromdate" id="fromdate" placeholder="From date">
										</label>
									</section>
									<section class="col col-3">
										<label class="input"> <i class="icon-append fa fa-calendar"></i>
											<input type="text" name="todate" id="todate" placeholder="To date">
										</label>
									</section>
									<section class="col col-3">
										<label class="select">
											<select name="interested">
												<option value="0" selected="" disabled="">Status</option>
												<option value="1">Waiting</option>
												<option value="1">Accepted</option>
												<option value="2">Rejected</option>
												<option value="2">On Hold</option>
												
											</select> <i></i> </label>
									</section>
									
									<section class="col col-3">
										<label class="button">
										<button type="submit" class="btn btn-primary">
									Search
								</button>
											 <i></i> </label>
									</section>
									
								</div>

								
							</fieldset>
							<!-- <footer>
								<button type="submit" class="btn btn-primary">
									Search
								</button>
							</footer> -->
						</form>

					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div> 	
		

<div id="loading" ></div>
	<kendo:grid name="grid" pageable="true"
		filterable="true" groupable="true" selectable="true" scrollable="true" reorderable="true" resizable="true" >
		<kendo:grid-pageable pageSizes="true" buttonCount="5"  pageSize="20" input="true" numeric="true" refresh="true" ></kendo:grid-pageable>
		<kendo:grid-filterable extra="false">
		
		 <kendo:grid-filterable-operators>
		  	<kendo:grid-filterable-operators-string eq="Is equal to"/>
		 </kendo:grid-filterable-operators>
			
		</kendo:grid-filterable>


    
    	<kendo:grid-editable mode="popup" confirmation="Are you sure you want to remove this item?"/>
      <kendo:grid-toolbarTemplate>
      <div class="toolbar">
      
	        	<a class='k-button' href='\\#' onclick="clearFilterOwner()"><span class='k-icon k-i-funnel-clear'></span> Clear Filter</a>
	        	
	        	<a class="k-button k-button-icontext k-grid-ownerTemplatesDetailsExport" href="#">
                <span class=" "></span>
                 Export To Excel
                </a>
      
      
      </div>
      
      
      </kendo:grid-toolbarTemplate>
        <kendo:grid-columns>
        <kendo:grid-column title="&nbsp;" width="80px">
			<kendo:grid-column-command>
				<kendo:grid-column-commandItem name="View" click="rdirect" />
			</kendo:grid-column-command>
		</kendo:grid-column>
           <kendo:grid-column title="Application ID" field="applicationId" width="110px" >
           </kendo:grid-column>
             <kendo:grid-column title="Reg. Date" filterable="false" field="date" width="130px">
        </kendo:grid-column>
        
          <kendo:grid-column title="Name" filterable="false" field="name" width="130px">
        </kendo:grid-column>
        <kendo:grid-column title="City" filterable="false" field="city" width="130px">
        </kendo:grid-column>
                <!-- 3rd report -->
                <kendo:grid-column title="Load(Kw)" field="loadkw" filterable="false" width="110px"/>
                <kendo:grid-column title="Load(Hp)" field="loadhp" filterable="false" width="110px"/>
                <kendo:grid-column title="Load(KVA)" field="loadkva" filterable="false" width="110px"/>
                <kendo:grid-column title="Nature Of Inst." field="natureOfInst" filterable="false" width="110px"/>
                <kendo:grid-column title="Tariff" field="tariff" filterable="false" width="110px"/>
                <kendo:grid-column title="Locality" field="locality" filterable="false" width="110px"/>
				
        </kendo:grid-columns>
        <kendo:dataSource  requestStart="requestStart" requestEnd="requestEnd">
            <kendo:dataSource-transport>
            
               <%--  <kendo:dataSource-transport-read url="${readUr}/${data}" dataType="json" type="POST" contentType="application/json"/> --%>
             <kendo:dataSource-transport-read url="${readUrl}" dataType="json" type="POST" contentType="application/json" />
            </kendo:dataSource-transport>
            <kendo:dataSource-schema parse="parse">
                <kendo:dataSource-schema-model id="dept_Id">
                 <kendo:dataSource-schema-model-fields>
                        <kendo:dataSource-schema-model-field name="dept_Id" editable="false">
                        </kendo:dataSource-schema-model-field>
                    
                   
                        <kendo:dataSource-schema-model-field name="dept_Name" type="string"/>
                        	
                        
                        <kendo:dataSource-schema-model-field name="dept_Desc" type="string">
                        </kendo:dataSource-schema-model-field>
                         
                         <kendo:dataSource-schema-model-field name="dept_Status"   type="string"/>
                         <kendo:dataSource-schema-model-field name="jan" type="number" defaultValue="0"/>
                      
                   
                        
                    </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
            </kendo:dataSource-schema>
        </kendo:dataSource>
    </kendo:grid>
    <div id="alertsBox" title="Alert"></div>   
    <script type="text/javascript">
    
    function requestStart()
    {
    	
    	kendo.ui.progress($("#loading"), true);

    	
    }
    
    function requestEnd()
    {
    	kendo.ui.progress($("#loading"), false);
    	
    }
    
    function parse (response) {
        $.each(response, function (idx, elem) {   
        	   if(elem.vStd=== null){
        		   elem.vStd = "";
        	   }else{
        		   elem.vStd = kendo.parseDate(new Date(elem.vStd),'dd/MM/yyyy');
        	   }
        	   
        	   if(elem.vEnd=== null){
        		   elem.vEnd = "";
        	   }else{
        		   elem.vEnd = kendo.parseDate(new Date(elem.vEnd),'dd/MM/yyyy');
        	   }
        	   if(elem.visitorInDate=== null){
        		   elem.visitorInDate = "";
        	   }else{
        		   elem.visitorInDate = kendo.parseDate(new Date(elem.visitorInDate),'dd/MM/yyyy');
        	   }
           });
           
           return response;
    }

   

	 function clearFilterOwner()
	 {
	 	    //custom actions
	 	    $("form.k-filter-menu button[type='reset']").slice().trigger("click");
	 		var grid = $("#grid").data("kendoGrid");
	 		grid.dataSource.read();
	 		grid.refresh();
	 	}
	 
		    	
            $(document).ready(function(){
            	
            	
            	$('#todate').datepicker({
        			dateFormat : 'dd/mm/yy',
        			prevText : '<i class="fa fa-chevron-left"></i>',
        			nextText : '<i class="fa fa-chevron-right"></i>',
        			
        		});
        		
        		$('#fromdate').datepicker({
        			dateFormat : 'dd/mm/yy',
        			prevText : '<i class="fa fa-chevron-left"></i>',
        			nextText : '<i class="fa fa-chevron-right"></i>',
        			
        		});
            	
            	$("#menu1thirdlevel").remove();
            	$("#menu2thirdlevel").remove();
            	$("#menu3thirdlevel").remove();
            	$("#menu4thirdlevel").remove();
            	$("#menu5thirdlevel").remove();
            	$("#menu6thirdlevel").remove();
            	$("#menu7thirdlevel").remove();
            	$("#menu8thirdlevel").remove();
            	$("#menu9thirdlevel").remove();
            	$("#menu10thirdlevel").remove();
            	$("#menu12thirdlevel").remove();
            	$("#menu13thirdlevel").remove();
            	$("#menu14thirdlevel").remove();
            	$("#menu15thirdlevel").remove();
            	$("#menu16thirdlevel").remove();
            	$("#menu17thirdlevel").remove();
            	$("#menu18thirdlevel").remove();
            	$("#menu19thirdlevel").remove();
            	$("#menu20thirdlevel").remove();
            	$("#menu21thirdlevel").remove();
            	$("#menu22thirdlevel").remove();
            	var grid = $("#grid").data("kendoGrid");
            	
            	
					if ("${data}" == 1)//primary contacts
						{
							/* ========== 3rd report ========== */
							grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");

							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							grid.hideColumn("prevBilldgUnit");
							grid.hideColumn("presentBilldgUnit");
							
							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	 	 
							/* ========== 6th report ========== */
							grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss");
           	 	  
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");
							
						}
						if ("${data}" == 2)//primary contacts
						{
							/* grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december"); */

							/* ========== 3rd report ========== */
							grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");

							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							grid.hideColumn("prevBilldgUnit");
							grid.hideColumn("presentBilldgUnit");
							
							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	 	 
							/* ========== 6th report ========== */
							grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss");
           	 	  
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");

						}
						if ("${data}" == 3)//primary contacts
						{
							grid.hideColumn("serviceType");
							grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december");
							
							
							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							grid.hideColumn("prevBilldgUnit");
							grid.hideColumn("presentBilldgUnit");
							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	
							/* ========== 6th report ========== */
							grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss");
           	 	  
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");

						}
						if ("${data}" == 4)//primary contacts
						{
							grid.hideColumn("serviceType");
							grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december");

							/* ========== 3rd report ========== */
							grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");

							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	 	 
							/* ========== 6th report ========== */
							grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss");
           	 	  
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");

						}
						if ("${data}" == 5)//primary contacts
						{
							grid.hideColumn("serviceType");
							grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december");

							/* ========== 3rd report ========== */
							//grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");

							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							grid.hideColumn("prevBilldgUnit");
							grid.hideColumn("presentBilldgUnit");
					
							/* ========== 6th report ========== */
							grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss");
           	 	  
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");

						}
						if ("${data}" == 6)//primary contacts
						{
							grid.hideColumn("serviceType");
							grid.hideColumn("name");
							grid.hideColumn("blockName");
							grid.hideColumn("flatNo");
							grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december");

							/* ========== 3rd report ========== */
							grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");
							
							
							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							
							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	 	 
							/* ========== 7th report ========== */
							grid.hideColumn("billedAmount");
							grid.hideColumn("collectionAmount");
							grid.hideColumn("collectionLoss");
							grid.hideColumn("atcLoss");

						}
						if ("${data}" == 7)//primary contacts
						{

							/* ========== 3rd report ========== */
							grid.hideColumn("serviceType");
							grid.hideColumn("month");
							grid.hideColumn("head1");
							grid.hideColumn("head2");
							grid.hideColumn("head3");
							grid.hideColumn("head4");
							grid.hideColumn("head5");

							/* ========== 1st report ========== */
							grid.hideColumn("name");
							grid.hideColumn("blockName");
							grid.hideColumn("flatNo");
							grid.hideColumn("jan");
							grid.hideColumn("feb");
							grid.hideColumn("march");
							grid.hideColumn("april");
							grid.hideColumn("may");
							grid.hideColumn("june");
							grid.hideColumn("july");
							grid.hideColumn("august");
							grid.hideColumn("september");
							grid.hideColumn("october");
							grid.hideColumn("november");
							grid.hideColumn("december");
							
							/* ========== 4th report ========== */
							grid.hideColumn("meterSrNo");
							grid.hideColumn("prevBillUnit");
							grid.hideColumn("presentBillUnit");
							grid.hideColumn("prevBillDate");
							grid.hideColumn("presentBillDate");
							
							/* ========== 5th report ========== */
							grid.hideColumn("monthBilledAmount");
							//grid.hideColumn("collectionAmount");
							grid.hideColumn("interest");
							grid.hideColumn("arrearsTax");
							grid.hideColumn("vat");
           	 	 
							/* ========== 6th report ========== */
							/* grid.hideColumn("tower");
							grid.hideColumn("totalFlats");
							grid.hideColumn("mainMeterReading");
							grid.hideColumn("otherMeterReading");
							grid.hideColumn("unitLoss");
							grid.hideColumn("loss"); */
           	 	  
						}

					})

					
				
		function  rdirect(){
			window.location.href="./finalForm";
		}
		
					
				</script>     
			