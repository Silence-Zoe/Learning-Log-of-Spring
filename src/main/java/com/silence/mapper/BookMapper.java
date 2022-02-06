package com.silence.mapper;

import com.silence.DO.BookDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM tbl_book WHERE id = #{id}")
    BookDO getBookById(Integer id);

    @Select("SELECT * FROM tbl_book")
    List<BookDO> listBooks();

    @Insert("INSERT INTO tbl_book(type, name, description) VALUES (#{type}, #{name}, #{description})")
    int saveBook(BookDO bookDO);

    @Delete("DELETE FROM tbl_book WHERE id = #{id}")
    int removeBookById(Integer id);

    @Update("UPDATE tbl_book SET type = #{type}, name = #{name}, description = #{description} WHERE id = #{id}")
    int updateBookById(BookDO bookDO);
}
