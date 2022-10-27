package com.yytech.mpcdsepc.Controller.api.account;
/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public Account select (Account account) {
        System.out.println("select!!");
        Account res;
        if(account.getId() != 0) {
            res = accountService.getAccountById(account.getId());
        } else if(!Objects.equals(account.getName(), "")) {
            res = accountService.getAccountByName(account.getName());
        } else if (!Objects.equals(account.getUserName(), "")) {
            res = accountService.getAccountByUserName(account.getUserName());
        } else {
            res = accountService.getAccountByTeleNum(account.getTeleNum());
        }
        return  res;
    }

    @ResponseBody
    @RequestMapping(value = "/signup" ,method = RequestMethod.POST)
    public String signup(){
        return "";
    }
}
