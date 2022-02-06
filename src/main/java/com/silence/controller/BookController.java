package com.silence.controller;

import com.silence.DO.BookDO;
import com.silence.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDO> listBooks() {
        return bookService.listBooks();
    }

    @PostMapping
    public void saveBook(@RequestBody BookDO bookDO) {
        bookService.saveBook(bookDO);
    }

    @PutMapping
    public void updateBookById(@RequestBody BookDO bookDO) {
        bookService.updateBookById(bookDO);
    }

    @DeleteMapping("{id}")
    public void removeBookById(@PathVariable Integer id) {
        bookService.removeBookById(id);
    }

    @GetMapping("{id}")
    public BookDO getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @GetMapping("{pageNumber}/{pageSize}")
    public List<BookDO> getBookPage(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return bookService.getBookPage(pageNumber, pageSize);
    }
}
