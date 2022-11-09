package com.yytech.mpcdsepc.controller.api.account;
/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lettle
 * Create by 2022/10/26 22:46
 */
@Controller
@CrossOrigin
@RequestMapping("/mpcdsepc/api/account")
public class ApiAccountController {

    @Resource
    private AccountServiceImpl accountService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account login(@RequestBody Map<String, String> map) {
        return accountService.accountLogin(map.get("username"), map.get("mm"));
    }

    /**
     * /mpcdsepc/api/account/select
     * 根据 id Name Username TeleNum的顺序 只按照一个靠前的信息精准搜索一个用户
     * @param account passed by browser.
     * @return All accounts.
     */
    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public Account select(Account account) {
        System.out.println("select!!");     // TODO: After debug, then delete it.
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

    /**
     * /mpcdsepc/api/account/queryAll
     * 获取所有的用户信息
     * TODO: 这里需要识别合法token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public List<Account> queryAllUser() {
        return accountService.getAllAccount();
    }

    /**
     * TODO: 这里需要识别合法token
     * @param account passed by browser.
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(Account account) {
        System.out.println("update!!!");    // TODO: After debug, then delete it.
        accountService.updateAccount(account);
    }

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestBody JSONObject jsonObject) {
        Account res = new Account();
        accountService.insertAccount(res);
        return "";//TODO 补全
    }

    @ResponseBody
    @RequestMapping(value = "/logout",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public String logout() {
        return "Logout Success";
    }
}
