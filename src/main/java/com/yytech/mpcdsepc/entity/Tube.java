package com.yytech.mpcdsepc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("MPCDSEPC_Tube")
public class Tube {
    @TableId(type = IdType.NONE,value = "TubeId")
    private String id;
    @TableField(value = "SamplePointName")
    private String samplePointName;
    private String samplingType;
    private String discoveryMethod;
    private int mixedCT;
    private String district;
    private String street;
    private String mixDetectionTime;
}
