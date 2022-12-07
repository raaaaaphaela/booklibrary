package de.neuefische.booklibrary.service;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.model.Cover;
import de.neuefische.booklibrary.repository.LibraryRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibraryServiceTest {

    @Test
    void getAllBooks() {

        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType()));
        books.add(new Book("278-4-8362-8345-7", "Lebensweisheiten", "Miss Unbekannt", Cover.E_BOOK.getCoverType()));

        LibraryRepository repo = mock(LibraryRepository.class);
        when(repo.getBooks()).thenReturn(books);

        LibraryService service = new LibraryService(repo);

        // when
        List<Book> actual = service.getAllBooks();

        // then
        assertEquals(books, actual);
    }

    @Test
    void getBookByISBN() {

        // given
        Book book = new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType());

        LibraryRepository repo = mock(LibraryRepository.class);
        when(repo.getBookForISBN("978-3-8362-8745-6")).thenReturn(book);

        LibraryService service = new LibraryService(repo);

        // when
        Book actual = service.getBookForISBN("978-3-8362-8745-6");

        // then
        assertEquals(book, actual);
    }

    @Test
    void addNewBookByISBN() {

        // given
        Book book = new Book("178-3-8362-8745-6", "Bali Guide", "Balinese Nr. 1", Cover.E_BOOK.getCoverType());

        LibraryRepository repo = mock(LibraryRepository.class);
        when(repo.saveBookWithISBN("178-3-8362-8745-6", book)).thenReturn(book);

        LibraryService service = new LibraryService(repo);

        // when
        Book actual = service.saveBookWithISBN("178-3-8362-8745-6", book);

        // then
        assertEquals(book, actual);
    }

    @Test
    void addNewBookByISBN_AlreadyExists() {

        // given
        Book book = new Book("978-3-8362-8745-6", "Java ist auch eine Insel", "Christian Ullenboom", Cover.HARD_COVER.getCoverType());

        LibraryRepository repo = mock(LibraryRepository.class);
        when(repo.saveBookWithISBN("978-3-8362-8745-6", book)).thenReturn(null);

        LibraryService service = new LibraryService(repo);

        // when
        Book actual = service.saveBookWithISBN("978-3-8362-8745-6", book);

        // then
        assertNull(actual);
    }

    @Test
    void addBookByUnmatchingISBN() {

        // given
        Book book = new Book("178-3-8362-8745-6", "Bali Guide", "Balinese Nr. 1", Cover.E_BOOK.getCoverType());

        LibraryRepository repo = mock(LibraryRepository.class);
        when(repo.saveBookWithISBN("278-3-8362-8745-6", book)).thenReturn(null);

        LibraryService service = new LibraryService(repo);

        // when
        Book actual = service.saveBookWithISBN("278-3-8362-8745-6", book);

        // then
        assertNull(actual);
    }
}