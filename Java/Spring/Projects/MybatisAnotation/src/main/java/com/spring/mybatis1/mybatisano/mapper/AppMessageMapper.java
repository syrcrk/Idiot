package com.spring.mybatis1.mybatisano.mapper;


import com.spring.mybatis1.mybatisano.model.AppMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface AppMessageMapper {
    @Insert("INSERT INTO messages(id, message, senddate) VALUES(#{id}, #{message}, #{senddate})")
    int insert(@Param("id") String id, @Param("message")String message, @Param("senddate")Date senddate);

    @Select("SELECT * FROM messages WHERE id = #{id}")
    AppMessage selectByPrimaryKey(@Param("id")String id);

}