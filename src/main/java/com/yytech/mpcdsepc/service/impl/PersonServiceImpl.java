package com.yytech.mpcdsepc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yytech.mpcdsepc.entity.CorrespondTP;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.service.CorrespondTPService;
import com.yytech.mpcdsepc.service.PersonService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Autowired
    private CorrespondTPService correspondTPService;

    @Override
    public XSSFWorkbook exportData(HttpServletResponse response, String id) {
        LambdaQueryWrapper<CorrespondTP> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CorrespondTP::getTubeId,id);
        List<CorrespondTP> list = correspondTPService.list(lambdaQueryWrapper);
        List<String> personIds = list.stream().map(i -> i.getPersonId()).collect(Collectors.toList());
        List<Person> peoples = this.listByIds(personIds);
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一张表
        Sheet sheet = wb.createSheet("Student");
        //创建第一行，起始为0
        Row titleRow = sheet.createRow(0);
        //第一列
        titleRow.createCell(0).setCellValue("身份证号");
        //第二列
        titleRow.createCell(1).setCellValue("姓名");
        //第三列
        titleRow.createCell(2).setCellValue("电话号码");
        //第四列
        titleRow.createCell(3).setCellValue("所属地区");
        //第五列
        titleRow.createCell(4).setCellValue("详细地址");
        //第六列
        titleRow.createCell(5).setCellValue("职业");
        titleRow.createCell(6).setCellValue("从哪来");
        titleRow.createCell(7).setCellValue("是否去过中高风险");
        titleRow.createCell(8).setCellValue("新冠疫苗接种情况");
        titleRow.createCell(9).setCellValue("是否感染过新冠");
        titleRow.createCell(10).setCellValue("接收情况");
        titleRow.createCell(11).setCellValue("感染种类");
        titleRow.createCell(12).setCellValue("采样点");
        titleRow.createCell(13).setCellValue("采样日期");
        titleRow.createCell(14).setCellValue("诊断时间");
        titleRow.createCell(15).setCellValue("症状");
        titleRow.createCell(16).setCellValue("派发时间");
        //序号，默认为1
        int cell = 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();;
        //遍历
        for (Person person : peoples) {
            //第一行保存的是每一列的列名
            //从第二行开始保存数据
            Row row = sheet.createRow(cell);
            //第一列 序号
            row.createCell(0).setCellValue(person.getID());
            //将数据库的数据遍历出来
            //第二列 学号
            row.createCell(1).setCellValue(person.getName());
            //第三列 姓名
            row.createCell(2).setCellValue(person.getPhone());
            //第四列 身份证
            row.createCell(3).setCellValue(person.getDistrict());
            row.createCell(4).setCellValue(person.getDetailedAddress());
            row.createCell(5).setCellValue(person.getJob());
            //第五列 年龄
            row.createCell(6).setCellValue(person.getComeFrom());
            //第六列 地址
            row.createCell(7).setCellValue(person.getHighRiskArea());
            row.createCell(8).setCellValue(person.getVaccine());
            row.createCell(9).setCellValue(person.getHaveBeenInfected());
            row.createCell(10).setCellValue(person.getReceiveStatus());
            row.createCell(11).setCellValue(person.getModeOfInfection());
            row.createCell(12).setCellValue(person.getSamplingPoint());
            row.createCell(13).setCellValue(person.getSamplingTime());
            row.createCell(14).setCellValue(person.getDiagnosisTime());
            row.createCell(15).setCellValue(person.getSymptomType());
            row.createCell(16).setCellValue(simpleDateFormat.format(person.getDeliverTime()));
            System.out.println("Time=====>" + simpleDateFormat.format(person.getDeliverTime()));
            //序号自增
            cell++;
        }
        //设置文档名称，这儿写死了，也可以前端传输（前端传一个文件名到后端就行）
        String fileName = "test.xlsx";
        OutputStream outputStream = null;
        try {
            //文件名编码格式
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            //设置标头
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }
}
