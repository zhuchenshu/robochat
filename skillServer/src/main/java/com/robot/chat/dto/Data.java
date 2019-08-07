package com.robot.chat.dto;

import java.util.List;

/**
 *
 * @Description 歌曲接口返回数据类，包含歌曲列表
 * @author chongting.li
 * @version 2019/8/6
 *
 */
public class Data {
    /**
     * 歌曲列表
     */
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
