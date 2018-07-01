<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Javascript -->
<script src="/css/googleApiManagerOwner.js"></script>
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
</head>
<body>


	<h1 class="element-margin-left">Gestione Parcheggi</h1>

	<div class="col-md-6 mb-3">
		</br> </br>
		<div id="map" class="map-place"></div>
		</br>
		
		<form style="display: inline-block;" action="/Home/dispatchHome"
			method="post">
			<button class="btn btn-lg btn-primary btn-block back-button"
				type="submit">Indietro</button>
		</form>
	</div>


	<div class="container">
		<h1>Lista posti auto</h1>
		</br>
		<table class="table table-striped">
			<thead>


				<tr>
					<th>ID PARCHEGGIO</th>
					<th>ID SLOT</th>
					<th>TIPO DISABILE</th>
					<th>OCCUPATO</th>
					<th>TARGA</th>
					<th>START</th>
					<th>FINISH</th>
					<th>PAGATO</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach items="${managementCarPlaceDTO}" var="carplacedto">
					<tr>

						<td>${carplacedto.id_carplace}</td>
						<td>${carplacedto.id_slot}</td>
						<td>${carplacedto.type}</td>
						<td>${carplacedto.busy}</td>
						<td>${carplacedto.license_plate}</td>
						<td>${carplacedto.start}</td>
						<td>${carplacedto.finish}</td>
						<td>""</td>
						<!-- manca pagato -->
					</tr>
				</c:forEach>
		</table>


		<a class="btn btn-lg btn-primary btn-block back-button"
			href="/ManagementSlot/ManagementSlotControl">Indietro</a></br>
		</p>
	</div>
	



	<script>
	function myMap() {
		var googleApiManagerOwner = new GoogleApiManagerOwner('map',41.9, 12.48,'/updateParkings');
// 			googleApiManagerOwner.selectAutoCompleteTextbox('autocomplete','startsearch');
// 			googleApiManagerOwner.selectDirectionModeBackButton('cambia');
// 			googleApiManagerOwner.selectRunTurnByTurnButton('seleziona');
// 			googleApiManagerOwner.selectChangeMinute('select','newprice','slot','payandgo','carSelect');
// 			googleApiManagerOwner.selectFindMyPosition('myposition');
		};
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places&language=en&key=AIzaSyAsEE6wc-sX5HniWTVBPTSZgHOwN_cf5F0&callback=myMap"></script>


</body>
</html>