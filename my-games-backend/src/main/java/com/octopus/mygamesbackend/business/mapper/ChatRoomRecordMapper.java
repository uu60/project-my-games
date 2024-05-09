package com.octopus.mygamesbackend.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.octopus.mygamesbackend.business.pojo.dao.ChatRoomRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomRecordMapper extends BaseMapper<ChatRoomRecord> {
    void addRecord(ChatRoomRecord record);
    List<ChatRoomRecord> getHistoryRecords();
}
