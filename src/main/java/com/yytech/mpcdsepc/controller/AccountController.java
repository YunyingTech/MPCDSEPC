package com.yytech.mpcdsepc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mpcdsepc")
public class AccountController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/api/account/login",method = RequestMethod.POST,produces = "application/json")
    public JSONObject loginPOST(HttpRequestHandler httpRequestHandler){
        JSONObject ret = new JSONObject();
        return ret;
    }

}