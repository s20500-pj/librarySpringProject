package com.library.demo.service;

import com.library.AdviceErrorHandler.CustomException;
import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;

    @Test
    void shouldfindAll() {
        //Given
        when(userRepository.findAll()).thenReturn(List.of());
        //When
        List<User> all = userService.findAll();
        //Then
        assertThat(all).isEmpty();
    }

    @Test
    void findById() {
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L,"Andrzej", "GD")));
        //When
        Optional<User> test = userService.findById(1L);
        //Then
        assertThat(test.get().getId()).isEqualTo(1L);
    }

    @Test
    void save(){
        //Given
        User user = new User("Andrzej", "GD");
        when(userRepository.save(user)).thenReturn(new User(1L,"Andrzej", "GD"));
        //When
        User user2 = userService.save(user);
        //Then
        assertThat(user2.getId().equals(1L));
    }
    @Test
    void deleteById() throws CustomException {
        //Given

        //When
        userService.deleteById(1L);
        //Then
        verify(userRepository, times(1)).deleteById(1L);
    }
    @Test
    void addbook() throws CustomException {
        User user = new User(1L,"Andrzej","GD");
        Book book = new Book(1L,"Wiedzmin", "Andrzej");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(userRepository.save(user)).thenReturn(user);
        when(bookRepository.save(book)).thenReturn(book);
        when(userService.addBook(1L,any())).thenCallRealMethod();
        User userbook = userService.addBook(1L,1L);
        assertThat(userbook.getBooks().contains(book));
    }

    @Test
    void nrOfBooks(){
        User user = new User(1L,"Andrzej","GD");
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Andrzej", "GD")));
        when(userService.nrOfBooks(1L)).thenReturn(user.getBooks().size());
        int nr = userService.nrOfBooks(1L);
        assertThat(nr == 0);
    }
}
