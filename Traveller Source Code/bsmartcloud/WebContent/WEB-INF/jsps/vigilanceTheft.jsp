<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
#image
{
   width: 1000px;
   height: 1000px;
}
 #image1
{
    border-radius: 20px;
}
 #Imageview
{
    margin-bottom: 5px;
} 
#rr1
{
   width: 50px;
   height: 50px;
     
}
#rl2
{
   width: 50px;
   height: 50px;
    margin-left: 350px; 
}
#rll
{
    margin-left: 390px;
}
</style>

 <script  type="text/javascript">
  $(".page-content").ready(function()
   	    	   {     
   	    	     App.init();
   	    	  TableEditable.init();
   	    	 FormComponents.init();
   	    	$('#vigilance-management').addClass('start active ,selected');
	   	  		$('#dash-board,#user-location,#meterData-Acquisition,#MIS-Reports-photoBilling,#Cash_Collection,#MIS-Reports').removeClass('start active ,selected');
   	    	   });
  
 
  function edit(param,opera)
	{
		var operation=parseInt(opera);
			  $.ajax(
						{
								type : "GET",
								url : "./editTheftRecording/" + operation,
								dataType : "json",
								success : function(response)
															{	
													    		
																document.getElementById("id").value=response.id;
																/* document.getElementById("sdoCode").value=response.sdoCode;
																document.getElementById("rr_Number").value=response.rr_Number;
																document.getElementById("address").value=response.address;
																document.getElementById("description").value=response.description;
																document.getElementById("place").value=response.place;
																document.getElementById("lattitude").value=response.lattitude;
																document.getElementById("longitude").value=response.longitude;
																document.getElementById("createdBy").value=response.createdBy; */
																document.getElementById("status").value=response.status;
															}
						}
				    );
			$('#'+param).attr("data-toggle", "modal");
		    $('#'+param).attr("data-target","#stack1");
	}
  
  
  var  rotation=0;
	function viewDocument(rrNo,value)
	{
	     $('#Imageview').empty(); 
	    var url2="./theftimageDisplay/getImage/"+rrNo+"/"+value;
	    rotation=0;
	    rotateLeftAgain();
	    rotateRightAgain();
	    $('#Imageview').append("<img id=\"tempImg\" style=\"width:935px;height:935px;\" src='"+url2+"'class='image_fit' title="+rrNo+" />");
	    	
	}


	/* $(document).keyup(function(e) {     
	    if(e.keyCode== 27) 
	    {
	    	alert("ESC is pressed");
	    	rotation=0;
	    	rotate = "rotate(" + rotation + "deg)";
	    } 
	}); */

	function rotateLeftAgain()
	{
		var rotate1 = "rotate(" + rotation + "deg)";
	    var trans = "all 0.3s ease-out"; 
	   $(".rotatecontrol").css({
	       "-webkit-transform": rotate1,
	       "-moz-transform": rotate1,
	       "-o-transform": rotate1,
	       "msTransform": rotate1,
	       "transform": rotate1,
	       "-webkit-transition": trans,
	       "-moz-transition": trans,
	       "-o-transition": trans,
	       "transition": trans
	   });
		
	}
	function rotateRightAgain()
	{
		var rotate2 = "rotate(" + rotation + "deg)";
	    var trans = "all 0.3s ease-out"; 
	   $(".rotatecontrol").css({
	       "-webkit-transform": rotate2,
	       "-moz-transform": rotate2,
	       "-o-transform": rotate2,
	       "msTransform": rotate2,
	       "transform": rotate2,
	       "-webkit-transition": trans,
	       "-moz-transition": trans,
	       "-o-transition": trans,
	       "transition": trans
	   });
		
	}


	function rotateRight()
	{
		rotation =rotation+90;
	    var rotate = "rotate(" + rotation + "deg)";
	     var trans = "all 0.3s ease-out"; 
	    $(".rotatecontrol").css({
	        "-webkit-transform": rotate,
	        "-moz-transform": rotate,
	        "-o-transform": rotate,
	        "msTransform": rotate,
	        "transform": rotate,
	        "-webkit-transition": trans,
	        "-moz-transition": trans,
	        "-o-transition": trans,
	        "transition": trans
	    });
	    
	}


	function rotateLeft()
	{
		rotation =rotation-90;
	    var rotate = "rotate(" + rotation + "deg)";
	     var trans = "all 0.3s ease-out"; 
	    $(".rotatecontrol").css({
	        "-webkit-transform": rotate,
	        "-moz-transform": rotate,
	        "-o-transform": rotate,
	        "msTransform": rotate,
	        "transform": rotate,
	        "-webkit-transition": trans,
	        "-moz-transition": trans,
	        "-o-transition": trans,
	        "transition": trans
	    });
	}
  
  
  
  
  </script>
