package com.yytech.MPCDSEPC.Utils;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Users {
    private String userName;
    private String role;
    private boolean gentle;
    private String passWord;
    private String name;
    private Date createDate;
    private String teleNum;
}
