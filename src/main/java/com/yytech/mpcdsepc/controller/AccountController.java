package com.yytech.mpcdsepc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/mpcdsepc")
public class AccountController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    

}