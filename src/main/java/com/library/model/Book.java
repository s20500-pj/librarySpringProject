package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String borrowDate;
    private String returnDate;
    private Long user;

    public Book(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.borrowDate = "";
        this.returnDate = "";
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.borrowDate = "";
        this.returnDate = "";
    }

    public Book() {
    }

    public Book(Long id, String name, String author, String borrowDate, String returnDate, Long user, boolean isBorrowed) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
