<%@ page import="java.sql.Timestamp;"%>
<html>
<body>

	<h1>Invia segnalazione</h1>
	
	
	<%
		Timestamp time = new Timestamp(System.currentTimeMillis());
		request.setAttribute("time", time);
	%>
	
	<form action="ReportServlet" method="post">
	<p>
		Tipo: <select name="type">
			<option>Abuso spazio dedicato a persone con disabilità</option>
			<option>Disservizio stradale</option>
			<option>Problema riscontrato nell'usufruire del servizio</option>
		</select>
	</p>
	<textarea rows="6" cols="50" name="description">Descrizione del problema riscontrato
	</textarea>
	<br>
	<button name="richiesta" value="addReport" />
		Invia
		</button>
	</form>
</body>
</html>
