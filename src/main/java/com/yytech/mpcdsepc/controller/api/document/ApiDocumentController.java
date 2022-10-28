package com.yytech.mpcdsepc.controller.api.document;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocumentController {

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public static String upload(HttpServletRequest httpServletRequest,@RequestParam("filename") String filename){
        try(InputStream inputStream = httpServletRequest.getInputStream()){
            String retFileName = UUID.randomUUID().toString();
            System.out.println(retFileName);
            File file = new File("Files\\" + retFileName);
            if(!file.getParentFile().exists()){
                boolean mkdirsSuccess = file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream("Files\\" + retFileName, false);
            fileOutputStream.write(inputStream.readAllBytes());
            fileOutputStream.close();
            return inputStream.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
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
    @ResponseBody
    @RequestMapping(value = "/tubelist",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public String mpcdsepcDocumentTubelistPost(@CookieValue("token") String token, @RequestParam(value = "year") Integer year, @RequestParam(value = "month") Integer month, @RequestParam(value = "day") Integer day) {
        return "";
    }
}
