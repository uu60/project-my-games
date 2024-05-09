package com.octopus.mygamesbackend.business.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_chat_room_record")
@AllArgsConstructor
public class ChatRoomRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private String username;
    @TableField("create_time")
    private Date createTime;

    public ChatRoomRecord(String content, String username, Date createTime) {
        this.content = content;
        this.username = username;
        this.createTime = createTime;
    }
}
