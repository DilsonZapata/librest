package com.dahelka.gbh.dao;

import com.dahelka.gbh.entity.Book;
import java.util.List;


public interface IBookDao {
    public List<Book> findAllBooks();
    public Book findBookById(Book book);
}