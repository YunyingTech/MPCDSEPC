package com.yytech.mpcdsepc.service.impl;

import com.yytech.mpcdsepc.entity.EditPersonHistory;
import com.yytech.mpcdsepc.service.EditPersonHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EditPersonHistoryServiceImpl implements EditPersonHistoryService {
    @Override
    public List<EditPersonHistory> getHistoryByPersonID(String personID) {
        return null;
    }

    @Override
    public List<EditPersonHistory> getHistoryByManagerID(String managerID) {
        return null;
    }

    @Override
    public int insert(EditPersonHistory editPersonHistory) {
        return 0;
    }

    @Override
    public int deleteByEditTime(Date date) {
        return 0;
    }

    @Override
    public int deleteByEditPersonID(Integer editPersonID) {
        return 0;
    }
}
