package com.yytech.mpcdsepc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yytech.mpcdsepc.entity.Person;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

public interface PersonService extends IService<Person> {
    public XSSFWorkbook exportData(HttpServletResponse response, String id) throws FileNotFoundException;
}
