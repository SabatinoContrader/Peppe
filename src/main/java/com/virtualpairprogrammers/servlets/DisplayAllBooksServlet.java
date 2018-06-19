package com.virtualpairprogrammers.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

    public static class ConnectionSingleton {


        private static Connection connection = null;


        private ConnectionSingleton() {
        }


        public static Connection getInstance() {
            if (connection == null) {
                try {
                    Properties properties = new Properties();
                    InputStream inputStream = new FileInputStream("config.properties");
                    properties.load(inputStream);
                    String vendor = properties.getProperty("db.vendor");
                    String driver = properties.getProperty("db.driver");
                    String host = properties.getProperty("db.host");
                    String port = properties.getProperty("db.port");
                    String dbName = properties.getProperty("db.name");
                    String username = properties.getProperty("db.username");
                    String password = properties.getProperty("db.password");
                    Class c = Class.forName(driver);
                    System.out.println("Ho caricato: " + c.getName());
                    String myUrl = "jdbc:" + vendor + "://" + host + ":" + port + "/" + dbName;
                    DriverManagerDataSource dataSource = new DriverManagerDataSource(myUrl, username, password);
                    dataSource.setDriverClassName(driver);
                    connection = dataSource.getConnection();
                } catch (Exception e) {
                    GestoreEccezioni.getInstance().gestisciEccezione(e);
                }
            }
            return connection;
        }

    }
}
