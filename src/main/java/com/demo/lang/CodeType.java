package com.demo.lang;

import com.google.common.base.MoreObjects;

/**
 * 调用短信验证码的时候可能有几种不同的用途
 *
 * @author developer.wang
 * @date 2020/3/14
 */
public enum CodeType {

    /**
     * 注册时使用
     */
    REGISTER(100000, "注册使用"),
    /**
     * 忘记密码使用
     */
    FORGET_PASSWORD(100001, "忘记密码使用"),
    /**
     * 更新手机号码使用
     */
    UPDATE_PHONE_NUMBER(100002, "更新手机号码使用");

    private final int code;

    private final String message;

    CodeType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(CodeType.class).add("code", this.code).add("message", this.message).toString();
    }
}
