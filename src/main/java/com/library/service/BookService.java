package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long bookId){
        return bookRepository.findById(bookId);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        if (bookRepository.findById(id).get().getUser() != null){
            throw new RuntimeException();
        }
        bookRepository.deleteById(id);
    }

    public Book update(Book book){
        Optional<Book> byId = bookRepository.findById(book.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException();
        } else {
            return bookRepository.save(book);
        }
    }

    public Optional<List<Book>> findByName(String name) {
        return bookRepository.getBookByName(name);
    }



}
