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
@TableName("MPCDSEPC_CorrespondTP")
public class CorrespondTP {
    @TableId(type = IdType.ASSIGN_ID)
    private String sampleId;
    private String personId;
    private String tubeId;
}
