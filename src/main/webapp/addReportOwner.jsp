<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Timestamp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
	<%
		Timestamp time = new Timestamp(System.currentTimeMillis());
		request.setAttribute("time", time);
	%>



	<h1 class="element-margin-left">Invia segnalazione</h1>
	<form action="/Report/owner/addedReport" method="post">
		<div class="row">
			<div class="col-md-6 mb-3">
				<textarea rows="6" cols="50" name="description"
					placeholder="Descrizione della segnalazione"
					class="form-control form-control-lg element-margin-left">
</textarea>
				<br>
				<button type="submit" class="btn btn-lg btn-primary btn-block element-margin-left submit-button">
					Invia</button>



				<a
					class="btn btn-lg btn-primary btn-block submit-button element-margin-left"
					href="/Report/owner/hystory">Accedi alla
					cronologia</a></br> </br> </br> <a
					class="btn btn-lg btn-primary btn-block back-button element-margin-left"
					href="/Home/dispatchHome">Indietro</a></br>


				</button>


			</div>
		</div>
</body>
</html>