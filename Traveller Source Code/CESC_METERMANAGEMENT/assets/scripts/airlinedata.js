var airlinesData = function(timeToLoad) {
	this.busyTime = timeToLoad || 1000;
};

airlinesData.prototype = function() {
	var ffInfo="";
	var ffInfo1 = {
		firstName: 'Jaxon', lastName: 'Daniels', ffNum: '12345678', status: 'Diamond', miles: 55555,
    
		flights: [
			{
				id: 1111, cNum: 'ABCDED',timeToCheckIn:true,currentSegment:0,
				segments: [
					{ from: 'SEA', to: 'BOS', departDate: '6/1/2012', time: '5:00PM', flightNum: '111', seat: '5A', gate: 'C10' },
					{ from: 'BOS', to: 'SEA', departDate: '6/11/2012', time: '4:00PM', flightNum: '122', seat: '5D', gate: 'A1' }
				]
			},{
				id: 1112, cNum: 'DSLEMS', timeToCheckIn: false,currentSegment:0,
				segments: [
					{ from: 'SEA', to: 'SAN', departDate: '6/13/2012', time: '5:00PM', flightNum: '113', seat: '5A', gate: 'D10' },
					{ from: 'SAN', to: 'SEA', departDate: '6/17/2012', time: '4:00PM', flightNum: '124', seat: '5D', gate: 'B1' }
				]
			}
		]
	},
    
	getDataforFF = function(response, callback) {
		fauxAjax(function () {
			
			  var veheditdata_edit;
			  
			     var data_edit_ps=0;
               var das="";
               var dad="";
               var lrd="";
               var tud="";
               var tcd="";
               var trd="";
               var tsd="";
			  
			//alert(response[0].personId);
			 var url = "http://192.168.2.172:8080/bfm_acq/";
			
  data = {
					"personId" : 4223
				};

                 
                 
               
                    // alert(category+"1");
                       myAjaxCall = $.post(url + "openNewTickets/openTicketsReadOnlyPerson", data, function(data) {
                    alert(data);
                    isneedtoKillAjax = false;
                    if (data == null) {
                        $('#dvLoadingbody').hide();
                        $('#dvLoading').hide();

                        alert("Request Failed ,try Again Later");
                        //  stopAnimation();
                    } else {
                    	
                    	
                    	
                    	
                    	  var index_edit = 0;
                  $.each(data, function(key, val) {
						    var tr1 = "<tr >";
                            var td1 = "<td>" + ValidateTextAppend(convert(val.ticketCreatedDate,7)) + " </td>";
                            var td2 = "<td>" + ValidateTextAppend(val.ticketNumber,7) + "</td>";
                            var td6 = "<td>" + ValidateTextAppend(val.priorityLevel,7) + "</td>";
                            var td7 = "<td>" + ValidateTextAppend(val.topicName,7) + "</td>";
                            var td8 = "<td>" + ValidateTextAppend(val.issueSubject,7) + "</td>";
                            var td9 = "<td>" + ValidateTextAppend(val.issueDetails,7) + "</td>";
                            var td10 = "<td>" + ValidateTextAppend(val.dept_Name,7) + "</td>";
                            var td11 = "<td>" + ValidateTextAppend(val.ticketStatus,7) + "</td>";
                            var td12 = "<td>" + ValidateTextAppend(val.deptAcceptanceStatus,7) + "</td>";
                            var td13 = "<td>" + ValidateTextAppend(convert(val.deptAcceptedDate,7)) + "</td>";
                            var td14 = "<td>" + ValidateTextAppend(convert(val.lastResonse,7)) + "</td>";
                            var td15 = "<td>" + ValidateTextAppend(convert(val.ticketUpdateDate,7)) + "</td>";
                            var td16 = "<td>" + ValidateTextAppend(convert(val.ticketClosedDate,7)) + "</td>";
                            var td17 = "<td>" + ValidateTextAppend(convert(val.ticketReopenDate,7)) + "</td>";
                            var td18 = "<td>" + ValidateTextAppend(convert(val.ticketAssignedDate,7)) + "</td>"; 
                       
								/*if(val.ticketStatus=="Open"){
								opentickets++;
							}
							else if(val.ticketStatus=="Re-Open"){
								reopentickets++;
							}
							else if(val.ticketStatus="Assigned"){
								assignedtickets++;
							}
							else{
								closedtickets++;
							}*/
                                callback(data);
                               $('#movie-list').append('<li><a href="" data-id="' + index_edit + '"><img  class="image-wrapp" style="height: 50px; width: 50px;  id="image_id" src="images/image_complaint.png"/><h2 class="myHeader">Ticket No:' + td2 + '</h2><p class="myParagraph">&nbsp;&nbsp;Created date:' + td1 + '</p><p class="myParagraph">&nbsp;&nbsp;Status:&nbsp;<b><font color = "blue">' + td11 + '</font></b>&nbsp;</p></a></li>');
							
							index_edit++;
						  });
                    	
                    	
     
                    	
                    	
                    	
                    	
                    	
                    	
                    	
                    	//alert(data);
                    	$.mobile.changePage('#home', { transition: 'flip' });
                    }
                    	//alert(data);
			
			
			
			  });
			
			
			
			               	
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
                    	
			
			
			
			
			
			
		}, 'be patient ...', this);
		
	//	$.mobile.changePage('#home', { transition: 'flip' });
	},
    
	logOn = function (uid, pwd, callback) {
		fauxAjax(function () {
			var data="";
			  var timeout=10000;
			 var url = "http://192.168.2.172:8080/bfm_acq/";
			 var propertyId="";
			// alert(uid+pwd);
			 data = {
					"userMailId" : uid,
					"password" : pwd
				 };
			 
			var myAjaxCall = $.post(url + "mobileLoginCustomers", data, function(data, textStatus, jqXHR) {
					//isneedtoKillAjax = false;
					if (data == "invalid") {
						//$('#dvLoadingbody').hide();
						//$('#username').val("");
						//$('#password').val("");
						alert("Invalid Credential");
					} else if (data == null) {
						//$('#dvLoadingbody').hide();
						alert("Request Failed ,try Again Later");
					} else if (data == "success") {
					//	$.jStorage.set("username", userName);
						
							
						 alert(uid+url);
						//	$('#dvLoadingbody').hide();
                            $.ajax({
                            type : "GET",
                            url:url+ "readInformation",
                           contentType : "application/json",
                           data : {
                            "username":uid    
                            },
                           success : function(response) {
                          	alert(response);
                           	 ffInfo = response;
                           	 
                           	 callback(response, true);
                          // 	$.mobile.changePage('#home', { transition: 'flip' });
                           	
                           /* $.each(response,function(key,value){ 
                            	alert(value.propertyId);
                            	propertyId=value.propertyId;
                            //propertyid1=value.propertyId;
                           // blockname1=value.blockName;
                          //  personid1=value.personId;
						   // $.jStorage.set("prpid",propertyid1);
						  //  $.jStorage.set("bname",blockname1);
						  //  $.jStorage.set("pid",personid1); 
                            //  alert(propertyid1+blockname1+personid1);
                           // openAndroidDialog();
                          });*/
                        },
                        error : function(x, t, m) {
                                alert("Request Failed ,try Again Later");
                        }
                        
                        });
						

					   } 
				     });
				    // Fire the checkajaxkill method after 10 seonds
				            setTimeout(function() {
					        checkajaxkill();
				         }, timeout); 
			
			
			 function checkajaxkill() {
					    if (isneedtoKillAjax) {
						$('#dvLoadingbody').hide();
						myAjaxCall.abort();
						alert('Request Failed ,try Again Later');
					    } else {
						
					    }
				   }
			
			
		}, 'please wait ...', this);
		
		
	},
    
	fauxAjax = function fauxAjax(func, text, thisObj) {
		$.mobile.loading('show', { theme: 'a', textVisible: true, text:text });
		window.setTimeout(function () {
			$.mobile.loading('hide');
			func();
           
		}, thisObj.busyTime);
	};
    
	return{
		logOn:logOn,
		getDataforFF:getDataforFF
	}
}();