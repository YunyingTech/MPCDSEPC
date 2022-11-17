package com.yytech.mpcdsepc.entity;/*
 *@Author Fan
 *@Create 2022-11-14 下午 4:16
 */

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "editSampleId")
    private int editSampleId;
    @JSONField(name = "sampleId")
    private int sampleId;
    @JSONField(name = "managerId")
    private int managerId;
    @JSONField(name = "discoveryMethod")
    private String discoveryMethod;
    @JSONField(name = "districtEdit")
    private String districtEdit;
    @JSONField(name = "streetEdit")
    private String streetEdit;
    @JSONField(name = "pointEdit")
    private String pointEdit;
    @JSONField(name = "samplingTypeEdit")
    private String samplingTypeEdit;
    @JSONField(name = "mixedCT")
    private int mixedCT;
    @JSONField(name = "mixedTimeEdit")
    private String mixedTimeEdit;
    @JSONField(name = "riskElement")
    private String riskElement;
    @JSONField(name = "testResultEdit")
    private String testResultEdit;
    @JSONField(name = "singleCTEdit")
    private int singleCTEdit;
    @JSONField(name = "singleTestTimeEdit")
    private String singleTestTimeEdit;
    @JSONField(name = "editTime")
    private Date editTime;

    public EditSampleHistory(Integer editSampleId,PositiveSample positiveSample,Integer managerId,Date editTime){
        this.editSampleId=editSampleId;
        this.managerId=managerId;
        this.editTime=editTime;
        this.sampleId=positiveSample.getSampleID();
        this.discoveryMethod=positiveSample.getDiscoveryMethod();
        this.districtEdit=positiveSample.getDistrictEdit();
        this.streetEdit=positiveSample.getStreetEdit();
        this.pointEdit=positiveSample.getPointEdit();
        this.samplingTypeEdit=positiveSample.getSamplingTypeEdit();
        this.mixedCT=positiveSample.getMixedCT();
        this.mixedTimeEdit=positiveSample.getMixTimeEdit();
        this.riskElement=positiveSample.getRiskElement();
        this.testResultEdit=positiveSample.getTestResultEdit();
        this.singleCTEdit=positiveSample.getSingleCTEdit();
    }
}
