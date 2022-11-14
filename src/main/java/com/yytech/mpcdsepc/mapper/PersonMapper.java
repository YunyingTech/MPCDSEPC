package com.yytech.mpcdsepc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yytech.mpcdsepc.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
