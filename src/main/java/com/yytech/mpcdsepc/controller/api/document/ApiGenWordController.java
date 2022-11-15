package com.yytech.mpcdsepc.controller.api.document;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.service.TubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ymx
 * @Create:
 * @Description: 进行中
 **/
@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiGenWordController {
    @Autowired
    private TubeService tubeService;

    public void genWord(){
        Map<String, Object> dataMap = new HashMap<>();
       // dataMap.put();
        try {
            saveWord("word.docx",dataMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveWord(String filePath, Map<String, Object> dataMap) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(ApiGenWordController.class, "/");
        Template template = configuration.getTemplate("word.xml");
        InputStreamSource streamSource = createWord(template, dataMap);
        InputStream inputStream = streamSource.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        byte[] bytes = new byte[1024];
        while ((inputStream.read(bytes)) != -1) {
            outputStream.write(bytes);// 写入数据
        }
        inputStream.close();
        outputStream.close();
    }

    public static InputStreamSource createWord(Template template, Map<String, Object> dataMap) {
        StringWriter out = null;
        Writer writer = null;
        try {
            out = new StringWriter();
            writer = new BufferedWriter(out, 1024);
            template.process(dataMap, writer);
            return new ByteArrayResource(out.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
