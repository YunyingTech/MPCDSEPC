package com.yytech.mpcdsepc.service;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:30
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yytech.mpcdsepc.entity.EditSampleHistory;
import com.yytech.mpcdsepc.result.Result;

public interface EditSampleHistoryService extends IService<EditSampleHistory> {
    default EditSampleHistory getOnly(QueryWrapper<EditSampleHistory> wrapper) {
        wrapper.last("limit 1");
        return this.getOne(wrapper);
    }
}
