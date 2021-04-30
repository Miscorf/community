package com.miscorf.service;

import com.miscorf.pojo.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    int addBook(Books books);
    int deleteBookById(int id);
    int updateBook(Books books);
    Books queryBookById( int id);
    List<Books> queryAllBook();

}
