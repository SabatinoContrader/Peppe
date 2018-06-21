<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Car"%>
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
  <h1>Lista auto</h1>
     </br>     
  <table class="table table-striped" style="width: 50%">
    <thead>
	
	<%
		List<Car> cars = (List<Car>) request.getAttribute("cars");
	%>
	
      <tr>
        <th>Targa</th>
			<th>Nome</th>
			<th></th>
      </tr>
    </thead>
    <tbody>
	<%
			for (Car car : cars) {
		%>
		<tr>
			<td><%=car.getLicensePlate()%></td>
			<td><%=car.getName()%></td>
			<td><a
				href="CarServlet?richiesta=removeCar&id=<%=car.getId_car()%>">Elimina</a></td>
		</tr>
		<%
			}
		%>
    </form>
    </tr>
    
	</table>
	
	

	
	<a class="btn btn-lg btn-primary btn-block" href="CarServlet?richiesta=addCar" style="width: 150px; height: 45px;">Aggiungi auto</a></br>
	<a class="btn btn-lg btn-primary btn-block" href="HomeServlet" style="width: 150px; height: 45px;">Indietro</a></br>
	
	</p>
</div>

</body>
</html>