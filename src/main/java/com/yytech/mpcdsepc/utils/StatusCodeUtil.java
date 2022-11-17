package com.yytech.mpcdsepc.utils;

import java.util.HashMap;
import java.util.Map;

public class StatusCodeUtil {
    public static int OperationSuccess = 200;
    public static int OperationErr = 200;
    public static int LoginSuccess = 20010;
    public static int DelSuccess = 20020;
    public static int RegSuccess = 20030;
    public static int UpdateSuccess = 20040;
    public static int UploadSuccess = 20050;
    public static int DelError = 20021;
    public static int RegError = 20031;
    public static int UpdateError = 20041;
    public static int UploadError = 20051;
    public static int LoginError = 20011;
    public static int UnknownError = 500;

//    public static Map<String, String> statusCode = new HashMap<String, String>() {
//        {
//            /* 操作成功 状态码 200
//             * */
//            put(OperationSuccess, "OperationSuccess");
//
//            /* 登陆成功 状态码 201
//             * */
//            put(LoginSuccess, "LoginSuccess");
//
//            /* 删除成功 状态码 202
//             * */
//            put(DelSuccess, "DelSuccess");
//
//            /* 注册成功 状态码 203
//             * */
//            put(RegSuccess, "RegSuccess");
//
//            /* 更新成功 状态码 204
//             * */
//            put(UpdateSuccess, "UpdateSuccess");
//
//            /* 上传成功 状态码 205
//             * */
//            put(UploadSuccess, "UploadSuccess");
//
//            /* 删除失败 状态码 402
//             * */
//            put(DelError, "DelError");
//
//            /* 注册失败 状态码 403
//             * */
//            put(RegError, "RegError");
//
//            /* 更新失败 状态码 404
//             * */
//            put(UpdateError, "UpdateError");
//
//            /* 上传失败 状态码 405
//             * */
//            put(UploadError, "UploadError");
//
//            /* 登陆失败-账号不存在 406
//             * */
//            put(LoginAccountIdError, "LoginAccountIdError");
//
//            /* 登录失败-密码错误 407
//             * */
//            put(LoginAccountPwdError, "LoginAccountPwdError");
//
//            /* 未知错误 状态码 409
//             * */
//            put(UnknownError, "UnknownError");
//        }
//    };
}
