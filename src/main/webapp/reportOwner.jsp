<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Report"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h1>Lista slot</h1>
     </br>     
  <table class="table table-striped">
    <thead>
	
	<%
		List<Report> reports = (List<Report>) request.getAttribute("reports");
	%>
	
      <tr>
        <th>Tipo Segnalazione</th>
			<th>Segnalazione</th>
			<th>Data</th>
			<th>Utente</th>
      </tr>
    </thead>
    <tbody>
      <%
			for (Report report : reports) {
		%>
		<tr>
		<% if(report.getType() != 0){ %>
			<td align="center">
			<%if(report.getType() == 1) out.println("Abuso spazio dedicato a persone con disabilità");
			else if(report.getType() == 2) out.println("Disservizio stradale");
			else if(report.getType() == 3) out.println("Problema riscontrato nell'usufruire del servizio");
			%>
			</td>
			<td><%=report.getDescription()%></td>
			<td><%=report.getTime()%></td>
			<td><%=report.getUsername()%></td>
			<%
			}
		%>
		</tr>
		<%
			}
		%>
	</table>
	<a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=indietro" style="width: 100px; height: 40px;">Indietro</a></br>
	
	
	
	</p>
</div>

</body>
</html>