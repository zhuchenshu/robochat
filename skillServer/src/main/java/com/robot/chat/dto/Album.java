package com.robot.chat.dto;

/**
 *
 * @Description 专辑类，记录专辑名和图片
 * @author chongting.li
 * @version 2019/8/7
 *
 */
public class Album {
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String picUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
