<%@include file="/common/taglibs.jsp"%>

<br><br><br>
<c:url value="/projectaccess/readcompanytree" var="projectReadUrl" />
<c:url value="/projectaccess/commitchanges" var="flushingChangesUrl" />
<c:url value="/projectaccess/getproductandroles" var="getProductAndRoles"/>

  <article
	class="col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable"> 
	<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-12"
		data-widget-colorbutton="false" data-widget-togglebutton="false"
		data-widget-editbutton="false" data-widget-fullscreenbutton="false"
		data-widget-deletebutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-arrow-down"></i>
			</span>
			<h2>
				<strong>Project Hierarchy</strong>
			</h2>
			<div class="widget-toolbar">

				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-warning"
						data-toggle="dropdown">
						Actions <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllprojNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllprojNodes">Collapse
								All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div style="height: 400px; max-height: 380px; overflow: scroll;">
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
				<div id="projectTree"></div>
			</div>
		</div>
	</div>
	<button style="float: left; " onclick="getAllChecked()" type="button" class="btn btn-labeled btn-success">
 <span class="btn-label">
  <i class="glyphicon glyphicon-ok"></i>
 </span> Commit Changes
</button>
</article>


<article
	class="col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable"> 
	<div style="height: 500px" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-12"
		data-widget-colorbutton="false" data-widget-togglebutton="false"
		data-widget-editbutton="false" data-widget-fullscreenbutton="false"
		data-widget-deletebutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-arrow-down"></i>
			</span>
			<h2>
				<strong>Product Hierarchy</strong>
			</h2>
			<div class="widget-toolbar">

				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-warning"
						data-toggle="dropdown">
						Actions <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllprodNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllprodNodes">Collapse
								All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div style="height: 400px; max-height: 380px; overflow: scroll;">
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
				<div id="productTree"></div>
			</div>
		</div>
	</div>
</article>

<article
	class="col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable"> 
	<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-12"
		data-widget-colorbutton="false" data-widget-togglebutton="false"
		data-widget-editbutton="false" data-widget-fullscreenbutton="false"
		data-widget-deletebutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-arrow-down"></i>
			</span>
			<h2>
				<strong>Roles</strong>
			</h2>
			<div class="widget-toolbar">

				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-warning"
						data-toggle="dropdown">
						Actions <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllroleNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllroleNodes">Collapse	All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div style="height: 400px; max-height: 380px; overflow: scroll;">
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
			<div id="rolesTree"></div>
			
				<%-- <kendo:grid name="grid" pageable="false" sortable="true" filterable="false" selectable="multiple" >
    				<kendo:grid-scrollable />    	
       				 <kendo:grid-columns>
            <kendo:grid-column title="Roles" field="text" />
           
        </kendo:grid-columns>
        <kendo:dataSource data="${roles}" pageSize="20">        
            <kendo:dataSource-schema>
                <kendo:dataSource-schema-model>
                    <kendo:dataSource-schema-model-fields>
                        <kendo:dataSource-schema-model-field name="text" type="string" />
                    </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
            </kendo:dataSource-schema>
        </kendo:dataSource>
        <kendo:grid-pageable input="true" numeric="false" />
    </kendo:grid> --%>
			</div>
		</div>
	</div>
