package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.PersonService;
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

    @GetMapping("getPersonByName")
    public Result getPersonByName(@RequestParam String name) {
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Person::getName,name);
        List<Person> PersonList = personService.list(lambdaQueryWrapper);
        return Result.ok(PersonList);
    }

    @GetMapping("getPersonById")
    public Result getPersonById(@RequestParam String ID) {
        Person person = personService.getById(ID);
        return Result.ok(person);
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

    @PostMapping("insertPerson")
    public Result insertPerson(@RequestBody Person person) {
        boolean flag = personService.save(person);
        if(flag) {
            return Result.ok();
        } else {
            return Result.build(400,"ID重复");
        }
    }

    @PutMapping("updatePerson")
    public Result updatePerson(@RequestParam Person person) {
        boolean flag = personService.update(person,null);
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
