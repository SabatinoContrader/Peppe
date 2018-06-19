<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<body>

	<%
		List<Car> cars = (List<Car>) request.getAttribute("cars");
		request.setAttribute("cars", cars);
	%>


	<h2>Lista auto</h2>
	<input type=submit value="Aggiungi auto">
	<input type=submit value="Indietro">
	</br>
	</br>
	</br>


	<table style="width: 50%">

		<tr>
			<th>Targa</th>
			<th>Nome</th>
			<th>Elimina</th>
		</tr>

<% for(Car car: cars){ %>
<tr>
<td align="center"><%= car.getLicensePlate()%>
</td>
<td align="center"><%= car.getName()%>
</td>
<td align="center"><input type=submit value="Elimina">
</td>
</tr>
<% } %>
	</table>

</body>
</html>
