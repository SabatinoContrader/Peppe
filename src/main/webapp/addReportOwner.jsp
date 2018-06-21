<%@ page import="java.sql.Timestamp;"%>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
<%
Timestamp time = new Timestamp(System.currentTimeMillis());
request.setAttribute("time", time);
%>



  	<h1 style="margin-left: 20px;">Invia segnalazione</h1>
    <form action="ReportServlet" method="post">
<div class="row">
		<div class="col-md-6 mb-3">
<textarea rows="6" cols="50" name="description" placeholder="Descrizione della segnalazione"  style="margin-left: 20px;" class="form-control form-control-lg">
</textarea>
<br>
<button name="richiesta" class="btn btn-lg btn-primary btn-block" value="addedReport"  style="margin-left: 20px; width: 250px;" >
Invia
</button>



<a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=hystoryOwner" style="width: 250px; height: 45px; margin-left: 20px;">Accedi alla
					cronologia</a></br>

</br> </br>

<a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=Indietro" style="width: 150px; height: 45px; margin-left: 20px;">Indietro</a></br>


</button>


</div>
</div>

</body>
</html>