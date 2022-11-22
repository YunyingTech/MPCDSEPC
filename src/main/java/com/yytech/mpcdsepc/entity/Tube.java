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
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
