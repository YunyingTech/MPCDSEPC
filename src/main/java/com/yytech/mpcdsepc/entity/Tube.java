package com.yytech.mpcdsepc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;

@Data
@ToString
@Getter
@Setter
@TableName("MPCDSEPC_Tube")
public class Tube {
    @TableId(type = IdType.NONE,value = "TubeId")
    private String id;
    @TableField(value = "SamplepointName")
    private String SamplePointName;
    private String SamplingType;
    private String DiscoveryMethod;
    private int MixedCT;
    private String District;
    private String Street;
    private String MixDetectionTime;
}
