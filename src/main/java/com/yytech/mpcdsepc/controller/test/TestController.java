//package com.yytech.mpcdsepc.controller.test;
//
//import com.aspose.cells.PdfSaveOptions;
//import com.aspose.cells.Workbook;
//import com.yytech.mpcdsepc.result.Result;
//import com.yytech.mpcdsepc.websocket.TestWebSocket;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("test")
//public class TestController {
//
//    @Autowired
//    private TestWebSocket testWebSocket;
//
//    @PostMapping("uploadXls")
//    public void sendAll(@RequestParam(value = "file") MultipartFile file, HttpServletResponse response) throws Exception {
//
//        Workbook wb = new Workbook(file.getInputStream());
//        PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
//        FileOutputStream fileOS = new FileOutputStream("C:\\Users\\Administrator\\Downloads\\某项目日报表.pdf");
//        pdfSaveOptions.setOnePagePerSheet(true);
//        wb.save(fileOS,pdfSaveOptions);
//        fileOS.flush();
//        fileOS.close();
//        System.out.println("convert success");
//    }
//
//}
