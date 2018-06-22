<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Car"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="pcarpet.css">
</head>
<body>

	<%! String latitude = "41.9"; %>
	<%! String longitude =  "12.48"; %>
	<%! String zoom =  "10"; %>
	<%
	if(request.getAttribute("latitude") != null)
	{		
		latitude = (String) request.getAttribute("latitude");
		longitude = (String) request.getAttribute("longitude");
		zoom = (String) request.getAttribute("zoom");
		
		System.out.println("latitude: " + latitude);
		System.out.println("longitude: " + longitude);
		System.out.println("zoom: " + zoom);
	}
		List<Car> cars = (List<Car>) request.getAttribute("cars");
	
	%>

	<h1>Cerca Parcheggio</h1>


	<form action="FindCarPlaceServlet" method="post">
		<input id="autocomplete" class="autocomplete-textview" type="text"
			placeholder="Inserisci destinazione">
		</text>

		<select>
			<%
				for (Car car : cars) {
			%>
			<option value=""><%=car.getName()%></option>
			<%
				}
			%>
		</select>


	</form>
	</br>

	<button id="startsearch" style="width: 55px" type="submit">Cerca</button>
	</br>
	</br>
	<div id="map" class="map-place"></div>


	<script>
		var markers = [];
	
		var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
        var icons = {
          parking: {
            icon: iconBase + 'parking_lot_maps.png'
          },
          library: {
            icon: iconBase + 'library_maps.png'
          },
          info: {
            icon: iconBase + 'info-i_maps.png'
          }
          };
		
		
		function myMap() {
			var mapOptions = {
				center : new google.maps.LatLng(<%=latitude%>, <%=longitude%>),
				zoom : <%=zoom%>,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				disableDefaultUI: true,
				zoomControl: true
			}

			var map = new google.maps.Map(document.getElementById("map"),
					mapOptions);

			var geocoder = new google.maps.Geocoder();

 			google.maps.event.addListener(map, "dragend", function(event) { 							
 				loadCarPlaces(map.getCenter().lat(),map.getCenter().lng());				
			});
 			
 			google.maps.event.addListener(map, "zoom_changed", function(event) { 
 				deleteMarkersZoom(map); 					
			});

			var input = document.getElementById('autocomplete');
			var autocomplete = new google.maps.places.Autocomplete(input);

			document.getElementById('startsearch').addEventListener('click',
					function() {
						geocodeAddress(geocoder, map);
					});
			
			//la chiamata viene indirizzata direttamente a doPost o doGet se c'è su service
			//dunque se necessatio creare un altro servlet
			function loadCarPlaces(lat,lng) {

				var http = new XMLHttpRequest();
				var url = 'MarkersServlet';
				var params = 'lat=' + lat + '&lng=' + lng + '';  //op=getMarkers&
				http.open('POST', url, true);

				//Send the proper header information along with the request
				http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

				http.send(params);
				http.onreadystatechange = function() {//Call a function when the state changes.
				    if(http.readyState == 4 && http.status == 200) { 	
				    	
				        //console.log("AAAAAAAAAAAAAAAA" + http.responseText);
				        
				        //rimuovo i markers precedenti
				        deleteMarkers();
				        
				        var objArray = JSON.parse(http.responseText);
				        
				        for (var i = 0; i < objArray.length; i++) {
				            var obj = objArray[i];
				            //console.log("AAAAAAAAAAAAAAAA" + obj.lat + ", " + obj.lng);
				            
				            var latLng = new google.maps.LatLng(obj.lat, obj.lng); 

				            // Creating a marker and putting it on the map
				            var marker = new google.maps.Marker({
				              position: latLng,
				              map: map,
				              //title: data.title
				              icon: icons["parking"].icon
				              //scaledsize: new google.maps.Size(64,64);
				              
				            });
				            markers.push(marker);		            
				        }				       			        

				        //var parser = new DOMParser();
				    	//var xmlDoc = parser.parseFromString(response,"text/xml");
				    	
				    }
				}
				
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
		    	if(map.getZoom() < 10)
		    		{
		    		console.log("zoom: " + map.getZoom());
		    		clearMarkers();
		    		}
		    	else
		    		{
		    		showMarkers();
		    		}

		      }
		      
		      function deleteMarkers() {
			        clearMarkers();
			        markers = [];
			      }

		}

		function geocodeAddress(geocoder, resultsMap) {
			var address = document.getElementById('autocomplete').value;
			geocoder
					.geocode(
							{
								'address' : address
							},
							function(results, status) {
								if (status === 'OK') {
									resultsMap
											.setCenter(results[0].geometry.location);
									var marker = new google.maps.Marker({
										map : resultsMap,
										position : results[0].geometry.location
									});
								} else {
									alert('Geocode was not successful for the following reason: '
											+ status);
								}
							});
		}
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&language=en&key=AIzaSyDTNgLH1U5z1iVMk8tTV8W8Xo3UFaocHqo&callback=myMap"></script>

	<form id="inviaCoordinate" action="FindCarPlaceServlet" method="post">
		<input id="latitude" type="text" name="latitude" value="null" hidden>
		<input id="longitude" type="text" name="longitude" value="null" hidden>
		<input id="zoom" type="text" name="zoom" value="null" hidden>
	</form>

</body>
</html>