package com.yytech.MPCDSEPC.mapper;

import com.yytech.MPCDSEPC.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lettle
 * @Create: 2022-10-26 21:45
 * @Description: Account 对 Mybatis 接口
 **/

@Mapper
@Repository
public interface AccountMapper {
    Account getAccountById(int id);
    Account getAccountByUserName(String username);
    Account getAccountByName(String name);
    Account getAccountByTeleNum(String teleNum);

    List<Account> getAccountByRole(String roleName);
    List<Account> getAccountBySex(boolean isGentle);
    List<Account> getAccountByIsOnline(boolean isOnline);
    List<Account> getAllAccount();

    void insertAccount(Account account);
    void updateAccount(Account account);                // 用户信息修改
    void deleteAccount(int id);

    Account accountLogin(String userName, String passWord);

}
