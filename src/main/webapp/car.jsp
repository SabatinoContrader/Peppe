<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<body>

	<%
		List<Car> cars = (List<Car>) request.getAttribute("cars");
	%>


	<h2>Lista auto</h2>
	<form action="CarServlet" method="post">
		<button name="richiesta" value="addCar" />
		Aggiungi auto
		</button>
	</form>

	</br>

	<form action="HomeServlet" method="post">
		<input type=submit value="Indietro">
	</form>

	</br>
	</br>
	</br>


	<table style="width: 25%">

		<tr>
			<th>Targa</th>
			<th>Nome</th>
			<th></th>
		</tr>

		<%
			for (Car car : cars) {
		%>
		<tr>
			<td align="center"><%=car.getLicensePlate()%></td>
			<td align="center"><%=car.getName()%></td>
			<td align="center"><a
				href="CarServlet?richiesta=removeCar&id=<%=car.getId_car()%>">Elimina</a></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>
