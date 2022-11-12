package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return Result.ok(personService.getPersonByName(name));
    }

    @GetMapping("getPersonById")
    public Result getPersonById(@RequestParam String ID) {
        return Result.ok(personService.getPersonById(ID));
    }

    @GetMapping("getPersonByTubeId")
    public Result getPersonByTubeId(@RequestParam("tubeId") int ID) {
        return Result.ok(personService.getPersonByTubeId(ID));
    }

    @GetMapping("getPersonByAccountId")
    public Result getPersonByAccountId(@RequestParam("accountId") int ID) {
        return Result.ok(personService.getPersonByAccountId(ID));
    }

    @PostMapping("insertPerson")
    public Result insertPerson(@RequestBody Person person) {
        if(personService.insertPerson(person)) {
            return Result.ok();
        } else {
            return Result.build(400,"ID重复");
        }
    }

    @PutMapping("updatePerson")
    public Result updatePerson(@RequestParam Person person) {
        if(personService.updatePerson(person)) {
            return Result.ok();
        } else {
            return Result.build(400,"update failed");
        }
    }

    @DeleteMapping("deletePersonById")
    public Result deletePersonById(@RequestParam int id) {
        if(personService.deletePersonById(id)) {
            return Result.ok();
        } else {
            return Result.build(400,"delete failed");
        }
    }


}
