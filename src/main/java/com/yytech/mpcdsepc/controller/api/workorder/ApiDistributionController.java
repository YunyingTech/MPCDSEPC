/**
 * @Author: Lettle
 * @Create: 2022-11-18 09:20
 * @Description: 工单派发API
 **/
package com.yytech.mpcdsepc.controller.api.workorder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.AccountMapper;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mpcdsepc/api/workorder")
public class ApiDistributionController {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 派发一个工单
     * @return 是否派发成功
     */
    @PostMapping("/sendOrder")
    public Result sendOrder(@RequestBody Map<String,String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        Account self = accountMapper.selectById(json.get("ManagerId"));
        Account to = accountMapper.selectById(json.get("toManagerId"));
        if(p == null) {
            return Result.fail("No person found");
        }
        if(self == null) {
            return Result.fail("Can't find myself");
        }
        if(to == null) {
            return Result.fail("Can't find the target");
        }
        if(self.getRole() < to.getRole()) {
            return Result.fail("Not a correct role");
        }
        p.setManagerId(Integer.parseInt(json.get("ManagerId")));        // 注意: 这里的ManagerId应当是派发一方的Id
        p.setReceiveStatus(false);

        UpdateWrapper<Person> wrapper = new UpdateWrapper<>();
        wrapper.eq("PersonID",json.get("PersonId"));
        personMapper.update(p,wrapper);
        return Result.ok("Order send done");
    }

    /**
     * 被派发工单的员工进行工单拒收
     */
    @PutMapping("/reject")
    public Result reject(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(false);
        p.setBackFrequency(p.getBackFrequency()+1);
        personMapper.updateById(p);
        return Result.ok("Order send done");
    }

    /**
     * 被派发工单的员工进行工单接受
     */
    @PutMapping("/accept")
    public Result accept(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(true);
        p.setManagerId(Integer.parseInt(json.get("ManagerId")));
        p.setDeliverTime(new Date());
        personMapper.updateById(p);
        return Result.ok("Accepted");
    }

    /**
     * 通过员工 id 查询自己有多少工单
     * @param managerId
     * @return
     */
    @GetMapping("/getWorkOrdersById/{managerId}")
    public Result getWorkOrdersById(@PathVariable String managerId) {
        Map<String, Object> search = new HashMap<>();
        search.put("ManagerID",managerId);
        return Result.ok(personMapper.selectByMap(search));
    }

    @GetMapping("/getWorkOrderNumById/{managerId}")
    public Result getWorkOrderNumById(@PathVariable String managerId) {
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Person::getManagerId,managerId);
        return Result.ok(personMapper.selectCount(lambdaQueryWrapper));
    }


}
