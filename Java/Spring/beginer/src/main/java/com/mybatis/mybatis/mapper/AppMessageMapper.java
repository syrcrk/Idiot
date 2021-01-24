package com.mybatis.mybatis.mapper;

import com.mybatis.mybatis.model.AppMessage;

public interface AppMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppMessage record);

    int insertSelective(AppMessage record);

    AppMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppMessage record);

    int updateByPrimaryKey(AppMessage record);
}