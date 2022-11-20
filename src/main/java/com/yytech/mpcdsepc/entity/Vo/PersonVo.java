package com.yytech.mpcdsepc.entity.Vo;

import com.yytech.mpcdsepc.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVo extends Person {
    private String key;
    private Long expireTime;
}
