package com.mybatis.mybatis.service;

import com.mybatis.mybatis.model.AppMessage;

import java.util.List;

public interface IAppMessageService {
//    List<AppMessage> getMessage();
    List<AppMessage> getAllMessage();
   int addMessage(AppMessage msg);
//    List<AppMessage> getMessageById(String id);
    int delMessage(String id);
}
