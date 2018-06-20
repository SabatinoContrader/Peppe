<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Slot"%>

<!DOCTYPE html>
<html>
<body>

	<%
		List<Slot> slots = (List<Slot>) request.getAttribute("slots");
	
	%>


	<h1>Lista Slot</h1>

	<table style="width: 25%">

		<tr>
			<th>INDIRIZZO</th>
			<th>PREZZO</th>
			<th></th>
		</tr>

		<%
			for (Slot slot : slots) {
		%>
		<tr>
			<td align="center"><%=slot.getAddress()%></td>
			<td align="center"><%=slot.getPrice()%></td>
			<td align="center"><a
				href="ManagementCarPlaceServlet?richiesta=slotList&id=<%=slot.getId_slot()%>">Seleziona</a></td>
		</tr>
		<%
			}
		%>
	</table>
<br>

	<form action="HomeServlet" method="post">
		<input type=submit value="Indietro">
	</form>

</body>
</html>
