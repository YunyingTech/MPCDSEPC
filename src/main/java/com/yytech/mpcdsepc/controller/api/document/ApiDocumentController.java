package com.yytech.mpcdsepc.controller.api.document;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.util.LoadPersonXlsxToDB;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocumentController {

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
    @RequestMapping(value = "/tubelist",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public String mpcdsepcDocumentTubelistPost(@CookieValue("token") String token, @RequestParam(value = "year") Integer year, @RequestParam(value = "month") Integer month, @RequestParam(value = "day") Integer day) {
        return "";
    }

    @Resource
    LoadPersonXlsxToDB loadPersonXlsxToDB;

    /**
     * 测试 文件上转解析到数据库
     * @return
     * @throws FileNotFoundException
     */
    @RequestMapping("/uploadPerson")
    public String uploadPerson() throws FileNotFoundException {
        loadPersonXlsxToDB.openXlsxAndProcess(new FileInputStream("E:\\code\\MPCDSEPC\\src\\main\\resources\\upload\\person_example.xlsx"));

        return "ok";
    }

    @RequestMapping("/uploadTube")
    public String uploadTube() throws FileNotFoundException {
        loadPersonXlsxToDB.openXlsxAndProcess(new FileInputStream("E:\\code\\MPCDSEPC\\src\\main\\resources\\upload\\tube_example.xlsx"));

        return "ok";
    }
}
