package com.yytech.mpcdsepc.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author liu
 * load前端上传的xlsx文件，存入到数据库中
 */
@Component
public abstract class LoadXlsxToDB {

    /**
     * 读取xlsx文件
     * @return 是否成功打开读取
     */
    public int openXlsxAndProcess(FileInputStream xlsxFile){
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(xlsxFile);
            int sheetNum = xssfWorkbook.getNumberOfSheets();
//            System.out.println("xlsx共"+sheetNum+"sheet");
            for (int i=0;i<sheetNum;i++){
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                processXlsx(sheet);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return 1;
    }

    public abstract int processXlsx(XSSFSheet sheet);
}
