package com.robot.chat.dto.dtoSkill;

import java.util.List;

public class Song {
    /**
     * 歌曲在曲库中的id
     */
    private String id;
    /**
     * 歌曲名称
     */
    private String name;

    /**
     * 歌曲专辑
     */
    private Album al;

    /**
     * 演唱者列表
     */
    private List<Artist> ar;

    public List<Artist> getAr() {
        return ar;
    }

    public void setAr(List<Artist> ar) {
        this.ar = ar;
    }

    public Album getAl() {
        return al;
    }

    public void setAl(Album al) {
        this.al = al;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

