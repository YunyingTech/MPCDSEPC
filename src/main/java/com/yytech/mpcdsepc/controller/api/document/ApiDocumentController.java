package com.yytech.mpcdsepc.controller.api.document;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller(value = "/mpcdspec/document")
public class ApiDocumentController {
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(){
        return "";
    }

    /**
     * POST /mpcdsepc/document/tubelist : 获取单管数据
     * 获取单管数据
     *
     * @param token 验证身份 (required)
     * @param year  获取单管数据的年 (required)
     * @param month 获取单管数据的月 (required)
     * @param day   获取单管数据的日 (optional)
     * @return 成功 (status code 200)
     */
    @RequestMapping(value = "/mpcdsepc/document/tubelist",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public String mpcdsepcDocumentTubelistPost(@CookieValue("token") String token, @RequestParam(value = "year") Integer year, @RequestParam(value = "month") Integer month, @RequestParam(value = "day") Integer day) {
        return "";
    }
}
