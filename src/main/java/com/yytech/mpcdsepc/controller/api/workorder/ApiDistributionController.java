/**
 * @Author: Lettle
 * @Create: 2022-11-18 09:20
 * @Description: 工单派发API
 **/
package com.yytech.mpcdsepc.controller.api.workorder;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mpcdsepc/api/workorder")
public class ApiDistributionController {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 派发一个工单
     * @return 是否派发成功
     */
    @PostMapping("/sendOrder")
    public String sendOrder(@RequestBody Map<String,String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        if(p == null) {
            return "No person found";
        }
        p.setManagerId(Integer.parseInt(json.get("ManagerId")));        // 注意: 这里的ManagerId应当是派发一方的Id
        p.setReceiveStatus(false);

        UpdateWrapper<Person> wrapper = new UpdateWrapper<>();
        wrapper.eq("PersonID",json.get("PersonId"));
        personMapper.update(p,wrapper);
        return "Order send done";
    }

    /**
     * 被派发工单的员工进行工单拒收
     */
    @PostMapping("/reject")
    public String reject(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(false);
        p.setBackFrequency(p.getBackFrequency()+1);
        personMapper.updateById(p);
        return "Reject done";
    }

    /**
     * 被派发工单的员工进行工单接受
     */
    @PostMapping("/accept")
    public String accept(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(true);
        p.setManagerId(Integer.parseInt(json.get("ManagerId")));
        p.setDeliverTime(new Date());
        personMapper.updateById(p);
        return "Accepted";
    }

    /**
     * 通过员工 id 查询自己有多少工单
     * @param json
     * @return
     */
    @PostMapping("/getWorkOrdersById")
    public List<Person> getWorkOrdersById(@RequestBody Map<String, String> json) {
        Map<String, Object> search = new HashMap<>();
        search.put("ManagerID",json.get("ManagerId"));
        return personMapper.selectByMap(search);
    }

    @PostMapping("/getWorkOrderNumById")
    public int getWorkOrderNumById(@RequestBody Map<String, String> json) {
        Map<String, Object> search = new HashMap<>();
        search.put("ManagerID",json.get("ManagerId"));
        return personMapper.selectByMap(search).size();
    }


}
