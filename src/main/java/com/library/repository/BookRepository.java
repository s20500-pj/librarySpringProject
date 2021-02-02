package com.library.repository;

import com.library.model.Book;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT p from Book p where p.name = :name")
    Optional<List<Book>> getBookByName(String name);

}
