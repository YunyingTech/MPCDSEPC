package com.yytech.mpcdsepc.entity;

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
    private String tubeId;
    private String samplingPointName;
    private String samplingType;
    private String discoveryMethod;
    private int mixedCT;
    private String district;
    private String street;
    private String mixDetection;
}
