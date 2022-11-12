package com.yytech.mpcdsepc.result;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result ok(){
        return new Result(200,"success",null);
    }
    public static <T> Result<T> ok(T data){
        return new Result(200,"success",data);
    }
    public static <T> Result fail(){
        return new Result(201,"failed",null);
    }
    public static <T> Result<T> fail(T data){
        return new Result(201,"failed",data);
    }
    public static <T> Result<T> build(int code,String msg,T data){
        return new Result(code,msg,data);
    }
    public static <T> Result<T> build(int code,T data){
        return new Result(code,null,data);
    }
}
