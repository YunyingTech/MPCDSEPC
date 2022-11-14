package com.yytech.mpcdsepc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.mapper.AccountMapper;
import com.yytech.mpcdsepc.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
