/**
 * @Author: Lettle
 * @Create: 2022-11-23 09:15
 * @Description: Person表维护定时服务
 *
 * receive status规则
 *
 * 0 新单                        0000
 * 1 未被接收 未被退回 未超时 已结单  0001
 * 2 未被接收 未被退回 已超时 未结单  0010
 * 3 未被接收 未被退回 已超时 已结单  0011
 * 4 未被接收 已被退回 未超时 未结单  0100
 * 5 未被接收 已被退回 未超时 已结单  0101
 * 6                            0110
 * 7                            0111
 * 8                            1000
 * 9                            1001
 * 10                           1010
 * 11                           1011
 * 12                           1100
 * 13                           1101
 * 14                           1110
 * 15                           1111
 **/
package com.yytech.mpcdsepc.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.AccountMapper;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class ScheduleComponent {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Scheduled(fixedDelay = 1000)
    public void task() {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        List<Person> list = personMapper.selectList(wrapper);
        for(Person p : list) {
            p.setStatusCode(getStatus(p));
            personMapper.updateById(p);
        }
    }

    public int getStatus(Person p) {
        Date deliverTime = p.getDeliverTime();
        long deliverLong = deliverTime.getTime();
        Date now = new Date();
        long nowLong = now.getTime();

        int isReceived=0, isBack=0, isTimeout=0, isFinished=0;

        if(p.getReceiveStatus()) {
            isReceived = 1;
        }
        if(p.getBackFrequency() != 0) {
            isBack = 1;
        }
        if(nowLong - deliverLong > 30*60*1000) {
            isTimeout = 1;
        }
        if(checkRole(p.getManagerId())==3) {
            isFinished = 1;
        }

        return isReceived*8 + isBack*4 + isTimeout*2 + isFinished;
    }
    public int checkRole(int accountId) {
        return accountMapper.selectById(accountId).getRole();
    }
}
