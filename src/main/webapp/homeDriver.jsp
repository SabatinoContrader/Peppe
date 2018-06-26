<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>PCARPET</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<form class="form-signin">
		<h1 class="h3 mb-3 font-weight-normal">
			<b>DRIVER</b>
		</h1>
		<a class="btn btn-lg btn-primary btn-block"
			href="FindCarPlaceServlet?richiesta=home">Trova parcheggio</a></br> <a
			class="btn btn-lg btn-primary btn-block"
			href="ExtensionStopServlet?richiesta=home">Prolunga sosta</a></br> <a
			class="btn btn-lg btn-primary btn-block"
			href="CarServlet?richiesta=home">Aggiungi/Rimuovi auto</a></br> <a
			class="btn btn-lg btn-primary btn-block"
			href="ReportServlet?richiesta=home">Invia segnalazione</a></br> <a
			class="btn btn-lg btn-primary btn-block"
			href="ReportServlet?richiesta=ownerReport">Segnalazioni del
			gestore</a></br> <a class="btn btn-lg btn-primary btn-block"
			href="UsefulNumbersServlet">Numeri utili</a></br> <a
			class="btn btn-lg btn-primary btn-block" href="LegislationServlet">Normative</a></br>
		<a class="btn btn-lg btn-primary btn-block"
			href="LoginServlet?richiesta=logout">Logout</a></br>

	</form>

</body>
</html>