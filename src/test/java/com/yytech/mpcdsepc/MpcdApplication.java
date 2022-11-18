package com.yytech.mpcdsepc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.service.AccountService;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.utils.TencentMsgUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class MpcdApplication {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PersonService personService;

    @Test
    void name() throws TencentCloudSDKException {
        Page<Person> page = new Page<>(1,10);
        Page<Person> page1 = personService.page(page);
        System.out.println(page1.getRecords());
    }
}
