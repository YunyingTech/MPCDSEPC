package com.yytech.mpcdsepc.controller.api.account;
/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/

import com.alibaba.fastjson.JSONObject;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lettle
 * Create by 2022/10/26 22:46
 */
@RestController
@CrossOrigin
@RequestMapping("/mpcdsepc/api/account")
public class ApiAccountController {

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> map) {
        Account account = accountService.accountLogin(map.get("username"), map.get("mm"));
        if(account == null){
            return Result.fail("账号或密码错误");
        }
        return Result.ok(account);
    }

    /**
     * /mpcdsepc/api/account/select
     * 根据 id Name Username TeleNum的顺序 只按照一个靠前的信息精准搜索一个用户
     * @param account passed by browser.
     * @return All accounts.
     */
    @PostMapping("select")
    public Result select(@RequestBody Account account) {
        System.out.println("select!!");     // TODO: After debug, then delete it.
//        Account res;
//        if (account.getId() != 0) {
//            res = accountService.getAccountById(account.getId());
//        } else if (!Objects.equals(account.getName(), "")) {
//            res = accountService.getAccountByName(account.getName());
//        } else if (!Objects.equals(account.getUserName(), "")) {
//            res = accountService.getAccountByUserName(account.getUserName());
//        } else {
//            res = accountService.getAccountByTeleNum(account.getTeleNum());
//        }
        return Result.ok(account.getUserName());
    }

    /**
     * /mpcdsepc/api/account/queryAll
     * 获取所有的用户信息
     * TODO: 这里需要识别合法token
     * @return
     */
    @GetMapping("queryAll")
    public Result queryAllUser() {
        return Result.ok(accountService.getAllAccount());
    }

    /**
     * TODO: 这里需要识别合法token
     * @param account passed by browser.
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(Account account) {
        System.out.println("update!!!");    // TODO: After debug, then delete it.
        accountService.updateAccount(account);
        return Result.ok();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestBody JSONObject jsonObject) {
        Account res = new Account();
        accountService.insertAccount(res);
        return "";//TODO 补全
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ok("Logout Success");
    }
}
