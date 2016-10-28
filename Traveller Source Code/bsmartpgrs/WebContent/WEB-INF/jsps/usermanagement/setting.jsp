<%@include file="/common/taglibs.jsp"%>

<br><br><br>
<c:url value="/projectaccess/readcompanytree" var="projectReadUrl" />

<article class="col-sm-8 col-md-8 col-lg-4 sortable-grid ui-sortable"> 
	<div data-widget-editbutton="false" id="wid-id-12" class="jarviswidget jarviswidget-color-orange jarviswidget-sortable" role="widget">
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
						Settings <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllNodes">Collapse
								All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
				<div id="productTree"></div>
			</div>
		</div>
	</div>
</article>

<article
	class="col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable"> 
	<div  id="wid-id-12" class="jarviswidget jarviswidget-color-blue jarviswidget-sortable" role="widget">
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
						Settings <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllNodes">Collapse
								All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
			<div id="rolesTree"></div>
			</div>
		</div>
	</div>
</article>

<!-- <footer>
	<div>
		<a onclick="getAllChecked()" href="javascript:void(0);" class="btn btn-success btn-lg">Commit Changes</a>
	</div>
</section> -->

	<script>
	$(document).ready(
			function() {
				var tree = "";
				$.ajax({
					type : "POST",
					url : "${projectReadUrl}/Products",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {

								tree = $("#productTree")
										.kendoTreeView({
											checkboxes : {
												checkChildren : true,
												template : " <input type='checkbox' class=prodCheck#:item.text# id='prodCheck' value=#: item.text#> "
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
						tree.collapse(".k-item");
					}
				});
				
				$.ajax({
					type : "POST",
					url : "${projectReadUrl}/Roles",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {

								tree = $("#rolesTree")
										.kendoTreeView({
											checkboxes : {
												checkChildren : true,
												template : " <input type='checkbox' class=rolesCheck#:item.text# id='rolesCheck' value=#: item.text#> "
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
						tree.collapse(".k-item");
					}
				});
		});
	var prodchecked = "";
	var prodcheckedValues = "";
	var roleschecked = "";
	var rolescheckedValues = "";
	function getAllChecked(){
		prodchecked = $("#prodCheck:checked");
		prodcheckedValues = prodchecked.map(function(i) {
					return $(this).val()
		}).get();
		
		roleschecked = $("#rolesCheck:checked");
		rolescheckedValues = roleschecked.map(function(i) {
					return $(this).val()
		}).get();
		
		if (prodchecked.length) {
			var prodstr = prodcheckedValues.join();
			alert(prodstr);
		}
		else{
			alert("No Product Selected");
		}
		
		if (roleschecked.length) {
			var rolesstr = rolescheckedValues.join();
			alert(rolesstr);
		}
		else{
			alert("No Roles Selected");
		}
	}
	</script>