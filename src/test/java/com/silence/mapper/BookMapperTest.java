package com.silence.mapper;

import com.silence.DO.BookDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookMapperTest {
    @Autowired
    BookMapper bookMapper;

    @Test
    void getBookByIdTest() {
        System.out.println(bookMapper.getBookById(6));
    }

    @Test
    void listBooksTest() {
        System.out.println(bookMapper.listBooks());
    }

    @Test
    void saveBookTest() {
        BookDO bookDO = new BookDO();
        bookDO.setType("测试一下");
        bookDO.setName("呵呵呵");
        bookDO.setDescription("测试用的书");
        System.out.println(bookMapper.saveBook(bookDO));
    }

    @Test
    void removeBookByIdTest() {
        System.out.println(bookMapper.removeBookById(16));
    }

    @Test
    void updateBookByIdTest() {
        BookDO bookDO = new BookDO();
        bookDO.setId(19);
        bookDO.setType("测试修改");
        bookDO.setName("测试一下修改");
        bookDO.setDescription("abab");
        System.out.println(bookMapper.updateBookById(bookDO));
    }

    @Test
    void selectBookByTypeTest() {
        System.out.println(bookMapper.selectByType("算"));
    }

    @Test
    void selectBookByNameTest() {
        System.out.println(bookMapper.selectByName("Spring"));
    }

    @Test
    void selectBookByDescriptionTest() {
        System.out.println(bookMapper.selectByDescription("全"));
    }
}
