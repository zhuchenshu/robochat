package com.robot.chat.dto.dtoSkill;

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

