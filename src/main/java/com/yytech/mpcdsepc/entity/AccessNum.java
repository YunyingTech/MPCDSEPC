package com.yytech.mpcdsepc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessNum {
    private String phone;
    private String name;
    private String job;
    private String pickArea;
}
