package com.hlx.manager.error;

public enum ErrorEnum {
    ID_NO_NULL("F001","编码不能为空",false),
    //...
    UNKNOWUN("999","未知错误",false);
    private String code;
    private String message;
    private Boolean conRetry;



    ErrorEnum(String code, String message, Boolean conRetry) {
        this.code = code;
        this.message = message;
        this.conRetry = conRetry;
    }

    public static ErrorEnum getByCode(String code){
        for (ErrorEnum errorEnum : ErrorEnum.values()){
            if (errorEnum.code.equals(code)){
                return errorEnum;
            }
        }
        return UNKNOWUN;
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getConRetry() {
        return conRetry;
    }
}

