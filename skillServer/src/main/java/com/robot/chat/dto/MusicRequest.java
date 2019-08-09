package com.robot.chat.dto;

import java.util.ArrayList;
import java.util.List;

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
    private String query;
    /**
     * NLU结果类
     */
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

    /**
     * @param
     * @return String[0]歌手名，String[1]歌名
     * @description 返回词槽中的歌手名和歌名
     */

    public String[] getSlot() {
        String[] info = new String[2];
        List<Slot> slotList = this.getNlu().getSlots();

        /**
         * 词槽中歌名，在第一个
         */
        String firstKeyName = slotList.get(0).getName();
        /**
         * 词槽中歌手名
         */
        String secondKeyName = slotList.get(1).getName();

        if (firstKeyName != null) {
            info[1] = slotList.get(0).getValue();
        } else {
            firstKeyName = "Person";
            info[1] = "null";
        }

        if (secondKeyName != null) {
            info[0] = slotList.get(1).getValue();
        } else {
            secondKeyName = "MusicName";
            info[0] = "null";
        }
        return info;
    }
}
