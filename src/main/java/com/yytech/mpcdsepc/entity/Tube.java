package com.yytech.mpcdsepc.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Getter
@Setter
public class Tube {
    public Date createDate;
    public int id;
    public Account creator;
    public Account lastModifier;
}
