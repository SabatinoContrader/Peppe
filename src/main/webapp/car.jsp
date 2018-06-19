<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.domain.Car" %>

<!DOCTYPE html>
<html>
<body>

  <% 
  		List<Car> cars = (List<Car>)request.getAttribute("cars");
  		int i = 1;
  %>


<h2>Lista auto</h2>
<input type=submit value="Aggiungi auto">
<input type=submit value="Indietro"></br></br></br>


<table style="width:100%">
  <% 
  for (Car car : this.cars) { 
  %>
  <tr>
    <th>#</th>
    <th>Targa</th> 
    <th>Nome</th>
    <th>Elimina</th>
  </tr>

  <tr>
    <td align="center"><%= i %></td>
    <td align="center">Smith</td>
    <td align="center">50</td>
    <td align='center'><form><input type=submit value="click me" style="width:25%"></td>
  </tr>
  
</table>

</body>
</html>
