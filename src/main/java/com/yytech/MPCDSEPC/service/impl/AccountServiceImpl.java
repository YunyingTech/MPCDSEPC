package com.yytech.MPCDSEPC.service.impl;

import com.yytech.MPCDSEPC.entity.Account;
import com.yytech.MPCDSEPC.mapper.AccountMapper;
import com.yytech.MPCDSEPC.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:36
 * @Description: Account Service 实现
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;


    @Override
    public Account getAccountById(int id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public Account getAccountByUserName(String username) {
        return accountMapper.getAccountByUserName(username);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountMapper.getAccountByName(name);
    }

    @Override
    public Account getAccountByTeleNum(String teleNum) {
        return accountMapper.getAccountByTeleNum(teleNum);
    }

    @Override
    public List<Account> getAccountByRole(String roleName) {
        return accountMapper.getAccountByRole(roleName);
    }

    @Override
    public List<Account> getAccountBySex(boolean isGentle) {
        return accountMapper.getAccountBySex(isGentle);
    }

    @Override
    public List<Account> getAccountByIsOnline(boolean isOnline) {
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public void updateAccountRole(String role) {

    }

    @Override
    public void updateAccountSex(boolean isGentle) {

    }

    @Override
    public void updateAccountPassword(String password) {

    }

    @Override
    public void updateAccountName(String name) {

    }

    @Override
    public void updateAccountTeleNum(String teleNum) {

    }

    @Override
    public void insertAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(int id) {

    }

    @Override
    public Account accountLogin(String userName, String passWord) {
        return null;
    }
}
