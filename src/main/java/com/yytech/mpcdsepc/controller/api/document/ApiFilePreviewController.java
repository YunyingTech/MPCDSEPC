package com.yytech.mpcdsepc.controller.api.document;

import com.yytech.mpcdsepc.utils.X2PDF;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 文件预览接口 传入 文件路径名
 * 返回pdf文件流
 */
@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiFilePreviewController {
    @RequestMapping(path = "/previewFile")
    public String preview(@RequestParam(required = true)String filePath, HttpServletResponse response){
//        String uploadPath = "src/main/resources/upload/";
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadPath = filePath.replace(fileName,"").replace("words/","").replace("excels/","");

        String pdfPath = "";

        // 将对应的后缀转换成小写
        String lastSuffix = suffix.toLowerCase();
        //读取文件内容,获取文件存储的路径
        String orgPath = "";
        if (lastSuffix.equals("xls") || lastSuffix.equals("xlsx")) {
            orgPath += uploadPath+"excels/";
        } else if (lastSuffix.equals("doc") || lastSuffix.equals("docx")) {
            orgPath += uploadPath+"words/";
        } else {
            //返回错误 文档类型错误
            return "fileType err";
        }

        // 生成pdf文件的路径
        String toPath = uploadPath+"pdfs/";

        File toDic = new File(toPath);
        if(!toDic.exists()){
            toDic.mkdirs();
        }


        // 转换之后的pdf文件
        String newName = fileName.replace(suffix, "pdf");
        File newFile = new File(toPath + "/" + newName);
        // 如果转换之后的文件夹中有转换后的pdf文件，则直接从里面读取即可
        if (newFile.exists()) {
            pdfPath = toPath + "/" + newName;
        } else if (lastSuffix.equals("doc") || lastSuffix.equals("docx")) {
            try {
                System.out.println("in doc convert");
                pdfPath = X2PDF.wordToPdf(fileName, orgPath, toPath, lastSuffix);
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                throw new RuntimeException(e);
            }
//            System.out.println(pdfPath);
        } else if (lastSuffix.equals("xls") || lastSuffix.equals("xlsx")) {
            try {
//                pdfPath = X2PDF.xlsxToPdf(fileName, orgPath, toPath, lastSuffix);
                System.out.println(orgPath+fileName);
                pdfPath = X2PDF.xlsxToPdf(fileName, orgPath, toPath, lastSuffix);
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                throw new RuntimeException(e);
            }
//            System.out.println(pdfPath);
        } else {
            return "convert err";
        }

//        response.setContentType("application/pdf");
        File file = new File(uploadPath+"/pdfs/"+fileName.replace(suffix,"pdf"));
        if (file.exists()){
            byte[] data = null;
            try {
                FileInputStream input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
                input.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }else{
            return "file not found";
        }

        return "200";
    }
}
