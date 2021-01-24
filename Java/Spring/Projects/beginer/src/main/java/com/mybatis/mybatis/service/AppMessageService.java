package com.mybatis.mybatis.service;

import com.mybatis.mybatis.mapper.AppMessageMapper;
import com.mybatis.mybatis.model.AppMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value="appService")
public class AppMessageService  implements  IAppMessageService{
    @Autowired
    AppMessageMapper mapper;

    @Override
    public List<AppMessage> getAllMessage() {
        return null;
    }

    @Override
    public int addMessage(AppMessage msg) {
        return mapper.insert(msg);
    }

    @Override
    public int delMessage(String id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
