/**
 * @Author: Lettle
 * @Create: 2022-11-12 18:14
 * @Description: 上传文件
 **/
package com.yytech.mpcdsepc.controller.api.document;
import com.yytech.mpcdsepc.utils.X2PDF;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.yytech.mpcdsepc.utils.X2PDF.wordToPdf;
import static com.yytech.mpcdsepc.utils.X2PDF.xlsxToPdf;

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


    @RequestMapping(path = "/previewFile")
    public String preview(@RequestParam(required = true)String path, @RequestParam(required = true)String fileName, @RequestParam(required = true)String suffix) throws Exception {
        // 读取pdf文件的路径
        String pdfPath = "";
        // 将对应的后缀转换成小写
        String lastSuffix = suffix.toLowerCase();
        //读取文件内容,获取文件存储的路径
        String orgPath = "";
        if (lastSuffix.equals(".xls") || lastSuffix.equals(".xlsx")) {
            orgPath = "upload/excels/";
        } else if (lastSuffix.equals(".doc") || lastSuffix.equals(".docx")) {
            orgPath = "upload/words/";
        } else {
            //返回错误 文档类型错误
            return "409";
        }

        // 生成pdf文件的路径
        String toPath = "upload/pdfs/";
        // 判断对应的pdf是否存在，不存在则创建
        File folder = new File(toPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 转换之后的pdf文件
        String newName = fileName.replace(lastSuffix, "pdf");
        ;
        File newFile = new File(toPath + "/" + newName);
        // 如果转换之后的文件夹中有转换后的pdf文件，则直接从里面读取即可
        if (newFile.exists()) {
            pdfPath = toPath + "/" + newName;
        } else if (lastSuffix.equals("doc") || lastSuffix.equals("docx")) {
            pdfPath = X2PDF.wordToPdf(fileName, orgPath, toPath, lastSuffix);
        } else if (lastSuffix.equals("xls") || lastSuffix.equals("xlsx")) {
            pdfPath = X2PDF.xlsxToPdf(fileName, orgPath, toPath, lastSuffix);
        } else {
            return "409";
        }

        return pdfPath;
    }
}
