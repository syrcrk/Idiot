package com.spring.mybatis1.mybatisano;

import com.spring.mybatis1.mybatisano.mapper.AppMessageMapper;
import com.spring.mybatis1.mybatisano.model.AppMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MybatisanoApplicationTests {
    @Autowired
    AppMessageMapper mapper;

    @Test
    void testAdd(){
        mapper.insert("456","wmd",new Date());
    }
    @Test
    void testFind(){
        AppMessage message = mapper.selectByPrimaryKey("crk");
        System.out.println(message.getMessage()+" "+message.getSenddate());
    }
}
