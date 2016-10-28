<%@include file="/common/taglibs.jsp"%>

<link href="<c:url value='/resources/kendo/css/web/kendo.default.min.css'/>" rel="stylesheet" />
<c:url value="/projecttree/map/read" var="treeReadUrl" />

<br><br><br>
<div>
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
				<strong>Office Hierarchy</strong>
			</h2>
			<div class="widget-toolbar">

				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-warning"
						data-toggle="dropdown">
						Actions <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#" class="" id="expandAllNodes">Expand All</a></li>
						<li><a href="#" class="" id="collapseAllNodes">Collapse
								All</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div style="height: 400px; max-height: 380px; overflow: scroll;">
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body">
	<kendo:treeView name="treeview" dataTextField="text"  expand="onExpand" select="onSelect" template="<span id='mainText' style='width: 100%'><b>#: item.text # </b></span><input type='hidden' id='syed' class='#: item.text ##: item.id#' value='#: item.id#'/><br><i>Type </i>:<span class='type#:item.id#' id='type#: item.type##:item.id#'> #: item.type#</span><span style='height:100%;display:none' id='hideit_#: item.id#'><a href='\\#' class='fa fa-lg fa-fw fa-plus' id='addtype#: item.type#' onclick='addCategory()'></a><a href='\\#' id='editype#: item.type#' class='fa fa-lg fa-fw fa-pencil' onClick='editCategory()' ></a><a href='\\#' class='fa fa-lg fa-fw fa-trash-o' id='deltype#: item.type#' onClick='deleteCategory()' ></a></span>">
     <kendo:dataSource >
         <kendo:dataSource-transport>
             <kendo:dataSource-transport-read url="${treeReadUrl}" type="POST"  contentType="application/json"/>    
             <kendo:dataSource-transport-parameterMap>
             	<script>
              	function parameterMap(options,type) {
              		return JSON.stringify(options);
              	}
             	</script>
             </kendo:dataSource-transport-parameterMap>         
         </kendo:dataSource-transport>
         <kendo:dataSource-schema>
             <kendo:dataSource-schema-hierarchical-model id="id" hasChildren="hasChilds" />
         </kendo:dataSource-schema>
     </kendo:dataSource>
 </kendo:treeView>
 </div>
		</div>
	</div>
</article>


	<article style="display: none" id='secondDiv'
		class="col-xs-12 col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable">
		<div style="height: 350px"
			class="jarviswidget jarviswidget-color-blueDark" id="wid-id-12"
			data-widget-colorbutton="false" data-widget-togglebutton="false"
			data-widget-editbutton="false" data-widget-fullscreenbutton="false"
			data-widget-deletebutton="false">
			<header>
				<span class="widget-icon"> <i class="fa fa-arrow-down"></i>
				</span>
				<h2>
					<strong>Office Details</strong>
				</h2>
				<div class="widget-toolbar">

					
				</div>
			</header>
			<div style="height: 250px; max-height: 250px; overflow: scroll;">
				<div class="jarviswidget-editbox"></div>
				<div class="widget-body">
				
					<table>
						<tr>
							<td colspan="2"><h1><span id='itext'></span></h1></td>
						</tr>
						
						<tr>
							<td><b>Invoice Number Format&nbsp;&nbsp;&nbsp;&nbsp; </b></td> <td> : </td><td><span id='inf'></span></td>
						</tr>
						<tr>
							<td><b>Last Invoice Number </b></td> <td> : </td><td><span id='lin'></span></td>
						</tr>
						
						<tr>
							<td><b>Tds Ledger&nbsp;&nbsp;&nbsp;&nbsp; </b></td> <td> : </td><td><span id='tLedger'></span></td>
						</tr>
						<tr>
							<td><b>Party Ledger </b></td> <td> : </td><td><span id='paLedger'></span></td>
						</tr>
						<tr>
							<td><b>Penalty Ledger&nbsp;&nbsp;&nbsp;&nbsp; </b></td> <td> : </td><td><span id='penLedger'></span></td>
						</tr>
						<tr>
							<td><b>GODOWN&nbsp;&nbsp;&nbsp;&nbsp; </b></td> <td> : </td><td><span id='gdown'></span></td>
						</tr>
						<tr>
						
						   <td><b>Tds Rate&nbsp;&nbsp;&nbsp;&nbsp;</b></td> <td> :</td><td><span id='trate'></span></td>
						</tr>
						
						 
					</table>
				
				</div>
			</div>
		</div>
		<button onclick="readCategory()" class="k-button" id="readButton">Import From Tally Server</button>
	</article>




	<br>
