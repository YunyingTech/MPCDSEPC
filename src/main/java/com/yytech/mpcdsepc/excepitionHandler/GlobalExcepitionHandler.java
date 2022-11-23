package com.yytech.mpcdsepc.excepitionHandler;

import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.utils.StatusCodeUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExcepitionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result DuplicateHandler(){
        return Result.build(StatusCodeUtil.DuplicateKey,"新增数据异常");
    }
}
