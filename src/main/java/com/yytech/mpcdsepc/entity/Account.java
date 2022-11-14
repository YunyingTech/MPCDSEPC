package com.yytech.mpcdsepc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@Getter
@Setter
@TableName("MPCDSEPC_Account")
public class Account {
    @TableId(type = IdType.NONE)
    private Integer managerId;             // 唯一标识符
    private String userName;    // 用户名
    private int role;        // 角色
    private String gentle;     // 性别
    private String passWord;    // 密码
    private String managerName;        // 姓名
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;    // 创建时间
    private String teleNum;     // 电话号码
    private int isOnline;   // 当前是否在线
    private String manageDistrict;
}
