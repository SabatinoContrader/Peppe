package com.virtualpairprogrammers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.virtualpairprogrammers.domain.Book;
//import com.virtualpairprogrammers.services.BookService;

public class BookServlet extends HttpServlet
{
	//CONTROLLARE METODI USATI
	
    public BookServlet() {

    }
    
	public void session (HttpServletRequest request, 
					     HttpServletResponse response) 
			throws ServletException,IOException
	{		
		//HttpSession book_session = request.getSession(false);
		
		//response.sendRedirect("Book.jsp");
        getServletContext().getRequestDispatcher("/Book.jsp").forward(request, response);
	}
}