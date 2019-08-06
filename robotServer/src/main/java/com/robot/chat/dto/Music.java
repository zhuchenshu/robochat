package com.robot.chat.dto;

/**
 * @Description 聊天回复 音乐内容
 * @author chenshu.zhu
 * @version 2019/8/6
 */
public class Music {
    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 歌曲作者
     */
    private String author;
    /**
     * 歌曲链接
     */
    private String url;
    /**
     * 歌曲时长
     */
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
