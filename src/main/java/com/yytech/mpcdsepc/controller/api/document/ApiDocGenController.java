/**
 * @Author: Lettle
 * @Create: 2022-11-16 14:14
 * @Description: 生成文件调用的API
 **/
package com.yytech.mpcdsepc.controller.api.document;

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.utils.POIUtils;
import com.yytech.mpcdsepc.utils.X2PDF;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mpcdsepc/api/gendocument")
public class ApiDocGenController {

    @PostMapping("/wordGen")
    public void wordGen(@RequestBody Map<String, Object> json, HttpServletResponse response) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一张表
        Sheet sheet = wb.createSheet("Covid");
        //创建第一行，起始为0
        Row titleRow = sheet.createRow(0);
        //第一列
//        序号	区县	街道	采样点	采样类型	混检Ct值	混检检出时间	风险元素	单检结果	Ct值	单检阳性时间

        titleRow.createCell(0).setCellValue("序号");
        System.out.println("= - =" + titleRow.getCell(0).getStringCellValue());
        //第二列
        titleRow.createCell(1).setCellValue("区县");
        //第三列
        titleRow.createCell(2).setCellValue("街道");
        //第四列
        titleRow.createCell(3).setCellValue("采样点");
        //第五列
        titleRow.createCell(4).setCellValue("采样类型");
        //第六列
        titleRow.createCell(5).setCellValue("混检Ct值");
        titleRow.createCell(6).setCellValue("风险元素");
        titleRow.createCell(7).setCellValue("单检结果");
        titleRow.createCell(8).setCellValue("Ct值");
        titleRow.createCell(9).setCellValue("单检阳性时间");
        //序号，默认为1
        int cell = 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        //遍历
        List<Map<String,String>> datas = (List<Map<String, String>>) json.get("data");
        for (Map<String,String> data : datas) {
            System.out.println(data);
            //第一行保存的是每一列的列名
            //从第二行开始保存数据
            Row row = sheet.createRow(cell);
            //第一列 序号
            row.createCell(0).setCellValue(data.get("id"));
            row.createCell(1).setCellValue(data.get("district"));
            row.createCell(2).setCellValue(data.get("street"));
            row.createCell(3).setCellValue(data.get("samplePoint"));
            row.createCell(4).setCellValue(data.get("sampleType"));
            row.createCell(5).setCellValue(data.get("mixCtValue"));
            row.createCell(6).setCellValue(data.get("mixTimeEdit"));
            row.createCell(7).setCellValue(data.get("risk"));
            row.createCell(8).setCellValue(data.get("singleSampleReson"));
            row.createCell(9).setCellValue(data.get("ctValue"));
            row.createCell(9).setCellValue(data.get("singleInfectedTime"));
            //将数据库的数据遍历出来

            //序号自增
            cell++;
        }
        //设置文档名称，这儿写死了，也可以前端传输（前端传一个文件名到后端就行）
        String fileName = "test.pdf";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ServletOutputStream fileOS = null;
        try {
            //文件名编码格式
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/pdf;charset=utf-8");

            //设置标头
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            fileOS = response.getOutputStream();
            wb.write(outputStream);
            byte[] bytes = outputStream.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            X2PDF.xlsxToPdf(inputStream,fileOS);

            System.out.println("convert success");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
                fileOS.flush();
                fileOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
