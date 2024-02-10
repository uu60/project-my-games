package com.octopus.mygamesbackend.pojo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChatRoomRecord {
    private Integer id;
    private String content;
    private String username;
    private Date createTime;

    public ChatRoomRecord(String content, String username, Date createTime) {
        this.content = content;
        this.username = username;
        this.createTime = createTime;
    }
}
