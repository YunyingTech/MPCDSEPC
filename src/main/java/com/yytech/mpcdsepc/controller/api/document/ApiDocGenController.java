/**
 * @Author: Lettle
 * @Create: 2022-11-16 14:14
 * @Description: 生成文件调用的API
 **/
package com.yytech.mpcdsepc.controller.api.document;

import com.yytech.mpcdsepc.utils.POIUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocGenController {

    @PostMapping("/wordGen")
    public String wordGen(@RequestBody Map<String, Object> json) throws Exception {
        return POIUtils.generateWord(json,"genTest.doc");
    }
}
