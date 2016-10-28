
// Wait for Apache Cordova to load
document.addEventListener("deviceready", onDeviceReady, false);

// PhoneGap is ready
function onDeviceReady() {
	navigator.splashscreen.hide();
}

var airlinesApp = function(){}

airlinesApp.prototype = function() {
	
	  var veheditdata_edit=0;
	
            
    var _flightForCheckin = null,
    _flightForDetails=null,
    _ffNum = null, 
    _customerData = null,
    _login = false,
    
    
    
    
    
    
    
    
    
    run = function(){
    	
    	   
			  
			     var data_edit_ps=0;
               var das="";
               var dad="";
               var lrd="";
               var tud="";
               var tcd="";
               var trd="";
               var tsd="";
    	
        var that = this,
        
        
        
        
        
        
        
        
        
        $seatPicker=$('#seatPicker');
        $('#tripDetail').on('pagebeforeshow',$.proxy(_initTripDetail,that));
        $('#boardingPass').on('pageshow',$.proxy(_initBoardingPass,that));
        $('#home').on('pagebeforecreate',$.proxy(_initHome,that));
        $('#checkIn').on('pageshow', $.proxy(_initCheckIn,that));
        
        $('#myTripsListView').on('click', 'li', function () {
        	var item = $(this);
        	_flightForCheckin = item.data('flight');
            _flightForDetails = item.data('flight');
        });
        
        
        
        
         $(document).on('vclick', '#movie-list li a', function(){  
                    showdata = $(this).attr('data-id');
                 //  alert(showdata);
                 window.location = "MyInboxdata.html";
                 //  $.mobile.changePage( "#headline", { transition: "slide", changeHash: false });
                   
                  });
                  
                  
                   $(document).on('pagebeforeshow', '#headline', function(){    
                //  alert(veheditdata_edit );
                  
                   $('#movie-data').empty();
                                //   alert("vvvvvvv"+veheditdata_edit[showdata]);
                                    data_edit_ps = veheditdata_edit[showdata];
                                  // alert(data_edit_ps);
                          das=ValidateTextAppend(data_edit_ps.deptAcceptanceStatus,7);
                          dad=convert(data_edit_ps.deptAcceptedDate);
                          lrd=convert(data_edit_ps.lastResonse);
                          tud=convert(data_edit_ps.ticketUpdateDate);
                          tcd=convert(data_edit_ps.ticketClosedDate);
                          trd=convert(data_edit_ps.ticketReopenDate);
                          tad=convert(data_edit_ps.ticketAssignedDate);
                               
                             //  alert(das+"/"+dad);
                         
                $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Issue Subject&nbsp;&nbsp; &nbsp; &nbsp;:&nbsp;' + data_edit_ps.issueSubject + '</b></li><p>');
                $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Issue Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + data_edit_ps.issueDetails + '</b></li><p>');
                  $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Priority&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + data_edit_ps.priorityLevel + '</b></li><p>');
                
                  $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Help Topic&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + data_edit_ps.topicName + '</b></li><p>');
                
                  $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + data_edit_ps.dept_Name + '</b></li><p>');
               
                
                  $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Dept Acceptance Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + das + '</b></li><p>');
                   $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Dept Acceptance Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + dad + '</b></li><p>');
                    $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Last Response Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + lrd + '</b></li><p>');
                     $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Ticket Updated Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + tud + '</b></li><p>');
                      $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Closed Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + tcd + '</b></li><p>');
                       $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Reopen Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + trd + '</b></li><p>');
                        $('#movie-data').append('<li><b>&nbsp; &nbsp;&nbsp;Assign Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; ' + tad + '</b></li><p>');
                       
                if(data_edit_ps.ticketStatus == "Closed"){
                	
                     $('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-danger"   data-toggle="modal" data-target="#"  onclick="Edit('+showdata+')"><i class="fa fa-edit fa-fw">&nbsp; &nbsp; <b></i>Edit</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>'); 
                 	$('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-success"   data-toggle="modal"  onclick="reopenTicket('+showdata+')"><i class="fa fa-plus fa-fw">&nbsp; &nbsp; <b></i>Re-open Ticket</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>');
                 	 $('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-info"   data-toggle="modal" data-target="#popup_edit_ticket_details_showidd" onclick="FeedbackMethod_two('+showdata+')"><i class="fa fa-plus fa-fw">&nbsp; &nbsp; <b></i>Send Feedback</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>');
                 	 
                 }
                 else{
                 $('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-danger"   data-toggle="modal" data-target="#popup_edit_ticket_details_showid"  onclick="Edit('+showdata+')"><i class="fa fa-edit fa-fw">&nbsp; &nbsp; <b></i>Edit</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>');
                 	$('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-success"   data-toggle="modal"  onclick="reopenTicket('+showdata+')"><i class="fa fa-plus fa-fw">&nbsp; &nbsp; <b></i>Re-open Ticket</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>');
                 	 $('#movie-data').append('<li> <button type=button  class="btn btn-outline btn-info"   data-toggle="modal" data-target="#popup_edit_ticket_details_showidd" onclick="FeedbackMethod_two('+showdata+')"><i class="fa fa-plus fa-fw">&nbsp; &nbsp; <b></i>Send Feedback</b>&nbsp; &nbsp; &nbsp;<br></button><br></li><br>');
                 }
         
                
            $('#movie-data').append('<li ><br></li>');
                  $('#movie-data').listview('refresh');  
                  
                    
                    
                      function convert(str) {
                
                if(str == "" || str == null){
                    return "";                }
                else{
                        var date = new Date(str), mnth = ("0" + (date.getMonth() + 1)).slice(-2), day = ("0" + date.getDate()).slice(-2);
                   return [day, mnth, date.getFullYear()].join("-");
                }
            }
                    	
                    	
                   function ValidateTextAppend(input, index) {
                  var val = input;
                  if (val == null || val == "null" || val == "Null" || val == "NULL" || val.length == 0 || val == "undefined") {

                    val = "";
                    for (var i = 0; i < index; i++) {
                        val += " ";
                    };
                } else {
                    if (val.length >= index) {

                    } else {
                        val = input;
                        for (var i = 0; i < (index - val.length ); i++) {
                            val += " ";
                        };
                    }

                }
                return val;
            } 	
                    
                    
                    
                    
                    
                    
                    
                    
                    });
        
        
        
        
        $seatPicker.on('pageshow', function (event) {
        	var el = $('#seatMapPickerContainer', this),
        	seat = _flightForCheckin.segments[_flightForCheckin.currentSegment].seat;
        	seatMapDrawing.drawSeatMap(el, seat);
        
        });
        
        $seatPicker.on('pagebeforehide', function (event) {
        	_flightForCheckin.segments[_flightForCheckin.currentSegment].seat = seatMapDrawing.getselectedSeat();
        });
    },
    
    _initTripDetail = function(){
        var seg = _flightForDetails.segments[0];
	    $('#tripDetail-title').text(seg.from + ' to ' + seg.to);
	    $('#tripDetail-flightNum').text(seg.flightNum);
	    $('#tripDetail-depart').text(seg.departDate + ' at ' + seg.time);
	    $('#tripDetail-seat').text(seg.seat);
	    seg = _flightForDetails.segments[1];
	    $('#tripDetail-return-title').text(seg.from + ' to ' + seg.to);
	    $('#tripDetail-return-flightNum').text(seg.flightNum);
	    $('#tripDetail-return-depart').text(seg.departDate + ' at ' + seg.time);
        $('#tripDetail-return-seat').text(seg.seat);
    },
    
    _initBoardingPass = function(){
        currentseg = _flightForCheckin.segments[_flightForCheckin.currentSegment];

	    $('#boardingpass-cnum').text(_flightForCheckin.cNum);
	    $('#boardingpass-passenger').text(_customerData.firstName + ' ' + _customerData.lastName);
	    $('#boardingpass-seat').text(currentseg.seat);
	    $('#boardingpass-gate').text(currentseg.gate);
	    $('#boardingpass-depart').text(currentseg.time);
	    var flight = currentseg.flightNum + ':' + currentseg.from + ' to ' + currentseg.to;
	    $('#boardingpass-flight').text(flight);
    },
    
    _initHome = function(){
        if (!_login) {
	    	$.mobile.changePage("#logon", { transition: "flip" });
	    	$('#login').submit(function () {
	    		$(this).hide();
	    		_login = true;
	    		airData.logOn($('#userName').val(), $('#pwd').val(),_handleLogOn);
	    		return false;
	    	});
	    }
    },
    
    _initCheckIn = function(){
        var currentseg = _flightForCheckin.segments[_flightForCheckin.currentSegment],
	    seat = currentseg.seat,
	    flight = currentseg.from + ' to ' + currentseg.to;
	    $('#checkIn-flight-number').text(currentseg.flightNum);
	    $('#checkIn-flight-destination').text(flight);
        
	    $('#checkIn-seat').text(seat);
    },
    
    _handleLogOn = function (response, success) {
		if (success) {
			//alert(ff+success);
			_ffNum = response;
			
			
			   var rowdata = response;
                        veheditdata_edit = rowdata;
			
			alert(veheditdata_edit );
		//	alert(_ffNum[0].blockName);
			
			  	           $('#ffname').text(response[0].blockName);
                           	
                           	$('#ffnum').text(response[0].propertyId);
		                    $('#currentStatus').text(response[0].propertyNo);
		                    $('#miles').text(response[0].personId);
			
			airData.getDataforFF(response,_handleDataForFF);
		}
	},
    
    _handleDataForFF = function (data) {
    	//alert("coming here");
    //	alert(data[0].issueSubject);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
       // $flightList = $('#myTripsListView');
		//_customerData = data;
		//$('#ffname').text(data.blockName);
		//$('#ffnum').text(data.ffNum);
		//$('#currentStatus').text(data.status);
		//$('#miles').text(data.miles);
	/*	$('#numberOfFlights').text(data.flights.length);
		for (var i in data.flights) {
			var flight = data.flights[i],
            currentSegment = flight.segments[flight.currentSegment];
			$flightList.append('<li id="' + flight.id + '"><a href="#tripDetail" data-transition="slide">' + currentSegment.from + ' to ' + currentSegment.to + '</a></li>');
			var item = $('#' + flight.id, $flightList);
			item.data('flight', flight);
			if (flight.timeToCheckIn) {

				item.addClass('checkIn');
				$('a', item).attr('href', '#checkIn');
			}
			else {
				item.addClass('tripDetail');
			}
		}
		$.mobile.changePage('#home', { transition: 'flip' });*/

	};
    
    return {
        run:run,
    };
}();