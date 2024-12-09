package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Page<Book> getPaginatedBooks(String category, Pageable pageable) {
        return bookRepository.findByCategory(category, pageable);
    }

    public Map<String, List<Book>> getCategorizedBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .collect(Collectors.groupingBy(Book::getCategory));
    }

    // Fetch all books (optional if needed)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
