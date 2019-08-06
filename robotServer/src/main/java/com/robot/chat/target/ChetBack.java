package com.robot.chat.target;

public class ChetBack {
    private Integer code;
    private String msg;
    private String datatype;
    private ChetReply[] newslist;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDataType() {
        return datatype;
    }

    public void setDataType(String dataType) {
        this.datatype = dataType;
    }

    public ChetReply[] getNewslist() {
        return newslist;
    }

    public void setNewslist(ChetReply[] newslist) {
        this.newslist = newslist;
    }
}
