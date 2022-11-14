package com.yytech.mpcdsepc.entity;

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
@TableName("MPCDSEPC_Person")
public class Person {
    public String ID;               // 身份证号
    public String name;             // 姓名
    public String phoneNum;         // 电话号码
    public String country;          // 区县
    public String address;          // 目前详细地址
    public String job;              // 职业
    public String comeFrom;         // 从哪来
    public boolean highRiskArea;    // 近期是否去过中高风险，接触阳性人员，到访重点场所
    public boolean vaccine;         // 新冠疫苗接种情况
    public boolean infect;          // 是否感染过新冠
    public String samplingPoint;    // 采样点
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date samplingDate;       // 采样日期
    public boolean isFinished;      // 是否回访
    public int accountId;           // 负责账号id
    public int tubeId;              // 所属混管ID
}

