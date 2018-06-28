<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
</head>
<body>


	<h1 class="element-margin-left">Cerca Parcheggio</h1>

	<div class="col-md-6 mb-3">
		<input id="autocomplete" class="form-control form-control-lg"
			type="text" placeholder="Inserisci destinazione">
		</text>
		</br> <select class="form-control form-control-lg">
			<option value="" hidden>Seleziona auto</option>


			<c:forEach items="${cars}" var="car">
				<option value="" class="">${car.name}</option>
			</c:forEach>
		</select> </br>

		<div style="float: left;">
			<button id="startsearch"
				class="btn btn-lg btn-primary btn-block back-button" style="witdh: 166px; float: left; margin-left:10px;">Cerca</button>
			
				<button id="seleziona" class="btn btn-lg btn-primary" style="witdh: 166px; float: left; margin-left:10px;" disabled>Parti</button>
				<button id="cambia" class="btn btn-lg btn-primary" style="witdh: 166px; float: left; margin-left:10px;" disabled>Cambia parcheggio</button>

		</div>
		</br> </br>
		<div id="map" class="map-place"></div>
		</br>
		<form style="display: inline-block;" action="/Home/dispatchHome"
			method="post">
			<button class="btn btn-lg btn-primary btn-block back-button"
				type="submit" name="richiesta" value="Indietro">Indietro</button>
		</form>
	</div>





	<script>
		var markers = [];

		function myMap() {
			
			var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
			var icons = {
				parking : {
					icon : iconBase + 'parking_lot_maps.png'
				},
				library : {
					icon : iconBase + 'library_maps.png'
				},
				info : {
					icon : iconBase + 'info-i_maps.png'
				},
				start : {
					url : iconBase + 'cabs.png',
					scaledSize : new google.maps.Size(25,25)
				}
			};

			var mapOptions = {
				center : new google.maps.LatLng(41.9, 12.48),
				zoom : 10,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				disableDefaultUI : true,
				zoomControl : true
			}
			
			var isInSearchDirectionMode = false;
			var map = new google.maps.Map(document.getElementById("map"),
					mapOptions);

			var directionsService = new google.maps.DirectionsService();
			var directionsRenderer = null;
			
			document.getElementById("cambia").addEventListener("click", function(){
				isInSearchDirectionMode = false;
				directionsRenderer.setMap(null);
				directionsRenderer = null;
				document.getElementById("cambia").disabled = true;
				document.getElementById("seleziona").disabled = true;
				deleteMarkers();
				loadCarSlots(map.getCenter().lat(), map.getCenter().lng());
			});				 
			
			function goPark()
			{
				// button "parti"
			}
			
			var geocoder = new google.maps.Geocoder();
			google.maps.event.addListener(map, "dragend", function(event) {
				if(isInSearchDirectionMode == false)
				loadCarSlots(map.getCenter().lat(), map.getCenter().lng());
			});
			google.maps.event.addListener(map, "zoom_changed", function(event) {
				if(isInSearchDirectionMode == false)
				deleteMarkersZoom(map);
			});
			var input = document.getElementById('autocomplete');
			var autocomplete = new google.maps.places.Autocomplete(input);
			function geocodeAddress(geocoder, map) {
				var address = document.getElementById('autocomplete').value;
				geocoder
						.geocode(
								{
									'address' : address
								},
								function(results, status) {
									if (status === 'OK') {
										map
												.setCenter(results[0].geometry.location);
										map.setZoom(15);
										loadCarSlots(map.getCenter().lat(), map
												.getCenter().lng());
									} else {
										alert('Geocode was not successful for the following reason: '
												+ status);
									}
								});
			}
			document.getElementById('startsearch').addEventListener('click',
					function() {
						geocodeAddress(geocoder, map);
					});
			document.getElementById("autocomplete").addEventListener('keydown',
					function(event) {
						//keycode 13 = Enter
						if (event.keyCode === 13) {
							event.preventDefault();
							geocodeAddress(geocoder, map);
							//document.getElementById("id_of_button").click();
						}
					});
			//la chiamata viene indirizzata direttamente a doPost o doGet se c'è su service
			//dunque se necessatio creare un altro servlet
			function loadCarSlots(lat, lng) {
				var http = new XMLHttpRequest();
				var url = '/updateParkings'; //window.location.href
				var params = 'lat=' + lat + '&lng=' + lng + ''; //op=getMarkers&
				http.open('POST', url, true);
				//Send the proper header information along with the request
				http.setRequestHeader('Content-type',
						'application/x-www-form-urlencoded');
				http.send(params);
				http.onreadystatechange = function() {//Call a function when the state changes.
					if (http.readyState == 4 && http.status == 200) {

						//rimuovo i markers precedenti
						deleteMarkers();
						var objDTOlist = JSON.parse(http.responseText);
						var infoWindow = new google.maps.InfoWindow(), marker, i;					
						
						var title = [];
						for (var i = 0; i < objDTOlist.length; i++) {
							var obj = objDTOlist[i];

							var latLng = new google.maps.LatLng(
									obj.slot.latitude, obj.slot.longitude);
							var freeCarPlaces = getFreeCarPlaces(obj.carplace);
							var info = "<h3>" + obj.slot.address + "</h3>"
									+ "<br> Tipo: " + obj.slot.type
									+ "<br> Numero posti: "
									+ obj.carplace.length + "<br> Disponibli: "
									+ freeCarPlaces + "<br><a id='indications'>Indicazioni</a>";
									

							if (obj.slot.type == "privato")
								info = info + "<br><a>Prenota</a>";

							title[i] = info;
							// Creating a marker and putting it on the map
							var marker = new google.maps.Marker({
								position : latLng,
								map : map,
								//title: data.title
								icon : icons["parking"].icon
							//scaledsize: new google.maps.Size(64,64);
							});
						
							
							google.maps.event.addListener(marker, 'click',
									(function(marker, i) {
										return function() {		
											google.maps.event.clearListeners(infoWindow, 'domready');
											infoWindow.setContent(title[i]);
											addInfoWindowDomreadyListener(marker);
											infoWindow.open(map, marker);
										}
									})(marker, i));
						
							function addInfoWindowDomreadyListener(marker)
							{
	 						google.maps.event.addListener(infoWindow, 'domready', function() {
	 							
 								//document.getElementById(obj.slot.address).addEventListener("click", GoogleMap_selected(obj.slot.latitude,obj.slot.longitude) );
	 							document.getElementById("indications").addEventListener("click", function() {

	 						        var from = new google.maps.LatLng(41.9, 12.48);
	 						        var to = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());
	 						        //var to = new google.maps.LatLng(41.909954, 12.494329);
									
	 						        //directionsService = new google.maps.DirectionsService();
	 						        
	 						         var directionsRequest = {
	 						           origin: from,
	 						           destination: to,
	 						           travelMode: google.maps.DirectionsTravelMode.DRIVING,
	 						           unitSystem: google.maps.UnitSystem.METRIC
	 						         };

	 						         directionsService.route(
	 						                  directionsRequest,
	 						                  function(response, status)
	 						                  {

	 						                    if (status == google.maps.DirectionsStatus.OK)
	 						                    {
	 						                    	directionsRenderer = new google.maps.DirectionsRenderer({
	 						                        map: map,
	 						                        directions: response,
	 						                        suppressMarkers: true

	 						                      });
	 						                    //directionsRenderer.setDirections(response);
	 						                     deleteMarkers();
	 						                    }
	 						                    else
	 						                        {
	 						                         alert("Unable to retrive route");
	 						                        }
	 						                var leg = response.routes[ 0 ].legs[ 0 ];
	 						                makeMarker( leg.start_location, icons["start"], "title" );
	 						                makeMarker( leg.end_location, icons["parking"].icon, 'title' );
	 						                  }
	 						                );	 
	 						        isInSearchDirectionMode = true;
	 						        document.getElementById("seleziona").disabled = false;
	 						       document.getElementById("cambia").disabled = false;
	 							});
							});
							}
	 						
							markers.push(marker);
						}						

					}
				}
			}
						
			 
			 function makeMarker( position, icon, title ) {
				 var marker = new google.maps.Marker({
				  position: position,
				  map: map,
				  icon: icon,
				  title: title
				 });
				 markers.push(marker);
				}

			function getFreeCarPlaces(carplaces) {
				var count = 0;
				for (var i = 0; i < carplaces.length; i++) {
					if (!carplaces[i].busy)
						count++;
				}
				return count;
			}

			// Sets the map on all markers in the array.
			function setMapOnAll(map) {
				for (var i = 0; i < markers.length; i++) {
					markers[i].setMap(map);
				}
			}
			// Removes the markers from the map, but keeps them in the array.
			function clearMarkers() {
				setMapOnAll(null);
			}
			// Shows any markers currently in the array.
			function showMarkers() {
				setMapOnAll(map);
			}
			// Deletes all markers in the array by removing references to them.
			function deleteMarkersZoom(map) {
				if (map.getZoom() < 15) {
					console.log("zoom: " + map.getZoom());
					clearMarkers();
				} else {
					showMarkers();
				}
			}
			function deleteMarkers() {
				clearMarkers();
				markers = [];
			}
		}
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&language=en&key=AIzaSyAsEE6wc-sX5HniWTVBPTSZgHOwN_cf5F0&callback=myMap"></script>


</body>
</html>