</div>

<div id="window1" style="display: none;" title="Add/Update Office Tree">

 <input id="hiddenid" type="hidden"/>
	<table style="width:100%; text-align: right;">
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		<tr>
			<td>Name *</td>
			<td align="center"> <input id="text" class="k-textbox" required="required" maxlength="20"/></td>
		</tr>
		
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		
		<tr>
			<td></td>
			<td align="center" hidden="true"><input  id="type"></td>
		</tr>
		
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		<tr style="border-top: 1px solid #D7D7D7"><td>&nbsp;</td><td>&nbsp;</td></tr>
		
		<tr>
			<td></td>
			<td align="center"><button class="k-button" onClick = "submitForm()"><span class="k-icon k-i-pencil"></span>Add/Update</button> </td>
		</tr>
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	</table>

</div>


<script>
	var texts = "";
	var id = "";	
	var treeview ="";
	var addOrEdit = "0";
	var catWindow = "" ;
	var officeId = 0;
	var type = "";
	var ledgerName = "";
	function getOfficeId(val){
		
		officeId = val;
	}
	
	function getType(val){
		type = val;
		alert(type);
	}
	function readCategory() {
	
		$("#readButton").hide();
		$.ajax({
			type : "POST",
			url : "./projecttree/getPartLedgerName",
			dataType : 'text',
			data : {
				ledgerName : "" + ledgerName + "",
			},
			success : function(response) {
				    alert(response);
				   $("#readButton").show();
				   
			}
		});

	}
	function onSelect(e) {
	
		
		$('span[id=hideit_2]').remove();
		$('#deltypeProject').remove();
		$('#deltypeProjects').remove();
		$('#editypeProject').remove();
		$('#addtypeProject').remove();
		$('span[id^=hideit_]').hide();

	
		id = $('.k-state-hover').find('#syed').val();
		var nn = $('.k-state-hover').html();
		var spli = nn.split(" <input");
		//$('#editNodeText').val(spli[0].trim());

		 var kitems = $(e.node).add(
				$(e.node).parentsUntil('.k-treeview',
						'.k-item'));
		texts = $.map(kitems, function(kitem) {
			var resVal = $(kitem).find('>div span.k-in').text();
			var resArr = resVal.split(" Type :");
			return resArr[0].trim();
		}); 
		$('#hideit_'+id).show();
		$.ajax({
			type : "POST",
			url : "./projecttree/getdetailonid",
			data : {
				id : id
			},
			success : function(response) {
				
				$('#itext').text(response.text);
				$('#itype').text(response.type);
				$("#type").data("kendoDropDownList").value(response.type);
	

			if(response.type=='Office'){
				$('#secondDiv').show();
			}else if(response.type=='Sub Division Office'){
				$('#secondDiv').show();
			}else{
				$('#secondDiv').hide();
			} 
		}
	});
	
	}
	var myflag = 0;
	function onExpand(e){
		if(myflag == 0){
            $("span[id^=typeDivision]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-th'></p>"); 
			$("span[id^=typeOffice]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-briefcase'></p>"); 
			$("span[id^=typeRegion]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-star-half-o'></p>"); 
			$("span[id^=typeZone]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-globe'></p>");
			$("span[id^=typeProject]").parent('span').prepend("<p class='fa fa-lg fa-fw fa-star-half-o'></p>");
			myflag = 1;
			
		}
	}

	function addCategory()
	{
		addOrEdit = "0";
		$('#text').val("");
		$('#window1').dialog({
            	width: 475,
            	position: 'center',
				modal : true,
			}); 
		$('#text').val("");
	}
	
	$("#expandAllNodes").click(function() {
		treeview.expand(".k-item");
	});

	$("#collapseAllNodes").click(function() {
		treeview.collapse(".k-item");
	});
	
	function editCategory(){
		addOrEdit = "1";
		$('#window1').dialog({
         	width: 475,
         	position: 'center',
				modal : true,
		}); 
		 
		$.ajax({
				type : "POST",
				url : "./projecttree/getdetailonid",
				data : {
					id : id
				},
				success : function(response) {
					$('#text').val(response.text);
				
				}
		});
			
	}
	
	function deleteCategory()
	{
		var cof = confirm("Are you sure want to delete this office?");
		
		if(cof){
		var selectedNode = treeview
		.select();
		if (selectedNode.length == 0) {
		alert("Select the Node to Delete");
		selectedNode = null;
		} else {
			
		if(id>2){
		$.ajax({
				type : "POST",
				url : "./projecttree/delete",
				data : {
					id : id
				},
				dataType: "text",
				success : function(
						response) {
					alert(response);
					if (response == 'Deleted Successfully') {
						treeview.remove(selectedNode);
					}
				}
			});
			}
			else{
				alert("Parent category can't be deleted");
			}
		}
		}
	}
	
	
	function submitForm()
	{
		/* treeview.expand(".k-item"); */
		var action ="";
		if(addOrEdit == "0"){
			action = "save";
		}else{
			action = "update";
		}
		
		var errorStr ="Please select the following - \n\n";
		var selectedNode = treeview.select();
		var text =  $('#text').val().trim();
		var treeHierarchy = texts.join('>');
		
		if(text == ''){
			flag =1;
			errorStr+=" - Name is required \n";
		}
		
		$.ajax({
			type : "POST",
			url : "./projecttree/"+action,
			dataType: "text",
			data : {
				id : id,
				text : text,
				treeHierarchy : treeHierarchy,
				
			},
			success : function(response) {
				if(response == 'no'){
					alert("Can't be added");
					$('#window1').dialog('close');
					return false;
				}
				var splitter = response.split("/");
				var value = splitter[0];
				var types= splitter[1];
				$('#window1').dialog('close');
			 	if(action == "save"){
				 	alert("Added Successfully");
					treeview.append({
						text: text,
						id: value,
						type:types
				}, selectedNode);
				$('.'+text).val(value);
				
				$("#text").val("");
				$("#type"+value).text(types);
				
			 }else{
				alert(value);
				$('.k-state-selected').text(text);
				$('#hideit_'+id).show();	
			
				/* $(".k-state-selected").append("<input type='hidden' id='syed' class='"+text+""+id+"' value='"+id+"'/><br><i>Type </i>:<span id='type"type+""+id+"'> "+type+"</span><span style='height:100%;display:none' id='hideit_"+id+"'><a href='\\#' class='fa fa-lg fa-fw fa-plus' onclick='addCategory()'></a><a href='\\#' class='fa fa-lg fa-fw fa-pencil' onClick='editCategory()' ></a><a href='\\#' class='fa fa-lg fa-fw fa-trash-o' onClick='deleteCategory()' ></a></span>");
			 */
			}
		}
		});
	}
	
	
	function cancelForm(){
		$('#window1').dialog('close');
	}
	
	$(document).ready(function() {
		treeview = $("#treeview").data("kendoTreeView"), handleTextBox = function(callback) {
		return function(e) {
		if (e.type != "keypress"|| kendo.keys.ENTER == e.keyCode) {
		callback(e);
		}
		}
		};
		
		var data = [
                    { text: "Zone", value: "Zone" },
                    { text: "Division", value: "Division" },
                    { text: "Region", value: "Region" },
                    { text: "Office", value: "Office" }
                ];

        $("#type").kendoDropDownList({
                	optionLabel: "Select",
                    dataTextField: "text",
                    dataValueField: "value",
                    dataSource: data,
                    index: 0
        });

        $("#rateContractDate").kendoDatePicker({
        	
        	 format: "dd MMM yyyy"
        	
        });
        
        $("#buyerOrderDate").kendoDatePicker({
        	format: "dd MMM yyyy"
        });

        $("#lastInvoiceMonth").kendoDatePicker({
            start: "year",
            depth: "year",
            format: "MMMM yyyy"
        });
	});
	
	
</script>
<style scoped>
.configuration .k-textbox {
	width: 50px;
}

.demo-section {
	width: 200px;
	margin: 0 auto;
}
</style>


<style scoped>
.k-picker-wrap .k-icon {
	margin-top: -7px;
	margin-left: -8px;
}

.fa-fw {
	height: 4px;
}
.k-state-selected{
	background: white !important;
	border-color : black !important;
	color: black
}
</style>