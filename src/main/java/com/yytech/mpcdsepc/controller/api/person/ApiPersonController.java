package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.yytech.mpcdsepc.entity.Person;
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
@Controller
@RequestMapping(value = "/mpcdsepc/api/person")
public class ApiPersonController {
    @Resource
    private PersonService personService;

    @ResponseBody
    @RequestMapping(value = "/getPersonByName", method = RequestMethod.GET)
    public List<Person> getPersonByName(@RequestParam String name) {
        return personService.getPersonByName(name);
    }

    @ResponseBody
    @RequestMapping(value = "/getPersonById", method = RequestMethod.GET)
    public Person getPersonById(@RequestParam String ID) {
        return personService.getPersonById(ID);
    }

    @ResponseBody
    @RequestMapping(value = "/getPersonByTubeId", method = RequestMethod.GET)
    public List<Person> getPersonByTubeId(@RequestParam("tubeId") int ID) {
        return personService.getPersonByTubeId(ID);
    }

    @ResponseBody
    @RequestMapping(value = "/getPersonByAccountId", method = RequestMethod.GET)
    public List<Person> getPersonByAccountId(@RequestParam("accountId") int ID) {
        return personService.getPersonByAccountId(ID);
    }

    @ResponseBody
    @RequestMapping(value = "/insertPerson", method = RequestMethod.POST)
    public Map<String, String> insertPerson(@RequestBody Person person) {
        if(personService.insertPerson(person)) {
            return null;
        } else {
            Map<String, String> ret = new HashMap<>();
            ret.put("msg","ID重复");
            ret.put("code","400");
            return ret;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updatePerson", method = RequestMethod.PUT)
    public Map<String, String> updatePerson(@RequestParam Person person) {
        if(personService.updatePerson(person)) {
            return null;
        } else {
            Map<String, String> ret = new HashMap<>();
            ret.put("msg","update failed");
            ret.put("code","400");
            return ret;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deletePersonById", method = RequestMethod.DELETE)
    public Map<String, String> deletePersonById(@RequestParam int id) {
        if(personService.deletePersonById(id)) {
            return null;
        } else {
            Map<String, String> ret = new HashMap<>();
            ret.put("msg","delete failed");
            ret.put("code","400");
            return ret;
        }
    }


}
