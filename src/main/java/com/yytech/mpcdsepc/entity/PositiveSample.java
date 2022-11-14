package com.yytech.mpcdsepc.entity;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:29
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
@TableName("MPCDSEPC_PositiveSample")
public class PositiveSample {
    private String personId;
    private String name;
    private String phone;
    private String district;
    private String detailedAddress;
    private String job;
    private String comeFrom;
    private String highRiskArea;
    private String vaccine;
    private String haveBeenInfected;
    private String modeOfInfection;
    private String samplingPoint;
    private String samplingTime;
    private String relationships;
    private String diagnosisTime;
    private String symptomType;
}
