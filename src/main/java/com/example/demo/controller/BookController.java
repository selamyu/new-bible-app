package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
//@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final MessageSource messageSource;

    public BookController(BookService bookService, MessageSource messageSource) {
        this.bookService = bookService;
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book, @RequestParam(defaultValue = "en") String lang) {
        // Set the locale based on the lang parameter
        Locale locale = new Locale(lang);
        String titleMessage = messageSource.getMessage("book.title", null, locale);
        System.out.println("Title translation: " + titleMessage);

        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @GetMapping("/paginated")
    public Page<Book> getPaginatedBooks(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "en") String lang
    ) {
        // Set the locale based on the lang parameter
        Locale locale = new Locale(lang);
        String categoryMessage = messageSource.getMessage("book.category", null, locale);
        Pageable pageable = PageRequest.of(page, size);
        System.out.println("Category translation: " + categoryMessage);

        return bookService.getPaginatedBooks(category, pageable);
    }

    // Fetch books categorized
    @GetMapping("categorized")
    public ResponseEntity<Map<String, List<Book>>> getCategorizedBooks() {
        Map<String, List<Book>> categorizedBooks = bookService.getCategorizedBooks();
        return ResponseEntity.ok(categorizedBooks);
    }

    // Optional: Fetch all books without categorization
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
