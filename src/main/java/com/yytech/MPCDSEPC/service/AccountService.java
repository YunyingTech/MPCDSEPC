package com.yytech.MPCDSEPC.service;

import com.yytech.MPCDSEPC.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:35
 * @Description: Account 服务接口
 **/
@Service
public interface AccountService {
    Account getAccountById(int id);
    Account getAccountByUserName(String username);
    Account getAccountByName(String name);
    Account getAccountByTeleNum(String teleNum);

    List<Account> getAccountByRole(String roleName);
    List<Account> getAccountBySex(boolean isGentle);
    List<Account> getAccountByIsOnline(boolean isOnline);
    List<Account> getAllAccount();

    void updateAccountRole(String role);
    void updateAccountSex(boolean isGentle);
    void updateAccountPassword(String password);
    void updateAccountName(String name);
    void updateAccountTeleNum(String teleNum);

    void insertAccount(Account account);
    void updateAccount(Account account);                // 用户信息修改
    void deleteAccount(int id);

    Account accountLogin(String userName, String passWord);
}
