<%@ page import="java.util.List"%>
<%@ page import="com.pCarpet.model.Car"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/css/pcarpet.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<h1>Cronologia pagamenti</h1>
		</br>
		<table class="table table-striped table-medium">
			<thead>


				<tr>
					<th>Id pagamento</th>
					<th>Quantità</th>
					<th>Id sosta</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${payments}" var="payment">
					<tr>
						<td>${payment.id_payment}</td>
						<td>${payment.quantity}</td>
						<td>${payment.stop.id_stop}</td>
					</tr>
				</c:forEach>


				</form>
				</tr>
		</table>




		<a class="btn btn-lg btn-primary btn-block back-button"
			href="/Home/dispatchHome">Indietro</a></br>

		</p>
	</div>

</body>
</html>