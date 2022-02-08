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
        Boolean flag = bookService.saveBook(bookDO);
        return new R(flag, flag ? "添加成功！" : "添加失败...");
    }

    @PutMapping
    public R updateBookById(@RequestBody BookDO bookDO) {
        Boolean flag = bookService.updateBookById(bookDO);
        return new R(flag, flag ? "修改成功！" : "修改失败，请重试...");
    }

    @DeleteMapping("{id}")
    public R removeBookById(@PathVariable Integer id) {
        Boolean flag = bookService.removeBookById(id);
        return new R(flag, flag ? "删除成功！" : "删除失败...");
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
