
<style>
hr {
    display: block;
    height: 1px;
    border: 1;
    border-top: 2px solid #ccc;
}
.container {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
.container1 {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
.container2 {
    display: inline-block;
    width: 60%;
    	/* border: 1px solid black; */
}
</style>
<div id="stackChart" class="modal fade" tabindex="-1"  data-keyboard="false">
						        <div class="modal-dialog" style="width: 90%;">
							       <div class="modal-content" >
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title" id="titleId">Device Health&nbsp;&nbsp;&nbsp;<span class="label label-warning"><font size="3" color="black"><b>SECTION:-</b><span class="label label-warning" ><font size="3" color="black" ><b id="sdoId"></b></font></span></font></span>
									&nbsp;&nbsp;&nbsp;<span class="label label-success"><font size="3" color="black"><b>MRCODE:-</b><span class="label label-success" ><font size="3" color="black" ><b id="mrcodeId"></b></font></span></font></span>
									</h4>
									
								</div>
								<div class="modal-body">
									<!--  <div id="batterySignal" style="width: 100%; height: 400px;"></div> -->
									<table class="" id="sample_3" >
									<tr>
									<td >
									<div id="batterySignal" class="container" >
									</div>
									</td>
									<td>
									<div id="batterySignal11"  >
									</div>
									</td>
									</tr>
									
									</table>
									<div id="Signal" class="container1" ></div>
									 <div id="memory" class="container2"></div>
									 
								</div>
							</div>
						</div>
					</div>