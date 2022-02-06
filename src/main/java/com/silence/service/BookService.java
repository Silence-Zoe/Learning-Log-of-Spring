package com.silence.service;

import com.silence.DO.BookDO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    void saveBook(BookDO bookDO);
    void updateBookById(BookDO bookDO);
    void removeBookById(Integer id);
    BookDO getBookById(Integer id);
    List<BookDO> listBooks();
    List<BookDO> getBookPage(int pageNumber, int pageSize);
}
