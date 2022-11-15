package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yytech.mpcdsepc.entity.CorrespondTP;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.CorrespondTPService;
import com.yytech.mpcdsepc.service.PersonService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PutMapping("updatePerson")
    public Result updatePerson(@RequestParam Person person) {
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


}
