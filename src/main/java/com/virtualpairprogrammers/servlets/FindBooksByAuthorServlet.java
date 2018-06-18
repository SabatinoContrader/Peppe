package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;

public class FindBooksByAuthorServlet extends HttpServlet
{
	public void service (HttpServletRequest request, 
						 HttpServletResponse response) 
			throws ServletException,IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
	
		// do the work - find all books by the supplied author
		BookService service = BookService.getService();
		
		// form handling - extracting a parameter from the form
		String author = request.getParameter("AUTHOR");
		
		List<Book> allBooks = service.getAllBooksByAuthor(author);		
		
		// render the output
		// output the result page
		out.println("<html><head><title>Books by " + author + "</title>");
		out.println("<body><h1>Books by the Author " + author + "</h1>");
		
		out.println("<ul>");
		for (Book nextBook : allBooks)
		{
			out.println("<li>");
			out.println(nextBook.getTitle());
			out.println("</li>");
		}
		out.println("</ul>");
		out.println("</body></html>");
		
		out.close();
		
	}
}
