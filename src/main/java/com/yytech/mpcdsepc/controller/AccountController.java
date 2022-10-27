package com.yytech.mpcdsepc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mpcdsepc")
public class AccountController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

}