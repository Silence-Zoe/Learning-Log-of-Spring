package com.silence.controller;

import com.silence.DO.BookDO;
import com.silence.controller.utils.R;
import com.silence.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public R listBooks() {
        return new R(true, bookService.listBooks());
    }

    @PostMapping
    public R saveBook(@RequestBody BookDO bookDO) {
        return new R(bookService.saveBook(bookDO));
    }

    @PutMapping
    public R updateBookById(@RequestBody BookDO bookDO) {
        return new R(bookService.updateBookById(bookDO));
    }

    @DeleteMapping("{id}")
    public R removeBookById(@PathVariable Integer id) {
        return new R(bookService.removeBookById(id));
    }

    @GetMapping("{id}")
    public R getBookById(@PathVariable Integer id) {
        return new R(true, bookService.getBookById(id));
    }

    @GetMapping("{pageNumber}/{pageSize}")
    public R getBookPage(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return new R(true, bookService.getBookPage(pageNumber, pageSize));
    }
}