<div class="page-content">

	<%@include file="pagebreadcrum.jsp"%><!-- //home link and Link label -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${results ne 'notDisplay'}">
				<div class="alert alert-danger display-show">
					<button class="close" data-close="alert"></button>
					<span style="color: red">${results}</span>
				</div>
			</c:if>

			<div class="portlet box purple">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>Theft Recording Details
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a> <a
							href="javascript:;" class="remove"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="table-toolbar">
						 <!-- <div class="btn-group">
							<button class="btn green" data-target="#stack1"
								data-toggle="modal" id="addData" id="em">
								Update Status <i class="fa fa-plus"></i>
							</button>
						</div> --> 
						<div class="btn-group pull-right">
							<!-- <button class="btn dropdown-toggle" data-toggle="dropdown">
								Tools <i class="fa fa-angle-down"></i>
							</button> -->
							<ul class="dropdown-menu pull-right">
								<li><a href="#" id="print">Print</a></li>
								<li><a href="#" id="excelExport"
									onclick="tableToExcel('sample_editable_1', 'Allowed Device Versions')">Export
										to Excel</a></li>
							</ul>
						</div>
					</div>
					<!-- <a href="#" id="editbutton"></a> -->
					<table class="table table-striped table-hover table-bordered"
						id="sample_editable_1">
						<thead>
							<tr style="background-color: lightgray;">
								<th hidden="true">Id</th>
								<th>SDO CODE</th>
								<th >Consumer No.</th>
								<th >Address</th>
								<th >Description</th>
								<!-- <th >Place</th> -->
								<th >Image 1</th>
								<th >Lattitude</th>
								<th >Longitude</th>
								<th >Created By</th>
								<th >Image 2</th>
								<th >Image 3</th>
								<th >Status</th>
								<th >Edit</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="element" items="${theftList}">
								<tr>
									<td hidden="true">${element.id}</td>
									<td>${element.sdoCode}</td>
									<td>${element.rr_Number}</td>
									<td>${element.address}</td>
									<td>${element.description}</td>
									<%-- <td>${element.place}</td> --%>
									<%-- <td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/1"  height="70" width="70" data-magnifyby="6" class="magnify"  title="${element.rr_Number}" /></td> --%>
									<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/1"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','1');" data-target='#popup_image'   height="70" width="70" title="${element.rr_Number}" /></td>
									<td>${element.longitude}</td>
									<td>${element.lattitude}</td>
									<td>${element.createdBy}</td>
									<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/2"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','2');" data-target='#popup_image'   height="70" width="70"    /></td>
									<td><img src="./theftimageDisplay/getImage/${fn:trim(element.rr_Number)}/3"  data-toggle=modal onclick="viewDocument('${fn:trim(element.rr_Number)}','3');" data-target='#popup_image'   height="70" width="70"  /></td>
									<td>${element.status}</td>
									<td><a href="#" onclick="edit(this.id,${element.id})"
										id="editData${element.id}">Edit</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Update Status</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-12">
											 <form:form action="./updateTheftStatus" modelAttribute="theftRecording"
																	commandName="theftRecording" method="post" id="theftRecording">
												 <table>
												
													 <tr hidden="true">
														<td>Id</td>
														<td><form:input path="id" id="id"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="id" ></form:input></td>
													<%--</tr>
													
													<tr>
														<td>SDO CODE</td>
														<td><form:input path="sdoCode" id="sdoCode"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="sdoCode" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Consumer No.</td>
														<td><form:input path="rr_Number" id="rr_Number"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="rr_Number" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Address</td>
														<td><form:input path="address" id="address"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="address" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Description</td>
														<td><form:input path="description" id="description"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="description" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Place</td>
														<td><form:input path="place" id="place"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="place" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Lattitude</td>
														<td><form:input path="lattitude" id="lattitude"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="lattitude" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Longitude</td>
														<td><form:input path="longitude" id="longitude"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="longitude" readonly="true"></form:input></td>
													</tr>
													
													<tr >
														<td>Created By</td>
														<td><form:input path="createdBy" id="createdBy"
																class="form-control placeholder-no-fix" type="text"
																autocomplete="off" placeholder="" name="createdBy" readonly="true"></form:input></td>
													</tr> --%>

													<tr>
														<td>Status</td>
														<td><select id="status" class="form-control" placeholder="" name="status">
														<c:forEach items="${statusList}" var="statusVal">
																		<option value="${statusVal}"
																			>${statusVal}</option>
														</c:forEach>
														</select></td>
													</tr>

												</table> 
														
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Close</button>
													<button class="btn blue pull-right" style="display: block;" id="updateOption"  onclick="return common_function();">Update Status</button>
												</div>
											</form:form> 

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="popup_image" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog" id="image">
        <div class="modal-content" >
         <div class="modal-header" id="modalheader">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
           &times;
          </button>
          <h4 class="modal-title">IMAGE
          <img  id="rl2" src="./resources/assets/img/RotateLeft.jpg" onclick="rotateLeft();"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <img  id="rr1" src="./resources/assets/img/RotateRight.jpg" onclick="rotateRight();"/>
         <label id="rll">Rotate Left&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rotate Right</label>
          </h4>
          
         </div>
         <div class="modal-body">
			<!-- <p class="padding">'<font color="red">*</font>'&nbsp;Indicates mandatory fields.</p> -->
          <div class="rotatecontrol" id="Imageview" >
           <img id="tempImg" src="" />
          </div>
         </div>
        </div>
        <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->
      </div>  