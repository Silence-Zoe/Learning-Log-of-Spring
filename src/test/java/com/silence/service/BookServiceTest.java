package com.silence.service;

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
}
