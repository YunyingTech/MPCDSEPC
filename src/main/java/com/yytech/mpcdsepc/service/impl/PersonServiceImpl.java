package com.yytech.mpcdsepc.service.impl;/*
 *@Author Fan
 *@Create 2022-10-27 下午 10:07
 */

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Resource
    public PersonMapper mapper;

    @Override
    public List<Person> getPersonByName(String name) {
        return mapper.getPersonByName(name);
    }

    @Override
    public Person getPersonById(String ID) {
        return mapper.getPersonById(ID);
    }

    @Override
    public List<Person> getPersonByTubeId(int id) {
        return mapper.getPersonByTubeId(id);
    }

    @Override
    public List<Person> getPersonByAccountId(int id) {
        return mapper.getPersonByAccountId(id);
    }

    @Override
    public boolean insertPerson(Person person) {
        try {
            mapper.insertPerson(person);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        try {
            mapper.updatePerson(person);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePersonById(int id) {
        try {
            mapper.deletePersonById(id);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
