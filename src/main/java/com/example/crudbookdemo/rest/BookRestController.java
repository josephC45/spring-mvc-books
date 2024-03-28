package com.example.crudbookdemo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.crudbookdemo.entity.Book;
import com.example.crudbookdemo.service.BookService;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book findBook(@PathVariable int id) {
        Book foundBook = bookService.findById(id);
        if(foundBook == null){
            throw new RuntimeException("Book id not found - " + id);
        }
        return foundBook;
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book theBook) {
        theBook.setId(0);
        Book savedBook = bookService.save(theBook);
        return savedBook;
    }
    
    @PutMapping("/books")
    public Book updateBook(@RequestBody Book bookToUpdate) {
        Book updatedBook = bookService.save(bookToUpdate);
        return updatedBook;
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id){
        Book bookToDelete = bookService.findById(id);
        if(bookToDelete == null){
            throw new RuntimeException("Book with id was not found - " + id);
        }
        bookService.deleteById(id);
        return "Book with id was deleted - " + id;
    }
    
}
