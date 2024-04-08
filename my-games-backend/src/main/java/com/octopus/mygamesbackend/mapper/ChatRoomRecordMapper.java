package com.octopus.mygamesbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.octopus.mygamesbackend.pojo.dao.ChatRoomRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomRecordMapper extends BaseMapper<ChatRoomRecord> {
    void addRecord(ChatRoomRecord record);
    List<ChatRoomRecord> getHistoryRecords();
}
