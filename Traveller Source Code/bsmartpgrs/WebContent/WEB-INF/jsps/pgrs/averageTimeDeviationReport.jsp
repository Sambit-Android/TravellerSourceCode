<%@include file="/common/taglibs.jsp"%>

<script src="./resources/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="./resources/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="./resources/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

<script src="./resources/js/plugin/highchart/highchart.js"></script>
<script src="./resources/js/plugin/highchart/exporting.js"></script>

<div id="content">
	<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false" data-widget-custombutton="false">
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body no-padding">
				<form:form id="order-form" action="#" method="POST" commandName="helpdeskBean" modelAttribute="helpdeskBean" class="smart-form" novalidate="novalidate">
					<fieldset>
						<div class="row">
							<section class="col col-3">
								<label class="input"> <i class="icon-append fa fa-calendar"></i> <input name="docketStatus" id="docketStatus" style="width: 100%; height: 30px;" placeholder="Select Year">
								</label>
							</section>
							
							<section class="col col-3">
								<label class="select"> <form:select path="circleSiteCode" id="circleSiteCode" name="circleSiteCode">
										<option value="0">Select Circle</option>
										<c:forEach items="${circleList}" var="circles">
											<form:option value="${circles.circleSiteCode}">${circles.circleName}</form:option>
										</c:forEach>
									</form:select><i></i>
								</label>
							</section>
							<section class="col col-3">
								<label class="select"> <form:select
										path="divisionSiteCode" id="divisionSiteCode"
										name="divisionSiteCode">
										<option value="0" selected="" disabled="">Select
											Division</option>
									</form:select><i></i></label>
							</section>

							<section class="col col-3">
								<label class="select"> <form:select path="siteCode"
										name="siteCode" id="siteCode">
										<option value="0" selected="" disabled="">Select Sub
											Division</option>
									</form:select> <i></i></label>
							</section>
							<section class="col col-3">
								<label class="select"> <select name="docketSource"
									id="docketSource">
										<option value="0" selected="" disabled="">Select
											Source</option>
										<option value="Phone">Phone</option>
										<option value="Web">Web</option>
										<option value="Sms">Sms</option>
										<option value="Email">Email</option>
										<option value="Facebook">Facebook</option>
										<option value="Hand Written">Hand Written</option>
								</select> <i></i></label>
							</section>

							<section class="col col-3">
								<label class="select"> <form:select path="categoryId"
										id="categoryId" name="categoryId">
										<option value="0" selected="" disabled="">Select
											Category</option>
										<c:forEach items="${categoryList}" var="categories">
											<form:option value="${categories.categoryId}">${categories.categoryName}</form:option>
										</c:forEach>
									</form:select><i></i></label>
							</section>

							<section class="col col-3">
								<label class="select"> <form:select path="subCategoryId"
										name="subCategoryId" id="subCategoryId">
										<option value="0" selected="" disabled="">Select
											SubCategory</option>
									</form:select><i></i></label>
							</section>


							<section class="col col-3">
								<button type="submit" class="btn btn-primary"
									style="height: 40px; width: 150px;" onclick="return loadData()">Filter</button>
							</section>

						</div>


					</fieldset>

				</form:form>

			</div>


		</div>
		
	</div>
	<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>
</div>


