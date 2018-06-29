<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Javascript -->
<script src="/css/googleApiManager.js"></script>
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
		
		<h3>Inserisci la durata della sosta</h3></br>
		<p id='slot'>Slot: </p>
		<select id='select' name='minute' disabled>
		<option value='15'>15 min</option>
		<option value='30'>30 min</option>
		<option value='45'>45 min</option>
		<option value='60'>1 h</option>
		<option value='75'>1 h 15 min</option>
		<option value='90'>1 h 30 min</option>
		<option value='105'>1 h 45 min</option>
		<option value='120'>2 h</option>
	    </select></br>
	    <p id='newprice'>Prezzo: </p>
	    <button id="payandgo"disabled>Paga ed inizia</button>
		</br></br>
		
		<form style="display: inline-block;" action="/Home/dispatchHome"
			method="post">
			<button class="btn btn-lg btn-primary btn-block back-button"
				type="submit" name="richiesta" value="Indietro">Indietro</button>
		</form>
	</div>





	<script>
	function myMap() {
		var googleApiManager = new GoogleApiManager('map',41.9, 12.48,'/updateParkings');
			googleApiManager.selectAutoCompleteTextbox('autocomplete','startsearch');
			googleApiManager.selectDirectionModeBackButton('cambia');
			googleApiManager.selectRunTurnByTurnButton('seleziona');
		};
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&language=en&key=AIzaSyAsEE6wc-sX5HniWTVBPTSZgHOwN_cf5F0&callback=myMap"></script>


</body>
</html>