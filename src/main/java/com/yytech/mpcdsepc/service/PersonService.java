package com.yytech.mpcdsepc.service;/*
 *@Author Fan
 *@Create 2022-10-27 下午 10:08
 */

import com.yytech.mpcdsepc.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> getPersonByName(String name);
    Person getPersonById(String ID);
    List<Person> getPersonByTubeId(int id);
    List<Person> getPersonByAccountId(int id);

    void insertPerson(Person person);
    void updatePerson(Person person);
    void deletePersonById(int id);
}
