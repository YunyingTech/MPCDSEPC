package com.yytech.mpcdsepc.controller.api.person;/**
 * @Author: Lettle
 * @Create: 2022-10-28 17:40
 * @Description: Person 相关api
 **/

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    public void insertPerson(@RequestParam Person person) {
        personService.insertPerson(person);
    }

    @ResponseBody
    @RequestMapping(value = "/updatePerson", method = RequestMethod.PUT)
    public void updatePerson(@RequestParam Person person) {
        personService.updatePerson(person);
    }

    @ResponseBody
    @RequestMapping(value = "/deletePersonById", method = RequestMethod.DELETE)
    public void deletePersonById(@RequestParam int id) {
        personService.deletePersonById(id);
    }


}
