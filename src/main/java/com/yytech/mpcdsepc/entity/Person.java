package com.yytech.mpcdsepc.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("MPCDSEPC_Person")
public class Person {
    @TableId(type = IdType.NONE,value = "PersonID")
    private String ID;               // 身份证号
    private String name;             // 姓名
    private String phone;         // 电话号码
    private String district;
    private String detailedAddress;
    private String job;              // 职业
    private String comeFrom;         // 从哪来
    private Boolean highRiskArea;    // 近期是否去过中高风险，接触阳性人员，到访重点场所
    private Boolean vaccine;         // 新冠疫苗接种情况
    private Boolean haveBeenInfected;          // 是否感染过新冠
    private Boolean receiveStatus;   // 接收情况
    private String ModeOfInfection;
    private String samplingPoint;    // 采样点
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String samplingTime;       // 采样日期
    private String diagnosisTime;
    private String symptomType;
    private String isLocked;
    @TableField("ManagerID")
    private int managerId;
    @TableField(fill = FieldFill.INSERT)
    private Date deliverTime;
}

