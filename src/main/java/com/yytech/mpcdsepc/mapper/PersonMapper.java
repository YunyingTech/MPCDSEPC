package com.yytech.mpcdsepc.mapper;

import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonMapper {
    List<Person> getPersonByName(String name);
    Person getPersonById(int id);
    List<Person> getPersonByTubeId(int id);
    List<Person> getPersonByAccountId(int id);

    void insertPerson(Person person);
    void updatePerson(Person person);
    void deletePersonById(int id);

}
