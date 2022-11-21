/**
 * @Author: Lettle
 * @Create: 2022-11-18 09:20
 * @Description: 工单派发API
 **/
package com.yytech.mpcdsepc.controller.api.workorder;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Vo.PersonVo;
import com.yytech.mpcdsepc.mapper.AccountMapper;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mpcdsepc/api/workorder")
public class ApiDistributionController {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
            return Result.fail("找不到这个人");
        }
        if(self.getRole() > to.getRole()) {
            return Result.fail("禁止越级发送");
        }
        String key = UUID.randomUUID().toString().substring(0,6);
        PersonVo personVo = new PersonVo();

        BeanUtils.copyProperties(p,personVo);
        personVo.setKey(key);
        personVo.setExpireTime(30L);
        redisTemplate.opsForValue().set(key,personVo,30, TimeUnit.MINUTES);
        redisTemplate.opsForList().leftPush(json.get("toManagerId"),key);
        redisTemplate.getExpire(key, TimeUnit.MINUTES);
//        p.setManagerId(Integer.parseInt(json.get("ManagerId")));        // 注意: 这里的ManagerId应当是派发一方的Id
        UpdateWrapper<Person> wrapper = new UpdateWrapper<>();
        wrapper.eq("PersonID",json.get("PersonId")).set("receiveStatus","0");
        personMapper.update(p,wrapper);
        return Result.ok("Order send done");
    }

    /**
     * 被派发工单的员工进行工单拒收
     */
    @PutMapping("/reject")
    public Result reject(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(true);
        redisTemplate.opsForList().remove(json.get("ManagerId"),0,json.get("Key"));
        Boolean flag = redisTemplate.delete(json.get("Key"));
        if (!flag.booleanValue()) {
            return Result.fail("派单已过期");
        }
        p.setBackFrequency(p.getBackFrequency()+1);
        personMapper.updateById(p);
        return Result.ok("拒绝成功");
    }

    /**
     * 被派发工单的员工进行工单接受
     */
    @PutMapping("/accept")
    public Result accept(@RequestBody Map<String, String> json) {
        Person p = personMapper.selectById(json.get("PersonId"));
        p.setReceiveStatus(true);
        redisTemplate.opsForList().remove(json.get("ManagerId"),0,json.get("Key"));
        Boolean flag = redisTemplate.delete(json.get("Key"));
        if (!flag.booleanValue()) {
            return Result.fail("派单已过期");
        }
        p.setManagerId(Integer.parseInt(json.get("ManagerId")));
        p.setDeliverTime(new Date());
        personMapper.updateById(p);
        return Result.ok("接收成功");
    }

    /**
     * 通过员工 id 查询自己有多少工单
     * @param managerId
     * @return
     */
    @GetMapping("/getWorkOrdersById/{managerId}")
    public Result getWorkOrdersById(@PathVariable String managerId) {
        List range = redisTemplate.opsForList().range(managerId, 0, -1);
        List<PersonVo> collect = (List<PersonVo>) range.stream().map(i -> {
            PersonVo personVo = (PersonVo) redisTemplate.opsForValue().get(i);
            Long expire = redisTemplate.getExpire(personVo.getKey(), TimeUnit.MINUTES);
            personVo.setExpireTime(expire);
            return personVo;
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }

    @GetMapping("/getWorkOrderNumById/{managerId}")
    public Result getWorkOrderNumById(@PathVariable String managerId) {

        Long size = redisTemplate.opsForList().size(managerId);
        return Result.ok(size);
    }


}
