package com.yytech.mpcdsepc.entity;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:16
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Getter
@Setter
@TableName("MPCDSEPC_EditPersonHistory")
public class EditPersonHistory {
    @TableId(type = IdType.NONE)
    private Integer editPersonID;
    private String personId;
    private int managerId;
    private Date editTime;
    private String name;
    private String phone;
    private String district;
    private String detailedAddress;
    private String job;
    private String comeFrom;
    private String highRiskArea;
    private String vaccine;
    private String haveBeenInfected;
    private String samplingPoint;
    private String samplingTime;
}
