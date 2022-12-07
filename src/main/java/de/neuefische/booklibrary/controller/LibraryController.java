package de.neuefische.booklibrary.controller;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LibraryController {

    private LibraryService libraryService;


    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/{ISBN}")
    public Book getAllBooks(@PathVariable String ISBN) {
        return libraryService.getBookForISBN(ISBN);
    }

    @PutMapping("/books/{ISBN}")
    public Book saveBookWithISBN(@PathVariable String ISBN, @RequestBody Book book) {
        return libraryService.saveBookWithISBN(ISBN, book);
    }

    @DeleteMapping("/books/{ISBN}")
    public String saveBookWithISBN(@PathVariable String ISBN) {
        return libraryService.deleteBookFromLibrary(ISBN);
    }
}
