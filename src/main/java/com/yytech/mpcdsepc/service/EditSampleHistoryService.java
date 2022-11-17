package com.yytech.mpcdsepc.service;

import com.yytech.mpcdsepc.entity.EditPersonHistory;
import com.yytech.mpcdsepc.entity.EditSampleHistory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EditSampleHistoryService {
    List<EditSampleHistory> getHistoryBySampleID(String sampleID);
    List<EditSampleHistory> getHistoryByManagerID(String managerID);

    int insert(EditSampleHistory editPersonHistory);

    //通过时间删除  service层可以通过时间段删除历史记录
    int deleteByEditTime(Date date);
    //通过记录编号删除
    int deleteByEditSampleID(Integer editSampleID);
}
