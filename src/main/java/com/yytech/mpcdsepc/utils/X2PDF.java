package com.yytech.mpcdsepc.utils;

import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.*;

/**
 * PDF转换工具类  将docx xlsx转换为pdf格式
 */
public class X2PDF {
    public static String wordToPdf(String fileName,String orgPath,String toPath,String lastSuffix) throws FileNotFoundException {
        String targetFile = fileName.replace(lastSuffix,"pdf");
        FileInputStream inFile = new FileInputStream(orgPath + fileName);
        FileOutputStream outFile = new FileOutputStream(toPath + targetFile);

        // 验证License 若不验证则转化出的pdf文档会有水印产生
//        if (!getLicense()) {
//            return "409";
//        }
        try {
            // 将源文件保存在com.aspose.words.Document中，具体的转换格式依靠里面的save方法
            com.aspose.words.Document doc = new com.aspose.words.Document(inFile);

            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,EPUB, XPS, SWF 相互转换
            doc.save(outFile, SaveFormat.PDF);

            System.out.println("word转换完毕");
        } catch (Exception e) {
            e.printStackTrace();
            return "409";
        }finally {
            if (outFile != null) {
                try {
                    outFile.flush();
                    outFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return toPath+targetFile;
    }

    public static String xlsxToPdf(String fileName,String orgPath,String toPath,String lastSuffix) throws FileNotFoundException {
        String targetFile = fileName.replace(lastSuffix,"pdf");
        FileInputStream inputStream = new FileInputStream(orgPath + fileName);
        FileOutputStream outputStream = new FileOutputStream(toPath + targetFile);

        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getExeclLicense()) {
            return "409";
        }
        try {
            com.aspose.cells.Workbook wb = new com.aspose.cells.Workbook(inputStream);// 原始excel路径

            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(false);


            int[] autoDrawSheets={3};
            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
            autoDraw(wb,autoDrawSheets);

            int[] showSheets={0};
            //隐藏workbook中不需要的sheet页。
            printSheetPage(wb,showSheets);
            wb.save(outputStream, pdfSaveOptions);
            outputStream.flush();
            outputStream.close();
            System.out.println("excel转换完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toPath+targetFile;
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     * @param wb
     * @param page 自动拉伸的页的sheet数组
     */
    public static void autoDraw(com.aspose.cells.Workbook wb,int[] page){
        if(null!=page&&page.length>0){
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }

    /**
     * 隐藏workbook中不需要的sheet页。
     *
     * @param wb
     * @param page 显示页的sheet数组
     */
    public static void printSheetPage(com.aspose.cells.Workbook wb, int[] page) {
        for (int i = 1; i < wb.getWorksheets().getCount(); i++) {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if (null == page || page.length == 0) {
            wb.getWorksheets().get(0).setVisible(true);
        } else {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }

    private static boolean getExeclLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            com.aspose.cells.License aposeLic = new com.aspose.cells.License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 官方文档的要求 无需理会
    private static boolean getLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
