// import { platformBrowserDynamic } from '@angular/platform-browser-dynamic'

// export class GoogleApiManagerDriver {
//     // google map
//     map;
//     serverUrl;
//     mapDOM;


//         // DOM elements  
//         directionModeBackButtonDOM;
//         runTurnByTurnButtonDOM;
//         autoCompleteTextboxDOM;
//         autoCompleteTextboxSubmitButtonDOM;
    
//         // Mode flags
//         isInSearchDirectionMode = false;
    
//         // icons vars
//         iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
//         icons = [];
    
//         // markers vars
//         markers = [];
//         currentSelectedMarker;
    
//         // InfoWindow
//         infoWindow = new google.maps.InfoWindow();
    
//         // Directions API objects
//         directionsService = new google.maps.DirectionsService();
//         directionsRenderer = null;
    
//         DirectionModeStartLatitude;
//         DirectionModeStartLongitude;
    
//         // Geocoding API objects
//         geocoder = new google.maps.Geocoder();
    
//         // Autocomplete API objects
//         autocomplete = null;
    
//         // init infoWindow events
//         // this.InitInfoWindowEvents();
//         tariffaOraria;
    
//         markerMap= new Map();
//         // this.InitChangeSelectMinuteEvent();
    
//         // select time to stop
//         selectMinuteDOM;
//         showPriceDOM;
//         slotAddressDOM;
//         payAndGoDOM;
//         selectCarChoiceDOM;
    
//         // select my position
//         findMyPositionButtonDOM;
    
//         currentLatitude;
//         currentLongitude;
    
//         freeCarPlaces = 0;
//         // this.payAndGo();


// FATTOOO
//     constructor(mapId: string, latitude: number, longitude: number, serverUrl: string)
//     {
//         // Server
//         this.serverUrl = serverUrl;
//         // init google map
//         this.mapDOM = document.getElementById(mapId);
//         this.map = this.InitMap(latitude, longitude);
                
//         // init icons
//         this.Initicons();

//         // init google map events
//         this.InitGoogleMapsEvents();
//     }


// FATTOOO
//     Initicons(): void {
//         this.iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
//         this.icons = [{
//             parking: {
//                 icon: this.iconBase + 'parking_lot_maps.png'
//             },
//             parkingGreen: {
//                 icon: '/resources/green.png'
//             },
//             parkingRed: {
//                 icon: '/resources/red.png'
//             },
//             parkingYellow: {
//                 icon: '/resources/yellow.png'
//             },
//             library: {
//                 icon: this.iconBase + 'library_maps.png'
//             },
//             info: {
//                 icon: this.iconBase + 'info-i_maps.png'
//             },
//             start: {
//                 url: this.iconBase + 'cabs.png',
//                 scaledSize: new google.maps.Size(25, 25)
//             }
//         }];
//     };


// FATTOOOOO
//     InitMap(latitude, longitude): any {
//         var mapOptions = {
//             center: new google.maps.LatLng(latitude, longitude),
//             zoom: 10,
//             mapTypeId: google.maps.MapTypeId.ROADMAP,
//             disableDefaultUI: true,
//             zoomControl: true
//         }
//         return this.map = new google.maps.Map(this.mapDOM, mapOptions);
//     };


// FATTOOOOO
//     doAjaxForNearSlots(latitude, longitude): void {
//         var self = this;
//         var http = new XMLHttpRequest();
//         var url = self.serverUrl; // window.location.href '/updateParkings'
//         var params = '?lat=' + latitude + '&lng=' + longitude + ''; 
//         http.open('GET', "/api/updateParkings" + params, true);
//         // Send the proper header information along with the request
//         http.setRequestHeader('Content-type',
//             'application/x-www-form-urlencoded');
//         http.send(null);
//         http.onreadystatechange = function () {// Call a function when the state
//             // changes.
//             if (http.readyState == 4 && http.status == 200) {
    
//                 // rimuovo i markers precedenti
//                 self.deleteMarkers();
//                 var objlist = JSON.parse(http.responseText);
//                 // var infoWindow = new google.maps.InfoWindow(), marker, i;
    
//                 var title = [];
//                 for (var i = 0; i < objlist.length; i++) {
//                     var obj = objlist[i];
    
