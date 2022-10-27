package com.yytech.mpcdsepc.service.impl;/*
 *@Author Fan
 *@Create 2022-10-27 下午 10:07
 */

import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    public PersonMapper mapper;

    @Override
    public List<Person> getPersonByName(String name) {
        return mapper.getPersonByName(name);
    }

    @Override
    public Person getPersonById(int id) {
        return mapper.getPersonById(id);
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
    public void insertPerson(Person person) {
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePersonById(int id) {

    }
}
