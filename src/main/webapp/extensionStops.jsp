<%@ page import="java.util.List"%>
<%@ page import="com.virtualpairprogrammers.domain.Stop"%>

<%@ page import="com.virtualpairprogrammers.dto.*"%>


<!DOCTYPE html>
<html>
<body>

  <%
    List<ManagementExtensionStopDTO> managementExtensionStopDTO = (List<ManagementExtensionStopDTO>) request.getAttribute("stops");
  %>


  <h2>Prolunga la tua sosta</h2>

  </br>

  <table style="width: 75%">

    <tr>
      <th>Indirizzo</th>
      <th>Auto</th>
      <th>Inizio</th>
      <th>Fine</th>
      <th></th>
      <th></th>
    </tr>

    <%
      for (ManagementExtensionStopDTO stop : managementExtensionStopDTO) {
    %>
    <tr>
    <form action="ExtensionStopServlet" method="post">
      <td align="center"><%=stop.getAddress()%></td>
      <td align="center"><%=stop.getName()%></td>
      <td align="center"><%=stop.getStart()%></td>
      <td align="center"><%=stop.getFinish()%></td>
      
      
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
      
      <input type="text" name="richiesta" value="extendStop" hidden></input>
      
      <input type="text" name="id"        value="<%= stop.getId_stop() %>" hidden></input>
      <input type="text" name="address"   value="<%= stop.getAddress() %>" hidden></input>
      <input type="text" name="name"      value="<%= stop.getName()    %>" hidden></input>
      <input type="text" name="start"     value="<%= stop.getStart()   %>" hidden></input>
      <input type="text" name="finish"    value="<%= stop.getFinish()  %>" hidden></input>
      
      
      <td align="center" ><button type="submit" style="width:100px; height:20px;">Prolunga</button></td> 
    </form>
    </tr>
    <%
      }
    %>
  </table>

  </br>

  <form action="HomeServlet" method="post">
    <input type=submit value="Indietro">
  </form>

</body>
</html>
