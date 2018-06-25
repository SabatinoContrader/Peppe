<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Car"%>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="pcarpet.css">
</head>
<body>

	<%
		List<Car> cars = (List<Car>) request.getAttribute("cars");
	%>

	<h1 class="element-margin-left">Cerca Parcheggio</h1>

	<div class="col-md-6 mb-3">
		<input id="autocomplete" class="form-control form-control-lg"
			type="text" placeholder="Inserisci destinazione">
		</text>
		</br> <select class="form-control form-control-lg">
			<option value="" hidden>Seleziona auto</option>
			<%
				for (Car car : cars) {
			%>
			<option value="" class=""><%=car.getName()%></option>
			<%
				}
			%>
		</select> </br>

		<div style="float: left;">
			<button id="startsearch"
				class="btn btn-lg btn-primary btn-block back-button">Cerca</button>

		</div>
		</br> </br>
		<div id="map" class="map-place"></div>
		</br>
		<form style="display: inline-block;" action="FindCarPlaceServlet"
			method="post">
			<button class="btn btn-lg btn-primary btn-block back-button"
				type="submit" name="richiesta" value="Indietro">Indietro</button>
		</form>
	</div>





	<script>
		var markers = [];
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
			}
		};
		function myMap() {
			
			var mapOptions = {
				center : new google.maps.LatLng(41.9, 12.48),
				zoom : 10,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				disableDefaultUI : true,
				zoomControl : true
			}
			var map = new google.maps.Map(document.getElementById("map"),
					mapOptions);
			
			var geocoder = new google.maps.Geocoder();
			google.maps.event.addListener(map, "dragend", function(event) {
				loadCarSlots(map.getCenter().lat(), map.getCenter().lng());
			});
			google.maps.event.addListener(map, "zoom_changed", function(event) {
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
				var url = 'MarkersServlet';
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
						var objArray = JSON.parse(http.responseText);
						var infoWindow = new google.maps.InfoWindow(), marker, i;
						var title = [];

						for (var i = 0; i < objArray.length; i++) {
							var obj = objArray[i];
							var latLng = new google.maps.LatLng(obj.lat,
									obj.lng);

							var freeCarPlaces = getFreeCarPlaces(obj.carplaceList);

							var info = "<h3>" + obj.address + "</h3>"
							+ "<br> Tipo: " + obj.type
							+ "<br> Numero posti: "
							+ obj.carplaceList.length
							+ "<br> Disponibli: " + freeCarPlaces
							+ "<br><a>Indicazioni</a>";
							
							if (obj.type == "privato")

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
											infoWindow.setContent(title[i]);
											infoWindow.open(map, marker);
										}
									})(marker, i));
							markers.push(marker);

						}
						//var parser = new DOMParser();
						//var xmlDoc = parser.parseFromString(response,"text/xml");
					}
				}
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
		src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&language=en&key=AIzaSyAUf_fIZF0iu40Uiwhj3RhFE3Kd1KrWUFw&callback=myMap"></script>


</body>
</html>