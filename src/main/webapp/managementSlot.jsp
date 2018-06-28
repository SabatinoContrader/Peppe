<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Slot"%>
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
		<h1>Lista slot</h1>
		</br>
		<table class="table table-striped table-medium">
			<thead>

				<tr>
					<th>INDIRIZZO</th>
					<th>PREZZO</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${slots}" var="slot">
					<tr>
						<td>${slot.address}</td>
						<td>${slot.price}</td>
						<td><a href="/ManagementCarPlace/CarPlace?id=${slot.id}">Seleziona</a></td>
					</tr>
				</c:forEach>
		</table>


		<a class="btn btn-lg btn-primary btn-block back-button"
			href="/Home/dispatchHome">Indietro</a></br>
		</p>
	</div>

</body>
</html>