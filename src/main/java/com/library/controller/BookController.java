package com.library.controller;

import com.library.AdviceErrorHandler.CustomException;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) throws CustomException {
        Optional<Book> byId = bookService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            throw new CustomException(String.format("Book of id: %s doesnt exist", id.toString()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws CustomException{
        if (bookService.findById(id).isEmpty()) {
            throw new CustomException(String.format("Book of id: %s doesnt exist", id.toString()));
        } else {
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }
    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.update(book));
    }

    @GetMapping("/findbyname/{name}")
    public ResponseEntity<List<Book>> findByName(@PathVariable String name) throws CustomException{
        Optional<List<Book>> books = bookService.findByName(name);
        if (books.isPresent()) {
            return ResponseEntity.ok(books.get());
        } else {
            throw new CustomException(String.format("Book not found for name: %s", name));
        }
    }
}
