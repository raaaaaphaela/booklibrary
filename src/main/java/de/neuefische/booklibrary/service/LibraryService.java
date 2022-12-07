package de.neuefische.booklibrary.service;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LibraryService {

    private LibraryRepository libraryRepository;

    public List<Book> getAllBooks() {
        return libraryRepository.getBooks();
    }

    public Book getBookForISBN(String ISBN) {
        return libraryRepository.getBookForISBN(ISBN);
    }

    public Book saveBookWithISBN(String ISBN, Book book) {
        return libraryRepository.saveBookWithISBN(ISBN, book);
    }

    public String deleteBookFromLibrary(String isbn) {
        return libraryRepository.deleteBookFromLibrary(isbn);
    }
}
