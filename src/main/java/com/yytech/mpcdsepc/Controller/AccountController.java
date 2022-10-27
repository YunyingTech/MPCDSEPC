package com.yytech.mpcdsepc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mpcdsepc")
public class AccountController {

    @RequestMapping(value = "")
    public String login(){
        return "login";
    }

}