/*
package com.yytech.mpcdsepc.file.xlsxLoad;

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.service.PersonService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

*/
/**
 * load person到数据库
 *//*

@Component
public class LoadPersonXlsxToDB extends LoadXlsxToDB{
    @Resource
    private PersonService personService;

    @Override
    public int processXlsx(XSSFSheet sheet) {
        int maxRow = sheet.getLastRowNum();
//        System.out.println(maxRow);
        Person[] persons = new Person[maxRow];
        for (int i=0;i<maxRow;i++){
            persons[i]=new Person();
        }

        for (int row=1;row<=maxRow;row++){
//            System.out.println(sheet.getRow(row).getCell(0).getStringCellValue());
            persons[row-1].setID(sheet.getRow(row).getCell(0).getStringCellValue());
            persons[row-1].setName(sheet.getRow(row).getCell(1).toString());
//            System.out.println(sheet.getRow(row).getCell(2).toString());
            persons[row-1].setPhoneNum(sheet.getRow(row).getCell(2).getStringCellValue());
            persons[row-1].setCountry(sheet.getRow(row).getCell(3).toString());
            persons[row-1].setAddress(sheet.getRow(row).getCell(4).toString());
            persons[row-1].setJob(sheet.getRow(row).getCell(5).toString());
            persons[row-1].setComeFrom(sheet.getRow(row).getCell(6).toString());
            persons[row-1].setHighRiskArea(Boolean.parseBoolean(sheet.getRow(row).getCell(7).toString()));
            persons[row-1].setVaccine(Boolean.parseBoolean(sheet.getRow(row).getCell(8).toString()));
            persons[row-1].setInfect(Boolean.parseBoolean(sheet.getRow(row).getCell(9).toString()));
            persons[row-1].setSamplingPoint(sheet.getRow(row).getCell(10).toString());
            persons[row-1].setSamplingDate(DateUtil.getJavaDate(sheet.getRow(row).getCell(11).getNumericCellValue()));
            persons[row-1].setFinished(Boolean.parseBoolean(sheet.getRow(row).getCell(12).toString()));
//            System.out.println(sheet.getRow(row).getCell(13).toString());
            persons[row-1].setAccountId((int) Double.parseDouble(sheet.getRow(row).getCell(13).toString()));
            persons[row-1].setTubeId((int) Double.parseDouble(sheet.getRow(row).getCell(14).toString()));
        }

        for (Person person: persons){
            personService.insertPerson(person);
            System.out.println(person.toString());
        }

        return 1;
    }

}
*/
