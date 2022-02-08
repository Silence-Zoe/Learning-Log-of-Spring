package com.silence.service.impl;

import com.github.pagehelper.Page;
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

    @Override
    public PageInfo<BookDO> getBookPage(int pageNumber, int pageSize, BookDO bookDO) {
        List<BookDO> list = bookMapper.listBooks();
        String type = bookDO.getType();
        String name = bookDO.getName();
        String description = bookDO.getDescription();
        if (type != null) {
            list.retainAll(bookMapper.selectByType(type));
        }
        if (name != null) {
            list.retainAll(bookMapper.selectByName(name));
        }
        if (description != null) {
            list.retainAll(bookMapper.selectByDescription(description));
        }

        // 对List进行分页
        // 创建Page类
        Page page = new Page(pageNumber, pageSize);
        // 为Page类中的total属性赋值
        page.setTotal(list.size());
        // 计算当前需要显示的数据下标起始值
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        // 从链表中截取需要显示的子链表，并加入到Page
        page.addAll(list.subList(startIndex,endIndex));
        // 以Page创建PageInfo
        return new PageInfo<>(page);
    }

}
