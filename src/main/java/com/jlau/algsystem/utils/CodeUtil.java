package com.jlau.algsystem.utils;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
public class CodeUtil {
    Integer code;
    String message;
    public final static CodeUtil SUCESS = new CodeUtil(200,"ok");
    public final static CodeUtil LOGIN_FAIL = new CodeUtil(10000,"用户名或密码错误");
    public final static CodeUtil SEARCH_FAILL = new CodeUtil(10002,"查询失败");
    public final static CodeUtil SAVE_FAIL = new CodeUtil(10003,"保存失败");
    public final static CodeUtil DELETE_FAIL = new CodeUtil(10004,"删除失败");
    public final static CodeUtil COMMON_FAIL = new CodeUtil(20000,"操作错误");
    public final static CodeUtil INVALID_SESSION = new CodeUtil(10005,"登录过期");
    public final static CodeUtil ACCESS_DENY = new CodeUtil(10006,"拒绝访问");
    public final static CodeUtil NOT_LOGIN = new CodeUtil(10007,"未登录");

    public CodeUtil(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
