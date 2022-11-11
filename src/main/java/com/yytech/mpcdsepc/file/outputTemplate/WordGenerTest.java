package com.yytech.mpcdsepc.file.outputTemplate;

import java.util.HashMap;
import java.util.Map;

public class WordGenerTest {
    public static void main(String[] args) throws Exception {
        WordUtil.generateWord(getWordData(),"D:\\word.doc");
    }

    /**
     * 获取生成Word文档所需要的数据
     */
    private static Map<String, Object> getWordData() {
        /*
         * 创建一个Map对象，将Word文档需要的数据都保存到该Map对象中
         */
        Map<String, Object> dataMap = new HashMap<>();

        /*
         * 直接在map里保存一个用户的各项信息
         * 该用户信息用于Word文档中FreeMarker普通文本处理
         * 模板文档占位符${name}中的name即指定使用这里的name属性的值"用户1"替换
         */
        dataMap.put("time", "2022-2-2");
        dataMap.put("location1", "西安");

        return dataMap;
    }
}
