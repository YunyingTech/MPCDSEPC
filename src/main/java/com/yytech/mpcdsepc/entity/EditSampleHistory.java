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
public class EditSampleHistory {
    private Integer editSampleID;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date editTime;
    private Integer sampleID;
    private String discoveryMethod;
    private String districtEdit;
    private String streetEdit;
    private String pointEdit;
    private String samplingTypeEdit;
    private Integer mixedCT;
    private String mixedTimeEdit;
    private String riskElement;
    private String testResultEdit;
    private Integer singleCTEdit;
    private String singleTestTimeEdit;
    private Integer managerID;
}
