package com.library.service;

import com.library.AdviceErrorHandler.CustomException;
import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long playerId) {
        return userRepository.findById(playerId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) throws CustomException {
        if(nrOfBooks(id)>0){
            throw new CustomException(String.format("The user didnt return his books", id.toString()));
        }
        else {
            userRepository.deleteById(id);
        }
    }

    public User update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException();
        } else {
            return userRepository.save(user);
        }
    }

    public User addBook(Long userId, Long bookId) throws CustomException {
        if (nrOfBooks(userId) >= 3) {
            throw new CustomException("You've reached maximum nr of books(3) you can borrow");
        }
        else if(bookRepository.findById(bookId).get().getUser() != null){
            throw new CustomException(String.format("Book of id: %s is already taken", bookId.toString()));

        }
        else {
            User byId = findById(userId).get();
            Book book = bookRepository.findById(bookId).get();
            byId.addBook(book);
            book.setUser(userId);
            book.setBorrowDate(java.time.LocalDate.now().toString());
            book.setReturnDate(java.time.LocalDate.now().plusMonths(1).toString());
            return update(byId);
        }
    }

    public User removeBook(Long userId, Long bookId) {

        User byId = findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        byId.remove(book);
        book.setUser(null);
        book.setBorrowDate(null);
        book.setReturnDate(null);
        return update(byId);

    }

    public int nrOfBooks(Long userId) {
        User byId = findById(userId).get();
        return byId.getBooks().size();
    }

    public Optional<List<User>> findByName(String name) {
        return userRepository.getUserByName(name);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }


}
