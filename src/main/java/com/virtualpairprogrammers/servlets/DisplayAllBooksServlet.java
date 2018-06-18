package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;

public class DisplayAllBooksServlet extends HttpServlet
{
	public void doGet (HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException,IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
	
		// do some work
		BookService bookservice = BookService.getService();
		
		List<Book> allBooks = bookservice.getEntireCatalogue();

		// output the result page
		out.println("<html><head><title>Our entire catalogue</title>");
		out.println("<body><h1>Our Entire Catalogue</h1>");
		
		out.println("<ul>");
		for (Book nextBook : allBooks)
		{
			out.println("<li>");
			out.println(nextBook.getTitle());	
			
			String url = "addToCart.html";
			url = response.encodeURL(url);
			
			out.println("<form method='post' action='" + url + "'>");
			out.println("<input type='hidden' name='id' value='" + nextBook.getId() + "'/>");
			out.println("<input type='submit' value='Add To Cart'/>");
			out.println("</form>");
			
			out.println("</li>");
		}
		out.println("</ul>");
		out.println("</body></html>");
		
		out.close();
	}
}
