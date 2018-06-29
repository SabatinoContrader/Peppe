		

function GoogleApiManager(mapId ,latitude, longitude, serverUrl)
{
	//Server
	this.serverUrl = serverUrl;
		
	//DOM elements
	this.mapDOM = document.getElementById(mapId);
	this.directionModeBackButtonDOM;
	this.runTurnByTurnButtonDOM;
	this.autoCompleteTextboxDOM;
	this.autoCompleteTextboxSubmitButtonDOM;
	
	//Mode flags 
	this.isInSearchDirectionMode = false;
	
	//icons vars
	this.iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
	this.icons = [];
	
	//markers vars
    this.markers = [];
    this.currentSelectedMarker;
    
    //google map
    this.map;
    
    //InfoWindow
    this.infoWindow = new google.maps.InfoWindow(),
    
    //Directions API objects
	this.directionsService = new google.maps.DirectionsService();
	this.directionsRenderer = null;
	
	this.DirectionModeStartLatitude;
	this.DirectionModeStartLongitude;
	
	//Geocoding API objects
	this.geocoder = new google.maps.Geocoder();
	
	//Autocomplete API objects
	this.autocomplete = null;

    //init icons
    this.Initicons();
    
    //init google map
    this.map = this.InitMap(latitude,longitude);
    
    //init google map events
    this.InitGoogleMapsEvents();
    
    //init infoWindow events
    //this.InitInfoWindowEvents();     
    
    this.tariffaOraria;
    
    this.markerMap = new Map();
    this.InitChangeSelectMinuteEvent();
};
  

GoogleApiManager.prototype.Initicons = function()
{
	this.iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
	this.icons = {
			parking : {
				icon : this.iconBase + 'parking_lot_maps.png'
			},
			library : {
				icon : this.iconBase + 'library_maps.png'
			},
			info : {
				icon : this.iconBase + 'info-i_maps.png'
			},
			start : {
				url : this.iconBase + 'cabs.png',
				scaledSize : new google.maps.Size(25,25)
			}
		};
};

GoogleApiManager.prototype.InitMap = function(latitude,longitude)
{
	var mapOptions = {
			center : new google.maps.LatLng(latitude, longitude),
			zoom : 10,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			disableDefaultUI : true,
			zoomControl : true
		}
	return map = new google.maps.Map(this.mapDOM,
			mapOptions);
};

GoogleApiManager.prototype.doAjaxForNearSlots = function(latitude,longitude)
{
	var self = this;
	var http = new XMLHttpRequest();
	var url = self.serverUrl; //window.location.href '/updateParkings'
	var params = 'lat=' + latitude + '&lng=' + longitude + ''; //op=getMarkers&
	http.open('POST', url, true);
	//Send the proper header information along with the request
	http.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');
	http.send(params);
	http.onreadystatechange = function() {//Call a function when the state changes.
		if (http.readyState == 4 && http.status == 200) {

			//rimuovo i markers precedenti
			self.deleteMarkers();
			var objDTOlist = JSON.parse(http.responseText);
			//var infoWindow = new google.maps.InfoWindow(), marker, i;					
			
			var title = [];
			for (var i = 0; i < objDTOlist.length; i++) {
				var obj = objDTOlist[i];

				var latLng = new google.maps.LatLng(
						obj.slot.latitude, obj.slot.longitude);
				var freeCarPlaces = self.getFreeCarPlaces(obj.carplace);
				var info = "<h3>" + obj.slot.address + "</h3>"
						+ "<br> Tipo: " + obj.slot.type
						+ "<br> Tariffa oraria: " + obj.slot.price + "\u20AC"
						+ "<br> Numero posti: "
						+ obj.carplace.length + "<br> Disponibli: "
						+ freeCarPlaces 
						+ "<br><a id='indications'>Indicazioni</a>"
						+ "<br><a id='sosta'>Inizia sosta</a>";
						
				if (obj.slot.type == "privato")
					info = info + "<br><a>Prenota</a>";

				title[i] = info;
				
				//make markers
				var marker = self.makeMarker(latLng,self.icons["parking"].icon);
				self.markerMap.set(marker, obj);
				self.AddMarkerEvent(marker,title[i], obj);
			}
		}
	}
};

