package com.robot.chat.dto;

/**
 * @Description 聊天回复
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class ChetResponse {
    /**
     * 头信息
     */
    private Header header;
    /**
     * 回复内容
     */
    private Payload payload;
    /**
     * 语义理解
     */
    private Nlu nlu;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Nlu getNlu() {
        return nlu;
    }

    public void setNlu(Nlu nlu) {
        this.nlu = nlu;
    }
}
