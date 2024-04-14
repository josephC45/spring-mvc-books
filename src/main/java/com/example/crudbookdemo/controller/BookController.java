package com.example.crudbookdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crudbookdemo.entity.Book;
import com.example.crudbookdemo.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    //since we only have one service autowire is optional
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/showFormForAdd")
    public String showForm(Model theModel){
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @GetMapping("/list")
    public String listBooks(Model theModel){
        //get books from db
        List<Book> theBooks = bookService.findAll();
        //add books to spring model
        theModel.addAttribute("books", theBooks);

        return "list-books";
    }

    @PostMapping("/processBook")
    public String processForm(@ModelAttribute("book") Book theBook) {
        //TODO: process POST request
        //save book
        bookService.save(theBook);

        //use a redirect to prevent duplicate submission
        return "redirect:/books/list";
    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam("id") int theId, Model theModel){
        Book theBook = bookService.findById(theId);
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId){
        bookService.deleteById(theId);
        return "redirect:/books/list";
    }
    

}
