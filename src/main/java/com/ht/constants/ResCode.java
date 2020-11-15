package com.ht.constants;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public enum ResCode {
    OK(200, "OK"),
    // 通用异常
    REQUEST_ERROR(400, "请求异常，请检查入参后再次调用"),
    NO_SERVICE(404, "未找到资源"),
    UNSPECIFIED(500, "服务异常，请稍后再试"),


    // 用户相关错误
    REGISTER_FAILED(400000, "注册失败"),
    USERNO_EXIST(400001, "该手机号已注册"),
    VERIFY_CODE_ERROR(400002, "验证码错误"),
    VERIFY_CODE_REQUIRED(400002, "请输入验证码"),
    USERNO_PASSWORD_ERROR(400003, "用户名或密码错误"),


    // 企业查询相关
    ENTERPRISENAME_EXISTS(500000, "企业名称已存在"),
    ENTERPRISE_NOT_EXIST(500000, "该企业不存在"),
    ENTERPRISE_ID_NULL(500001,"企业ID为空"),

    // 研发项目
    PROJECT_ID_NULL(600000,"项目ID为空"),
    PROJECT_ID_FORMAT_NOT_MATCH(600001,"项目ID格式不符合"),
    PROJECT_ID_NOT_EXIST(600002,"项目ID不存在"),
    PROJECT_NAME_NULL(600003,"项目名称为空"),

    ;


    private final int code;
    private final String desc;

    ResCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
