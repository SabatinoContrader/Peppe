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
<option value="1">Abuso spazio dedicato a persone con disabilità</option>
<option value="2">Disservizio stradale</option>
<option value="3">Problema riscontrato nell'usufruire del servizio</option>
</select>
</p>
<textarea rows="6" cols="50" name="description">Descrizione del problema riscontrato
</textarea>
<br>
<button name="richiesta" value="addReport" >
Invia
</button>

</form>
<form action="ReportServlet" method="post">
<button name="richiesta" value="Indietro" >
Indietro
</button>
</form>

<form action="ReportServlet" method="post">
<button name="richiesta" value="hystoryDriver" >
Accedi alla cronologia
</button>
<form>

</body>
</html>