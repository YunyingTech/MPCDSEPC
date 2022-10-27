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
public class Account {
    private int id;             // 唯一标识符
    private String userName;    // 用户名
    private String role;        // 角色
    private boolean gentle;     // 性别
    private String passWord;    // 密码
    private String name;        // 姓名
    private Date createDate;    // 创建时间
    private String teleNum;     // 电话号码
    private boolean isOnline;   // 当前是否在线
}
