<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.domain.Slot" %>
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
  <table class="table table-striped" style="width: 50%">
    <thead>
	
	<%
		List<Slot> slots = (List<Slot>) request.getAttribute("slots");
	
	%>
	
      <tr>
        <th>INDIRIZZO</th>
			<th>PREZZO</th>
			<th></th>
      </tr>
    </thead>
    <tbody>
      <%
			for (Slot slot : slots) {
		%>
		<tr>
			<td><%=slot.getAddress()%></td>
			<td><%=slot.getPrice()%></td>
			<td><a
				href="ManagementCarPlaceServlet?richiesta=slotList&id=<%=slot.getId_slot()%>">Seleziona</a></td>
		</tr>
		<%
			}
		%>
	</table>
	
	
	<a class="btn btn-lg btn-primary btn-block" href="HomeServlet" style="width: 100px; height: 45px;">Indietro</a></br>
	</p>
</div>

</body>
</html>