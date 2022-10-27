package com.yytech.mpcdsepc.controller.api.account;
/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/

import com.alibaba.fastjson.JSONObject;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Lettle
 * Create by 2022/10/26 22:46
 */
@Controller
@RequestMapping("/mpcdsepc/api/account")
public class ApiAccountController {

    @Resource
    private AccountServiceImpl accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account login(@RequestParam("username") String userName, @RequestParam("mm") String passWord) {
        return accountService.accountLogin(userName, passWord);
    }

    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public Account select(Account account) {
        System.out.println("select!!");
        Account res;
        if (account.getId() != 0) {
            res = accountService.getAccountById(account.getId());
        } else if (!Objects.equals(account.getName(), "")) {
            res = accountService.getAccountByName(account.getName());
        } else if (!Objects.equals(account.getUserName(), "")) {
            res = accountService.getAccountByUserName(account.getUserName());
        } else {
            res = accountService.getAccountByTeleNum(account.getTeleNum());
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public List<Account> queryAllUser() {
        return accountService.getAllAccount();
    }

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestBody JSONObject jsonObject) {
        Account res = new Account();
        accountService.insertAccount(res);
        return "";//TODO 补全
    }


}