GoogleApiManager.prototype.selectDirectionModeBackButton = function(backButtonID)
{
	var self = this;
	this.directionModeBackButtonDOM = document.getElementById(backButtonID);
	
	document.getElementById(backButtonID).addEventListener("click", function(){
		self.isInSearchDirectionMode = false;
		self.directionsRenderer.setMap(null);
		self.directionsRenderer = null;
		self.directionModeBackButtonDOM.disabled = true;
		self.runTurnByTurnButtonDOM.disabled = true;
		self.deleteMarkers();
		self.geocodeCoordinates(self.DirectionModeStartLatitude, self.DirectionModeStartLongitude);
		
		//self.doAjaxForNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng());
	});	
};

GoogleApiManager.prototype.selectRunTurnByTurnButton = function(turnByTurnButtonID)
{
	this.runTurnByTurnButtonDOM = document.getElementById(turnByTurnButtonID);
	
	document.getElementById(turnByTurnButtonID).addEventListener("click", function(){
		//document.getElementById(turnByTurnButtonID).disabled = true;
	});	
};

GoogleApiManager.prototype.InitGoogleMapsEvents = function()
{
	var self = this;
	google.maps.event.addListener(self.map, "dragend", function(event) {
		if(self.isInSearchDirectionMode == false)
			self.doAjaxForNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng());
	});
	google.maps.event.addListener(self.map, "zoom_changed", function(event) {
		if(self.isInSearchDirectionMode == false)
			self.deleteMarkersZoom();
	});
};

GoogleApiManager.prototype.selectAutoCompleteTextbox = function(autoCompleteTextboxId,autoCompleteSubmitButtonID)
{
	this.autoCompleteTextboxDOM = document.getElementById(autoCompleteTextboxId);
	this.autoCompleteTextboxSubmitButtonDOM = document.getElementById(autoCompleteSubmitButtonID);
	this.autocomplete = new google.maps.places.Autocomplete(this.autoCompleteTextboxDOM);
	
    //init geocode address events
    this.InitGeocodeAddressEvents();
};

//call only after selectAutoCompleteTextbox()
GoogleApiManager.prototype.geocodeAddress = function()
{
	var self = this;
	var address = this.autoCompleteTextboxDOM.value;
	this.geocoder.geocode(
					{
						'address' : address
					},
					function(results, status) 
					{
						if (status === 'OK')
						{
							this.map.setCenter(results[0].geometry.location);
							this.map.setZoom(15);
							self.doAjaxForNearSlots(this.map.getCenter().lat(), this.map.getCenter().lng());
						} else 
						{
							alert('Geocode was not successful for the following reason: '
									+ status);
						}
					});
};

GoogleApiManager.prototype.geocodeCoordinates = function(latitude,longitude)
{
	var self = this;
	var location = new google.maps.LatLng(latitude, longitude);
	this.geocoder.geocode(
					{
						'location' : location
					},
					function(results, status) 
					{
						if (status === 'OK')
						{
							this.map.setCenter(results[0].geometry.location);
							this.map.setZoom(15);
							self.doAjaxForNearSlots(this.map.getCenter().lat(), this.map.getCenter().lng());
						} else 
						{
							alert('Geocode was not successful for the following reason: '
									+ status);
						}
					});
};

//call only after selectAutoCompleteTextbox()
GoogleApiManager.prototype.InitGeocodeAddressEvents = function()
{
	var self = this;
	this.autoCompleteTextboxSubmitButtonDOM.addEventListener('click',
			function() {
				self.geocodeAddress();
			});
	this.autoCompleteTextboxDOM.addEventListener('keydown',
			function(event) {
				//keycode 13 = Enter
				if (event.keyCode === 13) {
					event.preventDefault();
					self.geocodeAddress();
				}
			});
};

GoogleApiManager.prototype.AddMarkerEvent = function(marker,content)
{
	var self = this;
	google.maps.event.addListener(marker, 'click',
			(function(marker,content) {
				
				return function() {	
					self.currentSelectedMarker = marker;
					
					google.maps.event.clearListeners(self.infoWindow, 'domready');
					self.infoWindow.setContent(content);
					self.InitInfoWindowEvents(marker);
					self.infoWindow.open(self.map, marker);
				}
			})(marker,content));
};

