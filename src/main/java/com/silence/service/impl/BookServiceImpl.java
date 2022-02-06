package com.silence.service.impl;

import com.github.pagehelper.PageHelper;
import com.silence.DO.BookDO;
import com.silence.mapper.BookMapper;
import com.silence.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void saveBook(BookDO bookDO) {
        bookMapper.saveBook(bookDO);
    }

    @Override
    public void updateBookById(BookDO bookDO) {
        bookMapper.updateBookById(bookDO);
    }

    @Override
    public void removeBookById(Integer id) {
        bookMapper.removeBookById(id);
    }

    @Override
    public BookDO getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public List<BookDO> listBooks() {
        return bookMapper.listBooks();
    }

    @Override
    public List<BookDO> getBookPage(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return bookMapper.listBooks();
    }
}
