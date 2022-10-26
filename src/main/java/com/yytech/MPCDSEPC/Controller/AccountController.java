package com.yytech.MPCDSEPC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller(value = "/MPCDSEPC/account")
public class AccountController {

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(@RequestParam("username") String userName,@RequestParam("mm") String passWord){
        return "";
    }

}
