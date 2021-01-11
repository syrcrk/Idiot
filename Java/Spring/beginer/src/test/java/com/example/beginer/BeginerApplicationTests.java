package com.example.beginer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BeginerApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoad(){
         List<Map<String,Object>> res =jdbcTemplate.queryForList("select * from myTest");
         System.out.println("rest "+res.size());
    }
    @Test
    public void updateTest(){
        jdbcTemplate.execute("update myTest set Name='gg' where Id=1");
        System.out.println("rest success");
    }
}
