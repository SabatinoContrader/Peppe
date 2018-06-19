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

		<c:forEach items="${cars}" var="car">
			<tr>
				<td align="center"><c:out value="${car.licensePlate}" /></td>
				<td align="center"><c:out value="${car.name}" /></td>
				<td align='center'><form>
						<input type=submit value="click me" style="width: 25%"></td>
			</tr>
		</c:forEach>


	</table>

</body>
</html>
