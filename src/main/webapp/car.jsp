<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="container">
  <h1>Lista auto</h1>
     </br>     
  <table class="table table-striped table-medium">
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
			<td><%=car.getLicense_plate()%></td>
			<td><%=car.getName()%></td>
			<td><a
				href="/Car/removeCar?id=<%=car.getId()%>">Elimina</a></td>
		</tr>
		<%
			}
		%>
    </form>
    </tr>
    
	</table>
	
	

	
	<a class="btn btn-lg btn-primary btn-block submit-button" href="/Car/addCar">Aggiungi auto</a></br>
	<a class="btn btn-lg btn-primary btn-block back-button" href="/Home/dispatchHome">Indietro</a></br>
	
	</p>
</div>

</body>
</html>