package com.yytech.mpcdsepc.util;

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.service.impl.TubeServiceImpl;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

@Component
public class LoadTubeXlsxToDB extends LoadXlsxToDB  {
    @Autowired
    public TubeServiceImpl tubeService;

    @Override
    public int processXlsx(XSSFSheet sheet) {
        int maxRow = sheet.getLastRowNum();
        Tube[] tubes = new Tube[maxRow];
        for (int i=0;i<maxRow;i++){
            tubes[i]=new Tube();
        }

        for (int row=1;row<=maxRow;row++){
            tubes[row-1].setId((int) Double.parseDouble(sheet.getRow(row).getCell(0).toString()));
            tubes[row-1].setCreateDate(DateUtil.getJavaDate(sheet.getRow(row).getCell(1).getNumericCellValue()));
            tubes[row-1].setCreatorId((int) Double.parseDouble(sheet.getRow(row).getCell(2).toString()));
            tubes[row-1].setLastModifierId((int) Double.parseDouble(sheet.getRow(row).getCell(3).toString()));

        }

        for (Tube tube: tubes){
//            tubeService.insertTube(tube);
            System.out.println(tube.toString());
        }

        return 1;
    }

    @Test
    void test() throws FileNotFoundException {
        LoadTubeXlsxToDB loadTubeXlsxToDB = new LoadTubeXlsxToDB();
        loadTubeXlsxToDB.openXlsxAndProcess(new FileInputStream("E:\\code\\MPCDSEPC\\src\\main\\resources\\upload\\tube_example.xlsx"));
    }
}
