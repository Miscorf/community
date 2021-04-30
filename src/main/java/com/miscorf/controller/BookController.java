package com.miscorf.controller;

import com.miscorf.pojo.Books;
import com.miscorf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // c run service
    @Autowired
    @Qualifier("BookServiceImpl")


    //select all book

    private BookService bookService;
    @RequestMapping("/allBook")
    public  String list(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book",books );
        return "updateBook";
    }


//    @RequestMapping("/updateBook")
//    public String updateBook(Model model, Books book) {
//        System.out.println(book);
//        bookService.updateBook(book);
//        Books books = bookService.queryBookById(book.getBookID());
//        model.addAttribute("books", books);
//        return "redirect:/book/allBook";
//    }
}
