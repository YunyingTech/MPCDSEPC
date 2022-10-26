package com.yytech.MPCDSEPC.controller;

import com.yytech.MPCDSEPC.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/MPCDSEPC")
public class AccountController {

    @RequestMapping(value = "")
    public String login(){
        return "login";
    }

}
