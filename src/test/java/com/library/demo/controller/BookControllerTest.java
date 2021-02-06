package com.library.demo.controller;

import com.library.model.Book;
import com.library.model.User;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/book")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldMatchContentForHelloWorld() throws Exception{
        mockMvc.perform(get("/book/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world")));

    }

    @Test
    void shouldNotFindById() throws Exception{
        mockMvc.perform(get("/book/2"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"Book of id: 2 doesnt exist\",\"debugMessage\":null}")));
    }


    @Test
    void shouldThrowErrorFindById() throws Exception{
        mockMvc.perform(get("/book/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"Book of id: 1 doesnt exist\",\"debugMessage\":null}")));
    }

    @Test
    void shouldFindById() throws Exception{
        Book book = bookService.save(new Book(1L,"Wiedzmin","Andrzej"));
        mockMvc.perform(get("/book/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Wiedzmin\",\"author\":\"Andrzej\",\"borrowDate\":\"\",\"returnDate\":\"\",\"user\":null}")));
    }
    @Test
    void shouldthrownFindById() throws Exception{
        Book book = bookService.save(new Book(1L,"Wiedzmin","Andrzej"));
        mockMvc.perform(get("/book/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"Book of id: 1 doesnt exist\",\"debugMessage\":null}")));
    }

    @Test
    void shouldDeleteById() throws Exception{
        Book book = bookService.save(new Book(1L,"Wiedzmin","Andrzej"));
        mockMvc.perform(delete("/book/1"))
                .andDo(print())
                .andExpect(status().isOk());}


}
