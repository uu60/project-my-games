package com.octopus.mygamesbackend.service;

import com.octopus.mygamesbackend.pojo.dao.ChatRoomRecord;
import com.octopus.mygamesbackend.pojo.vo.ChatRoomRecordVo;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoomRecordVo> getHistoryRecords();
}
