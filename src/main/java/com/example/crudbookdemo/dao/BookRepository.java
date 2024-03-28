package com.example.crudbookdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudbookdemo.entity.Book;

// Provide Entity type and primary key
public interface BookRepository extends JpaRepository<Book,Integer> {

}
