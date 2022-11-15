package com.yytech.mpcdsepc.service;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:31
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yytech.mpcdsepc.entity.EditPersonHistory;
import com.yytech.mpcdsepc.entity.EditSampleHistory;

public interface EditPersonHistoryService extends IService<EditPersonHistory> {
    default EditPersonHistory getOnly(QueryWrapper<EditPersonHistory> wrapper) {
        wrapper.last("limit 1");
        return this.getOne(wrapper);
    }
}
