<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.dto.ManagementCarPlaceDTO"%>


<html lang="en">
<head>
<title>CARPLACES</title>
</head>

<body>

	<%
		List<ManagementCarPlaceDTO> carPlaces = (List<ManagementCarPlaceDTO>) request
				.getAttribute("managementCarPlaceDTO");
	%>

	<p>
	<table style="width: 75%">
	
	<h1>Lista posti auto</h1>

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

		<%
			for (ManagementCarPlaceDTO carPlace : carPlaces) {
		%>
		<tr>

			<td align="center"><%=carPlace.getId_carplace()%></td>
			<td align="center"><%=carPlace.getId_slot()%></td>
			<td align="center"><%=carPlace.isType()%></td>
			<td align="center"><%=carPlace.isBusy()%></td>
			<td align="center"><%=carPlace.getLicense_plate()%></td>
			<td align="center"><%=carPlace.getStart()%></td>
			<td align="center"><%=carPlace.getFinish()%></td>
			<!-- manca pagato -->
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<br>
	<form action="ManagementCarPlaceServlet?richiesta=home" method="post">
		<input type=submit value="Indietro">
	</form>
	</p>







</body>
</html>