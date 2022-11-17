package com.yytech.mpcdsepc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPersonHistory {
    private Integer EditPersonID;
    private String PersonID;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date EditTime;
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
    private Integer managerId;
}
