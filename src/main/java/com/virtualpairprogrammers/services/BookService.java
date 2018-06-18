package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> testBooks = new ArrayList<Book>();
    private static BookService reference;

    /**
     * Use this method if you want to share the service across many servlets.
     * <p>
     * This is simulating what a true container such as Spring would provide
     */
    public static BookService getService() {
        if (reference == null)
            reference = new BookService();
        return reference;
    }

    public BookService() {
        Book book1 = new Book("ISBN1", "Forecasting Rain", "Phil Don", 10.99);
        Book book2 = new Book("ISBN2", "The Tulip", "Anna Pavord", 14.99);
        Book book3 = new Book("ISBN3", "Enough", "Bill McKibben", 16.99);
        testBooks.add(book1);
        testBooks.add(book2);
        testBooks.add(book3);
    }

    public List<Book> getBookByIsbn(String isbn) {
        List<Book> returnList = new ArrayList<Book>();
        for (Book next : testBooks) {
            if (next.getIsbn().equals(isbn)) {
                returnList.add(next);
            }
        }
        return returnList;
    }

    public List<Book> getEntireCatalogue() {
        return new ArrayList<Book>(testBooks);
    }

    public void registerNewBook(Book newBook) {
        testBooks.add(newBook);
    }

    public List<Book> getAllBooksByAuthor(String author) {
        List<Book> returnList = new ArrayList<Book>();
        for (Book next : testBooks) {
            if (next.getAuthor().equals(author)) {
                returnList.add(next);
            }
        }
        return returnList;
    }

    public void deleteBookFromStock(Book bookToRemove) {
        testBooks.remove(bookToRemove.getIsbn());
    }

    public Book getBookById(int id) {
        for (Book next : testBooks) {
            if (next.getId() == id) {
                return next;
            }
        }
        return null;
    }
}
