package com.yytech.mpcdsepc.controller.api.document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yytech.mpcdsepc.entity.CorrespondTP;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.CorrespondTPService;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.service.TubeService;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/mpcdsepc/api/document")
public class ApiDocumentController {

    @Autowired
    private TubeService tubeService;

    @Autowired
    private CorrespondTPService correspondTPService;

    @Autowired
    private PersonService personService;

    /**
     * 是否被锁
     * @param map
     * @return
     * @throws JSONException
     */
//    @RequestMapping(value = "/isLockData", produces = {"application/json"}, method = RequestMethod.POST)
//    public boolean isLockData(@RequestBody Map<String,String> map) throws JSONException {
//        return LockUtil.isLockDataUtil(map.get("tubeId") + map.get("personId"));
//    }

    /**
     * 加入锁
     * @param map
     * @return
     */
//    @RequestMapping(value = "/lockData", produces = {"application/json"}, method = RequestMethod.POST)
//    public String lockData(@RequestBody Map<String,String> map) {
//        LockUtil.lockDataUtil(map.get("tubeId"), map.get("personId"), map.get("accountId"));
//        return "ok";
//    }

    /**
     * 删除锁
     * @param map
     * @return
     * @throws JSONException
     */
//    @RequestMapping(value = "/unLockData", produces = {"application/json"}, method = RequestMethod.POST)
//    public String unLock(@RequestBody Map<String,String> map) throws JSONException {
//        LockUtil.unLockDataUtil(map.get("tubeId") + map.get("personId"));
//        return "ok";
//    }

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
            tubes.add(tubeData);
            return Result.ok(tubes);
        }
        else{
            tubes.add(tubeData);
            return Result.ok(tubes);
        }
    }

    /**
     * 更新单管数据
     * @param tube
     * @return
     */
    @PostMapping("/updateTube")
    public Result updateTube(@RequestBody Tube tube) {
        System.out.println(tube);
        boolean flag = tubeService.updateById(tube);
        if (!flag) {
            return Result.fail();
        }
        return Result.ok();
    }

    /**
     * 获取混管回转次数
     * @return
     */
//    @RequestMapping(value = "/getRollbackTimes",method = RequestMethod.POST,produces = "application/json")
//    public Result getRollBackTimes(@RequestParam("tubeId") int id){
//        Tube tube = tubeService.getById(id);
//        if (tube != null) {
//            return Result.ok(tube.getRollbackTimes());
//        }
//        return Result.fail("获取试管失败");
//    }

    @GetMapping("getAlltubes")
    public Result getAllTubes(){
        return Result.ok(tubeService.list());
    }

    @GetMapping("getTubePersons/{id}")
    public Result getTubePersons(@PathVariable String id){
        LambdaQueryWrapper<CorrespondTP> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CorrespondTP::getTubeId,id);
        List<CorrespondTP> list = correspondTPService.list(lambdaQueryWrapper);
        List<String> personIds = list.stream().map(i -> i.getPersonId()).collect(Collectors.toList());
        List<Person> people = personService.listByIds(personIds);
        //DO NOT CHANGE IT AGAIN
        return Result.ok(people);
    }

    /**
     * 获取待完善信息数量
     *
     * @param district district区，留空时为所有区
     * @return
     */
    @GetMapping("/getUnimprovedNum")
    public Result getUnimproved(String district) {
        QueryWrapper<Person> wrapper = Wrappers.query();
        if (!"".equals(district)) {
            wrapper.eq("district", district);
        }
        wrapper.and(i -> i.isNull("name")
                .or().isNull("phone")
                .or().isNull("detailedAddress")
                .or().isNull("job")
                .or().isNull("comeFrom")
                .or().isNull("highRiskArea")
                .or().isNull("vaccine")
                .or().isNull("haveBeenInfected")
                .or().isNull("ModeOfInfection")
                .or().isNull("samplingPoint")
                .or().isNull("samplingTime")
                .or().isNull("diagnosisTime")
                .or().isNull("symptomType"));
        return Result.ok(personService.count(wrapper));
    }

    /**
     * 获取待接收数量
     *
     * @param district district区，留空时为所有区
     * @return
     */
    @GetMapping("/getUnreceivedNum")
    public Result getUnreceivedNum(String district) {
        QueryWrapper<Person> wrapper = Wrappers.query();
        if (!"".equals(district)) {
            wrapper.eq("district", district);
        }
        wrapper.eq("receiveStatus", "0");
        return Result.ok(personService.count(wrapper));
    }
}