</article>


	<script>
	
	
	
	
	$(document).ready(
			function() {
				
		
				var projtree = "";
				var prodtree = "";
				var roletree = "";

				$.ajax({
							type : "POST",
							url : "${projectReadUrl}/Company",
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {

										projtree = $("#projectTree")
												.kendoTreeView({
													checkboxes :  {
														checkChildren : true,
														template : " <input type='checkbox' class=umCheck#:item.ldapurl# id='projCheck' value='#: item.ldapurl#'/> "
													} ,
													template : " #: item.text # <input type='hidden' id='hiddenId'  value='#: item.ldapurl#'/>",
													dataSource : data,
													select: projectTree
												})
												.data("kendoTreeView"),
										handleTextBox = function(
												callback) {
											return function(e) {
												if (e.type != "keypress"
														|| kendo.keys.ENTER == e.keyCode) {
													callback(e);
												}
											};
										};
								//projtree.collapse(".k-item");
								$(".projCheckCompany").closest('span').parent().remove();
							}
						});
				
				function projectTree(e){
					var project =$('.k-state-hover').find('#hiddenId').val(); 
					$(".umCheckremovethis").closest('span').closest('div').remove();
					$('input[class^="umCheck"]').prop("checked", false);
					prodtree.expand(".k-item");
					roletree.expand(".k-item");
					$.ajax({
						type : "GET",
						url : "${getProductAndRoles}",
						data : {
							project : project
						},
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(data) {
							if (data.length > 0) {
								for (var s = 0, len = data.length; s < len; ++s) {
									var um = data[s];
									//alert(contact);
									$('input[class="umCheck' + um + '"]').prop("checked", true);
								}
							}
						}
					});
					
				}
				
				$.ajax({
					type : "POST",
					url : "${projectReadUrl}/Products",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {

								prodtree = $("#productTree")
										.kendoTreeView({
											checkboxes : {
												checkChildren : true,
												template : "<input type='checkbox' class='umCheck#:item.ldapurl#' id='prodCheck' value='#: item.ldapurl#' /> "
											},
											dataSource : data
										})
										.data("kendoTreeView"),
								handleTextBox = function(
										callback) {
									return function(e) {
										if (e.type != "keypress"
												|| kendo.keys.ENTER == e.keyCode) {
											callback(e);
										}
									};
								};
						prodtree.collapse(".k-item");
					 	$(".umCheckremovethis").closest('span').parent().remove(); 
					}
				});
				
				$.ajax({
					type : "POST",
					url : "${projectReadUrl}/Roles",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {

								roletree = $("#rolesTree")
										.kendoTreeView({
											checkboxes : {
												checkChildren : true,
												template : "<input type='checkbox' class='umCheck#:item.ldapurl#' id='rolesCheck' value='#: item.ldapurl#' /> "
											},
											dataSource : data
										})
										.data("kendoTreeView"),
								handleTextBox = function(
										callback) {
									return function(e) {
										if (e.type != "keypress"
												|| kendo.keys.ENTER == e.keyCode) {
											callback(e);
										}
									};
								};
						roletree.collapse(".k-item");
						$(".umCheckremovethis").closest('span').parent().remove(); 
					}
				});

				$("#expandAllprojNodes").click(function() {
					projtree.expand(".k-item");
				});

				$("#collapseAllprojNodes").click(function() {
					projtree.collapse(".k-item");
				});
				$("#expandAllprodNodes").click(function() {
					prodtree.expand(".k-item");
				});

				$("#collapseAllprodNodes").click(function() {
					prodtree.collapse(".k-item");
				});
				$("#expandAllroleNodes").click(function() {
					roletree.expand(".k-item");
				});

				$("#collapseAllroleNodes").click(function() {
					roletree.collapse(".k-item");
				});
		});
	var projchecked = "";
	var projcheckedValues = "";
	var prodchecked = "";
	var prodcheckedValues = "";
	var roleschecked = "";
	var rolescheckedValues = "";
	var projsstr = "";
	var prodsstr = "";
	var rolesstr = "";
	


	
	
	function getAllChecked(){
		
		var flag = 0;
		projchecked = $("#projCheck:checked");
		projcheckedValues = projchecked.map(function(i) {
					return $(this).val()
		}).get();
		
		prodchecked = $("#prodCheck:checked");
		prodcheckedValues = prodchecked.map(function(i) {
					return $(this).val()
		}).get();
		
		roleschecked = $("#rolesCheck:checked");
		rolescheckedValues = roleschecked.map(function(i) {
					return $(this).val()
		}).get();
		
		if (projchecked.length) {
			projstr = projcheckedValues.join(":");
			//alert(projstr);
		}
		else{
			flag = 1; 
			alert("No Project Selected, please check to select the project");
		}
		if (prodchecked.length) {
			prodstr = prodcheckedValues.join(":");
			//alert(prodstr);
		}
		else{
			flag = 1; 
			alert("No Product Selected");
		}
		
		if (roleschecked.length) {
			rolesstr = rolescheckedValues.join(":");
			//alert(rolesstr);
		}
		else{
			flag = 1; 
			alert("No Roles Selected");
		}


		if (flag == 0) {
			 var result=securityCheckForActionsForStatus("./users/prm/commit");			
		     if(result=="success")
		     { 	
				$.ajax({
					type : "GET",
					url : "${flushingChangesUrl}",
					data : {
						projects : projstr,
						products : prodstr,
						roles : rolesstr
					},
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					success : function(data) {
						alert("Changes Saved Successfully");
						window.location.href="./index";
					}
				});
		     } 
			}
		}
	</script>