//                     var latLng = new google.maps.LatLng(
//                         obj.latitude, obj.longitude);
//                     self.freeCarPlaces = obj.number_carplace_free;
//                     var numberCarPlaces = obj.number_carplace;
//                     var info = "<h3>" + obj.address + "</h3>"
//                         + "<br> Tipo: " + obj.type
//                         + "<br> Tariffa oraria: " + obj.price + "\u20AC"
//                         + "<br> Numero posti: " + numberCarPlaces
//                         + "<br> Disponibli: " + self.freeCarPlaces
//                         + "<br><a id='indications'>Indicazioni</a>";
    
//                     if (self.freeCarPlaces > 0)
//                         info += "<br><a id='sosta'>Inizia sosta</a>";
    
//                     if (obj.type == "privato")
//                         info = info + "<br><a>Prenota</a>";
    
//                     title[i] = info;
    
//                     // make markers
//                     var marker;
//                     if (self.freeCarPlaces == 0)
//                         marker = self.makeMarker(latLng, self.icons["parkingRed"].icon);
//                     else if (self.freeCarPlaces < 5)
//                         marker = self.makeMarker(latLng, self.icons["parkingYellow"].icon);
//                     else
//                         marker = self.makeMarker(latLng, self.icons["parkingGreen"].icon);
//                     self.markerMap.set(marker, obj);
//                     self.AddMarkerEvent(marker, title[i]);
//                 }
//             }
//         }
//     };

// FATTOOOOO
//     // pass params in map
//     doAjax(url, mapParams, success, fail, method): void {
//         var self = this;
//         var http = new XMLHttpRequest();
//         var myurl = url;
    
//         var params = "";
//         for (const [key, value] of mapParams.entries()) {
//             console.log(key, value);
//             params = params + (key + "=" + value + "&");
//         }
//         if (params.length > 0) params.slice(0, -1);
    
//         console.log("params: " + params);
    
//         http.open(method, myurl, true);
//         // Send the proper header information along with the request
//         http.setRequestHeader('Content-type',
//             'application/x-www-form-urlencoded');
//         if (params.length > 0)
//             http.send(params);
//         else
//             http.send();
//         http.onreadystatechange = function () {
//             if (http.readyState == 4 && http.status == 200) {
//                 success(http);
//             }
//             else {
//                 fail(http);
//             }
//         }
//     };


//     selectDirectionModeBackButton(backButtonID): void {
//         var self = this;
//         this.directionModeBackButtonDOM = document.getElementById(backButtonID);
    
//         document.getElementById(backButtonID).addEventListener("click", function () {
//             self.isInSearchDirectionMode = false;
//             self.directionsRenderer.setMap(null);
//             self.directionsRenderer = null;
//             self.directionModeBackButtonDOM.disabled = true;
//             self.runTurnByTurnButtonDOM.disabled = true;
//             self.deleteMarkers();
//             self.geocodeCoordinates(self.DirectionModeStartLatitude, self.DirectionModeStartLongitude);
//         });
//     };
    

//     selectRunTurnByTurnButton(turnByTurnButtonID): void {
//         this.runTurnByTurnButtonDOM = document.getElementById(turnByTurnButtonID);
    
//         document.getElementById(turnByTurnButtonID).addEventListener("click", function () {
//             // document.getElementById(turnByTurnButtonID).disabled = true;
//         });
//     };

//     InitGoogleMapsEvents(): void {
//         var self = this;
//         google.maps.event.addListener(self.map, "dragend", function (event) {
//             if (self.isInSearchDirectionMode == false)
//                 self.doAjaxForNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng());
//         });
//         google.maps.event.addListener(self.map, "zoom_changed", function (event) {
//             if (self.isInSearchDirectionMode == false)
//                 self.deleteMarkersZoom();
//         });
//     };

// FATTOOOOO
//     selectAutoCompleteTextbox(autoCompleteTextboxId, autoCompleteSubmitButtonID): void {
//         this.autoCompleteTextboxDOM = document.getElementById(autoCompleteTextboxId);
//         this.autoCompleteTextboxSubmitButtonDOM = document.getElementById(autoCompleteSubmitButtonID);
//         this.autocomplete = new google.maps.places.Autocomplete(this.autoCompleteTextboxDOM);
    
//         // init geocode address events
//         this.InitGeocodeAddressEvents();
//     };


//     selectChangeMinute(selectTagId, showPriceTagID, slotAddressId, payAndGoButtonId, selectCarChoiceTagId): void {
//         this.selectMinuteDOM = document.getElementById(selectTagId);
//         this.showPriceDOM = document.getElementById(showPriceTagID);
//         this.slotAddressDOM = document.getElementById(slotAddressId);
//         this.payAndGoDOM = document.getElementById(payAndGoButtonId);
//         this.selectCarChoiceDOM = document.getElementById(selectCarChoiceTagId);
    
