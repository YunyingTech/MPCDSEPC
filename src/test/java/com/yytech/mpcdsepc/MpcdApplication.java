package com.yytech.mpcdsepc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class MpcdApplication {

    @Autowired
    private AccountService accountService;

    @Test
    void name() {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getUserName,"Doge").eq(Account::getPassWord,"666");
        Account account = accountService.getOne(lambdaQueryWrapper);
        System.out.println(account);
    }
}
