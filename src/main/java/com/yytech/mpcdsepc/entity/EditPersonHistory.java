package com.yytech.mpcdsepc.entity;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:16
 */

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
@TableName("MPCDSEPC_EditPersonHistory")
public class EditPersonHistory {
    @TableId(type = IdType.NONE)
    private Integer editPersonID;
    private String personId;
    private int managerId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String editTime;
    private String name;
    private String phone;
    private String district;
    private String detailedAddress;
    private String job;
    private String comeFrom;
    private boolean highRiskArea;
    private boolean vaccine;
    private boolean haveBeenInfected;
    private String samplingPoint;
    private String samplingTime;

    public EditPersonHistory(Integer editPersonID,Person person,Integer managerId,String editTime){
        this.editPersonID=editPersonID;
        this.managerId=managerId;
        this.personId=person.getID();
        this.editTime=editTime;
        this.name=person.getName();
        this.phone=person.getPhone();
        this.district=person.getDistrict();
        this.detailedAddress=person.getDetailedAddress();
        this.job=person.getJob();
        this.comeFrom=person.getComeFrom();
        this.highRiskArea=person.isHighRiskArea();
        this.vaccine=person.isVaccine();
        this.haveBeenInfected=person.isHaveBeenInfected();
        this.samplingPoint=person.getSamplingPoint();
        this.samplingTime=person.getSamplingTime();
    }
}
