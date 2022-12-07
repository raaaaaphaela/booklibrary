package de.neuefische.booklibrary.repository;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.model.Cover;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
public class LibraryRepository {

    private List<Book> books = new ArrayList<>();

    public LibraryRepository() {
        books.add(new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType()));
        books.add(new Book("278-4-8362-8345-7", "Lebensweisheiten", "Miss Unbekannt", Cover.E_BOOK.getCoverType()));
    }

    public Book getBookForISBN(String isbn) {

        Book book;
        for (Book b : books) {
            if (b.getISBN().equals(isbn)) {
                book = b;
                return book;
            }
        }
        return null;
    }

    public Book saveBookWithISBN(String ISBN, Book book) {

        if (book != null && book.getISBN().equals(ISBN)) {

            for (Book existingBook : books) {
                if (book.getISBN().equals(existingBook.getISBN())) {
                    // ISBN is unique, book already exists in library
                    return null;
                }
            }
            books.add(book);
            return book;
        }
        return null;
    }

    public String deleteBookFromLibrary(String isbn) {
        books.removeIf(book -> book.getISBN().equals(isbn));
        return "Deleted.";
    }
}
