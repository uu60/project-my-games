package com.octopus.mygamesbackend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChatRoomRecordVo {
    private String content;
    private String username;
    private String createTime;
}
