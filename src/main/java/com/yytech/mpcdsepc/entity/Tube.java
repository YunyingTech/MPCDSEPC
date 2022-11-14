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
    public int id;
    public Date createDate;
    public int creatorId;
    public int lastModifierId;
    public int rollbackTimes;
}
