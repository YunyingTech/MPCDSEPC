package com.yytech.mpcdsepc.controller.api.document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yytech.mpcdsepc.entity.*;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.*;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @Autowired
    private AccountService accountService;

    @Resource
    private EditSampleHistoryService editSampleHistoryService;


    @GetMapping("getAlltubes")
    public Result getAllTubes(){
        return Result.ok(tubeService.list());
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
    public Result updateTube(@RequestBody Tube tube, @RequestBody PositiveSample positiveSample,@RequestBody Integer editSampleId,@RequestBody Integer managerId) {
        // 编辑操作日志记录 传入参数编辑编号 编辑者id tube positiveSample（可为空）
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentTime = simpleFormatter.parse(new Date().toString());
            EditSampleHistory editSampleHistory = new EditSampleHistory(editSampleId, positiveSample, managerId, currentTime);
            editSampleHistoryService.save(editSampleHistory);
        } catch (ParseException e) {
            System.out.println("时间格式转换失败");
            throw new RuntimeException(e);
        }

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

    @GetMapping("getTubePersons/{tubeId}/{managerId}/{currentPage}/{size}")
    public Result getTubePersons(@PathVariable String tubeId,
                                 @PathVariable int managerId,
                                 @PathVariable int currentPage,
                                 @PathVariable int size){
        LambdaQueryWrapper<CorrespondTP> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Account account = accountService.getById(managerId);
        lambdaQueryWrapper.eq(CorrespondTP::getTubeId,tubeId);
        List<CorrespondTP> list = correspondTPService.list(lambdaQueryWrapper);
        List<String> personIds = list.stream().map(i -> i.getPersonId()).collect(Collectors.toList());
        if (personIds.size() == 0) {
            return Result.fail("无数据");
        }
        if (account.getRole() == 1) {
            List<Person> peoples = personService.listByIds(personIds).stream().filter(i -> i.getReceiveStatus().booleanValue() == true).collect(Collectors.toList());
            return page(currentPage, size, peoples);
        }
        if (account.getRole() == 3) {
            List<Person> peoples = personService.listByIds(personIds).stream().filter(i -> i.getReceiveStatus().booleanValue() == true && i.getManagerId() ==  managerId).collect(Collectors.toList());
            return page(currentPage, size, peoples);
        }
        List<Person> peoples = personService.listByIds(personIds).stream().filter(i -> i.getReceiveStatus().booleanValue() == true && i.getDistrict().equals(account.getManageDistrict())).collect(Collectors.toList());
        return page(currentPage, size, peoples);
    }



    @GetMapping("getTubesDate")
    public Result getTubesByDate(){
      LambdaQueryWrapper<Tube> lambdaQueryWrapper = new LambdaQueryWrapper<>();
      lambdaQueryWrapper.groupBy(Tube::getCreateTime).select(Tube::getCreateTime);
        List<Map<String, Object>> maps = tubeService.listMaps(lambdaQueryWrapper);
        return Result.ok(maps);
    }

    @GetMapping("getTubesByDate")
    public Result getTubesByDate(@RequestParam String date){
        LambdaQueryWrapper<Tube> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tube::getCreateTime,date);
        List<Tube> list = tubeService.list(lambdaQueryWrapper);
        return Result.ok(list);
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

    /**
     * 获取当天已处理数量
     *
     * @param district district区，留空时为所有区
     * @return
     */
    @GetMapping("/getReceivedNum")
    public Result getReceivedNum(String district) {
        int receivedNum = 0;
        QueryWrapper<Person> wrapper = Wrappers.query();
        if (!"".equals(district)) {
            wrapper.eq("district", district);
        }
        wrapper.eq("receiveStatus", "1");
        wrapper.isNotNull("deliverTime");
        List<Person> personList = personService.list(wrapper);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataNow = new Date();
        simpleDateFormat.format(dataNow);
        for (Person person : personList) {
            if (dataNow.getDay() == person.getDeliverTime().getDay()) {
                receivedNum++;
            }
        }
        return Result.ok(receivedNum);
    }

    /**
     * 获取纠纷数量
     *
     * @param district district区，留空时为所有区
     * @return
     */
    @GetMapping("getDisputeNum")
    public Result getDisputeNum(String district) {
        int disputeNum = 0;
        QueryWrapper<Person> wrapper = Wrappers.query();
        if (!"".equals(district)) {
            wrapper.eq("district", district);
        }
        wrapper.eq("receiveStatus", "0");
        wrapper.isNotNull("deliverTime");
        List<Person> personList = personService.list(wrapper);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataNow = new Date();
        simpleDateFormat.format(dataNow);
        for (Person person : personList) {
            if (dataNow.getTime() - person.getDeliverTime().getTime() > 900000) {
                disputeNum++;
            }
        }
        return Result.ok(disputeNum);
    }

    private Result<? extends Serializable> page(int currentPage, int size, List<Person> peoples) {
        if (peoples.size() == 0) {
            return Result.fail("无数据");
        }
        Page<Person> page = new Page<>(currentPage, size);
        int count = peoples.size();
        List<Person> pageList = new ArrayList<>();
//计算当前页第一条数据的下标
        int currId = currentPage >1 ? (currentPage -1)* size :0;
        for (int i = 0; i< size && i<count - currId; i++){
            pageList.add(peoples.get(currId+i));
        }
        page.setSize(size);
        page.setCurrent(currentPage);
        page.setTotal(count);
//计算分页总页数
        page.setPages(count %10 == 0 ? count/10 :count/10+1);
        page.setRecords(pageList);
        //DO NOT CHANGE IT AGAIN
        return Result.ok(page);
    }
}