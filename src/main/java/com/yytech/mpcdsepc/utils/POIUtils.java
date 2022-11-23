package com.yytech.mpcdsepc.utils;

import com.yytech.mpcdsepc.entity.CorrespondTP;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.service.CorrespondTPService;
import com.yytech.mpcdsepc.service.PersonService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class POIUtils {


    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    private final static String WORD_TEMPLATE_PATH = "./excels";

    /**
     *
     * @param dataMap   要填入数据的键值对
     * @param fileName  要生成的文件名
     * @return String 类型的文件下载链接
     */
    public static String generateWord(Map<String, Object> dataMap, String fileName) throws Exception {
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        configuration.setDirectoryForTemplateLoading(new File(WORD_TEMPLATE_PATH));
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate("word_template.ftl", "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
        return fileName;
    }

    public static boolean readExcel(MultipartFile file, String tubeId,int managerId,PersonService personService, CorrespondTPService correspondTPService) throws IOException {
        //检查文件
        int res = checkFile(file);
        if (res != 200) {
            return false;
        }
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Person> personsList = new ArrayList<>();
        List<CorrespondTP> correspondTPList = new ArrayList<>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum + 1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    Person person = new Person();
                    CorrespondTP correspondTP = new CorrespondTP();
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    person.setID(cells[0]);
                    person.setName(cells[1]);
                    person.setPhone(cells[2]);
                    person.setDistrict(cells[3]);
                    person.setDetailedAddress(cells[4]);
                    person.setComeFrom(cells[5]);
                    person.setHighRiskArea(cells[5].equals("1")?true:false);
                    person.setVaccine(cells[6].equals("1")?true:false);
                    person.setSamplingPoint(cells[7]);
                    person.setSamplingTime(cells[8]);
                    person.setManagerId(managerId);
                    correspondTP.setTubeId(tubeId);
                    correspondTP.setPersonId(cells[0]);
                    correspondTPList.add(correspondTP);
                    personsList.add(person);
                }
            }
            workbook.close();
            System.out.println(personsList);
            boolean flag1 = personService.saveBatch(personsList);
            boolean flag2 = correspondTPService.saveBatch(correspondTPList);
            return flag1 && flag2;
//            return true;
        }
        return false;
    }
    public static int checkFile(MultipartFile file) {
        //判断文件是否存在
        if(null == file){
            return 101;
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            return 102;
        }
        return 200;
    }
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == CellType.NUMERIC){
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case  BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case  BLANK: //空值
                cellValue = "";
                break;
            case  ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