GoogleApiManager.prototype.InitInfoWindowEvents = function(marker)
{
		var self = this;		
		google.maps.event.addListener(self.infoWindow, 'domready', function() {
			
			document.getElementById("indications").addEventListener("click", function(){
				self.StartDirectionsRequest(marker);
			});
			
			document.getElementById("sosta").addEventListener("click", function() {
				self.StartStop(marker);
			});
		});
};

GoogleApiManager.prototype.StartStop = function(marker){
	var obj = this.markerMap.get(marker);
	var select = document.getElementById("select");
	select.disabled = false;
	document.getElementById("payandgo").disabled = false;
	select.value = 15;
	document.getElementById("slot").innerHTML = "Slot: "+ obj.slot.address;
	var price = obj.slot.price;
	var minute = select.value;
	var newprice = document.getElementById("newprice");
	newprice.innerHTML = "Prezzo: " + (price / 60) * minute + "\u20AC";
	
}

GoogleApiManager.prototype.InitChangeSelectMinuteEvent = function()
{
	var self = this;
	var select = document.getElementById("select");
	select.addEventListener('change', function() {
		var obj = self.markerMap.get(self.currentSelectedMarker);
		var min = select.value;
		var pay = (obj.slot.price / 60) * min;
		document.getElementById("newprice").innerHTML = "Prezzo: " + pay + "\u20AC";
	});
}

GoogleApiManager.prototype.StartDirectionsRequest = function(marker)
{

    var from = new google.maps.LatLng(41.9, 12.48);
    var to = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());

     var directionsRequest = {
       origin: from,
       destination: to,
       travelMode: google.maps.DirectionsTravelMode.DRIVING,
       unitSystem: google.maps.UnitSystem.METRIC
     };

     var self = this;
     this.directionsService.route(
              directionsRequest,
              function(response, status)
              {

                if (status == google.maps.DirectionsStatus.OK)
                {
                	self.directionsRenderer = new google.maps.DirectionsRenderer({
                    map: self.map,
                    directions: response,
                    suppressMarkers: true

                  });
                self.deleteMarkers();
                }
                else
                    {
                     alert("Unable to retrive route");
                    }
            var leg = response.routes[ 0 ].legs[ 0 ];
            self.makeMarker( leg.start_location, self.icons["start"] );
            self.makeMarker( leg.end_location, self.icons["parking"].icon);                       
              }
            );	
     
     self.DirectionModeStartLatitude = marker.getPosition().lat();
     self.DirectionModeStartLongitude = marker.getPosition().lng();
    	 
     self.isInSearchDirectionMode = true;
     self.runTurnByTurnButtonDOM.disabled = false;
     self.directionModeBackButtonDOM.disabled = false;
};

GoogleApiManager.prototype.makeMarker = function(position,icon)
{
	 var marker = new google.maps.Marker({
		  position: position,
		  map: this.map,
		  icon: icon,
		 });
	 this.markers.push(marker); 
	 return marker;
};

GoogleApiManager.prototype.getFreeCarPlaces = function(carplaces)
{
	var count = 0;
	for (var i = 0; i < carplaces.length; i++) {
		if (!carplaces[i].busy)
			count++;
	}
	return count;
};

//Sets the map on all markers in the array.
GoogleApiManager.prototype.setMapOnAll = function(map)
{
	var self = this;
	for (var i = 0; i < self.markers.length; i++) {
		self.markers[i].setMap(map);
	}
};

//Removes the markers from the map, but keeps them in the array.
GoogleApiManager.prototype.clearMarkers = function()
{
	var self = this;
	self.setMapOnAll(null);
};

//Shows any markers currently in the array.
GoogleApiManager.prototype.showMarkers = function()
{
	var self = this;
	self.setMapOnAll(self.map);
};

//Hidden markers when zooming to far.
GoogleApiManager.prototype.deleteMarkersZoom = function()
{
	var self = this;
	if (self.map.getZoom() < 15) {
		console.log("zoom: " + self.map.getZoom());
		self.clearMarkers();
	} else {
		self.showMarkers();
	}
};

//Deletes all markers in the array by removing references to them.
GoogleApiManager.prototype.deleteMarkers = function()
{
	var self = this;
	self.clearMarkers();
	self.markers = [];
};


