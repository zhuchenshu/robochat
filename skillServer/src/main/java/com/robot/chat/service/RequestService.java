package com.robot.chat.service;

import com.robot.chat.dto.Music;

public interface RequestService {
    Music getResponse(String singer, String songName);
}
