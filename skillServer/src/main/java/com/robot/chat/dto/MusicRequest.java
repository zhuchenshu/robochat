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

    /**
     * @description 返回词槽中的歌手名和歌名
     * @param
     * @return String[0]歌手名，String[1]歌名
     */

    public String[] getSlot(){
        String[] info = new String[2];
        //人名、歌名都有
        if(this.nlu.slots.size() == 2) {
            if ((this.nlu.slots.get(0).getName()).equals("Person")) {
                info[0] = this.nlu.slots.get(0).getValue();
                info[1] = this.nlu.slots.get(1).getValue();
                return info;
            } else if ((this.nlu.slots.get(0).getName()).equals("MusicName")) {
                info[1] = this.nlu.slots.get(0).getValue();
                info[0] = this.nlu.slots.get(1).getValue();
                return info;
            }
        }
        //只有人名或者歌名
        else if(this.nlu.slots.size() == 1)
        {
            if ((this.nlu.slots.get(0).getName()).equals("Person")) {
                info[0] = this.nlu.slots.get(0).getValue();
                info[1] = " ";
                return info;
            } else if ((this.nlu.slots.get(0).getName()).equals("MusicName")) {
                info[1] = this.nlu.slots.get(0).getValue();
                info[0] = " ";
                return info;
            }
        }
        return info;
    }
}
