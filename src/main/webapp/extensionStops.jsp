<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Stop"%>

<%@ page import="com.pCarpet.dto.ManagementExtensionStopDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h1>Prolunga la tua sosta</h1>
     </br>     
  <table class="table table-striped">
    <thead>
	
	 <%
    List<ManagementExtensionStopDTO> managementExtensionStopDTO = (List<ManagementExtensionStopDTO>) request.getAttribute("stops");
  %>
	
      <tr>
        <th>INDIRIZZO</th>
      <th>AUTO</th>
      <th>INIZIO</th>
      <th>FINE</th>
      <th></th>
      <th></th>
      </tr>
    </thead>
    <tbody>
	<%
      for (ManagementExtensionStopDTO stop : managementExtensionStopDTO) {
    %>
    <tr>
         <form action="/ExtensionStop/extendStop" method="post">
      <td><%=stop.getAddress()%></td>
      <td><%=stop.getName()%></td>
      <td><%=stop.getStart()%></td>
      <td><%=stop.getFinish()%></td>
      
      
      <td>
      <select name="minute">
      <option value="15">15 min</option>
      <option value="30">30 min</option>
      <option value="45">45 min</option>
      <option value="60">1 h</option>
      <option value="75">1 h 15 min</option>
      <option value="90">1 h 30 min</option>
      <option value="105">1 h 45 min</option>
      <option value="120">2 h</option>
      </select>
      </td>
      
      <input type="text" name="id"        value="<%= stop.getId_stop() %>" hidden></input>
      <input type="text" name="address"   value="<%= stop.getAddress() %>" hidden></input>
      <input type="text" name="name"      value="<%= stop.getName()    %>" hidden></input>
      <input type="text" name="start"     value="<%= stop.getStart()   %>" hidden></input>
      <input type="text" name="finish"    value="<%= stop.getFinish()  %>" hidden></input>
      
      
      <td align="center" ><button type="submit" class="table-button">Prolunga</button></td> 
      
      <td>
    </form>
    </tr>
    <%
      }
    %>
	</table>
	
    <!--TODO:  Da fare -->
    <a class="btn btn-lg btn-primary btn-block back-button" href="HomeServlet">Indietro</a></br>
  </form>
	</p>
</div>

</body>
</html>