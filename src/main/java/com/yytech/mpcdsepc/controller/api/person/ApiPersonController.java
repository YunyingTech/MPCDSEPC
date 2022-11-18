package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yytech.mpcdsepc.entity.CorrespondTP;
import com.yytech.mpcdsepc.entity.EditPersonHistory;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.CorrespondTPService;
import com.yytech.mpcdsepc.service.PersonService;
import com.yytech.mpcdsepc.service.impl.EditPersonHistoryServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Lettle
 * Create by 2022/10/28 17:40
 */
@RestController
@RequestMapping(value = "/mpcdsepc/api/person")
public class ApiPersonController {
    @Resource
    private PersonService personService;

    @Resource
    private EditPersonHistoryServiceImpl editPersonHistoryService;

    @Resource
    private CorrespondTPService correspondTPService;

    @GetMapping("getPersonByName")
    public Result getPersonByName(@RequestParam String name) {
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Person::getName,name);
        List<Person> PersonList = personService.list(lambdaQueryWrapper);
        return Result.ok(PersonList);
    }

    @GetMapping("getPersonById/{id}")
    public Result getPersonById(@PathVariable String id) {
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Person::getID,id);
        List<Person> list = personService.list(lambdaQueryWrapper);
        if (list.size() > 0) {
            return Result.ok(list);
        }
        return Result.fail("无数据");
    }

//    @GetMapping("getPersonByTubeId")
//    public Result getPersonByTubeId(@RequestParam("tubeId") int ID) {
//        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Person::getTubeId,ID);
//        List<Person> PersonList = personService.list(lambdaQueryWrapper);
//        return Result.ok(PersonList);
//    }

//    @GetMapping("getPersonByAccountId")
//    public Result getPersonByAccountId(@RequestParam("accountId") int ID) {
//        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Person::getAccountId,ID);
//        List<Person> PersonList = personService.list(lambdaQueryWrapper);
//        return Result.ok(PersonList);
//    }

    @PostMapping("insertPerson/{tubeId}")
    @Transactional(rollbackFor = Exception.class)
    public Result insertPerson(@RequestBody Person person,@PathVariable String tubeId) {
        boolean flag1 = personService.save(person);
        CorrespondTP correspondTP = new CorrespondTP();
        correspondTP.setPersonId(person.getID());
        correspondTP.setTubeId(tubeId);
        boolean flag2 = correspondTPService.save(correspondTP);
        if(flag1 && flag2) {
            return Result.ok();
        } else {
            return Result.build(400,"ID重复");
        }
    }

    /**
     *
     * @param person
     * @param editPersonID  编辑记录编号
     * @return
     */
    @PutMapping("updatePerson")
    public Result updatePerson(@RequestParam Person person,@RequestParam Integer editPersonID,@RequestParam Integer managerID) {
        // 编辑操作日志记录 传入参数 编辑编号 编辑者id person
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentTime = simpleFormatter.parse(new Date().toString());
            EditPersonHistory editPersonHistory = new EditPersonHistory(editPersonID, person, managerID, currentTime.toString());
            editPersonHistoryService.save(editPersonHistory);
        } catch (ParseException e) {
            System.out.println("格式转换失败");
            throw new RuntimeException(e);
        }

        boolean flag = personService.updateById(person);
        if(flag) {
            return Result.ok();
        } else {
            return Result.build(400,"update failed");
        }
    }

    @DeleteMapping("deletePersonById")
    public Result deletePersonById(@RequestParam int id) {
        boolean flag = personService.removeById(id);
        if(flag) {
            return Result.ok();
        } else {
            return Result.build(400,"delete failed");
        }
    }

    @GetMapping("/exportData/{id}")
    public void exportData(HttpServletResponse response,@PathVariable String id){
        personService.exportData(response,id);
    }


}
