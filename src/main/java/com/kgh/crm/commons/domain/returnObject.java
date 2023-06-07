package com.kgh.crm.commons.domain;

public class returnObject {
    private String code;//处理成功或失败的标记。失败-0 成功-1
    private String msg;//提示信息
    private Object returnData;//返回其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
