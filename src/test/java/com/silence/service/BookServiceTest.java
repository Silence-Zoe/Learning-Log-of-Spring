package com.silence.service;

import com.silence.DO.BookDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    BookService bookService;

    @Test
    void getBookPageTest() {
        System.out.println(bookService.getBookPage(2, 5));
    }

    @Test
    void getBookByIdTest() {
        System.out.println(bookService.getBookById(15));
    }

    @Test
    void conditionSearchTest() {
        BookDO bookDO = new BookDO();
        bookDO.setType("计算机");
        bookDO.setName("Java");
        bookDO.setDescription("Java");
        System.out.println(bookService.getBookPage(3,2, bookDO));
    }
}
