package com.library.demo.service;

import com.library.AdviceErrorHandler.CustomException;
import com.library.model.Book;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class UserServiceTestIT {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @BeforeEach
    void cleanup(){
        userService.deleteAll();
    }

    @Test
    void shouldNotFindAnyone(){
        List<User> all = userService.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    void shouldFindAllPlayers(){
        userService.save(new User("Andrzej","GD"));
        List<User> all = userService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldSavePlayer(){
        User user = userService.save(new User(1L,"Andrzej", "GD"));
        assertThat(user.getId()).isPositive();
    }

    @Test
    void shouldFindById(){
        User user = userService.save(new User(5L,"Andrzej","GD"));
        assertThat(userService.findById(user.getId())).isNotEmpty();
    }
    @Test
    void shouldThrowExceptionOnFindById() throws CustomException{
        assertThatExceptionOfType(CustomException.class)
                .isThrownBy(() -> userService.findById(10L));
    }
    @Test
    void shouldAddBook() throws CustomException {
        User user = userService.save(new User(1L,"Andrzej", "GD"));
        Book book = bookService.save(new Book(1L,"Wiedzmin", "Andrzej"));
        userService.addBook(user.getId(),book.getId());
        assertThat(!userService.findById(user.getId()).get().getBooks().isEmpty());
    }

}
