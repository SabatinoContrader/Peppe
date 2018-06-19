<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>PCARPET - Registration </title>



  <body class="text-center">
    <form class="form-signup" action="SignupServlet" method="post">
      <h1 class="h3 mb-3 font-weight-normal">Registration</h1>
	  <label for="username" class="sr-only">Username</label></br>
	  <input type="text" name = "username" id="username" class="form-control" placeholder="Username" required autofocus></br>
	  <label for="inputPassword" class="sr-only">Password</label></br>
      <input type="password" name = "password" id="inputPassword" class="form-control" placeholder="Password" required></br>
      <label for="name" class="sr-only">Nome</label></br>
	  <input type="text" name = "name" id="name" class="form-control" placeholder="Name" required autofocus></br>
	  <label for="surname" class="sr-only">Cognome</label></br>
	  <input type="text" name = "surname" id="surname" class="form-control" placeholder="surname" required autofocus></br>
	  <label for="birthdate" class="sr-only">Data di nascita</label></br>
	  <input type="date" name = "birthdate" id="birthdate" class="form-control" placeholder="birthdate" required autofocus></br>
	  <label for="birthplace" class="sr-only">Luogo di nascita</label></br>
	  <input type="text" name = "birthplace" id="birthplace" class="form-control" placeholder="birthplace" required autofocus></br>
	  <label for="address" class="sr-only">Indirizzo</label></br>
	  <input type="text" name = "address" id="address" class="form-control" placeholder="address" required autofocus></br>
	  
	  
	  <input type="text" name="richiesta" value="registed" hidden></p>
	  
	  <label for="handicapped" class="sr-only">Disabile</label>
	  <input type="radio" id="handicappedChoice1"
     name="handicapped" value="true">
    <label for="handicappedChoice1">Si</label>

    <input type="radio" id="handicappedChoice2"
     name="handicapped" value="false">
    <label for="handicappedChoice2">No</label></br>
	  
	  
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button></br>
      
      </form>
  </body>
</html>