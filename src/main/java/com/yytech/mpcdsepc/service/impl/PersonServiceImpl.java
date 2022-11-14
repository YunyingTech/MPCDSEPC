package com.yytech.mpcdsepc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.mapper.PersonMapper;
import com.yytech.mpcdsepc.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
}
