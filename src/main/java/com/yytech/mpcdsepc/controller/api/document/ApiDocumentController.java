package com.yytech.mpcdsepc.controller.api.document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.TubeService;
import com.yytech.mpcdsepc.utils.LockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 删除混管信息
     * @param id 混管ID
     * @return
     */
    @RequestMapping(value = "/delTube",method = RequestMethod.POST,produces = "application/json")
    public Result deleteTube(@RequestParam("tubeId") int id){
        boolean flag = tubeService.removeById(id);
        if (!flag) {
            return Result.fail();
        }
        return Result.ok();
    }

    /**
     * 获取混管信息,如果查询不到返回空Tubes
     * @param id 混管ID
     * @return
     */
    @RequestMapping(value = "/getTubeData",method = RequestMethod.POST,produces = "application/json")
    public Result getTubeData(@RequestParam("tubeId") int id){
        Tube tube = tubeService.getById(id);
        Map<String,Object> tubeData = new HashMap<>();
        List<Map<String,Object>> tubes = new ArrayList<>();
        if(tube != null){
            tubeData.put("tubeId",tube.getTubeId());
            tubeData.put("district",tube.getDistrict());
            tubeData.put("discoveryMethod",tube.getDiscoveryMethod());
            tubeData.put("mixDetection",tube.getMixDetection());
            tubeData.put("samplingPointName",tube.getSamplingPointName());
            tubeData.put("street",tube.getStreet());
            tubes.add(tubeData);
            return Result.ok(tubes);
        }
        else{
            tubes.add(tubeData);
            return Result.ok(tubes);
        }
    }

    //TODO 需要重新写怎么判断多次派送
//    /**
//     * 获取混管回转次数
//     * @param id 混管ID
//     * @return
//     */
//    @RequestMapping(value = "/getRollbackTimes",method = RequestMethod.POST,produces = "application/json")
//    public Result getRollBackTimes(@RequestParam("tubeId") int id){
//        Tube tube = tubeService.getById(id);
//        if (tube != null) {
//            return Result.ok(tube.getRollbackTimes());
//        }
//        return Result.fail("获取试管失败");
//    }
}