//         // init chose stop time events
//         this.InitChangeSelectMinuteEvent();
//         // init pay and go event
//         this.payAndGo();
//     };


//     selectFindMyPosition(findMyPositionButtonId): void {
//         this.findMyPositionButtonDOM = document.getElementById(findMyPositionButtonId);
    
//         this.InitGeocodeFindMyPositionEvent();
//     };


//     // call only after selectAutoCompleteTextbox()
// geocodeAddress(): void {
//     var self = this;
//     var address = this.autoCompleteTextboxDOM.value;
//     this.geocoder.geocode(
//         {
//             'address': address
//         },
//         function (results, status) {
//             if (status === 1) {
//                 this.map.setCenter(results[0].geometry.location);
//                 this.map.setZoom(15);
//                 self.doAjaxForNearSlots(this.map.getCenter().lat(), this.map.getCenter().lng());
//             } else {
//                 alert('Geocode was not successful for the following reason: '
//                     + status);
//             }
//         });
//     };


//     geocodeCoordinates(latitude, longitude): void {
//         var self = this;
//         var location = new google.maps.LatLng(latitude, longitude);
//         this.geocoder.geocode(
//             {
//                 'location': location
//             },
//             function (results, status) {
//                 if (status === 1) {
//                     this.map.setCenter(results[0].geometry.location);
//                     this.map.setZoom(15);
//                     self.doAjaxForNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng());
//                 } else {
//                     alert('Geocode was not successful for the following reason: '
//                         + status);
//                 }
//             });
//     };


// FATTOOOOO
// // call only after selectAutoCompleteTextbox()
// InitGeocodeAddressEvents(): void {
//     var self = this;
//     this.autoCompleteTextboxSubmitButtonDOM.addEventListener('click',
//         function () {
//             self.geocodeAddress();
//         });
//     this.autoCompleteTextboxDOM.addEventListener('keydown',
//         function (event) {
//             // keycode 13 = Enter
//             if (event.keyCode === 13) {
//                 event.preventDefault();
//                 self.geocodeAddress();
//             }
//         });
//     };


//     InitGeocodeFindMyPositionEvent(): void {
//         var self = this;
//         this.findMyPositionButtonDOM.addEventListener('click',
//             function () {
//                 if (navigator.geolocation) {
//                     navigator.geolocation.getCurrentPosition(showPosition);
//                 } else {
//                     console.log("Geolocation is not supported by this browser.");
//                 }
    
//             });
    
//         function showPosition(position) {
//             self.currentLatitude = position.coords.latitude;
//             self.currentLongitude = position.coords.longitude;
//             self.geocodeCoordinates(self.currentLatitude, self.currentLongitude);
//         }
//     }


//     AddMarkerEvent(marker, content): void {
//         var self = this;
//         google.maps.event.addListener(marker, 'click',
//             (function (marker, content) {
    
//                 return function () {
//                     self.currentSelectedMarker = marker;
    
//                     google.maps.event.clearListeners(self.infoWindow, 'domready');
//                     self.infoWindow.setContent(content);
//                     self.InitInfoWindowEvents(marker);
//                     self.infoWindow.open(self.map, marker);
//                 }
//             })(marker, content));
//     };


//     InitInfoWindowEvents(marker): void {
//         var self = this;
//         google.maps.event.addListener(self.infoWindow, 'domready', function () {
    
//             document.getElementById("indications").addEventListener("click", function () {
    
//                 if (navigator.geolocation) {
//                     navigator.geolocation.getCurrentPosition(showPosition);
//                 } else {
//                     console.log("Geolocation is not supported by this browser.");
//                 }
    
//                 function showPosition(position) {
//                     self.currentLatitude = position.coords.latitude;
//                     self.currentLongitude = position.coords.longitude;
//                     self.StartDirectionsRequest(marker);
//                 }
//             });
    
//             var obj = self.markerMap.get(self.currentSelectedMarker);
//             if (obj.number_carplace_free > 0) {
//                 // call selectChangeMinute() before
//                 document.getElementById("sosta").addEventListener("click", function () {
//                     self.StartStop(marker);
//                 });
//             }
    
//         });
//     };


// // call selectChangeMinute() before
// StartStop(marker): void {

