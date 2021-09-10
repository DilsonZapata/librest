package com.dahelka.gbh.service;

import com.dahelka.gbh.entity.Book;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IBookService {

    public List<Book> BooksList();

    public Book findBookById(Book book);

}
