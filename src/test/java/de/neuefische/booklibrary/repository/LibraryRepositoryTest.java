package de.neuefische.booklibrary.repository;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.model.Cover;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryRepositoryTest {

    private List<Book> createBookList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType()));
        books.add(new Book("278-4-8362-8345-7", "Lebensweisheiten", "Miss Unbekannt", Cover.E_BOOK.getCoverType()));
        return books;
    }

    @Test
    void getBooks() {
        // given
        List<Book> books = createBookList();

        LibraryRepository repo = new LibraryRepository();
        // when

        List<Book> actual = repo.getBooks();

        // then
        assertEquals(books, actual);
    }

    @Test
    void getBookByISBN() {
        // given
        List<Book> books = createBookList();

        LibraryRepository repo = new LibraryRepository();
        // when

        Book actual = repo.getBookForISBN("978-3-8362-8745-6");

        // then
        assertEquals(books.get(0), actual);
    }

    @Test
    void addBookByISBN() {
        // given
        Book book = new Book("178-3-8362-8745-6", "Bali Guide", "Balinese Nr. 1", Cover.E_BOOK.getCoverType());


        LibraryRepository repo = new LibraryRepository();
        // when

        Book actual = repo.saveBookWithISBN("178-3-8362-8745-6", book);

        // then
        assertEquals(book, actual);
    }

    @Test
    void addBookByUnMatchingISBN() {
        // given
        Book book = new Book("178-3-8362-8745-6", "Bali Guide", "Balinese Nr. 1", Cover.E_BOOK.getCoverType());


        LibraryRepository repo = new LibraryRepository();
        // when

        Book actual = repo.saveBookWithISBN("278-3-8362-8745-6", book);

        // then
        assertNull(actual);
    }

    @Test
    void addBookByISBN_AlreadyExists() {
        // given
        Book book = new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType());

        LibraryRepository repo = new LibraryRepository();
        // when

        Book actual = repo.saveBookWithISBN("978-3-8362-8745-6", book);

        // then
        assertNull(actual);
    }
}