package com.yytech.mpcdsepc.service;

import com.yytech.mpcdsepc.entity.EditPersonHistory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EditPersonHistoryService {
    List<EditPersonHistory> getHistoryByPersonID(String personID);
    List<EditPersonHistory> getHistoryByManagerID(String managerID);

    int insert(EditPersonHistory editPersonHistory);

    //通过时间删除  service层可以通过时间段删除历史记录
    int deleteByEditTime(Date date);
    //通过记录编号删除
    int deleteByEditPersonID(Integer editPersonID);
}
