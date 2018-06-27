<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.dto.ManagementCarPlaceDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h1>Lista posti auto</h1>
		</br>
		<table class="table table-striped">
			<thead>

				<%
					List<ManagementCarPlaceDTO> carPlaces = (List<ManagementCarPlaceDTO>) request
							.getAttribute("managementCarPlaceDTO");
				%>

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
				<%
					for (ManagementCarPlaceDTO carPlace : carPlaces) {
				%>
				<tr>

					<td><%=carPlace.getId_carplace()%></td>
					<td><%=carPlace.getId_slot()%></td>
					<td><%=carPlace.isType()%></td>
					<td><%=carPlace.isBusy()%></td>
					<td><%=carPlace.getLicense_plate()%></td>
					<td><%=carPlace.getStart()%></td>
					<td><%=carPlace.getFinish()%></td>
					<td><%=""%></td>
					<!-- manca pagato -->
				</tr>
				<%
					}
				%>
			
		</table>


		<a class="btn btn-lg btn-primary btn-block back-button"
			href="/ManagementSlot/ManagementSlotControl">Indietro</a></br>
		</p>
	</div>

</body>
</html>