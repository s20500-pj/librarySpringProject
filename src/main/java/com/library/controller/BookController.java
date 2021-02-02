package com.library.controller;

import com.library.model.Book;
import com.library.model.User;
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
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> byId = bookService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<List<Book>> findByName(@PathVariable String name){
        Optional<List<Book>> books = bookService.findByName(name);
        if(books.isPresent()) {
            return ResponseEntity.ok(books.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
