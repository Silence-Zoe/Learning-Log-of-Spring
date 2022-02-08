package com.silence.service;

import com.github.pagehelper.PageInfo;
import com.silence.DO.BookDO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    Boolean saveBook(BookDO bookDO);
    Boolean updateBookById(BookDO bookDO);
    Boolean removeBookById(Integer id);
    BookDO getBookById(Integer id);
    List<BookDO> listBooks();
    PageInfo<BookDO> getBookPage(int pageNumber, int pageSize);
}
