<%@ page import="java.sql.Timestamp;"%>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
	<%
		Timestamp time = new Timestamp(System.currentTimeMillis());
		request.setAttribute("time", time);
	%>

	<div class="row">
		<div class="col-md-6 mb-3">
			<h1 style="margin-left: 20px;">Invia segnalazione</h1>
			<form action="ReportServlet" method="post" style="margin-left: 20px;" >
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
				<button class="btn btn-lg btn-primary btn-block" name="richiesta" value="addReport" style="width: 250px;">
					Invia</button>
			</form>

			<a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=hystoryDriver" style="width: 250px; height: 45px; margin-left: 20px; line-height: 22.5px;">Accedi alla
					cronologia</a></br>
			
			
			</br> </br>
			</form>
			
			
			<a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=indietro" style="width: 150px; height: 45px; margin-left: 20px;">Indietro</a></br>


		</div>
	</div>
</body>
</html>