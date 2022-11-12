package com.yytech.mpcdsepc.controller.api.document;

/**
 * @Author: Lettle
 * @Create: 2022-11-12 16:28
 * @Description: 分析 excel
 **/

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Lettle
 * Create by 2022/11/12 16:28
 */

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiAnalysisController {

    // 服务器被上传的 excel 存储路径
    private final static String dir = "./excels/";

    @PostMapping("/excelAnalysis")
    public Map<String,Object> excelAnalysis(@RequestBody Map<String,String> req) {
        Map<String,Object > res = new HashMap<>();          // 最后返回的 JSON 结果
        try {
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(dir + req.get("filename")));

            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            res.put("sheetNum",String.valueOf(sheetNum));       // 存入返回的 JSON

            Map<String, List<Map<String,String>>> sheets = new HashMap<>();
            res.put("sheets", sheets);

            //遍历工作簿中的 sheet
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个 sheet
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                List<Map<String,String>> nowsheet = new LinkedList<>();     // 存入当前表的 JSON

                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
                List<String> row0 = new LinkedList<>();             // 储存所有列标题
                Map<String,String> nowrow = new HashMap<>();        // 储存当前行
                // 获取所有列标题
                if(maxRow>=0) {
                    int maxRol = sheet.getRow(0).getLastCellNum();
                    for(int j=0;j<maxRol;j++) {
                        row0.add(sheet.getRow(0).getCell(j)+"");
                    }
                }

                for (int row = 1; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数   ***注意：此处从1开始计数***
                    for (int rol = 0; rol < sheet.getRow(row).getLastCellNum(); rol++){
                        nowrow.put(row0.get(rol), sheet.getRow(row).getCell(rol).toString());
                    }
                    nowsheet.add(nowrow);           // 存入当前行
                    nowrow = new HashMap<>();       // 新开一行
                }

                sheets.put(sheet.getSheetName(), nowsheet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
