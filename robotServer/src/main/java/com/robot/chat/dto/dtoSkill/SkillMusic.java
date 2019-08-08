package com.robot.chat.dto.dtoSkill;

public class SkillMusic {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 歌曲数据类型
     */
    private Data data;
    /**
     * 歌名
     */
    private String musicName;
    /**
     * 歌手名
     */
    private String singer;
    /**
     * 播放地址
     */
    private String playUrl;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }
}
