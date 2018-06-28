<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Report"%>
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
		<h1>Lista segnalazioni</h1>
		</br>
		<table class="table table-striped">
			<thead>


				<tr>
					<th>TIPO SEGNALAZIONE</th>
					<th>SEGNALAZIONE</th>
					<th>DATA</th>
					<th>UTENTE</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${reports}" var="report">
					<tr>
						<td><c:choose>
								<c:when test="${report.type == 0}">
									<p>Avviso del gestore</p>
								</c:when>

								<c:when test="${report.type == 1}">
									<p>Abuso spazio dedicato a persone con disabilità</p>
								</c:when>

								<c:when test="${report.type == 2}">
									<p>Disservizio stradale</p>
								</c:when>

								<c:when test="${report.type == 3}">
									<p>Problema riscontrato nell'usufruire del servizio</p>
								</c:when>
							</c:choose></td>
						<td>${report.description}</td>
						<td>${report.time}</td>
						<td>${report.user.username}</td>
						<!--  correggere? -->
				</c:forEach>

				</tr>
		</table>


		<a class="btn btn-lg btn-primary btn-block back-button"
			href="/Report/back">Indietro</a></br>
		</p>
	</div>

</body>
</html>