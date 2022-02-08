package com.silence.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Boolean saveBook(BookDO bookDO) {
        return bookMapper.saveBook(bookDO) != 0;
    }

    @Override
    public Boolean updateBookById(BookDO bookDO) {
        return bookMapper.updateBookById(bookDO) != 0;
    }

    @Override
    public Boolean removeBookById(Integer id) {
        return bookMapper.removeBookById(id) != 0;
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
    public PageInfo<BookDO> getBookPage(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(bookMapper.listBooks());
    }
}
