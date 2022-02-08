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

    @Select("SELECT * FROM tbl_book WHERE type LIKE CONCAT('%', #{pattern}, '%')")
    List<BookDO> selectByType(String type);

    @Select("SELECT * FROM tbl_book WHERE name LIKE CONCAT('%', #{pattern}, '%')")
    List<BookDO> selectByName(String name);

    @Select("SELECT * FROM tbl_book WHERE description LIKE CONCAT('%', #{pattern}, '%')")
    List<BookDO> selectByDescription(String description);
}
