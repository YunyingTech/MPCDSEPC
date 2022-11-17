package com.yytech.mpcdsepc.entity;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:29
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Integer sampleID;
    private String discoveryMethod;
    private String DistrictEdit;
    private String StreetEdit;
    private String pointEdit;
    private String samplingTypeEdit;
    private Integer mixedCT;
    private String mixTimeEdit;
    private String riskElement;
    private String testResultEdit;
    private Integer singleCTEdit;
    private String singleTestTimeEdit;
    private String tubeID;
}
