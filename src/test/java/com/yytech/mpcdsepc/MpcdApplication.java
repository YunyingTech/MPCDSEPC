package com.yytech.mpcdsepc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.service.AccountService;
import com.yytech.mpcdsepc.utils.TencentMsgUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class MpcdApplication {

    @Autowired
    private AccountService accountService;

    @Test
    void name() throws TencentCloudSDKException {
        TencentMsgUtils.sendMsg("17311637630","11111");
    }
}
