package com.yytech.mpcdsepc;

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
        System.out.println(6666);
    }
}
