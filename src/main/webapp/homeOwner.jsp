<html lang="en">
         <head>
           <meta charset="utf-8">
           <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
           <meta name="description" content="">
           <meta name="author" content="">
           
            <title>PCARPET</title>

		    <!-- Bootstrap core CSS -->
		    <link href="bootstrap.min.css" rel="stylesheet">
		
		    <!-- Custom styles for this template -->
		    <link href="signin.css" rel="stylesheet">
        </head>

         <body class="text-center">
		   <form class="form-signin" action="LoginServlet" method="post">
             <h1 class="h3 mb-3 font-weight-normal"><b>OWNER</b></h1>
             <a class="btn btn-lg btn-primary btn-block" href="ManagementCarPlaceServlet?richiesta=home">Gestione parcheggi</a></br>
             <a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=addReport">Invia segnalazione</a></br>
             <a class="btn btn-lg btn-primary btn-block" href="ReportServlet?richiesta=reportUser">Segnalazioni degli utenti</a></br>
             <a class="btn btn-lg btn-primary btn-block" href="LoginServlet?richiesta=logout">Logout</a></br>
             
                                   
           </form>

         </body>
       </html>