/**
 * @Author: Lettle
 * @Create: 2022-11-14 23:17
 * @Description: 文档操作日志 API
 **/

package com.yytech.mpcdsepc.controller.api.document;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yytech.mpcdsepc.entity.EditPersonHistory;
import com.yytech.mpcdsepc.entity.EditSampleHistory;
import com.yytech.mpcdsepc.mapper.EditPersonHistoryMapper;
import com.yytech.mpcdsepc.mapper.EditSampleHistoryMapper;
import com.yytech.mpcdsepc.service.EditPersonHistoryService;
import com.yytech.mpcdsepc.service.EditSampleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocLogController {

    @Autowired
    private EditPersonHistoryService editPersonHistoryService;
    @Autowired
    private EditSampleHistoryService editSampleHistoryService;

    @Autowired
    private EditSampleHistoryMapper editSampleHistoryMapper;
    @Autowired
    private EditPersonHistoryMapper editPersonHistoryMapper;

    @PostMapping("/getLatestData")
    public String getLastestData(@RequestBody Map<String, Object> json) {
        String res;
        if(json.get("type").equals("0")){
            QueryWrapper<EditSampleHistory> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("editTime");
            res = JSON.toJSONString(editSampleHistoryService.getOnly(wrapper));
        } else {
            QueryWrapper<EditPersonHistory> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("editTime");
            res = JSON.toJSONString(editPersonHistoryService.getOnly(wrapper));
        }
        return res;
    }

    @PostMapping("/getSampleEditLog")
    public List<EditSampleHistory> getSampleEditLog(@RequestBody Map<String, Object> json) {
        return editSampleHistoryMapper.selectList(null);
    }

    @PostMapping("/getPersonEditLog")
    public List<EditPersonHistory> getPersonEditLog(@RequestBody Map<String, Object> json) {

        return editPersonHistoryMapper.selectList(null);
    }

    private String getLatestSample() {
        QueryWrapper<EditSampleHistory> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("EditTime");
        EditSampleHistory editSampleHistory = editSampleHistoryService.getOne(wrapper);
        String res = JSON.toJSONString(editSampleHistory);
        return res;
    }

    private String getLatestPerson() {
        QueryWrapper<EditPersonHistory> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("EditTime");
        EditPersonHistory editPersonHistory = editPersonHistoryService.getOne(wrapper);
        String res = JSON.toJSONString(editPersonHistory);
        return res;
    }
}
