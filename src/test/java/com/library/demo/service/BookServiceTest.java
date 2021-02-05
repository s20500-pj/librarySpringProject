package com.library.demo.service;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.service.BookService;
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
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Test
    void shouldfindAll() {
        //Given
        when(bookRepository.findAll()).thenReturn(List.of());
        //When
        List<Book> all = bookService.findAll();
        //Then
        assertThat(all).isEmpty();
    }

    @Test
    void findById() {
        //Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book(1L,"Wiedzmin", "Andrzej")));
        //When
        Optional<Book> test = bookService.findById(1L);
        //Then
        assertThat(test.get().getId()).isEqualTo(1L);
    }

    @Test
    void save(){
        //Given
        Book book = new Book("Wiedzmin", "Andrzej");
        when(bookRepository.save(book)).thenReturn(new Book(1L,"Wiedzmin", "Andrzej"));
        //When
        Book book1 = bookService.save(book);
        //Then
        assertThat(book1.getId().equals(1L));
    }
    @Test
    void deleteById() {
        //Given

        //When
        bookService.deleteById(1L);
        //Then
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
