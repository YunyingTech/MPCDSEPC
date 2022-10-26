package com.yytech.MPCDSEPC.controller.api.account;/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/

import com.yytech.MPCDSEPC.entity.Account;
import com.yytech.MPCDSEPC.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Lettle
 * Create by 2022/10/26 22:46
 */
@Controller
@RequestMapping("/MPCDSEPC/api/account")
public class ApiAccountController {

    @Resource
    private AccountServiceImpl accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account login (@RequestParam("username") String userName, @RequestParam("mm") String passWord) {
        return accountService.accountLogin(userName, passWord);
    }

    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public Account select (@RequestParam("id") int userId, @RequestParam("userName") String userName, @RequestParam("name") String name, @RequestParam("teleNum")String teleNum) {
        System.out.println("select!!");
        Account account;
        if(!Objects.equals(userId, "")) {
            account = accountService.getAccountById(userId);
        } else if(!Objects.equals(name, "")) {
            account = accountService.getAccountByName(name);
        } else if (!Objects.equals(userName, "")) {
            account = accountService.getAccountByUserName(userName);
        } else {
            account = accountService.getAccountByTeleNum(teleNum);
        }
        return  account;
    }

}
