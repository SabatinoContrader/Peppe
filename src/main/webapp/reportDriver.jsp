<%@ page import="java.sql.Timestamp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 mb-3">
				<h1 class="element-margin-left">Invia segnalazione</h1>
				<form action="/Report/driver/addReport" method="post"
					class="element-margin-left">
					<select name="type" class="form-control form-control-lg"
						id="validationCustom03" onchange="ChangecatList()" required>
						<option value="1">Abuso spazio dedicato a persone con
							disabilità</option>
						<option value="2">Disservizio stradale</option>
						<option value="3">Problema riscontrato nell'usufruire del
							servizio</option>
					</select> </br>
					<textarea rows="6" class="form-control form-control-lg" cols="50"
						name="description"
						placeholder="Descrizione del problema riscontrato">
</textarea>
					</br>
					<button class="btn btn-lg btn-primary btn-block submit-button"
						type="submit">Invia</button>
				</form>

				<a
					class="btn btn-lg btn-primary btn-block submit-button element-margin-left center-button"
					href="/Report/driver/hystory">Accedi alla cronologia</a></br> </br> </br>
				</form>


				<a
					class="btn btn-lg btn-primary btn-block back-button element-margin-left"
					href="/Home/dispatchHome">Indietro</a></br>


			</div>
		</div>
	</div>
</body>
</html>