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
@TableName("MPCDSEPC_EditSampleHistory")
public class EditSampleHistory {
    @TableId(type = IdType.NONE)
    private Integer editSampleId;
    private int sampleId;
    private int managerId;
    private String discoveryMethod;
    private String districtEdit;
    private String streetEdit;
    private String pointEdit;
    private String samplingTypeEdit;
    private int mixedCT;
    private String mixedTimeEdit;
    private String riskElement;
    private String testResultEdit;
    private int singleCTEdit;
    private String singleTestTimeEdit;
    private Date editTime;
}
