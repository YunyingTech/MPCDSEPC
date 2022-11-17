package com.yytech.mpcdsepc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface EditPersonHistoryMapper {
    List<com.yytech.mpcdsepc.entity.EditPersonHistory> getHistoryByPersonID(String personID);
    List<com.yytech.mpcdsepc.entity.EditPersonHistory> getHistoryByManagerID(String managerID);

    int insert(com.yytech.mpcdsepc.entity.EditPersonHistory editPersonHistory);

    //通过时间删除  service层可以通过时间段删除历史记录
    int deleteByEditTime(Date date);
    //通过记录编号删除
    int deleteByEditPersonID(Integer editPersonID);
}
