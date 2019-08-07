package com.robot.chat.dto;

/**
 *
 * @Description 音乐类，记录音乐信息
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Music {
    /**
     * 状态码
     */
    private int code;

    /**
     * 歌曲数据类型
     */
    private Data data;

    private String playUrl;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = "https://v1.itooi.cn/netease/url?id="+ playUrl + "&quality=flac";
    }

    public String getPlayUrl() {
        return playUrl;
    }
}
