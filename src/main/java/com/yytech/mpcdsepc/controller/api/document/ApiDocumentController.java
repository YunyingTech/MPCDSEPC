package com.yytech.mpcdsepc.controller.api.document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.service.TubeService;
import com.yytech.mpcdsepc.utils.LockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocumentController {

    @Autowired
    private TubeService tubeService;

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

    @RequestMapping(value = "/delTube",method = RequestMethod.POST,produces = "application/json")
    public String deleteTube(@RequestParam("tubeId") int id){
        tubeService.deleteTube(id);
        return "ok";
    }

    @RequestMapping(value = "/getTubeData",method = RequestMethod.POST,produces = "application/json")
    public String getTubeData(@RequestParam("tubeId") int id){
        Tube tube = tubeService.getTubeById(id);
        JSONObject ret = new JSONObject();
        JSONObject tubeData = new JSONObject();
        JSONArray tubes = new JSONArray();
        if(tube != null){
            tubeData.put("tubeId",tube.getId());
            tubeData.put("createId",tube.getCreatorId());
            tubeData.put("lastModifierId",tube.getLastModifierId());
            tubeData.put("rollbackTimes",tube.getRollbackTimes());
            tubeData.put("createDate",tube.getCreateDate().toString());
            tubes.add(tubeData);
            ret.put("tubes",tubes);
            return ret.toJSONString();
        }
        else{
            tubes.add(tubeData);
            ret.put("tubes",tubes);
            return ret.toJSONString();
        }
    }
}