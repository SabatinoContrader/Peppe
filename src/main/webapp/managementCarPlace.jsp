<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.dto.ManagementCarPlaceDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

</body>
</html>