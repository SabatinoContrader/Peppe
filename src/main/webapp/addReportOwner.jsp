<%@ page import="java.sql.Timestamp;"%>
<html>
<body>

<h1>Invia segnalazione</h1>


<%
Timestamp time = new Timestamp(System.currentTimeMillis());
request.setAttribute("time", time);
%>

<form action="ReportServlet" method="post">

<textarea rows="6" cols="50" name="description" placeholder="Descrizione della segnalazione">
</textarea>
<br>
<button name="richiesta" value="addedReport" >
Invia
</button>

</form>
<form action="ReportServlet" method="post">
<button name="richiesta" value="Indietro" >
Indietro
</button>
</form>

<form action="ReportServlet" method="post">
<button name="richiesta" value="hystoryOwner" >
Accedi alla cronologia
</button>
<form>

</body>
</html>

