
 <%@include file="/common/taglibs.jsp"%>


<c:url value="/setting/producttree" var="projectReadUrl" />

<br><br>
<article
	class="col-xs-12 col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable"> 
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
						<li><a href="#" class="" id="collapseAllprodNodes">Collapse	All</a></li>
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
	<div style="height: 500px" class="jarviswidget jarviswidget-color-blueDark" id="wid-id-12"
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
						Roles <i class="fa fa-caret-down"></i>
					</button>
				</div>
			</div>
		</header>
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
			<br>
				<kendo:multiSelect name="treeItems" dataTextField="text" dataValueField="id" placeholder="Roles..."
							size="10px">
							<kendo:dataSource data="${items}"></kendo:dataSource>
				</kendo:multiSelect>	
				<br><br>
				<button class="k-button" id="editRoles">&nbsp;&nbsp;Assign Roles
							&nbsp;&nbsp;</button>		
							<br><br>
				<button class="k-button" onClick="flushAll()"> Commit all your changes</button>	
				<span id=commitplaceholder style="display: none;"><img src="./resources/img/loadingimg.GIF" style="vertical-align:middle"/> &nbsp;&nbsp; <img src="./resources/img/loadingText.GIF" alt="loading" style="vertical-align:middle" height=25px/></span>
			
			</div>
		</div>
	</div>
</article>


<script>

$( document ).ready(function() {
	
	 	var prodtree = "";
		$.ajax({
			type : "POST",
			url : "${projectReadUrl}",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
						prodtree = $("#productTree").kendoTreeView({
									dataSource : data,
									select : getRoles
								}).data("kendoTreeView"),
						handleTextBox = function(callback) {
							return function(e) {
								if (e.type != "keypress"
										|| kendo.keys.ENTER == e.keyCode) {
									callback(e);
								}
							};
						};
				prodtree.collapse(".k-item");
			 	
			 	 var roles = handleTextBox(function(e) {
						var selectedNode = prodtree.select();
						var multiSelect = $("#treeItems").data("kendoMultiSelect");
						var role = "(" + multiSelect.value() + ")";
						if (multiSelect.value() != "") {
							var ne = texts.join(',');
							var str = ne.split(",");
							var strspli = str[str.length - 1].split("=");
							if (strspli[0] == "Task " || strspli[0] == "Tasks " || strspli[0] == "Forms " || strspli[0] == "Module ") {
								$body = $("#content");
						        $body.addClass("loading");	
						        var result=securityCheckForActionsForStatus("./users/pam/assign");			
							     if(result=="success")
							     {	
								$.ajax({
									type : "POST",
									url : "./ldaptree/update",
									dataType: "text",
									data : {
										values : ne,
										mselect : role
									},
									success : function(response) {
										if(strspli[0] == "Tasks "){
											prodtree.append({
											text : "Roles = " + response
										}, selectedNode);
										$('#treeview_tv_active').find('.k-group').find('.k-top').remove();
										$('#treeview_tv_active').find('.k-group').find('.k-mid').remove();
										}
										alert("Updated Successfully");
									},
									complete:function()
								       {
								        $body.removeClass("loading");
								       }
								});
							     }
							} else {
								alert("Invalid Selection");
							}
						} else {
							alert("Please enter the Roles");
						}
					});
					$("#editRoles").click(roles);  
			}
		});
});

function flushAll(){	
		$('.k-button').hide();
		$('.k-multiselect').hide();
		$('#commitplaceholder').show();
		$body = $("body");
        $body.addClass("loading");
        var result=securityCheckForActionsForStatus("./users/pam/commit");			
	    if(result=="success")
	     { 	
		$.ajax({
			type : "POST",
			url : "./ldaptree/flush",
			dataType: "text",
			success : function(response) {
				$('.k-button').show();

				$('#commitplaceholder').hide();
				alert(response);
				window.location.href="./index";
			},
			complete:function(){
		        $body.removeClass("loading");
		     }
		});
	      } 
}

var texts = "";
function getRoles(e) {
		var kitems = $(e.node).add(
				$(e.node).parentsUntil('.k-treeview', '.k-item'));
		texts = $.map(kitems, function(kitem) {
			return $(kitem).find('>div span.k-in').text();
		});
		var ntext = texts.join(',');
		var len = ntext.split(",");
		if (len.length >= 2) {
			var ne = texts.join(',');
			var multiSelect = $("#treeItems").data("kendoMultiSelect");
			if(len[2]=='Forms = Manage Product Access'){
				 multiSelect.enable(false);
			}
			else{
				multiSelect.enable();
			}
			multiSelect.dataSource.filter({}); //clear applied filter before setting value
			$.ajax({
				type : "POST",
				url : "./ldaptree/taskroles",
				data : {
					values : ne
				},
				success : function(response) {
					var res = [];
					$.each(response, function(index, value) {
						if (value.text == null) {
						} else {
							res.push(value.id);
						}
					});
					multiSelect.value(res);
				}
			});
		}
	}
	
   /*  $(window).scroll(function(){     
        if ($(window).scrollTop() > 48){
 	    $("#taskperm").css({"top": ($(window).scrollTop()) -48 + "px"});
 	}
  }); */
    
</script>
<style scoped>
.configuration .k-textbox {
	width: 10px
}

.demo-section {
	margin: 0 auto;
	border: 0px solid #CDCDCD;
	box-shadow: 0 1px 0 #FFFFFF;
	text-shadow: 0 1px #FFFFFF;
	background-image: none,
		linear-gradient(to bottom, #FFFFFF 0px, #E6E6E6 100%);
	background-position: 50% 50%;
	font-weight: normal;
}

#taskperm {
	/* font-size: 8pt;
	overflow: auto;
	min-height: 100px;
	height: auto !important;
	height: 100px; */
	font-size: 8pt;
	overflow: auto;
	min-height: 100px;
	height: auto !important;
	height: 100px;
	float: none;
}

#emptyDiv {
	width: 1px;
}
</style>