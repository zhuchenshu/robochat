package com.robot.chat.dto;

/**
 *
 * @Description 音乐请求类，记录机器人server发送的json
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class MusicRequest {
    /**
     * 请求字符串
     */
    public String query;
    /**
     * NLU结果类
     */
    public Nlu nlu;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Nlu getNlu() {
        return nlu;
    }

    public void setNlu(Nlu nlu) {
        this.nlu = nlu;
    }
}
