/**
 * @Author: Lettle
 * @Create: 2022-11-12 18:14
 * @Description: 上传文件
 **/
package com.yytech.mpcdsepc.controller.api.document;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocUploadController {

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file){
        return saveFile(file);
    }
    @PostMapping("/multiUpload")
    public Object multiUpload(@RequestParam("file")MultipartFile[] files){
        for (MultipartFile f : files){
            String res = saveFile(f);
            if(!res.equals("ok")) {
                return res;
            }
        }
        return "ok";
    }

    private String saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath;
        if(filename.endsWith(".xls") || filename.endsWith(".xlsx")) {
            filePath = "upload/excels/";
        } else if (filename.endsWith(".doc") || filename.endsWith(".docx")) {
            filePath = "upload/words/";
        } else if (filename.endsWith(".pdf")) {
            filePath = "upload/pdfs/";
        } else {
            return "非法的文件类型,上传失败";
        }

        File temp = new File("upload");
        if (!temp.exists()){
            temp.mkdirs();
        }
        temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile = new File(filePath + filename);
        System.out.println(filePath + filename);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功:" + filePath + filename);
        }catch (IOException e){
            e.printStackTrace();
            return "上传失败";
        }

        return "ok";
    }
}