//     var obj = this.markerMap.get(marker);
//     this.selectMinuteDOM.disabled = false;
//     this.payAndGoDOM.disabled = false;
//     this.selectMinuteDOM.value = 15;
//     this.slotAddressDOM.innerHTML = "Slot: " + obj.address;
//     var price = obj.price;
//     var minute = this.selectMinuteDOM.value;
//     this.showPriceDOM.innerHTML = "Prezzo: " + (price / 60) * minute + "\u20AC";

// }


// payAndGo(): void {

//     var self = this;
//     this.payAndGoDOM.addEventListener('click', function () {
//         var selectedcar = self.selectCarChoiceDOM.value;
//         if (selectedcar != "") {
//             self.selectMinuteDOM.disabled = true;
//             self.payAndGoDOM.disabled = true;

//             var obj = self.markerMap.get(self.currentSelectedMarker);


//             var timeToAddFromNow = self.selectMinuteDOM.value;
//             var price = (obj.price / 60) * timeToAddFromNow;

//             var params = new Map();
//             params.set('timeToAdd', timeToAddFromNow);
//             params.set('totalPrice', price);
//             params.set('id_slot', obj.id);
//             params.set('id_car', selectedcar);

//             // /Payment
//             self.doAjax('/Payment/addPayment', params, paySuccess, payFailed, 'POST');
//         }
//         else
//             alert("Devi inserire un auto prima di iniziare la sosta!");
//     });

//     function paySuccess(http) {
//         console.log("invio pagamento riuscito: " + http.responseText);
//     }

//     function payFailed(http) {
//         console.log("errore connessione col server: " + http.responseText);
//     }
// }



// InitChangeSelectMinuteEvent(): void {
//     var self = this;
//     self.selectMinuteDOM.addEventListener('change', function () {
//         var obj = self.markerMap.get(self.currentSelectedMarker);
//         var min = this.selectMinuteDOM.value;
//         var pay = (obj.price / 60) * min;
//         self.showPriceDOM.innerHTML = "Prezzo: " + pay + "\u20AC";
//     });
// }



// StartDirectionsRequest(marker): void {

//     var from = new google.maps.LatLng(this.currentLatitude, this.currentLongitude);
//     var to = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());

//     var directionsRequest = {
//         origin: from,
//         destination: to,
//         travelMode: google.maps['DirectionsTravelMode'].DRIVING,
//         unitSystem: google.maps.UnitSystem.METRIC
//     };

//     var self = this;
//     this.directionsService.route(
//         directionsRequest,
//         function (response, status) {

//             if (status == google.maps.DirectionsStatus.OK) {
//                 self.directionsRenderer = new google.maps.DirectionsRenderer({
//                     map: self.map,
//                     directions: response,
//                     suppressMarkers: true

//                 });
//                 self.deleteMarkers();
//             }
//             else {
//                 alert("Unable to retrive route");
//             }
//             var leg = response.routes[0].legs[0];
//             self.makeMarker(leg.start_location, self.icons["start"]);
//             self.makeMarker(leg.end_location, self.icons["parking"].icon);
//         }
//     );

//     self.DirectionModeStartLatitude = marker.getPosition().lat();
//     self.DirectionModeStartLongitude = marker.getPosition().lng();

//     self.isInSearchDirectionMode = true;
//     self.runTurnByTurnButtonDOM.disabled = false;
//     self.directionModeBackButtonDOM.disabled = false;
// };


// makeMarker(position, icon): any {
//     var marker = new google.maps.Marker({
//         position: position,
//         map: this.map,
//         icon: icon,
//     });
//     this.markers.push(marker);
//     return marker;
// };


// // Sets the map on all markers in the array.
// setMapOnAll(map): void {
//     var self = this;
//     for (var i = 0; i < self.markers.length; i++) {
//         self.markers[i].setMap(map);
//     }
// };



// // Removes the markers from the map, but keeps them in the array.
// clearMarkers(): void{
//     var self = this;
//     self.setMapOnAll(null);
// };


// // Shows any markers currently in the array.
// showMarkers(): void {
//     var self = this;
//     self.setMapOnAll(self.map);
// };


// // Hidden markers when zooming to far.
// deleteMarkersZoom(): void {
//     var self = this;
//     if (self.map.getZoom() < 15) {
//         console.log("zoom: " + self.map.getZoom());
//         self.clearMarkers();
//     } else {
//         self.showMarkers();
//     }
// };


// // Deletes all markers in the array by removing references to them.
// deleteMarkers(): void {
//     var self = this;
//     self.clearMarkers();
//     self.markers = [];
//     self.markerMap.clear();
// };

// };


