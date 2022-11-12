package com.yytech.mpcdsepc.controller.api.document;

import com.yytech.mpcdsepc.file.xlsxLoad.LoadPersonXlsxToDB;
import com.yytech.mpcdsepc.utils.LockUtil;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocumentController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public static String upload(HttpServletRequest httpServletRequest, @RequestParam("filename") String filename) {
        try (InputStream inputStream = httpServletRequest.getInputStream()) {
            String retFileName = UUID.randomUUID().toString();
            System.out.println(retFileName);
            File file = new File("Files\\" + retFileName);
            if (!file.getParentFile().exists()) {
                boolean mkdirsSuccess = file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream("Files\\" + retFileName, false);
            fileOutputStream.write(inputStream.readAllBytes());
            fileOutputStream.close();
            return inputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * POST /mpcdsepc/document/tubelist : 获取单管数据
     * 获取单管数据
     *
     * @param token 验证身份 (required)
     * @return 成功 (status code 200)
     */
    @RequestMapping(value = "/tubelist",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public String mpcdsepcDocumentTubelistPost(@CookieValue("token") String token, @RequestBody Map<String, String> map) {
        return "";
    }

    @Resource
    LoadPersonXlsxToDB loadPersonXlsxToDB;

    /**
     * 测试 文件上转解析到数据库
     *
     * @return
     * @throws FileNotFoundException
     */
    @RequestMapping("/uploadPerson")
    public String uploadPerson() throws FileNotFoundException {
        loadPersonXlsxToDB.openXlsxAndProcess(new FileInputStream("E:\\code\\MPCDSEPC\\src\\main\\resources\\upload\\person_example.xlsx"));

        return "ok";
    }

    @RequestMapping("/uploadTube")
    public String uploadTube() throws FileNotFoundException {
        loadPersonXlsxToDB.openXlsxAndProcess(new FileInputStream("E:\\code\\MPCDSEPC\\src\\main\\resources\\upload\\tube_example.xlsx"));

        return "ok";
    }

    /**
     * 是否被锁
     * @param map
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/isLockData", produces = {"application/json"}, method = RequestMethod.POST)
    public boolean isLockData(@RequestBody Map<String,String> map) throws JSONException {
        return LockUtil.isLockDataUtil(map.get("tubeId") + map.get("personId"));
    }

    /**
     * 加入锁
     * @param map
     * @return
     */
    @RequestMapping(value = "/lockData", produces = {"application/json"}, method = RequestMethod.POST)
    public String lockData(@RequestBody Map<String,String> map) {
        LockUtil.lockDataUtil(map.get("tubeId"), map.get("personId"), map.get("accountId"));
        return "ok";
    }

    /**
     * 删除锁
     * @param map
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/unLockData", produces = {"application/json"}, method = RequestMethod.POST)
    public String unLock(@RequestBody Map<String,String> map) throws JSONException {
        LockUtil.unLockDataUtil(map.get("tubeId") + map.get("personId"));
        return "ok";
    }
}