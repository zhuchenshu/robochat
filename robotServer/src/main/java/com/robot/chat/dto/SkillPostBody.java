package com.robot.chat.dto;

/**
 * 技能发送body
 */
public class SkillPostBody {
    private String query;
    private Nlu nlu;

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
