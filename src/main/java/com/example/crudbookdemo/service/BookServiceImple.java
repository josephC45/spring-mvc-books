package com.example.crudbookdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudbookdemo.dao.BookRepository;
import com.example.crudbookdemo.entity.Book;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImple implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImple(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        Optional<Book> result = bookRepository.findById(id);
        Book foundBook = null;

        if(result.isPresent()) foundBook = result.get();
        else throw new RuntimeException("Did not find book with id - " + id);
        
        return foundBook;
    }

    @Override
    public Book save(Book theBook) {
        return bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
        return;
    }
}
