package com.yytech.mpcdsepc.service.impl;

import com.yytech.mpcdsepc.entity.EditSampleHistory;
import com.yytech.mpcdsepc.service.EditSampleHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EditSampleHistoryServiceImpl implements EditSampleHistoryService {
    @Override
    public List<EditSampleHistory> getHistoryBySampleID(String sampleID) {
        return null;
    }

    @Override
    public List<EditSampleHistory> getHistoryByManagerID(String managerID) {
        return null;
    }

    @Override
    public int insert(EditSampleHistory editPersonHistory) {
        return 0;
    }

    @Override
    public int deleteByEditTime(Date date) {
        return 0;
    }

    @Override
    public int deleteByEditSampleID(Integer editSampleID) {
        return 0;
    }
}
