package com.example.crudbookdemo.service;

import java.util.List;

import com.example.crudbookdemo.entity.Book;

public interface BookService {
    List<Book> findAll();
    Book findById(int id);
    Book save(Book theBook);
    void deleteById(int id);
}
