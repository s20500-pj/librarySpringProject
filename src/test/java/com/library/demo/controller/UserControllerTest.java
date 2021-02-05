package com.library.demo.controller;


import com.library.model.Book;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.UserService;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldMatchContentForHelloWorld() throws Exception{
        mockMvc.perform(get("/user/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world")));

    }

    @Test
    void shouldNotFindById() throws Exception{
        mockMvc.perform(get("/user/2"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"User of id: 2 doesnt exist\",\"debugMessage\":null}")));
    }

    @Test
    void shouldFindById() throws Exception{
        User user = userService.save(new User(1L,"Andrzej","GD"));
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Andrzej\",\"address\":\"GD\",\"books\":[]}")));
    }
    @Test
    void shouldthrownFindById() throws Exception{
        User user = userService.save(new User(1L,"Andrzej","GD"));
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"User of id: 1 doesnt exist\",\"debugMessage\":null}")));
    }

    @Test
    void shouldDeleteById() throws Exception{
        User user = userService.save(new User(1L,"Andrzej","GD"));
        mockMvc.perform(delete("/user/1"))
                .andDo(print())
                .andExpect(status().isOk());}

    @Test
    void shouldThrowErrorFindById() throws Exception{
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"status\":\"BAD_REQUEST\",\"message\":\"User of id: 1 doesnt exist\",\"debugMessage\":null}")));
    }

    @Test
    void shoulAddBook() throws Exception{
        User user = userService.save(new User(1L,"Andrzej","GD"));
        Book book = bookService.save(new Book(1L,"Wiedzmin", "Andrzej"));
        mockMvc.perform(get("/user/addbook/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Andrzej\",\"address\":\"GD\",\"books\":[{\"id\":1,\"name\":\"Wiedzmin\",\"author\":\"Andrzej\",\"borrowDate\":\"2021-02-06\",\"returnDate\":\"2021-03-06\",\"user\":1}]}")));
    }


}
