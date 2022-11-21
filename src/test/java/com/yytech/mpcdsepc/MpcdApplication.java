package com.yytech.mpcdsepc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.service.AccountService;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.utils.TencentMsgUtils;
import com.yytech.mpcdsepc.websocket.TestWebSocket;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class MpcdApplication {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void name() throws TencentCloudSDKException {
       System.out.println(redisTemplate.getExpire("09045bsa"));
    }
}