<script>
function loadData(){
	dochketStatus=$('#docketStatus').val();
	circleSiteCode=$('#circleSiteCode').val();
	divisionSiteCode=$('#divisionSiteCode').val();
	siteCode=$('#siteCode').val();
	docketSource=$('#docketSource').val();
	categoryId=$('#categoryId').val();
	subCategoryId=$('#subCategoryId').val();
	
	 $.ajax({
			url : "./reports/resolvedTrendComplaints",
			type : "GET",
			dataType : "JSON",
			async : false,
		data:{
			year:dochketStatus,
			circleSiteCode:circleSiteCode,
			divisionSiteCode:divisionSiteCode,
			siteCode:siteCode,
			docketSource:docketSource,
			categoryId:categoryId,
			subCategoryId:subCategoryId,
		},
		success : function(response) {
			Highcharts.setOptions({
		        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
		    });
			var title="";
				title="("+response[3]+")";
			$('#container').highcharts({
		        title: {
		            text: 'Trend Analysis Of Resolved Complaints '+title
		        },
		        xAxis: {
		        	categories: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		            type: 'category',
		            labels: {
		                rotation: -45,
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                }
		            }
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: 'Resolution Rate(%)'
		            }
		        },
		        plotOptions: {
		            series: {
		                dataLabels:{
		                    enabled:true,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333',
		                    formatter:function(){
		                        if(this.y > 0)
		                            return this.y;
		                    }
		                }
		            },
		        },
		        legend: {
		            align: 'right',
		            verticalAlign: 'top',
		            layout: 'vertical',
		            x: 0,
		            y: 100
		        },
		        tooltip: {
		        	formatter: function(){
		            	 var index = this.point.x;
		                    var comment = response[0][index];
		                    var comment1 = response[1][index];
		                    return 'Number of complaints resolved:'+comment+'<br>Number of complaints registerd:'+comment1;	
		            }
		        },
		        series: [{
		            name: 'Column Estimation',
		            type: 'column',
		            data:response[2],
		            dataLabels: {
		                enabled: true,
		                formatter:function(){
	                        if(this.y > 0)
	                            return this.y+' %';
	                    },
		                //rotation: -90,
		                color: '#FFFFFF',
		                align: 'right',
		                y: 10, // 10 pixels down from the top
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                },
		                
		            }
		        } , {
		            name: 'Linear Estimation',
		            type: 'spline',
		            data:response[2],
		            dataLabels: {
		                enabled: true,
		                formatter:function(){
	                        if(this.y > 0)
	                            return this.y+' %';
	                    },
		                //rotation: -90,
		                color: '#FFFFFF',
		                align: 'right',
		                y: 10, // 10 pixels down from the top
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                },
		                
		            }
		            
		            
		            
		        }]
		    });
		},

	});
	return false;
}
$(document).ready(function() {
	 $.ajax({
			url : "./reports/resolvedTrendComplaints",
			type : "GET",
			dataType : "JSON",
			async : false,
		success : function(response) {
			Highcharts.setOptions({
		        colors: ['#2F4F4F', '#FF9655', '#006400', '#FFF263','#00008B', '#6AF9C4']
		    });
			$('#container').highcharts({
		        title: {
		            text: 'Trend Analysis Of Resolved Complaints '
		        },
		        xAxis: {
		        	categories: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		            type: 'category',
		            labels: {
		                rotation: -45,
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                }
		            }
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: 'Resolution Rate(%)'
		            }
		        },
		        plotOptions: {
		            series: {
		                dataLabels:{
		                    enabled:true,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333',
		                    formatter:function(){
		                        if(this.y > 0)
		                            return this.y;
		                    }
		                }
		            },
		        },
		        legend: {
		            align: 'right',
		            verticalAlign: 'top',
		            layout: 'vertical',
		            x: 0,
		            y: 100
		        },
		        tooltip: {
		        	formatter: function(){
		            	 var index = this.point.x;
		                    var comment = response[0][index];
		                    var comment1 = response[1][index];
		                    return 'Number of complaints resolved:'+comment+'<br>Number of complaints registerd:'+comment1;	
		            }
		        },
		        series: [{
		            name: 'Column Estimation',
		            type: 'column',
		            data:response[2],
		            dataLabels: {
		                enabled: true,
		                formatter:function(){
	                        if(this.y > 0)
	                            return this.y+' %';
	                    },
		                //rotation: -90,
		                color: '#FFFFFF',
		                align: 'right',
		                y: 10, // 10 pixels down from the top
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                },
		                
		            }
		        } , {
		            name: 'Linear Estimation',
		            type: 'spline',
		            data:response[2],
		            dataLabels: {
		                enabled: true,
		                formatter:function(){
	                        if(this.y > 0)
	                            return this.y+' %';
	                    },
		                //rotation: -90,
		                color: '#FFFFFF',
		                align: 'right',
		                y: 10, // 10 pixels down from the top
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                },
		                
		            }
		            
		            
		            
		        }]
		    });
		},

	});
	
	$("#fromdate").keypress(function(event) {event.preventDefault();});
	$('select[id*=divisionSiteCode]').change(function() {
		var divisionSiteCode = $("#divisionSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubDivisions/" + divisionSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#siteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Division");
                $('#siteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.subDivisionName);
					$('#siteCode').append(newOption);
				}));
			}
		});
	});
	$('select[id*=circleSiteCode]').change(function() {
		var circleSiteCode = $("#circleSiteCode").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllDivisions/" + circleSiteCode,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#divisionSiteCode').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Division");
                $('#divisionSiteCode').append(defaultOption);
				($.map(data, function(item) {					 
					var newOption = $('<option>'); 					
					newOption.attr('value', item.siteCode).text(item.divisionName);
					$('#divisionSiteCode').append(newOption);
				}));
			}
		});
	});
	$('select[id*=categoryId]').change(function() {
		var categoryId = $("#categoryId").val();
		$.ajax({
			type : "POST",
			url : "./helpDesk/getAllSubCategories/" + categoryId,
			dataType : "json",
			success : function(data) {
				var newOption = $('<option>');
                newOption.attr('value',0).text(" "); 
                $('#subCategoryId').empty(newOption);
                var defaultOption = $('<option>');
                defaultOption.attr('value',0).text("Select Sub Category");
                $('#subCategoryId').append(defaultOption);
				($.map(data, function(item) {
					var newOption = $('<option>'); 
					newOption.attr('value', item.subCategoryId).text(item.subCategoryName);
					$('#subCategoryId').append(newOption);
				}));
			}
		});
	});
	
	pageSetUp();
	  $("#docketStatus").kendoDatePicker({
	        start: "decade",
	        depth: "decade",
	        format: "yyyy",
	        min: new Date(2010, 1, 1),
	          max: new Date(),
	          readonly : true,	          
	    });  
});
</script>

<style>
#content {
    padding: 2px 0px;
    position: relative;
}
.jarviswidget {
    margin: 0px 0px 7px;
}
</style>
		