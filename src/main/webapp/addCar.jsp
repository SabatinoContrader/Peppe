<html>
<body>
	<h1>Aggiungi auto</h1>

	<form action="CarServlet" method="post">
		<p>
			Targa:<input type="TEXT" name="licensePlate" />
		</p>
		<p>
			Nome:<input type="TEXT" name="name" />
		</p>
		<p>
			Dimensione: <select name="size">
				<option>Small</option>
				<option>Medium</option>
				<option>Large</option>
			</select>
		</p>


		<button name="richiesta" value="addedCar" />
		Aggiungi
		</button>
	</form>

	</br>
	</br>

	<form action="CarServlet?richiesta=home" method="post">
		<button value="Indietro" />
		Indietro
		</button>
	</form>
	</form>

</body>
</html>