<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Report"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<body>

	<%
		List<Report> reports = (List<Report>) request.getAttribute("reports");
	%>

	</br>

	<form action="ReportServlet" method="post">
		<input type=submit name="richiesta" value="Indietro">
	</form>

	</br>
	</br>
	</br>


	<table style="width: 75%">

		<tr>
			<th>TIPO SEGNALAZIONE</th>
			<th>SEGNALAZIONE</th>
			<th>DATA</th>
			<th>UTENTE</th>
		</tr>

		<%
			for (Report report : reports) {
		%>
		<tr>
			<td align="center">
			<% if(report.getType() == 0) out.println("Avviso del gestore");
			else if(report.getType() == 1) out.println("Abuso spazio dedicato a persone con disabilità");
			else if(report.getType() == 2) out.println("Disservizio stradale");
			else if(report.getType() == 3) out.println("Problema riscontrato nell'usufruire del servizio"); 
			%>
			</td>
			<td align="center"><%=report.getDescription()%></td>
			<td align="center"><%=report.getTime()%></td>
			<td align="center"><%=report.getUsername()%></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>
